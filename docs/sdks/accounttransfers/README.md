# AccountTransfers
(*accountTransfers()*)

## Overview

### Available Operations

* [createTransfer](#createtransfer) - Create Transfer
* [listTransfers](#listtransfers) - List Transfers
* [acceptTransfer](#accepttransfer) - Accept Transfer
* [rejectTransfer](#rejecttransfer) - Reject Transfer
* [getTransfer](#gettransfer) - Get Transfer

## createTransfer

Creates a transfer

### Example Usage

<!-- UsageSnippet language="java" operationID="AccountTransfers_CreateTransfer" method="post" path="/acats/v1/correspondents/{correspondent_id}/accounts/{account_id}/transfers" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountTransfersCreateTransferResponse;
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

        AccountTransfersCreateTransferResponse res = sdk.accountTransfers().createTransfer()
                .correspondentId("00000000-0000-0000-0000-000000000002")
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .requestId("ABC-123")
                .transferCreate(TransferCreate.builder()
                    .deliverer(TransferAccountCreate.builder()
                        .build())
                    .build())
                .call();

        if (res.acatsTransfer().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                  | Type                                                                                                                                                                                                       | Required                                                                                                                                                                                                   | Description                                                                                                                                                                                                | Example                                                                                                                                                                                                    |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `correspondentId`                                                                                                                                                                                          | *String*                                                                                                                                                                                                   | :heavy_check_mark:                                                                                                                                                                                         | The correspondent id.                                                                                                                                                                                      | 00000000-0000-0000-0000-000000000002                                                                                                                                                                       |
| `accountId`                                                                                                                                                                                                | *String*                                                                                                                                                                                                   | :heavy_check_mark:                                                                                                                                                                                         | The account id.                                                                                                                                                                                            | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                                                                                                                                 |
| `requestId`                                                                                                                                                                                                | *Optional\<String>*                                                                                                                                                                                        | :heavy_minus_sign:                                                                                                                                                                                         | A client-specified ID for the account transfer; no specific pattern is imposed. This field is used for idempotency to ensure that repeated requests with the same ID do not result in duplicate transfers. | ABC-123                                                                                                                                                                                                    |
| `transferCreate`                                                                                                                                                                                           | [TransferCreate](../../models/components/TransferCreate.md)                                                                                                                                                | :heavy_check_mark:                                                                                                                                                                                         | N/A                                                                                                                                                                                                        |                                                                                                                                                                                                            |

### Response

**[AccountTransfersCreateTransferResponse](../../models/operations/AccountTransfersCreateTransferResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 409          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## listTransfers

Lists existing transfers using a CEL filter.

### Example Usage

<!-- UsageSnippet language="java" operationID="AccountTransfers_ListTransfers" method="get" path="/acats/v1/correspondents/{correspondent_id}/accounts/{account_id}/transfers" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountTransfersListTransfersRequest;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountTransfersListTransfersResponse;
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

        AccountTransfersListTransfersRequest req = AccountTransfersListTransfersRequest.builder()
                .correspondentId("00000000-0000-0000-0000-000000000002")
                .accountId("01FAKEACCOUNT")
                .pageSize(25)
                .pageToken("CgwI5uHttgYQyJXO2wESJDAxOTFjOTMxLTA3YjMtYzU0ZC0yMDNmLWU1M2U0OTBkY2FhZRoicmVjZWl2ZXIuYWNjb3VudF9pZCBpbiBbJzEwMDAwQUEnXQ")
                .filter("deliverer.account_number == \"R9AHY8P\"")
                .build();


        sdk.accountTransfers().listTransfers()
                .callAsStream()
                .forEach((AccountTransfersListTransfersResponse item) -> {
                   // handle page
                });

    }
}
```

### Parameters

| Parameter                                                                                               | Type                                                                                                    | Required                                                                                                | Description                                                                                             |
| ------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------- |
| `request`                                                                                               | [AccountTransfersListTransfersRequest](../../models/operations/AccountTransfersListTransfersRequest.md) | :heavy_check_mark:                                                                                      | The request object to use for the request.                                                              |

### Response

**[AccountTransfersListTransfersResponse](../../models/operations/AccountTransfersListTransfersResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## acceptTransfer

Accept one side (incoming/outgoing) of an internal Ascend transfer. When both the incoming side and outgoing side of the transfer have been accepted then bookkeeping is done immediately. If neither, or only one side of a transfer is accepted, then both sides of the internal perform bookkeeping one full settlement day after they went into the bookkeeping queue.

### Example Usage

<!-- UsageSnippet language="java" operationID="AccountTransfers_AcceptTransfer" method="post" path="/acats/v1/correspondents/{correspondent_id}/accounts/{account_id}/transfers/{transfer_id}:accept" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountTransfersAcceptTransferResponse;
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

        AccountTransfersAcceptTransferResponse res = sdk.accountTransfers().acceptTransfer()
                .correspondentId("00000000-0000-0000-0000-000000000002")
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .transferId("00000000-0000-0000-0000-000000000000")
                .acceptTransferRequestCreate(AcceptTransferRequestCreate.builder()
                    .name("correspondents/00000000-0000-0000-0000-000000000002/accounts/01H8FB90ZRRFWXB4XC2JPJ1D4Y/transfers/00000000-0000-0000-0000-000000000000")
                    .build())
                .call();

        if (res.acceptTransferResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                             | Type                                                                                  | Required                                                                              | Description                                                                           | Example                                                                               |
| ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- |
| `correspondentId`                                                                     | *String*                                                                              | :heavy_check_mark:                                                                    | The correspondent id.                                                                 | 00000000-0000-0000-0000-000000000002                                                  |
| `accountId`                                                                           | *String*                                                                              | :heavy_check_mark:                                                                    | The account id.                                                                       | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                            |
| `transferId`                                                                          | *String*                                                                              | :heavy_check_mark:                                                                    | The transfer id.                                                                      | 00000000-0000-0000-0000-000000000000                                                  |
| `acceptTransferRequestCreate`                                                         | [AcceptTransferRequestCreate](../../models/components/AcceptTransferRequestCreate.md) | :heavy_check_mark:                                                                    | N/A                                                                                   |                                                                                       |

### Response

**[AccountTransfersAcceptTransferResponse](../../models/operations/AccountTransfersAcceptTransferResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## rejectTransfer

Reject one side (incoming/outgoing) of an internal Ascend transfer. Rejecting one side automatically moves the contra side of the transfer to be rejected as well.

### Example Usage

<!-- UsageSnippet language="java" operationID="AccountTransfers_RejectTransfer" method="post" path="/acats/v1/correspondents/{correspondent_id}/accounts/{account_id}/transfers/{transfer_id}:reject" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountTransfersRejectTransferResponse;
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

        AccountTransfersRejectTransferResponse res = sdk.accountTransfers().rejectTransfer()
                .correspondentId("00000000-0000-0000-0000-000000000002")
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .transferId("00000000-0000-0000-0000-000000000000")
                .rejectTransferRequestCreate(RejectTransferRequestCreate.builder()
                    .name("correspondents/00000000-0000-0000-0000-000000000002/accounts/01H8FB90ZRRFWXB4XC2JPJ1D4Y/transfers/00000000-0000-0000-0000-000000000000")
                    .build())
                .call();

        if (res.rejectTransferResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                             | Type                                                                                  | Required                                                                              | Description                                                                           | Example                                                                               |
| ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- |
| `correspondentId`                                                                     | *String*                                                                              | :heavy_check_mark:                                                                    | The correspondent id.                                                                 | 00000000-0000-0000-0000-000000000002                                                  |
| `accountId`                                                                           | *String*                                                                              | :heavy_check_mark:                                                                    | The account id.                                                                       | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                            |
| `transferId`                                                                          | *String*                                                                              | :heavy_check_mark:                                                                    | The transfer id.                                                                      | 00000000-0000-0000-0000-000000000000                                                  |
| `rejectTransferRequestCreate`                                                         | [RejectTransferRequestCreate](../../models/components/RejectTransferRequestCreate.md) | :heavy_check_mark:                                                                    | N/A                                                                                   |                                                                                       |

### Response

**[AccountTransfersRejectTransferResponse](../../models/operations/AccountTransfersRejectTransferResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getTransfer

Gets an existing transfer

### Example Usage

<!-- UsageSnippet language="java" operationID="AccountTransfers_GetTransfer" method="get" path="/acats/v1/correspondents/{correspondent_id}/accounts/{account_id}/transfers/{transfer_id}" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountTransfersGetTransferResponse;
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

        AccountTransfersGetTransferResponse res = sdk.accountTransfers().getTransfer()
                .correspondentId("00000000-0000-0000-0000-000000000002")
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .transferId("00000000-0000-0000-0000-000000000000")
                .call();

        if (res.acatsTransfer().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                            | Type                                 | Required                             | Description                          | Example                              |
| ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ |
| `correspondentId`                    | *String*                             | :heavy_check_mark:                   | The correspondent id.                | 00000000-0000-0000-0000-000000000002 |
| `accountId`                          | *String*                             | :heavy_check_mark:                   | The account id.                      | 01H8FB90ZRRFWXB4XC2JPJ1D4Y           |
| `transferId`                         | *String*                             | :heavy_check_mark:                   | The transfer id.                     | 00000000-0000-0000-0000-000000000000 |

### Response

**[AccountTransfersGetTransferResponse](../../models/operations/AccountTransfersGetTransferResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |