# SecuretatApi

All URIs are relative to *../../carpetaapi/externa*

Method | HTTP request | Description
------------- | ------------- | -------------
[**certificats**](SecuretatApi.md#certificats) | **GET** /secure/certificats/certificat | Retorna un certificat a CARPETA
[**echo**](SecuretatApi.md#echo) | **GET** /secure/echo | Fa un ECHO

<a name="certificats"></a>
# **certificats**
> CertificatBean certificats(dni, idioma)

Retorna un certificat a CARPETA

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
String dni = "dni_example"; // String | DNI o NIF de la persona de la qual volem obtenir el certificat.
String idioma = "idioma_example"; // String | Codi de l'idioma
try {
    CertificatBean result = apiInstance.certificats(dni, idioma);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SecuretatApi#certificats");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dni** | **String**| DNI o NIF de la persona de la qual volem obtenir el certificat. | [optional]
 **idioma** | **String**| Codi de l&#x27;idioma | [optional]

### Return type

[**CertificatBean**](CertificatBean.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

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

