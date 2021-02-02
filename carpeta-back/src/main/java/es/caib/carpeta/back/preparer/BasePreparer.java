package es.caib.carpeta.back.preparer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.security.RunAs;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.PreparerException;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.context.i18n.LocaleContextHolder;

import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import es.caib.carpeta.back.security.LoginInfo;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.ejb.IdiomaService;
import es.caib.carpeta.persistence.AvisJPA;
import es.caib.carpeta.logic.AvisLogicaService;
import es.caib.carpeta.logic.utils.EjbManager;
import es.caib.carpeta.model.fields.IdiomaFields;

/**
 * @author anadal
 *
 */
@RunAs("CAR_ADMIN")
@Component
public class BasePreparer implements ViewPreparer, Constants {

	public static Map<String, I18NTranslation> loginErrorMessage = new HashMap<String, I18NTranslation>();

	protected final Logger log = Logger.getLogger(getClass());


	@Override
	public void execute(Request tilesRequest, AttributeContext attributeContext) throws PreparerException {

		Map<String, Object> request = tilesRequest.getContext("request");

		// Informació de Login
		LoginInfo loginInfo = LoginInfo.getInstance();
		// Posa dins la sessió la informació de Login
		request.put("loginInfo", loginInfo);

		// URL
		// TODO ficarho dins cache (veure Capperpare.java)
		HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		httpRequest.getSession().setAttribute("loginInfo", loginInfo);
		{

			if (httpRequest.getSession().getAttribute("isMobile") == null) {

				Device currentDevice = DeviceUtils.getRequiredCurrentDevice(httpRequest);
				if (currentDevice.isMobile()) {

					log.debug("IS MOBILE = true");

					httpRequest.getSession().setAttribute("isMobile", true);
					request.put("isMobile", true);
				} else {
					log.debug("IS MOBILE = false");
				}
			}

			// Error de Login
			
			if (loginInfo != null && loginInfo.getUsuariPersona() != null) {
			
				final String username = loginInfo.getUsuariPersona().getUsername();
	
				I18NTranslation trans = loginErrorMessage.get(username);
				if (trans == null) {
					String msg = (String) httpRequest.getSession().getAttribute("loginerror");
					if (msg != null) {
						HtmlUtils.saveMessageError(httpRequest, msg);
					}
				} else {
					loginErrorMessage.remove(username);
					String msg = I18NUtils.tradueix(trans);
					HtmlUtils.saveMessageError(httpRequest, msg);
					httpRequest.getSession().setAttribute("loginerror", msg);
				}
			}

			request.put("urlActual", httpRequest.getServletPath());

			// Compatibilitat IE8
			String userAgent = httpRequest.getHeader("User-Agent");
			if (userAgent != null) {
				int index = userAgent.toUpperCase().indexOf("MSIE");
				if (index != -1) {
					try {
						String ieversion = userAgent.substring(index + 4, userAgent.indexOf(";", index + 4));
						if (Float.parseFloat(ieversion) < 9.0f) {
							request.put("IE8", true);
						}
					} catch (Throwable e) {
						log.debug(e);
					}
				}
			}
		}

		// Language
		Locale loc = LocaleContextHolder.getLocale();
		request.put("lang", loc.toString()); // LANG i si es necessari el country
		request.put("onlylang", loc.getLanguage()); // només el LANG
		try {
		    IdiomaService idiomaEjb =EjbManager.getIdiomaEJB();
		   request.put("languages", idiomaEjb.select(IdiomaFields.SUPORTAT.equal(true), new OrderBy(IdiomaFields.ORDRE)));
        } catch (I18NException e) {
            throw new PreparerException("Error consultant idiomes disponibles: " + I18NUtils.getMessage(e), e);
        }

		// Pipella
		request.put("pipella", attributeContext.getAttribute("pipella"));
        Attribute a = attributeContext.getAttribute("pipella");
        //log.info("\n\n PIPELLLA A: " + a);
        if (a != null) {
            String pipella = (String)a.getValue();
            //log.info("\n\n PIPELLLA P: " + pipella);
        }

		// TODO GENAPP
		// Warning for each ROLE

		// Avisos Back
		List<AvisJPA> avisosList = new ArrayList<AvisJPA>();
		try {
		    AvisLogicaService avisLogicaEjb = EjbManager.getAvisLogicaEJB();
			if("superadmin".equals((String)attributeContext.getAttribute("pipella").getValue())) {
				avisosList = avisLogicaEjb.findAllActive();
			} else if("adminentitat".equals((String)attributeContext.getAttribute("pipella").getValue())) { 
				avisosList = avisLogicaEjb.findActiveByEntidadID(LoginInfo.getInstance().getEntitatID());
			} 
			httpRequest.getSession().setAttribute("numAvisos", avisosList.size());
			
		} catch(Exception e) {
			log.error(e.getMessage());
		}catch (Throwable e) {
			log.error(e.getMessage());
		}
		
		// Avisos
		Map<String, Long> avisos = new HashMap<String, Long>();
		// avisos.put(rol, <<Number of warnings>>);
		request.put("avisos", avisos);

		if (attributeContext.getAttribute("menu") != null) {
			request.put("menu_tile", attributeContext.getAttribute("menu").toString());
		}

		// attributeContext.putAttribute("menu", new
		// Attribute("/WEB-INF/jsp/moduls/menu_inici.jsp"));
		
		

		request.put("contingut_tile", attributeContext.getAttribute("contingut").toString());

	}

}
