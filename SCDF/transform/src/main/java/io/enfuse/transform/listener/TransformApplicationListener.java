package io.enfuse.transform.listener;

import com.sun.tools.javah.Gen;
import io.enfuse.transform.domain.Transmission;
import io.enfuse.transform.util.MessageUtils;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import static io.enfuse.transform.util.MessageUtils.message;

@Component
@EnableBinding(Processor.class)
@EnableSchemaRegistryClient
public class TransformApplicationListener {

  private Processor processor;

  @Autowired public TransformApplicationListener(Processor processor){
    this.processor = processor;
  }

  @StreamListener(Processor.INPUT)
  public void handle(GenericMessage<String> incomingMessage){
    JSONObject jsonPayload = new JSONObject(incomingMessage.getPayload());
    jsonPayload.put("modifiedObject", "transform");
    GenericMessage<String> newMessage = new GenericMessage<>(jsonPayload.toString());
    Transmission transmission = new Transmission.Builder().withVehicleId(jsonPayload.get("VEH_VehicleId").toString()).withLatitude(jsonPayload.get("LOC_Latitude").toString()).withLongitude(jsonPayload.get("LOC_Longitude").toString()).withSpeed(jsonPayload.get("LOC_Speed").toString()).build();
    GenericRecord record = MessageUtils.convertToAvro(transmission);
    message(record);
    processor.output().send(message(record));

  }

}
