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
public class TestAchTransfers {
  private SDK sdk;
  private Account enrolledAccount;
  private AchWithdrawal withdrawal;
  private AchDeposit deposit;
  private BankRelationship bankRel;

  @BeforeAll()
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();
    enrolledAccount = AccountUtil.createEnrolledAccount(sdk);
    bankRel = TransfersUtil.createVerifiedBankRelationship(sdk, enrolledAccount);
    withdrawal = TransfersUtil.createAchWithdrawal(sdk, enrolledAccount, bankRel);
    deposit = TransfersUtil.createAchDeposit(sdk, enrolledAccount, bankRel);
    Thread.sleep(5000);
  }

  @Order(1)
  @Test
  public void ach_transfers_transfers_create_ach_deposit_create_ach_deposit1() throws Exception {
    Assertions.assertNotNull(deposit);
  }

  @Order(2)
  @Test
  public void ach_transfers_transfers_get_ach_deposit_get_ach_deposit1() throws Exception {
    var result =
        sdk.achTransfers()
            .getAchDeposit(
                enrolledAccount.accountId().get(), TransfersUtil.getAchDepositId(deposit));
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(3)
  @Test
  public void ach_transfers_transfers_cancel_ach_deposit_cancel_ach_deposit1() throws Exception {
    var request = CancelAchDepositRequestCreate.builder().name(deposit.name().get()).build();
    var result =
        sdk.achTransfers()
            .cancelAchDeposit(
                enrolledAccount.accountId().get(), TransfersUtil.getAchDepositId(deposit), request);
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(1)
  @Test
  public void ach_transfers_transfers_create_ach_withdrawal_create_ach_withdrawal1()
      throws Exception {
    Assertions.assertNotNull(withdrawal);
  }

  @Order(2)
  @Test
  public void ach_transfers_transfers_get_ach_withdrawal_get_ach_withdrawal1() throws Exception {
    var result =
        sdk.achTransfers()
            .getAchWithdrawal(
                enrolledAccount.accountId().get(), TransfersUtil.getAchWithdrawalId(withdrawal));
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(3)
  @Test
  public void ach_transfers_transfers_cancel_ach_withdrawal_cancel_ach_withdrawal1()
      throws Exception {
    var request = CancelAchWithdrawalRequestCreate.builder().name(withdrawal.name().get()).build();
    var response =
        sdk.achTransfers()
            .cancelAchWithdrawal(
                enrolledAccount.accountId().get(),
                TransfersUtil.getAchWithdrawalId(withdrawal),
                request);
    Assertions.assertEquals(200, response.statusCode());
  }
}
