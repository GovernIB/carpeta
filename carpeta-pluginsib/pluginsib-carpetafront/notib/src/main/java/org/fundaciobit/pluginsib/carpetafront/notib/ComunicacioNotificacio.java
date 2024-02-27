package org.fundaciobit.pluginsib.carpetafront.notib;

import java.util.Date;

import org.fundaciobit.pluginsib.carpetafront.notib2client.model.TransmissioV2;

/**
 * 
 * @author anadal
 *
 */
public class ComunicacioNotificacio {

    private Long id;
    
    private Long data;
    // Afegits
    private String tipus;

    private String dataEnviament;

    private String concepte;

    private String estatCodi;

    private String emissor;

    private String organGestor;

    private String procedimentCodi;

    private String descripcio;

    private String dataEstat;

    private String subestat;

    public ComunicacioNotificacio() {
        super();
    }

    public ComunicacioNotificacio(TransmissioV2 transmissio, String tipus) {
        super();

        this.tipus = tipus;

        this.id = transmissio.getId();
        this.data = transmissio.getDataEnviament();
        this.dataEnviament = NotibCarpetaFrontPlugin.SDF.format(new Date(transmissio.getDataEnviament()));
        this.concepte = transmissio.getConcepte();
        this.estatCodi = transmissio.getEstat().getCodi();
        this.emissor = transmissio.getEmisor();
        this.organGestor = transmissio.getOrganGestor().getNom();
        this.procedimentCodi = transmissio.getProcediment() == null ? null : transmissio.getProcediment().getCodi();
        this.descripcio = transmissio.getDescripcio();
        this.dataEstat = NotibCarpetaFrontPlugin.SDF.format(new Date(transmissio.getDataEstat()));
        this.subestat = "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public String getDataEnviament() {
        return dataEnviament;
    }

    public void setDataEnviament(String dataEnviament) {
        this.dataEnviament = dataEnviament;
    }

    public String getConcepte() {
        return concepte;
    }

    public void setConcepte(String concepte) {
        this.concepte = concepte;
    }

    public String getEstatCodi() {
        return estatCodi;
    }

    public void setEstatCodi(String estatCodi) {
        this.estatCodi = estatCodi;
    }

    public String getEmissor() {
        return emissor;
    }

    public void setEmissor(String emissor) {
        this.emissor = emissor;
    }

    public String getOrganGestor() {
        return organGestor;
    }

    public void setOrganGestor(String organGestor) {
        this.organGestor = organGestor;
    }

    public String getProcedimentCodi() {
        return procedimentCodi;
    }

    public void setProcedimentCodi(String procedimentCodi) {
        this.procedimentCodi = procedimentCodi;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getDataEstat() {
        return dataEstat;
    }

    public void setDataEstat(String dataEstat) {
        this.dataEstat = dataEstat;
    }

    public String getSubestat() {
        return subestat;
    }

    public void setSubestat(String subestat) {
        this.subestat = subestat;
    }

    public Long getData() {
        return data;
    }

    public void setData(Long data) {
        this.data = data;
    }

}