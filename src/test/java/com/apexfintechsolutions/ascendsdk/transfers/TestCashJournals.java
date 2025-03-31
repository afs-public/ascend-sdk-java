package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import java.util.*;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestCashJournals {
  private SDK sdk;

  private String cashJournalId;

  private String deceasedAccountId;

  private String withdrawalAccountId;

  @BeforeAll
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();
    deceasedAccountId = TransfersUtil.getDeceasedAccountId();
    withdrawalAccountId = TransfersUtil.getWithdrawalAccountId();
    cashJournalId = TransfersUtil.createCashJournalId(sdk, deceasedAccountId);
  }

  @Test
  public void test_journals_transfers_create_cash_journal_create_cash_journal1() throws Exception {
    Assertions.assertNotNull(cashJournalId);
  }

  @Test
  public void test_journals_transfers_get_cash_journal_get_cash_journal1() throws Exception {
    var res = sdk.journals().getCashJournal().cashJournalId(cashJournalId).call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }

  @Test
  public void test_journals_transfers_cancel_cash_journal_cancel_cash_journal1() throws Exception {
    var req =
        CancelCashJournalRequestCreate.builder()
            .name("cashJournals/" + cashJournalId)
            .reason("Test cancel cash journal")
            .build();

    var res =
        sdk.journals()
            .cancelCashJournal()
            .cancelCashJournalRequestCreate(req)
            .cashJournalId(cashJournalId)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }

  @Test
  public void test_journals_transfers_retrieve_cash_journal_party_retrieve_cash_journal_party1()
      throws Exception {
    var req =
        CheckPartyTypeRequestCreate.builder()
            .destinationAccount("accounts/" + deceasedAccountId)
            .sourceAccount("accounts/" + withdrawalAccountId)
            .build();
    var res = sdk.journals().checkPartyType().request(req).call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }

  @Test
  public void
      test_journals_transfers_retrieve_cash_journal_constraints_retrieve_cash_journal_constraints1()
          throws Exception {
    var req =
        RetrieveCashJournalConstraintsRequestCreate.builder()
            .destinationAccount("accounts/" + deceasedAccountId)
            .sourceAccount("accounts/" + withdrawalAccountId)
            .build();
    var res = sdk.journals().retrieveCashJournalConstraints().request(req).call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
  }
}
