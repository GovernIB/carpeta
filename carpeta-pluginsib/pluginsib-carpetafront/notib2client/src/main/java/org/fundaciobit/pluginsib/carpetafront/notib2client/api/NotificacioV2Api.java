package org.fundaciobit.pluginsib.carpetafront.notib2client.api;

import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiException;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiClient;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.Configuration;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.Pair;

  import javax.ws.rs.core.GenericType;

import org.fundaciobit.pluginsib.carpetafront.notib2client.model.DadesConsulta;
import org.fundaciobit.pluginsib.carpetafront.notib2client.model.NotificacioV2;
import org.fundaciobit.pluginsib.carpetafront.notib2client.model.PermisConsulta;
import org.fundaciobit.pluginsib.carpetafront.notib2client.model.RespostaAltaV2;
import org.fundaciobit.pluginsib.carpetafront.notib2client.model.RespostaConsultaDadesRegistreV2;
import org.fundaciobit.pluginsib.carpetafront.notib2client.model.RespostaConsultaEstatEnviamentV2;
import org.fundaciobit.pluginsib.carpetafront.notib2client.model.RespostaConsultaEstatNotificacioV2;
import org.fundaciobit.pluginsib.carpetafront.notib2client.model.RespostaConsultaJustificantEnviament;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NotificacioV2Api {
  private ApiClient apiClient;

  public NotificacioV2Api() {
    this(Configuration.getDefaultApiClient());
  }

  public NotificacioV2Api(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Registra i envia la notificació a Notific@.
   * Retorna una llista amb els codis dels enviaments creats per poder consultar el seu estat posteriorment
   * @param body  (required)
   * @return RespostaAltaV2
   * @throws ApiException if fails to make API call
   */
  public RespostaAltaV2 alta(NotificacioV2 body) throws ApiException {
    Object localVarPostBody = body;
    // verify the required parameter 'body' is set
    if (body == null) {
      throw new ApiException(400, "Missing the required parameter 'body' when calling alta");
    }
    // create path and map variables
    String localVarPath = "/notificacio/v2/alta".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "basic" };

    GenericType<RespostaAltaV2> localVarReturnType = new GenericType<RespostaAltaV2>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Genera el justificant i consulta la informació del registre d&#x27;una notificació.
   * Retorna la informació del registre i el justificant d&#x27;una notificació dins Notib.
   * @param body  (required)
   * @return RespostaConsultaDadesRegistreV2
   * @throws ApiException if fails to make API call
   */
  public RespostaConsultaDadesRegistreV2 consultaDadesRegistre(DadesConsulta body) throws ApiException {
    Object localVarPostBody = body;
    // verify the required parameter 'body' is set
    if (body == null) {
      throw new ApiException(400, "Missing the required parameter 'body' when calling consultaDadesRegistre");
    }
    // create path and map variables
    String localVarPath = "/notificacio/v2/consultaDadesRegistre".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "basic" };

    GenericType<RespostaConsultaDadesRegistreV2> localVarReturnType = new GenericType<RespostaConsultaDadesRegistreV2>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Consulta la informació de l&#x27;estat d&#x27;un enviament dins Notific@
   * Retorna la informació sobre l&#x27;estat de l&#x27;enviament dins Notific@.
   * @param referencia Referència de la notificació a consultar.   * A la url del mètode es mostra aquesta referència com a &#x27;**&#x27; degut a que per compatibilitat amb versions antigues, es poden trobar referències que contenen el caràcter &#x27;/&#x27;.   * Actualment les referències tenen el format de UUID (required)
   * @return RespostaConsultaEstatEnviamentV2
   * @throws ApiException if fails to make API call
   */
  public RespostaConsultaEstatEnviamentV2 consultaEstatEnviament(String referencia) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'referencia' is set
    if (referencia == null) {
      throw new ApiException(400, "Missing the required parameter 'referencia' when calling consultaEstatEnviament");
    }
    // create path and map variables
    String localVarPath = "/notificacio/v2/consultaEstatEnviament/**".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "referencia", referencia));

    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "basic" };

    GenericType<RespostaConsultaEstatEnviamentV2> localVarReturnType = new GenericType<RespostaConsultaEstatEnviamentV2>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Consulta de la informació d&#x27;una notificació
   * Retorna la informació sobre l&#x27;estat de l&#x27;enviament dins Notib o Notific@
   * @param identificador Identificador de la notificació a consultar.   * A la url del mètode es mostra aquest identificador com a &#x27;**&#x27; degut a que per compatibilitat amb versions antigues, es poden trobar identificadors que contenen el caràcter &#x27;/&#x27;.   * Actualment els identificadors tenen el format de UUID (required)
   * @return RespostaConsultaEstatNotificacioV2
   * @throws ApiException if fails to make API call
   */
  public RespostaConsultaEstatNotificacioV2 consultaEstatNotificacio(String identificador) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'identificador' is set
    if (identificador == null) {
      throw new ApiException(400, "Missing the required parameter 'identificador' when calling consultaEstatNotificacio");
    }
    // create path and map variables
    String localVarPath = "/notificacio/v2/consultaEstatNotificacio/**".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "identificador", identificador));

    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "basic" };

    GenericType<RespostaConsultaEstatNotificacioV2> localVarReturnType = new GenericType<RespostaConsultaEstatNotificacioV2>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Consulta el justificant de l&#x27;enviament d&#x27;una notificació
   * Retorna el document PDF amb el justificant de l&#x27;enviament de la notificació
   * @param identificador Identificador de la notificació a consultar.   * A la url del mètode es mostra aquest identificador com a &#x27;**&#x27; degut a que per compatibilitat amb versions antigues, es poden trobar identificadors que contenen el caràcter &#x27;/&#x27;.   * Actualment els identificadors tenen el format de UUID (required)
   * @return RespostaConsultaJustificantEnviament
   * @throws ApiException if fails to make API call
   */
  public RespostaConsultaJustificantEnviament consultaJustificantV2(String identificador) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'identificador' is set
    if (identificador == null) {
      throw new ApiException(400, "Missing the required parameter 'identificador' when calling consultaJustificantV2");
    }
    // create path and map variables
    String localVarPath = "/notificacio/v2/consultaJustificantNotificacio/**".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "identificador", identificador));

    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "basic" };

    GenericType<RespostaConsultaJustificantEnviament> localVarReturnType = new GenericType<RespostaConsultaJustificantEnviament>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Donar permis de consulta a un usuari sobre un procediment
   * Aquest mètode permet donar el permís de consulta a un usuari específic
   * @param body  (required)
   * @return String
   * @throws ApiException if fails to make API call
   */
  public String donarPermisConsultaV2(PermisConsulta body) throws ApiException {
    Object localVarPostBody = body;
    // verify the required parameter 'body' is set
    if (body == null) {
      throw new ApiException(400, "Missing the required parameter 'body' when calling donarPermisConsultaV2");
    }
    // create path and map variables
    String localVarPath = "/notificacio/v2/permisConsulta".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "basic" };

    GenericType<String> localVarReturnType = new GenericType<String>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
}
