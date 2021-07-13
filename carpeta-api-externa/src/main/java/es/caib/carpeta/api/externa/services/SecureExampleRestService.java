package es.caib.carpeta.api.externa.services;

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
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import es.caib.carpeta.commons.utils.Constants;



/**
 * 
 * @author anadal
 *
 */
@Path("/secure")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@org.eclipse.microprofile.openapi.annotations.tags.Tag()
public class SecureExampleRestService {

    protected static Logger log = Logger.getLogger(SecureExampleRestService.class);

    @GET
    @Operation(operationId = "echo", summary = "Fa un ECHO")
    //@RolesAllowed({Constants.CAR_ADMIN, Constants.CAR_SUPER })
    @APIResponse(responseCode = "200",
      description = "Respon el valor enviat per paràmetre",
      content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))             
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
