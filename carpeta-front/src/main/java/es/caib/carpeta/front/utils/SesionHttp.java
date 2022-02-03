package es.caib.carpeta.front.utils;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 * @author anadal entitatID
 *
 */
@Component("sesionHttp")
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SesionHttp {

    private String userAgent;
    private String idioma;
    private String urlEntrada;
    private String entitat;
    private long entitatID;
    private String errorLogin;
    private String nomEntitat;
    private long accesPlugin;
    private String idSessio;
    private String ipAddress;

    public SesionHttp() {
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getUrlEntrada() {
        return urlEntrada;
    }

    public void setUrlEntrada(String urlEntrada) {
        this.urlEntrada = urlEntrada;
    }

    public String getEntitat() {
        return entitat;
    }

    public void setEntitat(String entitat) {
        this.entitat = entitat;
    }

    public long getEntitatID() {
        return entitatID;
    }

    public void setEntitatID(long entitatID) {
        this.entitatID = entitatID;
    }

    public String getErrorLogin() {
        return errorLogin;
    }

    public void setErrorLogin(String errorLogin) {
        this.errorLogin = errorLogin;
    }

    public String getNomEntitat() { return nomEntitat; }

    public void setNomEntitat(String nomEntitat) {
        this.nomEntitat = nomEntitat;
    }

    public long getAccesPlugin() {
        return accesPlugin;
    }

    public void setAccesPlugin(long accesPlugin) {
        this.accesPlugin = accesPlugin;
    }
    
    public  String getIdSessio() {
    	return idSessio;
    }
    
    public void setIdSessio(String idSessio) {
    	this.idSessio = idSessio;
    }

    public  String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

}
