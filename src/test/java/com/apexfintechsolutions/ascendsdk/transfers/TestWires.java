package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import java.util.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestWires {
  private SDK sdk;
  private Account enrolledAccount;
  private String wireWithdrawalId;
  private String wireDepositId;
  private String withdrawalAccountId;

  @BeforeAll
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();
    enrolledAccount = AccountUtil.createEnrolledAccount(sdk);
    wireWithdrawalId = TransfersUtil.createWireWithdrawalId(sdk, enrolledAccount.accountId().get());
    wireDepositId = TransfersUtil.getWireDepositId();
    withdrawalAccountId = TransfersUtil.getWithdrawalAccountId();
  }

  @Test
  public void test_wires_transfers_get_wire_deposit_fee_summary_get_wire_deposit_fee_summary1()
      throws Exception {
    var res =
        sdk.wires()
            .getWireDeposit()
            .accountId(withdrawalAccountId)
            .wireDepositId(wireDepositId)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }

  @Order(1)
  @Test
  public void test_wires_transfers_create_wire_withdrawal_create_wire_withdrawal1()
      throws Exception {
    Assertions.assertNotNull(wireWithdrawalId);
  }

  @Order(2)
  @Test
  public void test_wires_transfers_get_wire_withdrawal_get_wire_withdrawal1() throws Exception {
    var res =
        sdk.wires()
            .getWireWithdrawal()
            .accountId(enrolledAccount.accountId().get())
            .wireWithdrawalId(wireWithdrawalId)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }

  @Order(3)
  @Test
  public void test_wires_transfers_cancel_wire_withdrawal_cancel_wire_withdrawal1()
      throws Exception {
    var req =
        CancelWireWithdrawalRequestCreate.builder()
            .name(
                "accounts/"
                    + enrolledAccount.accountId().get()
                    + "/wireWithdrawals/"
                    + wireWithdrawalId)
            .reason("User request")
            .build();
    var res =
        sdk.wires()
            .cancelWireWithdrawal()
            .accountId(enrolledAccount.accountId().get())
            .wireWithdrawalId(wireWithdrawalId)
            .cancelWireWithdrawalRequestCreate(req)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }
}
