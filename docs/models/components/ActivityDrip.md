# ActivityDrip

Used to record the movement of funds to/ from the pending_drip memo location


## Fields

| Field                                                                          | Type                                                                           | Required                                                                       | Description                                                                    | Example                                                                        |
| ------------------------------------------------------------------------------ | ------------------------------------------------------------------------------ | ------------------------------------------------------------------------------ | ------------------------------------------------------------------------------ | ------------------------------------------------------------------------------ |
| `action`                                                                       | [Optional\<ActivityDripAction>](../../models/components/ActivityDripAction.md) | :heavy_minus_sign:                                                             | Denotes whether the reinvestment is pending or complete                        | DRIP_PENDING                                                                   |