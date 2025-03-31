# Investigations
(*investigations()*)

## Overview

### Available Operations

* [getInvestigation](#getinvestigation) - Get Investigations
* [updateInvestigation](#updateinvestigation) - Update Investigation 
* [listInvestigations](#listinvestigations) - List Investigations
* [linkDocuments](#linkdocuments) - Link Documents
* [getWatchlistItem](#getwatchlistitem) - Get Watchlist Item
* [getCustomerIdentification](#getcustomeridentification) - Get Identity Verification

## getInvestigation

Use this endpoint to get the current state of an investigation by request reference UUID.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.InvestigationServiceGetInvestigationResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
                    .build())
            .build();

        InvestigationServiceGetInvestigationResponse res = sdk.investigations().getInvestigation()
                .investigationId("01HEWVF4ZSNKYRP293J53ASJCJ")
                .call();

        if (res.investigation().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                  | Type                       | Required                   | Description                | Example                    |
| -------------------------- | -------------------------- | -------------------------- | -------------------------- | -------------------------- |
| `investigationId`          | *String*                   | :heavy_check_mark:         | The investigation id.      | 01HEWVF4ZSNKYRP293J53ASJCJ |

### Response

**[InvestigationServiceGetInvestigationResponse](../../models/operations/InvestigationServiceGetInvestigationResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## updateInvestigation

Use this endpoint to update the details of an investigation by request reference UUID.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.InvestigationUpdate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.InvestigationServiceUpdateInvestigationResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
                    .build())
            .build();

        InvestigationServiceUpdateInvestigationResponse res = sdk.investigations().updateInvestigation()
                .investigationId("01HEWVF4ZSNKYRP293J53ASJCJ")
                .updateMask("{\"update_mask\":\"identity_verification\"}")
                .investigationUpdate(InvestigationUpdate.builder()
                    .build())
                .call();

        if (res.investigation().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                         | Type                                                                                                                                                                                                                              | Required                                                                                                                                                                                                                          | Description                                                                                                                                                                                                                       | Example                                                                                                                                                                                                                           |
| --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `investigationId`                                                                                                                                                                                                                 | *String*                                                                                                                                                                                                                          | :heavy_check_mark:                                                                                                                                                                                                                | The investigation id.                                                                                                                                                                                                             | 01HEWVF4ZSNKYRP293J53ASJCJ                                                                                                                                                                                                        |
| `updateMask`                                                                                                                                                                                                                      | *Optional\<String>*                                                                                                                                                                                                               | :heavy_minus_sign:                                                                                                                                                                                                                | The list of fields to update. Updatable Fields:<br/>  - identity_verification<br/>  - investigation_request_state<br/>  - watchlist_matches<br/>   - watchlist_id<br/>   - watchlist_item_id<br/>   - match_state<br/>   - exclude_from_screening<br/>  - comment | {<br/>"update_mask": "identity_verification"<br/>}                                                                                                                                                                                |
| `investigationUpdate`                                                                                                                                                                                                             | [InvestigationUpdate](../../models/components/InvestigationUpdate.md)                                                                                                                                                             | :heavy_check_mark:                                                                                                                                                                                                                | N/A                                                                                                                                                                                                                               |                                                                                                                                                                                                                                   |

### Response

**[InvestigationServiceUpdateInvestigationResponse](../../models/operations/InvestigationServiceUpdateInvestigationResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## listInvestigations

Use this endpoint to retrieve a list of investigation summaries based on optional search parameters

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.InvestigationServiceListInvestigationsResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
                    .build())
            .build();

        InvestigationServiceListInvestigationsResponse res = sdk.investigations().listInvestigations()
                .pageSize(100)
                .pageToken("<value>")
                .filter("person.given_name == 'Jane' && person.family_name == 'Dough'")
                .call();

        if (res.listInvestigationsResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                                          | Type                                                                                                                                                                                                                                               | Required                                                                                                                                                                                                                                           | Description                                                                                                                                                                                                                                        | Example                                                                                                                                                                                                                                            |
| -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `pageSize`                                                                                                                                                                                                                                         | *Optional\<Integer>*                                                                                                                                                                                                                               | :heavy_minus_sign:                                                                                                                                                                                                                                 | The maximum number of records to return. Default is 50 The maximum is 200, values exceeding this will be set to 200                                                                                                                                | 100                                                                                                                                                                                                                                                |
| `pageToken`                                                                                                                                                                                                                                        | *Optional\<String>*                                                                                                                                                                                                                                | :heavy_minus_sign:                                                                                                                                                                                                                                 | The page token used to request a specific page of the search results                                                                                                                                                                               |                                                                                                                                                                                                                                                    |
| `filter`                                                                                                                                                                                                                                           | *Optional\<String>*                                                                                                                                                                                                                                | :heavy_minus_sign:                                                                                                                                                                                                                                 | A CEL string to filter results; See the [CEL Search](https://developer.apexclearing.com/apex-fintech-solutions/docs/cel-search) page in Guides for more information; Filter options include:<br/> ListInvestigationStatesResponse.investigation_states | person.given_name == 'Jane' && person.family_name == 'Dough'                                                                                                                                                                                       |

### Response

**[InvestigationServiceListInvestigationsResponse](../../models/operations/InvestigationServiceListInvestigationsResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## linkDocuments

Use this endpoint to update identity verification document IDs.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.LinkDocumentsRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.InvestigationServiceLinkDocumentsResponse;
import java.lang.Exception;
import java.util.List;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
                    .build())
            .build();

        InvestigationServiceLinkDocumentsResponse res = sdk.investigations().linkDocuments()
                .investigationId("01HEWVF4ZSNKYRP293J53ASJCJ")
                .linkDocumentsRequestCreate(LinkDocumentsRequestCreate.builder()
                    .identityVerificationDocumentIds(List.of(
                        "0f01ae1f-d24c-4171-8f3f-c0b820bf3044"))
                    .build())
                .call();

        if (res.linkDocumentsResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                           | Type                                                                                | Required                                                                            | Description                                                                         | Example                                                                             |
| ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- |
| `investigationId`                                                                   | *String*                                                                            | :heavy_check_mark:                                                                  | The investigation id.                                                               | 01HEWVF4ZSNKYRP293J53ASJCJ                                                          |
| `linkDocumentsRequestCreate`                                                        | [LinkDocumentsRequestCreate](../../models/components/LinkDocumentsRequestCreate.md) | :heavy_check_mark:                                                                  | N/A                                                                                 |                                                                                     |

### Response

**[InvestigationServiceLinkDocumentsResponse](../../models/operations/InvestigationServiceLinkDocumentsResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getWatchlistItem

Gets the details of an investigation by watchlist type and valid watchlist id

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.WatchlistServiceGetWatchlistItemResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
                    .build())
            .build();

        WatchlistServiceGetWatchlistItemResponse res = sdk.investigations().getWatchlistItem()
                .watchlistId("DOWJONES")
                .itemId("123456")
                .call();

        if (res.watchlistItem().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter          | Type               | Required           | Description        | Example            |
| ------------------ | ------------------ | ------------------ | ------------------ | ------------------ |
| `watchlistId`      | *String*           | :heavy_check_mark: | The watchlist id.  | DOWJONES           |
| `itemId`           | *String*           | :heavy_check_mark: | The item id.       | 123456             |

### Response

**[WatchlistServiceGetWatchlistItemResponse](../../models/operations/WatchlistServiceGetWatchlistItemResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getCustomerIdentification

Gets a CustomerIdentification by CustomerIdentification ID.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.CustomerIdentificationResultServiceGetCustomerIdentificationQueryParamView;
import com.apexfintechsolutions.ascendsdk.models.operations.CustomerIdentificationResultServiceGetCustomerIdentificationResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
                    .build())
            .build();

        CustomerIdentificationResultServiceGetCustomerIdentificationResponse res = sdk.investigations().getCustomerIdentification()
                .correspondentId("01HPMZZM6RKMVZA1JQ63RQKJRP")
                .customerIdentificationId("01HEWVF4ZSNKYRP293J53ASJCJ")
                .view(CustomerIdentificationResultServiceGetCustomerIdentificationQueryParamView.BASIC)
                .call();

        if (res.customerIdentification().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                      | Type                                                                                                                                                                                           | Required                                                                                                                                                                                       | Description                                                                                                                                                                                    | Example                                                                                                                                                                                        |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `correspondentId`                                                                                                                                                                              | *String*                                                                                                                                                                                       | :heavy_check_mark:                                                                                                                                                                             | The correspondent id.                                                                                                                                                                          | 01HPMZZM6RKMVZA1JQ63RQKJRP                                                                                                                                                                     |
| `customerIdentificationId`                                                                                                                                                                     | *String*                                                                                                                                                                                       | :heavy_check_mark:                                                                                                                                                                             | The customerIdentification id.                                                                                                                                                                 | 01HEWVF4ZSNKYRP293J53ASJCJ                                                                                                                                                                     |
| `view`                                                                                                                                                                                         | [Optional\<CustomerIdentificationResultServiceGetCustomerIdentificationQueryParamView>](../../models/operations/CustomerIdentificationResultServiceGetCustomerIdentificationQueryParamView.md) | :heavy_minus_sign:                                                                                                                                                                             | Optional. The view to return. Defaults to BASIC.                                                                                                                                               | BASIC                                                                                                                                                                                          |

### Response

**[CustomerIdentificationResultServiceGetCustomerIdentificationResponse](../../models/operations/CustomerIdentificationResultServiceGetCustomerIdentificationResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |