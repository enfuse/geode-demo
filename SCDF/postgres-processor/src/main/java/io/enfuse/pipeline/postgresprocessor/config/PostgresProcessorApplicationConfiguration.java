package io.enfuse.pipeline.postgresprocessor.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostgresProcessorApplicationConfiguration {
  @Bean
  MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
    return registry ->
        registry
            .config()
            .commonTags("application_guid", "2")
            .commonTags("application-name", "postgres-processor")
            .commonTags("stream_name", "geode-demo-pipeline");
  }
}
