# Journals
(*journals()*)

## Overview

### Available Operations

* [retrieveCashJournalConstraints](#retrievecashjournalconstraints) - Retrieve Cash Journal Constraints
* [createCashJournal](#createcashjournal) - Create Cash Journal
* [getCashJournal](#getcashjournal) - Get Cash Journal
* [cancelCashJournal](#cancelcashjournal) - Cancel Cash Journal
* [checkPartyType](#checkpartytype) - Retrieve Cash Journal Party

## retrieveCashJournalConstraints

Retrieves retirement contribution and distribution constraints for a cash journal transfer

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.RetrieveCashJournalConstraintsRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.RetirementConstraintsRetrieveCashJournalConstraintsResponse;
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

        RetrieveCashJournalConstraintsRequestCreate req = RetrieveCashJournalConstraintsRequestCreate.builder()
                .destinationAccount("accounts/01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .sourceAccount("accounts/01H8FM6EXVH77SAW3TC8KAWMES")
                .build();

        RetirementConstraintsRetrieveCashJournalConstraintsResponse res = sdk.journals().retrieveCashJournalConstraints()
                .request(req)
                .call();

        if (res.cashJournalConstraints().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                         | Type                                                                                                              | Required                                                                                                          | Description                                                                                                       |
| ----------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------- |
| `request`                                                                                                         | [RetrieveCashJournalConstraintsRequestCreate](../../models/shared/RetrieveCashJournalConstraintsRequestCreate.md) | :heavy_check_mark:                                                                                                | The request object to use for the request.                                                                        |

### Response

**[RetirementConstraintsRetrieveCashJournalConstraintsResponse](../../models/operations/RetirementConstraintsRetrieveCashJournalConstraintsResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## createCashJournal

Creates a cash journal

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.CashJournalCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.CashJournalsCreateCashJournalResponse;
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

        CashJournalCreate req = CashJournalCreate.builder()
                .clientTransferId("113bw03-49f8-4525-934c-560fb39dg2kd")
                .destinationAccount("accounts/01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .sourceAccount("accounts/01H8FM6EXVH77SAW3TC8KAWMES")
                .build();

        CashJournalsCreateCashJournalResponse res = sdk.journals().createCashJournal()
                .request(req)
                .call();

        if (res.cashJournal().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                     | Type                                                          | Required                                                      | Description                                                   |
| ------------------------------------------------------------- | ------------------------------------------------------------- | ------------------------------------------------------------- | ------------------------------------------------------------- |
| `request`                                                     | [CashJournalCreate](../../models/shared/CashJournalCreate.md) | :heavy_check_mark:                                            | The request object to use for the request.                    |

### Response

**[CashJournalsCreateCashJournalResponse](../../models/operations/CashJournalsCreateCashJournalResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 409          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getCashJournal

Gets an existing cash journal

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.CashJournalsGetCashJournalResponse;
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

        CashJournalsGetCashJournalResponse res = sdk.journals().getCashJournal()
                .cashJournalId("20230817000319")
                .call();

        if (res.cashJournal().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter           | Type                | Required            | Description         | Example             |
| ------------------- | ------------------- | ------------------- | ------------------- | ------------------- |
| `cashJournalId`     | *String*            | :heavy_check_mark:  | The cashJournal id. | 20230817000319      |

### Response

**[CashJournalsGetCashJournalResponse](../../models/operations/CashJournalsGetCashJournalResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## cancelCashJournal

Cancels an existing cash journal

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.CancelCashJournalRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.CashJournalsCancelCashJournalResponse;
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

        CashJournalsCancelCashJournalResponse res = sdk.journals().cancelCashJournal()
                .cashJournalId("20240717000319")
                .cancelCashJournalRequestCreate(CancelCashJournalRequestCreate.builder()
                    .name("cashJournals/20240717000319")
                    .build())
                .call();

        if (res.cashJournal().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                   | Type                                                                                        | Required                                                                                    | Description                                                                                 | Example                                                                                     |
| ------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------- |
| `cashJournalId`                                                                             | *String*                                                                                    | :heavy_check_mark:                                                                          | The cashJournal id.                                                                         | 20240717000319                                                                              |
| `cancelCashJournalRequestCreate`                                                            | [CancelCashJournalRequestCreate](../../models/components/CancelCashJournalRequestCreate.md) | :heavy_check_mark:                                                                          | N/A                                                                                         |                                                                                             |

### Response

**[CashJournalsCancelCashJournalResponse](../../models/operations/CashJournalsCancelCashJournalResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## checkPartyType

Determines whether a potential cash journal will be considered first party or third party given a source and destination account

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.CheckPartyTypeRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.CashJournalsCheckPartyTypeResponse;
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

        CheckPartyTypeRequestCreate req = CheckPartyTypeRequestCreate.builder()
                .destinationAccount("accounts/01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .sourceAccount("accounts/01H8FM6EXVH77SAW3TC8KAWMES")
                .build();

        CashJournalsCheckPartyTypeResponse res = sdk.journals().checkPartyType()
                .request(req)
                .call();

        if (res.checkPartyTypeResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                         | Type                                                                              | Required                                                                          | Description                                                                       |
| --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- |
| `request`                                                                         | [CheckPartyTypeRequestCreate](../../models/shared/CheckPartyTypeRequestCreate.md) | :heavy_check_mark:                                                                | The request object to use for the request.                                        |

### Response

**[CashJournalsCheckPartyTypeResponse](../../models/operations/CashJournalsCheckPartyTypeResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |