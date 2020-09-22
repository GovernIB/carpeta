package es.caib.carpeta.front.utils;

import es.caib.carpeta.utils.CarpetaConstantes;
import es.caib.sistramit.rest.api.externa.v1.RTramitePersistencia;
import es.caib.zonaper.ws.v2.model.elementoexpediente.ElementoExpediente;
import es.caib.zonaper.ws.v2.model.tramitepersistente.TramitePersistente;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class TramitesCiudadano {

    protected final Log log = LogFactory.getLog(getClass());

    private List<Tramite> tramites = new ArrayList<>();

    public TramitesCiudadano(List<RTramitePersistencia> sistra2, List<TramitePersistente> sistra1, List<ElementoExpediente> elementos, Locale loc) {

        if(elementos != null && !elementos.isEmpty()){
           convertirElementosSistra1(elementos, loc);
        }
        if(sistra1 != null && !sistra1.isEmpty()){
           convertirTramitesSistra1(sistra1);
        }
        if(sistra2 != null && !sistra2.isEmpty()){
           convertirTramitesSistra2(sistra2);
        }

        //tramites.sort(Comparator.comparing(Tramite::getFechaInicio));
        Collections.reverse(tramites);

    }

    public TramitesCiudadano(List<ElementoExpediente> elementos, Locale loc) {
        if(elementos != null && !elementos.isEmpty()){
            convertirElementosSistra1(elementos, loc);
        }

        //tramites.sort(Comparator.comparing(Tramite::getFechaInicio));
        Collections.reverse(tramites);
    }

    public List<Tramite> getTramites() {
        return tramites;
    }

    public void setTramites(List<Tramite> tramites) {
        this.tramites = tramites;
    }

    private void convertirTramitesSistra2(List<RTramitePersistencia> sistra2){

        for (RTramitePersistencia tramitesistra2 : sistra2) {
            Tramite tramite = new Tramite();
            tramite.setSistra(CarpetaConstantes.SISTRA2);
            tramite.setIdTramite(tramitesistra2.getIdTramite());
            tramite.setIdSesionTramitacion(tramitesistra2.getIdSesionTramitacion());
            tramite.setDescripcionTramite(tramitesistra2.getDescripcionTramite());
            tramite.setIdioma(tramitesistra2.getIdioma());
            tramite.setFechaInicio(tramitesistra2.getFechaInicio());
            tramite.setUrl("");

            tramites.add(tramite);
        }
    }

    private void convertirTramitesSistra1(List<TramitePersistente> sistra1){

        for (TramitePersistente tramitesistra1 : sistra1) {
            Tramite tramite = new Tramite();
            tramite.setSistra(CarpetaConstantes.SISTRA1);
            tramite.setIdTramite(tramitesistra1.getIdTramite());
            tramite.setIdSesionTramitacion(tramitesistra1.getIdSesionTramitacion());
            tramite.setDescripcionTramite(tramitesistra1.getDescripcionTramite());
            tramite.setIdioma(tramitesistra1.getIdioma());
            tramite.setFechaInicio(tramitesistra1.getFechaInicio().toGregorianCalendar().getTime());
            tramite.setUrl(tramitesistra1.getUrlAcceso());

            tramites.add(tramite);
        }
    }

    private void convertirElementosSistra1(List<ElementoExpediente> sistra1, Locale loc){

        for (ElementoExpediente tramitesistra1 : sistra1) {
            Tramite tramite = new Tramite();
            tramite.setSistra(CarpetaConstantes.SISTRA1_ELEMENTOS);
            tramite.setIdTramite(tramitesistra1.getTipo().value());
            tramite.setIdSesionTramitacion(tramitesistra1.getTipo().value()); // Ojo, repetido
            tramite.setDescripcionTramite(tramitesistra1.getDescripcion());
            tramite.setIdioma(loc.getLanguage());
            tramite.setFechaInicio(tramitesistra1.getFecha().toGregorianCalendar().getTime());
            tramite.setUrl(tramitesistra1.getUrl());

            tramites.add(tramite);
        }
    }
}
