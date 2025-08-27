# Checks
(*checks()*)

## Overview

### Available Operations

* [getCheckDeposit](#getcheckdeposit) - Get Check Deposit

## getCheckDeposit

Gets an existing check deposit.

### Example Usage

<!-- UsageSnippet language="java" operationID="CheckDeposits_GetCheckDeposit" method="get" path="/transfers/v1/accounts/{account_id}/checkDeposits/{checkDeposit_id}" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.CheckDepositsGetCheckDepositResponse;
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

        CheckDepositsGetCheckDepositResponse res = sdk.checks().getCheckDeposit()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .checkDepositId("20230817000319")
                .call();

        if (res.checkDeposit().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                  | Type                       | Required                   | Description                | Example                    |
| -------------------------- | -------------------------- | -------------------------- | -------------------------- | -------------------------- |
| `accountId`                | *String*                   | :heavy_check_mark:         | The account id.            | 01H8FB90ZRRFWXB4XC2JPJ1D4Y |
| `checkDepositId`           | *String*                   | :heavy_check_mark:         | The checkDeposit id.       | 20230817000319             |

### Response

**[CheckDepositsGetCheckDepositResponse](../../models/operations/CheckDepositsGetCheckDepositResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |