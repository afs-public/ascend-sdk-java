package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.operations.ProgramDateFilterProgram;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestInstantCashTransfers {
  private SDK sdk;
  private String enrolledAccountId;
  private String depositId;
  private String withdrawalId;

  @BeforeAll
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();
    enrolledAccountId = AccountUtil.createEnrolledAccount(sdk).accountId().get();
    depositId = TransfersUtil.createIctDepositId(sdk, enrolledAccountId);
    withdrawalId = TransfersUtil.createIctWithdrawalId(sdk, enrolledAccountId);
    Thread.sleep(5000);
  }

  @Test
  @Order(1)
  public void test_instant_cash_transfer_transfers_create_ict_deposit_create_ict_deposit1()
      throws Exception {
    Assertions.assertNotNull(depositId);
  }

  @Test
  @Order(2)
  public void test_instant_cash_transfer_transfers_get_ict_deposit_get_ict_deposit1()
      throws Exception {
    var res =
        sdk.instantCashTransferICT()
            .getIctDeposit()
            .accountId(enrolledAccountId)
            .ictDepositId(depositId)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }

  @Test
  @Order(3)
  public void test_instant_cash_transfer_transfers_cancel_ict_deposit_cancel_ict_deposit1()
      throws Exception {
    var req =
        CancelIctDepositRequestCreate.builder()
            .name("accounts/" + enrolledAccountId + "/ictDeposits/" + depositId + ":cancel")
            .reason("User requested")
            .build();
    var res =
        sdk.instantCashTransferICT()
            .cancelIctDeposit()
            .accountId(enrolledAccountId)
            .ictDepositId(depositId)
            .cancelIctDepositRequestCreate(req)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }

  @Test
  @Order(1)
  public void test_instant_cash_transfer_transfers_create_ict_withdrawal_create_ict_withdrawal1()
      throws Exception {
    Assertions.assertNotNull(withdrawalId);
  }

  @Test
  @Order(2)
  public void test_instant_cash_transfer_transfers_get_ict_withdrawal_get_ict_withdrawal1()
      throws Exception {
    var res =
        sdk.instantCashTransferICT()
            .getIctWithdrawal()
            .accountId(enrolledAccountId)
            .ictWithdrawalId(withdrawalId)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }

  @Test
  @Order(3)
  public void test_instant_cash_transfer_transfers_cancel_ict_withdrawal_cancel_ict_withdrawal1()
      throws Exception {
    var req =
        CancelIctWithdrawalRequestCreate.builder()
            .name("accounts/" + enrolledAccountId + "/ictWithdrawals/" + withdrawalId + ":cancel")
            .reason("User requested")
            .build();
    var res =
        sdk.instantCashTransferICT()
            .cancelIctWithdrawal()
            .accountId(enrolledAccountId)
            .ictWithdrawalId(withdrawalId)
            .cancelIctWithdrawalRequestCreate(req)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }

  @Test
  public void test_instant_cash_transfer_transfers_locate_ict_report_locate_ict_report1()
      throws Exception {
    var res =
        sdk.instantCashTransferICT()
            .locateIctReport()
            .correspondentId(SdkUtil.getCorrespondentId())
            .programDateFilterProgram(ProgramDateFilterProgram.BROKER_PARTNER)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }
}
