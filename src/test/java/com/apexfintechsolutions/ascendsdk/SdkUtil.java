package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.utils.ApexSecuritySource;

public class SdkUtil {
  private static SDK sdkInstance = null;

  public static SDK getSdk() {
    if (sdkInstance != null) {
      return sdkInstance;
    }

    String baseURL = System.getenv("BASE_URL");
    String apiKey = System.getenv("API_KEY");
    String ascendJson = System.getenv("ASCEND_CREDS_JSON");

    SDK sdk =
        SDK.builder()
            .serverURL(baseURL)
            .securitySource(
                ApexSecuritySource.builder()
                    .ascendCredsJson(ascendJson)
                    .apiKey(apiKey)
                    .serverUrl(baseURL)
                    .build())
            .build();

    sdkInstance = sdk;
    return sdkInstance;
  }

  public static String getWithdrawalAccountId() {
    return "01JHGTEPC6ZTAHCFRH2MD3VJJT";
  }

  public static String getCorrespondentId() {
    String correspondent = System.getenv("CORRESPONDENT_ID");
    if (correspondent == null || correspondent.isEmpty()) {
      throw new IllegalArgumentException("CORRESPONDENT_ID environment variable is not set");
    }
    return correspondent;
  }

  public static String getAccountGroupId() {
    String accountGroupId = System.getenv("ACCOUNT_GROUP_ID");
    if (accountGroupId == null || accountGroupId.isEmpty()) {
      throw new IllegalArgumentException("ACCOUNT_GROUP_ID environment variable is not set");
    }
    return accountGroupId;
  }
}
