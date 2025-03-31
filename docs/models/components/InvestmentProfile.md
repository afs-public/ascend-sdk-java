# InvestmentProfile

The account's goals and customer's financial profile; Used to assess customer fitness and is required by FINRA


## Fields

| Field                                                                        | Type                                                                         | Required                                                                     | Description                                                                  | Example                                                                      |
| ---------------------------------------------------------------------------- | ---------------------------------------------------------------------------- | ---------------------------------------------------------------------------- | ---------------------------------------------------------------------------- | ---------------------------------------------------------------------------- |
| `accountGoals`                                                               | [JsonNullable\<AccountGoals>](../../models/components/AccountGoals.md)       | :heavy_minus_sign:                                                           | Account goals.                                                               |                                                                              |
| `customerProfile`                                                            | [JsonNullable\<CustomerProfile>](../../models/components/CustomerProfile.md) | :heavy_minus_sign:                                                           | Customer profile.                                                            |                                                                              |
| `investmentProfileId`                                                        | *Optional\<String>*                                                          | :heavy_minus_sign:                                                           | The investment profile ID (generated internally).                            | 8a111faf-2923-44e4-9e1b-59328e72467b                                         |