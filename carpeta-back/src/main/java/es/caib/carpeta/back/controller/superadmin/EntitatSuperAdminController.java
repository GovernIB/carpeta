package es.caib.carpeta.back.controller.superadmin;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import es.caib.carpeta.model.entity.Fitxer;
import es.caib.carpeta.model.entity.PluginEntitat;
import es.caib.carpeta.persistence.FitxerJPA;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemHeaders;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.ITableManager;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import es.caib.carpeta.back.controller.webdb.EntitatController;
import es.caib.carpeta.back.form.webdb.EntitatFilterForm;
import es.caib.carpeta.back.form.webdb.EntitatForm;
import es.caib.carpeta.persistence.EntitatJPA;
import es.caib.carpeta.logic.EntitatLogicaService;
import es.caib.carpeta.model.entity.Entitat;
import es.caib.carpeta.model.fields.EntitatFields;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/superadmin/entitat")
@SessionAttributes(types = { EntitatForm.class, EntitatFilterForm.class })
public class EntitatSuperAdminController extends EntitatController {

    @EJB(mappedName = EntitatLogicaService.JNDI_NAME)
    protected EntitatLogicaService entitatLogicaEjb;

    public boolean isSuperAdmin(){
        return true;
    }

    @Override
    public String getTileForm() {
        return isSuperAdmin()?"entitatFormSuperAdmin":"entitatFormAdminEntitat";
    }

    @Override
    public String getTileList() {
        return isSuperAdmin()?"entitatListSuperAdmin":"entitatListAdminEntitat";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "Entitat" + (isSuperAdmin()?"SuperAdmnin" : "AdminEntitat") + "_FilterForm";
    }

    @Override
    public EntitatFilterForm getEntitatFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        EntitatFilterForm entitatFilterForm = super.getEntitatFilterForm(pagina, mav, request);

        if (entitatFilterForm.isNou()) {

            Set<Field<?>> hiddenFields = new HashSet<Field<?>>(
                    Arrays.asList(EntitatFields.ALL_ENTITAT_FIELDS));

            hiddenFields.remove(EntitatFields.NOMID);
            hiddenFields.remove(EntitatFields.CODIDIR3);

            entitatFilterForm.setHiddenFields(hiddenFields);

        }

