# Money

Object containing currency/ price information for the trade lot


## Fields

| Field                                                          | Type                                                           | Required                                                       | Description                                                    | Example                                                        |
| -------------------------------------------------------------- | -------------------------------------------------------------- | -------------------------------------------------------------- | -------------------------------------------------------------- | -------------------------------------------------------------- |
| `currencyCode`                                                 | *Optional\<String>*                                            | :heavy_minus_sign:                                             | Currency code of the price                                     | USD                                                            |
| `price`                                                        | [JsonNullable\<LotPrice>](../../models/components/LotPrice.md) | :heavy_minus_sign:                                             | Price of the trade lot                                         | {<br/>"value": "0.25"<br/>}                                    |