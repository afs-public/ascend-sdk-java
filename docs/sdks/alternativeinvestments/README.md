# AlternativeInvestments
(*alternativeInvestments()*)

## Overview

### Available Operations

* [listAlternativeInvestments](#listalternativeinvestments) - List Alternative Investment Assets
* [getAlternativeInvestment](#getalternativeinvestment) - Get Alternative Investment Asset

## listAlternativeInvestments

Retrieves a list of available alternative investment assets and their details.  Replace `{asset_id}` in the endpoint path with a dash to act as a wild card.  Ex:`/trading/v1/assets/-/alternativeInvestments`

### Example Usage

<!-- UsageSnippet language="java" operationID="AlternativeInvestments_ListAlternativeInvestments" method="get" path="/trading/v1/assets/{asset_id}/alternativeInvestments" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AlternativeInvestmentsListAlternativeInvestmentsResponse;
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

        AlternativeInvestmentsListAlternativeInvestmentsResponse res = sdk.alternativeInvestments().listAlternativeInvestments()
                .assetId("-")
                .pageSize(50)
                .pageToken("eyJzaXplIjoxMCwib2Zmc2V0IjoxMDAsInBhcmVudElkIjoicGFyZW50SWQifQ==")
                .filter("state == 'OPEN' && type == 'PRIVATE_EQUITY_FUND'")
                .call();

        if (res.listAlternativeInvestmentsResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                                                                             | Type                                                                                                                                                                                                                                                                                  | Required                                                                                                                                                                                                                                                                              | Description                                                                                                                                                                                                                                                                           | Example                                                                                                                                                                                                                                                                               |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `assetId`                                                                                                                                                                                                                                                                             | *String*                                                                                                                                                                                                                                                                              | :heavy_check_mark:                                                                                                                                                                                                                                                                    | The asset id.                                                                                                                                                                                                                                                                         | -                                                                                                                                                                                                                                                                                     |
| `pageSize`                                                                                                                                                                                                                                                                            | *Optional\<Integer>*                                                                                                                                                                                                                                                                  | :heavy_minus_sign:                                                                                                                                                                                                                                                                    | The maximum number of orders to return.  - Max value =100  - Values above 100 will be coerced to 100.  - If the specified value is greater than the number of orders, the service will return fewer than the specified value.  - If unspecified, at most 100 orders will be returned. | 50                                                                                                                                                                                                                                                                                    |
| `pageToken`                                                                                                                                                                                                                                                                           | *Optional\<String>*                                                                                                                                                                                                                                                                   | :heavy_minus_sign:                                                                                                                                                                                                                                                                    | For pagination, provide the page token, received from a previous `ListAlternativeInvestments` call, to retrieve the subsequent page.  All other parameters provided to `ListAlternativeInvestments` must match the request that provided the page token.                              | eyJzaXplIjoxMCwib2Zmc2V0IjoxMDAsInBhcmVudElkIjoicGFyZW50SWQifQ==                                                                                                                                                                                                                      |
| `filter`                                                                                                                                                                                                                                                                              | *Optional\<String>*                                                                                                                                                                                                                                                                   | :heavy_minus_sign:                                                                                                                                                                                                                                                                    | A CEL string to filter results. All fields from the response can be used as filters.<br/><br/> See the [CEL Search](https://developer.apexclearing.com/apex-fintech-solutions/docs/cel-search) guide for syntax details and examples.                                                 | state == 'OPEN' && type == 'PRIVATE_EQUITY_FUND'                                                                                                                                                                                                                                      |

### Response

**[AlternativeInvestmentsListAlternativeInvestmentsResponse](../../models/operations/AlternativeInvestmentsListAlternativeInvestmentsResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403          | application/json       |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getAlternativeInvestment

Retrieves the specified alternative investment asset details.

### Example Usage

<!-- UsageSnippet language="java" operationID="AlternativeInvestments_GetAlternativeInvestment" method="get" path="/trading/v1/assets/{asset_id}/alternativeInvestment" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AlternativeInvestmentsGetAlternativeInvestmentResponse;
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

        AlternativeInvestmentsGetAlternativeInvestmentResponse res = sdk.alternativeInvestments().getAlternativeInvestment()
                .assetId("123")
                .call();

        if (res.alternativeInvestment().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter          | Type               | Required           | Description        | Example            |
| ------------------ | ------------------ | ------------------ | ------------------ | ------------------ |
| `assetId`          | *String*           | :heavy_check_mark: | The asset id.      | 123                |

### Response

**[AlternativeInvestmentsGetAlternativeInvestmentResponse](../../models/operations/AlternativeInvestmentsGetAlternativeInvestmentResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 404     | application/json       |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |