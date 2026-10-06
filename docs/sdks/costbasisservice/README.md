# CostBasisService
(*costBasisService()*)

## Overview

### Available Operations

* [searchClosedLots](#searchclosedlots) - Search Closed Lots
* [searchOpenLots](#searchopenlots) - Search Open Lots

## searchClosedLots

SearchClosedLots returns a list of closed lots for a given account and date range. Please note that if no trade date ranges are provided, all the closed lots using the MTD range will be returned.

### Example Usage

<!-- UsageSnippet language="java" operationID="CostBasisService_SearchClosedLots" method="post" path="/costbasis/v1/accounts/{account_id}/closedLots:search" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.CostBasisServiceSearchClosedLotsResponse;
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

        CostBasisServiceSearchClosedLotsResponse res = sdk.costBasisService().searchClosedLots()
                .accountId("01J71HKJ1K1GX5C0EWZ4BCPACB")
                .searchClosedLotsRequestCreate(SearchClosedLotsRequestCreate.builder()
                    .parent("accounts/01J71HKJ1K1GX5C0EWZ4BCPACB")
                    .build())
                .call();

        if (res.searchClosedLotsResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                 | Type                                                                                      | Required                                                                                  | Description                                                                               | Example                                                                                   |
| ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| `accountId`                                                                               | *String*                                                                                  | :heavy_check_mark:                                                                        | The account id.                                                                           | 01J71HKJ1K1GX5C0EWZ4BCPACB                                                                |
| `searchClosedLotsRequestCreate`                                                           | [SearchClosedLotsRequestCreate](../../models/components/SearchClosedLotsRequestCreate.md) | :heavy_check_mark:                                                                        | N/A                                                                                       |                                                                                           |

### Response

**[CostBasisServiceSearchClosedLotsResponse](../../models/operations/CostBasisServiceSearchClosedLotsResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403          | application/json       |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## searchOpenLots

SearchOpenLots returns a list of open lots for a given account

### Example Usage

<!-- UsageSnippet language="java" operationID="CostBasisService_SearchOpenLots" method="post" path="/costbasis/v1/accounts/{account_id}/openLots:search" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.CostBasisServiceSearchOpenLotsResponse;
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

        CostBasisServiceSearchOpenLotsResponse res = sdk.costBasisService().searchOpenLots()
                .accountId("01J71HKJ1K1GX5C0EWZ4BCPACB")
                .searchOpenLotsRequestCreate(SearchOpenLotsRequestCreate.builder()
                    .parent("accounts/01J71HKJ1K1GX5C0EWZ4BCPACB")
                    .build())
                .call();

        if (res.searchOpenLotsResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                             | Type                                                                                  | Required                                                                              | Description                                                                           | Example                                                                               |
| ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- |
| `accountId`                                                                           | *String*                                                                              | :heavy_check_mark:                                                                    | The account id.                                                                       | 01J71HKJ1K1GX5C0EWZ4BCPACB                                                            |
| `searchOpenLotsRequestCreate`                                                         | [SearchOpenLotsRequestCreate](../../models/components/SearchOpenLotsRequestCreate.md) | :heavy_check_mark:                                                                    | N/A                                                                                   |                                                                                       |

### Response

**[CostBasisServiceSearchOpenLotsResponse](../../models/operations/CostBasisServiceSearchOpenLotsResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403          | application/json       |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |