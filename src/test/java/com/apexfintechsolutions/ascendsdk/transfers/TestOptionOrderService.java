package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestOptionOrderService {
  private SDK sdk;
  private Account account;
  private OptionOrder optionOrder;
  private String optionOrderId;

  @BeforeAll
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();
    account = AccountUtil.createEnrolledAccount(sdk);

    var fundRequest =
        TransfersCreditCreate.builder()
            .amount(new DecimalCreate(java.util.Optional.of("10000.00")))
            .type(TransfersCreditCreateType.PROMOTIONAL)
            .clientTransferId(UUID.randomUUID().toString())
            .description("promotional credit")
            .build();

    var fundResponse = sdk.feesAndCredits().createCredit(account.accountId().get(), fundRequest);
    Assertions.assertEquals(200, fundResponse.statusCode());
  }

  @Order(1)
  @Test
  public void test_option_order_service_create_option_order() throws Exception {
    var today = Calendar.getInstance();

    var request =
        OptionOrderCreate.builder()
            .clientOrderId(UUID.randomUUID().toString())
            .currencyCode("USD")
            .orderType(OptionOrderCreateOrderType.LIMIT)
            .limitPrice(DecimalCreate.builder().value("0.01").build())
            .priceDirection(PriceDirection.DEBIT)
            .quantity(DecimalCreate.builder().value("1").build())
            .orderDate(
                DateCreate.builder()
                    .year(today.get(Calendar.YEAR))
                    .month(today.get(Calendar.MONTH) + 1)
                    .day(today.get(Calendar.DAY_OF_MONTH))
                    .build())
            .brokerCapacity(OptionOrderCreateBrokerCapacity.AGENCY)
            .timeInForce(OptionOrderCreateTimeInForce.DAY)
            .legs(
                List.of(
                    OptionOrderLegCreate.builder()
                        .assetType(OptionOrderLegCreateAssetType.OPTION)
                        .identifierType(OptionOrderLegCreateIdentifierType.OSI)
                        .identifier("SBUX260116C00084000")
                        .side(OptionOrderLegCreateSide.BUY)
                        .sideModifier(SideModifier.OPEN)
                        .ratioQuantity(1)
                        .build()))
            .build();

    var res = sdk.optionOrders().createOptionOrder(account.accountId().get(), request);

    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
    Assertions.assertTrue(res.optionOrder().isPresent());

    optionOrder = res.optionOrder().get();
    Assertions.assertTrue(optionOrder.optionOrderId().isPresent());
    optionOrderId = optionOrder.optionOrderId().get();
  }

  @Order(2)
  @Test
  public void test_option_order_service_get_option_order() throws Exception {
    var res = sdk.optionOrders().getOptionOrder(account.accountId().get(), optionOrderId);

    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
    Assertions.assertTrue(res.optionOrder().isPresent());
  }

  @Order(3)
  @Test
  public void test_option_order_service_cancel_option_order() throws Exception {
    var request = CancelOptionOrderRequestCreate.builder().name(optionOrder.name().get()).build();

    try {
      var res =
          sdk.optionOrders().cancelOptionOrder(account.accountId().get(), optionOrderId, request);

      Assertions.assertNotNull(res);
      Assertions.assertEquals(200, res.statusCode());
      Assertions.assertTrue(res.optionOrder().isPresent());
    } catch (Status status) {
      Assertions.assertEquals(9, status.code().get());
      Assertions.assertTrue(status.message().get().toLowerCase().contains("not in cancelable"));
    }
  }
}
