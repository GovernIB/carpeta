package es.caib.carpeta.api.externa.estadistiques;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.utils.rest.GenAppEntityConverter;
import org.fundaciobit.pluginsib.utils.rest.GenAppRangeOfDates;
import org.fundaciobit.pluginsib.utils.rest.GenAppRestUtils;
import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.fundaciobit.pluginsib.utils.rest.RestExceptionInfo;
import org.fundaciobit.pluginsib.utils.rest.RestUtils;

import es.caib.carpeta.commons.utils.Configuracio;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.ejb.PropietatGlobalService;
import es.caib.carpeta.logic.AccesLogicaService;
import es.caib.carpeta.logic.PluginDeCarpetaFrontLogicaService;
import es.caib.carpeta.logic.PluginEntitatLogicaService;
import es.caib.carpeta.logic.utils.EjbManager;
import es.caib.carpeta.model.entity.Acces;
import es.caib.carpeta.model.entity.Plugin;
import es.caib.carpeta.model.fields.AccesFields;
import es.caib.carpeta.model.fields.AccesQueryPath;
import es.caib.carpeta.model.fields.PluginFields;
import es.caib.carpeta.persistence.PluginJPA;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;

/**
 * 
 * @author jagarcia
 * @author anadal (anotacions openapi)
 * @author anadal (refactoring a RestUtils)
 *
 */
@RunAs(Constants.CAR_SUPER)
@Path("/public/estadistiques")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@OpenAPIDefinition(tags = @Tag(name = Estadistiques.TAG_NAME, description = "Estadístiques d'Accessos"))
public class Estadistiques extends RestUtils {

    public static final int DEFAULT_ITEMS_PER_PAGE = 25;

    public static final String TAG_NAME = "Estadistiques";

    protected static Logger log = Logger.getLogger(Estadistiques.class);

    @EJB(mappedName = PluginEntitatLogicaService.JNDI_NAME)
    protected PluginEntitatLogicaService pluginEntitatLogicaEjb;

    @EJB(mappedName = PluginDeCarpetaFrontLogicaService.JNDI_NAME)
    protected PluginDeCarpetaFrontLogicaService pluginCarpetaFrontEjb;

    @EJB(mappedName = AccesLogicaService.JNDI_NAME)
    protected AccesLogicaService accesEjb;

