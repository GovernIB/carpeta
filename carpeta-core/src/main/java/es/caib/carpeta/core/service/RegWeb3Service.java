package es.caib.carpeta.core.service;

import es.caib.regweb3.ws.api.v3.AsientoRegistralWs;
import es.caib.regweb3.ws.api.v3.ResultadoBusquedaWs;

public interface RegWeb3Service {

    /**
     *
     * @param documento
     * @param pageNumber
     * @return
     * @throws Exception
     */
    ResultadoBusquedaWs obtenerAsientosCiudadano(String documento, Integer pageNumber) throws Exception;

    /**
     *
     * @param documento
     * @param numeroRegistroFormateado
     * @return
     * @throws Exception
     */
    AsientoRegistralWs obtenerAsientoCiudadano(String documento, String numeroRegistroFormateado) throws Exception;
}
