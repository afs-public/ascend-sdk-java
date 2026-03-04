# PreIPOCompanies
(*preIPOCompanies()*)

## Overview

### Available Operations

* [listPreIpoCompanies](#listpreipocompanies) - List Pre IPO Company
* [getPreIpoCompany](#getpreipocompany) - Get Pre IPO Company

## listPreIpoCompanies

Lists Pre IPO Companies.

### Example Usage

<!-- UsageSnippet language="java" operationID="PreIpoCompanies_ListPreIpoCompanies" method="get" path="/trading/v1/preIpoCompanies" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.PreIpoCompaniesListPreIpoCompaniesResponse;
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

        PreIpoCompaniesListPreIpoCompaniesResponse res = sdk.preIPOCompanies().listPreIpoCompanies()
                .pageSize(50)
                .pageToken("eyJzaXplIjoxMCwib2Zmc2V0IjoxMDAsInBhcmVudElkIjoicGFyZW50SWQifQ==")
                .call();

        if (res.listPreIpoCompaniesResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                                                | Type                                                                                                                                                                                                                                                     | Required                                                                                                                                                                                                                                                 | Description                                                                                                                                                                                                                                              | Example                                                                                                                                                                                                                                                  |
| -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `pageSize`                                                                                                                                                                                                                                               | *Optional\<Integer>*                                                                                                                                                                                                                                     | :heavy_minus_sign:                                                                                                                                                                                                                                       | The maximum number of Pre IPO Companies to return. The service may return fewer than this value. If unspecified, at most 100 Pre IPO Companies will be returned. The maximum value is 100; values above 100 will be coerced to 100.                      | 50                                                                                                                                                                                                                                                       |
| `pageToken`                                                                                                                                                                                                                                              | *Optional\<String>*                                                                                                                                                                                                                                      | :heavy_minus_sign:                                                                                                                                                                                                                                       | A page token, received from a previous `ListPreIpoCompaniesRequest` call. Provide this to retrieve the subsequent page. When paginating, all other parameters provided to `ListPreIpoCompaniesRequest` must match the call that provided the page token. | eyJzaXplIjoxMCwib2Zmc2V0IjoxMDAsInBhcmVudElkIjoicGFyZW50SWQifQ==                                                                                                                                                                                         |

### Response

**[PreIpoCompaniesListPreIpoCompaniesResponse](../../models/operations/PreIpoCompaniesListPreIpoCompaniesResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403          | application/json       |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getPreIpoCompany

Gets a Pre IPO Company.

### Example Usage

<!-- UsageSnippet language="java" operationID="PreIpoCompanies_GetPreIpoCompany" method="get" path="/trading/v1/preIpoCompanies/{preIpoCompany_id}" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.PreIpoCompaniesGetPreIpoCompanyResponse;
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

        PreIpoCompaniesGetPreIpoCompanyResponse res = sdk.preIPOCompanies().getPreIpoCompany()
                .preIpoCompanyId("3fa85f64-5717-4562-b3fc-2c963f66afa6")
                .call();

        if (res.preIpoCompany().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                            | Type                                 | Required                             | Description                          | Example                              |
| ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ |
| `preIpoCompanyId`                    | *String*                             | :heavy_check_mark:                   | The preIpoCompany id.                | 3fa85f64-5717-4562-b3fc-2c963f66afa6 |

### Response

**[PreIpoCompaniesGetPreIpoCompanyResponse](../../models/operations/PreIpoCompaniesGetPreIpoCompanyResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 404     | application/json       |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |