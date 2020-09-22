package es.caib.carpeta.core.service;

import es.caib.carpeta.core.utils.UsuarioClave;
import es.caib.carpeta.utils.CarpetaConstantes;
import es.caib.zonaper.ws.v2.model.elementoexpediente.ElementoExpediente;
import es.caib.zonaper.ws.v2.model.elementoexpediente.TipoElementoExpediente;
import es.caib.zonaper.ws.v2.model.tramitepersistente.TramitePersistente;
import es.caib.zonaper.ws.v2.model.elementoexpediente.ElementosExpediente;


import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

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
     * @param claveAcceso
     * @return
     * @throws Exception
     */
    String obtenerTramiteAnonimo(String claveAcceso) throws Exception;

    /**
     *
     * @param tipos
     * @param usuarioClave
     * @param loc
     * @return
     * @throws Exception
     */
    List<ElementoExpediente> obtenerElementosExpediente(List<TipoElementoExpediente> tipos, UsuarioClave usuarioClave, Locale loc) throws Exception;

    /**
     *
     * @param tipos
     * @param pendiente
     * @param usuarioClave
     * @param loc
     * @return
     * @throws Exception
     */
    List<ElementoExpediente> obtenerElementosExpediente(List<TipoElementoExpediente> tipos, int pendiente, UsuarioClave usuarioClave, Locale loc) throws Exception;

    /**
     *
     * @param tipos
     * @param pendiente
     * @param usuarioClave
     * @param loc
     * @param dataIni
     * @param dataFi
     * @return
     * @throws Exception
     */

    List<ElementoExpediente> obtenerElementosExpediente(List<TipoElementoExpediente> tipos, int pendiente, UsuarioClave usuarioClave, Locale loc,
                                                        GregorianCalendar dataIni, GregorianCalendar dataFi) throws Exception;

}
