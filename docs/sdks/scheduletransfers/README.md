# ScheduleTransfers
(*scheduleTransfers()*)

## Overview

### Available Operations

* [listScheduleSummaries](#listschedulesummaries) - List Schedule Summaries
* [createAchDepositSchedule](#createachdepositschedule) - Create ACH Deposit Schedule
* [listAchDepositSchedules](#listachdepositschedules) - List ACH Deposit Schedules
* [getAchDepositSchedule](#getachdepositschedule) - Get ACH Deposit Schedule
* [updateAchDepositSchedule](#updateachdepositschedule) - Update ACH Deposit Schedules
* [cancelAchDepositSchedule](#cancelachdepositschedule) - Cancel ACH Deposit Schedule
* [createAchWithdrawalSchedule](#createachwithdrawalschedule) - Create ACH Withdrawal Schedule
* [listAchWithdrawalSchedules](#listachwithdrawalschedules) - List ACH Withdrawal Schedules
* [getAchWithdrawalSchedule](#getachwithdrawalschedule) - Get ACH Withdrawal Schedule
* [updateAchWithdrawalSchedule](#updateachwithdrawalschedule) - Update ACH Withdrawal Schedule
* [cancelAchWithdrawalSchedule](#cancelachwithdrawalschedule) - Cancel ACH Withdrawal Schedule
* [createCheckWithdrawalSchedule](#createcheckwithdrawalschedule) - Create Check Withdrawal Schedule
* [listCheckWithdrawalSchedules](#listcheckwithdrawalschedules) - List Check Withdrawal Schedules
* [getCheckWithdrawalSchedule](#getcheckwithdrawalschedule) - Get Check Withdrawal Schedule
* [updateCheckWithdrawalSchedule](#updatecheckwithdrawalschedule) - Update Check Withdrawal Schedule
* [cancelCheckWithdrawalSchedule](#cancelcheckwithdrawalschedule) - Cancel Check Withdrawal Schedule
* [createWireWithdrawalSchedule](#createwirewithdrawalschedule) - Create Wire Withdrawal Schedule
* [listWireWithdrawalSchedules](#listwirewithdrawalschedules) - List Wire Withdrawal Schedules
* [getWireWithdrawalSchedule](#getwirewithdrawalschedule) - Get Wire Withdrawal Schedule
* [updateWireWithdrawalSchedule](#updatewirewithdrawalschedule) - Update Wire Withdrawal Schedule
* [cancelWireWithdrawalSchedule](#cancelwirewithdrawalschedule) - Cancel Wire Withdrawal Schedule

## listScheduleSummaries

Lists transfer schedule summaries that match the filter in the request

### Example Usage

<!-- UsageSnippet language="java" operationID="TransferScheduleSummaries_ListScheduleSummaries" method="get" path="/transfers/v1/schedules" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.TransferScheduleSummariesListScheduleSummariesResponse;
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


        sdk.scheduleTransfers().listScheduleSummaries()
                .filter("mechanism == 'ACH' && direction == DEPOSIT && state == 'ACTIVE' && start_date > '2024-04-05' && end_date < '2024-08-10'")
                .pageSize(100)
                .pageToken("4ZHd3wAaMD1IQ0ZKS2BKV0FSRVdLW4VLWkY1R1B3MU4")
                .callAsStream()
                .forEach((TransferScheduleSummariesListScheduleSummariesResponse item) -> {
                   // handle page
                });

    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                                                            | Type                                                                                                                                                                                                                                                                 | Required                                                                                                                                                                                                                                                             | Description                                                                                                                                                                                                                                                          | Example                                                                                                                                                                                                                                                              |
| -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `filter`                                                                                                                                                                                                                                                             | *Optional\<String>*                                                                                                                                                                                                                                                  | :heavy_minus_sign:                                                                                                                                                                                                                                                   | A CEL string to filter results; See the [CEL Search](https://developer.apexclearing.com/apex-fintech-solutions/docs/cel-search) page in Guides for more information; Filter options include:<br/> `account`<br/> `mechanism`<br/> `direction`<br/> `state`<br/> `start_date`<br/> `end_date` | mechanism == 'ACH' && direction == DEPOSIT && state == 'ACTIVE' && start_date > '2024-04-05' && end_date < '2024-08-10'                                                                                                                                              |
| `pageSize`                                                                                                                                                                                                                                                           | *Optional\<Integer>*                                                                                                                                                                                                                                                 | :heavy_minus_sign:                                                                                                                                                                                                                                                   | The maximum number of schedules to return. The service may return fewer than this value. If unspecified, at most 25 schedules will be returned. The maximum value is 1000; values above 1000 will be coerced to 1000.                                                | 100                                                                                                                                                                                                                                                                  |
| `pageToken`                                                                                                                                                                                                                                                          | *Optional\<String>*                                                                                                                                                                                                                                                  | :heavy_minus_sign:                                                                                                                                                                                                                                                   | The page token to request                                                                                                                                                                                                                                            | 4ZHd3wAaMD1IQ0ZKS2BKV0FSRVdLW4VLWkY1R1B3MU4                                                                                                                                                                                                                          |

### Response

**[TransferScheduleSummariesListScheduleSummariesResponse](../../models/operations/TransferScheduleSummariesListScheduleSummariesResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## createAchDepositSchedule

Creates an ACH deposit transfer schedule

### Example Usage

<!-- UsageSnippet language="java" operationID="AchDepositSchedules_CreateAchDepositSchedule" method="post" path="/transfers/v1/accounts/{account_id}/achDepositSchedules" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AchDepositSchedulesCreateAchDepositScheduleResponse;
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

        AchDepositSchedulesCreateAchDepositScheduleResponse res = sdk.scheduleTransfers().createAchDepositSchedule()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .achDepositScheduleCreate(AchDepositScheduleCreate.builder()
                    .bankRelationship("accounts/01H8FB90ZRRFWXB4XC2JPJ1D4Y/bankRelationships/651ef9de0dee00240813e60e")
                    .scheduleDetails(DepositScheduleDetailsCreate.builder()
                        .amount(DecimalCreate.builder()
                            .build())
                        .clientScheduleId("ABC-123")
                        .scheduleProperties(SchedulePropertiesCreate.builder()
                            .startDate(DateCreate.builder()
                                .build())
                            .timeUnit(TimeUnit.MONTH)
                            .unitMultiplier(1)
                            .build())
                        .build())
                    .build())
                .call();

        if (res.achDepositSchedule().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                       | Type                                                                            | Required                                                                        | Description                                                                     | Example                                                                         |
| ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- |
| `accountId`                                                                     | *String*                                                                        | :heavy_check_mark:                                                              | The account id.                                                                 | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                      |
| `achDepositScheduleCreate`                                                      | [AchDepositScheduleCreate](../../models/components/AchDepositScheduleCreate.md) | :heavy_check_mark:                                                              | N/A                                                                             |                                                                                 |

### Response

**[AchDepositSchedulesCreateAchDepositScheduleResponse](../../models/operations/AchDepositSchedulesCreateAchDepositScheduleResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 409          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## listAchDepositSchedules

Return a list of ACH deposit schedules for the specified account and filter params

### Example Usage

<!-- UsageSnippet language="java" operationID="AchDepositSchedules_ListAchDepositSchedules" method="get" path="/transfers/v1/accounts/{account_id}/achDepositSchedules" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AchDepositSchedulesListAchDepositSchedulesResponse;
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


        sdk.scheduleTransfers().listAchDepositSchedules()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .filter("state == 'ACTIVE' && start_date > '2024-04-05' && end_date < '2024-08-10'")
                .pageSize(100)
                .pageToken("4ZHd3wAaMD1IQ0ZKS2BKV0FSRVdLW4VLWkY1R1B3MU4")
                .callAsStream()
                .forEach((AchDepositSchedulesListAchDepositSchedulesResponse item) -> {
                   // handle page
                });

    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                       | Type                                                                                                                                                                                                                            | Required                                                                                                                                                                                                                        | Description                                                                                                                                                                                                                     | Example                                                                                                                                                                                                                         |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                                                                                                                                     | *String*                                                                                                                                                                                                                        | :heavy_check_mark:                                                                                                                                                                                                              | The account id.                                                                                                                                                                                                                 | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                                                                                                                                                      |
| `filter`                                                                                                                                                                                                                        | *Optional\<String>*                                                                                                                                                                                                             | :heavy_minus_sign:                                                                                                                                                                                                              | A CEL string to filter results; See the [CEL Search](https://developer.apexclearing.com/apex-fintech-solutions/docs/cel-search) page in Guides for more information; Filter options include:<br/> `state`<br/> `start_date`<br/> `end_date` | state == 'ACTIVE' && start_date > '2024-04-05' && end_date < '2024-08-10'                                                                                                                                                       |
| `pageSize`                                                                                                                                                                                                                      | *Optional\<Integer>*                                                                                                                                                                                                            | :heavy_minus_sign:                                                                                                                                                                                                              | The maximum number of schedules to return. The service may return fewer than this value. If unspecified, at most 25 schedules will be returned. The maximum value is 1000; values above 1000 will be coerced to 1000.           | 100                                                                                                                                                                                                                             |
| `pageToken`                                                                                                                                                                                                                     | *Optional\<String>*                                                                                                                                                                                                             | :heavy_minus_sign:                                                                                                                                                                                                              | The page token to request                                                                                                                                                                                                       | 4ZHd3wAaMD1IQ0ZKS2BKV0FSRVdLW4VLWkY1R1B3MU4                                                                                                                                                                                     |

### Response

**[AchDepositSchedulesListAchDepositSchedulesResponse](../../models/operations/AchDepositSchedulesListAchDepositSchedulesResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getAchDepositSchedule

Gets an ACH deposit transfer schedule

### Example Usage

<!-- UsageSnippet language="java" operationID="AchDepositSchedules_GetAchDepositSchedule" method="get" path="/transfers/v1/accounts/{account_id}/achDepositSchedules/{achDepositSchedule_id}" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AchDepositSchedulesGetAchDepositScheduleResponse;
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

        AchDepositSchedulesGetAchDepositScheduleResponse res = sdk.scheduleTransfers().getAchDepositSchedule()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .achDepositScheduleId("40eb6b6f-76ff-4dc9-b8a0-b65a7658f8b1")
                .call();

        if (res.achDepositSchedule().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                            | Type                                 | Required                             | Description                          | Example                              |
| ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ |
| `accountId`                          | *String*                             | :heavy_check_mark:                   | The account id.                      | 01H8FB90ZRRFWXB4XC2JPJ1D4Y           |
| `achDepositScheduleId`               | *String*                             | :heavy_check_mark:                   | The achDepositSchedule id.           | 40eb6b6f-76ff-4dc9-b8a0-b65a7658f8b1 |

### Response

**[AchDepositSchedulesGetAchDepositScheduleResponse](../../models/operations/AchDepositSchedulesGetAchDepositScheduleResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## updateAchDepositSchedule

Updates the amount of an ACH deposit transfer schedule

### Example Usage

<!-- UsageSnippet language="java" operationID="AchDepositSchedules_UpdateAchDepositSchedule" method="patch" path="/transfers/v1/accounts/{account_id}/achDepositSchedules/{achDepositSchedule_id}" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AchDepositSchedulesUpdateAchDepositScheduleResponse;
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

        AchDepositSchedulesUpdateAchDepositScheduleResponse res = sdk.scheduleTransfers().updateAchDepositSchedule()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .achDepositScheduleId("40eb6b6f-76ff-4dc9-b8a0-b65a7658f8b1")
                .achDepositScheduleUpdate(AchDepositScheduleUpdate.builder()
                    .build())
                .call();

        if (res.achDepositSchedule().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                 | Type                                                                                                                      | Required                                                                                                                  | Description                                                                                                               | Example                                                                                                                   |
| ------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                               | *String*                                                                                                                  | :heavy_check_mark:                                                                                                        | The account id.                                                                                                           | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                                                |
| `achDepositScheduleId`                                                                                                    | *String*                                                                                                                  | :heavy_check_mark:                                                                                                        | The achDepositSchedule id.                                                                                                | 40eb6b6f-76ff-4dc9-b8a0-b65a7658f8b1                                                                                      |
| `updateMask`                                                                                                              | *Optional\<String>*                                                                                                       | :heavy_minus_sign:                                                                                                        | A field mask representing the update. Note: only the 'schedule_details.amount' field of a schedule is currently updatable |                                                                                                                           |
| `achDepositScheduleUpdate`                                                                                                | [AchDepositScheduleUpdate](../../models/components/AchDepositScheduleUpdate.md)                                           | :heavy_check_mark:                                                                                                        | N/A                                                                                                                       |                                                                                                                           |

### Response

**[AchDepositSchedulesUpdateAchDepositScheduleResponse](../../models/operations/AchDepositSchedulesUpdateAchDepositScheduleResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## cancelAchDepositSchedule

Cancels an ACH deposit transfer schedule

### Example Usage

<!-- UsageSnippet language="java" operationID="AchDepositSchedules_CancelAchDepositSchedule" method="post" path="/transfers/v1/accounts/{account_id}/achDepositSchedules/{achDepositSchedule_id}:cancel" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AchDepositSchedulesCancelAchDepositScheduleResponse;
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

        AchDepositSchedulesCancelAchDepositScheduleResponse res = sdk.scheduleTransfers().cancelAchDepositSchedule()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .achDepositScheduleId("40eb6b6f-76ff-4dc9-b8a0-b65a7658f8b1")
                .cancelAchDepositScheduleRequestCreate(CancelAchDepositScheduleRequestCreate.builder()
                    .name("accounts/01H8FB90ZRRFWXB4XC2JPJ1D4Y/achDepositSchedules/40eb6b6f-76ff-4dc9-b8a0-b65a7658f8b1")
                    .build())
                .call();

        if (res.achDepositSchedule().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                 | Type                                                                                                      | Required                                                                                                  | Description                                                                                               | Example                                                                                                   |
| --------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                               | *String*                                                                                                  | :heavy_check_mark:                                                                                        | The account id.                                                                                           | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                                |
| `achDepositScheduleId`                                                                                    | *String*                                                                                                  | :heavy_check_mark:                                                                                        | The achDepositSchedule id.                                                                                | 40eb6b6f-76ff-4dc9-b8a0-b65a7658f8b1                                                                      |
| `cancelAchDepositScheduleRequestCreate`                                                                   | [CancelAchDepositScheduleRequestCreate](../../models/components/CancelAchDepositScheduleRequestCreate.md) | :heavy_check_mark:                                                                                        | N/A                                                                                                       |                                                                                                           |

### Response

**[AchDepositSchedulesCancelAchDepositScheduleResponse](../../models/operations/AchDepositSchedulesCancelAchDepositScheduleResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## createAchWithdrawalSchedule

Creates an ACH withdrawal transfer schedule

### Example Usage

<!-- UsageSnippet language="java" operationID="AchWithdrawalSchedules_CreateAchWithdrawalSchedule" method="post" path="/transfers/v1/accounts/{account_id}/achWithdrawalSchedules" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AchWithdrawalSchedulesCreateAchWithdrawalScheduleResponse;
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

        AchWithdrawalSchedulesCreateAchWithdrawalScheduleResponse res = sdk.scheduleTransfers().createAchWithdrawalSchedule()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .achWithdrawalScheduleCreate(AchWithdrawalScheduleCreate.builder()
                    .bankRelationship("accounts/01H8FB90ZRRFWXB4XC2JPJ1D4Y/bankRelationships/651ef9de0dee00240813e60e")
                    .scheduleDetails(WithdrawalScheduleDetailsCreate.builder()
                        .clientScheduleId("ABC-123")
                        .scheduleProperties(SchedulePropertiesCreate.builder()
                            .startDate(DateCreate.builder()
                                .build())
                            .timeUnit(TimeUnit.MONTH)
                            .unitMultiplier(1)
                            .build())
                        .build())
                    .build())
                .call();

        if (res.achWithdrawalSchedule().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                             | Type                                                                                  | Required                                                                              | Description                                                                           | Example                                                                               |
| ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- |
| `accountId`                                                                           | *String*                                                                              | :heavy_check_mark:                                                                    | The account id.                                                                       | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                            |
| `achWithdrawalScheduleCreate`                                                         | [AchWithdrawalScheduleCreate](../../models/components/AchWithdrawalScheduleCreate.md) | :heavy_check_mark:                                                                    | N/A                                                                                   |                                                                                       |

### Response

**[AchWithdrawalSchedulesCreateAchWithdrawalScheduleResponse](../../models/operations/AchWithdrawalSchedulesCreateAchWithdrawalScheduleResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 409          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## listAchWithdrawalSchedules

Return a list of ACH withdrawal schedules for the specified account and filter params

### Example Usage

<!-- UsageSnippet language="java" operationID="AchWithdrawalSchedules_ListAchWithdrawalSchedules" method="get" path="/transfers/v1/accounts/{account_id}/achWithdrawalSchedules" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AchWithdrawalSchedulesListAchWithdrawalSchedulesResponse;
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


        sdk.scheduleTransfers().listAchWithdrawalSchedules()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .filter("state == 'ACTIVE' && start_date > '2024-04-05' && end_date < '2024-08-10'")
                .pageSize(100)
                .pageToken("4ZHd3wAaMD1IQ0ZKS2BKV0FSRVdLW4VLWkY1R1B3MU4")
                .callAsStream()
                .forEach((AchWithdrawalSchedulesListAchWithdrawalSchedulesResponse item) -> {
                   // handle page
                });

    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                       | Type                                                                                                                                                                                                                            | Required                                                                                                                                                                                                                        | Description                                                                                                                                                                                                                     | Example                                                                                                                                                                                                                         |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                                                                                                                                     | *String*                                                                                                                                                                                                                        | :heavy_check_mark:                                                                                                                                                                                                              | The account id.                                                                                                                                                                                                                 | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                                                                                                                                                      |
| `filter`                                                                                                                                                                                                                        | *Optional\<String>*                                                                                                                                                                                                             | :heavy_minus_sign:                                                                                                                                                                                                              | A CEL string to filter results; See the [CEL Search](https://developer.apexclearing.com/apex-fintech-solutions/docs/cel-search) page in Guides for more information; Filter options include:<br/> `state`<br/> `start_date`<br/> `end_date` | state == 'ACTIVE' && start_date > '2024-04-05' && end_date < '2024-08-10'                                                                                                                                                       |
| `pageSize`                                                                                                                                                                                                                      | *Optional\<Integer>*                                                                                                                                                                                                            | :heavy_minus_sign:                                                                                                                                                                                                              | The maximum number of schedules to return. The service may return fewer than this value. If unspecified, at most 25 schedules will be returned. The maximum value is 1000; values above 1000 will be coerced to 1000.           | 100                                                                                                                                                                                                                             |
| `pageToken`                                                                                                                                                                                                                     | *Optional\<String>*                                                                                                                                                                                                             | :heavy_minus_sign:                                                                                                                                                                                                              | The page token to request                                                                                                                                                                                                       | 4ZHd3wAaMD1IQ0ZKS2BKV0FSRVdLW4VLWkY1R1B3MU4                                                                                                                                                                                     |

### Response

**[AchWithdrawalSchedulesListAchWithdrawalSchedulesResponse](../../models/operations/AchWithdrawalSchedulesListAchWithdrawalSchedulesResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getAchWithdrawalSchedule

Gets an ACH withdrawal transfer schedule

### Example Usage

<!-- UsageSnippet language="java" operationID="AchWithdrawalSchedules_GetAchWithdrawalSchedule" method="get" path="/transfers/v1/accounts/{account_id}/achWithdrawalSchedules/{achWithdrawalSchedule_id}" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AchWithdrawalSchedulesGetAchWithdrawalScheduleResponse;
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

        AchWithdrawalSchedulesGetAchWithdrawalScheduleResponse res = sdk.scheduleTransfers().getAchWithdrawalSchedule()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .achWithdrawalScheduleId("40eb6b6f-76ff-4dc9-b8a0-b65a7658f8b1")
                .call();

        if (res.achWithdrawalSchedule().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                            | Type                                 | Required                             | Description                          | Example                              |
| ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ |
| `accountId`                          | *String*                             | :heavy_check_mark:                   | The account id.                      | 01H8FB90ZRRFWXB4XC2JPJ1D4Y           |
| `achWithdrawalScheduleId`            | *String*                             | :heavy_check_mark:                   | The achWithdrawalSchedule id.        | 40eb6b6f-76ff-4dc9-b8a0-b65a7658f8b1 |

### Response

**[AchWithdrawalSchedulesGetAchWithdrawalScheduleResponse](../../models/operations/AchWithdrawalSchedulesGetAchWithdrawalScheduleResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## updateAchWithdrawalSchedule

Updates the amount of an ACH withdrawal transfer schedule

### Example Usage

<!-- UsageSnippet language="java" operationID="AchWithdrawalSchedules_UpdateAchWithdrawalSchedule" method="patch" path="/transfers/v1/accounts/{account_id}/achWithdrawalSchedules/{achWithdrawalSchedule_id}" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AchWithdrawalSchedulesUpdateAchWithdrawalScheduleResponse;
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

        AchWithdrawalSchedulesUpdateAchWithdrawalScheduleResponse res = sdk.scheduleTransfers().updateAchWithdrawalSchedule()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .achWithdrawalScheduleId("40eb6b6f-76ff-4dc9-b8a0-b65a7658f8b1")
                .achWithdrawalScheduleUpdate(AchWithdrawalScheduleUpdate.builder()
                    .build())
                .call();

        if (res.achWithdrawalSchedule().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                 | Type                                                                                                                      | Required                                                                                                                  | Description                                                                                                               | Example                                                                                                                   |
| ------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                               | *String*                                                                                                                  | :heavy_check_mark:                                                                                                        | The account id.                                                                                                           | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                                                |
| `achWithdrawalScheduleId`                                                                                                 | *String*                                                                                                                  | :heavy_check_mark:                                                                                                        | The achWithdrawalSchedule id.                                                                                             | 40eb6b6f-76ff-4dc9-b8a0-b65a7658f8b1                                                                                      |
| `updateMask`                                                                                                              | *Optional\<String>*                                                                                                       | :heavy_minus_sign:                                                                                                        | A field mask representing the update. Note: only the 'schedule_details.amount' field of a schedule is currently updatable |                                                                                                                           |
| `achWithdrawalScheduleUpdate`                                                                                             | [AchWithdrawalScheduleUpdate](../../models/components/AchWithdrawalScheduleUpdate.md)                                     | :heavy_check_mark:                                                                                                        | N/A                                                                                                                       |                                                                                                                           |

### Response

**[AchWithdrawalSchedulesUpdateAchWithdrawalScheduleResponse](../../models/operations/AchWithdrawalSchedulesUpdateAchWithdrawalScheduleResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## cancelAchWithdrawalSchedule

Cancels an ACH withdrawal transfer schedule

### Example Usage

<!-- UsageSnippet language="java" operationID="AchWithdrawalSchedules_CancelAchWithdrawalSchedule" method="post" path="/transfers/v1/accounts/{account_id}/achWithdrawalSchedules/{achWithdrawalSchedule_id}:cancel" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AchWithdrawalSchedulesCancelAchWithdrawalScheduleResponse;
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

        AchWithdrawalSchedulesCancelAchWithdrawalScheduleResponse res = sdk.scheduleTransfers().cancelAchWithdrawalSchedule()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .achWithdrawalScheduleId("40eb6b6f-76ff-4dc9-b8a0-b65a7658f8b1")
                .cancelAchWithdrawalScheduleRequestCreate(CancelAchWithdrawalScheduleRequestCreate.builder()
                    .name("accounts/01H8FB90ZRRFWXB4XC2JPJ1D4Y/achWithdrawalSchedules/40eb6b6f-76ff-4dc9-b8a0-b65a7658f8b1")
                    .build())
                .call();

        if (res.achWithdrawalSchedule().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                       | Type                                                                                                            | Required                                                                                                        | Description                                                                                                     | Example                                                                                                         |
| --------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                     | *String*                                                                                                        | :heavy_check_mark:                                                                                              | The account id.                                                                                                 | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                                      |
| `achWithdrawalScheduleId`                                                                                       | *String*                                                                                                        | :heavy_check_mark:                                                                                              | The achWithdrawalSchedule id.                                                                                   | 40eb6b6f-76ff-4dc9-b8a0-b65a7658f8b1                                                                            |
| `cancelAchWithdrawalScheduleRequestCreate`                                                                      | [CancelAchWithdrawalScheduleRequestCreate](../../models/components/CancelAchWithdrawalScheduleRequestCreate.md) | :heavy_check_mark:                                                                                              | N/A                                                                                                             |                                                                                                                 |

### Response

**[AchWithdrawalSchedulesCancelAchWithdrawalScheduleResponse](../../models/operations/AchWithdrawalSchedulesCancelAchWithdrawalScheduleResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## createCheckWithdrawalSchedule

Creates a Check withdrawal transfer schedule

### Example Usage

<!-- UsageSnippet language="java" operationID="CheckWithdrawalSchedules_CreateCheckWithdrawalSchedule" method="post" path="/transfers/v1/accounts/{account_id}/checkWithdrawalSchedules" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.CheckWithdrawalSchedulesCreateCheckWithdrawalScheduleResponse;
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

        CheckWithdrawalSchedulesCreateCheckWithdrawalScheduleResponse res = sdk.scheduleTransfers().createCheckWithdrawalSchedule()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .checkWithdrawalScheduleCreate(CheckWithdrawalScheduleCreate.builder()
                    .deliveryMethod(DeliveryMethod.STANDARD)
                    .scheduleDetails(WithdrawalScheduleDetailsCreate.builder()
                        .clientScheduleId("ABC-123")
                        .scheduleProperties(SchedulePropertiesCreate.builder()
                            .startDate(DateCreate.builder()
                                .build())
                            .timeUnit(TimeUnit.MONTH)
                            .unitMultiplier(1)
                            .build())
                        .build())
                    .build())
                .call();

        if (res.checkWithdrawalSchedule().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                 | Type                                                                                      | Required                                                                                  | Description                                                                               | Example                                                                                   |
| ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| `accountId`                                                                               | *String*                                                                                  | :heavy_check_mark:                                                                        | The account id.                                                                           | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                |
| `checkWithdrawalScheduleCreate`                                                           | [CheckWithdrawalScheduleCreate](../../models/components/CheckWithdrawalScheduleCreate.md) | :heavy_check_mark:                                                                        | N/A                                                                                       |                                                                                           |

### Response

**[CheckWithdrawalSchedulesCreateCheckWithdrawalScheduleResponse](../../models/operations/CheckWithdrawalSchedulesCreateCheckWithdrawalScheduleResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 409          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## listCheckWithdrawalSchedules

Return a list of Check withdrawal schedules for the specified account and filter params

### Example Usage

<!-- UsageSnippet language="java" operationID="CheckWithdrawalSchedules_ListCheckWithdrawalSchedules" method="get" path="/transfers/v1/accounts/{account_id}/checkWithdrawalSchedules" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.CheckWithdrawalSchedulesListCheckWithdrawalSchedulesResponse;
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

        CheckWithdrawalSchedulesListCheckWithdrawalSchedulesResponse res = sdk.scheduleTransfers().listCheckWithdrawalSchedules()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .filter("state == 'ACTIVE' && start_date > '2024-04-05' && end_date < '2024-08-10'")
                .pageSize(100)
                .pageToken("4ZHd3wAaMD1IQ0ZKS2BKV0FSRVdLW4VLWkY1R1B3MU4")
                .call();

        if (res.listCheckWithdrawalSchedulesResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                       | Type                                                                                                                                                                                                                            | Required                                                                                                                                                                                                                        | Description                                                                                                                                                                                                                     | Example                                                                                                                                                                                                                         |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                                                                                                                                     | *String*                                                                                                                                                                                                                        | :heavy_check_mark:                                                                                                                                                                                                              | The account id.                                                                                                                                                                                                                 | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                                                                                                                                                      |
| `filter`                                                                                                                                                                                                                        | *Optional\<String>*                                                                                                                                                                                                             | :heavy_minus_sign:                                                                                                                                                                                                              | A CEL string to filter results; See the [CEL Search](https://developer.apexclearing.com/apex-fintech-solutions/docs/cel-search) page in Guides for more information; Filter options include:<br/> `state`<br/> `start_date`<br/> `end_date` | state == 'ACTIVE' && start_date > '2024-04-05' && end_date < '2024-08-10'                                                                                                                                                       |
| `pageSize`                                                                                                                                                                                                                      | *Optional\<Integer>*                                                                                                                                                                                                            | :heavy_minus_sign:                                                                                                                                                                                                              | The maximum number of schedules to return. The service may return fewer than this value. If unspecified, at most 25 schedules will be returned. The maximum value is 1000; values above 1000 will be coerced to 1000.           | 100                                                                                                                                                                                                                             |
| `pageToken`                                                                                                                                                                                                                     | *Optional\<String>*                                                                                                                                                                                                             | :heavy_minus_sign:                                                                                                                                                                                                              | The page token to request                                                                                                                                                                                                       | 4ZHd3wAaMD1IQ0ZKS2BKV0FSRVdLW4VLWkY1R1B3MU4                                                                                                                                                                                     |

### Response

**[CheckWithdrawalSchedulesListCheckWithdrawalSchedulesResponse](../../models/operations/CheckWithdrawalSchedulesListCheckWithdrawalSchedulesResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getCheckWithdrawalSchedule

Gets a Check withdrawal transfer schedule

### Example Usage

<!-- UsageSnippet language="java" operationID="CheckWithdrawalSchedules_GetCheckWithdrawalSchedule" method="get" path="/transfers/v1/accounts/{account_id}/checkWithdrawalSchedules/{checkWithdrawalSchedule_id}" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.CheckWithdrawalSchedulesGetCheckWithdrawalScheduleResponse;
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

        CheckWithdrawalSchedulesGetCheckWithdrawalScheduleResponse res = sdk.scheduleTransfers().getCheckWithdrawalSchedule()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .checkWithdrawalScheduleId("40eb6b6f-76ff-4dc9-b8a0-b65a7658f8b1")
                .call();

        if (res.checkWithdrawalSchedule().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                            | Type                                 | Required                             | Description                          | Example                              |
| ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ |
| `accountId`                          | *String*                             | :heavy_check_mark:                   | The account id.                      | 01H8FB90ZRRFWXB4XC2JPJ1D4Y           |
| `checkWithdrawalScheduleId`          | *String*                             | :heavy_check_mark:                   | The checkWithdrawalSchedule id.      | 40eb6b6f-76ff-4dc9-b8a0-b65a7658f8b1 |

### Response

**[CheckWithdrawalSchedulesGetCheckWithdrawalScheduleResponse](../../models/operations/CheckWithdrawalSchedulesGetCheckWithdrawalScheduleResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## updateCheckWithdrawalSchedule

Updates the amount of a Check withdrawal transfer schedule

### Example Usage

<!-- UsageSnippet language="java" operationID="CheckWithdrawalSchedules_UpdateCheckWithdrawalSchedule" method="patch" path="/transfers/v1/accounts/{account_id}/checkWithdrawalSchedules/{checkWithdrawalSchedule_id}" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.CheckWithdrawalSchedulesUpdateCheckWithdrawalScheduleResponse;
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

        CheckWithdrawalSchedulesUpdateCheckWithdrawalScheduleResponse res = sdk.scheduleTransfers().updateCheckWithdrawalSchedule()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .checkWithdrawalScheduleId("40eb6b6f-76ff-4dc9-b8a0-b65a7658f8b1")
                .checkWithdrawalScheduleUpdate(CheckWithdrawalScheduleUpdate.builder()
                    .build())
                .call();

        if (res.checkWithdrawalSchedule().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                 | Type                                                                                                                      | Required                                                                                                                  | Description                                                                                                               | Example                                                                                                                   |
| ------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                               | *String*                                                                                                                  | :heavy_check_mark:                                                                                                        | The account id.                                                                                                           | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                                                |
| `checkWithdrawalScheduleId`                                                                                               | *String*                                                                                                                  | :heavy_check_mark:                                                                                                        | The checkWithdrawalSchedule id.                                                                                           | 40eb6b6f-76ff-4dc9-b8a0-b65a7658f8b1                                                                                      |
| `updateMask`                                                                                                              | *Optional\<String>*                                                                                                       | :heavy_minus_sign:                                                                                                        | A field mask representing the update. Note: only the 'schedule_details.amount' field of a schedule is currently updatable |                                                                                                                           |
| `checkWithdrawalScheduleUpdate`                                                                                           | [CheckWithdrawalScheduleUpdate](../../models/components/CheckWithdrawalScheduleUpdate.md)                                 | :heavy_check_mark:                                                                                                        | N/A                                                                                                                       |                                                                                                                           |

### Response

**[CheckWithdrawalSchedulesUpdateCheckWithdrawalScheduleResponse](../../models/operations/CheckWithdrawalSchedulesUpdateCheckWithdrawalScheduleResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## cancelCheckWithdrawalSchedule

Cancels a Check withdrawal transfer schedule

### Example Usage

<!-- UsageSnippet language="java" operationID="CheckWithdrawalSchedules_CancelCheckWithdrawalSchedule" method="post" path="/transfers/v1/accounts/{account_id}/checkWithdrawalSchedules/{checkWithdrawalSchedule_id}:cancel" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.CheckWithdrawalSchedulesCancelCheckWithdrawalScheduleResponse;
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

        CheckWithdrawalSchedulesCancelCheckWithdrawalScheduleResponse res = sdk.scheduleTransfers().cancelCheckWithdrawalSchedule()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .checkWithdrawalScheduleId("40eb6b6f-76ff-4dc9-b8a0-b65a7658f8b1")
                .cancelCheckWithdrawalScheduleRequestCreate(CancelCheckWithdrawalScheduleRequestCreate.builder()
                    .name("accounts/01H8FB90ZRRFWXB4XC2JPJ1D4Y/checkWithdrawalSchedules/40eb6b6f-76ff-4dc9-b8a0-b65a7658f8b1")
                    .build())
                .call();

        if (res.checkWithdrawalSchedule().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                           | Type                                                                                                                | Required                                                                                                            | Description                                                                                                         | Example                                                                                                             |
| ------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                         | *String*                                                                                                            | :heavy_check_mark:                                                                                                  | The account id.                                                                                                     | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                                          |
| `checkWithdrawalScheduleId`                                                                                         | *String*                                                                                                            | :heavy_check_mark:                                                                                                  | The checkWithdrawalSchedule id.                                                                                     | 40eb6b6f-76ff-4dc9-b8a0-b65a7658f8b1                                                                                |
| `cancelCheckWithdrawalScheduleRequestCreate`                                                                        | [CancelCheckWithdrawalScheduleRequestCreate](../../models/components/CancelCheckWithdrawalScheduleRequestCreate.md) | :heavy_check_mark:                                                                                                  | N/A                                                                                                                 |                                                                                                                     |

### Response

**[CheckWithdrawalSchedulesCancelCheckWithdrawalScheduleResponse](../../models/operations/CheckWithdrawalSchedulesCancelCheckWithdrawalScheduleResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## createWireWithdrawalSchedule

Creates a Wire withdrawal transfer schedule

### Example Usage

<!-- UsageSnippet language="java" operationID="WireWithdrawalSchedules_CreateWireWithdrawalSchedule" method="post" path="/transfers/v1/accounts/{account_id}/wireWithdrawalSchedules" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.WireWithdrawalSchedulesCreateWireWithdrawalScheduleResponse;
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

        WireWithdrawalSchedulesCreateWireWithdrawalScheduleResponse res = sdk.scheduleTransfers().createWireWithdrawalSchedule()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .wireWithdrawalScheduleCreate(WireWithdrawalScheduleCreate.builder()
                    .beneficiary(WireWithdrawalBeneficiaryCreate.builder()
                        .account("73849218650987")
                        .build())
                    .recipientBank(WireWithdrawalRecipientBankCreate.builder()
                        .bankId(RecipientBankBankIdCreate.builder()
                            .id("ABNANL2AXXX")
                            .type(RecipientBankBankIdCreateType.BIC)
                            .build())
                        .build())
                    .scheduleDetails(WithdrawalScheduleDetailsCreate.builder()
                        .clientScheduleId("ABC-123")
                        .scheduleProperties(SchedulePropertiesCreate.builder()
                            .startDate(DateCreate.builder()
                                .build())
                            .timeUnit(TimeUnit.MONTH)
                            .unitMultiplier(1)
                            .build())
                        .build())
                    .build())
                .call();

        if (res.wireWithdrawalSchedule().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                               | Type                                                                                    | Required                                                                                | Description                                                                             | Example                                                                                 |
| --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- |
| `accountId`                                                                             | *String*                                                                                | :heavy_check_mark:                                                                      | The account id.                                                                         | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                              |
| `wireWithdrawalScheduleCreate`                                                          | [WireWithdrawalScheduleCreate](../../models/components/WireWithdrawalScheduleCreate.md) | :heavy_check_mark:                                                                      | N/A                                                                                     |                                                                                         |

### Response

**[WireWithdrawalSchedulesCreateWireWithdrawalScheduleResponse](../../models/operations/WireWithdrawalSchedulesCreateWireWithdrawalScheduleResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 409          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## listWireWithdrawalSchedules

Return a list of Wire withdrawal schedules for the specified account and filter params

### Example Usage

<!-- UsageSnippet language="java" operationID="WireWithdrawalSchedules_ListWireWithdrawalSchedules" method="get" path="/transfers/v1/accounts/{account_id}/wireWithdrawalSchedules" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.WireWithdrawalSchedulesListWireWithdrawalSchedulesResponse;
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

        WireWithdrawalSchedulesListWireWithdrawalSchedulesResponse res = sdk.scheduleTransfers().listWireWithdrawalSchedules()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .filter("state == 'ACTIVE' && start_date > '2024-04-05' && end_date < '2024-08-10'")
                .pageSize(100)
                .pageToken("4ZHd3wAaMD1IQ0ZKS2BKV0FSRVdLW4VLWkY1R1B3MU4")
                .call();

        if (res.listWireWithdrawalSchedulesResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                       | Type                                                                                                                                                                                                                            | Required                                                                                                                                                                                                                        | Description                                                                                                                                                                                                                     | Example                                                                                                                                                                                                                         |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                                                                                                                                     | *String*                                                                                                                                                                                                                        | :heavy_check_mark:                                                                                                                                                                                                              | The account id.                                                                                                                                                                                                                 | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                                                                                                                                                      |
| `filter`                                                                                                                                                                                                                        | *Optional\<String>*                                                                                                                                                                                                             | :heavy_minus_sign:                                                                                                                                                                                                              | A CEL string to filter results; See the [CEL Search](https://developer.apexclearing.com/apex-fintech-solutions/docs/cel-search) page in Guides for more information; Filter options include:<br/> `state`<br/> `start_date`<br/> `end_date` | state == 'ACTIVE' && start_date > '2024-04-05' && end_date < '2024-08-10'                                                                                                                                                       |
| `pageSize`                                                                                                                                                                                                                      | *Optional\<Integer>*                                                                                                                                                                                                            | :heavy_minus_sign:                                                                                                                                                                                                              | The maximum number of schedules to return. The service may return fewer than this value. If unspecified, at most 25 schedules will be returned. The maximum value is 1000; values above 1000 will be coerced to 1000.           | 100                                                                                                                                                                                                                             |
| `pageToken`                                                                                                                                                                                                                     | *Optional\<String>*                                                                                                                                                                                                             | :heavy_minus_sign:                                                                                                                                                                                                              | The page token to request                                                                                                                                                                                                       | 4ZHd3wAaMD1IQ0ZKS2BKV0FSRVdLW4VLWkY1R1B3MU4                                                                                                                                                                                     |

### Response

**[WireWithdrawalSchedulesListWireWithdrawalSchedulesResponse](../../models/operations/WireWithdrawalSchedulesListWireWithdrawalSchedulesResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getWireWithdrawalSchedule

Gets a Wire withdrawal transfer schedule

### Example Usage

<!-- UsageSnippet language="java" operationID="WireWithdrawalSchedules_GetWireWithdrawalSchedule" method="get" path="/transfers/v1/accounts/{account_id}/wireWithdrawalSchedules/{wireWithdrawalSchedule_id}" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.WireWithdrawalSchedulesGetWireWithdrawalScheduleResponse;
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

        WireWithdrawalSchedulesGetWireWithdrawalScheduleResponse res = sdk.scheduleTransfers().getWireWithdrawalSchedule()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .wireWithdrawalScheduleId("40eb6b6f-76ff-4dc9-b8a0-b65a7658f8b1")
                .call();

        if (res.wireWithdrawalSchedule().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                            | Type                                 | Required                             | Description                          | Example                              |
| ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ |
| `accountId`                          | *String*                             | :heavy_check_mark:                   | The account id.                      | 01H8FB90ZRRFWXB4XC2JPJ1D4Y           |
| `wireWithdrawalScheduleId`           | *String*                             | :heavy_check_mark:                   | The wireWithdrawalSchedule id.       | 40eb6b6f-76ff-4dc9-b8a0-b65a7658f8b1 |

### Response

**[WireWithdrawalSchedulesGetWireWithdrawalScheduleResponse](../../models/operations/WireWithdrawalSchedulesGetWireWithdrawalScheduleResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## updateWireWithdrawalSchedule

Updates the amount of a Wire withdrawal transfer schedule

### Example Usage

<!-- UsageSnippet language="java" operationID="WireWithdrawalSchedules_UpdateWireWithdrawalSchedule" method="patch" path="/transfers/v1/accounts/{account_id}/wireWithdrawalSchedules/{wireWithdrawalSchedule_id}" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.WireWithdrawalSchedulesUpdateWireWithdrawalScheduleResponse;
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

        WireWithdrawalSchedulesUpdateWireWithdrawalScheduleResponse res = sdk.scheduleTransfers().updateWireWithdrawalSchedule()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .wireWithdrawalScheduleId("40eb6b6f-76ff-4dc9-b8a0-b65a7658f8b1")
                .updateMask("[object Object]")
                .wireWithdrawalScheduleUpdate(WireWithdrawalScheduleUpdate.builder()
                    .build())
                .call();

        if (res.wireWithdrawalSchedule().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                 | Type                                                                                                                      | Required                                                                                                                  | Description                                                                                                               | Example                                                                                                                   |
| ------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                               | *String*                                                                                                                  | :heavy_check_mark:                                                                                                        | The account id.                                                                                                           | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                                                |
| `wireWithdrawalScheduleId`                                                                                                | *String*                                                                                                                  | :heavy_check_mark:                                                                                                        | The wireWithdrawalSchedule id.                                                                                            | 40eb6b6f-76ff-4dc9-b8a0-b65a7658f8b1                                                                                      |
| `updateMask`                                                                                                              | *Optional\<String>*                                                                                                       | :heavy_minus_sign:                                                                                                        | A field mask representing the update. Note: only the 'schedule_details.amount' field of a schedule is currently updatable | {<br/>"update_mask": "schedule_details.amount"<br/>}                                                                      |
| `wireWithdrawalScheduleUpdate`                                                                                            | [WireWithdrawalScheduleUpdate](../../models/components/WireWithdrawalScheduleUpdate.md)                                   | :heavy_check_mark:                                                                                                        | N/A                                                                                                                       |                                                                                                                           |

### Response

**[WireWithdrawalSchedulesUpdateWireWithdrawalScheduleResponse](../../models/operations/WireWithdrawalSchedulesUpdateWireWithdrawalScheduleResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## cancelWireWithdrawalSchedule

Cancels a Wire withdrawal transfer schedule

### Example Usage

<!-- UsageSnippet language="java" operationID="WireWithdrawalSchedules_CancelWireWithdrawalSchedule" method="post" path="/transfers/v1/accounts/{account_id}/wireWithdrawalSchedules/{wireWithdrawalSchedule_id}:cancel" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.WireWithdrawalSchedulesCancelWireWithdrawalScheduleResponse;
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

        WireWithdrawalSchedulesCancelWireWithdrawalScheduleResponse res = sdk.scheduleTransfers().cancelWireWithdrawalSchedule()
                .accountId("01H8FB90ZRRFWXB4XC2JPJ1D4Y")
                .wireWithdrawalScheduleId("40eb6b6f-76ff-4dc9-b8a0-b65a7658f8b1")
                .cancelWireWithdrawalScheduleRequestCreate(CancelWireWithdrawalScheduleRequestCreate.builder()
                    .name("accounts/01H8FB90ZRRFWXB4XC2JPJ1D4Y/wireWithdrawalSchedules/40eb6b6f-76ff-4dc9-b8a0-b65a7658f8b1")
                    .build())
                .call();

        if (res.wireWithdrawalSchedule().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                         | Type                                                                                                              | Required                                                                                                          | Description                                                                                                       | Example                                                                                                           |
| ----------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                       | *String*                                                                                                          | :heavy_check_mark:                                                                                                | The account id.                                                                                                   | 01H8FB90ZRRFWXB4XC2JPJ1D4Y                                                                                        |
| `wireWithdrawalScheduleId`                                                                                        | *String*                                                                                                          | :heavy_check_mark:                                                                                                | The wireWithdrawalSchedule id.                                                                                    | 40eb6b6f-76ff-4dc9-b8a0-b65a7658f8b1                                                                              |
| `cancelWireWithdrawalScheduleRequestCreate`                                                                       | [CancelWireWithdrawalScheduleRequestCreate](../../models/components/CancelWireWithdrawalScheduleRequestCreate.md) | :heavy_check_mark:                                                                                                | N/A                                                                                                               |                                                                                                                   |

### Response

**[WireWithdrawalSchedulesCancelWireWithdrawalScheduleResponse](../../models/operations/WireWithdrawalSchedulesCancelWireWithdrawalScheduleResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |