# ListPushSubscriptionsResponse

A response to a list push subscriptions method


## Fields

| Field                                                                                   | Type                                                                                    | Required                                                                                | Description                                                                             | Example                                                                                 |
| --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- |
| `nextPageToken`                                                                         | *Optional\<String>*                                                                     | :heavy_minus_sign:                                                                      | Page token used for pagination; Supplying a page token returns the next page of results | ZXhhbXBsZQo                                                                             |
| `pushSubscriptions`                                                                     | List\<[PushSubscription](../../models/components/PushSubscription.md)>                  | :heavy_minus_sign:                                                                      | The returned collection of subscriptions                                                |                                                                                         |