package es.caib.carpeta.api.services;

import es.caib.carpeta.commons.i18n.I18NException;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.ejb.UnitatOrganicaService;
import es.caib.carpeta.persistence.UnitatOrganica;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

/**
 * Recurs REST per accedir a Unitats Organiques.
 *
 * La seguretat es pot establir a nivel de url-pattern/http-method a dins web.xml, o amb l'etiqueta {@link RolesAllowed}
 * a nivell de tota la classe o de recurs. Per poder-la emprar cal que marquem el recurs com un bean {@link Stateless}.
 * Fixam també el {@link TransactionAttribute} al valor {@link TransactionAttributeType#NOT_SUPPORTED} atès que no
 * volem que demarqui transaccions.
 *
 * @author areus
 */
@Stateless
@Path("unitats")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({Constants.CAR_ADMIN})
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class UnitatOrganicaResource {

    @EJB
    private UnitatOrganicaService unitatOrganicaService;

    /**
     * Retorna totes les unitats orgàniques.
     *
     * @return Un codi 200 amb totes les unitats orgàniques.
     */
    @GET
    @Operation(operationId = "getAllUnitats", summary = "Retorna una llista de totes les unitats orgàniques")
    @APIResponse(
            responseCode = "200",
            description = "Llista d'unitats orgàniques",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(type = SchemaType.ARRAY, implementation = UnitatOrganica.class)))
    public Response getAll() throws I18NException {
        List<UnitatOrganica> all = unitatOrganicaService.findAll();
        return Response.ok().entity(all).build();
    }

    /**
     * Obté una Unitat orgàncica.
     *
     * @param id identificador
     * @return Resposta amb status 200 i la informació de la Unitat orgànica o
     * un resposta amb estatus 404 si l'identificador no existeix.
     */
    @GET
    @Path("{id}")
    @Operation(operationId = "getUnitat", summary = "Obté una unitat orgànica")
    @APIResponse(responseCode = "200",
            description = "Unitat orgànica",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UnitatOrganica.class)))
    @APIResponse(responseCode = "404", description = "Recurs no trobat")
    public Response get(@Parameter(description = "L'identificador de la unitat", required = true)
                        @PathParam("id") Long id) {
        UnitatOrganica unitatOrganica = unitatOrganicaService.findById(id);
        if (unitatOrganica != null) {
            return Response.ok(unitatOrganica).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     * Crea una nova unitat orgànica.
     *
     * @param unitatOrganica la nova unitat orgànica a crear.
     * @return Un codi 201 amb la localització de la unitat orgància creada.
     */
    @POST
    @Operation(operationId = "createUnitat", summary = "Crea una nova unitat orgànica")
    @APIResponse(responseCode = "201", description = "Recurs creat",
            headers = @Header(name = "location", description = "Enllaç al nou recurs"))
    public Response create(
            @RequestBody(
                    description = "Unitat orgànica",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UnitatOrganica.class)))
            @Valid UnitatOrganica unitatOrganica) throws I18NException {
        unitatOrganicaService.create(unitatOrganica);
        return Response.created(URI.create("unitats/" + unitatOrganica.getId())).build();
    }

    /**
     * Actualitza una unitat orgànica. Carrega la unitat orgànica indicada per l'identificador i l'actualitza
     * amb els camps rebuts.
     *
     * @param unitatOrganica dades de la unitat orgànica a actualitzar.
     * @param id             Identificador de la unitat orgància a actualitzar.
     * @return Resposta amb status 204 si l'operació té èxit, o 404 si el recurs amb l'id indicat no existeix.
     */
    @PUT
    @Path("{id}")
    @Operation(operationId = "updateUnitat", summary = "Actualitza una unitat orgànica")
    @APIResponse(responseCode = "204", description = "Operació realitzada correctament")
    @APIResponse(responseCode = "404", description = "Recurs no trobat")
    public Response update(
            @RequestBody(
                    description = "Unitat orgànica",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UnitatOrganica.class)))
            @Valid UnitatOrganica unitatOrganica,
            @Parameter(description = "L'identificador de la unitat", required = true)
            @PathParam("id") Long id) throws I18NException {

        UnitatOrganica unitatActual = unitatOrganicaService.findById(id);
        if (unitatActual == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            unitatActual.setNom(unitatOrganica.getNom());
            unitatActual.setCodiDir3(unitatOrganica.getCodiDir3());
            unitatActual.setEstat(unitatOrganica.getEstat());
            unitatActual.setDataCreacio(unitatOrganica.getDataCreacio());
            unitatOrganicaService.update(unitatActual);
            return Response.noContent().build();
        }
    }

    /**
     * Esborra una unitat orgànica.
     *
     * @param id identificador
     * @return Resposta amb status 204 si l'operació té èxit, o 404 si el recurs amb l'id indicat no existeix.
     */
    @DELETE
    @Path("{id}")
    @Operation(operationId = "deleteUnitat", summary = "Esborra una unitat orgànica")
    @APIResponse(responseCode = "204", description = "Operació realitzada correctament")
    @APIResponse(responseCode = "404", description = "Recurs no trobat")
    public Response delete(@Parameter(description = "L'identificador de la unitat", required = true)
                           @PathParam("id") Long id) throws I18NException {
        if (unitatOrganicaService.findById(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            unitatOrganicaService.delete(id);
            return Response.noContent().build();
        }
    }
}
