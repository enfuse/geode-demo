package io.enfuse.source;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

  /*
    @Override
    public void run(String... args) {
      consumer.transferContent(kafka);
    }
  */
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
