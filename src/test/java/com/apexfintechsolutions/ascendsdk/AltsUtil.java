package com.apexfintechsolutions.ascendsdk;

public class AltsUtil {
  /**
   * Dedicated alternative-investments test account. Created 2026-08 (after the Monark update that
   * routes SPV orders through Apex when the correspondent isn't registered for the SPV), funded,
   * and accredited. Pre-update accounts like the shared withdrawal account get their SPV orders
   * rejected by Monark.
   */
  public static final String ALTS_ACCOUNT_ID =
      envOr("ALTS_ACCOUNT_ID", "01M0DMB41SQR6SYZDQYZN3CJY2");

  /** An alternative order placed on ALTS_ACCOUNT_ID, used by get/settle tests. */
  public static final String ALTS_ORDER_ID = envOr("ALTS_ORDER_ID", "01M0DMH6QGPHZGFD4T1CJMABEM");

  private static String envOr(String key, String fallback) {
    String value = System.getenv(key);
    return value == null || value.isEmpty() ? fallback : value;
  }
}
