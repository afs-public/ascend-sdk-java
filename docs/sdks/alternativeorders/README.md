# AlternativeOrders
(*alternativeOrders()*)

## Overview

### Available Operations

* [createAlternativeOrder](#createalternativeorder) - Create Alternative Order
* [listAlternativeOrders](#listalternativeorders) - List Alternative Orders
* [getAlternativeOrder](#getalternativeorder) - Get Alternative Order
* [retrievePendingInvestorActions](#retrievependinginvestoractions) - Get Pending Investor Actions
* [settleAlternativeOrder](#settlealternativeorder) - Simulate Alternative Order Booking

## createAlternativeOrder

Creates an order for an alternative investment asset.

### Example Usage

<!-- UsageSnippet language="java" operationID="AlternativeOrders_CreateAlternativeOrder" method="post" path="/trading/v1/accounts/{account_id}/alternativeOrders" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AlternativeOrdersCreateAlternativeOrderResponse;
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

        AlternativeOrdersCreateAlternativeOrderResponse res = sdk.alternativeOrders().createAlternativeOrder()
                .accountId("01JR8YQT40WAQT8S95ZQGS1QN0")
                .alternativeOrderCreate(AlternativeOrderCreate.builder()
                    .clientOrderId("f5074670-1b25-439f-9b5c-702027660800")
                    .identifier("8395")
                    .identifierType(AlternativeOrderCreateIdentifierType.ASSET_ID)
                    .side(AlternativeOrderCreateSide.BUY)
                    .build())
                .call();

        if (res.alternativeOrder().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                   | Type                                                                        | Required                                                                    | Description                                                                 | Example                                                                     |
| --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- |
| `accountId`                                                                 | *String*                                                                    | :heavy_check_mark:                                                          | The account id.                                                             | 01JR8YQT40WAQT8S95ZQGS1QN0                                                  |
| `alternativeOrderCreate`                                                    | [AlternativeOrderCreate](../../models/components/AlternativeOrderCreate.md) | :heavy_check_mark:                                                          | N/A                                                                         |                                                                             |

### Response

**[AlternativeOrdersCreateAlternativeOrderResponse](../../models/operations/AlternativeOrdersCreateAlternativeOrderResponse.md)**

### Errors

| Error Type              | Status Code             | Content Type            |
| ----------------------- | ----------------------- | ----------------------- |
| models/errors/Status    | 400, 401, 403, 404, 409 | application/json        |
| models/errors/Status    | 500                     | application/json        |
| models/errors/SDKError  | 4XX, 5XX                | \*/\*                   |

## listAlternativeOrders

Retrieves a list of alternative investment orders for the specified account.

### Example Usage

<!-- UsageSnippet language="java" operationID="AlternativeOrders_ListAlternativeOrders" method="get" path="/trading/v1/accounts/{account_id}/alternativeOrders" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AlternativeOrdersListAlternativeOrdersResponse;
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

        AlternativeOrdersListAlternativeOrdersResponse res = sdk.alternativeOrders().listAlternativeOrders()
                .accountId("01JR8YQT40WAQT8S95ZQGS1QN0")
                .pageSize(100)
                .pageToken("cGFnZV90b2tlbgo=")
                .filter("side == 'BUY' && order_status == 'FILLED'")
                .call();

        if (res.listAlternativeOrdersResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                                                                          | Type                                                                                                                                                                                                                                                                               | Required                                                                                                                                                                                                                                                                           | Description                                                                                                                                                                                                                                                                        | Example                                                                                                                                                                                                                                                                            |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                                                                                                                                                                                        | *String*                                                                                                                                                                                                                                                                           | :heavy_check_mark:                                                                                                                                                                                                                                                                 | The account id.                                                                                                                                                                                                                                                                    | 01JR8YQT40WAQT8S95ZQGS1QN0                                                                                                                                                                                                                                                         |
| `pageSize`                                                                                                                                                                                                                                                                         | *Optional\<Integer>*                                                                                                                                                                                                                                                               | :heavy_minus_sign:                                                                                                                                                                                                                                                                 | The maximum number of orders to return. - Max value = 100 - Values above 100 will be coerced to 100. - If the specified value is greater than the number of orders, the service will return fewer than the specified value. - If unspecified, at most 100 orders will be returned. | 100                                                                                                                                                                                                                                                                                |
| `pageToken`                                                                                                                                                                                                                                                                        | *Optional\<String>*                                                                                                                                                                                                                                                                | :heavy_minus_sign:                                                                                                                                                                                                                                                                 | For pagination, provide the page token, received from a previous `ListAlternativeOrders` call, to retrieve the subsequent page. All other parameters provided to `ListAlternativeOrders` must match the request that provided the page token.                                      | cGFnZV90b2tlbgo=                                                                                                                                                                                                                                                                   |
| `filter`                                                                                                                                                                                                                                                                           | *Optional\<String>*                                                                                                                                                                                                                                                                | :heavy_minus_sign:                                                                                                                                                                                                                                                                 | A CEL string to filter results. All fields from the response can be used as filters.<br/><br/> See the [CEL Search](https://developer.apexclearing.com/apex-fintech-solutions/docs/cel-search) guide for syntax details and examples.                                              | side == 'BUY' && order_status == 'FILLED'                                                                                                                                                                                                                                          |

### Response

**[AlternativeOrdersListAlternativeOrdersResponse](../../models/operations/AlternativeOrdersListAlternativeOrdersResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403          | application/json       |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getAlternativeOrder

Retrieves the details for the specified alternative investment order.

### Example Usage

<!-- UsageSnippet language="java" operationID="AlternativeOrders_GetAlternativeOrder" method="get" path="/trading/v1/accounts/{account_id}/alternativeOrders/{alternativeOrder_id}" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AlternativeOrdersGetAlternativeOrderResponse;
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

        AlternativeOrdersGetAlternativeOrderResponse res = sdk.alternativeOrders().getAlternativeOrder()
                .accountId("01JR8YQT40WAQT8S95ZQGS1QN0")
                .alternativeOrderId("01H5TSDAD9MQ98B8KF36J3Q8P9")
                .call();

        if (res.alternativeOrder().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                  | Type                       | Required                   | Description                | Example                    |
| -------------------------- | -------------------------- | -------------------------- | -------------------------- | -------------------------- |
| `accountId`                | *String*                   | :heavy_check_mark:         | The account id.            | 01JR8YQT40WAQT8S95ZQGS1QN0 |
| `alternativeOrderId`       | *String*                   | :heavy_check_mark:         | The alternativeOrder id.   | 01H5TSDAD9MQ98B8KF36J3Q8P9 |

### Response

**[AlternativeOrdersGetAlternativeOrderResponse](../../models/operations/AlternativeOrdersGetAlternativeOrderResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 404     | application/json       |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## retrievePendingInvestorActions

Retrieves the links for any order documents that are awaiting signature and the `party_id` of the party responsible for signing them.

### Example Usage

<!-- UsageSnippet language="java" operationID="AlternativeOrders_RetrievePendingInvestorActions" method="get" path="/trading/v1/accounts/{account_id}/alternativeOrders/{alternativeOrder_id}:retrievePendingInvestorActions" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AlternativeOrdersRetrievePendingInvestorActionsResponse;
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

        AlternativeOrdersRetrievePendingInvestorActionsResponse res = sdk.alternativeOrders().retrievePendingInvestorActions()
                .accountId("01JR8YQT40WAQT8S95ZQGS1QN0")
                .alternativeOrderId("01H5TSDAD9MQ98B8KF36J3Q8P9")
                .call();

        if (res.retrievePendingInvestorActionsResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                  | Type                       | Required                   | Description                | Example                    |
| -------------------------- | -------------------------- | -------------------------- | -------------------------- | -------------------------- |
| `accountId`                | *String*                   | :heavy_check_mark:         | The account id.            | 01JR8YQT40WAQT8S95ZQGS1QN0 |
| `alternativeOrderId`       | *String*                   | :heavy_check_mark:         | The alternativeOrder id.   | 01H5TSDAD9MQ98B8KF36J3Q8P9 |

### Response

**[AlternativeOrdersRetrievePendingInvestorActionsResponse](../../models/operations/AlternativeOrdersRetrievePendingInvestorActionsResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403          | application/json       |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## settleAlternativeOrder

Simulates settlement process for an alternative order. For use in UAT environment only.

### Example Usage

<!-- UsageSnippet language="java" operationID="AlternativeOrders_SettleAlternativeOrder" method="post" path="/trading/v1/accounts/{account_id}/alternativeOrders/{alternativeOrder_id}:settle" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AlternativeOrdersSettleAlternativeOrderResponse;
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

        AlternativeOrdersSettleAlternativeOrderResponse res = sdk.alternativeOrders().settleAlternativeOrder()
                .accountId("01JR8YQT40WAQT8S95ZQGS1QN0")
                .alternativeOrderId("01H5TSDAD9MQ98B8KF36J3Q8P9")
                .settleAlternativeOrderRequestCreate(SettleAlternativeOrderRequestCreate.builder()
                    .name("accounts/01JR8YQT40WAQT8S95ZQGS1QN0/alternativeOrders/01H5TSDAD9MQ98B8KF36J3Q8P9")
                    .build())
                .call();

        if (res.alternativeOrder().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                             | Type                                                                                                  | Required                                                                                              | Description                                                                                           | Example                                                                                               |
| ----------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                           | *String*                                                                                              | :heavy_check_mark:                                                                                    | The account id.                                                                                       | 01JR8YQT40WAQT8S95ZQGS1QN0                                                                            |
| `alternativeOrderId`                                                                                  | *String*                                                                                              | :heavy_check_mark:                                                                                    | The alternativeOrder id.                                                                              | 01H5TSDAD9MQ98B8KF36J3Q8P9                                                                            |
| `settleAlternativeOrderRequestCreate`                                                                 | [SettleAlternativeOrderRequestCreate](../../models/components/SettleAlternativeOrderRequestCreate.md) | :heavy_check_mark:                                                                                    | N/A                                                                                                   |                                                                                                       |

### Response

**[AlternativeOrdersSettleAlternativeOrderResponse](../../models/operations/AlternativeOrdersSettleAlternativeOrderResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 404     | application/json       |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |