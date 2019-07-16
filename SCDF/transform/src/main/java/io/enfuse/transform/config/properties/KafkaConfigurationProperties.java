package io.enfuse.transform.config.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka-properties")
public class KafkaConfigurationProperties {
  private String brokers;
  private String schemaRegistry;
  private String groupId;
  private String inputTopic;
  private String listenerId;
  private String dltListenerId;
  private String inputTopicDlt;
  private String outputTopic;

  public String getInputTopicDlt() {
    return inputTopicDlt;
  }

  public void setInputTopicDlt(String inputTopicDlt) {
    this.inputTopicDlt = inputTopicDlt;
  }

  public String getDltListenerId() {
    return dltListenerId;
  }

  public void setDltListenerId(String dltListenerId) {
    this.dltListenerId = dltListenerId;
  }

  public String getListenerId() {
    return listenerId;
  }

  public void setListenerId(String listenerId) {
    this.listenerId = listenerId;
  }

  public String getBrokers() {
    return brokers;
  }

  public void setBrokers(String brokers) {
    this.brokers = brokers;
  }

  public String getSchemaRegistry() {
    return schemaRegistry;
  }

  public void setSchemaRegistry(String schemaRegistry) {
    this.schemaRegistry = schemaRegistry;
  }

  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  public String getInputTopic() {
    return inputTopic;
  }

  public void setInputTopic(String inputTopic) {
    this.inputTopic = inputTopic;
  }

  public String getOutputTopic() {
    return outputTopic;
  }

  public void setOutputTopic(String outputTopic) {
    this.outputTopic = outputTopic;
  }
}
