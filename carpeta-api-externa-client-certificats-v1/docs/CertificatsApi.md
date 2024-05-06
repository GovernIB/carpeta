# CertificatsApi

All URIs are relative to *http://../../carpetaapi/externa*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**descarregarCertificat**](CertificatsApi.md#descarregarCertificat) | **GET** /secure/certificats/descarregarCertificat | Retorna un certificat provinent de un servei extern a CARPETA. |
| [**teCertificat**](CertificatsApi.md#teCertificat) | **GET** /secure/certificats/teCertificat | Retorna un CertificatInfo que indica si l&#39;usuari té certificat  |



## descarregarCertificat

> CertificatBean descarregarCertificat(dni, idioma, pluginNumber)

Retorna un certificat provinent de un servei extern a CARPETA.

### Example

```java
// Import classes:
import es.caib.carpeta.api.externa.client.certificats.v1.services.ApiClient;
import es.caib.carpeta.api.externa.client.certificats.v1.services.ApiException;
import es.caib.carpeta.api.externa.client.certificats.v1.services.Configuration;
import es.caib.carpeta.api.externa.client.certificats.v1.services.auth.*;
import es.caib.carpeta.api.externa.client.certificats.v1.services.models.*;
import es.caib.carpeta.api.externa.client.certificats.v1.api.CertificatsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://../../carpetaapi/externa");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        CertificatsApi apiInstance = new CertificatsApi(defaultClient);
        String dni = "99999999X"; // String | DNI o NIF de la persona de la qual volem obtenir el certificat.
        String idioma = "ca"; // String | Codi de l'idioma
        String pluginNumber = "1"; // String | Numero de plugin
        try {
            CertificatBean result = apiInstance.descarregarCertificat(dni, idioma, pluginNumber);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling CertificatsApi#descarregarCertificat");
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
| **dni** | **String**| DNI o NIF de la persona de la qual volem obtenir el certificat. | |
| **idioma** | **String**| Codi de l&#39;idioma | |
| **pluginNumber** | **String**| Numero de plugin | [optional] |

### Return type

[**CertificatBean**](CertificatBean.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **500** | Error intern de servidor |  -  |
| **404** | Paràmetres incorrectes |  -  |
| **200** | Llista d&#39;accessos a CARPETA |  -  |


## teCertificat

> CertificatInfo teCertificat(dni, pluginNumber)

Retorna un CertificatInfo que indica si l&#39;usuari té certificat 

### Example

```java
// Import classes:
import es.caib.carpeta.api.externa.client.certificats.v1.services.ApiClient;
import es.caib.carpeta.api.externa.client.certificats.v1.services.ApiException;
import es.caib.carpeta.api.externa.client.certificats.v1.services.Configuration;
import es.caib.carpeta.api.externa.client.certificats.v1.services.auth.*;
import es.caib.carpeta.api.externa.client.certificats.v1.services.models.*;
import es.caib.carpeta.api.externa.client.certificats.v1.api.CertificatsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://../../carpetaapi/externa");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        CertificatsApi apiInstance = new CertificatsApi(defaultClient);
        String dni = "99999999X"; // String | DNI o NIF de la persona de la qual volem saber si té certificat.
        String pluginNumber = "1"; // String | Numero de plugin
        try {
            CertificatInfo result = apiInstance.teCertificat(dni, pluginNumber);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling CertificatsApi#teCertificat");
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
| **dni** | **String**| DNI o NIF de la persona de la qual volem saber si té certificat. | |
| **pluginNumber** | **String**| Numero de plugin | [optional] |

### Return type

[**CertificatInfo**](CertificatInfo.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **500** | Error intern de servidor |  -  |
| **404** |  XYZ Paràmetres incorrectes |  -  |
| **200** | XYZ Llista d&#39;accessos a CARPETA |  -  |

