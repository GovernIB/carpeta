package es.caib.carpeta.back.controller.superadmin;

import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.carpeta.back.controller.webdb.CiutadaController;
import es.caib.carpeta.back.form.webdb.CiutadaFilterForm;
import es.caib.carpeta.back.form.webdb.CiutadaForm;
import es.caib.carpeta.logic.CiutadaLogicaService;
import es.caib.carpeta.logic.utils.SendNotificationToMobile;
import es.caib.carpeta.logic.utils.SendNotificationResult;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/superadmin/ciutada")
@SessionAttributes(types = { CiutadaForm.class, CiutadaFilterForm.class })
public class CiutadaSuperAdminController extends CiutadaController {

    @EJB(mappedName = CiutadaLogicaService.JNDI_NAME)
    protected CiutadaLogicaService ciutadaLogicaEjb;
    
    @Override
    public String getTileForm() {
        return "ciutadaFormSuperAdmin";
    }

    @Override
    public String getTileList() {
        return "ciutadaListSuperAdmin";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "CiutadaSuperAdmin_FilterForm";
    }

    @Override
    public CiutadaFilterForm getCiutadaFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        CiutadaFilterForm ciutadaFilterForm = super.getCiutadaFilterForm(pagina, mav, request);
        if (ciutadaFilterForm.isNou()) {
            ciutadaFilterForm.addHiddenField(CIUTADAID);
            ciutadaFilterForm.addHiddenField(EMPRESA);
            ciutadaFilterForm.addHiddenField(REPRESENTANTLLINATGE1);
            ciutadaFilterForm.addHiddenField(REPRESENTANTLLINATGE2);
            ciutadaFilterForm.addHiddenField(REPRESENTANTNOM);
            ciutadaFilterForm.addHiddenField(MOBILEID);

            ciutadaFilterForm.addAdditionalButtonForEachItem(
                    new AdditionalButton("fas fa-envelope", "sendmessage.title",
                            getContextWeb() + "/sendmessage/{0}", "btn-warning"));

        }
        return ciutadaFilterForm;
    }
    
    
    
    @RequestMapping(value = "/sendmessage/{ciutadaid}", method = RequestMethod.GET)
    public ModelAndView sendMessageGet(HttpServletRequest request,
        HttpServletResponse response,@PathVariable("ciutadaid") Long ciutadaid) throws I18NException {

        ModelAndView mav = new ModelAndView("sendmessageSuperAdmin");

        mav.addObject("ciutadaid", ciutadaid);
        mav.addObject("contexte", getContextWeb());

        return mav;
    }
    
    
    @RequestMapping(value = "/sendmessage/{ciutadaid}", method = RequestMethod.POST)
    public ModelAndView sendMessagePost(HttpServletRequest request,
        HttpServletResponse response,@PathVariable("ciutadaid") Long ciutadaid) throws I18NException {
    
        String titol = request.getParameter("titol");
        String missatge = request.getParameter("missatge");
        
        
        if (titol.trim().length() == 0 || missatge.trim().length() == 0) {
            HtmlUtils.saveMessageError(request, I18NUtils.tradueix("sendmessage.error"));
            ModelAndView mav = new ModelAndView("sendmessageSuperAdmin");
            
            mav.addObject("ciutadaid", ciutadaid);
            mav.addObject("missatge", missatge);
            mav.addObject("titol", titol);
            mav.addObject("contexte", getContextWeb());
            
            return mav;
        }
        
        Map<String, Object> codeToDo = null;

        String mobileId = ciutadaLogicaEjb.executeQueryOne(MOBILEID, CIUTADAID.equal(ciutadaid));
        
        SendNotificationResult result = SendNotificationToMobile.sendMessageToMobile(mobileId, titol, missatge, codeToDo);
        
        if (result.isEstatEnviat() == true && result.isEstatRebut() == true) {
            HtmlUtils.saveMessageSuccess(request, result.toString().replace("\n", "<br>"));    
        } else {
            HtmlUtils.saveMessageSuccess(request, result.toString().replace("\n", "<br>"));
        }

        ModelAndView mav = new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
        return mav;

    }
}
