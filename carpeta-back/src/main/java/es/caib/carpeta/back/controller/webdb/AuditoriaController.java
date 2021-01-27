package es.caib.carpeta.back.controller.webdb;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.utils.Utils;
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
import es.caib.carpeta.back.form.webdb.AuditoriaForm;

import es.caib.carpeta.back.validator.webdb.AuditoriaWebValidator;

import es.caib.carpeta.jpa.AuditoriaJPA;
import es.caib.carpeta.model.entity.Auditoria;
import es.caib.carpeta.model.fields.*;

/**
 * Controller per gestionar un Auditoria
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/auditoria")
@SessionAttributes(types = { AuditoriaForm.class, AuditoriaFilterForm.class })
public class AuditoriaController
    extends es.caib.carpeta.back.controller.CarpetaBaseController<Auditoria, java.lang.Long> implements AuditoriaFields {

  @EJB(mappedName = es.caib.carpeta.ejb.AuditoriaLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.AuditoriaLocal auditoriaEjb;

  @Autowired
  private AuditoriaWebValidator auditoriaWebValidator;

  @Autowired
  protected AuditoriaRefList auditoriaRefList;

  /**
   * Llistat de totes Auditoria
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    AuditoriaFilterForm ff;
    ff = (AuditoriaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Auditoria de forma paginada
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
    llistat(mav, request, getAuditoriaFilterForm(pagina, mav, request));
    return mav;
  }

  public AuditoriaFilterForm getAuditoriaFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    AuditoriaFilterForm auditoriaFilterForm;
    auditoriaFilterForm = (AuditoriaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(auditoriaFilterForm == null) {
      auditoriaFilterForm = new AuditoriaFilterForm();
      auditoriaFilterForm.setContexte(getContextWeb());
      auditoriaFilterForm.setEntityNameCode(getEntityNameCode());
      auditoriaFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      auditoriaFilterForm.setNou(true);
    } else {
      auditoriaFilterForm.setNou(false);
    }
    auditoriaFilterForm.setPage(pagina == null ? 1 : pagina);
    return auditoriaFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Auditoria de forma paginada
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
      @ModelAttribute AuditoriaFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getAuditoriaFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Auditoria de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Auditoria> llistat(ModelAndView mav, HttpServletRequest request,
     AuditoriaFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Auditoria> auditoria = processarLlistat(auditoriaEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("auditoriaItems", auditoria);

    mav.addObject("auditoriaFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, auditoria, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, auditoria);

    return auditoria;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(AuditoriaFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Auditoria> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field tipus
    {
      _listSKV = getReferenceListForTipus(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForTipus(_tmp);
      if (filterForm.getGroupByFields().contains(TIPUS)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TIPUS, false);
      };
    }

    // Field entitatID
    {
      _listSKV = getReferenceListForEntitatID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForEntitatID(_tmp);
      if (filterForm.getGroupByFields().contains(ENTITATID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ENTITATID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    AuditoriaFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Auditoria> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_AUDITORIA_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(TIPUS, filterForm.getMapOfValuesForTipus());
    __mapping.put(ENTITATID, filterForm.getMapOfValuesForEntitatID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Auditoria
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearAuditoriaGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    AuditoriaForm auditoriaForm = getAuditoriaForm(null, false, request, mav);
    mav.addObject("auditoriaForm" ,auditoriaForm);
    fillReferencesForForm(auditoriaForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public AuditoriaForm getAuditoriaForm(AuditoriaJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    AuditoriaForm auditoriaForm;
    if(_jpa == null) {
      auditoriaForm = new AuditoriaForm(new AuditoriaJPA(), true);
    } else {
      auditoriaForm = new AuditoriaForm(_jpa, false);
      auditoriaForm.setView(__isView);
    }
    auditoriaForm.setContexte(getContextWeb());
    auditoriaForm.setEntityNameCode(getEntityNameCode());
    auditoriaForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return auditoriaForm;
  }

  public void fillReferencesForForm(AuditoriaForm auditoriaForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (auditoriaForm.getListOfValuesForTipus() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipus(request, mav, auditoriaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      auditoriaForm.setListOfValuesForTipus(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (auditoriaForm.getListOfValuesForEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEntitatID(request, mav, auditoriaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      auditoriaForm.setListOfValuesForEntitatID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou Auditoria
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearAuditoriaPost(@ModelAttribute AuditoriaForm auditoriaForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    AuditoriaJPA auditoria = auditoriaForm.getAuditoria();

    try {
      preValidate(request, auditoriaForm, result);
      getWebValidator().validate(auditoriaForm, result);
      postValidate(request,auditoriaForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        auditoria = create(request, auditoria);
        createMessageSuccess(request, "success.creation", auditoria.getAuditoriaID());
        auditoriaForm.setAuditoria(auditoria);
        return getRedirectWhenCreated(request, auditoriaForm);
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

  @RequestMapping(value = "/view/{auditoriaID}", method = RequestMethod.GET)
  public ModelAndView veureAuditoriaGet(@PathVariable("auditoriaID") java.lang.Long auditoriaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewAuditoriaGet(auditoriaID,
        request, response, true);
  }


  protected ModelAndView editAndViewAuditoriaGet(@PathVariable("auditoriaID") java.lang.Long auditoriaID,
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
    AuditoriaJPA auditoria = findByPrimaryKey(request, auditoriaID);

    if (auditoria == null) {
      createMessageWarning(request, "error.notfound", auditoriaID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, auditoriaID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      AuditoriaForm auditoriaForm = getAuditoriaForm(auditoria, __isView, request, mav);
      auditoriaForm.setView(__isView);
      if(__isView) {
        auditoriaForm.setAllFieldsReadOnly(ALL_AUDITORIA_FIELDS);
        auditoriaForm.setSaveButtonVisible(false);
        auditoriaForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(auditoriaForm, request, mav);
      mav.addObject("auditoriaForm", auditoriaForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Auditoria existent
   */
  @RequestMapping(value = "/{auditoriaID}/edit", method = RequestMethod.GET)
  public ModelAndView editarAuditoriaGet(@PathVariable("auditoriaID") java.lang.Long auditoriaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewAuditoriaGet(auditoriaID,
        request, response, false);
  }



  /**
   * Editar un Auditoria existent
   */
  @RequestMapping(value = "/{auditoriaID}/edit", method = RequestMethod.POST)
  public String editarAuditoriaPost(@ModelAttribute AuditoriaForm auditoriaForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    AuditoriaJPA auditoria = auditoriaForm.getAuditoria();

    try {
      preValidate(request, auditoriaForm, result);
      getWebValidator().validate(auditoriaForm, result);
      postValidate(request, auditoriaForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        auditoria = update(request, auditoria);
        createMessageSuccess(request, "success.modification", auditoria.getAuditoriaID());
        status.setComplete();
        return getRedirectWhenModified(request, auditoriaForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          auditoria.getAuditoriaID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, auditoriaForm, __e);
    }

  }


  /**
   * Eliminar un Auditoria existent
   */
  @RequestMapping(value = "/{auditoriaID}/delete")
  public String eliminarAuditoria(@PathVariable("auditoriaID") java.lang.Long auditoriaID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Auditoria auditoria = auditoriaEjb.findByPrimaryKey(auditoriaID);
      if (auditoria == null) {
        String __msg =createMessageError(request, "error.notfound", auditoriaID);
        return getRedirectWhenDelete(request, auditoriaID, new Exception(__msg));
      } else {
        delete(request, auditoria);
        createMessageSuccess(request, "success.deleted", auditoriaID);
        return getRedirectWhenDelete(request, auditoriaID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", auditoriaID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, auditoriaID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute AuditoriaFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarAuditoria(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __auditoriaID, Throwable e) {
    java.lang.Long auditoriaID = (java.lang.Long)__auditoriaID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (auditoriaID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(auditoriaID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "auditoria.auditoria";
  }

  public String getEntityNameCodePlural() {
    return "auditoria.auditoria.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("auditoria.auditoriaID");
  }

  @InitBinder("auditoriaFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("auditoriaForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    binder.setDisallowedFields("auditoriaID");

  }

  public AuditoriaWebValidator getWebValidator() {
    return auditoriaWebValidator;
  }


  public void setWebValidator(AuditoriaWebValidator __val) {
    if (__val != null) {
      this.auditoriaWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Auditoria
   */
  @RequestMapping(value = "/{auditoriaID}/cancel")
  public String cancelAuditoria(@PathVariable("auditoriaID") java.lang.Long auditoriaID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, auditoriaID);
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


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, AuditoriaForm auditoriaForm, Where where)  throws I18NException {
    if (auditoriaForm.isHiddenField(TIPUS)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForTipus(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, AuditoriaFilterForm auditoriaFilterForm,
       List<Auditoria> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (auditoriaFilterForm.isHiddenField(TIPUS)
      && !auditoriaFilterForm.isGroupByField(TIPUS)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForTipus(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    __tmp.add(new StringKeyValue("4" , "4"));
    __tmp.add(new StringKeyValue("5" , "5"));
    __tmp.add(new StringKeyValue("6" , "6"));
    __tmp.add(new StringKeyValue("7" , "7"));
    __tmp.add(new StringKeyValue("8" , "8"));
    __tmp.add(new StringKeyValue("9" , "9"));
    __tmp.add(new StringKeyValue("10" , "10"));
    __tmp.add(new StringKeyValue("11" , "11"));
    __tmp.add(new StringKeyValue("12" , "12"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, AuditoriaForm auditoriaForm, Where where)  throws I18NException {
    if (auditoriaForm.isHiddenField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForEntitatID(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, AuditoriaFilterForm auditoriaFilterForm,
       List<Auditoria> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (auditoriaFilterForm.isHiddenField(ENTITATID)
      && !auditoriaFilterForm.isGroupByField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForEntitatID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    return __tmp;
  }


  public void preValidate(HttpServletRequest request,AuditoriaForm auditoriaForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,AuditoriaForm auditoriaForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, AuditoriaFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, AuditoriaFilterForm filterForm,  List<Auditoria> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, AuditoriaForm auditoriaForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, AuditoriaForm auditoriaForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long auditoriaID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long auditoriaID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "auditoriaFormWebDB";
  }

  public String getTileList() {
    return "auditoriaListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "AuditoriaWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public AuditoriaJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long auditoriaID) throws I18NException {
    return (AuditoriaJPA) auditoriaEjb.findByPrimaryKey(auditoriaID);
  }


  public AuditoriaJPA create(HttpServletRequest request, AuditoriaJPA auditoria)
    throws Exception,I18NException, I18NValidationException {
    return (AuditoriaJPA) auditoriaEjb.create(auditoria);
  }


  public AuditoriaJPA update(HttpServletRequest request, AuditoriaJPA auditoria)
    throws Exception,I18NException, I18NValidationException {
    return (AuditoriaJPA) auditoriaEjb.update(auditoria);
  }


  public void delete(HttpServletRequest request, Auditoria auditoria) throws Exception,I18NException {
    auditoriaEjb.delete(auditoria);
  }

} // Final de Classe

