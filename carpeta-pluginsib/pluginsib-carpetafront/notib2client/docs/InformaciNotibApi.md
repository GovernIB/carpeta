# InformaciNotibApi

All URIs are relative to */notibapi/interna*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getAppInfo**](InformaciNotibApi.md#getAppInfo) | **GET** /api/rest/appinfo | Consulta la informació de la API |



## getAppInfo

> AppInfo getAppInfo()

Consulta la informació de la API

Retorna la data i la versió de la API REST Interna Notib

### Example

```java
// Import classes:
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiClient;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiException;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.Configuration;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.models.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.api.InformaciNotibApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/notibapi/interna");

        InformaciNotibApi apiInstance = new InformaciNotibApi(defaultClient);
        try {
            AppInfo result = apiInstance.getAppInfo();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling InformaciNotibApi#getAppInfo");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**AppInfo**](AppInfo.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Informació de l&#39;aplicació |  -  |

