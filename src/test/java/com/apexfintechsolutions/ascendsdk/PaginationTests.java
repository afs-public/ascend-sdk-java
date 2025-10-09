package com.apexfintechsolutions.ascendsdk;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.apexfintechsolutions.ascendsdk.models.operations.AssetsListAssets1Response;
import org.junit.jupiter.api.Test;

public class PaginationTests {

  @Test
  public void testPagination() throws Exception {
    SDK sdk = SdkUtil.getSdk();

    // Test pagination by iterating through the first few pages
    int pageCount = 0;
    int maxPages = 3; // Limit to the first 3 pages for testing to avoid long-running tests

    // Use callAsIterable() to iterate through pages
    Iterable<AssetsListAssets1Response> pages =
        sdk.assets().listAssets().parent("").pageSize(25).pageToken("").filter("").callAsIterable();

    // Iterate through pages until we reach the limit or no more pages exist
    for (AssetsListAssets1Response page : pages) {
      if (pageCount >= maxPages) {
        break;
      }

      System.out.printf("Processing page %d%n", pageCount + 1);

      // Verify current page has valid response structure
      assertNotNull(page, "Page should not be null");
      assertEquals(200, page.statusCode(), "Should receive successful HTTP 200 response");
      assertNotNull(
          page.listAssetsResponse(),
          String.format("Page %d should have valid assets response", pageCount + 1));

      pageCount++;
    }

    System.out.println("Pagination test completed successfully.");
  }

  @Test
  public void testEmptyInitialResponseHandling() throws Exception {
    SDK sdk = SdkUtil.getSdk();

    // Use a filter that should return no results to test empty response handling
    // This filter looks for assets with an impossible condition
    String emptyFilter = "name == \"NONEXISTENT_ASSET_12345_IMPOSSIBLE\"";

    // Use callAsIterable() to iterate through pages
    Iterable<AssetsListAssets1Response> pages =
        sdk.assets()
            .listAssets()
            .parent("")
            .pageSize(25)
            .pageToken("")
            .filter(emptyFilter)
            .callAsIterable();

    // Verify that iteration handles empty responses correctly
    for (AssetsListAssets1Response page : pages) {
      // Verify the response structure is still valid
      assertNotNull(page, "Page should not be null");
      assertEquals(200, page.statusCode(), "Should receive successful HTTP 200 response");
      assertNotNull(page.listAssetsResponse(), "Assets response should be present");

      // Check if the current response has assets
      if (page.listAssetsResponse().isPresent()
          && page.listAssetsResponse().get().assets().isPresent()
          && !page.listAssetsResponse().get().assets().get().isEmpty()) {
        fail("Expected no assets in response, but found some");
      }
    }

    System.out.println("Empty initial response handling test completed successfully.");
  }

  @Test
  public void testPaginationWithSymbolFilters() throws Exception {
    SDK sdk = SdkUtil.getSdk();

    // Test filter by symbol
    String equityFilter = "symbol == \"WTG\"";

    // Count pages with an equity filter
    int equityPages = 0;
    int equityAssets = 0;
    int maxPages = 3;

    // Use callAsIterable() to iterate through pages
    Iterable<AssetsListAssets1Response> pages =
        sdk.assets()
            .listAssets()
            .parent("")
            .pageSize(25)
            .pageToken("")
            .filter(equityFilter)
            .callAsIterable();

    for (AssetsListAssets1Response page : pages) {
      if (equityPages >= maxPages) {
        break;
      }

      equityPages++;

      assertEquals(200, page.statusCode(), "Should receive successful HTTP 200 response");
      assertNotNull(page.listAssetsResponse(), "Assets response should be present");

      if (page.listAssetsResponse().isPresent()
          && page.listAssetsResponse().get().assets().isPresent()
          && !page.listAssetsResponse().get().assets().get().isEmpty()) {
        int currentCount = page.listAssetsResponse().get().assets().get().size();
        equityAssets += currentCount;
        System.out.printf("Symbol filter - Page %d: %d assets%n", equityPages, currentCount);

        // Verify all returned assets match the filter (if any)
        page.listAssetsResponse()
            .get()
            .assets()
            .get()
            .forEach(
                asset -> {
                  if (asset.symbol().isPresent()) {
                    assertEquals(
                        "WTG",
                        asset.symbol().get(),
                        String.format("Symbol %s doesn't match filter", asset.symbol().get()));
                  }
                });
      }
    }

    System.out.printf(
        "Filter test completed. Symbol assets found: %d across %d pages%n",
        equityAssets, equityPages);
  }

