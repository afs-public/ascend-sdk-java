# FeesAndCredits
(*feesAndCredits()*)

## Overview

### Available Operations

* [createFee](#createfee) - Create Fee
* [getFee](#getfee) - Get Fee
* [cancelFee](#cancelfee) - Cancel Fee
* [createCredit](#createcredit) - Create Credit
* [getCredit](#getcredit) - Get Credit
* [cancelCredit](#cancelcredit) - Cancel Credit

## createFee

Create a fee

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.DecimalCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.components.TransfersFeeCreate;
import com.apexfintechsolutions.ascendsdk.models.components.TransfersFeeCreateType;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.FeesCreateFeeResponse;
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

        FeesCreateFeeResponse res = sdk.feesAndCredits().createFee()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .transfersFeeCreate(TransfersFeeCreate.builder()
                    .amount(DecimalCreate.builder()
                        .build())
                    .clientTransferId("179dcd33-49f8-4615-989c-560fb387c4fd")
                    .type(TransfersFeeCreateType.PLATFORM)
                    .build())
                .call();

        if (res.transfersFee().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                           | Type                                                                | Required                                                            | Description                                                         | Example                                                             |
| ------------------------------------------------------------------- | ------------------------------------------------------------------- | ------------------------------------------------------------------- | ------------------------------------------------------------------- | ------------------------------------------------------------------- |
| `accountId`                                                         | *String*                                                            | :heavy_check_mark:                                                  | The account id.                                                     | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                          |
| `transfersFeeCreate`                                                | [TransfersFeeCreate](../../models/components/TransfersFeeCreate.md) | :heavy_check_mark:                                                  | N/A                                                                 |                                                                     |

### Response

**[FeesCreateFeeResponse](../../models/operations/FeesCreateFeeResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 409          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getFee

Retrieve an existing fee

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.FeesGetFeeResponse;
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

        FeesGetFeeResponse res = sdk.feesAndCredits().getFee()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .feeId("20230823123456")
                .call();

        if (res.transfersFee().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                  | Type                       | Required                   | Description                | Example                    |
| -------------------------- | -------------------------- | -------------------------- | -------------------------- | -------------------------- |
| `accountId`                | *String*                   | :heavy_check_mark:         | The account id.            | 01H8FB90ZRRFWXB4XC2JPJ1D4Y |
| `feeId`                    | *String*                   | :heavy_check_mark:         | The fee id.                | 20230823123456             |

### Response

**[FeesGetFeeResponse](../../models/operations/FeesGetFeeResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## cancelFee

Cancel an existing fee

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.CancelFeeRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.FeesCancelFeeResponse;
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

        FeesCancelFeeResponse res = sdk.feesAndCredits().cancelFee()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .feeId("20230823123456")
                .cancelFeeRequestCreate(CancelFeeRequestCreate.builder()
                    .name("accounts/01H8FB90ZRRFWXB4XC2JPJ1D4Y/fees/20230823123456")
                    .build())
                .call();

        if (res.transfersFee().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                   | Type                                                                        | Required                                                                    | Description                                                                 | Example                                                                     |
| --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- |
| `accountId`                                                                 | *String*                                                                    | :heavy_check_mark:                                                          | The account id.                                                             | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                  |
| `feeId`                                                                     | *String*                                                                    | :heavy_check_mark:                                                          | The fee id.                                                                 | 20230823123456                                                              |
| `cancelFeeRequestCreate`                                                    | [CancelFeeRequestCreate](../../models/components/CancelFeeRequestCreate.md) | :heavy_check_mark:                                                          | N/A                                                                         |                                                                             |

### Response

**[FeesCancelFeeResponse](../../models/operations/FeesCancelFeeResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## createCredit

Create a credit

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.DecimalCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.components.TransfersCreditCreate;
import com.apexfintechsolutions.ascendsdk.models.components.TransfersCreditCreateType;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.CreditsCreateCreditResponse;
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

        CreditsCreateCreditResponse res = sdk.feesAndCredits().createCredit()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .transfersCreditCreate(TransfersCreditCreate.builder()
                    .amount(DecimalCreate.builder()
                        .build())
                    .clientTransferId("179dcd33-49f8-4615-989c-560fb387c4fd")
                    .type(TransfersCreditCreateType.PROMOTIONAL)
                    .build())
                .call();

        if (res.transfersCredit().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                 | Type                                                                      | Required                                                                  | Description                                                               | Example                                                                   |
| ------------------------------------------------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------- |
| `accountId`                                                               | *String*                                                                  | :heavy_check_mark:                                                        | The account id.                                                           | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                |
| `transfersCreditCreate`                                                   | [TransfersCreditCreate](../../models/components/TransfersCreditCreate.md) | :heavy_check_mark:                                                        | N/A                                                                       |                                                                           |

### Response

**[CreditsCreateCreditResponse](../../models/operations/CreditsCreateCreditResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 409          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getCredit

Retrieve an existing credit

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.CreditsGetCreditResponse;
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

        CreditsGetCreditResponse res = sdk.feesAndCredits().getCredit()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .creditId("20230823123456")
                .call();

        if (res.transfersCredit().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                  | Type                       | Required                   | Description                | Example                    |
| -------------------------- | -------------------------- | -------------------------- | -------------------------- | -------------------------- |
| `accountId`                | *String*                   | :heavy_check_mark:         | The account id.            | 01H8FB90ZRRFWXB4XC2JPJ1D4Y |
| `creditId`                 | *String*                   | :heavy_check_mark:         | The credit id.             | 20230823123456             |

### Response

**[CreditsGetCreditResponse](../../models/operations/CreditsGetCreditResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## cancelCredit

Cancel an existing credit

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.CancelCreditRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.CreditsCancelCreditResponse;
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

        CreditsCancelCreditResponse res = sdk.feesAndCredits().cancelCredit()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .creditId("20230823123456")
                .cancelCreditRequestCreate(CancelCreditRequestCreate.builder()
                    .name("accounts/01H8FB90ZRRFWXB4XC2JPJ1D4Y/credits/20230823123456")
                    .build())
                .call();

        if (res.transfersCredit().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                         | Type                                                                              | Required                                                                          | Description                                                                       | Example                                                                           |
| --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- |
| `accountId`                                                                       | *String*                                                                          | :heavy_check_mark:                                                                | The account id.                                                                   | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                        |
| `creditId`                                                                        | *String*                                                                          | :heavy_check_mark:                                                                | The credit id.                                                                    | 20230823123456                                                                    |
| `cancelCreditRequestCreate`                                                       | [CancelCreditRequestCreate](../../models/components/CancelCreditRequestCreate.md) | :heavy_check_mark:                                                                | N/A                                                                               |                                                                                   |

### Response

**[CreditsCancelCreditResponse](../../models/operations/CreditsCancelCreditResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |