package io.enfuse.gcssource.utils;

import io.enfuse.gcssource.dto.TruckTelemetry;
import org.apache.avro.generic.GenericRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class MessageUtilsTest {

  private String file;

  {
    try {
      file = new String(Files.readAllBytes(Paths.get("src/test/resources/trucks_telemetry.json")), StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  @Test
  public void convertToAvroReturnValidAvroRecord() {
    TruckTelemetry truckTelemetry = new TruckTelemetry();

    truckTelemetry.setSequenceNumber("1234567");
    truckTelemetry.setTimeTransmitted("TimeTransmitted");
    truckTelemetry.setTimeReceived("TimeReceived");
    truckTelemetry.setTimeZoneOffset("TimeZoneOffset");
    truckTelemetry.setCompanyName("CompanyName");
    truckTelemetry.setVehicleId("VehicleId");
    truckTelemetry.setVehicleName("VehicleName");
    truckTelemetry.setVIN("VIN");
    truckTelemetry.setMaxSpeed("MaxSpeed");
    truckTelemetry.setOdometerInMiles("OdometerInMiles");
    truckTelemetry.setTotalFuelUsed("TotalFuelUsed");
    truckTelemetry.setTotalEngineHours("TotalEngineHours");
    truckTelemetry.setTotalIdleHours("TotalIdleHours");
    truckTelemetry.setRPM100Minutes("RPM100Minutes");
    truckTelemetry.setRPM1500Minutes("RPM1500Minutes");
    truckTelemetry.setRPM2000Minutes("RPM2000Minutes");
    truckTelemetry.setCruiseMinutes("CruiseMinutes");
    truckTelemetry.setMPH01Minutes("MPH01Minutes");
    truckTelemetry.setMPH35Minutes("MPH35Minutes");
    truckTelemetry.setMPH55Minutes("MPH55Minutes");
    truckTelemetry.setMPH66Minutes("MPH66Minutes");
    truckTelemetry.setHardBrakingMinutes("HardBrakingMinutes");
    truckTelemetry.setPID1("PID1");
    truckTelemetry.setFMI1("FMI1");
    truckTelemetry.setPID2("PID2");
    truckTelemetry.setFMI2("FMI2");
    truckTelemetry.setPID3("PID3");
    truckTelemetry.setFMI3("FMI3");
    truckTelemetry.setPID4("PID4");
    truckTelemetry.setFMI4("FMI4");
    truckTelemetry.setTankPercent("TankPercent");
    truckTelemetry.setDeviceNumber("DeviceNumber");
    truckTelemetry.setModelPCB("ModelPCB");
    truckTelemetry.setLatitude("Latitude");
    truckTelemetry.setLongitude("Longitude");
    truckTelemetry.setAddress("Address");
    truckTelemetry.setCity("City");
    truckTelemetry.setProvince("Province");
    truckTelemetry.setPostalCode("PostalCode");
    truckTelemetry.setCountry("Country");
    truckTelemetry.setCardinalDirection("CardinalDirection");
    truckTelemetry.setAltitudeInFeet("AltitudeInFeet");
    truckTelemetry.setSpeed("Speed");
    truckTelemetry.setGPSFix("true");
    truckTelemetry.setReasonCode("ReasonCode");

    GenericRecord genericRecord = MessageUtils.convertToAvro(truckTelemetry);

    assertEquals(genericRecord.get("MSG_SequenceNumber"), truckTelemetry.getSequenceNumber());
    assertEquals(genericRecord.get("MSG_TimeTransmitted"), truckTelemetry.getTimeTransmitted());
    assertEquals(genericRecord.get("MSG_TimeReceived"), truckTelemetry.getTimeReceived());
    assertEquals(genericRecord.get("MSG_TimeZoneOffset"), truckTelemetry.getTimeZoneOffset());
    assertEquals(genericRecord.get("COM_CompanyName"), truckTelemetry.getCompanyName());
    assertEquals(genericRecord.get("VEH_VehicleId"), truckTelemetry.getVehicleId());
    assertEquals(genericRecord.get("VEH_VehicleName"), truckTelemetry.getVehicleName());
    assertEquals(genericRecord.get("VEH_VIN"), truckTelemetry.getVIN());
    assertEquals(genericRecord.get("ENG_MaxSpeed"), truckTelemetry.getMaxSpeed());
    assertEquals(genericRecord.get("ENG_OdometerInMiles"), truckTelemetry.getOdometerInMiles());
    assertEquals(genericRecord.get("ENG_TotalFuelUsed"), truckTelemetry.getTotalFuelUsed());
    assertEquals(genericRecord.get("ENG_TotalEngineHours"), truckTelemetry.getTotalEngineHours());
    assertEquals(genericRecord.get("ENG_TotalIdleHours"), truckTelemetry.getTotalIdleHours());
    assertEquals(genericRecord.get("ENG_RPM100Minutes"), truckTelemetry.getRPM100Minutes());
    assertEquals(genericRecord.get("ENG_RPM1500Minutes"), truckTelemetry.getRPM1500Minutes());
    assertEquals(genericRecord.get("ENG_RPM2000Minutes"), truckTelemetry.getRPM2000Minutes());
    assertEquals(genericRecord.get("ENG_CruiseMinutes"), truckTelemetry.getCruiseMinutes());
    assertEquals(genericRecord.get("ENG_MPH01Minutes"), truckTelemetry.getMPH01Minutes());
    assertEquals(genericRecord.get("ENG_MPH35Minutes"), truckTelemetry.getMPH35Minutes());
    assertEquals(genericRecord.get("ENG_MPH55Minutes"), truckTelemetry.getMPH55Minutes());
    assertEquals(genericRecord.get("ENG_MPH66Minutes"), truckTelemetry.getMPH66Minutes());
    assertEquals(genericRecord.get("ENG_HardBrakingMinutes"), truckTelemetry.getHardBrakingMinutes());
    assertEquals(genericRecord.get("ENG_PID1"), truckTelemetry.getPID1());
    assertEquals(genericRecord.get("ENG_FMI1"), truckTelemetry.getFMI1());
    assertEquals(genericRecord.get("ENG_PID2"), truckTelemetry.getPID2());
    assertEquals(genericRecord.get("ENG_FMI2"), truckTelemetry.getFMI2());
    assertEquals(genericRecord.get("ENG_PID3"), truckTelemetry.getPID3());
    assertEquals(genericRecord.get("ENG_FMI3"), truckTelemetry.getFMI3());
    assertEquals(genericRecord.get("ENG_PID4"), truckTelemetry.getPID4());
    assertEquals(genericRecord.get("ENG_FMI4"), truckTelemetry.getFMI4());
    assertEquals(genericRecord.get("ENG_TankPercent"), truckTelemetry.getTankPercent());
    assertEquals(genericRecord.get("DEV_DeviceNumber"), truckTelemetry.getDeviceNumber());
    assertEquals(genericRecord.get("DEV_ModelPCB"), truckTelemetry.getModelPCB());
    assertEquals(genericRecord.get("LOC_Latitude"), truckTelemetry.getLatitude());
    assertEquals(genericRecord.get("LOC_Longitude"), truckTelemetry.getLongitude());
    assertEquals(genericRecord.get("LOC_Address"), truckTelemetry.getAddress());
    assertEquals(genericRecord.get("LOC_City"), truckTelemetry.getCity());
    assertEquals(genericRecord.get("LOC_Province"), truckTelemetry.getProvince());
    assertEquals(genericRecord.get("LOC_PostalCode"), truckTelemetry.getPostalCode());
    assertEquals(genericRecord.get("LOC_Country"), truckTelemetry.getCountry());
    assertEquals(genericRecord.get("LOC_CardinalDirection"), truckTelemetry.getCardinalDirection());
    assertEquals(genericRecord.get("LOC_AltitudeInFeet"), truckTelemetry.getAltitudeInFeet());
    assertEquals(genericRecord.get("LOC_Speed"), truckTelemetry.getSpeed());
    assertEquals(genericRecord.get("LOC_GPSFix"), truckTelemetry.getGPSFix());
    assertEquals(genericRecord.get("ReasonCode"), truckTelemetry.getReasonCode());
  }

  @Test
  public void convertToJsonArrayReturnsValidJsonArrayWithExpectedLength() throws JSONException {
    JSONArray jsonArray = MessageUtils.convertStringToJsonArray(file);
    assertEquals(8, jsonArray.length());
  }

  @Test
  public void convertToTruckTelemetryReturnsWithExpectedValues() throws JSONException {
    JSONArray jsonArray = MessageUtils.convertStringToJsonArray(file);

    TruckTelemetry truckTelemetry = MessageUtils.convertJsonToTruckTelemetry(jsonArray.getJSONObject(0));
    assertEquals("240111636", truckTelemetry.getSequenceNumber());
    assertEquals("2015-04-13T06:12:16Z", truckTelemetry.getTimeTransmitted());
    assertEquals("2015-04-13T06:12:13.467Z", truckTelemetry.getTimeReceived());
    assertEquals("-4", truckTelemetry.getTimeZoneOffset());
    assertEquals("Ceva Logistics - East Liberty ", truckTelemetry.getCompanyName());
    assertEquals("34464198", truckTelemetry.getVehicleId());
    assertEquals("596475", truckTelemetry.getVehicleName());
    assertEquals("1FUJGEDV5BSAX7095", truckTelemetry.getVIN());
    assertEquals("36", truckTelemetry.getMaxSpeed());
    assertEquals("331402", truckTelemetry.getOdometerInMiles());
    assertEquals("54597", truckTelemetry.getTotalFuelUsed());
    assertEquals("10775.3", truckTelemetry.getTotalEngineHours());
    assertEquals("3217.7", truckTelemetry.getTotalIdleHours());
    assertEquals("4", truckTelemetry.getRPM100Minutes());
    assertEquals("0", truckTelemetry.getRPM1500Minutes());
    assertEquals("0", truckTelemetry.getRPM2000Minutes());
    assertEquals("0", truckTelemetry.getCruiseMinutes());
    assertEquals("0", truckTelemetry.getMPH01Minutes());
    assertEquals("4", truckTelemetry.getMPH35Minutes());
    assertEquals("0", truckTelemetry.getMPH55Minutes());
    assertEquals("0", truckTelemetry.getMPH66Minutes());
    assertEquals("0", truckTelemetry.getHardBrakingMinutes());
    assertNull(truckTelemetry.getPID1());
    assertNull(truckTelemetry.getFMI1());
    assertNull(truckTelemetry.getPID2());
    assertNull(truckTelemetry.getFMI2());
    assertNull(truckTelemetry.getPID3());
    assertNull(truckTelemetry.getFMI3());
    assertNull(truckTelemetry.getPID4());
    assertNull(truckTelemetry.getFMI4());
    assertEquals("92", truckTelemetry.getTankPercent());
    assertEquals("8901260761334464198", truckTelemetry.getDeviceNumber());
    assertEquals("REV_E LEON_100 J1939", truckTelemetry.getModelPCB());
    assertEquals("40.110761", truckTelemetry.getLatitude());
    assertEquals("-83.278189", truckTelemetry.getLongitude());
    assertEquals("112 S Jefferson Ave", truckTelemetry.getAddress());
    assertEquals("Plain City", truckTelemetry.getCity());
    assertEquals("OH", truckTelemetry.getProvince());
    assertEquals("43064", truckTelemetry.getPostalCode());
    assertEquals("US", truckTelemetry.getCountry());
    assertEquals("S", truckTelemetry.getCardinalDirection());
    assertNull(truckTelemetry.getAltitudeInFeet());
    assertEquals("36", truckTelemetry.getSpeed());
    assertNull(truckTelemetry.getGPSFix());
    assertEquals("Watch Moving", truckTelemetry.getReasonCode());
  }

}
