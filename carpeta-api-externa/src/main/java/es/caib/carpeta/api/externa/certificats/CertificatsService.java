package es.caib.carpeta.api.externa.certificats;

import java.util.Base64;
import java.util.Locale;
import javax.annotation.security.RunAs;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.carpeta.commons.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;

/**
 * 
 * @author fbosch
 *
 */

//XYZ ZZZ Arreglar definicions

@RunAs(Constants.CAR_SUPER)
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "BasicAuth", scheme = "basic")
@Path("/secure/certificats/")
@OpenAPIDefinition(tags = @Tag(name = "Certificats", description = "Certificats"))
public class CertificatsService {

    public static final String TAG = "Certificats";

    protected static Logger log = Logger.getLogger(CertificatsService.class);

    @Operation(tags = {
            TAG }, operationId = "descarregarCertificat", summary = "Retorna un certificat a CARPETA", method = "get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Paràmetres incorrectes", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
            // mediaType = MediaType.APPLICATION_JSON, 
            @ApiResponse(responseCode = "200", description = "Llista d'accessos a CARPETA", content = @Content(schema = @Schema(implementation = CertificatBean.class))) })
    @SecurityRequirement(name = "BasicAuth")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/descarregarCertificat")
    public Response descarregarCertificat(

            @Parameter(description = "DNI o NIF de la persona de la qual volem obtenir el certificat.", required = true, example = "99999999X", schema = @Schema(implementation = String.class)) @QueryParam("dni") String dni,

            @Parameter(description = "Codi de l'idioma", required = true, example = "ca", schema = @Schema(implementation = String.class)) @QueryParam("idioma") String idiomaRequest) {
        try {
            CertificatBean cert = new CertificatBean();
            cert.setTipus(CertificatType.FITXER);
            
            CertificatFileInfo fileInfo = new CertificatFileInfo();
            fileInfo.setMime("text/plain");
            fileInfo.setNom("Hola.txt");

            byte[] byteFile = "Hola Caracola".getBytes();
            fileInfo.setBytes(byteFile);

            //fileInfo.setDataB64(Base64.getEncoder().encodeToString(byteFile));            

            fileInfo.setLength(byteFile.length);
            cert.setFitxer(fileInfo);

            return Response.ok().entity(cert).build();

        } catch (Throwable th) {

            String msg;
            if (th instanceof I18NException) {
                I18NException ie = (I18NException) th;
                msg = I18NCommonUtils.getMessage(ie, new Locale(idiomaRequest));
            } else {
                msg = th.getMessage();
            }

            log.error("Error cridada api rest estadistiques accessos: " + msg, th);

            return Response.status(Response.Status.BAD_REQUEST).entity("{ \"error\" : " + "\"" + msg + "\" }").build();

        }
    }

    @Operation(tags = { TAG }, operationId = "teCertificat", summary = "Retorna un CertificatInfo que indica en un boolea si l'usuari te certificat ", method = "get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = " XYZ Paràmetres incorrectes", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
            @ApiResponse(responseCode = "200", description = "XYZ Llista d'accessos a CARPETA", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = CertificatInfo.class))) })
    @SecurityRequirement(name = "BasicAuth")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/teCertificat")
    public Response teCertificat(
            @Parameter(description = "DNI o NIF de la persona de la qual volem saber si té certificat.", required = false, example = "99999999X", schema = @Schema(implementation = String.class)) @QueryParam("dni") String dni) {
        try {
            CertificatInfo cert = new CertificatInfo();
            cert.setTeCertificat(true);
            cert.setAdministrationId(dni);
            return Response.ok().entity(cert).build();
        } catch (Throwable th) {

            String msg = "Error cridada api rest estadistiques accessos: " + th.getMessage();

            log.error(msg, th);
            return Response.status(Response.Status.BAD_REQUEST).entity("{ \"error\" : " + "\"" + msg + "\" }").build();

        }

    }

}
