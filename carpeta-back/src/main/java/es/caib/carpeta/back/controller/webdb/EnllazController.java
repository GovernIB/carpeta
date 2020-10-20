package es.caib.carpeta.back.controller.webdb;

import es.caib.carpeta.back.form.webdb.*;
import es.caib.carpeta.back.validator.webdb.EnllazWebValidator;
import es.caib.carpeta.jpa.EnllazJPA;
import es.caib.carpeta.jpa.FitxerJPA;
import es.caib.carpeta.model.entity.Enllaz;
import es.caib.carpeta.model.entity.Fitxer;
import es.caib.carpeta.model.fields.EnllazFields;
import es.caib.carpeta.model.fields.EntitatFields;
import es.caib.carpeta.model.fields.TraduccioFields;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.GroupByItem;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.utils.Utils;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller per gestionar un Enllaz
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/enllaz")
@SessionAttributes(types = { EnllazForm.class, EnllazFilterForm.class })
public class EnllazController
    extends es.caib.carpeta.back.controller.CarpetaFilesBaseController<Enllaz, java.lang.Long, EnllazForm> implements EnllazFields {

  @EJB(mappedName = es.caib.carpeta.ejb.IdiomaLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.IdiomaLocal idiomaEjb;

  @EJB(mappedName = es.caib.carpeta.ejb.EnllazLocal.JNDI_NAME)
  protected es.caib.carpeta.ejb.EnllazLocal enllazEjb;

  @Autowired
  private EnllazWebValidator enllazWebValidator;

  @Autowired
  protected EnllazRefList enllazRefList;

  // References 
  @Autowired
  protected TraduccioRefList traduccioRefList;

  // References 
  @Autowired
  protected EntitatRefList entitatRefList;

  /**
   * Llistat de totes Enllaz
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    EnllazFilterForm ff;
    ff = (EnllazFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Enllaz de forma paginada
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
    llistat(mav, request, getEnllazFilterForm(pagina, mav, request));
    return mav;
  }

  public EnllazFilterForm getEnllazFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    EnllazFilterForm enllazFilterForm;
    enllazFilterForm = (EnllazFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(enllazFilterForm == null) {
      enllazFilterForm = new EnllazFilterForm();
      enllazFilterForm.setContexte(getContextWeb());
      enllazFilterForm.setEntityNameCode(getEntityNameCode());
      enllazFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      enllazFilterForm.setNou(true);
    } else {
      enllazFilterForm.setNou(false);
    }
    enllazFilterForm.setPage(pagina == null ? 1 : pagina);
    return enllazFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Enllaz de forma paginada
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
      @ModelAttribute EnllazFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getEnllazFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Enllaz de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Enllaz> llistat(ModelAndView mav, HttpServletRequest request,
     EnllazFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Enllaz> enllaz = processarLlistat(enllazEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("enllazItems", enllaz);

    mav.addObject("enllazFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, enllaz, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, enllaz);

    return enllaz;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(EnllazFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Enllaz> list, List<GroupByItem> groupItems) throws I18NException {
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

    // Field nomID
    {
      _listSKV = getReferenceListForNomID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTraduccioForNomID(_tmp);
      if (filterForm.getGroupByFields().contains(NOMID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, NOMID, false);
      };
    }

    // Field urlID
    {
      _listSKV = getReferenceListForUrlID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTraduccioForUrlID(_tmp);
      if (filterForm.getGroupByFields().contains(URLID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, URLID, false);
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


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    EnllazFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Enllaz> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_ENLLAZ_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(TIPUS, filterForm.getMapOfValuesForTipus());
    __mapping.put(NOMID, filterForm.getMapOfTraduccioForNomID());
    __mapping.put(URLID, filterForm.getMapOfTraduccioForUrlID());
    __mapping.put(ENTITATID, filterForm.getMapOfEntitatForEntitatID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Enllaz
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearEnllazGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    EnllazForm enllazForm = getEnllazForm(null, false, request, mav);
    
    if (enllazForm.getEnllaz().getNom() == null){
      es.caib.carpeta.jpa.TraduccioJPA trad = new es.caib.carpeta.jpa.TraduccioJPA();
      for (es.caib.carpeta.model.entity.Idioma idioma : enllazForm.getIdiomesTraduccio()) {
        trad.addTraduccio(idioma.getIdiomaID(), new es.caib.carpeta.jpa.TraduccioMapJPA());
      }
      enllazForm.getEnllaz().setNom(trad);
    }

    
    if (enllazForm.getEnllaz().getUrl() == null){
      es.caib.carpeta.jpa.TraduccioJPA trad = new es.caib.carpeta.jpa.TraduccioJPA();
      for (es.caib.carpeta.model.entity.Idioma idioma : enllazForm.getIdiomesTraduccio()) {
        trad.addTraduccio(idioma.getIdiomaID(), new es.caib.carpeta.jpa.TraduccioMapJPA());
      }
      enllazForm.getEnllaz().setUrl(trad);
    }

    mav.addObject("enllazForm" ,enllazForm);
    fillReferencesForForm(enllazForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public EnllazForm getEnllazForm(EnllazJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    EnllazForm enllazForm;
    if(_jpa == null) {
      enllazForm = new EnllazForm(new EnllazJPA(), true);
    } else {
      enllazForm = new EnllazForm(_jpa, false);
      enllazForm.setView(__isView);
    }
    enllazForm.setContexte(getContextWeb());
    enllazForm.setEntityNameCode(getEntityNameCode());
    enllazForm.setEntityNameCodePlural(getEntityNameCodePlural());
    enllazForm.setIdiomesTraduccio(getIdiomesSuportats());
    return enllazForm;
  }

  public void fillReferencesForForm(EnllazForm enllazForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (enllazForm.getListOfValuesForTipus() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipus(request, mav, enllazForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      enllazForm.setListOfValuesForTipus(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (enllazForm.getListOfEntitatForEntitatID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEntitatID(request, mav, enllazForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      enllazForm.setListOfEntitatForEntitatID(_listSKV);
    }
    
  }


  public List<es.caib.carpeta.model.entity.Idioma> getIdiomesSuportats() throws I18NException {
    List<es.caib.carpeta.model.entity.Idioma> idiomes = idiomaEjb.select(es.caib.carpeta.model.fields.IdiomaFields.SUPORTAT.equal(true));
    return idiomes;
  }


  /**
   * Guardar un nou Enllaz
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearEnllazPost(@ModelAttribute EnllazForm enllazForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    EnllazJPA enllaz = enllazForm.getEnllaz();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE

    try {
      this.setFilesFormToEntity(afm, enllaz, enllazForm); // FILE
      preValidate(request, enllazForm, result);
      getWebValidator().validate(enllazForm, result);
      postValidate(request,enllazForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        enllaz = create(request, enllaz);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.creation", enllaz.getEnllazID());
        enllazForm.setEnllaz(enllaz);
        return getRedirectWhenCreated(request, enllazForm);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.creation", null, __e);
      log.error(msg, __e);
      return getTileForm();
    }
  }

  @RequestMapping(value = "/view/{enllazID}", method = RequestMethod.GET)
  public ModelAndView veureEnllazGet(@PathVariable("enllazID") java.lang.Long enllazID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewEnllazGet(enllazID,
        request, response, true);
  }


  protected ModelAndView editAndViewEnllazGet(@PathVariable("enllazID") java.lang.Long enllazID,
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
    EnllazJPA enllaz = findByPrimaryKey(request, enllazID);

    if (enllaz == null) {
      createMessageWarning(request, "error.notfound", enllazID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, enllazID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      EnllazForm enllazForm = getEnllazForm(enllaz, __isView, request, mav);
      enllazForm.setView(__isView);
      if(__isView) {
        enllazForm.setAllFieldsReadOnly(ALL_ENLLAZ_FIELDS);
        enllazForm.setSaveButtonVisible(false);
        enllazForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(enllazForm, request, mav);
      mav.addObject("enllazForm", enllazForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Enllaz existent
   */
  @RequestMapping(value = "/{enllazID}/edit", method = RequestMethod.GET)
  public ModelAndView editarEnllazGet(@PathVariable("enllazID") java.lang.Long enllazID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewEnllazGet(enllazID,
        request, response, false);
  }



  /**
   * Editar un Enllaz existent
   */
  @RequestMapping(value = "/{enllazID}/edit", method = RequestMethod.POST)
  public String editarEnllazPost(@ModelAttribute EnllazForm enllazForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    EnllazJPA enllaz = enllazForm.getEnllaz();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE
    try {
      this.setFilesFormToEntity(afm, enllaz, enllazForm); // FILE
      preValidate(request, enllazForm, result);
      getWebValidator().validate(enllazForm, result);
      postValidate(request, enllazForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        enllaz = update(request, enllaz);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.modification", enllaz.getEnllazID());
        status.setComplete();
        return getRedirectWhenModified(request, enllazForm, null);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          enllaz.getEnllazID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, enllazForm, __e);
    }

  }


  /**
   * Eliminar un Enllaz existent
   */
  @RequestMapping(value = "/{enllazID}/delete")
  public String eliminarEnllaz(@PathVariable("enllazID") java.lang.Long enllazID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Enllaz enllaz = enllazEjb.findByPrimaryKey(enllazID);
      if (enllaz == null) {
        String __msg =createMessageError(request, "error.notfound", enllazID);
        return getRedirectWhenDelete(request, enllazID, new Exception(__msg));
      } else {
        delete(request, enllaz);
        createMessageSuccess(request, "success.deleted", enllazID);
        return getRedirectWhenDelete(request, enllazID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", enllazID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, enllazID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute EnllazFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarEnllaz(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __enllazID, Throwable e) {
    java.lang.Long enllazID = (java.lang.Long)__enllazID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (enllazID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(enllazID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "enllaz.enllaz";
  }

  public String getEntityNameCodePlural() {
    return "enllaz.enllaz.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("enllaz.enllazID");
  }

  @InitBinder("enllazFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("enllazForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());

    binder.setDisallowedFields("enllazID");

  }

  public EnllazWebValidator getWebValidator() {
    return enllazWebValidator;
  }


  public void setWebValidator(EnllazWebValidator __val) {
    if (__val != null) {
      this.enllazWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Enllaz
   */
  @RequestMapping(value = "/{enllazID}/cancel")
  public String cancelEnllaz(@PathVariable("enllazID") java.lang.Long enllazID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, enllazID);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // FILE
  @Override
  public void setFilesFormToEntity(FilesFormManager<Fitxer> afm, Enllaz enllaz,
      EnllazForm form) throws I18NException {

    FitxerJPA f;
    f = (FitxerJPA)afm.preProcessFile(form.getLogoID(), form.isLogoIDDelete(),
        form.isNou()? null : enllaz.getLogo());
    ((EnllazJPA)enllaz).setLogo(f);
    if (f != null) { 
      enllaz.setLogoID(f.getFitxerID());
    } else {
      enllaz.setLogoID(0);
    }

  }

  // FILE
  @Override
  public void deleteFiles(Enllaz enllaz) {
    deleteFile(enllaz.getLogoID());
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
       ModelAndView mav, EnllazForm enllazForm, Where where)  throws I18NException {
    if (enllazForm.isHiddenField(TIPUS)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForTipus(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, EnllazFilterForm enllazFilterForm,
       List<Enllaz> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (enllazFilterForm.isHiddenField(TIPUS)
      && !enllazFilterForm.isGroupByField(TIPUS)) {
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

  public List<StringKeyValue> getReferenceListForNomID(HttpServletRequest request,
       ModelAndView mav, EnllazFilterForm enllazFilterForm,
       List<Enllaz> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (enllazFilterForm.isHiddenField(NOMID)
      && !enllazFilterForm.isGroupByField(NOMID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(NOMID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Enllaz _item : list) {
        _pkList.add(_item.getNomID());
        }
        _w = TraduccioFields.TRADUCCIOID.in(_pkList);
      }
    return getReferenceListForNomID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForNomID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return traduccioRefList.getReferenceList(TraduccioFields.TRADUCCIOID, where );
  }

  public List<StringKeyValue> getReferenceListForUrlID(HttpServletRequest request,
       ModelAndView mav, EnllazFilterForm enllazFilterForm,
       List<Enllaz> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (enllazFilterForm.isHiddenField(URLID)
      && !enllazFilterForm.isGroupByField(URLID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(URLID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Enllaz _item : list) {
        _pkList.add(_item.getUrlID());
        }
        _w = TraduccioFields.TRADUCCIOID.in(_pkList);
      }
    return getReferenceListForUrlID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForUrlID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return traduccioRefList.getReferenceList(TraduccioFields.TRADUCCIOID, where );
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, EnllazForm enllazForm, Where where)  throws I18NException {
    if (enllazForm.isHiddenField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (enllazForm.isReadOnlyField(ENTITATID)) {
      _where = EntitatFields.ENTITATID.equal(enllazForm.getEnllaz().getEntitatID());
    }
    return getReferenceListForEntitatID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForEntitatID(HttpServletRequest request,
       ModelAndView mav, EnllazFilterForm enllazFilterForm,
       List<Enllaz> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (enllazFilterForm.isHiddenField(ENTITATID)
      && !enllazFilterForm.isGroupByField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Enllaz _item : list) {
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


  public void preValidate(HttpServletRequest request,EnllazForm enllazForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,EnllazForm enllazForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, EnllazFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, EnllazFilterForm filterForm,  List<Enllaz> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, EnllazForm enllazForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, EnllazForm enllazForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long enllazID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long enllazID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "enllazFormWebDB";
  }

  public String getTileList() {
    return "enllazListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "EnllazWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public EnllazJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long enllazID) throws I18NException {
    return (EnllazJPA) enllazEjb.findByPrimaryKey(enllazID);
  }


  public EnllazJPA create(HttpServletRequest request, EnllazJPA enllaz)
    throws Exception,I18NException, I18NValidationException {
    return (EnllazJPA) enllazEjb.create(enllaz);
  }


  public EnllazJPA update(HttpServletRequest request, EnllazJPA enllaz)
    throws Exception,I18NException, I18NValidationException {
    return (EnllazJPA) enllazEjb.update(enllaz);
  }


  public void delete(HttpServletRequest request, Enllaz enllaz) throws Exception,I18NException {
    enllazEjb.delete(enllaz);
  }

} // Final de Classe

