package es.caib.carpeta.front.pluginlogin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

//import org.glassfish.jersey.client.ClientConfig;
//import org.glassfish.jersey.client.ClientProperties;
//import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * 
 * @author anadal
 *
 */
public class AbstractJersey2ConnectionManager {
    
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    final boolean ignoreServerCertificates;

    final String token;

    final String username;

    final String password;
    
    protected Integer connectionTimeoutMs = null;
    
    protected Integer readTimeoutMs = null;

    public AbstractJersey2ConnectionManager(String token, boolean ignoreServerCertificates) {
        super();
        this.token = token;
        this.username = null;
        this.password = null;
        this.ignoreServerCertificates = ignoreServerCertificates;
    }

    public AbstractJersey2ConnectionManager(String username, String password, boolean ignoreServerCertificates) {
        super();
        this.token = null;
        this.username = username;
        this.password = password;
        this.ignoreServerCertificates = ignoreServerCertificates;
    }

    public static String serializeJson(Object pojo) throws Exception {

        ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(pojo);

    }

    public static  <T> T deserializeJson(String pojo, Class<T> classe) throws Exception {

        ObjectMapper om = new ObjectMapper();

        T t = om.readValue(pojo, classe);

        return t;

    }

    protected Response commonCall(Object parameter, String endPoint) throws Exception {

        Response response;
        try {

            /* XXXX
            ClientConfig config = new ClientConfig();
            
            if (this.getConnectionTimeoutMs() != null) {
                config.property(ClientProperties.CONNECT_TIMEOUT, this.getConnectionTimeoutMs());
            }

            if (this.getReadTimeoutMs() != null) {
                config.property(ClientProperties.READ_TIMEOUT, this.getReadTimeoutMs());
            }
            
            config.property(ClientProperties.FOLLOW_REDIRECTS, Boolean.TRUE);
            

            ClientBuilder clientBuilder = ClientBuilder.newBuilder();
            
            clientBuilder.property(ClientProperties.FOLLOW_REDIRECTS, Boolean.TRUE);
            
            
           
            

            if (endPoint.toLowerCase().startsWith("https") && ignoreServerCertificates) {
                // Ignorar Certificats de la part servidora

                SSLContext ctx = SSLContext.getInstance("SSL");
                ctx.init(null, new TrustManager[] { new InsecureTrustManager() }, null);

                clientBuilder.sslContext(ctx);

                clientBuilder.hostnameVerifier(new TrustAllHostNameVerifier());

            }

            
            if (this.getConnectionTimeoutMs() != null) {
                clientBuilder.connectTimeout(this.getConnectionTimeoutMs(), TimeUnit.MILLISECONDS);
            }

            if (this.getReadTimeoutMs() != null) {
                clientBuilder.readTimeout(this.getReadTimeoutMs(), TimeUnit.MILLISECONDS);
            }
            
            
            final Client client = clientBuilder.build();
            */
            
            // NOU JAX-RS
            ClientBuilder configuration = ClientBuilder.newBuilder();
            configuration.connectTimeout(this.getConnectionTimeoutMs(), TimeUnit.MILLISECONDS);
            configuration.readTimeout(this.getReadTimeoutMs(), TimeUnit.MILLISECONDS);
            Client client = configuration.build();
            
            WebTarget webTarget = client.target(endPoint);
            
            // XXX webTarget.property(ClientProperties.FOLLOW_REDIRECTS, Boolean.TRUE);

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

            if (token != null) {
                // Authentication by Token
                invocationBuilder.header(HttpHeaders.AUTHORIZATION, "Bearer " + token);
            } else {
                // Basic Authenticatin (username and password)
                // JERSEY 2.0
                /*
                HttpAuthenticationFeature feature = HttpAuthenticationFeature.universal(this.username, this.password);
                webTarget.register(feature);
                */
                
                // JAX RS
                webTarget.register(new Authenticator(this.username, this.password));
            }

            if (parameter == null) {
                response = invocationBuilder.get(); // Entity.json(null));
            } else {
                /*
                Entity<RLoginParams> entity = Entity.entity(parameter, MediaType.APPLICATION_JSON);
                
                response = invocationBuilder.post(entity);
*/
                
                String data = serializeJson(parameter);

                System.out.println(" OBJECT PARAMETER: " + data);

                Entity<?> json = Entity.entity(data, MediaType.APPLICATION_JSON_TYPE);

                response = invocationBuilder.post(json);
                
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
        
        
        
       if (response.getStatus() == 302) {
            
            
            String newEndPoint =  response.getHeaderString("Location");
                    
            System.out.println(" \n\n LOCATION HEADER = ]" + newEndPoint + "[\n\n");

            return commonCall(parameter,newEndPoint);

        } else if (response.getStatus() == 200 || response.getStatus() == 201) {
            return response;
        } else {

            // System.out.println(" ERROR SIMPLE: ]" + simple + "[");

            // Error de Comunicació o no controlat
            String raw_msg = response.readEntity(String.class);
            
            
            
            throw new Exception("Error desconegut (Codi de servidor " + response.getStatus() + "): \n " + raw_msg
                    + response.toString());

        }

    }

    public static class TrustAllHostNameVerifier implements HostnameVerifier {

        public boolean verify(String hostname, SSLSession session) {
            return true;
        }

    }
    
    
    /**
     * 
     */
    public class Authenticator implements ClientRequestFilter {

        private final String user;
        private final String password;

        public Authenticator(String user, String password) {
            this.user = user;
            this.password = password;
        }

        public void filter(ClientRequestContext requestContext) throws IOException {
            MultivaluedMap<String, Object> headers = requestContext.getHeaders();
            final String basicAuthentication = getBasicAuthentication();
            headers.add("Authorization", basicAuthentication);

        }

        private String getBasicAuthentication() {
            String token = this.user + ":" + this.password;
            try {
                return "BASIC " + DatatypeConverter.printBase64Binary(token.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                throw new IllegalStateException("Cannot encode with UTF-8", ex);
            }
        }
    }
    

    /**
     * 
     * @author anadal
     *
     */
    public class InsecureTrustManager implements X509TrustManager {
        /**
         * {@inheritDoc}
         */
        @Override
        public void checkClientTrusted(final X509Certificate[] chain, final String authType)
                throws CertificateException {
            // Everyone is trusted!
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void checkServerTrusted(final X509Certificate[] chain, final String authType)
                throws CertificateException {
            // Everyone is trusted!
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    public Integer getConnectionTimeoutMs() {
        return connectionTimeoutMs;
    }

    public void setConnectionTimeoutMs(Integer connectionTimeoutMs) {
        this.connectionTimeoutMs = connectionTimeoutMs;
    }

    public Integer getReadTimeoutMs() {
        return readTimeoutMs;
    }

    public void setReadTimeoutMs(Integer readTimeoutMs) {
        this.readTimeoutMs = readTimeoutMs;
    }

    
    
    
}
