# CancelPositionJournalRequestCreate

Request to cancel an existing position journal


## Fields

| Field                                                                                 | Type                                                                                  | Required                                                                              | Description                                                                           | Example                                                                               |
| ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- |
| `name`                                                                                | *String*                                                                              | :heavy_check_mark:                                                                    | The name of the position journal to cancel                                            | positionJournals/20240717000319                                                       |
| `reason`                                                                              | *Optional\<String>*                                                                   | :heavy_minus_sign:                                                                    | The reason for canceling the position journal Maximum of 100 characters are supported | Cancel position journal                                                               |