package io.enfuse.transform.util;




import java.io.IOException;

import io.enfuse.transform.domain.Transmission;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;


public class MessageUtils {
  private static Logger log = LoggerFactory.getLogger(MessageUtils.class);
  private static Schema schema;

  static {
    try {
      schema =
          new Schema.Parser()
              .parse(new ClassPathResource("avro/transmission-v1.json").getInputStream());
    } catch (IOException e) {
      log.error("avro schema parsing error" + e);
    }
  }


  public static <T> Message<T> message(T val) {
    return MessageBuilder.withPayload(val).build();
  }

  public static GenericRecord convertToAvro(Transmission transmission) {

    GenericRecord avroRecord = new GenericData.Record(schema);
    avroRecord.put("vehicleId", transmission.getVehicleId());
    avroRecord.put("latitude", transmission.getLatitude());
    avroRecord.put("longitude", transmission.getLongitude());
    avroRecord.put("speed", transmission.getSpeed());

    return avroRecord;
  }
}
