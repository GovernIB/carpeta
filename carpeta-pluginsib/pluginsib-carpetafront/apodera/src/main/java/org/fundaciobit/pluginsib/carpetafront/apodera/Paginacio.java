package org.fundaciobit.pluginsib.carpetafront.apodera;



/**
 * Created by Fundació BIT.
 *
 * @author anadal
 * Date: 23/02/2021
 */

public class Paginacio {

    private int paginaActual;
    private int elementsPerPagina;
    private int totalPagines;
    private int elementsRetornats;
    private int totalElements;

    
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
    public int getElementsRetornats() {
        return elementsRetornats;
    }
    public void setElementsRetornats(int elementsRetornats) {
        this.elementsRetornats = elementsRetornats;
    }
    public int getTotalElements() {
        return totalElements;
    }
    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }
    
    
}
