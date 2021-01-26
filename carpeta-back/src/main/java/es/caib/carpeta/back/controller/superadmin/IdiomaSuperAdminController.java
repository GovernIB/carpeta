package es.caib.carpeta.back.controller.superadmin;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.carpeta.back.controller.webdb.IdiomaController;
import es.caib.carpeta.back.form.webdb.IdiomaFilterForm;
import es.caib.carpeta.back.form.webdb.IdiomaForm;
import es.caib.carpeta.jpa.IdiomaJPA;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/superadmin/idioma")
@SessionAttributes(types = { IdiomaForm.class, IdiomaFilterForm.class })
public class IdiomaSuperAdminController extends IdiomaController {

    
    

    @Override
    public String getTileForm() {
      return "idiomaFormSuperAdmin";
    }

    @Override
    public String getTileList() {
      return "idiomaListSuperAdmin";
    }

    @Override
    public String getSessionAttributeFilterForm() {
      return "IdiomaSuperAdmin_FilterForm";
    }
    
    
    public IdiomaFilterForm getIdiomaFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
            IdiomaFilterForm idiomaFilterForm = super.getIdiomaFilterForm(pagina, mav, request);
            
            if(idiomaFilterForm.isNou()) {
              idiomaFilterForm.setDeleteButtonVisible(false);
              idiomaFilterForm.setDeleteSelectedButtonVisible(false);
              idiomaFilterForm.setVisibleMultipleSelection(false);
            }
            return idiomaFilterForm;
          }

    
    public IdiomaForm getIdiomaForm(IdiomaJPA _jpa,
            boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
         IdiomaForm idiomaForm = super.getIdiomaForm(_jpa, __isView, request, mav);
         
         if (!idiomaForm.isNou()) {
             String idiomaID = idiomaForm.getIdioma().getIdiomaID(); 
             if ("es".equals(idiomaID) || "ca".equals(idiomaID) ) {
                 idiomaForm.addReadOnlyField(SUPORTAT);
                 idiomaForm.setDeleteButtonVisible(false);
             }
             idiomaForm.addReadOnlyField(IDIOMAID);
         }

         return idiomaForm;
       }

}
