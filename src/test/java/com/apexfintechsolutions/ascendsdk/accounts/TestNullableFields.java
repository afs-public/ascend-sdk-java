package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import java.util.List;
import org.junit.jupiter.api.*;
import org.openapitools.jackson.nullable.JsonNullable;

/**
 * Verifies nullable protobuf wrapper fields are correctly modeled as {@code JsonNullable<Boolean>}
 * — the Java counterpart to test_nullable_fields.test.ts in the TypeScript SDK.
 *
 * <p>For a W-9, the API leaves {@code tax_profile.treaty_benefits_requested} unset. It is a {@code
 * google.protobuf.BoolValue}, so an unset value is serialized as JSON {@code null} (EmitUnpopulated
 * marshaling). The SDK now correctly models this as {@code JsonNullable<Boolean>}, which preserves
 * the tri-state: undefined (never sent), null (explicitly null), or a value.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestNullableFields {
  private SDK sdk;

  @BeforeAll
  public void setup() {
    sdk = SdkUtil.getSdk();
  }

  @Test
  public void test_create_lnp_validator_w9() throws Exception {
    var request =
        LegalNaturalPersonCreate.builder()
            .birthDate(DateCreate.builder().day(13).month(3).year(1981).build())
            .givenName("Bob")
            .familyName("Jacob")
            .correspondentId(SdkUtil.getCorrespondentId())
            .taxIdType(TaxIdType.TAX_ID_TYPE_SSN)
            .taxId("874456789")
            .citizenshipCountries(List.of("US"))
            .personalAddress(
                PostalAddressCreate.builder()
                    .addressLines(List.of("19409 Sherilyn Courts"))
                    .locality("Portland")
                    .administrativeArea("OR")
                    .postalCode("97035")
                    .regionCode("US")
                    .build())
            .taxProfile(
                TaxProfileCreate.builder()
                    .federalTaxClassification(
                        FederalTaxClassification.INDIV_SOLEPROP_OR_SINGLEMEMBERLLC)
                    .usTinStatus(UsTinStatus.PASSING)
                    .irsFormType(IrsFormType.W9)
                    .legalTaxRegionCode("US")
                    .build())
            .employment(
                EmploymentCreate.builder()
                    .occupation("fisherman")
                    .employmentStatus(EmploymentStatus.EMPLOYED)
                    .employerAddress(
                        PostalAddressCreate.builder()
                            .addressLines(List.of("123 Street"))
                            .locality("Portland")
                            .administrativeArea("OR")
                            .postalCode("97209")
                            .regionCode("US")
                            .build())
                    .build())
            .identityVerificationResult(
                IdentityVerificationResultCreate.builder()
                    .addressVerified(true)
                    .birthDateVerified(true)
                    .executionDate(DateCreate.builder().day(13).month(3).year(2021).build())
                    .nameVerified(true)
                    .taxIdVerified(true)
                    .externalCaseId("6526280")
                    .vendor("Super Security Service")
                    .rawVendorDataDocumentId("04eb923b-793d-481d-98c4-bb16f17378ea")
                    .build())
            .build();

    // Current behavior: unlike the TS SDK, the Java SDK does NOT reject the null
    // wrapper — the response deserializes successfully.
    var response = sdk.personManagement().createLegalNaturalPerson(request);
    Assertions.assertEquals(200, response.statusCode());

    var lnp = response.legalNaturalPerson().get();
    var taxProfile = lnp.taxProfile().get();

    // The server returns treaty_benefits_requested = null for W-9. With JsonNullable,
    // the SDK correctly distinguishes "explicitly null" (isPresent + null value) from
    // "never sent" (!isPresent).
    JsonNullable<Boolean> treatyBenefitsRequested = taxProfile.treatyBenefitsRequested();
    Assertions.assertTrue(
        treatyBenefitsRequested.isPresent(),
        "Expected treatyBenefitsRequested to be present (explicitly null, not undefined)");
    Assertions.assertNull(
        treatyBenefitsRequested.get(),
        "Expected treatyBenefitsRequested value to be null (server sent explicit null)");
  }
}
