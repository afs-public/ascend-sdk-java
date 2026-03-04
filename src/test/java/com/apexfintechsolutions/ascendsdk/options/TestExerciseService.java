package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.operations.*;
import org.junit.jupiter.api.Assertions;
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
  private String assetId = "9098656";
  private String instructionId;

  @BeforeAll
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();
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
