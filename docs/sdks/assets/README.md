# Assets
(*assets()*)

## Overview

### Available Operations

* [listAssets](#listassets) - List Assets
* [getAsset](#getasset) - Get Asset
* [listAssetsCorrespondent](#listassetscorrespondent) - List Assets (By Correspondent)
* [getAssetCorrespondent](#getassetcorrespondent) - Get Asset (By Correspondent)

## listAssets

Lists assets

### Example Usage

<!-- UsageSnippet language="java" operationID="Assets_ListAssets_1" method="get" path="/assets/v1/assets" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AssetsListAssets1Response;
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


        sdk.assets().listAssets()
                .parent("correspondents/1234")
                .pageSize(100)
                .pageToken("Mv-BAwEBCVBhZ2VUb2tlbgH_ggABAgEPUmVxdWVzdENoZWNrc3VtAQYAAQJJZAEMAAAAD_-CAfzrRtzkAQQ1MDA3AA==")
                .filter("(symbol == 'IBM' && usable) || symbol == 'USD'")
                .callAsStream()
                .forEach((AssetsListAssets1Response item) -> {
                   // handle page
                });

    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                                                         | Type                                                                                                                                                                                                                                                              | Required                                                                                                                                                                                                                                                          | Description                                                                                                                                                                                                                                                       | Example                                                                                                                                                                                                                                                           |
| ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `parent`                                                                                                                                                                                                                                                          | *Optional\<String>*                                                                                                                                                                                                                                               | :heavy_minus_sign:                                                                                                                                                                                                                                                | The parent resource name, which is the correspondent ID.                                                                                                                                                                                                          | correspondents/1234                                                                                                                                                                                                                                               |
| `pageSize`                                                                                                                                                                                                                                                        | *Optional\<Integer>*                                                                                                                                                                                                                                              | :heavy_minus_sign:                                                                                                                                                                                                                                                | The maximum number of assets to return. The service may return fewer than this value. Default is 100 (subject to change) The maximum is 1000, values exceeding this will be set to 1000 (subject to change)                                                       | 100                                                                                                                                                                                                                                                               |
| `pageToken`                                                                                                                                                                                                                                                       | *Optional\<String>*                                                                                                                                                                                                                                               | :heavy_minus_sign:                                                                                                                                                                                                                                                | A page token, received from a previous `ListAssets` call. Provide this to retrieve the subsequent page. When paginating, all other parameters provided to `ListAssets` must match the call that provided the page token in order to maintain a stable result set. | Mv-BAwEBCVBhZ2VUb2tlbgH_ggABAgEPUmVxdWVzdENoZWNrc3VtAQYAAQJJZAEMAAAAD_-CAfzrRtzkAQQ1MDA3AA==                                                                                                                                                                      |
| `filter`                                                                                                                                                                                                                                                          | *Optional\<String>*                                                                                                                                                                                                                                               | :heavy_minus_sign:                                                                                                                                                                                                                                                | A CEL string to filter results; See the [CEL Search](https://developer.apexclearing.com/apex-fintech-solutions/docs/cel-search) page in Guides for more information;                                                                                              | (symbol == 'IBM' && usable) \|\| symbol == 'USD'                                                                                                                                                                                                                  |

### Response

**[AssetsListAssets1Response](../../models/operations/AssetsListAssets1Response.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503, 504          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getAsset

Gets assets

### Example Usage

<!-- UsageSnippet language="java" operationID="Assets_GetAsset" method="get" path="/assets/v1/assets/{asset_id}" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AssetsGetAssetResponse;
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

        AssetsGetAssetResponse res = sdk.assets().getAsset()
                .assetId("8395")
                .call();

        if (res.asset().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter          | Type               | Required           | Description        | Example            |
| ------------------ | ------------------ | ------------------ | ------------------ | ------------------ |
| `assetId`          | *String*           | :heavy_check_mark: | The asset id.      | 8395               |

### Response

**[AssetsGetAssetResponse](../../models/operations/AssetsGetAssetResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## listAssetsCorrespondent

Lists assets

### Example Usage

<!-- UsageSnippet language="java" operationID="Assets_ListAssets_Correspondent" method="get" path="/assets/v1/correspondents/{correspondent_id}/assets" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AssetsListAssetsCorrespondentResponse;
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


        sdk.assets().listAssetsCorrespondent()
                .correspondentId("1234")
                .pageSize(100)
                .pageToken("Mv-BAwEBCVBhZ2VUb2tlbgH_ggABAgEPUmVxdWVzdENoZWNrc3VtAQYAAQJJZAEMAAAAD_-CAfzrRtzkAQQ1MDA3AA==")
                .filter("(symbol == 'IBM' && usable) || symbol == 'USD'")
                .callAsStream()
                .forEach((AssetsListAssetsCorrespondentResponse item) -> {
                   // handle page
                });

    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                                                         | Type                                                                                                                                                                                                                                                              | Required                                                                                                                                                                                                                                                          | Description                                                                                                                                                                                                                                                       | Example                                                                                                                                                                                                                                                           |
| ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `correspondentId`                                                                                                                                                                                                                                                 | *String*                                                                                                                                                                                                                                                          | :heavy_check_mark:                                                                                                                                                                                                                                                | The correspondent id.                                                                                                                                                                                                                                             | 1234                                                                                                                                                                                                                                                              |
| `pageSize`                                                                                                                                                                                                                                                        | *Optional\<Integer>*                                                                                                                                                                                                                                              | :heavy_minus_sign:                                                                                                                                                                                                                                                | The maximum number of assets to return. The service may return fewer than this value. Default is 100 (subject to change) The maximum is 1000, values exceeding this will be set to 1000 (subject to change)                                                       | 100                                                                                                                                                                                                                                                               |
| `pageToken`                                                                                                                                                                                                                                                       | *Optional\<String>*                                                                                                                                                                                                                                               | :heavy_minus_sign:                                                                                                                                                                                                                                                | A page token, received from a previous `ListAssets` call. Provide this to retrieve the subsequent page. When paginating, all other parameters provided to `ListAssets` must match the call that provided the page token in order to maintain a stable result set. | Mv-BAwEBCVBhZ2VUb2tlbgH_ggABAgEPUmVxdWVzdENoZWNrc3VtAQYAAQJJZAEMAAAAD_-CAfzrRtzkAQQ1MDA3AA==                                                                                                                                                                      |
| `filter`                                                                                                                                                                                                                                                          | *Optional\<String>*                                                                                                                                                                                                                                               | :heavy_minus_sign:                                                                                                                                                                                                                                                | A CEL string to filter results; See the [CEL Search](https://developer.apexclearing.com/apex-fintech-solutions/docs/cel-search) page in Guides for more information;                                                                                              | (symbol == 'IBM' && usable) \|\| symbol == 'USD'                                                                                                                                                                                                                  |

### Response

**[AssetsListAssetsCorrespondentResponse](../../models/operations/AssetsListAssetsCorrespondentResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503, 504          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getAssetCorrespondent

Gets assets

### Example Usage

<!-- UsageSnippet language="java" operationID="Assets_GetAsset_Correspondent" method="get" path="/assets/v1/correspondents/{correspondent_id}/assets/{asset_id}" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AssetsGetAssetCorrespondentResponse;
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

        AssetsGetAssetCorrespondentResponse res = sdk.assets().getAssetCorrespondent()
                .correspondentId("8395")
                .assetId("<id>")
                .call();

        if (res.asset().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter             | Type                  | Required              | Description           | Example               |
| --------------------- | --------------------- | --------------------- | --------------------- | --------------------- |
| `correspondentId`     | *String*              | :heavy_check_mark:    | The correspondent id. | 8395                  |
| `assetId`             | *String*              | :heavy_check_mark:    | The asset id.         |                       |

### Response

**[AssetsGetAssetCorrespondentResponse](../../models/operations/AssetsGetAssetCorrespondentResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |