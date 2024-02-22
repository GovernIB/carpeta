# DefaultApi

All URIs are relative to *../../carpetaapi/externa*

Method | HTTP request | Description
------------- | ------------- | -------------
[**consultaNotificacions**](DefaultApi.md#consultaNotificacions) | **GET** /public/estadistiques/consulta/v2/notificacions/{nif} | 

<a name="consultaNotificacions"></a>
# **consultaNotificacions**
> consultaNotificacions(nif)



### Example
```java
// Import classes:
//import es.caib.carpeta.apiexterna.client.services.ApiException;
//import es.caib.carpeta.apiexterna.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String nif = "nif_example"; // String | 
try {
    apiInstance.consultaNotificacions(nif);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#consultaNotificacions");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nif** | **String**|  |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

