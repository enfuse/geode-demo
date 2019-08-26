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

   private Processor mockProcessor;

  @Mock private ClientCache mockClientCache;

  @Mock private MessageChannel mockMessageChannel;

  @Mock private Counter counter;
  @Mock private Timer timer;
  @Mock private Region mockRegion;
  @Mock private MeterRegistry meterRegistry;
  @Captor private ArgumentCaptor<GenericMessage<String>> processorOutputCaptor;
  @Mock private TelemetryRepository telemetryRepository;

  @Test
  public void handle_givenPayloadJsonBlob_returnsPayloadWithTransmissionData() throws Exception {
    //given(mockProcessor.output()).willReturn(mockMessageChannel);

    Telemetry telemetry = new Telemetry.Builder().withValue("34464198 value").build();

    when(telemetryRepository.findById("34464198")).thenReturn(Optional.of(telemetry));
    when(meterRegistry.counter(anyString())).thenReturn(counter);
    doNothing().when(counter).increment();
    GenericMessage<String> bakedMessage = new GenericMessage<>(BAKED_INPUT);
    subject =
        new GeodeProcessorApplicationListener(telemetryRepository, meterRegistry);
    GenericMessage<Telemetry> result =  subject.handle(bakedMessage);

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
