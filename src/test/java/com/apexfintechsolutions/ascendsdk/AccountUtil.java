package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsAffirmAgreementsResponse;
import com.apexfintechsolutions.ascendsdk.models.operations.AccountsGetAccountResponse;
import java.util.*;

public class AccountUtil {

  public static String large_trader_id = "123456789100";

  public static LegalNaturalPerson createLnp(SDK sdk) throws Exception {
    // Create request
    var req =
        LegalNaturalPersonCreate.builder()
            .birthDate(DateCreate.builder().day(13).month(3).year(1981).build())
            .givenName("Bob")
            .familyName("Jacob")
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
            .identityVerificationResult(
                IdentityVerificationResultCreate.builder()
                    .addressVerified(true)
                    .birthDateVerified(true)
                    .executionDate(DateCreate.builder().day(13).month(3).year(2021).build())
                    .externalCaseId("6526280")
                    .nameVerified(true)
                    .taxIdVerified(true)
                    .vendor("Super Security Service")
                    .rawVendorDataDocumentId("04eb923b-793d-481d-98c4-bb16f17378ea")
                    .build())
            .build();

    // Submit request

    var response = sdk.personManagement().createLegalNaturalPerson(req);
    return response.legalNaturalPerson().get();
  }

  public static Account createEnrolledAccount(SDK sdk) throws Exception {
    var person = createLnp(sdk);
    Thread.sleep(5000);
    var account = createAccount(sdk, person);
    Thread.sleep(5000);
    var agreements = enrollAccount(sdk, account);
    Thread.sleep(5000);
    affirmAgreements(sdk, account, agreements);

    Thread.sleep(1000);

    int attempts = 5;
    for (int i = 0; i < attempts; i++) {
      Account acc = getAccount(sdk, account.accountId().get());
      if (acc.state().get() == AccountState.OPEN) {
        return acc;
      }
      Thread.sleep(2000);
    }

    throw new Exception("Account never reached OPEN state!");
  }

  public static Account getAccount(SDK sdk, String accountId) throws Exception {
    AccountsGetAccountResponse res = sdk.accountCreation().getAccount(accountId);
    return res.account().get();
  }

  public static Account createAccount(SDK sdk, LegalNaturalPerson person) throws Exception {
    var party = createParty(person.legalNaturalPersonId().get());

    // Create request
    var req =
        AccountRequestCreate.builder()
            .correspondentId(SdkUtil.getCorrespondentId())
            .accountGroupId(SdkUtil.getAccountGroupId())
            .parties(List.of(party))
            .investmentProfile(
                InvestmentProfileCreate.builder()
                    .accountGoals(
                        AccountGoalsCreate.builder()
                            .investmentObjective(InvestmentObjective.BALANCED)
                            .liquidityNeeds(LiquidityNeeds.SOMEWHAT_IMPORTANT)
                            .riskTolerance(RiskTolerance.MEDIUM)
                            .timeHorizon(TimeHorizon.AVERAGE)
                            .build())
                    .customerProfile(
                        CustomerProfileCreate.builder()
                            .annualIncomeRangeUsd(AnnualIncomeRangeUsd.FROM25_K_TO50_K)
                            .federalTaxBracket(24)
                            .investmentExperience(InvestmentExperience.LIMITED)
                            .liquidNetWorthRangeUsd(LiquidNetWorthRangeUsd.FROM200_K_TO300_K)
                            .totalNetWorthRangeUsd(TotalNetWorthRangeUsd.FROM300_K_TO500_K)
                            .build())
                    .build())
            .taxProfile(
                AccountTaxProfileCreate.builder()
                    .costBasisLotDisposalMethod(
                        CostBasisLotDisposalMethod.COST_BASIS_LOT_DISPOSAL_FIFO)
                    .section475Election(false)
                    .build())
            .correspondentId(SdkUtil.getCorrespondentId())
            .build();

    // Submit request
    var res = sdk.accountCreation().createAccount(req);
    return res.account().get();
  }

  public static PartyRequestCreate createParty(String legalNaturalPersonId) {
    return PartyRequestCreate.builder()
        .emailAddress("john@gmail.com")
        .mailingAddress(
            PostalAddressCreate.builder()
                .addressLines(List.of("123 Main St"))
                .locality("Anytown")
                .administrativeArea("CA")
                .postalCode("12345")
                .regionCode("US")
                .build())
        .phoneNumber(PhoneNumberCreate.builder().e164Number("+14155552671").build())
        .relationType(RelationType.PRIMARY_OWNER)
        .legalNaturalPersonId(legalNaturalPersonId)
        .build();
  }

