package org.fundaciobit.pluginsib.carpetafront.notib;


import es.caib.zonaper.ws.v2.model.elementoexpediente.ElementoExpediente;
import es.caib.zonaper.ws.v2.model.elementoexpediente.ElementosExpediente;
import es.caib.zonaper.ws.v2.model.elementoexpediente.FiltroElementosExpediente;
import es.caib.zonaper.ws.v2.model.elementoexpediente.ObjectFactory;
import es.caib.zonaper.ws.v2.model.elementoexpediente.TipoElementoExpediente;
import es.caib.zonaper.ws.v2.model.elementoexpediente.TiposElementoExpediente;
import es.caib.zonaper.ws.v2.services.BackofficeFacade;
import es.caib.zonaper.ws.v2.services.BackofficeFacadeService;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class Sistra1ServiceImpl {

    /* -------------- SISTRA 1/ ELEMENTOS PENDIENTES -------------- */
    public static final int ELEMENTO_PENDIENTE = 1;
    public static final int ELEMENTO_NO_PENDIENTE = 0;
    public static final int ELEMENTO_TODOS = -1;

    //private static final Logger log = LoggerFactory.getLogger(Sistra1ServiceImpl.class);

    protected final String url;
    protected final String user;
    protected final String pass;
    protected final boolean development;

    public Sistra1ServiceImpl(String url, String user, String pass, boolean development) {
        super();
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.development = development;
    }

    /*
     * @Override public List<TramitePersistente> obtenerTramites(String documento,
     * Date fechaInicio, Date fechaFin) throws Exception{ //SISTRA1 i SISTRA2 fan la
     * consulta de tramits amb intervals no inclusius fechaFin =
     * DateUtils.sumarRestarDiasFecha(fechaFin, 1);
     * 
     * BackofficeFacade backofficeFacade = getBackofficeFacade();
     * 
     * GregorianCalendar inicio = new GregorianCalendar();
     * inicio.setTime(fechaInicio);
     * 
     * GregorianCalendar hoy = new GregorianCalendar(); hoy.setTime(fechaFin);
     * 
     * TramitesPersistentes tramites =
     * backofficeFacade.obtenerPersistentes(documento,
     * DatatypeFactory.newInstance().newXMLGregorianCalendar(inicio),DatatypeFactory
     * .newInstance().newXMLGregorianCalendar(hoy));
     * 
     * return tramites.getTramitePersistente(); }
     * 
     * public String obtenerTramiteAnonimo(String claveAcceso) throws Exception {
     * BackofficeFacade backofficeFacade = getBackofficeFacade();
     * 
     * return backofficeFacade.obtenerUrlAccesoAnonimo(claveAcceso); }
     */

    public List<ElementoExpediente> obtenerElementosExpediente(List<TipoElementoExpediente> tipos, int pendiente,
            String administrationID, Locale loc) throws Exception {
        return obtenerElementosExpediente(tipos, pendiente, administrationID, loc, null, null);
    }

    public List<ElementoExpediente> obtenerElementosExpediente(List<TipoElementoExpediente> tipos,
            String administrationID, Locale loc) throws Exception {
        return obtenerElementosExpediente(tipos, ELEMENTO_TODOS, administrationID, loc, null, null);
    }

    public List<ElementoExpediente> obtenerElementosExpediente(List<TipoElementoExpediente> tipos, int pendiente,
            String administrationID, Locale loc, GregorianCalendar dataIni, GregorianCalendar dataFi) throws Exception {

        ObjectFactory factory = new ObjectFactory();
        JAXBElement<XMLGregorianCalendar> fechaIni = null;
        JAXBElement<XMLGregorianCalendar> fechaFi = null;

        if (dataIni != null && dataFi != null) {
            fechaIni = factory.createFiltroElementosExpedienteFechaInicio(
                    DatatypeFactory.newInstance().newXMLGregorianCalendar(dataIni));
            fechaFi = factory.createFiltroElementosExpedienteFechaFin(
                    DatatypeFactory.newInstance().newXMLGregorianCalendar(dataFi));
        }

        BackofficeFacade backofficeFacade = getBackofficeFacade();

        TiposElementoExpediente teess = new TiposElementoExpediente();
        for (TipoElementoExpediente tee : tipos) {
            teess.getTipo().add(tee);
        }

        FiltroElementosExpediente fee = new FiltroElementosExpediente();
        fee.setNif(administrationID);
        fee.setIdioma(loc.getLanguage());
        fee.setTipos(teess);
        fee.setFechaInicio(fechaIni);
        fee.setFechaFin(fechaFi);

        long num = backofficeFacade.obtenerTotalElementosExpediente(fee);

        //int pagina = 1;
        //int tamPagina = (int) num;

        ElementosExpediente el = backofficeFacade.obtenerElementosExpediente(fee, null, null);

        List<ElementoExpediente> els = new ArrayList<>();
        for (ElementoExpediente elem : el.getElemento()) {
            switch (pendiente) {
            case ELEMENTO_PENDIENTE:
                if (elem.isPendiente())
                    els.add(elem);
                break;
            case ELEMENTO_NO_PENDIENTE:
                if (!elem.isPendiente())
                    els.add(elem);
                break;
            case ELEMENTO_TODOS:
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
    private BackofficeFacade getBackofficeFacade() throws Exception {
        final URL wsdl = new URL(this.url + "?wsdl");

        BackofficeFacadeService service = new BackofficeFacadeService(wsdl);
        BackofficeFacade backofficeFacade = service.getBackofficeFacade();

        BindingProvider bindingProvider = (BindingProvider) backofficeFacade;
        bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, this.url);
        bindingProvider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, this.user);
        bindingProvider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, this.pass);

        return backofficeFacade;
    }
}
