package io.enfuse.pipeline.geodeprocessor.listener;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import io.enfuse.pipeline.geodeprocessor.domain.Telemetry;
import io.enfuse.pipeline.geodeprocessor.domain.TelemetryRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import java.util.Optional;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.messaging.support.GenericMessage;

@RunWith(MockitoJUnitRunner.class)
public class GeodeProcessorApplicationListenerTest {
  private GeodeProcessorApplicationListener subject;

  private final String BAKED_INPUT = getTestJson().toString();

  @Mock private Counter counter;
  @Mock private MeterRegistry meterRegistry;
  @Mock private Timer mockTimer;
  @Mock private TelemetryRepository telemetryRepository;

  @Test
  public void handle_givenPayloadJsonBlob_returnsPayloadWithTransmissionData() throws Exception {
    // given(mockProcessor.output()).willReturn(mockMessageChannel);

    Telemetry telemetry = new Telemetry.Builder().withValue("34464198 value").build();

    when(telemetryRepository.findById("34464198")).thenReturn(Optional.of(telemetry));
    when(meterRegistry.counter(anyString())).thenReturn(counter);
    doNothing().when(counter).increment();
    when(meterRegistry.timer(anyString(), anyString(), anyString())).thenReturn(mockTimer);
    GenericMessage<String> bakedMessage = new GenericMessage<>(BAKED_INPUT);
    subject = new GeodeProcessorApplicationListener(telemetryRepository, meterRegistry);
    GenericMessage<Telemetry> result = subject.handle(bakedMessage);

    Telemetry returnValue = result.getPayload();

    assertEquals("34464198", returnValue.getVehicleId());
    assertEquals("40.110761", returnValue.getLatitude());
    assertEquals("-83.278189", returnValue.getLongitude());
    assertEquals("36", returnValue.getSpeed());
    assertEquals("34464198 value", returnValue.getValue());
  }

  private static JSONObject getTestJson() {
    String test =
        "{\"MSG_SequenceNumber\": 240111636,\n"
            + "        \"VehicleId\": \"34464198\",\n"
            + "        \"Latitude\": \"40.110761\",\n"
            + "        \"Longitude\": \"-83.278189\",\n"
            + "        \"Speed\": \"36\"}";

    return new JSONObject(test);
  }
}
