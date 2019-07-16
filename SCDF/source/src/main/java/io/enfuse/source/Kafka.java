package io.enfuse.source;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Kafka {
  private KafkaConfig config;

  @Autowired
  public Kafka(KafkaConfig config) {
    this.config = config;
  }

  void publish(Producer producer, String topic, String data) throws Exception {
    final ProducerRecord<String, String> record = new ProducerRecord<>(topic, data);
    System.out.println(producer.send(record).get());
  }

  Producer<String, String> createProducer() {
    return createProducer(config);
  }

  private static Producer<String, String> createProducer(KafkaConfig config) {
    Properties props = new Properties();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getBootStrapServers());
    props.put(ProducerConfig.CLIENT_ID_CONFIG, "DemoKafkaProducer");
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    return new KafkaProducer<>(props);
  }
}
