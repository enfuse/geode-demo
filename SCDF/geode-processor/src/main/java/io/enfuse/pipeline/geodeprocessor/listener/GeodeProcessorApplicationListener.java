package io.enfuse.pipeline.geodeprocessor.listener;

import io.enfuse.pipeline.geodeprocessor.domain.Telemetry;
import io.enfuse.pipeline.geodeprocessor.domain.TelemetryRepository;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

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

  private static final Logger logger = LogManager.getLogger(GeodeProcessorApplicationListener.class);

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
    logger.debug("incoming payload " + jsonPayload.toString());

    String vehicleId;
    final String[] vehicleValue = new String[1];
    vehicleId = jsonPayload.get("VehicleId").toString();

    meterRegistry.counter("custom.metrics.for.transform").increment();

    meterRegistry
        .timer("Geode.Speed.throughput", "Geode.Speed.Tag", "Time")
        .record(() -> vehicleValue[0] = telemetryRepository.findById(vehicleId).toString());

    //    vehicleValue = telemetryRepository.findById(vehicleId).toString();
    Telemetry telemetry =
        new Telemetry.Builder()
            .withVehicleId(jsonPayload.get("VehicleId").toString())
            .withLatitude(jsonPayload.get("Latitude").toString())
            .withLongitude(jsonPayload.get("Longitude").toString())
            .withSpeed(jsonPayload.get("Speed").toString())
            .withValue(vehicleValue[0])
            .build();

    logger.debug("publishing to kafka: " + telemetry.toString());
    processor.output().send(new GenericMessage<>(new JSONObject(telemetry).toString()));
  }
}
