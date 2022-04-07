package es.caib.carpeta.api.externa.secureexample;

import javax.annotation.security.RolesAllowed;
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
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;


/**
 * 
 * @author anadal
 *
 */
@OpenAPIDefinition(        
        tags = { @Tag(name = "Securetat", description = "Exemple de Securetat"),
               })
@Path("/secure")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SecureExampleRestService {

    protected static Logger log = Logger.getLogger(SecureExampleRestService.class);


    @Operation(operationId = "echo", summary = "Fa un ECHO", tags= { "Securetat" })
    
    @ApiResponse(responseCode = "200",
      description = "Respon el valor enviat per paràmetre",
      content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class)))
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({Constants.CAR_ADMIN, Constants.CAR_SUPER })
    @Path("/echo")
    public Response echo( @Parameter(description = "Cadena a retornar") @QueryParam("echoInput") String echoInput ) {

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
