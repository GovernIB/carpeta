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
import es.caib.carpeta.back.form.webdb.LogCarpetaForm;

import es.caib.carpeta.back.validator.webdb.LogCarpetaWebValidator;

import es.caib.carpeta.persistence.LogCarpetaJPA;
import es.caib.carpeta.model.entity.LogCarpeta;
import es.caib.carpeta.model.fields.*;

/**
 * Controller per gestionar un LogCarpeta
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/logCarpeta")
@SessionAttributes(types = { LogCarpetaForm.class, LogCarpetaFilterForm.class })
public class LogCarpetaController
    extends es.caib.carpeta.back.controller.CarpetaBaseController<LogCarpeta, java.lang.Long> implements LogCarpetaFields {

  @EJB(mappedName = es.caib.carpeta.ejb.LogCarpetaService.JNDI_NAME)
  protected es.caib.carpeta.ejb.LogCarpetaService logCarpetaEjb;

  @Autowired
  private LogCarpetaWebValidator logCarpetaWebValidator;

  @Autowired
  protected LogCarpetaRefList logCarpetaRefList;

  /**
   * Llistat de totes LogCarpeta
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    LogCarpetaFilterForm ff;
    ff = (LogCarpetaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar LogCarpeta de forma paginada
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
    llistat(mav, request, getLogCarpetaFilterForm(pagina, mav, request));
    return mav;
  }

  public LogCarpetaFilterForm getLogCarpetaFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    LogCarpetaFilterForm logCarpetaFilterForm;
    logCarpetaFilterForm = (LogCarpetaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(logCarpetaFilterForm == null) {
      logCarpetaFilterForm = new LogCarpetaFilterForm();
      logCarpetaFilterForm.setContexte(getContextWeb());
      logCarpetaFilterForm.setEntityNameCode(getEntityNameCode());
      logCarpetaFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      logCarpetaFilterForm.setNou(true);
    } else {
      logCarpetaFilterForm.setNou(false);
    }
    logCarpetaFilterForm.setPage(pagina == null ? 1 : pagina);
    return logCarpetaFilterForm;
  }

  /**
   * Segona i següent peticions per llistar LogCarpeta de forma paginada
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
      @ModelAttribute LogCarpetaFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getLogCarpetaFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de LogCarpeta de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<LogCarpeta> llistat(ModelAndView mav, HttpServletRequest request,
     LogCarpetaFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<LogCarpeta> logCarpeta = processarLlistat(logCarpetaEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("logCarpetaItems", logCarpeta);

    mav.addObject("logCarpetaFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, logCarpeta, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, logCarpeta);

    return logCarpeta;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(LogCarpetaFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<LogCarpeta> list, List<GroupByItem> groupItems) throws I18NException {
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

    // Field estat
    {
      _listSKV = getReferenceListForEstat(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForEstat(_tmp);
      if (filterForm.getGroupByFields().contains(ESTAT)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ESTAT, false);
      };
    }

    // Field pluginID
    {
      _listSKV = getReferenceListForPluginID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForPluginID(_tmp);
      if (filterForm.getGroupByFields().contains(PLUGINID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, PLUGINID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    LogCarpetaFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<LogCarpeta> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_LOGCARPETA_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(TIPUS, filterForm.getMapOfValuesForTipus());
    __mapping.put(ESTAT, filterForm.getMapOfValuesForEstat());
    __mapping.put(PLUGINID, filterForm.getMapOfValuesForPluginID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou LogCarpeta
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearLogCarpetaGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    LogCarpetaForm logCarpetaForm = getLogCarpetaForm(null, false, request, mav);
    mav.addObject("logCarpetaForm" ,logCarpetaForm);
    fillReferencesForForm(logCarpetaForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public LogCarpetaForm getLogCarpetaForm(LogCarpetaJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    LogCarpetaForm logCarpetaForm;
    if(_jpa == null) {
      logCarpetaForm = new LogCarpetaForm(new LogCarpetaJPA(), true);
    } else {
      logCarpetaForm = new LogCarpetaForm(_jpa, false);
      logCarpetaForm.setView(__isView);
    }
    logCarpetaForm.setContexte(getContextWeb());
    logCarpetaForm.setEntityNameCode(getEntityNameCode());
    logCarpetaForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return logCarpetaForm;
  }

  public void fillReferencesForForm(LogCarpetaForm logCarpetaForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (logCarpetaForm.getListOfValuesForTipus() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipus(request, mav, logCarpetaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      logCarpetaForm.setListOfValuesForTipus(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (logCarpetaForm.getListOfValuesForEstat() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEstat(request, mav, logCarpetaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      logCarpetaForm.setListOfValuesForEstat(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (logCarpetaForm.getListOfValuesForPluginID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForPluginID(request, mav, logCarpetaForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      logCarpetaForm.setListOfValuesForPluginID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou LogCarpeta
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearLogCarpetaPost(@ModelAttribute LogCarpetaForm logCarpetaForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    LogCarpetaJPA logCarpeta = logCarpetaForm.getLogCarpeta();

    try {
      preValidate(request, logCarpetaForm, result);
      getWebValidator().validate(logCarpetaForm, result);
      postValidate(request,logCarpetaForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        logCarpeta = create(request, logCarpeta);
        createMessageSuccess(request, "success.creation", logCarpeta.getLogID());
        logCarpetaForm.setLogCarpeta(logCarpeta);
        return getRedirectWhenCreated(request, logCarpetaForm);
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

  @RequestMapping(value = "/view/{logID}", method = RequestMethod.GET)
  public ModelAndView veureLogCarpetaGet(@PathVariable("logID") java.lang.Long logID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewLogCarpetaGet(logID,
        request, response, true);
  }


  protected ModelAndView editAndViewLogCarpetaGet(@PathVariable("logID") java.lang.Long logID,
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
    LogCarpetaJPA logCarpeta = findByPrimaryKey(request, logID);

    if (logCarpeta == null) {
      createMessageWarning(request, "error.notfound", logID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, logID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      LogCarpetaForm logCarpetaForm = getLogCarpetaForm(logCarpeta, __isView, request, mav);
      logCarpetaForm.setView(__isView);
      if(__isView) {
        logCarpetaForm.setAllFieldsReadOnly(ALL_LOGCARPETA_FIELDS);
        logCarpetaForm.setSaveButtonVisible(false);
        logCarpetaForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(logCarpetaForm, request, mav);
      mav.addObject("logCarpetaForm", logCarpetaForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un LogCarpeta existent
   */
  @RequestMapping(value = "/{logID}/edit", method = RequestMethod.GET)
  public ModelAndView editarLogCarpetaGet(@PathVariable("logID") java.lang.Long logID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewLogCarpetaGet(logID,
        request, response, false);
  }



  /**
   * Editar un LogCarpeta existent
   */
  @RequestMapping(value = "/{logID}/edit", method = RequestMethod.POST)
  public String editarLogCarpetaPost(@ModelAttribute LogCarpetaForm logCarpetaForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    LogCarpetaJPA logCarpeta = logCarpetaForm.getLogCarpeta();

    try {
      preValidate(request, logCarpetaForm, result);
      getWebValidator().validate(logCarpetaForm, result);
      postValidate(request, logCarpetaForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        logCarpeta = update(request, logCarpeta);
        createMessageSuccess(request, "success.modification", logCarpeta.getLogID());
        status.setComplete();
        return getRedirectWhenModified(request, logCarpetaForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          logCarpeta.getLogID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, logCarpetaForm, __e);
    }

  }


  /**
   * Eliminar un LogCarpeta existent
   */
  @RequestMapping(value = "/{logID}/delete")
  public String eliminarLogCarpeta(@PathVariable("logID") java.lang.Long logID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      LogCarpeta logCarpeta = logCarpetaEjb.findByPrimaryKey(logID);
      if (logCarpeta == null) {
        String __msg =createMessageError(request, "error.notfound", logID);
        return getRedirectWhenDelete(request, logID, new Exception(__msg));
      } else {
        delete(request, logCarpeta);
        createMessageSuccess(request, "success.deleted", logID);
        return getRedirectWhenDelete(request, logID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", logID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, logID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute LogCarpetaFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarLogCarpeta(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __logID, Throwable e) {
    java.lang.Long logID = (java.lang.Long)__logID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (logID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(logID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "logCarpeta.logCarpeta";
  }

  public String getEntityNameCodePlural() {
    return "logCarpeta.logCarpeta.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("logCarpeta.logID");
  }

  @InitBinder("logCarpetaFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("logCarpetaForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    binder.setDisallowedFields("logID");

  }

  public LogCarpetaWebValidator getWebValidator() {
    return logCarpetaWebValidator;
  }


  public void setWebValidator(LogCarpetaWebValidator __val) {
    if (__val != null) {
      this.logCarpetaWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de LogCarpeta
   */
  @RequestMapping(value = "/{logID}/cancel")
  public String cancelLogCarpeta(@PathVariable("logID") java.lang.Long logID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, logID);
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
       ModelAndView mav, LogCarpetaForm logCarpetaForm, Where where)  throws I18NException {
    if (logCarpetaForm.isHiddenField(TIPUS)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForTipus(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, LogCarpetaFilterForm logCarpetaFilterForm,
       List<LogCarpeta> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (logCarpetaFilterForm.isHiddenField(TIPUS)
      && !logCarpetaFilterForm.isGroupByField(TIPUS)) {
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
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request,
       ModelAndView mav, LogCarpetaForm logCarpetaForm, Where where)  throws I18NException {
    if (logCarpetaForm.isHiddenField(ESTAT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForEstat(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request,
       ModelAndView mav, LogCarpetaFilterForm logCarpetaFilterForm,
       List<LogCarpeta> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (logCarpetaFilterForm.isHiddenField(ESTAT)
      && !logCarpetaFilterForm.isGroupByField(ESTAT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForEstat(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("0" , "0"));
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForPluginID(HttpServletRequest request,
       ModelAndView mav, LogCarpetaForm logCarpetaForm, Where where)  throws I18NException {
    if (logCarpetaForm.isHiddenField(PLUGINID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForPluginID(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForPluginID(HttpServletRequest request,
       ModelAndView mav, LogCarpetaFilterForm logCarpetaFilterForm,
       List<LogCarpeta> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (logCarpetaFilterForm.isHiddenField(PLUGINID)
      && !logCarpetaFilterForm.isGroupByField(PLUGINID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForPluginID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForPluginID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    __tmp.add(new StringKeyValue("4" , "4"));
    __tmp.add(new StringKeyValue("5" , "5"));
    return __tmp;
  }


  public void preValidate(HttpServletRequest request,LogCarpetaForm logCarpetaForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,LogCarpetaForm logCarpetaForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, LogCarpetaFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, LogCarpetaFilterForm filterForm,  List<LogCarpeta> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, LogCarpetaForm logCarpetaForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, LogCarpetaForm logCarpetaForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long logID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long logID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "logCarpetaFormWebDB";
  }

  public String getTileList() {
    return "logCarpetaListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "LogCarpetaWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public LogCarpetaJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long logID) throws I18NException {
    return (LogCarpetaJPA) logCarpetaEjb.findByPrimaryKey(logID);
  }


  public LogCarpetaJPA create(HttpServletRequest request, LogCarpetaJPA logCarpeta)
    throws Exception,I18NException, I18NValidationException {
    return (LogCarpetaJPA) logCarpetaEjb.create(logCarpeta);
  }


  public LogCarpetaJPA update(HttpServletRequest request, LogCarpetaJPA logCarpeta)
    throws Exception,I18NException, I18NValidationException {
    return (LogCarpetaJPA) logCarpetaEjb.update(logCarpeta);
  }


  public void delete(HttpServletRequest request, LogCarpeta logCarpeta) throws Exception,I18NException {
    logCarpetaEjb.delete(logCarpeta);
  }

} // Final de Classe

