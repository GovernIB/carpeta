package es.caib.carpeta.core.service;

import es.caib.regweb3.ws.api.v3.AsientoRegistralWs;
import es.caib.regweb3.ws.api.v3.ResultadoBusquedaWs;

import java.util.List;

public interface RegWeb3Service {

    ResultadoBusquedaWs obtenerAsientosCiudadano(String documento, Integer pageNumber) throws Exception;

    AsientoRegistralWs obtenerAsientoCiudadano(String documento, String numeroRegistroFormateado) throws Exception;
}
