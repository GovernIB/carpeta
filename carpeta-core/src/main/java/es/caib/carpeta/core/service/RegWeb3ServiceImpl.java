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

    @Override
    public List<AsientoRegistralWs> obtenerAsientosCiudadano(String documento) throws Exception {

        try {
            String endpoint = REGWEB3_HOST + REGWEB3_API;
            final URL wsdl = new URL(endpoint + "?wsdl");

            RegWebAsientoRegistralWsService service =  new RegWebAsientoRegistralWsService(wsdl);

            RegWebAsientoRegistralWs api = service.getRegWebAsientoRegistralWs();

            configAddressUserPassword(REGWEB3_USER, REGWEB3_PASS, endpoint, api);

            log.info("Buscando registros de: " + documento + "en " + REGWEB3_ENTIDAD);

            if(StringUtils.isEmpty(documento)){
                documento="43146650F";
            }

            List<AsientoRegistralWs> asientos = api.obtenerAsientosCiudadano(REGWEB3_ENTIDAD, documento);

            log.info("Total asientos: " + asientos.size());

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

    private static void configAddressUserPassword(String usr, String pwd,
                                                  String endpoint, Object api) {

        Map<String, Object> reqContext = ((BindingProvider) api).getRequestContext();
        reqContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
        reqContext.put(BindingProvider.USERNAME_PROPERTY, usr);
        reqContext.put(BindingProvider.PASSWORD_PROPERTY, pwd);
    }
}