    @Operation(tags = TAG_NAME, operationId = "accessos", summary = "Retorna la llista d`accessos a CARPETA")
    @ApiResponses(
            value = {

                    @ApiResponse(
                            responseCode = "200",
                            description = "Llista d'accessos a CARPETA",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = LlistatPaginatAcces.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Paràmetres incorrectes",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = RestExceptionInfo.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error no controlat",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = RestExceptionInfo.class))) })
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/accessos")
    public LlistatPaginatAcces estadistiques(

            @Parameter(
                    description = "Codi de l'entitat de la qual obtenim les estadístiques",
                    required = false,
                    example = "caib",
                    schema = @Schema(implementation = String.class)) @QueryParam("entitat") String entitat,

            @Parameter(
                    name = "startdate",
                    description = "Filtre Data d'inici de la consulta. Opcional. Format yyyy-MM-dd (ISO 8601)",
                    required = false,
                    example = "2023-07-19",
                    in = ParameterIn.QUERY,
                    schema = @Schema(
                            implementation = String.class,
                            pattern = DATE_PATTERN_ISO8601_ONLYDATE)) @QueryParam("startdate") String startdate,

            @Parameter(
                    name = "enddate",
                    description = "Filtre Data final de la consulta. Opcional. Format yyyy-MM-dd (ISO 8601)",
                    required = false,
                    example = "2024-12-31",
                    in = ParameterIn.QUERY,
                    schema = @Schema(
                            implementation = String.class,
                            pattern = DATE_PATTERN_ISO8601_ONLYDATE)) @QueryParam("enddate") String enddate,
            @Parameter(
                    name = "page",
                    description = "Numero de pàgina quan el llistat és paginat. Opcional. Per defecte 1.",
                    required = false,
                    in = ParameterIn.QUERY,
                    schema = @Schema(implementation = Integer.class)) @QueryParam("page") Integer page,

            @Parameter(
                    name = "page-size",
                    description = "Número d'elements a retornar per pàgina. Opcional. Per defecte "
                            + DEFAULT_ITEMS_PER_PAGE,
                    required = false,
                    in = ParameterIn.QUERY,
                    schema = @Schema(implementation = Integer.class)) @QueryParam("page-size") Integer pageSize,

            @Parameter(
                    name = "language",
                    description = "Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,
                    required = false,
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(
                            defaultValue = "ca",
                            implementation = String.class)) @QueryParam("language") String language,

            @Parameter(hidden = true) @Context HttpServletRequest request) throws RestException {

        try {

            // Check de page i pagesize
            if (page == null || page <= 0) {
                page = 1;
            }
            if (pageSize == null || pageSize < 1) {
                pageSize = DEFAULT_ITEMS_PER_PAGE;
            }

            final String entitatFinal;
            {
                // Check nom de l'entitat
                // si no hi ha entitatId, es retorna la propietatGlobal defaultEntity
                PropietatGlobalService propietatGlobalEjb = EjbManager.getPropietatLogicaEJB();
                if (entitat == null) {
                    entitat = EjbManager.getDefaultEntityCode(propietatGlobalEjb);
                }
    
                // si falta entitat, retorna error 400
                if (entitat == null) {
                    throw new RestException("No s'ha definit el paràmetre 'entitat'", Response.Status.BAD_REQUEST);
                }
                entitatFinal = entitat;
            }

            // TODO Canviar request.getRequestURL() pel nom del servidor
            // En entorns CAIB no funciona  request.getRequestURL()
            StringBuilder nextQuery = new StringBuilder(
                    request.getRequestURL() + "?page=" + (page + 1) + "&page-size=" + pageSize + "&entitat=" + entitat);

            // Check Dates 
            Where wd = null;
            {
                GenAppRangeOfDates grd = GenAppRestUtils.checkRangeOfOnlyDates(startdate, "startdate", enddate,
                        "enddate", AccesFields.DATAACCES, nextQuery, language);
                wd = grd.getWhere();
            }

            // Check de language
            if (language == null || language.trim().length() == 0) {
                language = "ca";
            } else {
                if (!"es".equals(language) && !"ca".equals(language)) {
                    language = "ca";
                }
                nextQuery.append("&language=" + language);
            }

            if (Configuracio.isDesenvolupament()) {
                log.info("REQUEST API EXTERNA ACCESSOS: {startdate=" + startdate + "&enddate=" + enddate + "&entitat="
                        + entitatFinal + "&language=" + language + "}");
            }

            List<Long> pluginsEntitat = pluginEntitatLogicaEjb.getAllPluginsByEntitat(entitat);
            List<Plugin> plugins = pluginCarpetaFrontEjb.getAllPlugins(PluginFields.PLUGINID.in(pluginsEntitat));
            Map<Long, String> llistaPlugins = new HashMap<Long, String>(plugins.size());
            for (Plugin plugin : plugins) {
                PluginJPA p = (PluginJPA) plugin;
                llistaPlugins.put(p.getPluginID(), p.getNom().getTraduccio(language).getValor());
            }
            
            final String name = "es".equalsIgnoreCase(language) ? "Listado de accesos a Carpeta"
                    : "Llistat d'accessos a Carpeta";


            Where we = new AccesQueryPath().ENTITAT().CODI().equal(entitat);
            Where w = Where.AND(wd, we);
            OrderBy order = new OrderBy(AccesFields.DATAACCES, OrderType.DESC);
            //List<Acces> accesos = accesEjb.select(w,order);
            
            
            GenAppEntityConverter<Acces, AccesDTO> converter = new GenAppEntityConverter<Acces, AccesDTO>() {

                @Override
                public AccesDTO convert(Acces item) throws RestException {
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
                            nomPlugin = (llistaPlugins.containsKey(itemPluginId)) ? llistaPlugins.get(itemPluginId)
                                    : String.valueOf(itemPluginId);
                        break;
                    }

                    log.info("DATA => " + item.getDataAcces() );

                    return new AccesDTO(item.getProveidorIdentitat(), item.getMetodeAutenticacio(), item.getQaa(),
                            item.getDataAcces(), item.getIdioma(), entitatFinal, tipusAcces, nomPlugin, item.getIdsessio());
                }
            };
            
/*
            InternalAccessPagination accesos = GenAppRestUtils.createRestPagination(InternalAccessPagination.class,
                    accesEjb, (int) page, (int) pageSize, w, order, converter);

            List<AccesDTO> accesosInfo = new ArrayList<AccesDTO>();

            for (Acces item : accesos.getData()) {

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
                        nomPlugin = (llistaPlugins.containsKey(itemPluginId)) ? llistaPlugins.get(itemPluginId)
                                : String.valueOf(itemPluginId);
                    break;
                }

                log.info("DATA => " + item.getDataAcces() );

                accesosInfo.add(new AccesDTO(item.getProveidorIdentitat(), item.getMetodeAutenticacio(), item.getQaa(),
                        item.getDataAcces(), item.getIdioma(), entitat, tipusAcces, nomPlugin, item.getIdsessio()));
            }

            

            return new LlistatPaginatAcces(accesosInfo, accesos, nextQuery.toString(), name);
            */
            return GenAppRestUtils.createRestPagination(LlistatPaginatAcces.class,
                    accesEjb, (int) page, (int) pageSize, w, order, converter, name, nextQuery);
            
         
        } catch (Throwable th) {

            String msg;
            if (th instanceof I18NException) {
                I18NException ie = (I18NException) th;
                msg = I18NCommonUtils.getMessage(ie, new Locale(language));
            } else {
                msg = th.getMessage();
            }

            // XYZ ZZZ
            log.error("Error cridada api rest estadistiques accessos: " + msg, th);

            throw new RestException(msg, Response.Status.BAD_REQUEST);
        }
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/consulta/v2/notificacions/{nif}")
    public Response consultaNotificacions(@PathParam("nif") String nif) {
        log.info("REST request health check");
        return Response.status(200).entity("{\n"
                + "  \"numeroElementsTotals\": 323,\n"
                + "  \"numeroElementsRetornats\": 3,\n"
                + "  \"resultat\": [\n"
                + "    {\n"
                + "      \"id\": 3752500,\n"
                + "      \"emisor\": \"GOIB\",\n"
                + "      \"organGestor\": {\n"
                + "        \"codi\": \"A04027005\",\n"
                + "        \"nom\": \"Direcció General de Simplificació Administrativa, Modernització i Administració Digital\",\n"
                + "        \"descripcio\": null\n"
                + "      },\n"
                + "      \"procediment\": {\n"
                + "        \"codi\": \"894623\",\n"
                + "        \"nom\": \"Mesures de camp electromagnètic (SE)\",\n"
                + "        \"descripcio\": null\n"
                + "      },\n"
                + "      \"numExpedient\": null,\n"
                + "      \"concepte\": \"test not web\",\n"
                + "      \"descripcio\": null,\n"
                + "      \"dataEnviament\": \"2024-01-25T07:34:53.962+0000\",\n"
                + "      \"estat\": {\n"
                + "        \"codi\": \"EXPIRADA\",\n"
                + "        \"nom\": \"Expirada\",\n"
                + "        \"descripcio\": \"S’ha superat la data de caducitat\"\n"
                + "      },\n"
                + "      \"dataEstat\": \"2024-02-02T23:00:00.000+0000\",\n"
                + "      \"document\": {\n"
                + "        \"nom\": \"blank.pdf\",\n"
                + "        \"mediaType\": \"application/pdf\",\n"
                + "        \"mida\": 6911,\n"
                + "        \"url\": \"https://se.caib.es/notibapi/interna/consulta/v2/document/3752498\"\n"
                + "      },\n"
                + "      \"titular\": {\n"
                + "        \"tipus\": {\n"
                + "          \"codi\": \"FISICA\",\n"
                + "          \"nom\": \"Persona física\",\n"
                + "          \"descripcio\": null\n"
                + "        },\n"
                + "        \"nom\": \"Nom\",\n"
                + "        \"llinatge1\": \"Llinatges\",\n"
                + "        \"llinatge2\": null,\n"
                + "        \"nif\": \"99999999R\",\n"
                + "        \"email\": null\n"
                + "      },\n"
                + "      \"destinataris\": [],\n"
                + "      \"error\": false,\n"
                + "      \"errorData\": null,\n"
                + "      \"errorDescripcio\": null,\n"
                + "      \"justificant\": null,\n"
                + "      \"certificacio\": \"https://se.caib.es/notibapi/interna/consulta/v2/certificacio/3752500\"\n"
                + "    },\n"
                + "    {\n"
                + "      \"id\": 3975600,\n"
                + "      \"emisor\": \"GOIB\",\n"
                + "      \"organGestor\": {\n"
                + "        \"codi\": \"A04027005\",\n"
                + "        \"nom\": \"Direcció General de Simplificació Administrativa, Modernització i Administració Digital\",\n"
                + "        \"descripcio\": null\n"
                + "      },\n"
                + "      \"procediment\": {\n"
                + "        \"codi\": \"894623\",\n"
                + "        \"nom\": \"Mesures de camp electromagnètic (SE)\",\n"
                + "        \"descripcio\": null\n"
                + "      },\n"
                + "      \"numExpedient\": null,\n"
                + "      \"concepte\": \"Activitat 4\",\n"
                + "      \"descripcio\": \"Activitat de la Dehú\",\n"
                + "      \"dataEnviament\": \"2024-01-29T16:47:51.012+0000\",\n"
                + "      \"estat\": {\n"
                + "        \"codi\": \"EXPIRADA\",\n"
                + "        \"nom\": \"Expirada\",\n"
                + "        \"descripcio\": \"S’ha superat la data de caducitat\"\n"
                + "      },\n"
                + "      \"dataEstat\": \"2024-02-08T23:00:00.000+0000\",\n"
                + "      \"document\": {\n"
                + "        \"nom\": \"annex_firmat.pdf\",\n"
                + "        \"mediaType\": \"application/pdf\",\n"
                + "        \"mida\": 67506,\n"
                + "        \"url\": \"https://se.caib.es/notibapi/interna/consulta/v2/document/3975597\"\n"
                + "      },\n"
                + "      \"titular\": {\n"
                + "        \"tipus\": {\n"
                + "          \"codi\": \"FISICA\",\n"
                + "          \"nom\": \"Persona física\",\n"
                + "          \"descripcio\": null\n"
                + "        },\n"
                + "        \"nom\": \"Nom\",\n"
                + "        \"llinatge1\": \"Llinatge1 Llinatge2\",\n"
                + "        \"llinatge2\": null,\n"
                + "        \"nif\": \"99999999R\",\n"
                + "        \"email\": null\n"
                + "      },\n"
                + "      \"destinataris\": [],\n"
                + "      \"error\": false,\n"
                + "      \"errorData\": null,\n"
                + "      \"errorDescripcio\": null,\n"
                + "      \"justificant\": null,\n"
                + "      \"certificacio\": \"https://se.caib.es/notibapi/interna/consulta/v2/certificacio/3975600\"\n"
                + "    },\n"
                + "    {\n"
                + "      \"id\": 3975700,\n"
                + "      \"emisor\": \"GOIB\",\n"
                + "      \"organGestor\": {\n"
                + "        \"codi\": \"A04027005\",\n"
                + "        \"nom\": \"Direcció General de Simplificació Administrativa, Modernització i Administració Digital\",\n"
                + "        \"descripcio\": null\n"
                + "      },\n"
                + "      \"procediment\": {\n"
                + "        \"codi\": \"894623\",\n"
                + "        \"nom\": \"Mesures de camp electromagnètic (SE)\",\n"
                + "        \"descripcio\": null\n"
                + "      },\n"
                + "      \"numExpedient\": null,\n"
                + "      \"concepte\": \"Activitat 4\",\n"
                + "      \"descripcio\": \"Activitat de la Dehú\",\n"
                + "      \"dataEnviament\": \"2024-01-29T16:49:32.019+0000\",\n"
                + "      \"estat\": {\n"
                + "        \"codi\": \"EXPIRADA\",\n"
                + "        \"nom\": \"Expirada\",\n"
                + "        \"descripcio\": \"S’ha superat la data de caducitat\"\n"
                + "      },\n"
                + "      \"dataEstat\": \"2024-02-08T23:00:00.000+0000\",\n"
                + "      \"document\": {\n"
                + "        \"nom\": \"annex_firmat.pdf\",\n"
                + "        \"mediaType\": \"application/pdf\",\n"
                + "        \"mida\": 67506,\n"
                + "        \"url\": \"https://se.caib.es/notibapi/interna/consulta/v2/document/3975697\"\n"
                + "      },\n"
                + "      \"titular\": {\n"
                + "        \"tipus\": {\n"
                + "          \"codi\": \"FISICA\",\n"
                + "          \"nom\": \"Persona física\",\n"
                + "          \"descripcio\": null\n"
                + "        },\n"
                + "        \"nom\": \"Nom\",\n"
                + "        \"llinatge1\": \"Llinatge1 Llinatge2\",\n"
                + "        \"llinatge2\": null,\n"
                + "        \"nif\": \"99999999R\",\n"
                + "        \"email\": null\n"
                + "      },\n"
                + "      \"destinataris\": [],\n"
                + "      \"error\": false,\n"
                + "      \"errorData\": null,\n"
                + "      \"errorDescripcio\": null,\n"
                + "      \"justificant\": null,\n"
                + "      \"certificacio\": \"https://se.caib.es/notibapi/interna/consulta/v2/certificacio/3975700\"\n"
                + "    }\n"
                + "  ],\n"
                + "  \"error\": false,\n"
                + "  \"errorDescripcio\": null,\n"
                + "  \"errorData\": null\n"
                + "}\n"
                + "").build();
    }
    

    
}
