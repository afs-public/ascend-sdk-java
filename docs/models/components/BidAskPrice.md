# BidAskPrice

The definition of a price value and its calculation method as returned in quote data


## Fields

| Field                                                                          | Type                                                                           | Required                                                                       | Description                                                                    | Example                                                                        |
| ------------------------------------------------------------------------------ | ------------------------------------------------------------------------------ | ------------------------------------------------------------------------------ | ------------------------------------------------------------------------------ | ------------------------------------------------------------------------------ |
| `price`                                                                        | [JsonNullable\<BidAskPricePrice>](../../models/components/BidAskPricePrice.md) | :heavy_minus_sign:                                                             | The price value                                                                | {<br/>"value": "97.43"<br/>}                                                   |
| `type`                                                                         | [Optional\<BidAskPriceType>](../../models/components/BidAskPriceType.md)       | :heavy_minus_sign:                                                             | The calculation type of this price                                             | PERCENTAGE_OF_PAR                                                              |