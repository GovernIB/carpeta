<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="org.fundaciobit.genapp.common.web.i18n.I18NUtils"%>
<%@ page import="org.apache.log4j.Logger"%>
<%@ page import="org.springframework.context.i18n.LocaleContextHolder"%>
<%@ page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%!
	protected final Logger log = Logger.getLogger(getClass());
%>
<%
	// Página d'error que mostra per pantalla i amb format els errors que es produeixen.
	long idError = System.currentTimeMillis() % 1000;
	try {

		log.error(" =================  ENTRA ERROR.JSP (" + idError
				+ " - " + request.getAttribute("error")
				+ request.getSession().getId() + ") ===================");

		log.error("LANNNNNNG: " + LocaleContextHolder.getLocale().toString());

		String e = (String)request.getAttribute("error");

		// Definim les etiquetes que mostram traduides.
		String etiquetaBoto = I18NUtils.tradueix("error.jsp.tornarprincipal");
		String titolPagina  = I18NUtils.tradueix("error.jsp.pagina");
		String detallError  = I18NUtils.tradueix("error.jsp.detall");
		String veureDetall  = I18NUtils.tradueix("error.jsp.veuredetall");


%>

<html>

	<title><fmt:message key="titol.tab" /></title>
	<link rel="shortcut icon" type="image/x-ico" href="${pageContext.request.contextPath}/webui/icona">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

	<script src="https://cdnjs.cloudflare.com/ajax/libs/react/15.4.2/react.js" charset="utf-8"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/react/15.4.2/react-dom.js" charset="utf-8"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/babel-standalone/6.21.1/babel.min.js" charset="utf-8"></script>

	<!-- Scripts -->
	<!-- google analytics -->
	<script type="text/javascript" async="" src="<c:url value="/src/assets/js/ga.js"/>"></script>
	<script src="<c:url value="/src/assets/js/jquery-3.5.0.js"/>"></script>
	<script src="<c:url value="/src/assets/js/jquery-ui.min.js"/>"></script>
	<script src="<c:url value="/src/assets/js/bootstrap.min.js"/>"></script>
	<!-- Datetimpicker -->
	<script src="<c:url value="/src/assets/js/moment-with-locales.min.js"/>"></script>
	<script src="<c:url value="/src/assets/js/bootstrap-datetimepicker.js"/>"></script>
	<!-- Necessari per les cookies -->
	<script type="text/javascript" src="<c:url value="/src/assets/js/jquery.slimscroll.min.js"/>"></script>
	<script src="<c:url value="/src/assets/js/modernizr.js"/>"></script>
	<!-- Del Goib -->
	<script src="<c:url value="/src/assets/js/globales.js"/>" type="text/javascript"></script>
	<!-- Bootstrap compatibilitat amb IE -->
	<script src="<c:url value="/src/assets/js/respond.js"/>" type="text/javascript"></script>

	<script src="<c:url value="/src/assets/js/jquery.dataTables.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/src/assets/js/datetime-moment.js"/>" type="text/javascript"></script>

	<!-- Scripts per Components React -->
	<script src="<c:url value="/src/utils/carpeta_react.js"/>" type="text/javascript"></script>

	<!-- ESTILS -->
	<link href="<c:url value="/src/assets/css/bootstrap.min.css"/>" rel="stylesheet">
	<link href="<c:url value="/src/assets/css/bootstrap-datetimepicker.min.css"/>" rel="stylesheet">
	<link href="<c:url value="/src/assets/css/jquery-ui.min.css"/>" rel="stylesheet">
	<link href="<c:url value="/src/assets/css/goib.css"/>" rel="stylesheet">
	<link href="<c:url value="/src/assets/css/carpeta.css"/>" rel="stylesheet">
	<link href="<c:url value="/src/assets/css/open-iconic-bootstrap.css"/>" rel="stylesheet">
	<link href="<c:url value="/src/assets/css/jquery.dataTables.min.css"/>" rel="stylesheet">
	<link href="<c:url value="/src/assets/css/datatables.min.css"/>" rel="stylesheet">


<script type="text/javascript">
	function showError(){
		$(".trace").toggle();
	}

	function tornaEnrera(path){
		if(window.top){
			window.location.href = path + "/";
		}else{
			window.location.href = path + "/error";
		}
	}
</script>

<body>
	<div class="alert alert-error">
		<c:set var="stacktrace"  value="<%=e%>"/>
		<c:set var="context" value="${pageContext.request.contextPath}" />

		<div><h4><%=titolPagina%></h4></div>
		<br/>
		<div><b>Error:</b>${stacktrace}</div>

	<div>
		<!-- Botó de mostrar stacktrace en cas que hi hagi stacktrace -->
		<c:if test="${not empty stacktrace}">
			<button class="btn btn-danger" onclick="showError()"><%=veureDetall%></button>
		</c:if>

		<!-- Mostram el botó de tornar a principal -->
		<button class="" onclick="tornaEnrera('${context}')"><%=etiquetaBoto%></button>
		<br/>
		<br/>
		<!-- Mostram la traça de l'error -->
		<c:if test="${not empty stacktrace}">
			<div class="trace ocult"><b><%=detallError%></b></div>
			<c:forEach var="trace" items="${stacktrace}">
				<p class="trace ocult">${trace}</p>
			</c:forEach>
			<br/>
		</c:if>

	</div>
	</div>
</body>

</html>
<%} catch (Throwable t){
	log.error(t.getMessage(), t);
	out.println("<html><body>S'ha produit un error dins error.jsp " + t.getMessage() + "</body></html>");
} finally {
	// TODO DEBUG
	log.error(" =================  FINAL ERROR.JSP (" + idError + ")");
}%>