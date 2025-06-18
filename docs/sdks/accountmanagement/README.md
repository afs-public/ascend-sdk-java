# AccountManagement
(*accountManagement()*)

## Overview

### Available Operations

* [listAccounts](#listaccounts) - List Accounts
* [updateAccount](#updateaccount) - Update Account
* [addParty](#addparty) - Add Party
* [updateParty](#updateparty) - Update Party
* [replaceParty](#replaceparty) - Replace Party
* [removeParty](#removeparty) - Remove Party
* [closeAccount](#closeaccount) - Close Account
* [createTrustedContact](#createtrustedcontact) - Create Trusted Contact
* [updateTrustedContact](#updatetrustedcontact) - Update Trusted Contact
* [deleteTrustedContact](#deletetrustedcontact) - Delete Trusted Contact
* [createInterestedParty](#createinterestedparty) - Create Interested Party
* [updateInterestedParty](#updateinterestedparty) - Update Interested Party
* [deleteInterestedParty](#deleteinterestedparty) - Delete Interested Party
* [listAvailableRestrictions](#listavailablerestrictions) - List Available Restrictions
* [createRestriction](#createrestriction) - Create Restriction
* [endRestriction](#endrestriction) - End Restriction

## listAccounts

Gets a list of Accounts based on search criteria.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsListAccountsRequest;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsListAccountsResponse;
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

        AccountsListAccountsRequest req = AccountsListAccountsRequest.builder()
                .build();

        AccountsListAccountsResponse res = sdk.accountManagement().listAccounts()
                .request(req)
                .call();

        if (res.listAccountsResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                             | Type                                                                                  | Required                                                                              | Description                                                                           |
| ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- |
| `request`                                                                             | [AccountsListAccountsRequest](../../models/operations/AccountsListAccountsRequest.md) | :heavy_check_mark:                                                                    | The request object to use for the request.                                            |

### Response

**[AccountsListAccountsResponse](../../models/operations/AccountsListAccountsResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## updateAccount

UPDATE Updates an Account.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.AccountRequestUpdate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsUpdateAccountResponse;
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

        AccountsUpdateAccountResponse res = sdk.accountManagement().updateAccount()
                .accountId("01HC3MAQ4DR9QN1V8MJ4CN1HMK")
                .updateMask("<value>")
                .accountRequestUpdate(AccountRequestUpdate.builder()
                    .build())
                .call();

        if (res.account().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     | Type                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          | Required                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   | Example                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   | *String*                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      | :heavy_check_mark:                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            | The account id.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               | 01HC3MAQ4DR9QN1V8MJ4CN1HMK                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| `updateMask`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  | *Optional\<String>*                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           | :heavy_minus_sign:                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            | The list of fields to update. Updatable Fields  `advised`  `cat_account_holder_type`  `primary_registered_rep_id`  `investment_profile.account_goals.investment_objective`  `investment_profile.account_goals.risk_tolerance`  `investment_profile.account_goals.liquidity_needs`  `investment_profile.account_goals.time_horizon`  `investment_profile.customer_profile.annual_income_range_usd`  `investment_profile.customer_profile.liquid_net_worth_range_usd`  `investment_profile.customer_profile.total_net_worth_range_usd`  `investment_profile.customer_profile.federal_tax_bracket`  `wrap_fee_billed`  `managed` |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| `accountRequestUpdate`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        | [AccountRequestUpdate](../../models/components/AccountRequestUpdate.md)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       | :heavy_check_mark:                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            | N/A                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |

### Response

**[AccountsUpdateAccountResponse](../../models/operations/AccountsUpdateAccountResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## addParty

Adds a party to an account

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.AddPartyRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.PartyRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.PhoneNumberCreate;
import com.apexfintechsolutions.ascendsdk.models.components.PostalAddressCreate;
import com.apexfintechsolutions.ascendsdk.models.components.RelationType;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsAddPartyResponse;
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

        AccountsAddPartyResponse res = sdk.accountManagement().addParty()
                .accountId("01HC3MAQ4DR9QN1V8MJ4CN1HMK")
                .addPartyRequestCreate(AddPartyRequestCreate.builder()
                    .parent("accounts/01HC3MAQ4DR9QN1V8MJ4CN1HMK")
                    .party(PartyRequestCreate.builder()
                        .emailAddress("example@domain.com")
                        .mailingAddress(PostalAddressCreate.builder()
                            .build())
                        .phoneNumber(PhoneNumberCreate.builder()
                            .build())
                        .relationType(RelationType.PRIMARY_OWNER)
                        .build())
                    .build())
                .call();

        if (res.party().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                 | Type                                                                      | Required                                                                  | Description                                                               | Example                                                                   |
| ------------------------------------------------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------- |
| `accountId`                                                               | *String*                                                                  | :heavy_check_mark:                                                        | The account id.                                                           | 01HC3MAQ4DR9QN1V8MJ4CN1HMK                                                |
| `addPartyRequestCreate`                                                   | [AddPartyRequestCreate](../../models/components/AddPartyRequestCreate.md) | :heavy_check_mark:                                                        | N/A                                                                       |                                                                           |

### Response

**[AccountsAddPartyResponse](../../models/operations/AccountsAddPartyResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## updateParty

Updates a Party.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.PartyRequestUpdate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsUpdatePartyResponse;
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

        AccountsUpdatePartyResponse res = sdk.accountManagement().updateParty()
                .accountId("01HC3MAQ4DR9QN1V8MJ4CN1HMK")
                .partyId("a58ddb02-3954-4249-a7d5-1d408def12cf")
                .updateMask("<value>")
                .partyRequestUpdate(PartyRequestUpdate.builder()
                    .build())
                .call();

        if (res.party().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                                                                                                                                                                                                                       | Type                                                                                                                                                                                                                                                                                                                                                                                                                            | Required                                                                                                                                                                                                                                                                                                                                                                                                                        | Description                                                                                                                                                                                                                                                                                                                                                                                                                     | Example                                                                                                                                                                                                                                                                                                                                                                                                                         |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                                                                                                                                                                                                                                                                                                                                     | *String*                                                                                                                                                                                                                                                                                                                                                                                                                        | :heavy_check_mark:                                                                                                                                                                                                                                                                                                                                                                                                              | The account id.                                                                                                                                                                                                                                                                                                                                                                                                                 | 01HC3MAQ4DR9QN1V8MJ4CN1HMK                                                                                                                                                                                                                                                                                                                                                                                                      |
| `partyId`                                                                                                                                                                                                                                                                                                                                                                                                                       | *String*                                                                                                                                                                                                                                                                                                                                                                                                                        | :heavy_check_mark:                                                                                                                                                                                                                                                                                                                                                                                                              | The party id.                                                                                                                                                                                                                                                                                                                                                                                                                   | a58ddb02-3954-4249-a7d5-1d408def12cf                                                                                                                                                                                                                                                                                                                                                                                            |
| `updateMask`                                                                                                                                                                                                                                                                                                                                                                                                                    | *Optional\<String>*                                                                                                                                                                                                                                                                                                                                                                                                             | :heavy_minus_sign:                                                                                                                                                                                                                                                                                                                                                                                                              | The list of fields to update. Updatable Fields  `phone_number`  `email_address`  `statement_delivery_preference`  `trade_confirmation_delivery_preference`  `tax_document_delivery_preference`  `proxy_delivery_preference`  `prospectus_delivery_preference`  `mailing_address.region_code`  `mailing_address.postal_code`  `mailing_address.administrative_area`  `mailing_address.locality`  `mailing_address.address_lines` |                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| `partyRequestUpdate`                                                                                                                                                                                                                                                                                                                                                                                                            | [PartyRequestUpdate](../../models/components/PartyRequestUpdate.md)                                                                                                                                                                                                                                                                                                                                                             | :heavy_check_mark:                                                                                                                                                                                                                                                                                                                                                                                                              | N/A                                                                                                                                                                                                                                                                                                                                                                                                                             |                                                                                                                                                                                                                                                                                                                                                                                                                                 |

### Response

**[AccountsUpdatePartyResponse](../../models/operations/AccountsUpdatePartyResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## replaceParty

Replaces a party on an account

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.PartyRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.PhoneNumberCreate;
import com.apexfintechsolutions.ascendsdk.models.components.PostalAddressCreate;
import com.apexfintechsolutions.ascendsdk.models.components.RelationType;
import com.apexfintechsolutions.ascendsdk.models.components.ReplacePartyRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsReplacePartyResponse;
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

        AccountsReplacePartyResponse res = sdk.accountManagement().replaceParty()
                .accountId("01HC3MAQ4DR9QN1V8MJ4CN1HMK")
                .partyId("8096110d-fb55-4f9d-b883-b84f0b70d3ea")
                .replacePartyRequestCreate(ReplacePartyRequestCreate.builder()
                    .name("accounts/01HC3MAQ4DR9QN1V8MJ4CN1HMK/parties/8096110d-fb55-4f9d-b883-b84f0b70d3ea")
                    .party(PartyRequestCreate.builder()
                        .emailAddress("example@domain.com")
                        .mailingAddress(PostalAddressCreate.builder()
                            .build())
                        .phoneNumber(PhoneNumberCreate.builder()
                            .build())
                        .relationType(RelationType.PRIMARY_OWNER)
                        .build())
                    .build())
                .call();

        if (res.party().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                         | Type                                                                              | Required                                                                          | Description                                                                       | Example                                                                           |
| --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- |
| `accountId`                                                                       | *String*                                                                          | :heavy_check_mark:                                                                | The account id.                                                                   | 01HC3MAQ4DR9QN1V8MJ4CN1HMK                                                        |
| `partyId`                                                                         | *String*                                                                          | :heavy_check_mark:                                                                | The party id.                                                                     | 8096110d-fb55-4f9d-b883-b84f0b70d3ea                                              |
| `replacePartyRequestCreate`                                                       | [ReplacePartyRequestCreate](../../models/components/ReplacePartyRequestCreate.md) | :heavy_check_mark:                                                                | N/A                                                                               |                                                                                   |

### Response

**[AccountsReplacePartyResponse](../../models/operations/AccountsReplacePartyResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## removeParty

Remove a party from an account

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.RemovePartyRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsRemovePartyResponse;
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

        AccountsRemovePartyResponse res = sdk.accountManagement().removeParty()
                .accountId("01HC3MAQ4DR9QN1V8MJ4CN1HMK")
                .partyId("8096110d-fb55-4f9d-b883-b84f0b70d3ea")
                .removePartyRequestCreate(RemovePartyRequestCreate.builder()
                    .name("accounts/01HC3MAQ4DR9QN1V8MJ4CN1HMK/parties/8096110d-fb55-4f9d-b883-b84f0b70d3ea")
                    .build())
                .call();

        // handle response
    }
}
```

### Parameters

| Parameter                                                                       | Type                                                                            | Required                                                                        | Description                                                                     | Example                                                                         |
| ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- |
| `accountId`                                                                     | *String*                                                                        | :heavy_check_mark:                                                              | The account id.                                                                 | 01HC3MAQ4DR9QN1V8MJ4CN1HMK                                                      |
| `partyId`                                                                       | *String*                                                                        | :heavy_check_mark:                                                              | The party id.                                                                   | 8096110d-fb55-4f9d-b883-b84f0b70d3ea                                            |
| `removePartyRequestCreate`                                                      | [RemovePartyRequestCreate](../../models/components/RemovePartyRequestCreate.md) | :heavy_check_mark:                                                              | N/A                                                                             |                                                                                 |

### Response

**[AccountsRemovePartyResponse](../../models/operations/AccountsRemovePartyResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## closeAccount

CUSTOM Places an ACCT_MAINT_CLOSURE_PREP_BY_CORRESPONDENT restriction on the Account ready for closure.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.CloseAccountRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsCloseAccountResponse;
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

        AccountsCloseAccountResponse res = sdk.accountManagement().closeAccount()
                .accountId("01HC3MAQ4DR9QN1V8MJ4CN1HMK")
                .closeAccountRequestCreate(CloseAccountRequestCreate.builder()
                    .build())
                .call();

        if (res.account().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                         | Type                                                                              | Required                                                                          | Description                                                                       | Example                                                                           |
| --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- |
| `accountId`                                                                       | *String*                                                                          | :heavy_check_mark:                                                                | The account id.                                                                   | 01HC3MAQ4DR9QN1V8MJ4CN1HMK                                                        |
| `closeAccountRequestCreate`                                                       | [CloseAccountRequestCreate](../../models/components/CloseAccountRequestCreate.md) | :heavy_check_mark:                                                                | N/A                                                                               |                                                                                   |

### Response

**[AccountsCloseAccountResponse](../../models/operations/AccountsCloseAccountResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404, 409     | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## createTrustedContact

Creates a new Trusted Contact for an account.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.components.TrustedContactCreate;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsCreateTrustedContactResponse;
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

        AccountsCreateTrustedContactResponse res = sdk.accountManagement().createTrustedContact()
                .accountId("01HC3MAQ4DR9QN1V8MJ4CN1HMK")
                .trustedContactCreate(TrustedContactCreate.builder()
                    .familyName("Doe")
                    .givenName("John")
                    .build())
                .call();

        if (res.trustedContact().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                               | Type                                                                    | Required                                                                | Description                                                             | Example                                                                 |
| ----------------------------------------------------------------------- | ----------------------------------------------------------------------- | ----------------------------------------------------------------------- | ----------------------------------------------------------------------- | ----------------------------------------------------------------------- |
| `accountId`                                                             | *String*                                                                | :heavy_check_mark:                                                      | The account id.                                                         | 01HC3MAQ4DR9QN1V8MJ4CN1HMK                                              |
| `trustedContactCreate`                                                  | [TrustedContactCreate](../../models/components/TrustedContactCreate.md) | :heavy_check_mark:                                                      | N/A                                                                     |                                                                         |

### Response

**[AccountsCreateTrustedContactResponse](../../models/operations/AccountsCreateTrustedContactResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## updateTrustedContact

Updates a Trusted Contact.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.components.TrustedContactUpdate;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsUpdateTrustedContactResponse;
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

        AccountsUpdateTrustedContactResponse res = sdk.accountManagement().updateTrustedContact()
                .accountId("01HC3MAQ4DR9QN1V8MJ4CN1HMK")
                .trustedContactId("8096110d-fb55-4f9d-b883-b84f0b70d3ea")
                .updateMask("<value>")
                .trustedContactUpdate(TrustedContactUpdate.builder()
                    .build())
                .call();

        if (res.trustedContact().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                                                                                      | Type                                                                                                                                                                                                                                                                                           | Required                                                                                                                                                                                                                                                                                       | Description                                                                                                                                                                                                                                                                                    | Example                                                                                                                                                                                                                                                                                        |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                                                                                                                                                                                                    | *String*                                                                                                                                                                                                                                                                                       | :heavy_check_mark:                                                                                                                                                                                                                                                                             | The account id.                                                                                                                                                                                                                                                                                | 01HC3MAQ4DR9QN1V8MJ4CN1HMK                                                                                                                                                                                                                                                                     |
| `trustedContactId`                                                                                                                                                                                                                                                                             | *String*                                                                                                                                                                                                                                                                                       | :heavy_check_mark:                                                                                                                                                                                                                                                                             | The trustedContact id.                                                                                                                                                                                                                                                                         | 8096110d-fb55-4f9d-b883-b84f0b70d3ea                                                                                                                                                                                                                                                           |
| `updateMask`                                                                                                                                                                                                                                                                                   | *Optional\<String>*                                                                                                                                                                                                                                                                            | :heavy_minus_sign:                                                                                                                                                                                                                                                                             | The list of fields to update. Updatable Fields  `given_name`  `middle_names`  `family_name`  `phone_number`  `email_address`  `mailing_address.region_code`  `mailing_address.postal_code`  `mailing_address.administrative_area`  `mailing_address.locality`  `mailing_address.address_lines` |                                                                                                                                                                                                                                                                                                |
| `trustedContactUpdate`                                                                                                                                                                                                                                                                         | [TrustedContactUpdate](../../models/components/TrustedContactUpdate.md)                                                                                                                                                                                                                        | :heavy_check_mark:                                                                                                                                                                                                                                                                             | N/A                                                                                                                                                                                                                                                                                            |                                                                                                                                                                                                                                                                                                |

### Response

**[AccountsUpdateTrustedContactResponse](../../models/operations/AccountsUpdateTrustedContactResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## deleteTrustedContact

DELETE Deletes a Trusted Contact for an Account.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsDeleteTrustedContactResponse;
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

        AccountsDeleteTrustedContactResponse res = sdk.accountManagement().deleteTrustedContact()
                .accountId("01HC3MAQ4DR9QN1V8MJ4CN1HMK")
                .trustedContactId("8096110d-fb55-4f9d-b883-b84f0b70d3ea")
                .call();

        // handle response
    }
}
```

### Parameters

| Parameter                            | Type                                 | Required                             | Description                          | Example                              |
| ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ |
| `accountId`                          | *String*                             | :heavy_check_mark:                   | The account id.                      | 01HC3MAQ4DR9QN1V8MJ4CN1HMK           |
| `trustedContactId`                   | *String*                             | :heavy_check_mark:                   | The trustedContact id.               | 8096110d-fb55-4f9d-b883-b84f0b70d3ea |

### Response

**[AccountsDeleteTrustedContactResponse](../../models/operations/AccountsDeleteTrustedContactResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## createInterestedParty

Creates an Interested Party record for an Account.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.InterestedPartyCreate;
import com.apexfintechsolutions.ascendsdk.models.components.PostalAddressCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsCreateInterestedPartyResponse;
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

        AccountsCreateInterestedPartyResponse res = sdk.accountManagement().createInterestedParty()
                .accountId("01HC3MAQ4DR9QN1V8MJ4CN1HMK")
                .interestedPartyCreate(InterestedPartyCreate.builder()
                    .mailingAddress(PostalAddressCreate.builder()
                        .build())
                    .recipient("John Dough")
                    .build())
                .call();

        if (res.interestedParty().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                 | Type                                                                      | Required                                                                  | Description                                                               | Example                                                                   |
| ------------------------------------------------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------- |
| `accountId`                                                               | *String*                                                                  | :heavy_check_mark:                                                        | The account id.                                                           | 01HC3MAQ4DR9QN1V8MJ4CN1HMK                                                |
| `interestedPartyCreate`                                                   | [InterestedPartyCreate](../../models/components/InterestedPartyCreate.md) | :heavy_check_mark:                                                        | N/A                                                                       |                                                                           |

### Response

**[AccountsCreateInterestedPartyResponse](../../models/operations/AccountsCreateInterestedPartyResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## updateInterestedParty

Updates an Interested Party.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.InterestedPartyUpdate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsUpdateInterestedPartyResponse;
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

        AccountsUpdateInterestedPartyResponse res = sdk.accountManagement().updateInterestedParty()
                .accountId("01HC3MAQ4DR9QN1V8MJ4CN1HMK")
                .interestedPartyId("ecf44f2f-7030-48ed-b937-c40891ee10c8")
                .updateMask("<value>")
                .interestedPartyUpdate(InterestedPartyUpdate.builder()
                    .build())
                .call();

        if (res.interestedParty().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                                                                                                | Type                                                                                                                                                                                                                                                                                                     | Required                                                                                                                                                                                                                                                                                                 | Description                                                                                                                                                                                                                                                                                              | Example                                                                                                                                                                                                                                                                                                  |
| -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `accountId`                                                                                                                                                                                                                                                                                              | *String*                                                                                                                                                                                                                                                                                                 | :heavy_check_mark:                                                                                                                                                                                                                                                                                       | The account id.                                                                                                                                                                                                                                                                                          | 01HC3MAQ4DR9QN1V8MJ4CN1HMK                                                                                                                                                                                                                                                                               |
| `interestedPartyId`                                                                                                                                                                                                                                                                                      | *String*                                                                                                                                                                                                                                                                                                 | :heavy_check_mark:                                                                                                                                                                                                                                                                                       | The interestedParty id.                                                                                                                                                                                                                                                                                  | ecf44f2f-7030-48ed-b937-c40891ee10c8                                                                                                                                                                                                                                                                     |
| `updateMask`                                                                                                                                                                                                                                                                                             | *Optional\<String>*                                                                                                                                                                                                                                                                                      | :heavy_minus_sign:                                                                                                                                                                                                                                                                                       | The list of fields to update. Updatable Fields  `recipient`  `statement_delivery_preference`  `trade_confirmation_delivery_preference`  `mailing_address.region_code`  `mailing_address.postal_code`  `mailing_address.administrative_area`  `mailing_address.locality`  `mailing_address.address_lines` |                                                                                                                                                                                                                                                                                                          |
| `interestedPartyUpdate`                                                                                                                                                                                                                                                                                  | [InterestedPartyUpdate](../../models/components/InterestedPartyUpdate.md)                                                                                                                                                                                                                                | :heavy_check_mark:                                                                                                                                                                                                                                                                                       | N/A                                                                                                                                                                                                                                                                                                      |                                                                                                                                                                                                                                                                                                          |

### Response

**[AccountsUpdateInterestedPartyResponse](../../models/operations/AccountsUpdateInterestedPartyResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## deleteInterestedParty

Deletes an Interested Party associated from an Account.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsDeleteInterestedPartyResponse;
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

        AccountsDeleteInterestedPartyResponse res = sdk.accountManagement().deleteInterestedParty()
                .accountId("01HC3MAQ4DR9QN1V8MJ4CN1HMK")
                .interestedPartyId("8096110d-fb55-4f9d-b883-b84f0b70d3ea")
                .call();

        // handle response
    }
}
```

### Parameters

| Parameter                            | Type                                 | Required                             | Description                          | Example                              |
| ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ |
| `accountId`                          | *String*                             | :heavy_check_mark:                   | The account id.                      | 01HC3MAQ4DR9QN1V8MJ4CN1HMK           |
| `interestedPartyId`                  | *String*                             | :heavy_check_mark:                   | The interestedParty id.              | 8096110d-fb55-4f9d-b883-b84f0b70d3ea |

### Response

**[AccountsDeleteInterestedPartyResponse](../../models/operations/AccountsDeleteInterestedPartyResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## listAvailableRestrictions

Gets a list of possible Restrictions that can be placed on an Account based on Enrollments.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsListAvailableRestrictionsResponse;
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

        AccountsListAvailableRestrictionsResponse res = sdk.accountManagement().listAvailableRestrictions()
                .accountId("01HC3MAQ4DR9QN1V8MJ4CN1HMK")
                .call();

        if (res.listAvailableRestrictionsResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                  | Type                       | Required                   | Description                | Example                    |
| -------------------------- | -------------------------- | -------------------------- | -------------------------- | -------------------------- |
| `accountId`                | *String*                   | :heavy_check_mark:         | The account id.            | 01HC3MAQ4DR9QN1V8MJ4CN1HMK |

### Response

**[AccountsListAvailableRestrictionsResponse](../../models/operations/AccountsListAvailableRestrictionsResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## createRestriction

Applies a Restriction to an account that suspends one or more Entitlements.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.RestrictionCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsCreateRestrictionResponse;
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

        AccountsCreateRestrictionResponse res = sdk.accountManagement().createRestriction()
                .accountId("01HC3MAQ4DR9QN1V8MJ4CN1HMK")
                .restrictionCreate(RestrictionCreate.builder()
                    .createReason("Some reason for creating")
                    .restrictionCode("MARGIN_CALL_VIOLATION_REG_T")
                    .build())
                .call();

        if (res.restriction().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                         | Type                                                              | Required                                                          | Description                                                       | Example                                                           |
| ----------------------------------------------------------------- | ----------------------------------------------------------------- | ----------------------------------------------------------------- | ----------------------------------------------------------------- | ----------------------------------------------------------------- |
| `accountId`                                                       | *String*                                                          | :heavy_check_mark:                                                | The account id.                                                   | 01HC3MAQ4DR9QN1V8MJ4CN1HMK                                        |
| `restrictionCreate`                                               | [RestrictionCreate](../../models/components/RestrictionCreate.md) | :heavy_check_mark:                                                | N/A                                                               |                                                                   |

### Response

**[AccountsCreateRestrictionResponse](../../models/operations/AccountsCreateRestrictionResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 409          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## endRestriction

Ends a Restriction on an Account.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.EndRestrictionRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsEndRestrictionResponse;
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

        AccountsEndRestrictionResponse res = sdk.accountManagement().endRestriction()
                .accountId("01HC3MAQ4DR9QN1V8MJ4CN1HMK")
                .restrictionId("FRAUD_SUSPENDED_BY_CORRESPONDENT")
                .endRestrictionRequestCreate(EndRestrictionRequestCreate.builder()
                    .reason("Reason for ending the restriction")
                    .build())
                .call();

        // handle response
    }
}
```

### Parameters

| Parameter                                                                             | Type                                                                                  | Required                                                                              | Description                                                                           | Example                                                                               |
| ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- |
| `accountId`                                                                           | *String*                                                                              | :heavy_check_mark:                                                                    | The account id.                                                                       | 01HC3MAQ4DR9QN1V8MJ4CN1HMK                                                            |
| `restrictionId`                                                                       | *String*                                                                              | :heavy_check_mark:                                                                    | The restriction id.                                                                   | FRAUD_SUSPENDED_BY_CORRESPONDENT                                                      |
| `endRestrictionRequestCreate`                                                         | [EndRestrictionRequestCreate](../../models/components/EndRestrictionRequestCreate.md) | :heavy_check_mark:                                                                    | N/A                                                                                   |                                                                                       |

### Response

**[AccountsEndRestrictionResponse](../../models/operations/AccountsEndRestrictionResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |