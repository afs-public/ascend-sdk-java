# FixedIncomePricing
(*fixedIncomePricing()*)

## Overview

### Available Operations

* [previewOrderCost](#previewordercost) - Preview Order Cost
* [retrieveQuote](#retrievequote) - Retrieve Quote
* [retrieveFixedIncomeMarks](#retrievefixedincomemarks) - Retrieve Fixed Income Marks

## previewOrderCost

Returns a calculation estimating the costs involved in ordering a given quantity of a Fixed Income asset at a specified limit price.

### Example Usage

<!-- UsageSnippet language="java" operationID="OrderPriceService_PreviewOrderCost" method="post" path="/trading/v1/accounts/{account_id}/orders:previewOrderCost" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.OrderPriceServicePreviewOrderCostResponse;
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

        OrderPriceServicePreviewOrderCostResponse res = sdk.fixedIncomePricing().previewOrderCost()
                .accountId("01HBRQ5BW6ZAY4BNWP4GWRD80X")
                .orderCostPreviewRequestCreate(OrderCostPreviewRequestCreate.builder()
                    .assetType(OrderCostPreviewRequestCreateAssetType.FIXED_INCOME)
                    .identifier("037833100")
                    .identifierType(OrderCostPreviewRequestCreateIdentifierType.CUSIP)
                    .limitPrice(LimitPriceCreate.builder()
                        .price(DecimalCreate.builder()
                            .build())
                        .type(LimitPriceCreateType.PRICE_PER_UNIT)
                        .build())
                    .parent("accounts/01HBRQ5BW6ZAY4BNWP4GWRD80X")
                    .quantity(DecimalCreate.builder()
                        .build())
                    .build())
                .call();

        if (res.orderCostPreviewResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                 | Type                                                                                      | Required                                                                                  | Description                                                                               | Example                                                                                   |
| ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| `accountId`                                                                               | *String*                                                                                  | :heavy_check_mark:                                                                        | The account id.                                                                           | 01HBRQ5BW6ZAY4BNWP4GWRD80X                                                                |
| `orderCostPreviewRequestCreate`                                                           | [OrderCostPreviewRequestCreate](../../models/components/OrderCostPreviewRequestCreate.md) | :heavy_check_mark:                                                                        | N/A                                                                                       |                                                                                           |

### Response

**[OrderPriceServicePreviewOrderCostResponse](../../models/operations/OrderPriceServicePreviewOrderCostResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## retrieveQuote

Returns quote information containing the best bid/ask for the given Fixed Income asset. For Fixed Income assets in the UAT environment, CUSIPS are subject to expiration. Therefore please refer to the [list available in the Market Simulator](https://developer.apexclearing.com/apex-fintech-solutions/docs/market-simulator#fixed-income-simulator-scenarios) for the most recent CUSIP list prior to testing.

### Example Usage

<!-- UsageSnippet language="java" operationID="OrderPriceService_RetrieveQuote" method="post" path="/trading/v1/accounts/{account_id}/orders:retrieveAssetQuote" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.OrderPriceServiceRetrieveQuoteResponse;
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

        OrderPriceServiceRetrieveQuoteResponse res = sdk.fixedIncomePricing().retrieveQuote()
                .accountId("01HBRQ5BW6ZAY4BNWP4GWRD80X")
                .retrieveQuoteRequestCreate(RetrieveQuoteRequestCreate.builder()
                    .assetType(RetrieveQuoteRequestCreateAssetType.FIXED_INCOME)
                    .identifier("037833100")
                    .identifierType(RetrieveQuoteRequestCreateIdentifierType.CUSIP)
                    .parent("accounts/01HBRQ5BW6ZAY4BNWP4GWRD80X")
                    .build())
                .call();

        if (res.retrieveQuoteResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                           | Type                                                                                | Required                                                                            | Description                                                                         | Example                                                                             |
| ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- |
| `accountId`                                                                         | *String*                                                                            | :heavy_check_mark:                                                                  | The account id.                                                                     | 01HBRQ5BW6ZAY4BNWP4GWRD80X                                                          |
| `retrieveQuoteRequestCreate`                                                        | [RetrieveQuoteRequestCreate](../../models/components/RetrieveQuoteRequestCreate.md) | :heavy_check_mark:                                                                  | N/A                                                                                 |                                                                                     |

### Response

**[OrderPriceServiceRetrieveQuoteResponse](../../models/operations/OrderPriceServiceRetrieveQuoteResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## retrieveFixedIncomeMarks

Returns marks for a specified set of Fixed Income assets (up to 100 per request)

### Example Usage

<!-- UsageSnippet language="java" operationID="OrderPriceService_RetrieveFixedIncomeMarks" method="post" path="/trading/v1/correspondents/{correspondent_id}/prices:retrieveFixedIncomeMarks" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.OrderPriceServiceRetrieveFixedIncomeMarksResponse;
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

        OrderPriceServiceRetrieveFixedIncomeMarksResponse res = sdk.fixedIncomePricing().retrieveFixedIncomeMarks()
                .correspondentId("01HBRQ5BW6ZAY4BNWP4GWRD80X")
                .retrieveFixedIncomeMarksRequestCreate(RetrieveFixedIncomeMarksRequestCreate.builder()
                    .parent("correspondents/01HBRQ5BW6ZAY4BNWP4GWRD80X")
                    .securityIdentifiers(List.of(
                        RetrieveFixedIncomeMarksRequestSecurityIdentifiersCreate.builder()
                            .identifier("US0378331005")
                            .identifierType(RetrieveFixedIncomeMarksRequestSecurityIdentifiersCreateIdentifierType.ISIN)
                            .build(),
                        RetrieveFixedIncomeMarksRequestSecurityIdentifiersCreate.builder()
                            .identifier("38259P508")
                            .identifierType(RetrieveFixedIncomeMarksRequestSecurityIdentifiersCreateIdentifierType.CUSIP)
                            .build()))
                    .build())
                .call();

        if (res.retrieveFixedIncomeMarksResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                 | Type                                                                                                      | Required                                                                                                  | Description                                                                                               | Example                                                                                                   |
| --------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- |
| `correspondentId`                                                                                         | *String*                                                                                                  | :heavy_check_mark:                                                                                        | The correspondent id.                                                                                     | 01HBRQ5BW6ZAY4BNWP4GWRD80X                                                                                |
| `retrieveFixedIncomeMarksRequestCreate`                                                                   | [RetrieveFixedIncomeMarksRequestCreate](../../models/components/RetrieveFixedIncomeMarksRequestCreate.md) | :heavy_check_mark:                                                                                        | N/A                                                                                                       |                                                                                                           |

### Response

**[OrderPriceServiceRetrieveFixedIncomeMarksResponse](../../models/operations/OrderPriceServiceRetrieveFixedIncomeMarksResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |