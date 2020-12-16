package es.caib.carpeta.back.controller.superadmin;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import es.caib.carpeta.back.form.webdb.AccesFilterForm;
import es.caib.carpeta.back.form.webdb.AccesForm;

import es.caib.carpeta.back.controller.webdb.AccesController;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.model.fields.AccesFields;
import es.caib.carpeta.model.fields.EntitatFields;
import java.util.List;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 16/12/2020
 */

@Controller
@RequestMapping(value = "/superadmin/acces")
@SessionAttributes(types = { AccesForm.class, AccesFilterForm.class })
public class AccesSuperAdminController extends AccesController {

    @Override
    public String getTileForm() { return "accesFormSuperAdmin"; }

    @Override
    public String getTileList() {
        return "accesListSuperAdmin";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "AccesSuperAdmin_FilterForm";
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        return null;
    }

    @Override
    public AccesFilterForm getAccesFilterForm(Integer pagina, ModelAndView mav,
                                              HttpServletRequest request) throws I18NException {
        AccesFilterForm accesFilterForm = super.getAccesFilterForm(pagina,mav,request);

        if (accesFilterForm.isNou()) {
            accesFilterForm.addHiddenField(ACCESID);
            accesFilterForm.setAddButtonVisible(false);
            accesFilterForm.setDeleteSelectedButtonVisible(false);
            accesFilterForm.setDeleteButtonVisible(false);
            accesFilterForm.setEditButtonVisible(false);


            OrderBy[] orderByDef = {new OrderBy(AccesFields.ENTITATID.javaName),new OrderBy(AccesFields.TIPUS.javaName),new OrderBy(AccesFields.DATADARRERACCES.javaName, OrderType.DESC)};
            accesFilterForm.setDefaultOrderBy(orderByDef);

        }

        return accesFilterForm;
    }

    /*@Override
    public AccesForm getAccesForm(AccesJPA _jpa,
                                  boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
        AccesForm accesForm = super.getAccesForm(_jpa,__isView,request,mav);

        //accesForm.setAllFieldsReadOnly(AccesFields.ALL_ACCES_FIELDS);
       // accesForm.setSaveButtonVisible(false);
       // accesForm.setDeleteButtonVisible(false);

        return accesForm;
    }*/

    @Override
    public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
                                                             ModelAndView mav, Where where)  throws I18NException {
        return entitatRefList.getReferenceList(EntitatFields.ENTITATID, where );
    }

    @Override
    public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
                                                         ModelAndView mav, Where where)  throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        for (int i = 0; i < Constants.TIPUS_ACCES_ALL.length; i++) {
            int v = Constants.TIPUS_ACCES_ALL[i];
            __tmp.add(new StringKeyValue("" + v, I18NUtils.tradueix("acces.tipus." + v)));
        }
        return __tmp;
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
        return false;
    }

    @Override
    public boolean isActiveFormView() {
        return false;
    }

}
