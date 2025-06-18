# CreateOrder
(*createOrder()*)

## Overview

### Available Operations

* [createOrder](#createorder) - Create Order
* [getOrder](#getorder) - Get Order
* [cancelOrder](#cancelorder) - Cancel Order

## createOrder

Creates a new order for equity or fixed income securities.

 Equity quantities may be for fractional shares, whole shares, or notional dollar amounts. Fixed income orders may be specified in face value currency amounts, with prices expressed in conventional "percentage of par" values.

 Upon successful submission, if the request is a duplicate, returns the existing order in its current state in the system. If the request is not a duplicate, returns the summary of the newly submitted order.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.AssetType;
import com.apexfintechsolutions.ascendsdk.models.components.DateCreate;
import com.apexfintechsolutions.ascendsdk.models.components.IdentifierType;
import com.apexfintechsolutions.ascendsdk.models.components.OrderCreate;
import com.apexfintechsolutions.ascendsdk.models.components.OrderType;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.components.Side;
import com.apexfintechsolutions.ascendsdk.models.components.TimeInForce;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.OrderServiceCreateOrderResponse;
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

        OrderServiceCreateOrderResponse res = sdk.createOrder().createOrder()
                .accountId("01HBRQ5BW6ZAY4BNWP4GWRD80X")
                .orderCreate(OrderCreate.builder()
                    .assetType(AssetType.EQUITY)
                    .clientOrderId("a6d5258b-6b23-478a-8145-98e79d60427a")
                    .identifier("SBUX")
                    .identifierType(IdentifierType.SYMBOL)
                    .orderDate(DateCreate.builder()
                        .build())
                    .orderType(OrderType.MARKET)
                    .side(Side.BUY)
                    .timeInForce(TimeInForce.DAY)
                    .build())
                .call();

        if (res.order().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                             | Type                                                  | Required                                              | Description                                           | Example                                               |
| ----------------------------------------------------- | ----------------------------------------------------- | ----------------------------------------------------- | ----------------------------------------------------- | ----------------------------------------------------- |
| `accountId`                                           | *String*                                              | :heavy_check_mark:                                    | The account id.                                       | 01HBRQ5BW6ZAY4BNWP4GWRD80X                            |
| `orderCreate`                                         | [OrderCreate](../../models/components/OrderCreate.md) | :heavy_check_mark:                                    | N/A                                                   |                                                       |

### Response

**[OrderServiceCreateOrderResponse](../../models/operations/OrderServiceCreateOrderResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 409     | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getOrder

Gets an order by order ID.

 Upon successful submission, returns the details of the queried order.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.OrderServiceGetOrderResponse;
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

        OrderServiceGetOrderResponse res = sdk.createOrder().getOrder()
                .accountId("01HBRQ5BW6ZAY4BNWP4GWRD80X")
                .orderId("ebb0c9b5-2c74-45c9-a4ab-40596b778706")
                .call();

        if (res.order().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                            | Type                                 | Required                             | Description                          | Example                              |
| ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ |
| `accountId`                          | *String*                             | :heavy_check_mark:                   | The account id.                      | 01HBRQ5BW6ZAY4BNWP4GWRD80X           |
| `orderId`                            | *String*                             | :heavy_check_mark:                   | The order id.                        | ebb0c9b5-2c74-45c9-a4ab-40596b778706 |

### Response

**[OrderServiceGetOrderResponse](../../models/operations/OrderServiceGetOrderResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 404     | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## cancelOrder

Submits an order cancellation request by order ID. Confirmation of order cancellation requests are provided through asynchronous events.

 Upon successful submission, returns the details of the order pending cancellation.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.CancelOrderRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.OrderServiceCancelOrderResponse;
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

        OrderServiceCancelOrderResponse res = sdk.createOrder().cancelOrder()
                .accountId("01HBRQ5BW6ZAY4BNWP4GWRD80X")
                .orderId("ebb0c9b5-2c74-45c9-a4ab-40596b778706")
                .cancelOrderRequestCreate(CancelOrderRequestCreate.builder()
                    .name("accounts/01HBRQ5BW6ZAY4BNWP4GWRD80X/orders/ebb0c9b5-2c74-45c9-a4ab-40596b778706")
                    .build())
                .call();

        if (res.order().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                       | Type                                                                            | Required                                                                        | Description                                                                     | Example                                                                         |
| ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- |
| `accountId`                                                                     | *String*                                                                        | :heavy_check_mark:                                                              | The account id.                                                                 | 01HBRQ5BW6ZAY4BNWP4GWRD80X                                                      |
| `orderId`                                                                       | *String*                                                                        | :heavy_check_mark:                                                              | The order id.                                                                   | ebb0c9b5-2c74-45c9-a4ab-40596b778706                                            |
| `cancelOrderRequestCreate`                                                      | [CancelOrderRequestCreate](../../models/components/CancelOrderRequestCreate.md) | :heavy_check_mark:                                                              | N/A                                                                             |                                                                                 |

### Response

**[OrderServiceCancelOrderResponse](../../models/operations/OrderServiceCancelOrderResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 404     | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |