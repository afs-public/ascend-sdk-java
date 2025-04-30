# TradeAllocation
(*tradeAllocation()*)

## Overview

### Available Operations

* [createTradeAllocation](#createtradeallocation) - Create Trade Allocation
* [getTradeAllocation](#gettradeallocation) - Get Trade Allocation
* [cancelTradeAllocation](#canceltradeallocation) - Cancel Trade Allocation
* [rebookTradeAllocation](#rebooktradeallocation) - Rebook Trade Allocation

## createTradeAllocation

Creates a new trade allocation. These are used to allocate or distribute positions between Apex accounts.

 Upon success, returns the created trade allocation and its enriched details.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.DecimalCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ToSide;
import com.apexfintechsolutions.ascendsdk.models.components.TradeAllocationCreate;
import com.apexfintechsolutions.ascendsdk.models.components.TradeAllocationCreateBrokerCapacity;
import com.apexfintechsolutions.ascendsdk.models.components.TradeAllocationCreateIdentifierType;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BookingCreateTradeAllocationResponse;
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

        BookingCreateTradeAllocationResponse res = sdk.tradeAllocation().createTradeAllocation()
                .accountId("01FAKEACCOUNT1TYKWEYRH8S2K")
                .requestId("8a0d35c0-428c-439e-9b03-b611530fe06f")
                .tradeAllocationCreate(TradeAllocationCreate.builder()
                    .brokerCapacity(TradeAllocationCreateBrokerCapacity.AGENCY)
                    .executionTime(OffsetDateTime.parse("2024-07-17T12:00:00Z"))
                    .fromAccountId("01HASWB2DTMRT3DAM45P56J2H3")
                    .identifier("AAPL")
                    .identifierType(TradeAllocationCreateIdentifierType.SYMBOL)
                    .price(DecimalCreate.builder()
                        .build())
                    .quantity(DecimalCreate.builder()
                        .build())
                    .sourceApplication("Trading-App")
                    .toAccountId("02HASWB2DTMRT3DAM45P56J2T2")
                    .toSide(ToSide.BUY)
                    .build())
                .call();

        if (res.tradeAllocation().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                   | Type                                                                                                                        | Required                                                                                                                    | Description                                                                                                                 | Example                                                                                                                     |
| --------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                                 | *String*                                                                                                                    | :heavy_check_mark:                                                                                                          | The account id.                                                                                                             | 01FAKEACCOUNT1TYKWEYRH8S2K                                                                                                  |
| `requestId`                                                                                                                 | *Optional\<String>*                                                                                                         | :heavy_minus_sign:                                                                                                          | A globally unique UUID that is specific to the request. This id is used to prevent duplicate requests from being processed. | 8a0d35c0-428c-439e-9b03-b611530fe06f                                                                                        |
| `tradeAllocationCreate`                                                                                                     | [TradeAllocationCreate](../../models/components/TradeAllocationCreate.md)                                                   | :heavy_check_mark:                                                                                                          | N/A                                                                                                                         |                                                                                                                             |

### Response

**[BookingCreateTradeAllocationResponse](../../models/operations/BookingCreateTradeAllocationResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404, 409     | application/json       |
| models/errors/Status   | 500, 503, 504          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getTradeAllocation

Retrieves a trade allocation and its details.

 Upon successful submission, returns the trade allocation details.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BookingGetTradeAllocationResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
                    .build())
            .build();

        BookingGetTradeAllocationResponse res = sdk.tradeAllocation().getTradeAllocation()
                .accountId("02HASWB2DTMRT3DAM45P56J2T2")
                .tradeAllocationId("01J0XX2KDN3M9QKFKRE2HYSCQM")
                .call();

        if (res.tradeAllocation().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                  | Type                       | Required                   | Description                | Example                    |
| -------------------------- | -------------------------- | -------------------------- | -------------------------- | -------------------------- |
| `accountId`                | *String*                   | :heavy_check_mark:         | The account id.            | 02HASWB2DTMRT3DAM45P56J2T2 |
| `tradeAllocationId`        | *String*                   | :heavy_check_mark:         | The tradeAllocation id.    | 01J0XX2KDN3M9QKFKRE2HYSCQM |

### Response

**[BookingGetTradeAllocationResponse](../../models/operations/BookingGetTradeAllocationResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503, 504          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## cancelTradeAllocation

Cancel a trade allocation using the original trade_allocation_id.

 Upon successful submission, returns an empty response. CancelTradeAllocation will either cancel everything, or nothing at all if a failure occurs.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.CancelTradeAllocationRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BookingCancelTradeAllocationResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
                    .build())
            .build();

        BookingCancelTradeAllocationResponse res = sdk.tradeAllocation().cancelTradeAllocation()
                .accountId("02HASWB2DTMRT3DAM45P56J2T2")
                .tradeAllocationId("01J0XX2KDN3M9QKFKRE2HYSCQM")
                .cancelTradeAllocationRequestCreate(CancelTradeAllocationRequestCreate.builder()
                    .name("accounts/02HASWB2DTMRT3DAM45P56J2T2/tradeAllocations/01J0XX2KDN3M9QKFKRE2HYSCQM")
                    .build())
                .call();

        if (res.cancelTradeAllocationResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                           | Type                                                                                                | Required                                                                                            | Description                                                                                         | Example                                                                                             |
| --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                         | *String*                                                                                            | :heavy_check_mark:                                                                                  | The account id.                                                                                     | 02HASWB2DTMRT3DAM45P56J2T2                                                                          |
| `tradeAllocationId`                                                                                 | *String*                                                                                            | :heavy_check_mark:                                                                                  | The tradeAllocation id.                                                                             | 01J0XX2KDN3M9QKFKRE2HYSCQM                                                                          |
| `cancelTradeAllocationRequestCreate`                                                                | [CancelTradeAllocationRequestCreate](../../models/components/CancelTradeAllocationRequestCreate.md) | :heavy_check_mark:                                                                                  | N/A                                                                                                 |                                                                                                     |

### Response

**[BookingCancelTradeAllocationResponse](../../models/operations/BookingCancelTradeAllocationResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503, 504          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## rebookTradeAllocation

Rebook a trade allocation by the original trade_allocation_id. The allocation is rebooked by canceling the original allocation and creating a new one with the provided details.

 Upon successful submission, returns both the original and new allocation, as separate resources.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.DecimalCreate;
import com.apexfintechsolutions.ascendsdk.models.components.RebookTradeAllocationRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ToSide;
import com.apexfintechsolutions.ascendsdk.models.components.TradeAllocationCreate;
import com.apexfintechsolutions.ascendsdk.models.components.TradeAllocationCreateBrokerCapacity;
import com.apexfintechsolutions.ascendsdk.models.components.TradeAllocationCreateIdentifierType;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BookingRebookTradeAllocationResponse;
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

        BookingRebookTradeAllocationResponse res = sdk.tradeAllocation().rebookTradeAllocation()
                .accountId("02HASWB2DTMRT3DAM45P56J2T2")
                .tradeAllocationId("01J0XX2KDN3M9QKFKRE2HYSCQM")
                .rebookTradeAllocationRequestCreate(RebookTradeAllocationRequestCreate.builder()
                    .name("accounts/02HASWB2DTMRT3DAM45P56J2T2/tradeAllocations/01J0XX2KDN3M9QKFKRE2HYSCQM")
                    .requestId("8a0d35c0-428c-439e-9b03-b611530fe06f")
                    .tradeAllocation(TradeAllocationCreate.builder()
                        .brokerCapacity(TradeAllocationCreateBrokerCapacity.AGENCY)
                        .executionTime(OffsetDateTime.parse("2024-07-17T12:00:00Z"))
                        .fromAccountId("01HASWB2DTMRT3DAM45P56J2H3")
                        .identifier("AAPL")
                        .identifierType(TradeAllocationCreateIdentifierType.SYMBOL)
                        .price(DecimalCreate.builder()
                            .build())
                        .quantity(DecimalCreate.builder()
                            .build())
                        .sourceApplication("Trading-App")
                        .toAccountId("02HASWB2DTMRT3DAM45P56J2T2")
                        .toSide(ToSide.BUY)
                        .build())
                    .build())
                .call();

        if (res.rebookTradeAllocationResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                           | Type                                                                                                | Required                                                                                            | Description                                                                                         | Example                                                                                             |
| --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                         | *String*                                                                                            | :heavy_check_mark:                                                                                  | The account id.                                                                                     | 02HASWB2DTMRT3DAM45P56J2T2                                                                          |
| `tradeAllocationId`                                                                                 | *String*                                                                                            | :heavy_check_mark:                                                                                  | The tradeAllocation id.                                                                             | 01J0XX2KDN3M9QKFKRE2HYSCQM                                                                          |
| `rebookTradeAllocationRequestCreate`                                                                | [RebookTradeAllocationRequestCreate](../../models/components/RebookTradeAllocationRequestCreate.md) | :heavy_check_mark:                                                                                  | N/A                                                                                                 |                                                                                                     |

### Response

**[BookingRebookTradeAllocationResponse](../../models/operations/BookingRebookTradeAllocationResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503, 504          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |