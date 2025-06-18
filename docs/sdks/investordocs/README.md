# InvestorDocs
(*investorDocs()*)

## Overview

### Available Operations

* [batchCreateUploadLinks](#batchcreateuploadlinks) - Batch Create Upload Links
* [listDocuments](#listdocuments) - List Documents

## batchCreateUploadLinks

Create a batch of signed links that can be used to upload files.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.BatchCreateUploadLinksRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.InvestorCommunicationServiceBatchCreateUploadLinksResponse;
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

        BatchCreateUploadLinksRequestCreate req = BatchCreateUploadLinksRequestCreate.builder()
                .build();

        InvestorCommunicationServiceBatchCreateUploadLinksResponse res = sdk.investorDocs().batchCreateUploadLinks()
                .request(req)
                .call();

        if (res.batchCreateUploadLinksResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                         | Type                                                                                              | Required                                                                                          | Description                                                                                       |
| ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------- |
| `request`                                                                                         | [BatchCreateUploadLinksRequestCreate](../../models/shared/BatchCreateUploadLinksRequestCreate.md) | :heavy_check_mark:                                                                                | The request object to use for the request.                                                        |

### Response

**[InvestorCommunicationServiceBatchCreateUploadLinksResponse](../../models/operations/InvestorCommunicationServiceBatchCreateUploadLinksResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403          | application/json       |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## listDocuments

List documents that match search parameters.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.InvestorCommunicationServiceListDocumentsResponse;
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

        InvestorCommunicationServiceListDocumentsResponse res = sdk.investorDocs().listDocuments()
                .pageSize(454807)
                .pageToken("<value>")
                .filter("<value>")
                .call();

        if (res.listDocumentsResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                             | Type                                                                                                                                                  | Required                                                                                                                                              | Description                                                                                                                                           |
| ----------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------- |
| `pageSize`                                                                                                                                            | *Optional\<Integer>*                                                                                                                                  | :heavy_minus_sign:                                                                                                                                    | The maximum number of items to return; The service may return fewer than this value                                                                   |
| `pageToken`                                                                                                                                           | *Optional\<String>*                                                                                                                                   | :heavy_minus_sign:                                                                                                                                    | Token used to get a specific page of results                                                                                                          |
| `filter`                                                                                                                                              | *Optional\<String>*                                                                                                                                   | :heavy_minus_sign:                                                                                                                                    | CEL filter to be applied against the documents; Providing a correspondent to search for is required; Only one correspondent can be searched at a time |

### Response

**[InvestorCommunicationServiceListDocumentsResponse](../../models/operations/InvestorCommunicationServiceListDocumentsResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403          | application/json       |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |