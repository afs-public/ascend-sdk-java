package com.apexfintechsolutions.ascendsdk;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.apexfintechsolutions.ascendsdk.models.components.EventMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.BufferedReader;
import java.io.StringReader;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openapitools.jackson.nullable.JsonNullableModule;

public class WebHooksTests {

  // Test constants
  private static final String TEST_SECRET = "my-super-secret-webhook-key";
  private static final Duration TEST_ALLOWED_AGE = Duration.ofMinutes(5);

  private static final String TEST_MESSAGE_ID = "msg_2WpGqg8fH9jZ7kY6aB3dX2eF1c";
  private static final String TEST_EVENT_TYPE = "transaction.created";
  private static final String TEST_ACCOUNT_ID = "acc_1a2b3c4d5e6f7g8h9i";
  private static final String TEST_CLIENT_ID = "cli_j0k1l2m3n4p5q6r7s8";
  private static final String TEST_PARTITION_KEY = "user_xyz";

  @Test
  void testValidEventPayload() throws Exception {
    // Generate a valid send time, truncated to seconds
    Instant validSendTime = Instant.now().truncatedTo(ChronoUnit.SECONDS);

    // Generate valid body using helper
    String body = createTestBody(validSendTime);

    // Calculate the expected signature
    String expectedSignature =
        calculateExpectedSignature(body.trim(), validSendTime.toString(), TEST_SECRET);

    // Mock HttpServletRequest
    HttpServletRequest request = mock(HttpServletRequest.class);

    // Set up mocked headers
    when(request.getHeader("x-apex-event-signature")).thenReturn(expectedSignature);
    when(request.getHeader("x-apex-event-send-time")).thenReturn(validSendTime.toString());

    // Set up mocked body
    when(request.getReader()).thenReturn(new BufferedReader(new StringReader(body)));

    // Call the validateEventPayload method
    EventMessage eventMessage =
        WebHooks.validateEventPayload(request, TEST_SECRET, TEST_ALLOWED_AGE);

    // Validate the fields
    assertNotNull(eventMessage);
    assertTrue(eventMessage.messageId().isPresent(), "messageId should be present");
    assertEquals(TEST_MESSAGE_ID, eventMessage.messageId().get());
    assertTrue(eventMessage.eventType().isPresent(), "eventType should be present");
    assertEquals(TEST_EVENT_TYPE, eventMessage.eventType().get());
    assertTrue(eventMessage.accountId().isPresent(), "accountId should be present");
    assertEquals(TEST_ACCOUNT_ID, eventMessage.accountId().get());
    assertTrue(eventMessage.clientId().isPresent(), "clientId should be present");
    assertEquals(TEST_CLIENT_ID, eventMessage.clientId().get());
    assertTrue(eventMessage.name().isPresent(), "name should be present");
    assertEquals("messages/" + TEST_MESSAGE_ID, eventMessage.name().get());
    assertTrue(eventMessage.partitionKey().isPresent(), "partitionKey should be present");
    assertEquals(TEST_PARTITION_KEY, eventMessage.partitionKey().get());
    assertTrue(eventMessage.publishTime().isPresent(), "publishTime should be present");
    assertEquals(validSendTime, eventMessage.publishTime().get().toInstant());

    assertTrue(eventMessage.data().isPresent(), "data should be present");
    assertTrue(eventMessage.data().get().containsKey("status"), "data should contain status");
    assertEquals("completed", eventMessage.data().get().get("status"));
  }

  @Test
  void testErrorInvalidSignature() throws Exception {
    // Generate a valid send time, truncated to seconds
    Instant validSendTime = Instant.now().truncatedTo(ChronoUnit.SECONDS);

    // Generate valid body using helper
    String body = createTestBody(validSendTime);

    // Mock HttpServletRequest
    HttpServletRequest request = mock(HttpServletRequest.class);

    // Set up mocked headers
    when(request.getHeader("x-apex-event-signature"))
        .thenReturn("this-is-not-the-correct-signature");
    when(request.getHeader("x-apex-event-send-time")).thenReturn(validSendTime.toString());

    // Set up mocked body
    when(request.getReader()).thenReturn(new BufferedReader(new StringReader(body)));

    // Act and Assert
    Exception exception =
        assertThrows(
            Exception.class,
            () -> {
              WebHooks.validateEventPayload(request, TEST_SECRET, TEST_ALLOWED_AGE);
            });

    assertTrue(
        exception.getMessage().contains("Provided signature does not match calculated signature"));
  }

