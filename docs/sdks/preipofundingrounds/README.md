# PreIPOFundingRounds
(*preIPOFundingRounds()*)

## Overview

### Available Operations

* [listPreIpoCompanyFundingRounds](#listpreipocompanyfundingrounds) - List Pre IPO Company Funding Rounds
* [getPreIpoCompanyFundingRound](#getpreipocompanyfundinground) - Get Pre IPO Company Funding Round

## listPreIpoCompanyFundingRounds

Lists Pre IPO Company Funding Rounds.

### Example Usage

<!-- UsageSnippet language="java" operationID="PreIpoCompanyFundingRounds_ListPreIpoCompanyFundingRounds" method="get" path="/trading/v1/preIpoCompanies/{preIpoCompany_id}/fundingRounds" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.PreIpoCompanyFundingRoundsListPreIpoCompanyFundingRoundsResponse;
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

        PreIpoCompanyFundingRoundsListPreIpoCompanyFundingRoundsResponse res = sdk.preIPOFundingRounds().listPreIpoCompanyFundingRounds()
                .preIpoCompanyId("3fa85f64-5717-4562-b3fc-2c963f66afa6")
                .pageSize(50)
                .pageToken("eyJzaXplIjoxMCwib2Zmc2V0IjoxMDAsInBhcmVudElkIjoicGFyZW50SWQifQ==")
                .call();

        if (res.listPreIpoCompanyFundingRoundsResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                                                                      | Type                                                                                                                                                                                                                                                                           | Required                                                                                                                                                                                                                                                                       | Description                                                                                                                                                                                                                                                                    | Example                                                                                                                                                                                                                                                                        |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `preIpoCompanyId`                                                                                                                                                                                                                                                              | *String*                                                                                                                                                                                                                                                                       | :heavy_check_mark:                                                                                                                                                                                                                                                             | The preIpoCompany id.                                                                                                                                                                                                                                                          | 3fa85f64-5717-4562-b3fc-2c963f66afa6                                                                                                                                                                                                                                           |
| `pageSize`                                                                                                                                                                                                                                                                     | *Optional\<Integer>*                                                                                                                                                                                                                                                           | :heavy_minus_sign:                                                                                                                                                                                                                                                             | The maximum number of Pre IPO Company Funding Rounds to return. The service may return fewer than this value. If unspecified, at most 100 Pre IPO Company Funding Rounds will be returned. The maximum value is 100; values above 100 will be coerced to 100.                  | 50                                                                                                                                                                                                                                                                             |
| `pageToken`                                                                                                                                                                                                                                                                    | *Optional\<String>*                                                                                                                                                                                                                                                            | :heavy_minus_sign:                                                                                                                                                                                                                                                             | A page token, received from a previous `ListPreIpoCompanyFundingRoundsRequest` call. Provide this to retrieve the subsequent page. When paginating, all other parameters provided to `ListPreIpoCompanyFundingRoundsRequest` must match the call that provided the page token. | eyJzaXplIjoxMCwib2Zmc2V0IjoxMDAsInBhcmVudElkIjoicGFyZW50SWQifQ==                                                                                                                                                                                                               |

### Response

**[PreIpoCompanyFundingRoundsListPreIpoCompanyFundingRoundsResponse](../../models/operations/PreIpoCompanyFundingRoundsListPreIpoCompanyFundingRoundsResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 404     | application/json       |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getPreIpoCompanyFundingRound

Gets a Pre IPO Company Funding Round.

### Example Usage

<!-- UsageSnippet language="java" operationID="PreIpoCompanyFundingRounds_GetPreIpoCompanyFundingRound" method="get" path="/trading/v1/preIpoCompanies/{preIpoCompany_id}/fundingRounds/{fundingRound_id}" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.PreIpoCompanyFundingRoundsGetPreIpoCompanyFundingRoundResponse;
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

        PreIpoCompanyFundingRoundsGetPreIpoCompanyFundingRoundResponse res = sdk.preIPOFundingRounds().getPreIpoCompanyFundingRound()
                .preIpoCompanyId("3fa85f64-5717-4562-b3fc-2c963f66afa6")
                .fundingRoundId("5f29def7-445a-46e1-b0af-e475c5481820")
                .call();

        if (res.preIpoCompanyFundingRound().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                            | Type                                 | Required                             | Description                          | Example                              |
| ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ |
| `preIpoCompanyId`                    | *String*                             | :heavy_check_mark:                   | The preIpoCompany id.                | 3fa85f64-5717-4562-b3fc-2c963f66afa6 |
| `fundingRoundId`                     | *String*                             | :heavy_check_mark:                   | The fundingRound id.                 | 5f29def7-445a-46e1-b0af-e475c5481820 |

### Response

**[PreIpoCompanyFundingRoundsGetPreIpoCompanyFundingRoundResponse](../../models/operations/PreIpoCompanyFundingRoundsGetPreIpoCompanyFundingRoundResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 404     | application/json       |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |