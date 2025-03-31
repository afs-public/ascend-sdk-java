package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestAssets {
  private SDK sdk;

  @BeforeAll
  public void setup() {
    sdk = SdkUtil.getSdk();
  }

  @Test
  public void assets_assets_list_assets_1_assets_list_assets_1() throws Exception {
    var res = sdk.assets().listAssets().call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }

  @Test
  public void assets_assets_get_asset_assets_get_asset1() throws Exception {
    var response = sdk.assets().getAsset("8395");
    Assertions.assertNotNull(response);
    Assertions.assertEquals(response.statusCode(), 200);
  }

  @Test
  public void assets_assets_list_assets_by_correspondent_assets_list_assets_by_correspondent1()
      throws Exception {
    var response = sdk.assets().listAssetsCorrespondent(SdkUtil.getCorrespondentId());
    Assertions.assertEquals(200, response.statusCode());
  }

  @Test
  public void assets_assets_get_asset_by_correspondent_assets_get_asset_by_correspondent1()
      throws Exception {
    var result = sdk.assets().getAssetCorrespondent(SdkUtil.getCorrespondentId(), "8395");
  }
}
