# ForceRejectCashJournalRequestCreate

Request to force reject a pending cash journal


## Fields

| Field                                                  | Type                                                   | Required                                               | Description                                            | Example                                                |
| ------------------------------------------------------ | ------------------------------------------------------ | ------------------------------------------------------ | ------------------------------------------------------ | ------------------------------------------------------ |
| `name`                                                 | *String*                                               | :heavy_check_mark:                                     | The name of the cash journal to force reject           | cashJournals/20230817000319                            |
| `reason`                                               | *Optional\<String>*                                    | :heavy_minus_sign:                                     | The optional reason for force rejecting a cash journal | Forced rejection test                                  |