# PersonManagement
(*personManagement()*)

## Overview

### Available Operations

* [createLegalNaturalPerson](#createlegalnaturalperson) - Create Legal Natural Person
* [listLegalNaturalPersons](#listlegalnaturalpersons) - List Legal Natural Persons
* [getLegalNaturalPerson](#getlegalnaturalperson) - Get Legal Natural Persons
* [updateLegalNaturalPerson](#updatelegalnaturalperson) - Update Legal Natural Person
* [assignLargeTrader](#assignlargetrader) - Assign Large Trader
* [endLargeTraderLegalNaturalPerson](#endlargetraderlegalnaturalperson) - End Large Trader
* [createLegalEntity](#createlegalentity) - Create Legal Entity
* [listLegalEntities](#listlegalentities) - List Legal Entity
* [getLegalEntity](#getlegalentity) - Get Legal Entity
* [updateLegalEntity](#updatelegalentity) - Update Legal Entity
* [assignLargeTraderLegalEntity](#assignlargetraderlegalentity) - Assign Entity Large Trader
* [endLargeTrader](#endlargetrader) - End Entity Large Trader

## createLegalNaturalPerson

Creates a Legal Natural Person.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.DateCreate;
import com.apexfintechsolutions.ascendsdk.models.components.EmploymentCreate;
import com.apexfintechsolutions.ascendsdk.models.components.EmploymentStatus;
import com.apexfintechsolutions.ascendsdk.models.components.FederalTaxClassification;
import com.apexfintechsolutions.ascendsdk.models.components.IrsFormType;
import com.apexfintechsolutions.ascendsdk.models.components.LegalNaturalPersonCreate;
import com.apexfintechsolutions.ascendsdk.models.components.PostalAddressCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.components.TaxProfileCreate;
import com.apexfintechsolutions.ascendsdk.models.components.UsTinStatus;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsCreateLegalNaturalPersonResponse;
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

        LegalNaturalPersonCreate req = LegalNaturalPersonCreate.builder()
                .birthDate(DateCreate.builder()
                    .build())
                .citizenshipCountries(List.of(
                    "US",
                    "CA"))
                .correspondentId("01HPMZZM6RKMVZA1JQ63RQKJRP")
                .employment(EmploymentCreate.builder()
                    .employmentStatus(EmploymentStatus.EMPLOYED)
                    .build())
                .familyName("Doe")
                .givenName("John")
                .personalAddress(PostalAddressCreate.builder()
                    .build())
                .taxProfile(TaxProfileCreate.builder()
                    .federalTaxClassification(FederalTaxClassification.C_CORPORATION)
                    .irsFormType(IrsFormType.W9)
                    .legalTaxRegionCode("US")
                    .usTinStatus(UsTinStatus.PASSING)
                    .build())
                .build();

        AccountsCreateLegalNaturalPersonResponse res = sdk.personManagement().createLegalNaturalPerson()
                .request(req)
                .call();

        if (res.legalNaturalPerson().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                   | Type                                                                        | Required                                                                    | Description                                                                 |
| --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- |
| `request`                                                                   | [LegalNaturalPersonCreate](../../models/shared/LegalNaturalPersonCreate.md) | :heavy_check_mark:                                                          | The request object to use for the request.                                  |

### Response

**[AccountsCreateLegalNaturalPersonResponse](../../models/operations/AccountsCreateLegalNaturalPersonResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## listLegalNaturalPersons

Gets a list of Legal Natural Person records based on search criteria.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsListLegalNaturalPersonsResponse;
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

        AccountsListLegalNaturalPersonsResponse res = sdk.personManagement().listLegalNaturalPersons()
                .pageSize(25)
                .pageToken("AbTYnwAkMjIyZDNjYTAtZmVjZS00N2Q5LTgyMDctNzI3MDdkMjFiZj3h")
                .orderBy("<value>")
                .filter("legal_natural_person_id == \"e6716139-da77-46d1-9f15-13599161db0b\"")
                .call();

        if (res.listLegalNaturalPersonsResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                                                                                                                                                                                                                     | Type                                                                                                                                                                                                                                                                                                                                                                                                                          | Required                                                                                                                                                                                                                                                                                                                                                                                                                      | Description                                                                                                                                                                                                                                                                                                                                                                                                                   | Example                                                                                                                                                                                                                                                                                                                                                                                                                       |
| ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `pageSize`                                                                                                                                                                                                                                                                                                                                                                                                                    | *Optional\<Integer>*                                                                                                                                                                                                                                                                                                                                                                                                          | :heavy_minus_sign:                                                                                                                                                                                                                                                                                                                                                                                                            | The maximum number of legal natural persons to return. The service may return fewer than this value. If unspecified, at most 25 legal natural persons will be returned. The maximum value is 100; values above 100 will be coerced to 100.                                                                                                                                                                                    | 25                                                                                                                                                                                                                                                                                                                                                                                                                            |
| `pageToken`                                                                                                                                                                                                                                                                                                                                                                                                                   | *Optional\<String>*                                                                                                                                                                                                                                                                                                                                                                                                           | :heavy_minus_sign:                                                                                                                                                                                                                                                                                                                                                                                                            | A page token, received from a previous `ListLegalNaturalPersons` call. Provide this to retrieve the subsequent page.<br/><br/> When paginating, all other parameters provided to `ListLegalNaturalPersons` must match the call that provided the page token.                                                                                                                                                                  | AbTYnwAkMjIyZDNjYTAtZmVjZS00N2Q5LTgyMDctNzI3MDdkMjFiZj3h                                                                                                                                                                                                                                                                                                                                                                      |
| `orderBy`                                                                                                                                                                                                                                                                                                                                                                                                                     | *Optional\<String>*                                                                                                                                                                                                                                                                                                                                                                                                           | :heavy_minus_sign:                                                                                                                                                                                                                                                                                                                                                                                                            | The order in which legal natural persons are listed.                                                                                                                                                                                                                                                                                                                                                                          |                                                                                                                                                                                                                                                                                                                                                                                                                               |
| `filter`                                                                                                                                                                                                                                                                                                                                                                                                                      | *Optional\<String>*                                                                                                                                                                                                                                                                                                                                                                                                           | :heavy_minus_sign:                                                                                                                                                                                                                                                                                                                                                                                                            | A CEL string to filter results; Use `upperAscii()` for case-insensitive searches; E.g. `given_name.upperAscii()=="rUsTy".upperAscii()`; See the [CEL Search](https://developer.apexclearing.com/apex-fintech-solutions/docs/cel-search) page in Guides for more information; Filter options include:<br/> `legal_natural_person_id`<br/> `correspondent_id`<br/> `given_name`<br/> `family_name`<br/> `tax_id`<br/> `tax_id_type`<br/> `investigation_id` | legal_natural_person_id == "e6716139-da77-46d1-9f15-13599161db0b"                                                                                                                                                                                                                                                                                                                                                             |

### Response

**[AccountsListLegalNaturalPersonsResponse](../../models/operations/AccountsListLegalNaturalPersonsResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getLegalNaturalPerson

Get Legal Natural Person

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsGetLegalNaturalPersonResponse;
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

        AccountsGetLegalNaturalPersonResponse res = sdk.personManagement().getLegalNaturalPerson()
                .legalNaturalPersonId("e6716139-da77-46d1-9f15-13599161db0b")
                .call();

        if (res.legalNaturalPerson().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                            | Type                                 | Required                             | Description                          | Example                              |
| ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ |
| `legalNaturalPersonId`               | *String*                             | :heavy_check_mark:                   | The legalNaturalPerson id.           | e6716139-da77-46d1-9f15-13599161db0b |

### Response

**[AccountsGetLegalNaturalPersonResponse](../../models/operations/AccountsGetLegalNaturalPersonResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## updateLegalNaturalPerson

Updates a Legal Natural Person.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.LegalNaturalPersonUpdate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsUpdateLegalNaturalPersonResponse;
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

        AccountsUpdateLegalNaturalPersonResponse res = sdk.personManagement().updateLegalNaturalPerson()
                .legalNaturalPersonId("e6716139-da77-46d1-9f15-13599161db0b")
                .updateMask("<value>")
                .legalNaturalPersonUpdate(LegalNaturalPersonUpdate.builder()
                    .build())
                .call();

        if (res.legalNaturalPerson().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                | Type                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     | Required                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              | Example                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `legalNaturalPersonId`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   | *String*                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 | :heavy_check_mark:                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       | The legalNaturalPerson id.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               | e6716139-da77-46d1-9f15-13599161db0b                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     |
| `updateMask`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             | *Optional\<String>*                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      | :heavy_minus_sign:                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       | The list of fields to update. Updatable Fields  `marital_status`  `citizenship_countries`  `personal_address.address_lines`  `personal_address.locality`  `personal_address.administrative_area`  `personal_address.region_code`  `personal_address.postal_code`  `control_person_company_symbols`  `finra_associated_entity`  `politically_exposed_organization`  `politically_exposed_immediate_family_names`  `is_correspondent_employee`  `employment.employer`  `employment.occupation`  `employment.start_year`  `employment.employment_status`  `employment.employer_address.address_lines`  `employment.employer_address.locality`  `employment.employer_address.administrative_area`  `employment.employer_address.region_code`  `employment.employer_address.postal_code`  `given_name`  `middle_names`  `family_name`  `tax_id`  `birth_date.year`  `birth_date.month`  `birth_date.day`  `death_date.day`  `death_date.month`  `death_date.year`  `identity_verification_result.raw_vendor_data_document_id`  `identity_verification_result.identity_verification_document_ids`  `accredited_investor`  `adviser`  `institutional_customer`  `foreign_identification.ftin`  `foreign_identification.identification_number`  `foreign_identification.issuing_region_code`  `foreign_identification.type`  `foreign_identification.issue_date.year`  `foreign_identification.issue_date.month`  `foreign_identification.issue_date.day`  `foreign_identification.expiration_date.year`  `foreign_identification.expiration_date.month`  `foreign_identification.expiration_date.day`  `tax_profile.withholding_state`  `tax_profile.legal_tax_region_code`  `natural_person_fdd.customer_referral_source.name`  `natural_person_fdd.customer_referral_source.relationship_to_applicant`  `natural_person_fdd.customer_referral_source.relationship_years_with_applicant`  `natural_person_fdd.customer_referral_source.relationship_years_with_broker`  `natural_person_fdd.customer_non_referral_source`  `natural_person_fdd.employment_and_employer_description`  `natural_person_fdd.negative_news.owner_has_negative_news_against_related_parties`  `natural_person_fdd.negative_news.negative_news_against_related_parties_description`  `natural_person_fdd.other_sources_of_wealth.applicant_has_other_sources_of_wealth`  `natural_person_fdd.other_sources_of_wealth.other_sources_of_wealth`  `natural_person_fdd.other_sources_of_wealth.other_source_of_wealth_verification`  `doing_business_as` |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| `legalNaturalPersonUpdate`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               | [LegalNaturalPersonUpdate](../../models/components/LegalNaturalPersonUpdate.md)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          | :heavy_check_mark:                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       | N/A                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |

### Response

**[AccountsUpdateLegalNaturalPersonResponse](../../models/operations/AccountsUpdateLegalNaturalPersonResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## assignLargeTrader

Assigns a person's Large Trader ID.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.AssignLargeTraderRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsAssignLargeTraderResponse;
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

        AccountsAssignLargeTraderResponse res = sdk.personManagement().assignLargeTrader()
                .legalNaturalPersonId("e6716139-da77-46d1-9f15-13599161db0b")
                .assignLargeTraderRequestCreate(AssignLargeTraderRequestCreate.builder()
                    .largeTraderId("1234567890")
                    .build())
                .call();

        if (res.largeTrader().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                   | Type                                                                                        | Required                                                                                    | Description                                                                                 | Example                                                                                     |
| ------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------- |
| `legalNaturalPersonId`                                                                      | *String*                                                                                    | :heavy_check_mark:                                                                          | The legalNaturalPerson id.                                                                  | e6716139-da77-46d1-9f15-13599161db0b                                                        |
| `assignLargeTraderRequestCreate`                                                            | [AssignLargeTraderRequestCreate](../../models/components/AssignLargeTraderRequestCreate.md) | :heavy_check_mark:                                                                          | N/A                                                                                         |                                                                                             |

### Response

**[AccountsAssignLargeTraderResponse](../../models/operations/AccountsAssignLargeTraderResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## endLargeTraderLegalNaturalPerson

Removes a person's Large Trader ID.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.EndLargeTraderRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.EndReason;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsEndLargeTraderLegalNaturalPersonResponse;
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

        AccountsEndLargeTraderLegalNaturalPersonResponse res = sdk.personManagement().endLargeTraderLegalNaturalPerson()
                .legalNaturalPersonId("e6716139-da77-46d1-9f15-13599161db0b")
                .endLargeTraderRequestCreate(EndLargeTraderRequestCreate.builder()
                    .endReason(EndReason.EVENT_REASON_OTHER)
                    .build())
                .call();

        // handle response
    }
}
```

### Parameters

| Parameter                                                                             | Type                                                                                  | Required                                                                              | Description                                                                           | Example                                                                               |
| ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- |
| `legalNaturalPersonId`                                                                | *String*                                                                              | :heavy_check_mark:                                                                    | The legalNaturalPerson id.                                                            | e6716139-da77-46d1-9f15-13599161db0b                                                  |
| `endLargeTraderRequestCreate`                                                         | [EndLargeTraderRequestCreate](../../models/components/EndLargeTraderRequestCreate.md) | :heavy_check_mark:                                                                    | N/A                                                                                   |                                                                                       |

### Response

**[AccountsEndLargeTraderLegalNaturalPersonResponse](../../models/operations/AccountsEndLargeTraderLegalNaturalPersonResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## createLegalEntity

Creates a Legal Entity.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.EntityType;
import com.apexfintechsolutions.ascendsdk.models.components.FederalTaxClassification;
import com.apexfintechsolutions.ascendsdk.models.components.IrsFormType;
import com.apexfintechsolutions.ascendsdk.models.components.LegalEntityCreate;
import com.apexfintechsolutions.ascendsdk.models.components.LegalEntityCreateTaxIdType;
import com.apexfintechsolutions.ascendsdk.models.components.PostalAddressCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.components.TaxProfileCreate;
import com.apexfintechsolutions.ascendsdk.models.components.UsTinStatus;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsCreateLegalEntityResponse;
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

        LegalEntityCreate req = LegalEntityCreate.builder()
                .correspondentId("01HPMZZM6RKMVZA1JQ63RQKJRP")
                .entityName("Acme, Inc")
                .entityType(EntityType.CORPORATION)
                .legalAddress(PostalAddressCreate.builder()
                    .build())
                .operatingRegions(List.of(
                    "US",
                    "CA"))
                .registrationRegion("US")
                .taxId("987-65-4321")
                .taxIdType(LegalEntityCreateTaxIdType.TAX_ID_TYPE_ITIN)
                .taxProfile(TaxProfileCreate.builder()
                    .federalTaxClassification(FederalTaxClassification.C_CORPORATION)
                    .irsFormType(IrsFormType.W9)
                    .legalTaxRegionCode("US")
                    .usTinStatus(UsTinStatus.PASSING)
                    .build())
                .build();

        AccountsCreateLegalEntityResponse res = sdk.personManagement().createLegalEntity()
                .request(req)
                .call();

        if (res.legalEntity().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                     | Type                                                          | Required                                                      | Description                                                   |
| ------------------------------------------------------------- | ------------------------------------------------------------- | ------------------------------------------------------------- | ------------------------------------------------------------- |
| `request`                                                     | [LegalEntityCreate](../../models/shared/LegalEntityCreate.md) | :heavy_check_mark:                                            | The request object to use for the request.                    |

### Response

**[AccountsCreateLegalEntityResponse](../../models/operations/AccountsCreateLegalEntityResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## listLegalEntities

Gets a list of Legal Entity records based on search criteria.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsListLegalEntitiesResponse;
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

        AccountsListLegalEntitiesResponse res = sdk.personManagement().listLegalEntities()
                .pageSize(25)
                .pageToken("AbTYnwAkMjIyZDNjYTAtZmVjZS00N2Q5LTgyMDctNzI3MDdkMjFiZj3h")
                .orderBy("<value>")
                .filter("<value>")
                .call();

        if (res.listLegalEntitiesResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                                                                                                                                                                                                       | Type                                                                                                                                                                                                                                                                                                                                                                                                            | Required                                                                                                                                                                                                                                                                                                                                                                                                        | Description                                                                                                                                                                                                                                                                                                                                                                                                     | Example                                                                                                                                                                                                                                                                                                                                                                                                         |
| --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `pageSize`                                                                                                                                                                                                                                                                                                                                                                                                      | *Optional\<Integer>*                                                                                                                                                                                                                                                                                                                                                                                            | :heavy_minus_sign:                                                                                                                                                                                                                                                                                                                                                                                              | The maximum number of legal entities to return. The service may return fewer than this value. If unspecified, at most 25 legal entities will be returned. The maximum value is 100; values above 100 will be coerced to 100.                                                                                                                                                                                    | 25                                                                                                                                                                                                                                                                                                                                                                                                              |
| `pageToken`                                                                                                                                                                                                                                                                                                                                                                                                     | *Optional\<String>*                                                                                                                                                                                                                                                                                                                                                                                             | :heavy_minus_sign:                                                                                                                                                                                                                                                                                                                                                                                              | A page token, received from a previous `ListLegalEntities` call. Provide this to retrieve the subsequent page.<br/><br/> When paginating, all other parameters provided to `ListLegalEntities` must match the call that provided the page token.                                                                                                                                                                | AbTYnwAkMjIyZDNjYTAtZmVjZS00N2Q5LTgyMDctNzI3MDdkMjFiZj3h                                                                                                                                                                                                                                                                                                                                                        |
| `orderBy`                                                                                                                                                                                                                                                                                                                                                                                                       | *Optional\<String>*                                                                                                                                                                                                                                                                                                                                                                                             | :heavy_minus_sign:                                                                                                                                                                                                                                                                                                                                                                                              | The order in which legal entities are listed.                                                                                                                                                                                                                                                                                                                                                                   |                                                                                                                                                                                                                                                                                                                                                                                                                 |
| `filter`                                                                                                                                                                                                                                                                                                                                                                                                        | *Optional\<String>*                                                                                                                                                                                                                                                                                                                                                                                             | :heavy_minus_sign:                                                                                                                                                                                                                                                                                                                                                                                              | A CEL string to filter results; Use `upperAscii()` for case-insensitive searches; E.g. `entity_name.upperAscii()=="AcMe,InC".upperAscii()`; See the [CEL Search](https://developer.apexclearing.com/apex-fintech-solutions/docs/cel-search) page in Guides for more information; Filter options include:<br/> `legal_entity_id`<br/> `investigation_id`<br/> `exempt_customer_reason`<br/> `exempt_verifying_beneficial_owners` |                                                                                                                                                                                                                                                                                                                                                                                                                 |

### Response

**[AccountsListLegalEntitiesResponse](../../models/operations/AccountsListLegalEntitiesResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403               | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getLegalEntity

Get Legal Entity

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsGetLegalEntityResponse;
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

        AccountsGetLegalEntityResponse res = sdk.personManagement().getLegalEntity()
                .legalEntityId("e6716139-da77-46d1-9f15-13599161db0b")
                .call();

        if (res.legalEntity().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                            | Type                                 | Required                             | Description                          | Example                              |
| ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ | ------------------------------------ |
| `legalEntityId`                      | *String*                             | :heavy_check_mark:                   | The legalEntity id.                  | e6716139-da77-46d1-9f15-13599161db0b |

### Response

**[AccountsGetLegalEntityResponse](../../models/operations/AccountsGetLegalEntityResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## updateLegalEntity

Updates a Legal Entity.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.LegalEntityUpdate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsUpdateLegalEntityResponse;
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

        AccountsUpdateLegalEntityResponse res = sdk.personManagement().updateLegalEntity()
                .legalEntityId("42567868-9373-4872-9d24-2e33f6c19b75")
                .updateMask("<value>")
                .legalEntityUpdate(LegalEntityUpdate.builder()
                    .build())
                .call();

        if (res.legalEntity().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             | Type                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  | Required                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           | Example                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `legalEntityId`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       | *String*                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              | :heavy_check_mark:                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    | The legalEntity id.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   | 42567868-9373-4872-9d24-2e33f6c19b75                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
| `updateMask`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          | *Optional\<String>*                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   | :heavy_minus_sign:                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    | The list of fields to update. Updatable Fields `for_the_benefit_of` `tax_profile.withholding_state` `formation_date` `broker_dealer` `legal_address.address_lines` `legal_address.locality` `legal_address.administrative_area` `legal_address.region_code` `legal_address.postal_code` `exempt_verifying_beneficial_owners` `exempt_customer_reason` `foreign_financial_institution` `accredited_investor` `adviser` `regulated_investment_company` `lei_code` `entity_name` `tax_id` `tax_id_type` `institutional_customer` `operating_regions` `doing_business_as` |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| `legalEntityUpdate`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   | [LegalEntityUpdate](../../models/components/LegalEntityUpdate.md)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     | :heavy_check_mark:                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    | N/A                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |

### Response

**[AccountsUpdateLegalEntityResponse](../../models/operations/AccountsUpdateLegalEntityResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## assignLargeTraderLegalEntity

Assigns a person's Large Trader ID.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.AssignLargeTraderRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsAssignLargeTraderLegalEntityResponse;
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

        AccountsAssignLargeTraderLegalEntityResponse res = sdk.personManagement().assignLargeTraderLegalEntity()
                .legalEntityId("e6716139-da77-46d1-9f15-13599161db0b")
                .assignLargeTraderRequestCreate(AssignLargeTraderRequestCreate.builder()
                    .largeTraderId("1234567890")
                    .build())
                .call();

        if (res.largeTrader().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                   | Type                                                                                        | Required                                                                                    | Description                                                                                 | Example                                                                                     |
| ------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------- |
| `legalEntityId`                                                                             | *String*                                                                                    | :heavy_check_mark:                                                                          | The legalEntity id.                                                                         | e6716139-da77-46d1-9f15-13599161db0b                                                        |
| `assignLargeTraderRequestCreate`                                                            | [AssignLargeTraderRequestCreate](../../models/components/AssignLargeTraderRequestCreate.md) | :heavy_check_mark:                                                                          | N/A                                                                                         |                                                                                             |

### Response

**[AccountsAssignLargeTraderLegalEntityResponse](../../models/operations/AccountsAssignLargeTraderLegalEntityResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## endLargeTrader

Removes a person's Large Trader ID.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.EndLargeTraderRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.EndReason;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsEndLargeTrader1Response;
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

        AccountsEndLargeTrader1Response res = sdk.personManagement().endLargeTrader()
                .legalEntityId("e6716139-da77-46d1-9f15-13599161db0b")
                .endLargeTraderRequestCreate(EndLargeTraderRequestCreate.builder()
                    .endReason(EndReason.EVENT_REASON_CREATED)
                    .build())
                .call();

        // handle response
    }
}
```

### Parameters

| Parameter                                                                             | Type                                                                                  | Required                                                                              | Description                                                                           | Example                                                                               |
| ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- |
| `legalEntityId`                                                                       | *String*                                                                              | :heavy_check_mark:                                                                    | The legalEntity id.                                                                   | e6716139-da77-46d1-9f15-13599161db0b                                                  |
| `endLargeTraderRequestCreate`                                                         | [EndLargeTraderRequestCreate](../../models/components/EndLargeTraderRequestCreate.md) | :heavy_check_mark:                                                                    | N/A                                                                                   |                                                                                       |

### Response

**[AccountsEndLargeTrader1Response](../../models/operations/AccountsEndLargeTrader1Response.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 403, 404          | application/json       |
| models/errors/Status   | 500, 503               | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |