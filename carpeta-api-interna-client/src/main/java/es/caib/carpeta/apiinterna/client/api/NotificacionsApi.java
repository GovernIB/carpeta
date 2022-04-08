package es.caib.carpeta.apiinterna.client.api;

import es.caib.carpeta.apiinterna.client.services.ApiException;
import es.caib.carpeta.apiinterna.client.services.ApiClient;
import es.caib.carpeta.apiinterna.client.services.Configuration;
import es.caib.carpeta.apiinterna.client.services.Pair;

import javax.ws.rs.core.GenericType;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificacionsApi {
  private ApiClient apiClient;

  public NotificacionsApi() {
    this(Configuration.getDefaultApiClient());
  }

  public NotificacionsApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Consulta si tenim donat d&#x27;alta el mòbil d&#x27;un ciutadà/empresa a partir del seu NIF.
   * 
   * @param nif NIF del Ciutadà o l&#x27;entitat (required)
   * @param lang Codi de l&#x27;idioma (optional)
   * @return Boolean
   * @throws ApiException if fails to make API call
   */
  public Boolean existCiutada(String nif, String lang) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'nif' is set
    if (nif == null) {
      throw new ApiException(400, "Missing the required parameter 'nif' when calling existCiutada");
    }
    // create path and map variables
    String localVarPath = "/secure/mobilenotification/existciutada".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "nif", nif));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "lang", lang));

    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<Boolean> localVarReturnType = new GenericType<Boolean>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Envia un missatge al mòbil del ciutada a traves de l&#x27;App de Carpeta.
   * 
   * @param nif NIF del Ciutadà o l&#x27;entitat (required)
   * @param title Títol de la notificació (required)
   * @param message Missatge de la notificació (required)
   * @param lang Codi de l&#x27;idioma (optional)
   * @return String
   * @throws ApiException if fails to make API call
   */
  public String sendMessage(String nif, String title, String message, String lang) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'nif' is set
    if (nif == null) {
      throw new ApiException(400, "Missing the required parameter 'nif' when calling sendMessage");
    }
    // verify the required parameter 'title' is set
    if (title == null) {
      throw new ApiException(400, "Missing the required parameter 'title' when calling sendMessage");
    }
    // verify the required parameter 'message' is set
    if (message == null) {
      throw new ApiException(400, "Missing the required parameter 'message' when calling sendMessage");
    }
    // create path and map variables
    String localVarPath = "/secure/mobilenotification/sendmessage".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "nif", nif));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "title", title));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "message", message));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "lang", lang));

    
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
