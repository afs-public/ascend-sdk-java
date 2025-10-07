package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.components.Order;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestCreateOrder {
  private SDK sdk;
  private Account account;
  private Order order;

  @BeforeAll
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();
    account = AccountUtil.createEnrolledAccount(sdk);
    order = OrderUtil.createLimitOrder(sdk, account);
  }

  @org.junit.jupiter.api.Order(1)
  @Test
  public void create_order_orders_create_order_create_order1() throws Exception {
    Assertions.assertNotNull(order);
    Assertions.assertTrue(order.orderId().isPresent());
  }

  @org.junit.jupiter.api.Order(2)
  @Test
  public void create_order_orders_cancel_order_cancel_order1() throws Exception {
    var request = CancelOrderRequestCreate.builder().name(order.name().get()).build();
    var result =
        sdk.createOrder().cancelOrder(account.accountId().get(), order.orderId().get(), request);
    Assertions.assertEquals(200, result.statusCode());
  }

  @org.junit.jupiter.api.Order(3)
  @Test
  public void test_create_order_orders_get_order_get_order1() throws Exception {
    var result = sdk.createOrder().getOrder(account.accountId().get(), order.orderId().get());
    Assertions.assertEquals(200, result.statusCode());
  }
}
