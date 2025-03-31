package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import java.util.*;
import java.util.Date;

public class EventsUtil {
  public static String getSubscriberId(SDK sdk) throws Exception {
    var now = new Date();
    var req =
        PushSubscriptionCreate.builder()
            .correspondentId(SdkUtil.getCorrespondentId())
            .displayName(now.toString())
            .eventTypes(List.of("position.v1.updated"))
            .httpCallback(
                HttpPushCallbackCreate.builder()
                    .clientSecret("mysecretkey1")
                    .timeoutSeconds(30)
                    .url("https://brokercheck.finra.org/")
                    .build())
            .build();

    var res = sdk.subscriber().createPushSubscription(req);
    if (res.statusCode() != 200) {
      throw new Exception("Failed to create push subscription");
    }
    return res.pushSubscription().get().subscriptionId().get();
  }

  public static String getMessageId(SDK sdk) throws Exception {
    var res = sdk.reader().listEventMessages().call();
    if (res.statusCode() == 200
        && !res.listEventMessagesResponse().get().eventMessages().get().isEmpty()) {
      return res.listEventMessagesResponse().get().eventMessages().get().get(0).messageId().get();
    } else {
      throw new Exception("Failed to get message ID");
    }
  }

  public static String getFixedSubscriberId() {
    return "01JJYZ16TVYZM6A6BDJ8RJRMTQ";
  }

  public static String getDeliveryID(SDK sdk) throws Exception {
    var res =
        sdk.subscriber()
            .listPushSubscriptionDeliveries()
            .subscriptionId(getFixedSubscriberId())
            .call();

    if (res.statusCode() == 200
        && !res.listPushSubscriptionDeliveriesResponse()
            .get()
            .pushSubscriptionDeliveries()
            .get()
            .isEmpty()) {
      return res.listPushSubscriptionDeliveriesResponse()
          .get()
          .pushSubscriptionDeliveries()
          .get()
          .get(0)
          .deliveryId()
          .get();
    } else {
      throw new Exception("Failed to get delivery ID");
    }
  }
}
