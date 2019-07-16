package io.enfuse.pipeline.transform.listener;

import io.enfuse.pipeline.transform.domain.Transmission;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.support.GenericMessage;

@EnableBinding(Processor.class)
public class TransformApplicationListener {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @StreamListener(Processor.INPUT)
  @Output(Processor.OUTPUT)
  public Transmission handle(GenericMessage<String> incomingMessage) {
    JSONObject jsonPayload = new JSONObject(incomingMessage.getPayload());
    logger.info("incoming payload " + jsonPayload.toString());

    Transmission transmission =
        new Transmission.Builder()
            .withVehicleId(jsonPayload.get("VEH_VehicleId").toString())
            .withLatitude(jsonPayload.get("LOC_Latitude").toString())
            .withLongitude(jsonPayload.get("LOC_Longitude").toString())
            .withSpeed(jsonPayload.get("LOC_Speed").toString())
            .build();

    logger.info("publishing to kafka " + transmission.toString());
    return transmission;
  }
}
