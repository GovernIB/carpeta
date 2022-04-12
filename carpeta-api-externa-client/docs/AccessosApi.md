# AccessosApi

All URIs are relative to *http://localhost:8080/carpetaapi/externa*

Method | HTTP request | Description
------------- | ------------- | -------------
[**accessos**](AccessosApi.md#accessos) | **GET** /services/accessos | Retorna la llista d&#x60;accessos a CARPETA

<a name="accessos"></a>
# **accessos**
> PaginaAcces accessos(entitat, inici, fi, idioma)

Retorna la llista d&#x60;accessos a CARPETA

### Example
```java
// Import classes:
//import es.caib.carpeta.apiexterna.client.services.ApiException;
//import es.caib.carpeta.apiexterna.client.api.AccessosApi;


AccessosApi apiInstance = new AccessosApi();
String entitat = "entitat_example"; // String | Codi de l'entitat de la qual obtenim les estadistiques
String inici = "inici_example"; // String | Data d'inici, en format YYYY-MM-DD, a partir de la qual volem obtenir estadistiques
String fi = "fi_example"; // String | Data fi, en format YYYY-MM-DD, fins la qual volem tenir estadistiques
String idioma = "idioma_example"; // String | Codi de l'idioma
try {
    PaginaAcces result = apiInstance.accessos(entitat, inici, fi, idioma);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccessosApi#accessos");
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

