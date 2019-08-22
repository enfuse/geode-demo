package io.enfuse.pipeline.geodeprocessor.listener;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import io.enfuse.pipeline.geodeprocessor.domain.Telemetry;
import io.enfuse.pipeline.geodeprocessor.domain.TelemetryRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import java.util.Optional;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

@RunWith(MockitoJUnitRunner.class)
public class GeodeProcessorApplicationListenerTest {
  private GeodeProcessorApplicationListener subject;

  private final String BAKED_INPUT = getTestJson().toString();

  @Mock private Processor mockProcessor;

  @Mock private ClientCache mockClientCache;

  @Mock private MessageChannel mockMessageChannel;

  @Mock private Counter counter;
  @Mock private Timer timer;
  @Mock private Region mockRegion;
  @Mock private MeterRegistry meterRegistry;
  @Captor private ArgumentCaptor<GenericMessage<String>> processorOutputCaptor;
  @Mock private TelemetryRepository telemetryRepository;

  @Test
  @Ignore
  public void handle_givenPayloadJsonBlob_returnsPayloadWithTransmissionData() {
    given(mockProcessor.output()).willReturn(mockMessageChannel);

    Telemetry telemetry = new Telemetry.Builder().withValue("34464198 value").build();

    when(telemetryRepository.findById("34464198")).thenReturn(Optional.of(telemetry));
    when(mockRegion.get("34464198")).thenReturn("34464198 value");
    when(meterRegistry.counter(anyString())).thenReturn(counter);
    doNothing().when(counter).increment();
    when(meterRegistry.timer(anyString(), anyString(), anyString())).thenReturn(timer);

    GenericMessage<String> bakedMessage = new GenericMessage<>(BAKED_INPUT);
    subject =
        new GeodeProcessorApplicationListener(mockProcessor, telemetryRepository, meterRegistry);
    subject.handle(bakedMessage);

    verify(mockMessageChannel).send(processorOutputCaptor.capture());

    JSONObject resultJsonObject = new JSONObject(processorOutputCaptor.getValue().getPayload());
    assertEquals("34464198", resultJsonObject.get("vehicleId"));
    assertEquals("40.110761", resultJsonObject.get("latitude"));
    assertEquals("-83.278189", resultJsonObject.get("longitude"));
    assertEquals("36", resultJsonObject.get("speed"));
    assertEquals("34464198 value", resultJsonObject.get("value"));
  }

  private static JSONObject getTestJson() {
    String test =
        "{\"MSG_SequenceNumber\": 240111636,\n"
            + "        \"TimeTransmitted\": \"2015-04-13T06:12:16Z\",\n"
            + "        \"TimeReceived\": \"2015-04-13T06:12:13.467Z\",\n"
            + "        \"TimeZoneOffset\": -4,\n"
            + "        \"CompanyName\": \"Ceva Logistics - East Liberty\",\n"
            + "        \"VehicleId\": \"34464198\",\n"
            + "        \"VehicleName\": \"596475\",\n"
            + "        \"VIN\": \"1FUJGEDV5BSAX7095\",\n"
            + "        \"MaxSpeed\": 36,\n"
            + "        \"OdometerInMiles\": 331402,\n"
            + "        \"TotalFuelUsed\": 54597.0,\n"
            + "        \"TotalEngineHours\": 10775.3,\n"
            + "        \"TotalIdleHours\": 3217.7,\n"
            + "        \"RPM100Minutes\": 4,\n"
            + "        \"RPM1500Minutes\": 0,\n"
            + "        \"RPM2000Minutes\": 0,\n"
            + "        \"CruiseMinutes\": 0,\n"
            + "        \"MPH01Minutes\": 0,\n"
            + "        \"MPH35Minutes\": 4,\n"
            + "        \"MPH55Minutes\": 0,\n"
            + "        \"MPH66Minutes\": 0,\n"
            + "        \"HardBrakingMinutes\": 0,\n"
            + "        \"DTC47\": null,\n"
            + "        \"DTC50\": null,\n"
            + "        \"DTCLegacy\": null,\n"
            + "        \"PID1\": null,\n"
            + "        \"FMI1\": null,\n"
            + "        \"PID2\": null,\n"
            + "        \"FMI2\": null,\n"
            + "        \"PID3\": null,\n"
            + "        \"FMI3\": null,\n"
            + "        \"PID4\": null,\n"
            + "        \"FMI4\": null,\n"
            + "        \"TankPercent\": 92.0,\n"
            + "        \"DeviceNumber\": \"8901260761334464198\",\n"
            + "        \"ModelPCB\": \"REV_E LEON_100 J1939\",\n"
            + "        \"Latitude\": \"40.110761\",\n"
            + "        \"Longitude\": \"-83.278189\",\n"
            + "        \"Address\": \"112 S Jefferson Ave\",\n"
            + "        \"City\": \"Plain City\",\n"
            + "        \"Province\": \"OH\",\n"
            + "        \"PostalCode\": \"43064\",\n"
            + "        \"Country\": \"US\",\n"
            + "        \"CardinalDirection\": \"S\",\n"
            + "        \"AltitudeInFeet\": null,\n"
            + "        \"Speed\": 36,\n"
            + "        \"GPSFix\": null,\n"
            + "        \"ReasonCode\": \"Watch Moving\"}";

    return new JSONObject(test);
  }
}
