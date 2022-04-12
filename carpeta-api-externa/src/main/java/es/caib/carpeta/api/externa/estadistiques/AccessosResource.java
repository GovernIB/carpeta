package es.caib.carpeta.api.externa.estadistiques;


import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import es.caib.carpeta.logic.AccesLogicaService;
import es.caib.carpeta.logic.PluginDeCarpetaFrontLogicaService;
import es.caib.carpeta.logic.PluginEntitatLogicaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@Path("/services")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

@OpenAPIDefinition(tags = @Tag(name = "Accessos", description = "Estadistiques d'Accessos"))
public class AccessosResource {

    protected static Logger log = Logger.getLogger(AccessosResource.class);

    @EJB(mappedName = PluginEntitatLogicaService.JNDI_NAME)
    protected PluginEntitatLogicaService pluginEntitatLogicaEjb;

    @EJB(mappedName = PluginDeCarpetaFrontLogicaService.JNDI_NAME)
    protected PluginDeCarpetaFrontLogicaService pluginCarpetaFrontEjb;

    @EJB(mappedName = AccesLogicaService.JNDI_NAME)
    protected AccesLogicaService accesEjb;

    @Operation(
            tags = "Accessos",
            operationId = "accessos",
            summary = "Retorna la llista d`accessos a CARPETA",
            method = "get",
            deprecated = true)
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "Paràmetres incorrectes",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON)),
                    @ApiResponse(
                            responseCode = "200",
                            description = "Llista d'accessos a CARPETA",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = PaginaAccesDTO.class))) })
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/accessos")
    @Deprecated
    public Response estadistiques(

            @Parameter(
                    description = "Data d'inici, en format YYYY-MM-DD, a partir de la qual volem obtenir estadistiques",
                    required = false,
                    example = "2021-04-24",
                    schema = @Schema(implementation = String.class))
            @QueryParam("inici") String dataIniciRequest,

            @Parameter(
                    description = "Data fi, en format YYYY-MM-DD, fins la qual volem tenir estadistiques",
                    required = false,
                    example = "2021-08-12",
                    schema = @Schema(implementation = String.class))
            @QueryParam("fi") String dataFiRequest,

            @Parameter(
                    description = "Codi de l'entitat de la qual obtenim les estadistiques",
                    required = true,
                    example = "caib",
                    schema = @Schema(implementation = String.class))
            @QueryParam("entitat") String entitatRequest,

            @Parameter(
                    description = "Codi de l'idioma",
                    required = false,
                    example = "ca",
                    schema = @Schema(implementation = String.class))
            @QueryParam("idioma") String idiomaRequest) {

        return Estadistiques.accessosStatic(pluginCarpetaFrontEjb, accesEjb, pluginEntitatLogicaEjb,
                dataIniciRequest, dataFiRequest, entitatRequest, idiomaRequest);

    }

}
