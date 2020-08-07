package es.caib.carpeta.pluginsib.carpetafront.api;

/**
 * 
 * @author anadal
 *
 */
public class BasicServiceInformation {
    
    private String transactionID;

    private Boolean existsInformation;
    
    private String redirectUrl;
    
    

    public BasicServiceInformation() {
        super();
        // TODO Auto-generated constructor stub
    }

    public BasicServiceInformation(String transactionID, Boolean existsInformation, String redirectUrl) {
        super();
        this.transactionID = transactionID;
        this.existsInformation = existsInformation;
        this.redirectUrl = redirectUrl;
    }

    public Boolean getExistsInformation() {
        return existsInformation;
    }

    public void setExistsInformation(Boolean existsInformation) {
        this.existsInformation = existsInformation;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }
    
    
    
    
    
    
}
