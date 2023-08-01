# CertificatsApi

All URIs are relative to *../../carpetaapi/externa*

Method | HTTP request | Description
------------- | ------------- | -------------
[**descarregarCertificat**](CertificatsApi.md#descarregarCertificat) | **GET** /secure/certificats/descarregarCertificat | Retorna un certificat provinent de un servei extern a CARPETA.
[**teCertificat**](CertificatsApi.md#teCertificat) | **GET** /secure/certificats/teCertificat | Retorna un CertificatInfo que indica si l&#x27;usuari té certificat 

<a name="descarregarCertificat"></a>
# **descarregarCertificat**
> CertificatBean descarregarCertificat(dni, idioma, pluginNumber)

Retorna un certificat provinent de un servei extern a CARPETA.

### Example
```java
// Import classes:
//import es.caib.carpeta.apicertificats.client.services.ApiClient;
//import es.caib.carpeta.apicertificats.client.services.ApiException;
//import es.caib.carpeta.apicertificats.client.services.Configuration;
//import es.caib.carpeta.apicertificats.client.services.auth.*;
//import es.caib.carpeta.apicertificats.client.api.CertificatsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();
// Configure HTTP basic authorization: BasicAuth
HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
BasicAuth.setUsername("YOUR USERNAME");
BasicAuth.setPassword("YOUR PASSWORD");

CertificatsApi apiInstance = new CertificatsApi();
String dni = "dni_example"; // String | DNI o NIF de la persona de la qual volem obtenir el certificat.
String idioma = "idioma_example"; // String | Codi de l'idioma
String pluginNumber = "pluginNumber_example"; // String | Numero de plugin
try {
    CertificatBean result = apiInstance.descarregarCertificat(dni, idioma, pluginNumber);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CertificatsApi#descarregarCertificat");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dni** | **String**| DNI o NIF de la persona de la qual volem obtenir el certificat. |
 **idioma** | **String**| Codi de l&#x27;idioma |
 **pluginNumber** | **String**| Numero de plugin | [optional]

### Return type

[**CertificatBean**](CertificatBean.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="teCertificat"></a>
# **teCertificat**
> CertificatInfo teCertificat(dni, pluginNumber)

Retorna un CertificatInfo que indica si l&#x27;usuari té certificat 

### Example
```java
// Import classes:
//import es.caib.carpeta.apicertificats.client.services.ApiClient;
//import es.caib.carpeta.apicertificats.client.services.ApiException;
//import es.caib.carpeta.apicertificats.client.services.Configuration;
//import es.caib.carpeta.apicertificats.client.services.auth.*;
//import es.caib.carpeta.apicertificats.client.api.CertificatsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();
// Configure HTTP basic authorization: BasicAuth
HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
BasicAuth.setUsername("YOUR USERNAME");
BasicAuth.setPassword("YOUR PASSWORD");

CertificatsApi apiInstance = new CertificatsApi();
String dni = "dni_example"; // String | DNI o NIF de la persona de la qual volem saber si té certificat.
String pluginNumber = "pluginNumber_example"; // String | Numero de plugin
try {
    CertificatInfo result = apiInstance.teCertificat(dni, pluginNumber);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CertificatsApi#teCertificat");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dni** | **String**| DNI o NIF de la persona de la qual volem saber si té certificat. |
 **pluginNumber** | **String**| Numero de plugin | [optional]

### Return type

[**CertificatInfo**](CertificatInfo.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

