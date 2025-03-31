package com.apexfintechsolutions.ascendsdk;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class OpenEnumTests {
  @Disabled
  @Test
  public void test_Can_Fetch_Open_Enum_Data() throws Exception {
    WireMock.configureFor(9980);

    WireMockServer mockServer = new WireMockServer(wireMockConfig().port(9980));
    mockServer.start();

    stubFor(
        post(urlEqualTo("/iam/v1/serviceAccounts:generateAccessToken"))
            .willReturn(
                aResponse()
                    .withHeader("content-type", "application/json")
                    .withHeader("x-correlation-id", "abcdefg")
                    .withHeader("x-request-id", "123456")
                    .withBody(
                        "{\n"
                            + "    \"access_token\": \"test_access_token\",\n"
                            + "    \"token_type\": \"Bearer\",\n"
                            + "    \"expires_in\": 90000\n"
                            + "}")));

    stubFor(
        get(urlEqualTo("/assets/v1/assets/204218"))
            .willReturn(
                aResponse()
                    .withHeader("content-type", "application/json")
                    .withHeader("x-correlation-id", "abcdefg")
                    .withHeader("x-request-id", "204218")
                    .withBody(
                        "        {\n"
                            + "            \"name\": \"assets/204218\",\n"
                            + "            \"asset_id\": \"204218\",\n"
                            + "            \"equity\": {\n"
                            + "                \"symbol\": \"EPWK\",\n"
                            + "                \"symbol_description\": \"EPWK\",\n"
                            + "                \"type\": \"OPEN_ENUM_TEST_ASSET_TYPE\",\n"
                            + "                \"exchange_code\": \"3\",\n"
                            + "                \"region_code\": \"US\",\n"
                            + "                \"is_current\": true,\n"
                            + "                \"tradeable\": false,\n"
                            + "                \"fractionable\": false,\n"
                            + "                \"liquidate\": false,\n"
                            + "                \"primary_exchange\": \"exchanges/3\",\n"
                            + "                \"primary_exchange_id\": \"3\"\n"
                            + "            },\n"
                            + "            \"symbol\": \"EPWK\",\n"
                            + "            \"type\": \"EQUITY\",\n"
                            + "            \"description\": \"EPWK\",\n"
                            + "            \"issuing_region_code\": \"US\",\n"
                            + "            \"usable\": true,\n"
                            + "            \"originating_region_code\": \"KY\"\n"
                            + "        },")));

    SDK sdk =
        SDK.builder()
            .serverURL("http://localhost:9980")
            .security(Security.builder().bearerAuth("MOCK").apiKeyAuth("MOCK").build())
            .build();

    var result = sdk.assets().getAsset().assetId("204218").call();
    System.out.println("result: " + result.toString());
  }
}
