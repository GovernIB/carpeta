# CertificatsApi

All URIs are relative to */*

Method | HTTP request | Description
------------- | ------------- | -------------
[**descarregarCertificat**](CertificatsApi.md#descarregarCertificat) | **GET** /secure/certificats/descarregarCertificat | Retorna un certificat a CARPETA
[**teCertificat**](CertificatsApi.md#teCertificat) | **GET** /secure/certificats/teCertificat | Retorna un CertificatInfo que indica en un boolea si l&#x27;usuari te certificat 

<a name="descarregarCertificat"></a>
# **descarregarCertificat**
> CertificatBean descarregarCertificat(dni, idioma)

Retorna un certificat a CARPETA

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
try {
    CertificatBean result = apiInstance.descarregarCertificat(dni, idioma);
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

### Return type

[**CertificatBean**](CertificatBean.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="teCertificat"></a>
# **teCertificat**
> CertificatInfo teCertificat(dni)

Retorna un CertificatInfo que indica en un boolea si l&#x27;usuari te certificat 

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
try {
    CertificatInfo result = apiInstance.teCertificat(dni);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CertificatsApi#teCertificat");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dni** | **String**| DNI o NIF de la persona de la qual volem saber si té certificat. | [optional]

### Return type

[**CertificatInfo**](CertificatInfo.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

