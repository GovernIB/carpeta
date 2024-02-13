# InformaciNotibApi

All URIs are relative to */notibapi/interna*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getAppInfo**](InformaciNotibApi.md#getAppInfo) | **GET** /api/rest/appinfo | Consulta la informació de la API

<a name="getAppInfo"></a>
# **getAppInfo**
> AppInfo getAppInfo()

Consulta la informació de la API

Retorna la data i la versió de la API REST Interna Notib

### Example
```java
// Import classes:
//import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiException;
//import org.fundaciobit.pluginsib.carpetafront.notib2client.api.InformaciNotibApi;


InformaciNotibApi apiInstance = new InformaciNotibApi();
try {
    AppInfo result = apiInstance.getAppInfo();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InformaciNotibApi#getAppInfo");
    e.printStackTrace();
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

