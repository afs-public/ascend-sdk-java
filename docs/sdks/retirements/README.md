# Retirements
(*retirements()*)

## Overview

### Available Operations

* [listContributionSummaries](#listcontributionsummaries) - List Contribution Summaries
* [retrieveContributionConstraints](#retrievecontributionconstraints) - Retrieve Contribution Constraints
* [listDistributionSummaries](#listdistributionsummaries) - List Distribution Summaries
* [retrieveDistributionConstraints](#retrievedistributionconstraints) - Retrieve Distribution Constraints

## listContributionSummaries

Lists the aggregated retirement contribution summaries by tax year

### Example Usage

<!-- UsageSnippet language="java" operationID="RetirementConstraints_ListContributionSummaries" method="get" path="/transfers/v1/accounts/{account_id}/contributionSummaries" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.RetirementConstraintsListContributionSummariesResponse;
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


        sdk.retirements().listContributionSummaries()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .pageSize(2)
                .pageToken("AbTYnwAkMjIyZDNjYTAtZmVjZS00N2Q5LTgyMDctNzI3MDdkMjFiZ3hh")
                .callAsStream()
                .forEach((RetirementConstraintsListContributionSummariesResponse item) -> {
                   // handle page
                });

    }
}
```

### Parameters

| Parameter                                                                                                                 | Type                                                                                                                      | Required                                                                                                                  | Description                                                                                                               | Example                                                                                                                   |
| ------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                               | *String*                                                                                                                  | :heavy_check_mark:                                                                                                        | The account id.                                                                                                           | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                                                |
| `pageSize`                                                                                                                | *Optional\<Integer>*                                                                                                      | :heavy_minus_sign:                                                                                                        | Number of contribution summaries to get (partitioned by tax year) Default = 2 (current year and prior year), maximum = 10 | 2                                                                                                                         |
| `pageToken`                                                                                                               | *Optional\<String>*                                                                                                       | :heavy_minus_sign:                                                                                                        | When paginating, this is used to retrieve a specific page from the overall response                                       | AbTYnwAkMjIyZDNjYTAtZmVjZS00N2Q5LTgyMDctNzI3MDdkMjFiZ3hh                                                                  |

### Response

**[RetirementConstraintsListContributionSummariesResponse](../../models/operations/RetirementConstraintsListContributionSummariesResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## retrieveContributionConstraints

Retrieves retirement contribution constraints for an account

### Example Usage

<!-- UsageSnippet language="java" operationID="RetirementConstraints_RetrieveContributionConstraints" method="post" path="/transfers/v1/accounts/{account_id}:retrieveContributionConstraints" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.RetirementConstraintsRetrieveContributionConstraintsResponse;
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

        RetirementConstraintsRetrieveContributionConstraintsResponse res = sdk.retirements().retrieveContributionConstraints()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .retrieveContributionConstraintsRequestCreate(RetrieveContributionConstraintsRequestCreate.builder()
                    .mechanism(Mechanism.ACH)
                    .name("accounts/01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                    .build())
                .call();

        if (res.contributionConstraints().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                               | Type                                                                                                                    | Required                                                                                                                | Description                                                                                                             | Example                                                                                                                 |
| ----------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                             | *String*                                                                                                                | :heavy_check_mark:                                                                                                      | The account id.                                                                                                         | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                                              |
| `retrieveContributionConstraintsRequestCreate`                                                                          | [RetrieveContributionConstraintsRequestCreate](../../models/components/RetrieveContributionConstraintsRequestCreate.md) | :heavy_check_mark:                                                                                                      | N/A                                                                                                                     |                                                                                                                         |

### Response

**[RetirementConstraintsRetrieveContributionConstraintsResponse](../../models/operations/RetirementConstraintsRetrieveContributionConstraintsResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## listDistributionSummaries

Lists the aggregated retirement distribution summaries by tax year

### Example Usage

<!-- UsageSnippet language="java" operationID="RetirementConstraints_ListDistributionSummaries" method="get" path="/transfers/v1/accounts/{account_id}/distributionSummaries" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.RetirementConstraintsListDistributionSummariesResponse;
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


        sdk.retirements().listDistributionSummaries()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .pageSize(2)
                .pageToken("AbTYnwAkMjIyZDNjYTAtZmVjZS00N2Q5LTgyMDctNzI3MDdkMjFiZ3hh")
                .callAsStream()
                .forEach((RetirementConstraintsListDistributionSummariesResponse item) -> {
                   // handle page
                });

    }
}
```

### Parameters

| Parameter                                                                                                                 | Type                                                                                                                      | Required                                                                                                                  | Description                                                                                                               | Example                                                                                                                   |
| ------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                               | *String*                                                                                                                  | :heavy_check_mark:                                                                                                        | The account id.                                                                                                           | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                                                |
| `pageSize`                                                                                                                | *Optional\<Integer>*                                                                                                      | :heavy_minus_sign:                                                                                                        | Number of distribution summaries to get (partitioned by tax year) Default = 2 (current year and prior year), maximum = 10 | 2                                                                                                                         |
| `pageToken`                                                                                                               | *Optional\<String>*                                                                                                       | :heavy_minus_sign:                                                                                                        | When paginating, this is used to retrieve a specific page from the overall response                                       | AbTYnwAkMjIyZDNjYTAtZmVjZS00N2Q5LTgyMDctNzI3MDdkMjFiZ3hh                                                                  |

### Response

**[RetirementConstraintsListDistributionSummariesResponse](../../models/operations/RetirementConstraintsListDistributionSummariesResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## retrieveDistributionConstraints

Retrieves retirement distribution constraints for an account

### Example Usage

<!-- UsageSnippet language="java" operationID="RetirementConstraints_RetrieveDistributionConstraints" method="post" path="/transfers/v1/accounts/{account_id}:retrieveDistributionConstraints" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.RetirementConstraintsRetrieveDistributionConstraintsResponse;
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

        RetirementConstraintsRetrieveDistributionConstraintsResponse res = sdk.retirements().retrieveDistributionConstraints()
                .accountId("01H8FM6EXVH77SAW3TC8KAWMES")
                .retrieveDistributionConstraintsRequestCreate(RetrieveDistributionConstraintsRequestCreate.builder()
                    .mechanism(RetrieveDistributionConstraintsRequestCreateMechanism.ACH)
                    .name("accounts/01H8FM6EXVH77SAW3TC8KAWMES")
                    .build())
                .call();

        if (res.distributionConstraints().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                               | Type                                                                                                                    | Required                                                                                                                | Description                                                                                                             | Example                                                                                                                 |
| ----------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                             | *String*                                                                                                                | :heavy_check_mark:                                                                                                      | The account id.                                                                                                         | 01H8FM6EXVH77SAW3TC8KAWMES                                                                                              |
| `retrieveDistributionConstraintsRequestCreate`                                                                          | [RetrieveDistributionConstraintsRequestCreate](../../models/components/RetrieveDistributionConstraintsRequestCreate.md) | :heavy_check_mark:                                                                                                      | N/A                                                                                                                     |                                                                                                                         |

### Response

**[RetirementConstraintsRetrieveDistributionConstraintsResponse](../../models/operations/RetirementConstraintsRetrieveDistributionConstraintsResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |