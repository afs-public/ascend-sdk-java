# Status

The status message serves as the general-purpose service error message. Each status message includes a gRPC error code, error message, and error details.


## Fields

| Field                                                               | Type                                                                | Required                                                            | Description                                                         |
| ------------------------------------------------------------------- | ------------------------------------------------------------------- | ------------------------------------------------------------------- | ------------------------------------------------------------------- |
| `code`                                                              | *Optional\<Integer>*                                                | :heavy_minus_sign:                                                  | The code field contains an enum value of google.rpc.Code.           |
| `details`                                                           | List\<[Any](../../models/components/Any.md)>                        | :heavy_minus_sign:                                                  | The details field contains one or more technical error details.     |
| `message`                                                           | *Optional\<String>*                                                 | :heavy_minus_sign:                                                  | The message field contains human-friendly messages about the error. |