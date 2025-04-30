# RebookTradeAllocationResponse

Rebooking a trade allocation will always return a new resource that contains the rebooked trade allocation.


## Fields

| Field                                                                                        | Type                                                                                         | Required                                                                                     | Description                                                                                  |
| -------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------- |
| `newTradeAllocation`                                                                         | [JsonNullable\<NewTradeAllocation>](../../models/components/NewTradeAllocation.md)           | :heavy_minus_sign:                                                                           | The new trade allocation that is booked.                                                     |
| `originalTradeAllocation`                                                                    | [JsonNullable\<OriginalTradeAllocation>](../../models/components/OriginalTradeAllocation.md) | :heavy_minus_sign:                                                                           | The original trade allocation that was rebooked.                                             |