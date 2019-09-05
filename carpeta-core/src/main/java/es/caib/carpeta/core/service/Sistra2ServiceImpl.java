package es.caib.carpeta.core.service;

import es.caib.carpeta.core.utils.DateUtils;
import es.caib.carpeta.core.utils.StringUtils;
import es.caib.carpeta.core.utils.UsuarioClave;
import es.caib.sistramit.rest.api.externa.v1.RFiltroTramitePersistencia;
import es.caib.sistramit.rest.api.externa.v1.RInfoTicketAcceso;
import es.caib.sistramit.rest.api.externa.v1.RTramitePersistencia;
import es.caib.sistramit.rest.api.externa.v1.RUsuarioAutenticadoInfo;
import es.caib.sistramit.rest.api.util.JsonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class Sistra2ServiceImpl implements Sistra2Service{

    private static final Logger log = LoggerFactory.getLogger(Sistra2ServiceImpl.class);

    @Value("${es.caib.carpeta.sistra2.url}")    private String SISTRA2_URL;
    @Value("${es.caib.carpeta.sistra2.user}")   private String SISTRA2_USER;
    @Value("${es.caib.carpeta.sistra2.pass}")   private String SISTRA2_PASS;

    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<RTramitePersistencia> obtenerTramites(String documento) throws Exception {

        List<RTramitePersistencia> resultado = null;

        final RFiltroTramitePersistencia filtroPer = new RFiltroTramitePersistencia();
        filtroPer.setFechaDesde(DateUtils.sumarRestarDiasFecha(new Date(), -200));
        filtroPer.setFechaHasta(DateUtils.sumarRestarDiasFecha(new Date(), 0));

        // Utilizamos el dni que Indra usa para las pruebas
        if(StringUtils.isEmpty(documento)){
            documento="11111111H";
        }

        filtroPer.setNif(documento);

        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(SISTRA2_USER, SISTRA2_PASS));

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<RFiltroTramitePersistencia> request = new HttpEntity<>(filtroPer, headers);

        final ResponseEntity<RTramitePersistencia[]> response = restTemplate.postForEntity(
                SISTRA2_URL + "/tramite", request, RTramitePersistencia[].class);

        if (response.getBody() != null) {

            resultado = Arrays.asList(response.getBody());

           /* for (RTramitePersistencia tramitePersistencia : resultado) {
                System.out.println("IdTramite: " + tramitePersistencia.getIdTramite());
                System.out.println("Descripcion: " + tramitePersistencia.getDescripcionTramite());
                System.out.println("IdSesion: " + tramitePersistencia.getIdSesionTramitacion());
                System.out.println("Fecha inicio: " +sdf.format(tramitePersistencia.getFechaInicio()));
                System.out.println("Fecha ultimo acceso: " + sdf.format(tramitePersistencia.getFechaUltimoAcceso()));
                System.out.println("-------------------------------------");
            }*/

        }else{
            log.info("Sistra2: No hay tramites...");
        }



        return  resultado;

    }

    @Override
    public String obtenerUrlTicketAcceso(UsuarioClave usuario, String idSesionTramitacion) throws JsonException {

        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(SISTRA2_USER, SISTRA2_PASS));

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final RInfoTicketAcceso infoTicket = new RInfoTicketAcceso();
        //infoTicket.setIdSesionTramitacion("Q2KQIERJ-HED0WNT8-T804QSNW");
        infoTicket.setIdSesionTramitacion(idSesionTramitacion);

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
                SISTRA2_URL + "/ticketAcceso", request, String.class);

        log.info("Url: " + response.getBody());

        return response.getBody();
    }


}