  @Test
  void testErrorExpiredEvent() throws Exception {
    // Generate a valid send time, truncated to seconds
    Instant expiredSendTime =
        Instant.now()
            .minus(TEST_ALLOWED_AGE)
            .minus(Duration.ofMinutes(1))
            .truncatedTo(ChronoUnit.SECONDS);

    // Generate valid body using helper
    String body = createTestBody(expiredSendTime);

    // Calculate the expected signature
    String expectedSignature =
        calculateExpectedSignature(body.trim(), expiredSendTime.toString(), TEST_SECRET);

    // Mock HttpServletRequest
    HttpServletRequest request = mock(HttpServletRequest.class);

    // Set up mocked headers
    when(request.getHeader("x-apex-event-signature")).thenReturn(expectedSignature);
    when(request.getHeader("x-apex-event-send-time")).thenReturn(expiredSendTime.toString());

    // Set up mocked body
    when(request.getReader()).thenReturn(new BufferedReader(new StringReader(body)));

    // Act and Assert
    Exception exception =
        assertThrows(
            Exception.class,
            () -> {
              WebHooks.validateEventPayload(request, TEST_SECRET, TEST_ALLOWED_AGE);
            });

    assertTrue(exception.getMessage().contains("Event is out of range"));
  }

  @Test
  void testErrorMissingSignatureHeader() throws Exception {
    // Generate a valid send time, truncated to seconds
    Instant validSendTime = Instant.now().truncatedTo(ChronoUnit.SECONDS);

    // Generate valid body using helper
    String body = createTestBody(validSendTime);

    // Mock HttpServletRequest
    HttpServletRequest request = mock(HttpServletRequest.class);

    // Set up mocked headers
    when(request.getHeader("x-apex-event-send-time")).thenReturn(validSendTime.toString());

    // Set up mocked body
    when(request.getReader()).thenReturn(new BufferedReader(new StringReader(body)));

    // Act and Assert
    Exception exception =
        assertThrows(
            Exception.class,
            () -> {
              WebHooks.validateEventPayload(request, TEST_SECRET, TEST_ALLOWED_AGE);
            });

    assertTrue(
        exception
            .getMessage()
            .contains(
                "Missing required headers: x-apex-event-signature and/or x-apex-event-send-time"));
  }

  @Test
  void testErrorMissingSendTimeHeader() throws Exception {
    // Generate a valid send time, truncated to seconds
    Instant validSendTime = Instant.now().truncatedTo(ChronoUnit.SECONDS);

    // Generate valid body using helper
    String body = createTestBody(validSendTime);

    // Calculate the expected signature
    String expectedSignature =
        calculateExpectedSignature(body.trim(), validSendTime.toString(), TEST_SECRET);

    // Mock HttpServletRequest
    HttpServletRequest request = mock(HttpServletRequest.class);

    // Set up mocked headers
    when(request.getHeader("x-apex-event-signature")).thenReturn(expectedSignature);

    // Set up mocked body
    when(request.getReader()).thenReturn(new BufferedReader(new StringReader(body)));

    // Act and Assert
    Exception exception =
        assertThrows(
            Exception.class,
            () -> {
              WebHooks.validateEventPayload(request, TEST_SECRET, TEST_ALLOWED_AGE);
            });

    assertTrue(
        exception
            .getMessage()
            .contains(
                "Missing required headers: x-apex-event-signature and/or x-apex-event-send-time"));
  }

  @Test
  void testErrorInvalidSendTimeFormat() throws Exception {
    // Generate a valid send time, truncated to seconds
    Instant validSendTime = Instant.now().truncatedTo(ChronoUnit.SECONDS);

    // Generate valid body using helper
    String body = createTestBody(validSendTime);

    // Calculate the expected signature
    String expectedSignature =
        calculateExpectedSignature(body.trim(), validSendTime.toString(), TEST_SECRET);

    // Mock HttpServletRequest
    HttpServletRequest request = mock(HttpServletRequest.class);

    // Set up mocked headers
    when(request.getHeader("x-apex-event-signature")).thenReturn(expectedSignature);
    when(request.getHeader("x-apex-event-send-time")).thenReturn("not-a-valid-time");

    // Set up mocked body
    when(request.getReader()).thenReturn(new BufferedReader(new StringReader(body)));

    // Act and Assert
    Exception exception =
        assertThrows(
            Exception.class,
            () -> {
              WebHooks.validateEventPayload(request, TEST_SECRET, TEST_ALLOWED_AGE);
            });

    assertTrue(exception.getMessage().contains("Invalid send time format"));
  }

