package org.fundaciobit.pluginsib.carpetafront.regweb32;

import java.util.List;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 23/02/2021
 */

public class Paginacio {

    private int totalResults;
    private int totalPages;
    private int beginIndex;
    private int endIndex;
    private int currentIndex;
    private List<?> listado;

    int RESULTATS_PER_PAGINA = 10;

    public Paginacio(int total, int pageNumber) {
        totalResults = total;
        totalPages = (totalResults / RESULTATS_PER_PAGINA);
        if(totalResults % RESULTATS_PER_PAGINA != 0){
            totalPages = totalPages +1;
        }

        currentIndex = pageNumber;
        beginIndex = Math.max(1, currentIndex - RESULTATS_PER_PAGINA);
        endIndex = Math.min(beginIndex + RESULTATS_PER_PAGINA, totalPages);
    }

    public Paginacio(int total, int pageNumber, int totalPaginacion) {
        totalResults = total;
        totalPages = (totalResults / totalPaginacion);
        if(totalResults % totalPaginacion != 0){
            totalPages = totalPages +1;
        }

        currentIndex = pageNumber;
        beginIndex = Math.max(1, currentIndex - totalPaginacion);
        endIndex = Math.min(beginIndex + RESULTATS_PER_PAGINA, totalPages);
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getBeginIndex() {
        return beginIndex;
    }


    public int getEndIndex() {
        return endIndex;
    }


    public int getCurrentIndex() {
        return currentIndex;
    }


    public List<?> getListado() {
        return listado;
    }

    public void setListado(List<?> listado) {
        this.listado = listado;
    }

    public int getTotalResults() {
        return totalResults;
    }
}
