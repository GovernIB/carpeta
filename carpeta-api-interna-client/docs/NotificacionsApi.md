# NotificacionsApi

All URIs are relative to *http://../../carpetaapi/interna*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**existCitizen**](NotificacionsApi.md#existCitizen) | **GET** /secure/mobilenotification/existcitizen | Consulta si tenim donat d&#39;alta el mòbil d&#39;un ciutadà/empresa a partir del seu NIF. |
| [**help**](NotificacionsApi.md#help) | **GET** /secure/mobilenotification/help | Envia un missatge al mòbil del ciutada a traves de l&#39;App de Carpeta. |
| [**sendNotificationToMobile**](NotificacionsApi.md#sendNotificationToMobile) | **GET** /secure/mobilenotification/sendnotificationtomobile | Envia un missatge al mòbil del ciutada a traves de l&#39;App de Carpeta. |



## existCitizen

> Boolean existCitizen(nif, lang)

Consulta si tenim donat d&#39;alta el mòbil d&#39;un ciutadà/empresa a partir del seu NIF.

### Example

```java
// Import classes:
import es.caib.carpeta.apiinterna.client.services.ApiClient;
import es.caib.carpeta.apiinterna.client.services.ApiException;
import es.caib.carpeta.apiinterna.client.services.Configuration;
import es.caib.carpeta.apiinterna.client.services.auth.*;
import es.caib.carpeta.apiinterna.client.services.models.*;
import es.caib.carpeta.apiinterna.client.api.NotificacionsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://../../carpetaapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        NotificacionsApi apiInstance = new NotificacionsApi(defaultClient);
        String nif = "12345678Z"; // String | NIF del Ciutadà o l'empresa
        String lang = "ca"; // String | Codi de l'idioma
        try {
            Boolean result = apiInstance.existCitizen(nif, lang);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling NotificacionsApi#existCitizen");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **nif** | **String**| NIF del Ciutadà o l&#39;empresa | |
| **lang** | **String**| Codi de l&#39;idioma | |

### Return type

**Boolean**

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **404** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No Autoritzat |  -  |
| **200** | Consulta finalitzada. Retorna true si existeix el ciutadà/empresa o false en cas contrari. |  -  |


## help

> String help(notificationCode, langError)

Envia un missatge al mòbil del ciutada a traves de l&#39;App de Carpeta.

### Example

```java
// Import classes:
import es.caib.carpeta.apiinterna.client.services.ApiClient;
import es.caib.carpeta.apiinterna.client.services.ApiException;
import es.caib.carpeta.apiinterna.client.services.Configuration;
import es.caib.carpeta.apiinterna.client.services.auth.*;
import es.caib.carpeta.apiinterna.client.services.models.*;
import es.caib.carpeta.apiinterna.client.api.NotificacionsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://../../carpetaapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        NotificacionsApi apiInstance = new NotificacionsApi(defaultClient);
        String notificationCode = "CODENAME"; // String | Codi de la notificació. Demanar a l'administrador de Carpeta.
        String langError = "ca"; // String | Idioma en que s'enviaran els missatges d'error
        try {
            String result = apiInstance.help(notificationCode, langError);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling NotificacionsApi#help");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **notificationCode** | **String**| Codi de la notificació. Demanar a l&#39;administrador de Carpeta. | |
| **langError** | **String**| Idioma en que s&#39;enviaran els missatges d&#39;error | |

### Return type

**String**

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **404** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No Autoritzat |  -  |
| **400** | Error |  -  |
| **200** | Enviada ajuda correctament |  -  |


## sendNotificationToMobile

> SendMessageResult sendNotificationToMobile(nif, notificationCode, notificationParameters, notificationLang, langError)

Envia un missatge al mòbil del ciutada a traves de l&#39;App de Carpeta.

### Example

```java
// Import classes:
import es.caib.carpeta.apiinterna.client.services.ApiClient;
import es.caib.carpeta.apiinterna.client.services.ApiException;
import es.caib.carpeta.apiinterna.client.services.Configuration;
import es.caib.carpeta.apiinterna.client.services.auth.*;
import es.caib.carpeta.apiinterna.client.services.models.*;
import es.caib.carpeta.apiinterna.client.api.NotificacionsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://../../carpetaapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        NotificacionsApi apiInstance = new NotificacionsApi(defaultClient);
        String nif = "12345678Z"; // String | NIF del Ciutadà o l'empresa
        String notificationCode = "CODENAME"; // String | Codi de la notificació. Demanar a l'administrador de Carpeta.
        List<String> notificationParameters = Arrays.asList(); // List<String> | Paràmetres associats al Codi de la notificació
        String notificationLang = "ca"; // String | Idioma en que s'enviaran les notificacion
        String langError = "ca"; // String | Idioma en que s'enviaran els missatges d'error
        try {
            SendMessageResult result = apiInstance.sendNotificationToMobile(nif, notificationCode, notificationParameters, notificationLang, langError);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling NotificacionsApi#sendNotificationToMobile");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **nif** | **String**| NIF del Ciutadà o l&#39;empresa | |
| **notificationCode** | **String**| Codi de la notificació. Demanar a l&#39;administrador de Carpeta. | |
| **notificationParameters** | [**List&lt;String&gt;**](String.md)| Paràmetres associats al Codi de la notificació | |
| **notificationLang** | **String**| Idioma en que s&#39;enviaran les notificacion | |
| **langError** | **String**| Idioma en que s&#39;enviaran els missatges d&#39;error | |

### Return type

[**SendMessageResult**](SendMessageResult.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **404** | Paràmetres incorrectes |  -  |
| **401** | No Autenticat |  -  |
| **403** | No Autoritzat |  -  |
| **400** | Error durant el processament o enviament del missatge |  -  |
| **200** | Enviat missatge correctament |  -  |

