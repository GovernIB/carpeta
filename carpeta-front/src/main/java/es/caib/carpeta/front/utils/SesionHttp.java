package es.caib.carpeta.front.utils;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;


@Component("sesionHttp")
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SesionHttp {

    private String userAgent;
    private String idioma;
    private String urlEntrada;
    private String entitat;

    public SesionHttp() { }


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


    public String getUrlEntrada() { return urlEntrada; }


    public void setUrlEntrada(String urlEntrada) { this.urlEntrada = urlEntrada; }


    public String getEntitat() {
        return entitat;
    }


    public void setEntitat(String entitat) {
        this.entitat = entitat;
    }
}
