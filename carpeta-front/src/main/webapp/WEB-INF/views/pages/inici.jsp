<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="${pageContext.response.locale.language}" style="" class=" js flexbox flexboxlegacy hashchange backgroundsize boxshadow textshadow opacity cssanimations cssgradients csstransforms csstransitions fontface generatedcontent localstorage svg" lang="${pageContext.response.locale.language}">
  <head>
    <title><fmt:message key="titol.tab" /></title>
	<link rel="shortcut icon" type="image/x-ico" href="${pageContext.request.contextPath}/webui/icona">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

	<!-- Scripts React-->
    <script type="text/javascript" src="<c:url value="/src/assets/js/react.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/src/assets/js/react-dom.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/src/assets/js/babel.min.js"/>"></script>

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

<%-- XYZ ZZZ
    <!-- Scripts per Components React -->
    <script src="<c:url value="/src/utils/carpeta_react.js"/>" type="text/javascript"></script>
--%>

	<!-- ESTILS -->
	<link href="<c:url value="/src/assets/css/bootstrap.min.css"/>" rel="stylesheet">
	<link href="<c:url value="/src/assets/css/bootstrap-datetimepicker.min.css"/>" rel="stylesheet">
	<link href="<c:url value="/src/assets/css/jquery-ui.min.css"/>" rel="stylesheet">
	<link href="<c:url value="/src/assets/css/goib.css"/>" rel="stylesheet">
	<link href="<c:url value="/src/assets/css/carpeta.css"/>" rel="stylesheet">
	<link href="<c:url value="/src/assets/css/open-iconic-bootstrap.css"/>" rel="stylesheet">
	<link href="<c:url value="/src/assets/css/jquery.dataTables.min.css"/>" rel="stylesheet">
	<link href="<c:url value="/src/assets/css/datatables.min.css"/>" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/webui/customcss/${entitat}" rel="stylesheet">

  </head>

  <body>
  

    <sec:authorize access="!isAuthenticated()"> 
	  <script type="text/javascript">
			  sessionStorage.setItem('autenticat', '0');
			  sessionStorage.setItem('entitat', '${entitat}');
			  sessionStorage.setItem('defaultEntityCode', '${defaultEntityCode}');
			  sessionStorage.setItem('maxInactiveInterval','${maxInactiveInterval}');
			  sessionStorage.setItem('numEntitats', '${numEntitats}');
			  sessionStorage.setItem('canviarDeFront', '${canviarDeFront}');
			  sessionStorage.setItem('contextPath', '${pageContext.request.contextPath}');
			  sessionStorage.setItem('errorLogin', '${errorLogin}');
			  sessionStorage.setItem('langActual', '${pageContext.response.locale}');
			  sessionStorage.setItem('pagTornar', '${pageContext.request.contextPath}');
			  sessionStorage.setItem('cssExtern', '${pageContext.request.contextPath}/webui/customcss/${entitat}');
	  </script>
  </sec:authorize>

  

     <sec:authorize access="isAuthenticated()">  
  
		<sec:authentication var="usuariNomComplet" property="principal.usuarioClave.nombreCompleto" />
		<sec:authentication var="userName" property="principal.usuarioClave.nombre" />
		<sec:authentication var="userSurname1" property="principal.usuarioClave.apellido1" />
		<sec:authentication var="userSurname2" property="principal.usuarioClave.apellido2" />
		<sec:authentication var="userDNI" property="principal.usuarioClave.nif" />
		<sec:authentication var="userMethod" property="principal.usuarioClave.metodoAutentificacion" />
		<sec:authentication var="userLevelAut" property="principal.usuarioClave.qaa" />
        <sec:authentication var="userEsEmpresa" property="principal.usuarioClave.empresa" />
        
        
        <sec:authentication var="userRepresentant" property="principal.usuarioClave.usuarioClaveRepresentante" />
        
        
        <c:if test="${not empty userRepresentant }" >
            <c:set var="representantName" value="${userRepresentant.nombre}" />
            <c:set var="representantSurname1" value="${userRepresentant.apellido1}" />
            <c:set var="representantSurname2" value="${userRepresentant.apellido2}" />
            <c:set var="representantDNI" value="${userRepresentant.nif}" />
        </c:if>
        
        
        
		<script type="text/javascript">

				//var element = document.getElementById('contenedor');
				//element.setAttribute('style','margin-top: 4.5em !important');

				// Passa dades personals a React
				var autenticat = '1';
                sessionStorage.setItem('autenticat', autenticat);
				sessionStorage.setItem('usuariNomComplet', '${usuariNomComplet}');
				sessionStorage.setItem('usuariNom', '${userName}');
				sessionStorage.setItem('usuariLlinatge1', '${userSurname1}');
				sessionStorage.setItem('usuariLlinatge2', '${userSurname2}');
				sessionStorage.setItem('usuariDNI', '${userDNI}');
				sessionStorage.setItem('usuariEsEmpresa', ${userEsEmpresa});
				sessionStorage.setItem('usuariMetode', '${userMethod}');
				sessionStorage.setItem('usuariNivell', '${userLevelAut}');
				sessionStorage.setItem('contextPath', '${pageContext.request.contextPath}');
				sessionStorage.setItem('errorLogin', '${errorLogin}');
				sessionStorage.setItem('maxInactiveInterval', '${maxInactiveInterval}');

				<c:if test="${not empty userRepresentant }" >
				sessionStorage.setItem('representantExisteix', true);
				sessionStorage.setItem('representantNom', '${representantName}');
                sessionStorage.setItem('representantLlinatge1', '${representantSurname1}');
                sessionStorage.setItem('representantLlinatge2', '${representantSurname2}');
                sessionStorage.setItem('representantDNI', '${representantDNI}');
				</c:if>

				<c:if test="${empty userRepresentant }" >
                sessionStorage.setItem('representantExisteix', false);
                </c:if>

				sessionStorage.setItem('pagTornar', '${pageContext.request.contextPath}');
				sessionStorage.setItem('cssExtern', '${pageContext.request.contextPath}/webui/customcss/${entitat}');
				

		</script>
	  </sec:authorize> 


    <div id="fullpagecarpetafront" tabindex="-1" class="noEnfocar"></div>

	<!-- js react -->
	<script src = "<c:url value="/dist/reactjs_main.js"/>" type="text/javascript"></script>
	<!-- acceptar cookies -->
	<script src="<c:url value="/src/assets/js/aceptar_cookies.js"/>" type="text/javascript"></script>

  </body>
</html>