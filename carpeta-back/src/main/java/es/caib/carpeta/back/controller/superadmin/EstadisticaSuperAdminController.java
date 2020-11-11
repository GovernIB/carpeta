package es.caib.carpeta.back.controller.superadmin;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import es.caib.carpeta.back.form.webdb.*;

import es.caib.carpeta.back.controller.webdb.EstadisticaController;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.model.fields.*;

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


   // References
   @Autowired
   protected PluginRefList pluginRefList;

   @Override
   public String getTileForm() {
      return "estadisticaFormSuperAdmin";
   }

   @Override
   public String getTileList() {
      return "estadisticaListSuperAdmin";
   }

   @Override
   public String getSessionAttributeFilterForm() {
      return "EstadisticaSuperAdmin_FilterForm";
   }


   @Override
   public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
      return null;
   }


   @Override
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


   @Override
   public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
                                                        ModelAndView mav, Where where)  throws I18NException {
      List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();



      for (int i = 0; i < Constants.TIPUS_ESTADISTICA_ALL.length; i++) {
         int v = Constants.TIPUS_ESTADISTICA_ALL[i];
         __tmp.add(new StringKeyValue("" + v, I18NUtils.tradueix("estadistica.tipus." + v)));
      }

      return __tmp;
   }




   @Override
   public List<StringKeyValue> getReferenceListForPluginID(HttpServletRequest request,
                                                           ModelAndView mav, Where where)  throws I18NException {
      return pluginRefList.getReferenceList(PluginFields.PLUGINID, where );
   }











}
