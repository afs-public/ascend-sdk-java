package com.apexfintechsolutions.ascendsdk;

import org.junit.jupiter.api.*;

public class TestDataRetrieval {
  @Test
  public void test_data_retrieval_snapshots_list_snapshots_list_snapshots1() throws Exception {
    var sdk = SdkUtil.getSdk();
    // An unfiltered list forces the service onto its slow GCS/BQ scan path,
    // which exceeds the 55s gateway timeout (504) in UAT; a snapshot_type
    // filter keeps it on the fast path (~3s).
    var res =
        sdk.dataRetrieval().listSnapshots().filter("snapshot_type==\"daily_accounts\"").call();
    Assertions.assertEquals(200, res.statusCode());
  }
}
