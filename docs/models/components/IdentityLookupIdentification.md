# IdentityLookupIdentification

Identification document for verification


## Fields

| Field                                                                          | Type                                                                           | Required                                                                       | Description                                                                    | Example                                                                        |
| ------------------------------------------------------------------------------ | ------------------------------------------------------------------------------ | ------------------------------------------------------------------------------ | ------------------------------------------------------------------------------ | ------------------------------------------------------------------------------ |
| `regionCode`                                                                   | *Optional\<String>*                                                            | :heavy_minus_sign:                                                             | CLDR format                                                                    | US                                                                             |
| `type`                                                                         | [Optional\<IdentityLookupType>](../../models/components/IdentityLookupType.md) | :heavy_minus_sign:                                                             | The type of identification document                                            | SSN                                                                            |
| `value`                                                                        | *Optional\<String>*                                                            | :heavy_minus_sign:                                                             | The value of the identification document                                       | 123-45-6789                                                                    |