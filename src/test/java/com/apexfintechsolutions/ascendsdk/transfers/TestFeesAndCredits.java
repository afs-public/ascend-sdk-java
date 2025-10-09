package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import java.util.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestFeesAndCredits {
  private SDK sdk;
  private Account enrolledAccount;
  private BankRelationship bankRel;
  private String feeId;
  private String creditId;

  @BeforeAll
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();
    enrolledAccount = AccountUtil.createEnrolledAccount(sdk);
    bankRel = TransfersUtil.createVerifiedBankRelationship(sdk, enrolledAccount);
    feeId = TransfersUtil.createFeeId(sdk, enrolledAccount, bankRel);
    creditId = TransfersUtil.createCreditId(sdk, enrolledAccount, bankRel);
  }

  @Test
  @Order(1)
  public void test_fees_and_credits_transfers_create_fee_create_fee1() throws Exception {
    Assertions.assertNotNull(feeId);
  }

  @Test
  @Order(2)
  public void test_fees_and_credits_transfers_get_fee_get_fee1() throws Exception {
    var res =
        sdk.feesAndCredits()
            .getFee()
            .accountId(enrolledAccount.accountId().get())
            .feeId(feeId)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }

  @Test
  @Order(3)
  public void test_fees_and_credits_transfers_cancel_fee_cancel_fee1() throws Exception {
    var req =
        CancelFeeRequestCreate.builder()
            .name("accounts/" + enrolledAccount.accountId().get() + "/feesAndCredits/" + feeId)
            .build();
    var res =
        sdk.feesAndCredits()
            .cancelFee()
            .accountId(enrolledAccount.accountId().get())
            .feeId(feeId)
            .cancelFeeRequestCreate(req)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }

  @Test
  @Order(1)
  public void test_fees_and_credits_transfers_create_credit_create_credit1() throws Exception {
    Assertions.assertNotNull(creditId);
  }

  @Test
  @Order(2)
  public void test_fees_and_credits_transfers_get_credit_get_credit1() throws Exception {
    var res =
        sdk.feesAndCredits()
            .getCredit()
            .accountId(enrolledAccount.accountId().get())
            .creditId(creditId)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }

  @Test
  @Order(3)
  public void test_fees_and_credits_transfers_cancel_credit_cancel_credit1() throws Exception {
    var req =
        CancelCreditRequestCreate.builder()
            .name("accounts/" + enrolledAccount.accountId().get() + "/feesAndCredits/" + creditId)
            .build();
    var res =
        sdk.feesAndCredits()
            .cancelCredit()
            .accountId(enrolledAccount.accountId().get())
            .creditId(creditId)
            .cancelCreditRequestCreate(req)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }
}
