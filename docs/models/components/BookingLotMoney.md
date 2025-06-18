# ~~BookingLotMoney~~

Deprecated; use the price field instead

> :warning: **DEPRECATED**: This will be removed in a future release, please migrate away from it as soon as possible.


## Fields

| Field                                                                                  | Type                                                                                   | Required                                                                               | Description                                                                            | Example                                                                                |
| -------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------- |
| `currencyCode`                                                                         | *Optional\<String>*                                                                    | :heavy_minus_sign:                                                                     | N/A                                                                                    | USD                                                                                    |
| `price`                                                                                | [JsonNullable\<BookingLotMoneyPrice>](../../models/components/BookingLotMoneyPrice.md) | :heavy_minus_sign:                                                                     | N/A                                                                                    | {<br/>"value": "2.50"<br/>}                                                            |