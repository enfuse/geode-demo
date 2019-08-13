package io.enfuse.pipeline.transform.configuration;

import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;

@SpringBootApplication
@ComponentScan("io.enfuse.pipeline.transform")
@Configuration
class TestConfiguration {

  @Bean("clientCache")
  ClientCache clientCache() {
    ClientCacheFactory clientCacheFactory = new ClientCacheFactory();
    return clientCacheFactory.create();
  }

  @Bean("otherTruckTelemetryRegion")
  public ClientRegionFactoryBean<Object, Object> truckTelemetryRegion(ClientCache clientCache) {

    ClientRegionFactoryBean<Object, Object> clientRegion = new ClientRegionFactoryBean<>();

    clientRegion.setCache(clientCache);
    clientRegion.setClose(false);
    clientRegion.setShortcut(ClientRegionShortcut.LOCAL);

    return clientRegion;
  }
}
