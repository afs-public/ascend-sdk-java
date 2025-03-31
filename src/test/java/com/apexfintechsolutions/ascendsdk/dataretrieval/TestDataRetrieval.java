package com.apexfintechsolutions.ascendsdk;

import org.junit.jupiter.api.*;

public class TestDataRetrieval {
  @Test
  public void test_data_retrieval_snapshots_list_snapshots_list_snapshots1() throws Exception {
    var sdk = SdkUtil.getSdk();
    var res = sdk.dataRetrieval().listSnapshots().call();
    Assertions.assertEquals(200, res.statusCode());
  }
}
