package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import java.util.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestAccountManagement {
  private SDK sdk;
  private Account account = null;
  private LegalNaturalPerson lnp = null;
  private LegalEntity legalEntity = null;
  private String addPartyId = null;
  private String replacePartyId = null;
  private String trustedContactId = null;
  private String interestedPartyId = null;
  private String restrictionCode = null;

  @BeforeAll
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();
    lnp = AccountUtil.createLnp(sdk);
    account = AccountUtil.createAccount(sdk, lnp);
    legalEntity = AccountUtil.createLegalEntity(sdk);
    addPartyId =
        AccountUtil.getAddPartyId(
            sdk, account.accountId().get(), legalEntity.legalEntityId().get());
    replacePartyId =
        AccountUtil.getReplacePartyId(
            sdk, account.accountId().get(), lnp.legalNaturalPersonId().get(), addPartyId);
    trustedContactId = AccountUtil.getTrustedContactId(sdk, account.accountId().get());
    interestedPartyId = AccountUtil.getInterestedPartyId(sdk, account.accountId().get());
    restrictionCode = AccountUtil.getRestrictionCode(sdk, account.accountId().get());
  }

  @Test
  public void test_account_management_accounts_list_accounts_list_accounts1() throws Exception {
    var res = sdk.accountManagement().listAccounts().call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }

  @Test
  public void test_account_management_accounts_update_account_update_account1() throws Exception {
    var update =
        new AccountRequestUpdate()
            .withCatAccountHolderType(AccountRequestUpdateCatAccountHolderType.I_INDIVIDUAL);
    var res =
        sdk.accountManagement()
            .updateAccount()
            .accountId(account.accountId().get())
            .accountRequestUpdate(update)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }

  @Test
  @Order(1)
  public void test_account_management_accounts_add_party_add_party1() throws Exception {
    Assertions.assertNotNull(addPartyId);
  }

  @Test
  @Order(2)
  public void test_account_management_accounts_update_party_update_party1() throws Exception {
    var update = new PartyRequestUpdate().withEmailAddress("email@example.com");
    var res =
        sdk.accountManagement()
            .updateParty()
            .accountId(account.accountId().get())
            .partyId(addPartyId)
            .partyRequestUpdate(update)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }

  @Test
  @Order(3)
  public void test_account_management_accounts_replace_party_replace_party1() throws Exception {
    Assertions.assertNotNull(replacePartyId);
  }

  @Test
  @Order(4)
  public void test_account_management_accounts_remove_party_remove_party1() throws Exception {
    var req =
        RemovePartyRequestCreate.builder()
            .authorizedByPartyIds(
                List.of(
                    "8096110d-fb55-4f9d-b883-b84f0b70d3ea", "8096110d-fb55-4f9d-b883-b84f0b70d3rb"))
            .name("accounts/" + account.accountId().get() + "/parties/" + replacePartyId)
            .build();
    var res =
        sdk.accountManagement()
            .removeParty()
            .accountId(account.accountId().get())
            .partyId(replacePartyId)
            .removePartyRequestCreate(req)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }

  @Test
  @Order(1)
  public void test_account_management_accounts_create_trusted_contact_create_trusted_contact1()
      throws Exception {
    Assertions.assertNotNull(trustedContactId);
  }

  @Test
  @Order(2)
  public void test_account_management_accounts_update_trusted_contact_update_trusted_contact1()
      throws Exception {
    var update = TrustedContactUpdate.builder().emailAddress("email@email.com").build();
    var res =
        sdk.accountManagement()
            .updateTrustedContact()
            .accountId(account.accountId().get())
            .trustedContactId(trustedContactId)
            .trustedContactUpdate(update)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }

  @Test
  @Order(3)
  public void test_account_management_accounts_delete_trusted_contact_delete_trusted_contact1()
      throws Exception {
    var res =
        sdk.accountManagement()
            .deleteTrustedContact()
            .accountId(account.accountId().get())
            .trustedContactId(trustedContactId)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }

  @Test
  @Order(1)
  public void test_account_management_accounts_create_interested_party_create_interested_party1()
      throws Exception {
    Assertions.assertNotNull(interestedPartyId);
  }

  @Test
  @Order(2)
  public void test_account_management_accounts_update_interested_party_update_interested_party1()
      throws Exception {
    var update = InterestedPartyUpdate.builder().recipient("Jane Doe").build();
    var res =
        sdk.accountManagement()
            .updateInterestedParty()
            .accountId(account.accountId().get())
            .interestedPartyId(interestedPartyId)
            .interestedPartyUpdate(update)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }

  @Test
  @Order(3)
  public void test_account_management_accounts_delete_interested_party_delete_interested_party1()
      throws Exception {
    var res =
        sdk.accountManagement()
            .deleteInterestedParty()
            .accountId(account.accountId().get())
            .interestedPartyId(interestedPartyId)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }

  @Test
  public void
      test_account_management_accounts_list_available_restrictions_list_available_restrictions1()
          throws Exception {
    var res =
        sdk.accountManagement()
            .listAvailableRestrictions()
            .accountId(account.accountId().get())
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }

  @Test
  @Order(1)
  public void test_account_management_accounts_create_restriction_create_restrictions1()
      throws Exception {
    Assertions.assertNotNull(restrictionCode);
  }

  @Test
  @Order(2)
  public void test_account_management_accounts_end_restriction_end_restrictions() throws Exception {
    var req = EndRestrictionRequestCreate.builder().reason("Some reason for removing").build();
    var res =
        sdk.accountManagement()
            .endRestriction()
            .accountId(account.accountId().get())
            .restrictionId(restrictionCode)
            .endRestrictionRequestCreate(req)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }

  @Test
  @Order(5)
  public void test_account_management_accounts_close_account_close_account1() throws Exception {
    var req = new CloseAccountRequestCreate();
    var res =
        sdk.accountManagement()
            .closeAccount()
            .accountId(account.accountId().get())
            .closeAccountRequestCreate(req)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }
}
