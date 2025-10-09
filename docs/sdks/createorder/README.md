# CreateOrder
(*createOrder()*)

## Overview

### Available Operations

* [createOrder](#createorder) - Create Order
* [getOrder](#getorder) - Get Order
* [cancelOrder](#cancelorder) - Cancel Order
* [setExtraReportingData](#setextrareportingdata) - Set Extra Reporting Data
* [listCorrespondentOrders](#listcorrespondentorders) - List Correspondent Orders

## createOrder

Creates a new order for equity or fixed income securities.

 Equity quantities may be for fractional shares, whole shares, or notional dollar amounts. Fixed income orders may be specified in face value currency amounts, with prices expressed in conventional "percentage of par" values.

 Upon successful submission, if the request is a duplicate, returns the existing order in its current state in the system. If the request is not a duplicate, returns the summary of the newly submitted order.

### Example Usage

<!-- UsageSnippet language="java" operationID="OrderService_CreateOrder" method="post" path="/trading/v1/accounts/{account_id}/orders" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
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

<!-- UsageSnippet language="java" operationID="OrderService_GetOrder" method="get" path="/trading/v1/accounts/{account_id}/orders/{order_id}" -->
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

<!-- UsageSnippet language="java" operationID="OrderService_CancelOrder" method="post" path="/trading/v1/accounts/{account_id}/orders/{order_id}:cancel" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
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

## setExtraReportingData

Sets extra reporting data to an existing order. Any SetExtraReportingDataRequest must include the name of the order and the cancel_confirmed_time

### Example Usage

<!-- UsageSnippet language="java" operationID="OrderService_SetExtraReportingData" method="post" path="/trading/v1/accounts/{account_id}/orders/{order_id}:setExtraReportingData" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.OrderServiceSetExtraReportingDataResponse;
import java.lang.Exception;
import java.time.OffsetDateTime;

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

        OrderServiceSetExtraReportingDataResponse res = sdk.createOrder().setExtraReportingData()
                .accountId("01HBRQ5BW6ZAY4BNWP4GWRD80X")
                .orderId("ebb0c9b5-2c74-45c9-a4ab-40596b778706")
                .setExtraReportingDataRequestCreate(SetExtraReportingDataRequestCreate.builder()
                    .cancelConfirmedTime(OffsetDateTime.parse("2025-12-13T15:28:17.262732Z"))
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

| Parameter                                                                                           | Type                                                                                                | Required                                                                                            | Description                                                                                         | Example                                                                                             |
| --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                         | *String*                                                                                            | :heavy_check_mark:                                                                                  | The account id.                                                                                     | 01HBRQ5BW6ZAY4BNWP4GWRD80X                                                                          |
| `orderId`                                                                                           | *String*                                                                                            | :heavy_check_mark:                                                                                  | The order id.                                                                                       | ebb0c9b5-2c74-45c9-a4ab-40596b778706                                                                |
| `setExtraReportingDataRequestCreate`                                                                | [SetExtraReportingDataRequestCreate](../../models/components/SetExtraReportingDataRequestCreate.md) | :heavy_check_mark:                                                                                  | N/A                                                                                                 |                                                                                                     |

### Response

**[OrderServiceSetExtraReportingDataResponse](../../models/operations/OrderServiceSetExtraReportingDataResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 404     | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## listCorrespondentOrders

Lists orders matching the specified filter criteria. Results are paginated and sorted in the reverse order of their creation.

### Example Usage

<!-- UsageSnippet language="java" operationID="OrderService_ListCorrespondentOrders" method="get" path="/trading/v1/correspondents/{correspondent_id}/orders" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.OrderServiceListCorrespondentOrdersResponse;
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

        OrderServiceListCorrespondentOrdersResponse res = sdk.createOrder().listCorrespondentOrders()
                .correspondentId("01HBRQ5BW6ZAY4BNWP4GWRD80X")
                .filter("open && order_date >= date('2025-05-10')")
                .pageSize(50)
                .pageToken("CiAKGjBpNDd2Nmp2Zml2cXRwYjBpOXA")
                .call();

        if (res.listCorrespondentOrdersResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          | Type                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               | Required                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        | Example                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `correspondentId`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  | *String*                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           | :heavy_check_mark:                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 | The correspondent id.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              | 01HBRQ5BW6ZAY4BNWP4GWRD80X                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |
| `filter`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           | *Optional\<String>*                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                | :heavy_minus_sign:                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 | CEL filter string expressing what orders should be listed. The only properties available for filtering are the boolean `open` and `order_date`. Each of these represent fields on the Orders object, and more details about each can be found attached to the properties.<br/><br/> If `open` is not provided, both "open" and "not open" orders will be returned. All `order_date` searches are limited to orders within the most recent 365 days. If no `order_date` is specified, the default will search between now and 365 days ago. | open && order_date >= date('2025-05-10')                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| `pageSize`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         | *Optional\<Integer>*                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               | :heavy_minus_sign:                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 | The number of records to return in a single page. The maximum page size is 100. If a value is not provided, the default of 100 will be used. If a value less than one, or greater than the maximum, is provided, the default value will be used.                                                                                                                                                                                                                                                                                   | 50                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| `pageToken`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        | *Optional\<String>*                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                | :heavy_minus_sign:                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 | The token for the next page of content to fetch. When paginating, all other parameters provided to `ListOrders` must match the call that provided the page token.                                                                                                                                                                                                                                                                                                                                                                  | CiAKGjBpNDd2Nmp2Zml2cXRwYjBpOXA                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |

### Response

**[OrderServiceListCorrespondentOrdersResponse](../../models/operations/OrderServiceListCorrespondentOrdersResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |