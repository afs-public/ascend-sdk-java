package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openapitools.jackson.nullable.JsonNullableModule;

public class EventTests {
  @Disabled
  @Test
  public void test_Can_Deserialize_Event_Data() throws Exception {
    String baseURL = System.getenv("BASE_URL");
    String apiKey = System.getenv("API_KEY");
    String privateKey = System.getenv("SERVICE_ACCOUNT_CREDS_PRIVATE_KEY");
    String name = System.getenv("SERVICE_ACCOUNT_CREDS_NAME");
    String organization = System.getenv("SERVICE_ACCOUNT_CREDS_ORGANIZATION");

    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new Jdk8Module());
    mapper.registerModule(new JsonNullableModule());
    mapper.registerModule(new JavaTimeModule());

    SDK sdk =
        SDK.builder()
            .serverURL(baseURL)
            .security(
                Security.builder()
                    .apiKey(apiKey)
                    .serviceAccountCreds(
                        ServiceAccountCreds.builder()
                            .privateKey(privateKey)
                            .name(name)
                            .organization(organization)
                            .type("serviceAccount")
                            .build())
                    .build())
            .build();

    // Credit event
    EventMessage message =
        sdk.reader().getEventMessage("01JHK72SA8MXJS7XV53AD6RXBW").eventMessage().get();
    Assertions.assertEquals("credit.v1.updated", message.eventType().get());

    System.out.println("Credit message: " + message.toString());

    TransfersCredit credit = mapper.convertValue(message.data().get(), TransfersCredit.class);

    System.out.println("Parsed message: " + credit.toString());

    Assertions.assertEquals("10000.00", credit.amount().get().value().get());
    Assertions.assertEquals(
        "5825cb57-5bcb-4bf4-88fb-c56095af3c87", credit.clientTransferId().get());
    Assertions.assertEquals(TransfersCreditStateState.POSTED, credit.state().get().state().get());
    Assertions.assertEquals(TransfersCreditType.PROMOTIONAL, credit.type().get());
  }
}
