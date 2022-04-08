package es.caib.carpeta.api.externa.mobilenotification;

import java.util.Locale;

import javax.ejb.EJB;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
import org.fundaciobit.genapp.common.query.Where;

import es.caib.carpeta.logic.CiutadaLogicaService;
import es.caib.carpeta.logic.utils.SendNotificationResult;
import es.caib.carpeta.logic.utils.SendNotificationToMobile;
import es.caib.carpeta.model.fields.CiutadaFields;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;

/**
 *
 * @author anadal
 *
 */
@Path("/secure/mobilenotification")
@OpenAPIDefinition(
        tags = @Tag(
                name = "Notificacions",
                description = "Notificacions a l'APP de Carpeta (missateg a Mòbil)")
        )
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "BasicAuth", scheme = "basic")
public class MobileNotification {

    protected Logger log = Logger.getLogger(MobileNotification.class);

    @EJB(mappedName = CiutadaLogicaService.JNDI_NAME)
    protected CiutadaLogicaService ciutadaLogicaEjb;

    @GET
    @Path("/sendmessage")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(tags = {"Notificacions"},
            operationId = "sendMessage",
            summary = "Envia un missatge al mòbil del ciutada a traves de l'App de Carpeta.")
    @SecurityRequirement(name = "BasicAuth")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "Paràmetres incorrectes",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON)),
                    @ApiResponse(
                            responseCode = "401",
                            description = "No Autenticat",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON)),
                    @ApiResponse(
                            responseCode = "403",
                            description = "No Autoritzat",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON)),
                    @ApiResponse(
                            responseCode = "200",
                            description = "Enviat missatge correctament",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = String.class))) })
    public Response sendMessage(
            @Parameter(
                    description = "NIF del Ciutadà o l'entitat",
                    required = true,
                    example = "12345678Z",
                    schema = @Schema(implementation = String.class))
            @NotEmpty
            @Size(min=7, max=20)
            @QueryParam("nif") String nif,

            @Parameter(
                    description = "Títol de la notificació",
                    required = true,
                    example = "Això és el títol",
                    schema = @Schema(implementation = String.class))
            @NotNull
            @QueryParam("title") String title,

            @Parameter(
                    description = "Missatge de la notificació",
                    required = true,
                    example = "Això és el missatge",
                    schema = @Schema(implementation = String.class))
            @NotNull
            @QueryParam("message") String message,

            @Parameter(
                    description = "Codi de l'idioma",
                    required = false,
                    example = "ca",
                    schema = @Schema(implementation = String.class))
            @Pattern(regexp="^ca|es$")
            @QueryParam("lang") String lang) {

        try {
            String mobileID = getMobileIdOfCiutada(nif);

            if (mobileID == null) {
                throw new I18NException("genapp.comodi",
                        "No s'ha trobat el mòbil del ciutada/entitat amb NIF " + nif);
            }

            SendNotificationResult snr;
            snr = SendNotificationToMobile.sendMessageToMobile(mobileID, title, message, null);

            if (snr.isEstatEnviat() && snr.isEstatRebut()) {
                return Response.ok().entity("Ok").build();
            } else {
                throw new I18NException("genapp.comodi", snr.toString());
            }

        } catch (Throwable th) {

            String msg;
            if (th instanceof I18NException) {
                I18NException ie = (I18NException) th;
                msg = I18NCommonUtils.getMessage(ie, new Locale(lang));
            } else {
                msg = th.getMessage();
            }

            log.error("Error cridada api rest enviar notificacions: " + msg, th);

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{ \"error\" : " + "\"" + msg + "\" }").build();

        }

    }


    protected String getMobileIdOfCiutada(String nif) throws I18NException {
        Where w1 = CiutadaFields.NIF.equal(nif);
        Where w2 = CiutadaFields.REPRESENTANTNIF.isNull();

        String mobileID = ciutadaLogicaEjb.executeQueryOne(CiutadaFields.MOBILEID,
                Where.AND(w1, w2));

        if (mobileID == null) {
            Where w3 = CiutadaFields.REPRESENTANTNIF.isNotNull();
            mobileID = ciutadaLogicaEjb.executeQueryOne(CiutadaFields.MOBILEID,
                    Where.AND(w1, w3));
        }
        return mobileID;
    }
    


    @Operation(
            tags = {"Notificacions"},
            operationId = "existCiutada",            
            summary = "Consulta si tenim donat d'alta el mòbil d'un ciutadà/empresa a partir del seu NIF.")
    @SecurityRequirement(name = "BasicAuth")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "404",
                            description = "Paràmetres incorrectes",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON)),
                    @ApiResponse(
                            responseCode = "401",
                            description = "No Autenticat",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON)),
                    @ApiResponse(
                            responseCode = "403",
                            description = "No Autoritzat",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON)),
                    @ApiResponse(
                            responseCode = "200",
                            description = "Consulta finalitzada. Retorna true si existeix el ciutadà/empresa o false en cas contrari.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = Boolean.class))) })
    @GET
    @Path("/existciutada")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response existCiutada(
            @Parameter(
                    description = "NIF del Ciutadà o l'entitat",
                    required = true,
                    example = "12345678Z",
                    schema = @Schema(implementation = String.class))
            @NotEmpty
            @Size(min=7, max=20)
            @QueryParam("nif") String nif,
            @Parameter(
                    description = "Codi de l'idioma",
                    required = false,
                    example = "ca",
                    schema = @Schema(implementation = String.class))
            @Pattern(regexp="^ca|es$")
            @QueryParam("lang") String lang) {

        try {
            
            
            String mobileID = getMobileIdOfCiutada(nif);

            if (mobileID == null) {
              return Response.ok().entity(Boolean.FALSE).build();
            } else {
              return Response.ok().entity(Boolean.TRUE).build();
            }
        } catch (Throwable th) {

            String msg;
            if (th instanceof I18NException) {
                I18NException ie = (I18NException) th;
                msg = I18NCommonUtils.getMessage(ie, new Locale(lang));
            } else {
                msg = th.getMessage();
            }

            log.error("Error cridada api rest consulta de ciutadà/empresa: " + msg, th);

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{ \"error\" : " + "\"" + msg + "\" }").build();

        }

    }
    
        

}
