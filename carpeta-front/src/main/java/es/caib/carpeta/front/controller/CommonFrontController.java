package es.caib.carpeta.front.controller;

import es.caib.carpeta.ejb.IdiomaService;
import es.caib.carpeta.front.utils.SesionHttp;
import es.caib.carpeta.logic.EntitatLogicaService;
import es.caib.carpeta.logic.LogCarpetaLogicaService;
import es.caib.carpeta.logic.UtilitiesForFrontLogicaService;
import es.caib.carpeta.model.entity.Fitxer;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static es.caib.carpeta.commons.utils.Constants.ESTAT_LOG_ERROR;
import static es.caib.carpeta.commons.utils.Constants.TIPUS_LOG_AUTENTICACIO_FRONT;

/**
 * 
 * @author anadal
 *
 */
public abstract class CommonFrontController {

    protected final Log log = LogFactory.getLog(getClass());

    @Autowired
    private SesionHttp sesionHttp;

    @EJB(mappedName = UtilitiesForFrontLogicaService.JNDI_NAME)
    protected UtilitiesForFrontLogicaService utilsEjb;

    @EJB(mappedName = EntitatLogicaService.JNDI_NAME)
    EntitatLogicaService entitatEjb;

    @EJB(mappedName = IdiomaService.JNDI_NAME)
    IdiomaService idiomaEjb;

    @EJB(mappedName = LogCarpetaLogicaService.JNDI_NAME)
    protected LogCarpetaLogicaService logLogicaEjb;
    
    
   

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

    public void processExceptionHtml(Throwable e, HttpServletRequest request, HttpServletResponse response) {

        log.error(e.getMessage(), e);

        try {
            long temps = System.currentTimeMillis();

            logLogicaEjb.crearLog("Error al Front", ESTAT_LOG_ERROR,TIPUS_LOG_AUTENTICACIO_FRONT,
                    System.currentTimeMillis() - temps ,null,e.getMessage(),null,sesionHttp.getEntitat(),null);

            HttpSession session = request.getSession(false);
            session.setAttribute("error", e.getMessage());

            response.sendRedirect(request.getContextPath() + "/error");

        } catch (Throwable e1) {
            log.error("Error: " + e1.getMessage(), e1);
        }


    }

    public void processExceptionRest(Throwable e, HttpServletRequest request, HttpServletResponse response) {

        log.error("HttpServletResponse.SC_INTERNAL_SERVER_ERROR: " + e.getMessage());

        try {

            long temps = System.currentTimeMillis();

            logLogicaEjb.crearLog("Error al Front", ESTAT_LOG_ERROR,TIPUS_LOG_AUTENTICACIO_FRONT,
                    System.currentTimeMillis() - temps ,null,e.getMessage(),null,sesionHttp.getEntitat(),null);

            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());

        } catch (IOException e1) {
            log.error("Error desconegut: " + e1.getMessage(), e1);
        }

    }

}
