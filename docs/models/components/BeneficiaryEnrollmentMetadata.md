# BeneficiaryEnrollmentMetadata

Metadata for the BENEFICIARY_DESIGNATION enrollment type.


## Fields

| Field                                                                                            | Type                                                                                             | Required                                                                                         | Description                                                                                      |
| ------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------ |
| `contingentBeneficiaries`                                                                        | List\<[Beneficiary](../../models/components/Beneficiary.md)>                                     | :heavy_minus_sign:                                                                               | Contingent Beneficiary list is optional, with a maximum of five contingent beneficiaries.        |
| `primaryBeneficiaries`                                                                           | List\<[Beneficiary](../../models/components/Beneficiary.md)>                                     | :heavy_minus_sign:                                                                               | At least one primary beneficiary must be provided, with a maximum of five primary beneficiaries. |