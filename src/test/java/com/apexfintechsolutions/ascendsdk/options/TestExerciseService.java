package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.operations.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestExerciseService {
  private SDK sdk;
  private String accountId;
  private String assetId;
  private String instructionId;

  // Exercise instructions (DO_NOT_EXERCISE, etc.) are only accepted between
  // 3:00:00 PM and 4:19:59 PM Central Time -- the real-world options exercise
  // cutoff window near market close. Outside that window every attempt 400s
  // with "outside allowed time window" regardless of which contract or
  // expiration date is used.
  private static boolean isOutsideExerciseSubmissionWindow() {
    ZonedDateTime now = ZonedDateTime.now(ZoneId.of("America/Chicago"));
    LocalTime windowStart = LocalTime.of(15, 0, 0);
    LocalTime windowEnd = LocalTime.of(16, 19, 59);
    LocalTime currentTime = now.toLocalTime();
    return currentTime.isBefore(windowStart) || currentTime.isAfter(windowEnd);
  }

  // Finds a usable equity option contract expiring today. DO_NOT_EXERCISE
  // instructions can only be submitted on an option's expiration date, so a
  // fixed/hardcoded asset_id only works on the one calendar day it happens to
  // expire. Looking one up dynamically each run stays valid indefinitely
  // instead of breaking again the day after whatever asset was hardcoded.
  // Restricted to EQUITY options -- 0DTE index options (XSP, APXSIM, etc.)
  // expire daily but don't support exercise instructions at all ("exercise
  // instructions are not supported for index options"). Equity options only
  // expire on specific days (weekly/monthly), so none may be expiring today;
  // the caller should skip in that case rather than treat it as a failure.
  private static String findOptionExpiringToday(SDK sdk) throws Exception {
    LocalDate today = LocalDate.now(ZoneId.of("America/Chicago"));
    Optional<String> pageToken = Optional.empty();
    // Filter server-side: without the expiration/usable constraints this
    // walks the entire option universe page by page on no-match days.
    String filter =
        String.format(
            "type == \"OPTION\" && usable && option.expiration_date == date(\"%s\")", today);

    while (true) {
      AssetsListAssets1Response res =
          sdk.assets()
              .listAssets(
                  Optional.empty(),
                  Optional.of(200),
                  pageToken,
                  Optional.of(filter),
                  Optional.empty());

      if (res.listAssetsResponse().isEmpty()) {
        return null;
      }
      ListAssetsResponse listAssetsResponse = res.listAssetsResponse().get();
      List<Asset> assets = listAssetsResponse.assets().orElse(List.of());

      for (Asset asset : assets) {
        if (asset.assetId().isEmpty() || !asset.option().isPresent()) {
          continue;
        }
        Option option = asset.option().get();
        if (option.optionType().isPresent() && option.optionType().get() == OptionType.EQUITY) {
          return asset.assetId().get();
        }
      }

      pageToken = listAssetsResponse.nextPageToken();
      if (pageToken.isEmpty() || pageToken.get().isEmpty()) {
        return null;
      }
    }
  }

  @BeforeAll
  public void setup() throws Exception {
    Assumptions.assumeFalse(
        isOutsideExerciseSubmissionWindow(),
        "Exercise instructions are only accepted 3:00-4:19:59 PM Central Time");

    sdk = SdkUtil.getSdk();

    assetId = findOptionExpiringToday(sdk);
    Assumptions.assumeTrue(assetId != null, "No equity option contract expiring today was found");

    accountId = AccountUtil.createEnrolledAccount(sdk).accountId().get();
  }

  @Test
  @Order(1)
  public void test_exercise_service_create_option_instruction() throws Exception {
    var optionInstruction =
        OptionInstructionCreate.builder()
            .accountId(accountId)
            .identifier(assetId)
            .identifierType(OptionInstructionCreateIdentifierType.ASSET_ID)
            .quantity(DecimalCreate.builder().value("1").build())
            .type(OptionInstructionCreateType.DO_NOT_EXERCISE)
            .build();

    var res =
        sdk.optionInstructions()
            .createOptionInstruction()
            .accountId(accountId)
            .assetId(assetId)
            .optionInstructionCreate(optionInstruction)
            .call();

    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
    Assertions.assertTrue(res.optionInstruction().isPresent());
    instructionId = res.optionInstruction().get().instructionId().get();
    Assertions.assertNotNull(instructionId);
  }

  @Test
  @Order(2)
  public void test_exercise_service_get_option_instruction() throws Exception {
    var res =
        sdk.optionInstructions()
            .getOptionInstruction()
            .accountId(accountId)
            .assetId(assetId)
            .instructionId(instructionId)
            .call();

    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
    Assertions.assertTrue(res.optionInstruction().isPresent());
  }

  @Test
  @Order(3)
  public void test_exercise_service_list_option_instructions() throws Exception {
    var request =
        ExerciseServiceListOptionInstructionsRequest.builder()
            .accountId(accountId)
            .assetId(assetId)
            .build();

    var res = sdk.optionInstructions().listOptionInstructions().request(request).call();

    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
    Assertions.assertTrue(res.listOptionInstructionsResponse().isPresent());
  }

  @Test
  @Order(4)
  public void test_exercise_service_cancel_option_instruction() throws Exception {
    var cancelRequest =
        CancelOptionInstructionRequestCreate.builder()
            .name("accounts/" + accountId + "/assets/" + assetId + "/instructions/" + instructionId)
            .build();

    var res =
        sdk.optionInstructions()
            .cancelOptionInstruction()
            .accountId(accountId)
            .assetId(assetId)
            .instructionId(instructionId)
            .cancelOptionInstructionRequestCreate(cancelRequest)
            .call();

    Assertions.assertNotNull(res);
    Assertions.assertEquals(200, res.statusCode());
    Assertions.assertTrue(res.optionInstruction().isPresent());
  }
}
