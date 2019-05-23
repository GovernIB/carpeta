package es.caib.carpeta.core.service;

import es.caib.carpeta.core.utils.UsuarioClave;

public interface SecurityService {

    /**
     *
     * @return
     * @throws Exception
     */
    String iniciarSesionAutentificacion() throws Exception;

    /**
     *
     * @param ticket
     * @return
     * @throws Exception
     */
    UsuarioClave validarTicketAutentificacion(String ticket) throws Exception;

    /**
     *
     * @return
     * @throws Exception
     */
    String iniciarSesionLogout() throws Exception;
}
