# NotificacioV2Api

All URIs are relative to */notibapi/interna*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**alta**](NotificacioV2Api.md#alta) | **POST** /notificacio/v2/alta | Registra i envia la notificació a Notific@. |
| [**consultaDadesRegistre**](NotificacioV2Api.md#consultaDadesRegistre) | **POST** /notificacio/v2/consultaDadesRegistre | Genera el justificant i consulta la informació del registre d&#39;una notificació. |
| [**consultaEstatEnviament**](NotificacioV2Api.md#consultaEstatEnviament) | **GET** /notificacio/v2/consultaEstatEnviament/** | Consulta la informació de l&#39;estat d&#39;un enviament dins Notific@ |
| [**consultaEstatNotificacio**](NotificacioV2Api.md#consultaEstatNotificacio) | **GET** /notificacio/v2/consultaEstatNotificacio/** | Consulta de la informació d&#39;una notificació |
| [**consultaJustificantV2**](NotificacioV2Api.md#consultaJustificantV2) | **GET** /notificacio/v2/consultaJustificantNotificacio/** | Consulta el justificant de l&#39;enviament d&#39;una notificació |
| [**donarPermisConsultaV2**](NotificacioV2Api.md#donarPermisConsultaV2) | **POST** /notificacio/v2/permisConsulta | Donar permis de consulta a un usuari sobre un procediment |



## alta

> RespostaAltaV2 alta(notificacioV2)

Registra i envia la notificació a Notific@.

Retorna una llista amb els codis dels enviaments creats per poder consultar el seu estat posteriorment

### Example

```java
// Import classes:
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiClient;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiException;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.Configuration;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.auth.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.models.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.api.NotificacioV2Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/notibapi/interna");
        
        // Configure HTTP basic authorization: basic
        HttpBasicAuth basic = (HttpBasicAuth) defaultClient.getAuthentication("basic");
        basic.setUsername("YOUR USERNAME");
        basic.setPassword("YOUR PASSWORD");

        NotificacioV2Api apiInstance = new NotificacioV2Api(defaultClient);
        NotificacioV2 notificacioV2 = new NotificacioV2(); // NotificacioV2 | 
        try {
            RespostaAltaV2 result = apiInstance.alta(notificacioV2);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling NotificacioV2Api#alta");
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
| **notificacioV2** | [**NotificacioV2**](NotificacioV2.md)|  | |

### Return type

[**RespostaAltaV2**](RespostaAltaV2.md)

### Authorization

[basic](../README.md#basic)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **201** | Alta de notificació |  -  |


## consultaDadesRegistre

> RespostaConsultaDadesRegistreV2 consultaDadesRegistre(dadesConsulta)

Genera el justificant i consulta la informació del registre d&#39;una notificació.

Retorna la informació del registre i el justificant d&#39;una notificació dins Notib.

### Example

```java
// Import classes:
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiClient;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiException;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.Configuration;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.auth.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.models.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.api.NotificacioV2Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/notibapi/interna");
        
        // Configure HTTP basic authorization: basic
        HttpBasicAuth basic = (HttpBasicAuth) defaultClient.getAuthentication("basic");
        basic.setUsername("YOUR USERNAME");
        basic.setPassword("YOUR PASSWORD");

        NotificacioV2Api apiInstance = new NotificacioV2Api(defaultClient);
        DadesConsulta dadesConsulta = new DadesConsulta(); // DadesConsulta | 
        try {
            RespostaConsultaDadesRegistreV2 result = apiInstance.consultaDadesRegistre(dadesConsulta);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling NotificacioV2Api#consultaDadesRegistre");
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
| **dadesConsulta** | [**DadesConsulta**](DadesConsulta.md)|  | |

### Return type

[**RespostaConsultaDadesRegistreV2**](RespostaConsultaDadesRegistreV2.md)

### Authorization

[basic](../README.md#basic)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **200** | Consulta realitzada correctament |  -  |


## consultaEstatEnviament

> RespostaConsultaEstatEnviamentV2 consultaEstatEnviament(referencia)

Consulta la informació de l&#39;estat d&#39;un enviament dins Notific@

Retorna la informació sobre l&#39;estat de l&#39;enviament dins Notific@.

### Example

```java
// Import classes:
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiClient;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiException;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.Configuration;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.auth.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.models.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.api.NotificacioV2Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/notibapi/interna");
        
        // Configure HTTP basic authorization: basic
        HttpBasicAuth basic = (HttpBasicAuth) defaultClient.getAuthentication("basic");
        basic.setUsername("YOUR USERNAME");
        basic.setPassword("YOUR PASSWORD");

        NotificacioV2Api apiInstance = new NotificacioV2Api(defaultClient);
        String referencia = "00000000-0000-0000-0000-000000000000"; // String | Referència de la notificació a consultar.   * A la url del mètode es mostra aquesta referència com a '**' degut a que per compatibilitat amb versions antigues, es poden trobar referències que contenen el caràcter '/'.   * Actualment les referències tenen el format de UUID
        try {
            RespostaConsultaEstatEnviamentV2 result = apiInstance.consultaEstatEnviament(referencia);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling NotificacioV2Api#consultaEstatEnviament");
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
| **referencia** | **String**| Referència de la notificació a consultar.   * A la url del mètode es mostra aquesta referència com a &#39;**&#39; degut a que per compatibilitat amb versions antigues, es poden trobar referències que contenen el caràcter &#39;/&#39;.   * Actualment les referències tenen el format de UUID | |

### Return type

[**RespostaConsultaEstatEnviamentV2**](RespostaConsultaEstatEnviamentV2.md)

### Authorization

[basic](../README.md#basic)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **200** | Consulta realitzada correctament |  -  |


## consultaEstatNotificacio

> RespostaConsultaEstatNotificacioV2 consultaEstatNotificacio(identificador)

Consulta de la informació d&#39;una notificació

Retorna la informació sobre l&#39;estat de l&#39;enviament dins Notib o Notific@

### Example

```java
// Import classes:
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiClient;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiException;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.Configuration;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.auth.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.models.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.api.NotificacioV2Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/notibapi/interna");
        
        // Configure HTTP basic authorization: basic
        HttpBasicAuth basic = (HttpBasicAuth) defaultClient.getAuthentication("basic");
        basic.setUsername("YOUR USERNAME");
        basic.setPassword("YOUR PASSWORD");

        NotificacioV2Api apiInstance = new NotificacioV2Api(defaultClient);
        String identificador = "00000000-0000-0000-0000-000000000000"; // String | Identificador de la notificació a consultar.   * A la url del mètode es mostra aquest identificador com a '**' degut a que per compatibilitat amb versions antigues, es poden trobar identificadors que contenen el caràcter '/'.   * Actualment els identificadors tenen el format de UUID
        try {
            RespostaConsultaEstatNotificacioV2 result = apiInstance.consultaEstatNotificacio(identificador);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling NotificacioV2Api#consultaEstatNotificacio");
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
| **identificador** | **String**| Identificador de la notificació a consultar.   * A la url del mètode es mostra aquest identificador com a &#39;**&#39; degut a que per compatibilitat amb versions antigues, es poden trobar identificadors que contenen el caràcter &#39;/&#39;.   * Actualment els identificadors tenen el format de UUID | |

### Return type

[**RespostaConsultaEstatNotificacioV2**](RespostaConsultaEstatNotificacioV2.md)

### Authorization

[basic](../README.md#basic)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **200** | Consulta realitzada correctament |  -  |


## consultaJustificantV2

> RespostaConsultaJustificantEnviament consultaJustificantV2(identificador)

Consulta el justificant de l&#39;enviament d&#39;una notificació

Retorna el document PDF amb el justificant de l&#39;enviament de la notificació

### Example

```java
// Import classes:
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiClient;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiException;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.Configuration;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.auth.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.models.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.api.NotificacioV2Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/notibapi/interna");
        
        // Configure HTTP basic authorization: basic
        HttpBasicAuth basic = (HttpBasicAuth) defaultClient.getAuthentication("basic");
        basic.setUsername("YOUR USERNAME");
        basic.setPassword("YOUR PASSWORD");

        NotificacioV2Api apiInstance = new NotificacioV2Api(defaultClient);
        String identificador = "00000000-0000-0000-0000-000000000000"; // String | Identificador de la notificació a consultar.   * A la url del mètode es mostra aquest identificador com a '**' degut a que per compatibilitat amb versions antigues, es poden trobar identificadors que contenen el caràcter '/'.   * Actualment els identificadors tenen el format de UUID
        try {
            RespostaConsultaJustificantEnviament result = apiInstance.consultaJustificantV2(identificador);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling NotificacioV2Api#consultaJustificantV2");
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
| **identificador** | **String**| Identificador de la notificació a consultar.   * A la url del mètode es mostra aquest identificador com a &#39;**&#39; degut a que per compatibilitat amb versions antigues, es poden trobar identificadors que contenen el caràcter &#39;/&#39;.   * Actualment els identificadors tenen el format de UUID | |

### Return type

[**RespostaConsultaJustificantEnviament**](RespostaConsultaJustificantEnviament.md)

### Authorization

[basic](../README.md#basic)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **200** | Consulta realitzada correctament |  -  |


## donarPermisConsultaV2

> String donarPermisConsultaV2(permisConsulta)

Donar permis de consulta a un usuari sobre un procediment

Aquest mètode permet donar el permís de consulta a un usuari específic

### Example

```java
// Import classes:
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiClient;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiException;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.Configuration;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.auth.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.models.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.api.NotificacioV2Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/notibapi/interna");
        
        // Configure HTTP basic authorization: basic
        HttpBasicAuth basic = (HttpBasicAuth) defaultClient.getAuthentication("basic");
        basic.setUsername("YOUR USERNAME");
        basic.setPassword("YOUR PASSWORD");

        NotificacioV2Api apiInstance = new NotificacioV2Api(defaultClient);
        PermisConsulta permisConsulta = new PermisConsulta(); // PermisConsulta | 
        try {
            String result = apiInstance.donarPermisConsultaV2(permisConsulta);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling NotificacioV2Api#donarPermisConsultaV2");
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
| **permisConsulta** | [**PermisConsulta**](PermisConsulta.md)|  | |

### Return type

**String**

### Authorization

[basic](../README.md#basic)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **200** | Permisos assignats |  -  |
| **201** | Created |  -  |

