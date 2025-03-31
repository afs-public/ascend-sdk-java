package com.apexfintechsolutions.ascendsdk.utils;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.SecuritySource;
import com.apexfintechsolutions.ascendsdk.models.components.GenerateServiceAccountTokenRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AuthenticationGenerateServiceAccountTokenResponse;
import com.apexfintechsolutions.ascendsdk.models.operations.AuthenticationGenerateServiceAccountTokenSecurity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Map;

public class ApexSecuritySource implements SecuritySource {
  private final String apiKey;
  private final String ascendCredsJson;
  private final SDK sdk;
  private String accessToken;

  private LocalDateTime accessTokenExpiration;

  public ApexSecuritySource(String serverUrl, String apiKey, String ascendCredsJson) {
    this.apiKey = apiKey;
    this.ascendCredsJson = ascendCredsJson;
    try {
      sdk =
          SDK.builder()
              .serverURL(serverUrl)
              .security(Security.builder().apiKeyAuth(apiKey).bearerAuth("").build())
              .build();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String serverUrl = "https://api.apexapis.com";
    private String apiKey;
    private String ascendCredsJson;

    public Builder serverUrl(String serverUrl) {
      this.serverUrl = serverUrl;
      return this;
    }

    public Builder apiKey(String apiKey) {
      this.apiKey = apiKey;
      return this;
    }

    public ApexSecuritySource.Builder ascendCredsJson(String ascendCredsJson) {
      this.ascendCredsJson = ascendCredsJson;
      return this;
    }

    public ApexSecuritySource build() {
      return new ApexSecuritySource(serverUrl, apiKey, ascendCredsJson);
    }
  }

  @Override
  public Security getSecurity() {
    if (accessTokenStillValid()) {
      return Security.builder().apiKeyAuth(apiKey).bearerAuth(accessToken).build();
    }
    try {
      String jws = createJws(ascendCredsJson);
      AuthenticationGenerateServiceAccountTokenResponse res =
          sdk.authentication()
              .generateServiceAccountToken(
                  new GenerateServiceAccountTokenRequestCreate(jws),
                  new AuthenticationGenerateServiceAccountTokenSecurity(apiKey));
      if (res.statusCode() != 200) {
        throw new RuntimeException(
            "res.status.message = " + res.status().flatMap(Status::message).orElse("No message"));
      }
      if (res.token().isPresent()) {
        if (res.token().get().accessToken().isPresent()) {
          accessToken = res.token().get().accessToken().get();
          accessTokenExpiration = LocalDateTime.now().plusMinutes(60 * 25 - 30);
        }
      }
      if (accessToken == null) {
        throw new RuntimeException("");
      }
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }

    return Security.builder().apiKeyAuth(apiKey).bearerAuth(accessToken).build();
  }

  private boolean accessTokenStillValid() {
    if (accessToken == null) {
      return false;
    }
    return accessTokenExpiration != null && accessTokenExpiration.isAfter(LocalDateTime.now());
  }

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  private static String createJws(String apexClientCredsJson) {
    try {
      Map<?, ?> map = OBJECT_MAPPER.readValue(apexClientCredsJson, Map.class);
      // RSA private key in PKCS#8 format
      // Remove headers and newline characters from the private key
      String privateKeyContent =
          map.get("privateKey")
              .toString()
              .replace("\n", "")
              .replace("\r", "")
              .replace("-----BEGIN PRIVATE KEY-----", "")
              .replace("-----END PRIVATE KEY-----", "");

      // Decode the base64-encoded private key
      byte[] decodedKey = Base64.getDecoder().decode(privateKeyContent);

      // Reconstruct the private key
      PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);

      String nowIsoDateTime =
          ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_DATE_TIME);

      // Create an RSA256 algorithm instance with the RSA private key
      JWSSigner algorithm = new RSASSASigner(privateKey);

      JWTClaimsSet claimsSet =
          new JWTClaimsSet.Builder()
              .issuer("issuer")
              .subject("subject")
              .claim("name", map.get("name"))
              .claim("organization", map.get("organization"))
              .claim("datetime", nowIsoDateTime)
              .build();

      SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256), claimsSet);

      signedJWT.sign(algorithm);
      return signedJWT.serialize();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
