package es.caib.carpeta.back.controller.webdb;

import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.GroupByItem;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.validation.ValidationWebUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import es.caib.carpeta.back.form.webdb.*;
import es.caib.carpeta.back.form.webdb.CiutadaForm;

import es.caib.carpeta.back.validator.webdb.CiutadaWebValidator;

import es.caib.carpeta.persistence.CiutadaJPA;
import es.caib.carpeta.model.entity.Ciutada;
import es.caib.carpeta.model.fields.*;

/**
 * Controller per gestionar un Ciutada
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/ciutada")
@SessionAttributes(types = { CiutadaForm.class, CiutadaFilterForm.class })
public class CiutadaController
    extends es.caib.carpeta.back.controller.CarpetaBaseController<Ciutada, java.lang.Long> implements CiutadaFields {

  @EJB(mappedName = es.caib.carpeta.ejb.CiutadaService.JNDI_NAME)
  protected es.caib.carpeta.ejb.CiutadaService ciutadaEjb;

  @Autowired
  private CiutadaWebValidator ciutadaWebValidator;

  @Autowired
  protected CiutadaRefList ciutadaRefList;

  /**
   * Llistat de totes Ciutada
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    CiutadaFilterForm ff;
    ff = (CiutadaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Ciutada de forma paginada
   */
  @RequestMapping(value = "/list/{pagina}", method = RequestMethod.GET)
  public ModelAndView llistatPaginat(HttpServletRequest request,
    HttpServletResponse response, @PathVariable Integer pagina)
      throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileList());
    llistat(mav, request, getCiutadaFilterForm(pagina, mav, request));
    return mav;
  }

  public CiutadaFilterForm getCiutadaFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    CiutadaFilterForm ciutadaFilterForm;
    ciutadaFilterForm = (CiutadaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(ciutadaFilterForm == null) {
      ciutadaFilterForm = new CiutadaFilterForm();
      ciutadaFilterForm.setContexte(getContextWeb());
      ciutadaFilterForm.setEntityNameCode(getEntityNameCode());
      ciutadaFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      ciutadaFilterForm.setNou(true);
    } else {
      ciutadaFilterForm.setNou(false);
    }
    ciutadaFilterForm.setPage(pagina == null ? 1 : pagina);
    return ciutadaFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Ciutada de forma paginada
   * 
   * @param request
   * @param pagina
   * @param filterForm
   * @return
   * @throws I18NException
   */
  @RequestMapping(value = "/list/{pagina}", method = RequestMethod.POST)
  public ModelAndView llistatPaginat(HttpServletRequest request,
      HttpServletResponse response,@PathVariable Integer pagina,
      @ModelAttribute CiutadaFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getCiutadaFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Ciutada de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Ciutada> llistat(ModelAndView mav, HttpServletRequest request,
     CiutadaFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Ciutada> ciutada = processarLlistat(ciutadaEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("ciutadaItems", ciutada);

    mav.addObject("ciutadaFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, ciutada, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, ciutada);

    return ciutada;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(CiutadaFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Ciutada> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, EMPRESA);


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    CiutadaFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Ciutada> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_CIUTADA_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Ciutada
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearCiutadaGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    CiutadaForm ciutadaForm = getCiutadaForm(null, false, request, mav);
    mav.addObject("ciutadaForm" ,ciutadaForm);
    fillReferencesForForm(ciutadaForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public CiutadaForm getCiutadaForm(CiutadaJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    CiutadaForm ciutadaForm;
    if(_jpa == null) {
      ciutadaForm = new CiutadaForm(new CiutadaJPA(), true);
    } else {
      ciutadaForm = new CiutadaForm(_jpa, false);
      ciutadaForm.setView(__isView);
    }
    ciutadaForm.setContexte(getContextWeb());
    ciutadaForm.setEntityNameCode(getEntityNameCode());
    ciutadaForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return ciutadaForm;
  }

  public void fillReferencesForForm(CiutadaForm ciutadaForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou Ciutada
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearCiutadaPost(@ModelAttribute CiutadaForm ciutadaForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    CiutadaJPA ciutada = ciutadaForm.getCiutada();

    try {
      preValidate(request, ciutadaForm, result);
      getWebValidator().validate(ciutadaForm, result);
      postValidate(request,ciutadaForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        ciutada = create(request, ciutada);
        createMessageSuccess(request, "success.creation", ciutada.getCiutadaID());
        ciutadaForm.setCiutada(ciutada);
        return getRedirectWhenCreated(request, ciutadaForm);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.creation", null, __e);
      log.error(msg, __e);
      return getTileForm();
    }
  }

  @RequestMapping(value = "/view/{ciutadaID}", method = RequestMethod.GET)
  public ModelAndView veureCiutadaGet(@PathVariable("ciutadaID") java.lang.Long ciutadaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewCiutadaGet(ciutadaID,
        request, response, true);
  }


  protected ModelAndView editAndViewCiutadaGet(@PathVariable("ciutadaID") java.lang.Long ciutadaID,
      HttpServletRequest request,
      HttpServletResponse response, boolean __isView) throws I18NException {
    if((!__isView) && !isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    } else {
      if(__isView && !isActiveFormView()) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return null;
      }
    }
    CiutadaJPA ciutada = findByPrimaryKey(request, ciutadaID);

    if (ciutada == null) {
      createMessageWarning(request, "error.notfound", ciutadaID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, ciutadaID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      CiutadaForm ciutadaForm = getCiutadaForm(ciutada, __isView, request, mav);
      ciutadaForm.setView(__isView);
      if(__isView) {
        ciutadaForm.setAllFieldsReadOnly(ALL_CIUTADA_FIELDS);
        ciutadaForm.setSaveButtonVisible(false);
        ciutadaForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(ciutadaForm, request, mav);
      mav.addObject("ciutadaForm", ciutadaForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Ciutada existent
   */
  @RequestMapping(value = "/{ciutadaID}/edit", method = RequestMethod.GET)
  public ModelAndView editarCiutadaGet(@PathVariable("ciutadaID") java.lang.Long ciutadaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewCiutadaGet(ciutadaID,
        request, response, false);
  }



  /**
   * Editar un Ciutada existent
   */
  @RequestMapping(value = "/{ciutadaID}/edit", method = RequestMethod.POST)
  public String editarCiutadaPost(@ModelAttribute CiutadaForm ciutadaForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    CiutadaJPA ciutada = ciutadaForm.getCiutada();

    try {
      preValidate(request, ciutadaForm, result);
      getWebValidator().validate(ciutadaForm, result);
      postValidate(request, ciutadaForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        ciutada = update(request, ciutada);
        createMessageSuccess(request, "success.modification", ciutada.getCiutadaID());
        status.setComplete();
        return getRedirectWhenModified(request, ciutadaForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          ciutada.getCiutadaID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, ciutadaForm, __e);
    }

  }


  /**
   * Eliminar un Ciutada existent
   */
  @RequestMapping(value = "/{ciutadaID}/delete")
  public String eliminarCiutada(@PathVariable("ciutadaID") java.lang.Long ciutadaID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Ciutada ciutada = this.findByPrimaryKey(request, ciutadaID);
      if (ciutada == null) {
        String __msg = createMessageError(request, "error.notfound", ciutadaID);
        return getRedirectWhenDelete(request, ciutadaID, new Exception(__msg));
      } else {
        delete(request, ciutada);
        createMessageSuccess(request, "success.deleted", ciutadaID);
        return getRedirectWhenDelete(request, ciutadaID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", ciutadaID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, ciutadaID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute CiutadaFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarCiutada(stringToPK(seleccionats[i]), request, response);
    }
  }
  if (redirect == null) {
    redirect = getRedirectWhenDelete(request, null,null);
  }

  return redirect;
}



public java.lang.Long stringToPK(String value) {
  return java.lang.Long.parseLong(value, 10);
}

  @Override
  public String[] getArgumentsMissatge(Object __ciutadaID, Throwable e) {
    java.lang.Long ciutadaID = (java.lang.Long)__ciutadaID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (ciutadaID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(ciutadaID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "ciutada.ciutada";
  }

  public String getEntityNameCodePlural() {
    return "ciutada.ciutada.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("ciutada.ciutadaID");
  }

  @InitBinder("ciutadaFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("ciutadaForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "ciutada.ciutadaID");
  }

  public CiutadaWebValidator getWebValidator() {
    return ciutadaWebValidator;
  }


  public void setWebValidator(CiutadaWebValidator __val) {
    if (__val != null) {
      this.ciutadaWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Ciutada
   */
  @RequestMapping(value = "/{ciutadaID}/cancel")
  public String cancelCiutada(@PathVariable("ciutadaID") java.lang.Long ciutadaID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, ciutadaID);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // Mètodes a sobreescriure 

  public boolean isActiveList() {
    return true;
  }


  public boolean isActiveFormNew() {
    return true;
  }


  public boolean isActiveFormEdit() {
    return true;
  }


  public boolean isActiveDelete() {
    return true;
  }


  public boolean isActiveFormView() {
    return isActiveFormEdit();
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,CiutadaForm ciutadaForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,CiutadaForm ciutadaForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, CiutadaFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, CiutadaFilterForm filterForm,  List<Ciutada> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, CiutadaForm ciutadaForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, CiutadaForm ciutadaForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long ciutadaID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long ciutadaID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "ciutadaFormWebDB";
  }

  public String getTileList() {
    return "ciutadaListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "Ciutada_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public CiutadaJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long ciutadaID) throws I18NException {
    return (CiutadaJPA) ciutadaEjb.findByPrimaryKey(ciutadaID);
  }


  public CiutadaJPA create(HttpServletRequest request, CiutadaJPA ciutada)
    throws I18NException, I18NValidationException {
    return (CiutadaJPA) ciutadaEjb.create(ciutada);
  }


  public CiutadaJPA update(HttpServletRequest request, CiutadaJPA ciutada)
    throws I18NException, I18NValidationException {
    return (CiutadaJPA) ciutadaEjb.update(ciutada);
  }


  public void delete(HttpServletRequest request, Ciutada ciutada) throws I18NException {
    ciutadaEjb.delete(ciutada);
  }

} // Final de Classe

