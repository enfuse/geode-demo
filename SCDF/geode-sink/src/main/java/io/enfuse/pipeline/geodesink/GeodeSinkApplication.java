package io.enfuse.pipeline.geodesink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

@SpringBootApplication
@ClientCacheApplication(logLevel = "debug")
@EnableGemfireRepositories
@EnableEntityDefinedRegions
public class GeodeSinkApplication {

  public static void main(String[] args) {
    SpringApplication.run(GeodeSinkApplication.class, args);
  }
}
