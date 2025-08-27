# Wires
(*wires()*)

## Overview

### Available Operations

* [getWireDeposit](#getwiredeposit) - Get Wire Deposit
* [createWireWithdrawal](#createwirewithdrawal) - Create Wire Withdrawal
* [getWireWithdrawal](#getwirewithdrawal) - Get Wire Withdrawal
* [cancelWireWithdrawal](#cancelwirewithdrawal) - Cancel Wire Withdrawal

## getWireDeposit

Gets an existing wire deposit

### Example Usage

<!-- UsageSnippet language="java" operationID="WireDeposits_GetWireDeposit" method="get" path="/transfers/v1/accounts/{account_id}/wireDeposits/{wireDeposit_id}" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.WireDepositsGetWireDepositResponse;
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

        WireDepositsGetWireDepositResponse res = sdk.wires().getWireDeposit()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .wireDepositId("20230817000319")
                .call();

        if (res.wireDeposit().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                  | Type                       | Required                   | Description                | Example                    |
| -------------------------- | -------------------------- | -------------------------- | -------------------------- | -------------------------- |
| `accountId`                | *String*                   | :heavy_check_mark:         | The account id.            | 01H8FB90ZRRFWXB4XC2JPJ1D4Y |
| `wireDepositId`            | *String*                   | :heavy_check_mark:         | The wireDeposit id.        | 20230817000319             |

### Response

**[WireDepositsGetWireDepositResponse](../../models/operations/WireDepositsGetWireDepositResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## createWireWithdrawal

Creates a wire withdrawal

### Example Usage

<!-- UsageSnippet language="java" operationID="WireWithdrawals_CreateWireWithdrawal" method="post" path="/transfers/v1/accounts/{account_id}/wireWithdrawals" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.WireWithdrawalsCreateWireWithdrawalResponse;
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

        WireWithdrawalsCreateWireWithdrawalResponse res = sdk.wires().createWireWithdrawal()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .wireWithdrawalCreate(WireWithdrawalCreate.builder()
                    .beneficiary(WireWithdrawalBeneficiaryCreate.builder()
                        .account("73849218650987")
                        .build())
                    .clientTransferId("ABC-123")
                    .recipientBank(WireWithdrawalRecipientBankCreate.builder()
                        .bankId(RecipientBankBankIdCreate.builder()
                            .id("ABNANL2AXXX")
                            .type(RecipientBankBankIdCreateType.BIC)
                            .build())
                        .build())
                    .build())
                .call();

        if (res.wireWithdrawal().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                               | Type                                                                    | Required                                                                | Description                                                             | Example                                                                 |
| ----------------------------------------------------------------------- | ----------------------------------------------------------------------- | ----------------------------------------------------------------------- | ----------------------------------------------------------------------- | ----------------------------------------------------------------------- |
| `accountId`                                                             | *String*                                                                | :heavy_check_mark:                                                      | The account id.                                                         | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                              |
| `wireWithdrawalCreate`                                                  | [WireWithdrawalCreate](../../models/components/WireWithdrawalCreate.md) | :heavy_check_mark:                                                      | N/A                                                                     |                                                                         |

### Response

**[WireWithdrawalsCreateWireWithdrawalResponse](../../models/operations/WireWithdrawalsCreateWireWithdrawalResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 409          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getWireWithdrawal

Gets an existing wire withdrawal

### Example Usage

<!-- UsageSnippet language="java" operationID="WireWithdrawals_GetWireWithdrawal" method="get" path="/transfers/v1/accounts/{account_id}/wireWithdrawals/{wireWithdrawal_id}" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.WireWithdrawalsGetWireWithdrawalResponse;
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

        WireWithdrawalsGetWireWithdrawalResponse res = sdk.wires().getWireWithdrawal()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .wireWithdrawalId("20230817000319")
                .call();

        if (res.wireWithdrawal().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                  | Type                       | Required                   | Description                | Example                    |
| -------------------------- | -------------------------- | -------------------------- | -------------------------- | -------------------------- |
| `accountId`                | *String*                   | :heavy_check_mark:         | The account id.            | 01H8FB90ZRRFWXB4XC2JPJ1D4Y |
| `wireWithdrawalId`         | *String*                   | :heavy_check_mark:         | The wireWithdrawal id.     | 20230817000319             |

### Response

**[WireWithdrawalsGetWireWithdrawalResponse](../../models/operations/WireWithdrawalsGetWireWithdrawalResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## cancelWireWithdrawal

Cancels an existing wire withdrawal

### Example Usage

<!-- UsageSnippet language="java" operationID="WireWithdrawals_CancelWireWithdrawal" method="post" path="/transfers/v1/accounts/{account_id}/wireWithdrawals/{wireWithdrawal_id}:cancel" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.WireWithdrawalsCancelWireWithdrawalResponse;
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

        WireWithdrawalsCancelWireWithdrawalResponse res = sdk.wires().cancelWireWithdrawal()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .wireWithdrawalId("20230817000319")
                .cancelWireWithdrawalRequestCreate(CancelWireWithdrawalRequestCreate.builder()
                    .name("accounts/01H8FB90ZRRFWXB4XC2JPJ1D4Y/wireWithdrawals/20230817000319")
                    .build())
                .call();

        if (res.wireWithdrawal().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                         | Type                                                                                              | Required                                                                                          | Description                                                                                       | Example                                                                                           |
| ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                       | *String*                                                                                          | :heavy_check_mark:                                                                                | The account id.                                                                                   | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                        |
| `wireWithdrawalId`                                                                                | *String*                                                                                          | :heavy_check_mark:                                                                                | The wireWithdrawal id.                                                                            | 20230817000319                                                                                    |
| `cancelWireWithdrawalRequestCreate`                                                               | [CancelWireWithdrawalRequestCreate](../../models/components/CancelWireWithdrawalRequestCreate.md) | :heavy_check_mark:                                                                                | N/A                                                                                               |                                                                                                   |

### Response

**[WireWithdrawalsCancelWireWithdrawalResponse](../../models/operations/WireWithdrawalsCancelWireWithdrawalResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |