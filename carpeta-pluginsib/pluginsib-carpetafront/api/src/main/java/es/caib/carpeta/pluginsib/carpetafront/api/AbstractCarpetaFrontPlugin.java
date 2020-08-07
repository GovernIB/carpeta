package es.caib.carpeta.pluginsib.carpetafront.api;

import java.io.PrintWriter;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractCarpetaFrontPlugin extends AbstractPluginFullUtilities implements
    ICarpetaFrontPlugin {

  



  /**
   * 
   */
  public AbstractCarpetaFrontPlugin() {
    super();
  }

  /**
   * @param propertyKeyBase
   * @param properties
   */
  public AbstractCarpetaFrontPlugin(String propertyKeyBase, Properties properties) {
    super(propertyKeyBase, properties);
  }

  /**
   * @param propertyKeyBase
   */
  public AbstractCarpetaFrontPlugin(String propertyKeyBase) {
    super(propertyKeyBase);
  }
  
  

  
  //----------------------------------------------------------------------------
   // ----------------------------------------------------------------------------
   // ------------------- REQUEST GET-POST ---------------------------------------
   // ----------------------------------------------------------------------------
   // ----------------------------------------------------------------------------


    
   /**
    * 
    */
   protected void requestGETPOST(String absolutePluginRequestPath,
       String relativePluginRequestPath, String scanWebID, 
       // XYZ ZZZ
       Object fullInfo,
       String query, Locale languageUI,
       HttpServletRequest request, HttpServletResponse response, boolean isGet) {

    

     if (fullInfo == null) {
       String titol = (isGet ? "GET" : "POST") + " " + getTitle(new Locale("ca"))
           + " PETICIO HA CADUCAT";

       requestTimeOutError(absolutePluginRequestPath, relativePluginRequestPath, query,
           String.valueOf(scanWebID), request, response, titol);

     } else if (query.startsWith(WEBRESOURCE)) {

       retornarRecursLocal(absolutePluginRequestPath, relativePluginRequestPath, scanWebID,
           query, request, response, languageUI);
     
     } else {
       // XYZ Fer un missatges com toca
       String titol = (isGet ? "GET" : "POST") + " " + getTitle(new Locale("ca"))
           + " DESCONEGUT";
       requestNotFoundError(titol, absolutePluginRequestPath, relativePluginRequestPath,
           query, String.valueOf(scanWebID), request, response, languageUI);
     }
   }
    
    
   // XYZ TODO Passat a pare web
   public static final String WEBRESOURCE = "webresource";
   
   protected void getJavascriptCSS(HttpServletRequest request,
       String absolutePluginRequestPath, String relativePluginRequestPath, PrintWriter out,
       Locale languageUI) {
     
     out.println("<script type=\"text/javascript\" src=\"" + relativePluginRequestPath + WEBRESOURCE + "/js/jquery.js\"></script>");
     out.println("<script type=\"text/javascript\" src=\"" + relativePluginRequestPath + WEBRESOURCE + "/js/bootstrap.js\"></script>");
     out.println("<link href=\"" + relativePluginRequestPath + WEBRESOURCE + "/css/bootstrap.css\" rel=\"stylesheet\" media=\"screen\">");

   }
  

}
