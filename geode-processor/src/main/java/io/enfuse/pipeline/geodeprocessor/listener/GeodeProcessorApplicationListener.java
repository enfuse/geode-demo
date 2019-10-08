package io.enfuse.pipeline.geodeprocessor.listener;

import io.enfuse.pipeline.geodeprocessor.domain.Telemetry;
import io.enfuse.pipeline.geodeprocessor.domain.TelemetryRepository;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Processor.class)
public class GeodeProcessorApplicationListener {

  private static final Logger logger = LogManager.getLogger(GeodeProcessorApplicationListener.class);
  private TelemetryRepository telemetryRepository;
  private MeterRegistry meterRegistry;
  private final Timer timer;

  @Autowired
  public GeodeProcessorApplicationListener(
      TelemetryRepository telemetryRepository, MeterRegistry meterRegistry) {
    this.telemetryRepository = telemetryRepository;
    this.meterRegistry = meterRegistry;
    this.timer = meterRegistry.timer("Geode.Speed.throughput", "Geode.Speed.Tag", "Time");
  }

  @StreamListener(Processor.INPUT)
  @SendTo(Processor.OUTPUT)
  public Telemetry handle(Telemetry telemetry) {
    meterRegistry.counter("custom.metrics.for.transform").increment();
    long start = System.currentTimeMillis();

    final String vehicleId = telemetry.getVehicleId();
    Telemetry lookup = telemetryRepository.findById(vehicleId).orElse(new Telemetry.Builder().build());
    telemetry.setValue(lookup.getValue());
    timer.record(System.currentTimeMillis() - start, TimeUnit.MILLISECONDS);

    logger.info("publishing to kafka: " + lookup.toString());
    return telemetry;
  }
}
