package es.caib.carpeta.core.service;

import es.caib.carpeta.core.utils.DateUtils;
import es.caib.carpeta.core.utils.StringUtils;
import es.caib.zonaper.ws.v2.model.tramitepersistente.TramitePersistente;
import es.caib.zonaper.ws.v2.model.tramitepersistente.TramitesPersistentes;
import es.caib.zonaper.ws.v2.services.BackofficeFacade;
import es.caib.zonaper.ws.v2.services.BackofficeFacadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.ws.BindingProvider;
import java.net.URL;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class Sistra1ServiceImpl implements Sistra1Service{

    private static final Logger log = LoggerFactory.getLogger(Sistra1ServiceImpl.class);

    @Value("${es.caib.carpeta.sistra1.url}")    private String SISTRA1_URL;
    @Value("${es.caib.carpeta.sistra1.user}")   private String SISTRA1_USER;
    @Value("${es.caib.carpeta.sistra1.pass}")   private String SISTRA1_PASS;

    @Override
    public List<TramitePersistente> obtenerTramites(String documento) throws Exception{

        final URL wsdl = new URL(SISTRA1_URL + "?wsdl");
        BackofficeFacadeService service = new BackofficeFacadeService(wsdl);
        BackofficeFacade backofficeFacade =  service.getBackofficeFacade();

        BindingProvider bindingProvider = (BindingProvider) backofficeFacade;
        bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, SISTRA1_URL);
        bindingProvider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, SISTRA1_USER);
        bindingProvider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, SISTRA1_PASS);
        GregorianCalendar inicio = new GregorianCalendar();
        inicio.setTime((DateUtils.sumarRestarDiasFecha(new Date(), -120)));

        GregorianCalendar hoy = new GregorianCalendar();
        hoy.setTime(new Date());

        // Utilizamos el dni que Indra usa para las pruebas
        if(StringUtils.isEmpty(documento)){
            documento="43146650F";
        }

        TramitesPersistentes tramites = backofficeFacade.obtenerPersistentes(documento,
                DatatypeFactory.newInstance().newXMLGregorianCalendar(inicio),DatatypeFactory.newInstance().newXMLGregorianCalendar(hoy));


        System.out.println("Sistra1 Tramites: " + tramites.getTramitePersistente().size());

        return tramites.getTramitePersistente();
    }
}
