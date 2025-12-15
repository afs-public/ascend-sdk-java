package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestInvestigations {

  private final String apex_investigation_id = "01JP8EHZ3CJKCTMHKTT4FZ51HC";

  @Test
  public void test_investigations_investigation_service_get_investigation_get_investigation1()
      throws Exception {
    var sdk = SdkUtil.getSdk();
    Assertions.assertNotNull(sdk);

    var res =
        sdk.investigations()
            .getInvestigation()
            .investigationId(AccountUtil.getInvestigationId())
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }

  @Test
  public void test_investigations_investigation_service_list_investigations_list_investigations1()
      throws Exception {
    var sdk = SdkUtil.getSdk();
    Assertions.assertNotNull(sdk);

    var res = sdk.investigations().listInvestigations().call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }

  @Test
  public void test_investigations_watchlist_service_get_watchlist_item_get_watchlist_item1()
      throws Exception {
    var sdk = SdkUtil.getSdk();
    Assertions.assertNotNull(sdk);

    var res =
        sdk.investigations().getWatchlistItem().watchlistId("DOWJONES").itemId("123456").call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }

  @Test
  public void test_investigations_investigation_service_update_investigation_update_investigation1()
      throws Exception {
    var sdk = SdkUtil.getSdk();
    Assertions.assertNotNull(sdk);

    var update = new InvestigationUpdate().withComment("new investigation name");
    var res =
        sdk.investigations()
            .updateInvestigation()
            .investigationId(AccountUtil.getInvestigationId())
            .investigationUpdate(update)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }

  @Test
  public void test_investigations_investigation_service_link_documents_link_documents1()
      throws Exception {
    var sdk = SdkUtil.getSdk();
    Assertions.assertNotNull(sdk);

    var linkDocumentsRequest =
        new LinkDocumentsRequestCreate(List.of(UUID.randomUUID().toString()));
    var res =
        sdk.investigations()
            .linkDocuments()
            .investigationId(AccountUtil.getInvestigationId())
            .linkDocumentsRequestCreate(linkDocumentsRequest)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }

  @Test
  public void test_investigations_investigations_get_identify_verification() throws Exception {
    var sdk = SdkUtil.getSdk();
    var investigation = sdk.investigations().getInvestigation(apex_investigation_id);
    var id_results = investigation.investigation().get().identityVerificationResults().get();
    Assertions.assertTrue(id_results.size() > 0);
    var first_id = id_results.get(0);
    var result =
        sdk.investigations()
            .getCustomerIdentification(
                SdkUtil.getCorrespondentId(), first_id.customerIdentificationId().get());
    Assertions.assertEquals(result.statusCode(), 200);
  }

  @Test
  public void test_identity_lookup_service_create_identity_lookup() throws Exception {
    var sdk = SdkUtil.getSdk();
    Assertions.assertNotNull(sdk);

    var request =
        IdentityLookupCreate.builder()
            .deviceMetadata(DeviceMetadataCreate.builder().ipAddress("203.0.113.42").build())
            .identification(
                IdentificationCreate.builder()
                    .regionCode("US")
                    .type(IdentificationCreateType.SSN)
                    .value("123-45-6789")
                    .build())
            .phoneNumber(
                PhoneNumberCreate.builder().e164Number("+15035550123").extension("123").build())
            .userConsent(true)
            .build();

    var res =
        sdk.investigations()
            .createIdentityLookup()
            .correspondentId(SdkUtil.getCorrespondentId())
            .identityLookupCreate(request)
            .call();

    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
    Assertions.assertTrue(res.identityLookup().isPresent());
    Assertions.assertTrue(res.identityLookup().get().name().isPresent());

    // Extract identity lookup ID from name
    String name = res.identityLookup().get().name().get();
    String[] nameParts = name.split("/");
    String lookupId = nameParts[nameParts.length - 1];
    Assertions.assertNotNull(lookupId);
    Assertions.assertTrue(lookupId.length() > 0);
  }

  @Test
  public void test_identity_lookup_service_verify_identity_lookup() throws Exception {
    var sdk = SdkUtil.getSdk();
    Assertions.assertNotNull(sdk);

    // First create an identity lookup
    var createRequest =
        IdentityLookupCreate.builder()
            .deviceMetadata(DeviceMetadataCreate.builder().ipAddress("203.0.113.42").build())
            .identification(
                IdentificationCreate.builder()
                    .regionCode("US")
                    .type(IdentificationCreateType.SSN)
                    .value("123-45-6789")
                    .build())
            .phoneNumber(
                PhoneNumberCreate.builder().e164Number("+15035550123").extension("123").build())
            .userConsent(true)
            .build();

    var createRes =
        sdk.investigations()
            .createIdentityLookup()
            .correspondentId(SdkUtil.getCorrespondentId())
            .identityLookupCreate(createRequest)
            .call();

    Assertions.assertTrue(createRes.identityLookup().isPresent());
    Assertions.assertTrue(createRes.identityLookup().get().name().isPresent());

    // Extract identity lookup ID from name
    String name = createRes.identityLookup().get().name().get();
    String[] nameParts = name.split("/");
    String lookupId = nameParts[nameParts.length - 1];

    // Now verify the identity lookup
    var verifyRequest =
        VerifyIdentityLookupRequestCreate.builder()
            .name("correspondents/" + SdkUtil.getCorrespondentId() + "/identityLookups/" + lookupId)
            .verificationCode("123456") // This is a test verification code
            .build();

    try {
      var res =
          sdk.investigations()
              .verifyIdentityLookup()
              .correspondentId(SdkUtil.getCorrespondentId())
              .identityLookupId(lookupId)
              .verifyIdentityLookupRequestCreate(verifyRequest)
              .call();

      Assertions.assertNotNull(res);
      Assertions.assertEquals(200, res.statusCode());
    } catch (Exception error) {
      // The verification may fail with invalid code, which is expected in test environment
      // We're just testing that the endpoint is callable
      Assertions.assertTrue(error.getMessage().toLowerCase().contains("verification"));
    }
  }
}
