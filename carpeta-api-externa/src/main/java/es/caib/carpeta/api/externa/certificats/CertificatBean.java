package es.caib.carpeta.api.externa.certificats;

import com.fasterxml.jackson.annotation.JsonCreator;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Dades d'un Certificat
 * 
 * @author fbosch
 *
 */

@Schema(name = "CertificatBean")
public class CertificatBean {

    private CertificatType tipus;
    private CertificatFileInfo fitxer;
    private String url;

    @JsonCreator
    public CertificatBean() {
    }

    public CertificatType getTipus() {
        return tipus;
    }

    public void setTipus(CertificatType tipus) {
        this.tipus = tipus;
    }

    public CertificatFileInfo getFitxer() {
        return fitxer;
    }

    public void setFitxer(CertificatFileInfo fitxer) {
        this.fitxer = fitxer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
