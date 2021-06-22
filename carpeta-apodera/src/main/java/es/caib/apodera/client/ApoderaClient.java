package es.caib.apodera.client;

import java.net.URL;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.fundaciobit.pluginsib.utils.cxf.ClientHandler;
import org.fundaciobit.pluginsib.utils.cxf.ClientHandlerCertificate;
import org.fundaciobit.pluginsib.utils.cxf.ClientHandlerUsernamePassword;

import es.caib.apodera.client.api.ConsultaApoderamientosResponse;
import es.caib.apodera.client.api.ConsultaAvanzadaPortType;
import es.caib.apodera.client.api.ConsultaAvanzadaService;
import es.caib.apodera.client.api.ErrorType;
import es.caib.apodera.client.api.PeticionConsulta;
import es.caib.apodera.client.api.RespuestaConsulta;

/**
 * 
 * @author anadal
 *
 */
@SuppressWarnings("deprecation")
public class ApoderaClient {

  // Cridada Cxf
  protected final String endPoint;

  // Username Token
  protected final String auth_up_Username;
  protected final String auth_up_Password;

  // Certificate Token
  protected final String auth_ks_Path;
  protected final String auth_ks_Type;
  protected final String auth_ks_Password;
  protected final String auth_ks_Alias;
  protected final String auth_ks_Cert_Password;

  protected long connectTimeout = 20000L;

  protected long readTimeout = 20000L;

  protected boolean debug = false;

  /**
   * @param endPoint
   * @param auth_up_Username
   * @param auth_up_Passwor
   */
  public ApoderaClient(String endPoint, String auth_up_Username, String auth_up_Password) {
    super();
    this.endPoint = endPoint;
    this.auth_up_Username = auth_up_Username;
    this.auth_up_Password = auth_up_Password;

    this.auth_ks_Path = null;
    this.auth_ks_Type = null;
    this.auth_ks_Password = null;
    this.auth_ks_Alias = null;
    this.auth_ks_Cert_Password = null;
  }

  /**
   * @param endPoint
   * @param auth_ks_Path
   * @param auth_ks_Type
   * @param auth_ks_Password
   * @param auth_ks_Alias
   * @param auth_ks_Cert_Password
   */
  public ApoderaClient(String endPoint, String auth_ks_Path, String auth_ks_Type,
      String auth_ks_Password, String auth_ks_Alias, String auth_ks_Cert_Password) {
    super();
    this.endPoint = endPoint;

    this.auth_up_Username = null;
    this.auth_up_Password = null;

    this.auth_ks_Path = auth_ks_Path;
    this.auth_ks_Type = auth_ks_Type;
    this.auth_ks_Password = auth_ks_Password;
    this.auth_ks_Alias = auth_ks_Alias;
    this.auth_ks_Cert_Password = auth_ks_Cert_Password;
  }
  
  
  
  public ConsultaApoderamientosResponse consulta(PeticionConsulta peticio) throws Exception  {
    
    ConsultaAvanzadaPortType api = getApi();
    
    RespuestaConsulta resposta = api.consultaAvanzadaApoderamientos(peticio);
    
    
    ConsultaApoderamientosResponse response = resposta.getConsultaApoderamientosResponse();
    
    ErrorType errorType = response.getResultadoError();
            
    if (errorType == null) {
      
    
         return response;
      
      
      
      
    } else {
      
      
       if ("0102".equals(errorType.getCodError())) {
          // No existe ningún apoderamiento con los parámetros especificados.
          return response;
       }

       System.out.println("errorType::CodError => " + errorType.getCodError());
       System.out.println("errorType::DesError => " + errorType.getDesError());
       
       throw new Exception("Error cridant a la consulta de apoderaments: "
           + errorType.getDesError() + " (CODI: " + errorType.getCodError()   + ")");
       
    } 
    
    
    
    
  }
  
  

  /**
   * 
   * @return
   * @throws Exception
   */
  private ConsultaAvanzadaPortType getApi() throws Exception {
    
      String url = this.getEndPoint();
    
      // XYZ ZZZ  TODO 
      URL wsdlUrl = new URL(url + /*"https://pre-reaws.redsara.es/reaCXFServer/services/reaCXFWSv2" +*/ "?wsdl");
      ConsultaAvanzadaService service = new ConsultaAvanzadaService(wsdlUrl);

      ConsultaAvanzadaPortType api = service.getConsultaAvanzadaPort();

      Map<String, Object> reqContext = ((BindingProvider) api).getRequestContext();
      reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);

      getClientHandler().addSecureHeader(api);

      // Fixar timeout
      HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
      httpClientPolicy.setConnectionTimeout(this.getConnectTimeout());
      httpClientPolicy.setReceiveTimeout(this.getReadTimeout());
      httpClientPolicy.setAllowChunking(false);

      Client client = ClientProxy.getClient(api);
      ((HTTPConduit) client.getConduit()).setClient(httpClientPolicy);
      
      // Print XML in and out
      if (isDebug()) {
        client.getInInterceptors().add(new LoggingInInterceptor());
        client.getOutInterceptors().add(new LoggingOutInterceptor());
      }
      
      
      return api;

    
  }

  private ClientHandler getClientHandler() throws Exception {
    final ClientHandler clientHandler;

    String username = auth_up_Username;
    if (username != null && username.trim().length() != 0) {
      String password = auth_up_Password;
      clientHandler = new ClientHandlerUsernamePassword(username, password);
    } else {

      clientHandler = new ClientHandlerCertificate(auth_ks_Path, auth_ks_Type,
          auth_ks_Password, auth_ks_Alias, auth_ks_Cert_Password);
    }
    return clientHandler;
  }

  public String getEndPoint() {
    return endPoint;
  }

  public String getAuth_up_Username() {
    return auth_up_Username;
  }

  public String getAuth_ks_Path() {
    return auth_ks_Path;
  }

  public String getAuth_ks_Type() {
    return auth_ks_Type;
  }

  public String getAuth_ks_Alias() {
    return auth_ks_Alias;
  }

  public long getConnectTimeout() {
    return connectTimeout;
  }

  public void setConnectTimeout(long connectTimeout) {
    this.connectTimeout = connectTimeout;
  }

  public long getReadTimeout() {
    return readTimeout;
  }

  public void setReadTimeout(long readTimeout) {
    this.readTimeout = readTimeout;
  }

  public boolean isDebug() {
    return debug;
  }

  public void setDebug(boolean debug) {
    this.debug = debug;
  }

}
