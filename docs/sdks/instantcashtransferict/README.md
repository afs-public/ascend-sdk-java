# InstantCashTransferICT
(*instantCashTransferICT()*)

## Overview

### Available Operations

* [createIctDeposit](#createictdeposit) - Create ICT Deposit
* [getIctDeposit](#getictdeposit) - Get ICT Deposit
* [cancelIctDeposit](#cancelictdeposit) - Cancel ICT Deposit
* [createIctWithdrawal](#createictwithdrawal) - Create ICT Withdrawal
* [getIctWithdrawal](#getictwithdrawal) - Get ICT Withdrawal
* [cancelIctWithdrawal](#cancelictwithdrawal) - Cancel ICT Withdrawal
* [locateIctReport](#locateictreport) - Locate ICT Report

## createIctDeposit

Creates an ICT deposit

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.DecimalCreate;
import com.apexfintechsolutions.ascendsdk.models.components.IctDepositCreate;
import com.apexfintechsolutions.ascendsdk.models.components.IctDepositTravelRuleCreate;
import com.apexfintechsolutions.ascendsdk.models.components.InstitutionCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Program;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.IctDepositsCreateIctDepositResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
                    .build())
            .build();

        IctDepositsCreateIctDepositResponse res = sdk.instantCashTransferICT().createIctDeposit()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .ictDepositCreate(IctDepositCreate.builder()
                    .amount(DecimalCreate.builder()
                        .build())
                    .clientTransferId("ABC-123")
                    .program(Program.DEPOSIT_ONLY)
                    .travelRule(IctDepositTravelRuleCreate.builder()
                        .originatingInstitution(InstitutionCreate.builder()
                            .accountId("<id>")
                            .title("<value>")
                            .build())
                        .build())
                    .build())
                .call();

        if (res.ictDeposit().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                       | Type                                                            | Required                                                        | Description                                                     | Example                                                         |
| --------------------------------------------------------------- | --------------------------------------------------------------- | --------------------------------------------------------------- | --------------------------------------------------------------- | --------------------------------------------------------------- |
| `accountId`                                                     | *String*                                                        | :heavy_check_mark:                                              | The account id.                                                 | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                      |
| `ictDepositCreate`                                              | [IctDepositCreate](../../models/components/IctDepositCreate.md) | :heavy_check_mark:                                              | N/A                                                             |                                                                 |

### Response

**[IctDepositsCreateIctDepositResponse](../../models/operations/IctDepositsCreateIctDepositResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 409          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getIctDeposit

Gets an existing ICT deposit

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.IctDepositsGetIctDepositResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
                    .build())
            .build();

        IctDepositsGetIctDepositResponse res = sdk.instantCashTransferICT().getIctDeposit()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .ictDepositId("20240321000472")
                .call();

        if (res.ictDeposit().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                  | Type                       | Required                   | Description                | Example                    |
| -------------------------- | -------------------------- | -------------------------- | -------------------------- | -------------------------- |
| `accountId`                | *String*                   | :heavy_check_mark:         | The account id.            | 01H8FB90ZRRFWXB4XC2JPJ1D4Y |
| `ictDepositId`             | *String*                   | :heavy_check_mark:         | The ictDeposit id.         | 20240321000472             |

### Response

**[IctDepositsGetIctDepositResponse](../../models/operations/IctDepositsGetIctDepositResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## cancelIctDeposit

Cancels an existing ICT deposit

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.CancelIctDepositRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.IctDepositsCancelIctDepositResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
                    .build())
            .build();

        IctDepositsCancelIctDepositResponse res = sdk.instantCashTransferICT().cancelIctDeposit()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .ictDepositId("20240321000472")
                .cancelIctDepositRequestCreate(CancelIctDepositRequestCreate.builder()
                    .name("accounts/01H8FB90ZRRFWXB4XC2JPJ1D4Y/ictDeposits/20240321000472")
                    .build())
                .call();

        if (res.ictDeposit().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                 | Type                                                                                      | Required                                                                                  | Description                                                                               | Example                                                                                   |
| ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| `accountId`                                                                               | *String*                                                                                  | :heavy_check_mark:                                                                        | The account id.                                                                           | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                |
| `ictDepositId`                                                                            | *String*                                                                                  | :heavy_check_mark:                                                                        | The ictDeposit id.                                                                        | 20240321000472                                                                            |
| `cancelIctDepositRequestCreate`                                                           | [CancelIctDepositRequestCreate](../../models/components/CancelIctDepositRequestCreate.md) | :heavy_check_mark:                                                                        | N/A                                                                                       |                                                                                           |

### Response

**[IctDepositsCancelIctDepositResponse](../../models/operations/IctDepositsCancelIctDepositResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## createIctWithdrawal

Creates an ICT withdrawal

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.IctWithdrawalCreate;
import com.apexfintechsolutions.ascendsdk.models.components.IctWithdrawalCreateProgram;
import com.apexfintechsolutions.ascendsdk.models.components.IctWithdrawalTravelRuleCreate;
import com.apexfintechsolutions.ascendsdk.models.components.InstitutionCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.IctWithdrawalsCreateIctWithdrawalResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
                    .build())
            .build();

        IctWithdrawalsCreateIctWithdrawalResponse res = sdk.instantCashTransferICT().createIctWithdrawal()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .ictWithdrawalCreate(IctWithdrawalCreate.builder()
                    .clientTransferId("20230817000319")
                    .program(IctWithdrawalCreateProgram.BROKER_PARTNER)
                    .travelRule(IctWithdrawalTravelRuleCreate.builder()
                        .recipientInstitution(InstitutionCreate.builder()
                            .accountId("<id>")
                            .title("<value>")
                            .build())
                        .build())
                    .build())
                .call();

        if (res.ictWithdrawal().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                             | Type                                                                  | Required                                                              | Description                                                           | Example                                                               |
| --------------------------------------------------------------------- | --------------------------------------------------------------------- | --------------------------------------------------------------------- | --------------------------------------------------------------------- | --------------------------------------------------------------------- |
| `accountId`                                                           | *String*                                                              | :heavy_check_mark:                                                    | The account id.                                                       | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                            |
| `ictWithdrawalCreate`                                                 | [IctWithdrawalCreate](../../models/components/IctWithdrawalCreate.md) | :heavy_check_mark:                                                    | N/A                                                                   |                                                                       |

### Response

**[IctWithdrawalsCreateIctWithdrawalResponse](../../models/operations/IctWithdrawalsCreateIctWithdrawalResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 409          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getIctWithdrawal

Gets an existing ICT withdrawal

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.IctWithdrawalsGetIctWithdrawalResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
                    .build())
            .build();

        IctWithdrawalsGetIctWithdrawalResponse res = sdk.instantCashTransferICT().getIctWithdrawal()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .ictWithdrawalId("20240321000472")
                .call();

        if (res.ictWithdrawal().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                  | Type                       | Required                   | Description                | Example                    |
| -------------------------- | -------------------------- | -------------------------- | -------------------------- | -------------------------- |
| `accountId`                | *String*                   | :heavy_check_mark:         | The account id.            | 01H8FB90ZRRFWXB4XC2JPJ1D4Y |
| `ictWithdrawalId`          | *String*                   | :heavy_check_mark:         | The ictWithdrawal id.      | 20240321000472             |

### Response

**[IctWithdrawalsGetIctWithdrawalResponse](../../models/operations/IctWithdrawalsGetIctWithdrawalResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## cancelIctWithdrawal

Cancels an existing ICT withdrawal

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.CancelIctWithdrawalRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.IctWithdrawalsCancelIctWithdrawalResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
                    .build())
            .build();

        IctWithdrawalsCancelIctWithdrawalResponse res = sdk.instantCashTransferICT().cancelIctWithdrawal()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .ictWithdrawalId("20240321000472")
                .cancelIctWithdrawalRequestCreate(CancelIctWithdrawalRequestCreate.builder()
                    .name("accounts/01H8FB90ZRRFWXB4XC2JPJ1D4Y/ictWithdrawals/20240321000472")
                    .build())
                .call();

        if (res.ictWithdrawal().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                       | Type                                                                                            | Required                                                                                        | Description                                                                                     | Example                                                                                         |
| ----------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------- |
| `accountId`                                                                                     | *String*                                                                                        | :heavy_check_mark:                                                                              | The account id.                                                                                 | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                      |
| `ictWithdrawalId`                                                                               | *String*                                                                                        | :heavy_check_mark:                                                                              | The ictWithdrawal id.                                                                           | 20240321000472                                                                                  |
| `cancelIctWithdrawalRequestCreate`                                                              | [CancelIctWithdrawalRequestCreate](../../models/components/CancelIctWithdrawalRequestCreate.md) | :heavy_check_mark:                                                                              | N/A                                                                                             |                                                                                                 |

### Response

**[IctWithdrawalsCancelIctWithdrawalResponse](../../models/operations/IctWithdrawalsCancelIctWithdrawalResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## locateIctReport

Returns a signed link pointing to a recon report file for a specific ICT batch.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.DateCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.IctReconReportsLocateIctReportResponse;
import com.apexfintechsolutions.ascendsdk.models.operations.ProgramDateFilterProgram;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
                    .build())
            .build();

        IctReconReportsLocateIctReportResponse res = sdk.instantCashTransferICT().locateIctReport()
                .correspondentId("01H8MCDXH4HYJJAV921BDKCC83")
                .batchId("24114.108.2b2c1.001")
                .programDateFilterProgram(ProgramDateFilterProgram.BROKER_PARTNER)
                .programDateFilterProcessDate(DateCreate.builder()
                    .build())
                .call();

        if (res.locateIctReportResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                  | Type                                                                                       | Required                                                                                   | Description                                                                                | Example                                                                                    |
| ------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------ |
| `correspondentId`                                                                          | *String*                                                                                   | :heavy_check_mark:                                                                         | The correspondent id.                                                                      | 01H8MCDXH4HYJJAV921BDKCC83                                                                 |
| `batchId`                                                                                  | *Optional\<String>*                                                                        | :heavy_minus_sign:                                                                         | The id of the ICT batch for which to locate the report.                                    | 24114.108.2b2c1.001                                                                        |
| `programDateFilterProgram`                                                                 | [Optional\<ProgramDateFilterProgram>](../../models/operations/ProgramDateFilterProgram.md) | :heavy_minus_sign:                                                                         | The ICT program for which to locate the report.                                            | BROKER_PARTNER                                                                             |
| `programDateFilterProcessDate`                                                             | [Optional\<DateCreate>](../../models/components/DateCreate.md)                             | :heavy_minus_sign:                                                                         | The process date for which to locate the report.                                           | {<br/>"process_date": {<br/>"day": 30,<br/>"month": 9,<br/>"year": 2023<br/>}<br/>}        |

### Response

**[IctReconReportsLocateIctReportResponse](../../models/operations/IctReconReportsLocateIctReportResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |