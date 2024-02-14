package es.caib.carpeta.apiexterna.client.api;

import es.caib.carpeta.apiexterna.client.services.ApiException;
import es.caib.carpeta.apiexterna.client.services.ApiClient;
import es.caib.carpeta.apiexterna.client.services.Configuration;
import es.caib.carpeta.apiexterna.client.services.Pair;

import javax.ws.rs.core.GenericType;

import es.caib.carpeta.apiexterna.client.model.LlistatPaginatAcces;
import es.caib.carpeta.apiexterna.client.model.RestExceptionInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstadistiquesApi {
  private ApiClient apiClient;

  public EstadistiquesApi() {
    this(Configuration.getDefaultApiClient());
  }

  public EstadistiquesApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Retorna la llista d&#x60;accessos a CARPETA
   * 
   * @param entitat Codi de l&#x27;entitat de la qual obtenim les estadístiques (optional)
   * @param startdate Filtre Data d&#x27;inici de la consulta. Opcional. Format yyyy-MM-dd (ISO 8601) (optional)
   * @param enddate Filtre Data final de la consulta. Opcional. Format yyyy-MM-dd (ISO 8601) (optional)
   * @param page Numero de pàgina quan el llistat és paginat. Opcional. Per defecte 1. (optional)
   * @param pageSize Número d&#x27;elements a retornar per pàgina. Opcional. Per defecte 25 (optional)
   * @param language Idioma en que s&#x27;han de retornar les dades(Només suportat &#x27;ca&#x27; o &#x27;es&#x27;) (optional, default to ca)
   * @return LlistatPaginatAcces
   * @throws ApiException if fails to make API call
   */
  public LlistatPaginatAcces accessos(String entitat, String startdate, String enddate, Integer page, Integer pageSize, String language) throws ApiException {
    Object localVarPostBody = null;
    // create path and map variables
    String localVarPath = "/public/estadistiques/accessos".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "entitat", entitat));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "startdate", startdate));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "enddate", enddate));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "page", page));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "page-size", pageSize));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "language", language));

    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<LlistatPaginatAcces> localVarReturnType = new GenericType<LlistatPaginatAcces>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
}
