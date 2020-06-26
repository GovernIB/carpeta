package es.caib.carpeta.back.utils;

import javax.annotation.security.RunAs;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.crypt.AlgorithmEncrypter;
import org.fundaciobit.genapp.common.crypt.FileIDEncrypter;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;


import es.caib.carpeta.commons.utils.Configuracio;
import es.caib.carpeta.commons.utils.Version;
import es.caib.carpeta.ejb.utils.I18NTranslatorEjb;
import es.caib.carpeta.persistence.hibernate.HibernateFileUtil;


/**
 * Servlet emprat per inicialitzar el Back
 * 
 * @author anadal
 * 
 */
@RunAs("CAR_USER")
public class InitServlet extends HttpServlet {

  protected final Logger log = Logger.getLogger(getClass());

  @Override
  public void init(ServletConfig config) throws ServletException {

    // Sistema de Fitxers
    // XYZ ZZZZ ZZZ Moure a Logic
	 
    try {      
      //FileSystemManager.setFilesPath(Configuracio.getFilesDirectory());
      log.info("FileSystemManager path = " + FileSystemManager.getFilesPath().getAbsolutePath());
    } catch (Throwable th) {
      log.error("Error inicialitzant el sistema de sistema de fitxers: " + th.getMessage(), th);
    }
    

    // Sistema de Traduccions WEB
    try {
      ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
      String[] basenames = { 
          "missatges", // /WEB-INF/classes/
          "logicmissatges",
          "genapp",
          "carpeta_genapp"};
      ms.setDefaultEncoding("UTF-8");
      ms.setBasenames(basenames);
      I18NUtils.setMessageSource(ms);
    } catch (Throwable th) {
      log.error("Error inicialitzant el sistema de traduccions web: " + th.getMessage(), th);
    }

    // Sistema de Traduccions LOGIC
    // TODO Moure a logic
    try {
      Class.forName(I18NTranslatorEjb.class.getName());
    } catch (Throwable th) {
      log.error("Error inicialitzant el sistema de traduccions logic: " + th.getMessage(), th);
    }

    // Encriptador d'identificador de Fitxer
    try {
      FileIDEncrypter encrypter = new FileIDEncrypter(Configuracio.getEncryptKey(),
          AlgorithmEncrypter.ALGORITHM_AES);
      HibernateFileUtil.setEncrypter(encrypter);
    } catch (Exception e) {
      log.error("Error instanciant File Encrypter: " + e.getMessage(), e);
    }

    // Inicialitzar els DataExporters
    /*
    try {
      Set<Class<? extends IExportDataPlugin>> plugins;
      
      if (Configuracio.isDesenvolupament()) {
        String [] classes = new String[] {
           "org.fundaciobit.plugins.exportdata.cvs.CSVPlugin",
           "org.fundaciobit.plugins.exportdata.ods.ODSPlugin",
           "org.fundaciobit.plugins.exportdata.excel.ExcelPlugin"  
        };
        plugins = new HashSet<Class<? extends IExportDataPlugin>>();
        for (String str : classes) {
          
          try {
            Class<?> cls = Class.forName(str);
            plugins.add((Class<? extends IExportDataPlugin>)cls);
          } catch (Throwable e) {            
          }
        }
        
        
      } else {
        plugins = PluginsManager.getPluginsByInterface(IExportDataPlugin.class);  
      }
      
      
      
      if (plugins == null || plugins.size() == 0) {
         log.warn("No existeixen Plugins de ExportData !!!!!");
      } else {
      
        for (Class<? extends IExportDataPlugin> class1 : plugins) {
          IExportDataPlugin edp = (IExportDataPlugin)PluginsManager.instancePluginByClass(class1);
          if (edp == null) {
            log.warn("No s'ha pogut instanciar Plugin associat a la classe " + class1.getName());
          } else {
            log.warn("Registrant DataExporter: " + class1.getName());
            DataExporterManager.addDataExporter(new DataExporterCarpeta(edp));
          } 
        }
      }

    } catch(Throwable e) {
      log.error("Error inicialitzant els DataExporters: " + e.getMessage(), e);
    }
    */

    // Mostrar Versió
    String ver = new Version().getVersion() + (Configuracio.isCAIB() ? "-caib" : "");
    try {
      log.info("Carpeta Version: " + ver);
    } catch (Throwable e) {
      log.info("Carpeta Version: " + ver);
    }

  }

}
