package io.enfuse.source;

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DemoGcsConsumer {
  // Should be > 1kb and < 10MB
  DemoGcsConsumerConfig consumerConfig;

  @Autowired
  DemoGcsConsumer(DemoGcsConsumerConfig config) {
    this.consumerConfig = config;
  }

  @SuppressFBWarnings("DM_DEFAULT_ENCODING")
  void transferContent(Kafka kafka) {
    Producer producer = kafka.createProducer();
    try {
      Storage storage = StorageOptions.getDefaultInstance().getService();
      Page<Blob> blobs = storage.list(consumerConfig.getBucket());
      for (Blob blob : blobs.iterateAll()) {
        String topic = blob.getName();
        String data = new String(blob.getContent());
        System.out.println(data);
        kafka.publish(producer, topic, data);
      }
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      producer.flush();
      producer.close();
    }
  }
}
