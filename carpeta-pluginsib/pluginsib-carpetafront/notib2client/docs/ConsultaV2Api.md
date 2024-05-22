# ConsultaV2Api

All URIs are relative to */notibapi/interna*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**comunicacionsByTitular**](ConsultaV2Api.md#comunicacionsByTitular) | **GET** /consulta/v2/comunicacions/{dniTitular} | Consulta totes les comunicacions d&#39;un titular donat el seu dni |
| [**comunicacionsLlegidesByTitular**](ConsultaV2Api.md#comunicacionsLlegidesByTitular) | **GET** /consulta/v2/comunicacions/{dniTitular}/llegides | Consulta totes les comunicacions llegides d&#39;un titular donat el seu dni |
| [**comunicacionsPendentsByTitular**](ConsultaV2Api.md#comunicacionsPendentsByTitular) | **GET** /consulta/v2/comunicacions/{dniTitular}/pendents | Consulta totes les comunicacions pendents (no llegides) d&#39;un titular donat el seu dni |
| [**getCertificacio**](ConsultaV2Api.md#getCertificacio) | **GET** /consulta/v2/certificacio/{enviamentId} | Obté la certificació d&#39;una notificació |
| [**getDocument**](ConsultaV2Api.md#getDocument) | **GET** /consulta/v2/document/{notificacioId} | Obté el document d&#39;una notificació |
| [**getJustificant**](ConsultaV2Api.md#getJustificant) | **GET** /consulta/v2/justificant/{enviamentId} | Obté el justificant d&#39;una comunicació |
| [**notificacionsByTitular**](ConsultaV2Api.md#notificacionsByTitular) | **GET** /consulta/v2/notificacions/{dniTitular} | Consulta totes les notificacions d&#39;un titular donat el seu dni |
| [**notificacionsLlegidesByTitular**](ConsultaV2Api.md#notificacionsLlegidesByTitular) | **GET** /consulta/v2/notificacions/{dniTitular}/llegides | Consulta totes les notificacions llegides d&#39;un titular donat el seu dni |
| [**notificacionsPendentsByTitular**](ConsultaV2Api.md#notificacionsPendentsByTitular) | **GET** /consulta/v2/notificacions/{dniTitular}/pendents | Consulta totes les notificacions pendents (no llegides) d&#39;un titular donat el seu dni |



## comunicacionsByTitular

> RespostaConsultaV2 comunicacionsByTitular(dniTitular, dataInicial, dataFinal, visibleCarpeta, lang, pagina, mida)

Consulta totes les comunicacions d&#39;un titular donat el seu dni

Retorna informació de totes les comunicacions d&#39;un titular, i el seu estat

### Example

```java
// Import classes:
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiClient;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiException;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.Configuration;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.auth.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.models.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.api.ConsultaV2Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/notibapi/interna");
        
        // Configure HTTP basic authorization: basic
        HttpBasicAuth basic = (HttpBasicAuth) defaultClient.getAuthentication("basic");
        basic.setUsername("YOUR USERNAME");
        basic.setPassword("YOUR PASSWORD");

        ConsultaV2Api apiInstance = new ConsultaV2Api(defaultClient);
        String dniTitular = "dniTitular_example"; // String | DNI del titular de les comunicacions a consultar
        Date dataInicial = new Date(); // Date | Data inicial d'enviament a consultar
        Date dataFinal = new Date(); // Date | Datfa final d'enviament a consultar
        Boolean visibleCarpeta = true; // Boolean | Filtrar per visible a carpeta. Si s'indica el valor si, només es retornaran enviaments amb estats visibles per la carpeta. Si s'indica el valor no, es retornaran tots els enviaments independentment de si els seus estats son visible o no a la carpeta.
        String lang = "CA"; // String | Idioma de les descripcions
        Integer pagina = 56; // Integer | Número de pàgina a mostrar en la paginació
        Integer mida = 56; // Integer | Mida de la pàgina a mostrar en la paginació
        try {
            RespostaConsultaV2 result = apiInstance.comunicacionsByTitular(dniTitular, dataInicial, dataFinal, visibleCarpeta, lang, pagina, mida);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ConsultaV2Api#comunicacionsByTitular");
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
| **dniTitular** | **String**| DNI del titular de les comunicacions a consultar | |
| **dataInicial** | **Date**| Data inicial d&#39;enviament a consultar | [optional] |
| **dataFinal** | **Date**| Datfa final d&#39;enviament a consultar | [optional] |
| **visibleCarpeta** | **Boolean**| Filtrar per visible a carpeta. Si s&#39;indica el valor si, només es retornaran enviaments amb estats visibles per la carpeta. Si s&#39;indica el valor no, es retornaran tots els enviaments independentment de si els seus estats son visible o no a la carpeta. | [optional] |
| **lang** | **String**| Idioma de les descripcions | [optional] [enum: CA, ES] |
| **pagina** | **Integer**| Número de pàgina a mostrar en la paginació | [optional] |
| **mida** | **Integer**| Mida de la pàgina a mostrar en la paginació | [optional] |

### Return type

[**RespostaConsultaV2**](RespostaConsultaV2.md)

### Authorization

[basic](../README.md#basic)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **200** | Notificacions/Comunicacions per titular |  -  |


## comunicacionsLlegidesByTitular

> RespostaConsultaV2 comunicacionsLlegidesByTitular(dniTitular, dataInicial, dataFinal, visibleCarpeta, lang, pagina, mida)

Consulta totes les comunicacions llegides d&#39;un titular donat el seu dni

Retorna informació sobre les comunicacions ja llegides d&#39;un titular, i el seu estat

### Example

```java
// Import classes:
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiClient;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiException;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.Configuration;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.auth.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.models.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.api.ConsultaV2Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/notibapi/interna");
        
        // Configure HTTP basic authorization: basic
        HttpBasicAuth basic = (HttpBasicAuth) defaultClient.getAuthentication("basic");
        basic.setUsername("YOUR USERNAME");
        basic.setPassword("YOUR PASSWORD");

        ConsultaV2Api apiInstance = new ConsultaV2Api(defaultClient);
        String dniTitular = "dniTitular_example"; // String | DNI del titular de les comunicacions a consultar
        Date dataInicial = new Date(); // Date | Data inicial d'enviament a consultar
        Date dataFinal = new Date(); // Date | Datfa final d'enviament a consultar
        Boolean visibleCarpeta = true; // Boolean | Filtrar per visible a carpeta. Si s'indica el valor si, només es retornaran enviaments amb estats visibles per la carpeta. Si s'indica el valor no, es retornaran tots els enviaments independentment de si els seus estats son visible o no a la carpeta.
        String lang = "CA"; // String | Idioma de les descripcions
        Integer pagina = 56; // Integer | Número de pàgina a mostrar en la paginació
        Integer mida = 56; // Integer | Mida de la pàgina a mostrar en la paginació
        try {
            RespostaConsultaV2 result = apiInstance.comunicacionsLlegidesByTitular(dniTitular, dataInicial, dataFinal, visibleCarpeta, lang, pagina, mida);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ConsultaV2Api#comunicacionsLlegidesByTitular");
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
| **dniTitular** | **String**| DNI del titular de les comunicacions a consultar | |
| **dataInicial** | **Date**| Data inicial d&#39;enviament a consultar | [optional] |
| **dataFinal** | **Date**| Datfa final d&#39;enviament a consultar | [optional] |
| **visibleCarpeta** | **Boolean**| Filtrar per visible a carpeta. Si s&#39;indica el valor si, només es retornaran enviaments amb estats visibles per la carpeta. Si s&#39;indica el valor no, es retornaran tots els enviaments independentment de si els seus estats son visible o no a la carpeta. | [optional] |
| **lang** | **String**| Idioma de les descripcions | [optional] [enum: CA, ES] |
| **pagina** | **Integer**| Número de pàgina a mostrar en la paginació | [optional] |
| **mida** | **Integer**| Mida de la pàgina a mostrar en la paginació | [optional] |

### Return type

[**RespostaConsultaV2**](RespostaConsultaV2.md)

### Authorization

[basic](../README.md#basic)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **200** | Notificacions/Comunicacions per titular |  -  |


## comunicacionsPendentsByTitular

> RespostaConsultaV2 comunicacionsPendentsByTitular(dniTitular, dataInicial, dataFinal, visibleCarpeta, lang, pagina, mida)

Consulta totes les comunicacions pendents (no llegides) d&#39;un titular donat el seu dni

Retorna informació sobre les comunicacions pendents d&#39;un titular, i el seu estat

### Example

```java
// Import classes:
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiClient;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiException;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.Configuration;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.auth.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.models.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.api.ConsultaV2Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/notibapi/interna");
        
        // Configure HTTP basic authorization: basic
        HttpBasicAuth basic = (HttpBasicAuth) defaultClient.getAuthentication("basic");
        basic.setUsername("YOUR USERNAME");
        basic.setPassword("YOUR PASSWORD");

        ConsultaV2Api apiInstance = new ConsultaV2Api(defaultClient);
        String dniTitular = "dniTitular_example"; // String | DNI del titular de les comunicacions a consultar
        Date dataInicial = new Date(); // Date | Data inicial d'enviament a consultar
        Date dataFinal = new Date(); // Date | Datfa final d'enviament a consultar
        Boolean visibleCarpeta = true; // Boolean | Filtrar per visible a carpeta. Si s'indica el valor si, només es retornaran enviaments amb estats visibles per la carpeta. Si s'indica el valor no, es retornaran tots els enviaments independentment de si els seus estats son visible o no a la carpeta.
        String lang = "CA"; // String | Idioma de les descripcions
        Integer pagina = 56; // Integer | Número de pàgina a mostrar en la paginació
        Integer mida = 56; // Integer | Mida de la pàgina a mostrar en la paginació
        try {
            RespostaConsultaV2 result = apiInstance.comunicacionsPendentsByTitular(dniTitular, dataInicial, dataFinal, visibleCarpeta, lang, pagina, mida);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ConsultaV2Api#comunicacionsPendentsByTitular");
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
| **dniTitular** | **String**| DNI del titular de les comunicacions a consultar | |
| **dataInicial** | **Date**| Data inicial d&#39;enviament a consultar | [optional] |
| **dataFinal** | **Date**| Datfa final d&#39;enviament a consultar | [optional] |
| **visibleCarpeta** | **Boolean**| Filtrar per visible a carpeta. Si s&#39;indica el valor si, només es retornaran enviaments amb estats visibles per la carpeta. Si s&#39;indica el valor no, es retornaran tots els enviaments independentment de si els seus estats son visible o no a la carpeta. | [optional] |
| **lang** | **String**| Idioma de les descripcions | [optional] [enum: CA, ES] |
| **pagina** | **Integer**| Número de pàgina a mostrar en la paginació | [optional] |
| **mida** | **Integer**| Mida de la pàgina a mostrar en la paginació | [optional] |

### Return type

[**RespostaConsultaV2**](RespostaConsultaV2.md)

### Authorization

[basic](../README.md#basic)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **200** | Notificacions/Comunicacions per titular |  -  |


## getCertificacio

> Arxiu getCertificacio(enviamentId)

Obté la certificació d&#39;una notificació

Retorna el document de certificació de lectura de la notificació. El contingut del document està en Base64

### Example

```java
// Import classes:
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiClient;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiException;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.Configuration;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.auth.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.models.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.api.ConsultaV2Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/notibapi/interna");
        
        // Configure HTTP basic authorization: basic
        HttpBasicAuth basic = (HttpBasicAuth) defaultClient.getAuthentication("basic");
        basic.setUsername("YOUR USERNAME");
        basic.setPassword("YOUR PASSWORD");

        ConsultaV2Api apiInstance = new ConsultaV2Api(defaultClient);
        Long enviamentId = 56L; // Long | Identificador de l'enviament de la que es vol obtenir la certificació
        try {
            Arxiu result = apiInstance.getCertificacio(enviamentId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ConsultaV2Api#getCertificacio");
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
| **enviamentId** | **Long**| Identificador de l&#39;enviament de la que es vol obtenir la certificació | |

### Return type

[**Arxiu**](Arxiu.md)

### Authorization

[basic](../README.md#basic)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **200** | Notificacions/Comunicacions per titular |  -  |


## getDocument

> Arxiu getDocument(notificacioId)

Obté el document d&#39;una notificació

Retorna el document de la notificació. El contingut del document està en Base64

### Example

```java
// Import classes:
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiClient;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiException;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.Configuration;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.auth.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.models.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.api.ConsultaV2Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/notibapi/interna");
        
        // Configure HTTP basic authorization: basic
        HttpBasicAuth basic = (HttpBasicAuth) defaultClient.getAuthentication("basic");
        basic.setUsername("YOUR USERNAME");
        basic.setPassword("YOUR PASSWORD");

        ConsultaV2Api apiInstance = new ConsultaV2Api(defaultClient);
        Long notificacioId = 56L; // Long | Identificador de la notificació de la que es vol obtenir el document
        try {
            Arxiu result = apiInstance.getDocument(notificacioId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ConsultaV2Api#getDocument");
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
| **notificacioId** | **Long**| Identificador de la notificació de la que es vol obtenir el document | |

### Return type

[**Arxiu**](Arxiu.md)

### Authorization

[basic](../README.md#basic)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **200** | Notificacions/Comunicacions per titular |  -  |


## getJustificant

> Arxiu getJustificant(enviamentId)

Obté el justificant d&#39;una comunicació

Retorna el document de justificant de entrega de la comunicació. El contingut del document està en Base64

### Example

```java
// Import classes:
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiClient;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiException;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.Configuration;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.auth.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.models.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.api.ConsultaV2Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/notibapi/interna");
        
        // Configure HTTP basic authorization: basic
        HttpBasicAuth basic = (HttpBasicAuth) defaultClient.getAuthentication("basic");
        basic.setUsername("YOUR USERNAME");
        basic.setPassword("YOUR PASSWORD");

        ConsultaV2Api apiInstance = new ConsultaV2Api(defaultClient);
        Long enviamentId = 56L; // Long | Identificador de l'enviament de la que es vol obtenir el justificant
        try {
            Arxiu result = apiInstance.getJustificant(enviamentId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ConsultaV2Api#getJustificant");
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
| **enviamentId** | **Long**| Identificador de l&#39;enviament de la que es vol obtenir el justificant | |

### Return type

[**Arxiu**](Arxiu.md)

### Authorization

[basic](../README.md#basic)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **200** | Notificacions/Comunicacions per titular |  -  |


## notificacionsByTitular

> RespostaConsultaV2 notificacionsByTitular(dniTitular, dataInicial, dataFinal, visibleCarpeta, lang, pagina, mida)

Consulta totes les notificacions d&#39;un titular donat el seu dni

Retorna informació de totes les notificacions d&#39;un titular, i el seu estat

### Example

```java
// Import classes:
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiClient;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiException;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.Configuration;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.auth.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.models.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.api.ConsultaV2Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/notibapi/interna");
        
        // Configure HTTP basic authorization: basic
        HttpBasicAuth basic = (HttpBasicAuth) defaultClient.getAuthentication("basic");
        basic.setUsername("YOUR USERNAME");
        basic.setPassword("YOUR PASSWORD");

        ConsultaV2Api apiInstance = new ConsultaV2Api(defaultClient);
        String dniTitular = "dniTitular_example"; // String | DNI del titular de les comunicacions a consultar
        Date dataInicial = new Date(); // Date | Data inicial d'enviament a consultar
        Date dataFinal = new Date(); // Date | Datfa final d'enviament a consultar
        Boolean visibleCarpeta = true; // Boolean | Filtrar per visible a carpeta. Si s'indica el valor si, només es retornaran enviaments amb estats visibles per la carpeta. Si s'indica el valor no, es retornaran tots els enviaments independentment de si els seus estats son visible o no a la carpeta.
        String lang = "CA"; // String | Idioma de les descripcions
        Integer pagina = 56; // Integer | Número de pàgina a mostrar en la paginació
        Integer mida = 56; // Integer | Mida de la pàgina a mostrar en la paginació
        try {
            RespostaConsultaV2 result = apiInstance.notificacionsByTitular(dniTitular, dataInicial, dataFinal, visibleCarpeta, lang, pagina, mida);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ConsultaV2Api#notificacionsByTitular");
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
| **dniTitular** | **String**| DNI del titular de les comunicacions a consultar | |
| **dataInicial** | **Date**| Data inicial d&#39;enviament a consultar | [optional] |
| **dataFinal** | **Date**| Datfa final d&#39;enviament a consultar | [optional] |
| **visibleCarpeta** | **Boolean**| Filtrar per visible a carpeta. Si s&#39;indica el valor si, només es retornaran enviaments amb estats visibles per la carpeta. Si s&#39;indica el valor no, es retornaran tots els enviaments independentment de si els seus estats son visible o no a la carpeta. | [optional] |
| **lang** | **String**| Idioma de les descripcions | [optional] [enum: CA, ES] |
| **pagina** | **Integer**| Número de pàgina a mostrar en la paginació | [optional] |
| **mida** | **Integer**| Mida de la pàgina a mostrar en la paginació | [optional] |

### Return type

[**RespostaConsultaV2**](RespostaConsultaV2.md)

### Authorization

[basic](../README.md#basic)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **200** | Notificacions/Comunicacions per titular |  -  |


## notificacionsLlegidesByTitular

> RespostaConsultaV2 notificacionsLlegidesByTitular(dniTitular, dataInicial, dataFinal, visibleCarpeta, lang, pagina, mida)

Consulta totes les notificacions llegides d&#39;un titular donat el seu dni

Retorna informació sobre les notificacions ja llegides d&#39;un titular, i el seu estat

### Example

```java
// Import classes:
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiClient;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiException;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.Configuration;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.auth.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.models.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.api.ConsultaV2Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/notibapi/interna");
        
        // Configure HTTP basic authorization: basic
        HttpBasicAuth basic = (HttpBasicAuth) defaultClient.getAuthentication("basic");
        basic.setUsername("YOUR USERNAME");
        basic.setPassword("YOUR PASSWORD");

        ConsultaV2Api apiInstance = new ConsultaV2Api(defaultClient);
        String dniTitular = "dniTitular_example"; // String | DNI del titular de les comunicacions a consultar
        Date dataInicial = new Date(); // Date | Data inicial d'enviament a consultar
        Date dataFinal = new Date(); // Date | Datfa final d'enviament a consultar
        Boolean visibleCarpeta = true; // Boolean | Filtrar per visible a carpeta. Si s'indica el valor si, només es retornaran enviaments amb estats visibles per la carpeta. Si s'indica el valor no, es retornaran tots els enviaments independentment de si els seus estats son visible o no a la carpeta.
        String lang = "CA"; // String | Idioma de les descripcions
        Integer pagina = 56; // Integer | Número de pàgina a mostrar en la paginació
        Integer mida = 56; // Integer | Mida de la pàgina a mostrar en la paginació
        try {
            RespostaConsultaV2 result = apiInstance.notificacionsLlegidesByTitular(dniTitular, dataInicial, dataFinal, visibleCarpeta, lang, pagina, mida);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ConsultaV2Api#notificacionsLlegidesByTitular");
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
| **dniTitular** | **String**| DNI del titular de les comunicacions a consultar | |
| **dataInicial** | **Date**| Data inicial d&#39;enviament a consultar | [optional] |
| **dataFinal** | **Date**| Datfa final d&#39;enviament a consultar | [optional] |
| **visibleCarpeta** | **Boolean**| Filtrar per visible a carpeta. Si s&#39;indica el valor si, només es retornaran enviaments amb estats visibles per la carpeta. Si s&#39;indica el valor no, es retornaran tots els enviaments independentment de si els seus estats son visible o no a la carpeta. | [optional] |
| **lang** | **String**| Idioma de les descripcions | [optional] [enum: CA, ES] |
| **pagina** | **Integer**| Número de pàgina a mostrar en la paginació | [optional] |
| **mida** | **Integer**| Mida de la pàgina a mostrar en la paginació | [optional] |

### Return type

[**RespostaConsultaV2**](RespostaConsultaV2.md)

### Authorization

[basic](../README.md#basic)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **200** | Notificacions/Comunicacions per titular |  -  |


## notificacionsPendentsByTitular

> RespostaConsultaV2 notificacionsPendentsByTitular(dniTitular, dataInicial, dataFinal, visibleCarpeta, lang, pagina, mida)

Consulta totes les notificacions pendents (no llegides) d&#39;un titular donat el seu dni

Retorna informació sobre les notificacions pendents d&#39;un titular, i el seu estat

### Example

```java
// Import classes:
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiClient;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiException;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.Configuration;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.auth.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.models.*;
import org.fundaciobit.pluginsib.carpetafront.notib2client.api.ConsultaV2Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/notibapi/interna");
        
        // Configure HTTP basic authorization: basic
        HttpBasicAuth basic = (HttpBasicAuth) defaultClient.getAuthentication("basic");
        basic.setUsername("YOUR USERNAME");
        basic.setPassword("YOUR PASSWORD");

        ConsultaV2Api apiInstance = new ConsultaV2Api(defaultClient);
        String dniTitular = "dniTitular_example"; // String | DNI del titular de les comunicacions a consultar
        Date dataInicial = new Date(); // Date | Data inicial d'enviament a consultar
        Date dataFinal = new Date(); // Date | Datfa final d'enviament a consultar
        Boolean visibleCarpeta = true; // Boolean | Filtrar per visible a carpeta. Si s'indica el valor si, només es retornaran enviaments amb estats visibles per la carpeta. Si s'indica el valor no, es retornaran tots els enviaments independentment de si els seus estats son visible o no a la carpeta.
        String lang = "CA"; // String | Idioma de les descripcions
        Integer pagina = 56; // Integer | Número de pàgina a mostrar en la paginació
        Integer mida = 56; // Integer | Mida de la pàgina a mostrar en la paginació
        try {
            RespostaConsultaV2 result = apiInstance.notificacionsPendentsByTitular(dniTitular, dataInicial, dataFinal, visibleCarpeta, lang, pagina, mida);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ConsultaV2Api#notificacionsPendentsByTitular");
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
| **dniTitular** | **String**| DNI del titular de les comunicacions a consultar | |
| **dataInicial** | **Date**| Data inicial d&#39;enviament a consultar | [optional] |
| **dataFinal** | **Date**| Datfa final d&#39;enviament a consultar | [optional] |
| **visibleCarpeta** | **Boolean**| Filtrar per visible a carpeta. Si s&#39;indica el valor si, només es retornaran enviaments amb estats visibles per la carpeta. Si s&#39;indica el valor no, es retornaran tots els enviaments independentment de si els seus estats son visible o no a la carpeta. | [optional] |
| **lang** | **String**| Idioma de les descripcions | [optional] [enum: CA, ES] |
| **pagina** | **Integer**| Número de pàgina a mostrar en la paginació | [optional] |
| **mida** | **Integer**| Mida de la pàgina a mostrar en la paginació | [optional] |

### Return type

[**RespostaConsultaV2**](RespostaConsultaV2.md)

### Authorization

[basic](../README.md#basic)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **200** | Notificacions/Comunicacions per titular |  -  |

