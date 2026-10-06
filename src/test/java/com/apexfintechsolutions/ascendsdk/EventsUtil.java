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

  public static String getTestSubscriberId(SDK sdk) throws Exception {
    var res = sdk.subscriber().listPushSubscriptions().call();

    if (res.statusCode() == 200
        && res.listPushSubscriptionsResponse().isPresent()
        && res.listPushSubscriptionsResponse().get().pushSubscriptions().isPresent()
        && !res.listPushSubscriptionsResponse().get().pushSubscriptions().get().isEmpty()) {

      var subscriptions = res.listPushSubscriptionsResponse().get().pushSubscriptions().get();

      // The first listed subscription can be a freshly created one with no
      // delivery history (e.g. from a concurrently running suite's create
      // test); prefer a subscription that already has deliveries.
      for (var subscription : subscriptions) {
        if (subscription.subscriptionId().isEmpty()) {
          continue;
        }
        String subscriptionId = subscription.subscriptionId().get();
        try {
          if (!firstDeliveryId(sdk, subscriptionId).isEmpty()) {
            return subscriptionId;
          }
        } catch (Exception ignored) {
          // No deliveries (or transient error) -- try the next subscription.
        }
      }
      return subscriptions
          .get(0)
          .subscriptionId()
          .orElseThrow(() -> new Exception("Subscription ID not found"));

    } else {
      throw new Exception("Failed to get subscriber ID.");
    }
  }

  public static String getDeliveryID(SDK sdk) throws Exception {
    return firstDeliveryId(sdk, getTestSubscriberId(sdk));
  }

  static String firstDeliveryId(SDK sdk, String subscriptionId) throws Exception {
    var res =
        sdk.subscriber().listPushSubscriptionDeliveries().subscriptionId(subscriptionId).call();

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
