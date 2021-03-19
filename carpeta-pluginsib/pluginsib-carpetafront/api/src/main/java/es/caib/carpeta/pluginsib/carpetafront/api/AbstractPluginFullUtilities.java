package es.caib.carpeta.pluginsib.carpetafront.api;

//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.fundaciobit.pluginsib.core.utils.AbstractPluginPropertiesTranslations;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import static java.nio.charset.StandardCharsets.ISO_8859_1;
import java.util.Locale;
import java.util.Properties;

/**
 * 
 * @author anadal
 * 
 * TODO revisar noms i variables amb SCANWEB
 *
 */
public abstract class AbstractPluginFullUtilities extends AbstractPluginPropertiesTranslations {

    protected Logger log = Logger.getLogger(this.getClass());
    
    
    
    /**
     * 
     */
    public AbstractPluginFullUtilities() {
      super();
    }

    /**
     * @param propertyKeyBase
     * @param properties
     */
    public AbstractPluginFullUtilities(String propertyKeyBase, Properties properties) {
      super(propertyKeyBase, properties);
    }

    /**
     * @param propertyKeyBase
     */
    public AbstractPluginFullUtilities(String propertyKeyBase) {
      super(propertyKeyBase);
    }
    
    
    public abstract String getTitle(Locale locale);

    // ----------------------------------------------------------------------------
    // ----------------------------------------------------------------------------
    // ------------------- HTML UTILS BUTTON -------------------------------------
    // ----------------------------------------------------------------------------
    // ----------------------------------------------------------------------------

    // TODO XYZ mogut a base
    
    protected void sendRedirect(HttpServletResponse response, String url) {
      try {
        response.sendRedirect(url);
      } catch (IOException e) {
        log.error(e.getMessage(), e);
      }
    }

    protected final PrintWriter generateHeader(HttpServletRequest request,
        HttpServletResponse response, String absolutePluginRequestPath,
        String relativePluginRequestPath, Locale languageUI) {

      response.setCharacterEncoding("utf-8");
      response.setContentType("text/html");
      PrintWriter out;
      try {
        out = response.getWriter();
      } catch (IOException e) {
        return null;
      }

      String lang = languageUI.getLanguage();

      out.println("<!DOCTYPE html>");
      out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"" + lang
          + "\"  lang=\"" + lang + "\">");
      out.println("<head>");

      out.println("<meta http-equiv=\"Content-Type\" content=\"text/html;\" charset=\"UTF-8\" >");

      out.println("<title>" + getTitle(languageUI) + "</title>");
      out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");

      // Javascript i CSS externs
      getJavascriptCSS(request, absolutePluginRequestPath, relativePluginRequestPath, out,
          languageUI);

      out.println("</head>");
      out.println("<body>");

