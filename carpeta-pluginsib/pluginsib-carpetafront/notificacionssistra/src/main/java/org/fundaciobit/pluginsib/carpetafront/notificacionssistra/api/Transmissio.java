package org.fundaciobit.pluginsib.carpetafront.notificacionssistra.api;

import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * Informació d'una Transmissió (Notificació / Comunicació)
 * 
 * @author Limit Tecnologies <limit@limit.es>
 */
@Data
public class Transmissio {

    // Notificació
    private Long id;
    private String emisor;
    private String organGestor;
    private String procediment;
    private String numExpedient;
    private String concepte;
    private String descripcio;
    private Date dataEnviament;
    private Estat estat;
    private Date dataEstat;
    private Document document;

    // Enviament
    private Persona titular;
    private List<Persona> destinataris;
    private SubEstat subestat;
    private Date dataSubestat;

    // Error
    private boolean error;
    private Date errorData;
    private String errorDescripcio;

    private String justificant;
    private String certificacio;
    
    
    
    

    public Transmissio() {
        super();
    }

    public Transmissio(Long id, String emisor, String organGestor, String procediment, String numExpedient,
            String concepte, String descripcio, Date dataEnviament, Estat estat, Date dataEstat, Document document,
            Persona titular, List<Persona> destinataris, SubEstat subestat, Date dataSubestat, boolean error,
            Date errorData, String errorDescripcio, String justificant, String certificacio) {
        super();
        this.id = id;
        this.emisor = emisor;
        this.organGestor = organGestor;
        this.procediment = procediment;
        this.numExpedient = numExpedient;
        this.concepte = concepte;
        this.descripcio = descripcio;
        this.dataEnviament = dataEnviament;
        this.estat = estat;
        this.dataEstat = dataEstat;
        this.document = document;
        this.titular = titular;
        this.destinataris = destinataris;
        this.subestat = subestat;
        this.dataSubestat = dataSubestat;
        this.error = error;
        this.errorData = errorData;
        this.errorDescripcio = errorDescripcio;
        this.justificant = justificant;
        this.certificacio = certificacio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getOrganGestor() {
        return organGestor;
    }

    public void setOrganGestor(String organGestor) {
        this.organGestor = organGestor;
    }

    public String getProcediment() {
        return procediment;
    }

    public void setProcediment(String procediment) {
        this.procediment = procediment;
    }

    public String getNumExpedient() {
        return numExpedient;
    }

    public void setNumExpedient(String numExpedient) {
        this.numExpedient = numExpedient;
    }

    public String getConcepte() {
        return concepte;
    }

    public void setConcepte(String concepte) {
        this.concepte = concepte;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public Date getDataEnviament() {
        return dataEnviament;
    }

    public void setDataEnviament(Date dataEnviament) {
        this.dataEnviament = dataEnviament;
    }

    public Estat getEstat() {
        return estat;
    }

    public void setEstat(Estat estat) {
        this.estat = estat;
    }

    public Date getDataEstat() {
        return dataEstat;
    }

    public void setDataEstat(Date dataEstat) {
        this.dataEstat = dataEstat;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Persona getTitular() {
        return titular;
    }

    public void setTitular(Persona titular) {
        this.titular = titular;
    }

    public List<Persona> getDestinataris() {
        return destinataris;
    }

    public void setDestinataris(List<Persona> destinataris) {
        this.destinataris = destinataris;
    }

    public SubEstat getSubestat() {
        return subestat;
    }

    public void setSubestat(SubEstat subestat) {
        this.subestat = subestat;
    }

    public Date getDataSubestat() {
        return dataSubestat;
    }

    public void setDataSubestat(Date dataSubestat) {
        this.dataSubestat = dataSubestat;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public Date getErrorData() {
        return errorData;
    }

    public void setErrorData(Date errorData) {
        this.errorData = errorData;
    }

    public String getErrorDescripcio() {
        return errorDescripcio;
    }

    public void setErrorDescripcio(String errorDescripcio) {
        this.errorDescripcio = errorDescripcio;
    }

    public String getJustificant() {
        return justificant;
    }

    public void setJustificant(String justificant) {
        this.justificant = justificant;
    }

    public String getCertificacio() {
        return certificacio;
    }

    public void setCertificacio(String certificacio) {
        this.certificacio = certificacio;
    }

}
