# DataRetrieval
(*dataRetrieval()*)

## Overview

### Available Operations

* [listSnapshots](#listsnapshots) - List Snapshots

## listSnapshots

Returns details of a list of snapshots.

### Example Usage

<!-- UsageSnippet language="java" operationID="Snapshots_ListSnapshots" method="get" path="/analytics/v1/snapshots" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.SnapshotsListSnapshotsResponse;
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


        sdk.dataRetrieval().listSnapshots()
                .filter("snapshot_type==\"daily_accounts\"&&process_date==date(\"2023-09-30\")")
                .pageSize(500)
                .pageToken("M_-BAwEBCVBhZ2VUb2tlbgH_ggABAgEMUnVubmluZ1RvdGFsAQQAAQZGaWx0ZXIBDAAAAAX_ggEyAA==")
                .callAsStream()
                .forEach((SnapshotsListSnapshotsResponse item) -> {
                   // handle page
                });

    }
}
```

### Parameters

| Parameter                                                                                                                                                                     | Type                                                                                                                                                                          | Required                                                                                                                                                                      | Description                                                                                                                                                                   | Example                                                                                                                                                                       |
| ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `filter`                                                                                                                                                                      | *Optional\<String>*                                                                                                                                                           | :heavy_minus_sign:                                                                                                                                                            | A CEL string to filter snapshot results; See the [CEL Search](https://developer.apexclearing.com/apex-fintech-solutions/docs/cel-search) page in Guides for more information; | snapshot_type=="daily_accounts"&&process_date==date("2023-09-30")                                                                                                             |
| `pageSize`                                                                                                                                                                    | *Optional\<Integer>*                                                                                                                                                          | :heavy_minus_sign:                                                                                                                                                            | The number of snapshots to be returned per page. Defaults to 500. Maximum is 1000.                                                                                            | 500                                                                                                                                                                           |
| `pageToken`                                                                                                                                                                   | *Optional\<String>*                                                                                                                                                           | :heavy_minus_sign:                                                                                                                                                            | The token for retrieving the next page of snapshots, the value of which will have been returned in a previous response.                                                       | M_-BAwEBCVBhZ2VUb2tlbgH_ggABAgEMUnVubmluZ1RvdGFsAQQAAQZGaWx0ZXIBDAAAAAX_ggEyAA==                                                                                              |

### Response

**[SnapshotsListSnapshotsResponse](../../models/operations/SnapshotsListSnapshotsResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |