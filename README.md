# Introducing the Apex Java SDK

In today's fast-paced digital ecosystem, developers need tools that not only streamline the development process but also unlock new possibilities for innovation and efficiency.

Enter the Apex Java SDK, a cutting-edge software development kit designed to empower fintech app developers like never before.  
With our SDK, you can easily integrate new account creation, optimize trading, and elevate your applications with realtime buying power, all through a seamless, API interface.

Whether you're building complex, data-driven platforms or simple, user-centric applications, Apex Java SDK offers the flexibility, power, and ease of use to bring your visions to life faster and more effectively.  
Join us in redefining the boundaries of what your applications can achieve.

Start your journey with Apex today.

## SDK Installation

The samples below show how a published SDK artifact is used:

Gradle:
```groovy
implementation 'com.apexfintechsolutions:ascendsdk:1.6.0'
```

Maven:
```xml
<dependency>
    <groupId>com.apexfintechsolutions</groupId>
    <artifactId>ascendsdk</artifactId>
    <version>1.6.0</version>
</dependency>
```

<!-- No SDK Installation [installation] -->

## Supported Java Versions

- Java SE 11
- Java SE 17
- Java SE 21

## Initializing the SDK

The following sample shows the recommended way to initialise the SDK, so that JWT generation (and refresh every 24hrs) is handled automatically:
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsGetAccountResponse;
import com.apexfintechsolutions.ascendsdk.models.operations.QueryParamView;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;

public class Application {

    public static void main(String[] args) throws Exception {
        SDK sdk = SDK.builder()
            .serverURL(System.getenv("APEX_APIS_URL"))
            .security(Security.builder()
                .apiKey("X_API_KEY")
                .serviceAccountCreds(ServiceAccountCreds.builder()
                    .privateKey("SERVICE_ACCOUNT_CREDS_PRIVATE_KEY")
                    .name("SERVICE_ACCOUNT_CREDS_NAME")
                    .organization("SERVICE_ACCOUNT_CREDS_ORGANIZATION")
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

<!-- No SDK Example Usage [usage] -->

<!-- Start Pagination [pagination] -->
## Pagination

Some of the endpoints in this SDK support pagination. To use pagination, you can make your SDK calls using the `callAsIterable` or `callAsStream` methods.
For certain operations, you can also use the `callAsStreamUnwrapped` method that streams individual page items directly.

Here's an example depicting the different ways to use pagination:

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AuthenticationListSigningKeysResponse;
import com.apexfintechsolutions.ascendsdk.models.operations.AuthenticationListSigningKeysSecurity;
import java.lang.Exception;
import java.lang.Iterable;

public class Application {

    public static void main(String[] args) throws Status, Exception {

        SDK sdk = SDK.builder()
            .build();


        var b = sdk.authentication().listSigningKeys()
                .security(AuthenticationListSigningKeysSecurity.builder()
                    .apiKeyAuth(System.getenv().getOrDefault("API_KEY_AUTH", ""))
                    .build())
                .pageSize(50)
                .pageToken("ZXhhbXBsZQo");

        // Iterate through all pages using a traditional for-each loop
        // Each iteration returns a complete page response
        Iterable<AuthenticationListSigningKeysResponse> iterable = b.callAsIterable();
        for (AuthenticationListSigningKeysResponse page : iterable) {
            // handle page
        }

        // Stream through all pages and process individual items
        // callAsStreamUnwrapped() flattens pages into individual items

        // Stream through pages without unwrapping (each item is a complete page)
        b.callAsStream()
            .forEach((AuthenticationListSigningKeysResponse page) -> {
                // handle page
            });

    }
}
```
<!-- End Pagination [pagination] -->

<!-- Start Retries [retries] -->
## Retries

Some of the endpoints in this SDK support retries. If you use the SDK without any configuration, it will fall back to the default retry strategy provided by the API. However, the default retry strategy can be overridden on a per-operation basis, or across the entire SDK.

To change the default retry strategy for a single API call, you can provide a `RetryConfig` object through the `retryConfig` builder method:
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.GenerateServiceAccountTokenRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AuthenticationGenerateServiceAccountTokenResponse;
import com.apexfintechsolutions.ascendsdk.models.operations.AuthenticationGenerateServiceAccountTokenSecurity;
import com.apexfintechsolutions.ascendsdk.utils.BackoffStrategy;
import com.apexfintechsolutions.ascendsdk.utils.RetryConfig;
import java.lang.Exception;
import java.util.concurrent.TimeUnit;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
            .build();

        GenerateServiceAccountTokenRequestCreate req = GenerateServiceAccountTokenRequestCreate.builder()
                .jws("eyJhbGciOiAiUlMyNTYifQ.eyJuYW1lIjogImpkb3VnaCIsIm9yZ2FuaXphdGlvbiI6ICJjb3JyZXNwb25kZW50cy8xMjM0NTY3OC0xMjM0LTEyMzQtMTIzNC0xMjM0NTY3ODkxMDEiLCJkYXRldGltZSI6ICIyMDI0LTAyLTA1VDIxOjAyOjI3LjkwMTE4MFoifQ.IMy3KmYoG8Ppf+7hXN7tm7J4MrNpQLGL7WCWvhh4nZWAVKkluL3/u3KC6hZ6Mb/5p7Y54CgZ68aWT2BcP5y4VtzIZR1Chm5pxbLfgE4aJuk+FnF6K3Gc3bBjOWCL58pxY2aTb0iU/exDEA1cbMDvbCzmY5kRefDvorLOqgUS/tS2MJ2jv4RlZFPlmHv5PtOruJ8xUW19gEgGhsPXYYeSHFTE1ZlaDvyXrKtpOvlf+FVc2RTuEw529LZnzwH4/eJJR3BpSpHyJTjQqiaMT3wzpXXYKfCRqnDkSSKJDzCzTb0/uWK/Lf0uafxPXk5YLdis+dbo1zNQhVVKjwnMpk1vLw")
                .build();

        AuthenticationGenerateServiceAccountTokenResponse res = sdk.authentication().generateServiceAccountToken()
                .request(req)
                .security(AuthenticationGenerateServiceAccountTokenSecurity.builder()
                    .apiKeyAuth(System.getenv().getOrDefault("API_KEY_AUTH", ""))
                    .build())
                .retryConfig(RetryConfig.builder()
                    .backoff(BackoffStrategy.builder()
                        .initialInterval(1L, TimeUnit.MILLISECONDS)
                        .maxInterval(50L, TimeUnit.MILLISECONDS)
                        .maxElapsedTime(1000L, TimeUnit.MILLISECONDS)
                        .baseFactor(1.1)
                        .jitterFactor(0.15)
                        .retryConnectError(false)
                        .build())
                    .build())
                .call();

        if (res.token().isPresent()) {
            // handle response
        }
    }
}
```

If you'd like to override the default retry strategy for all operations that support retries, you can provide a configuration at SDK initialization:
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.GenerateServiceAccountTokenRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AuthenticationGenerateServiceAccountTokenResponse;
import com.apexfintechsolutions.ascendsdk.models.operations.AuthenticationGenerateServiceAccountTokenSecurity;
import com.apexfintechsolutions.ascendsdk.utils.BackoffStrategy;
import com.apexfintechsolutions.ascendsdk.utils.RetryConfig;
import java.lang.Exception;
import java.util.concurrent.TimeUnit;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .retryConfig(RetryConfig.builder()
                    .backoff(BackoffStrategy.builder()
                        .initialInterval(1L, TimeUnit.MILLISECONDS)
                        .maxInterval(50L, TimeUnit.MILLISECONDS)
                        .maxElapsedTime(1000L, TimeUnit.MILLISECONDS)
                        .baseFactor(1.1)
                        .jitterFactor(0.15)
                        .retryConnectError(false)
                        .build())
                    .build())
            .build();

        GenerateServiceAccountTokenRequestCreate req = GenerateServiceAccountTokenRequestCreate.builder()
                .jws("eyJhbGciOiAiUlMyNTYifQ.eyJuYW1lIjogImpkb3VnaCIsIm9yZ2FuaXphdGlvbiI6ICJjb3JyZXNwb25kZW50cy8xMjM0NTY3OC0xMjM0LTEyMzQtMTIzNC0xMjM0NTY3ODkxMDEiLCJkYXRldGltZSI6ICIyMDI0LTAyLTA1VDIxOjAyOjI3LjkwMTE4MFoifQ.IMy3KmYoG8Ppf+7hXN7tm7J4MrNpQLGL7WCWvhh4nZWAVKkluL3/u3KC6hZ6Mb/5p7Y54CgZ68aWT2BcP5y4VtzIZR1Chm5pxbLfgE4aJuk+FnF6K3Gc3bBjOWCL58pxY2aTb0iU/exDEA1cbMDvbCzmY5kRefDvorLOqgUS/tS2MJ2jv4RlZFPlmHv5PtOruJ8xUW19gEgGhsPXYYeSHFTE1ZlaDvyXrKtpOvlf+FVc2RTuEw529LZnzwH4/eJJR3BpSpHyJTjQqiaMT3wzpXXYKfCRqnDkSSKJDzCzTb0/uWK/Lf0uafxPXk5YLdis+dbo1zNQhVVKjwnMpk1vLw")
                .build();

        AuthenticationGenerateServiceAccountTokenResponse res = sdk.authentication().generateServiceAccountToken()
                .request(req)
                .security(AuthenticationGenerateServiceAccountTokenSecurity.builder()
                    .apiKeyAuth(System.getenv().getOrDefault("API_KEY_AUTH", ""))
                    .build())
                .call();

        if (res.token().isPresent()) {
            // handle response
        }
    }
}
```
<!-- End Retries [retries] -->

<!-- Start Error Handling [errors] -->
## Error Handling

Handling errors in this SDK should largely match your expectations. All operations return a response object or raise an exception.

By default, an API error will throw a `models/errors/SDKError` exception. When custom error responses are specified for an operation, the SDK may also throw their associated exception. You can refer to respective *Errors* tables in SDK docs for more details on possible exception types for each operation. For example, the `getAccount` method throws the following exceptions:

| Error Type             | Status Code   | Content Type     |
| ---------------------- | ------------- | ---------------- |
| models/errors/Status   | 400, 403, 404 | application/json |
| models/errors/Status   | 500, 503      | application/json |
| models/errors/SDKError | 4XX, 5XX      | \*/\*            |

### Example

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
<!-- End Error Handling [errors] -->

<!-- Start Server Selection [server] -->
## Server Selection

### Select Server by Name

You can override the default server globally using the `.server(AvailableServers server)` builder method when initializing the SDK client instance. The selected server will then be used as the default on the operations that use it. This table lists the names associated with the available servers:

| Name   | Server                     | Description                |
| ------ | -------------------------- | -------------------------- |
| `uat`  | `https://uat.apexapis.com` | our uat environment        |
| `prod` | `https://api.apexapis.com` | our production environment |
| `sbx`  | `https://sbx.apexapis.com` | our sandbox environment    |

#### Example

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
                .server(SDK.AvailableServers.SBX)
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

### Override Server URL Per-Client

The default server can also be overridden globally using the `.serverURL(String serverUrl)` builder method when initializing the SDK client instance. For example:
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
                .serverURL("https://uat.apexapis.com")
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
<!-- End Server Selection [server] -->

<!-- No Authentication [security] -->

<!-- No Summary [summary] -->

<!-- No Table of Contents [toc] -->

<!-- Start Available Resources and Operations [operations] -->
## Available Resources and Operations

<details open>
<summary>Available methods</summary>

### [accountCreation()](docs/sdks/accountcreation/README.md)

* [createAccount](docs/sdks/accountcreation/README.md#createaccount) - Create Account
* [getAccount](docs/sdks/accountcreation/README.md#getaccount) - Get Account

### [accountManagement()](docs/sdks/accountmanagement/README.md)

* [listAccounts](docs/sdks/accountmanagement/README.md#listaccounts) - List Accounts
* [updateAccount](docs/sdks/accountmanagement/README.md#updateaccount) - Update Account
* [addParty](docs/sdks/accountmanagement/README.md#addparty) - Add Party
* [updateParty](docs/sdks/accountmanagement/README.md#updateparty) - Update Party
* [replaceParty](docs/sdks/accountmanagement/README.md#replaceparty) - Replace Party
* [removeParty](docs/sdks/accountmanagement/README.md#removeparty) - Remove Party
* [closeAccount](docs/sdks/accountmanagement/README.md#closeaccount) - Close Account
* [createTrustedContact](docs/sdks/accountmanagement/README.md#createtrustedcontact) - Create Trusted Contact
* [updateTrustedContact](docs/sdks/accountmanagement/README.md#updatetrustedcontact) - Update Trusted Contact
* [deleteTrustedContact](docs/sdks/accountmanagement/README.md#deletetrustedcontact) - Delete Trusted Contact
* [createInterestedParty](docs/sdks/accountmanagement/README.md#createinterestedparty) - Create Interested Party
* [updateInterestedParty](docs/sdks/accountmanagement/README.md#updateinterestedparty) - Update Interested Party
* [deleteInterestedParty](docs/sdks/accountmanagement/README.md#deleteinterestedparty) - Delete Interested Party
* [listAvailableRestrictions](docs/sdks/accountmanagement/README.md#listavailablerestrictions) - List Available Restrictions
* [createRestriction](docs/sdks/accountmanagement/README.md#createrestriction) - Create Restriction
* [endRestriction](docs/sdks/accountmanagement/README.md#endrestriction) - End Restriction

### [accountTransfers()](docs/sdks/accounttransfers/README.md)

* [createTransfer](docs/sdks/accounttransfers/README.md#createtransfer) - Create Transfer
* [listTransfers](docs/sdks/accounttransfers/README.md#listtransfers) - List Transfers
* [acceptTransfer](docs/sdks/accounttransfers/README.md#accepttransfer) - Accept Transfer
* [rejectTransfer](docs/sdks/accounttransfers/README.md#rejecttransfer) - Reject Transfer
* [getTransfer](docs/sdks/accounttransfers/README.md#gettransfer) - Get Transfer

### [achTransfers()](docs/sdks/achtransfers/README.md)

* [createAchDeposit](docs/sdks/achtransfers/README.md#createachdeposit) - Create ACH Deposit
* [getAchDeposit](docs/sdks/achtransfers/README.md#getachdeposit) - Get ACH Deposit
* [cancelAchDeposit](docs/sdks/achtransfers/README.md#cancelachdeposit) - Cancel ACH Deposit
* [createAchWithdrawal](docs/sdks/achtransfers/README.md#createachwithdrawal) - Create ACH Withdrawal
* [getAchWithdrawal](docs/sdks/achtransfers/README.md#getachwithdrawal) - Get ACH Withdrawal
* [cancelAchWithdrawal](docs/sdks/achtransfers/README.md#cancelachwithdrawal) - Cancel ACH Withdrawal

### [assets()](docs/sdks/assets/README.md)

* [listAssets](docs/sdks/assets/README.md#listassets) - List Assets
* [getAsset](docs/sdks/assets/README.md#getasset) - Get Asset
* [listAssetsCorrespondent](docs/sdks/assets/README.md#listassetscorrespondent) - List Assets (By Correspondent)
* [getAssetCorrespondent](docs/sdks/assets/README.md#getassetcorrespondent) - Get Asset (By Correspondent)

### [assetTradingConfig()](docs/sdks/assettradingconfig/README.md)

* [getAssetTradingConfig](docs/sdks/assettradingconfig/README.md#getassettradingconfig) - Get Asset Trading Config
* [listAssetTradingConfigs](docs/sdks/assettradingconfig/README.md#listassettradingconfigs) - List Asset Trading Configs

### [authentication()](docs/sdks/authentication/README.md)

* [generateServiceAccountToken](docs/sdks/authentication/README.md#generateserviceaccounttoken) - Generate Service Account Token
* [listSigningKeys](docs/sdks/authentication/README.md#listsigningkeys) - List Signing Keys

### [bankRelationships()](docs/sdks/bankrelationships/README.md)

* [createBankRelationship](docs/sdks/bankrelationships/README.md#createbankrelationship) - Create Bank Relationship
* [listBankRelationships](docs/sdks/bankrelationships/README.md#listbankrelationships) - List Bank Relationships
* [getBankRelationship](docs/sdks/bankrelationships/README.md#getbankrelationship) - Get Bank Relationship
* [updateBankRelationship](docs/sdks/bankrelationships/README.md#updatebankrelationship) - Update Bank Relationship
* [cancelBankRelationship](docs/sdks/bankrelationships/README.md#cancelbankrelationship) - Cancel Bank Relationship
* [verifyMicroDeposits](docs/sdks/bankrelationships/README.md#verifymicrodeposits) - Verify Micro Deposits
* [reissueMicroDeposits](docs/sdks/bankrelationships/README.md#reissuemicrodeposits) - Reissue Micro Deposits
* [reuseBankRelationship](docs/sdks/bankrelationships/README.md#reusebankrelationship) - Reuse Bank Relationship

### [basketOrders()](docs/sdks/basketorders/README.md)

* [createBasket](docs/sdks/basketorders/README.md#createbasket) - Create Basket
* [addOrders](docs/sdks/basketorders/README.md#addorders) - Add Orders
* [getBasket](docs/sdks/basketorders/README.md#getbasket) - Get Basket
* [submitBasket](docs/sdks/basketorders/README.md#submitbasket) - Submit Basket
* [listBasketOrders](docs/sdks/basketorders/README.md#listbasketorders) - List Basket Orders
* [listCompressedOrders](docs/sdks/basketorders/README.md#listcompressedorders) - List Compressed Orders
* [removeOrders](docs/sdks/basketorders/README.md#removeorders) - Remove Basket Orders

### [cashBalances()](docs/sdks/cashbalances/README.md)

* [calculateCashBalance](docs/sdks/cashbalances/README.md#calculatecashbalance) - Get Cash Balance

### [checks()](docs/sdks/checks/README.md)

* [getCheckDeposit](docs/sdks/checks/README.md#getcheckdeposit) - Get Check Deposit

### [dataRetrieval()](docs/sdks/dataretrieval/README.md)

* [listSnapshots](docs/sdks/dataretrieval/README.md#listsnapshots) - List Snapshots

### [enrollmentsAndAgreements()](docs/sdks/enrollmentsandagreements/README.md)

* [enrollAccount](docs/sdks/enrollmentsandagreements/README.md#enrollaccount) - Enroll Account
* [listAvailableEnrollments](docs/sdks/enrollmentsandagreements/README.md#listavailableenrollments) - List Available Enrollments
* [accountsListAvailableEnrollmentsByAccountGroup](docs/sdks/enrollmentsandagreements/README.md#accountslistavailableenrollmentsbyaccountgroup) - List Available Enrollments (by Account Group)
* [deactivateEnrollment](docs/sdks/enrollmentsandagreements/README.md#deactivateenrollment) - Deactivate Enrollment
* [listEnrollments](docs/sdks/enrollmentsandagreements/README.md#listenrollments) - List Account Enrollments
* [affirmAgreements](docs/sdks/enrollmentsandagreements/README.md#affirmagreements) - Affirm Agreements
* [listAgreements](docs/sdks/enrollmentsandagreements/README.md#listagreements) - List Account Agreements
* [listEntitlements](docs/sdks/enrollmentsandagreements/README.md#listentitlements) - List Account Entitlements

### [feesAndCredits()](docs/sdks/feesandcredits/README.md)

* [createFee](docs/sdks/feesandcredits/README.md#createfee) - Create Fee
* [getFee](docs/sdks/feesandcredits/README.md#getfee) - Get Fee
* [cancelFee](docs/sdks/feesandcredits/README.md#cancelfee) - Cancel Fee
* [createCredit](docs/sdks/feesandcredits/README.md#createcredit) - Create Credit
* [getCredit](docs/sdks/feesandcredits/README.md#getcredit) - Get Credit
* [cancelCredit](docs/sdks/feesandcredits/README.md#cancelcredit) - Cancel Credit

### [fixedIncomePricing()](docs/sdks/fixedincomepricing/README.md)

* [previewOrderCost](docs/sdks/fixedincomepricing/README.md#previewordercost) - Preview Order Cost
* [retrieveQuote](docs/sdks/fixedincomepricing/README.md#retrievequote) - Retrieve Quote
* [retrieveFixedIncomeMarks](docs/sdks/fixedincomepricing/README.md#retrievefixedincomemarks) - Retrieve Fixed Income Marks

### [instantCashTransferICT()](docs/sdks/instantcashtransferict/README.md)

* [createIctDeposit](docs/sdks/instantcashtransferict/README.md#createictdeposit) - Create ICT Deposit
* [getIctDeposit](docs/sdks/instantcashtransferict/README.md#getictdeposit) - Get ICT Deposit
* [cancelIctDeposit](docs/sdks/instantcashtransferict/README.md#cancelictdeposit) - Cancel ICT Deposit
* [createIctWithdrawal](docs/sdks/instantcashtransferict/README.md#createictwithdrawal) - Create ICT Withdrawal
* [getIctWithdrawal](docs/sdks/instantcashtransferict/README.md#getictwithdrawal) - Get ICT Withdrawal
* [cancelIctWithdrawal](docs/sdks/instantcashtransferict/README.md#cancelictwithdrawal) - Cancel ICT Withdrawal
* [locateIctReport](docs/sdks/instantcashtransferict/README.md#locateictreport) - Locate ICT Report

### [investigations()](docs/sdks/investigations/README.md)

* [getInvestigation](docs/sdks/investigations/README.md#getinvestigation) - Get Investigations
* [updateInvestigation](docs/sdks/investigations/README.md#updateinvestigation) - Update Investigation 
* [listInvestigations](docs/sdks/investigations/README.md#listinvestigations) - List Investigations
* [linkDocuments](docs/sdks/investigations/README.md#linkdocuments) - Link Documents
* [getWatchlistItem](docs/sdks/investigations/README.md#getwatchlistitem) - Get Watchlist Item
* [getCustomerIdentification](docs/sdks/investigations/README.md#getcustomeridentification) - Get Identity Verification

### [investorDocs()](docs/sdks/investordocs/README.md)

* [batchCreateUploadLinks](docs/sdks/investordocs/README.md#batchcreateuploadlinks) - Batch Create Upload Links
* [listDocuments](docs/sdks/investordocs/README.md#listdocuments) - List Documents

### [journals()](docs/sdks/journals/README.md)

* [retrieveCashJournalConstraints](docs/sdks/journals/README.md#retrievecashjournalconstraints) - Retrieve Cash Journal Constraints
* [createCashJournal](docs/sdks/journals/README.md#createcashjournal) - Create Cash Journal
* [getCashJournal](docs/sdks/journals/README.md#getcashjournal) - Get Cash Journal
* [cancelCashJournal](docs/sdks/journals/README.md#cancelcashjournal) - Cancel Cash Journal
* [checkPartyType](docs/sdks/journals/README.md#checkpartytype) - Retrieve Cash Journal Party

### [ledger()](docs/sdks/ledger/README.md)

* [listEntries](docs/sdks/ledger/README.md#listentries) - List Entries
* [listActivities](docs/sdks/ledger/README.md#listactivities) - List Activities
* [listPositions](docs/sdks/ledger/README.md#listpositions) - List Positions
* [getActivity](docs/sdks/ledger/README.md#getactivity) - Get Activity
* [getEntry](docs/sdks/ledger/README.md#getentry) - Get Entry

### [margins()](docs/sdks/margins/README.md)

* [getBuyingPower](docs/sdks/margins/README.md#getbuyingpower) - Get Buying Power

### [orders()](docs/sdks/orders/README.md)

* [createOrder](docs/sdks/orders/README.md#createorder) - Create Order
* [getOrder](docs/sdks/orders/README.md#getorder) - Get Order
* [cancelOrder](docs/sdks/orders/README.md#cancelorder) - Cancel Order
* [setExtraReportingData](docs/sdks/orders/README.md#setextrareportingdata) - Set Extra Reporting Data
* [listCorrespondentOrders](docs/sdks/orders/README.md#listcorrespondentorders) - List Correspondent Orders

### [personManagement()](docs/sdks/personmanagement/README.md)

* [createLegalNaturalPerson](docs/sdks/personmanagement/README.md#createlegalnaturalperson) - Create Legal Natural Person
* [listLegalNaturalPersons](docs/sdks/personmanagement/README.md#listlegalnaturalpersons) - List Legal Natural Persons
* [getLegalNaturalPerson](docs/sdks/personmanagement/README.md#getlegalnaturalperson) - Get Legal Natural Persons
* [updateLegalNaturalPerson](docs/sdks/personmanagement/README.md#updatelegalnaturalperson) - Update Legal Natural Person
* [assignLargeTrader](docs/sdks/personmanagement/README.md#assignlargetrader) - Assign Large Trader
* [endLargeTraderLegalNaturalPerson](docs/sdks/personmanagement/README.md#endlargetraderlegalnaturalperson) - End Large Trader
* [createLegalEntity](docs/sdks/personmanagement/README.md#createlegalentity) - Create Legal Entity
* [listLegalEntities](docs/sdks/personmanagement/README.md#listlegalentities) - List Legal Entity
* [getLegalEntity](docs/sdks/personmanagement/README.md#getlegalentity) - Get Legal Entity
* [updateLegalEntity](docs/sdks/personmanagement/README.md#updatelegalentity) - Update Legal Entity
* [assignLargeTraderLegalEntity](docs/sdks/personmanagement/README.md#assignlargetraderlegalentity) - Assign Entity Large Trader
* [endLargeTrader](docs/sdks/personmanagement/README.md#endlargetrader) - End Entity Large Trader

### [reader()](docs/sdks/reader/README.md)

* [listEventMessages](docs/sdks/reader/README.md#listeventmessages) - List Event Messages
* [getEventMessage](docs/sdks/reader/README.md#geteventmessage) - Get Event Message

### [retirements()](docs/sdks/retirements/README.md)

* [listContributionSummaries](docs/sdks/retirements/README.md#listcontributionsummaries) - List Contribution Summaries
* [retrieveContributionConstraints](docs/sdks/retirements/README.md#retrievecontributionconstraints) - Retrieve Contribution Constraints
* [listDistributionSummaries](docs/sdks/retirements/README.md#listdistributionsummaries) - List Distribution Summaries
* [retrieveDistributionConstraints](docs/sdks/retirements/README.md#retrievedistributionconstraints) - Retrieve Distribution Constraints

### [scheduleTransfers()](docs/sdks/scheduletransfers/README.md)

* [listScheduleSummaries](docs/sdks/scheduletransfers/README.md#listschedulesummaries) - List Schedule Summaries
* [createAchDepositSchedule](docs/sdks/scheduletransfers/README.md#createachdepositschedule) - Create ACH Deposit Schedule
* [listAchDepositSchedules](docs/sdks/scheduletransfers/README.md#listachdepositschedules) - List ACH Deposit Schedules
* [getAchDepositSchedule](docs/sdks/scheduletransfers/README.md#getachdepositschedule) - Get ACH Deposit Schedule
* [updateAchDepositSchedule](docs/sdks/scheduletransfers/README.md#updateachdepositschedule) - Update ACH Deposit Schedules
* [cancelAchDepositSchedule](docs/sdks/scheduletransfers/README.md#cancelachdepositschedule) - Cancel ACH Deposit Schedule
* [createAchWithdrawalSchedule](docs/sdks/scheduletransfers/README.md#createachwithdrawalschedule) - Create ACH Withdrawal Schedule
* [listAchWithdrawalSchedules](docs/sdks/scheduletransfers/README.md#listachwithdrawalschedules) - List ACH Withdrawal Schedules
* [getAchWithdrawalSchedule](docs/sdks/scheduletransfers/README.md#getachwithdrawalschedule) - Get ACH Withdrawal Schedule
* [updateAchWithdrawalSchedule](docs/sdks/scheduletransfers/README.md#updateachwithdrawalschedule) - Update ACH Withdrawal Schedule
* [cancelAchWithdrawalSchedule](docs/sdks/scheduletransfers/README.md#cancelachwithdrawalschedule) - Cancel ACH Withdrawal Schedule
* [createWireWithdrawalSchedule](docs/sdks/scheduletransfers/README.md#createwirewithdrawalschedule) - Create Wire Withdrawal Schedule
* [listWireWithdrawalSchedules](docs/sdks/scheduletransfers/README.md#listwirewithdrawalschedules) - List Wire Withdrawal Schedules
* [getWireWithdrawalSchedule](docs/sdks/scheduletransfers/README.md#getwirewithdrawalschedule) - Get Wire Withdrawal Schedule
* [updateWireWithdrawalSchedule](docs/sdks/scheduletransfers/README.md#updatewirewithdrawalschedule) - Update Wire Withdrawal Schedule
* [cancelWireWithdrawalSchedule](docs/sdks/scheduletransfers/README.md#cancelwirewithdrawalschedule) - Cancel Wire Withdrawal Schedule


### [subscriber()](docs/sdks/subscriber/README.md)

* [createPushSubscription](docs/sdks/subscriber/README.md#createpushsubscription) - Create Push Subscription
* [listPushSubscriptions](docs/sdks/subscriber/README.md#listpushsubscriptions) - List Push Subscriptions
* [getPushSubscription](docs/sdks/subscriber/README.md#getpushsubscription) - Get Push Subscription
* [updatePushSubscription](docs/sdks/subscriber/README.md#updatepushsubscription) - Update Subscription
* [deletePushSubscription](docs/sdks/subscriber/README.md#deletepushsubscription) - Delete Subscription
* [getPushSubscriptionDelivery](docs/sdks/subscriber/README.md#getpushsubscriptiondelivery) - Get Subscription Event Delivery
* [listPushSubscriptionDeliveries](docs/sdks/subscriber/README.md#listpushsubscriptiondeliveries) - List Push Subscription Event Deliveries

### [testSimulation()](docs/sdks/testsimulation/README.md)

* [simulateCreateCheckDeposit](docs/sdks/testsimulation/README.md#simulatecreatecheckdeposit) - Simulate Check Deposit Creation
* [forceApproveAchDeposit](docs/sdks/testsimulation/README.md#forceapproveachdeposit) - ACH Deposit Approval
* [forceNocAchDeposit](docs/sdks/testsimulation/README.md#forcenocachdeposit) - NOC for a Deposit
* [forceRejectAchDeposit](docs/sdks/testsimulation/README.md#forcerejectachdeposit) - ACH Deposit Rejection
* [forceReturnAchDeposit](docs/sdks/testsimulation/README.md#forcereturnachdeposit) - ACH Deposit Return
* [forceApproveAchWithdrawal](docs/sdks/testsimulation/README.md#forceapproveachwithdrawal) - ACH Withdrawal Approval
* [forceNocAchWithdrawal](docs/sdks/testsimulation/README.md#forcenocachwithdrawal) - ACH Withdrawal NOC
* [forceRejectAchWithdrawal](docs/sdks/testsimulation/README.md#forcerejectachwithdrawal) - ACH Withdrawal Rejection
* [forceReturnAchWithdrawal](docs/sdks/testsimulation/README.md#forcereturnachwithdrawal) - ACH Withdrawal Return
* [getMicroDepositAmounts](docs/sdks/testsimulation/README.md#getmicrodepositamounts) - Get Relationship Micro Deposit Verification
* [forceApproveIctDeposit](docs/sdks/testsimulation/README.md#forceapproveictdeposit) - Force Approve ICT Deposit
* [forceRejectIctDeposit](docs/sdks/testsimulation/README.md#forcerejectictdeposit) - Force Reject ICT Deposit
* [forceApproveIctWithdrawal](docs/sdks/testsimulation/README.md#forceapproveictwithdrawal) - Force Approve ICT Withdrawal
* [forceRejectIctWithdrawal](docs/sdks/testsimulation/README.md#forcerejectictwithdrawal) - Force Reject ICT Withdrawal
* [forceApproveWireWithdrawal](docs/sdks/testsimulation/README.md#forceapprovewirewithdrawal) - Force Approve Wire Withdrawal
* [forceRejectWireWithdrawal](docs/sdks/testsimulation/README.md#forcerejectwirewithdrawal) - Force Reject Wire Withdrawal
* [forceApproveCashJournal](docs/sdks/testsimulation/README.md#forceapprovecashjournal) - Force Approve Cash Journal
* [forceRejectCashJournal](docs/sdks/testsimulation/README.md#forcerejectcashjournal) - Force Reject Cash Journal

### [tradeAllocation()](docs/sdks/tradeallocation/README.md)

* [createTradeAllocation](docs/sdks/tradeallocation/README.md#createtradeallocation) - Create Trade Allocation
* [getTradeAllocation](docs/sdks/tradeallocation/README.md#gettradeallocation) - Get Trade Allocation
* [cancelTradeAllocation](docs/sdks/tradeallocation/README.md#canceltradeallocation) - Cancel Trade Allocation
* [rebookTradeAllocation](docs/sdks/tradeallocation/README.md#rebooktradeallocation) - Rebook Trade Allocation

### [tradeBooking()](docs/sdks/tradebooking/README.md)

* [createTrade](docs/sdks/tradebooking/README.md#createtrade) - Create Trade
* [getTrade](docs/sdks/tradebooking/README.md#gettrade) - Get Trade
* [completeTrade](docs/sdks/tradebooking/README.md#completetrade) - Complete Trade
* [cancelTrade](docs/sdks/tradebooking/README.md#canceltrade) - Cancel Trade
* [rebookTrade](docs/sdks/tradebooking/README.md#rebooktrade) - Rebook Trade
* [createExecution](docs/sdks/tradebooking/README.md#createexecution) - Create Execution
* [getExecution](docs/sdks/tradebooking/README.md#getexecution) - Get Execution
* [cancelExecution](docs/sdks/tradebooking/README.md#cancelexecution) - Cancel Execution
* [rebookExecution](docs/sdks/tradebooking/README.md#rebookexecution) - Rebook Execution

### [wires()](docs/sdks/wires/README.md)

* [getWireDeposit](docs/sdks/wires/README.md#getwiredeposit) - Get Wire Deposit
* [createWireWithdrawal](docs/sdks/wires/README.md#createwirewithdrawal) - Create Wire Withdrawal
* [getWireWithdrawal](docs/sdks/wires/README.md#getwirewithdrawal) - Get Wire Withdrawal
* [cancelWireWithdrawal](docs/sdks/wires/README.md#cancelwirewithdrawal) - Cancel Wire Withdrawal

</details>
<!-- End Available Resources and Operations [operations] -->

<!-- Start Debugging [debug] -->
## Debugging

### Debug
You can setup your SDK to emit debug logs for SDK requests and responses.

For request and response logging (especially json bodies), call `enableHTTPDebugLogging(boolean)` on the SDK builder like so:
```java
SDK.builder()
    .enableHTTPDebugLogging(true)
    .build();
```
Example output:
```
Sending request: http://localhost:35123/bearer#global GET
Request headers: {Accept=[application/json], Authorization=[******], Client-Level-Header=[added by client], Idempotency-Key=[some-key], x-speakeasy-user-agent=[speakeasy-sdk/java 0.0.1 internal 0.1.0 org.openapis.openapi]}
Received response: (GET http://localhost:35123/bearer#global) 200
Response headers: {access-control-allow-credentials=[true], access-control-allow-origin=[*], connection=[keep-alive], content-length=[50], content-type=[application/json], date=[Wed, 09 Apr 2025 01:43:29 GMT], server=[gunicorn/19.9.0]}
Response body:
{
  "authenticated": true, 
  "token": "global"
}
```
__WARNING__: This should only used for temporary debugging purposes. Leaving this option on in a production system could expose credentials/secrets in logs. <i>Authorization</i> headers are redacted by default and there is the ability to specify redacted header names via `SpeakeasyHTTPClient.setRedactedHeaders`.

__NOTE__: This is a convenience method that calls `HTTPClient.enableDebugLogging()`. The `SpeakeasyHTTPClient` honors this setting. If you are using a custom HTTP client, it is up to the custom client to honor this setting.

Another option is to set the System property `-Djdk.httpclient.HttpClient.log=all`. However, this second option does not log bodies.
<!-- End Debugging [debug] -->

<!-- Placeholder for Future Speakeasy SDK Sections -->
