package io.enfuse.pipeline.transform.configuration;

import io.enfuse.pipeline.transform.domain.Telemetry;
import io.enfuse.pipeline.transform.domain.TelemetryRepository;
import io.micrometer.core.instrument.MeterRegistry;
import java.util.Collections;
import org.apache.geode.cache.RegionShortcut;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.ClientCacheConfigurer;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.config.annotation.EnableIndexing;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.data.gemfire.support.ConnectionEndpoint;

@ClientCacheApplication
@EnableEntityDefinedRegions(
    basePackageClasses = Telemetry.class,
    clientRegionShortcut = ClientRegionShortcut.PROXY,
    serverRegionShortcut = RegionShortcut.PARTITION)
@EnableGemfireRepositories(basePackageClasses = TelemetryRepository.class)
@EnableIndexing
public class GeodeClientConfiguration {
  @Bean
  ClientCacheConfigurer clientCacheServerConfigurer(
      @Value("${spring.data.geode.locator.host:locator.localhost}") String hostname,
      @Value("${spring.data.geode.locator.port:10334}") int port) {

    return (beanName, clientCacheFactoryBean) ->
        clientCacheFactoryBean.setLocators(
            Collections.singletonList(new ConnectionEndpoint(hostname, port)));
  }

  @Bean
  MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
    return registry ->
        registry
            .config()
            .commonTags("application_guid", "1")
            .commonTags("application-name", "transform")
            .commonTags("stream_name", "geode-demo-pipeline");
  }
}
