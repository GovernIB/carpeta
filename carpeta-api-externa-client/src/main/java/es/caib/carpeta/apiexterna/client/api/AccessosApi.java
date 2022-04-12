package es.caib.carpeta.apiexterna.client.api;

import es.caib.carpeta.apiexterna.client.services.ApiException;
import es.caib.carpeta.apiexterna.client.services.ApiClient;
import es.caib.carpeta.apiexterna.client.services.Configuration;
import es.caib.carpeta.apiexterna.client.services.Pair;

import javax.ws.rs.core.GenericType;

import es.caib.carpeta.apiexterna.client.model.PaginaAcces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccessosApi {
  private ApiClient apiClient;

  public AccessosApi() {
    this(Configuration.getDefaultApiClient());
  }

  public AccessosApi(ApiClient apiClient) {
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
   * @param entitat Codi de l&#x27;entitat de la qual obtenim les estadistiques (required)
   * @param inici Data d&#x27;inici, en format YYYY-MM-DD, a partir de la qual volem obtenir estadistiques (optional)
   * @param fi Data fi, en format YYYY-MM-DD, fins la qual volem tenir estadistiques (optional)
   * @param idioma Codi de l&#x27;idioma (optional)
   * @return PaginaAcces
   * @throws ApiException if fails to make API call
   * @deprecated
   */
  @Deprecated
  public PaginaAcces accessos(String entitat, String inici, String fi, String idioma) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'entitat' is set
    if (entitat == null) {
      throw new ApiException(400, "Missing the required parameter 'entitat' when calling accessos");
    }
    // create path and map variables
    String localVarPath = "/services/accessos".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "inici", inici));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "fi", fi));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "entitat", entitat));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "idioma", idioma));

    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<PaginaAcces> localVarReturnType = new GenericType<PaginaAcces>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
}
