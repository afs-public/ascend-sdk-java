package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.operations.*;
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
  public void test_account_transfers_account_transfers_list_transfers_list_transfers1()
      throws Exception {
    var request =
        AccountTransfersListTransfersRequest.builder()
            .correspondentId(SdkUtil.getCorrespondentId())
            .accountId(accountId)
            .build();

    var res = sdk.accountTransfers().listTransfers().request(request).call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }

  @Test
  @Order(3)
  public void test_account_transfers_account_transfers_reject_transfer_reject_transfer1()
      throws Exception {
    var request =
        RejectTransferRequestCreate.builder()
            .name(
                "correspondents/"
                    + SdkUtil.getCorrespondentId()
                    + "/accounts/"
                    + accountId
                    + "/transfers/"
                    + accountTransferId)
            .build();

    var res =
        sdk.accountTransfers()
            .rejectTransfer()
            .correspondentId(SdkUtil.getCorrespondentId())
            .accountId(accountId)
            .transferId(accountTransferId)
            .rejectTransferRequestCreate(request)
            .call();

    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }

  @Test
  @Order(4)
  public void test_account_transfers_account_transfers_accept_transfer_accept_transfer1()
      throws Exception {

    var accept_transfer_id = AccountTransfersUtil.createAccountTransferId(sdk, accountId);
    var request =
        AcceptTransferRequestCreate.builder()
            .name(
                "correspondents/"
                    + SdkUtil.getCorrespondentId()
                    + "/accounts/"
                    + accountId
                    + "/transfers/"
                    + accept_transfer_id)
            .build();

    var res =
        sdk.accountTransfers()
            .acceptTransfer()
            .correspondentId(SdkUtil.getCorrespondentId())
            .accountId(accountId)
            .transferId(accept_transfer_id)
            .acceptTransferRequestCreate(request)
            .call();

    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }

  @Test
  @Order(5)
  public void test_account_transfers_account_transfers_get_transfer_get_transfer1()
      throws Exception {
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
