package es.caib.carpeta.core.service;

import es.caib.carpeta.core.utils.DateUtils;
import es.caib.carpeta.core.utils.UsuarioClave;
import es.caib.zonaper.ws.v2.model.elementoexpediente.ElementosExpediente;
import es.caib.zonaper.ws.v2.model.elementoexpediente.FiltroElementosExpediente;
import es.caib.zonaper.ws.v2.model.elementoexpediente.TipoElementoExpediente;
import es.caib.zonaper.ws.v2.model.elementoexpediente.TiposElementoExpediente;
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

import javax.xml.datatype.DatatypeFactory;
import javax.xml.ws.BindingProvider;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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

    @Override
    public String obtenerTiquetAcceso(String idSesionTramitacion, UsuarioClave usuario) throws Exception {

        BackofficeFacade backofficeFacade = getBackofficeFacade();

//        UsuarioAutenticadoInfo usuarioAutenticadoInfo = new UsuarioAutenticadoInfo();
//        usuarioAutenticadoInfo.setNombre(usuario.getNombre());
//        usuarioAutenticadoInfo.setApellido1(usuario.getApellido1());
//        usuarioAutenticadoInfo.setApellido2(usuario.getApellido2());
//        usuarioAutenticadoInfo.setMetodoAutenticacion(usuario.getMetodoAutentificacion());
//        usuarioAutenticadoInfo.setNif(usuario.getNif());
//
        
//        String url = backofficeFacade.oobtenerTiquetAcceso(idSesionTramitacion, usuario);
        return "url";
    }


//    public String obtenerElementosExpediente(UsuarioClave usuario) throws Exception {
    public ElementosExpediente obtenerElementosExpediente() throws Exception {
        log.debug("ANTES de declarar elementos");
        BackofficeFacade backofficeFacade = getBackofficeFacade();

//        UsuarioAutenticadoInfo usuarioAutenticadoInfo = new UsuarioAutenticadoInfo();
//        usuarioAutenticadoInfo.setNombre(usuario.getNombre());
//        usuarioAutenticadoInfo.setApellido1(usuario.getApellido1());
//        usuarioAutenticadoInfo.setApellido2(usuario.getApellido2());
//        usuarioAutenticadoInfo.setMetodoAutenticacion(usuario.getMetodoAutentificacion());
//        usuarioAutenticadoInfo.setNif(usuario.getNif());

        log.debug("ANTES de declarar elementos");

        TiposElementoExpediente teess = new TiposElementoExpediente();
        teess.getTipo().add(TipoElementoExpediente.COMUNICACION);
        teess.getTipo().add(TipoElementoExpediente.NOTIFICACION);
        teess.getTipo().add(TipoElementoExpediente.ENVIO);
        teess.getTipo().add(TipoElementoExpediente.PREENVIO);
        teess.getTipo().add(TipoElementoExpediente.PREREGISTRO);
        teess.getTipo().add(TipoElementoExpediente.REGISTRO);

        FiltroElementosExpediente fee = new FiltroElementosExpediente();
        fee.setNif("11111111H");
        fee.setIdioma("es");
        fee.setTipos(teess);

        log.debug("ANTES de LLAMADA");

        ElementosExpediente el = backofficeFacade.obtenerElementosExpediente(fee);

        log.debug("DESPUEEEEEEEEEEEEEES");

        return el;
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
