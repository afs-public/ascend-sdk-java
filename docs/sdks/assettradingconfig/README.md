# AssetTradingConfig
(*assetTradingConfig()*)

## Overview

### Available Operations

* [getAssetTradingConfig](#getassettradingconfig) - Get Asset Trading Config
* [listAssetTradingConfigs](#listassettradingconfigs) - List Asset Trading Configs

## getAssetTradingConfig

Gets an asset trading config by asset_id `/assettradingconfig/v1/correspondents/{correspondent_id}/assets/{asset_id}/tradingConfig`

### Example Usage

<!-- UsageSnippet language="java" operationID="AssetTradingConfigService_GetAssetTradingConfig" method="get" path="/assettradingconfig/v1/correspondents/{correspondent_id}/assets/{asset_id}/tradingConfig" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AssetTradingConfigServiceGetAssetTradingConfigResponse;
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

        AssetTradingConfigServiceGetAssetTradingConfigResponse res = sdk.assetTradingConfig().getAssetTradingConfig()
                .correspondentId("01HBRQ5BW6ZAY4BNWP4GWRD80X")
                .assetId("612")
                .call();

        if (res.assetTradingConfig().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                  | Type                       | Required                   | Description                | Example                    |
| -------------------------- | -------------------------- | -------------------------- | -------------------------- | -------------------------- |
| `correspondentId`          | *String*                   | :heavy_check_mark:         | The correspondent id.      | 01HBRQ5BW6ZAY4BNWP4GWRD80X |
| `assetId`                  | *String*                   | :heavy_check_mark:         | The asset id.              | 612                        |

### Response

**[AssetTradingConfigServiceGetAssetTradingConfigResponse](../../models/operations/AssetTradingConfigServiceGetAssetTradingConfigResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 404     | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## listAssetTradingConfigs

Retrieve a list of asset trading configs `/assettradingconfig/v1/correspondents/{correspondent_id}/assets/-/tradingConfigs`

### Example Usage

<!-- UsageSnippet language="java" operationID="AssetTradingConfigService_ListAssetTradingConfigs" method="get" path="/assettradingconfig/v1/correspondents/{correspondent_id}/assets/{asset_id}/tradingConfigs" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AssetTradingConfigServiceListAssetTradingConfigsRequest;
import com.apexfintechsolutions.ascendsdk.models.operations.AssetTradingConfigServiceListAssetTradingConfigsResponse;
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

        AssetTradingConfigServiceListAssetTradingConfigsRequest req = AssetTradingConfigServiceListAssetTradingConfigsRequest.builder()
                .correspondentId("01HBRQ5BW6ZAY4BNWP4GWRD80X")
                .assetId("-")
                .pageSize(100)
                .pageToken("Mv-BAwEBCVBhZ2VUb2tlbgH_ggABAgEPUmVxdWVzdENoZWNrc3VtAQYAAQJJZAEMAAAAD_-CAfzrRtzkAQQ1MDA3AA==")
                .filter("symbol == 'SBUX' && asset_type == 'EQUITY'")
                .build();

        AssetTradingConfigServiceListAssetTradingConfigsResponse res = sdk.assetTradingConfig().listAssetTradingConfigs()
                .request(req)
                .call();

        if (res.listAssetTradingConfigsResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                     | Type                                                                                                                                          | Required                                                                                                                                      | Description                                                                                                                                   |
| --------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------- |
| `request`                                                                                                                                     | [AssetTradingConfigServiceListAssetTradingConfigsRequest](../../models/operations/AssetTradingConfigServiceListAssetTradingConfigsRequest.md) | :heavy_check_mark:                                                                                                                            | The request object to use for the request.                                                                                                    |

### Response

**[AssetTradingConfigServiceListAssetTradingConfigsResponse](../../models/operations/AssetTradingConfigServiceListAssetTradingConfigsResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403          | application/json       |
| models/errors/Status   | 500, 503, 504          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |