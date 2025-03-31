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
}
