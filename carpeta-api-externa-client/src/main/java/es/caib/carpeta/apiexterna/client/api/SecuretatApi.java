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

public class SecuretatApi {
  private ApiClient apiClient;

  public SecuretatApi() {
    this(Configuration.getDefaultApiClient());
  }

  public SecuretatApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Fa un ECHO
   * 
   * @param echoInput Cadena a retornar (optional)
   * @return String
   * @throws ApiException if fails to make API call
   */
  public String echo(String echoInput) throws ApiException {
    Object localVarPostBody = null;
    // create path and map variables
    String localVarPath = "/secure/echo".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "echoInput", echoInput));

    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<String> localVarReturnType = new GenericType<String>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
}
