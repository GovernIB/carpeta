package es.caib.carpeta.back.controller.superadmin;


import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import es.caib.carpeta.back.form.webdb.*;

import es.caib.carpeta.back.controller.webdb.PropietatGlobalController;
import es.caib.carpeta.back.security.LoginInfo;
import es.caib.carpeta.persistence.PropietatGlobalJPA;
import es.caib.carpeta.logic.AuditoriaLogicaService;
import es.caib.carpeta.model.entity.PropietatGlobal;
import es.caib.carpeta.model.fields.*;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/superadmin/propietatglobal")
@SessionAttributes(types = { PropietatGlobalForm.class, PropietatGlobalFilterForm.class })
public class PropietatGlobalSuperAdminController extends PropietatGlobalController {

    @EJB(mappedName = AuditoriaLogicaService.JNDI_NAME)
    protected AuditoriaLogicaService auditoriaLogicaEjb;

    @Override
    public String getTileForm() {
        return "propietatGlobalForm" + getName();
    }

    @Override
    public String getTileList() {
        return "propietatGlobalList" + getName();
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "PropietatGlobal" + getName() + "_FilterForm";
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        return isSuperAdmin() ? null 
                : PropietatGlobalFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID());
    }

    protected boolean isSuperAdmin() {
        return true;
    }

    protected String getName() {
        return isSuperAdmin() ? "SuperAdmin" : "AdminEntitat";
    }

    @Override
    public PropietatGlobalForm getPropietatGlobalForm(PropietatGlobalJPA _jpa, boolean __isView,
            HttpServletRequest request, ModelAndView mav) throws I18NException {

        PropietatGlobalForm propietatGlobalForm = super.getPropietatGlobalForm(_jpa, __isView, request, mav);

        if (propietatGlobalForm.isNou()) {
            if (isSuperAdmin()) {
                propietatGlobalForm.getPropietatGlobal().setEntitatID(null);
                //propietatGlobalForm.addHiddenField(ENTITATID);
            } else {
                propietatGlobalForm.getPropietatGlobal().setEntitatID(LoginInfo.getInstance().getEntitatID());
                propietatGlobalForm.addReadOnlyField(ENTITATID);
            }
        }

        return propietatGlobalForm;
    }

    @Override
    public PropietatGlobalFilterForm getPropietatGlobalFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        PropietatGlobalFilterForm propietatGlobalFilterForm = super.getPropietatGlobalFilterForm(pagina, mav, request);

        if (propietatGlobalFilterForm.isNou() && !isSuperAdmin()) {
            propietatGlobalFilterForm.addHiddenField(ENTITATID);
        }

        return propietatGlobalFilterForm;
    }
    
    @Override
    public String getEntityNameCode() {
        return "propietatGlobal.superadmin.titol";
      }

    @Override
    public String getEntityNameCodePlural() {
        return "propietatGlobal.superadmin.titol.plural";
    }


    @Override
    public PropietatGlobalJPA create(HttpServletRequest request, PropietatGlobalJPA propietatGlobal) throws Exception, I18NException, I18NValidationException {
        PropietatGlobalJPA propietatGlobalJPA = super.create(request, propietatGlobal);

        try{
            if(isSuperAdmin()){
                LoginInfo loginInfo = LoginInfo.getInstance();
                auditoriaLogicaEjb.crearAuditoria(TIPUS_AUDIT_AFEGIR_PROPGLOB,null,loginInfo.getUsuariPersona().getUsername(), propietatGlobalJPA.getCodi());
            }
        }catch(I18NException e){

            String msg = "Error creant auditoria "+ "("+  e.getMessage() + ")";
            log.error(msg, e);
        }

        return propietatGlobalJPA;

    }


    @Override
    public void delete(HttpServletRequest request, PropietatGlobal propietatGlobal) throws Exception,I18NException {
        String codi = propietatGlobal.getCodi();
        super.delete(request,propietatGlobal);

        try{
            if(isSuperAdmin()){
                LoginInfo loginInfo = LoginInfo.getInstance();
                auditoriaLogicaEjb.crearAuditoria(TIPUS_AUDIT_ELIMINAT_PROPGLOB,null,loginInfo.getUsuariPersona().getUsername(), codi);
            }
        }catch(I18NException e){

            String msg = "Error creant auditoria "+ "("+  e.getMessage() + ")";
            log.error(msg, e);
        }

    }


}
