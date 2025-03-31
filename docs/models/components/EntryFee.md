# EntryFee

Used to record Fees that have been assessed to account and capture details related to the fee


## Fields

| Field                                                                     | Type                                                                      | Required                                                                  | Description                                                               | Example                                                                   |
| ------------------------------------------------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------- |
| `additionalInstructions`                                                  | *Optional\<String>*                                                       | :heavy_minus_sign:                                                        | Free form text field providing additional information about a transaction | Fee instruction                                                           |
| `type`                                                                    | [Optional\<EntryFeeType>](../../models/components/EntryFeeType.md)        | :heavy_minus_sign:                                                        | Enum providing additional information about the type of fee being charged | LIQUIDITY                                                                 |