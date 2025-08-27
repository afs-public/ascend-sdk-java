# BankRelationships
(*bankRelationships()*)

## Overview

### Available Operations

* [createBankRelationship](#createbankrelationship) - Create Bank Relationship
* [listBankRelationships](#listbankrelationships) - List Bank Relationships
* [getBankRelationship](#getbankrelationship) - Get Bank Relationship
* [updateBankRelationship](#updatebankrelationship) - Update Bank Relationship
* [cancelBankRelationship](#cancelbankrelationship) - Cancel Bank Relationship
* [verifyMicroDeposits](#verifymicrodeposits) - Verify Micro Deposits
* [reissueMicroDeposits](#reissuemicrodeposits) - Reissue Micro Deposits
* [reuseBankRelationship](#reusebankrelationship) - Reuse Bank Relationship

## createBankRelationship

Creates a bank relationship.

### Example Usage

<!-- UsageSnippet language="java" operationID="BankRelationships_CreateBankRelationship" method="post" path="/transfers/v1/accounts/{account_id}/bankRelationships" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BankRelationshipsCreateBankRelationshipResponse;
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

        BankRelationshipsCreateBankRelationshipResponse res = sdk.bankRelationships().createBankRelationship()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .bankRelationshipCreate(BankRelationshipCreate.builder()
                    .nickname("My Primary Bank")
                    .verificationMethod(VerificationMethod.MICRO_DEPOSIT)
                    .build())
                .call();

        if (res.bankRelationship().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                   | Type                                                                        | Required                                                                    | Description                                                                 | Example                                                                     |
| --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- |
| `accountId`                                                                 | *String*                                                                    | :heavy_check_mark:                                                          | The account id.                                                             | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                  |
| `bankRelationshipCreate`                                                    | [BankRelationshipCreate](../../models/components/BankRelationshipCreate.md) | :heavy_check_mark:                                                          | N/A                                                                         |                                                                             |

### Response

**[BankRelationshipsCreateBankRelationshipResponse](../../models/operations/BankRelationshipsCreateBankRelationshipResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## listBankRelationships

Lists bank relationships for an account.

### Example Usage

<!-- UsageSnippet language="java" operationID="BankRelationships_ListBankRelationships" method="get" path="/transfers/v1/accounts/{account_id}/bankRelationships" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BankRelationshipsListBankRelationshipsResponse;
import com.apexfintechsolutions.ascendsdk.models.operations.State;
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

        BankRelationshipsListBankRelationshipsResponse res = sdk.bankRelationships().listBankRelationships()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .pageSize(100)
                .pageToken("CMFRGgYQup3BhQgaCSkAQCKS7AAAAA==")
                .state(State.APPROVED)
                .call();

        if (res.listBankRelationshipsResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                                      | Type                                                                                                                                                                                                                                           | Required                                                                                                                                                                                                                                       | Description                                                                                                                                                                                                                                    | Example                                                                                                                                                                                                                                        |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                                                                                                                                                    | *String*                                                                                                                                                                                                                                       | :heavy_check_mark:                                                                                                                                                                                                                             | The account id.                                                                                                                                                                                                                                | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                                                                                                                                                                     |
| `pageSize`                                                                                                                                                                                                                                     | *Optional\<Integer>*                                                                                                                                                                                                                           | :heavy_minus_sign:                                                                                                                                                                                                                             | The maximum number of bank relationships to return. The service may return fewer than this value. If unspecified, at most 50 bank relationships will be returned. The maximum value is 1000; values above 1000 will be coerced to 1000.        | 100                                                                                                                                                                                                                                            |
| `pageToken`                                                                                                                                                                                                                                    | *Optional\<String>*                                                                                                                                                                                                                            | :heavy_minus_sign:                                                                                                                                                                                                                             | A page token, received from a previous `ListBankRelationships` call. Provide this to retrieve the subsequent page. When paginating, all other parameters provided to `ListBankRelationships` must match the call that provided the page token. | CMFRGgYQup3BhQgaCSkAQCKS7AAAAA==                                                                                                                                                                                                               |
| `state`                                                                                                                                                                                                                                        | [Optional\<State>](../../models/operations/State.md)                                                                                                                                                                                           | :heavy_minus_sign:                                                                                                                                                                                                                             | The state of bank relationships to filter by. Unspecified returns relationships of all states.                                                                                                                                                 | APPROVED                                                                                                                                                                                                                                       |

### Response

**[BankRelationshipsListBankRelationshipsResponse](../../models/operations/BankRelationshipsListBankRelationshipsResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getBankRelationship

Gets an existing bank relationship.

### Example Usage

<!-- UsageSnippet language="java" operationID="BankRelationships_GetBankRelationship" method="get" path="/transfers/v1/accounts/{account_id}/bankRelationships/{bankRelationship_id}" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BankRelationshipsGetBankRelationshipResponse;
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

        BankRelationshipsGetBankRelationshipResponse res = sdk.bankRelationships().getBankRelationship()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .bankRelationshipId("651ef9de0dee00240813e60e")
                .call();

        if (res.bankRelationship().isPresent()) {
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

**[BankRelationshipsGetBankRelationshipResponse](../../models/operations/BankRelationshipsGetBankRelationshipResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## updateBankRelationship

Updates an existing bank relationship.

### Example Usage

<!-- UsageSnippet language="java" operationID="BankRelationships_UpdateBankRelationship" method="patch" path="/transfers/v1/accounts/{account_id}/bankRelationships/{bankRelationship_id}" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BankRelationshipsUpdateBankRelationshipResponse;
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

        BankRelationshipsUpdateBankRelationshipResponse res = sdk.bankRelationships().updateBankRelationship()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .bankRelationshipId("651ef9de0dee00240813e60e")
                .updateMask("[object Object]")
                .bankRelationshipUpdate(BankRelationshipUpdate.builder()
                    .build())
                .call();

        if (res.bankRelationship().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                   | Type                                                                        | Required                                                                    | Description                                                                 | Example                                                                     |
| --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- |
| `accountId`                                                                 | *String*                                                                    | :heavy_check_mark:                                                          | The account id.                                                             | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                  |
| `bankRelationshipId`                                                        | *String*                                                                    | :heavy_check_mark:                                                          | The bankRelationship id.                                                    | 651ef9de0dee00240813e60e                                                    |
| `updateMask`                                                                | *Optional\<String>*                                                         | :heavy_minus_sign:                                                          | The field of the bank relationship to update. Only `nickname` is supported. | {<br/>"update_mask": {<br/>"paths": [<br/>"nickname"<br/>]<br/>}<br/>}      |
| `bankRelationshipUpdate`                                                    | [BankRelationshipUpdate](../../models/components/BankRelationshipUpdate.md) | :heavy_check_mark:                                                          | N/A                                                                         |                                                                             |

### Response

**[BankRelationshipsUpdateBankRelationshipResponse](../../models/operations/BankRelationshipsUpdateBankRelationshipResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## cancelBankRelationship

Cancels an existing bank relationship.

### Example Usage

<!-- UsageSnippet language="java" operationID="BankRelationships_CancelBankRelationship" method="post" path="/transfers/v1/accounts/{account_id}/bankRelationships/{bankRelationship_id}:cancel" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BankRelationshipsCancelBankRelationshipResponse;
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

        BankRelationshipsCancelBankRelationshipResponse res = sdk.bankRelationships().cancelBankRelationship()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .bankRelationshipId("651ef9de0dee00240813e60e")
                .cancelBankRelationshipRequestCreate(CancelBankRelationshipRequestCreate.builder()
                    .comment("User Request")
                    .name("accounts/01H8FB90ZRRFWXB4XC2JPJ1D4Y/bankRelationships/651ef9de0dee00240813e60e")
                    .build())
                .call();

        if (res.bankRelationship().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                             | Type                                                                                                  | Required                                                                                              | Description                                                                                           | Example                                                                                               |
| ----------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                           | *String*                                                                                              | :heavy_check_mark:                                                                                    | The account id.                                                                                       | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                            |
| `bankRelationshipId`                                                                                  | *String*                                                                                              | :heavy_check_mark:                                                                                    | The bankRelationship id.                                                                              | 651ef9de0dee00240813e60e                                                                              |
| `cancelBankRelationshipRequestCreate`                                                                 | [CancelBankRelationshipRequestCreate](../../models/components/CancelBankRelationshipRequestCreate.md) | :heavy_check_mark:                                                                                    | N/A                                                                                                   |                                                                                                       |

### Response

**[BankRelationshipsCancelBankRelationshipResponse](../../models/operations/BankRelationshipsCancelBankRelationshipResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## verifyMicroDeposits

Verifies a pending bank relationship with the `MICRO_DEPOSIT` verification method. Successful verification of the micro deposit amounts will result in the relationship moving to the `APPROVED` state. The micro deposits will be taken back from the bank account.

### Example Usage

<!-- UsageSnippet language="java" operationID="BankRelationships_VerifyMicroDeposits" method="post" path="/transfers/v1/accounts/{account_id}/bankRelationships/{bankRelationship_id}:verifyMicroDeposits" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BankRelationshipsVerifyMicroDepositsResponse;
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

        BankRelationshipsVerifyMicroDepositsResponse res = sdk.bankRelationships().verifyMicroDeposits()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .bankRelationshipId("651ef9de0dee00240813e60e")
                .verifyMicroDepositsRequestCreate(VerifyMicroDepositsRequestCreate.builder()
                    .amounts(MicroDepositAmountsCreate.builder()
                        .amount1(DecimalCreate.builder()
                            .build())
                        .amount2(DecimalCreate.builder()
                            .build())
                        .build())
                    .name("accounts/01H8FB90ZRRFWXB4XC2JPJ1D4Y/bankRelationships/651ef9de0dee00240813e60e")
                    .build())
                .call();

        if (res.bankRelationship().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                       | Type                                                                                            | Required                                                                                        | Description                                                                                     | Example                                                                                         |
| ----------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------- |
| `accountId`                                                                                     | *String*                                                                                        | :heavy_check_mark:                                                                              | The account id.                                                                                 | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                      |
| `bankRelationshipId`                                                                            | *String*                                                                                        | :heavy_check_mark:                                                                              | The bankRelationship id.                                                                        | 651ef9de0dee00240813e60e                                                                        |
| `verifyMicroDepositsRequestCreate`                                                              | [VerifyMicroDepositsRequestCreate](../../models/components/VerifyMicroDepositsRequestCreate.md) | :heavy_check_mark:                                                                              | N/A                                                                                             |                                                                                                 |

### Response

**[BankRelationshipsVerifyMicroDepositsResponse](../../models/operations/BankRelationshipsVerifyMicroDepositsResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## reissueMicroDeposits

Reissues micro deposits after micro deposit verification has failed. The user should have received a message that new micro deposits should be reissued.

### Example Usage

<!-- UsageSnippet language="java" operationID="BankRelationships_ReissueMicroDeposits" method="post" path="/transfers/v1/accounts/{account_id}/bankRelationships/{bankRelationship_id}:reissue" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BankRelationshipsReissueMicroDepositsResponse;
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

        BankRelationshipsReissueMicroDepositsResponse res = sdk.bankRelationships().reissueMicroDeposits()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .bankRelationshipId("651ef9de0dee00240813e60e")
                .reissueMicroDepositsRequestCreate(ReissueMicroDepositsRequestCreate.builder()
                    .name("accounts/01H8FB90ZRRFWXB4XC2JPJ1D4Y/bankRelationships/651ef9de0dee00240813e60e")
                    .build())
                .call();

        if (res.bankRelationship().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                         | Type                                                                                              | Required                                                                                          | Description                                                                                       | Example                                                                                           |
| ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                       | *String*                                                                                          | :heavy_check_mark:                                                                                | The account id.                                                                                   | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                        |
| `bankRelationshipId`                                                                              | *String*                                                                                          | :heavy_check_mark:                                                                                | The bankRelationship id.                                                                          | 651ef9de0dee00240813e60e                                                                          |
| `reissueMicroDepositsRequestCreate`                                                               | [ReissueMicroDepositsRequestCreate](../../models/components/ReissueMicroDepositsRequestCreate.md) | :heavy_check_mark:                                                                                | N/A                                                                                               |                                                                                                   |

### Response

**[BankRelationshipsReissueMicroDepositsResponse](../../models/operations/BankRelationshipsReissueMicroDepositsResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## reuseBankRelationship

Reuses an existing bank relationship for a new account. The source bank relationship must be approved. The new account must be related to the parent account of the `source_bank_relationship`. The new relationship will be created with the `USE_EXISTING` verification method in place of the source bank relationship's verification method.

### Example Usage

<!-- UsageSnippet language="java" operationID="BankRelationships_ReuseBankRelationship" method="post" path="/transfers/v1/accounts/{account_id}/bankRelationships:reuse" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.BankRelationshipsReuseBankRelationshipResponse;
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

        BankRelationshipsReuseBankRelationshipResponse res = sdk.bankRelationships().reuseBankRelationship()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Z")
                .reuseBankRelationshipRequestCreate(ReuseBankRelationshipRequestCreate.builder()
                    .parent("accounts/01H8FB90ZRRFWXB4XC2JPJ1D4Z")
                    .sourceBankRelationship("accounts/01H8FB90ZRRFWXB4XC2JPJ1D4Y/bankRelationships/651ef9de0dee00240813e60e")
                    .build())
                .call();

        if (res.bankRelationship().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                           | Type                                                                                                | Required                                                                                            | Description                                                                                         | Example                                                                                             |
| --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                         | *String*                                                                                            | :heavy_check_mark:                                                                                  | The account id.                                                                                     | 01H8FB90ZRRFWXB4XC2JPJ1D4Z                                                                          |
| `reuseBankRelationshipRequestCreate`                                                                | [ReuseBankRelationshipRequestCreate](../../models/components/ReuseBankRelationshipRequestCreate.md) | :heavy_check_mark:                                                                                  | N/A                                                                                                 |                                                                                                     |

### Response

**[BankRelationshipsReuseBankRelationshipResponse](../../models/operations/BankRelationshipsReuseBankRelationshipResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |