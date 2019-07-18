package io.enfuse.source;

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Source.class)
public class DemoGcsConsumer {

  private Source source;
  private DemoGcsConsumerConfig consumerConfig;
  // Should be > 1kb and < 10MB
  @Autowired
  DemoGcsConsumer(DemoGcsConsumerConfig config, Source source) {
    this.consumerConfig = config;
    this.source = source;
  }

  @SuppressFBWarnings("DM_DEFAULT_ENCODING")
  @InboundChannelAdapter(channel = Source.OUTPUT, poller = @Poller(fixedDelay = "100000000"))
  public String transferContent() {
    // Producer producer = kafka.createProducer();
    try {
      Storage storage = StorageOptions.getDefaultInstance().getService();
      Page<Blob> blobs = storage.list(consumerConfig.getBucket());
      for (Blob blob : blobs.iterateAll()) {
        String topic = blob.getName();
        String data = new String(blob.getContent());
        System.out.println(data);
        // kafka.publish(producer, topic, data);
        source.output().send(new GenericMessage<>(data));
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      //  producer.flush();
      // producer.close();
    }
    return null;
  }
}
