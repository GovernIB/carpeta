package es.caib.carpeta.api.externa.estadistiques;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.carpeta.commons.utils.Configuracio;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.ejb.PropietatGlobalService;
import es.caib.carpeta.logic.AccesLogicaService;
import es.caib.carpeta.logic.PluginDeCarpetaFrontLogicaService;
import es.caib.carpeta.logic.PluginEntitatLogicaService;
import es.caib.carpeta.logic.utils.EjbManager;
import es.caib.carpeta.model.entity.Acces;
import es.caib.carpeta.model.entity.Plugin;
import es.caib.carpeta.model.fields.PluginFields;
import es.caib.carpeta.persistence.PluginJPA;

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
@Path("/public/estadistiques")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

@OpenAPIDefinition(tags = @Tag(name = "Estadistiques", description = "Estadistiques d'Accessos"))
public class Estadistiques {

    protected static Logger log = Logger.getLogger(Estadistiques.class);

    @EJB(mappedName = PluginEntitatLogicaService.JNDI_NAME)
    protected PluginEntitatLogicaService pluginEntitatLogicaEjb;

    @EJB(mappedName = PluginDeCarpetaFrontLogicaService.JNDI_NAME)
    protected PluginDeCarpetaFrontLogicaService pluginCarpetaFrontEjb;

    @EJB(mappedName = AccesLogicaService.JNDI_NAME)
    protected AccesLogicaService accesEjb;

    @Operation(
            tags = "Estadistiques",
            operationId = "accessos",
            summary = "Retorna la llista d`accessos a CARPETA",
            method = "get")
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

