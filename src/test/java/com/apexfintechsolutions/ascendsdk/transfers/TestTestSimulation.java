package com.apexfintechsolutions.ascendsdk;

import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.components.Code;
import com.apexfintechsolutions.ascendsdk.models.components.TransfersCreditCreateType;
import com.apexfintechsolutions.ascendsdk.models.components.UpdatedBankAccountType;
import java.time.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestTestSimulation {
  private SDK sdk;
  private LegalNaturalPerson lnp;
  private Account account;
  private BankRelationship bankRelationship;
  private MicroDepositAmounts microdeposits;
  private boolean isEvening = true;
  private boolean isMarketHours = false;
  private boolean isJournalsHours = false;
  private String deceasedAccountId = TransfersUtil.getDeceasedAccountId();
  private boolean isWireHours = false;
  private String deceasedBankRelationshipId = "6786a8e8ea916b424a53cc24";
  private String retirementAccId = "01JMMMFW5B42JPFKEX2XJRQMPW";
  private String retirementBankRelationshipId = "67b8a7f26942931bee57f185";
  private String cashJournalId;

  @BeforeAll
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();
    lnp = AccountUtil.createLnp(sdk);
    account = AccountUtil.createEnrolledAccount(sdk);
    bankRelationship = TransfersUtil.createBankRelationship(sdk, account);
    Thread.sleep(5000);
    microdeposits = TransfersUtil.getCorrectMicrodepositAmounts(sdk, account, bankRelationship);
    Thread.sleep(5000);
    TransfersUtil.verifyMicroDeposits(sdk, account, bankRelationship, microdeposits);
    cashJournalId = TransfersUtil.createCashJournalId(sdk, account.accountId().get());

    ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.of("America/Chicago"));
    ZonedDateTime morning = currentTime.withHour(23).withMinute(30).withSecond(0).withNano(0);
    if (currentTime.isBefore(morning)) {
      morning = morning.minusDays(1);
    }
    ZonedDateTime afternoon = currentTime.withHour(18).withMinute(0).withSecond(0).withNano(0);
    if (currentTime.isAfter(morning) && currentTime.isBefore(afternoon)) {
      isEvening = false;
    }

    morning = currentTime.withHour(6).withMinute(0);
    afternoon = currentTime.withHour(15).withMinute(0);
    if (morning.isBefore(currentTime) && currentTime.isBefore(afternoon)) {
      isMarketHours = true;
    }

    morning = currentTime.withHour(7).withMinute(0);
    afternoon = currentTime.withHour(14).withMinute(30);
    if (morning.isBefore(currentTime) && currentTime.isBefore(afternoon)) {
      isWireHours = true;
    }

    morning = currentTime.withHour(6).withMinute(0);
    afternoon = currentTime.withHour(19).withMinute(0);
    if (morning.isBefore(currentTime) && currentTime.isBefore(afternoon)) {
      isJournalsHours = true;
    }
  }

  @Test
  @Order(1)
  public void test_test_simulation_transfers_get_micro_deposit_get_micro_deposit1()
      throws Exception {
    Assertions.assertNotNull(microdeposits);
  }

  //  @Test
  //  @Order(2)
  //  public void
  // test_test_simulation_transfers_force_approve_ach_deposit_force_approve_ach_deposit1() throws
  // Exception {
  //    assumeFalse(isEvening, "Skipping Endpoint Test: Force Approve ACH Deposit. Requires current
  // time to be between 11:30 PM CT and 6:00 PM CT");
  //    var pendingDeposit = TransfersUtil.createAchDeposit(sdk, AccountUtil.getAccount(sdk,
  // deceasedAccountId), TransfersUtil.getBankRelationship(sdk, deceasedAccountId,
  // deceasedBankRelationshipId));
  //    var pendingDepositId = TransfersUtil.getAchDepositId(pendingDeposit);
  //    Thread.sleep(5000);
  //    var req = new ForceApproveAchDepositRequestCreate("accounts/" + deceasedAccountId +
  // "/achDeposits/" + pendingDepositId);
  //    var res = sdk.testSimulation().forceApproveAchDeposit()
  //      .accountId(deceasedAccountId)
  //      .achDepositId(pendingDepositId)
  //      .forceApproveAchDepositRequestCreate(req)
  //      .call();
  //    Assertions.assertNotNull(res);
  //    Assertions.assertEquals(200, res.statusCode());
  //  }

  @Test
  @Order(3)
  public void test_test_simulation_transfers_force_noc_ach_deposit_force_noc_ach_deposit1()
      throws Exception {
    assumeFalse(
        isEvening,
        "Skipping Endpoint Test: Force NOC ACH Deposit. Requires current time to be between 11:30"
            + " PM CT and 6:00 PM CT");
    var achDeposit = TransfersUtil.createAchDeposit(sdk, account, bankRelationship);
    Thread.sleep(5000);
    var req =
        ForceNocAchDepositRequestCreate.builder()
            .nachaNoc(
                NachaNocCreate.builder()
                    .code(Code.C05)
                    .updatedBankAccountType(UpdatedBankAccountType.CHECKING)
                    .build())
            .name(
                "accounts/"
                    + account.accountId().get()
                    + "/achDeposits/"
                    + TransfersUtil.getAchDepositId(achDeposit))
            .build();

    var res =
        sdk.testSimulation()
            .forceNocAchDeposit()
            .accountId(account.accountId().get())
            .achDepositId(TransfersUtil.getAchDepositId(achDeposit))
            .forceNocAchDepositRequestCreate(req)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }

  @Test
  @Order(4)
  public void test_test_simulation_transfers_force_reject_ach_deposit_force_reject_ach_deposit1()
      throws Exception {
    assumeFalse(
        isEvening,
        "Skipping Endpoint Test: Force Reject ACH Deposit. Requires current time to be between"
            + " 11:30 PM CT and 6:00 PM CT");

    var pendingDeposit =
        TransfersUtil.createAchDeposit(
            sdk,
            AccountUtil.getAccount(sdk, deceasedAccountId),
            TransfersUtil.getBankRelationship(sdk, deceasedAccountId, deceasedBankRelationshipId));
    var pendingDepositId = TransfersUtil.getAchDepositId(pendingDeposit);
    Thread.sleep(5000);

    var req =
        new ForceRejectAchDepositRequestCreate(
            "accounts/" + deceasedAccountId + "/achDeposits/" + pendingDepositId);
    var res =
        sdk.testSimulation()
            .forceRejectAchDeposit()
            .accountId(deceasedAccountId)
            .achDepositId(pendingDepositId)
            .forceRejectAchDepositRequestCreate(req)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }

  @Test
  @Order(5)
  public void test_test_simulation_transfers_force_ach_deposit_return_force_ach_deposit_return1()
      throws Exception {
    assumeFalse(
        isEvening,
        "Skipping Endpoint Test: Force Return ACH Deposit. Requires current time to be between"
            + " 11:30 PM CT and 6:00 PM CT");

    var achDeposit = TransfersUtil.createAchDeposit(sdk, account, bankRelationship);
    Thread.sleep(5000);
    var req =
        ForceReturnAchDepositRequestCreate.builder()
            .nachaReturn(new NachaReturnCreate(NachaReturnCreateCode.R16))
            .name(
                "accounts/"
                    + account.accountId().get()
                    + "/achDeposits/"
                    + TransfersUtil.getAchDepositId(achDeposit))
            .build();
    var res =
        sdk.testSimulation()
            .forceReturnAchDeposit()
            .accountId(account.accountId().get())
            .achDepositId(TransfersUtil.getAchDepositId(achDeposit))
            .forceReturnAchDepositRequestCreate(req)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }

  //  @Test
  //  @Order(6)
  //  public void
  // test_test_simulation_transfers_force_approve_ach_withdrawal_force_approve_ach_withdrawal1()
  // throws Exception {
  //    assumeFalse(
  //      isEvening,
  //      "Skipping Endpoint Test: Force Approve ACH Withdrawal. Requires current time to be between
  // 11:30 PM CT"
  //        + " and 6:00 PM CT");
  //    var pendingWithdrawal = TransfersUtil.createAchWithdrawalRetirement(sdk,
  // retirementAccId, retirementBankRelationshipId);
  //    var pendingWithdrawalId = TransfersUtil.getAchWithdrawalId(pendingWithdrawal);
  //    var req = new ForceApproveAchWithdrawalRequestCreate("accounts/" + retirementAccId +
  // "/achWithdrawals/" + pendingWithdrawalId);
  //    var res = sdk.testSimulation().forceApproveAchWithdrawal()
  //      .accountId(retirementAccId)
  //      .achWithdrawalId(pendingWithdrawalId)
  //      .forceApproveAchWithdrawalRequestCreate(req)
  //      .call();
  //    Assertions.assertNotNull(res);
  //    Assertions.assertEquals(200, res.statusCode());
  //  }

  @Test
  @Order(7)
  public void test_test_simulation_transfers_force_noc_ach_withdrawal_force_noc_ach_withdrawal1()
      throws Exception {
    assumeFalse(
        isEvening,
        "Skipping Endpoint Test: Force NOC ACH Withdrawal. Requires current time to be between"
            + " 11:30 PM CT and 6:00 PM CT");

    var completedWithdrawalId =
        TransfersUtil.createCompletedWithdrawal(sdk, TransfersUtil.getWithdrawalAccountId());
    var req =
        ForceNocAchWithdrawalRequestCreate.builder()
            .nachaNoc(
                NachaNocCreate.builder()
                    .code(Code.C05)
                    .updatedBankAccountType(UpdatedBankAccountType.CHECKING)
                    .build())
            .name(
                "accounts/"
                    + TransfersUtil.getWithdrawalAccountId()
                    + "/achWithdrawals/"
                    + completedWithdrawalId)
            .build();
    var res =
        sdk.testSimulation()
            .forceNocAchWithdrawal()
            .forceNocAchWithdrawalRequestCreate(req)
            .achWithdrawalId(completedWithdrawalId)
            .accountId(TransfersUtil.getWithdrawalAccountId())
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }

  @Test
  @Order(8)
  public void
      test_test_simulation_transfers_force_reject_ach_withdrawal_force_reject_ach_withdrawal1()
          throws Exception {
    assumeFalse(
        isEvening,
        "Skipping Endpoint Test: Force Reject ACH Withdrawal. Requires current time to be between"
            + " 11:30 PM CT and 6:00 PM CT");

    var pendingWithdrawal =
        TransfersUtil.createAchWithdrawal(
            sdk,
            AccountUtil.getAccount(sdk, deceasedAccountId),
            TransfersUtil.getBankRelationship(sdk, deceasedAccountId, deceasedBankRelationshipId));
    var pendingWithdrawalId = TransfersUtil.getAchWithdrawalId(pendingWithdrawal);
    Thread.sleep(5000);
    var req =
        new ForceRejectAchWithdrawalRequestCreate(
            "accounts/" + deceasedAccountId + "/achWithdrawals/" + pendingWithdrawalId);
    var res =
        sdk.testSimulation()
            .forceRejectAchWithdrawal()
            .accountId(deceasedAccountId)
            .achWithdrawalId(pendingWithdrawalId)
            .forceRejectAchWithdrawalRequestCreate(req)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }

  @Test
  @Order(9)
  public void
      test_test_simulation_transfers_force_ach_withdrawal_return_force_ach_withdrawal_return1()
          throws Exception {
    assumeFalse(
        isEvening,
        "Skipping Endpoint Test: Force Return ACH Withdrawal. Requires current time to be between"
            + " 11:30 PM CT and 6:00 PM CT");

    var completedWithdrawalId =
        TransfersUtil.createCompletedWithdrawal(sdk, TransfersUtil.getWithdrawalAccountId());
    var req =
        ForceReturnAchWithdrawalRequestCreate.builder()
            .nachaReturn(new NachaReturnCreate(NachaReturnCreateCode.R16))
            .name(
                "accounts/"
                    + TransfersUtil.getWithdrawalAccountId()
                    + "/achWithdrawals/"
                    + completedWithdrawalId)
            .build();
    var res =
        sdk.testSimulation()
            .forceReturnAchWithdrawal()
            .forceReturnAchWithdrawalRequestCreate(req)
            .achWithdrawalId(completedWithdrawalId)
            .accountId(TransfersUtil.getWithdrawalAccountId())
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }

  //    @Test
  //    @Order(10)
  //    public void
  // test_test_simulation_transfers_force_ict_deposit_approve_force_ict_deposit_approve1() throws
  // Exception {
  //      assumeTrue(isMarketHours, "Skipping Endpoint Test: Force ICT Deposit Approve. Requires
  // current time to be between 6:00 AM CT and 3:00 PM CT");
  //      var ictDepositId = TransfersUtil.createIctDepositId(sdk, deceasedAccountId);
  //      Thread.sleep(10000);
  //      var req = new ForceApproveIctDepositRequestCreate("accounts/" + deceasedAccountId +
  // "/ictDeposits/" + ictDepositId);
  //      var res = sdk.testSimulation().forceApproveIctDeposit()
  //        .accountId(deceasedAccountId)
  //        .ictDepositId(ictDepositId)
  //        .forceApproveIctDepositRequestCreate(req)
  //        .call();
  //      Assertions.assertNotNull(res);
  //      Assertions.assertEquals(200, res.statusCode());
  //    }

  @Test
  @Order(11)
  public void test_test_simulation_transfers_force_ict_deposit_reject_force_ict_deposit_reject1()
      throws Exception {
    assumeTrue(
        isMarketHours,
        "Skipping Endpoint Test: Force ICT Deposit Reject. Requires current time to be between 6:00"
            + " AM CT and 3:00 PM CT");
    var pendingDepositId = TransfersUtil.createIctDepositId(sdk, deceasedAccountId);
    Thread.sleep(10000);

    var req =
        new ForceRejectIctDepositRequestCreate(
            "accounts/" + deceasedAccountId + "/ictDeposits/" + pendingDepositId);
    var res =
        sdk.testSimulation()
            .forceRejectIctDeposit()
            .accountId(deceasedAccountId)
            .ictDepositId(pendingDepositId)
            .forceRejectIctDepositRequestCreate(req)
            .call();

    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }

  //    @Test
  //    @Order(12)
  //    public void
  // test_test_simulation_transfers_force_ict_withdrawal_approve_force_ict_withdrawal_approve1()
  // throws Exception {
  //      assumeTrue(isMarketHours, "Skipping Endpoint Test: Force ICT Withdrawal Approve. Requires
  // current time to be between 6:00 AM CT and 3:00 PM CT");
  //      var ictWithdrawalId = TransfersUtil.createIctWithdrawalId(sdk, retirementAccId);
  //
  //      Thread.sleep(10000);
  //      var req = new ForceApproveIctWithdrawalRequestCreate("accounts/" + retirementAccId +
  // "/ictWithdrawals/" + ictWithdrawalId);
  //      var res = sdk.testSimulation().forceApproveIctWithdrawal()
  //        .accountId(retirementAccId)
  //        .ictWithdrawalId(ictWithdrawalId)
  //        .forceApproveIctWithdrawalRequestCreate(req)
  //        .call();
  //      Assertions.assertNotNull(res);
  //      Assertions.assertEquals(200, res.statusCode());
  //    }

  @Test
  @Order(13)
  public void
      test_test_simulation_transfers_force_ict_withdrawal_reject_force_ict_withdrawal_reject1()
          throws Exception {
    assumeTrue(
        isMarketHours,
        "Skipping Endpoint Test: Force ICT Withdrawal Reject. Requires current time to be between"
            + " 6:00 AM CT and 3:00 PM CT");
    var pendingWithdrawalId = TransfersUtil.createIctWithdrawalId(sdk, deceasedAccountId);
    Thread.sleep(10000);

    var req =
        new ForceRejectIctWithdrawalRequestCreate(
            "accounts/" + deceasedAccountId + "/ictWithdrawals/" + pendingWithdrawalId);
    var res =
        sdk.testSimulation()
            .forceRejectIctWithdrawal()
            .accountId(deceasedAccountId)
            .ictWithdrawalId(pendingWithdrawalId)
            .forceRejectIctWithdrawalRequestCreate(req)
            .call();

    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }

  @Order(1)
  @Test
  public void
      test_test_simulation_transfers_force_cash_journal_approve_force_cash_journal_approve1()
          throws Exception {
    assumeTrue(
        isJournalsHours,
        "Skipping Endpoint Test: Force Cash Journal Approve. Requires current time to be between"
            + " 6:00 AM CT and 7:00 PM CT");
    var creditReq =
        TransfersCreditCreate.builder()
            .amount(DecimalCreate.builder().value("500000.00").build())
            .clientTransferId(java.util.UUID.randomUUID().toString())
            .description("Credit")
            .type(TransfersCreditCreateType.PROMOTIONAL)
            .build();
    sdk.feesAndCredits()
        .createCredit()
        .accountId(account.accountId().get())
        .transfersCreditCreate(creditReq)
        .call();

    Thread.sleep(5000);
    var req =
        ForceApproveCashJournalRequestCreate.builder()
            .name("cashJournals/" + cashJournalId)
            .build();
    var res =
        sdk.testSimulation()
            .forceApproveCashJournal()
            .forceApproveCashJournalRequestCreate(req)
            .cashJournalId(cashJournalId)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }

  @Order(2)
  @Test
  public void test_test_simulation_transfers_force_cash_journal_reject_force_cash_journal_reject1()
      throws Exception {
    assumeTrue(
        isJournalsHours,
        "Skipping Endpoint Test: Force Cash Journal Reject. Requires current time to be between"
            + " 6:00 AM CT and 7:00 PM CT");
    var req =
        ForceRejectCashJournalRequestCreate.builder().name("cashJournals/" + cashJournalId).build();
    var res =
        sdk.testSimulation()
            .forceRejectCashJournal()
            .forceRejectCashJournalRequestCreate(req)
            .cashJournalId(cashJournalId)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }

  @Test
  @Order(14)
  public void
      test_test_simulation_transfers_force_approve_wire_withdrawal_force_approve_wire_withdrawal1()
          throws Exception {
    assumeTrue(
        isWireHours,
        "Skipping Endpoint Test: Force Approve Wire Withdrawal. Requires current time to be between"
            + " 7:00 AM CT and 2:30 PM CT");

    String wireWithdrawalId =
        TransfersUtil.createWireWithdrawalId(sdk, TransfersUtil.getWithdrawalAccountId());

    Thread.sleep(5000);

    var request =
        ForceApproveWireWithdrawalRequestCreate.builder()
            .name(
                String.format(
                    "accounts/%s/wireWithdrawals/%s",
                    TransfersUtil.getWithdrawalAccountId(), wireWithdrawalId))
            .build();

    var result =
        sdk.testSimulation()
            .forceApproveWireWithdrawal(
                TransfersUtil.getWithdrawalAccountId(), wireWithdrawalId, request);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(200, result.statusCode());
  }

  @Test
  @Order(15)
  public void
      test_test_simulation_transfers_force_wire_withdrawal_reject_force_wire_withdrawal_reject1()
          throws Exception {
    assumeTrue(
        isWireHours,
        "Skipping Endpoint Test: Force Reject Wire Withdrawal. Requires current time to be between"
            + " 7:00 AM CT and 2:30 PM CT");

    String wireWithdrawalId =
        TransfersUtil.createWireWithdrawalId(sdk, TransfersUtil.getWithdrawalAccountId());
    var request =
        ForceRejectWireWithdrawalRequestCreate.builder()
            .name(
                String.format(
                    "accounts/%s/wireWithdrawals/%s",
                    TransfersUtil.getWithdrawalAccountId(), wireWithdrawalId))
            .build();

    var result =
        sdk.testSimulation()
            .forceRejectWireWithdrawal(
                TransfersUtil.getWithdrawalAccountId(), wireWithdrawalId, request);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(200, result.statusCode());
  }
}
