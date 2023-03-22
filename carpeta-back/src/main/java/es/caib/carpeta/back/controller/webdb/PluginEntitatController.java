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
import es.caib.carpeta.back.form.webdb.PluginEntitatForm;

import es.caib.carpeta.back.validator.webdb.PluginEntitatWebValidator;

import es.caib.carpeta.persistence.PluginEntitatJPA;
import es.caib.carpeta.model.entity.PluginEntitat;
import es.caib.carpeta.model.fields.*;

/**
 * Controller per gestionar un PluginEntitat
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/pluginEntitat")
@SessionAttributes(types = { PluginEntitatForm.class, PluginEntitatFilterForm.class })
public class PluginEntitatController
    extends es.caib.carpeta.back.controller.CarpetaBaseController<PluginEntitat, java.lang.Long> implements PluginEntitatFields {

  @EJB(mappedName = es.caib.carpeta.ejb.PluginEntitatService.JNDI_NAME)
  protected es.caib.carpeta.ejb.PluginEntitatService pluginEntitatEjb;

  @Autowired
  private PluginEntitatWebValidator pluginEntitatWebValidator;

  @Autowired
  protected PluginEntitatRefList pluginEntitatRefList;

  // References 
  @Autowired
  protected PluginRefList pluginRefList;

  // References 
  @Autowired
  protected EntitatRefList entitatRefList;

  // References 
  @Autowired
  protected SeccioRefList seccioRefList;

  /**
   * Llistat de totes PluginEntitat
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    PluginEntitatFilterForm ff;
    ff = (PluginEntitatFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar PluginEntitat de forma paginada
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
    llistat(mav, request, getPluginEntitatFilterForm(pagina, mav, request));
    return mav;
  }

  public PluginEntitatFilterForm getPluginEntitatFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    PluginEntitatFilterForm pluginEntitatFilterForm;
    pluginEntitatFilterForm = (PluginEntitatFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(pluginEntitatFilterForm == null) {
      pluginEntitatFilterForm = new PluginEntitatFilterForm();
      pluginEntitatFilterForm.setContexte(getContextWeb());
      pluginEntitatFilterForm.setEntityNameCode(getEntityNameCode());
      pluginEntitatFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      pluginEntitatFilterForm.setNou(true);
    } else {
      pluginEntitatFilterForm.setNou(false);
    }
    pluginEntitatFilterForm.setPage(pagina == null ? 1 : pagina);
    return pluginEntitatFilterForm;
  }

  /**
   * Segona i següent peticions per llistar PluginEntitat de forma paginada
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
      @ModelAttribute PluginEntitatFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getPluginEntitatFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de PluginEntitat de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<PluginEntitat> llistat(ModelAndView mav, HttpServletRequest request,
     PluginEntitatFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<PluginEntitat> pluginEntitat = processarLlistat(pluginEntitatEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("pluginEntitatItems", pluginEntitat);

    mav.addObject("pluginEntitatFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, pluginEntitat, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, pluginEntitat);

    return pluginEntitat;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(PluginEntitatFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<PluginEntitat> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field pluginID
    {
      _listSKV = getReferenceListForPluginID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfPluginForPluginID(_tmp);
      if (filterForm.getGroupByFields().contains(PLUGINID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, PLUGINID, false);
      };
    }

    // Field entitatID
    {
      _listSKV = getReferenceListForEntitatID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfEntitatForEntitatID(_tmp);
      if (filterForm.getGroupByFields().contains(ENTITATID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ENTITATID, false);
      };
    }


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, ACTIU);

    // Field seccioID
    {
      _listSKV = getReferenceListForSeccioID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfSeccioForSeccioID(_tmp);
      if (filterForm.getGroupByFields().contains(SECCIOID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, SECCIOID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    PluginEntitatFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<PluginEntitat> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_PLUGINENTITAT_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(PLUGINID, filterForm.getMapOfPluginForPluginID());
    __mapping.put(ENTITATID, filterForm.getMapOfEntitatForEntitatID());
    __mapping.put(SECCIOID, filterForm.getMapOfSeccioForSeccioID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou PluginEntitat
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearPluginEntitatGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    PluginEntitatForm pluginEntitatForm = getPluginEntitatForm(null, false, request, mav);
    mav.addObject("pluginEntitatForm" ,pluginEntitatForm);
    fillReferencesForForm(pluginEntitatForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public PluginEntitatForm getPluginEntitatForm(PluginEntitatJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    PluginEntitatForm pluginEntitatForm;
    if(_jpa == null) {
      pluginEntitatForm = new PluginEntitatForm(new PluginEntitatJPA(), true);
    } else {
      pluginEntitatForm = new PluginEntitatForm(_jpa, false);
      pluginEntitatForm.setView(__isView);
    }
    pluginEntitatForm.setContexte(getContextWeb());
    pluginEntitatForm.setEntityNameCode(getEntityNameCode());
    pluginEntitatForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return pluginEntitatForm;
  }

  public void fillReferencesForForm(PluginEntitatForm pluginEntitatForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (pluginEntitatForm.getListOfPluginForPluginID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForPluginID(request, mav, pluginEntitatForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      pluginEntitatForm.setListOfPluginForPluginID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (pluginEntitatForm.getListOfEntitatForEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEntitatID(request, mav, pluginEntitatForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      pluginEntitatForm.setListOfEntitatForEntitatID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (pluginEntitatForm.getListOfSeccioForSeccioID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForSeccioID(request, mav, pluginEntitatForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      pluginEntitatForm.setListOfSeccioForSeccioID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou PluginEntitat
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearPluginEntitatPost(@ModelAttribute PluginEntitatForm pluginEntitatForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    PluginEntitatJPA pluginEntitat = pluginEntitatForm.getPluginEntitat();

    try {
      preValidate(request, pluginEntitatForm, result);
      getWebValidator().validate(pluginEntitatForm, result);
      postValidate(request,pluginEntitatForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        pluginEntitat = create(request, pluginEntitat);
        createMessageSuccess(request, "success.creation", pluginEntitat.getPluginEntitatID());
        pluginEntitatForm.setPluginEntitat(pluginEntitat);
        return getRedirectWhenCreated(request, pluginEntitatForm);
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

  @RequestMapping(value = "/view/{pluginEntitatID}", method = RequestMethod.GET)
  public ModelAndView veurePluginEntitatGet(@PathVariable("pluginEntitatID") java.lang.Long pluginEntitatID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPluginEntitatGet(pluginEntitatID,
        request, response, true);
  }


  protected ModelAndView editAndViewPluginEntitatGet(@PathVariable("pluginEntitatID") java.lang.Long pluginEntitatID,
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
    PluginEntitatJPA pluginEntitat = findByPrimaryKey(request, pluginEntitatID);

    if (pluginEntitat == null) {
      createMessageWarning(request, "error.notfound", pluginEntitatID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, pluginEntitatID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      PluginEntitatForm pluginEntitatForm = getPluginEntitatForm(pluginEntitat, __isView, request, mav);
      pluginEntitatForm.setView(__isView);
      if(__isView) {
        pluginEntitatForm.setAllFieldsReadOnly(ALL_PLUGINENTITAT_FIELDS);
        pluginEntitatForm.setSaveButtonVisible(false);
        pluginEntitatForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(pluginEntitatForm, request, mav);
      mav.addObject("pluginEntitatForm", pluginEntitatForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un PluginEntitat existent
   */
  @RequestMapping(value = "/{pluginEntitatID}/edit", method = RequestMethod.GET)
  public ModelAndView editarPluginEntitatGet(@PathVariable("pluginEntitatID") java.lang.Long pluginEntitatID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPluginEntitatGet(pluginEntitatID,
        request, response, false);
  }



  /**
   * Editar un PluginEntitat existent
   */
  @RequestMapping(value = "/{pluginEntitatID}/edit", method = RequestMethod.POST)
  public String editarPluginEntitatPost(@ModelAttribute PluginEntitatForm pluginEntitatForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    PluginEntitatJPA pluginEntitat = pluginEntitatForm.getPluginEntitat();

    try {
      preValidate(request, pluginEntitatForm, result);
      getWebValidator().validate(pluginEntitatForm, result);
      postValidate(request, pluginEntitatForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        pluginEntitat = update(request, pluginEntitat);
        createMessageSuccess(request, "success.modification", pluginEntitat.getPluginEntitatID());
        status.setComplete();
        return getRedirectWhenModified(request, pluginEntitatForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          pluginEntitat.getPluginEntitatID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, pluginEntitatForm, __e);
    }

  }


  /**
   * Eliminar un PluginEntitat existent
   */
  @RequestMapping(value = "/{pluginEntitatID}/delete")
  public String eliminarPluginEntitat(@PathVariable("pluginEntitatID") java.lang.Long pluginEntitatID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      PluginEntitat pluginEntitat = this.findByPrimaryKey(request, pluginEntitatID);
      if (pluginEntitat == null) {
        String __msg = createMessageError(request, "error.notfound", pluginEntitatID);
        return getRedirectWhenDelete(request, pluginEntitatID, new Exception(__msg));
      } else {
        delete(request, pluginEntitat);
        createMessageSuccess(request, "success.deleted", pluginEntitatID);
        return getRedirectWhenDelete(request, pluginEntitatID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", pluginEntitatID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, pluginEntitatID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute PluginEntitatFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarPluginEntitat(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __pluginEntitatID, Throwable e) {
    java.lang.Long pluginEntitatID = (java.lang.Long)__pluginEntitatID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (pluginEntitatID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(pluginEntitatID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "pluginEntitat.pluginEntitat";
  }

  public String getEntityNameCodePlural() {
    return "pluginEntitat.pluginEntitat.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("pluginEntitat.pluginEntitatID");
  }

  @InitBinder("pluginEntitatFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("pluginEntitatForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "pluginEntitat.pluginEntitatID");
  }

  public PluginEntitatWebValidator getWebValidator() {
    return pluginEntitatWebValidator;
  }


  public void setWebValidator(PluginEntitatWebValidator __val) {
    if (__val != null) {
      this.pluginEntitatWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de PluginEntitat
   */
  @RequestMapping(value = "/{pluginEntitatID}/cancel")
  public String cancelPluginEntitat(@PathVariable("pluginEntitatID") java.lang.Long pluginEntitatID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, pluginEntitatID);
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


  public List<StringKeyValue> getReferenceListForPluginID(HttpServletRequest request,
       ModelAndView mav, PluginEntitatForm pluginEntitatForm, Where where)  throws I18NException {
    if (pluginEntitatForm.isHiddenField(PLUGINID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (pluginEntitatForm.isReadOnlyField(PLUGINID)) {
      _where = PluginFields.PLUGINID.equal(pluginEntitatForm.getPluginEntitat().getPluginID());
    }
    return getReferenceListForPluginID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForPluginID(HttpServletRequest request,
       ModelAndView mav, PluginEntitatFilterForm pluginEntitatFilterForm,
       List<PluginEntitat> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (pluginEntitatFilterForm.isHiddenField(PLUGINID)
      && !pluginEntitatFilterForm.isGroupByField(PLUGINID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(PLUGINID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (PluginEntitat _item : list) {
        _pkList.add(_item.getPluginID());
        }
        _w = PluginFields.PLUGINID.in(_pkList);
      }
    return getReferenceListForPluginID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForPluginID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return pluginRefList.getReferenceList(PluginFields.PLUGINID, where );
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, PluginEntitatForm pluginEntitatForm, Where where)  throws I18NException {
    if (pluginEntitatForm.isHiddenField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (pluginEntitatForm.isReadOnlyField(ENTITATID)) {
      _where = EntitatFields.ENTITATID.equal(pluginEntitatForm.getPluginEntitat().getEntitatID());
    }
    return getReferenceListForEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, PluginEntitatFilterForm pluginEntitatFilterForm,
       List<PluginEntitat> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (pluginEntitatFilterForm.isHiddenField(ENTITATID)
      && !pluginEntitatFilterForm.isGroupByField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (PluginEntitat _item : list) {
        _pkList.add(_item.getEntitatID());
        }
        _w = EntitatFields.ENTITATID.in(_pkList);
      }
    return getReferenceListForEntitatID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return entitatRefList.getReferenceList(EntitatFields.ENTITATID, where );
  }


  public List<StringKeyValue> getReferenceListForSeccioID(HttpServletRequest request,
       ModelAndView mav, PluginEntitatForm pluginEntitatForm, Where where)  throws I18NException {
    if (pluginEntitatForm.isHiddenField(SECCIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (pluginEntitatForm.isReadOnlyField(SECCIOID)) {
      _where = SeccioFields.SECCIOID.equal(pluginEntitatForm.getPluginEntitat().getSeccioID());
    }
    return getReferenceListForSeccioID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForSeccioID(HttpServletRequest request,
       ModelAndView mav, PluginEntitatFilterForm pluginEntitatFilterForm,
       List<PluginEntitat> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (pluginEntitatFilterForm.isHiddenField(SECCIOID)
      && !pluginEntitatFilterForm.isGroupByField(SECCIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(SECCIOID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (PluginEntitat _item : list) {
        if(_item.getSeccioID() == null) { continue; };
        _pkList.add(_item.getSeccioID());
        }
        _w = SeccioFields.SECCIOID.in(_pkList);
      }
    return getReferenceListForSeccioID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForSeccioID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return seccioRefList.getReferenceList(SeccioFields.SECCIOID, where );
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,PluginEntitatForm pluginEntitatForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,PluginEntitatForm pluginEntitatForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, PluginEntitatFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, PluginEntitatFilterForm filterForm,  List<PluginEntitat> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, PluginEntitatForm pluginEntitatForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, PluginEntitatForm pluginEntitatForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long pluginEntitatID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long pluginEntitatID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "pluginEntitatFormWebDB";
  }

  public String getTileList() {
    return "pluginEntitatListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "PluginEntitatWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public PluginEntitatJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long pluginEntitatID) throws I18NException {
    return (PluginEntitatJPA) pluginEntitatEjb.findByPrimaryKey(pluginEntitatID);
  }


  public PluginEntitatJPA create(HttpServletRequest request, PluginEntitatJPA pluginEntitat)
    throws I18NException, I18NValidationException {
    return (PluginEntitatJPA) pluginEntitatEjb.create(pluginEntitat);
  }


  public PluginEntitatJPA update(HttpServletRequest request, PluginEntitatJPA pluginEntitat)
    throws I18NException, I18NValidationException {
    return (PluginEntitatJPA) pluginEntitatEjb.update(pluginEntitat);
  }


  public void delete(HttpServletRequest request, PluginEntitat pluginEntitat) throws I18NException {
    pluginEntitatEjb.delete(pluginEntitat);
  }

} // Final de Classe

