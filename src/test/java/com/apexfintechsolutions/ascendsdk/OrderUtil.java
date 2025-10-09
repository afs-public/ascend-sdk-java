package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import java.util.*;
import org.junit.jupiter.api.Assertions;

public class OrderUtil {
  public static String WITHDRAWAL_ACCOUNT_ID = "01JHGTEPC6ZTAHCFRH2MD3VJJT";

  public static Basket createBasketOrder(SDK sdk) throws Exception {
    var request =
        BasketCreate.builder()
            .clientBasketId(UUID.randomUUID().toString())
            .correspondentId(SdkUtil.getCorrespondentId())
            .build();

    var response = sdk.basketOrders().createBasket(SdkUtil.getCorrespondentId(), request);

    if (response.basket().isEmpty()) {
      throw new Exception("Failed to create basket");
    }

    return response.basket().get();
  }

  public static Order createOrder(SDK sdk, Account account) throws Exception {
    var fundRequest =
        TransfersCreditCreate.builder()
            .amount(new DecimalCreate(Optional.of("10000.00")))
            .type(TransfersCreditCreateType.PROMOTIONAL)
            .clientTransferId(UUID.randomUUID().toString())
            .description("promotional credit")
            .build();

    var fundResponse = sdk.feesAndCredits().createCredit(account.accountId().get(), fundRequest);

    Assertions.assertEquals(200, fundResponse.statusCode());

    Calendar c = Calendar.getInstance();

    String client_order_id = UUID.randomUUID().toString();

    OrderCreate order =
        OrderCreate.builder()
            .clientOrderId(client_order_id)
            .orderDate(
                DateCreate.builder()
                    .year(c.get(Calendar.YEAR))
                    .month(c.get(Calendar.MONTH) + 1) // Calendar month is 0 indexed
                    .day(c.get(Calendar.DAY_OF_MONTH))
                    .build())
            .quantity(DecimalCreate.builder().value("1").build())
            .orderType(OrderType.MARKET)
            .assetType(AssetType.EQUITY)
            .identifier("SBUX")
            .identifierType(IdentifierType.SYMBOL)
            .side(Side.BUY)
            .orderType(OrderType.MARKET)
            .timeInForce(TimeInForce.DAY)
            .build();

    var result = sdk.createOrder().createOrder(account.accountId().get(), order);

    Assertions.assertEquals(200, result.statusCode());
    return result.order().get();
  }

  public static Order createLimitOrder(SDK sdk, Account account) throws Exception {
    var fundRequest =
        TransfersCreditCreate.builder()
            .amount(new DecimalCreate(Optional.of("10000.00")))
            .type(TransfersCreditCreateType.PROMOTIONAL)
            .clientTransferId(UUID.randomUUID().toString())
            .description("promotional credit")
            .build();

    var fundResponse = sdk.feesAndCredits().createCredit(account.accountId().get(), fundRequest);

    Assertions.assertEquals(200, fundResponse.statusCode());

    Calendar c = Calendar.getInstance();

    String client_order_id = UUID.randomUUID().toString();

    OrderCreate order =
        OrderCreate.builder()
            .clientOrderId(client_order_id)
            .orderDate(
                DateCreate.builder()
                    .year(c.get(Calendar.YEAR))
                    .month(c.get(Calendar.MONTH) + 1) // Calendar month is 0 indexed
                    .day(c.get(Calendar.DAY_OF_MONTH))
                    .build())
            .quantity(DecimalCreate.builder().value("1").build())
            .orderType(OrderType.LIMIT)
            .limitPrice(
                LimitPriceCreate.builder()
                    .type(LimitPriceCreateType.PRICE_PER_UNIT)
                    .price(DecimalCreate.builder().value("5.00").build())
                    .build())
            .assetType(AssetType.EQUITY)
            .identifier("SBUX")
            .identifierType(IdentifierType.SYMBOL)
            .side(Side.BUY)
            .timeInForce(TimeInForce.DAY)
            .build();

    var result = sdk.createOrder().createOrder(account.accountId().get(), order);

    Assertions.assertEquals(200, result.statusCode());
    return result.order().get();
  }

  public static String getIdentifierCusp() {
    return "912810SX7";
  }
}
