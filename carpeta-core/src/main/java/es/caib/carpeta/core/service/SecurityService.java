package es.caib.carpeta.core.service;

import es.caib.carpeta.core.utils.UsuarioClave;

public interface SecurityService {

    String iniciarSesionAutentificacion() throws Exception;

    UsuarioClave validarTicketAutentificacion(String ticket) throws Exception;

    String iniciarSesionLogout() throws Exception;
}
