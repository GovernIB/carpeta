package es.caib.carpeta.back.controller.superadmin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;
import javax.ejb.EJB;

import es.caib.carpeta.back.controller.webdb.AvisController;
import es.caib.carpeta.back.form.webdb.AvisFilterForm;
import es.caib.carpeta.back.form.webdb.AvisForm;
import es.caib.carpeta.back.security.LoginInfo;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.persistence.AvisJPA;
import es.caib.carpeta.model.fields.AvisFields;
import es.caib.carpeta.model.fields.PluginFields;

import es.caib.carpeta.logic.PluginEntitatLogicaService;
import es.caib.carpeta.persistence.PluginEntitatJPA;

/* 
 *  @author jagarcia
 */

@Controller
@RequestMapping(value = "/superadmin/avis")
@SessionAttributes(types = { AvisForm.class, AvisFilterForm.class })
public class AvisSuperAdminController extends AvisController {
	
	@EJB(mappedName = PluginEntitatLogicaService.JNDI_NAME)
	  protected PluginEntitatLogicaService pluginEntitatEjb;

	@Override
	public String getTileForm() {
		return "avisForm" + getName();
	}

	@Override
	public String getTileList() {
		return "avisList" + getName();
	}

	@Override
	public String getSessionAttributeFilterForm() {
		return "Avis"+ getName() + "_FilterForm";
	}

	@Override
	public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
		
		/* AvisFields.ENTITATID.isNotNull() */
		return isSuperAdmin() ? null 
				: AvisFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID());
	}

	@Override
	public AvisFilterForm getAvisFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
			throws I18NException {
		AvisFilterForm avisFilterForm = super.getAvisFilterForm(pagina, mav, request);
		
		if(!isSuperAdmin()) {
			avisFilterForm.addHiddenField(ENTITATID);
		}
		
		avisFilterForm.addHiddenField(AVISID);
		
		return avisFilterForm;
	}

	@Override
	public AvisForm getAvisForm(AvisJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
			throws I18NException {
		AvisForm avisForm = super.getAvisForm(_jpa, __isView, request, mav);
		
		if (avisForm.isNou()) {
		    avisForm.getAvis().setEntitatID(LoginInfo.getInstance().getEntitatID());
		}
		
		if (!isSuperAdmin()) {
			avisForm.addHiddenField(ENTITATID);
		}
		
		avisForm.setAttachedAdditionalJspCode(true);
		avisForm.addHiddenField(AVISID);
		return avisForm;
	}
	
	@Override
	public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
		       ModelAndView mav, Where where)  throws I18NException {

		List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
	    for (int i = 0; i < Constants.TIPUS_AVIS_ALL.length; i++) {
            int v = Constants.TIPUS_AVIS_ALL[i];
            __tmp.add(new StringKeyValue("" + v, I18NUtils.tradueix("avis.tipus." + v)));
        }
	    return __tmp;
	}
	
	@Override
	public List<StringKeyValue> getReferenceListForGravetat(HttpServletRequest request,
		       ModelAndView mav, Where where)  throws I18NException {
	    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
	    for (int i = 0; i < Constants.GRAVETAT_AVIS_ALL.length; i++) {
            int v = Constants.GRAVETAT_AVIS_ALL[i];
            __tmp.add(new StringKeyValue("" + v, I18NUtils.tradueix("avis.gravetat." + v)));
        }
	    return __tmp;
    }
	
	@Override
	public List<StringKeyValue> getReferenceListForPluginFrontID(HttpServletRequest request,
		       ModelAndView mav, Where where)  throws I18NException {
		    return pluginRefList.getReferenceList(PluginFields.PLUGINID, where );
		  }
	
	@Override
	public void postValidate(HttpServletRequest request, AvisForm avisForm,
			BindingResult result) throws I18NException {

		super.postValidate(request, avisForm, result);
		
	    String entitatIdField = (String)result.getFieldValue(get(ENTITATID));
	    String pluginIdField = (String)result.getFieldValue(get(PLUGINFRONTID));
	    
	    Long __entidadid = (entitatIdField != null) ? Long.valueOf(entitatIdField) :  0L;
	    Long __pluginid = (pluginIdField != null) ? Long.valueOf(pluginIdField) : 0L;
	    int __tipus = Integer.parseInt((String)result.getFieldValue(get(TIPUS)));
	    
	    if (__entidadid < 1L && __tipus != Constants.TIPUS_AVIS_BACK) {
	    	//log.error("El camp Entitat es obligatori");
	    	result.rejectValue(
					get(ENTITATID),
					"genapp.validation.required",
					new String[] { I18NUtils
							.tradueix(get(ENTITATID)) }, null
					);
	    }
	    
	    if (__pluginid < 1L && __tipus == Constants.TIPUS_AVIS_FRONT_PLUGIN) {
	    	//log.error("El camp Plugin és obligatori");
	    	result.rejectValue(
					get(PLUGINFRONTID),
					"genapp.validation.required",
					new String[] { I18NUtils
							.tradueix(get(PLUGINFRONTID)) }, null
					);
	    }
	    
	    if (__tipus == Constants.TIPUS_AVIS_BACK) {
	    	if (__entidadid > 0L) {
	    		//log.error("Els camp entitat no és necessari");
	    		result.rejectValue(
						get(ENTITATID),
						"avis.validation.tipusback",
						new String[] { I18NUtils
								.tradueix(get(ENTITATID)) }, null
				);
	    	}
	    	if (__pluginid > 0L) {
	    		//log.error("Els camp plugin no és necessari");
		    	result.rejectValue(
						get(PLUGINFRONTID),
						"avis.validation.tipusback",
						new String[] { I18NUtils
								.tradueix(get(PLUGINFRONTID)) }, null
				);
	    	}
	    	
	    }
	    
	    // Comprobar que el plugin está associat a l'entitat
	    if (__tipus == Constants.TIPUS_AVIS_FRONT_PLUGIN) {
	    	if (__entidadid > 0L && __pluginid > 0L) {
				
				List<PluginEntitatJPA> pluginsEntitat = pluginEntitatEjb.findAllByEntitatId(__entidadid);
				Boolean pertany = false;
				for (PluginEntitatJPA pluginEntitat : pluginsEntitat) {
					pertany = (pluginEntitat.getPluginID() == __pluginid && pluginEntitat.getEntitatID() == __entidadid);
					if (pertany) {
						break;
					}
				}
				if(!pertany) {
					//log.error("El plugin {0} no pertany a la entitat {1}");
					result.rejectValue(
							get(PLUGINFRONTID),
							"avis.validation.pluginentitat",
							new String[] { entitatIdField, pluginIdField }, null
					);
				}		
	    	}
	    }
	}
	
	protected boolean isSuperAdmin() {
        return true;
    }
	
	protected String getName() {
        return isSuperAdmin() ? "SuperAdmin" : "AdminEntitat";
    }
	
}