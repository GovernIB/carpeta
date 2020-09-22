package es.caib.carpeta.core.service;

import es.caib.carpeta.core.utils.DateUtils;
import es.caib.carpeta.core.utils.UsuarioClave;
import es.caib.carpeta.utils.CarpetaConstantes;
import es.caib.zonaper.ws.v2.model.elementoexpediente.*;
import es.caib.zonaper.ws.v2.model.tramitepersistente.TramitePersistente;
import es.caib.zonaper.ws.v2.model.tramitepersistente.TramitesPersistentes;
//import es.caib.zonaper.ws.v2.model.usuarioautenticadoinfo.UsuarioAutenticadoInfo;
import es.caib.zonaper.ws.v2.services.BackofficeFacade;
import es.caib.zonaper.ws.v2.services.BackofficeFacadeService;
import org.fundaciobit.plugins.utils.XTrustProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import java.net.URL;
import java.util.*;

@Service
public class Sistra1ServiceImpl implements Sistra1Service{

    private static final Logger log = LoggerFactory.getLogger(Sistra1ServiceImpl.class);

    @Value("${es.caib.carpeta.sistra1.url}")    private String SISTRA1_URL;
    @Value("${es.caib.carpeta.sistra1.user}")   private String SISTRA1_USER;
    @Value("${es.caib.carpeta.sistra1.pass}")   private String SISTRA1_PASS;
    @Value("${es.caib.carpeta.development}")    private Boolean development;

    @Override
    public List<TramitePersistente> obtenerTramites(String documento, Date fechaInicio, Date fechaFin) throws Exception{
        //SISTRA1 i SISTRA2 fan la consulta de tramits amb intervals no inclusius
        fechaFin = DateUtils.sumarRestarDiasFecha(fechaFin, 1);

        BackofficeFacade backofficeFacade =  getBackofficeFacade();

        GregorianCalendar inicio = new GregorianCalendar();
        inicio.setTime(fechaInicio);

        GregorianCalendar hoy = new GregorianCalendar();
        hoy.setTime(fechaFin);

        TramitesPersistentes tramites = backofficeFacade.obtenerPersistentes(documento,
                DatatypeFactory.newInstance().newXMLGregorianCalendar(inicio),DatatypeFactory.newInstance().newXMLGregorianCalendar(hoy));

        return tramites.getTramitePersistente();
    }

    public String obtenerTramiteAnonimo(String claveAcceso) throws Exception {
        BackofficeFacade backofficeFacade =  getBackofficeFacade();

        return backofficeFacade.obtenerUrlAccesoAnonimo(claveAcceso);
    }

    public List<ElementoExpediente> obtenerElementosExpediente(List<TipoElementoExpediente> tipos, int pendiente, UsuarioClave usuarioClave, Locale loc) throws Exception {
        return obtenerElementosExpediente(tipos, pendiente, usuarioClave, loc, null, null);
    }

    public List<ElementoExpediente> obtenerElementosExpediente(List<TipoElementoExpediente> tipos, UsuarioClave usuarioClave, Locale loc) throws Exception {
        return obtenerElementosExpediente(tipos, CarpetaConstantes.ELEMENTO_TODOS, usuarioClave, loc, null, null);
    }

    public List<ElementoExpediente> obtenerElementosExpediente(List<TipoElementoExpediente> tipos, int pendiente, UsuarioClave usuarioClave, Locale loc, GregorianCalendar dataIni, GregorianCalendar dataFi) throws Exception {

        ObjectFactory factory = new ObjectFactory();
        JAXBElement<XMLGregorianCalendar> fechaIni = null;
        JAXBElement<XMLGregorianCalendar> fechaFi = null;

        if (dataIni != null && dataFi != null) {
            fechaIni = factory.createFiltroElementosExpedienteFechaInicio(DatatypeFactory.newInstance().newXMLGregorianCalendar(dataIni));
            fechaFi = factory.createFiltroElementosExpedienteFechaFin(DatatypeFactory.newInstance().newXMLGregorianCalendar(dataFi));
        }

        BackofficeFacade backofficeFacade = getBackofficeFacade();

        TiposElementoExpediente teess = new TiposElementoExpediente();
        for (TipoElementoExpediente tee : tipos) {
            teess.getTipo().add(tee);
        }

        FiltroElementosExpediente fee = new FiltroElementosExpediente();
        fee.setNif(usuarioClave.getNif());
        fee.setIdioma(loc.getLanguage());
        fee.setTipos(teess);
        fee.setFechaInicio(fechaIni);
        fee.setFechaFin(fechaFi);

        long num = backofficeFacade.obtenerTotalElementosExpediente(fee);

        int pagina = 1;
        int tamPagina = (int) num;

        ElementosExpediente el = backofficeFacade.obtenerElementosExpediente(fee, null, null);

        List<ElementoExpediente> els = new ArrayList<>();
        for (ElementoExpediente elem : el.getElemento()) {
            switch (pendiente) {
                case CarpetaConstantes.ELEMENTO_PENDIENTE:
                    if (elem.isPendiente()) els.add(elem);
                    break;
                case CarpetaConstantes.ELEMENTO_NO_PENDIENTE:
                    if (!elem.isPendiente()) els.add(elem);
                    break;
                case CarpetaConstantes.ELEMENTO_TODOS:
                    els.add(elem);
            }
        }

        return els;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    private BackofficeFacade getBackofficeFacade() throws Exception{
        final URL wsdl = new URL(SISTRA1_URL + "?wsdl");

        if(SISTRA1_URL.startsWith("https") && development){
            XTrustProvider.install();
        }

        BackofficeFacadeService service = new BackofficeFacadeService(wsdl);
        BackofficeFacade backofficeFacade =  service.getBackofficeFacade();

        BindingProvider bindingProvider = (BindingProvider) backofficeFacade;
        bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, SISTRA1_URL);
        bindingProvider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, SISTRA1_USER);
        bindingProvider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, SISTRA1_PASS);

        return backofficeFacade;
    }
}
