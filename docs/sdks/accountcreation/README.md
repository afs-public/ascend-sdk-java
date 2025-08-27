# AccountCreation
(*accountCreation()*)

## Overview

### Available Operations

* [createAccount](#createaccount) - Create Account
* [getAccount](#getaccount) - Get Account

## createAccount

CREATE Creates an account.

### Example Usage

<!-- UsageSnippet language="java" operationID="Accounts_CreateAccount" method="post" path="/accounts/v1/accounts" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsCreateAccountResponse;
import java.lang.Exception;
import java.util.List;

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

        AccountRequestCreate req = AccountRequestCreate.builder()
                .accountGroupId("01ARZ3NDEKTSV4RRFFQ69G5FAV")
                .correspondentId("01HPMZZM6RKMVZA1JQ63RQKJRP")
                .parties(List.of())
                .build();

        AccountsCreateAccountResponse res = sdk.accountCreation().createAccount()
                .request(req)
                .call();

        if (res.account().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                           | Type                                                                | Required                                                            | Description                                                         |
| ------------------------------------------------------------------- | ------------------------------------------------------------------- | ------------------------------------------------------------------- | ------------------------------------------------------------------- |
| `request`                                                           | [AccountRequestCreate](../../models/shared/AccountRequestCreate.md) | :heavy_check_mark:                                                  | The request object to use for the request.                          |

### Response

**[AccountsCreateAccountResponse](../../models/operations/AccountsCreateAccountResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getAccount

READ Get Account

### Example Usage

<!-- UsageSnippet language="java" operationID="Accounts_GetAccount" method="get" path="/accounts/v1/accounts/{account_id}" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsGetAccountResponse;
import com.apexfintechsolutions.ascendsdk.models.operations.QueryParamView;
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

        AccountsGetAccountResponse res = sdk.accountCreation().getAccount()
                .accountId("01HC3MAQ4DR9QN1V8MJ4CN1HMK")
                .view(QueryParamView.FULL)
                .call();

        if (res.account().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                              | Type                                                                   | Required                                                               | Description                                                            | Example                                                                |
| ---------------------------------------------------------------------- | ---------------------------------------------------------------------- | ---------------------------------------------------------------------- | ---------------------------------------------------------------------- | ---------------------------------------------------------------------- |
| `accountId`                                                            | *String*                                                               | :heavy_check_mark:                                                     | The account id.                                                        | 01HC3MAQ4DR9QN1V8MJ4CN1HMK                                             |
| `view`                                                                 | [Optional\<QueryParamView>](../../models/operations/QueryParamView.md) | :heavy_minus_sign:                                                     | The view to return. Defaults to `FULL`.                                | FULL                                                                   |

### Response

**[AccountsGetAccountResponse](../../models/operations/AccountsGetAccountResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |