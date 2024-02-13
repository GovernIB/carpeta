# NotificacioV2Api

All URIs are relative to */notibapi/interna*

Method | HTTP request | Description
------------- | ------------- | -------------
[**alta**](NotificacioV2Api.md#alta) | **POST** /notificacio/v2/alta | Registra i envia la notificació a Notific@.
[**consultaDadesRegistre**](NotificacioV2Api.md#consultaDadesRegistre) | **POST** /notificacio/v2/consultaDadesRegistre | Genera el justificant i consulta la informació del registre d&#x27;una notificació.
[**consultaEstatEnviament**](NotificacioV2Api.md#consultaEstatEnviament) | **GET** /notificacio/v2/consultaEstatEnviament/** | Consulta la informació de l&#x27;estat d&#x27;un enviament dins Notific@
[**consultaEstatNotificacio**](NotificacioV2Api.md#consultaEstatNotificacio) | **GET** /notificacio/v2/consultaEstatNotificacio/** | Consulta de la informació d&#x27;una notificació
[**consultaJustificantV2**](NotificacioV2Api.md#consultaJustificantV2) | **GET** /notificacio/v2/consultaJustificantNotificacio/** | Consulta el justificant de l&#x27;enviament d&#x27;una notificació
[**donarPermisConsultaV2**](NotificacioV2Api.md#donarPermisConsultaV2) | **POST** /notificacio/v2/permisConsulta | Donar permis de consulta a un usuari sobre un procediment

<a name="alta"></a>
# **alta**
> RespostaAltaV2 alta(body)

Registra i envia la notificació a Notific@.

Retorna una llista amb els codis dels enviaments creats per poder consultar el seu estat posteriorment

### Example
```java
// Import classes:
//import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiClient;
//import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiException;
//import org.fundaciobit.pluginsib.carpetafront.notib2client.services.Configuration;
//import org.fundaciobit.pluginsib.carpetafront.notib2client.services.auth.*;
//import org.fundaciobit.pluginsib.carpetafront.notib2client.api.NotificacioV2Api;

ApiClient defaultClient = Configuration.getDefaultApiClient();
// Configure HTTP basic authorization: basic
HttpBasicAuth basic = (HttpBasicAuth) defaultClient.getAuthentication("basic");
basic.setUsername("YOUR USERNAME");
basic.setPassword("YOUR PASSWORD");

NotificacioV2Api apiInstance = new NotificacioV2Api();
NotificacioV2 body = new NotificacioV2(); // NotificacioV2 | 
try {
    RespostaAltaV2 result = apiInstance.alta(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificacioV2Api#alta");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**NotificacioV2**](NotificacioV2.md)|  |

### Return type

[**RespostaAltaV2**](RespostaAltaV2.md)

### Authorization

[basic](../README.md#basic)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="consultaDadesRegistre"></a>
# **consultaDadesRegistre**
> RespostaConsultaDadesRegistreV2 consultaDadesRegistre(body)

Genera el justificant i consulta la informació del registre d&#x27;una notificació.

Retorna la informació del registre i el justificant d&#x27;una notificació dins Notib.

### Example
```java
// Import classes:
//import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiClient;
//import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiException;
//import org.fundaciobit.pluginsib.carpetafront.notib2client.services.Configuration;
//import org.fundaciobit.pluginsib.carpetafront.notib2client.services.auth.*;
//import org.fundaciobit.pluginsib.carpetafront.notib2client.api.NotificacioV2Api;

ApiClient defaultClient = Configuration.getDefaultApiClient();
// Configure HTTP basic authorization: basic
HttpBasicAuth basic = (HttpBasicAuth) defaultClient.getAuthentication("basic");
basic.setUsername("YOUR USERNAME");
basic.setPassword("YOUR PASSWORD");

NotificacioV2Api apiInstance = new NotificacioV2Api();
DadesConsulta body = new DadesConsulta(); // DadesConsulta | 
try {
    RespostaConsultaDadesRegistreV2 result = apiInstance.consultaDadesRegistre(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificacioV2Api#consultaDadesRegistre");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**DadesConsulta**](DadesConsulta.md)|  |

### Return type

[**RespostaConsultaDadesRegistreV2**](RespostaConsultaDadesRegistreV2.md)

### Authorization

[basic](../README.md#basic)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="consultaEstatEnviament"></a>
# **consultaEstatEnviament**
> RespostaConsultaEstatEnviamentV2 consultaEstatEnviament(referencia)

Consulta la informació de l&#x27;estat d&#x27;un enviament dins Notific@

Retorna la informació sobre l&#x27;estat de l&#x27;enviament dins Notific@.

### Example
```java
// Import classes:
//import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiClient;
//import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiException;
//import org.fundaciobit.pluginsib.carpetafront.notib2client.services.Configuration;
//import org.fundaciobit.pluginsib.carpetafront.notib2client.services.auth.*;
//import org.fundaciobit.pluginsib.carpetafront.notib2client.api.NotificacioV2Api;

ApiClient defaultClient = Configuration.getDefaultApiClient();
// Configure HTTP basic authorization: basic
HttpBasicAuth basic = (HttpBasicAuth) defaultClient.getAuthentication("basic");
basic.setUsername("YOUR USERNAME");
basic.setPassword("YOUR PASSWORD");

NotificacioV2Api apiInstance = new NotificacioV2Api();
String referencia = "referencia_example"; // String | Referència de la notificació a consultar.   * A la url del mètode es mostra aquesta referència com a '**' degut a que per compatibilitat amb versions antigues, es poden trobar referències que contenen el caràcter '/'.   * Actualment les referències tenen el format de UUID
try {
    RespostaConsultaEstatEnviamentV2 result = apiInstance.consultaEstatEnviament(referencia);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificacioV2Api#consultaEstatEnviament");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **referencia** | **String**| Referència de la notificació a consultar.   * A la url del mètode es mostra aquesta referència com a &#x27;**&#x27; degut a que per compatibilitat amb versions antigues, es poden trobar referències que contenen el caràcter &#x27;/&#x27;.   * Actualment les referències tenen el format de UUID |

### Return type

[**RespostaConsultaEstatEnviamentV2**](RespostaConsultaEstatEnviamentV2.md)

### Authorization

[basic](../README.md#basic)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="consultaEstatNotificacio"></a>
# **consultaEstatNotificacio**
> RespostaConsultaEstatNotificacioV2 consultaEstatNotificacio(identificador)

Consulta de la informació d&#x27;una notificació

Retorna la informació sobre l&#x27;estat de l&#x27;enviament dins Notib o Notific@

### Example
```java
// Import classes:
//import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiClient;
//import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiException;
//import org.fundaciobit.pluginsib.carpetafront.notib2client.services.Configuration;
//import org.fundaciobit.pluginsib.carpetafront.notib2client.services.auth.*;
//import org.fundaciobit.pluginsib.carpetafront.notib2client.api.NotificacioV2Api;

ApiClient defaultClient = Configuration.getDefaultApiClient();
// Configure HTTP basic authorization: basic
HttpBasicAuth basic = (HttpBasicAuth) defaultClient.getAuthentication("basic");
basic.setUsername("YOUR USERNAME");
basic.setPassword("YOUR PASSWORD");

NotificacioV2Api apiInstance = new NotificacioV2Api();
String identificador = "identificador_example"; // String | Identificador de la notificació a consultar.   * A la url del mètode es mostra aquest identificador com a '**' degut a que per compatibilitat amb versions antigues, es poden trobar identificadors que contenen el caràcter '/'.   * Actualment els identificadors tenen el format de UUID
try {
    RespostaConsultaEstatNotificacioV2 result = apiInstance.consultaEstatNotificacio(identificador);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificacioV2Api#consultaEstatNotificacio");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **identificador** | **String**| Identificador de la notificació a consultar.   * A la url del mètode es mostra aquest identificador com a &#x27;**&#x27; degut a que per compatibilitat amb versions antigues, es poden trobar identificadors que contenen el caràcter &#x27;/&#x27;.   * Actualment els identificadors tenen el format de UUID |

### Return type

[**RespostaConsultaEstatNotificacioV2**](RespostaConsultaEstatNotificacioV2.md)

### Authorization

[basic](../README.md#basic)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="consultaJustificantV2"></a>
# **consultaJustificantV2**
> RespostaConsultaJustificantEnviament consultaJustificantV2(identificador)

Consulta el justificant de l&#x27;enviament d&#x27;una notificació

Retorna el document PDF amb el justificant de l&#x27;enviament de la notificació

### Example
```java
// Import classes:
//import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiClient;
//import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiException;
//import org.fundaciobit.pluginsib.carpetafront.notib2client.services.Configuration;
//import org.fundaciobit.pluginsib.carpetafront.notib2client.services.auth.*;
//import org.fundaciobit.pluginsib.carpetafront.notib2client.api.NotificacioV2Api;

ApiClient defaultClient = Configuration.getDefaultApiClient();
// Configure HTTP basic authorization: basic
HttpBasicAuth basic = (HttpBasicAuth) defaultClient.getAuthentication("basic");
basic.setUsername("YOUR USERNAME");
basic.setPassword("YOUR PASSWORD");

NotificacioV2Api apiInstance = new NotificacioV2Api();
String identificador = "identificador_example"; // String | Identificador de la notificació a consultar.   * A la url del mètode es mostra aquest identificador com a '**' degut a que per compatibilitat amb versions antigues, es poden trobar identificadors que contenen el caràcter '/'.   * Actualment els identificadors tenen el format de UUID
try {
    RespostaConsultaJustificantEnviament result = apiInstance.consultaJustificantV2(identificador);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificacioV2Api#consultaJustificantV2");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **identificador** | **String**| Identificador de la notificació a consultar.   * A la url del mètode es mostra aquest identificador com a &#x27;**&#x27; degut a que per compatibilitat amb versions antigues, es poden trobar identificadors que contenen el caràcter &#x27;/&#x27;.   * Actualment els identificadors tenen el format de UUID |

### Return type

[**RespostaConsultaJustificantEnviament**](RespostaConsultaJustificantEnviament.md)

### Authorization

[basic](../README.md#basic)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="donarPermisConsultaV2"></a>
# **donarPermisConsultaV2**
> String donarPermisConsultaV2(body)

Donar permis de consulta a un usuari sobre un procediment

Aquest mètode permet donar el permís de consulta a un usuari específic

### Example
```java
// Import classes:
//import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiClient;
//import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiException;
//import org.fundaciobit.pluginsib.carpetafront.notib2client.services.Configuration;
//import org.fundaciobit.pluginsib.carpetafront.notib2client.services.auth.*;
//import org.fundaciobit.pluginsib.carpetafront.notib2client.api.NotificacioV2Api;

ApiClient defaultClient = Configuration.getDefaultApiClient();
// Configure HTTP basic authorization: basic
HttpBasicAuth basic = (HttpBasicAuth) defaultClient.getAuthentication("basic");
basic.setUsername("YOUR USERNAME");
basic.setPassword("YOUR PASSWORD");

NotificacioV2Api apiInstance = new NotificacioV2Api();
PermisConsulta body = new PermisConsulta(); // PermisConsulta | 
try {
    String result = apiInstance.donarPermisConsultaV2(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificacioV2Api#donarPermisConsultaV2");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**PermisConsulta**](PermisConsulta.md)|  |

### Return type

**String**

### Authorization

[basic](../README.md#basic)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

