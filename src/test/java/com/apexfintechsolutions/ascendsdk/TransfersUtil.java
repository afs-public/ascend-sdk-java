package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.components.BankRelationshipStateState;
import com.apexfintechsolutions.ascendsdk.models.components.RetirementDistributionCreateType;
import java.util.*;

public class TransfersUtil {
  public static BankRelationship createVerifiedBankRelationship(SDK sdk, Account enrolledAccount)
      throws Exception {
    var bankRelationship = createBankRelationship(sdk, enrolledAccount);
    Thread.sleep(5000);
    var amounts = getCorrectMicrodepositAmounts(sdk, enrolledAccount, bankRelationship);
    Thread.sleep(5000);
    var verifiedBankRelationship =
        verifyBankRelationship(sdk, enrolledAccount, bankRelationship, amounts);
    Thread.sleep(5000);
    return verifiedBankRelationship;
  }

  private static MicroDepositAmountsCreate adjustMicroDepositAmount(MicroDepositAmounts amounts) {
    String amt1 = amounts.amount1().get().value().get();
    String amt2 = amounts.amount2().get().value().get();

    String adjustedAmt1 = "" + (Double.parseDouble(amt1) + 0.01);
    String adjustedAmt2 = "" + (Double.parseDouble(amt2) + 0.01);

    return MicroDepositAmountsCreate.builder()
        .amount1(DecimalCreate.builder().value(adjustedAmt1).build())
        .amount2(DecimalCreate.builder().value(adjustedAmt2).build())
        .build();
  }

  public static MicroDepositAmountsCreate getFailingMicrodepositAmounts(
      SDK sdk, Account account, BankRelationship relationship) throws Exception {
    var response =
        sdk.testSimulation()
            .getMicroDepositAmounts(account.accountId().get(), getBankRelationshipId(relationship));
    if (response.statusCode() == 200) {
      var amounts = response.microDepositAmounts().get();
      return adjustMicroDepositAmount(amounts);
    } else {
      throw new Exception("Failed to get micro deposit amounts: " + response.statusCode());
    }
  }

  public static AchDeposit createAchDeposit(SDK sdk, Account account, BankRelationship relationship)
      throws Exception {
    var request =
        AchDepositCreate.builder()
            .bankRelationship(relationship.name().get())
            .amount(DecimalCreate.builder().value("100").build())
            .clientTransferId(UUID.randomUUID().toString())
            .build();

    var response = sdk.achTransfers().createAchDeposit(account.accountId().get(), request);
    if (response.statusCode() == 200) {
      return response.achDeposit().get();
    } else {
      throw new Exception("Failed to create ACH deposit: " + response.statusCode());
    }
  }

  public static AchWithdrawal createAchWithdrawal(
      SDK sdk, Account account, BankRelationship relationship) throws Exception {
    var request =
        AchWithdrawalCreate.builder()
            .bankRelationship(relationship.name().get())
            .amount(DecimalCreate.builder().value("0.01").build())
            .clientTransferId(UUID.randomUUID().toString())
            .fullDisbursement(false)
            .memo("ACH")
            .build();

    var response = sdk.achTransfers().createAchWithdrawal(account.accountId().get(), request);
    if (response.statusCode() == 200) {
      return response.achWithdrawal().get();
    } else {
      throw new Exception("Failed to create ACH withdrawal: " + response.statusCode());
    }
  }

  public static BankRelationship createBankRelationship(SDK sdk, Account enrolledAccount)
      throws Exception {
    var randomBankAccountNum = "" + new Random().nextInt(1000000) + 1000000;
    var request =
        BankRelationshipCreate.builder()
            .bankAccount(
                BankAccountCreate.builder()
                    .accountNumber(randomBankAccountNum)
                    .routingNumber("112203216")
                    .owner("TEST123")
                    .type(BankAccountCreateType.SAVINGS)
                    .build())
            .nickname("ACH TEST")
            .verificationMethod(VerificationMethod.MICRO_DEPOSIT)
            .build();

    var response =
        sdk.bankRelationships().createBankRelationship(enrolledAccount.accountId().get(), request);
    if (response.statusCode() == 200) {
      return response.bankRelationship().get();
    } else {
      throw new Exception("Failed to create bank relationship: " + response.statusCode());
    }
  }

  public static String getBankRelationshipId(BankRelationship relationship) {
    return relationship.name().get().split("/")[3];
  }

