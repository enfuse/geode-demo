package io.enfuse.gcssource.runner;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import io.enfuse.gcssource.properties.GcsConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@EnableBinding(Processor.class)
@EnableSchemaRegistryClient
public class GcsSourceRunner {

  private GcsConfigurationProperties properties;
  private Storage gcs;

  @Autowired
  public GcsSourceRunner(GcsConfigurationProperties properties, Storage gcs) {
    this.properties = properties;
    this.gcs = gcs;
  }

  @ServiceActivator(inputChannel = "streamChannel")
  public void outboundChannelAdapter(Message<?> message) throws IOException, JSONException {

    String filePath = message.getHeaders().get(FileHeaders.REMOTE_FILE, String.class);
    Blob blob = gcs.get(properties.getBucket(), filePath);

    String s = new String(blob.getContent(), "UTF-8");

    System.out.println("*******FILE PATH " + filePath);

    JSONObject jsonFromFile = new JSONObject(s);

    JSONArray jsonArray = jsonFromFile.getJSONArray("HistoryList");
    JSONObject jsonObject;

    for (int i = 0; i < jsonArray.length(); i++) {
      System.out.println("******************* JSON Added ");
      jsonObject = jsonArray.getJSONObject(i);
      System.out.println(jsonObject);
    }
  }
}
