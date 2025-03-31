# Authentication
(*authentication()*)

## Overview

### Available Operations

* [generateServiceAccountToken](#generateserviceaccounttoken) - Generate Service Account Token
* [listSigningKeys](#listsigningkeys) - List Signing Keys

## generateServiceAccountToken

Creates an access token for a service account.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.GenerateServiceAccountTokenRequestCreate;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AuthenticationGenerateServiceAccountTokenResponse;
import com.apexfintechsolutions.ascendsdk.models.operations.AuthenticationGenerateServiceAccountTokenSecurity;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Status, Exception {

        SDK sdk = SDK.builder()
            .build();

        GenerateServiceAccountTokenRequestCreate req = GenerateServiceAccountTokenRequestCreate.builder()
                .jws("eyJhbGciOiAiUlMyNTYifQ.eyJuYW1lIjogImpkb3VnaCIsIm9yZ2FuaXphdGlvbiI6ICJjb3JyZXNwb25kZW50cy8xMjM0NTY3OC0xMjM0LTEyMzQtMTIzNC0xMjM0NTY3ODkxMDEiLCJkYXRldGltZSI6ICIyMDI0LTAyLTA1VDIxOjAyOjI3LjkwMTE4MFoifQ.IMy3KmYoG8Ppf+7hXN7tm7J4MrNpQLGL7WCWvhh4nZWAVKkluL3/u3KC6hZ6Mb/5p7Y54CgZ68aWT2BcP5y4VtzIZR1Chm5pxbLfgE4aJuk+FnF6K3Gc3bBjOWCL58pxY2aTb0iU/exDEA1cbMDvbCzmY5kRefDvorLOqgUS/tS2MJ2jv4RlZFPlmHv5PtOruJ8xUW19gEgGhsPXYYeSHFTE1ZlaDvyXrKtpOvlf+FVc2RTuEw529LZnzwH4/eJJR3BpSpHyJTjQqiaMT3wzpXXYKfCRqnDkSSKJDzCzTb0/uWK/Lf0uafxPXk5YLdis+dbo1zNQhVVKjwnMpk1vLw")
                .build();

        AuthenticationGenerateServiceAccountTokenResponse res = sdk.authentication().generateServiceAccountToken()
                .request(req)
                .security(AuthenticationGenerateServiceAccountTokenSecurity.builder()
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
                    .build())
                .call();

        if (res.token().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                                                                                                                              | Type                                                                                                                                                                                   | Required                                                                                                                                                                               | Description                                                                                                                                                                            |
| -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `request`                                                                                                                                                                              | [GenerateServiceAccountTokenRequestCreate](../../models/shared/GenerateServiceAccountTokenRequestCreate.md)                                                                            | :heavy_check_mark:                                                                                                                                                                     | The request object to use for the request.                                                                                                                                             |
| `security`                                                                                                                                                                             | [com.apexfintechsolutions.ascendsdk.models.operations.AuthenticationGenerateServiceAccountTokenSecurity](../../models/operations/AuthenticationGenerateServiceAccountTokenSecurity.md) | :heavy_check_mark:                                                                                                                                                                     | The security requirements to use for the request.                                                                                                                                      |

### Response

**[AuthenticationGenerateServiceAccountTokenResponse](../../models/operations/AuthenticationGenerateServiceAccountTokenResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 400, 401               | application/json       |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |

## listSigningKeys

Gets the public signing keys used to verify JSON Web Tokens generated by this service.

### Example Usage

```java
package hello.world;

import com.apexfintechsolutions.ascendsdk.SDK;
import com.apexfintechsolutions.ascendsdk.models.components.Security;
import com.apexfintechsolutions.ascendsdk.models.errors.Status;
import com.apexfintechsolutions.ascendsdk.models.operations.AuthenticationListSigningKeysResponse;
import java.lang.Exception;

public class Application {

    public static void main(String[] args) throws Status, Exception {

        SDK sdk = SDK.builder()
                .security(Security.builder()
                    .bearerAuth("<YOUR_BEARER_TOKEN_HERE>")
                    .apiKeyAuth("<YOUR_API_KEY_HERE>")
                    .build())
            .build();

        AuthenticationListSigningKeysResponse res = sdk.authentication().listSigningKeys()
                .pageSize(50)
                .pageToken("ZXhhbXBsZQo")
                .call();

        if (res.listSigningKeysResponse().isPresent()) {
            // handle response
        }
    }
}
```

### Parameters

| Parameter                                                                               | Type                                                                                    | Required                                                                                | Description                                                                             | Example                                                                                 |
| --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------- |
| `pageSize`                                                                              | *Optional\<Integer>*                                                                    | :heavy_minus_sign:                                                                      | The number of entries to return in a single page; Default = 100; Maximum = 1000         | 50                                                                                      |
| `pageToken`                                                                             | *Optional\<String>*                                                                     | :heavy_minus_sign:                                                                      | Page token used for pagination; Supplying a page token returns the next page of results | ZXhhbXBsZQo                                                                             |

### Response

**[AuthenticationListSigningKeysResponse](../../models/operations/AuthenticationListSigningKeysResponse.md)**

### Errors

| Error Type             | Status Code            | Content Type           |
| ---------------------- | ---------------------- | ---------------------- |
| models/errors/Status   | 500                    | application/json       |
| models/errors/SDKError | 4XX, 5XX               | \*/\*                  |