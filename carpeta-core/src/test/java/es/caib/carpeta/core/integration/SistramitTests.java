package es.caib.carpeta.core.integration;

import es.caib.carpeta.core.CarpetaTestUtils;
import es.caib.sistramit.rest.api.externa.v1.*;
import es.caib.sistramit.rest.api.util.JsonException;
import es.caib.sistramit.rest.api.util.JsonUtil;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

public class SistramitTests extends CarpetaTestUtils {

    private final static String urlBase = getSistraUrl();
    private final static String user = getSistraUser();
    private final static String pass = getSistraPass();

    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void obtenerTramitesPersistencia() throws JsonException {

        List<RTramitePersistencia> resultado = null;

        final RFiltroTramitePersistencia filtroPer = new RFiltroTramitePersistencia();
        filtroPer.setFechaDesde(sumarRestarDiasFecha(new Date(), -30));
        filtroPer.setFechaHasta(sumarRestarDiasFecha(new Date(), 0));

        filtroPer.setNif("11111111H");

        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(user, pass));

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<RFiltroTramitePersistencia> request = new HttpEntity<>(filtroPer, headers);

        final ResponseEntity<RTramitePersistencia[]> response = restTemplate.postForEntity(
                urlBase + "/tramite", request, RTramitePersistencia[].class);

        if (response.getBody() != null) {
            resultado = Arrays.asList(response.getBody());
        }

        for (RTramitePersistencia tramitePersistencia : resultado) {
            System.out.println("IdTramite: " + tramitePersistencia.getIdTramite());
            System.out.println("Descripcion: " + tramitePersistencia.getDescripcionTramite());
            System.out.println("IdSesion: " + tramitePersistencia.getIdSesionTramitacion());
            System.out.println("Fecha inicio: " +sdf.format(tramitePersistencia.getFechaInicio()));
            System.out.println("Fecha ultimo acceso: " + sdf.format(tramitePersistencia.getFechaUltimoAcceso()));
            System.out.println("-------------------------------------");
        }

        //return resultado;

    }

    @Test
    public void obtenerTicketAcceso() throws JsonException {

        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(user, pass));

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final RInfoTicketAcceso infoTicket = new RInfoTicketAcceso();
        infoTicket.setIdSesionTramitacion("Q2KQIERJ-HED0WNT8-T804QSNW");

        final RUsuarioAutenticadoInfo usuariInfo = new RUsuarioAutenticadoInfo();
        usuariInfo.setUsername("jgarcia");
        usuariInfo.setNombre("Jose");
        usuariInfo.setApellido1("Garcia");
        usuariInfo.setApellido2("Gutierrez");
        usuariInfo.setEmail("jgarcia@caib.es");
        usuariInfo.setNif("11111111H");
        usuariInfo.setAutenticacion("CLAVE_CERTIFICADO");
        usuariInfo.setMetodoAutenticacion("CLAVE_CERTIFICADO");

        infoTicket.setUsuarioAutenticadoInfo(usuariInfo);

        final HttpEntity<RInfoTicketAcceso> request = new HttpEntity<>(infoTicket, headers);

        final ResponseEntity<String> response = restTemplate.postForEntity(
                urlBase + "/ticketAcceso", request, String.class);

        System.out.println("Url: " + response.getBody());
    }

    private static Date sumarRestarDiasFecha(Date fecha, int dias){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }

}
