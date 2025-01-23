package es.caib.carpeta.api.externa.client.certificats.v1.api;

import es.caib.carpeta.api.externa.client.certificats.v1.services.ApiException;
import es.caib.carpeta.api.externa.client.certificats.v1.services.ApiClient;
import es.caib.carpeta.api.externa.client.certificats.v1.services.Configuration;
import es.caib.carpeta.api.externa.client.certificats.v1.services.Pair;

import javax.ws.rs.core.GenericType;

import es.caib.carpeta.api.externa.client.certificats.v1.model.CertificatBean;
import es.caib.carpeta.api.externa.client.certificats.v1.model.CertificatInfo;
import es.caib.carpeta.api.externa.client.certificats.v1.model.RestExceptionInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.10.0")
public class CertificatsApi {
  private ApiClient apiClient;

  public CertificatsApi() {
    this(Configuration.getDefaultApiClient());
  }

  public CertificatsApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Retorna un certificat provinent de un servei extern a CARPETA.
   * 
   * @param dni DNI o NIF de la persona de la qual volem obtenir el certificat. (required)
   * @param idioma Codi de l&#39;idioma (required)
   * @param pluginNumber Numero de plugin (optional)
   * @return a {@code CertificatBean}
   * @throws ApiException if fails to make API call
   */
  public CertificatBean descarregarCertificat(String dni, String idioma, String pluginNumber) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'dni' is set
    if (dni == null) {
      throw new ApiException(400, "Missing the required parameter 'dni' when calling descarregarCertificat");
    }
    
    // verify the required parameter 'idioma' is set
    if (idioma == null) {
      throw new ApiException(400, "Missing the required parameter 'idioma' when calling descarregarCertificat");
    }
    
    // create path and map variables
    String localVarPath = "/secure/certificats/descarregarCertificat".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dni", dni));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "idioma", idioma));

    if (pluginNumber != null)
      localVarHeaderParams.put("pluginNumber", apiClient.parameterToString(pluginNumber));

    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<CertificatBean> localVarReturnType = new GenericType<CertificatBean>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Retorna un CertificatInfo que indica si l&#39;usuari té certificat 
   * 
   * @param dni DNI o NIF de la persona de la qual volem saber si té certificat. (required)
   * @param pluginNumber Numero de plugin (optional)
   * @return a {@code CertificatInfo}
   * @throws ApiException if fails to make API call
   */
  public CertificatInfo teCertificat(String dni, String pluginNumber) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'dni' is set
    if (dni == null) {
      throw new ApiException(400, "Missing the required parameter 'dni' when calling teCertificat");
    }
    
    // create path and map variables
    String localVarPath = "/secure/certificats/teCertificat".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dni", dni));

    if (pluginNumber != null)
      localVarHeaderParams.put("pluginNumber", apiClient.parameterToString(pluginNumber));

    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<CertificatInfo> localVarReturnType = new GenericType<CertificatInfo>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}
