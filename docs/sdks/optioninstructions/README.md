# OptionInstructions
(*optionInstructions()*)

## Overview

### Available Operations

* [createOptionInstruction](#createoptioninstruction) - Create Option Instruction
* [listOptionInstructions](#listoptioninstructions) - List Option Instructions
* [getOptionInstruction](#getoptioninstruction) - Get Option Instruction
* [cancelOptionInstruction](#canceloptioninstruction) - Cancel Option Instruction

## createOptionInstruction

CreateOptionInstruction creates a new option instruction for trading actions

### Example Usage

<!-- UsageSnippet language="java" operationID="ExerciseService_CreateOptionInstruction" method="post" path="/options/v1/accounts/{account_id}/assets/{asset_id}/instructions" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.operations.ExerciseServiceCreateOptionInstructionResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

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

        ExerciseServiceCreateOptionInstructionResponse res = sdk.optionInstructions().createOptionInstruction()
                .accountId("ACC123456")
                .assetId("12345")
                .optionInstructionCreate(OptionInstructionCreate.builder()
                    .accountId("01JTNGZM8PWRR6MHBCC6TEG9HE")
                    .identifier("AAPL250620P00090000")
                    .identifierType(OptionInstructionCreateIdentifierType.OSI)
                    .quantity(DecimalCreate.builder()
                        .build())
                    .type(OptionInstructionCreateType.DO_NOT_EXERCISE)
                    .build())
                .call();

        if (res.optionInstruction().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                     | Type                                                                          | Required                                                                      | Description                                                                   | Example                                                                       |
| ----------------------------------------------------------------------------- | ----------------------------------------------------------------------------- | ----------------------------------------------------------------------------- | ----------------------------------------------------------------------------- | ----------------------------------------------------------------------------- |
| `accountId`                                                                   | *String*                                                                      | :heavy_check_mark:                                                            | The account id.                                                               | ACC123456                                                                     |
| `assetId`                                                                     | *String*                                                                      | :heavy_check_mark:                                                            | The asset id.                                                                 | 12345                                                                         |
| `optionInstructionCreate`                                                     | [OptionInstructionCreate](../../models/components/OptionInstructionCreate.md) | :heavy_check_mark:                                                            | N/A                                                                           |                                                                               |

### Response

**[ExerciseServiceCreateOptionInstructionResponse](../../models/operations/ExerciseServiceCreateOptionInstructionResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## listOptionInstructions

ListOptionInstructions lists option instructions with optional filtering and pagination

### Example Usage

<!-- UsageSnippet language="java" operationID="ExerciseService_ListOptionInstructions" method="get" path="/options/v1/accounts/{account_id}/assets/{asset_id}/instructions" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.operations.ExerciseServiceListOptionInstructionsRequest;
import com.apexfintechsolutions.ascendsdk.models.operations.ExerciseServiceListOptionInstructionsResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

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

        ExerciseServiceListOptionInstructionsRequest req = ExerciseServiceListOptionInstructionsRequest.builder()
                .accountId("ACC123456")
                .assetId("12345")
                .pageSize(50)
                .pageToken("eyJvZmZzZXQiOjUwfQ==")
                .filter("type == 'DO_NOT_EXERCISE' && account_id == '12345'")
                .build();

        ExerciseServiceListOptionInstructionsResponse res = sdk.optionInstructions().listOptionInstructions()
                .request(req)
                .call();

        if (res.listOptionInstructionsResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                               | Type                                                                                                                    | Required                                                                                                                | Description                                                                                                             |
| ----------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------- |
| `request`                                                                                                               | [ExerciseServiceListOptionInstructionsRequest](../../models/operations/ExerciseServiceListOptionInstructionsRequest.md) | :heavy_check_mark:                                                                                                      | The request object to use for the request.                                                                              |

### Response

**[ExerciseServiceListOptionInstructionsResponse](../../models/operations/ExerciseServiceListOptionInstructionsResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getOptionInstruction

GetOptionInstruction retrieves an existing instruction by name

### Example Usage

<!-- UsageSnippet language="java" operationID="ExerciseService_GetOptionInstruction" method="get" path="/options/v1/accounts/{account_id}/assets/{asset_id}/instructions/{instruction_id}" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.operations.ExerciseServiceGetOptionInstructionResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

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

        ExerciseServiceGetOptionInstructionResponse res = sdk.optionInstructions().getOptionInstruction()
                .accountId("ACC123456")
                .assetId("12345")
                .instructionId("abc12345")
                .call();

        if (res.optionInstruction().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter           | Type                | Required            | Description         | Example             |
| ------------------- | ------------------- | ------------------- | ------------------- | ------------------- |
| `accountId`         | *String*            | :heavy_check_mark:  | The account id.     | ACC123456           |
| `assetId`           | *String*            | :heavy_check_mark:  | The asset id.       | 12345               |
| `instructionId`     | *String*            | :heavy_check_mark:  | The instruction id. | abc12345            |

### Response

**[ExerciseServiceGetOptionInstructionResponse](../../models/operations/ExerciseServiceGetOptionInstructionResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## cancelOptionInstruction

CancelOptionInstruction cancels an existing instruction by name

### Example Usage

<!-- UsageSnippet language="java" operationID="ExerciseService_CancelOptionInstruction" method="post" path="/options/v1/accounts/{account_id}/assets/{asset_id}/instructions/{instruction_id}:cancel" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.operations.ExerciseServiceCancelOptionInstructionResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Exception {

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

        ExerciseServiceCancelOptionInstructionResponse res = sdk.optionInstructions().cancelOptionInstruction()
                .accountId("ACC123456")
                .assetId("12345")
                .instructionId("abc12345")
                .cancelOptionInstructionRequestCreate(CancelOptionInstructionRequestCreate.builder()
                    .name("accounts/ACC123456/assets/12345/instructions/abc12345")
                    .build())
                .call();

        if (res.optionInstruction().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                               | Type                                                                                                    | Required                                                                                                | Description                                                                                             | Example                                                                                                 |
| ------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                             | *String*                                                                                                | :heavy_check_mark:                                                                                      | The account id.                                                                                         | ACC123456                                                                                               |
| `assetId`                                                                                               | *String*                                                                                                | :heavy_check_mark:                                                                                      | The asset id.                                                                                           | 12345                                                                                                   |
| `instructionId`                                                                                         | *String*                                                                                                | :heavy_check_mark:                                                                                      | The instruction id.                                                                                     | abc12345                                                                                                |
| `cancelOptionInstructionRequestCreate`                                                                  | [CancelOptionInstructionRequestCreate](../../models/components/CancelOptionInstructionRequestCreate.md) | :heavy_check_mark:                                                                                      | N/A                                                                                                     |                                                                                                         |

### Response

**[ExerciseServiceCancelOptionInstructionResponse](../../models/operations/ExerciseServiceCancelOptionInstructionResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |