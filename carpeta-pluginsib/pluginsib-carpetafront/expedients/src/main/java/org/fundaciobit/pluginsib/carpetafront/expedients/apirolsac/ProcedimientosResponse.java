package org.fundaciobit.pluginsib.carpetafront.expedients.apirolsac;

import java.util.List;

/**
 * 
 * @author anadal
 *
 */
public class ProcedimientosResponse {

    private String numeroElementos;
    private String status;
    private List<ProcedimentDto> resultado;

    public String getNumeroElementos() {
        return numeroElementos;
    }

    public void setNumeroElementos(String numeroElementos) {
        this.numeroElementos = numeroElementos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProcedimentDto> getResultado() {
        return resultado;
    }

    public void setResultado(List<ProcedimentDto> resultado) {
        this.resultado = resultado;
    }
}
