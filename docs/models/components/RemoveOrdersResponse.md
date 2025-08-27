# RemoveOrdersResponse

The response message for removing a list of basket orders by client order ID.


## Fields

| Field                                                                                 | Type                                                                                  | Required                                                                              | Description                                                                           | Example                                                                               |
| ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- |
| `basketOrders`                                                                        | List\<[BasketOrder](../../models/components/BasketOrder.md)>                          | :heavy_minus_sign:                                                                    | The removed basket orders.                                                            |                                                                                       |
| `name`                                                                                | *Optional\<String>*                                                                   | :heavy_minus_sign:                                                                    | Format: correspondents/{correspondent}/baskets/{basket}                               | correspondents/01HPMZZM6RKMVZA1JQ63RQKJRP/baskets/fffd326-72fa-4d2b-bd1f-45384fe5d521 |