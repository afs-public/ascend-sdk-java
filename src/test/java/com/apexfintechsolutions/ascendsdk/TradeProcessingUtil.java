package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.Date;

public class TradeProcessingUtil {
  public static String[] createBooking(SDK sdk, String accountID) throws Exception {

    // Create a booking
    var now = new Date();
    var req =
        TradeCreate.builder()
            .accountId(accountID)
            .brokerCapacity(TradeCreateBrokerCapacity.PRINCIPAL)
            .clientOrderId(UUID.randomUUID().toString())
            .executions(
                List.of(
                    ExecutionCreate.builder()
                        .executionTime(OffsetDateTime.ofInstant(now.toInstant(), ZoneOffset.UTC))
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
            .open(true)
            .build();

    var response = sdk.tradeBooking().createTrade(accountID, req);

    if (response.statusCode() == 200) {
      return new String[] {
        response.bookingTrade().get().tradeId().get(),
        response.bookingTrade().get().clientOrderId().get()
      };
    } else {
      throw new Exception("Failed to create Trade Booking: " + response.statusCode());
    }
  }

  public static String createExecution(SDK sdk, String accountID, String tradeID) throws Exception {
    var now = new Date();
    var req =
        ExecutionCreate.builder()
            .executionTime(OffsetDateTime.ofInstant(now.toInstant(), ZoneOffset.UTC))
            .externalId(UUID.randomUUID().toString())
            .price(DecimalCreate.builder().value("5").build())
            .quantity(DecimalCreate.builder().value("1").build())
            .build();

    var response = sdk.tradeBooking().createExecution(accountID, tradeID, req);

    if (response.statusCode() == 200) {
      return response.execution().get().executionId().get();
    } else {
      throw new Exception("Failed to create Execution: " + response.statusCode());
    }
  }

  public static String createTradeAllocation(SDK sdk, String accountID) throws Exception {
    var now = new Date();
    var req =
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
            .toAccountId(accountID)
            .toSide(ToSide.BUY)
            .build();

    var requestID = UUID.randomUUID().toString();

    var response =
        sdk.tradeAllocation()
            .createTradeAllocation()
            .requestId(requestID)
            .accountId(accountID)
            .tradeAllocationCreate(req)
            .call();

    if (response.statusCode() == 200) {
      return response.tradeAllocation().get().tradeAllocationId().get();
    } else {
      throw new Exception("Failed to create Trade Allocation: " + response.statusCode());
    }
  }
}
