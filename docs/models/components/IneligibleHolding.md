# IneligibleHolding

IneligibleHolding is a holding that can't transfer, with the reason(s) why.


## Fields

| Field                                                                                                    | Type                                                                                                     | Required                                                                                                 | Description                                                                                              |
| -------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------- |
| `failures`                                                                                               | List\<[AcatsAssetFailure](../../models/components/AcatsAssetFailure.md)>                                 | :heavy_minus_sign:                                                                                       | Why the holding is ineligible; at least one entry.                                                       |
| `holding`                                                                                                | [JsonNullable\<AcatsIneligibleHoldingHolding>](../../models/components/AcatsIneligibleHoldingHolding.md) | :heavy_minus_sign:                                                                                       | The holding.                                                                                             |