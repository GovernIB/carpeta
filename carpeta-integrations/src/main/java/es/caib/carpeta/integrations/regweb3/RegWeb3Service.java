package es.caib.carpeta.integrations.regweb3;

import es.caib.regweb3.ws.api.v3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.ws.BindingProvider;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class RegWeb3Service {

    private static final Logger log = LoggerFactory.getLogger(RegWeb3Service.class);

    private String REGWEB3_HOST = "";
    private String REGWEB3_API = "";
    private String REGWEB3_ENTIDAD = "";
    private String REGWEB3_USER = "";
    private String REGWEB3_PASS = "";


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

            ResultadoBusquedaWs asientos = api.obtenerAsientosCiudadano(REGWEB3_ENTIDAD, documento, pageNumber);

            log.info("Total asientos: " + asientos.getTotalResults());

            return asientos;

        } catch (MalformedURLException | WsI18NException e) {
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

            return asiento;

        } catch (MalformedURLException | WsI18NException e) {
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
