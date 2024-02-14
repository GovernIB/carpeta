# EstadistiquesApi

All URIs are relative to *../../carpetaapi/externa*

Method | HTTP request | Description
------------- | ------------- | -------------
[**accessos**](EstadistiquesApi.md#accessos) | **GET** /public/estadistiques/accessos | Retorna la llista d&#x60;accessos a CARPETA

<a name="accessos"></a>
# **accessos**
> LlistatPaginatAcces accessos(entitat, startdate, enddate, page, pageSize, language)

Retorna la llista d&#x60;accessos a CARPETA

### Example
```java
// Import classes:
//import es.caib.carpeta.apiexterna.client.services.ApiException;
//import es.caib.carpeta.apiexterna.client.api.EstadistiquesApi;


EstadistiquesApi apiInstance = new EstadistiquesApi();
String entitat = "entitat_example"; // String | Codi de l'entitat de la qual obtenim les estadístiques
String startdate = "startdate_example"; // String | Filtre Data d'inici de la consulta. Opcional. Format yyyy-MM-dd (ISO 8601)
String enddate = "enddate_example"; // String | Filtre Data final de la consulta. Opcional. Format yyyy-MM-dd (ISO 8601)
Integer page = 56; // Integer | Numero de pàgina quan el llistat és paginat. Opcional. Per defecte 1.
Integer pageSize = 56; // Integer | Número d'elements a retornar per pàgina. Opcional. Per defecte 25
String language = "ca"; // String | Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')
try {
    LlistatPaginatAcces result = apiInstance.accessos(entitat, startdate, enddate, page, pageSize, language);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EstadistiquesApi#accessos");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **entitat** | **String**| Codi de l&#x27;entitat de la qual obtenim les estadístiques | [optional]
 **startdate** | **String**| Filtre Data d&#x27;inici de la consulta. Opcional. Format yyyy-MM-dd (ISO 8601) | [optional]
 **enddate** | **String**| Filtre Data final de la consulta. Opcional. Format yyyy-MM-dd (ISO 8601) | [optional]
 **page** | **Integer**| Numero de pàgina quan el llistat és paginat. Opcional. Per defecte 1. | [optional]
 **pageSize** | **Integer**| Número d&#x27;elements a retornar per pàgina. Opcional. Per defecte 25 | [optional]
 **language** | **String**| Idioma en que s&#x27;han de retornar les dades(Només suportat &#x27;ca&#x27; o &#x27;es&#x27;) | [optional] [default to ca]

### Return type

[**LlistatPaginatAcces**](LlistatPaginatAcces.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

