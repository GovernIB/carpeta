package es.caib.carpeta.api.externa.secure.echo;

import javax.annotation.security.RolesAllowed;
import javax.annotation.security.RunAs;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import es.caib.carpeta.commons.utils.Constants;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;

/**
 * 
 * @author anadal
 *
 */
@RunAs(Constants.CAR_SUPER)
@OpenAPIDefinition(
        info = @Info(
                title = "API REST EXTERNA de Carpeta - Echo",
                description = "Exemple de Echo amb seguretat.",
                version = "1.0.0",
                license = @License(
                        name = "European Union Public Licence (EUPL v1.2)",
                        url = "https://joinup.ec.europa.eu/sites/default/files/custom-page/attachment/eupl_v1.2_es.pdf"),
                contact = @Contact(
                        name = "Departament de Govern Digital a la Fundació Bit",
                        email = "governdigital.carpeta@fundaciobit.org",
                        url = "https://governdigital.fundaciobit.org")

        ),
        externalDocs = @ExternalDocumentation(
                description = "Java Client (GovernIB Github)",
                url = "https://github.com/GovernIB/carpeta/tree/carpeta-1.1/carpeta-api-externa"),
        tags = { @Tag(name = "Echo", description = "Implementació de Echo"), })
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "BasicAuth", scheme = "basic")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/secure/echo")
public class SecureExampleRestService {

    protected static Logger log = Logger.getLogger(SecureExampleRestService.class);

    @Operation(operationId = "echo", summary = "Fa un ECHO", tags = { "Securetat" })
    @ApiResponse(
            responseCode = "200",
            description = "Respon el valor enviat per paràmetre",
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class)))
    @SecurityRequirement(name = "BasicAuth")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({ Constants.CAR_ADMIN, Constants.CAR_SUPER })
    @Path("/echo")
    public Response echo(@Parameter(description = "Cadena a retornar") @QueryParam("echoInput") String echoInput) {

        log.info(" Entra a ECHO de Secure ... ");

        try {
            final String echoOutput = new String(echoInput);

            return Response.ok().entity(echoOutput).build();

        } catch (Exception e) {
            log.error("Error cridada api rest estadistiques accessos: " + e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

}
