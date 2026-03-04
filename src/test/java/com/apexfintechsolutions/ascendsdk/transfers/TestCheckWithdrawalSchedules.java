package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestCheckWithdrawalSchedules {
  private SDK sdk;
  private Account enrolledAccount;
  private CheckWithdrawalSchedule checkWithdrawalSchedule;
  private String checkWithdrawalScheduleId;

  @BeforeAll
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();
    enrolledAccount = AccountUtil.createEnrolledAccount(sdk);
  }

  @Order(1)
  @Test
  public void test_check_withdrawal_schedules_create_check_withdrawal_schedule() throws Exception {
    var today = Calendar.getInstance();

    var request =
        CheckWithdrawalScheduleCreate.builder()
            .deliveryMethod(DeliveryMethod.of("STANDARD"))
            .memos(List.of("Test memo"))
            .scheduleDetails(
                WithdrawalScheduleDetailsCreate.builder()
                    .amount(DecimalCreate.builder().value("100").build())
                    .clientScheduleId(UUID.randomUUID().toString())
                    .scheduleProperties(
                        SchedulePropertiesCreate.builder()
                            .startDate(
                                DateCreate.builder()
                                    .day(today.get(Calendar.DAY_OF_MONTH))
                                    .month(today.get(Calendar.MONTH) + 1)
                                    .year(today.get(Calendar.YEAR))
                                    .build())
                            .timeUnit(TimeUnit.MONTH)
                            .unitMultiplier(1)
                            .occurrences(12)
                            .build())
                    .build())
            .build();

    var res =
        sdk.scheduleTransfers()
            .createCheckWithdrawalSchedule(enrolledAccount.accountId().get(), request);

    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
    Assertions.assertTrue(res.checkWithdrawalSchedule().isPresent());

    checkWithdrawalSchedule = res.checkWithdrawalSchedule().get();
    Assertions.assertTrue(checkWithdrawalSchedule.name().isPresent());
    checkWithdrawalScheduleId = checkWithdrawalSchedule.name().get().split("/")[3];
  }

  @Order(2)
  @Test
  public void test_check_withdrawal_schedules_get_check_withdrawal_schedule() throws Exception {
    var res =
        sdk.scheduleTransfers()
            .getCheckWithdrawalSchedule(
                enrolledAccount.accountId().get(), checkWithdrawalScheduleId);

    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
    Assertions.assertTrue(res.checkWithdrawalSchedule().isPresent());
  }

  @Order(3)
  @Test
  public void test_check_withdrawal_schedules_list_check_withdrawal_schedules() throws Exception {
    var res =
        sdk.scheduleTransfers().listCheckWithdrawalSchedules(enrolledAccount.accountId().get());

    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
    Assertions.assertTrue(res.listCheckWithdrawalSchedulesResponse().isPresent());
  }

  @Order(4)
  @Test
  public void test_check_withdrawal_schedules_update_check_withdrawal_schedule() throws Exception {
    var request =
        CheckWithdrawalScheduleUpdate.builder()
            .scheduleDetails(
                WithdrawalScheduleDetailsUpdate.builder()
                    .amount(DecimalUpdate.builder().value("200.00").build())
                    .build())
            .build();

    var res =
        sdk.scheduleTransfers()
            .updateCheckWithdrawalSchedule()
            .accountId(enrolledAccount.accountId().get())
            .checkWithdrawalScheduleId(checkWithdrawalScheduleId)
            .updateMask(Optional.of("schedule_details.amount"))
            .checkWithdrawalScheduleUpdate(request)
            .call();

    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
    Assertions.assertTrue(res.checkWithdrawalSchedule().isPresent());
  }

  @Order(5)
  @Test
  public void test_check_withdrawal_schedules_cancel_check_withdrawal_schedule() throws Exception {
    var request =
        CancelCheckWithdrawalScheduleRequestCreate.builder()
            .name(checkWithdrawalSchedule.name().get())
            .comment("Test cancel check withdrawal schedule")
            .build();

    var res =
        sdk.scheduleTransfers()
            .cancelCheckWithdrawalSchedule(
                enrolledAccount.accountId().get(), checkWithdrawalScheduleId, request);

    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
    Assertions.assertTrue(res.checkWithdrawalSchedule().isPresent());
  }
}
