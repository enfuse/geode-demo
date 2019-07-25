package io.enfuse.gcssource.utils;

import com.google.gson.Gson;
import io.enfuse.gcssource.dto.TruckTelemetry;
import org.apache.avro.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

public class MessageUtils {
  private static Logger log = LoggerFactory.getLogger(MessageUtils.class);
  private static Schema schema;

  public static <T> Message<T> message(T val) {
    return MessageBuilder.withPayload(val).build();
  }

  public static JSONArray convertStringToJsonArray(String file) throws JSONException {
    JSONObject jsnobject = new JSONObject(file);
    JSONArray jsonArray = jsnobject.getJSONArray("HistoryList");
    return jsonArray;
  }

  public static TruckTelemetry convertJsonToTruckTelemetry(JSONObject json) {
    Gson gson = new Gson();
    TruckTelemetry truckTelemetry = gson.fromJson(String.valueOf(json), TruckTelemetry.class);
    return truckTelemetry;
  }
}