        return accessosStatic(pluginCarpetaFrontEjb, accesEjb, pluginEntitatLogicaEjb,
                dataIniciRequest, dataFiRequest, entitatRequest, idiomaRequest);
    }

    protected static Response accessosStatic(PluginDeCarpetaFrontLogicaService pluginCarpetaFrontEjb,
            AccesLogicaService accesEjb, PluginEntitatLogicaService  pluginEntitatLogicaEjb, 
            String dataIniciRequest, String dataFiRequest, String entitatRequest, String idiomaRequest) {
        try {

            // Si no hi ha parametres de dates, es retorna per defecte el darrer mes
            // Li suman 1 dia per fer les cerques inclusives

            ZoneId zoneId = ZoneId.of("Europe/Madrid");
            ZonedDateTime dataFiDate;
            if (dataFiRequest == null) {
                dataFiDate = ZonedDateTime.now(zoneId).plusDays(1);
                dataFiRequest = dataFiDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } else {
                dataFiDate = LocalDate
                        .parse(dataFiRequest, DateTimeFormatter.ofPattern("yyyy-MM-dd")).plusDays(1)
                        .atStartOfDay(zoneId);
                dataFiRequest = dataFiDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

            ZonedDateTime dataIniciDate;
            if (dataIniciRequest == null) {
                dataIniciDate = LocalDate
                        .parse(dataFiRequest, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        .minusMonths(1).atStartOfDay(zoneId);
                dataIniciRequest = dataIniciDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } else {
                dataIniciDate = LocalDate
                        .parse(dataIniciRequest, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        .atStartOfDay(zoneId);
            }

            if (Configuracio.isDesenvolupament()) {
                log.info("REQUEST API EXTERNA ACCESSOS: {inici=" + dataIniciRequest + "&fi="
                        + dataFiRequest + "&entitat=" + entitatRequest + "&idioma=" + idiomaRequest
                        + "&timezone=" + dataFiDate.getZone() + "}");
            }

            // si no hi ha entitatId, es retorna la propietatGlobal defaultEntity
            PropietatGlobalService propietatGlobalEjb = EjbManager.getPropietatLogicaEJB();
            if (entitatRequest == null) {
                entitatRequest = EjbManager.getDefaultEntityCode(propietatGlobalEjb);
            }

            // Si no es defineix idioma, agafa el de la aplicació i en cas de no estar
            // definit, català per defecte.
            idiomaRequest = (idiomaRequest != null) ? idiomaRequest
                    : (Configuracio.getDefaultLanguage() != null)
                            ? Configuracio.getDefaultLanguage()
                            : "ca";

            // Comprobam que la diferencia entre dates no supera el maxDays
            int maxDays = Integer.valueOf(EjbManager.getAccessosMaxDies(propietatGlobalEjb));
            long daysBeetween = Duration.between(dataIniciDate, dataFiDate).toDays();
            if (daysBeetween > maxDays) {
                // .entity("La diferencia entre data inici i fi no pot ser superior a " +
                // maxDays)
                return Response.status(Status.BAD_REQUEST).entity(Collections.emptyList()).build();
            }

            // si falta entitat, retorna error 400
            if (entitatRequest == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{ \"error\" : " + "\"No s'ha definit el paràmetre 'entitat'\" }")
                        .build();
            }

            List<Long> pluginsEntitat = pluginEntitatLogicaEjb
                    .getAllPluginsByEntitat(entitatRequest);
            List<Plugin> plugins = pluginCarpetaFrontEjb
                    .getAllPlugins(PluginFields.PLUGINID.in(pluginsEntitat));
            Map<Long, String> llistaPlugins = new HashMap<Long, String>(plugins.size());
            for (Plugin plugin : plugins) {
                PluginJPA p = (PluginJPA) plugin;
                llistaPlugins.put(p.getPluginID(),
                        p.getNom().getTraduccio(idiomaRequest).getValor());
            }

            List<Acces> accesos = accesEjb.findBetweenDates(
                    java.sql.Date.valueOf(LocalDate.parse(dataIniciRequest,
                            DateTimeFormatter.ofPattern("yyyy-MM-dd"))),
                    java.sql.Date.valueOf(LocalDate.parse(dataFiRequest,
                            DateTimeFormatter.ofPattern("yyyy-MM-dd"))),
                    entitatRequest);

            List<AccesDTO> accesosInfo = new ArrayList<AccesDTO>();

            for (Acces item : accesos) {

                String tipusAcces = "";
                String nomPlugin = "";
                Long itemPluginId = item.getPluginID();

                switch (item.getTipus()) {
                    case Constants.TIPUS_ACCES_LOGIN_AUTENTICAT:
                        tipusAcces = "Login Autenticat";
                        nomPlugin = (Configuracio.isCAIB()) ? "LoginIB" : "Login";
                    break;
                    case Constants.TIPUS_ACCES_LOGIN_NO_AUTENTICAT:
                        tipusAcces = "Login No Autenticat";
                        nomPlugin = (Configuracio.isCAIB()) ? "LoginIB" : "Login";
                    break;
                    case Constants.TIPUS_ACCES_PLUGIN:
                        tipusAcces = "Accés a Plugin";
                        nomPlugin = (llistaPlugins.containsKey(itemPluginId))
                                ? llistaPlugins.get(itemPluginId)
                                : String.valueOf(itemPluginId);
                    break;
                }

                accesosInfo.add(
                        new AccesDTO(item.getProveidorIdentitat(), item.getMetodeAutenticacio(),
                                item.getQaa(), item.getDataAcces(), item.getIdioma(),
                                entitatRequest, tipusAcces, nomPlugin, item.getIdsessio()));
            }

            return Response.ok().entity(new PaginaAccesDTO(accesosInfo)).build();

        } catch (Throwable th) {

            String msg;
            if (th instanceof I18NException) {
                I18NException ie = (I18NException) th;
                msg = I18NCommonUtils.getMessage(ie, new Locale(idiomaRequest));
            } else {
                msg = th.getMessage();
            }

            log.error("Error cridada api rest estadistiques accessos: " + msg, th);

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{ \"error\" : " + "\"" + msg + "\" }").build();

        }
    }

}
