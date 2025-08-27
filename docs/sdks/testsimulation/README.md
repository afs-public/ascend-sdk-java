# TestSimulation
(*testSimulation()*)

## Overview

### Available Operations

* [simulateCreateCheckDeposit](#simulatecreatecheckdeposit) - Simulate Check Deposit Creation
* [forceApproveAchDeposit](#forceapproveachdeposit) - ACH Deposit Approval
* [forceNocAchDeposit](#forcenocachdeposit) - NOC for a Deposit
* [forceRejectAchDeposit](#forcerejectachdeposit) - ACH Deposit Rejection
* [forceReturnAchDeposit](#forcereturnachdeposit) - ACH Deposit Return
* [forceApproveAchWithdrawal](#forceapproveachwithdrawal) - ACH Withdrawal Approval
* [forceNocAchWithdrawal](#forcenocachwithdrawal) - ACH Withdrawal NOC
* [forceRejectAchWithdrawal](#forcerejectachwithdrawal) - ACH Withdrawal Rejection
* [forceReturnAchWithdrawal](#forcereturnachwithdrawal) - ACH Withdrawal Return
* [getMicroDepositAmounts](#getmicrodepositamounts) - Get Relationship Micro Deposit Verification
* [forceApproveIctDeposit](#forceapproveictdeposit) - Force Approve ICT Deposit
* [forceRejectIctDeposit](#forcerejectictdeposit) - Force Reject ICT Deposit
* [forceApproveIctWithdrawal](#forceapproveictwithdrawal) - Force Approve ICT Withdrawal
* [forceRejectIctWithdrawal](#forcerejectictwithdrawal) - Force Reject ICT Withdrawal
* [forceApproveWireWithdrawal](#forceapprovewirewithdrawal) - Force Approve Wire Withdrawal
* [forceRejectWireWithdrawal](#forcerejectwirewithdrawal) - Force Reject Wire Withdrawal
* [forceApproveCashJournal](#forceapprovecashjournal) - Force Approve Cash Journal
* [forceRejectCashJournal](#forcerejectcashjournal) - Force Reject Cash Journal

## simulateCreateCheckDeposit

Creates a check deposit for a specific account FOR TESTING ONLY!

### Example Usage

<!-- UsageSnippet language="java" operationID="CheckDeposits_SimulateCreateCheckDeposit" method="post" path="/transfers/v1/accounts/{account_id}/checkDeposits:simulate" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.CheckDepositsSimulateCreateCheckDepositResponse;
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

        CheckDepositsSimulateCreateCheckDepositResponse res = sdk.testSimulation().simulateCreateCheckDeposit()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .simulateCreateCheckDepositRequestCreate(SimulateCreateCheckDepositRequestCreate.builder()
                    .amount(DecimalCreate.builder()
                        .build())
                    .parent("accounts/01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                    .build())
                .call();

        if (res.checkDeposit().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                     | Type                                                                                                          | Required                                                                                                      | Description                                                                                                   | Example                                                                                                       |
| ------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                   | *String*                                                                                                      | :heavy_check_mark:                                                                                            | The account id.                                                                                               | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                                    |
| `simulateCreateCheckDepositRequestCreate`                                                                     | [SimulateCreateCheckDepositRequestCreate](../../models/components/SimulateCreateCheckDepositRequestCreate.md) | :heavy_check_mark:                                                                                            | N/A                                                                                                           |                                                                                                               |

### Response

**[CheckDepositsSimulateCreateCheckDepositResponse](../../models/operations/CheckDepositsSimulateCreateCheckDepositResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## forceApproveAchDeposit

Forces approval of an existing ACH deposit that is pending review. FOR TESTING ONLY!

### Example Usage

<!-- UsageSnippet language="java" operationID="AchDeposits_ForceApproveAchDeposit" method="post" path="/transfers/v1/accounts/{account_id}/achDeposits/{achDeposit_id}:forceApprove" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AchDepositsForceApproveAchDepositResponse;
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

        AchDepositsForceApproveAchDepositResponse res = sdk.testSimulation().forceApproveAchDeposit()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .achDepositId("20230817000319")
                .forceApproveAchDepositRequestCreate(ForceApproveAchDepositRequestCreate.builder()
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

| Parameter                                                                                             | Type                                                                                                  | Required                                                                                              | Description                                                                                           | Example                                                                                               |
| ----------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                           | *String*                                                                                              | :heavy_check_mark:                                                                                    | The account id.                                                                                       | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                            |
| `achDepositId`                                                                                        | *String*                                                                                              | :heavy_check_mark:                                                                                    | The achDeposit id.                                                                                    | 20230817000319                                                                                        |
| `forceApproveAchDepositRequestCreate`                                                                 | [ForceApproveAchDepositRequestCreate](../../models/components/ForceApproveAchDepositRequestCreate.md) | :heavy_check_mark:                                                                                    | N/A                                                                                                   |                                                                                                       |

### Response

**[AchDepositsForceApproveAchDepositResponse](../../models/operations/AchDepositsForceApproveAchDepositResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## forceNocAchDeposit

Forces a Nacha notice of change (NOC) on a completed ACH deposit. FOR TESTING ONLY!

### Example Usage

<!-- UsageSnippet language="java" operationID="AchDeposits_ForceNocAchDeposit" method="post" path="/transfers/v1/accounts/{account_id}/achDeposits/{achDeposit_id}:forceNoc" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AchDepositsForceNocAchDepositResponse;
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

        AchDepositsForceNocAchDepositResponse res = sdk.testSimulation().forceNocAchDeposit()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .achDepositId("20230817000319")
                .forceNocAchDepositRequestCreate(ForceNocAchDepositRequestCreate.builder()
                    .nachaNoc(NachaNocCreate.builder()
                        .code(Code.C01)
                        .build())
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

| Parameter                                                                                     | Type                                                                                          | Required                                                                                      | Description                                                                                   | Example                                                                                       |
| --------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------- |
| `accountId`                                                                                   | *String*                                                                                      | :heavy_check_mark:                                                                            | The account id.                                                                               | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                    |
| `achDepositId`                                                                                | *String*                                                                                      | :heavy_check_mark:                                                                            | The achDeposit id.                                                                            | 20230817000319                                                                                |
| `forceNocAchDepositRequestCreate`                                                             | [ForceNocAchDepositRequestCreate](../../models/components/ForceNocAchDepositRequestCreate.md) | :heavy_check_mark:                                                                            | N/A                                                                                           |                                                                                               |

### Response

**[AchDepositsForceNocAchDepositResponse](../../models/operations/AchDepositsForceNocAchDepositResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## forceRejectAchDeposit

Forces rejection of an existing ACH deposit that is pending review. FOR TESTING ONLY!

### Example Usage

<!-- UsageSnippet language="java" operationID="AchDeposits_ForceRejectAchDeposit" method="post" path="/transfers/v1/accounts/{account_id}/achDeposits/{achDeposit_id}:forceReject" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AchDepositsForceRejectAchDepositResponse;
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

        AchDepositsForceRejectAchDepositResponse res = sdk.testSimulation().forceRejectAchDeposit()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .achDepositId("20230817000319")
                .forceRejectAchDepositRequestCreate(ForceRejectAchDepositRequestCreate.builder()
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

| Parameter                                                                                           | Type                                                                                                | Required                                                                                            | Description                                                                                         | Example                                                                                             |
| --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                         | *String*                                                                                            | :heavy_check_mark:                                                                                  | The account id.                                                                                     | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                          |
| `achDepositId`                                                                                      | *String*                                                                                            | :heavy_check_mark:                                                                                  | The achDeposit id.                                                                                  | 20230817000319                                                                                      |
| `forceRejectAchDepositRequestCreate`                                                                | [ForceRejectAchDepositRequestCreate](../../models/components/ForceRejectAchDepositRequestCreate.md) | :heavy_check_mark:                                                                                  | N/A                                                                                                 |                                                                                                     |

### Response

**[AchDepositsForceRejectAchDepositResponse](../../models/operations/AchDepositsForceRejectAchDepositResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## forceReturnAchDeposit

Forces a Nacha return on a completed ACH deposit. FOR TESTING ONLY!

### Example Usage

<!-- UsageSnippet language="java" operationID="AchDeposits_ForceReturnAchDeposit" method="post" path="/transfers/v1/accounts/{account_id}/achDeposits/{achDeposit_id}:forceReturn" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AchDepositsForceReturnAchDepositResponse;
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

        AchDepositsForceReturnAchDepositResponse res = sdk.testSimulation().forceReturnAchDeposit()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .achDepositId("20230817000319")
                .forceReturnAchDepositRequestCreate(ForceReturnAchDepositRequestCreate.builder()
                    .nachaReturn(NachaReturnCreate.builder()
                        .code(NachaReturnCreateCode.R13)
                        .build())
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

| Parameter                                                                                           | Type                                                                                                | Required                                                                                            | Description                                                                                         | Example                                                                                             |
| --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                         | *String*                                                                                            | :heavy_check_mark:                                                                                  | The account id.                                                                                     | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                          |
| `achDepositId`                                                                                      | *String*                                                                                            | :heavy_check_mark:                                                                                  | The achDeposit id.                                                                                  | 20230817000319                                                                                      |
| `forceReturnAchDepositRequestCreate`                                                                | [ForceReturnAchDepositRequestCreate](../../models/components/ForceReturnAchDepositRequestCreate.md) | :heavy_check_mark:                                                                                  | N/A                                                                                                 |                                                                                                     |

### Response

**[AchDepositsForceReturnAchDepositResponse](../../models/operations/AchDepositsForceReturnAchDepositResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## forceApproveAchWithdrawal

Forces approval of an existing ACH withdrawal that is pending review. FOR TESTING ONLY!

### Example Usage

<!-- UsageSnippet language="java" operationID="AchWithdrawals_ForceApproveAchWithdrawal" method="post" path="/transfers/v1/accounts/{account_id}/achWithdrawals/{achWithdrawal_id}:forceApprove" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AchWithdrawalsForceApproveAchWithdrawalResponse;
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

        AchWithdrawalsForceApproveAchWithdrawalResponse res = sdk.testSimulation().forceApproveAchWithdrawal()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .achWithdrawalId("20230620500726")
                .forceApproveAchWithdrawalRequestCreate(ForceApproveAchWithdrawalRequestCreate.builder()
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

| Parameter                                                                                                   | Type                                                                                                        | Required                                                                                                    | Description                                                                                                 | Example                                                                                                     |
| ----------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                 | *String*                                                                                                    | :heavy_check_mark:                                                                                          | The account id.                                                                                             | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                                  |
| `achWithdrawalId`                                                                                           | *String*                                                                                                    | :heavy_check_mark:                                                                                          | The achWithdrawal id.                                                                                       | 20230620500726                                                                                              |
| `forceApproveAchWithdrawalRequestCreate`                                                                    | [ForceApproveAchWithdrawalRequestCreate](../../models/components/ForceApproveAchWithdrawalRequestCreate.md) | :heavy_check_mark:                                                                                          | N/A                                                                                                         |                                                                                                             |

### Response

**[AchWithdrawalsForceApproveAchWithdrawalResponse](../../models/operations/AchWithdrawalsForceApproveAchWithdrawalResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## forceNocAchWithdrawal

Forces a Nacha notice of change (NOC) on a completed ACH withdrawal. FOR TESTING ONLY!

### Example Usage

<!-- UsageSnippet language="java" operationID="AchWithdrawals_ForceNocAchWithdrawal" method="post" path="/transfers/v1/accounts/{account_id}/achWithdrawals/{achWithdrawal_id}:forceNoc" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AchWithdrawalsForceNocAchWithdrawalResponse;
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

        AchWithdrawalsForceNocAchWithdrawalResponse res = sdk.testSimulation().forceNocAchWithdrawal()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .achWithdrawalId("20230620500726")
                .forceNocAchWithdrawalRequestCreate(ForceNocAchWithdrawalRequestCreate.builder()
                    .nachaNoc(NachaNocCreate.builder()
                        .code(Code.C01)
                        .build())
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

| Parameter                                                                                           | Type                                                                                                | Required                                                                                            | Description                                                                                         | Example                                                                                             |
| --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                         | *String*                                                                                            | :heavy_check_mark:                                                                                  | The account id.                                                                                     | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                          |
| `achWithdrawalId`                                                                                   | *String*                                                                                            | :heavy_check_mark:                                                                                  | The achWithdrawal id.                                                                               | 20230620500726                                                                                      |
| `forceNocAchWithdrawalRequestCreate`                                                                | [ForceNocAchWithdrawalRequestCreate](../../models/components/ForceNocAchWithdrawalRequestCreate.md) | :heavy_check_mark:                                                                                  | N/A                                                                                                 |                                                                                                     |

### Response

**[AchWithdrawalsForceNocAchWithdrawalResponse](../../models/operations/AchWithdrawalsForceNocAchWithdrawalResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## forceRejectAchWithdrawal

Forces rejection of an existing ACH withdrawal that is pending review. FOR TESTING ONLY!

### Example Usage

<!-- UsageSnippet language="java" operationID="AchWithdrawals_ForceRejectAchWithdrawal" method="post" path="/transfers/v1/accounts/{account_id}/achWithdrawals/{achWithdrawal_id}:forceReject" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AchWithdrawalsForceRejectAchWithdrawalResponse;
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

        AchWithdrawalsForceRejectAchWithdrawalResponse res = sdk.testSimulation().forceRejectAchWithdrawal()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .achWithdrawalId("20230620500726")
                .forceRejectAchWithdrawalRequestCreate(ForceRejectAchWithdrawalRequestCreate.builder()
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

| Parameter                                                                                                 | Type                                                                                                      | Required                                                                                                  | Description                                                                                               | Example                                                                                                   |
| --------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                               | *String*                                                                                                  | :heavy_check_mark:                                                                                        | The account id.                                                                                           | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                                |
| `achWithdrawalId`                                                                                         | *String*                                                                                                  | :heavy_check_mark:                                                                                        | The achWithdrawal id.                                                                                     | 20230620500726                                                                                            |
| `forceRejectAchWithdrawalRequestCreate`                                                                   | [ForceRejectAchWithdrawalRequestCreate](../../models/components/ForceRejectAchWithdrawalRequestCreate.md) | :heavy_check_mark:                                                                                        | N/A                                                                                                       |                                                                                                           |

### Response

**[AchWithdrawalsForceRejectAchWithdrawalResponse](../../models/operations/AchWithdrawalsForceRejectAchWithdrawalResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## forceReturnAchWithdrawal

Forces a Nacha return on a completed ACH withdrawal. FOR TESTING ONLY!

### Example Usage

<!-- UsageSnippet language="java" operationID="AchWithdrawals_ForceReturnAchWithdrawal" method="post" path="/transfers/v1/accounts/{account_id}/achWithdrawals/{achWithdrawal_id}:forceReturn" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AchWithdrawalsForceReturnAchWithdrawalResponse;
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

        AchWithdrawalsForceReturnAchWithdrawalResponse res = sdk.testSimulation().forceReturnAchWithdrawal()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .achWithdrawalId("20230620500726")
                .forceReturnAchWithdrawalRequestCreate(ForceReturnAchWithdrawalRequestCreate.builder()
                    .nachaReturn(NachaReturnCreate.builder()
                        .code(NachaReturnCreateCode.R15)
                        .build())
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

| Parameter                                                                                                 | Type                                                                                                      | Required                                                                                                  | Description                                                                                               | Example                                                                                                   |
| --------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                               | *String*                                                                                                  | :heavy_check_mark:                                                                                        | The account id.                                                                                           | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                                |
| `achWithdrawalId`                                                                                         | *String*                                                                                                  | :heavy_check_mark:                                                                                        | The achWithdrawal id.                                                                                     | 20230620500726                                                                                            |
| `forceReturnAchWithdrawalRequestCreate`                                                                   | [ForceReturnAchWithdrawalRequestCreate](../../models/components/ForceReturnAchWithdrawalRequestCreate.md) | :heavy_check_mark:                                                                                        | N/A                                                                                                       |                                                                                                           |

### Response

**[AchWithdrawalsForceReturnAchWithdrawalResponse](../../models/operations/AchWithdrawalsForceReturnAchWithdrawalResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getMicroDepositAmounts

Gets micro deposit amounts for bank relationships with the `MICRO_DEPOSIT` verification method. FOR TESTING ONLY!

### Example Usage

<!-- UsageSnippet language="java" operationID="BankRelationships_GetMicroDepositAmounts" method="get" path="/transfers/v1/accounts/{account_id}/bankRelationships/{bankRelationship_id}:getMicroDepositAmounts" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BankRelationshipsGetMicroDepositAmountsResponse;
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

        BankRelationshipsGetMicroDepositAmountsResponse res = sdk.testSimulation().getMicroDepositAmounts()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .bankRelationshipId("651ef9de0dee00240813e60e")
                .call();

        if (res.microDepositAmounts().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                  | Type                       | Required                   | Description                | Example                    |
| -------------------------- | -------------------------- | -------------------------- | -------------------------- | -------------------------- |
| `accountId`                | *String*                   | :heavy_check_mark:         | The account id.            | 01H8FB90ZRRFWXB4XC2JPJ1D4Y |
| `bankRelationshipId`       | *String*                   | :heavy_check_mark:         | The bankRelationship id.   | 651ef9de0dee00240813e60e   |

### Response

**[BankRelationshipsGetMicroDepositAmountsResponse](../../models/operations/BankRelationshipsGetMicroDepositAmountsResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## forceApproveIctDeposit

Forces an approval on an existing ICT deposit pending review - FOR TESTING

### Example Usage

<!-- UsageSnippet language="java" operationID="IctDeposits_ForceApproveIctDeposit" method="post" path="/transfers/v1/accounts/{account_id}/ictDeposits/{ictDeposit_id}:forceApprove" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.IctDepositsForceApproveIctDepositResponse;
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

        IctDepositsForceApproveIctDepositResponse res = sdk.testSimulation().forceApproveIctDeposit()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .ictDepositId("20240321000472")
                .forceApproveIctDepositRequestCreate(ForceApproveIctDepositRequestCreate.builder()
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

| Parameter                                                                                             | Type                                                                                                  | Required                                                                                              | Description                                                                                           | Example                                                                                               |
| ----------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                           | *String*                                                                                              | :heavy_check_mark:                                                                                    | The account id.                                                                                       | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                            |
| `ictDepositId`                                                                                        | *String*                                                                                              | :heavy_check_mark:                                                                                    | The ictDeposit id.                                                                                    | 20240321000472                                                                                        |
| `forceApproveIctDepositRequestCreate`                                                                 | [ForceApproveIctDepositRequestCreate](../../models/components/ForceApproveIctDepositRequestCreate.md) | :heavy_check_mark:                                                                                    | N/A                                                                                                   |                                                                                                       |

### Response

**[IctDepositsForceApproveIctDepositResponse](../../models/operations/IctDepositsForceApproveIctDepositResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## forceRejectIctDeposit

Forces a rejection on an existing ICT deposit pending review - FOR TESTING

### Example Usage

<!-- UsageSnippet language="java" operationID="IctDeposits_ForceRejectIctDeposit" method="post" path="/transfers/v1/accounts/{account_id}/ictDeposits/{ictDeposit_id}:forceReject" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.IctDepositsForceRejectIctDepositResponse;
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

        IctDepositsForceRejectIctDepositResponse res = sdk.testSimulation().forceRejectIctDeposit()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .ictDepositId("20240321000472")
                .forceRejectIctDepositRequestCreate(ForceRejectIctDepositRequestCreate.builder()
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

| Parameter                                                                                           | Type                                                                                                | Required                                                                                            | Description                                                                                         | Example                                                                                             |
| --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                         | *String*                                                                                            | :heavy_check_mark:                                                                                  | The account id.                                                                                     | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                          |
| `ictDepositId`                                                                                      | *String*                                                                                            | :heavy_check_mark:                                                                                  | The ictDeposit id.                                                                                  | 20240321000472                                                                                      |
| `forceRejectIctDepositRequestCreate`                                                                | [ForceRejectIctDepositRequestCreate](../../models/components/ForceRejectIctDepositRequestCreate.md) | :heavy_check_mark:                                                                                  | N/A                                                                                                 |                                                                                                     |

### Response

**[IctDepositsForceRejectIctDepositResponse](../../models/operations/IctDepositsForceRejectIctDepositResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## forceApproveIctWithdrawal

Forces an approval on an existing ICT withdrawal pending review - FOR TESTING

### Example Usage

<!-- UsageSnippet language="java" operationID="IctWithdrawals_ForceApproveIctWithdrawal" method="post" path="/transfers/v1/accounts/{account_id}/ictWithdrawals/{ictWithdrawal_id}:forceApprove" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.IctWithdrawalsForceApproveIctWithdrawalResponse;
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

        IctWithdrawalsForceApproveIctWithdrawalResponse res = sdk.testSimulation().forceApproveIctWithdrawal()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .ictWithdrawalId("20240321000472")
                .forceApproveIctWithdrawalRequestCreate(ForceApproveIctWithdrawalRequestCreate.builder()
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

| Parameter                                                                                                   | Type                                                                                                        | Required                                                                                                    | Description                                                                                                 | Example                                                                                                     |
| ----------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                 | *String*                                                                                                    | :heavy_check_mark:                                                                                          | The account id.                                                                                             | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                                  |
| `ictWithdrawalId`                                                                                           | *String*                                                                                                    | :heavy_check_mark:                                                                                          | The ictWithdrawal id.                                                                                       | 20240321000472                                                                                              |
| `forceApproveIctWithdrawalRequestCreate`                                                                    | [ForceApproveIctWithdrawalRequestCreate](../../models/components/ForceApproveIctWithdrawalRequestCreate.md) | :heavy_check_mark:                                                                                          | N/A                                                                                                         |                                                                                                             |

### Response

**[IctWithdrawalsForceApproveIctWithdrawalResponse](../../models/operations/IctWithdrawalsForceApproveIctWithdrawalResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## forceRejectIctWithdrawal

Forces a rejection on an existing ICT withdrawal pending review - FOR TESTING

### Example Usage

<!-- UsageSnippet language="java" operationID="IctWithdrawals_ForceRejectIctWithdrawal" method="post" path="/transfers/v1/accounts/{account_id}/ictWithdrawals/{ictWithdrawal_id}:forceReject" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.IctWithdrawalsForceRejectIctWithdrawalResponse;
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

        IctWithdrawalsForceRejectIctWithdrawalResponse res = sdk.testSimulation().forceRejectIctWithdrawal()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .ictWithdrawalId("20240321000472")
                .forceRejectIctWithdrawalRequestCreate(ForceRejectIctWithdrawalRequestCreate.builder()
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

| Parameter                                                                                                 | Type                                                                                                      | Required                                                                                                  | Description                                                                                               | Example                                                                                                   |
| --------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                               | *String*                                                                                                  | :heavy_check_mark:                                                                                        | The account id.                                                                                           | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                                |
| `ictWithdrawalId`                                                                                         | *String*                                                                                                  | :heavy_check_mark:                                                                                        | The ictWithdrawal id.                                                                                     | 20240321000472                                                                                            |
| `forceRejectIctWithdrawalRequestCreate`                                                                   | [ForceRejectIctWithdrawalRequestCreate](../../models/components/ForceRejectIctWithdrawalRequestCreate.md) | :heavy_check_mark:                                                                                        | N/A                                                                                                       |                                                                                                           |

### Response

**[IctWithdrawalsForceRejectIctWithdrawalResponse](../../models/operations/IctWithdrawalsForceRejectIctWithdrawalResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## forceApproveWireWithdrawal

Forces an approval on an existing wire withdrawal pending review - FOR TESTING

### Example Usage

<!-- UsageSnippet language="java" operationID="WireWithdrawals_ForceApproveWireWithdrawal" method="post" path="/transfers/v1/accounts/{account_id}/wireWithdrawals/{wireWithdrawal_id}:forceApprove" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.WireWithdrawalsForceApproveWireWithdrawalResponse;
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

        WireWithdrawalsForceApproveWireWithdrawalResponse res = sdk.testSimulation().forceApproveWireWithdrawal()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .wireWithdrawalId("20230817000319")
                .forceApproveWireWithdrawalRequestCreate(ForceApproveWireWithdrawalRequestCreate.builder()
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

| Parameter                                                                                                     | Type                                                                                                          | Required                                                                                                      | Description                                                                                                   | Example                                                                                                       |
| ------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                   | *String*                                                                                                      | :heavy_check_mark:                                                                                            | The account id.                                                                                               | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                                    |
| `wireWithdrawalId`                                                                                            | *String*                                                                                                      | :heavy_check_mark:                                                                                            | The wireWithdrawal id.                                                                                        | 20230817000319                                                                                                |
| `forceApproveWireWithdrawalRequestCreate`                                                                     | [ForceApproveWireWithdrawalRequestCreate](../../models/components/ForceApproveWireWithdrawalRequestCreate.md) | :heavy_check_mark:                                                                                            | N/A                                                                                                           |                                                                                                               |

### Response

**[WireWithdrawalsForceApproveWireWithdrawalResponse](../../models/operations/WireWithdrawalsForceApproveWireWithdrawalResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## forceRejectWireWithdrawal

Forces a rejection on an existing wire withdrawal pending review - FOR TESTING

### Example Usage

<!-- UsageSnippet language="java" operationID="WireWithdrawals_ForceRejectWireWithdrawal" method="post" path="/transfers/v1/accounts/{account_id}/wireWithdrawals/{wireWithdrawal_id}:forceReject" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.WireWithdrawalsForceRejectWireWithdrawalResponse;
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

        WireWithdrawalsForceRejectWireWithdrawalResponse res = sdk.testSimulation().forceRejectWireWithdrawal()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .wireWithdrawalId("20230817000319")
                .forceRejectWireWithdrawalRequestCreate(ForceRejectWireWithdrawalRequestCreate.builder()
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

| Parameter                                                                                                   | Type                                                                                                        | Required                                                                                                    | Description                                                                                                 | Example                                                                                                     |
| ----------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                 | *String*                                                                                                    | :heavy_check_mark:                                                                                          | The account id.                                                                                             | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                                  |
| `wireWithdrawalId`                                                                                          | *String*                                                                                                    | :heavy_check_mark:                                                                                          | The wireWithdrawal id.                                                                                      | 20230817000319                                                                                              |
| `forceRejectWireWithdrawalRequestCreate`                                                                    | [ForceRejectWireWithdrawalRequestCreate](../../models/components/ForceRejectWireWithdrawalRequestCreate.md) | :heavy_check_mark:                                                                                          | N/A                                                                                                         |                                                                                                             |

### Response

**[WireWithdrawalsForceRejectWireWithdrawalResponse](../../models/operations/WireWithdrawalsForceRejectWireWithdrawalResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## forceApproveCashJournal

Forces approval of an existing cash journal that is pending review FOR TESTING ONLY!

### Example Usage

<!-- UsageSnippet language="java" operationID="CashJournals_ForceApproveCashJournal" method="post" path="/transfers/v1/cashJournals/{cashJournal_id}:forceApprove" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.CashJournalsForceApproveCashJournalResponse;
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

        CashJournalsForceApproveCashJournalResponse res = sdk.testSimulation().forceApproveCashJournal()
                .cashJournalId("20230817000319")
                .forceApproveCashJournalRequestCreate(ForceApproveCashJournalRequestCreate.builder()
                    .name("cashJournals/20230817000319")
                    .build())
                .call();

        if (res.cashJournal().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                               | Type                                                                                                    | Required                                                                                                | Description                                                                                             | Example                                                                                                 |
| ------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------- |
| `cashJournalId`                                                                                         | *String*                                                                                                | :heavy_check_mark:                                                                                      | The cashJournal id.                                                                                     | 20230817000319                                                                                          |
| `forceApproveCashJournalRequestCreate`                                                                  | [ForceApproveCashJournalRequestCreate](../../models/components/ForceApproveCashJournalRequestCreate.md) | :heavy_check_mark:                                                                                      | N/A                                                                                                     |                                                                                                         |

### Response

**[CashJournalsForceApproveCashJournalResponse](../../models/operations/CashJournalsForceApproveCashJournalResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## forceRejectCashJournal

Forces rejection of an existing cash journal that is pending review FOR TESTING ONLY!

### Example Usage

<!-- UsageSnippet language="java" operationID="CashJournals_ForceRejectCashJournal" method="post" path="/transfers/v1/cashJournals/{cashJournal_id}:forceReject" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.CashJournalsForceRejectCashJournalResponse;
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

        CashJournalsForceRejectCashJournalResponse res = sdk.testSimulation().forceRejectCashJournal()
                .cashJournalId("20230817000319")
                .forceRejectCashJournalRequestCreate(ForceRejectCashJournalRequestCreate.builder()
                    .name("cashJournals/20230817000319")
                    .build())
                .call();

        if (res.cashJournal().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                             | Type                                                                                                  | Required                                                                                              | Description                                                                                           | Example                                                                                               |
| ----------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------- |
| `cashJournalId`                                                                                       | *String*                                                                                              | :heavy_check_mark:                                                                                    | The cashJournal id.                                                                                   | 20230817000319                                                                                        |
| `forceRejectCashJournalRequestCreate`                                                                 | [ForceRejectCashJournalRequestCreate](../../models/components/ForceRejectCashJournalRequestCreate.md) | :heavy_check_mark:                                                                                    | N/A                                                                                                   |                                                                                                       |

### Response

**[CashJournalsForceRejectCashJournalResponse](../../models/operations/CashJournalsForceRejectCashJournalResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |