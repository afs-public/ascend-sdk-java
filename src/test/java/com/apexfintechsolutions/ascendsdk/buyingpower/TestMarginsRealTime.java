package com.apexfintechsolutions.ascendsdk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestMarginsRealTime {
  private SDK sdk;
  private String accountId;

  @BeforeAll
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();
    accountId = AccountUtil.createEnrolledAccount(sdk).accountId().get();
  }

  @Order(1)
  @Test
  public void test_margins_real_time_get_buying_power() throws Exception {
    var res = sdk.buyingPower().getBuyingPower(accountId);

    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
    Assertions.assertTrue(res.buyingPower().isPresent());
  }

  @Order(2)
  @Test
  public void test_margins_real_time_get_asset_buying_power() throws Exception {
    var res = sdk.buyingPower().getAssetBuyingPower(accountId, "8395");

    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
    Assertions.assertTrue(res.assetBuyingPower().isPresent());
  }
}
