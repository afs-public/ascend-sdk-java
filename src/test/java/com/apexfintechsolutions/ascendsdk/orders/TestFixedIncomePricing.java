package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import java.util.*;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestFixedIncomePricing {
  private SDK sdk;
  private String accountId = null;
  private final String identifier = "912810SX7";

  @BeforeAll
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();
    accountId = AccountUtil.createEnrolledAccount(sdk).accountId().get();
  }

  @Test
  public void test_fixed_income_pricing_orders_preview_order_cost_preview_order_cost1()
      throws Exception {
    var req =
        OrderCostPreviewRequestCreate.builder()
            .assetType(OrderCostPreviewRequestCreateAssetType.FIXED_INCOME)
            .identifier(identifier)
            .identifierType(OrderCostPreviewRequestCreateIdentifierType.CUSIP)
            .parent("accounts/" + accountId)
            .quantity(new DecimalCreate(Optional.of("5")))
            .limitPrice(
                LimitPriceCreate.builder()
                    .price(new DecimalCreate(Optional.of("100")))
                    .type(LimitPriceCreateType.PERCENTAGE_OF_PAR)
                    .build())
            .build();
    var res =
        sdk.fixedIncomePricing()
            .previewOrderCost()
            .accountId(accountId)
            .orderCostPreviewRequestCreate(req)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }

  @Test
  public void test_fixed_income_pricing_orders_retrieve_quote_retrieve_quote1() throws Exception {
    var req =
        new RetrieveQuoteRequestCreate(
            RetrieveQuoteRequestCreateAssetType.FIXED_INCOME,
            identifier,
            RetrieveQuoteRequestCreateIdentifierType.CUSIP,
            "accounts/" + accountId);
    var res =
        sdk.fixedIncomePricing()
            .retrieveQuote()
            .accountId(accountId)
            .retrieveQuoteRequestCreate(req)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }

  @Test
  public void test_fixed_income_pricing_orders_retrieve_fixed_income_marks() throws Exception {
    var request =
        RetrieveFixedIncomeMarksRequestCreate.builder()
            .parent("correspondents/" + SdkUtil.getCorrespondentId())
            .securityIdentifiers(
                List.of(
                    RetrieveFixedIncomeMarksRequestSecurityIdentifiersCreate.builder()
                        .identifier(OrderUtil.getIdentifierCusp())
                        .identifierType(
                            RetrieveFixedIncomeMarksRequestSecurityIdentifiersCreateIdentifierType
                                .CUSIP)
                        .build()))
            .build();

    var result =
        sdk.fixedIncomePricing().retrieveFixedIncomeMarks(SdkUtil.getCorrespondentId(), request);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(result.statusCode(), 200);
  }
}
