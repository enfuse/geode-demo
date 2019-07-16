package io.enfuse.pipeline.transform.listener;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TransformApplicationListenerTest {
  private TransformApplicationListener subject;

  private final String BAKED_INPUT = getTestJson().toString();

  @Mock
  private Processor mockProcessor;

  @Mock
  private MessageChannel mockMessageChannel;

  @Captor
  private ArgumentCaptor<GenericMessage<String>> processorOutputCaptor;

  @Test
  public void handle_givenPayloadJsonBlob_returnsPayloadWithTransmissionData() {
    given(mockProcessor.output()).willReturn(mockMessageChannel);

    GenericMessage<String> bakedMessage = new GenericMessage<>(BAKED_INPUT);
    subject = new TransformApplicationListener(mockProcessor);
    subject.handle(bakedMessage);

    verify(mockMessageChannel).send(processorOutputCaptor.capture());

    JSONObject resultJsonObject = new JSONObject(processorOutputCaptor.getValue().getPayload());
    assertEquals("34464198", resultJsonObject.get("vehicleId"));
    assertEquals("40.110761", resultJsonObject.get("latitude"));
    assertEquals("-83.278189", resultJsonObject.get("longitude"));
    assertEquals("36", resultJsonObject.get("speed"));  }

  private static JSONObject getTestJson() {
    String test =
        "{\"MSG_SequenceNumber\": 240111636,\n"
            + "        \"MSG_TimeTransmitted\": \"2015-04-13T06:12:16Z\",\n"
            + "        \"MSG_TimeReceived\": \"2015-04-13T06:12:13.467Z\",\n"
            + "        \"MSG_TimeZoneOffset\": -4,\n"
            + "        \"COM_CompanyName\": \"Ceva Logistics - East Liberty\",\n"
            + "        \"VEH_VehicleId\": \"34464198\",\n"
            + "        \"VEH_VehicleName\": \"596475\",\n"
            + "        \"VEH_VIN\": \"1FUJGEDV5BSAX7095\",\n"
            + "        \"ENG_MaxSpeed\": 36,\n"
            + "        \"ENG_OdometerInMiles\": 331402,\n"
            + "        \"ENG_TotalFuelUsed\": 54597.0,\n"
            + "        \"ENG_TotalEngineHours\": 10775.3,\n"
            + "        \"ENG_TotalIdleHours\": 3217.7,\n"
            + "        \"ENG_RPM100Minutes\": 4,\n"
            + "        \"ENG_RPM1500Minutes\": 0,\n"
            + "        \"ENG_RPM2000Minutes\": 0,\n"
            + "        \"ENG_CruiseMinutes\": 0,\n"
            + "        \"ENG_MPH01Minutes\": 0,\n"
            + "        \"ENG_MPH35Minutes\": 4,\n"
            + "        \"ENG_MPH55Minutes\": 0,\n"
            + "        \"ENG_MPH66Minutes\": 0,\n"
            + "        \"ENG_HardBrakingMinutes\": 0,\n"
            + "        \"ENG_DTC47\": null,\n"
            + "        \"ENG_DTC50\": null,\n"
            + "        \"ENG_DTCLegacy\": null,\n"
            + "        \"ENG_PID1\": null,\n"
            + "        \"ENG_FMI1\": null,\n"
            + "        \"ENG_PID2\": null,\n"
            + "        \"ENG_FMI2\": null,\n"
            + "        \"ENG_PID3\": null,\n"
            + "        \"ENG_FMI3\": null,\n"
            + "        \"ENG_PID4\": null,\n"
            + "        \"ENG_FMI4\": null,\n"
            + "        \"ENG_TankPercent\": 92.0,\n"
            + "        \"DEV_DeviceNumber\": \"8901260761334464198\",\n"
            + "        \"DEV_ModelPCB\": \"REV_E LEON_100 J1939\",\n"
            + "        \"LOC_Latitude\": \"40.110761\",\n"
            + "        \"LOC_Longitude\": \"-83.278189\",\n"
            + "        \"LOC_Address\": \"112 S Jefferson Ave\",\n"
            + "        \"LOC_City\": \"Plain City\",\n"
            + "        \"LOC_Province\": \"OH\",\n"
            + "        \"LOC_PostalCode\": \"43064\",\n"
            + "        \"LOC_Country\": \"US\",\n"
            + "        \"LOC_CardinalDirection\": \"S\",\n"
            + "        \"LOC_AltitudeInFeet\": null,\n"
            + "        \"LOC_Speed\": 36,\n"
            + "        \"LOC_GPSFix\": null,\n"
            + "        \"ReasonCode\": \"Watch Moving\"}";

    return new JSONObject(test);
  }
}
