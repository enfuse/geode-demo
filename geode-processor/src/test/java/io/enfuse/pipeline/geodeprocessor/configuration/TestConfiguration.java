package io.enfuse.pipeline.geodeprocessor.configuration;

import io.micrometer.core.instrument.MeterRegistry;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;
import org.springframework.test.context.ActiveProfiles;

@Configuration
@ActiveProfiles("integration")
class TestConfiguration {

  @Bean("gemfireCache")
  ClientCache clientCache() {
    ClientCacheFactory clientCacheFactory = new ClientCacheFactory();
    return clientCacheFactory.create();
  }

  @Bean("telemetryRegion")
  public ClientRegionFactoryBean<Object, Object> truckTelemetryRegion(ClientCache clientCache) {

    ClientRegionFactoryBean<Object, Object> clientRegion = new ClientRegionFactoryBean<>();

    clientRegion.setCache(clientCache);
    clientRegion.setClose(false);
    clientRegion.setShortcut(ClientRegionShortcut.LOCAL);

    return clientRegion;
  }

  @Bean
  MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
    return registry ->
        registry
            .config()
            .commonTags("application_guid", "1")
            .commonTags("application-name", "geode-processor")
            .commonTags("stream_name", "geode-demo-pipeline");
  }
}
