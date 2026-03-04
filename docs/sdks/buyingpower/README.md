# BuyingPower
(*buyingPower()*)

## Overview

### Available Operations

* [getBuyingPower](#getbuyingpower) - Get Buying Power
* [getAssetBuyingPower](#getassetbuyingpower) - Get Asset Buying Power

## getBuyingPower

Gets the buying power for an account

### Example Usage

<!-- UsageSnippet language="java" operationID="MarginsRealTime_GetBuyingPower" method="get" path="/buyingpower/v1/accounts/{account_id}/buyingPower" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.MarginsRealTimeGetBuyingPowerResponse;
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

        MarginsRealTimeGetBuyingPowerResponse res = sdk.buyingPower().getBuyingPower()
                .accountId("01HMS9S15AKBHBD8GPW33P2PMH")
                .call();

        if (res.buyingPower().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                  | Type                       | Required                   | Description                | Example                    |
| -------------------------- | -------------------------- | -------------------------- | -------------------------- | -------------------------- |
| `accountId`                | *String*                   | :heavy_check_mark:         | The account id.            | 01HMS9S15AKBHBD8GPW33P2PMH |

### Response

**[MarginsRealTimeGetBuyingPowerResponse](../../models/operations/MarginsRealTimeGetBuyingPowerResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 404     | application/json       |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getAssetBuyingPower

Gets buying power for an account and asset

### Example Usage

<!-- UsageSnippet language="java" operationID="MarginsRealTime_GetAssetBuyingPower" method="get" path="/buyingpower/v1/accounts/{account_id}/assets/{asset_id}/buyingPower" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.MarginsRealTimeGetAssetBuyingPowerResponse;
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

        MarginsRealTimeGetAssetBuyingPowerResponse res = sdk.buyingPower().getAssetBuyingPower()
                .accountId("01HMS9S15AKBHBD8GPW33P2PMH")
                .assetId("67587")
                .call();

        if (res.assetBuyingPower().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                  | Type                       | Required                   | Description                | Example                    |
| -------------------------- | -------------------------- | -------------------------- | -------------------------- | -------------------------- |
| `accountId`                | *String*                   | :heavy_check_mark:         | The account id.            | 01HMS9S15AKBHBD8GPW33P2PMH |
| `assetId`                  | *String*                   | :heavy_check_mark:         | The asset id.              | 67587                      |

### Response

**[MarginsRealTimeGetAssetBuyingPowerResponse](../../models/operations/MarginsRealTimeGetAssetBuyingPowerResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 404     | application/json       |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |