package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestCashBalances {
  private SDK sdk;
  private String accountId;

  @BeforeAll
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();
    accountId = AccountUtil.createEnrolledAccount(sdk).accountId().get();
  }

  @Test
  public void test_cash_balances_transfers_get_cash_balance_get_cash_balance() throws Exception {
    var res = sdk.cashBalances().calculateCashBalance().accountId(accountId).call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }
}
