package es.caib.carpeta.api.externa.certificats;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "CertificatInfo")
public class CertificatInfo {

    private boolean teCertificat;
    
    private String administrationId;
    
    public CertificatInfo() {
        
    }

    public boolean isTeCertificat() {
        return teCertificat;
    }

    public void setTeCertificat(boolean teCertificat) {
        this.teCertificat = teCertificat;
    }

    public String getAdministrationId() {
        return administrationId;
    }

    public void setAdministrationId(String administrationId) {
        this.administrationId = administrationId;
    }

    
    
}
