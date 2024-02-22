package org.fundaciobit.pluginsib.carpetafront.notib2client.api;

import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiException;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.ApiClient;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.Configuration;
import org.fundaciobit.pluginsib.carpetafront.notib2client.services.Pair;

  import javax.ws.rs.core.GenericType;

import org.fundaciobit.pluginsib.carpetafront.notib2client.model.Arxiu;
import org.joda.time.LocalDate;
import org.fundaciobit.pluginsib.carpetafront.notib2client.model.RespostaConsultaV2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ConsultaV2Api {
  private ApiClient apiClient;

  public ConsultaV2Api() {
    this(Configuration.getDefaultApiClient());
  }

  public ConsultaV2Api(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Consulta totes les comunicacions d&#x27;un titular donat el seu dni
   * Retorna informació de totes les comunicacions d&#x27;un titular, i el seu estat
   * @param dniTitular DNI del titular de les comunicacions a consultar (required)
   * @param dataInicial Data inicial d&#x27;enviament a consultar (optional)
   * @param dataFinal Datfa final d&#x27;enviament a consultar (optional)
   * @param visibleCarpeta Filtrar per visible a carpeta. Si s&#x27;indica el valor si, només es retornaran enviaments amb estats visibles per la carpeta. Si s&#x27;indica el valor no, es retornaran tots els enviaments independentment de si els seus estats son visible o no a la carpeta. (optional)
   * @param lang Idioma de les descripcions (optional)
   * @param pagina Número de pàgina a mostrar en la paginació (optional)
   * @param mida Mida de la pàgina a mostrar en la paginació (optional)
   * @return RespostaConsultaV2
   * @throws ApiException if fails to make API call
   */
  public RespostaConsultaV2 comunicacionsByTitular(String dniTitular, LocalDate dataInicial, LocalDate dataFinal, Boolean visibleCarpeta, String lang, Integer pagina, Integer mida) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'dniTitular' is set
    if (dniTitular == null) {
      throw new ApiException(400, "Missing the required parameter 'dniTitular' when calling comunicacionsByTitular");
    }
    // create path and map variables
    String localVarPath = "/consulta/v2/comunicacions/{dniTitular}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "dniTitular" + "\\}", apiClient.escapeString(dniTitular.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dataInicial", dataInicial));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dataFinal", dataFinal));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "visibleCarpeta", visibleCarpeta));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "lang", lang));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "pagina", pagina));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "mida", mida));

    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "basic" };

    GenericType<RespostaConsultaV2> localVarReturnType = new GenericType<RespostaConsultaV2>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Consulta totes les comunicacions llegides d&#x27;un titular donat el seu dni
   * Retorna informació sobre les comunicacions ja llegides d&#x27;un titular, i el seu estat
   * @param dniTitular DNI del titular de les comunicacions a consultar (required)
   * @param dataInicial Data inicial d&#x27;enviament a consultar (optional)
   * @param dataFinal Datfa final d&#x27;enviament a consultar (optional)
   * @param visibleCarpeta Filtrar per visible a carpeta. Si s&#x27;indica el valor si, només es retornaran enviaments amb estats visibles per la carpeta. Si s&#x27;indica el valor no, es retornaran tots els enviaments independentment de si els seus estats son visible o no a la carpeta. (optional)
   * @param lang Idioma de les descripcions (optional)
   * @param pagina Número de pàgina a mostrar en la paginació (optional)
   * @param mida Mida de la pàgina a mostrar en la paginació (optional)
   * @return RespostaConsultaV2
   * @throws ApiException if fails to make API call
   */
  public RespostaConsultaV2 comunicacionsLlegidesByTitular(String dniTitular, LocalDate dataInicial, LocalDate dataFinal, Boolean visibleCarpeta, String lang, Integer pagina, Integer mida) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'dniTitular' is set
    if (dniTitular == null) {
      throw new ApiException(400, "Missing the required parameter 'dniTitular' when calling comunicacionsLlegidesByTitular");
    }
    // create path and map variables
    String localVarPath = "/consulta/v2/comunicacions/{dniTitular}/llegides".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "dniTitular" + "\\}", apiClient.escapeString(dniTitular.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dataInicial", dataInicial));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dataFinal", dataFinal));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "visibleCarpeta", visibleCarpeta));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "lang", lang));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "pagina", pagina));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "mida", mida));

    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "basic" };

    GenericType<RespostaConsultaV2> localVarReturnType = new GenericType<RespostaConsultaV2>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Consulta totes les comunicacions pendents (no llegides) d&#x27;un titular donat el seu dni
   * Retorna informació sobre les comunicacions pendents d&#x27;un titular, i el seu estat
   * @param dniTitular DNI del titular de les comunicacions a consultar (required)
   * @param dataInicial Data inicial d&#x27;enviament a consultar (optional)
   * @param dataFinal Datfa final d&#x27;enviament a consultar (optional)
   * @param visibleCarpeta Filtrar per visible a carpeta. Si s&#x27;indica el valor si, només es retornaran enviaments amb estats visibles per la carpeta. Si s&#x27;indica el valor no, es retornaran tots els enviaments independentment de si els seus estats son visible o no a la carpeta. (optional)
   * @param lang Idioma de les descripcions (optional)
   * @param pagina Número de pàgina a mostrar en la paginació (optional)
   * @param mida Mida de la pàgina a mostrar en la paginació (optional)
   * @return RespostaConsultaV2
   * @throws ApiException if fails to make API call
   */
  public RespostaConsultaV2 comunicacionsPendentsByTitular(String dniTitular, LocalDate dataInicial, LocalDate dataFinal, Boolean visibleCarpeta, String lang, Integer pagina, Integer mida) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'dniTitular' is set
    if (dniTitular == null) {
      throw new ApiException(400, "Missing the required parameter 'dniTitular' when calling comunicacionsPendentsByTitular");
    }
    // create path and map variables
    String localVarPath = "/consulta/v2/comunicacions/{dniTitular}/pendents".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "dniTitular" + "\\}", apiClient.escapeString(dniTitular.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dataInicial", dataInicial));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dataFinal", dataFinal));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "visibleCarpeta", visibleCarpeta));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "lang", lang));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "pagina", pagina));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "mida", mida));

    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "basic" };

    GenericType<RespostaConsultaV2> localVarReturnType = new GenericType<RespostaConsultaV2>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Obté la certificació d&#x27;una notificació
   * Retorna el document de certificació de lectura de la notificació. El contingut del document està en Base64
   * @param enviamentId Identificador de l&#x27;enviament de la que es vol obtenir la certificació (required)
   * @return Arxiu
   * @throws ApiException if fails to make API call
   */
  public Arxiu getCertificacio(Long enviamentId) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'enviamentId' is set
    if (enviamentId == null) {
      throw new ApiException(400, "Missing the required parameter 'enviamentId' when calling getCertificacio");
    }
    // create path and map variables
    String localVarPath = "/consulta/v2/certificacio/{enviamentId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "enviamentId" + "\\}", apiClient.escapeString(enviamentId.toString()));

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

    String[] localVarAuthNames = new String[] { "basic" };

    GenericType<Arxiu> localVarReturnType = new GenericType<Arxiu>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Obté el document d&#x27;una notificació
   * Retorna el document de la notificació. El contingut del document està en Base64
   * @param notificacioId Identificador de la notificació de la que es vol obtenir el document (required)
   * @return Arxiu
   * @throws ApiException if fails to make API call
   */
  public Arxiu getDocument(Long notificacioId) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'notificacioId' is set
    if (notificacioId == null) {
      throw new ApiException(400, "Missing the required parameter 'notificacioId' when calling getDocument");
    }
    // create path and map variables
    String localVarPath = "/consulta/v2/document/{notificacioId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "notificacioId" + "\\}", apiClient.escapeString(notificacioId.toString()));

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

    String[] localVarAuthNames = new String[] { "basic" };

    GenericType<Arxiu> localVarReturnType = new GenericType<Arxiu>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Obté el justificant d&#x27;una comunicació
   * Retorna el document de justificant de entrega de la comunicació. El contingut del document està en Base64
   * @param enviamentId Identificador de l&#x27;enviament de la que es vol obtenir el justificant (required)
   * @return Arxiu
   * @throws ApiException if fails to make API call
   */
  public Arxiu getJustificant(Long enviamentId) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'enviamentId' is set
    if (enviamentId == null) {
      throw new ApiException(400, "Missing the required parameter 'enviamentId' when calling getJustificant");
    }
    // create path and map variables
    String localVarPath = "/consulta/v2/justificant/{enviamentId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "enviamentId" + "\\}", apiClient.escapeString(enviamentId.toString()));

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

    String[] localVarAuthNames = new String[] { "basic" };

    GenericType<Arxiu> localVarReturnType = new GenericType<Arxiu>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Consulta totes les notificacions d&#x27;un titular donat el seu dni
   * Retorna informació de totes les notificacions d&#x27;un titular, i el seu estat
   * @param dniTitular DNI del titular de les comunicacions a consultar (required)
   * @param dataInicial Data inicial d&#x27;enviament a consultar (optional)
   * @param dataFinal Datfa final d&#x27;enviament a consultar (optional)
   * @param visibleCarpeta Filtrar per visible a carpeta. Si s&#x27;indica el valor si, només es retornaran enviaments amb estats visibles per la carpeta. Si s&#x27;indica el valor no, es retornaran tots els enviaments independentment de si els seus estats son visible o no a la carpeta. (optional)
   * @param lang Idioma de les descripcions (optional)
   * @param pagina Número de pàgina a mostrar en la paginació (optional)
   * @param mida Mida de la pàgina a mostrar en la paginació (optional)
   * @return RespostaConsultaV2
   * @throws ApiException if fails to make API call
   */
  public RespostaConsultaV2 notificacionsByTitular(String dniTitular, LocalDate dataInicial, LocalDate dataFinal, Boolean visibleCarpeta, String lang, Integer pagina, Integer mida) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'dniTitular' is set
    if (dniTitular == null) {
      throw new ApiException(400, "Missing the required parameter 'dniTitular' when calling notificacionsByTitular");
    }
    // create path and map variables
    String localVarPath = "/consulta/v2/notificacions/{dniTitular}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "dniTitular" + "\\}", apiClient.escapeString(dniTitular.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dataInicial", dataInicial));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dataFinal", dataFinal));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "visibleCarpeta", visibleCarpeta));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "lang", lang));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "pagina", pagina));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "mida", mida));

    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "basic" };

    GenericType<RespostaConsultaV2> localVarReturnType = new GenericType<RespostaConsultaV2>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Consulta totes les notificacions llegides d&#x27;un titular donat el seu dni
   * Retorna informació sobre les notificacions ja llegides d&#x27;un titular, i el seu estat
   * @param dniTitular DNI del titular de les comunicacions a consultar (required)
   * @param dataInicial Data inicial d&#x27;enviament a consultar (optional)
   * @param dataFinal Datfa final d&#x27;enviament a consultar (optional)
   * @param visibleCarpeta Filtrar per visible a carpeta. Si s&#x27;indica el valor si, només es retornaran enviaments amb estats visibles per la carpeta. Si s&#x27;indica el valor no, es retornaran tots els enviaments independentment de si els seus estats son visible o no a la carpeta. (optional)
   * @param lang Idioma de les descripcions (optional)
   * @param pagina Número de pàgina a mostrar en la paginació (optional)
   * @param mida Mida de la pàgina a mostrar en la paginació (optional)
   * @return RespostaConsultaV2
   * @throws ApiException if fails to make API call
   */
  public RespostaConsultaV2 notificacionsLlegidesByTitular(String dniTitular, LocalDate dataInicial, LocalDate dataFinal, Boolean visibleCarpeta, String lang, Integer pagina, Integer mida) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'dniTitular' is set
    if (dniTitular == null) {
      throw new ApiException(400, "Missing the required parameter 'dniTitular' when calling notificacionsLlegidesByTitular");
    }
    // create path and map variables
    String localVarPath = "/consulta/v2/notificacions/{dniTitular}/llegides".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "dniTitular" + "\\}", apiClient.escapeString(dniTitular.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dataInicial", dataInicial));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dataFinal", dataFinal));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "visibleCarpeta", visibleCarpeta));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "lang", lang));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "pagina", pagina));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "mida", mida));

    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "basic" };

    GenericType<RespostaConsultaV2> localVarReturnType = new GenericType<RespostaConsultaV2>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Consulta totes les notificacions pendents (no llegides) d&#x27;un titular donat el seu dni
   * Retorna informació sobre les notificacions pendents d&#x27;un titular, i el seu estat
   * @param dniTitular DNI del titular de les comunicacions a consultar (required)
   * @param dataInicial Data inicial d&#x27;enviament a consultar (optional)
   * @param dataFinal Datfa final d&#x27;enviament a consultar (optional)
   * @param visibleCarpeta Filtrar per visible a carpeta. Si s&#x27;indica el valor si, només es retornaran enviaments amb estats visibles per la carpeta. Si s&#x27;indica el valor no, es retornaran tots els enviaments independentment de si els seus estats son visible o no a la carpeta. (optional)
   * @param lang Idioma de les descripcions (optional)
   * @param pagina Número de pàgina a mostrar en la paginació (optional)
   * @param mida Mida de la pàgina a mostrar en la paginació (optional)
   * @return RespostaConsultaV2
   * @throws ApiException if fails to make API call
   */
  public RespostaConsultaV2 notificacionsPendentsByTitular(String dniTitular, LocalDate dataInicial, LocalDate dataFinal, Boolean visibleCarpeta, String lang, Integer pagina, Integer mida) throws ApiException {
    Object localVarPostBody = null;
    // verify the required parameter 'dniTitular' is set
    if (dniTitular == null) {
      throw new ApiException(400, "Missing the required parameter 'dniTitular' when calling notificacionsPendentsByTitular");
    }
    // create path and map variables
    String localVarPath = "/consulta/v2/notificacions/{dniTitular}/pendents".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "dniTitular" + "\\}", apiClient.escapeString(dniTitular.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dataInicial", dataInicial));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dataFinal", dataFinal));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "visibleCarpeta", visibleCarpeta));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "lang", lang));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "pagina", pagina));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "mida", mida));

    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "basic" };

    GenericType<RespostaConsultaV2> localVarReturnType = new GenericType<RespostaConsultaV2>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
}
