package es.caib.carpeta.api.externa.server.certificats.v1.api;

import es.caib.carpeta.api.externa.server.certificats.v1.model.CertificatBean;
import es.caib.carpeta.api.externa.server.certificats.v1.model.CertificatInfo;
import es.caib.carpeta.api.externa.server.certificats.v1.model.RestExceptionInfo;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import io.swagger.jaxrs.PATCH;
import javax.validation.constraints.*;
import javax.validation.Valid;

/**
 * API REST EXTERNA de Carpeta - Certificats
 *
 * <p>Plantilla de Serveis REST a implementar per servidors externs per des de Carpeta poder accedir a Certificats dels Ciutadans
 *
 */
@Path("/secure/certificats")
@Api(value = "/", description = "")
public interface CertificatsApi  {

    /**
     * Retorna un certificat provinent de un servei extern a CARPETA.
     *
     */
    @GET
    @Path("/descarregarCertificat")
    @Produces({ "application/json" })
    @ApiOperation(value = "Retorna un certificat provinent de un servei extern a CARPETA.", tags={ "Certificats" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Llista d'accessos a CARPETA", response = CertificatBean.class),
        @ApiResponse(code = 400, message = "Paràmetres incorrectes", response = RestExceptionInfo.class),
        @ApiResponse(code = 401, message = "No Autenticat"),
        @ApiResponse(code = 403, message = "No Autoritzat"),
        @ApiResponse(code = 404, message = "Paràmetres incorrectes"),
        @ApiResponse(code = 500, message = "Error no controlat", response = RestExceptionInfo.class) })
    public CertificatBean descarregarCertificat(@QueryParam("dni") @NotNull String dni, @QueryParam("idioma") @NotNull String idioma, @HeaderParam("pluginNumber")  String pluginNumber);

    /**
     * Retorna un CertificatInfo que indica si l&#39;usuari té certificat 
     *
     */
    @GET
    @Path("/teCertificat")
    @Produces({ "application/json" })
    @ApiOperation(value = "Retorna un CertificatInfo que indica si l'usuari té certificat ", tags={ "Certificats" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Llista d'accessos a CARPETA", response = CertificatInfo.class),
        @ApiResponse(code = 400, message = "Paràmetres incorrectes", response = RestExceptionInfo.class),
        @ApiResponse(code = 401, message = "No Autenticat"),
        @ApiResponse(code = 403, message = "No Autoritzat"),
        @ApiResponse(code = 404, message = "Paràmetres incorrectes"),
        @ApiResponse(code = 500, message = "Error intern de servidor", response = RestExceptionInfo.class) })
    public CertificatInfo teCertificat(@QueryParam("dni") @NotNull String dni, @HeaderParam("pluginNumber")  String pluginNumber);
}
