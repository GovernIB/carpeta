package es.caib.carpeta.api.externa.certificats;


import io.swagger.v3.oas.annotations.media.Schema;

//import org.eclipse.microprofile.openapi.annotations.media.Schema;

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
	
	public CertificatBean() {
	}

    public CertificatType getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = CertificatType.valueOf(tipus);
    }
    public CertificatFileInfo getFitxer() {
        return fitxer;
    }

    public void setFitxer(CertificatFileInfo fitxer) {
        this.fitxer = fitxer;
    }

	
	
	
	
}
