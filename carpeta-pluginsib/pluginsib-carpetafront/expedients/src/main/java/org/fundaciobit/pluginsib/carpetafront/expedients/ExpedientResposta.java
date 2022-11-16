package org.fundaciobit.pluginsib.carpetafront.expedients;

import java.util.List;

/**
 * 
 * @author anadal
 *
 */
public class ExpedientResposta {

    protected int paginaActual;
    protected int elementsPerPagina;

    protected int totalPagines;
    protected int registresRetornats;
    protected int totalRegistres;

    protected List<ExpedientInfo> expedients;
    
    protected String error; 

    public ExpedientResposta() {
        super();
    }
    
    public ExpedientResposta(String error) {
        super();
        this.error = error;
    }
    

    public ExpedientResposta(int paginaActual, int elementsPerPagina, int totalPagines, int registresRetornats,
            int totalRegistres, List<ExpedientInfo> expedients) {
        super();
        this.paginaActual = paginaActual;
        this.elementsPerPagina = elementsPerPagina;
        this.totalPagines = totalPagines;
        this.registresRetornats = registresRetornats;
        this.totalRegistres = totalRegistres;
        this.expedients = expedients;
    }

    public int getPaginaActual() {
        return paginaActual;
    }

    public void setPaginaActual(int paginaActual) {
        this.paginaActual = paginaActual;
    }

    public int getElementsPerPagina() {
        return elementsPerPagina;
    }

    public void setElementsPerPagina(int elementsPerPagina) {
        this.elementsPerPagina = elementsPerPagina;
    }

    public int getTotalPagines() {
        return totalPagines;
    }

    public void setTotalPagines(int totalPagines) {
        this.totalPagines = totalPagines;
    }

    public int getRegistresRetornats() {
        return registresRetornats;
    }

    public void setRegistresRetornats(int registresRetornats) {
        this.registresRetornats = registresRetornats;
    }

    public int getTotalRegistres() {
        return totalRegistres;
    }

    public void setTotalRegistres(int totalRegistres) {
        this.totalRegistres = totalRegistres;
    }

    public List<ExpedientInfo> getExpedients() {
        return expedients;
    }

    public void setExpedients(List<ExpedientInfo> expedients) {
        this.expedients = expedients;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
