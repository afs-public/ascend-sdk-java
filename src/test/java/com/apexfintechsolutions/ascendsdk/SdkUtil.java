package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;

public class SdkUtil {
  private static SDK sdkInstance = null;

  public static SDK getSdk() {
    if (sdkInstance != null) {
      return sdkInstance;
    }

    String baseURL = System.getenv("BASE_URL");
    String apiKey = System.getenv("API_KEY");
    String privateKey = System.getenv("SERVICE_ACCOUNT_CREDS_PRIVATE_KEY");
    String name = System.getenv("SERVICE_ACCOUNT_CREDS_NAME");
    String organization = System.getenv("SERVICE_ACCOUNT_CREDS_ORGANIZATION");

    SDK sdk =
        SDK.builder()
            .serverURL(baseURL)
            .security(
                Security.builder()
                    .apiKey(apiKey)
                    .serviceAccountCreds(
                        ServiceAccountCreds.builder()
                            .privateKey(privateKey)
                            .name(name)
                            .organization(organization)
                            .type("serviceAccount")
                            .build())
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
