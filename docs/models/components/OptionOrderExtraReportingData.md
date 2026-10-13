# OptionOrderExtraReportingData

Post-cancel reporting data provided via the SetOptionExtraReportingData endpoint.


## Fields

| Field                                                                                              | Type                                                                                               | Required                                                                                           | Description                                                                                        | Example                                                                                            |
| -------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------- |
| `cancelConfirmedTime`                                                                              | [OffsetDateTime](https://docs.oracle.com/javase/8/docs/api/java/time/OffsetDateTime.html)          | :heavy_minus_sign:                                                                                 | Correspondent-side timestamp when the cancellation was confirmed. Maps to CAT CANCEL_CONFIRM_TIME. | 2025-12-13T15:28:17.262732Z                                                                        |