package es.caib.carpeta.front.service;

import es.caib.carpeta.commons.utils.UsuarioClave;

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
