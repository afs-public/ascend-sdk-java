# Ledger
(*ledger()*)

## Overview

### Available Operations

* [listEntries](#listentries) - List Entries
* [listActivities](#listactivities) - List Activities
* [listPositions](#listpositions) - List Positions
* [getActivity](#getactivity) - Get Activity
* [getEntry](#getentry) - Get Entry

## listEntries

List all Entries based on a filter

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.LedgerListEntriesResponse;
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

        LedgerListEntriesResponse res = sdk.ledger().listEntries()
                .accountId("01FAKEACCOUNT1TYKWEYRH8S2K")
                .pageSize(0)
                .pageToken("v-BAwEBCVBhZ2VUb2tlbgH_ggABAgEPUmVxdWVzdENoZWNrc3VtAQYAAQJJZAEMAAAAOv-CAfzbNG7ZAS8xZWYyMmM3ZS01NjdmLTBhYzgtYjZmZi1kNzYwNDI3YmI3N2Q6MjAyNC0wNi0wMgA=")
                .filter("process_date == date('2024-05-11') && account_id == '01HBRQ5BW6ZAY4BNWP4GWRD80X'")
                .call();

        if (res.listEntriesResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                                                         | Type                                                                                                                                                                                                                                                              | Required                                                                                                                                                                                                                                                          | Description                                                                                                                                                                                                                                                       | Example                                                                                                                                                                                                                                                           |
| ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                                                                                                                                                                       | *String*                                                                                                                                                                                                                                                          | :heavy_check_mark:                                                                                                                                                                                                                                                | The account id.                                                                                                                                                                                                                                                   | 01FAKEACCOUNT1TYKWEYRH8S2K                                                                                                                                                                                                                                        |
| `pageSize`                                                                                                                                                                                                                                                        | *Optional\<Integer>*                                                                                                                                                                                                                                              | :heavy_minus_sign:                                                                                                                                                                                                                                                | The maximum number of entries to return. The service may return fewer than this value Default is 100 (subject to change) The maximum is 1000, values exceeding this will be set to 1000 (subject to change)                                                       | 0                                                                                                                                                                                                                                                                 |
| `pageToken`                                                                                                                                                                                                                                                       | *Optional\<String>*                                                                                                                                                                                                                                               | :heavy_minus_sign:                                                                                                                                                                                                                                                | A page token, received from a previous `ListEntries` call. Provide this to retrieve the subsequent page When paginating, all other parameters provided to `ListEntries` must match the call that provided the page token in order to maintain a stable result set | v-BAwEBCVBhZ2VUb2tlbgH_ggABAgEPUmVxdWVzdENoZWNrc3VtAQYAAQJJZAEMAAAAOv-CAfzbNG7ZAS8xZWYyMmM3ZS01NjdmLTBhYzgtYjZmZi1kNzYwNDI3YmI3N2Q6MjAyNC0wNi0wMgA=                                                                                                               |
| `filter`                                                                                                                                                                                                                                                          | *Optional\<String>*                                                                                                                                                                                                                                               | :heavy_minus_sign:                                                                                                                                                                                                                                                | A CEL string to filter results; See the [CEL Search](https://developer.apexclearing.com/apex-fintech-solutions/docs/cel-search) page in Guides for more information;                                                                                              | process_date == date('2024-05-11') && account_id == '01HBRQ5BW6ZAY4BNWP4GWRD80X'                                                                                                                                                                                  |

### Response

**[LedgerListEntriesResponse](../../models/operations/LedgerListEntriesResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/Status   | 500, 503, 504          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## listActivities

List all Completed Activities based on a filter

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.LedgerListActivitiesResponse;
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

        LedgerListActivitiesResponse res = sdk.ledger().listActivities()
                .accountId("01FAKEACCOUNT1TYKWEYRH8S2K")
                .pageSize(100)
                .pageToken("Mv-BAwEBCVBhZ2VUb2tlbgH_ggABAgEPUmVxdWVzdENoZWNrc3VtAQYAAQJJZAEMAAAAI_-CAfwVsHF9ARgyMDI0LTA2LTA0OjFGQTA1MDExOjUwMDEA")
                .filter("subtype_category == 'TRADE' && process_date >= date('2023-07-31') && settle_date >= date('2023-08-18') && side == 'BUY' &&  activity_date >= date('2023-09-15') && asset_id == 8395")
                .call();

        if (res.listActivitiesResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                                                           | Type                                                                                                                                                                                                                                                                | Required                                                                                                                                                                                                                                                            | Description                                                                                                                                                                                                                                                         | Example                                                                                                                                                                                                                                                             |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                                                                                                                                                                         | *String*                                                                                                                                                                                                                                                            | :heavy_check_mark:                                                                                                                                                                                                                                                  | The account id.                                                                                                                                                                                                                                                     | 01FAKEACCOUNT1TYKWEYRH8S2K                                                                                                                                                                                                                                          |
| `pageSize`                                                                                                                                                                                                                                                          | *Optional\<Integer>*                                                                                                                                                                                                                                                | :heavy_minus_sign:                                                                                                                                                                                                                                                  | The maximum number of activities to return. The service may return fewer than this value Default is 100 (subject to change) The maximum is 1000, values exceeding this will be set to 1000 (subject to change)                                                      | 100                                                                                                                                                                                                                                                                 |
| `pageToken`                                                                                                                                                                                                                                                         | *Optional\<String>*                                                                                                                                                                                                                                                 | :heavy_minus_sign:                                                                                                                                                                                                                                                  | A page token, received from a previous `ListActivity` call. Provide this to retrieve the subsequent page When paginating, all other parameters provided to `ListActivity` must match the call that provided the page token in order to maintain a stable result set | Mv-BAwEBCVBhZ2VUb2tlbgH_ggABAgEPUmVxdWVzdENoZWNrc3VtAQYAAQJJZAEMAAAAI_-CAfwVsHF9ARgyMDI0LTA2LTA0OjFGQTA1MDExOjUwMDEA                                                                                                                                                |
| `filter`                                                                                                                                                                                                                                                            | *Optional\<String>*                                                                                                                                                                                                                                                 | :heavy_minus_sign:                                                                                                                                                                                                                                                  | A CEL string to filter results; See the [CEL Search](https://developer.apexclearing.com/apex-fintech-solutions/docs/cel-search) page in Guides for more information;                                                                                                | subtype_category == 'TRADE' && process_date >= date('2023-07-31') && settle_date >= date('2023-08-18') && side == 'BUY' &&  activity_date >= date('2023-09-15') && asset_id == 8395                                                                                 |

### Response

**[LedgerListActivitiesResponse](../../models/operations/LedgerListActivitiesResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/Status   | 500, 503, 504          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## listPositions

List positions based on a filter

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.LedgerListPositionsResponse;
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

        LedgerListPositionsResponse res = sdk.ledger().listPositions()
                .accountId("01HBRQ5BW6ZAY4BNWP4GWRD80X")
                .pageSize(20)
                .pageToken("Mv-BAwEBCVBhZ2VUb2tlbgH_ggABAgEPUmVxdWVzdENoZWNrc3VtAQYAAQJJZAEMAAAAOv-CAfwFIZG3AS8xZWYyMmM4Ny0zNDI5LTAyYzItODRjNC03ODdmNTJlNDY1MTE6MjAyNC0wNi0wMgA=")
                .filter("date >= date('2023-08-31') && asset_id == 8395")
                .call();

        if (res.listPositionsResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                                                            | Type                                                                                                                                                                                                                                                                 | Required                                                                                                                                                                                                                                                             | Description                                                                                                                                                                                                                                                          | Example                                                                                                                                                                                                                                                              |
| -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                                                                                                                                                                          | *String*                                                                                                                                                                                                                                                             | :heavy_check_mark:                                                                                                                                                                                                                                                   | The account id.                                                                                                                                                                                                                                                      | 01HBRQ5BW6ZAY4BNWP4GWRD80X                                                                                                                                                                                                                                           |
| `pageSize`                                                                                                                                                                                                                                                           | *Optional\<Integer>*                                                                                                                                                                                                                                                 | :heavy_minus_sign:                                                                                                                                                                                                                                                   | The maximum number of positions to return. The service may return fewer than this value Default is 100 (subject to change) The maximum is 1000, values exceeding this will be set to 1000 (subject to change)                                                        | 20                                                                                                                                                                                                                                                                   |
| `pageToken`                                                                                                                                                                                                                                                          | *Optional\<String>*                                                                                                                                                                                                                                                  | :heavy_minus_sign:                                                                                                                                                                                                                                                   | This page token comes from a previous `ListPositions` call; provide this token to retrieve the subsequent page When paginating, all other parameters you include in `ListPositions` must match the call that includes the page token to maintain a stable result set | Mv-BAwEBCVBhZ2VUb2tlbgH_ggABAgEPUmVxdWVzdENoZWNrc3VtAQYAAQJJZAEMAAAAOv-CAfwFIZG3AS8xZWYyMmM4Ny0zNDI5LTAyYzItODRjNC03ODdmNTJlNDY1MTE6MjAyNC0wNi0wMgA=                                                                                                                 |
| `filter`                                                                                                                                                                                                                                                             | *Optional\<String>*                                                                                                                                                                                                                                                  | :heavy_minus_sign:                                                                                                                                                                                                                                                   | A CEL string to filter results; See the [CEL Search](https://developer.apexclearing.com/apex-fintech-solutions/docs/cel-search) page in Guides for more information;                                                                                                 | date >= date('2023-08-31') && asset_id == 8395                                                                                                                                                                                                                       |

### Response

**[LedgerListPositionsResponse](../../models/operations/LedgerListPositionsResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/Status   | 500, 503, 504          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getActivity

Get an activity

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.LedgerGetActivityResponse;
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

        LedgerGetActivityResponse res = sdk.ledger().getActivity()
                .accountId("01FAKEACCOUNT1TYKWEYRH8S2K")
                .activityId("FAKEACTIVITYID")
                .call();

        if (res.activity().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                  | Type                       | Required                   | Description                | Example                    |
| -------------------------- | -------------------------- | -------------------------- | -------------------------- | -------------------------- |
| `accountId`                | *String*                   | :heavy_check_mark:         | The account id.            | 01FAKEACCOUNT1TYKWEYRH8S2K |
| `activityId`               | *String*                   | :heavy_check_mark:         | The activity id.           | FAKEACTIVITYID             |

### Response

**[LedgerGetActivityResponse](../../models/operations/LedgerGetActivityResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/Status   | 500, 503, 504          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getEntry

Get an entry

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.LedgerGetEntryResponse;
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

        LedgerGetEntryResponse res = sdk.ledger().getEntry()
                .accountId("{\"account_id\":\"\"}")
                .entryId("{\"entry_id\":\"\"}")
                .call();

        if (res.entry().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter            | Type                 | Required             | Description          | Example              |
| -------------------- | -------------------- | -------------------- | -------------------- | -------------------- |
| `accountId`          | *String*             | :heavy_check_mark:   | The account id.      | {<br/>"account_id": ""<br/>} |
| `entryId`            | *String*             | :heavy_check_mark:   | The entry id.        | {<br/>"entry_id": ""<br/>} |

### Response

**[LedgerGetEntryResponse](../../models/operations/LedgerGetEntryResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/Status   | 500, 503, 504          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |