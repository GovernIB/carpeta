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

import es.caib.carpeta.back.controller.webdb.AvisController;
import es.caib.carpeta.back.form.webdb.AvisFilterForm;
import es.caib.carpeta.back.form.webdb.AvisForm;
import es.caib.carpeta.back.security.LoginInfo;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.jpa.AvisJPA;
import es.caib.carpeta.model.fields.AvisFields;

/* 
 *  @author jagarcia
 */

@Controller
@RequestMapping(value = "/superadmin/avis")
@SessionAttributes(types = { AvisForm.class, AvisFilterForm.class })
public class AvisSuperAdminController extends AvisController {

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
		
		return isSuperAdmin() ? AvisFields.ENTITATID.isNotNull() 
				: AvisFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID());
	}

	@Override
	public AvisFilterForm getAvisFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
			throws I18NException {
		AvisFilterForm avisFilterForm = super.getAvisFilterForm(pagina, mav, request);
		
		if(!isSuperAdmin())
			avisFilterForm.addHiddenField(ENTITATID);
		
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
	
	protected boolean isSuperAdmin() {
        return true;
    }
	
	protected String getName() {
        return isSuperAdmin() ? "SuperAdmin" : "AdminEntitat";
    }
	
}