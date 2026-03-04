# AlternativeInvestmentDocuments
(*alternativeInvestmentDocuments()*)

## Overview

### Available Operations

* [listAlternativeInvestmentDocuments](#listalternativeinvestmentdocuments) - List Alternative Investment Documents
* [getAlternativeInvestmentDocument](#getalternativeinvestmentdocument) - Get Alternative Investment Document
* [downloadAlternativeInvestmentDocument](#downloadalternativeinvestmentdocument) - Download Alternative Investment Documents

## listAlternativeInvestmentDocuments

Retrieves a list of all investment document details for the specified asset.

### Example Usage

<!-- UsageSnippet language="java" operationID="AlternativeInvestmentDocuments_ListAlternativeInvestmentDocuments" method="get" path="/trading/v1/assets/{asset_id}/alternativeInvestmentDocuments" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AlternativeInvestmentDocumentsListAlternativeInvestmentDocumentsResponse;
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

        AlternativeInvestmentDocumentsListAlternativeInvestmentDocumentsResponse res = sdk.alternativeInvestmentDocuments().listAlternativeInvestmentDocuments()
                .assetId("123")
                .pageSize(10)
                .pageToken("next-page-token-example")
                .filter("type == 'OFFERING_DOCUMENT'")
                .call();

        if (res.listAlternativeInvestmentDocumentsResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                                                                             | Type                                                                                                                                                                                                                                                                                  | Required                                                                                                                                                                                                                                                                              | Description                                                                                                                                                                                                                                                                           | Example                                                                                                                                                                                                                                                                               |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `assetId`                                                                                                                                                                                                                                                                             | *String*                                                                                                                                                                                                                                                                              | :heavy_check_mark:                                                                                                                                                                                                                                                                    | The asset id.                                                                                                                                                                                                                                                                         | 123                                                                                                                                                                                                                                                                                   |
| `pageSize`                                                                                                                                                                                                                                                                            | *Optional\<Integer>*                                                                                                                                                                                                                                                                  | :heavy_minus_sign:                                                                                                                                                                                                                                                                    | The maximum number of orders to return. - Max value = 100  - Values above 100 will be coerced to 100.  - If the specified value is greater than the number of orders, the service will return fewer than the specified value.  - If unspecified, at most 100 orders will be returned. | 10                                                                                                                                                                                                                                                                                    |
| `pageToken`                                                                                                                                                                                                                                                                           | *Optional\<String>*                                                                                                                                                                                                                                                                   | :heavy_minus_sign:                                                                                                                                                                                                                                                                    | For pagination, provide the page token, received from a previous `ListInvestmentDocuments` call, to retrieve the subsequent page.  All other parameters provided to `ListInvestmentDocuments` must match the request that provided the page token.                                    | next-page-token-example                                                                                                                                                                                                                                                               |
| `filter`                                                                                                                                                                                                                                                                              | *Optional\<String>*                                                                                                                                                                                                                                                                   | :heavy_minus_sign:                                                                                                                                                                                                                                                                    | A CEL string to filter results. All fields from the response can be used as filters.<br/><br/> See the [CEL Search](https://developer.apexclearing.com/apex-fintech-solutions/docs/cel-search) guide for syntax details and examples.                                                 | type == 'OFFERING_DOCUMENT'                                                                                                                                                                                                                                                           |

### Response

**[AlternativeInvestmentDocumentsListAlternativeInvestmentDocumentsResponse](../../models/operations/AlternativeInvestmentDocumentsListAlternativeInvestmentDocumentsResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403          | application/json       |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getAlternativeInvestmentDocument

Retrieves a specific investment document for the specified asset.

### Example Usage

<!-- UsageSnippet language="java" operationID="AlternativeInvestmentDocuments_GetAlternativeInvestmentDocument" method="get" path="/trading/v1/assets/{asset_id}/alternativeInvestmentDocuments/{alternativeInvestmentDocument_id}" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AlternativeInvestmentDocumentsGetAlternativeInvestmentDocumentResponse;
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

        AlternativeInvestmentDocumentsGetAlternativeInvestmentDocumentResponse res = sdk.alternativeInvestmentDocuments().getAlternativeInvestmentDocument()
                .assetId("123")
                .alternativeInvestmentDocumentId("01H7YH8QKZJ8V4Q2X8F4G6JQ2B")
                .call();

        if (res.alternativeInvestmentDocument().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                             | Type                                  | Required                              | Description                           | Example                               |
| ------------------------------------- | ------------------------------------- | ------------------------------------- | ------------------------------------- | ------------------------------------- |
| `assetId`                             | *String*                              | :heavy_check_mark:                    | The asset id.                         | 123                                   |
| `alternativeInvestmentDocumentId`     | *String*                              | :heavy_check_mark:                    | The alternativeInvestmentDocument id. | 01H7YH8QKZJ8V4Q2X8F4G6JQ2B            |

### Response

**[AlternativeInvestmentDocumentsGetAlternativeInvestmentDocumentResponse](../../models/operations/AlternativeInvestmentDocumentsGetAlternativeInvestmentDocumentResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 404     | application/json       |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## downloadAlternativeInvestmentDocument

Returns a URI to download the specified investment document.

### Example Usage

<!-- UsageSnippet language="java" operationID="AlternativeInvestmentDocuments_DownloadAlternativeInvestmentDocument" method="get" path="/trading/v1/assets/{asset_id}/alternativeInvestmentDocuments/{alternativeInvestmentDocument_id}:download" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AlternativeInvestmentDocumentsDownloadAlternativeInvestmentDocumentResponse;
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

        AlternativeInvestmentDocumentsDownloadAlternativeInvestmentDocumentResponse res = sdk.alternativeInvestmentDocuments().downloadAlternativeInvestmentDocument()
                .assetId("123")
                .alternativeInvestmentDocumentId("01H7YH8QKZJ8V4Q2X8F4G6JQ2B")
                .call();

        if (res.downloadAlternativeInvestmentDocumentResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                             | Type                                  | Required                              | Description                           | Example                               |
| ------------------------------------- | ------------------------------------- | ------------------------------------- | ------------------------------------- | ------------------------------------- |
| `assetId`                             | *String*                              | :heavy_check_mark:                    | The asset id.                         | 123                                   |
| `alternativeInvestmentDocumentId`     | *String*                              | :heavy_check_mark:                    | The alternativeInvestmentDocument id. | 01H7YH8QKZJ8V4Q2X8F4G6JQ2B            |

### Response

**[AlternativeInvestmentDocumentsDownloadAlternativeInvestmentDocumentResponse](../../models/operations/AlternativeInvestmentDocumentsDownloadAlternativeInvestmentDocumentResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 404     | application/json       |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |