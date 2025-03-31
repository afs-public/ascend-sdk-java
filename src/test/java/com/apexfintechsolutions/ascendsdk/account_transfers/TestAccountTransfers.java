package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestAccountTransfers {
  private SDK sdk;
  private String accountId;
  private String accountTransferId;

  @BeforeAll
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();
    accountId = AccountUtil.createEnrolledAccount(sdk).accountId().get();
    Thread.sleep(5000);
    accountTransferId = AccountTransfersUtil.createAccountTransferId(sdk, accountId);
  }

  @Test
  @Order(1)
  public void test_account_transfers_account_transfers_create_transfer_create_transfer1()
      throws Exception {
    Assertions.assertNotNull(accountTransferId);
  }

  @Test
  @Order(2)
  public void test_account_transfers_account_transfers_get_transfer_get_transfer1()
      throws Exception {
    Thread.sleep(5000);
    var res =
        sdk.accountTransfers()
            .getTransfer()
            .correspondentId(SdkUtil.getCorrespondentId())
            .accountId(accountId)
            .transferId(accountTransferId)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }
}
