package es.caib.carpeta.front.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.caib.carpeta.hibernate.HibernateFileUtil;
import es.caib.carpeta.logic.UtilitiesForFrontLogicaLocal;
import es.caib.carpeta.model.entity.Fitxer;

/**
 * 
 * @author anadal
 *
 */
public abstract class CommonFrontController {

    protected final Log log = LogFactory.getLog(getClass());

    @EJB(mappedName = UtilitiesForFrontLogicaLocal.JNDI_NAME)
    UtilitiesForFrontLogicaLocal utilsEjb;
    
    
    public static final String ENLLAZ_LOGO_PATH =  "/enllazlogo";

    @RequestMapping(value = ENLLAZ_LOGO_PATH + "/{encodedenllazlogoid}", method = RequestMethod.GET)
    public void getEnllazLogo(@PathVariable("encodedenllazlogoid") String encodedenllazlogoid,
            HttpServletRequest request, HttpServletResponse response) {

        try {
            String fileIDStr = HibernateFileUtil.decryptString(encodedenllazlogoid);

            Long fileID = Long.parseLong(fileIDStr);

            Fitxer fi = utilsEjb.getFileInfo(fileID);

            File file = FileSystemManager.getFile(fileID);

            FileInputStream fis = new FileInputStream(file);

            try {
                response.setContentType(fi.getMime());
                response.setHeader("Content-Disposition", "inline; filename=\"" + fi.getNom() + "\"");
                response.setContentLength((int) file.length());

                IOUtils.copy(fis, response.getOutputStream());
            } finally {

                try {
                    fis.close();
                } catch (Exception e) {

                }

            }

        } catch (Throwable e) {
            processException(e, response);
        }

    }

    public void processException(Throwable e, HttpServletResponse response) {
        /// XYZ ZZZ
        // XYZ ZZZ Afegir log de BBDD d'error al front

        log.error(e);

        try {
            response.sendRedirect("/error");
        } catch (IOException e1) {
            log.error("Erro desconegut realitzant un sendRedirect: " + e1.getMessage(), e1);
        }

    }

}
