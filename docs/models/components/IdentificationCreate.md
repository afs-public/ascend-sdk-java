# IdentificationCreate

Represents an identification document


## Fields

| Field                                                                           | Type                                                                            | Required                                                                        | Description                                                                     | Example                                                                         |
| ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------- |
| `regionCode`                                                                    | *String*                                                                        | :heavy_check_mark:                                                              | CLDR format                                                                     | US                                                                              |
| `type`                                                                          | [IdentificationCreateType](../../models/components/IdentificationCreateType.md) | :heavy_check_mark:                                                              | The type of identification document                                             | SSN                                                                             |
| `value`                                                                         | *String*                                                                        | :heavy_check_mark:                                                              | The value of the identification document                                        | 123-45-6789                                                                     |