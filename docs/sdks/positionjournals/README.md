# PositionJournals
(*positionJournals()*)

## Overview

### Available Operations

* [createPositionJournal](#createpositionjournal) - Create Position Journal
* [getPositionJournal](#getpositionjournal) - Get Position Journal
* [cancelPositionJournal](#cancelpositionjournal) - Cancel Position Journal

## createPositionJournal

Creates a position journal

### Example Usage

<!-- UsageSnippet language="java" operationID="PositionJournals_CreatePositionJournal" method="post" path="/transfers/v1/positionJournals" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.PositionJournalsCreatePositionJournalResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Exception {

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

        PositionJournalCreate req = PositionJournalCreate.builder()
                .clientTransferId("113bw03-49f8-4525-934c-560fb39dg2kd")
                .destinationAccount("accounts/01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .identifier("AAPL")
                .identifierType(IdentifierType.SYMBOL)
                .quantity(DecimalCreate.builder()
                    .build())
                .sourceAccount("accounts/01H8FM6EXVH77SAW3TC8KAWMES")
                .type(PositionJournalCreateType.REWARD)
                .build();

        PositionJournalsCreatePositionJournalResponse res = sdk.positionJournals().createPositionJournal()
                .request(req)
                .call();

        if (res.positionJournal().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                             | Type                                                                  | Required                                                              | Description                                                           |
| --------------------------------------------------------------------- | --------------------------------------------------------------------- | --------------------------------------------------------------------- | --------------------------------------------------------------------- |
| `request`                                                             | [PositionJournalCreate](../../models/shared/PositionJournalCreate.md) | :heavy_check_mark:                                                    | The request object to use for the request.                            |

### Response

**[PositionJournalsCreatePositionJournalResponse](../../models/operations/PositionJournalsCreatePositionJournalResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 409          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getPositionJournal

Gets an existing position journal

### Example Usage

<!-- UsageSnippet language="java" operationID="PositionJournals_GetPositionJournal" method="get" path="/transfers/v1/positionJournals/{positionJournal_id}" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.PositionJournalsGetPositionJournalResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Exception {

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

        PositionJournalsGetPositionJournalResponse res = sdk.positionJournals().getPositionJournal()
                .positionJournalId("20230817000319")
                .call();

        if (res.positionJournal().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter               | Type                    | Required                | Description             | Example                 |
| ----------------------- | ----------------------- | ----------------------- | ----------------------- | ----------------------- |
| `positionJournalId`     | *String*                | :heavy_check_mark:      | The positionJournal id. | 20230817000319          |

### Response

**[PositionJournalsGetPositionJournalResponse](../../models/operations/PositionJournalsGetPositionJournalResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## cancelPositionJournal

Cancels an existing position journal

### Example Usage

<!-- UsageSnippet language="java" operationID="PositionJournals_CancelPositionJournal" method="post" path="/transfers/v1/positionJournals/{positionJournal_id}:cancel" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.PositionJournalsCancelPositionJournalResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Exception {

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

        PositionJournalsCancelPositionJournalResponse res = sdk.positionJournals().cancelPositionJournal()
                .positionJournalId("20240717000319")
                .cancelPositionJournalRequestCreate(CancelPositionJournalRequestCreate.builder()
                    .name("positionJournals/20240717000319")
                    .build())
                .call();

        if (res.positionJournal().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                           | Type                                                                                                | Required                                                                                            | Description                                                                                         | Example                                                                                             |
| --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- |
| `positionJournalId`                                                                                 | *String*                                                                                            | :heavy_check_mark:                                                                                  | The positionJournal id.                                                                             | 20240717000319                                                                                      |
| `cancelPositionJournalRequestCreate`                                                                | [CancelPositionJournalRequestCreate](../../models/components/CancelPositionJournalRequestCreate.md) | :heavy_check_mark:                                                                                  | N/A                                                                                                 |                                                                                                     |

### Response

**[PositionJournalsCancelPositionJournalResponse](../../models/operations/PositionJournalsCancelPositionJournalResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |