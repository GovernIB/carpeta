package es.caib.carpeta.back.controller.common;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.carpeta.back.controller.webdb.UsuariController;
import es.caib.carpeta.back.form.webdb.UsuariFilterForm;
import es.caib.carpeta.back.form.webdb.UsuariForm;
import es.caib.carpeta.back.security.LoginInfo;
import es.caib.carpeta.persistence.UsuariJPA;
import es.caib.carpeta.model.entity.UsuariEntitat;
import es.caib.carpeta.model.fields.EntitatFields;
import es.caib.carpeta.model.fields.IdiomaFields;
import es.caib.carpeta.model.fields.UsuariEntitatFields;
import es.caib.carpeta.model.fields.UsuariFields;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/common/usuari")
@SessionAttributes(types = { UsuariForm.class, UsuariFilterForm.class })
public class UsuariPersonaCommonController extends UsuariController {

    @EJB(mappedName = es.caib.carpeta.ejb.UsuariEntitatService.JNDI_NAME)
    protected es.caib.carpeta.ejb.UsuariEntitatService usuariEntitatEjb;

    @Override
    public String getTileForm() {
        return "usuariFormCommon";
    }

    @Override
    public UsuariForm getUsuariForm(UsuariJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        UsuariForm usuariForm;
        usuariForm = super.getUsuariForm(_jpa, __isView, request, mav);

        if (usuariForm.getUsuari().getUsuariID() != LoginInfo.getInstance().getUsuariPersona().getUsuariID()) {
            mav.setStatus(HttpStatus.FORBIDDEN);
        }

        
        Set<Field<?>> readOnlyFields = new HashSet<Field<?>>(Arrays.asList(UsuariFields.ALL_USUARI_FIELDS));
        
        readOnlyFields.remove(IDIOMAID);
        
        /* Quan entra per primera vegada desde keycloak, si el NIF és NUL, permet introduirlo */
        if( _jpa.getNif() == null || _jpa.getNif().length() < 1) {
        	readOnlyFields.remove(NIF);
        }
        
        usuariForm.setReadOnlyFields(readOnlyFields);
        
        usuariForm.setDeleteButtonVisible(false);

        return usuariForm;

    }

    @Override
    public boolean isActiveList() {
        return false;
    }

    @Override
    public boolean isActiveFormNew() {
        return false;
    }

    @Override
    public boolean isActiveFormEdit() {
        return true;
    }

    @Override
    public boolean isActiveDelete() {
        return false;
    }

    @Override
    public List<StringKeyValue> getReferenceListForDarreraEntitat(HttpServletRequest request, ModelAndView mav,
            UsuariForm usuariForm, Where where) throws I18NException {

        SubQuery<UsuariEntitat, Long> subQuery = usuariEntitatEjb.getSubQuery(UsuariEntitatFields.ENTITATID,
                UsuariEntitatFields.USUARIID.equal(LoginInfo.getInstance().getUsuariPersona().getUsuariID()));

        Where w = Where.AND(where, EntitatFields.ENTITATID.in(subQuery));

        return entitatRefList.getReferenceList(EntitatFields.ENTITATID, w);
    }

    @Override
    public String getRedirectWhenModified(HttpServletRequest request, UsuariForm usuariForm, Throwable __e) {
        if (__e == null) {

            LoginInfo.getInstance().setUsuariPersona(usuariForm.getUsuari());

            return "redirect:/";
        } else {
            return getTileForm();
        }
    }
    
    @Override
    public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long usuariID) {
        return "redirect:/";
    }

    @Override
    public List<StringKeyValue> getReferenceListForIdiomaID(HttpServletRequest request,
            ModelAndView mav, Where where)  throws I18NException {
         return super.getReferenceListForIdiomaID(request, mav, Where.AND(where, IdiomaFields.SUPORTAT.equal(true)));
    }
    
    @Override
    public void preValidate(HttpServletRequest request,UsuariForm usuariForm, BindingResult result)  throws I18NException {
    	
    	// Revisar que NIF no sigui NULL
    	String nif =  (String)result.getFieldValue(get(NIF));
    	
    	if(nif == null || nif.length() < 1) {
    		result.rejectValue(
					get(NIF),
					"genapp.validation.required",
					new String[] { I18NUtils
							.tradueix(get(NIF)) }, null
					);
    	}
    	
    	super.preValidate(request, usuariForm, result);
    
    }
    
}
