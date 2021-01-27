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
import es.caib.carpeta.back.form.webdb.AvisForm;

import es.caib.carpeta.back.validator.webdb.AvisWebValidator;

import es.caib.carpeta.persistence.AvisJPA;
import es.caib.carpeta.model.entity.Avis;
import es.caib.carpeta.model.fields.*;

/**
 * Controller per gestionar un Avis
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/avis")
@SessionAttributes(types = { AvisForm.class, AvisFilterForm.class })
public class AvisController
    extends es.caib.carpeta.back.controller.CarpetaBaseController<Avis, java.lang.Long> implements AvisFields {

  @EJB(mappedName = es.caib.carpeta.ejb.IdiomaService.JNDI_NAME)
  protected es.caib.carpeta.ejb.IdiomaService idiomaEjb;

  @EJB(mappedName = es.caib.carpeta.ejb.AvisService.JNDI_NAME)
  protected es.caib.carpeta.ejb.AvisService avisEjb;

  @Autowired
  private AvisWebValidator avisWebValidator;

  @Autowired
  protected AvisRefList avisRefList;

  // References 
  @Autowired
  protected TraduccioRefList traduccioRefList;

  // References 
  @Autowired
  protected EntitatRefList entitatRefList;

  // References 
  @Autowired
  protected PluginRefList pluginRefList;

  /**
   * Llistat de totes Avis
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    AvisFilterForm ff;
    ff = (AvisFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Avis de forma paginada
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
    llistat(mav, request, getAvisFilterForm(pagina, mav, request));
    return mav;
  }

  public AvisFilterForm getAvisFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    AvisFilterForm avisFilterForm;
    avisFilterForm = (AvisFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(avisFilterForm == null) {
      avisFilterForm = new AvisFilterForm();
      avisFilterForm.setContexte(getContextWeb());
      avisFilterForm.setEntityNameCode(getEntityNameCode());
      avisFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      avisFilterForm.setNou(true);
    } else {
      avisFilterForm.setNou(false);
    }
    avisFilterForm.setPage(pagina == null ? 1 : pagina);
    return avisFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Avis de forma paginada
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
      @ModelAttribute AvisFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getAvisFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Avis de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Avis> llistat(ModelAndView mav, HttpServletRequest request,
     AvisFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Avis> avis = processarLlistat(avisEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("avisItems", avis);

    mav.addObject("avisFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, avis, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, avis);

    return avis;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(AvisFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Avis> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field descripcioID
    {
      _listSKV = getReferenceListForDescripcioID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTraduccioForDescripcioID(_tmp);
      if (filterForm.getGroupByFields().contains(DESCRIPCIOID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, DESCRIPCIOID, false);
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

    // Field tipus
    {
      _listSKV = getReferenceListForTipus(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForTipus(_tmp);
      if (filterForm.getGroupByFields().contains(TIPUS)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TIPUS, false);
      };
    }

    // Field gravetat
    {
      _listSKV = getReferenceListForGravetat(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForGravetat(_tmp);
      if (filterForm.getGroupByFields().contains(GRAVETAT)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, GRAVETAT, false);
      };
    }

    // Field pluginFrontID
    {
      _listSKV = getReferenceListForPluginFrontID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfPluginForPluginFrontID(_tmp);
      if (filterForm.getGroupByFields().contains(PLUGINFRONTID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, PLUGINFRONTID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    AvisFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Avis> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_AVIS_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(DESCRIPCIOID, filterForm.getMapOfTraduccioForDescripcioID());
    __mapping.put(ENTITATID, filterForm.getMapOfEntitatForEntitatID());
    __mapping.put(TIPUS, filterForm.getMapOfValuesForTipus());
    __mapping.put(GRAVETAT, filterForm.getMapOfValuesForGravetat());
    __mapping.put(PLUGINFRONTID, filterForm.getMapOfPluginForPluginFrontID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Avis
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearAvisGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    AvisForm avisForm = getAvisForm(null, false, request, mav);
    
    if (avisForm.getAvis().getDescripcio() == null){
      es.caib.carpeta.persistence.TraduccioJPA trad = new es.caib.carpeta.persistence.TraduccioJPA();
      for (es.caib.carpeta.model.entity.Idioma idioma : avisForm.getIdiomesTraduccio()) {
        trad.addTraduccio(idioma.getIdiomaID(), new es.caib.carpeta.persistence.TraduccioMapJPA());
      }
      avisForm.getAvis().setDescripcio(trad);
    }

    mav.addObject("avisForm" ,avisForm);
    fillReferencesForForm(avisForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public AvisForm getAvisForm(AvisJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    AvisForm avisForm;
    if(_jpa == null) {
      avisForm = new AvisForm(new AvisJPA(), true);
    } else {
      avisForm = new AvisForm(_jpa, false);
      avisForm.setView(__isView);
    }
    avisForm.setContexte(getContextWeb());
    avisForm.setEntityNameCode(getEntityNameCode());
    avisForm.setEntityNameCodePlural(getEntityNameCodePlural());
    avisForm.setIdiomesTraduccio(getIdiomesSuportats());
    return avisForm;
  }

  public void fillReferencesForForm(AvisForm avisForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (avisForm.getListOfEntitatForEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEntitatID(request, mav, avisForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      avisForm.setListOfEntitatForEntitatID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (avisForm.getListOfValuesForTipus() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipus(request, mav, avisForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      avisForm.setListOfValuesForTipus(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (avisForm.getListOfValuesForGravetat() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForGravetat(request, mav, avisForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      avisForm.setListOfValuesForGravetat(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (avisForm.getListOfPluginForPluginFrontID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForPluginFrontID(request, mav, avisForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      avisForm.setListOfPluginForPluginFrontID(_listSKV);
    }
    
  }


  public List<es.caib.carpeta.model.entity.Idioma> getIdiomesSuportats() throws I18NException {
    List<es.caib.carpeta.model.entity.Idioma> idiomes = idiomaEjb.select(es.caib.carpeta.model.fields.IdiomaFields.SUPORTAT.equal(true));
    return idiomes;
  }


  /**
   * Guardar un nou Avis
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearAvisPost(@ModelAttribute AvisForm avisForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    AvisJPA avis = avisForm.getAvis();

    try {
      preValidate(request, avisForm, result);
      getWebValidator().validate(avisForm, result);
      postValidate(request,avisForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        avis = create(request, avis);
        createMessageSuccess(request, "success.creation", avis.getAvisID());
        avisForm.setAvis(avis);
        return getRedirectWhenCreated(request, avisForm);
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

  @RequestMapping(value = "/view/{avisID}", method = RequestMethod.GET)
  public ModelAndView veureAvisGet(@PathVariable("avisID") java.lang.Long avisID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewAvisGet(avisID,
        request, response, true);
  }


  protected ModelAndView editAndViewAvisGet(@PathVariable("avisID") java.lang.Long avisID,
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
    AvisJPA avis = findByPrimaryKey(request, avisID);

    if (avis == null) {
      createMessageWarning(request, "error.notfound", avisID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, avisID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      AvisForm avisForm = getAvisForm(avis, __isView, request, mav);
      avisForm.setView(__isView);
      if(__isView) {
        avisForm.setAllFieldsReadOnly(ALL_AVIS_FIELDS);
        avisForm.setSaveButtonVisible(false);
        avisForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(avisForm, request, mav);
      mav.addObject("avisForm", avisForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Avis existent
   */
  @RequestMapping(value = "/{avisID}/edit", method = RequestMethod.GET)
  public ModelAndView editarAvisGet(@PathVariable("avisID") java.lang.Long avisID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewAvisGet(avisID,
        request, response, false);
  }



  /**
   * Editar un Avis existent
   */
  @RequestMapping(value = "/{avisID}/edit", method = RequestMethod.POST)
  public String editarAvisPost(@ModelAttribute AvisForm avisForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    AvisJPA avis = avisForm.getAvis();

    try {
      preValidate(request, avisForm, result);
      getWebValidator().validate(avisForm, result);
      postValidate(request, avisForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        avis = update(request, avis);
        createMessageSuccess(request, "success.modification", avis.getAvisID());
        status.setComplete();
        return getRedirectWhenModified(request, avisForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          avis.getAvisID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, avisForm, __e);
    }

  }


  /**
   * Eliminar un Avis existent
   */
  @RequestMapping(value = "/{avisID}/delete")
  public String eliminarAvis(@PathVariable("avisID") java.lang.Long avisID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Avis avis = avisEjb.findByPrimaryKey(avisID);
      if (avis == null) {
        String __msg =createMessageError(request, "error.notfound", avisID);
        return getRedirectWhenDelete(request, avisID, new Exception(__msg));
      } else {
        delete(request, avis);
        createMessageSuccess(request, "success.deleted", avisID);
        return getRedirectWhenDelete(request, avisID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", avisID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, avisID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute AvisFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarAvis(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __avisID, Throwable e) {
    java.lang.Long avisID = (java.lang.Long)__avisID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (avisID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(avisID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "avis.avis";
  }

  public String getEntityNameCodePlural() {
    return "avis.avis.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("avis.avisID");
  }

  @InitBinder("avisFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("avisForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    binder.setDisallowedFields("avisID");

  }

  public AvisWebValidator getWebValidator() {
    return avisWebValidator;
  }


  public void setWebValidator(AvisWebValidator __val) {
    if (__val != null) {
      this.avisWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Avis
   */
  @RequestMapping(value = "/{avisID}/cancel")
  public String cancelAvis(@PathVariable("avisID") java.lang.Long avisID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, avisID);
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

  public List<StringKeyValue> getReferenceListForDescripcioID(HttpServletRequest request,
       ModelAndView mav, AvisFilterForm avisFilterForm,
       List<Avis> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (avisFilterForm.isHiddenField(DESCRIPCIOID)
      && !avisFilterForm.isGroupByField(DESCRIPCIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(DESCRIPCIOID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Avis _item : list) {
        _pkList.add(_item.getDescripcioID());
        }
        _w = TraduccioFields.TRADUCCIOID.in(_pkList);
      }
    return getReferenceListForDescripcioID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForDescripcioID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return traduccioRefList.getReferenceList(TraduccioFields.TRADUCCIOID, where );
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, AvisForm avisForm, Where where)  throws I18NException {
    if (avisForm.isHiddenField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (avisForm.isReadOnlyField(ENTITATID)) {
      _where = EntitatFields.ENTITATID.equal(avisForm.getAvis().getEntitatID());
    }
    return getReferenceListForEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, AvisFilterForm avisFilterForm,
       List<Avis> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (avisFilterForm.isHiddenField(ENTITATID)
      && !avisFilterForm.isGroupByField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Avis _item : list) {
        if(_item.getEntitatID() == null) { continue; };
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


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, AvisForm avisForm, Where where)  throws I18NException {
    if (avisForm.isHiddenField(TIPUS)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForTipus(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, AvisFilterForm avisFilterForm,
       List<Avis> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (avisFilterForm.isHiddenField(TIPUS)
      && !avisFilterForm.isGroupByField(TIPUS)) {
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


  public List<StringKeyValue> getReferenceListForGravetat(HttpServletRequest request,
       ModelAndView mav, AvisForm avisForm, Where where)  throws I18NException {
    if (avisForm.isHiddenField(GRAVETAT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForGravetat(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForGravetat(HttpServletRequest request,
       ModelAndView mav, AvisFilterForm avisFilterForm,
       List<Avis> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (avisFilterForm.isHiddenField(GRAVETAT)
      && !avisFilterForm.isGroupByField(GRAVETAT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForGravetat(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForGravetat(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForPluginFrontID(HttpServletRequest request,
       ModelAndView mav, AvisForm avisForm, Where where)  throws I18NException {
    if (avisForm.isHiddenField(PLUGINFRONTID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (avisForm.isReadOnlyField(PLUGINFRONTID)) {
      _where = PluginFields.PLUGINID.equal(avisForm.getAvis().getPluginFrontID());
    }
    return getReferenceListForPluginFrontID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForPluginFrontID(HttpServletRequest request,
       ModelAndView mav, AvisFilterForm avisFilterForm,
       List<Avis> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (avisFilterForm.isHiddenField(PLUGINFRONTID)
      && !avisFilterForm.isGroupByField(PLUGINFRONTID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(PLUGINFRONTID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Avis _item : list) {
        if(_item.getPluginFrontID() == null) { continue; };
        _pkList.add(_item.getPluginFrontID());
        }
        _w = PluginFields.PLUGINID.in(_pkList);
      }
    return getReferenceListForPluginFrontID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForPluginFrontID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return pluginRefList.getReferenceList(PluginFields.PLUGINID, where );
  }


  public void preValidate(HttpServletRequest request,AvisForm avisForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,AvisForm avisForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, AvisFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, AvisFilterForm filterForm,  List<Avis> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, AvisForm avisForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, AvisForm avisForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long avisID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long avisID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "avisFormWebDB";
  }

  public String getTileList() {
    return "avisListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "AvisWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public AvisJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long avisID) throws I18NException {
    return (AvisJPA) avisEjb.findByPrimaryKey(avisID);
  }


  public AvisJPA create(HttpServletRequest request, AvisJPA avis)
    throws Exception,I18NException, I18NValidationException {
    return (AvisJPA) avisEjb.create(avis);
  }


  public AvisJPA update(HttpServletRequest request, AvisJPA avis)
    throws Exception,I18NException, I18NValidationException {
    return (AvisJPA) avisEjb.update(avis);
  }


  public void delete(HttpServletRequest request, Avis avis) throws Exception,I18NException {
    avisEjb.delete(avis);
  }

} // Final de Classe

