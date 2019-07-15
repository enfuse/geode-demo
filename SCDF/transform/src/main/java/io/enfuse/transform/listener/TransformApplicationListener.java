package io.enfuse.transform.listener;

import io.enfuse.transform.domain.Transmission;
import io.enfuse.transform.util.MessageUtils;
import org.apache.avro.generic.GenericRecord;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;


@Component
@EnableBinding(Processor.class)
@EnableSchemaRegistryClient
public class TransformApplicationListener {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private Processor processor;

  @Autowired public TransformApplicationListener(Processor processor){
    this.processor = processor;
  }

  @StreamListener(Processor.INPUT)
  @SendTo(Processor.OUTPUT)
  public Message<GenericRecord> handle(GenericMessage<String> incomingMessage){
    JSONObject jsonPayload = new JSONObject(incomingMessage.getPayload());
    logger.info("incoming payload " + jsonPayload.toString());
    Transmission transmission = new Transmission.Builder().withVehicleId(jsonPayload.get("VEH_VehicleId").toString()).withLatitude(jsonPayload.get("LOC_Latitude").toString()).withLongitude(jsonPayload.get("LOC_Longitude").toString()).withSpeed(jsonPayload.get("LOC_Speed").toString()).build();
    GenericRecord record = MessageUtils.convertToAvro(transmission);
    logger.info("publishing to kafka " + transmission.toString());
    return message(record);

  }

  public static <T> Message<T> message(T val) {
    return MessageBuilder.withPayload(val).build();
  }
}
