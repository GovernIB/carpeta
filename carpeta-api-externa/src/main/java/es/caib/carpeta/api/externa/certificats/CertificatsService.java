package es.caib.carpeta.api.externa.certificats;


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
 * @author jagarcia
 * @author anadal (anotacions openapi)
 *
 */
@RunAs(Constants.CAR_SUPER)
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "BasicAuth", scheme = "basic")
@Path("/secure/certificats/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@OpenAPIDefinition(tags = @Tag(name = "Certificats", description = "Certificats"))
public class CertificatsService {

    protected static Logger log = Logger.getLogger(CertificatsService.class);

    @Operation(tags = {"Certificats", "Securetat"}, operationId = "certificats", summary = "Retorna un certificat a CARPETA", method = "get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Paràmetres incorrectes", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
            @ApiResponse(responseCode = "200", description = "Llista d'accessos a CARPETA", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = CertificatBean.class))) })
    @SecurityRequirement(name = "BasicAuth")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/certificat")
    public Response estadistiques(

            @Parameter(description = "DNI o NIF de la persona de la qual volem obtenir el certificat.", required = false, example = "99999999X", schema = @Schema(implementation = String.class)) @QueryParam("dni") String dni,

            @Parameter(description = "Codi de l'idioma", required = false, example = "ca", schema = @Schema(implementation = String.class)) @QueryParam("idioma") String idiomaRequest) {
        try {
            CertificatBean cert = new CertificatBean();
            cert.setTipus(CertificatType.FITXER.getValue());
            CertificatFileInfo fileInfo = new CertificatFileInfo();
            fileInfo.setMime("text/plain");
            fileInfo.setNom("Hola.txt");
            
            byte[] byteFile = "Hola Caracola".getBytes();
            fileInfo.setBytes(byteFile);
            
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

}
