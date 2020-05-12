package es.caib.carpeta.api.services;

import es.caib.carpeta.commons.i18n.I18NException;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.ejb.ProcedimentService;
import es.caib.carpeta.persistence.Procediment;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.links.Link;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

/**
 * Recurs REST per accedir a Procediments.
 *
 * La seguretat es pot establir a nivel de url-pattern/http-method a dins web.xml, o amb l'etiqueta {@link RolesAllowed}
 * a nivell de tota la classe o de recurs. Per poder-la emprar cal que marquem el recurs com un bean {@link Stateless}.
 * Fixam també el {@link TransactionAttribute} al valor {@link TransactionAttributeType#NOT_SUPPORTED} atès que no
 * volem que demarqui transaccions.
 *
 *  @author areus
 */
@Stateless
@Path("procediments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({Constants.CAR_ADMIN})
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ProcedimentResource {

    @EJB
    private ProcedimentService procedimentService;

    /**
     * Retorna tots els procediments d'una unitat orgànica.
     *
     * @param unitatId identificador de la unitat orgànica
     * @return Un codi 200 amb tots els procediments
     */
    @GET
    @Operation(operationId = "getProcedimentsByUnitat", summary = "Retorna una llista de tots els procediments")
    @APIResponse(
            responseCode = "200",
            description = "Llista de procediments",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(type = SchemaType.ARRAY, implementation = Procediment.class)))
    public Response getAll(
            @Parameter(description = "L'identificador de la unitat", required = true)
            @QueryParam("unitatId") Long unitatId) {
        List<Procediment> all = procedimentService.findAllByUnitatOrganica(unitatId);
        return Response.ok().entity(all).build();
    }

    /**
     * Obté un procediment
     *
     * @param id identificador
     * @return Resposta amb status 200 i la informació del procediment o
     * un resposta amb estatus 404 si l'identificador no existeix.
     */
    @GET
    @Path("{id}")
    @Operation(operationId = "getProcediment", summary = "Obté un procediment")
    @APIResponse(responseCode = "200",
            description = "Procediment",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Procediment.class)),
            links = @Link(name = "unitat", description = "Enllaç a la unitat orgància del procediment")
    )
    @APIResponse(responseCode = "404", description = "Recurs no trobat")
    public Response get(@Parameter(description = "L'identificador del procediment", required = true)
                        @PathParam("id") Long id) {
        Procediment procediment = procedimentService.findById(id);
        if (procediment != null) {
            return Response.ok(procediment)
                    .link(URI.create("unitats/" + procediment.getUnitatOrganica().getId()), "unitat")
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     * Crea un nou procediment dependent d'una unitat orgànica.
     *
     * @param procediment la nova unitat orgànica a crear.
     * @param unitatId    identificador de la unitat orgànica de la que dependrà el procediment.
     * @return Un codi 201 amb la localització del procediment creat.
     */
    @POST
    @Operation(operationId = "createProcediment", summary = "Crea un nou procediment associat a la unitat orgànica")
    @APIResponse(responseCode = "201", description = "Recurs creat",
            headers = @Header(name = "location", description = "Enllaç al nou recurs"))
    public Response create(
            @RequestBody(
                    description = "Procediment",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Procediment.class)))
            @Valid Procediment procediment,
            @Parameter(description = "L'identificador de la unitat", required = true)
            @QueryParam("unitatId") Long unitatId) throws I18NException {
        procedimentService.create(procediment, unitatId);
        return Response.created(URI.create("procediments/" + procediment.getId())).build();
    }

    /**
     * Actualitza un procediment. Carrega el procediment indicat per l'identificador i l'actualitza
     * amb els camps rebuts.
     *
     * @param procediment dades a actualitzar del procediment.
     * @param id          Identificador del procediment a actualitzar.
     * @return Resposta amb status 204 si l'operació té èxit, o 404 si el recurs amb l'id indicat no existeix.
     */
    @PUT
    @Path("{id}")
    @Operation(operationId = "updateProcediment", summary = "Actualitza un procediment")
    @APIResponse(responseCode = "204", description = "Operació realitzada correctament")
    @APIResponse(responseCode = "404", description = "Recurs no trobat")
    public Response update(
            @RequestBody(
                    description = "Procediment",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Procediment.class)))
            @Valid Procediment procediment,
            @Parameter(description = "L'identificador del procediment", required = true)
            @PathParam("id") Long id) throws I18NException {
        Procediment procedimentActual = procedimentService.findById(id);
        if (procedimentActual == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            procedimentActual.setCodiSia(procediment.getCodiSia());
            procedimentActual.setNom(procediment.getNom());
            procedimentService.update(procedimentActual);
            return Response.noContent().build();
        }
    }

    /**
     * Esborra un procediment
     *
     * @param id identificador
     * @return Resposta amb status 204 si l'operació té èxit, o 404 si el recurs amb l'id indicat no existeix.
     */
    @DELETE
    @Path("{id}")
    @Operation(operationId = "deleteProcediment", summary = "Esborra un procediment")
    @APIResponse(responseCode = "204", description = "Operació realitzada correctament")
    @APIResponse(responseCode = "404", description = "Recurs no trobat")
    public Response delete(@Parameter(description = "L'identificador del procediment", required = true)
                           @PathParam("id") Long id) throws I18NException {
        if (procedimentService.findById(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            procedimentService.delete(id);
            return Response.noContent().build();
        }
    }
}
