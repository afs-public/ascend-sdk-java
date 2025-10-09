package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestTradeBooking {
  private SDK sdk;
  private String account;
  private String[] bookingTradeId;

  private String executionId;

  private String rebookExecutionId;

  @BeforeAll
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();
    account = TransfersUtil.getWithdrawalAccountId();
    bookingTradeId = TradeProcessingUtil.createBooking(sdk, account);
    executionId = TradeProcessingUtil.createExecution(sdk, account, bookingTradeId[0]);
    rebookExecutionId = TradeProcessingUtil.createExecution(sdk, account, bookingTradeId[0]);
  }

  @Order(1)
  @Test
  public void test_trade_booking_trade_processing_create_trade_create_trade1() throws Exception {
    assert (bookingTradeId != null);
    assert (bookingTradeId.length == 2);
  }

  @Order(2)
  @Test
  public void test_trade_booking_trade_processing_get_trade_get_trade1() throws Exception {
    var result = sdk.tradeBooking().getTrade(account, bookingTradeId[0]);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(3)
  @Test
  public void test_trade_booking_trade_processing_create_execution_create_execution1()
      throws Exception {
    assert (executionId != null);
  }

  @Order(4)
  @Test
  public void test_trade_booking_trade_processing_get_execution_get_execution1() throws Exception {
    var result = sdk.tradeBooking().getExecution(account, bookingTradeId[0], executionId);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(5)
  @Test
  public void test_trade_booking_trade_processing_complete_trade_complete_trade1()
      throws Exception {
    var req =
        CompleteTradeRequestCreate.builder()
            .name("accounts/" + account + "trades/" + bookingTradeId[0])
            .build();

    var result = sdk.tradeBooking().completeTrade(account, bookingTradeId[0], req);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(6)
  @Test
  public void test_trade_booking_trade_processing_rebook_execution_rebook_execution1()
      throws Exception {
    var now = new Date();
    var req =
        RebookExecutionRequestCreate.builder()
            .name(
                "accounts/"
                    + account
                    + "trades/"
                    + bookingTradeId[0]
                    + "/executions/"
                    + rebookExecutionId)
            .execution(
                ExecutionCreate.builder()
                    .executionTime(OffsetDateTime.ofInstant(now.toInstant(), ZoneOffset.UTC))
                    .externalId(UUID.randomUUID().toString())
                    .price(DecimalCreate.builder().value("5").build())
                    .quantity(DecimalCreate.builder().value("1").build())
                    .build())
            .build();

    var result =
        sdk.tradeBooking().rebookExecution(account, bookingTradeId[0], rebookExecutionId, req);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(7)
  @Test
  public void test_trade_booking_trade_processing_cancel_execution_cancel_execution1()
      throws Exception {
    var req =
        CancelExecutionRequestCreate.builder()
            .name(
                "accounts/"
                    + account
                    + "trades/"
                    + bookingTradeId[0]
                    + "/executions/"
                    + executionId)
            .build();

    var result = sdk.tradeBooking().cancelExecution(account, bookingTradeId[0], executionId, req);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(8)
  @Test
  public void test_trade_booking_trade_processing_rebook_trade_rebook_trade1() throws Exception {
    var now = new Date();
    var req =
        RebookTradeRequestCreate.builder()
            .name("accounts/" + account + "trades/" + bookingTradeId[0])
            .trade(
                TradeCreate.builder()
                    .accountId(account)
                    .brokerCapacity(TradeCreateBrokerCapacity.PRINCIPAL)
                    .clientOrderId(bookingTradeId[1])
                    .executions(
                        List.of(
                            ExecutionCreate.builder()
                                .executionTime(
                                    OffsetDateTime.ofInstant(now.toInstant(), ZoneOffset.UTC))
                                .externalId(UUID.randomUUID().toString())
                                .price(DecimalCreate.builder().value("5").build())
                                .quantity(DecimalCreate.builder().value("1").build())
                                .build()))
                    .identifier("SBUX")
                    .identifierType(TradeCreateIdentifierType.SYMBOL)
                    .routeType(RouteType.QUIK)
                    .side(TradeCreateSide.BUY)
                    .sourceApplication("Trading-App")
                    .assetType(TradeCreateAssetType.EQUITY)
                    .build())
            .build();

    var result = sdk.tradeBooking().rebookTrade(account, bookingTradeId[0], req);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(9)
  @Test
  public void test_trade_booking_trade_processing_cancel_trade_cancel_trade1() throws Exception {
    var req =
        CancelTradeRequestCreate.builder()
            .name("accounts/" + account + "trades/" + bookingTradeId[0])
            .build();

    var result = sdk.tradeBooking().cancelTrade(account, bookingTradeId[0], req);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(200, result.statusCode());
  }
}
