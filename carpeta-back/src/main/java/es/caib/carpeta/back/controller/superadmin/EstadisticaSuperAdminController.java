package es.caib.carpeta.back.controller.superadmin;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.GroupByItem;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;



import es.caib.carpeta.back.controller.webdb.EstadisticaController;
import es.caib.carpeta.back.form.webdb.EntitatRefList;
import es.caib.carpeta.back.form.webdb.EstadisticaFilterForm;
import es.caib.carpeta.back.form.webdb.EstadisticaForm;
import es.caib.carpeta.back.form.webdb.PluginRefList;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.model.entity.Estadistica;
import es.caib.carpeta.model.fields.EntitatFields;
import es.caib.carpeta.model.fields.EstadisticaFields;
import es.caib.carpeta.model.fields.PluginFields;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * 
 * Date: 29/10/2020
 */
@Controller
@RequestMapping(value = "/superadmin/estadistica")
@SessionAttributes(types = { EstadisticaForm.class, EstadisticaFilterForm.class })
public class EstadisticaSuperAdminController  extends EstadisticaController {


   // References
   @Autowired
   protected PluginRefList pluginRefList;
   
   @Autowired
   protected EntitatRefList entitatRefList;

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

         OrderBy[] orderByDef = {new OrderBy(EstadisticaFields.DATAESTADISTICA.javaName, OrderType.DESC)};
         estadisticaFilterForm.setDefaultOrderBy(orderByDef);

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

   @Override
   public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
		   ModelAndView mav, EstadisticaForm estadisticaForm, Where where)  throws I18NException {
	   
	  List<StringKeyValue> entitats = entitatRefList.getReferenceList(EntitatFields.ENTITATID, where);
	   
	  List<String> entitatIdKeys = new ArrayList<String>();
	  for(StringKeyValue skv : entitats) {
		  entitatIdKeys.add(skv.getKey());
	  }
	  if(!entitatIdKeys.contains(estadisticaForm.getEstadistica().getEntitatID().toString())) {
		  entitats.add(new StringKeyValue(estadisticaForm.getEstadistica().getEntitatID().toString(), I18NUtils.tradueix("entitat.esborrada")));
	  }
	  
	  return entitats;
   }

   @Override
   public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
		   ModelAndView mav, EstadisticaFilterForm estadisticaFilterForm,
		   List<Estadistica> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
	   
	   List<StringKeyValue> entitats = entitatRefList.getReferenceList(EntitatFields.ENTITATID, where);
	   
	   List<String> entitatIdKeys = new ArrayList<>();
	   for(StringKeyValue skv : entitats) {
		   entitatIdKeys.add(skv.getKey());
	   }
	   for(Estadistica estadistica : list) {
		   if(estadistica.getEntitatID() != null) {
			   if(!entitatIdKeys.contains(estadistica.getEntitatID().toString())) {
				   entitats.add(new StringKeyValue(estadistica.getEntitatID().toString(), I18NUtils.tradueix("entitat.esborrada")));
			   }
		   }
	   }
	   
	   return entitats;
   }

   @Override
   public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
		   ModelAndView mav, Where where)  throws I18NException {
	   return entitatRefList.getReferenceList(EntitatFields.ENTITATID, where );
   }

}
