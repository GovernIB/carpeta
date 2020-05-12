package es.caib.carpeta.integrations;

import es.caib.carpeta.commons.utils.BasicAuthenticator;
import es.caib.carpeta.commons.utils.DateUtils;
import es.caib.sistramit.rest.api.externa.v1.RFiltroTramitePersistencia;
import es.caib.sistramit.rest.api.externa.v1.RTramitePersistencia;
import org.fundaciobit.plugins.utils.XTrustProvider;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SistramitTests extends IntegrationsTestsUtlis{

    private final static String urlBase = getSistraUrl();
    private final static String user = getSistraUser();
    private final static String pass = getSistraPass();
    private final static String documento = getSistraDocumento();

    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private Client client;

    @Before
    public void getClient() {

        if(urlBase.startsWith("https")){
            XTrustProvider.install();
        }
        client =  ClientBuilder.newClient().register(new BasicAuthenticator(user, pass));

    }

    @Test
    public void obtenerTramitesPersistencia()  {

        final RFiltroTramitePersistencia filtroPer = new RFiltroTramitePersistencia();
        filtroPer.setFechaDesde(DateUtils.sumarRestarDiasFecha(new Date(), -150));
        filtroPer.setFechaHasta(DateUtils.sumarRestarDiasFecha(new Date(), 0));
        filtroPer.setNif(documento);

        List<RTramitePersistencia> tramites = client.target(urlBase + "/tramite")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(filtroPer), new GenericType<List<RTramitePersistencia>>() {});

        for (RTramitePersistencia tramite : tramites) {
            System.out.println("IdTramite: " + tramite.getIdTramite());
            System.out.println("Descripcion: " + tramite.getDescripcionTramite());
            System.out.println("IdSesion: " + tramite.getIdSesionTramitacion());
            System.out.println("Fecha inicio: " +sdf.format(tramite.getFechaInicio()));
            System.out.println("Fecha ultimo acceso: " + sdf.format(tramite.getFechaUltimoAcceso()));
            System.out.println("-------------------------------------");
        }


    }
}
