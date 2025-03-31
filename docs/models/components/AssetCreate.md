# AssetCreate

The asset being transferred If cash, the asset_id is the currency code (e.g. USD) and the position is the amount


## Fields

| Field                                                         | Type                                                          | Required                                                      | Description                                                   | Example                                                       |
| ------------------------------------------------------------- | ------------------------------------------------------------- | ------------------------------------------------------------- | ------------------------------------------------------------- | ------------------------------------------------------------- |
| `identifier`                                                  | *String*                                                      | :heavy_check_mark:                                            | The asset identifier                                          | US37733W2044                                                  |
| `position`                                                    | [PositionCreate](../../models/components/PositionCreate.md)   | :heavy_check_mark:                                            | The position or amount of the asset                           |                                                               |
| `type`                                                        | [AssetCreateType](../../models/components/AssetCreateType.md) | :heavy_check_mark:                                            | The asset identifier type                                     | CUSIP                                                         |