# CashBalances
(*cashBalances()*)

## Overview

### Available Operations

* [calculateCashBalance](#calculatecashbalance) - Get Cash Balance

## calculateCashBalance

Calculates the cash balance for an account.

### Example Usage

<!-- UsageSnippet language="java" operationID="CashBalances_CalculateCashBalance" method="get" path="/transfers/v1/accounts/{account_id}:calculateCashBalance" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.CashBalancesCalculateCashBalanceResponse;
import com.apexfintechsolutions.ascendsdk.models.operations.Mechanism;
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

        CashBalancesCalculateCashBalanceResponse res = sdk.cashBalances().calculateCashBalance()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .mechanism(Mechanism.ACH)
                .call();

        if (res.calculateCashBalanceResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                    | Type                                                                                                                         | Required                                                                                                                     | Description                                                                                                                  | Example                                                                                                                      |
| ---------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                                  | *String*                                                                                                                     | :heavy_check_mark:                                                                                                           | The account id.                                                                                                              | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                                                   |
| `mechanism`                                                                                                                  | [Optional\<Mechanism>](../../models/operations/Mechanism.md)                                                                 | :heavy_minus_sign:                                                                                                           | The withdraw mechanism to calculate the balance for. The mechanism determines what account activity will affect the balance. | ACH                                                                                                                          |

### Response

**[CashBalancesCalculateCashBalanceResponse](../../models/operations/CashBalancesCalculateCashBalanceResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |