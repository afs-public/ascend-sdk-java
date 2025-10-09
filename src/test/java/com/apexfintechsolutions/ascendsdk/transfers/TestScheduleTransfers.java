package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import java.util.Optional;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestScheduleTransfers {
  private SDK sdk;
  private BankRelationship bankRelationship;
  private Account enrolledAccount;
  private AchDepositSchedule depositSchedule;
  private AchWithdrawalSchedule withdrawalSchedule;
  private WireWithdrawalSchedule wireWithdrawalSchedule;

  @BeforeAll
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();
    enrolledAccount = AccountUtil.createEnrolledAccount(sdk);
    bankRelationship = TransfersUtil.createVerifiedBankRelationship(sdk, enrolledAccount);
    depositSchedule =
        ScheduleTransfersUtil.createScheduledAchDeposit(sdk, enrolledAccount, bankRelationship);
    withdrawalSchedule =
        ScheduleTransfersUtil.createScheduledAchWithdrawal(sdk, enrolledAccount, bankRelationship);
    wireWithdrawalSchedule =
        ScheduleTransfersUtil.createWireWithdrawalSchedule(sdk, enrolledAccount);
  }

  @Order(1)
  @Test
  public void
      test_schedule_transfers_transfers_create_ach_deposit_schedule_create_ach_deposit_schedule1()
          throws Exception {
    Assertions.assertNotNull(depositSchedule);
    Assertions.assertTrue(depositSchedule.name().isPresent());
  }

  @Order(2)
  @Test
  public void
      test_schedule_transfers_transfers_list_ach_deposit_schedules_list_ach_deposit_schedules1()
          throws Exception {
    var result = sdk.scheduleTransfers().listAchDepositSchedules(enrolledAccount.accountId().get());
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(3)
  @Test
  public void test_schedule_transfers_transfers_get_ach_deposit_schedule_get_ach_deposit_schedule1()
      throws Exception {
    var result =
        sdk.scheduleTransfers()
            .getAchDepositSchedule(
                enrolledAccount.accountId().get(),
                ScheduleTransfersUtil.getScheduleId(depositSchedule));
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(4)
  @Test
  public void
      test_schedule_transfers_transfers_update_ach_deposit_schedule_update_ach_deposit_schedule1()
          throws Exception {
    var request =
        AchDepositScheduleUpdate.builder()
            .scheduleDetails(
                DepositScheduleDetailsUpdate.builder()
                    .amount(DecimalUpdate.builder().value("200.00").build())
                    .build())
            .build();

    var result =
        sdk.scheduleTransfers()
            .updateAchDepositSchedule()
            .accountId(enrolledAccount.accountId().get())
            .achDepositScheduleId(ScheduleTransfersUtil.getScheduleId(depositSchedule))
            .updateMask(Optional.of("schedule_details.amount"))
            .achDepositScheduleUpdate(request)
            .call();
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(5)
  @Test
  public void
      test_schedule_transfers_transfers_cancel_ach_deposit_schedule_cancel_ach_deposit_schedule1()
          throws Exception {
    var request =
        CancelAchDepositScheduleRequestCreate.builder().name(depositSchedule.name().get()).build();
    var result =
        sdk.scheduleTransfers()
            .cancelAchDepositSchedule(
                enrolledAccount.accountId().get(),
                ScheduleTransfersUtil.getScheduleId(depositSchedule),
                request);
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(6)
  @Test
  public void
      test_schedule_transfers_transfers_create_ach_withdrawal_schedule_create_ach_withdrawal_withdrawal1()
          throws Exception {
    Assertions.assertNotNull(withdrawalSchedule);
    Assertions.assertTrue(withdrawalSchedule.name().isPresent());
  }

  @Order(7)
  @Test
  public void
      test_schedule_transfers_transfers_list_ach_withdrawal_schedules_list_ach_withdrawal_schedules1()
          throws Exception {
    var result =
        sdk.scheduleTransfers().listAchWithdrawalSchedules(enrolledAccount.accountId().get());
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(8)
  @Test
  public void
      test_schedule_transfers_transfers_get_ach_withdrawal_schedule_get_ach_withdrawal_schedule1()
          throws Exception {
    var result =
        sdk.scheduleTransfers()
            .getAchWithdrawalSchedule(
                enrolledAccount.accountId().get(),
                ScheduleTransfersUtil.getScheduleId(withdrawalSchedule));
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(9)
  @Test
  public void
      test_schedule_transfers_transfers_update_ach_withdrawal_schedule_update_ach_withdrawal_schedule1()
          throws Exception {
    var request =
        AchWithdrawalScheduleUpdate.builder()
            .scheduleDetails(
                WithdrawalScheduleDetailsUpdate.builder()
                    .amount(DecimalUpdate.builder().value("50.00").build())
                    .build())
            .build();

    var result =
        sdk.scheduleTransfers()
            .updateAchWithdrawalSchedule()
            .accountId(enrolledAccount.accountId().get())
            .achWithdrawalScheduleId(ScheduleTransfersUtil.getScheduleId(withdrawalSchedule))
            .updateMask(Optional.of("schedule_details.amount"))
            .achWithdrawalScheduleUpdate(request)
            .call();
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(10)
  @Test
  public void
      test_schedule_transfers_transfers_cancel_ach_withdrawal_schedule_cancel_ach_withdrawal_schedule1()
          throws Exception {
    var request =
        CancelAchWithdrawalScheduleRequestCreate.builder()
            .name(withdrawalSchedule.name().get())
            .build();
    var result =
        sdk.scheduleTransfers()
            .cancelAchWithdrawalSchedule(
                enrolledAccount.accountId().get(),
                ScheduleTransfersUtil.getScheduleId(withdrawalSchedule),
                request);

    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(1)
  @Test
  public void
      test_schedule_transfers_transfers_create_wire_withdrawal_schedule_create_wire_withdrawal_withdrawal1()
          throws Exception {
    Assertions.assertNotNull(wireWithdrawalSchedule);
  }

  @Order(2)
  @Test
  public void
      test_schedule_transfers_transfers_list_wire_withdrawal_schedules_list_wire_withdrawal_schedules1()
          throws Exception {
    var result =
        sdk.scheduleTransfers().listWireWithdrawalSchedules(enrolledAccount.accountId().get());
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(3)
  @Test
  public void
      test_schedule_transfers_transfers_get_wire_withdrawal_schedule_get_wire_withdrawal_schedule1()
          throws Exception {
    var result =
        sdk.scheduleTransfers()
            .getWireWithdrawalSchedule(
                enrolledAccount.accountId().get(),
                ScheduleTransfersUtil.getScheduleId(wireWithdrawalSchedule));
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(4)
  @Test
  public void
      test_schedule_transfers_transfers_update_wire_withdrawal_schedule_update_wire_withdrawal_schedule1()
          throws Exception {
    var request =
        WireWithdrawalScheduleUpdate.builder()
            .scheduleDetails(
                WithdrawalScheduleDetailsUpdate.builder()
                    .amount(DecimalUpdate.builder().value("50.00").build())
                    .build())
            .build();

    var result =
        sdk.scheduleTransfers()
            .updateWireWithdrawalSchedule()
            .accountId(enrolledAccount.accountId().get())
            .wireWithdrawalScheduleId(ScheduleTransfersUtil.getScheduleId(wireWithdrawalSchedule))
            .updateMask(Optional.of("schedule_details.amount"))
            .wireWithdrawalScheduleUpdate(request)
            .call();
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(5)
  @Test
  public void
      test_schedule_transfers_transfers_cancel_wire_withdrawal_schedule_cancel_wire_withdrawal_schedule1()
          throws Exception {
    var request =
        CancelWireWithdrawalScheduleRequestCreate.builder()
            .name(wireWithdrawalSchedule.name().get())
            .build();
    var result =
        sdk.scheduleTransfers()
            .cancelWireWithdrawalSchedule(
                enrolledAccount.accountId().get(),
                ScheduleTransfersUtil.getScheduleId(wireWithdrawalSchedule),
                request);

    Assertions.assertEquals(200, result.statusCode());
  }

  @Test
  public void test_schedule_transfers_transfers_list_schedule_summaries_list_schedule_summaries1()
      throws Exception {
    var res = sdk.scheduleTransfers().listScheduleSummaries().call();
    Assertions.assertEquals(200, res.statusCode());
  }
}
