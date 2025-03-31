package com.apexfintechsolutions.ascendsdk;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MarginTests {
  private SDK sdk;
  private String account;

  @BeforeAll
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();
    account = "01JHGTEPC6ZTAHCFRH2MD3VJJT";
  }

  @org.junit.jupiter.api.Order(1)
  @Test
  public void margins_margins_get_buying_power_get_buying_power1() throws Exception {
    var result = sdk.margins().getBuyingPower(account);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(200, result.statusCode());
  }
}
