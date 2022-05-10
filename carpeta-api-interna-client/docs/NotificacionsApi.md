# NotificacionsApi

All URIs are relative to *http://localhost:8080/carpetaapi/interna*

Method | HTTP request | Description
------------- | ------------- | -------------
[**existCitizen**](NotificacionsApi.md#existCitizen) | **GET** /secure/mobilenotification/existcitizen | Consulta si tenim donat d&#x27;alta el mòbil d&#x27;un ciutadà/empresa a partir del seu NIF.
[**help**](NotificacionsApi.md#help) | **GET** /secure/mobilenotification/help | Envia un missatge al mòbil del ciutada a traves de l&#x27;App de Carpeta.
[**sendNotificationToMobile**](NotificacionsApi.md#sendNotificationToMobile) | **GET** /secure/mobilenotification/sendnotificationtomobile | Envia un missatge al mòbil del ciutada a traves de l&#x27;App de Carpeta.

<a name="existCitizen"></a>
# **existCitizen**
> Boolean existCitizen(nif, lang)

Consulta si tenim donat d&#x27;alta el mòbil d&#x27;un ciutadà/empresa a partir del seu NIF.

### Example
```java
// Import classes:
//import es.caib.carpeta.apiinterna.client.services.ApiClient;
//import es.caib.carpeta.apiinterna.client.services.ApiException;
//import es.caib.carpeta.apiinterna.client.services.Configuration;
//import es.caib.carpeta.apiinterna.client.services.auth.*;
//import es.caib.carpeta.apiinterna.client.api.NotificacionsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();
// Configure HTTP basic authorization: BasicAuth
HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
BasicAuth.setUsername("YOUR USERNAME");
BasicAuth.setPassword("YOUR PASSWORD");

NotificacionsApi apiInstance = new NotificacionsApi();
String nif = "nif_example"; // String | NIF del Ciutadà o l'empresa
String lang = "lang_example"; // String | Codi de l'idioma
try {
    Boolean result = apiInstance.existCitizen(nif, lang);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificacionsApi#existCitizen");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nif** | **String**| NIF del Ciutadà o l&#x27;empresa |
 **lang** | **String**| Codi de l&#x27;idioma |

### Return type

**Boolean**

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="help"></a>
# **help**
> String help(notificationCode, langError)

Envia un missatge al mòbil del ciutada a traves de l&#x27;App de Carpeta.

### Example
```java
// Import classes:
//import es.caib.carpeta.apiinterna.client.services.ApiClient;
//import es.caib.carpeta.apiinterna.client.services.ApiException;
//import es.caib.carpeta.apiinterna.client.services.Configuration;
//import es.caib.carpeta.apiinterna.client.services.auth.*;
//import es.caib.carpeta.apiinterna.client.api.NotificacionsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();
// Configure HTTP basic authorization: BasicAuth
HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
BasicAuth.setUsername("YOUR USERNAME");
BasicAuth.setPassword("YOUR PASSWORD");

NotificacionsApi apiInstance = new NotificacionsApi();
String notificationCode = "notificationCode_example"; // String | Codi de la notificació. Demanar a l'administrador de Carpeta.
String langError = "langError_example"; // String | Idioma en que s'enviaran els missatges d'error
try {
    String result = apiInstance.help(notificationCode, langError);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificacionsApi#help");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **notificationCode** | **String**| Codi de la notificació. Demanar a l&#x27;administrador de Carpeta. |
 **langError** | **String**| Idioma en que s&#x27;enviaran els missatges d&#x27;error |

### Return type

**String**

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="sendNotificationToMobile"></a>
# **sendNotificationToMobile**
> SendMessageResult sendNotificationToMobile(nif, notificationCode, notificationParameters, notificationLang, langError)

Envia un missatge al mòbil del ciutada a traves de l&#x27;App de Carpeta.

### Example
```java
// Import classes:
//import es.caib.carpeta.apiinterna.client.services.ApiClient;
//import es.caib.carpeta.apiinterna.client.services.ApiException;
//import es.caib.carpeta.apiinterna.client.services.Configuration;
//import es.caib.carpeta.apiinterna.client.services.auth.*;
//import es.caib.carpeta.apiinterna.client.api.NotificacionsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();
// Configure HTTP basic authorization: BasicAuth
HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
BasicAuth.setUsername("YOUR USERNAME");
BasicAuth.setPassword("YOUR PASSWORD");

NotificacionsApi apiInstance = new NotificacionsApi();
String nif = "nif_example"; // String | NIF del Ciutadà o l'empresa
String notificationCode = "notificationCode_example"; // String | Codi de la notificació. Demanar a l'administrador de Carpeta.
List<String> notificationParameters = Arrays.asList("notificationParameters_example"); // List<String> | Paràmetres associats al Codi de la notificació
String notificationLang = "notificationLang_example"; // String | Idioma en que s'enviaran les notificacion
String langError = "langError_example"; // String | Idioma en que s'enviaran els missatges d'error
try {
    SendMessageResult result = apiInstance.sendNotificationToMobile(nif, notificationCode, notificationParameters, notificationLang, langError);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificacionsApi#sendNotificationToMobile");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nif** | **String**| NIF del Ciutadà o l&#x27;empresa |
 **notificationCode** | **String**| Codi de la notificació. Demanar a l&#x27;administrador de Carpeta. |
 **notificationParameters** | [**List&lt;String&gt;**](String.md)| Paràmetres associats al Codi de la notificació |
 **notificationLang** | **String**| Idioma en que s&#x27;enviaran les notificacion |
 **langError** | **String**| Idioma en que s&#x27;enviaran els missatges d&#x27;error |

### Return type

[**SendMessageResult**](SendMessageResult.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

