# TradeBooking
(*tradeBooking()*)

## Overview

### Available Operations

* [createTrade](#createtrade) - Create Trade
* [getTrade](#gettrade) - Get Trade
* [completeTrade](#completetrade) - Complete Trade
* [cancelTrade](#canceltrade) - Cancel Trade
* [rebookTrade](#rebooktrade) - Rebook Trade
* [createExecution](#createexecution) - Create Execution
* [getExecution](#getexecution) - Get Execution
* [cancelExecution](#cancelexecution) - Cancel Execution
* [rebookExecution](#rebookexecution) - Rebook Execution

## createTrade

Creates a trade with one or more executions. Combination of (account_id, client_order_id, and the process_date (determined by Booking service)) determines the uniqueness of the trade.

 Upon successful submission, returns the created trade and its details including Booking service enriched details.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.DecimalCreate;
import com.apexfintechsolutions.ascendsdk.models.components.ExecutionCreate;
import com.apexfintechsolutions.ascendsdk.models.components.RouteType;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.TradeCreate;
import com.apexfintechsolutions.ascendsdk.models.components.TradeCreateBrokerCapacity;
import com.apexfintechsolutions.ascendsdk.models.components.TradeCreateIdentifierType;
import com.apexfintechsolutions.ascendsdk.models.components.TradeCreateSide;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BookingCreateTradeResponse;
import java.lang.Exception;
import java.time.OffsetDateTime;
import java.util.List;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
                    .build())
            .build();

        BookingCreateTradeResponse res = sdk.tradeBooking().createTrade()
                .accountId("01FAKEACCOUNT1TYKWEYRH8S2K")
                .tradeCreate(TradeCreate.builder()
                    .accountId("02HASWB2DTMRT3DAM45P56J2T2")
                    .brokerCapacity(TradeCreateBrokerCapacity.AGENCY)
                    .clientOrderId("00be5285-0623-4560-8c58-f05af2c56ba0")
                    .executions(List.of(
                        ExecutionCreate.builder()
                            .executionTime(OffsetDateTime.parse("2024-07-17T12:00:00Z"))
                            .externalId("0H06HAP3A3Y")
                            .price(DecimalCreate.builder()
                                .build())
                            .quantity(DecimalCreate.builder()
                                .build())
                            .build(),
                        ExecutionCreate.builder()
                            .executionTime(OffsetDateTime.parse("2024-07-17T12:00:00Z"))
                            .externalId("0H06HAP3A3Y")
                            .price(DecimalCreate.builder()
                                .build())
                            .quantity(DecimalCreate.builder()
                                .build())
                            .build(),
                        ExecutionCreate.builder()
                            .executionTime(OffsetDateTime.parse("2024-07-17T12:00:00Z"))
                            .externalId("0H06HAP3A3Y")
                            .price(DecimalCreate.builder()
                                .build())
                            .quantity(DecimalCreate.builder()
                                .build())
                            .build()))
                    .identifier("AAPL")
                    .identifierType(TradeCreateIdentifierType.SYMBOL)
                    .routeType(RouteType.MNGD)
                    .side(TradeCreateSide.BUY)
                    .sourceApplication("Trading-App")
                    .build())
                .call();

        if (res.trade().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                             | Type                                                  | Required                                              | Description                                           | Example                                               |
| ----------------------------------------------------- | ----------------------------------------------------- | ----------------------------------------------------- | ----------------------------------------------------- | ----------------------------------------------------- |
| `accountId`                                           | *String*                                              | :heavy_check_mark:                                    | The account id.                                       | 01FAKEACCOUNT1TYKWEYRH8S2K                            |
| `tradeCreate`                                         | [TradeCreate](../../models/components/TradeCreate.md) | :heavy_check_mark:                                    | N/A                                                   |                                                       |

### Response

**[BookingCreateTradeResponse](../../models/operations/BookingCreateTradeResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 409          | application/json       |
| models/errors/Status   | 500, 503, 504          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getTrade

Gets a trade and all executions by trade_id.

 Upon successful submission, returns the trade details and all the execution by trade_id.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BookingGetTradeResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
                    .build())
            .build();

        BookingGetTradeResponse res = sdk.tradeBooking().getTrade()
                .accountId("01FAKEACCOUNT1TYKWEYRH8S2K")
                .tradeId("01FAKETRADEIDPROVIDEDFROMCREATETRADE")
                .call();

        if (res.trade().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                            | Type                                 | Required                             | Description                          | Example                              |
| ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ |
| `accountId`                          | *String*                             | :heavy_check_mark:                   | The account id.                      | 01FAKEACCOUNT1TYKWEYRH8S2K           |
| `tradeId`                            | *String*                             | :heavy_check_mark:                   | The trade id.                        | 01FAKETRADEIDPROVIDEDFROMCREATETRADE |

### Response

**[BookingGetTradeResponse](../../models/operations/BookingGetTradeResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503, 504          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## completeTrade

Complete a Trade by closing and generating any fees and withholdings if necessary. Once this endpoint returns an OK, the combination of details that generated the Trade (account_id, client_order_id, and the process_date) cannot be reused.

 Upon successful submission, returns completed trade details and all the executions. Trades that are left open will be automatically closed nightly before Ledger's EOD.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.CompleteTradeRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BookingCompleteTradeResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
                    .build())
            .build();

        BookingCompleteTradeResponse res = sdk.tradeBooking().completeTrade()
                .accountId("02HASWB2DTMRT3DAM45P56J2T2")
                .tradeId("01J0XX2KDN3M9QKFKRE2HYSCQM")
                .completeTradeRequestCreate(CompleteTradeRequestCreate.builder()
                    .name("accounts/02HASWB2DTMRT3DAM45P56J2T2/trades/01J0XX2KDN3M9QKFKRE2HYSCQM")
                    .build())
                .call();

        if (res.completeTradeResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                           | Type                                                                                | Required                                                                            | Description                                                                         | Example                                                                             |
| ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- |
| `accountId`                                                                         | *String*                                                                            | :heavy_check_mark:                                                                  | The account id.                                                                     | 02HASWB2DTMRT3DAM45P56J2T2                                                          |
| `tradeId`                                                                           | *String*                                                                            | :heavy_check_mark:                                                                  | The trade id.                                                                       | 01J0XX2KDN3M9QKFKRE2HYSCQM                                                          |
| `completeTradeRequestCreate`                                                        | [CompleteTradeRequestCreate](../../models/components/CompleteTradeRequestCreate.md) | :heavy_check_mark:                                                                  | N/A                                                                                 |                                                                                     |

### Response

**[BookingCompleteTradeResponse](../../models/operations/BookingCompleteTradeResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503, 504          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## cancelTrade

Cancel a trade and all the executions using the original trade_id. CancelTrade will either cancel everything, or nothing at all if a failure occurs.

 Upon successful submission, returns an empty response.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.CancelTradeRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BookingCancelTradeResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
                    .build())
            .build();

        BookingCancelTradeResponse res = sdk.tradeBooking().cancelTrade()
                .accountId("01FAKEACCOUNT1TYKWEYRH8S2K")
                .tradeId("01FAKETRADEIDPROVIDEDFROMCREATETRADE")
                .cancelTradeRequestCreate(CancelTradeRequestCreate.builder()
                    .name("accounts/01FAKEACCOUNT1TYKWEYRH8S2K/trades/01FAKETRADEIDPROVIDEDFROMCREATETRADE")
                    .build())
                .call();

        if (res.cancelTradeResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                       | Type                                                                            | Required                                                                        | Description                                                                     | Example                                                                         |
| ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- |
| `accountId`                                                                     | *String*                                                                        | :heavy_check_mark:                                                              | The account id.                                                                 | 01FAKEACCOUNT1TYKWEYRH8S2K                                                      |
| `tradeId`                                                                       | *String*                                                                        | :heavy_check_mark:                                                              | The trade id.                                                                   | 01FAKETRADEIDPROVIDEDFROMCREATETRADE                                            |
| `cancelTradeRequestCreate`                                                      | [CancelTradeRequestCreate](../../models/components/CancelTradeRequestCreate.md) | :heavy_check_mark:                                                              | N/A                                                                             |                                                                                 |

### Response

**[BookingCancelTradeResponse](../../models/operations/BookingCancelTradeResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503, 504          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## rebookTrade

Rebook a trade by the original trade_id. The entire original trade's executions are rebooked using the executions provided in the request. If applicable, fees and backup withholdings will be re-calculated.

 Upon successful submission, returns the rebooked trade details and all the executions.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.DecimalCreate;
import com.apexfintechsolutions.ascendsdk.models.components.ExecutionCreate;
import com.apexfintechsolutions.ascendsdk.models.components.RebookTradeRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.RouteType;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.TradeCreate;
import com.apexfintechsolutions.ascendsdk.models.components.TradeCreateBrokerCapacity;
import com.apexfintechsolutions.ascendsdk.models.components.TradeCreateIdentifierType;
import com.apexfintechsolutions.ascendsdk.models.components.TradeCreateSide;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BookingRebookTradeResponse;
import java.lang.Exception;
import java.time.OffsetDateTime;
import java.util.List;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
                    .build())
            .build();

        BookingRebookTradeResponse res = sdk.tradeBooking().rebookTrade()
                .accountId("02HASWB2DTMRT3DAM45P56J2T2")
                .tradeId("01J0XX2KDN3M9QKFKRE2HYSCQM")
                .rebookTradeRequestCreate(RebookTradeRequestCreate.builder()
                    .name("accounts/02HASWB2DTMRT3DAM45P56J2T2/trades/01J0XX2KDN3M9QKFKRE2HYSCQM")
                    .trade(TradeCreate.builder()
                        .accountId("02HASWB2DTMRT3DAM45P56J2T2")
                        .brokerCapacity(TradeCreateBrokerCapacity.AGENCY)
                        .clientOrderId("00be5285-0623-4560-8c58-f05af2c56ba0")
                        .executions(List.of(
                            ExecutionCreate.builder()
                                .executionTime(OffsetDateTime.parse("2024-07-17T12:00:00Z"))
                                .externalId("0H06HAP3A3Y")
                                .price(DecimalCreate.builder()
                                    .build())
                                .quantity(DecimalCreate.builder()
                                    .build())
                                .build(),
                            ExecutionCreate.builder()
                                .executionTime(OffsetDateTime.parse("2024-07-17T12:00:00Z"))
                                .externalId("0H06HAP3A3Y")
                                .price(DecimalCreate.builder()
                                    .build())
                                .quantity(DecimalCreate.builder()
                                    .build())
                                .build(),
                            ExecutionCreate.builder()
                                .executionTime(OffsetDateTime.parse("2024-07-17T12:00:00Z"))
                                .externalId("0H06HAP3A3Y")
                                .price(DecimalCreate.builder()
                                    .build())
                                .quantity(DecimalCreate.builder()
                                    .build())
                                .build()))
                        .identifier("AAPL")
                        .identifierType(TradeCreateIdentifierType.SYMBOL)
                        .routeType(RouteType.MNGD)
                        .side(TradeCreateSide.BUY)
                        .sourceApplication("Trading-App")
                        .build())
                    .build())
                .call();

        if (res.rebookTradeResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                       | Type                                                                            | Required                                                                        | Description                                                                     | Example                                                                         |
| ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- |
| `accountId`                                                                     | *String*                                                                        | :heavy_check_mark:                                                              | The account id.                                                                 | 02HASWB2DTMRT3DAM45P56J2T2                                                      |
| `tradeId`                                                                       | *String*                                                                        | :heavy_check_mark:                                                              | The trade id.                                                                   | 01J0XX2KDN3M9QKFKRE2HYSCQM                                                      |
| `rebookTradeRequestCreate`                                                      | [RebookTradeRequestCreate](../../models/components/RebookTradeRequestCreate.md) | :heavy_check_mark:                                                              | N/A                                                                             |                                                                                 |

### Response

**[BookingRebookTradeResponse](../../models/operations/BookingRebookTradeResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503, 504          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## createExecution

Create a new execution under an existing trade that is open.

 Upon successful submission, returns the created execution and its details.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.DecimalCreate;
import com.apexfintechsolutions.ascendsdk.models.components.ExecutionCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BookingCreateExecutionResponse;
import java.lang.Exception;
import java.time.OffsetDateTime;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
                    .build())
            .build();

        BookingCreateExecutionResponse res = sdk.tradeBooking().createExecution()
                .accountId("01FAKEACCOUNT1TYKWEYRH8S2K")
                .tradeId("01FAKETRADEIDPROVIDEDFROMCREATETRADE")
                .executionCreate(ExecutionCreate.builder()
                    .executionTime(OffsetDateTime.parse("2024-07-17T12:00:00Z"))
                    .externalId("0H06HAP3A3Y")
                    .price(DecimalCreate.builder()
                        .build())
                    .quantity(DecimalCreate.builder()
                        .build())
                    .build())
                .call();

        if (res.execution().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                     | Type                                                          | Required                                                      | Description                                                   | Example                                                       |
| ------------------------------------------------------------- | ------------------------------------------------------------- | ------------------------------------------------------------- | ------------------------------------------------------------- | ------------------------------------------------------------- |
| `accountId`                                                   | *String*                                                      | :heavy_check_mark:                                            | The account id.                                               | 01FAKEACCOUNT1TYKWEYRH8S2K                                    |
| `tradeId`                                                     | *String*                                                      | :heavy_check_mark:                                            | The trade id.                                                 | 01FAKETRADEIDPROVIDEDFROMCREATETRADE                          |
| `executionCreate`                                             | [ExecutionCreate](../../models/components/ExecutionCreate.md) | :heavy_check_mark:                                            | N/A                                                           |                                                               |

### Response

**[BookingCreateExecutionResponse](../../models/operations/BookingCreateExecutionResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/Status   | 500, 503, 504          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getExecution

Gets an execution by execution_id.

 Upon successful submission, returns the execution details by execution_id.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BookingGetExecutionResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
                    .build())
            .build();

        BookingGetExecutionResponse res = sdk.tradeBooking().getExecution()
                .accountId("01FAKEACCOUNT1TYKWEYRH8S2K")
                .tradeId("01FAKETRADEIDPROVIDEDFROMCREATETRADE")
                .executionId("01FAKEEXECUTONIDPROVIDEDFROMBOOKINGAPI")
                .call();

        if (res.execution().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                              | Type                                   | Required                               | Description                            | Example                                |
| -------------------------------------- | -------------------------------------- | -------------------------------------- | -------------------------------------- | -------------------------------------- |
| `accountId`                            | *String*                               | :heavy_check_mark:                     | The account id.                        | 01FAKEACCOUNT1TYKWEYRH8S2K             |
| `tradeId`                              | *String*                               | :heavy_check_mark:                     | The trade id.                          | 01FAKETRADEIDPROVIDEDFROMCREATETRADE   |
| `executionId`                          | *String*                               | :heavy_check_mark:                     | The execution id.                      | 01FAKEEXECUTONIDPROVIDEDFROMBOOKINGAPI |

### Response

**[BookingGetExecutionResponse](../../models/operations/BookingGetExecutionResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503, 504          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## cancelExecution

Cancel an execution using the original execution_id. If applicable, fees and backup withholdings will be re-calculated.

 Upon successful submission, returns the execution that was canceled.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.CancelExecutionRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BookingCancelExecutionResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
                    .build())
            .build();

        BookingCancelExecutionResponse res = sdk.tradeBooking().cancelExecution()
                .accountId("02HASWB2DTMRT3DAM45P56J2T2")
                .tradeId("01J0XX2KDN3M9QKFKRE2HYSCQM")
                .executionId("02G0XX2KDN3M9QKFKRE2HYSCMY")
                .cancelExecutionRequestCreate(CancelExecutionRequestCreate.builder()
                    .name("accounts/02HASWB2DTMRT3DAM45P56J2T2/trades/01J0XX2KDN3M9QKFKRE2HYSCQM/executions/02G0XX2KDN3M9QKFKRE2HYSCMY")
                    .build())
                .call();

        if (res.cancelExecutionResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                               | Type                                                                                    | Required                                                                                | Description                                                                             | Example                                                                                 |
| --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- |
| `accountId`                                                                             | *String*                                                                                | :heavy_check_mark:                                                                      | The account id.                                                                         | 02HASWB2DTMRT3DAM45P56J2T2                                                              |
| `tradeId`                                                                               | *String*                                                                                | :heavy_check_mark:                                                                      | The trade id.                                                                           | 01J0XX2KDN3M9QKFKRE2HYSCQM                                                              |
| `executionId`                                                                           | *String*                                                                                | :heavy_check_mark:                                                                      | The execution id.                                                                       | 02G0XX2KDN3M9QKFKRE2HYSCMY                                                              |
| `cancelExecutionRequestCreate`                                                          | [CancelExecutionRequestCreate](../../models/components/CancelExecutionRequestCreate.md) | :heavy_check_mark:                                                                      | N/A                                                                                     |                                                                                         |

### Response

**[BookingCancelExecutionResponse](../../models/operations/BookingCancelExecutionResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503, 504          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## rebookExecution

Rebook an execution by the original execution_id. If applicable, fees and backup withholdings will be re-calculated.

 Upon successful submission, returns the rebooked execution details.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.DecimalCreate;
import com.apexfintechsolutions.ascendsdk.models.components.ExecutionCreate;
import com.apexfintechsolutions.ascendsdk.models.components.RebookExecutionRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BookingRebookExecutionResponse;
import java.lang.Exception;
import java.time.OffsetDateTime;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
                    .build())
            .build();

        BookingRebookExecutionResponse res = sdk.tradeBooking().rebookExecution()
                .accountId("02HASWB2DTMRT3DAM45P56J2T2")
                .tradeId("01J0XX2KDN3M9QKFKRE2HYSCQM")
                .executionId("02G0XX2KDN3M9QKFKRE2HYSCMY")
                .rebookExecutionRequestCreate(RebookExecutionRequestCreate.builder()
                    .execution(ExecutionCreate.builder()
                        .executionTime(OffsetDateTime.parse("2024-07-17T12:00:00Z"))
                        .externalId("0H06HAP3A3Y")
                        .price(DecimalCreate.builder()
                            .build())
                        .quantity(DecimalCreate.builder()
                            .build())
                        .build())
                    .name("accounts/02HASWB2DTMRT3DAM45P56J2T2/trades/01J0XX2KDN3M9QKFKRE2HYSCQM/executions/02G0XX2KDN3M9QKFKRE2HYSCMY")
                    .build())
                .call();

        if (res.rebookExecutionResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                               | Type                                                                                    | Required                                                                                | Description                                                                             | Example                                                                                 |
| --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- |
| `accountId`                                                                             | *String*                                                                                | :heavy_check_mark:                                                                      | The account id.                                                                         | 02HASWB2DTMRT3DAM45P56J2T2                                                              |
| `tradeId`                                                                               | *String*                                                                                | :heavy_check_mark:                                                                      | The trade id.                                                                           | 01J0XX2KDN3M9QKFKRE2HYSCQM                                                              |
| `executionId`                                                                           | *String*                                                                                | :heavy_check_mark:                                                                      | The execution id.                                                                       | 02G0XX2KDN3M9QKFKRE2HYSCMY                                                              |
| `rebookExecutionRequestCreate`                                                          | [RebookExecutionRequestCreate](../../models/components/RebookExecutionRequestCreate.md) | :heavy_check_mark:                                                                      | N/A                                                                                     |                                                                                         |

### Response

**[BookingRebookExecutionResponse](../../models/operations/BookingRebookExecutionResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503, 504          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |