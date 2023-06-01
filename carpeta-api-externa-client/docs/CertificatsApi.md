# CertificatsApi

All URIs are relative to *../../carpetaapi/externa*

Method | HTTP request | Description
------------- | ------------- | -------------
[**certificats**](CertificatsApi.md#certificats) | **GET** /secure/certificats/certificat | Retorna un certificat a CARPETA

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
//import es.caib.carpeta.apiexterna.client.api.CertificatsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();
// Configure HTTP basic authorization: BasicAuth
HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
BasicAuth.setUsername("YOUR USERNAME");
BasicAuth.setPassword("YOUR PASSWORD");

CertificatsApi apiInstance = new CertificatsApi();
String dni = "dni_example"; // String | DNI o NIF de la persona de la qual volem obtenir el certificat.
String idioma = "idioma_example"; // String | Codi de l'idioma
try {
    CertificatBean result = apiInstance.certificats(dni, idioma);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CertificatsApi#certificats");
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

