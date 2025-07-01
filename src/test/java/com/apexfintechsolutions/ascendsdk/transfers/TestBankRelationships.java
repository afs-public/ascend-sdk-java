package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.SDKError;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestBankRelationships {
  private SDK sdk;

  private BankRelationship bankRelationship;
  private Account enrolledAccount;

  private Account reUseAccount;

  @BeforeAll
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();

    LegalNaturalPerson lnp = AccountUtil.createLnp(sdk);
    enrolledAccount = AccountUtil.createEnrolledAccountWithLNP(sdk, lnp);
    reUseAccount = AccountUtil.createEnrolledAccountWithLNP(sdk, lnp);
    bankRelationship = TransfersUtil.createBankRelationship(sdk, enrolledAccount);
  }

  @Order(1)
  @Test
  public void bank_relationships_transfers_create_bank_relationships_create_bank_relationships1()
      throws Exception {
    Assertions.assertNotNull(bankRelationship);
    Assertions.assertTrue(bankRelationship.name().isPresent());
  }

  @Order(2)
  @Test
  public void bank_relationships_transfers_list_bank_relationships_list_bank_relationships1()
      throws Exception {
    var result = sdk.bankRelationships().listBankRelationships(enrolledAccount.accountId().get());
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(3)
  @Test
  public void bank_relationships_transfers_get_bank_relationships_get_bank_relationships1()
      throws Exception {
    var result =
        sdk.bankRelationships()
            .getBankRelationship(
                enrolledAccount.accountId().get(),
                TransfersUtil.getBankRelationshipId(bankRelationship));
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(4)
  @Test
  public void bank_relationships_transfers_update_bank_relationships_update_bank_relationships1()
      throws Exception {
    var request = BankRelationshipUpdate.builder().nickname("My primary bank").build();

    var result =
        sdk.bankRelationships()
            .updateBankRelationship(
                enrolledAccount.accountId().get(),
                TransfersUtil.getBankRelationshipId(bankRelationship),
                request);
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(5)
  @Test
  public void bank_relationships_transfers_reissue_micro_deposits_reissue_micro_deposits1()
      throws Exception {
    var wrongAmounts =
        TransfersUtil.getFailingMicrodepositAmounts(sdk, enrolledAccount, bankRelationship);
    var request =
        VerifyMicroDepositsRequestCreate.builder()
            .name(bankRelationship.name().get())
            .amounts(wrongAmounts)
            .build();

    // try to verify the micro deposits with the wrong amounts 3x to make the MD amounts get
    // canceled
    for (int i = 0; i < 3; i++) {
      try {
        sdk.bankRelationships()
            .verifyMicroDeposits(
                enrolledAccount.accountId().get(),
                TransfersUtil.getBankRelationshipId(bankRelationship),
                request);
      } catch (SDKError sdkError) {
        throw sdkError;
      } catch (Exception e) {
        continue;
      }
    }

    var reissueRequest =
        ReissueMicroDepositsRequestCreate.builder().name(bankRelationship.name().get()).build();

    // reissue the micro deposits
    var result =
        sdk.bankRelationships()
            .reissueMicroDeposits(
                enrolledAccount.accountId().get(),
                TransfersUtil.getBankRelationshipId(bankRelationship),
                reissueRequest);
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(6)
  @Test
  public void bank_relationships_transfers_verify_micro_deposits_verify_micro_deposits1()
      throws Exception {
    var amounts =
        TransfersUtil.getCorrectMicrodepositAmounts(sdk, enrolledAccount, bankRelationship);

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
            .name(bankRelationship.name().get())
            .build();

    var result =
        sdk.bankRelationships()
            .verifyMicroDeposits(
                enrolledAccount.accountId().get(),
                TransfersUtil.getBankRelationshipId(bankRelationship),
                request);
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(7)
  @Test
  public void bank_relationships_transfers_reuse_bank_relationships_reuse_bank_relationships1()
      throws Exception {
    var request =
        ReuseBankRelationshipRequestCreate.builder()
            .parent("accounts/" + enrolledAccount.accountId().get())
            .sourceBankRelationship(
                "accounts/"
                    + enrolledAccount.accountId().get()
                    + "/bank_relationships/"
                    + TransfersUtil.getBankRelationshipId(bankRelationship))
            .build();

    var result =
        sdk.bankRelationships().reuseBankRelationship(reUseAccount.accountId().get(), request);
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(8)
  @Test
  public void bank_relationships_transfers_cancel_bank_relationships_cancel_bank_relationships1()
      throws Exception {
    var request =
        CancelBankRelationshipRequestCreate.builder()
            .name(bankRelationship.name().get())
            .comment("Canceling BR")
            .build();

    var result =
        sdk.bankRelationships()
            .cancelBankRelationship(
                enrolledAccount.accountId().get(),
                TransfersUtil.getBankRelationshipId(bankRelationship),
                request);
    Assertions.assertEquals(200, result.statusCode());
  }
}
