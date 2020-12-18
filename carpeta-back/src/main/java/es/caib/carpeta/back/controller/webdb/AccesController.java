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
import es.caib.carpeta.back.form.webdb.AccesForm;

import es.caib.carpeta.back.validator.webdb.AccesWebValidator;

import es.caib.carpeta.jpa.AccesJPA;
import es.caib.carpeta.model.entity.Acces;
import es.caib.carpeta.model.fields.*;

/**
 * Controller per gestionar un Acces
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/acces")
@SessionAttributes(types = { AccesForm.class, AccesFilterForm.class })
public class AccesController
    extends es.caib.carpeta.back.controller.CarpetaBaseController<Acces, java.lang.Long> implements AccesFields {

  @EJB(mappedName = es.caib.carpeta.ejb.AccesLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.AccesLocal accesEjb;

  @Autowired
  private AccesWebValidator accesWebValidator;

  @Autowired
  protected AccesRefList accesRefList;

  // References 
  @Autowired
  protected EntitatRefList entitatRefList;

  /**
   * Llistat de totes Acces
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    AccesFilterForm ff;
    ff = (AccesFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Acces de forma paginada
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
    llistat(mav, request, getAccesFilterForm(pagina, mav, request));
    return mav;
  }

  public AccesFilterForm getAccesFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    AccesFilterForm accesFilterForm;
    accesFilterForm = (AccesFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(accesFilterForm == null) {
      accesFilterForm = new AccesFilterForm();
      accesFilterForm.setContexte(getContextWeb());
      accesFilterForm.setEntityNameCode(getEntityNameCode());
      accesFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      accesFilterForm.setNou(true);
    } else {
      accesFilterForm.setNou(false);
    }
    accesFilterForm.setPage(pagina == null ? 1 : pagina);
    return accesFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Acces de forma paginada
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
      @ModelAttribute AccesFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getAccesFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Acces de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Acces> llistat(ModelAndView mav, HttpServletRequest request,
     AccesFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Acces> acces = processarLlistat(accesEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("accesItems", acces);

    mav.addObject("accesFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, acces, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, acces);

    return acces;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(AccesFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Acces> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field entitatID
    {
      _listSKV = getReferenceListForEntitatID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfEntitatForEntitatID(_tmp);
      if (filterForm.getGroupByFields().contains(ENTITATID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ENTITATID, false);
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

    // Field tipus
    {
      _listSKV = getReferenceListForTipus(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForTipus(_tmp);
      if (filterForm.getGroupByFields().contains(TIPUS)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TIPUS, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    AccesFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Acces> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_ACCES_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(ENTITATID, filterForm.getMapOfEntitatForEntitatID());
    __mapping.put(PLUGINID, filterForm.getMapOfValuesForPluginID());
    __mapping.put(TIPUS, filterForm.getMapOfValuesForTipus());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Acces
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearAccesGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    AccesForm accesForm = getAccesForm(null, false, request, mav);
    mav.addObject("accesForm" ,accesForm);
    fillReferencesForForm(accesForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public AccesForm getAccesForm(AccesJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    AccesForm accesForm;
    if(_jpa == null) {
      accesForm = new AccesForm(new AccesJPA(), true);
    } else {
      accesForm = new AccesForm(_jpa, false);
      accesForm.setView(__isView);
    }
    accesForm.setContexte(getContextWeb());
    accesForm.setEntityNameCode(getEntityNameCode());
    accesForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return accesForm;
  }

  public void fillReferencesForForm(AccesForm accesForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (accesForm.getListOfEntitatForEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEntitatID(request, mav, accesForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      accesForm.setListOfEntitatForEntitatID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (accesForm.getListOfValuesForPluginID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForPluginID(request, mav, accesForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      accesForm.setListOfValuesForPluginID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (accesForm.getListOfValuesForTipus() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipus(request, mav, accesForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      accesForm.setListOfValuesForTipus(_listSKV);
    }
    
  }

  /**
   * Guardar un nou Acces
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearAccesPost(@ModelAttribute AccesForm accesForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    AccesJPA acces = accesForm.getAcces();

    try {
      preValidate(request, accesForm, result);
      getWebValidator().validate(accesForm, result);
      postValidate(request,accesForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        acces = create(request, acces);
        createMessageSuccess(request, "success.creation", acces.getAccesID());
        accesForm.setAcces(acces);
        return getRedirectWhenCreated(request, accesForm);
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

  @RequestMapping(value = "/view/{accesID}", method = RequestMethod.GET)
  public ModelAndView veureAccesGet(@PathVariable("accesID") java.lang.Long accesID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewAccesGet(accesID,
        request, response, true);
  }


  protected ModelAndView editAndViewAccesGet(@PathVariable("accesID") java.lang.Long accesID,
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
    AccesJPA acces = findByPrimaryKey(request, accesID);

    if (acces == null) {
      createMessageWarning(request, "error.notfound", accesID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, accesID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      AccesForm accesForm = getAccesForm(acces, __isView, request, mav);
      accesForm.setView(__isView);
      if(__isView) {
        accesForm.setAllFieldsReadOnly(ALL_ACCES_FIELDS);
        accesForm.setSaveButtonVisible(false);
        accesForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(accesForm, request, mav);
      mav.addObject("accesForm", accesForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Acces existent
   */
  @RequestMapping(value = "/{accesID}/edit", method = RequestMethod.GET)
  public ModelAndView editarAccesGet(@PathVariable("accesID") java.lang.Long accesID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewAccesGet(accesID,
        request, response, false);
  }



  /**
   * Editar un Acces existent
   */
  @RequestMapping(value = "/{accesID}/edit", method = RequestMethod.POST)
  public String editarAccesPost(@ModelAttribute AccesForm accesForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    AccesJPA acces = accesForm.getAcces();

    try {
      preValidate(request, accesForm, result);
      getWebValidator().validate(accesForm, result);
      postValidate(request, accesForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        acces = update(request, acces);
        createMessageSuccess(request, "success.modification", acces.getAccesID());
        status.setComplete();
        return getRedirectWhenModified(request, accesForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          acces.getAccesID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, accesForm, __e);
    }

  }


  /**
   * Eliminar un Acces existent
   */
  @RequestMapping(value = "/{accesID}/delete")
  public String eliminarAcces(@PathVariable("accesID") java.lang.Long accesID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Acces acces = accesEjb.findByPrimaryKey(accesID);
      if (acces == null) {
        String __msg =createMessageError(request, "error.notfound", accesID);
        return getRedirectWhenDelete(request, accesID, new Exception(__msg));
      } else {
        delete(request, acces);
        createMessageSuccess(request, "success.deleted", accesID);
        return getRedirectWhenDelete(request, accesID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", accesID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, accesID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute AccesFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarAcces(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __accesID, Throwable e) {
    java.lang.Long accesID = (java.lang.Long)__accesID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (accesID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(accesID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "acces.acces";
  }

  public String getEntityNameCodePlural() {
    return "acces.acces.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("acces.accesID");
  }

  @InitBinder("accesFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("accesForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    binder.setDisallowedFields("accesID");

  }

  public AccesWebValidator getWebValidator() {
    return accesWebValidator;
  }


  public void setWebValidator(AccesWebValidator __val) {
    if (__val != null) {
      this.accesWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Acces
   */
  @RequestMapping(value = "/{accesID}/cancel")
  public String cancelAcces(@PathVariable("accesID") java.lang.Long accesID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, accesID);
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


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, AccesForm accesForm, Where where)  throws I18NException {
    if (accesForm.isHiddenField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (accesForm.isReadOnlyField(ENTITATID)) {
      _where = EntitatFields.ENTITATID.equal(accesForm.getAcces().getEntitatID());
    }
    return getReferenceListForEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, AccesFilterForm accesFilterForm,
       List<Acces> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (accesFilterForm.isHiddenField(ENTITATID)
      && !accesFilterForm.isGroupByField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Acces _item : list) {
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


  public List<StringKeyValue> getReferenceListForPluginID(HttpServletRequest request,
       ModelAndView mav, AccesForm accesForm, Where where)  throws I18NException {
    if (accesForm.isHiddenField(PLUGINID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForPluginID(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForPluginID(HttpServletRequest request,
       ModelAndView mav, AccesFilterForm accesFilterForm,
       List<Acces> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (accesFilterForm.isHiddenField(PLUGINID)
      && !accesFilterForm.isGroupByField(PLUGINID)) {
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


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, AccesForm accesForm, Where where)  throws I18NException {
    if (accesForm.isHiddenField(TIPUS)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForTipus(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, AccesFilterForm accesFilterForm,
       List<Acces> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (accesFilterForm.isHiddenField(TIPUS)
      && !accesFilterForm.isGroupByField(TIPUS)) {
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
    return __tmp;
  }


  public void preValidate(HttpServletRequest request,AccesForm accesForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,AccesForm accesForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, AccesFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, AccesFilterForm filterForm,  List<Acces> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, AccesForm accesForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, AccesForm accesForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long accesID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long accesID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "accesFormWebDB";
  }

  public String getTileList() {
    return "accesListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "AccesWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public AccesJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long accesID) throws I18NException {
    return (AccesJPA) accesEjb.findByPrimaryKey(accesID);
  }


  public AccesJPA create(HttpServletRequest request, AccesJPA acces)
    throws Exception,I18NException, I18NValidationException {
    return (AccesJPA) accesEjb.create(acces);
  }


  public AccesJPA update(HttpServletRequest request, AccesJPA acces)
    throws Exception,I18NException, I18NValidationException {
    return (AccesJPA) accesEjb.update(acces);
  }


  public void delete(HttpServletRequest request, Acces acces) throws Exception,I18NException {
    accesEjb.delete(acces);
  }

} // Final de Classe

