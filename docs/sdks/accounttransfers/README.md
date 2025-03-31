# AccountTransfers
(*accountTransfers()*)

## Overview

### Available Operations

* [createTransfer](#createtransfer) - Create Transfer
* [getTransfer](#gettransfer) - Get Transfer

## createTransfer

Creates a transfer

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.TransferAccountCreate;
import com.apexfintechsolutions.ascendsdk.models.components.TransferCreate;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountTransfersCreateTransferResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
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

## getTransfer

Gets an existing transfer

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountTransfersGetTransferResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
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