# RecipientBank

The recipient bank / financial institution


## Fields

| Field                                                                                          | Type                                                                                           | Required                                                                                       | Description                                                                                    |
| ---------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------- |
| `bankId`                                                                                       | [JsonNullable\<BankId>](../../models/components/BankId.md)                                     | :heavy_minus_sign:                                                                             | An identifier that represents ABA routing number for domestic wire or BIC for foreign wire     |
| `internationalBankDetails`                                                                     | [JsonNullable\<InternationalBankDetails>](../../models/components/InternationalBankDetails.md) | :heavy_minus_sign:                                                                             | Bank details required in the case of an international wire transfer                            |