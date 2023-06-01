package es.caib.carpeta.apiexterna.client.api;

import es.caib.carpeta.apiexterna.client.services.ApiException;
import es.caib.carpeta.apiexterna.client.services.ApiClient;
import es.caib.carpeta.apiexterna.client.services.Configuration;
import es.caib.carpeta.apiexterna.client.services.Pair;

import javax.ws.rs.core.GenericType;

import es.caib.carpeta.apiexterna.client.model.CertificatBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
   * Retorna un certificat a CARPETA
   * 
   * @param dni DNI o NIF de la persona de la qual volem obtenir el certificat. (optional)
   * @param idioma Codi de l&#x27;idioma (optional)
   * @return CertificatBean
   * @throws ApiException if fails to make API call
   */
  public CertificatBean certificats(String dni, String idioma) throws ApiException {
    Object localVarPostBody = null;
    // create path and map variables
    String localVarPath = "/secure/certificats/certificat".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dni", dni));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "idioma", idioma));

    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<CertificatBean> localVarReturnType = new GenericType<CertificatBean>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
}
