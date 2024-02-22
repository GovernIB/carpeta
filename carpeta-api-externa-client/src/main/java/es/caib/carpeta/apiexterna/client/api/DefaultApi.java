package es.caib.carpeta.apiexterna.client.api;

import es.caib.carpeta.apiexterna.client.services.ApiException;
import es.caib.carpeta.apiexterna.client.services.ApiClient;
import es.caib.carpeta.apiexterna.client.services.Configuration;
import es.caib.carpeta.apiexterna.client.services.Pair;

import javax.ws.rs.core.GenericType;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultApi {
  private ApiClient apiClient;

  public DefaultApi() {
    this(Configuration.getDefaultApiClient());
  }

  public DefaultApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * 
   * 
   * @param nif  (required)
   * @throws ApiException if fails to make API call
   */
  public void consultaNotificacions(String nif) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'nif' is set
    if (nif == null) {
      throw new ApiException(400, "Missing the required parameter 'nif' when calling consultaNotificacions");
    }
    // create path and map variables
    String localVarPath = "/public/estadistiques/consulta/v2/notificacions/{nif}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "nif" + "\\}", apiClient.escapeString(nif.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
  }
}
