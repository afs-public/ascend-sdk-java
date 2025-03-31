package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import java.util.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestRetirements {
  private SDK sdk;
  private Account account;

  @BeforeAll
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();
    account = AccountUtil.createEnrolledAccount(sdk);
  }

  @Test
  public void test_retirements_transfers_list_contribution_summaries_list_contribution_summaries1()
      throws Exception {
    var res =
        sdk.retirements().listContributionSummaries().accountId(account.accountId().get()).call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }

  @Order(1)
  @Test
  public void
      test_retirements_transfers_retrieve_contribution_constraints_retrieve_contribution_constraints1()
          throws Exception {

    var req =
        RetrieveContributionConstraintsRequestCreate.builder()
            .mechanism(Mechanism.ACH)
            .name("accounts/" + account.accountId().get())
            .build();
    var res =
        sdk.retirements()
            .retrieveContributionConstraints()
            .retrieveContributionConstraintsRequestCreate(req)
            .accountId(account.accountId().get())
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }

  @Order(2)
  @Test
  public void test_retirements_transfers_list_distribution_summaries_list_distribution_summaries1()
      throws Exception {
    var res =
        sdk.retirements().listDistributionSummaries().accountId(account.accountId().get()).call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }

  @Order(3)
  @Test
  public void
      test_retirements_transfers_retrieve_distribution_constraints_retrieve_distribution_constraints1()
          throws Exception {
    var req =
        RetrieveDistributionConstraintsRequestCreate.builder()
            .mechanism(RetrieveDistributionConstraintsRequestCreateMechanism.ACH)
            .name("accounts/" + account.accountId().get())
            .build();
    var res =
        sdk.retirements()
            .retrieveDistributionConstraints()
            .retrieveDistributionConstraintsRequestCreate(req)
            .accountId(account.accountId().get())
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }
}