  @Test
  public void testPaginationWithUsableFilter() throws Exception {
    SDK sdk = SdkUtil.getSdk();

    // Test with a usable filter
    String activeFilter = "usable == true";

    int pageCount = 0;
    int totalActiveAssets = 0;
    int maxPages = 3;

    // Use callAsIterable() to iterate through pages
    Iterable<AssetsListAssets1Response> pages =
        sdk.assets()
            .listAssets()
            .parent("")
            .pageSize(25)
            .pageToken("")
            .filter(activeFilter)
            .callAsIterable();

    for (AssetsListAssets1Response page : pages) {
      if (pageCount >= maxPages) {
        break;
      }

      pageCount++;

      assertEquals(200, page.statusCode(), "Should receive successful HTTP 200 response");
      assertNotNull(page.listAssetsResponse(), "Assets response should be present");

      if (page.listAssetsResponse().isPresent()
          && page.listAssetsResponse().get().assets().isPresent()
          && !page.listAssetsResponse().get().assets().get().isEmpty()) {
        int pageAssets = page.listAssetsResponse().get().assets().get().size();
        totalActiveAssets += pageAssets;
        System.out.printf("Usable filter - Page %d: %d assets%n", pageCount, pageAssets);

        // Verify all returned assets match the filter
        page.listAssetsResponse()
            .get()
            .assets()
            .get()
            .forEach(
                asset -> {
                  if (asset.usable().isPresent()) {
                    assertTrue(
                        asset.usable().get(),
                        String.format(
                            "Asset usable status %s doesn't match filter", asset.usable().get()));
                  }
                });
      }
    }

    System.out.printf(
        "Status filter test completed. Active assets: %d across %d pages%n",
        totalActiveAssets, pageCount);
  }

  @Test
  public void testComplexFilterPagination() throws Exception {
    SDK sdk = SdkUtil.getSdk();

    // Test with a complex filter combining multiple conditions
    String complexFilter = "type == \"EQUITY\" && usable == true";

    int pageCount = 0;
    int matchingAssets = 0;
    int maxPages = 3;

    // Use callAsIterable() to iterate through pages
    Iterable<AssetsListAssets1Response> pages =
        sdk.assets()
            .listAssets()
            .parent("")
            .pageSize(25)
            .pageToken("")
            .filter(complexFilter)
            .callAsIterable();

    for (AssetsListAssets1Response page : pages) {
      if (pageCount >= maxPages) {
        break;
      }

      pageCount++;

      assertEquals(200, page.statusCode(), "Should receive successful HTTP 200 response");
      assertNotNull(page.listAssetsResponse(), "Assets response should be present");

      if (page.listAssetsResponse().isPresent()
          && page.listAssetsResponse().get().assets().isPresent()
          && !page.listAssetsResponse().get().assets().get().isEmpty()) {
        int pageAssets = page.listAssetsResponse().get().assets().get().size();
        matchingAssets += pageAssets;
        System.out.printf("Complex filter - Page %d: %d assets%n", pageCount, pageAssets);

        // Verify assets match both conditions
        page.listAssetsResponse()
            .get()
            .assets()
            .get()
            .forEach(
                asset -> {
                  if (asset.type().isPresent()) {
                    assertEquals(
                        "EQUITY",
                        asset.type().get().value(),
                        String.format("Asset type %s doesn't match filter", asset.type().get()));
                  }
                  if (asset.usable().isPresent()) {
                    assertTrue(
                        asset.usable().get(),
                        String.format(
                            "Asset usable status %s doesn't match filter", asset.usable().get()));
                  }
                });
      }
    }

    System.out.printf(
        "Complex filter test completed. Matching assets: %d across %d pages%n",
        matchingAssets, pageCount);
  }
}
