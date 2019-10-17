package es.caib.carpeta.core.service;

import es.caib.carpeta.core.utils.UsuarioClave;
import es.caib.sistramit.rest.api.externa.v1.RTramitePersistencia;
import es.caib.sistramit.rest.api.util.JsonException;

import java.util.Date;
import java.util.List;

public interface Sistra2Service {

    List<RTramitePersistencia> obtenerTramites(String documento, Date fechaInicio, Date fechaFin) throws Exception;

    String obtenerUrlTicketAcceso(UsuarioClave usuario, String idSesionTramitacion) throws JsonException;
}
