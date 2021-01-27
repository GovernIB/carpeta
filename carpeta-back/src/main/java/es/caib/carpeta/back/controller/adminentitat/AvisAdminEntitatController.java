package es.caib.carpeta.back.controller.adminentitat;

import es.caib.carpeta.back.controller.superadmin.AvisSuperAdminController;
import es.caib.carpeta.back.form.webdb.AvisFilterForm;
import es.caib.carpeta.back.form.webdb.AvisForm;
import es.caib.carpeta.back.security.LoginInfo;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.model.fields.PluginFields;
import es.caib.carpeta.model.fields.PluginEntitatFields;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;


/* 
 *  @author jagarcia
 */

@Controller
@RequestMapping(value = "/adminentitat/avis")
@SessionAttributes(types = { AvisForm.class, AvisFilterForm.class })
public class AvisAdminEntitatController extends AvisSuperAdminController {

    protected boolean isSuperAdmin() {
        return false;
    }
    
    @EJB(mappedName = es.caib.carpeta.ejb.PluginEntitatService.JNDI_NAME)
    protected es.caib.carpeta.ejb.PluginEntitatService pluginEntitatEjb;
    
    
    @Override
	public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
		       ModelAndView mav, Where where)  throws I18NException {

		List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
	    for (int i = 0; i < Constants.TIPUS_AVIS_ALL.length; i++) {
            int v = Constants.TIPUS_AVIS_ALL[i];
            /* El administrador d'entitat no pot crear avisos de tipus back general */
            if (v != Constants.TIPUS_AVIS_BACK) {
            	__tmp.add(new StringKeyValue("" + v, I18NUtils.tradueix("avis.tipus." + v)));
            }
        }
	    return __tmp;
	}
    
    @Override
	public List<StringKeyValue> getReferenceListForPluginFrontID(HttpServletRequest request,
		       ModelAndView mav, Where where)  throws I18NException {
    	
    	List<Long> subQuery = pluginEntitatEjb.executeQuery( 
    				PluginEntitatFields.PLUGINID, 
    				PluginEntitatFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID())
    		); 
    		
		where = Where.AND(
    					where, 
    					PluginFields.PLUGINID.in(subQuery)
    				); 
    		
	    return pluginRefList.getReferenceList(PluginFields.PLUGINID, where );
    }
    
    
    @Override
	public void postValidate(HttpServletRequest request, AvisForm avisForm,
			BindingResult result) throws I18NException {

		super.postValidate(request, avisForm, result);
				
	}
    
}