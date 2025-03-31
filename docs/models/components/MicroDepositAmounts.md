# MicroDepositAmounts

The micro deposits sent to verify a pending bank relationship. FOR TESTING ONLY!


## Fields

| Field                                                        | Type                                                         | Required                                                     | Description                                                  | Example                                                      |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| `amount1`                                                    | [JsonNullable\<Amount1>](../../models/components/Amount1.md) | :heavy_minus_sign:                                           | The amount of one of the micro deposits.                     | {<br/>"value": "0.03"<br/>}                                  |
| `amount2`                                                    | [JsonNullable\<Amount2>](../../models/components/Amount2.md) | :heavy_minus_sign:                                           | The amount of the other micro deposit.                       | {<br/>"value": "0.67"<br/>}                                  |