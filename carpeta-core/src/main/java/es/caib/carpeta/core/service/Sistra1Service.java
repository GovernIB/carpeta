package es.caib.carpeta.core.service;

import es.caib.carpeta.core.utils.UsuarioClave;
import es.caib.zonaper.ws.v2.model.tramitepersistente.TramitePersistente;

import java.util.Date;
import java.util.List;

public interface Sistra1Service {

    /**
     *
     * @param documento
     * @return
     * @throws Exception
     */
    List<TramitePersistente> obtenerTramites(String documento, Date fechaInicio, Date fechaFin) throws Exception;

    /**
     *
     * @param idSesionTramitacion
     * @param usuario
     * @return
     * @throws Exception
     */
    String obtenerTiquetAcceso(String idSesionTramitacion, UsuarioClave usuario) throws Exception;

}
