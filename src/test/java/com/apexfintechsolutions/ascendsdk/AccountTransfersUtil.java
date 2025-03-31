package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import java.util.*;

public class AccountTransfersUtil {
  public static String createAccountTransferId(SDK sdk, String accountId) throws Exception {
    var req =
        TransferCreate.builder()
            .deliverer(
                TransferAccountCreate.builder()
                    .externalAccount(
                        ExternalAccountCreate.builder()
                            .accountNumber("1234567890")
                            .participantNumber("987")
                            .build())
                    .build())
            .build();
    var res =
        sdk.accountTransfers()
            .createTransfer()
            .correspondentId(SdkUtil.getCorrespondentId())
            .accountId(accountId)
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

  public static String getFixedSubscriberId() {
    return "01JJYZ16TVYZM6A6BDJ8RJRMTQ";
  }

  public static String getFixedDeliveryId() {
    return "01JJYZ7DBHP60A9QJ584MNS2ZC";
  }
}
