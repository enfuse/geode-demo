package io.enfuse.gcssource.configuration;

import com.google.cloud.storage.Storage;
import com.google.gson.Gson;
import io.enfuse.gcssource.properties.GcsConfigurationProperties;
import org.springframework.cloud.gcp.storage.integration.GcsRemoteFileTemplate;
import org.springframework.cloud.gcp.storage.integration.GcsSessionFactory;
import org.springframework.cloud.gcp.storage.integration.inbound.GcsStreamingMessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;

import java.io.InputStream;

@Configuration
public class GcsSourceConfiguration {
  @Bean
  public Gson gson() {
    return new Gson();
  }

  @Bean
  @InboundChannelAdapter(channel = "streamChannel", poller = @Poller(fixedDelay = "1000"))
  public MessageSource<InputStream> streamingAdapter(
      Storage gcs, GcsConfigurationProperties props) {
    GcsStreamingMessageSource adapter =
        new GcsStreamingMessageSource(new GcsRemoteFileTemplate(new GcsSessionFactory(gcs)));
    adapter.setRemoteDirectory(props.getBucket());
    return adapter;
  }
}
