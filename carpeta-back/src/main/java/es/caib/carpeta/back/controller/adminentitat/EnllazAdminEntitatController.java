package es.caib.carpeta.back.controller.adminentitat;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.carpeta.back.controller.webdb.EnllazController;
import es.caib.carpeta.back.form.webdb.EnllazFilterForm;
import es.caib.carpeta.back.form.webdb.EnllazForm;
import es.caib.carpeta.back.security.LoginInfo;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.persistence.EnllazJPA;
import es.caib.carpeta.logic.EnllazLogicaService;
import es.caib.carpeta.model.entity.Enllaz;
import es.caib.carpeta.model.fields.EnllazFields;
import es.caib.carpeta.model.fields.SeccioFields;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/adminentitat/enllaz")
@SessionAttributes(types = { EnllazForm.class, EnllazFilterForm.class })
public class EnllazAdminEntitatController extends EnllazController {

    @EJB(mappedName = EnllazLogicaService.JNDI_NAME)
    protected EnllazLogicaService enllazLogicaEjb;

    @Override
    public String getTileForm() {
        return "enllazFormAdminEntitat";
    }

    @Override
    public String getTileList() {
        return "enllazListAdminEntitat";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "EnllazAdminEntitat_FilterForm";
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        return EnllazFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID());
    }

    @Override
    public EnllazFilterForm getEnllazFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        EnllazFilterForm enllazFilterForm = super.getEnllazFilterForm(pagina, mav, request);

        if (enllazFilterForm.isNou()) {
            enllazFilterForm.addHiddenField(ENTITATID);
        }

        return enllazFilterForm;
    }

    @Override
    public EnllazForm getEnllazForm(EnllazJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        EnllazForm enllazForm = super.getEnllazForm(_jpa, __isView, request, mav);

        if (enllazForm.isNou()) {
            enllazForm.getEnllaz().setEntitatID(LoginInfo.getInstance().getEntitatID());
        }

        enllazForm.addHiddenField(ENTITATID);

        return enllazForm;
    }

    @Override
    public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        for (int i = 0; i < Constants.TIPUS_ENLLAZ_ALL.length; i++) {
            int v = Constants.TIPUS_ENLLAZ_ALL[i];
            __tmp.add(new StringKeyValue("" + v, I18NUtils.tradueix("enllaz.tipus." + v)));
        }

        return __tmp;
    }

    @Override
    public void delete(HttpServletRequest request, Enllaz enllaz) throws Exception, I18NException {
        enllazLogicaEjb.deleteFull(enllaz, true);
    }

    @Override
    public List<StringKeyValue> getReferenceListForSeccioID(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        Where w = Where.AND(where, SeccioFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID()));
        List<StringKeyValue> list = super.getReferenceListForSeccioID(request, mav, w);
        
        list.add(new StringKeyValue("", I18NUtils.tradueix("seccio.arrel")));
        
        return list;
    }

}
