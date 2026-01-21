package com.apexfintechsolutions.ascendsdk;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

import com.apexfintechsolutions.ascendsdk.models.components.Account;
import com.apexfintechsolutions.ascendsdk.models.components.CancelPositionJournalRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.DecimalCreate;
import com.apexfintechsolutions.ascendsdk.models.components.ForceApprovePositionJournalRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.ForceRejectPositionJournalRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.components.IdentifierType;
import com.apexfintechsolutions.ascendsdk.models.components.PositionJournalCreate;
import com.apexfintechsolutions.ascendsdk.models.components.PositionJournalCreateType;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestPositionsJournals {
  private SDK sdk;
  private Account sourceAccount;
  private Account destinationAccount;
  private String positionJournalId;
  private boolean isJournalsHours = false;

  @BeforeAll
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();
    sourceAccount = AccountUtil.createEnrolledAccount(sdk);
    destinationAccount = AccountUtil.createEnrolledAccount(sdk);

    ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.of("America/Chicago"));
    ZonedDateTime morning = currentTime.withHour(6).withMinute(0).withSecond(0).withNano(0);
    ZonedDateTime afternoon = currentTime.withHour(19).withMinute(0).withSecond(0).withNano(0);
    if (morning.isBefore(currentTime) && currentTime.isBefore(afternoon)) {
      isJournalsHours = true;
    }
  }

  @Test
  @Order(1)
  public void test_position_journals_create_position_journal() throws Exception {
    var request =
        PositionJournalCreate.builder()
            .clientTransferId(UUID.randomUUID().toString())
            .destinationAccount("accounts/" + destinationAccount.accountId().get())
            .identifier("AAPL")
            .identifierType(IdentifierType.SYMBOL)
            .quantity(DecimalCreate.builder().value("1.0").build())
            .sourceAccount("accounts/" + sourceAccount.accountId().get())
            .type(PositionJournalCreateType.REWARD)
            .fairMarketValue(DecimalCreate.builder().value("150.00").build())
            .description("Stock reward for testing")
            .build();

    var res = sdk.positionJournals().createPositionJournal(request);
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
    Assertions.assertTrue(res.positionJournal().isPresent());
    Assertions.assertTrue(res.positionJournal().get().name().isPresent());

    String name = res.positionJournal().get().name().get();
    String[] parts = name.split("/");
    positionJournalId = parts[parts.length - 1];
  }

  @Test
  @Order(2)
  public void test_position_journals_get_position_journal() throws Exception {
    Assertions.assertNotNull(
        positionJournalId, "Position journal ID should be set from create test");

    var res = sdk.positionJournals().getPositionJournal(positionJournalId);
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
    Assertions.assertTrue(res.positionJournal().isPresent());
  }

  @Test
  @Order(3)
  public void test_position_journals_cancel_position_journal() throws Exception {
    Assertions.assertNotNull(
        positionJournalId, "Position journal ID should be set from create test");

    var request =
        CancelPositionJournalRequestCreate.builder()
            .name("positionJournals/" + positionJournalId)
            .reason("Cancel position journal for testing")
            .build();

    var res =
        sdk.positionJournals()
            .cancelPositionJournal()
            .positionJournalId(positionJournalId)
            .cancelPositionJournalRequestCreate(request)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
    Assertions.assertTrue(res.positionJournal().isPresent());
  }

  @Test
  @Order(4)
  public void test_test_simulation_force_approve_position_journal() throws Exception {
    assumeTrue(
        isJournalsHours,
        "Skipping Endpoint Test: Force Approve Position Journal. Requires current time to be"
            + " between 6:00 AM CT and 7:00 PM CT");

    var createRequest =
        PositionJournalCreate.builder()
            .clientTransferId(UUID.randomUUID().toString())
            .destinationAccount("accounts/" + destinationAccount.accountId().get())
            .identifier("AAPL")
            .identifierType(IdentifierType.SYMBOL)
            .quantity(DecimalCreate.builder().value("1.0").build())
            .sourceAccount("accounts/" + sourceAccount.accountId().get())
            .type(PositionJournalCreateType.REWARD)
            .fairMarketValue(DecimalCreate.builder().value("150.00").build())
            .description("Stock reward for testing")
            .build();

    var createRes = sdk.positionJournals().createPositionJournal(createRequest);
    Assertions.assertTrue(createRes.positionJournal().isPresent());
    Assertions.assertTrue(createRes.positionJournal().get().name().isPresent());

    String name = createRes.positionJournal().get().name().get();
    String[] parts = name.split("/");
    String journalId = parts[parts.length - 1];

    var forceApproveRequest =
        ForceApprovePositionJournalRequestCreate.builder()
            .name("positionJournals/" + journalId)
            .build();

    try {
      var res =
          sdk.testSimulation()
              .forceApprovePositionJournal()
              .positionJournalId(journalId)
              .forceApprovePositionJournalRequestCreate(forceApproveRequest)
              .call();
      Assertions.assertNotNull(res);
      Assertions.assertEquals(200, res.statusCode());
      Assertions.assertTrue(res.positionJournal().isPresent());
    } catch (Status status) {
      Assertions.assertEquals(3, status.code().get());
      Assertions.assertTrue(
          status.message().get().toLowerCase().contains("that does not need review"));
    }
  }

  @Test
  @Order(5)
  public void test_test_simulation_force_reject_position_journal() throws Exception {
    assumeTrue(
        isJournalsHours,
        "Skipping Endpoint Test: Force Reject Position Journal. Requires current time to be between"
            + " 6:00 AM CT and 7:00 PM CT");

    var createRequest =
        PositionJournalCreate.builder()
            .clientTransferId(UUID.randomUUID().toString())
            .destinationAccount("accounts/" + destinationAccount.accountId().get())
            .identifier("AAPL")
            .identifierType(IdentifierType.SYMBOL)
            .quantity(DecimalCreate.builder().value("1.0").build())
            .sourceAccount("accounts/" + sourceAccount.accountId().get())
            .type(PositionJournalCreateType.REWARD)
            .fairMarketValue(DecimalCreate.builder().value("150.00").build())
            .description("Stock reward for testing")
            .build();

    var createRes = sdk.positionJournals().createPositionJournal(createRequest);
    Assertions.assertTrue(createRes.positionJournal().isPresent());
    Assertions.assertTrue(createRes.positionJournal().get().name().isPresent());

    String name = createRes.positionJournal().get().name().get();
    String[] parts = name.split("/");
    String journalId = parts[parts.length - 1];

    var forceRejectRequest =
        ForceRejectPositionJournalRequestCreate.builder()
            .name("positionJournals/" + journalId)
            .reason("Force reject for testing")
            .build();

    try {
      var res =
          sdk.testSimulation()
              .forceRejectPositionJournal()
              .positionJournalId(journalId)
              .forceRejectPositionJournalRequestCreate(forceRejectRequest)
              .call();
      Assertions.assertNotNull(res);
      Assertions.assertEquals(200, res.statusCode());
      Assertions.assertTrue(res.positionJournal().isPresent());
    } catch (Status status) {
      Assertions.assertEquals(3, status.code().get());
    }
  }
}
