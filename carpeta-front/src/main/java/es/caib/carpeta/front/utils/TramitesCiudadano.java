package es.caib.carpeta.front.utils;

import es.caib.carpeta.utils.CarpetaConstantes;
import es.caib.sistramit.rest.api.externa.v1.RTramitePersistencia;
import es.caib.zonaper.ws.v2.model.tramitepersistente.TramitePersistente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TramitesCiudadano {

    private List<Tramite> tramites = new ArrayList<>();

    public TramitesCiudadano(List<RTramitePersistencia> sistra2, List<TramitePersistente> sistra1) {

        if(sistra1 != null && !sistra1.isEmpty()){
           convertirTramitesSistra1(sistra1);
        }
        if(sistra2 != null && !sistra2.isEmpty()){
           convertirTramitesSistra2(sistra2);
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
            tramite.setFechaUltimoAcceso(tramitesistra2.getFechaUltimoAcceso());

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
            tramite.setFechaUltimoAcceso(tramitesistra1.getFechaUltimoAcceso().toGregorianCalendar().getTime());

            tramites.add(tramite);
        }
    }
}