  public static MicroDepositAmounts getCorrectMicrodepositAmounts(
      SDK sdk, Account enrolledAccount, BankRelationship br) throws Exception {
    var response =
        sdk.testSimulation()
            .getMicroDepositAmounts(enrolledAccount.accountId().get(), getBankRelationshipId(br));
    if (response.statusCode() == 200) {
      return response.microDepositAmounts().get();
    } else {
      throw new Exception("Failed to get micro deposit amounts: " + response.statusCode());
    }
  }

  public static BankRelationship verifyBankRelationship(
      SDK sdk, Account enrolledAccount, BankRelationship relationship, MicroDepositAmounts amounts)
      throws Exception {
    var request =
        VerifyMicroDepositsRequestCreate.builder()
            .amounts(
                MicroDepositAmountsCreate.builder()
                    .amount1(
                        DecimalCreate.builder()
                            .value(amounts.amount1().get().value().get())
                            .build())
                    .amount2(
                        DecimalCreate.builder()
                            .value(amounts.amount2().get().value().get())
                            .build())
                    .build())
            .name(relationship.name().get())
            .build();

    var response =
        sdk.bankRelationships()
            .verifyMicroDeposits(
                enrolledAccount.accountId().get(), getBankRelationshipId(relationship), request);
    if (response.statusCode() == 200) {
      return response.bankRelationship().get();
    } else {
      throw new Exception("Failed to verify bank relationship: " + response.statusCode());
    }
  }

  public static String getAchDepositId(AchDeposit deposit) {
    return deposit.name().get().split("/")[3];
  }

  public static String getAchWithdrawalId(AchWithdrawal withdrawal) {
    return withdrawal.name().get().split("/")[3];
  }

  public static String getIctDepositId(IctDeposit deposit) {
    return deposit.name().get().split("/")[3];
  }

  public static String getIctWithdrawalId(IctWithdrawal withdrawal) {
    return withdrawal.name().get().split("/")[3];
  }

  public static String createIctDepositId(SDK sdk, String accountId) throws Exception {
    var req =
        IctDepositCreate.builder()
            .amount(new DecimalCreate(Optional.of("100.00")))
            .clientTransferId(UUID.randomUUID().toString())
            .program(Program.BROKER_PARTNER)
            .travelRule(
                IctDepositTravelRuleCreate.builder()
                    .individualOriginatingParty(
                        TravelRulePartyCreate.builder()
                            .address(
                                PostalAddressCreate.builder()
                                    .locality("San Francisco")
                                    .regionCode("US")
                                    .addressLines(Collections.singletonList("123 Main St"))
                                    .postalCode("94105")
                                    .administrativeArea("CA")
                                    .build())
                            .familyName("Jacob")
                            .givenNames(Collections.singletonList("Bob"))
                            .build())
                    .individualRecipientParty(
                        TravelRulePartyCreate.builder()
                            .address(
                                PostalAddressCreate.builder()
                                    .locality("San Francisco")
                                    .regionCode("US")
                                    .addressLines(Collections.singletonList("123 Main St"))
                                    .postalCode("94105")
                                    .administrativeArea("CA")
                                    .build())
                            .familyName("Jacob")
                            .givenNames(Collections.singletonList("Bob"))
                            .build())
                    .originatingInstitution(
                        InstitutionCreate.builder()
                            .accountId(accountId)
                            .title("Default Bank")
                            .build())
                    .build())
            .build();
    var res =
        sdk.instantCashTransferICT()
            .createIctDeposit()
            .accountId(accountId)
            .ictDepositCreate(req)
            .call();

    if (res.statusCode() == 200) {
      return res.ictDeposit().get().name().get().split("/")[3];
    }
    throw new Exception("Failed to create ICT deposit: " + res.statusCode());
  }

  public static String createIctWithdrawalId(SDK sdk, String accountId) throws Exception {
    var req =
        IctWithdrawalCreate.builder()
            .clientTransferId(UUID.randomUUID().toString())
            .program(IctWithdrawalCreateProgram.BROKER_PARTNER)
            .fullDisbursement(true)
            .travelRule(
                IctWithdrawalTravelRuleCreate.builder()
                    .recipientInstitution(
                        InstitutionCreate.builder()
                            .accountId("09673049")
                            .title("Default Bank")
                            .build())
                    .build())
            .build();
    var res =
        sdk.instantCashTransferICT()
            .createIctWithdrawal()
            .accountId(accountId)
            .ictWithdrawalCreate(req)
            .call();
    if (res.statusCode() == 200) {
      return res.ictWithdrawal().get().name().get().split("/")[3];
    }
    throw new Exception("Failed to create ICT withdrawal: " + res.statusCode());
  }

