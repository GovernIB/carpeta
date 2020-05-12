package es.caib.carpeta.integrations.sistra;

import es.caib.carpeta.commons.utils.BasicAuthenticator;
import es.caib.carpeta.commons.utils.DateUtils;
import es.caib.sistramit.rest.api.externa.v1.RFiltroTramitePersistencia;
import es.caib.sistramit.rest.api.externa.v1.RInfoTicketAcceso;
import es.caib.sistramit.rest.api.externa.v1.RTramitePersistencia;
import es.caib.sistramit.rest.api.externa.v1.RUsuarioAutenticadoInfo;
import org.fundaciobit.plugins.utils.XTrustProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Sistra2Service {

    private static final Logger log = LoggerFactory.getLogger(Sistra2Service.class);

    private final static String SISTRA2_URL = "";
    private final static String SISTRA2_USER = "";
    private final static String SISTRA2_PASS = "";
    private final static Boolean development = true;

    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     *
     * @param documento
     * @param fechaInicio
     * @param fechaFin
     * @return
     * @throws Exception
     */
    public List<RTramitePersistencia> obtenerTramites(String documento, Date fechaInicio, Date fechaFin) throws Exception {

        Client client = getClientBasicAuthenticator();

        final RFiltroTramitePersistencia filtroPer = new RFiltroTramitePersistencia();
        filtroPer.setFechaDesde(fechaInicio);
        filtroPer.setFechaHasta(DateUtils.sumarRestarDiasFecha(fechaFin, 1));
        filtroPer.setNif(documento);

        List<RTramitePersistencia> tramites = client.target(SISTRA2_URL + "/tramite")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(filtroPer, MediaType.APPLICATION_JSON), new GenericType<List<RTramitePersistencia>>() {});

        return tramites;

    }

    /**
     *
     * @param usuario
     * @param idSesionTramitacion
     * @return
     * @throws Exception
     */
    /*public String obtenerUrlTicketAcceso(UsuarioClave usuario, String idSesionTramitacion) throws Exception {

        Client client = getClientBasicAuthenticator();

        final RInfoTicketAcceso infoTicket = new RInfoTicketAcceso();
        infoTicket.setIdSesionTramitacion(idSesionTramitacion);

        final RUsuarioAutenticadoInfo usuariInfo = new RUsuarioAutenticadoInfo();
        usuariInfo.setUsername(usuario.getNif());
        usuariInfo.setNombre(usuario.getNombre());
        usuariInfo.setApellido1(usuario.getApellido1());
        usuariInfo.setApellido2(usuario.getApellido2());
        usuariInfo.setEmail("");
        usuariInfo.setNif(usuario.getNif());
        usuariInfo.setAutenticacion("c");
        usuariInfo.setMetodoAutenticacion("CLAVE_CERTIFICADO");
        usuariInfo.setQaa(usuario.getQaa());


        String ticket = client.target(SISTRA2_URL + "/tramite")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(usuariInfo, MediaType.APPLICATION_JSON), String.class);

        return ticket;

    }*/

    /**
     *
     * @return
     */
    private Client getClientBasicAuthenticator(){

        if(SISTRA2_URL.startsWith("https") && development){
           XTrustProvider.install();
        }

        return ClientBuilder.newClient().register(new BasicAuthenticator(SISTRA2_USER, SISTRA2_PASS));
    }


}
