package com.apexfintechsolutions.ascendsdk;

import com.apexfintechsolutions.ascendsdk.models.components.*;
import java.util.List;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestSubscriber {
  private SDK sdk;
  private String subscriberId = "";
  private String deliveryID = "";

  @BeforeAll
  public void setup() throws Exception {
    sdk = SdkUtil.getSdk();
    subscriberId = EventsUtil.getSubscriberId(sdk);
    if (subscriberId == null || subscriberId.isEmpty()) {
      throw new Exception("Subscriber ID not found");
    }
    deliveryID = EventsUtil.getDeliveryID(sdk);
    if (deliveryID == null || deliveryID.isEmpty()) {
      throw new Exception("Delivery ID not found");
    }
  }

  @Test
  public void test_subscriber_events_create_push_subscription_create_push_subscription1()
      throws Exception {
    Assertions.assertNotNull(subscriberId);
  }

  @Test
  @Order(1)
  public void test_subscriber_events_get_push_subscription_get_push_subscription1()
      throws Exception {
    var res = sdk.subscriber().getPushSubscription().subscriptionId(subscriberId).call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }

  @Test
  @Order(2)
  public void test_subscriber_events_update_push_subscription_update_push_subscription1()
      throws Exception {
    var update = new PushSubscriptionUpdate().withEventTypes(List.of("position.v1.updated"));
    var res =
        sdk.subscriber()
            .updatePushSubscription()
            .subscriptionId(subscriberId)
            .pushSubscriptionUpdate(update)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }

  @Test
  @Order(3)
  public void
      test_subscriber_events_list_subscription_event_deliveries_list_subscription_event_deliveries1()
          throws Exception {
    Assertions.assertNotNull(deliveryID);
  }

  @Test
  public void
      test_subscriber_events_get_subscription_event_delivery_get_subscription_event_delivery1()
          throws Exception {
    var res =
        sdk.subscriber()
            .getPushSubscriptionDelivery()
            .subscriptionId(EventsUtil.getFixedSubscriberId())
            .deliveryId(deliveryID)
            .call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }

  @Test
  @Order(4)
  public void test_subscriber_events_delete_push_subscription_delete_push_subscription1()
      throws Exception {
    Thread.sleep(25000);
    var res = sdk.subscriber().deletePushSubscription().subscriptionId(subscriberId).call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }

  @Test
  public void test_subscriber_subscriber_list_push_subscriptions_list_push_subscriptions1()
      throws Exception {
    var res = sdk.subscriber().listPushSubscriptions().call();
    Assertions.assertNotNull(res);
    Assertions.assertEquals(res.statusCode(), 200);
  }
}