  public static List<Agreement> enrollAccount(SDK sdk, Account account) throws Exception {
    // Create request
    var request =
        EnrollmentCreate.builder()
            .principalApproverId("01HMESE8WMDNTTWJ2BAEG3TZWA")
            .type(EnrollmentCreateType.REGISTRATION_INDIVIDUAL)
            .individualEnrollmentMetadata(
                IndividualEnrollmentMetadataCreate.builder()
                    .fdicCashSweep(
                        IndividualEnrollmentMetadataCreateFdicCashSweep.FDIC_CASH_SWEEP_DECLINE)
                    .dividendReinvestmentPlan(
                        IndividualEnrollmentMetadataCreateDividendReinvestmentPlan
                            .DIVIDEND_REINVESTMENT_ENROLL)
                    .build())
            .build();

    // Submit request
    var res =
        sdk.enrollmentsAndAgreements()
            .enrollAccount(
                account.accountId().get(),
                EnrollAccountRequestCreate.builder().enrollment(request).build());
    return res.enrollAccountResponse().get().agreements().get();
  }

  public static AccountsAffirmAgreementsResponse affirmAgreements(
      SDK sdk, Account account, List<Agreement> agreements) throws Exception {
    List<String> agreementIds = new ArrayList<>();
    for (Agreement agreement : agreements) {
      agreementIds.add(agreement.agreementId().get());
    }
    var response =
        sdk.enrollmentsAndAgreements()
            .affirmAgreements(
                account.accountId().get(), new AffirmAgreementsRequestCreate(agreementIds));
    return response;
  }

  public static LargeTrader assignLargeTrader(SDK sdk, String lnpId) throws Exception {
    var request = AssignLargeTraderRequestCreate.builder().largeTraderId(large_trader_id).build();

    // Submit request
    var res = sdk.personManagement().assignLargeTrader(lnpId, request);
    return res.largeTrader().get();
  }

  public static LegalEntity createLegalEntity(SDK sdk) throws Exception {
    var request =
        LegalEntityCreate.builder()
            .accreditedInvestor(false)
            .adviser(false)
            .brokerDealer(false)
            .correspondentId(SdkUtil.getCorrespondentId())
            .entityName("AcmeInc")
            .entityType(EntityType.ESTATE)
            .exemptVerifyingBeneficialOwners(false)
            .foreignFinancialInstitution(false)
            .legalAddress(
                PostalAddressCreate.builder()
                    .locality("Portland")
                    .regionCode("US")
                    .postalCode("97035")
                    .administrativeArea("OR")
                    .addressLines(List.of("123 Main St"))
                    .build())
            .leiCode("12340012345678911000")
            .operatingRegions(List.of("US"))
            .registrationRegion("US")
            .taxId("874-45-6789")
            .taxIdType(LegalEntityCreateTaxIdType.TAX_ID_TYPE_SSN)
            .taxProfile(
                TaxProfileCreate.builder()
                    .federalTaxClassification(FederalTaxClassification.TRUST_ESTATE)
                    .usTinStatus(UsTinStatus.PASSING)
                    .irsFormType(IrsFormType.W9)
                    .legalTaxRegionCode("US")
                    .build())
            .build();

    var result = sdk.personManagement().createLegalEntity(request);
    if (result.statusCode() != 200) {
      throw new Exception("Failed to create legal entity: " + result.statusCode());
    }

    return result.legalEntity().get();
  }

  public static String getInvestigationId() {
    return "01JHGRJG62CZ0TV805CSWYHJ31";
  }

  public static String getAddPartyId(SDK sdk, String accountId, String legalEntityId)
      throws Exception {
    var partyReq =
        AddPartyRequestCreate.builder()
            .authorizedByPartyIds(
                List.of(
                    "8096110d-fb55-4f9d-b883-b84f0b70d3ea", "8096110d-fb55-4f9d-b883-b84f0b70d3rb"))
            .parent("accounts/" + accountId)
            .party(
                PartyRequestCreate.builder()
                    .emailAddress("example@domain.com")
                    .mailingAddress(
                        PostalAddressCreate.builder()
                            .addressLines(List.of("123 Main St"))
                            .locality("Anytown")
                            .administrativeArea("CA")
                            .postalCode("12345")
                            .regionCode("US")
                            .build())
                    .phoneNumber(PhoneNumberCreate.builder().e164Number("+14155552671").build())
                    .legalEntityId(legalEntityId)
                    .relationType(RelationType.PRIMARY_OWNER)
                    .build())
            .build();
    var res =
        sdk.accountManagement()
            .addParty()
            .addPartyRequestCreate(partyReq)
            .accountId(accountId)
            .call();
    if (res.statusCode() != 200) {
      throw new Exception("Failed to add party: " + res.statusCode());
    }
    return res.party().get().partyId().get();
  }

  public static String getReplacePartyId(SDK sdk, String accountId, String lnpId, String partyId)
      throws Exception {
    var partyReq =
        ReplacePartyRequestCreate.builder()
            .authorizedByPartyIds(
                List.of(
                    "8096110d-fb55-4f9d-b883-b84f0b70d3ea", "8096110d-fb55-4f9d-b883-b84f0b70d3rb"))
            .name("accounts/" + accountId + "/parties/" + partyId)
            .party(
                PartyRequestCreate.builder()
                    .emailAddress("example@domain.com")
                    .mailingAddress(
                        PostalAddressCreate.builder()
                            .addressLines(List.of("123 Main St"))
                            .locality("Anytown")
                            .administrativeArea("CA")
                            .postalCode("12345")
                            .regionCode("US")
                            .build())
                    .phoneNumber(PhoneNumberCreate.builder().e164Number("+14155552671").build())
                    .legalNaturalPersonId(lnpId)
                    .relationType(RelationType.PRIMARY_OWNER)
                    .build())
            .build();

    var res =
        sdk.accountManagement()
            .replaceParty()
            .accountId(accountId)
            .partyId(partyId)
            .replacePartyRequestCreate(partyReq)
            .call();
    if (res.statusCode() == 200) {
      return res.party().get().partyId().get();
    } else {
      throw new Exception("Failed to add party: " + res.statusCode());
    }
  }

  public static String getTrustedContactId(SDK sdk, String accountId) throws Exception {
    var req =
        TrustedContactCreate.builder()
            .emailAddress("example@email.com")
            .familyName("Doe")
            .givenName("John")
            .phoneNumber(PhoneNumberCreate.builder().e164Number("+14155552671").build())
            .build();
    var res =
        sdk.accountManagement()
            .createTrustedContact()
            .accountId(accountId)
            .trustedContactCreate(req)
            .call();
    if (res.statusCode() == 200) {
      return res.trustedContact().get().trustedContactId().get();
    }
    throw new Exception("Failed to create trusted contact: " + res.statusCode());
  }

  public static String getInterestedPartyId(SDK sdk, String accountId) throws Exception {
    var req =
        InterestedPartyCreate.builder()
            .mailingAddress(
                PostalAddressCreate.builder()
                    .addressLines(List.of("123 Main St"))
                    .locality("Anytown")
                    .administrativeArea("CA")
                    .postalCode("12345")
                    .regionCode("US")
                    .build())
            .recipient("John Doe")
            .build();
    var res =
        sdk.accountManagement()
            .createInterestedParty()
            .accountId(accountId)
            .interestedPartyCreate(req)
            .call();
    if (res.statusCode() == 200) {
      return res.interestedParty().get().interestedPartyId().get();
    }
    throw new Exception("Failed to create interested party: " + res.statusCode());
  }

  public static String getRestrictionCode(SDK sdk, String accountId) throws Exception {
    var req =
        RestrictionCreate.builder()
            .createReason("Some reason for creating")
            .endedReason("Some reason for removing")
            .restrictionCode("TRADING_LIQUIDATION_ONLY_BY_CORRESPONDENT")
            .build();
    var res =
        sdk.accountManagement()
            .createRestriction()
            .accountId(accountId)
            .restrictionCreate(req)
            .call();
    if (res.statusCode() == 200) {
      return res.restriction().get().restrictionCode().get();
    }
    throw new Exception("Failed to create restriction: " + res.statusCode());
  }

  public static String getEnrollmentToDeactivate(SDK sdk, String accountId) throws Exception {
    var res = sdk.enrollmentsAndAgreements().listEnrollments().accountId(accountId).call();
    var enrollments = res.listEnrollmentsResponse().get().enrollments().get();
    for (Enrollment enrollment : enrollments) {
      if (enrollment.type().get().equals(EnrollmentType1.DIVIDEND_REINVESTMENT_PLAN)) {
        return enrollment.enrollmentId().get();
      }
    }
    throw new Exception("No enrollment to deactivate: " + enrollments);
  }
}
