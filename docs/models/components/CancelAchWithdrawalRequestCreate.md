# CancelAchWithdrawalRequestCreate

Request to cancel an existing ACH withdrawal.


## Fields

| Field                                                             | Type                                                              | Required                                                          | Description                                                       | Example                                                           |
| ----------------------------------------------------------------- | ----------------------------------------------------------------- | ----------------------------------------------------------------- | ----------------------------------------------------------------- | ----------------------------------------------------------------- |
| `name`                                                            | *String*                                                          | :heavy_check_mark:                                                | The name of the ACH withdrawal to cancel.                         | accounts/01H8FB90ZRRFWXB4XC2JPJ1D4Y/achWithdrawals/20230620500726 |
| `reason`                                                          | *Optional\<String>*                                               | :heavy_minus_sign:                                                | The reason why the ACH withdrawal is being canceled.              | User Request                                                      |