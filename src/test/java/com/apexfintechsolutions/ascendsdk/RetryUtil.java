package com.apexfintechsolutions.ascendsdk;

public class RetryUtil {
  public interface ThrowingSupplier<T> {
    T get() throws Exception;
  }

  /**
   * Retries op for a resource that was just created. A resource is occasionally not yet
   * mutable/queryable for a window observed up to ~18-20s after creation against the real UAT
   * environment, so this retries on any exception rather than pattern-matching a specific error.
   */
  public static <T> T retryOnTransientError(ThrowingSupplier<T> op) throws Exception {
    return retryOnTransientError(op, 20, 2000);
  }

  public static <T> T retryOnTransientError(ThrowingSupplier<T> op, int maxAttempts, long delayMs)
      throws Exception {
    Exception lastError = null;
    for (int attempt = 1; attempt <= maxAttempts; attempt++) {
      try {
        return op.get();
      } catch (Exception e) {
        lastError = e;
        if (attempt < maxAttempts) {
          Thread.sleep(delayMs);
        }
      }
    }
    throw lastError;
  }
}
