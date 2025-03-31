package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestLedger {
  private String getEntryId() throws Exception {
    var sdk = SdkUtil.getSdk();
    Assertions.assertNotNull(sdk);
    var res = sdk.ledger().listEntries().accountId(SdkUtil.getWithdrawalAccountId()).call();
    if (res.statusCode() == 200) {
      return res.listEntriesResponse().get().entries().get().get(0).entryId().get();
    } else {
      return null;
    }
  }

  private String getActivityId() throws Exception {
    var sdk = SdkUtil.getSdk();
    Assertions.assertNotNull(sdk);
    var res = sdk.ledger().listActivities().accountId(SdkUtil.getWithdrawalAccountId()).call();
    if (res.statusCode() == 200) {
      return res.listActivitiesResponse().get().activities().get().get(0).activityId().get();
    } else {
      return null;
    }
  }

  @Test
  public void test_ledger_ledger_list_entries_ledger_list_entries1() throws Exception {
    String entryId = getEntryId();
    Assertions.assertNotNull(entryId);
  }

  @Test
  public void test_ledger_ledger_list_activities_ledger_list_activities1() throws Exception {
    String activityId = getActivityId();
    Assertions.assertNotNull(activityId);
  }

  @Test
  public void test_ledger_ledger_list_positions_ledger_list_positions1() throws Exception {
    var sdk = SdkUtil.getSdk();
    Assertions.assertNotNull(sdk);

    var response = sdk.ledger().listPositions().accountId(SdkUtil.getWithdrawalAccountId()).call();
    Assertions.assertNotNull(response);
  }

  @Test
  public void test_ledger_ledger_get_activity_ledger_get_activity1() throws Exception {
    var sdk = SdkUtil.getSdk();
    Assertions.assertNotNull(sdk);

    var response =
        sdk.ledger()
            .getActivity()
            .accountId(SdkUtil.getWithdrawalAccountId())
            .activityId(getActivityId())
            .call();
    Assertions.assertEquals(200, response.statusCode());
  }

  @Test
  public void test_ledger_ledger_get_entry_ledger_get_entry1() throws Exception {
    var sdk = SdkUtil.getSdk();
    Assertions.assertNotNull(sdk);

    var response =
        sdk.ledger()
            .getEntry()
            .accountId(SdkUtil.getWithdrawalAccountId())
            .entryId(getEntryId())
            .call();
    Assertions.assertEquals(200, response.statusCode());
  }
}
