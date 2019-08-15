package io.enfuse.pipeline.transform.listener;

import io.enfuse.pipeline.transform.domain.Telemetry;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import org.apache.geode.cache.client.ClientCache;
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
public class TransformApplicationListener {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private Processor processor;

  private ClientCache clientCache;

  @Autowired private MeterRegistry registry;

  @Autowired
  public TransformApplicationListener(Processor processor, ClientCache clientCache) {
    this.processor = processor;
    this.clientCache = clientCache;
  }

  @StreamListener(Processor.INPUT)
  public void handle(GenericMessage<String> incomingMessage) {
    JSONObject jsonPayload = new JSONObject(incomingMessage.getPayload());
    logger.info("incoming payload " + jsonPayload.toString());

    String vehicleId;
    vehicleId = jsonPayload.get("VehicleId").toString();

    registry.counter("custom.metrics.for.transform").increment();

    registry
        .timer("Geode.Speed.throughput", "GeodeSpeed.Tag", "Time")
        .record(() -> System.out.println("timerTest"));
    String vehicleValue = clientCache.getRegion("telemetryRegion").get(vehicleId).toString();
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
