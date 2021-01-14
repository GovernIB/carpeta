<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ca" style="" class=" js flexbox flexboxlegacy hashchange backgroundsize boxshadow textshadow opacity cssanimations cssgradients csstransforms csstransitions fontface generatedcontent localstorage svg" lang="ca">
  <head>
    <title>Carpeta Ciutadana - Front</title>
	<link rel="shortcut icon" type="image/x-ico" href="${pageContext.request.contextPath}/webui/icona">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

    <script src="https://cdnjs.cloudflare.com/ajax/libs/react/15.4.2/react.js" charset="utf-8"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/react/15.4.2/react-dom.js" charset="utf-8"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/babel-standalone/6.21.1/babel.min.js" charset="utf-8"></script>
	
	<!-- Scripts -->
	<!-- google analytics -->
	<script type="text/javascript" async="" src="${pageContext.request.contextPath}/src/assets/js/ga.js"></script>
	<script src="${pageContext.request.contextPath}/src/assets/js/jquery-3.5.0.js"></script>
	<script src="${pageContext.request.contextPath}/src/assets/js/jquery-ui.min.js"></script>
	<script src="${pageContext.request.contextPath}/src/assets/js/bootstrap.min.js"></script>
	<!-- Datetimpicker -->
	<script src="${pageContext.request.contextPath}/src/assets/js/moment-with-locales.min.js"></script>
	<script src="${pageContext.request.contextPath}/src/assets/js/bootstrap-datetimepicker.js"></script>
	<!-- Necessari per les cookies -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/src/assets/js/jquery.slimscroll.min.js"></script>
	<script src="${pageContext.request.contextPath}/src/assets/js/modernizr.js"></script>
	<!-- Del Goib -->
	<script src="${pageContext.request.contextPath}/src/assets/js/globales.js" type="text/javascript"></script>
	<!-- Bootstrap compatibilitat amb IE -->
	<script src="${pageContext.request.contextPath}/src/assets/js/respond.js" type="text/javascript"></script>
	
	<script src="${pageContext.request.contextPath}/src/assets/js/jquery.dataTables.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/src/assets/js/datetime-moment.js" type="text/javascript"></script>

	<!-- Scripts per Components React -->
	<script src="${pageContext.request.contextPath}/src/utils/carpeta_react.js" type="text/javascript"></script>

	<!-- ESTILS -->
	<link href="${pageContext.request.contextPath}/src/assets/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/src/assets/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/src/assets/css/jquery-ui.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/src/assets/css/goib.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/src/assets/css/carpeta.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/src/assets/css/open-iconic-bootstrap.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/src/assets/css/jquery.dataTables.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/src/assets/css/datatables.min.css" rel="stylesheet">

  </head>

  <body>

  <sec:authorize access="!isAuthenticated()">
	  <script type="text/javascript">
		  window.onload = function($) {
			  sessionStorage.setItem('autenticat', '0');
			  sessionStorage.setItem('entitat', '${entitat}');
			  sessionStorage.setItem('defaultEntityCode', '${defaultEntityCode}');
			  sessionStorage.setItem('maxInactiveInterval','${maxInactiveInterval}');
			  sessionStorage.setItem('numEntitats', '${numEntitats}');
			  sessionStorage.setItem('canviarDeFront', '${canviarDeFront}');
			  sessionStorage.removeItem('pluginActiu');
			  var aut = sessionStorage.getItem('autenticat');
			  newMenuDesllisant('menuDesllisant', aut);
			  newPeu('peu', aut);
			  newMenuRapid('menuRapid', aut);
		  }
	  </script>
  </sec:authorize>
  
	<!-- Menú vertical -->
	<div id = "menuLateral"></div>
	
	<!-- Contingut dret --> 
	<div class="contenedor" id="contenedor">

		<!-- Capçalera -->
		<div id = "barraMenu"></div>


		<!-- Diàleg d'expiració de sessió -->
