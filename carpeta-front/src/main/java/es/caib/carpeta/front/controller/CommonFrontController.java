package es.caib.carpeta.front.controller;

import es.caib.carpeta.logic.EntitatLogicaLocal;
import es.caib.carpeta.logic.UtilitiesForFrontLogicaLocal;
import es.caib.carpeta.model.entity.Fitxer;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 
 * @author anadal
 *
 */
public abstract class CommonFrontController {

    protected final Log log = LogFactory.getLog(getClass());

    @EJB(mappedName = UtilitiesForFrontLogicaLocal.JNDI_NAME)
    protected UtilitiesForFrontLogicaLocal utilsEjb;

    @EJB(mappedName = EntitatLogicaLocal.JNDI_NAME)
    EntitatLogicaLocal entitatEjb;
    
    
   

    protected void descarregaFitxer(HttpServletResponse response, Long fileID)
            throws I18NException, FileNotFoundException, IOException {
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
    }

    public void processException(Throwable e, HttpServletResponse response) {

        //  Afegir log de BBDD d'error al front
        // ISSUE "Mostrar error al fallar autenticació de front #309"

        log.error(e);

        try {
            response.sendRedirect("/error");
        } catch (IOException e1) {
            log.error("Error desconegut realitzant un sendRedirect: " + e1.getMessage(), e1);
        }

    }

}
