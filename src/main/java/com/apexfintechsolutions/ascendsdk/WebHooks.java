/*
 * Code manually maintained by the SDK team.
 */

package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.EventMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.security.SignatureException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import org.openapitools.jackson.nullable.JsonNullableModule;

public class WebHooks {
  /** Validates an incoming webhook request. */
  public static EventMessage validateEventPayload(
      HttpServletRequest request, String webhookSecret, Duration allowedEventAge) throws Exception {

    String signatureHeader = request.getHeader("x-apex-event-signature");
    String sendTimeHeader = request.getHeader("x-apex-event-send-time");

    if (signatureHeader == null || sendTimeHeader == null) {
      throw new IllegalArgumentException(
          "Missing required headers: x-apex-event-signature and/or x-apex-event-send-time");
    }

    // Validate and parse the send time header
    Instant sendTime;
    try {
      sendTime = Instant.parse(sendTimeHeader); // Parsing ISO-8601 format (e.g., RFC3339)
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid send time format: " + sendTimeHeader, e);
    }

    Date sendTimeDate = Date.from(sendTime);
    if (eventOutOfRange(sendTimeDate, allowedEventAge)) {
      throw new IllegalArgumentException(
          "Event is out of range, it must be sent within the allowed event age.");
    }

    // Extract the request body
    String requestBody = extractRequestBody(request);

    verifySignature(signatureHeader, sendTimeHeader, requestBody, webhookSecret);

    // Attempt to deserialize the JSON payload into EventMessage
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.registerModule(new Jdk8Module());
      objectMapper.registerModule(new JsonNullableModule());
      objectMapper.registerModule(new JavaTimeModule());
      return objectMapper.readValue(requestBody, EventMessage.class);
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException("Failed to parse event message: " + e.getMessage(), e);
    }
  }

  // Check if the event is out of range
  private static boolean eventOutOfRange(Date sendTime, Duration allowedEventAge) {
    Date currentTime = Date.from(Instant.now());
    // event is too old
    boolean isTooOld = sendTime.before(Date.from(currentTime.toInstant().minus(allowedEventAge)));
    // event is in the future
    boolean isInTheFuture =
        sendTime.after(Date.from(currentTime.toInstant().plus(allowedEventAge)));
    return isTooOld || isInTheFuture;
  }

  private static String extractRequestBody(HttpServletRequest request) throws Exception {
    StringBuilder body = new StringBuilder();
    try (BufferedReader reader = request.getReader()) {
      String line;
      while ((line = reader.readLine()) != null) {
        body.append(line).append("\n");
      }
    }
    return body.toString();
  }

  // Verify Signature method
  private static void verifySignature(
      String headerSignature, String sendTimeHeader, String body, String secret) throws Exception {
    String payload = body.trim() + "." + sendTimeHeader;

    Mac mac = Mac.getInstance("HmacSHA256");
    SecretKeySpec secretKeySpec =
        new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
    mac.init(secretKeySpec);

    byte[] expectedSignatureBytes = mac.doFinal(payload.getBytes(StandardCharsets.UTF_8));
    String expectedSignature = bytesToHex(expectedSignatureBytes);

    if (!constantTimeEquals(expectedSignature, headerSignature)) {
      throw new SignatureException("Provided signature does not match calculated signature");
    }
  }

  // Convert bytes to hex string
  private static String bytesToHex(byte[] bytes) {
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

  // Constant time comparison to prevent timing attacks
  private static boolean constantTimeEquals(String a, String b) {
    if (a.length() != b.length()) {
      return false;
    }
    int comparison = 0;
    for (int i = 0; i < a.length(); i++) {
      comparison |= a.charAt(i) ^ b.charAt(i);
    }
    return comparison == 0;
  }
}
