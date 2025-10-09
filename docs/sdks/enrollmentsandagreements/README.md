# EnrollmentsAndAgreements
(*enrollmentsAndAgreements()*)

## Overview

### Available Operations

* [enrollAccount](#enrollaccount) - Enroll Account
* [listAvailableEnrollments](#listavailableenrollments) - List Available Enrollments
* [accountsListAvailableEnrollmentsByAccountGroup](#accountslistavailableenrollmentsbyaccountgroup) - List Available Enrollments (by Account Group)
* [deactivateEnrollment](#deactivateenrollment) - Deactivate Enrollment
* [listEnrollments](#listenrollments) - List Account Enrollments
* [affirmAgreements](#affirmagreements) - Affirm Agreements
* [listAgreements](#listagreements) - List Account Agreements
* [listEntitlements](#listentitlements) - List Account Entitlements

## enrollAccount

Adds an Enrollment to an Account.

### Example Usage

<!-- UsageSnippet language="java" operationID="Accounts_EnrollAccount" method="post" path="/accounts/v1/accounts/{account_id}:enroll" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsEnrollAccountResponse;
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

        AccountsEnrollAccountResponse res = sdk.enrollmentsAndAgreements().enrollAccount()
                .accountId("01HC3MAQ4DR9QN1V8MJ4CN1HMK")
                .enrollAccountRequestCreate(EnrollAccountRequestCreate.builder()
                    .enrollment(EnrollmentCreate.builder()
                        .principalApproverId("02HB7N66WW02WL3B6B9W29K0HW")
                        .type(EnrollmentCreateType.REGISTRATION_INDIVIDUAL)
                        .build())
                    .build())
                .call();

        if (res.enrollAccountResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                           | Type                                                                                | Required                                                                            | Description                                                                         | Example                                                                             |
| ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- |
| `accountId`                                                                         | *String*                                                                            | :heavy_check_mark:                                                                  | The account id.                                                                     | 01HC3MAQ4DR9QN1V8MJ4CN1HMK                                                          |
| `enrollAccountRequestCreate`                                                        | [EnrollAccountRequestCreate](../../models/components/EnrollAccountRequestCreate.md) | :heavy_check_mark:                                                                  | N/A                                                                                 |                                                                                     |

### Response

**[AccountsEnrollAccountResponse](../../models/operations/AccountsEnrollAccountResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## listAvailableEnrollments

Get a list of Enrollments available for an Account.

### Example Usage

<!-- UsageSnippet language="java" operationID="Accounts_ListAvailableEnrollments" method="get" path="/accounts/v1/accounts/{account_id}/availableEnrollments" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsListAvailableEnrollmentsResponse;
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

        AccountsListAvailableEnrollmentsResponse res = sdk.enrollmentsAndAgreements().listAvailableEnrollments()
                .accountId("01HC3MAQ4DR9QN1V8MJ4CN1HMK")
                .pageSize(25)
                .pageToken("AbTYnwAkMjIyZDNjYTAtZmVjZS00N2Q5LTgyMDctNzI3MDdkMjFiZj3h")
                .filter("enrollment_type == \"REGISTRATION_INDIVIDUAL\"")
                .call();

        if (res.listAvailableEnrollmentsResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                                              | Type                                                                                                                                                                                                                                                   | Required                                                                                                                                                                                                                                               | Description                                                                                                                                                                                                                                            | Example                                                                                                                                                                                                                                                |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `accountId`                                                                                                                                                                                                                                            | *String*                                                                                                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                                                                                                     | The account id.                                                                                                                                                                                                                                        | 01HC3MAQ4DR9QN1V8MJ4CN1HMK                                                                                                                                                                                                                             |
| `pageSize`                                                                                                                                                                                                                                             | *Optional\<Integer>*                                                                                                                                                                                                                                   | :heavy_minus_sign:                                                                                                                                                                                                                                     | The maximum number of available enrollments to return. The service may return fewer than this value. The maximum value is 100; values above 100 will be coerced to 100.                                                                                | 25                                                                                                                                                                                                                                                     |
| `pageToken`                                                                                                                                                                                                                                            | *Optional\<String>*                                                                                                                                                                                                                                    | :heavy_minus_sign:                                                                                                                                                                                                                                     | A page token, received from a previous `ListAvailableEnrollments` call. Provide this to retrieve the subsequent page.<br/><br/> When paginating, all other parameters provided to `ListAvailableEnrollments` must match the call that provided the page token. | AbTYnwAkMjIyZDNjYTAtZmVjZS00N2Q5LTgyMDctNzI3MDdkMjFiZj3h                                                                                                                                                                                               |
| `filter`                                                                                                                                                                                                                                               | *Optional\<String>*                                                                                                                                                                                                                                    | :heavy_minus_sign:                                                                                                                                                                                                                                     | A CEL string to filter results; See the [CEL Search](https://developer.apexclearing.com/apex-fintech-solutions/docs/cel-search) page in Guides for more information; Filter options include:<br/> `enrollment_type`                                    | enrollment_type == "REGISTRATION_INDIVIDUAL"                                                                                                                                                                                                           |

### Response

**[AccountsListAvailableEnrollmentsResponse](../../models/operations/AccountsListAvailableEnrollmentsResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## accountsListAvailableEnrollmentsByAccountGroup

Get a list of Enrollments available for an Account.

### Example Usage

<!-- UsageSnippet language="java" operationID="Accounts_ListAvailableEnrollments_1" method="get" path="/accounts/v1/accountGroups/{accountGroup_id}/availableEnrollments" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsListAvailableEnrollments1Response;
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

        AccountsListAvailableEnrollments1Response res = sdk.enrollmentsAndAgreements().accountsListAvailableEnrollmentsByAccountGroup()
                .accountGroupId("01HC3MAQ4DR9QN1V8MJ4CN1HMK")
                .pageSize(25)
                .pageToken("AbTYnwAkMjIyZDNjYTAtZmVjZS00N2Q5LTgyMDctNzI3MDdkMjFiZj3h")
                .filter("enrollment_type == \"REGISTRATION_INDIVIDUAL\"")
                .call();

        if (res.listAvailableEnrollmentsResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                                              | Type                                                                                                                                                                                                                                                   | Required                                                                                                                                                                                                                                               | Description                                                                                                                                                                                                                                            | Example                                                                                                                                                                                                                                                |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `accountGroupId`                                                                                                                                                                                                                                       | *String*                                                                                                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                                                                                                     | The accountGroup id.                                                                                                                                                                                                                                   | 01HC3MAQ4DR9QN1V8MJ4CN1HMK                                                                                                                                                                                                                             |
| `pageSize`                                                                                                                                                                                                                                             | *Optional\<Integer>*                                                                                                                                                                                                                                   | :heavy_minus_sign:                                                                                                                                                                                                                                     | The maximum number of available enrollments to return. The service may return fewer than this value. The maximum value is 100; values above 100 will be coerced to 100.                                                                                | 25                                                                                                                                                                                                                                                     |
| `pageToken`                                                                                                                                                                                                                                            | *Optional\<String>*                                                                                                                                                                                                                                    | :heavy_minus_sign:                                                                                                                                                                                                                                     | A page token, received from a previous `ListAvailableEnrollments` call. Provide this to retrieve the subsequent page.<br/><br/> When paginating, all other parameters provided to `ListAvailableEnrollments` must match the call that provided the page token. | AbTYnwAkMjIyZDNjYTAtZmVjZS00N2Q5LTgyMDctNzI3MDdkMjFiZj3h                                                                                                                                                                                               |
| `filter`                                                                                                                                                                                                                                               | *Optional\<String>*                                                                                                                                                                                                                                    | :heavy_minus_sign:                                                                                                                                                                                                                                     | A CEL string to filter results; See the [CEL Search](https://developer.apexclearing.com/apex-fintech-solutions/docs/cel-search) page in Guides for more information; Filter options include:<br/> `enrollment_type`                                    | enrollment_type == "REGISTRATION_INDIVIDUAL"                                                                                                                                                                                                           |

### Response

**[AccountsListAvailableEnrollments1Response](../../models/operations/AccountsListAvailableEnrollments1Response.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## deactivateEnrollment

Deactivates an Account Enrollment.

### Example Usage

<!-- UsageSnippet language="java" operationID="Accounts_DeactivateEnrollment" method="post" path="/accounts/v1/accounts/{account_id}/enrollments:deactivate" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsDeactivateEnrollmentResponse;
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

        AccountsDeactivateEnrollmentResponse res = sdk.enrollmentsAndAgreements().deactivateEnrollment()
                .accountId("01HC3MAQ4DR9QN1V8MJ4CN1HMK")
                .deactivateEnrollmentRequestCreate(DeactivateEnrollmentRequestCreate.builder()
                    .build())
                .call();

        if (res.enrollment().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                         | Type                                                                                              | Required                                                                                          | Description                                                                                       | Example                                                                                           |
| ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                       | *String*                                                                                          | :heavy_check_mark:                                                                                | The account id.                                                                                   | 01HC3MAQ4DR9QN1V8MJ4CN1HMK                                                                        |
| `deactivateEnrollmentRequestCreate`                                                               | [DeactivateEnrollmentRequestCreate](../../models/components/DeactivateEnrollmentRequestCreate.md) | :heavy_check_mark:                                                                                | N/A                                                                                               |                                                                                                   |

### Response

**[AccountsDeactivateEnrollmentResponse](../../models/operations/AccountsDeactivateEnrollmentResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## listEnrollments

Gets a list of Enrollments for an Account.

### Example Usage

<!-- UsageSnippet language="java" operationID="Accounts_ListEnrollments" method="get" path="/accounts/v1/accounts/{account_id}/enrollments" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsListEnrollmentsResponse;
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


        sdk.enrollmentsAndAgreements().listEnrollments()
                .accountId("01HC3MAQ4DR9QN1V8MJ4CN1HMK")
                .pageSize(5)
                .pageToken("4ZHd3wAaMD1IQ0ZKS2BKV0FSRVdLW4VLWkY1R1B3MU4")
                .callAsStream()
                .forEach((AccountsListEnrollmentsResponse item) -> {
                   // handle page
                });

    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                            | Type                                                                                                                                                                                                                                 | Required                                                                                                                                                                                                                             | Description                                                                                                                                                                                                                          | Example                                                                                                                                                                                                                              |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `accountId`                                                                                                                                                                                                                          | *String*                                                                                                                                                                                                                             | :heavy_check_mark:                                                                                                                                                                                                                   | The account id.                                                                                                                                                                                                                      | 01HC3MAQ4DR9QN1V8MJ4CN1HMK                                                                                                                                                                                                           |
| `pageSize`                                                                                                                                                                                                                           | *Optional\<Integer>*                                                                                                                                                                                                                 | :heavy_minus_sign:                                                                                                                                                                                                                   | The maximum number of enrollments to return.                                                                                                                                                                                         | 5                                                                                                                                                                                                                                    |
| `pageToken`                                                                                                                                                                                                                          | *Optional\<String>*                                                                                                                                                                                                                  | :heavy_minus_sign:                                                                                                                                                                                                                   | A page token, received from a previous `ListEnrollments` call. Provide this to retrieve the subsequent page.<br/><br/> When paginating, all other parameters provided to `ListEnrollments` must match the call that provided the page token. | 4ZHd3wAaMD1IQ0ZKS2BKV0FSRVdLW4VLWkY1R1B3MU4                                                                                                                                                                                          |

### Response

**[AccountsListEnrollmentsResponse](../../models/operations/AccountsListEnrollmentsResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## affirmAgreements

Affirm Agreements for an Account.

### Example Usage

<!-- UsageSnippet language="java" operationID="Accounts_AffirmAgreements" method="post" path="/accounts/v1/accounts/{account_id}/agreements:affirm" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsAffirmAgreementsResponse;
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

        AccountsAffirmAgreementsResponse res = sdk.enrollmentsAndAgreements().affirmAgreements()
                .accountId("01HC3MAQ4DR9QN1V8MJ4CN1HMK")
                .affirmAgreementsRequestCreate(AffirmAgreementsRequestCreate.builder()
                    .accountAgreementIds(List.of(
                        "fa2f181c-f2fb-4bc2-b75a-79302c634ae5"))
                    .build())
                .call();

        if (res.affirmAgreementsResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                 | Type                                                                                      | Required                                                                                  | Description                                                                               | Example                                                                                   |
| ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| `accountId`                                                                               | *String*                                                                                  | :heavy_check_mark:                                                                        | The account id.                                                                           | 01HC3MAQ4DR9QN1V8MJ4CN1HMK                                                                |
| `affirmAgreementsRequestCreate`                                                           | [AffirmAgreementsRequestCreate](../../models/components/AffirmAgreementsRequestCreate.md) | :heavy_check_mark:                                                                        | N/A                                                                                       |                                                                                           |

### Response

**[AccountsAffirmAgreementsResponse](../../models/operations/AccountsAffirmAgreementsResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## listAgreements

Gets a list of Agreements on an Account.

### Example Usage

<!-- UsageSnippet language="java" operationID="Accounts_ListAgreements" method="get" path="/accounts/v1/accounts/{account_id}/agreements" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsListAgreementsResponse;
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


        sdk.enrollmentsAndAgreements().listAgreements()
                .accountId("01HC3MAQ4DR9QN1V8MJ4CN1HMK")
                .pageSize(5)
                .pageToken("4ZHd3wAaMD1IQ0ZKS2BKV0FSRVdLW4VLWkY1R1B3MU4")
                .callAsStream()
                .forEach((AccountsListAgreementsResponse item) -> {
                   // handle page
                });

    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                          | Type                                                                                                                                                                                                                               | Required                                                                                                                                                                                                                           | Description                                                                                                                                                                                                                        | Example                                                                                                                                                                                                                            |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                                                                                                                                        | *String*                                                                                                                                                                                                                           | :heavy_check_mark:                                                                                                                                                                                                                 | The account id.                                                                                                                                                                                                                    | 01HC3MAQ4DR9QN1V8MJ4CN1HMK                                                                                                                                                                                                         |
| `pageSize`                                                                                                                                                                                                                         | *Optional\<Integer>*                                                                                                                                                                                                               | :heavy_minus_sign:                                                                                                                                                                                                                 | The maximum number of agreements to return.                                                                                                                                                                                        | 5                                                                                                                                                                                                                                  |
| `pageToken`                                                                                                                                                                                                                        | *Optional\<String>*                                                                                                                                                                                                                | :heavy_minus_sign:                                                                                                                                                                                                                 | A page token, received from a previous `ListAgreements` call. Provide this to retrieve the subsequent page.<br/><br/> When paginating, all other parameters provided to `ListAgreements` must match the call that provided the page token. | 4ZHd3wAaMD1IQ0ZKS2BKV0FSRVdLW4VLWkY1R1B3MU4                                                                                                                                                                                        |

### Response

**[AccountsListAgreementsResponse](../../models/operations/AccountsListAgreementsResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## listEntitlements

Gets a list of Entitlements for an Account.

### Example Usage

<!-- UsageSnippet language="java" operationID="Accounts_ListEntitlements" method="get" path="/accounts/v1/accounts/{account_id}/entitlements" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsListEntitlementsResponse;
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


        sdk.enrollmentsAndAgreements().listEntitlements()
                .accountId("01HC3MAQ4DR9QN1V8MJ4CN1HMK")
                .pageSize(5)
                .pageToken("4ZHd3wAaMD1IQ0ZKS2BKV0FSRVdLW4VLWkY1R1B3MU4")
                .callAsStream()
                .forEach((AccountsListEntitlementsResponse item) -> {
                   // handle page
                });

    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                              | Type                                                                                                                                                                                                                                   | Required                                                                                                                                                                                                                               | Description                                                                                                                                                                                                                            | Example                                                                                                                                                                                                                                |
| -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                                                                                                                                            | *String*                                                                                                                                                                                                                               | :heavy_check_mark:                                                                                                                                                                                                                     | The account id.                                                                                                                                                                                                                        | 01HC3MAQ4DR9QN1V8MJ4CN1HMK                                                                                                                                                                                                             |
| `pageSize`                                                                                                                                                                                                                             | *Optional\<Integer>*                                                                                                                                                                                                                   | :heavy_minus_sign:                                                                                                                                                                                                                     | The maximum number of entitlements to return.                                                                                                                                                                                          | 5                                                                                                                                                                                                                                      |
| `pageToken`                                                                                                                                                                                                                            | *Optional\<String>*                                                                                                                                                                                                                    | :heavy_minus_sign:                                                                                                                                                                                                                     | A page token, received from a previous `ListEntitlements` call. Provide this to retrieve the subsequent page.<br/><br/> When paginating, all other parameters provided to `ListEntitlements` must match the call that provided the page token. | 4ZHd3wAaMD1IQ0ZKS2BKV0FSRVdLW4VLWkY1R1B3MU4                                                                                                                                                                                            |

### Response

**[AccountsListEntitlementsResponse](../../models/operations/AccountsListEntitlementsResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |