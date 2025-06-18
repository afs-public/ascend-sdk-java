# ACHTransfers
(*achTransfers()*)

## Overview

### Available Operations

* [createAchDeposit](#createachdeposit) - Create ACH Deposit
* [getAchDeposit](#getachdeposit) - Get ACH Deposit
* [cancelAchDeposit](#cancelachdeposit) - Cancel ACH Deposit
* [createAchWithdrawal](#createachwithdrawal) - Create ACH Withdrawal
* [getAchWithdrawal](#getachwithdrawal) - Get ACH Withdrawal
* [cancelAchWithdrawal](#cancelachwithdrawal) - Cancel ACH Withdrawal

## createAchDeposit

Creates an ACH deposit.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.AchDepositCreate;
import com.apexfintechsolutions.ascendsdk.models.components.DecimalCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AchDepositsCreateAchDepositResponse;
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

        AchDepositsCreateAchDepositResponse res = sdk.achTransfers().createAchDeposit()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .achDepositCreate(AchDepositCreate.builder()
                    .amount(DecimalCreate.builder()
                        .build())
                    .bankRelationship("accounts/01H8FB90ZRRFWXB4XC2JPJ1D4Y/bankRelationships/651ef9de0dee00240813e60e")
                    .clientTransferId("179dcd33-49f8-4615-989c-560fb387c4fd")
                    .build())
                .call();

        if (res.achDeposit().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                       | Type                                                            | Required                                                        | Description                                                     | Example                                                         |
| --------------------------------------------------------------- | --------------------------------------------------------------- | --------------------------------------------------------------- | --------------------------------------------------------------- | --------------------------------------------------------------- |
| `accountId`                                                     | *String*                                                        | :heavy_check_mark:                                              | The account id.                                                 | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                      |
| `achDepositCreate`                                              | [AchDepositCreate](../../models/components/AchDepositCreate.md) | :heavy_check_mark:                                              | N/A                                                             |                                                                 |

### Response

**[AchDepositsCreateAchDepositResponse](../../models/operations/AchDepositsCreateAchDepositResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 409          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getAchDeposit

Gets an existing ACH deposit.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AchDepositsGetAchDepositResponse;
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

        AchDepositsGetAchDepositResponse res = sdk.achTransfers().getAchDeposit()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .achDepositId("20230817000319")
                .call();

        if (res.achDeposit().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                  | Type                       | Required                   | Description                | Example                    |
| -------------------------- | -------------------------- | -------------------------- | -------------------------- | -------------------------- |
| `accountId`                | *String*                   | :heavy_check_mark:         | The account id.            | 01H8FB90ZRRFWXB4XC2JPJ1D4Y |
| `achDepositId`             | *String*                   | :heavy_check_mark:         | The achDeposit id.         | 20230817000319             |

### Response

**[AchDepositsGetAchDepositResponse](../../models/operations/AchDepositsGetAchDepositResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## cancelAchDeposit

Cancels an existing ACH deposit.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.CancelAchDepositRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AchDepositsCancelAchDepositResponse;
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

        AchDepositsCancelAchDepositResponse res = sdk.achTransfers().cancelAchDeposit()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .achDepositId("20230817000319")
                .cancelAchDepositRequestCreate(CancelAchDepositRequestCreate.builder()
                    .name("accounts/01H8FB90ZRRFWXB4XC2JPJ1D4Y/achDeposits/20230817000319")
                    .build())
                .call();

        if (res.achDeposit().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                 | Type                                                                                      | Required                                                                                  | Description                                                                               | Example                                                                                   |
| ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| `accountId`                                                                               | *String*                                                                                  | :heavy_check_mark:                                                                        | The account id.                                                                           | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                |
| `achDepositId`                                                                            | *String*                                                                                  | :heavy_check_mark:                                                                        | The achDeposit id.                                                                        | 20230817000319                                                                            |
| `cancelAchDepositRequestCreate`                                                           | [CancelAchDepositRequestCreate](../../models/components/CancelAchDepositRequestCreate.md) | :heavy_check_mark:                                                                        | N/A                                                                                       |                                                                                           |

### Response

**[AchDepositsCancelAchDepositResponse](../../models/operations/AchDepositsCancelAchDepositResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## createAchWithdrawal

Creates an ACH withdrawal.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.AchWithdrawalCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AchWithdrawalsCreateAchWithdrawalResponse;
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

        AchWithdrawalsCreateAchWithdrawalResponse res = sdk.achTransfers().createAchWithdrawal()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .achWithdrawalCreate(AchWithdrawalCreate.builder()
                    .bankRelationship("accounts/01H8FB90ZRRFWXB4XC2JPJ1D4Y/bankRelationships/651ef9de0dee00240813e60e")
                    .clientTransferId("179dcd33-49f8-4615-989c-560fb387c4fd")
                    .build())
                .call();

        if (res.achWithdrawal().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                             | Type                                                                  | Required                                                              | Description                                                           | Example                                                               |
| --------------------------------------------------------------------- | --------------------------------------------------------------------- | --------------------------------------------------------------------- | --------------------------------------------------------------------- | --------------------------------------------------------------------- |
| `accountId`                                                           | *String*                                                              | :heavy_check_mark:                                                    | The account id.                                                       | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                            |
| `achWithdrawalCreate`                                                 | [AchWithdrawalCreate](../../models/components/AchWithdrawalCreate.md) | :heavy_check_mark:                                                    | N/A                                                                   |                                                                       |

### Response

**[AchWithdrawalsCreateAchWithdrawalResponse](../../models/operations/AchWithdrawalsCreateAchWithdrawalResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 409          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getAchWithdrawal

Gets an existing ACH withdrawal.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AchWithdrawalsGetAchWithdrawalResponse;
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

        AchWithdrawalsGetAchWithdrawalResponse res = sdk.achTransfers().getAchWithdrawal()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .achWithdrawalId("20230620500726")
                .call();

        if (res.achWithdrawal().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                  | Type                       | Required                   | Description                | Example                    |
| -------------------------- | -------------------------- | -------------------------- | -------------------------- | -------------------------- |
| `accountId`                | *String*                   | :heavy_check_mark:         | The account id.            | 01H8FB90ZRRFWXB4XC2JPJ1D4Y |
| `achWithdrawalId`          | *String*                   | :heavy_check_mark:         | The achWithdrawal id.      | 20230620500726             |

### Response

**[AchWithdrawalsGetAchWithdrawalResponse](../../models/operations/AchWithdrawalsGetAchWithdrawalResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## cancelAchWithdrawal

Cancels an existing ACH withdrawal.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.CancelAchWithdrawalRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AchWithdrawalsCancelAchWithdrawalResponse;
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

        AchWithdrawalsCancelAchWithdrawalResponse res = sdk.achTransfers().cancelAchWithdrawal()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .achWithdrawalId("20230620500726")
                .cancelAchWithdrawalRequestCreate(CancelAchWithdrawalRequestCreate.builder()
                    .name("accounts/01H8FB90ZRRFWXB4XC2JPJ1D4Y/achWithdrawals/20230620500726")
                    .build())
                .call();

        if (res.achWithdrawal().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                       | Type                                                                                            | Required                                                                                        | Description                                                                                     | Example                                                                                         |
| ----------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------- |
| `accountId`                                                                                     | *String*                                                                                        | :heavy_check_mark:                                                                              | The account id.                                                                                 | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                      |
| `achWithdrawalId`                                                                               | *String*                                                                                        | :heavy_check_mark:                                                                              | The achWithdrawal id.                                                                           | 20230620500726                                                                                  |
| `cancelAchWithdrawalRequestCreate`                                                              | [CancelAchWithdrawalRequestCreate](../../models/components/CancelAchWithdrawalRequestCreate.md) | :heavy_check_mark:                                                                              | N/A                                                                                             |                                                                                                 |

### Response

**[AchWithdrawalsCancelAchWithdrawalResponse](../../models/operations/AchWithdrawalsCancelAchWithdrawalResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |