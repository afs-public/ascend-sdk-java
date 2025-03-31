# Credit

Used to disburse funds into a customer's account, typically for purposes such as refunds, interest payments, or rewards from enrolled programs and details related to the credit


## Fields

| Field                                                                     | Type                                                                      | Required                                                                  | Description                                                               | Example                                                                   |
| ------------------------------------------------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------- |
| `additionalInstructions`                                                  | *Optional\<String>*                                                       | :heavy_minus_sign:                                                        | Free form text field providing additional information about a transaction | FDIC sweep interest payment                                               |
| `creditType`                                                              | [Optional\<CreditType>](../../models/components/CreditType.md)            | :heavy_minus_sign:                                                        | Provides more details on the type of credit                               | WRITE_OFF                                                                 |
| `taxable`                                                                 | *Optional\<Boolean>*                                                      | :heavy_minus_sign:                                                        | Indicates whether the credit is taxable                                   | false                                                                     |