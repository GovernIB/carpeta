package es.caib.carpeta.core.service;

import es.caib.carpeta.core.utils.UsuarioClave;
import es.caib.sistramit.rest.api.externa.v1.RFiltroTramitePersistencia;
import es.caib.sistramit.rest.api.externa.v1.RInfoTicketAcceso;
import es.caib.sistramit.rest.api.externa.v1.RTramitePersistencia;
import es.caib.sistramit.rest.api.externa.v1.RUsuarioAutenticadoInfo;
import es.caib.sistramit.rest.api.util.JsonException;
import org.fundaciobit.plugins.utils.XTrustProvider;
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
    public List<RTramitePersistencia> obtenerTramites(String documento, Date fechaInicio, Date fechaFin) throws Exception {

        final RestTemplate restTemplate = getRestTemplate();

        List<RTramitePersistencia> resultado = null;

        final RFiltroTramitePersistencia filtroPer = new RFiltroTramitePersistencia();
        filtroPer.setFechaDesde(fechaInicio);
        filtroPer.setFechaHasta(fechaFin);
        filtroPer.setNif(documento);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<RFiltroTramitePersistencia> request = new HttpEntity<>(filtroPer, headers);

        final ResponseEntity<RTramitePersistencia[]> response = restTemplate.postForEntity(
                SISTRA2_URL + "/tramite", request, RTramitePersistencia[].class);

        if (response.getBody() != null) {

            resultado = Arrays.asList(response.getBody());
        }

        return  resultado;

    }

    @Override
    public String obtenerUrlTicketAcceso(UsuarioClave usuario, String idSesionTramitacion) throws JsonException {

        final RestTemplate restTemplate = getRestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

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

        infoTicket.setUsuarioAutenticadoInfo(usuariInfo);

        final HttpEntity<RInfoTicketAcceso> request = new HttpEntity<>(infoTicket, headers);

        final ResponseEntity<String> response = restTemplate.postForEntity(
                SISTRA2_URL + "/ticketAcceso", request, String.class);

        return response.getBody();
    }

    /**
     *
     * @return
     */
    private RestTemplate getRestTemplate(){

        if(SISTRA2_URL.startsWith("https")){
            XTrustProvider.install();
        }

        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(SISTRA2_USER, SISTRA2_PASS));

        return restTemplate;
    }

}
