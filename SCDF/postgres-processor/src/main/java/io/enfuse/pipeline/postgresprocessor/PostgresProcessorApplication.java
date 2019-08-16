package io.enfuse.pipeline.postgresprocessor;

import io.enfuse.pipeline.postgresprocessor.domain.Telemetry;
import io.enfuse.pipeline.postgresprocessor.repository.TelemetryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PostgresProcessorApplication {

  public static void main(String[] args) {
    SpringApplication.run(PostgresProcessorApplication.class, args);
  }

  @Bean
  public CommandLineRunner demoData(TelemetryRepository telemetryRepository) {
    return args -> {
      for (int i = 1; i < 10000; i++) {
        Telemetry telemetry = new Telemetry();
        telemetry.setVehicleId(String.valueOf(i));
        telemetry.setValue("New Value " + i);

        telemetryRepository.save(telemetry);
      }
    };
  }
}
