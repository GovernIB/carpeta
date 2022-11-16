package org.fundaciobit.pluginsib.carpetafront.expedients;

/**
 * 
 * @author anadal
 *
 */
public class ExpedientConsulta {

    /**
     * Primera pagina és la 0
     */
    protected int pagina;

    protected int elementsPerPagina;
    
    protected String language;

    public ExpedientConsulta() {
        super();
    }

    public ExpedientConsulta(String language, int pagina, int elementsPerPagina) {
        super();
        this.language = language;
        this.pagina = pagina;
        this.elementsPerPagina = elementsPerPagina;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public int getElementsPerPagina() {
        return elementsPerPagina;
    }

    public void setElementsPerPagina(int elementsPerPagina) {
        this.elementsPerPagina = elementsPerPagina;
    }

}
