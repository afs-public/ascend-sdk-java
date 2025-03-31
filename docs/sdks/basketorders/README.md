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

## createBasket

Creates an empty basket

 Upon successful submission, if the request is a duplicate, returns the existing basket in its current state in the system. If the request is not a duplicate, returns the summary of the newly created basket.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.BasketCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BasketOrdersServiceCreateBasketResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
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

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.AddOrdersRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.BasketOrderCreate;
import com.apexfintechsolutions.ascendsdk.models.components.BasketOrderCreateAssetType;
import com.apexfintechsolutions.ascendsdk.models.components.BasketOrderCreateIdentifierType;
import com.apexfintechsolutions.ascendsdk.models.components.BasketOrderCreateOrderType;
import com.apexfintechsolutions.ascendsdk.models.components.BasketOrderCreateSide;
import com.apexfintechsolutions.ascendsdk.models.components.BasketOrderCreateTimeInForce;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BasketOrdersServiceAddOrdersResponse;
import java.lang.Exception;
import java.util.List;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
                    .build())
            .build();

        BasketOrdersServiceAddOrdersResponse res = sdk.basketOrders().addOrders()
                .correspondentId("01HPMZZM6RKMVZA1JQ63RQKJRP")
                .basketId("fffd326-72fa-4d2b-bd1f-45384fe5d521")
                .addOrdersRequestCreate(AddOrdersRequestCreate.builder()
                    .basketOrders(List.of(
                        BasketOrderCreate.builder()
                            .accountId("01HBRQ5BW6ZAY4BNWP4GWRD80X")
                            .assetType(BasketOrderCreateAssetType.EQUITY)
                            .clientOrderId("a6d5258b-6b23-478a-8145-98e79d60427a")
                            .identifier("SBUX")
                            .identifierType(BasketOrderCreateIdentifierType.SYMBOL)
                            .orderType(BasketOrderCreateOrderType.MARKET)
                            .side(BasketOrderCreateSide.BUY)
                            .timeInForce(BasketOrderCreateTimeInForce.DAY)
                            .build(),
                        BasketOrderCreate.builder()
                            .accountId("01HBRQ5BW6ZAY4BNWP4GWRD80X")
                            .assetType(BasketOrderCreateAssetType.EQUITY)
                            .clientOrderId("a6d5258b-6b23-478a-8145-98e79d60427a")
                            .identifier("SBUX")
                            .identifierType(BasketOrderCreateIdentifierType.SYMBOL)
                            .orderType(BasketOrderCreateOrderType.MARKET)
                            .side(BasketOrderCreateSide.BUY)
                            .timeInForce(BasketOrderCreateTimeInForce.DAY)
                            .build()))
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

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BasketOrdersServiceGetBasketResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
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

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.SubmitBasketRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BasketOrdersServiceSubmitBasketResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
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

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BasketOrdersServiceListBasketOrdersResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
                    .build())
            .build();

        BasketOrdersServiceListBasketOrdersResponse res = sdk.basketOrders().listBasketOrders()
                .correspondentId("01HPMZZM6RKMVZA1JQ63RQKJRP")
                .basketId("fffd326-72fa-4d2b-bd1f-45384fe5d521")
                .pageSize(25)
                .pageToken("AbTYnwAkMjIyZDNjYTAtZmVjZS00N2Q5LTgyMDctNzI3MDdkMjFiZj3h")
                .call();

        if (res.listBasketOrdersResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                            | Type                                                                                                                                                                                                                                 | Required                                                                                                                                                                                                                             | Description                                                                                                                                                                                                                          | Example                                                                                                                                                                                                                              |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `correspondentId`                                                                                                                                                                                                                    | *String*                                                                                                                                                                                                                             | :heavy_check_mark:                                                                                                                                                                                                                   | The correspondent id.                                                                                                                                                                                                                | 01HPMZZM6RKMVZA1JQ63RQKJRP                                                                                                                                                                                                           |
| `basketId`                                                                                                                                                                                                                           | *String*                                                                                                                                                                                                                             | :heavy_check_mark:                                                                                                                                                                                                                   | The basket id.                                                                                                                                                                                                                       | fffd326-72fa-4d2b-bd1f-45384fe5d521                                                                                                                                                                                                  |
| `pageSize`                                                                                                                                                                                                                           | *Optional\<Integer>*                                                                                                                                                                                                                 | :heavy_minus_sign:                                                                                                                                                                                                                   | The maximum number of basket orders to return. The service may return fewer than this value. If unspecified, at most 1000 basket orders will be returned. The maximum value is 1000; values above 1000 will be coerced to 1000.      | 25                                                                                                                                                                                                                                   |
| `pageToken`                                                                                                                                                                                                                          | *Optional\<String>*                                                                                                                                                                                                                  | :heavy_minus_sign:                                                                                                                                                                                                                   | A page token, received from a previous `ListBasketOrders` call. Provide this to retrieve the subsequent page. When paginating, all other parameters provided to `ListBasketOrders` must match the call that provided the page token. | AbTYnwAkMjIyZDNjYTAtZmVjZS00N2Q5LTgyMDctNzI3MDdkMjFiZj3h                                                                                                                                                                             |

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

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BasketOrdersServiceListCompressedOrdersResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
                    .build())
            .build();

        BasketOrdersServiceListCompressedOrdersResponse res = sdk.basketOrders().listCompressedOrders()
                .correspondentId("01HPMZZM6RKMVZA1JQ63RQKJRP")
                .basketId("fffd326-72fa-4d2b-bd1f-45384fe5d521")
                .pageSize(25)
                .pageToken("AbTYnwAkMjIyZDNjYTAtZmVjZS00N2Q5LTgyMDctNzI3MDdkMjFiZj3h")
                .call();

        if (res.listCompressedOrdersResponse().isPresent()) {
            // handle response
        }
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