  public static String createFeeId(SDK sdk, Account account, BankRelationship relationship)
      throws Exception {
    var req =
        TransfersFeeCreate.builder()
            .amount(DecimalCreate.builder().value("10.00").build())
            .clientTransferId(UUID.randomUUID().toString())
            .description("Fee charged")
            .type(TransfersFeeCreateType.MANAGEMENT)
            .build();
    var res =
        sdk.feesAndCredits()
            .createFee()
            .accountId(account.accountId().get())
            .transfersFeeCreate(req)
            .call();
    if (res.statusCode() == 200) {
      return res.transfersFee().get().name().get().split("/")[3];
    }
    throw new Exception("Failed to create fee: " + res.statusCode());
  }

  public static String createCreditId(SDK sdk, Account account, BankRelationship relationship)
      throws Exception {
    var req =
        TransfersCreditCreate.builder()
            .amount(DecimalCreate.builder().value("10.00").build())
            .clientTransferId(UUID.randomUUID().toString())
            .description("Credit given as a promotion")
            .type(TransfersCreditCreateType.PROMOTIONAL)
            .build();
    var res =
        sdk.feesAndCredits()
            .createCredit()
            .accountId(account.accountId().get())
            .transfersCreditCreate(req)
            .call();
    if (res.statusCode() == 200) {
      return res.transfersCredit().get().name().get().split("/")[3];
    }
    throw new Exception("Failed to create credit: " + res.statusCode());
  }

  public static void verifyMicroDeposits(
      SDK sdk, Account account, BankRelationship relationship, MicroDepositAmounts amounts)
      throws Exception {
    var request =
        VerifyMicroDepositsRequestCreate.builder()
            .amounts(
                MicroDepositAmountsCreate.builder()
                    .amount1(
                        DecimalCreate.builder()
                            .value(amounts.amount1().get().value().get())
                            .build())
                    .amount2(
                        DecimalCreate.builder()
                            .value(amounts.amount2().get().value().get())
                            .build())
                    .build())
            .name(relationship.name().get())
            .build();
    var response =
        sdk.bankRelationships()
            .verifyMicroDeposits(
                account.accountId().get(), getBankRelationshipId(relationship), request);
    if (response.statusCode() != 200) {
      throw new Exception("Failed to verify micro deposits: " + response.statusCode());
    }
  }

  public static String getWithdrawalAccountId() {
    return "01JHGTEPC6ZTAHCFRH2MD3VJJT";
  }

  public static String getDeceasedAccountId() {
    return "01JHK07CRQ9X8P5XE9JWG4PFSP";
  }

  public static String createCompletedWithdrawal(SDK sdk, String withdrawalAccountId)
      throws Exception {
    var res = sdk.bankRelationships().listBankRelationships(withdrawalAccountId);
    var maxRelationships =
        res.listBankRelationshipsResponse().get().bankRelationships().get().size();
    var attemptCount = 0;
    while (attemptCount < maxRelationships) {
      if (res.listBankRelationshipsResponse()
          .get()
          .bankRelationships()
          .get()
          .get(attemptCount)
          .state()
          .get()
          .state()
          .get()
          .equals(BankRelationshipStateState.APPROVED)) {
        var cancelBankRelationshipId =
            res.listBankRelationshipsResponse()
                .get()
                .bankRelationships()
                .get()
                .get(attemptCount)
                .name()
                .get()
                .split("/")[3];
        var req =
            CancelBankRelationshipRequestCreate.builder()
                .name(
                    "accounts/"
                        + withdrawalAccountId
                        + "/bankRelationships/"
                        + cancelBankRelationshipId)
                .comment("Cancling bank user request")
                .build();
        sdk.bankRelationships()
            .cancelBankRelationship()
            .accountId(withdrawalAccountId)
            .bankRelationshipId(cancelBankRelationshipId)
            .cancelBankRelationshipRequestCreate(req)
            .call();
      }
      attemptCount++;
    }

    var bankRel =
        createVerifiedBankRelationship(sdk, AccountUtil.getAccount(sdk, withdrawalAccountId));
    Thread.sleep(10000);
    var withdrawal =
        createAchWithdrawal(sdk, AccountUtil.getAccount(sdk, withdrawalAccountId), bankRel);
    return getAchWithdrawalId(withdrawal);
  }