        return entitatFilterForm;

    }

    @Override
    public EntitatForm getEntitatForm(EntitatJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        EntitatForm entitatForm = super.getEntitatForm(_jpa, __isView, request, mav);

        entitatForm.addHiddenField(PLUGINLOGINID);
        // entitatForm.addHiddenField(LOGINTEXTID);
        entitatForm.addHiddenField(CONTEXT);
        entitatForm.addHiddenField(COMMIT);

        // Valors de camps de text per defecte
        if(entitatForm.isNou()){
            entitatForm.getEntitat().setColorMenu("888888");
            entitatForm.getEntitat().setWebEntitat("https://www.webentitat.es");
            entitatForm.getEntitat().setEntitatDescFront("<p><a href=\"http://www.weborganigrama.com\" tabindex=\"601\">Nom Organisme</a><a href=\"http://www.webplanol.es\" tabindex=\"602\">: Adreça - CP Localitat</a></p><p></p>Telèfon XXXXXXXXX - Fax XXXXXXXXX<p></p>");
        }

        if(isSuperAdmin()) {
            // Es lleven aquests camps, només es gestionen des d'ADMIN ENTITAT
            entitatForm.addHiddenField(DESCRIPCIOID);
            entitatForm.addHiddenField(COLORMENU);
            entitatForm.addHiddenField(LOGOCAPBACKID);
            entitatForm.addHiddenField(LOGOPEUBACKID);
            entitatForm.addHiddenField(LOGOLATERALFRONTID);
            entitatForm.addHiddenField(ICONID);
            entitatForm.addHiddenField(WEBENTITAT);
            entitatForm.addHiddenField(ENTITATDESCFRONT);
            entitatForm.addHiddenField(SUPORTWEB);
            entitatForm.addHiddenField(SUPORTTELEFON);
            entitatForm.addHiddenField(SUPORTEMAIL);
            entitatForm.addHiddenField(SUPORTFAQ);
            entitatForm.addHiddenField(SUPORTAUTENTICACIO);
            entitatForm.addHiddenField(SUPORTQSSI);
            entitatForm.addHiddenField(LOGINTEXTID);
            entitatForm.addHiddenField(FITXERCSSID);
            entitatForm.addHiddenField(AVISLEGALCA);
            entitatForm.addHiddenField(AVISLEGALES);
            entitatForm.addHiddenField(ACCESSIBILITATCA);
            entitatForm.addHiddenField(ACCESSIBILITATES);
        }

        return entitatForm;
    }

    @Override
    public void delete(HttpServletRequest request, Entitat entitat)
            throws Exception, I18NException {
        entitatLogicaEjb.deleteFull(entitat);
    }

    @Override
    public void setFilesFormToEntity(FilesFormManager<Fitxer> afm, Entitat entitat,
                                     EntitatForm form) throws I18NException {

        super.setFilesFormToEntity(afm, entitat, form);

        if(isSuperAdmin() && form.isNou()){

            if(entitat.getLogoCapBackID() == 0){
                afegeixImatgeDefecte("logoCapBack.png", "image/png", entitat, form);
            }

            if(entitat.getLogoPeuBackID() == 0){
                afegeixImatgeDefecte("logoPeuBack.png", "image/png", entitat, form);
            }

            if(entitat.getLogoLateralFrontID() == 0){
                afegeixImatgeDefecte("logoLateralFront.png", "image/png", entitat, form);
            }

            if(entitat.getIconID() == 0){
                afegeixImatgeDefecte("icona.png", "image/png", entitat, form);
            }

        }

    }

    public void afegeixImatgeDefecte(String nomImatge, String mimeType, Entitat entitat,
                                     EntitatForm form) throws I18NException {

        InputStream is = getClass().getClassLoader().getResourceAsStream("entityDefaultImages/" + nomImatge);

        try {
            FitxerJPA fitxer = new FitxerJPA(nomImatge, mimeType, is.available(), "");

            fitxerEjb.create(fitxer);
            long fId = fitxer.getFitxerID();
            FileSystemManager.crearFitxer(is,fId);

            switch (nomImatge){
                case "logoCapBack.png":
                    entitat.setLogoCapBackID(fId);
                    form.getEntitat().setLogoCapBack(fitxer);
                    form.setLogoCapBackID(new CommonsMultipartFile(new EmptyFileItem(FileSystemManager.getFile(fId))));
                    break;

                case "logoPeuBack.png":
                    entitat.setLogoPeuBackID(fId);
                    form.getEntitat().setLogoPeuBack(fitxer);
                    form.setLogoPeuBackID(new CommonsMultipartFile(new EmptyFileItem(FileSystemManager.getFile(fId))));
                    break;

                case "logoLateralFront.png":
                    entitat.setLogoLateralFrontID(fId);
                    form.getEntitat().setLogoLateralFront(fitxer);
                    form.setLogoLateralFrontID(new CommonsMultipartFile(new EmptyFileItem(FileSystemManager.getFile(fId))));
                    break;

                case "icona.png":
                    entitat.setIconID(fId);
                    form.getEntitat().setIcon(fitxer);
                    form.setIconID(new CommonsMultipartFile(new EmptyFileItem(FileSystemManager.getFile(fId))));
                    break;
            }

        } catch (IOException e) {
            String msg = "Error creant fitxer " + nomImatge+ ": " + e.getMessage();
            log.error(msg, e);
            throw new I18NException("genapp.comodi", msg);
        }

    }

    public static class EmptyFileItem implements FileItem {

        protected File file;

        public EmptyFileItem(File f){
            this.file = f;
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return new FileInputStream(this.file);
        }

        @Override
        public String getContentType() {
            return "image/png";
        }

        @Override
        public String getName() {
            return file.getName();
        }

        @Override
        public boolean isInMemory() {
            return false;
        }

        @Override
        public long getSize() {
            return file.length();
        }

        @Override
        public byte[] get() {
            return new byte[0];
        }

        @Override
        public String getString(String s) throws UnsupportedEncodingException {
            return null;
        }

        @Override
        public String getString() {
            return null;
        }

        @Override
        public void write(File file) throws Exception {

        }

        @Override
        public void delete() {

        }

        @Override
        public String getFieldName() {
            return file.getName();
        }

        @Override
        public void setFieldName(String s) {

        }

        @Override
        public boolean isFormField() {
            return false;
        }

        @Override
        public void setFormField(boolean b) {

        }

        @Override
        public OutputStream getOutputStream() throws IOException {
            return null;
        }

        @Override
        public FileItemHeaders getHeaders() {
            return null;
        }

        @Override
        public void setHeaders(FileItemHeaders fileItemHeaders) {

        }
    }

}
