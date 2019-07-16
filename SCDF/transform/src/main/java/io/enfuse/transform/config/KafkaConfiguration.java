package io.enfuse.transform.config;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.enfuse.transform.config.properties.KafkaConfigurationProperties;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.DefaultKafkaHeaderMapper;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfiguration {
  @Bean
  public DefaultKafkaHeaderMapper headerMapper() {
    return new DefaultKafkaHeaderMapper();
  }

  @Bean
  public ProducerFactory<Object, Object> producerFactory(
      KafkaConfigurationProperties kafkaProperties) {

    Map<String, Object> configProps = new HashMap<>();

    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBrokers());
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
    configProps.put("schema.registry.url", kafkaProperties.getSchemaRegistry());
    return new DefaultKafkaProducerFactory<>(configProps);
  }



  @Bean
  public KafkaTemplate<Object, Object> kafkaTemplate(
      KafkaConfigurationProperties kafkaProperties) {
    KafkaTemplate<Object, Object> template =
        new KafkaTemplate<Object, Object>(producerFactory(kafkaProperties));
    template.setDefaultTopic(kafkaProperties.getOutputTopic());
    return template;
  }

  @Bean
  public ConsumerFactory<Object, Object> consumerFactory(
      KafkaConfigurationProperties kafkaProperties) {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBrokers());
    props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getGroupId());
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
    props.put("schema.registry.url", kafkaProperties.getSchemaRegistry());
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    return new DefaultKafkaConsumerFactory<>(props);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<Object, Object>
  kafkaListenerContainerFactory(
      KafkaConfigurationProperties kafkaProperties, KafkaTemplate<Object, Object> template) {

    ConcurrentKafkaListenerContainerFactory<Object, Object> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory(kafkaProperties));
    factory.setErrorHandler(
        new SeekToCurrentErrorHandler(
            new DeadLetterPublishingRecoverer(
                template,
                (cr, e) ->
                    new TopicPartition(
                        "peoplemaster.awsstg.dsUpdate.postgres" + ".DLT", cr.partition())),
            3));
    return factory;
  }
}
