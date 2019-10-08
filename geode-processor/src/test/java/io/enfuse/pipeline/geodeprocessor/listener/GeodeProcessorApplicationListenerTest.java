package io.enfuse.pipeline.geodeprocessor.listener;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.enfuse.pipeline.geodeprocessor.GeodeProcessorApplication;
import io.enfuse.pipeline.geodeprocessor.domain.Telemetry;
import io.enfuse.pipeline.geodeprocessor.domain.TelemetryRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfiguration.class, GeodeProcessorApplication.class})
@ActiveProfiles("test")
public class GeodeProcessorApplicationListenerTest {

  @Mock private Counter counter;
  @Mock private MeterRegistry meterRegistry;
  @Mock private Timer mockTimer;
  @MockBean private TelemetryRepository telemetryRepository;

  @Autowired private Processor processor;
  @Autowired private MessageCollector messageCollector;

  private ObjectMapper objectMapper = new ObjectMapper();

  @Test
  public void handle_givenTelemetry_returnsPayloadWithLookupValue() throws JsonProcessingException {
    Telemetry telemetryLookup =
        new Telemetry.Builder().withVehicleId("34464198").withValue("34464198 value").build();

    when(telemetryRepository.findById("34464198")).thenReturn(Optional.of(telemetryLookup));
    when(meterRegistry.counter(anyString())).thenReturn(counter);
    doNothing().when(counter).increment();
    when(meterRegistry.timer(anyString(), anyString(), anyString())).thenReturn(mockTimer);

    Telemetry incomingTelemetry =
        new Telemetry.Builder()
            .withVehicleId("34464198")
            .withLatitude("40.110761")
            .withLongitude("-83.278189")
            .withSpeed("36")
            .build();
    String incomingMessage = objectMapper.writeValueAsString(incomingTelemetry);

    processor.input().send(new GenericMessage<>(incomingMessage));

    final Message<?> result = messageCollector.forChannel(processor.output()).poll();

    Telemetry enrichedTelemetry = (Telemetry) result.getPayload();

    assertEquals("34464198", enrichedTelemetry.getVehicleId());
    assertEquals("40.110761", enrichedTelemetry.getLatitude());
    assertEquals("-83.278189", enrichedTelemetry.getLongitude());
    assertEquals("36", enrichedTelemetry.getSpeed());
    assertEquals("34464198 value", enrichedTelemetry.getValue());
  }
}
