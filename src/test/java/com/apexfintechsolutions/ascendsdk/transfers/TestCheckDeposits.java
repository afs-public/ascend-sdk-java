package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestCheckDeposits {
  private SDK sdk;
  private Account enrolledAccount;
  private String checkDepositId;
  private String checkDepositName;

  @BeforeAll
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();
    enrolledAccount = AccountUtil.createEnrolledAccount(sdk);
  }

  @Order(1)
  @Test
  public void test_check_deposits_simulate_create_check_deposit() throws Exception {
    var request =
        SimulateCreateCheckDepositRequestCreate.builder()
            .amount(DecimalCreate.builder().value("100.00").build())
            .parent("accounts/" + enrolledAccount.accountId().get())
            .build();

    var res =
        sdk.testSimulation().simulateCreateCheckDeposit(enrolledAccount.accountId().get(), request);

    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
    Assertions.assertTrue(res.checkDeposit().isPresent());
    Assertions.assertTrue(res.checkDeposit().get().name().isPresent());

    checkDepositName = res.checkDeposit().get().name().get();
    checkDepositId = checkDepositName.split("/")[3];
  }

  @Order(2)
  @Test
  public void test_check_deposits_get_check_deposit() throws Exception {
    var res = sdk.checks().getCheckDeposit(enrolledAccount.accountId().get(), checkDepositId);

    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
    Assertions.assertTrue(res.checkDeposit().isPresent());
  }

  @Order(3)
  @Test
  public void test_check_deposits_force_approve_check_deposit() throws Exception {
    var request = ForceApproveCheckDepositRequestCreate.builder().name(checkDepositName).build();

    try {
      var res =
          sdk.testSimulation()
              .forceApproveCheckDeposit(enrolledAccount.accountId().get(), checkDepositId, request);

      Assertions.assertNotNull(res);
      Assertions.assertEquals(200, res.statusCode());
      Assertions.assertTrue(res.checkDeposit().isPresent());
    } catch (Status status) {
      Assertions.assertEquals(3, status.code().get());
      Assertions.assertTrue(
          status.message().get().toLowerCase().contains("that does not need review"));
    }
  }
}
