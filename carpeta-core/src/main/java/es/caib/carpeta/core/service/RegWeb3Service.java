package es.caib.carpeta.core.service;

import es.caib.regweb3.ws.api.v3.AsientoRegistralWs;

import java.util.List;

public interface RegWeb3Service {

    List<AsientoRegistralWs> obtenerAsientosCiudadano(String documento) throws Exception;
}
