# MarkPrice

The definition of a price value and its calculation method as returned in mark data


## Fields

| Field                                                                      | Type                                                                       | Required                                                                   | Description                                                                | Example                                                                    |
| -------------------------------------------------------------------------- | -------------------------------------------------------------------------- | -------------------------------------------------------------------------- | -------------------------------------------------------------------------- | -------------------------------------------------------------------------- |
| `price`                                                                    | [JsonNullable\<MarkPricePrice>](../../models/components/MarkPricePrice.md) | :heavy_minus_sign:                                                         | The price value                                                            | {<br/>"value": "97.43"<br/>}                                               |
| `type`                                                                     | [Optional\<MarkPriceType>](../../models/components/MarkPriceType.md)       | :heavy_minus_sign:                                                         | The calculation type of this price                                         | PERCENTAGE_OF_PAR                                                          |