package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.operations.BasketOrdersServiceListBasketOrdersRequest;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestBasketOrders {
  private SDK sdk;
  private Account account;
  private Basket basket;
  private String basketOrderToRemove = UUID.randomUUID().toString();

  @BeforeAll
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();
    account = AccountUtil.createEnrolledAccount(sdk);
    basket = OrderUtil.createBasketOrder(sdk);
  }

  @Order(1)
  @Test
  public void test_create_order_orders_create_order_create_order1() throws Exception {
    Assertions.assertNotNull(basket);
    Assertions.assertFalse(basket.basketId().get().isEmpty());
  }

  @Order(2)
  @Test
  public void test_basket_orders_orders_add_orders_add_orders1() throws Exception {
    var request =
        AddOrdersRequestCreate.builder()
            .name(basket.name().get())
            .basketOrders(
                List.of(
                    BasketOrderCreate.builder()
                        .accountId(OrderUtil.WITHDRAWAL_ACCOUNT_ID)
                        .assetType(BasketOrderCreateAssetType.EQUITY)
                        .clientOrderId(UUID.randomUUID().toString())
                        .identifier("SBUX")
                        .identifierType(BasketOrderCreateIdentifierType.SYMBOL)
                        .orderType(BasketOrderCreateOrderType.MARKET)
                        .quantity(DecimalCreate.builder().value("1").build())
                        .side(BasketOrderCreateSide.BUY)
                        .timeInForce(BasketOrderCreateTimeInForce.DAY)
                        .build(),
                    BasketOrderCreate.builder()
                        .accountId(OrderUtil.WITHDRAWAL_ACCOUNT_ID)
                        .assetType(BasketOrderCreateAssetType.EQUITY)
                        .clientOrderId(basketOrderToRemove)
                        .identifier("SBUX")
                        .identifierType(BasketOrderCreateIdentifierType.SYMBOL)
                        .orderType(BasketOrderCreateOrderType.MARKET)
                        .quantity(DecimalCreate.builder().value("1").build())
                        .side(BasketOrderCreateSide.BUY)
                        .timeInForce(BasketOrderCreateTimeInForce.DAY)
                        .build()))
            .build();

    var result =
        sdk.basketOrders()
            .addOrders(SdkUtil.getCorrespondentId(), basket.basketId().get(), request);

    Assertions.assertNotNull(result);
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(3)
  @Test
  public void test_basket_orders_orders_get_basket_get_basket1() throws Exception {
    var result =
        sdk.basketOrders().getBasket(SdkUtil.getCorrespondentId(), basket.basketId().get());
    Assertions.assertNotNull(result);
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(4)
  @Test
  public void test_basket_orders_orders_remove_basket_remove_basket1() throws Exception {
    var request =
        RemoveOrdersRequestCreate.builder()
            .name(basket.name().get())
            .clientOrderIds(List.of(basketOrderToRemove))
            .build();

    var result =
        sdk.basketOrders()
            .removeOrders(SdkUtil.getCorrespondentId(), basket.basketId().get(), request);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(5)
  @Test
  public void test_basket_orders_orders_submit_basket_submit_basket1() throws Exception {
    var request = SubmitBasketRequestCreate.builder().name(basket.name().get()).build();

    var result =
        sdk.basketOrders()
            .submitBasket(SdkUtil.getCorrespondentId(), basket.basketId().get(), request);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(6)
  @Test
  public void test_basket_orders_orders_list_basket_orders_list_basket_orders1() throws Exception {
    var request =
        BasketOrdersServiceListBasketOrdersRequest.builder()
            .correspondentId(SdkUtil.getCorrespondentId())
            .basketId(basket.basketId().get())
            .build();

    var result = sdk.basketOrders().listBasketOrders(request);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(7)
  @Test
  public void test_basket_orders_orders_list_compressed_orders_list_compressed_orders1()
      throws Exception {
    var result =
        sdk.basketOrders()
            .listCompressedOrders(SdkUtil.getCorrespondentId(), basket.basketId().get());
    Assertions.assertNotNull(result);
    Assertions.assertEquals(200, result.statusCode());
  }
}
