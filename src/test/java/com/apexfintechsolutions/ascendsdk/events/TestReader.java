package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestReader {
  private SDK sdk;
  private String messageId = "";

  @BeforeAll
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();
    messageId = EventsUtil.getMessageId(sdk);
    if (messageId == null || messageId.isEmpty()) {
      throw new Exception("Message ID not found");
    }
  }

  @Test
  public void test_reader_events_list_event_messages_list_event_messages1() throws Exception {
    Assertions.assertNotNull(messageId);
  }

  @Test
  public void test_reader_events_get_event_message_get_event_message1() throws Exception {
    var res = sdk.reader().getEventMessage().messageId(messageId).call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }
}
