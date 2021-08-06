# DefaultApi

All URIs are relative to */carpetaapi/externa*

Method | HTTP request | Description
------------- | ------------- | -------------
[**accessos**](DefaultApi.md#accessos) | **GET** /services/accessos | Retorna la llista d&#x60;accessos a CARPETA
[**echo**](DefaultApi.md#echo) | **GET** /services/secure | Fa un ECHO

<a name="accessos"></a>
# **accessos**
> PaginaAcces accessos(entitat, inici, fi, idioma)

Retorna la llista d&#x60;accessos a CARPETA

### Example
```java
// Import classes:
//import es.caib.carpeta.api.client.services.ApiException;
//import es.caib.carpeta.api.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String entitat = "entitat_example"; // String | Codi de l'entitat de la qual obtenim les estadistiques
String inici = "inici_example"; // String | Data d'inici, en format YYYY-MM-DD, a partir de la qual volem obtenir estadistiques
String fi = "fi_example"; // String | Data fi, en format YYYY-MM-DD, fins la qual volem tenir estadistiques
String idioma = "idioma_example"; // String | Codi de l'idioma
try {
    PaginaAcces result = apiInstance.accessos(entitat, inici, fi, idioma);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#accessos");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **entitat** | **String**| Codi de l&#x27;entitat de la qual obtenim les estadistiques |
 **inici** | **String**| Data d&#x27;inici, en format YYYY-MM-DD, a partir de la qual volem obtenir estadistiques | [optional]
 **fi** | **String**| Data fi, en format YYYY-MM-DD, fins la qual volem tenir estadistiques | [optional]
 **idioma** | **String**| Codi de l&#x27;idioma | [optional]

### Return type

[**PaginaAcces**](PaginaAcces.md)

### Authorization

No authorization required

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
//import es.caib.carpeta.api.client.services.ApiException;
//import es.caib.carpeta.api.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
String echoInput = "echoInput_example"; // String | Cadena a retornar
try {
    String result = apiInstance.echo(echoInput);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#echo");
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

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