<%--		<fmt:setLocale value="${idiomaFMT}"/>--%>
<%--		<div id="dialog" title="<fmt:message key="sessio.avis.titol" bundle="missa"/>" style="display:none">--%>
<%--			<p class="pt-4"><fmt:message key="sessio.avis.descripcio" /></p>--%>
<%--		</div>--%>

<%--		<div id="expirat" title="<fmt:message key="sessio.expirada.titol" />" style="display:none" class="dialogExpirat">--%>
<%--			<p class="pt-4"><fmt:message key="sessio.expirada.descripcio" /></p>--%>
<%--		</div>--%>

		<sec:authorize access="isAuthenticated()">
			<sec:authentication var="user" property="principal.usuarioClave.nombreCompleto" />
			<sec:authentication var="userName" property="principal.usuarioClave.nombre" />
			<sec:authentication var="userSurname1" property="principal.usuarioClave.apellido1" />
			<sec:authentication var="userSurname2" property="principal.usuarioClave.apellido2" />
			<sec:authentication var="userDNI" property="principal.usuarioClave.nif" />
			<sec:authentication var="userMethod" property="principal.usuarioClave.metodoAutentificacion" />
			<sec:authentication var="userLevelAut" property="principal.usuarioClave.qaa" />
			<div class="imc-titol usuari">
				<nav class="imc--contingut">
						<span class="estilUsuari">
							<c:if test="${user != null}">
								<span class="oi oi-person pr-2" aria-hidden="true"> </span><sec:authentication property="principal.usuarioClave.nombreCompleto" />
<%--								<span class="pl-3" title="<fmt:message key="nivell.autenticacio"/>">--%>
<%--									<c:forEach begin="0" end="${userLevelAut - 1}">--%>
<%--										<span class="oi oi-lock-locked nivellAutenticacio"></span>--%>
<%--									</c:forEach>--%>
<%--								</span>--%>
								<!-- Nivell d'autenticació -->
								<span id="nivellAutenticacio"></span>
							</c:if>
						</span>
					</h3>
				</nav>
			</div>
			<script type="text/javascript">
				window.onload = function($) {
					var element = document.getElementById('contenedor');
					var autenticat = '1';
					sessionStorage.setItem('autenticat', '1');
					element.setAttribute('style','margin-top: 4.5em !important');
                    newMenuDesllisant('menuDesllisant', autenticat);
					newInici('contingut', autenticat);
					newPeu('peu', autenticat);
					newAvisosFront('avisosFront', autenticat);
					newMenuRapid('menuRapid', autenticat);
					// Passa dades personals a React
					sessionStorage.setItem('usuariNomComplet', '${user}');
					sessionStorage.setItem('usuariNom', '${userName}');
					sessionStorage.setItem('usuariLlinatge1', '${userSurname1}');
					sessionStorage.setItem('usuariLlinatge2', '${userSurname2}');
					sessionStorage.setItem('usuariDNI', '${userDNI}');
					sessionStorage.setItem('usuariMetode', '${userMethod}');
					sessionStorage.setItem('usuariNivell', '${userLevelAut}');
				}
			</script>
		</sec:authorize>

		<!-- Menú desplegable -->
		<div id = "menuDesllisant"></div>
			
		<!-- Zona Contingut -->
		<div class="imc-continguts" id="continguts">

			<!-- Menú Ràpid-->
			<div id = "menuRapid"></div>

			<!-- Molla de pa -->
			<div id = "mollaPa"></div>

			<!-- Avisos Front -->
			<div id = "avisosFront"></div>

			<!-- Contingut pàgina -->
			<div id = "contingut" class="pt-5"></div>

		</div>
		<!-- Fi Zona Contingut -->

		<!-- Peu -->
		<div id = "peu"></div>


	</div>

	<!-- js react -->
	<script src = "${pageContext.request.contextPath}/dist/reactjs_main.js" type="text/javascript"></script>
	<!-- menú lateral -->
	<script src="${pageContext.request.contextPath}/src/assets/js/menu-lateral.js" type="text/javascript"></script>
	<!-- acceptar cookies -->
	<script src="${pageContext.request.contextPath}/src/assets/js/aceptar_cookies.js" type="text/javascript"></script>

  </body>
</html>