# AssetSpecific

Asset Specific fields returned from the request.


## Fields

| Field                                                                | Type                                                                 | Required                                                             | Description                                                          | Example                                                              |
| -------------------------------------------------------------------- | -------------------------------------------------------------------- | -------------------------------------------------------------------- | -------------------------------------------------------------------- | -------------------------------------------------------------------- |
| `dayTrade`                                                           | [JsonNullable\<DayTrade>](../../models/components/DayTrade.md)       | :heavy_minus_sign:                                                   | Day Trade Asset Buying Power figures returned from the request.      |                                                                      |
| `nonDayTrade`                                                        | [JsonNullable\<NonDayTrade>](../../models/components/NonDayTrade.md) | :heavy_minus_sign:                                                   | Non Day Trade Asset Buying Power figures returned from the request.  |                                                                      |
| `priceAmount`                                                        | [JsonNullable\<PriceAmount>](../../models/components/PriceAmount.md) | :heavy_minus_sign:                                                   | The current price of the asset in USD returned from the request.     | {<br/>"value": "100.00"<br/>}                                        |