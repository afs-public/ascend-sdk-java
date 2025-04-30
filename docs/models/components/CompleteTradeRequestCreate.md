# CompleteTradeRequestCreate

A request for completing a trade.


## Fields

| Field                                                                 | Type                                                                  | Required                                                              | Description                                                           | Example                                                               |
| --------------------------------------------------------------------- | --------------------------------------------------------------------- | --------------------------------------------------------------------- | --------------------------------------------------------------------- | --------------------------------------------------------------------- |
| `fees`                                                                | List\<[FeeCreate](../../models/components/FeeCreate.md)>              | :heavy_minus_sign:                                                    | Client calculated fees to use while completing an existing trade.     |                                                                       |
| `name`                                                                | *String*                                                              | :heavy_check_mark:                                                    | The name of the trade to complete.                                    | accounts/02HASWB2DTMRT3DAM45P56J2T2/trades/01J0XX2KDN3M9QKFKRE2HYSCQM |