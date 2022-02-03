package es.caib.carpeta.back.controller.adminentitat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.ITableManager;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.carpeta.back.controller.webdb.SeccioController;
import es.caib.carpeta.back.form.webdb.SeccioFilterForm;
import es.caib.carpeta.back.form.webdb.SeccioForm;
import es.caib.carpeta.back.security.LoginInfo;
import es.caib.carpeta.back.utils.OrdenacioPerTraduible;
import es.caib.carpeta.logic.SeccioLogicaService;
import es.caib.carpeta.model.entity.Seccio;
import es.caib.carpeta.model.fields.SeccioFields;
import es.caib.carpeta.persistence.SeccioJPA;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/adminentitat/seccio")
@SessionAttributes(types = { SeccioForm.class, SeccioFilterForm.class })
public class SeccioAdminEntitatController extends SeccioController {

    @EJB(mappedName = SeccioLogicaService.JNDI_NAME)
    protected SeccioLogicaService seccioLogicaEjb;

    @Override
    public String getTileForm() {
        return "seccioFormAdminEntitat";
    }

    @Override
    public String getTileList() {
        return "seccioListAdminEntitat";
    }

    @Override

    public String getSessionAttributeFilterForm() {
        return "SeccioAdminEntitat_FilterForm";
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        return SeccioFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID());
    }

    @Override
    public SeccioForm getSeccioForm(SeccioJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        SeccioForm seccioForm = super.getSeccioForm(_jpa, __isView, request, mav);

        seccioForm.addHiddenField(ENTITATID);
        seccioForm.addHiddenField(SECCIOPAREID);

        if (seccioForm.isNou()) {
            seccioForm.getSeccio().setEntitatID(LoginInfo.getInstance().getEntitatID());
            seccioForm.getSeccio().setActiva(true);
            seccioForm.getSeccio().setSeccioPareID(null);
        }

        return seccioForm;
    }

    @Override
    public SeccioJPA create(HttpServletRequest request, SeccioJPA seccio)
            throws Exception, I18NException, I18NValidationException {

        log.info(" \n\n Creant Secció amb context ]" + seccio.getContexte() + "[ \n\n");

        return (SeccioJPA) seccioLogicaEjb.create(seccio);
    }

    @Override
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String crearSeccioPost(@ModelAttribute SeccioForm seccioForm,
        BindingResult result, HttpServletRequest request,
        HttpServletResponse response) throws Exception {


        //  Error creant una secció a dins Administrador d'Entitat. #623 
        // No sabem perque però SEMPE seccioForm.getSeccio().getContexte() retorna null        
        log.info("\n XYXXXXXX   POST Contexte => ]" + seccioForm.getSeccio().getContexte() + "[\n");
        
        // PARXE: llegir de request.getParameter("seccio.contexte")
        seccioForm.getSeccio().setContexte(request.getParameter(SeccioFields._TABLE_MODEL +"." + SeccioFields.CONTEXTE.javaName));
        
        return super.crearSeccioPost(seccioForm, result, request, response);
        
    }

    @Override
    public SeccioFilterForm getSeccioFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        SeccioFilterForm seccioFilterForm = super.getSeccioFilterForm(pagina, mav, request);
        if (seccioFilterForm.isNou()) {
            seccioFilterForm.addHiddenField(ENTITATID);
            seccioFilterForm.addHiddenField(ICONAID);
            seccioFilterForm.addHiddenField(SECCIOPAREID);
            seccioFilterForm.addHiddenField(DESCRIPCIOID);

            seccioFilterForm.setDefaultOrderBy(new OrderBy[] { new OrderBy(NOMID) });

            seccioFilterForm.setAllItemsPerPage(new int[] { -1 });
            seccioFilterForm.setItemsPerPage(-1);
        }

        return seccioFilterForm;
    }

    @Override
    public void delete(HttpServletRequest request, Seccio seccio) throws Exception, I18NException {
        // Ho esborra tot.
        seccioLogicaEjb.deleteFull(seccio);
    }

    @Override
    public List<Seccio> executeSelect(ITableManager<Seccio, Long> ejb, Where where,
            final OrderBy[] orderBy, final Integer itemsPerPage, final int inici)
            throws I18NException {

        List<Seccio> list = super.executeSelect(seccioEjb, where, orderBy, itemsPerPage, inici);

        Field<?> field = SeccioFields.NOMID;

        Map<Long, String> nomPerID = new HashMap<Long, String>();

        String lang = LocaleContextHolder.getLocale().getLanguage();

        for (Seccio seccio : list) {
            nomPerID.put(seccio.getSeccioID(),
                    ((SeccioJPA) seccio).getNom().getTraduccio(lang).getValor());
        }

        new OrdenacioPerTraduible<Seccio, Long>() {
            @Override
            public Long getPK(Seccio o1) {
                return o1.getSeccioID();
            }
        }.ordenar(list, field, nomPerID, orderBy);

        return list;

    }
}