  @Test
  void testErrorInvalidJsonBody() throws Exception {
    // Generate a valid send time, truncated to seconds
    Instant validSendTime = Instant.now().truncatedTo(ChronoUnit.SECONDS);

    // Malformed body with missing comma
    String invalidBody = "{\"id\":\"evt_123\", \"type\": \"user.created\" \"data\":{}}";

    // Calculate the expected signature
    String expectedSignature =
        calculateExpectedSignature(invalidBody.trim(), validSendTime.toString(), TEST_SECRET);

    // Mock HttpServletRequest
    HttpServletRequest request = mock(HttpServletRequest.class);

    // Set up mocked headers
    when(request.getHeader("x-apex-event-signature")).thenReturn(expectedSignature);
    when(request.getHeader("x-apex-event-send-time")).thenReturn(validSendTime.toString());

    // Set up mocked body
    when(request.getReader()).thenReturn(new BufferedReader(new StringReader(invalidBody)));

    // Act and Assert
    Exception exception =
        assertThrows(
            Exception.class,
            () -> {
              WebHooks.validateEventPayload(request, TEST_SECRET, TEST_ALLOWED_AGE);
            });

    assertTrue(exception.getMessage().contains("Failed to parse event message"));
  }

  private String createTestBody(Instant publishTime) {
    return "{"
        + "\"message_id\":\""
        + TEST_MESSAGE_ID
        + "\","
        + "\"event_type\":\""
        + TEST_EVENT_TYPE
        + "\","
        + "\"account_id\":\""
        + TEST_ACCOUNT_ID
        + "\","
        + "\"client_id\":\""
        + TEST_CLIENT_ID
        + "\","
        + "\"name\":\"messages/"
        + TEST_MESSAGE_ID
        + "\","
        + "\"partition_key\":\""
        + TEST_PARTITION_KEY
        + "\","
        + "\"publish_time\":\""
        + publishTime.toString()
        + "\","
        + "\"data\":{"
        + "   \"transactionId\":\"txn_abcdef123456\","
        + "   \"amount\":100.50,"
        + "   \"currency\":\"USD\","
        + "   \"status\":\"completed\""
        + "}"
        + "}";
  }

  private String calculateExpectedSignature(String body, String sendTimeHeader, String secret)
      throws Exception {
    String payload = body.trim() + "." + sendTimeHeader;

    javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA256");
    javax.crypto.spec.SecretKeySpec secretKeySpec =
        new javax.crypto.spec.SecretKeySpec(secret.getBytes(), "HmacSHA256");
    mac.init(secretKeySpec);

    byte[] rawHmac = mac.doFinal(payload.getBytes());
    return bytesToHex(rawHmac);
  }

  private String bytesToHex(byte[] bytes) {
    StringBuilder hexString = new StringBuilder();
    for (byte b : bytes) {
      String hex = Integer.toHexString(0xFF & b);
      if (hex.length() == 1) {
        hexString.append('0');
      }
      hexString.append(hex);
    }
    return hexString.toString();
  }

  @Test
  void testIntegrationGetEventMessageAndValidate() throws Exception {
    SDK sdk = SdkUtil.getSdk();
    var events = sdk.reader().listEventMessages().call();
    Assertions.assertNotNull(events);
    Assertions.assertEquals(200, events.statusCode(), "Expected status code 200");

    String eventMessageId =
        events
            .listEventMessagesResponse()
            .orElseThrow(() -> new Exception("Failed to get event messages"))
            .eventMessages()
            .orElseThrow(() -> new Exception("No event messages found"))
            .get(0)
            .messageId()
            .orElseThrow(() -> new Exception("Message ID not found"));

    var event = sdk.reader().getEventMessage().messageId(eventMessageId).call();
    Assertions.assertNotNull(event);
    Assertions.assertEquals(200, event.statusCode(), "Expected status code 200");

    // Simulate webhook request
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new Jdk8Module());
    objectMapper.registerModule(new JsonNullableModule());
    objectMapper.registerModule(new JavaTimeModule());
    String simulatedBody = objectMapper.writeValueAsString(event.eventMessage());

    System.out.println("Simulated body: " + simulatedBody);
    String secretKey = "super-secret-key";
    Instant validSendTime = Instant.now().truncatedTo(ChronoUnit.SECONDS);
    String sendTimeStr = validSendTime.toString();

    String signature = calculateExpectedSignature(simulatedBody, sendTimeStr, secretKey);

    // Mock HttpServletRequest
    HttpServletRequest request = mock(HttpServletRequest.class);

    // Set up mocked headers
    when(request.getHeader("x-apex-event-signature")).thenReturn(signature);
    when(request.getHeader("x-apex-event-send-time")).thenReturn(sendTimeStr);
    when(request.getHeader("Content-Type")).thenReturn("application/json");

    // Set up mocked body
    when(request.getReader()).thenReturn(new BufferedReader(new StringReader(simulatedBody)));

    // Pass to validation function
    EventMessage validatedEvent =
        WebHooks.validateEventPayload(request, secretKey, Duration.ofMinutes(5));
    Assertions.assertNotNull(validatedEvent, "Validated event should not be null");
  }
}
