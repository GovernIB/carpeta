package es.caib.carpeta.core.utils;

import java.util.List;

public class Paginacion {

    private static final int RESULTADOS_PAGINACION = 10;

    private int totalResults;
    private int totalPages;
    private int beginIndex;
    private int endIndex;
    private int currentIndex;
    private List<?> listado;

    public Paginacion(int total, int pageNumber) {
        totalResults = total;
        totalPages = (totalResults / RESULTADOS_PAGINACION);
        if(totalResults % RESULTADOS_PAGINACION != 0){
            totalPages = totalPages +1;
        }

        currentIndex = pageNumber;
        beginIndex = Math.max(1, currentIndex - RESULTADOS_PAGINACION);
        endIndex = Math.min(beginIndex + 10, totalPages);
    }

    public Paginacion(int total, int pageNumber, int totalPaginacion) {
        totalResults = total;
        totalPages = (totalResults / totalPaginacion);
        if(totalResults % totalPaginacion != 0){
            totalPages = totalPages +1;
        }

        currentIndex = pageNumber;
        beginIndex = Math.max(1, currentIndex - totalPaginacion);
        endIndex = Math.min(beginIndex + 10, totalPages);
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
