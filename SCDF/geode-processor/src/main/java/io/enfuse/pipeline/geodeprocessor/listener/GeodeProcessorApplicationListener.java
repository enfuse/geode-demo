package io.enfuse.pipeline.geodeprocessor.listener;

import io.enfuse.pipeline.geodeprocessor.domain.Telemetry;
import io.enfuse.pipeline.geodeprocessor.domain.TelemetryRepository;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Processor.class)
@Timed
public class GeodeProcessorApplicationListener {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private Processor processor;

  private TelemetryRepository telemetryRepository;

  private MeterRegistry meterRegistry;

  @Autowired
  public GeodeProcessorApplicationListener(
      Processor processor, TelemetryRepository telemetryRepository, MeterRegistry meterRegistry) {
    this.processor = processor;
    this.telemetryRepository = telemetryRepository;
    this.meterRegistry = meterRegistry;
  }

  @StreamListener(Processor.INPUT)
  public void handle(GenericMessage<String> incomingMessage) {
    JSONObject jsonPayload = new JSONObject(incomingMessage.getPayload());
    logger.info("incoming payload " + jsonPayload.toString());

    String vehicleId;
    vehicleId = jsonPayload.get("VehicleId").toString();

    meterRegistry.counter("custom.metrics.for.transform").increment();

    meterRegistry
        .timer("Geode.Speed.throughput", "Geode.Speed.Tag", "Time")
        .record(() -> System.out.println("timerTest"));
    //    String vehicleValue = clientCache.getRegion("telemetryRegion").get(vehicleId).toString();
    String vehicleValue = telemetryRepository.findById(vehicleId).toString();
    Telemetry telemetry =
        new Telemetry.Builder()
            .withVehicleId(jsonPayload.get("VehicleId").toString())
            .withLatitude(jsonPayload.get("Latitude").toString())
            .withLongitude(jsonPayload.get("Longitude").toString())
            .withSpeed(jsonPayload.get("Speed").toString())
            .withValue(vehicleValue)
            .build();

    logger.info("publishing to kafka: " + telemetry.toString());
    processor.output().send(new GenericMessage<>(new JSONObject(telemetry).toString()));
  }
}
