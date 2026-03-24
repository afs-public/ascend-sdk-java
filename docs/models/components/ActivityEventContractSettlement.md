# ActivityEventContractSettlement

Used to record the settlement/payout of event contracts based on real-world event outcomes


## Fields

| Field                                                                    | Type                                                                     | Required                                                                 | Description                                                              | Example                                                                  |
| ------------------------------------------------------------------------ | ------------------------------------------------------------------------ | ------------------------------------------------------------------------ | ------------------------------------------------------------------------ | ------------------------------------------------------------------------ |
| `exchange`                                                               | *Optional\<String>*                                                      | :heavy_minus_sign:                                                       | The exchange that issued the event contract                              | KALSHI                                                                   |
| `outcome`                                                                | [Optional\<ActivityOutcome>](../../models/components/ActivityOutcome.md) | :heavy_minus_sign:                                                       | The determined outcome of the event                                      | FAVORABLE                                                                |