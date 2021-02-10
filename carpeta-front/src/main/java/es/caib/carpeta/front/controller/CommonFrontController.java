package es.caib.carpeta.front.controller;

import es.caib.carpeta.ejb.IdiomaService;
import es.caib.carpeta.logic.EntitatLogicaService;
import es.caib.carpeta.logic.UtilitiesForFrontLogicaService;
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

    @EJB(mappedName = UtilitiesForFrontLogicaService.JNDI_NAME)
    protected UtilitiesForFrontLogicaService utilsEjb;

    @EJB(mappedName = EntitatLogicaService.JNDI_NAME)
    EntitatLogicaService entitatEjb;

    @EJB(mappedName = IdiomaService.JNDI_NAME)
    IdiomaService idiomaEjb;
    
    
   

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

    // XYZ ZZZ AIXÔ HA DE FUNCIONAR PER SERVEIS REST !!!!!!!1
    public void processException(Throwable e, HttpServletResponse response) {

        //  Afegir log de BBDD d'error al front
        // ISSUE "Mostrar error al fallar autenticació de front #309"

        log.error(e.getMessage(), e);

        try {
            response.sendRedirect("/error");
        } catch (IOException e1) {
            log.error("Error desconegut realitzant un sendRedirect: " + e1.getMessage(), e1);
        }

    }

}
