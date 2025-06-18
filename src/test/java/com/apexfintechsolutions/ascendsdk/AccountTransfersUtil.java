package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import java.util.*;

public class AccountTransfersUtil {
  private static final String WITHDRAWAL_ACCOUNT_ID = "01JHGTEPC6ZTAHCFRH2MD3VJJT";

  public static String createAccountTransferId(SDK sdk, String accountId) throws Exception {
    var accountNumber =
        sdk.accountCreation()
            .getAccount()
            .accountId(accountId)
            .call()
            .account()
            .get()
            .accountNumber();
    if (accountNumber.isEmpty()) {
      throw new Exception("Account number is null or empty");
    }
    // Fund Account
    var creditReq =
        TransfersCreditCreate.builder()
            .amount(DecimalCreate.builder().value("1000.00").build())
            .clientTransferId(UUID.randomUUID().toString())
            .description("Credit given as a promotion")
            .type(TransfersCreditCreateType.PROMOTIONAL)
            .build();
    var creditResponse =
        sdk.feesAndCredits()
            .createCredit()
            .accountId(accountId)
            .transfersCreditCreate(creditReq)
            .call();
    if (creditResponse.statusCode() != 200) {
      throw new Exception("Failed to credit account: " + creditResponse.statusCode());
    }

    Thread.sleep(5000);

    var req =
        TransferCreate.builder()
            .assets(
                List.of(
                    AssetCreate.builder()
                        .identifier("USD")
                        .position(
                            PositionCreate.builder()
                                .quantity(new DecimalCreate(Optional.of("1")))
                                .build())
                        .type(AssetCreateType.CURRENCY_CODE)
                        .build()))
            .deliverer(
                TransferAccountCreate.builder()
                    .externalAccount(
                        ExternalAccountCreate.builder()
                            .accountNumber(accountNumber.get())
                            .participantNumber("158")
                            .build())
                    .build())
            .build();
    var res =
        sdk.accountTransfers()
            .createTransfer()
            .correspondentId(SdkUtil.getCorrespondentId())
            .accountId(WITHDRAWAL_ACCOUNT_ID)
            .transferCreate(req)
            .call();
    if (res.statusCode() == 200) {
      return res.acatsTransfer()
          .get()
          .name()
          .get()
          .split("/")[res.acatsTransfer().get().name().get().split("/").length - 1];
    }
    throw new Exception("Failed to create account transfer");
  }
}
