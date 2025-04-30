# Fee


## Fields

| Field                                                            | Type                                                             | Required                                                         | Description                                                      | Example                                                          |
| ---------------------------------------------------------------- | ---------------------------------------------------------------- | ---------------------------------------------------------------- | ---------------------------------------------------------------- | ---------------------------------------------------------------- |
| `amount`                                                         | [JsonNullable\<FeeAmount>](../../models/components/FeeAmount.md) | :heavy_minus_sign:                                               | Monetary amount associated with the fee                          | {<br/>"value": "0.25"<br/>}                                      |
| `type`                                                           | [Optional\<FeeType>](../../models/components/FeeType.md)         | :heavy_minus_sign:                                               | The type of fee being assessed                                   | LIQUIDITY                                                        |