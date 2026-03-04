# AlternativeAccountAccreditation
(*alternativeAccountAccreditation()*)

## Overview

### Available Operations

* [getAccountAccreditation](#getaccountaccreditation) - Get Account Accreditation
* [setAccountAccreditationType](#setaccountaccreditationtype) - Set Account Accreditation

## getAccountAccreditation

Gets the accreditation properties for the specified account.

### Example Usage

<!-- UsageSnippet language="java" operationID="AccountAccreditationService_GetAccountAccreditation" method="get" path="/trading/v1/accounts/{account_id}/accreditation" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountAccreditationServiceGetAccountAccreditationResponse;
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

        AccountAccreditationServiceGetAccountAccreditationResponse res = sdk.alternativeAccountAccreditation().getAccountAccreditation()
                .accountId("01JR8YQT40WAQT8S95ZQGS1QN0")
                .call();

        if (res.accountAccreditation().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                  | Type                       | Required                   | Description                | Example                    |
| -------------------------- | -------------------------- | -------------------------- | -------------------------- | -------------------------- |
| `accountId`                | *String*                   | :heavy_check_mark:         | The account id.            | 01JR8YQT40WAQT8S95ZQGS1QN0 |

### Response

**[AccountAccreditationServiceGetAccountAccreditationResponse](../../models/operations/AccountAccreditationServiceGetAccountAccreditationResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403          | application/json       |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## setAccountAccreditationType

Sets the accreditation type for an account. Accounts must be accredited to participate in alternative investment orders.

### Example Usage

<!-- UsageSnippet language="java" operationID="AccountAccreditationService_SetAccountAccreditationType" method="post" path="/trading/v1/accounts/{account_id}/accreditation:setType" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountAccreditationServiceSetAccountAccreditationTypeResponse;
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

        AccountAccreditationServiceSetAccountAccreditationTypeResponse res = sdk.alternativeAccountAccreditation().setAccountAccreditationType()
                .accountId("01JR8YQT40WAQT8S95ZQGS1QN0")
                .setAccountAccreditationTypeRequestCreate(SetAccountAccreditationTypeRequestCreate.builder()
                    .accreditationType(SetAccountAccreditationTypeRequestCreateAccreditationType.NET_WORTH_GT1_M)
                    .name("accounts/01JR8YQT40WAQT8S95ZQGS1QN0/accreditation")
                    .build())
                .call();

        if (res.accountAccreditation().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                       | Type                                                                                                            | Required                                                                                                        | Description                                                                                                     | Example                                                                                                         |
| --------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                     | *String*                                                                                                        | :heavy_check_mark:                                                                                              | The account id.                                                                                                 | 01JR8YQT40WAQT8S95ZQGS1QN0                                                                                      |
| `setAccountAccreditationTypeRequestCreate`                                                                      | [SetAccountAccreditationTypeRequestCreate](../../models/components/SetAccountAccreditationTypeRequestCreate.md) | :heavy_check_mark:                                                                                              | N/A                                                                                                             |                                                                                                                 |

### Response

**[AccountAccreditationServiceSetAccountAccreditationTypeResponse](../../models/operations/AccountAccreditationServiceSetAccountAccreditationTypeResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 404     | application/json       |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |