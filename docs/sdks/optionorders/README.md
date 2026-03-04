# OptionOrders
(*optionOrders()*)

## Overview

### Available Operations

* [createOptionOrder](#createoptionorder) - Create Option Order
* [getOptionOrder](#getoptionorder) - Get Option Order
* [cancelOptionOrder](#canceloptionorder) - Cancel Option Order

## createOptionOrder

Creates a new option order.

 Currently only single-leg option orders are supported, but the data structures will support multi-leg orders in the future. The single-leg constraint will be repeated in this documentation, but validation rules related to the initial (future) multi-leg support are also documented.

 Upon successful submission, if the request is a duplicate, returns the existing order in its current state in the system. If the request is not a duplicate, returns the summary of the newly submitted order.

### Example Usage

<!-- UsageSnippet language="java" operationID="OptionOrderService_CreateOptionOrder" method="post" path="/trading/v1/accounts/{account_id}/optionOrders" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.OptionOrderServiceCreateOptionOrderResponse;
import java.lang.Exception;
import java.util.List;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .apiKey("ABCDEFGHIJ0123456789abcdefghij0123456789")
                    .serviceAccountCreds(ServiceAccountCreds.builder()
                        .privateKey("-----BEGIN PRIVATE KEY--{OMITTED FOR BREVITY}")
                        .name("FinFirm")
                        .organization("correspondents/00000000-0000-0000-0000-000000000000")
                        .type("serviceAccount")
                        .build())
                    .build())
            .build();

        OptionOrderServiceCreateOptionOrderResponse res = sdk.optionOrders().createOptionOrder()
                .accountId("01HBRQ5BW6ZAY4BNWP4GWRD80X")
                .optionOrderCreate(OptionOrderCreate.builder()
                    .brokerCapacity(OptionOrderCreateBrokerCapacity.AGENCY)
                    .clientOrderId("a6d5258b-6b23-478a-8145-98e79d60427a")
                    .currencyCode("USD")
                    .legs(List.of())
                    .limitPrice(DecimalCreate.builder()
                        .build())
                    .orderDate(DateCreate.builder()
                        .build())
                    .orderType(OptionOrderCreateOrderType.LIMIT)
                    .priceDirection(PriceDirection.CREDIT)
                    .quantity(DecimalCreate.builder()
                        .build())
                    .timeInForce(OptionOrderCreateTimeInForce.DAY)
                    .build())
                .call();

        if (res.optionOrder().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                         | Type                                                              | Required                                                          | Description                                                       | Example                                                           |
| ----------------------------------------------------------------- | ----------------------------------------------------------------- | ----------------------------------------------------------------- | ----------------------------------------------------------------- | ----------------------------------------------------------------- |
| `accountId`                                                       | *String*                                                          | :heavy_check_mark:                                                | The account id.                                                   | 01HBRQ5BW6ZAY4BNWP4GWRD80X                                        |
| `optionOrderCreate`                                               | [OptionOrderCreate](../../models/components/OptionOrderCreate.md) | :heavy_check_mark:                                                | N/A                                                               |                                                                   |

### Response

**[OptionOrderServiceCreateOptionOrderResponse](../../models/operations/OptionOrderServiceCreateOptionOrderResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 409     | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getOptionOrder

Gets an option order by option order ID.

 Upon successful submission, returns the details of the queried order.

### Example Usage

<!-- UsageSnippet language="java" operationID="OptionOrderService_GetOptionOrder" method="get" path="/trading/v1/accounts/{account_id}/optionOrders/{optionOrder_id}" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.OptionOrderServiceGetOptionOrderResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .apiKey("ABCDEFGHIJ0123456789abcdefghij0123456789")
                    .serviceAccountCreds(ServiceAccountCreds.builder()
                        .privateKey("-----BEGIN PRIVATE KEY--{OMITTED FOR BREVITY}")
                        .name("FinFirm")
                        .organization("correspondents/00000000-0000-0000-0000-000000000000")
                        .type("serviceAccount")
                        .build())
                    .build())
            .build();

        OptionOrderServiceGetOptionOrderResponse res = sdk.optionOrders().getOptionOrder()
                .accountId("01HBRQ5BW6ZAY4BNWP4GWRD80X")
                .optionOrderId("ebb0c9b5-2c74-45c9-a4ab-40596b778706")
                .call();

        if (res.optionOrder().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                            | Type                                 | Required                             | Description                          | Example                              |
| ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ |
| `accountId`                          | *String*                             | :heavy_check_mark:                   | The account id.                      | 01HBRQ5BW6ZAY4BNWP4GWRD80X           |
| `optionOrderId`                      | *String*                             | :heavy_check_mark:                   | The optionOrder id.                  | ebb0c9b5-2c74-45c9-a4ab-40596b778706 |

### Response

**[OptionOrderServiceGetOptionOrderResponse](../../models/operations/OptionOrderServiceGetOptionOrderResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 404     | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## cancelOptionOrder

Submits an order cancellation request by option order ID. Confirmation of order cancellation requests are provided through asynchronous events.

 Upon successful submission, returns the details of the order pending cancellation.

### Example Usage

<!-- UsageSnippet language="java" operationID="OptionOrderService_CancelOptionOrder" method="post" path="/trading/v1/accounts/{account_id}/optionOrders/{optionOrder_id}:cancel" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.OptionOrderServiceCancelOptionOrderResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .apiKey("ABCDEFGHIJ0123456789abcdefghij0123456789")
                    .serviceAccountCreds(ServiceAccountCreds.builder()
                        .privateKey("-----BEGIN PRIVATE KEY--{OMITTED FOR BREVITY}")
                        .name("FinFirm")
                        .organization("correspondents/00000000-0000-0000-0000-000000000000")
                        .type("serviceAccount")
                        .build())
                    .build())
            .build();

        OptionOrderServiceCancelOptionOrderResponse res = sdk.optionOrders().cancelOptionOrder()
                .accountId("01HBRQ5BW6ZAY4BNWP4GWRD80X")
                .optionOrderId("ebb0c9b5-2c74-45c9-a4ab-40596b778706")
                .cancelOptionOrderRequestCreate(CancelOptionOrderRequestCreate.builder()
                    .name("accounts/01HBRQ5BW6ZAY4BNWP4GWRD80X/optionOrders/ebb0c9b5-2c74-45c9-a4ab-40596b778706")
                    .build())
                .call();

        if (res.optionOrder().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                   | Type                                                                                        | Required                                                                                    | Description                                                                                 | Example                                                                                     |
| ------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------- |
| `accountId`                                                                                 | *String*                                                                                    | :heavy_check_mark:                                                                          | The account id.                                                                             | 01HBRQ5BW6ZAY4BNWP4GWRD80X                                                                  |
| `optionOrderId`                                                                             | *String*                                                                                    | :heavy_check_mark:                                                                          | The optionOrder id.                                                                         | ebb0c9b5-2c74-45c9-a4ab-40596b778706                                                        |
| `cancelOptionOrderRequestCreate`                                                            | [CancelOptionOrderRequestCreate](../../models/components/CancelOptionOrderRequestCreate.md) | :heavy_check_mark:                                                                          | N/A                                                                                         |                                                                                             |

### Response

**[OptionOrderServiceCancelOptionOrderResponse](../../models/operations/OptionOrderServiceCancelOptionOrderResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 404     | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |