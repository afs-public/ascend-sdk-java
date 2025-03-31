package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestAccountCreation {
  private SDK sdk;
  private Account enrolledAccount;

  @BeforeAll
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();
    enrolledAccount = AccountUtil.createEnrolledAccount(sdk);
  }

  @Order(1)
  @Test
  public void account_creation_accounts_create_account_create_account1() throws Exception {
    Assertions.assertNotNull(enrolledAccount);
    Assertions.assertTrue(enrolledAccount.name().isPresent());
  }

  @Order(2)
  @Test
  public void account_creation_accounts_get_account_get_account1() throws Exception {
    var result = sdk.accountCreation().getAccount(enrolledAccount.accountId().get());
    Assertions.assertEquals(200, result.statusCode());
  }
}
