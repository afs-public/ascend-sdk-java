# BankAccountCreate

A representation of a bank account.


## Fields

| Field                                                                     | Type                                                                      | Required                                                                  | Description                                                               | Example                                                                   |
| ------------------------------------------------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------------------- |
| `accountNumber`                                                           | *String*                                                                  | :heavy_check_mark:                                                        | The bank account number. This value will be masked in responses.          | 100100123                                                                 |
| `owner`                                                                   | *String*                                                                  | :heavy_check_mark:                                                        | The name of the bank account owner.                                       | John Doe                                                                  |
| `routingNumber`                                                           | *String*                                                                  | :heavy_check_mark:                                                        | The bank routing number (either ABA or BIC).                              | 1.2345678e+07                                                             |
| `type`                                                                    | [BankAccountCreateType](../../models/components/BankAccountCreateType.md) | :heavy_check_mark:                                                        | The bank account type.                                                    | CHECKING                                                                  |