package es.caib.carpeta.core.service;

import es.caib.carpeta.core.utils.StringUtils;
import es.caib.regweb3.ws.api.v3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.ws.BindingProvider;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Service
public class RegWeb3ServiceImpl implements RegWeb3Service{

    private static final Logger log = LoggerFactory.getLogger(RegWeb3ServiceImpl.class);

    @Value("${es.caib.carpeta.regweb3.host}")    private String REGWEB3_HOST;
    @Value("${es.caib.carpeta.regweb3.api}")     private String REGWEB3_API;
    @Value("${es.caib.carpeta.regweb3.entidad}") private String REGWEB3_ENTIDAD;
    @Value("${es.caib.carpeta.regweb3.user}")    private String REGWEB3_USER;
    @Value("${es.caib.carpeta.regweb3.pass}")    private String REGWEB3_PASS;


    /**
     *
     * @param documento
     * @param pageNumber
     * @return
     * @throws Exception
     */
    public ResultadoBusquedaWs obtenerAsientosCiudadano(String documento, Integer pageNumber) throws Exception {

        try {

            RegWebAsientoRegistralWs api = getRegWebAsientoRegistralWsApi();

            log.info("Buscando registros de: " + documento + " en " + REGWEB3_ENTIDAD);

            if(StringUtils.isEmpty(documento)){
                documento="43146650F";
            }

            ResultadoBusquedaWs asientos = api.obtenerAsientosCiudadano(REGWEB3_ENTIDAD, documento, pageNumber);

            log.info("Total asientos: " + asientos.getTotalResults());

            return asientos;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (WsI18NException e) {
            e.printStackTrace();
        } catch (WsValidationException e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     *
     * @param documento
     * @param numeroRegistroFormateado
     * @return
     * @throws Exception
     */
    public AsientoRegistralWs obtenerAsientoCiudadano(String documento, String numeroRegistroFormateado) throws Exception{

        try {

            RegWebAsientoRegistralWs api = getRegWebAsientoRegistralWsApi();

            log.info("Obteniendo registro: " + numeroRegistroFormateado + " en " + REGWEB3_ENTIDAD);

            AsientoRegistralWs asiento = api.obtenerAsientoCiudadano(REGWEB3_ENTIDAD, documento, numeroRegistroFormateado);

            log.info("Resumen asiento: " + asiento.getResumen());

            return asiento;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (WsI18NException e) {
            e.printStackTrace();
        } catch (WsValidationException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *
     * @param numeroRegistroFormateado
     * @return
     * @throws Exception
     */
    public JustificanteReferenciaWs obtenerReferenciaJustificante(String numeroRegistroFormateado) throws Exception{

        try {

            RegWebAsientoRegistralWs api = getRegWebAsientoRegistralWsApi();

            log.info("Obteniendo justificante: " + numeroRegistroFormateado + " en " + REGWEB3_ENTIDAD);

            JustificanteReferenciaWs justificante = api.obtenerReferenciaJustificante(REGWEB3_ENTIDAD, numeroRegistroFormateado);

            log.info("Justificnte url: " + justificante.getUrl());
            log.info("Justificnte csv: " + justificante.getCsv());

            return justificante;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (WsI18NException e) {
            e.printStackTrace();
        } catch (WsValidationException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     *
     * @return
     * @throws MalformedURLException
     */
    private RegWebAsientoRegistralWs getRegWebAsientoRegistralWsApi() throws MalformedURLException {

        String endpoint = REGWEB3_HOST + REGWEB3_API;
        final URL wsdl = new URL(endpoint + "?wsdl");

        RegWebAsientoRegistralWsService service =  new RegWebAsientoRegistralWsService(wsdl);

        RegWebAsientoRegistralWs regWebAsientoRegistralWs = service.getRegWebAsientoRegistralWs();

        Map<String, Object> reqContext = ((BindingProvider) regWebAsientoRegistralWs).getRequestContext();
        reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
        reqContext.put(BindingProvider.USERNAME_PROPERTY, REGWEB3_USER);
        reqContext.put(BindingProvider.PASSWORD_PROPERTY, REGWEB3_PASS);


        return regWebAsientoRegistralWs;
    }

}
