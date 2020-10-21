package es.caib.carpeta.front.service;

import es.caib.carpeta.commons.utils.UsuarioClave;

public interface SecurityService {

    /**
     *
     * @return
     * @throws Exception
     */
    public String iniciarSesionAutentificacion(String URL_CALLBACK_LOGIN, 
            String URL_CALLBACK_ERROR, String IDIOMA) throws Exception;

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
    public String iniciarSesionLogout(String URL_CALLBACK_LOGOUT, String IDIOMA) throws Exception;
}
