# SecuretatApi

All URIs are relative to *http://localhost:8080/carpetaapi/externa*

Method | HTTP request | Description
------------- | ------------- | -------------
[**echo**](SecuretatApi.md#echo) | **GET** /secure/echo | Fa un ECHO

<a name="echo"></a>
# **echo**
> String echo(echoInput)

Fa un ECHO

### Example
```java
// Import classes:
//import es.caib.carpeta.apiexterna.client.services.ApiClient;
//import es.caib.carpeta.apiexterna.client.services.ApiException;
//import es.caib.carpeta.apiexterna.client.services.Configuration;
//import es.caib.carpeta.apiexterna.client.services.auth.*;
//import es.caib.carpeta.apiexterna.client.api.SecuretatApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();
// Configure HTTP basic authorization: BasicAuth
HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
BasicAuth.setUsername("YOUR USERNAME");
BasicAuth.setPassword("YOUR PASSWORD");

SecuretatApi apiInstance = new SecuretatApi();
String echoInput = "echoInput_example"; // String | Cadena a retornar
try {
    String result = apiInstance.echo(echoInput);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SecuretatApi#echo");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **echoInput** | **String**| Cadena a retornar | [optional]

### Return type

**String**

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

