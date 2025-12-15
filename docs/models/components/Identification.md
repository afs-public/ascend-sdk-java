# Identification

Represents an identification document


## Fields

| Field                                                                          | Type                                                                           | Required                                                                       | Description                                                                    | Example                                                                        |
| ------------------------------------------------------------------------------ | ------------------------------------------------------------------------------ | ------------------------------------------------------------------------------ | ------------------------------------------------------------------------------ | ------------------------------------------------------------------------------ |
| `regionCode`                                                                   | *Optional\<String>*                                                            | :heavy_minus_sign:                                                             | CLDR format                                                                    | US                                                                             |
| `type`                                                                         | [Optional\<IdentificationType>](../../models/components/IdentificationType.md) | :heavy_minus_sign:                                                             | The type of identification document                                            | SSN                                                                            |
| `value`                                                                        | *Optional\<String>*                                                            | :heavy_minus_sign:                                                             | The value of the identification document                                       | 123-45-6789                                                                    |