  public static BankRelationship getBankRelationship(SDK sdk, String accountId, String bankRelId)
      throws Exception {
    var res = sdk.bankRelationships().getBankRelationship(accountId, bankRelId);
    return res.bankRelationship().get();
  }

  public static AchWithdrawal createAchWithdrawalRetirement(
      SDK sdk, String accountId, String relationshipId) throws Exception {
    var request =
        AchWithdrawalCreate.builder()
            .bankRelationship("accounts/" + accountId + "/bankRelationships/" + relationshipId)
            .amount(DecimalCreate.builder().value("1").build())
            .clientTransferId(UUID.randomUUID().toString())
            .retirementDistribution(
                RetirementDistributionCreate.builder()
                    .type(
                        RetirementDistributionCreateType
                            .EXCESS_CONTRIBUTION_REMOVAL_BEFORE_TAX_DEADLINE)
                    .build())
            .build();

    var response = sdk.achTransfers().createAchWithdrawal(accountId, request);
    if (response.statusCode() == 200) {
      return response.achWithdrawal().get();
    } else {
      throw new Exception("Failed to create ACH withdrawal: " + response.statusCode());
    }
  }

  public static String createIctWithdrawalIdRetirement(SDK sdk, String accountId) throws Exception {
    var req =
        IctWithdrawalCreate.builder()
            .clientTransferId(UUID.randomUUID().toString())
            .program(IctWithdrawalCreateProgram.BROKER_PARTNER)
            .fullDisbursement(true)
            .retirementDistribution(
                new RetirementDistributionCreate(
                    RetirementDistributionCreateType
                        .EXCESS_CONTRIBUTION_REMOVAL_BEFORE_TAX_DEADLINE))
            .travelRule(
                IctWithdrawalTravelRuleCreate.builder()
                    .recipientInstitution(
                        InstitutionCreate.builder()
                            .accountId("09673049")
                            .title("Default Bank")
                            .build())
                    .build())
            .build();
    var res =
        sdk.instantCashTransferICT()
            .createIctWithdrawal()
            .accountId(accountId)
            .ictWithdrawalCreate(req)
            .call();
    if (res.statusCode() == 200) {
      return res.ictWithdrawal().get().name().get().split("/")[3];
    }
    throw new Exception("Failed to create ICT withdrawal: " + res.statusCode());
  }

  public static String createWireWithdrawalId(SDK sdk, String accountId) throws Exception {
    var request =
        WireWithdrawalCreate.builder()
            .amount(DecimalCreate.builder().value("1.00").build())
            .beneficiary(
                WireWithdrawalBeneficiaryCreate.builder()
                    .account(getWithdrawalAccountId())
                    .accountTitle("Test")
                    .address(
                        AddressCreate.builder()
                            .streetAddress(Collections.singletonList("123 Main St"))
                            .state("OR")
                            .city("Portland")
                            .postalCode("97201")
                            .country("USA")
                            .build())
                    .thirdParty(true)
                    .build())
            .recipientBank(
                WireWithdrawalRecipientBankCreate.builder()
                    .bankId(
                        RecipientBankBankIdCreate.builder()
                            .id("011000028")
                            .type(
                                com.apexfintechsolutions.ascendsdk.models.components
                                    .RecipientBankBankIdCreateType.ABA)
                            .build())
                    .build())
            .clientTransferId(UUID.randomUUID().toString())
            .build();

    var response = sdk.wires().createWireWithdrawal(accountId, request);
    if (response.statusCode() == 200) {
      return response.wireWithdrawal().get().name().get().split("/")[3];
    } else {
      throw new Exception("Failed to create wire withdrawal: " + response.statusCode());
    }
  }

  public static String getWireDepositId() {
    return "20250218014356";
  }

  public static String createCashJournalId(SDK sdk, String accountId) throws Exception {
    var req =
        CashJournalCreate.builder()
            .clientTransferId(UUID.randomUUID().toString())
            .destinationAccount("accounts/" + accountId)
            .amount(DecimalCreate.builder().value("250001.00").build())
            .sourceAccount("accounts/" + getWithdrawalAccountId())
            .build();
    var res = sdk.journals().createCashJournal(req);
    if (res.statusCode() == 200) {
      return res.cashJournal().get().name().get().split("/")[1];
    }
    throw new Exception("Failed to create cash journal: " + res.statusCode());
  }
}
