package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestTradeAllocation {
  private SDK sdk;
  private String account;

  private String tradeAllocationID;

  private String rebookTradeAllocationID;

  @BeforeAll
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();
    account = TransfersUtil.getWithdrawalAccountId();
    tradeAllocationID = TradeProcessingUtil.createTradeAllocation(sdk, account);
    rebookTradeAllocationID = TradeProcessingUtil.createTradeAllocation(sdk, account);
  }

  @Order(1)
  @Test
  public void
      test_trade_allocation_trade_processing_create_trade_allocation_create_trade_allocation1()
          throws Exception {
    Assertions.assertNotNull(tradeAllocationID);
  }

  @Order(2)
  @Test
  public void test_trade_allocation_trade_processing_get_trade_allocation_get_trade_allocation1()
      throws Exception {
    Thread.sleep(5000);
    var result = sdk.tradeAllocation().getTradeAllocation(account, tradeAllocationID);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(3)
  @Test
  public void
      test_trade_allocation_trade_processing_rebook_trade_allocation_rebook_trade_allocation1()
          throws Exception {
    var now = new Date();
    var req =
        RebookTradeAllocationRequestCreate.builder()
            .name("accounts/" + account + "tradeAllocations/" + rebookTradeAllocationID)
            .requestId(UUID.randomUUID().toString())
            .tradeAllocation(
                TradeAllocationCreate.builder()
                    .brokerCapacity(TradeAllocationCreateBrokerCapacity.PRINCIPAL)
                    .executionTime(OffsetDateTime.ofInstant(now.toInstant(), ZoneOffset.UTC))
                    .fromAccountId(TransfersUtil.getDeceasedAccountId())
                    .identifier("SBUX")
                    .identifierType(TradeAllocationCreateIdentifierType.SYMBOL)
                    .assetType(TradeAllocationCreateAssetType.EQUITY)
                    .price(DecimalCreate.builder().value("5").build())
                    .quantity(DecimalCreate.builder().value("1").build())
                    .sourceApplication("Trading-App")
                    .toAccountId(account)
                    .toSide(ToSide.BUY)
                    .build())
            .build();

    var result = sdk.tradeAllocation().rebookTradeAllocation(account, rebookTradeAllocationID, req);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(4)
  @Test
  public void
      test_trade_allocation_trade_processing_cancel_trade_allocation_cancel_trade_allocation1()
          throws Exception {
    var req =
        CancelTradeAllocationRequestCreate.builder()
            .name("accounts/" + account + "tradeAllocations/" + tradeAllocationID)
            .build();

    var result = sdk.tradeAllocation().cancelTradeAllocation(account, tradeAllocationID, req);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(200, result.statusCode());
  }
}
