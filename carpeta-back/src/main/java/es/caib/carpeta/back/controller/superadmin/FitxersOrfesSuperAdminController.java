package es.caib.carpeta.back.controller.superadmin;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.carpeta.back.controller.webdb.FitxerController;
import es.caib.carpeta.back.form.webdb.FitxerFilterForm;
import es.caib.carpeta.back.form.webdb.FitxerForm;
import es.caib.carpeta.model.entity.Fitxer;
import es.caib.carpeta.model.fields.EnllazFields;
import es.caib.carpeta.model.fields.EntitatFields;
import es.caib.carpeta.model.fields.PluginFields;

/**
 * 
 * @author jagarcia
 *
 */
@Controller
@RequestMapping(value = "/superadmin/fitxerorfes")
@SessionAttributes(types = { FitxerForm.class, FitxerFilterForm.class })
public class FitxersOrfesSuperAdminController extends FitxerController{
	
	
	  @EJB(mappedName = es.caib.carpeta.ejb.EnllazService.JNDI_NAME)
	  protected es.caib.carpeta.ejb.EnllazService enllazEjb;
	  
	  @EJB(mappedName = es.caib.carpeta.ejb.EntitatService.JNDI_NAME)
	  protected es.caib.carpeta.ejb.EntitatService entitatEjb;
	  
	  @EJB(mappedName = es.caib.carpeta.ejb.PluginService.JNDI_NAME)
	  protected es.caib.carpeta.ejb.PluginService pluginEjb;
	  
	  @Override
	  public String getTileForm() {
	    return "fitxerOrfesFormSuperAdmin";
	  }
	
	  @Override
	  public String getTileList() {
	    return "fitxerOrfesListSuperAdmin";
	  }
	  
	  @Override
	  public String getSessionAttributeFilterForm() {
	    return "FitxerOrfesSuperAdmin_FilterForm";
	  }
	  
	  @Override
	  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
	    Where parent = super.getAdditionalCondition(request);
	    
	    Where w1 = FITXERID.notIn(enllazEjb.getSubQuery(EnllazFields.LOGOID, null));
	    Where w2 = FITXERID.notIn(entitatEjb.getSubQuery(EntitatFields.ICONID, null));
	    Where w3 = FITXERID.notIn(entitatEjb.getSubQuery(EntitatFields.LOGOCAPBACKID, null));
	    Where w4 = FITXERID.notIn(entitatEjb.getSubQuery(EntitatFields.LOGOPEUBACKID, null));
	    Where w5 = FITXERID.notIn(entitatEjb.getSubQuery(EntitatFields.LOGOLATERALFRONTID, null));
	    Where w6 = FITXERID.notIn(pluginEjb.getSubQuery(PluginFields.LOGOID, null));
	    
	    return Where.AND(parent, w1, w2, w3, w4, w5, w6);
	  }
	  
	  
	  @Override
	  public boolean isActiveFormNew() {
	    return false;
	  }

	  @Override
	  public boolean isActiveFormEdit() {
	    return false;
	  }

	  @Override
	  public boolean isActiveDelete() {
	    return true;
	  }

	  @Override
	  public boolean isActiveFormView() {
	    return true;
	  }
	  
	  
	  
	  @Override
	  public FitxerFilterForm getFitxerFilterForm(Integer pagina, ModelAndView mav,
	      HttpServletRequest request) throws I18NException {
	      FitxerFilterForm fitxerFilterForm;
	      fitxerFilterForm = (FitxerFilterForm)super.getFitxerFilterForm(pagina, mav, request);
	      
	      if (fitxerFilterForm.isNou()) {
	        fitxerFilterForm.setTitleCode("fitxers.orfes");
	        fitxerFilterForm.setAddButtonVisible(false);
	        fitxerFilterForm.setEditButtonVisible(false);
	      }
	      
	      return fitxerFilterForm;
	  }
	  
	  
	  
	  @Override
	  public void postList(HttpServletRequest request, ModelAndView mav, FitxerFilterForm filterForm,
	      List<Fitxer> list) throws I18NException {
		  
		  
		  Map<Long, File> fitxersFisics = FileSystemManager.getAllFiles();
		  
		  List<Long> fitxersBBDD = fitxerEjb.executeQuery(FITXERID, new OrderBy(FITXERID));
		  
		  // Fitxers que existeixen a BBDD pero no fisicament
		  for (Long fID : fitxersBBDD) {
		      if (!fitxersFisics.containsKey(fID)) {
		        // Fitxer amb ID={0} existeix en BBDD pero no existeix fisicament !!!
		        HtmlUtils.saveMessageError(request, I18NUtils.tradueix("error.fitxer.noexisteix", String.valueOf(fID)));
		      }
		  }
		  
		  // Fitxers que existeixen fisicament pero no en BBDD
		  for (Long fBDID : fitxersBBDD) {
		      if (fitxersFisics.containsKey(fBDID)) {
		          fitxersFisics.remove(fBDID); 
		      }
		  }
		  for (Map.Entry<Long, File> fFisic : fitxersFisics.entrySet()) {
		      HtmlUtils.saveMessageError(request, "Fitxer Fisic amb id ]"
		          + fFisic.getKey() + "[ (" + fFisic.getValue().getAbsolutePath() +") no existeix en la BBDD !!!" );  
		  }
	   
	  }

}
