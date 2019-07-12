package io.enfuse.transform.listener;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransformApplicationListenerIntegrationTest {

  private final String BAKED_INPUT = "{\"address\":\"601 West Street_601 West 5th Avenue_Anchorage\",\"latitude\":-149.8935557,\"storeName\":\"Starbucks - AK - Anchorage  00001\",\"longitude\":61.21759217}";

  @Autowired
  private Processor processor;

  @Autowired
  private MessageCollector messageCollector;

  @Test
  public void handle_givenJsonString_outputsUpperCaseValues() {
    processor.input().send(new GenericMessage<>(getTestJson().toString()));

    GenericMessage<String> result = (GenericMessage<String>) messageCollector.forChannel(processor.output()).poll();
    JSONObject resultJsonObject = createJsonObject(Objects.requireNonNull(result));

    assertThat(resultJsonObject.get("COM_CompanyName")).isEqualTo("Ceva Logistics - East Liberty");
    assertThat(resultJsonObject.get("MSG_SequenceNumber")).isEqualTo(240111636);
  }

  private JSONObject getTestJson(){
    String test = "{\"MSG_SequenceNumber\": 240111636,\n" +
        "        \"MSG_TimeTransmitted\": \"2015-04-13T06:12:16Z\",\n" +
        "        \"MSG_TimeReceived\": \"2015-04-13T06:12:13.467Z\",\n" +
        "        \"MSG_TimeZoneOffset\": -4,\n" +
        "        \"COM_CompanyName\": \"Ceva Logistics - East Liberty\",\n" +
        "        \"VEH_VehicleId\": \"34464198\",\n" +
        "        \"VEH_VehicleName\": \"596475\",\n" +
        "        \"VEH_VIN\": \"1FUJGEDV5BSAX7095\",\n" +
        "        \"ENG_MaxSpeed\": 36,\n" +
        "        \"ENG_OdometerInMiles\": 331402,\n" +
        "        \"ENG_TotalFuelUsed\": 54597.0,\n" +
        "        \"ENG_TotalEngineHours\": 10775.3,\n" +
        "        \"ENG_TotalIdleHours\": 3217.7,\n" +
        "        \"ENG_RPM100Minutes\": 4,\n" +
        "        \"ENG_RPM1500Minutes\": 0,\n" +
        "        \"ENG_RPM2000Minutes\": 0,\n" +
        "        \"ENG_CruiseMinutes\": 0,\n" +
        "        \"ENG_MPH01Minutes\": 0,\n" +
        "        \"ENG_MPH35Minutes\": 4,\n" +
        "        \"ENG_MPH55Minutes\": 0,\n" +
        "        \"ENG_MPH66Minutes\": 0,\n" +
        "        \"ENG_HardBrakingMinutes\": 0,\n" +
        "        \"ENG_DTC47\": null,\n" +
        "        \"ENG_DTC50\": null,\n" +
        "        \"ENG_DTCLegacy\": null,\n" +
        "        \"ENG_PID1\": null,\n" +
        "        \"ENG_FMI1\": null,\n" +
        "        \"ENG_PID2\": null,\n" +
        "        \"ENG_FMI2\": null,\n" +
        "        \"ENG_PID3\": null,\n" +
        "        \"ENG_FMI3\": null,\n" +
        "        \"ENG_PID4\": null,\n" +
        "        \"ENG_FMI4\": null,\n" +
        "        \"ENG_TankPercent\": 92.0,\n" +
        "        \"DEV_DeviceNumber\": \"8901260761334464198\",\n" +
        "        \"DEV_ModelPCB\": \"REV_E LEON_100 J1939\",\n" +
        "        \"LOC_Latitude\": \"40.110761\",\n" +
        "        \"LOC_Longitude\": \"-83.278189\",\n" +
        "        \"LOC_Address\": \"112 S Jefferson Ave\",\n" +
        "        \"LOC_City\": \"Plain City\",\n" +
        "        \"LOC_Province\": \"OH\",\n" +
        "        \"LOC_PostalCode\": \"43064\",\n" +
        "        \"LOC_Country\": \"US\",\n" +
        "        \"LOC_CardinalDirection\": \"S\",\n" +
        "        \"LOC_AltitudeInFeet\": null,\n" +
        "        \"LOC_Speed\": 36,\n" +
        "        \"LOC_GPSFix\": null,\n" +
        "        \"ReasonCode\": \"Watch Moving\"}";

    JSONObject jsonObject = new JSONObject(test);
    return jsonObject;
  }

  private JSONObject createJsonObject(GenericMessage<String> message) {
    return new JSONObject(message.getPayload());
  }
}
