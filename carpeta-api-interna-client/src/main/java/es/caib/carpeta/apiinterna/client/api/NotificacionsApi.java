package es.caib.carpeta.apiinterna.client.api;

import es.caib.carpeta.apiinterna.client.services.ApiException;
import es.caib.carpeta.apiinterna.client.services.ApiClient;
import es.caib.carpeta.apiinterna.client.services.Configuration;
import es.caib.carpeta.apiinterna.client.services.Pair;

import javax.ws.rs.core.GenericType;

import es.caib.carpeta.apiinterna.client.model.SendMessageResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
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
   * Consulta si tenim donat d&#39;alta el mòbil d&#39;un ciutadà/empresa a partir del seu NIF.
   * 
   * @param nif NIF del Ciutadà o l&#39;empresa (required)
   * @param lang Codi de l&#39;idioma (required)
   * @return a {@code Boolean}
   * @throws ApiException if fails to make API call
   */
  public Boolean existCitizen(String nif, String lang) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'nif' is set
    if (nif == null) {
      throw new ApiException(400, "Missing the required parameter 'nif' when calling existCitizen");
    }
    
    // verify the required parameter 'lang' is set
    if (lang == null) {
      throw new ApiException(400, "Missing the required parameter 'lang' when calling existCitizen");
    }
    
    // create path and map variables
    String localVarPath = "/secure/mobilenotification/existcitizen".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
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
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Envia un missatge al mòbil del ciutada a traves de l&#39;App de Carpeta.
   * 
   * @param notificationCode Codi de la notificació. Demanar a l&#39;administrador de Carpeta. (required)
   * @param langError Idioma en que s&#39;enviaran els missatges d&#39;error (required)
   * @return a {@code String}
   * @throws ApiException if fails to make API call
   */
  public String help(String notificationCode, String langError) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'notificationCode' is set
    if (notificationCode == null) {
      throw new ApiException(400, "Missing the required parameter 'notificationCode' when calling help");
    }
    
    // verify the required parameter 'langError' is set
    if (langError == null) {
      throw new ApiException(400, "Missing the required parameter 'langError' when calling help");
    }
    
    // create path and map variables
    String localVarPath = "/secure/mobilenotification/help".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "notificationCode", notificationCode));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "langError", langError));

    
    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<String> localVarReturnType = new GenericType<String>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Envia un missatge al mòbil del ciutada a traves de l&#39;App de Carpeta.
   * 
   * @param nif NIF del Ciutadà o l&#39;empresa (required)
   * @param notificationCode Codi de la notificació. Demanar a l&#39;administrador de Carpeta. (required)
   * @param notificationParameters Paràmetres associats al Codi de la notificació (required)
   * @param notificationLang Idioma en que s&#39;enviaran les notificacion (required)
   * @param langError Idioma en que s&#39;enviaran els missatges d&#39;error (required)
   * @return a {@code SendMessageResult}
   * @throws ApiException if fails to make API call
   */
  public SendMessageResult sendNotificationToMobile(String nif, String notificationCode, List<String> notificationParameters, String notificationLang, String langError) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'nif' is set
    if (nif == null) {
      throw new ApiException(400, "Missing the required parameter 'nif' when calling sendNotificationToMobile");
    }
    
    // verify the required parameter 'notificationCode' is set
    if (notificationCode == null) {
      throw new ApiException(400, "Missing the required parameter 'notificationCode' when calling sendNotificationToMobile");
    }
    
    // verify the required parameter 'notificationParameters' is set
    if (notificationParameters == null) {
      throw new ApiException(400, "Missing the required parameter 'notificationParameters' when calling sendNotificationToMobile");
    }
    
    // verify the required parameter 'notificationLang' is set
    if (notificationLang == null) {
      throw new ApiException(400, "Missing the required parameter 'notificationLang' when calling sendNotificationToMobile");
    }
    
    // verify the required parameter 'langError' is set
    if (langError == null) {
      throw new ApiException(400, "Missing the required parameter 'langError' when calling sendNotificationToMobile");
    }
    
    // create path and map variables
    String localVarPath = "/secure/mobilenotification/sendnotificationtomobile".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "nif", nif));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "notificationCode", notificationCode));
    localVarQueryParams.addAll(apiClient.parameterToPairs("multi", "notificationParameters", notificationParameters));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "notificationLang", notificationLang));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "langError", langError));

    
    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<SendMessageResult> localVarReturnType = new GenericType<SendMessageResult>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}
