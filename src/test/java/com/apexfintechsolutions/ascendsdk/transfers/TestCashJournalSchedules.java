package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.operations.*;
import java.util.Calendar;
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
public class TestCashJournalSchedules {
  private SDK sdk;
  private String sourceAccountId;
  private String destinationAccountId;
  private CashJournalSchedule cashJournalSchedule;
  private String cashJournalScheduleId;

  @BeforeAll
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();
    sourceAccountId = TransfersUtil.getWithdrawalAccountId();
    destinationAccountId = TransfersUtil.getDeceasedAccountId();
  }

  @Order(1)
  @Test
  public void test_cash_journal_schedules_create_cash_journal_schedule() throws Exception {
    var today = Calendar.getInstance();

    var request =
        CashJournalScheduleCreate.builder()
            .sourceAccount(sourceAccountId)
            .destinationAccount(destinationAccountId)
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

    var res = sdk.scheduleTransfers().createCashJournalSchedule(request);

    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
    Assertions.assertTrue(res.cashJournalSchedule().isPresent());

    cashJournalSchedule = res.cashJournalSchedule().get();
    Assertions.assertTrue(cashJournalSchedule.name().isPresent());
    cashJournalScheduleId = cashJournalSchedule.name().get().split("/")[1];
  }

  @Order(2)
  @Test
  public void test_cash_journal_schedules_get_cash_journal_schedule() throws Exception {
    var res = sdk.scheduleTransfers().getCashJournalSchedule(cashJournalScheduleId);

    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
    Assertions.assertTrue(res.cashJournalSchedule().isPresent());
  }

  @Order(3)
  @Test
  public void test_cash_journal_schedules_search_cash_journal_schedules() throws Exception {
    var request =
        CashJournalSchedulesSearchCashJournalSchedulesRequest.builder()
            .sourceAccount(sourceAccountId)
            .build();

    var res = sdk.scheduleTransfers().searchCashJournalSchedules(request);

    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
    Assertions.assertTrue(res.searchCashJournalSchedulesResponse().isPresent());
  }

  @Order(4)
  @Test
  public void test_cash_journal_schedules_update_cash_journal_schedule() throws Exception {
    var request =
        CashJournalScheduleUpdate.builder()
            .scheduleDetails(
                WithdrawalScheduleDetailsUpdate.builder()
                    .amount(DecimalUpdate.builder().value("200.00").build())
                    .build())
            .build();

    var res =
        sdk.scheduleTransfers()
            .updateCashJournalSchedule()
            .cashJournalScheduleId(cashJournalScheduleId)
            .updateMask(Optional.of("schedule_details.amount"))
            .cashJournalScheduleUpdate(request)
            .call();

    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
    Assertions.assertTrue(res.cashJournalSchedule().isPresent());
  }

  @Order(5)
  @Test
  public void test_cash_journal_schedules_cancel_cash_journal_schedule() throws Exception {
    var request =
        CancelCashJournalScheduleRequestCreate.builder()
            .name(cashJournalSchedule.name().get())
            .comment("Test cancel cash journal schedule")
            .build();

    var res = sdk.scheduleTransfers().cancelCashJournalSchedule(cashJournalScheduleId, request);

    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
    Assertions.assertTrue(res.cashJournalSchedule().isPresent());
  }
}
