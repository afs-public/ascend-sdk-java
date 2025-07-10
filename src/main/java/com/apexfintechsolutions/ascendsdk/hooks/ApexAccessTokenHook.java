package com.apexfintechsolutions.ascendsdk.hooks;

import com.apexfintechsolutions.ascendsdk.SecuritySource;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.utils.HasSecurity;
import com.apexfintechsolutions.ascendsdk.utils.Hook;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.LocalDateTime;
import java.util.*;

final class ApexAccessTokenHook implements Hook.BeforeRequest {

  private static String accessToken;
  private LocalDateTime accessTokenExpiration;

  @Override
  public HttpRequest beforeRequest(Hook.BeforeRequestContext context, HttpRequest request)
      throws Exception {

    // Handle securitySource being Optional
    Optional<SecuritySource> securitySourceOptional = context.securitySource();
    if (securitySourceOptional.isEmpty()) {
      throw new Exception("Security source is empty.");
    }

    // Extract the HasSecurity object from SecuritySource
    SecuritySource securitySource = securitySourceOptional.get();
    HasSecurity hasSecurity = securitySource.getSecurity();

    // Cast or process HasSecurity as Security (assuming this is correct)
    if (!(hasSecurity instanceof Security)) {
      throw new Exception("Security source does not implement Security.");
    }
    Security security = (Security) hasSecurity;

    // Validate the API key
    if (security.apiKey().isEmpty()) {
      throw new Exception("API key is empty.");
    }

    // Configure request headers
    HttpRequest.Builder requestBuilder =
        HttpRequest.newBuilder(request.uri())
            .method(
                request.method(),
                request.bodyPublisher().orElse(HttpRequest.BodyPublishers.noBody()))
            .header("x-api-key", security.apiKey().get());

    // Re-add the original headers such that they are not lost
    request
        .headers()
        .map()
        .forEach(
            (key, values) -> {
              for (String value : values) {
                requestBuilder.header(key, value);
              }
            });

    // Validate and process service account credentials
    if (security.serviceAccountCreds().isEmpty()) {
      throw new Exception("Service account credentials are empty.");
    }

    String serverUrl = request.uri().getScheme() + "://" + request.uri().getHost();
    String token =
        getAccessToken(serverUrl, security.apiKey().get(), security.serviceAccountCreds().get());
    return requestBuilder.header("Authorization", "Bearer " + token).build();
  }

  private String getAccessToken(String serverUrl, String apiKey, ServiceAccountCreds creds)
      throws Exception {
    if (isAccessTokenExpired()) {
      // Create JWT token as JWS
      String jws = createJws(creds);

      // Use JWS to acquire new access token
      accessToken = generateAccessToken(serverUrl, apiKey, jws);
    }
    return accessToken;
  }

  private String generateAccessToken(String serverUrl, String apiKey, String jws) throws Exception {
    String url = String.format("%s/iam/v1/serviceAccounts:generateAccessToken", serverUrl);

    Map<String, String> bodyData = new HashMap<>();
    bodyData.put("jws", jws);

    ObjectMapper objectMapper = new ObjectMapper();
    String body = objectMapper.writeValueAsString(bodyData);

    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create(url))
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .header("Content-Type", "application/json")
            .header("x-api-key", apiKey)
            .build();

    HttpClient client = HttpClient.newHttpClient();
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    // Check response status
    if (response.statusCode() != 200) {
      throw new Exception("Failed to get access token. Status Code: " + response.statusCode());
    }

    // Parse JSON response for token
    JsonNode jsonNode = objectMapper.readTree(response.body());
    accessToken = jsonNode.get("access_token").asText();
    int expiresIn = jsonNode.get("expires_in").asInt();

    // Set expiration time
    accessTokenExpiration = LocalDateTime.now().plusSeconds(expiresIn);
    return accessToken;
  }

  private String createJws(ServiceAccountCreds creds) throws Exception {
    // Get and sanitize the private key
    String privateKeyContent =
        creds
            .privateKey()
            .replace("\\n", "")
            .replace("\r", "")
            .replace("-----BEGIN PRIVATE KEY-----", "")
            .replace("-----END PRIVATE KEY-----", "");

    byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyContent);
    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
    PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(keySpec);

    // Build JWT claims
    Date now = new Date();
    JWTClaimsSet claimsSet =
        new JWTClaimsSet.Builder()
            .issuer("issuer")
            .subject("subject")
            .claim("name", creds.name())
            .claim("organization", creds.organization())
            .claim("datetime", now.toInstant().toString())
            .issueTime(now)
            .build();

    // Create JWS header and sign using RS256
    JWSSigner signer = new RSASSASigner(privateKey);
    SignedJWT signedJWT =
        new SignedJWT(new JWSHeader.Builder(JWSAlgorithm.RS256).build(), claimsSet);

    signedJWT.sign(signer);

    // Serialize the signed JWT to compact form
    return signedJWT.serialize();
  }

  private boolean isAccessTokenExpired() {
    if (accessTokenExpiration == null) {
      return true;
    }
    return LocalDateTime.now().isAfter(accessTokenExpiration);
  }
}