      return out;

    }

    protected final void generateFooter(PrintWriter out) {
      out.println("</body>");
      out.println("</html>");
    }

    


    protected void getJavascriptCSS(HttpServletRequest request, String absolutePluginRequestPath,
            String relativePluginRequestPath, PrintWriter out, Locale languageUI) {
        
    }



    

    // ---------------------------------------------------------------------------
    // ---------------------------------------------------------------------------
    // ------------------- UPLOAD FILES UTILS ------------------------------------
    // ---------------------------------------------------------------------------
    // ---------------------------------------------------------------------------
    
   // TODO XYZ Llegir de AbstractWebPlugin
    
    /**
     * 
     * @param request
     * @param response
     * @return null when error then you must call to "return;"
     */
    /*
    protected Map<String, FileItem> readFilesFromRequest(HttpServletRequest request,
        HttpServletResponse response) {
      boolean isMultipart = ServletFileUpload.isMultipartContent(request);

      try {

        if (!isMultipart) {
          throw new Exception("Form is not Multipart !!!!!!!");
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();

        
        File temp= getTempDir();
        factory.setRepository(temp);
        
        
        

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Parse the request to get file items.
        @SuppressWarnings("unchecked")
        List<FileItem> fileItems = upload.parseRequest(request);

        Map<String, FileItem> mapFile = new HashMap<String, FileItem>();

        // Process the uploaded file items
        for (FileItem fi : fileItems) {

          if (!fi.isFormField()) {
            String fieldName = fi.getFieldName();
            if (log.isDebugEnabled()) {
              log.debug("Uploaded File:  PARAM = " + fieldName + " | FILENAME: "
                + fi.getName());
            }

            mapFile.put(fieldName, fi);

          }
        }

        return mapFile;

      } catch (Exception e) {
        String msg = e.getMessage();
        log.error(msg, e);
        // No emprar ni 404 ni 403
        try {
          response.sendError(HttpServletResponse.SC_BAD_REQUEST, msg); // bad
                                                                       // request
        } catch (Exception ee) {
          log.error(ee.getMessage(), ee);
        }
        return null; // = ERROR
      }

    }
    */
    
    public static File tempDir = null;
    
    protected synchronized File getTempDir() throws IOException {
      
      if (tempDir == null) {
        File temp = File.createTempFile("test", "test");
        
        tempDir = temp.getParentFile();
              
        if (!temp.delete()) {
          temp.deleteOnExit();
        }
      }
      
      return tempDir;
      
    }
    
    
    
    // ---------------------------------------------------------------------------
    // ---------------------------------------------------------------------------
    // ------------------- GESTIO D'ERRORS ---------------------------------------
    // ---------------------------------------------------------------------------
    // ---------------------------------------------------------------------------
    
    // TODO XYZ mogut a base
    
    protected static final String ABSTRACT_SCAN_WEB_RES_BUNDLE = "carpetafrontsistra";
    

    public void requestTimeOutError(String absolutePluginRequestPath,
        String relativePluginRequestPath, String query, String scanWebID,
        HttpServletRequest request, HttpServletResponse response,
        String titol) {
      String str = allRequestInfoToStr(request, titol, absolutePluginRequestPath,
          relativePluginRequestPath, query, scanWebID);

      // TODO Traduir
      // El procés de s'escaneig amb ID " + scanWebID  + " ha caducat. Torni a
      // intentar-ho.\n" + str;
      Locale locale = request.getLocale();

      String msg = getTraduccio(ABSTRACT_SCAN_WEB_RES_BUNDLE, "timeout.error", locale, getTitle(locale));

      log.error(msg + "\n" + str);

      // No emprar ni 404 ni 403
      try {
        response.sendError(HttpServletResponse.SC_REQUEST_TIMEOUT, msg); // Timeout
      } catch (IOException e) {
        log.error(e.getMessage(), e);
      }
    }


    public void requestNotFoundError(String titol, String absolutePluginRequestPath,
        String relativePluginRequestPath, String query, 
        String ID, HttpServletRequest request, HttpServletResponse response,
        Locale locale) {
      
   // No emprar ni 404 ni 403
      int httpStatusCode = HttpServletResponse.SC_BAD_REQUEST;
   // S'ha realitzat una petició al plugin [{0}] però no s'ha trobat cap mètode
      // per processar-la {1}
      
      String str = allRequestInfoToStr(request, titol, absolutePluginRequestPath,
          relativePluginRequestPath, query, ID);
      
      log.error(str);
      
      String msg = getTraduccio(ABSTRACT_SCAN_WEB_RES_BUNDLE, "notfound.error", locale, getTitle(locale),
          str);
      
      
      

      
      
      try {
        response.sendError(httpStatusCode, msg); // bad request
      } catch (IOException e) {
        log.error(e.getMessage(), e);
      }
    }

    
    // ---------------------------------------------------------
    // ------------------- READ LOCAL RESOURCES  ---------------
    // ---------------------------------------------------------

    // TODO XYZ mogut a base
    public void retornarRecursLocal(String absolutePluginRequestPath,
        String relativePluginRequestPath, String ID, String query,
        HttpServletRequest request, HttpServletResponse response, Locale languageUI) {
      byte[] contingut = null;
      String mime = getMimeType(query);
      query = query.replace('\\', '/');

      query = query.startsWith("/") ? query : ('/' + query);

      try {

        InputStream input = getClass().getResourceAsStream(query);

        if (input != null) {

          contingut = IOUtils.toByteArray(input);

          int pos = query.lastIndexOf('/');
          String resourcename = pos == -1 ? query : query.substring(pos + 1);
          
          OutputStream out = response.getOutputStream();
          

          response.setContentType(mime);
          response.setHeader("Content-Disposition", "inline; filename=\"" + resourcename + "\"");
          response.setContentLength(contingut.length);


          out.write(contingut);
          out.flush();

          return;
        }
      } catch (IOException e) {
        log.error("Error llegint recurs " + query, e);
      }

      // ERROR

      String titol = "No trob el recurs " + query;
      requestNotFoundError(titol, absolutePluginRequestPath, relativePluginRequestPath, query,
          String.valueOf(ID), request, response, languageUI);
    }

    protected String getMimeType(String resourcename) {
      String mime = "application/octet-stream";
      if (resourcename != null && !"".equals(resourcename)) {
        String type = resourcename.substring(resourcename.lastIndexOf(".") + 1);
        if ("jar".equalsIgnoreCase(type)) {
          mime = "application/java-archive";
        } else if ("gif".equalsIgnoreCase(type)) {
          mime = "image/gif";
        } else if ("cab".equalsIgnoreCase(type)) {
          mime = "application/octet-stream";
        } else if ("exe".equalsIgnoreCase(type)) {
          mime = "application/octet-stream";
        } else if ("pkg".equalsIgnoreCase(type)) {
          mime = "application/octet-stream";
        } else if ("msi".equalsIgnoreCase(type)) {
          mime = "application/octet-stream";
        } else if ("js".equalsIgnoreCase(type)) {
          mime = "text/javascript";
        } else if ("zip".equalsIgnoreCase(type)) {
          mime = "application/zip";
        } else if ("css".equalsIgnoreCase(type)) {
          mime = "text/css";
        } else if ("png".equalsIgnoreCase(type)) {
          mime = "image/png";
        }
      }
      return mime;
    }

    protected void obtenerContentType(String contentType, HttpServletResponse response, String filename, MimetypesFileTypeMap mimeTypesMap, byte[] data) throws IOException, Exception {
        OutputStream output;
        if (contentType == null) {
            try {
                File tmp = File.createTempFile("regweb_annex_", filename);
                FileOutputStream fos = new FileOutputStream(tmp);
                fos.write(data);
                fos.flush();
                fos.close();
                contentType = mimeTypesMap.getContentType(tmp);
                if (!tmp.delete()) {
                    tmp.deleteOnExit();
                }
            } catch (Throwable th) {
                log.error("Error intentant obtenir el tipus MIME: " + th.getMessage(), th);
                contentType = "application/octet-stream";
            }
        }
        response.setContentType(contentType);
        response.setHeader("Content-Disposition", getContentDispositionHeader(true,filename));
        response.setContentLength(data.length);

        output = response.getOutputStream();
        output.write(data);

        output.flush();

    }



    /**
     * Retorna el contingut de la capçalera <i>Content-Disposition</i> que compleix
     * amb https://tools.ietf.org/html/rfc6266#section-5
     * @param attachment si és true empra attachment, sinó inline
     * @param filename nom del fitxer suggerit
     * @return contingut de la capçalera.
     * @throws Exception
     */
    public static String getContentDispositionHeader(boolean attachment, String filename)
            throws Exception {

        // Preparam el nom suggerit en UTF-8
        // No podem emprar URLEncoder.encode perquè només és per paràmetres http i
        // converteix els espais a "+" enlloc de a "%20"
        String utf8filename = URLEncoder.encode(filename, "UTF-8");

        // Asseguram que el filename suggerit en ISO-8859-1 no té caràcters incompatibles
        CharsetEncoder charsetEncoder = ISO_8859_1.newEncoder();
        if (!charsetEncoder.canEncode(filename)) {
            charsetEncoder.onUnmappableCharacter(CodingErrorAction.REPLACE);
            charsetEncoder.replaceWith("_".getBytes(ISO_8859_1) );
            ByteBuffer buffer = charsetEncoder.encode(CharBuffer.wrap(filename));
            filename = new String(buffer.array(), ISO_8859_1);
        }

        // Implementació de RFC6266. La majoria de navegadors soporten la codificació en UTF-8 emprant
        // "filename*=", pels que no ho soportin, ficam "filename=" abans.
        return (attachment ? "attachment" : "inline")
                + "; filename=\"" + filename + "\""
                + "; filename*=UTF-8''" + utf8filename;
    }

    

    // ---------------------------------------------------------
    // ------------------- DEBUG ------------------------
    // ---------------------------------------------------------

    // TODO XYZ Moure a Plugin Abstracte de tipus WEB
    
    protected void logAllRequestInfo(HttpServletRequest request, String titol,
        String absolutePluginRequestPath, String relativePluginRequestPath, String query,
        String ID) {

      log.info(allRequestInfoToStr(request, titol, absolutePluginRequestPath,
          relativePluginRequestPath, query, ID));

    }

    protected String allRequestInfoToStr(HttpServletRequest request, String titol,
        String absolutePluginRequestPath, String relativePluginRequestPath, String query,
        String ID) {

      String str1 = pluginRequestInfoToStr(titol, absolutePluginRequestPath,
          relativePluginRequestPath, query, ID);

      String str2 = servletRequestInfoToStr(request);

      return str1 + str2;
    }

    protected String pluginRequestInfoToStr(String titol, String absolutePluginRequestPath,
        String relativePluginRequestPath, String query, String ID) {
      StringBuffer str = new StringBuffer("======== PLUGIN REQUEST " + titol + " ===========\n");
      str.append("absolutePluginRequestPath: " + absolutePluginRequestPath + "\n");
      str.append("relativePluginRequestPath: " + relativePluginRequestPath + "\n");
      str.append("query: " + query + "\n");
      str.append("ID: " + ID + "\n");
      return str.toString();
    }

    public static String servletRequestInfoToStr(HttpServletRequest request) {
      StringBuffer str = new StringBuffer(
          " +++++++++++++++++ SERVLET REQUEST INFO ++++++++++++++++++++++\n");
      str.append(" ++++ Scheme: " + request.getScheme() + "\n");
      str.append(" ++++ ServerName: " + request.getServerName() + "\n");
      str.append(" ++++ ServerPort: " + request.getServerPort() + "\n");
      str.append(" ++++ PathInfo: " + request.getPathInfo() + "\n");
      str.append(" ++++ PathTrans: " + request.getPathTranslated() + "\n");
      str.append(" ++++ ContextPath: " + request.getContextPath() + "\n");
      str.append(" ++++ ServletPath: " + request.getServletPath() + "\n");
      str.append(" ++++ getRequestURI: " + request.getRequestURI() + "\n");
      str.append(" ++++ getRequestURL: " + request.getRequestURL() + "\n");
      str.append(" ++++ getQueryString: " + request.getQueryString() + "\n");
      str.append(" ++++ javax.servlet.forward.request_uri: "
          + (String) request.getAttribute("javax.servlet.forward.request_uri")  + "\n");
      str.append(" ===============================================================");
      return str.toString();
    }


    // --------------------------------------------------------------------------
    // -------------------------------------------------------------------------
    // ---------------------------- E R R O R P A G E ----------------------
    // -------------------------------------------------------------------------
    // -------------------------------------------------------------------------

    protected void errorPage(String errorMsg, Throwable th, HttpServletRequest request,
                             HttpServletResponse response, Locale locale) throws Exception {

        if (th == null) {
            log.error("AbstractPluginFullUtilities::errorPage() " + errorMsg);
        } else {
            log.error("AbstractPluginFullUtilities::errorPage() " + errorMsg, th);
        }

        if (locale == null) {
            locale = request.getLocale();
        }


        PrintWriter out =  response.getWriter();

        out.println("<html>");
        out.println("<body>");
        out.println("<h4 style=\"color:red\">" + errorMsg + "</h4>");

        if (th != null) {

            out.println("<div style=\"border:1px solid #D3D3D3; border-radius: 15px;\">");
            out.println("<pre>");
            StringWriter sw = new StringWriter();
            th.printStackTrace(out);
            out.println(sw.toString());
            out.println("</pre>");
            out.println("</div>");

        }
        out.println("</body>");
        out.println("</html>");

        out.flush();


    }



}
