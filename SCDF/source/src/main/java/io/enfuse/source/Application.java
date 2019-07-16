package io.enfuse.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
  private DemoGcsConsumer consumer;
  private Kafka kafka;

  @Autowired
  Application(DemoGcsConsumer consumer, Kafka kafka) {
    this.consumer = consumer;
    this.kafka = kafka;
  }

  @Override
  public void run(String... args) {
    consumer.transferContent(kafka);
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
