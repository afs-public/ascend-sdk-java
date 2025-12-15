# BasketOrders
(*basketOrders()*)

## Overview

### Available Operations

* [createBasket](#createbasket) - Create Basket
* [addOrders](#addorders) - Add Orders
* [getBasket](#getbasket) - Get Basket
* [submitBasket](#submitbasket) - Submit Basket
* [listBasketOrders](#listbasketorders) - List Basket Orders
* [listCompressedOrders](#listcompressedorders) - List Compressed Orders
* [removeOrders](#removeorders) - Remove Basket Orders
* [setExtraReportingData](#setextrareportingdata) - Set Extra Reporting Data

## createBasket

Creates an empty basket

 Upon successful submission, if the request is a duplicate, returns the existing basket in its current state in the system. If the request is not a duplicate, returns the summary of the newly created basket.

### Example Usage

<!-- UsageSnippet language="java" operationID="BasketOrdersService_CreateBasket" method="post" path="/baskettrading/v1/correspondents/{correspondent_id}/baskets" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BasketOrdersServiceCreateBasketResponse;
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

        BasketOrdersServiceCreateBasketResponse res = sdk.basketOrders().createBasket()
                .correspondentId("01HPMZZM6RKMVZA1JQ63RQKJRP")
                .basketCreate(BasketCreate.builder()
                    .clientBasketId("39347a0d-860b-48e8-a04d-511133f057e3")
                    .correspondentId("01HPMZZM6RKMVZA1JQ63RQKJRP")
                    .build())
                .call();

        if (res.basket().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                               | Type                                                    | Required                                                | Description                                             | Example                                                 |
| ------------------------------------------------------- | ------------------------------------------------------- | ------------------------------------------------------- | ------------------------------------------------------- | ------------------------------------------------------- |
| `correspondentId`                                       | *String*                                                | :heavy_check_mark:                                      | The correspondent id.                                   | 01HPMZZM6RKMVZA1JQ63RQKJRP                              |
| `basketCreate`                                          | [BasketCreate](../../models/components/BasketCreate.md) | :heavy_check_mark:                                      | N/A                                                     |                                                         |

### Response

**[BasketOrdersServiceCreateBasketResponse](../../models/operations/BasketOrdersServiceCreateBasketResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 409     | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## addOrders

Adds a list of basket orders to a basket

 Upon successful submission, returns the basket with a new total count of orders within the basket

### Example Usage

<!-- UsageSnippet language="java" operationID="BasketOrdersService_AddOrders" method="post" path="/baskettrading/v1/correspondents/{correspondent_id}/baskets/{basket_id}:addOrders" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BasketOrdersServiceAddOrdersResponse;
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

        BasketOrdersServiceAddOrdersResponse res = sdk.basketOrders().addOrders()
                .correspondentId("01HPMZZM6RKMVZA1JQ63RQKJRP")
                .basketId("fffd326-72fa-4d2b-bd1f-45384fe5d521")
                .addOrdersRequestCreate(AddOrdersRequestCreate.builder()
                    .basketOrders(List.of())
                    .name("correspondents/01HPMZZM6RKMVZA1JQ63RQKJRP/baskets/fffd326-72fa-4d2b-bd1f-45384fe5d521")
                    .build())
                .call();

        if (res.basket().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                   | Type                                                                        | Required                                                                    | Description                                                                 | Example                                                                     |
| --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- |
| `correspondentId`                                                           | *String*                                                                    | :heavy_check_mark:                                                          | The correspondent id.                                                       | 01HPMZZM6RKMVZA1JQ63RQKJRP                                                  |
| `basketId`                                                                  | *String*                                                                    | :heavy_check_mark:                                                          | The basket id.                                                              | fffd326-72fa-4d2b-bd1f-45384fe5d521                                         |
| `addOrdersRequestCreate`                                                    | [AddOrdersRequestCreate](../../models/components/AddOrdersRequestCreate.md) | :heavy_check_mark:                                                          | N/A                                                                         |                                                                             |

### Response

**[BasketOrdersServiceAddOrdersResponse](../../models/operations/BasketOrdersServiceAddOrdersResponse.md)**

### Errors

| Error Type              | Status Code             | Content Type            |
| ----------------------- | ----------------------- | ----------------------- |
| models/errors/Status    | 400, 401, 403, 404, 409 | application/json        |
| models/errors/Status    | 500, 503                | application/json        |
| models/errors/SDKError  | 4XX, 5XX                | \*/\*                   |

## getBasket

Gets a basket by basket ID.

 Upon successful submission, returns the details of the queried basket

### Example Usage

<!-- UsageSnippet language="java" operationID="BasketOrdersService_GetBasket" method="get" path="/baskettrading/v1/correspondents/{correspondent_id}/baskets/{basket_id}" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BasketOrdersServiceGetBasketResponse;
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

        BasketOrdersServiceGetBasketResponse res = sdk.basketOrders().getBasket()
                .correspondentId("01HPMZZM6RKMVZA1JQ63RQKJRP")
                .basketId("fffd326-72fa-4d2b-bd1f-45384fe5d521")
                .call();

        if (res.basket().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                           | Type                                | Required                            | Description                         | Example                             |
| ----------------------------------- | ----------------------------------- | ----------------------------------- | ----------------------------------- | ----------------------------------- |
| `correspondentId`                   | *String*                            | :heavy_check_mark:                  | The correspondent id.               | 01HPMZZM6RKMVZA1JQ63RQKJRP          |
| `basketId`                          | *String*                            | :heavy_check_mark:                  | The basket id.                      | fffd326-72fa-4d2b-bd1f-45384fe5d521 |

### Response

**[BasketOrdersServiceGetBasketResponse](../../models/operations/BasketOrdersServiceGetBasketResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 404     | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## submitBasket

Submits a basket for execution in the market

 Upon successful submission, if the request is a duplicate, returns the existing basket in its current state in the system. If the request is not a duplicate, returns the summary of the newly submitted basket in a SUBMITTED state

### Example Usage

<!-- UsageSnippet language="java" operationID="BasketOrdersService_SubmitBasket" method="post" path="/baskettrading/v1/correspondents/{correspondent_id}/baskets/{basket_id}:submit" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BasketOrdersServiceSubmitBasketResponse;
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

        BasketOrdersServiceSubmitBasketResponse res = sdk.basketOrders().submitBasket()
                .correspondentId("01HPMZZM6RKMVZA1JQ63RQKJRP")
                .basketId("fffd326-72fa-4d2b-bd1f-45384fe5d521")
                .submitBasketRequestCreate(SubmitBasketRequestCreate.builder()
                    .name("correspondents/01HPMZZM6RKMVZA1JQ63RQKJRP/baskets/fffd326-72fa-4d2b-bd1f-45384fe5d521")
                    .build())
                .call();

        if (res.basket().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                         | Type                                                                              | Required                                                                          | Description                                                                       | Example                                                                           |
| --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- |
| `correspondentId`                                                                 | *String*                                                                          | :heavy_check_mark:                                                                | The correspondent id.                                                             | 01HPMZZM6RKMVZA1JQ63RQKJRP                                                        |
| `basketId`                                                                        | *String*                                                                          | :heavy_check_mark:                                                                | The basket id.                                                                    | fffd326-72fa-4d2b-bd1f-45384fe5d521                                               |
| `submitBasketRequestCreate`                                                       | [SubmitBasketRequestCreate](../../models/components/SubmitBasketRequestCreate.md) | :heavy_check_mark:                                                                | N/A                                                                               |                                                                                   |

### Response

**[BasketOrdersServiceSubmitBasketResponse](../../models/operations/BasketOrdersServiceSubmitBasketResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 404     | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## listBasketOrders

Gets a list of basket orders within a basket.

 Upon successful submission, returns a list of basket orders for the basket. If the list of basket orders becomes too large, a token is returned to retrieve the next page of basket orders.

### Example Usage

<!-- UsageSnippet language="java" operationID="BasketOrdersService_ListBasketOrders" method="get" path="/baskettrading/v1/correspondents/{correspondent_id}/baskets/{basket_id}/basketOrders" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BasketOrdersServiceListBasketOrdersRequest;
import com.apexfintechsolutions.ascendsdk.models.operations.BasketOrdersServiceListBasketOrdersResponse;
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

        BasketOrdersServiceListBasketOrdersRequest req = BasketOrdersServiceListBasketOrdersRequest.builder()
                .correspondentId("01HPMZZM6RKMVZA1JQ63RQKJRP")
                .basketId("fffd326-72fa-4d2b-bd1f-45384fe5d521")
                .pageSize(25)
                .pageToken("AbTYnwAkMjIyZDNjYTAtZmVjZS00N2Q5LTgyMDctNzI3MDdkMjFiZj3h")
                .showRemoved(true)
                .build();


        sdk.basketOrders().listBasketOrders()
                .callAsStream()
                .forEach((BasketOrdersServiceListBasketOrdersResponse item) -> {
                   // handle page
                });

    }
}
```

### Parameters

| Parameter                                                                                                           | Type                                                                                                                | Required                                                                                                            | Description                                                                                                         |
| ------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------- |
| `request`                                                                                                           | [BasketOrdersServiceListBasketOrdersRequest](../../models/operations/BasketOrdersServiceListBasketOrdersRequest.md) | :heavy_check_mark:                                                                                                  | The request object to use for the request.                                                                          |

### Response

**[BasketOrdersServiceListBasketOrdersResponse](../../models/operations/BasketOrdersServiceListBasketOrdersResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 404     | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## listCompressedOrders

Gets a list of compressed orders within a basket.

 Upon successful submission, returns a list of compressed orders for the basket. If the basket has not been submitted yet, this list will be empty. If the list of compressed orders becomes too large, a token is returned to retrieve the next page of compressed orders.

### Example Usage

<!-- UsageSnippet language="java" operationID="BasketOrdersService_ListCompressedOrders" method="get" path="/baskettrading/v1/correspondents/{correspondent_id}/baskets/{basket_id}/compressedOrders" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BasketOrdersServiceListCompressedOrdersResponse;
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


        sdk.basketOrders().listCompressedOrders()
                .correspondentId("01HPMZZM6RKMVZA1JQ63RQKJRP")
                .basketId("fffd326-72fa-4d2b-bd1f-45384fe5d521")
                .pageSize(25)
                .pageToken("AbTYnwAkMjIyZDNjYTAtZmVjZS00N2Q5LTgyMDctNzI3MDdkMjFiZj3h")
                .callAsStream()
                .forEach((BasketOrdersServiceListCompressedOrdersResponse item) -> {
                   // handle page
                });

    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                                    | Type                                                                                                                                                                                                                                         | Required                                                                                                                                                                                                                                     | Description                                                                                                                                                                                                                                  | Example                                                                                                                                                                                                                                      |
| -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `correspondentId`                                                                                                                                                                                                                            | *String*                                                                                                                                                                                                                                     | :heavy_check_mark:                                                                                                                                                                                                                           | The correspondent id.                                                                                                                                                                                                                        | 01HPMZZM6RKMVZA1JQ63RQKJRP                                                                                                                                                                                                                   |
| `basketId`                                                                                                                                                                                                                                   | *String*                                                                                                                                                                                                                                     | :heavy_check_mark:                                                                                                                                                                                                                           | The basket id.                                                                                                                                                                                                                               | fffd326-72fa-4d2b-bd1f-45384fe5d521                                                                                                                                                                                                          |
| `pageSize`                                                                                                                                                                                                                                   | *Optional\<Integer>*                                                                                                                                                                                                                         | :heavy_minus_sign:                                                                                                                                                                                                                           | The maximum number of compressed orders to return. The service may return fewer than this value. If unspecified, at most 1000 compressed orders will be returned. The maximum value is 1000; values above 1000 will be coerced to 1000.      | 25                                                                                                                                                                                                                                           |
| `pageToken`                                                                                                                                                                                                                                  | *Optional\<String>*                                                                                                                                                                                                                          | :heavy_minus_sign:                                                                                                                                                                                                                           | A page token, received from a previous `ListCompressedOrders` call. Provide this to retrieve the subsequent page. When paginating, all other parameters provided to `ListCompressedOrders` must match the call that provided the page token. | AbTYnwAkMjIyZDNjYTAtZmVjZS00N2Q5LTgyMDctNzI3MDdkMjFiZj3h                                                                                                                                                                                     |

### Response

**[BasketOrdersServiceListCompressedOrdersResponse](../../models/operations/BasketOrdersServiceListCompressedOrdersResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 404     | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## removeOrders

Removes a list of basket orders by client order ID.

 Upon successful submission, returns the details of the removed basket orders.

### Example Usage

<!-- UsageSnippet language="java" operationID="BasketOrdersService_RemoveOrders" method="post" path="/baskettrading/v1/correspondents/{correspondent_id}/baskets/{basket_id}:removeOrders" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BasketOrdersServiceRemoveOrdersResponse;
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

        BasketOrdersServiceRemoveOrdersResponse res = sdk.basketOrders().removeOrders()
                .correspondentId("01HPMZZM6RKMVZA1JQ63RQKJRP")
                .basketId("fffd326-72fa-4d2b-bd1f-45384fe5d521")
                .removeOrdersRequestCreate(RemoveOrdersRequestCreate.builder()
                    .name("correspondents/01HPMZZM6RKMVZA1JQ63RQKJRP/baskets/fffd326-72fa-4d2b-bd1f-45384fe5d521")
                    .build())
                .call();

        if (res.removeOrdersResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                         | Type                                                                              | Required                                                                          | Description                                                                       | Example                                                                           |
| --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- |
| `correspondentId`                                                                 | *String*                                                                          | :heavy_check_mark:                                                                | The correspondent id.                                                             | 01HPMZZM6RKMVZA1JQ63RQKJRP                                                        |
| `basketId`                                                                        | *String*                                                                          | :heavy_check_mark:                                                                | The basket id.                                                                    | fffd326-72fa-4d2b-bd1f-45384fe5d521                                               |
| `removeOrdersRequestCreate`                                                       | [RemoveOrdersRequestCreate](../../models/components/RemoveOrdersRequestCreate.md) | :heavy_check_mark:                                                                | N/A                                                                               |                                                                                   |

### Response

**[BasketOrdersServiceRemoveOrdersResponse](../../models/operations/BasketOrdersServiceRemoveOrdersResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 404     | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## setExtraReportingData

Sets extra reporting data to an existing basket order. Any SetExtraReportingDataRequest must include the name of the order and the cancel_confirmed_time

### Example Usage

<!-- UsageSnippet language="java" operationID="BasketOrdersService_SetExtraReportingData" method="post" path="/baskettrading/v1/correspondents/{correspondent_id}/baskets/{basket_id}:setExtraReportingData" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BasketOrdersServiceSetExtraReportingDataResponse;
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

        BasketOrdersServiceSetExtraReportingDataResponse res = sdk.basketOrders().setExtraReportingData()
                .correspondentId("01HPMZZM6RKMVZA1JQ63RQKJRP")
                .basketId("fffd326-72fa-4d2b-bd1f-45384fe5d521")
                .setExtraReportingDataRequestCreate(SetExtraReportingDataRequestCreate.builder()
                    .cancelConfirmedTime(OffsetDateTime.parse("2025-12-13T15:28:17.262732Z"))
                    .name("accounts/01HBRQ5BW6ZAY4BNWP4GWRD80X/orders/ebb0c9b5-2c74-45c9-a4ab-40596b778706")
                    .build())
                .call();

        if (res.setExtraReportingDataResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                           | Type                                                                                                | Required                                                                                            | Description                                                                                         | Example                                                                                             |
| --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- |
| `correspondentId`                                                                                   | *String*                                                                                            | :heavy_check_mark:                                                                                  | The correspondent id.                                                                               | 01HPMZZM6RKMVZA1JQ63RQKJRP                                                                          |
| `basketId`                                                                                          | *String*                                                                                            | :heavy_check_mark:                                                                                  | The basket id.                                                                                      | fffd326-72fa-4d2b-bd1f-45384fe5d521                                                                 |
| `setExtraReportingDataRequestCreate`                                                                | [SetExtraReportingDataRequestCreate](../../models/components/SetExtraReportingDataRequestCreate.md) | :heavy_check_mark:                                                                                  | N/A                                                                                                 |                                                                                                     |

### Response

**[BasketOrdersServiceSetExtraReportingDataResponse](../../models/operations/BasketOrdersServiceSetExtraReportingDataResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 404     | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |