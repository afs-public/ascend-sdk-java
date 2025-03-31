package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import java.util.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestEnrollmentsAndAgreements {
  private SDK sdk = null;
  private LegalNaturalPerson lnp;
  private Account account;
  private String accountId;
  private List<Agreement> agreements;

  @BeforeAll
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();
    lnp = AccountUtil.createLnp(sdk);
    account = AccountUtil.createAccount(sdk, lnp);
    accountId = account.accountId().get();
    agreements = AccountUtil.enrollAccount(sdk, account);
  }

  @Test
  @Order(1)
  public void test_enrollments_and_agreements_accounts_enroll_account_enroll_account1()
      throws Exception {
    Assertions.assertNotNull(agreements);
  }

  @Test
  @Order(2)
  public void
      test_enrollments_and_agreements_list_available_enrollments_list_available_enrollments1()
          throws Exception {
    Thread.sleep(5000);
    var res = sdk.enrollmentsAndAgreements().listAvailableEnrollments().accountId(accountId).call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }

  @Test
  @Order(3)
  public void test_enrollments_and_agreements_list_enrollments_list_enrollments1()
      throws Exception {
    var res = sdk.enrollmentsAndAgreements().listEnrollments().accountId(accountId).call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }

  @Test
  @Order(4)
  public void test_enrollments_and_agreements_affirm_agreements_affirm_agreements1()
      throws Exception {
    var res = AccountUtil.affirmAgreements(sdk, account, agreements);
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }

  @Test
  @Order(5)
  public void test_enrollments_and_agreements_list_agreements_list_agreements1() throws Exception {
    var res = sdk.enrollmentsAndAgreements().listAgreements().accountId(accountId).call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }

  @Test
  @Order(6)
  public void test_enrollments_and_agreements_list_entitlements_list_entitlements1()
      throws Exception {
    var res = sdk.enrollmentsAndAgreements().listEntitlements().accountId(accountId).call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }

  @Test
  @Order(7)
  public void test_enrollments_and_agreements_deactivate_enrollment_deactivate_enrollment1()
      throws Exception {
    var req =
        DeactivateEnrollmentRequestCreate.builder()
            .enrollmentId(AccountUtil.getEnrollmentToDeactivate(sdk, accountId))
            .build();
    var res =
        sdk.enrollmentsAndAgreements()
            .deactivateEnrollment()
            .accountId(accountId)
            .deactivateEnrollmentRequestCreate(req)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }

  @Order(8)
  @Test
  public void
      test_enrollments_and_agreements_accounts_ListAvailableEnrollmentsByAccountGroup_accounts_ListAvailableEnrollmentsByAccountGroup1()
          throws Exception {
    var result =
        sdk.enrollmentsAndAgreements()
            .accountsListAvailableEnrollmentsByAccountGroup(SdkUtil.getAccountGroupId());
    Assertions.assertNotNull(result);
    Assertions.assertEquals(200, result.statusCode());
  }
}
