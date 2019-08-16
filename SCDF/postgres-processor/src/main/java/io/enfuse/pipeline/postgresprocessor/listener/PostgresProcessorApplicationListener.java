package io.enfuse.pipeline.postgresprocessor.listener;

import io.enfuse.pipeline.postgresprocessor.domain.Telemetry;
import io.enfuse.pipeline.postgresprocessor.repository.TelemetryRepository;
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
public class PostgresProcessorApplicationListener {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private Processor processor;

  private TelemetryRepository telemetryRepository;

  @Autowired private MeterRegistry registry;

  @Autowired
  public PostgresProcessorApplicationListener(
      Processor processor, TelemetryRepository telemetryRepository) {
    this.processor = processor;
    this.telemetryRepository = telemetryRepository;
  }

  @StreamListener(Processor.INPUT)
  public void handle(GenericMessage<String> incomingMessage) {
    JSONObject jsonPayload = new JSONObject(incomingMessage.getPayload());
    logger.info("incoming payload " + jsonPayload.toString());

    String vehicleId;
    vehicleId = jsonPayload.get("VehicleId").toString();

    registry.counter("custom.metrics.for.transform").increment();

    registry
        .timer("Geode.Speed.throughput", "Geode.Speed.Tag", "Time")
        .record(() -> System.out.println("timerTest"));
    //    String vehicleValue = clientCache.getRegion("telemetryRegion").get(vehicleId).toString();
    String vehicleValue = telemetryRepository.findOneByVehicleId(vehicleId).toString();
    Telemetry telemetry = new Telemetry();
    telemetry.setVehicleId(jsonPayload.get("VehicleId").toString());
    telemetry.setLatitude(jsonPayload.get("Latitude").toString());
    telemetry.setLongitude(jsonPayload.get("Longitude").toString());
    telemetry.setSpeed(jsonPayload.get("Speed").toString());
    telemetry.setValue(vehicleValue);

    logger.info("publishing to kafka: " + telemetry.toString());
    processor.output().send(new GenericMessage<>(new JSONObject(telemetry).toString()));
  }
}
