package es.caib.carpeta.back.controller.superadmin;

import es.caib.carpeta.back.controller.webdb.EstadisticaController;
import es.caib.carpeta.back.form.webdb.EstadisticaFilterForm;
import es.caib.carpeta.back.form.webdb.EstadisticaForm;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.jpa.EstadisticaJPA;
import es.caib.carpeta.model.fields.EstadisticaFields;
import es.caib.carpeta.model.fields.LogCarpetaFields;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 29/10/2020
 */
@Controller
@RequestMapping(value = "/superadmin/estadistica")
@SessionAttributes(types = { EstadisticaForm.class, EstadisticaFilterForm.class })
public class EstadisticaSuperAdminController  extends EstadisticaController {

   public String getTileForm() {
      return "estadisticaFormSuperAdmin";
   }

   public String getTileList() {
      return "estadisticaListSuperAdmin";
   }

   public String getSessionAttributeFilterForm() {
      return "EstadisticaSuperAdmin_FilterForm";
   }



   public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
      return null;
   }


   public EstadisticaFilterForm getEstadisticaFilterForm(Integer pagina, ModelAndView mav,
                                                         HttpServletRequest request) throws I18NException {
      EstadisticaFilterForm estadisticaFilterForm = super.getEstadisticaFilterForm(pagina, mav, request);

      if(estadisticaFilterForm.isNou()) {
         estadisticaFilterForm.addHiddenField(ESTADISTICAID);
         estadisticaFilterForm.setAddButtonVisible(false);
         estadisticaFilterForm.setDeleteSelectedButtonVisible(false);
         estadisticaFilterForm.setDeleteButtonVisible(false);
         estadisticaFilterForm.setEditButtonVisible(false);
         estadisticaFilterForm.addGroupByField(TIPUS);
         estadisticaFilterForm.addGroupByField(ENTITATID);

         estadisticaFilterForm.setOrderBy(EstadisticaFields.DATAESTADISTICA.javaName);
         estadisticaFilterForm.setOrderAsc(false);
      }

      return estadisticaFilterForm;
   }


   /**
    *
    * @return
    * @throws Exception
    */
   public EstadisticaForm getEstadisticaForm(EstadisticaJPA _jpa,
                                             boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
      EstadisticaForm estadisticaForm = super.getEstadisticaForm(_jpa,__isView,request,mav);



      estadisticaForm.setAllFieldsReadOnly(LogCarpetaFields.ALL_LOGCARPETA_FIELDS);
      estadisticaForm.setSaveButtonVisible(false);
      estadisticaForm.setDeleteButtonVisible(false);

      return estadisticaForm;
   }


   public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
                                                        ModelAndView mav, Where where)  throws I18NException {
      List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();



      for (int i = 0; i < Constants.TIPUS_ESTADISTICA_ALL.length; i++) {
         int v = Constants.TIPUS_ESTADISTICA_ALL[i];
         __tmp.add(new StringKeyValue("" + v, I18NUtils.tradueix("estadistica.tipus." + v)));
      }

      return __tmp;
   }
}
