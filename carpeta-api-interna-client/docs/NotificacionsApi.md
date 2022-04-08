# NotificacionsApi

All URIs are relative to *http://localhost:8080/carpetaapi/interna*

Method | HTTP request | Description
------------- | ------------- | -------------
[**existCiutada**](NotificacionsApi.md#existCiutada) | **GET** /secure/mobilenotification/existciutada | Consulta si tenim donat d&#x27;alta el mòbil d&#x27;un ciutadà/empresa a partir del seu NIF.
[**sendMessage**](NotificacionsApi.md#sendMessage) | **GET** /secure/mobilenotification/sendmessage | Envia un missatge al mòbil del ciutada a traves de l&#x27;App de Carpeta.

<a name="existCiutada"></a>
# **existCiutada**
> Boolean existCiutada(nif, lang)

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
String nif = "nif_example"; // String | NIF del Ciutadà o l'entitat
String lang = "lang_example"; // String | Codi de l'idioma
try {
    Boolean result = apiInstance.existCiutada(nif, lang);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificacionsApi#existCiutada");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nif** | **String**| NIF del Ciutadà o l&#x27;entitat |
 **lang** | **String**| Codi de l&#x27;idioma | [optional]

### Return type

**Boolean**

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="sendMessage"></a>
# **sendMessage**
> String sendMessage(nif, title, message, lang)

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
String nif = "nif_example"; // String | NIF del Ciutadà o l'entitat
String title = "title_example"; // String | Títol de la notificació
String message = "message_example"; // String | Missatge de la notificació
String lang = "lang_example"; // String | Codi de l'idioma
try {
    String result = apiInstance.sendMessage(nif, title, message, lang);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificacionsApi#sendMessage");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nif** | **String**| NIF del Ciutadà o l&#x27;entitat |
 **title** | **String**| Títol de la notificació |
 **message** | **String**| Missatge de la notificació |
 **lang** | **String**| Codi de l&#x27;idioma | [optional]

### Return type

**String**

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

