package es.caib.carpeta.logic.utils;

import java.util.List;

/**
 * 
 * @author anadal
 *
 */
public class SendNotificationResult {

    protected final List<String> mobileId;
    protected final String messageId;
    protected final boolean estatEnviat;
    // Re
    protected boolean estatRebut = false;

    protected String error = null;

    public SendNotificationResult(String messageId, List<String> mobileId) {
        super();
        this.messageId = messageId;
        this.mobileId = mobileId;
        this.estatEnviat = true;
    }

    public SendNotificationResult(String messageId, List<String> mobileId, String error) {
        super();
        this.messageId = messageId;
        this.mobileId = mobileId;
        this.estatEnviat = false;
        this.error = error;
    }

    public String getMessageId() {
        return messageId;
    }

    public boolean isEstatRebut() {
        return estatRebut;
    }

    public void setEstatRebut(boolean estatRebut) {
        this.estatRebut = estatRebut;
    }

    public boolean isEstatEnviat() {
        return estatEnviat;
    }

    public List<String> getMobileId() {
        return mobileId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "MobileID: " + this.mobileId + "\n" + "NotificationID: " + this.messageId + "\n" + "Enviament: "
                + this.estatEnviat + "\n" + "Recepció: " + this.estatRebut
                + ((this.error == null) ? "" : ("\nError: " + this.error));
    }

}
