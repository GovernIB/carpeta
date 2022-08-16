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
import es.caib.carpeta.back.form.webdb.NotificacioAppForm;

import es.caib.carpeta.back.validator.webdb.NotificacioAppWebValidator;

import es.caib.carpeta.persistence.NotificacioAppJPA;
import es.caib.carpeta.model.entity.NotificacioApp;
import es.caib.carpeta.model.fields.*;

/**
 * Controller per gestionar un NotificacioApp
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/notificacioApp")
@SessionAttributes(types = { NotificacioAppForm.class, NotificacioAppFilterForm.class })
public class NotificacioAppController
    extends es.caib.carpeta.back.controller.CarpetaBaseController<NotificacioApp, java.lang.Long> implements NotificacioAppFields {

  @EJB(mappedName = es.caib.carpeta.ejb.IdiomaService.JNDI_NAME)
  protected es.caib.carpeta.ejb.IdiomaService idiomaEjb;

  @EJB(mappedName = es.caib.carpeta.ejb.NotificacioAppService.JNDI_NAME)
  protected es.caib.carpeta.ejb.NotificacioAppService notificacioAppEjb;

  @Autowired
  private NotificacioAppWebValidator notificacioAppWebValidator;

  @Autowired
  protected NotificacioAppRefList notificacioAppRefList;

  // References 
  @Autowired
  protected TraduccioRefList traduccioRefList;

  // References 
  @Autowired
  protected PluginRefList pluginRefList;

  // References 
  @Autowired
  protected EntitatRefList entitatRefList;

  /**
   * Llistat de totes NotificacioApp
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    NotificacioAppFilterForm ff;
    ff = (NotificacioAppFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar NotificacioApp de forma paginada
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
    llistat(mav, request, getNotificacioAppFilterForm(pagina, mav, request));
    return mav;
  }

  public NotificacioAppFilterForm getNotificacioAppFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    NotificacioAppFilterForm notificacioAppFilterForm;
    notificacioAppFilterForm = (NotificacioAppFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(notificacioAppFilterForm == null) {
      notificacioAppFilterForm = new NotificacioAppFilterForm();
      notificacioAppFilterForm.setContexte(getContextWeb());
      notificacioAppFilterForm.setEntityNameCode(getEntityNameCode());
      notificacioAppFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      notificacioAppFilterForm.setNou(true);
    } else {
      notificacioAppFilterForm.setNou(false);
    }
    notificacioAppFilterForm.setPage(pagina == null ? 1 : pagina);
    return notificacioAppFilterForm;
  }

  /**
   * Segona i següent peticions per llistar NotificacioApp de forma paginada
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
      @ModelAttribute NotificacioAppFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getNotificacioAppFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de NotificacioApp de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<NotificacioApp> llistat(ModelAndView mav, HttpServletRequest request,
     NotificacioAppFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<NotificacioApp> notificacioApp = processarLlistat(notificacioAppEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("notificacioAppItems", notificacioApp);

    mav.addObject("notificacioAppFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, notificacioApp, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, notificacioApp);

    return notificacioApp;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(NotificacioAppFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<NotificacioApp> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field titolID
    {
      _listSKV = getReferenceListForTitolID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTraduccioForTitolID(_tmp);
      if (filterForm.getGroupByFields().contains(TITOLID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TITOLID, false);
      };
    }

    // Field missatgeID
    {
      _listSKV = getReferenceListForMissatgeID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTraduccioForMissatgeID(_tmp);
      if (filterForm.getGroupByFields().contains(MISSATGEID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, MISSATGEID, false);
      };
    }

    // Field frontPluginID
    {
      _listSKV = getReferenceListForFrontPluginID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfPluginForFrontPluginID(_tmp);
      if (filterForm.getGroupByFields().contains(FRONTPLUGINID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, FRONTPLUGINID, false);
      };
    }


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, ACTIVA);

    // Field entitatID
    {
      _listSKV = getReferenceListForEntitatID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfEntitatForEntitatID(_tmp);
      if (filterForm.getGroupByFields().contains(ENTITATID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ENTITATID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    NotificacioAppFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<NotificacioApp> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_NOTIFICACIOAPP_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(TITOLID, filterForm.getMapOfTraduccioForTitolID());
    __mapping.put(MISSATGEID, filterForm.getMapOfTraduccioForMissatgeID());
    __mapping.put(FRONTPLUGINID, filterForm.getMapOfPluginForFrontPluginID());
    __mapping.put(ENTITATID, filterForm.getMapOfEntitatForEntitatID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou NotificacioApp
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearNotificacioAppGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    NotificacioAppForm notificacioAppForm = getNotificacioAppForm(null, false, request, mav);
    
    if (notificacioAppForm.getNotificacioApp().getTitol() == null){
      es.caib.carpeta.persistence.TraduccioJPA trad = new es.caib.carpeta.persistence.TraduccioJPA();
      for (es.caib.carpeta.model.entity.Idioma idioma : notificacioAppForm.getIdiomesTraduccio()) {
        trad.addTraduccio(idioma.getIdiomaID(), new es.caib.carpeta.persistence.TraduccioMapJPA());
      }
      notificacioAppForm.getNotificacioApp().setTitol(trad);
    }

    
    if (notificacioAppForm.getNotificacioApp().getMissatge() == null){
      es.caib.carpeta.persistence.TraduccioJPA trad = new es.caib.carpeta.persistence.TraduccioJPA();
      for (es.caib.carpeta.model.entity.Idioma idioma : notificacioAppForm.getIdiomesTraduccio()) {
        trad.addTraduccio(idioma.getIdiomaID(), new es.caib.carpeta.persistence.TraduccioMapJPA());
      }
      notificacioAppForm.getNotificacioApp().setMissatge(trad);
    }

    mav.addObject("notificacioAppForm" ,notificacioAppForm);
    fillReferencesForForm(notificacioAppForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public NotificacioAppForm getNotificacioAppForm(NotificacioAppJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    NotificacioAppForm notificacioAppForm;
    if(_jpa == null) {
      notificacioAppForm = new NotificacioAppForm(new NotificacioAppJPA(), true);
    } else {
      notificacioAppForm = new NotificacioAppForm(_jpa, false);
      notificacioAppForm.setView(__isView);
    }
    notificacioAppForm.setContexte(getContextWeb());
    notificacioAppForm.setEntityNameCode(getEntityNameCode());
    notificacioAppForm.setEntityNameCodePlural(getEntityNameCodePlural());
    notificacioAppForm.setIdiomesTraduccio(getIdiomesSuportats());
    return notificacioAppForm;
  }

  public void fillReferencesForForm(NotificacioAppForm notificacioAppForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (notificacioAppForm.getListOfPluginForFrontPluginID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForFrontPluginID(request, mav, notificacioAppForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      notificacioAppForm.setListOfPluginForFrontPluginID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (notificacioAppForm.getListOfEntitatForEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEntitatID(request, mav, notificacioAppForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      notificacioAppForm.setListOfEntitatForEntitatID(_listSKV);
    }
    
  }


  public List<es.caib.carpeta.model.entity.Idioma> getIdiomesSuportats() throws I18NException {
    List<es.caib.carpeta.model.entity.Idioma> idiomes = idiomaEjb.select(es.caib.carpeta.model.fields.IdiomaFields.SUPORTAT.equal(true));
    return idiomes;
  }


  /**
   * Guardar un nou NotificacioApp
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearNotificacioAppPost(@ModelAttribute NotificacioAppForm notificacioAppForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    NotificacioAppJPA notificacioApp = notificacioAppForm.getNotificacioApp();

    try {
      preValidate(request, notificacioAppForm, result);
      getWebValidator().validate(notificacioAppForm, result);
      postValidate(request,notificacioAppForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        notificacioApp = create(request, notificacioApp);
        createMessageSuccess(request, "success.creation", notificacioApp.getNotificacioAppID());
        notificacioAppForm.setNotificacioApp(notificacioApp);
        return getRedirectWhenCreated(request, notificacioAppForm);
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

  @RequestMapping(value = "/view/{notificacioAppID}", method = RequestMethod.GET)
  public ModelAndView veureNotificacioAppGet(@PathVariable("notificacioAppID") java.lang.Long notificacioAppID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewNotificacioAppGet(notificacioAppID,
        request, response, true);
  }


  protected ModelAndView editAndViewNotificacioAppGet(@PathVariable("notificacioAppID") java.lang.Long notificacioAppID,
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
    NotificacioAppJPA notificacioApp = findByPrimaryKey(request, notificacioAppID);

    if (notificacioApp == null) {
      createMessageWarning(request, "error.notfound", notificacioAppID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, notificacioAppID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      NotificacioAppForm notificacioAppForm = getNotificacioAppForm(notificacioApp, __isView, request, mav);
      notificacioAppForm.setView(__isView);
      if(__isView) {
        notificacioAppForm.setAllFieldsReadOnly(ALL_NOTIFICACIOAPP_FIELDS);
        notificacioAppForm.setSaveButtonVisible(false);
        notificacioAppForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(notificacioAppForm, request, mav);
      mav.addObject("notificacioAppForm", notificacioAppForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un NotificacioApp existent
   */
  @RequestMapping(value = "/{notificacioAppID}/edit", method = RequestMethod.GET)
  public ModelAndView editarNotificacioAppGet(@PathVariable("notificacioAppID") java.lang.Long notificacioAppID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewNotificacioAppGet(notificacioAppID,
        request, response, false);
  }



  /**
   * Editar un NotificacioApp existent
   */
  @RequestMapping(value = "/{notificacioAppID}/edit", method = RequestMethod.POST)
  public String editarNotificacioAppPost(@ModelAttribute NotificacioAppForm notificacioAppForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    NotificacioAppJPA notificacioApp = notificacioAppForm.getNotificacioApp();

    try {
      preValidate(request, notificacioAppForm, result);
      getWebValidator().validate(notificacioAppForm, result);
      postValidate(request, notificacioAppForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        notificacioApp = update(request, notificacioApp);
        createMessageSuccess(request, "success.modification", notificacioApp.getNotificacioAppID());
        status.setComplete();
        return getRedirectWhenModified(request, notificacioAppForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          notificacioApp.getNotificacioAppID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, notificacioAppForm, __e);
    }

  }


  /**
   * Eliminar un NotificacioApp existent
   */
  @RequestMapping(value = "/{notificacioAppID}/delete")
  public String eliminarNotificacioApp(@PathVariable("notificacioAppID") java.lang.Long notificacioAppID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      NotificacioApp notificacioApp = notificacioAppEjb.findByPrimaryKey(notificacioAppID);
      if (notificacioApp == null) {
        String __msg =createMessageError(request, "error.notfound", notificacioAppID);
        return getRedirectWhenDelete(request, notificacioAppID, new Exception(__msg));
      } else {
        delete(request, notificacioApp);
        createMessageSuccess(request, "success.deleted", notificacioAppID);
        return getRedirectWhenDelete(request, notificacioAppID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", notificacioAppID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, notificacioAppID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute NotificacioAppFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarNotificacioApp(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __notificacioAppID, Throwable e) {
    java.lang.Long notificacioAppID = (java.lang.Long)__notificacioAppID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (notificacioAppID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(notificacioAppID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "notificacioApp.notificacioApp";
  }

  public String getEntityNameCodePlural() {
    return "notificacioApp.notificacioApp.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("notificacioApp.notificacioAppID");
  }

  @InitBinder("notificacioAppFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("notificacioAppForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "notificacioApp.notificacioAppID");
  }

  public NotificacioAppWebValidator getWebValidator() {
    return notificacioAppWebValidator;
  }


  public void setWebValidator(NotificacioAppWebValidator __val) {
    if (__val != null) {
      this.notificacioAppWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de NotificacioApp
   */
  @RequestMapping(value = "/{notificacioAppID}/cancel")
  public String cancelNotificacioApp(@PathVariable("notificacioAppID") java.lang.Long notificacioAppID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, notificacioAppID);
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

  public List<StringKeyValue> getReferenceListForTitolID(HttpServletRequest request,
       ModelAndView mav, NotificacioAppFilterForm notificacioAppFilterForm,
       List<NotificacioApp> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (notificacioAppFilterForm.isHiddenField(TITOLID)
      && !notificacioAppFilterForm.isGroupByField(TITOLID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(TITOLID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (NotificacioApp _item : list) {
        _pkList.add(_item.getTitolID());
        }
        _w = TraduccioFields.TRADUCCIOID.in(_pkList);
      }
    return getReferenceListForTitolID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForTitolID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return traduccioRefList.getReferenceList(TraduccioFields.TRADUCCIOID, where );
  }

  public List<StringKeyValue> getReferenceListForMissatgeID(HttpServletRequest request,
       ModelAndView mav, NotificacioAppFilterForm notificacioAppFilterForm,
       List<NotificacioApp> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (notificacioAppFilterForm.isHiddenField(MISSATGEID)
      && !notificacioAppFilterForm.isGroupByField(MISSATGEID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(MISSATGEID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (NotificacioApp _item : list) {
        _pkList.add(_item.getMissatgeID());
        }
        _w = TraduccioFields.TRADUCCIOID.in(_pkList);
      }
    return getReferenceListForMissatgeID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForMissatgeID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return traduccioRefList.getReferenceList(TraduccioFields.TRADUCCIOID, where );
  }


  public List<StringKeyValue> getReferenceListForFrontPluginID(HttpServletRequest request,
       ModelAndView mav, NotificacioAppForm notificacioAppForm, Where where)  throws I18NException {
    if (notificacioAppForm.isHiddenField(FRONTPLUGINID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (notificacioAppForm.isReadOnlyField(FRONTPLUGINID)) {
      _where = PluginFields.PLUGINID.equal(notificacioAppForm.getNotificacioApp().getFrontPluginID());
    }
    return getReferenceListForFrontPluginID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForFrontPluginID(HttpServletRequest request,
       ModelAndView mav, NotificacioAppFilterForm notificacioAppFilterForm,
       List<NotificacioApp> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (notificacioAppFilterForm.isHiddenField(FRONTPLUGINID)
      && !notificacioAppFilterForm.isGroupByField(FRONTPLUGINID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(FRONTPLUGINID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (NotificacioApp _item : list) {
        if(_item.getFrontPluginID() == null) { continue; };
        _pkList.add(_item.getFrontPluginID());
        }
        _w = PluginFields.PLUGINID.in(_pkList);
      }
    return getReferenceListForFrontPluginID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForFrontPluginID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return pluginRefList.getReferenceList(PluginFields.PLUGINID, where );
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, NotificacioAppForm notificacioAppForm, Where where)  throws I18NException {
    if (notificacioAppForm.isHiddenField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (notificacioAppForm.isReadOnlyField(ENTITATID)) {
      _where = EntitatFields.ENTITATID.equal(notificacioAppForm.getNotificacioApp().getEntitatID());
    }
    return getReferenceListForEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, NotificacioAppFilterForm notificacioAppFilterForm,
       List<NotificacioApp> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (notificacioAppFilterForm.isHiddenField(ENTITATID)
      && !notificacioAppFilterForm.isGroupByField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (NotificacioApp _item : list) {
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


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,NotificacioAppForm notificacioAppForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,NotificacioAppForm notificacioAppForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, NotificacioAppFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, NotificacioAppFilterForm filterForm,  List<NotificacioApp> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, NotificacioAppForm notificacioAppForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, NotificacioAppForm notificacioAppForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long notificacioAppID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long notificacioAppID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "notificacioAppFormWebDB";
  }

  public String getTileList() {
    return "notificacioAppListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "NotificacioAppWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public NotificacioAppJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long notificacioAppID) throws I18NException {
    return (NotificacioAppJPA) notificacioAppEjb.findByPrimaryKey(notificacioAppID);
  }


  public NotificacioAppJPA create(HttpServletRequest request, NotificacioAppJPA notificacioApp)
    throws I18NException, I18NValidationException {
    return (NotificacioAppJPA) notificacioAppEjb.create(notificacioApp);
  }


  public NotificacioAppJPA update(HttpServletRequest request, NotificacioAppJPA notificacioApp)
    throws I18NException, I18NValidationException {
    return (NotificacioAppJPA) notificacioAppEjb.update(notificacioApp);
  }


  public void delete(HttpServletRequest request, NotificacioApp notificacioApp) throws I18NException {
    notificacioAppEjb.delete(notificacioApp);
  }

} // Final de Classe

