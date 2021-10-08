<%@page import="org.fundaciobit.genapp.common.i18n.I18NException"
%><%@page import="es.caib.carpeta.logic.LogCarpetaLogicaService"
%><%@page import="es.caib.carpeta.back.utils.Utils"
%><%@page import="es.caib.carpeta.logic.utils.EjbManager"
%><%@page import="es.caib.carpeta.logic.AuthenticationLogicaService"
%><%@page import="es.caib.carpeta.back.security.LoginException"
%><%@page import="org.fundaciobit.genapp.common.web.i18n.I18NUtils"
%><%@page import="org.apache.log4j.Logger"
%><%@page import="org.fundaciobit.genapp.common.web.HtmlUtils"
%><%@ page import="org.springframework.context.i18n.LocaleContextHolder"
%><%@ page import="java.util.Locale"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"
%><%@ page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%!

protected final Logger log = Logger.getLogger(getClass());

%><%
// Página d'error que mostra per pantalla i amb format els errors que es produeixen.
long idError = System.currentTimeMillis() % 100;
try {

    // TODO DEBUG
    
    
    log.error(" =================  ENTRA ERROR.JSP (" + idError 
        + " - " + (String)request.getAttribute("javax.servlet.error.request_uri") 
        + request.getSession().getId() + ") ===================");
    
  
    /**
     * No se per quina rao, però perd la configuració de l'idioma de l'usuari
     * i posa per defecte la del navegador.
     */    
    { 
      Locale loc = request.getLocale();
      if (loc != null) {
        LocaleContextHolder.setLocale(loc);
        {
          log.error("Set locale to " + LocaleContextHolder.getLocale() + " from Request");
        }
      }
    } 

    // Agafam la excepció que s'ha produit.
    Throwable e = pageContext.getException();
    boolean sessioinvalida = false;
    boolean senseroles = false;
    String missatgeSessioInvalida = "";
    String missatgeTipusError="";
    String redirect = null;

    if (e == null){
        String stipusError = request.getParameter("errortype");

        if (pageContext.getErrorData() != null) {
            log.error("[[" + idError + "]] Requested getRequestURI: "
                    + pageContext.getErrorData().getRequestURI() );
            log.error("[[" + idError + "]] Requested getServletName: "
                    + pageContext.getErrorData().getServletName() );
            log.error("[[" + idError + "]] Requested getStatusCode: "
                    + pageContext.getErrorData().getStatusCode() );
        }
        int tipusError = !stipusError.isEmpty()?new Integer(stipusError).intValue():-1;

        switch (tipusError) {
            case 403:
                missatgeTipusError = I18NUtils.tradueix("error.jsp.403", (String)request.getAttribute("javax.servlet.error.request_uri"));
                break;
            case 404:
                missatgeTipusError = I18NUtils.tradueix("error.jsp.404", (String)request.getAttribute("javax.servlet.error.request_uri"));
                break;
            default:
                missatgeTipusError = I18NUtils.tradueix("error.jsp.desconegut");
        }
        
        if ( pageContext.getErrorData().getStatusCode() == 403 )
            senseroles = true;
        
    } else {
        
      if (e instanceof org.springframework.web.util.NestedServletException) {
          
          e = ((org.springframework.web.util.NestedServletException)e).getCause();
          
      }
        
    	
      log.error("Error[" +e.getClass() + "]: " + e.getMessage(), e);
      // Si els errors són de perdua de sessio no mostram el botó per tornar a principal,
      // han de tancar navegador i tornar obrir sessió.
      boolean runAsException = ( e instanceof IllegalArgumentException
          && "Either callerSubject or callerRunAs should be non-null".equals(e.getMessage()) );
      if ( runAsException 
          || ( e instanceof org.springframework.web.HttpSessionRequiredException )
          || ( e instanceof javax.ejb.EJBAccessException && ("Invalid User".equals(e.getMessage())))) {
          // fora botó
          sessioinvalida = true;
          missatgeSessioInvalida = I18NUtils.tradueix("error.jsp.sessioinvalida");
          if (runAsException) {
            redirect = (String)request.getAttribute("javax.servlet.error.request_uri");
          }
      } else if (e instanceof I18NException){
          log.error("Entra a I18NException ...");
          missatgeTipusError = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof org.springframework.web.util.NestedServletException) {
          org.springframework.web.util.NestedServletException nse;
          nse = (org.springframework.web.util.NestedServletException)e;
          Throwable thnse = e.getCause();
          if (thnse instanceof I18NException){
              missatgeTipusError = I18NUtils.getMessage((I18NException)thnse);
          } else {
              missatgeTipusError =  thnse.getMessage();
          }
      
      } else {
      
           missatgeTipusError =  e.getMessage();
          
          
           log.error("[[" + idError + "]] Exceptio de tipus " + e.getClass() );
           log.error("[[" + idError + "]] Exceptio MSG " + e.getMessage() );
         
      }
    }
    // Definim les etiquetes que mostram traduides.
    String etiquetaBoto = I18NUtils.tradueix("error.jsp.tornarprincipal");
    String titolPagina  = I18NUtils.tradueix("error.jsp.pagina");
    String detallError  = I18NUtils.tradueix("error.jsp.detall");
    String veureDetall  = I18NUtils.tradueix("error.jsp.veuredetall");
    
    try {
        LogCarpetaLogicaService logCarpetaLogicaEjb;
        logCarpetaLogicaEjb = EjbManager.getLogCarpetaLogicaEJB();
        
        String missatge = (missatgeSessioInvalida == null ? I18NUtils.tradueix("error.jsp.desconegut"):  missatgeSessioInvalida)
                          + (missatgeTipusError == null ? I18NUtils.tradueix("error.jsp.desconegut") : missatgeTipusError);
        
        Utils.createLog(logCarpetaLogicaEjb, missatge, request, null, null, e);
        
    } catch(Throwable th) {
        log.error("Error desconegut guardant dins la taula LOG una excepció: " + th.getMessage(), th );
    }

    request.getSession().setAttribute("locale", LocaleContextHolder.getLocale().toString());

    if (senseroles){
    	
    	pageContext.forward("/public/senseroles");
    	
    }

%>
<fmt:setLocale value="${locale}"/>
<html>
<link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
<link href="<c:url value="/css/default.css"/>" rel="stylesheet">
<script src="<c:url value="/js/jquery.js" />"></script>
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>



<script type="text/javascript">
  function showError(){
    $(".trace").toggle();
  }

<% if (redirect != null) {
      request.getSession().setAttribute("redirect", redirect);
%>
  setTimeout("location.href = '${redirect}';",3500);
<% } %>
</script>

<body>
 <div class="alert alert-danger">
      <c:set var="stacktrace"  value="${pageContext.exception.stackTrace}"/>
      <div><h4><%=titolPagina%></h4></div>
      <br/>
      
      <% if (!sessioinvalida) { %>
        <div>
        <b>Error:</b>
        <% if (missatgeTipusError!= null) { %>
          <%= missatgeTipusError%>
        <% } else { %>
          ${pageContext.exception.message}
        <% } %>
        <br/>
        <b>URL:</b>${requestScope['javax.servlet.forward.request_uri']}
        </div>
      <% } %>
      <div><b><%=missatgeSessioInvalida%></b></div>
      <br/>
      <!-- Mostram el stacktrace de l'excepció en cas que hi hagi -->
      <div>
        <!-- Botó de mostrar stacktrace en cas que hi hagi stacktrace -->
        <% if (!sessioinvalida) { %>
        <c:if test="${not empty stacktrace}">
            <button class="btn btn-danger" onclick="showError()"><%=veureDetall%></button>
        </c:if>

        <!-- Mostram el botó de tornar a principal -->
        <a class="btn btn-outline-success" href="<c:url value="/common/principal.html"/>" class="btn"><%=etiquetaBoto%></a>
        
        <br/>
        
        
        <b>Missatge Original:</b>: ${pageContext.exception.message}<br/>
        <b>MIssatge Processat:</b>: <%= missatgeTipusError%>

        <br/>
        <br/>
        <!-- Mostram la traça de l'error -->
        <c:if test="${not empty stacktrace}">
            <div class="trace"><b><%=detallError%></b></div>
            <c:forEach var="trace" items="${stacktrace}">
              <p class="trace">"${trace}"</p>
            </c:forEach>
            <br/>
         </c:if>
       <% } %>
      </div>
  </div>
</body>

</html>
<%} catch (Throwable t){
    log.error(t.getMessage(), t);
    out.println("<html><body>S'ha produit un error dins error.jsp " +t.getMessage()+"</body></html>");
  } finally {
    // TODO DEBUG
    log.error(" =================  FINAL ERROR.JSP (" + idError + ")");
  }%>