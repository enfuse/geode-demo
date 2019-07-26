package io.enfuse.gcssource.utils;

import com.google.gson.Gson;
import io.enfuse.gcssource.dto.TruckTelemetry;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.io.IOException;

public class MessageUtils {
  private static Logger log = LoggerFactory.getLogger(MessageUtils.class);
  private static Schema schema;

  static {
    try {
      schema =
          new Schema.Parser()
              .parse(new ClassPathResource("avro/truck-telemetry-v1.json").getInputStream());
    } catch (IOException e) {
      log.error("avro schema parsing error" + e);
    }
  }

  private static final Gson gson = new Gson();

  public static <T> Message<T> message(T val) {
    return MessageBuilder.withPayload(val).build();
  }

  public static GenericRecord convertToAvro(TruckTelemetry truckTelemetry) {

    GenericRecord avroTruckTelemetry = new GenericData.Record(schema);

    avroTruckTelemetry.put("MSG_SequenceNumber", truckTelemetry.getSequenceNumber());
    avroTruckTelemetry.put("MSG_TimeTransmitted", truckTelemetry.getTimeTransmitted());
    avroTruckTelemetry.put("MSG_TimeReceived", truckTelemetry.getTimeReceived());
    avroTruckTelemetry.put("MSG_TimeZoneOffset", truckTelemetry.getTimeZoneOffset());
    avroTruckTelemetry.put("COM_CompanyName", truckTelemetry.getCompanyName());
    avroTruckTelemetry.put("VEH_VehicleId", truckTelemetry.getVehicleId());
    avroTruckTelemetry.put("VEH_VehicleName", truckTelemetry.getVehicleName());
    avroTruckTelemetry.put("VEH_VIN", truckTelemetry.getVIN());
    avroTruckTelemetry.put("ENG_MaxSpeed", truckTelemetry.getMaxSpeed());
    avroTruckTelemetry.put("ENG_OdometerInMiles", truckTelemetry.getOdometerInMiles());
    avroTruckTelemetry.put("ENG_TotalFuelUsed", truckTelemetry.getTotalFuelUsed());
    avroTruckTelemetry.put("ENG_TotalEngineHours", truckTelemetry.getTotalEngineHours());
    avroTruckTelemetry.put("ENG_TotalIdleHours", truckTelemetry.getTotalIdleHours());
    avroTruckTelemetry.put("ENG_RPM100Minutes", truckTelemetry.getRPM100Minutes());
    avroTruckTelemetry.put("ENG_RPM1500Minutes", truckTelemetry.getRPM1500Minutes());
    avroTruckTelemetry.put("ENG_RPM2000Minutes", truckTelemetry.getRPM2000Minutes());
    avroTruckTelemetry.put("ENG_CruiseMinutes", truckTelemetry.getCruiseMinutes());
    avroTruckTelemetry.put("ENG_MPH01Minutes", truckTelemetry.getMPH01Minutes());
    avroTruckTelemetry.put("ENG_MPH35Minutes", truckTelemetry.getMPH35Minutes());
    avroTruckTelemetry.put("ENG_MPH55Minutes", truckTelemetry.getMPH55Minutes());
    avroTruckTelemetry.put("ENG_MPH66Minutes", truckTelemetry.getMPH66Minutes());
    avroTruckTelemetry.put("ENG_HardBrakingMinutes", truckTelemetry.getHardBrakingMinutes());
    avroTruckTelemetry.put("ENG_PID1", truckTelemetry.getPID1());
    avroTruckTelemetry.put("ENG_FMI1", truckTelemetry.getFMI1());
    avroTruckTelemetry.put("ENG_PID2", truckTelemetry.getPID2());
    avroTruckTelemetry.put("ENG_FMI2", truckTelemetry.getFMI2());
    avroTruckTelemetry.put("ENG_PID3", truckTelemetry.getPID3());
    avroTruckTelemetry.put("ENG_FMI3", truckTelemetry.getFMI3());
    avroTruckTelemetry.put("ENG_PID4", truckTelemetry.getPID4());
    avroTruckTelemetry.put("ENG_FMI4", truckTelemetry.getFMI4());
    avroTruckTelemetry.put("ENG_TankPercent", truckTelemetry.getTankPercent());
    avroTruckTelemetry.put("DEV_DeviceNumber", truckTelemetry.getDeviceNumber());
    avroTruckTelemetry.put("DEV_ModelPCB", truckTelemetry.getModelPCB());
    avroTruckTelemetry.put("LOC_Latitude", truckTelemetry.getLatitude());
    avroTruckTelemetry.put("LOC_Longitude", truckTelemetry.getLongitude());
    avroTruckTelemetry.put("LOC_Address", truckTelemetry.getAddress());
    avroTruckTelemetry.put("LOC_City", truckTelemetry.getCity());
    avroTruckTelemetry.put("LOC_Province", truckTelemetry.getProvince());
    avroTruckTelemetry.put("LOC_PostalCode", truckTelemetry.getPostalCode());
    avroTruckTelemetry.put("LOC_Country", truckTelemetry.getCountry());
    avroTruckTelemetry.put("LOC_CardinalDirection", truckTelemetry.getCardinalDirection());
    avroTruckTelemetry.put("LOC_AltitudeInFeet", truckTelemetry.getAltitudeInFeet());
    avroTruckTelemetry.put("LOC_Speed", truckTelemetry.getSpeed());
    avroTruckTelemetry.put("LOC_GPSFix", truckTelemetry.getGPSFix());
    avroTruckTelemetry.put("ReasonCode", truckTelemetry.getReasonCode());

    return avroTruckTelemetry;
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
