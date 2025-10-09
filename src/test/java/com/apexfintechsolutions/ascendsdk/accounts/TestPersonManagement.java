package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestPersonManagement {
  private SDK sdk;

  private LegalNaturalPerson lnp = null;

  private LargeTrader largeTrader = null;

  private LegalEntity legalEntity = null;

  @BeforeAll
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();
    lnp = AccountUtil.createLnp(sdk);
    largeTrader = AccountUtil.assignLargeTrader(sdk, lnp.legalNaturalPersonId().get());

    if (largeTrader == null) {
      throw new Exception("Failed to assign large trader");
    }

    legalEntity = AccountUtil.createLegalEntity(sdk);
  }

  @Test
  public void person_management_accounts_list_legal_natural_persons_list_legal_natural_persons1()
      throws Exception {
    var result = sdk.personManagement().listLegalNaturalPersons().call();
    Assertions.assertEquals(200, result.statusCode());
  }

  @Test
  public void person_management_accounts_list_legal_entities_list_legal_entities1()
      throws Exception {
    var result = sdk.personManagement().listLegalEntities().call();
    Assertions.assertEquals(200, result.statusCode());
  }

  @Test
  public void person_management_accounts_create_legal_natural_person_create_legal_natural_person1()
      throws Exception {
    Assertions.assertNotNull(lnp);
  }

  @Test
  public void person_management_accounts_get_legal_natural_person_get_legal_natural_person1()
      throws Exception {
    Assertions.assertNotNull(lnp);
    var result = sdk.personManagement().getLegalNaturalPerson(lnp.legalNaturalPersonId().get());
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(1)
  @Test
  public void person_management_accounts_update_legal_natural_person_update_legal_natural_person1()
      throws Exception {
    var update =
        LegalNaturalPersonUpdate.builder()
            .maritalStatus(LegalNaturalPersonUpdateMaritalStatus.MARRIED)
            .build();
    var result =
        sdk.personManagement().updateLegalNaturalPerson(lnp.legalNaturalPersonId().get(), update);
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(2)
  @Test
  public void person_management_accounts_assign_large_trader_assign_large_trader1()
      throws Exception {
    Assertions.assertNotNull(largeTrader);
    Assertions.assertTrue(largeTrader.largeTraderId().isPresent());
  }

  @Order(3)
  @Test
  public void person_management_accounts_end_large_trader_end_large_trader1() throws Exception {

    var request =
        EndLargeTraderRequestCreate.builder().endReason(EndReason.EVENT_REASON_CREATED).build();
    var result =
        sdk.personManagement()
            .endLargeTraderLegalNaturalPerson(lnp.legalNaturalPersonId().get(), request);
    Assertions.assertEquals(200, result.statusCode());
  }

  @Test
  public void person_management_accounts_create_legal_entity_create_legal_entity1() {
    Assertions.assertNotNull(legalEntity);
  }

  @Test
  public void person_management_accounts_get_legal_entity_get_legal_entity1() throws Exception {
    var result = sdk.personManagement().getLegalEntity(legalEntity.legalEntityId().get());
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(1)
  @Test
  public void person_management_accounts_update_legal_entity_update_legal_entity1()
      throws Exception {
    var request = LegalEntityUpdate.builder().entityName("John Doe").build();
    var result =
        sdk.personManagement().updateLegalEntity(legalEntity.legalEntityId().get(), request);
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(2)
  @Test
  public void
      person_management_accounts_assign_large_trader_legal_entity_assign_large_trader_legal_entity1()
          throws Exception {

    var request =
        AssignLargeTraderRequestCreate.builder()
            .largeTraderId(largeTrader.largeTraderId().get())
            .build();
    var result =
        sdk.personManagement()
            .assignLargeTraderLegalEntity(legalEntity.legalEntityId().get(), request);
    Assertions.assertEquals(200, result.statusCode());
  }

  @Order(3)
  @Test
  public void
      person_management_accounts_end_large_trader_legal_entity_end_large_trader_legal_entity1()
          throws Exception {

    var request =
        EndLargeTraderRequestCreate.builder().endReason(EndReason.EVENT_REASON_CREATED).build();
    var result = sdk.personManagement().endLargeTrader(legalEntity.legalEntityId().get(), request);
    Assertions.assertEquals(200, result.statusCode());
  }
}
