# BondYield

A percentage yield


## Fields

| Field                                                                          | Type                                                                           | Required                                                                       | Description                                                                    | Example                                                                        |
| ------------------------------------------------------------------------------ | ------------------------------------------------------------------------------ | ------------------------------------------------------------------------------ | ------------------------------------------------------------------------------ | ------------------------------------------------------------------------------ |
| `percent`                                                                      | [JsonNullable\<Percent>](../../models/components/Percent.md)                   | :heavy_minus_sign:                                                             | The percentage yield.                                                          | {<br/>"value": "25.00"<br/>}                                                   |
| `yieldType`                                                                    | [Optional\<BondYieldYieldType>](../../models/components/BondYieldYieldType.md) | :heavy_minus_sign:                                                             | The type of yield.                                                             | YIELD_TO_MATURITY                                                              |