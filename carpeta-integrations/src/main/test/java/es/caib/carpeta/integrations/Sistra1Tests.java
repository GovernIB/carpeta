package es.caib.carpeta.integrations;

import es.caib.carpeta.commons.utils.DateUtils;
import es.caib.zonaper.ws.v2.model.tramitepersistente.TramitePersistente;
import es.caib.zonaper.ws.v2.model.tramitepersistente.TramitesPersistentes;
import es.caib.zonaper.ws.v2.services.BackofficeFacade;
import es.caib.zonaper.ws.v2.services.BackofficeFacadeException;
import es.caib.zonaper.ws.v2.services.BackofficeFacadeService;
import org.fundaciobit.plugins.utils.XTrustProvider;
import org.junit.Before;
import org.junit.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.ws.BindingProvider;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Sistra1Tests extends IntegrationsTestsUtlis{

    private final static String SISTRA1_URL = getSistra1Url();
    private final static String SISTRA1_USER = getSistra1User();
    private final static String SISTRA1_PASS = getSistra1Pass();
    private final static String documento = getSistra1Documento();

    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private BackofficeFacade backofficeFacade;

    @Before
    public void getClient() {

        if(SISTRA1_URL.startsWith("https")){
            XTrustProvider.install();
        }
        backofficeFacade =  getBackofficeFacade();

    }

    @Test
    public void obtenerTramites()  {
        try {

            BackofficeFacade backofficeFacade =  getBackofficeFacade();

            GregorianCalendar inicio = new GregorianCalendar();
            inicio.setTime(DateUtils.sumarRestarDiasFecha(new Date(), -150));

            GregorianCalendar hoy = new GregorianCalendar();
            hoy.setTime(new Date());


            TramitesPersistentes tramites = backofficeFacade.obtenerPersistentes(documento,
                    DatatypeFactory.newInstance().newXMLGregorianCalendar(inicio),DatatypeFactory.newInstance().newXMLGregorianCalendar(hoy));

            for(TramitePersistente tramite:tramites.getTramitePersistente()){
                System.out.println("IdTramite: " + tramite.getIdTramite());
                System.out.println("Descripcion: " + tramite.getDescripcionTramite());
            }

        } catch (BackofficeFacadeException | DatatypeConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return
     * @throws Exception
     */
    private BackofficeFacade getBackofficeFacade() {

        try{
            final URL wsdl = new URL(SISTRA1_URL + "?wsdl");

            BackofficeFacadeService service = new BackofficeFacadeService(wsdl);
            BackofficeFacade backofficeFacade =  service.getBackofficeFacade();

            BindingProvider bindingProvider = (BindingProvider) backofficeFacade;
            bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, SISTRA1_URL);
            bindingProvider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, SISTRA1_USER);
            bindingProvider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, SISTRA1_PASS);

            return backofficeFacade;
        }catch (Exception e){
            System.out.println("Error obteniendo el cliente");
        }

        return null;

    }
}
