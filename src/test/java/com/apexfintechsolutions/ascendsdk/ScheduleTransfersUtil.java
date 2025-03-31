package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.components.RecipientBankBankIdCreateType;
import java.util.Calendar;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;

public class ScheduleTransfersUtil {
  public static AchDepositSchedule createScheduledAchDeposit(
      SDK sdk, Account enrolledAccount, BankRelationship br) throws Exception {
    var today = Calendar.getInstance();

    var request =
        AchDepositScheduleCreate.builder()
            .bankRelationship(br.name().get())
            .scheduleDetails(
                DepositScheduleDetailsCreate.builder()
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

    var result =
        sdk.scheduleTransfers()
            .createAchDepositSchedule(enrolledAccount.accountId().get(), request);
    Assertions.assertEquals(200, result.statusCode());

    Thread.sleep(5000);

    System.out.println("Created deposit schedule: " + result.achDepositSchedule().get().toString());

    return result.achDepositSchedule().get();
  }

  public static AchWithdrawalSchedule createScheduledAchWithdrawal(
      SDK sdk, Account enrolledAccount, BankRelationship br) throws Exception {
    var today = Calendar.getInstance();

    var request =
        AchWithdrawalScheduleCreate.builder()
            .bankRelationship(br.name().get())
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

    var result =
        sdk.scheduleTransfers()
            .createAchWithdrawalSchedule(enrolledAccount.accountId().get(), request);
    Assertions.assertEquals(200, result.statusCode());

    Thread.sleep(5000);

    System.out.println(
        "Created withdrawal schedule: " + result.achWithdrawalSchedule().get().toString());

    return result.achWithdrawalSchedule().get();
  }

  public static WireWithdrawalSchedule createWireWithdrawalSchedule(
      SDK sdk, Account enrolledAccount) throws Exception {
    var today = Calendar.getInstance();

    var request =
        WireWithdrawalScheduleCreate.builder()
            .beneficiary(
                WireWithdrawalBeneficiaryCreate.builder()
                    .account(enrolledAccount.accountId().get())
                    .build())
            .recipientBank(
                WireWithdrawalRecipientBankCreate.builder()
                    .bankId(
                        RecipientBankBankIdCreate.builder()
                            .id("ABNANL2AXXX")
                            .type(RecipientBankBankIdCreateType.ABA)
                            .build())
                    .build())
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

    var result =
        sdk.scheduleTransfers()
            .createWireWithdrawalSchedule(enrolledAccount.accountId().get(), request);
    Assertions.assertEquals(200, result.statusCode());

    Thread.sleep(5000);

    System.out.println(
        "Created wire withdrawal schedule: " + result.wireWithdrawalSchedule().get().toString());

    return result.wireWithdrawalSchedule().get();
  }

  public static String getScheduleId(AchWithdrawalSchedule schedule) {
    return schedule.name().get().split("/")[3];
  }

  public static String getScheduleId(AchDepositSchedule schedule) {
    return schedule.name().get().split("/")[3];
  }

  public static String getScheduleId(WireWithdrawalSchedule schedule) {
    return schedule.name().get().split("/")[3];
  }
}
