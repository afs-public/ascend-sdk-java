# Subscriber
(*subscriber()*)

## Overview

### Available Operations

* [createPushSubscription](#createpushsubscription) - Create Push Subscription
* [listPushSubscriptions](#listpushsubscriptions) - List Push Subscriptions
* [getPushSubscription](#getpushsubscription) - Get Push Subscription
* [updatePushSubscription](#updatepushsubscription) - Update Subscription
* [deletePushSubscription](#deletepushsubscription) - Delete Subscription
* [getPushSubscriptionDelivery](#getpushsubscriptiondelivery) - Get Subscription Event Delivery
* [listPushSubscriptionDeliveries](#listpushsubscriptiondeliveries) - List Push Subscription Event Deliveries

## createPushSubscription

Creates a new push subscription for event notifications.

### Example Usage

<!-- UsageSnippet language="java" operationID="Subscriber_CreatePushSubscription" method="post" path="/events/v1/subscriptions" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.SubscriberCreatePushSubscriptionResponse;
import java.lang.Exception;
import java.util.List;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .apiKey("ABCDEFGHIJ0123456789abcdefghij0123456789")
                    .serviceAccountCreds(ServiceAccountCreds.builder()
                        .privateKey("-----BEGIN PRIVATE KEY--{OMITTED FOR BREVITY}")
                        .name("FinFirm")
                        .organization("correspondents/00000000-0000-0000-0000-000000000000")
                        .type("serviceAccount")
                        .build())
                    .build())
            .build();

        PushSubscriptionCreate req = PushSubscriptionCreate.builder()
                .displayName("This is an example HTTP configuration.")
                .eventTypes(List.of(
                    "position.v1.updated"))
                .build();

        SubscriberCreatePushSubscriptionResponse res = sdk.subscriber().createPushSubscription()
                .request(req)
                .call();

        if (res.pushSubscription().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                               | Type                                                                    | Required                                                                | Description                                                             |
| ----------------------------------------------------------------------- | ----------------------------------------------------------------------- | ----------------------------------------------------------------------- | ----------------------------------------------------------------------- |
| `request`                                                               | [PushSubscriptionCreate](../../models/shared/PushSubscriptionCreate.md) | :heavy_check_mark:                                                      | The request object to use for the request.                              |

### Response

**[SubscriberCreatePushSubscriptionResponse](../../models/operations/SubscriberCreatePushSubscriptionResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 409     | application/json       |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## listPushSubscriptions

Gets a list of push subscriptions.

### Example Usage

<!-- UsageSnippet language="java" operationID="Subscriber_ListPushSubscriptions" method="get" path="/events/v1/subscriptions" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.SubscriberListPushSubscriptionsResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .apiKey("ABCDEFGHIJ0123456789abcdefghij0123456789")
                    .serviceAccountCreds(ServiceAccountCreds.builder()
                        .privateKey("-----BEGIN PRIVATE KEY--{OMITTED FOR BREVITY}")
                        .name("FinFirm")
                        .organization("correspondents/00000000-0000-0000-0000-000000000000")
                        .type("serviceAccount")
                        .build())
                    .build())
            .build();


        sdk.subscriber().listPushSubscriptions()
                .filter("correspondent_id==\"01H8MCDXH4HYJJAV921BDKCC83\"")
                .pageSize(50)
                .pageToken("ZXhhbXBsZQo")
                .callAsStream()
                .forEach((SubscriberListPushSubscriptionsResponse item) -> {
                   // handle page
                });

    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                                                                                                                                                                                                                            | Type                                                                                                                                                                                                                                                                                                                                                                                                                                 | Required                                                                                                                                                                                                                                                                                                                                                                                                                             | Description                                                                                                                                                                                                                                                                                                                                                                                                                          | Example                                                                                                                                                                                                                                                                                                                                                                                                                              |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `filter`                                                                                                                                                                                                                                                                                                                                                                                                                             | *Optional\<String>*                                                                                                                                                                                                                                                                                                                                                                                                                  | :heavy_minus_sign:                                                                                                                                                                                                                                                                                                                                                                                                                   | A CEL string to filter results; See the [CEL Search](https://developer.apexclearing.com/apex-fintech-solutions/docs/cel-search) page in Guides for more information; If empty, all subscriptions the user has permission to view will be returned; Filter options include:<br/> `name`<br/> `subscription_id`<br/> `client_id`<br/> `correspondent_id`<br/> `display_name`<br/> `event_types`<br/> `state`<br/> `http_callback.url`<br/> `http_callback.timeout_seconds` | correspondent_id=="01H8MCDXH4HYJJAV921BDKCC83"                                                                                                                                                                                                                                                                                                                                                                                       |
| `pageSize`                                                                                                                                                                                                                                                                                                                                                                                                                           | *Optional\<Integer>*                                                                                                                                                                                                                                                                                                                                                                                                                 | :heavy_minus_sign:                                                                                                                                                                                                                                                                                                                                                                                                                   | The number of entries to return in a single page; Default = 100; Maximum = 1000                                                                                                                                                                                                                                                                                                                                                      | 50                                                                                                                                                                                                                                                                                                                                                                                                                                   |
| `pageToken`                                                                                                                                                                                                                                                                                                                                                                                                                          | *Optional\<String>*                                                                                                                                                                                                                                                                                                                                                                                                                  | :heavy_minus_sign:                                                                                                                                                                                                                                                                                                                                                                                                                   | Page token used for pagination; Supplying a page token returns the next page of results                                                                                                                                                                                                                                                                                                                                              | ZXhhbXBsZQo                                                                                                                                                                                                                                                                                                                                                                                                                          |

### Response

**[SubscriberListPushSubscriptionsResponse](../../models/operations/SubscriberListPushSubscriptionsResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403          | application/json       |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getPushSubscription

Gets the details of a specific push subscription.

### Example Usage

<!-- UsageSnippet language="java" operationID="Subscriber_GetPushSubscription" method="get" path="/events/v1/subscriptions/{subscription_id}" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.SubscriberGetPushSubscriptionResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .apiKey("ABCDEFGHIJ0123456789abcdefghij0123456789")
                    .serviceAccountCreds(ServiceAccountCreds.builder()
                        .privateKey("-----BEGIN PRIVATE KEY--{OMITTED FOR BREVITY}")
                        .name("FinFirm")
                        .organization("correspondents/00000000-0000-0000-0000-000000000000")
                        .type("serviceAccount")
                        .build())
                    .build())
            .build();

        SubscriberGetPushSubscriptionResponse res = sdk.subscriber().getPushSubscription()
                .subscriptionId("01H8MCDXH4JVH7KVNB2YY42907")
                .call();

        if (res.pushSubscription().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                  | Type                       | Required                   | Description                | Example                    |
| -------------------------- | -------------------------- | -------------------------- | -------------------------- | -------------------------- |
| `subscriptionId`           | *String*                   | :heavy_check_mark:         | The subscription id.       | 01H8MCDXH4JVH7KVNB2YY42907 |

### Response

**[SubscriberGetPushSubscriptionResponse](../../models/operations/SubscriberGetPushSubscriptionResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 404     | application/json       |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## updatePushSubscription

Updates the details of a push subscription.

### Example Usage

<!-- UsageSnippet language="java" operationID="Subscriber_UpdatePushSubscription" method="patch" path="/events/v1/subscriptions/{subscription_id}" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.*;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.SubscriberUpdatePushSubscriptionResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .apiKey("ABCDEFGHIJ0123456789abcdefghij0123456789")
                    .serviceAccountCreds(ServiceAccountCreds.builder()
                        .privateKey("-----BEGIN PRIVATE KEY--{OMITTED FOR BREVITY}")
                        .name("FinFirm")
                        .organization("correspondents/00000000-0000-0000-0000-000000000000")
                        .type("serviceAccount")
                        .build())
                    .build())
            .build();

        SubscriberUpdatePushSubscriptionResponse res = sdk.subscriber().updatePushSubscription()
                .subscriptionId("01H8MCDXH4JVH7KVNB2YY42907")
                .pushSubscriptionUpdate(PushSubscriptionUpdate.builder()
                    .build())
                .call();

        if (res.pushSubscription().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                   | Type                                                                        | Required                                                                    | Description                                                                 | Example                                                                     |
| --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- | --------------------------------------------------------------------------- |
| `subscriptionId`                                                            | *String*                                                                    | :heavy_check_mark:                                                          | The subscription id.                                                        | 01H8MCDXH4JVH7KVNB2YY42907                                                  |
| `updateMask`                                                                | *Optional\<String>*                                                         | :heavy_minus_sign:                                                          | The fields to update in subscription                                        |                                                                             |
| `pushSubscriptionUpdate`                                                    | [PushSubscriptionUpdate](../../models/components/PushSubscriptionUpdate.md) | :heavy_check_mark:                                                          | N/A                                                                         |                                                                             |

### Response

**[SubscriberUpdatePushSubscriptionResponse](../../models/operations/SubscriberUpdatePushSubscriptionResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 404     | application/json       |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## deletePushSubscription

Stops receiving events from a push subscription, and then deletes it.

### Example Usage

<!-- UsageSnippet language="java" operationID="Subscriber_DeletePushSubscription" method="delete" path="/events/v1/subscriptions/{subscription_id}" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.SubscriberDeletePushSubscriptionResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .apiKey("ABCDEFGHIJ0123456789abcdefghij0123456789")
                    .serviceAccountCreds(ServiceAccountCreds.builder()
                        .privateKey("-----BEGIN PRIVATE KEY--{OMITTED FOR BREVITY}")
                        .name("FinFirm")
                        .organization("correspondents/00000000-0000-0000-0000-000000000000")
                        .type("serviceAccount")
                        .build())
                    .build())
            .build();

        SubscriberDeletePushSubscriptionResponse res = sdk.subscriber().deletePushSubscription()
                .subscriptionId("01H8MCDXH4JVH7KVNB2YY42907")
                .call();

        // handle response
    }
}
```

### Parameters

| Parameter                  | Type                       | Required                   | Description                | Example                    |
| -------------------------- | -------------------------- | -------------------------- | -------------------------- | -------------------------- |
| `subscriptionId`           | *String*                   | :heavy_check_mark:         | The subscription id.       | 01H8MCDXH4JVH7KVNB2YY42907 |

### Response

**[SubscriberDeletePushSubscriptionResponse](../../models/operations/SubscriberDeletePushSubscriptionResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 404     | application/json       |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## getPushSubscriptionDelivery

Gets the details of a specific push subscription delivery.

### Example Usage

<!-- UsageSnippet language="java" operationID="Subscriber_GetPushSubscriptionDelivery" method="get" path="/events/v1/subscriptions/{subscription_id}/deliveries/{delivery_id}" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.SubscriberGetPushSubscriptionDeliveryResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .apiKey("ABCDEFGHIJ0123456789abcdefghij0123456789")
                    .serviceAccountCreds(ServiceAccountCreds.builder()
                        .privateKey("-----BEGIN PRIVATE KEY--{OMITTED FOR BREVITY}")
                        .name("FinFirm")
                        .organization("correspondents/00000000-0000-0000-0000-000000000000")
                        .type("serviceAccount")
                        .build())
                    .build())
            .build();

        SubscriberGetPushSubscriptionDeliveryResponse res = sdk.subscriber().getPushSubscriptionDelivery()
                .subscriptionId("01H8MCDXH4JVH7KVNB2YY42907")
                .deliveryId("01H8MCDXH415BJ962YDN4B02JK")
                .call();

        if (res.pushSubscriptionDelivery().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                  | Type                       | Required                   | Description                | Example                    |
| -------------------------- | -------------------------- | -------------------------- | -------------------------- | -------------------------- |
| `subscriptionId`           | *String*                   | :heavy_check_mark:         | The subscription id.       | 01H8MCDXH4JVH7KVNB2YY42907 |
| `deliveryId`               | *String*                   | :heavy_check_mark:         | The delivery id.           | 01H8MCDXH415BJ962YDN4B02JK |

### Response

**[SubscriberGetPushSubscriptionDeliveryResponse](../../models/operations/SubscriberGetPushSubscriptionDeliveryResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 404     | application/json       |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## listPushSubscriptionDeliveries

Gets a list of a push subscription's event deliveries.

### Example Usage

<!-- UsageSnippet language="java" operationID="Subscriber_ListPushSubscriptionDeliveries" method="get" path="/events/v1/subscriptions/{subscription_id}/deliveries" -->
```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.components.ServiceAccountCreds;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.SubscriberListPushSubscriptionDeliveriesResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .apiKey("ABCDEFGHIJ0123456789abcdefghij0123456789")
                    .serviceAccountCreds(ServiceAccountCreds.builder()
                        .privateKey("-----BEGIN PRIVATE KEY--{OMITTED FOR BREVITY}")
                        .name("FinFirm")
                        .organization("correspondents/00000000-0000-0000-0000-000000000000")
                        .type("serviceAccount")
                        .build())
                    .build())
            .build();


        sdk.subscriber().listPushSubscriptionDeliveries()
                .subscriptionId("01H8MCDXH4JVH7KVNB2YY42907")
                .filter("event_publish_time==timestamp(\"2023-06-13T23:48:58.343Z\")")
                .pageSize(50)
                .pageToken("ZXhhbXBsZQo")
                .callAsStream()
                .forEach((SubscriberListPushSubscriptionDeliveriesResponse item) -> {
                   // handle page
                });

    }
}
```

### Parameters

| Parameter                                                                                                                                                                                                                                                                                                                                                                               | Type                                                                                                                                                                                                                                                                                                                                                                                    | Required                                                                                                                                                                                                                                                                                                                                                                                | Description                                                                                                                                                                                                                                                                                                                                                                             | Example                                                                                                                                                                                                                                                                                                                                                                                 |
| --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `subscriptionId`                                                                                                                                                                                                                                                                                                                                                                        | *String*                                                                                                                                                                                                                                                                                                                                                                                | :heavy_check_mark:                                                                                                                                                                                                                                                                                                                                                                      | The subscription id.                                                                                                                                                                                                                                                                                                                                                                    | 01H8MCDXH4JVH7KVNB2YY42907                                                                                                                                                                                                                                                                                                                                                              |
| `filter`                                                                                                                                                                                                                                                                                                                                                                                | *Optional\<String>*                                                                                                                                                                                                                                                                                                                                                                     | :heavy_minus_sign:                                                                                                                                                                                                                                                                                                                                                                      | A CEL string to filter results; See the [CEL Search](https://developer.apexclearing.com/apex-fintech-solutions/docs/cel-search) page in Guides for more information; If left empty, all deliveries the user has permission to view are returned; Filter options include:<br/> `name`<br/> `delivery_id`<br/> `event`<br/> `event_publish_time`<br/> `result`<br/> `last_response`<br/> `last_send_time`<br/> `duration` | event_publish_time==timestamp("2023-06-13T23:48:58.343Z")                                                                                                                                                                                                                                                                                                                               |
| `pageSize`                                                                                                                                                                                                                                                                                                                                                                              | *Optional\<Integer>*                                                                                                                                                                                                                                                                                                                                                                    | :heavy_minus_sign:                                                                                                                                                                                                                                                                                                                                                                      | The number of entries to return in a single page; Default = 100; Maximum = 1000                                                                                                                                                                                                                                                                                                         | 50                                                                                                                                                                                                                                                                                                                                                                                      |
| `pageToken`                                                                                                                                                                                                                                                                                                                                                                             | *Optional\<String>*                                                                                                                                                                                                                                                                                                                                                                     | :heavy_minus_sign:                                                                                                                                                                                                                                                                                                                                                                      | Page token used for pagination; Supplying a page token returns the next page of results                                                                                                                                                                                                                                                                                                 | ZXhhbXBsZQo                                                                                                                                                                                                                                                                                                                                                                             |

### Response

**[SubscriberListPushSubscriptionDeliveriesResponse](../../models/operations/SubscriberListPushSubscriptionDeliveriesResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401, 403, 404     | application/json       |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |