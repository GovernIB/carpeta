<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ca" style="" class=" js flexbox flexboxlegacy hashchange backgroundsize boxshadow textshadow opacity cssanimations cssgradients csstransforms csstransitions fontface generatedcontent localstorage svg" lang="ca">
  <head>
    <title>Carpeta Ciutadana - Front React</title>
	<link rel="shortcut icon" type="image/x-ico" href="http://www.caib.es/sites/favicon.png">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

    <script src="https://cdnjs.cloudflare.com/ajax/libs/react/15.4.2/react.js" charset="utf-8"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/react/15.4.2/react-dom.js" charset="utf-8"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/babel-standalone/6.21.1/babel.min.js" charset="utf-8"></script>
	
	<!-- Scripts -->
	<!-- google analytics -->
	<script type="text/javascript" async="" src="${pageContext.request.contextPath}/src/assets/js/ga.js"></script>
	<script src="${pageContext.request.contextPath}/src/assets/js/jquery-3.5.0.js"></script>
	<script src="${pageContext.request.contextPath}/src/assets/js/bootstrap.min.js"></script>
	<!-- Datetimpicker -->
	<script src="${pageContext.request.contextPath}/src/assets/js/moment-with-locales.min.js"></script>
	<script src="${pageContext.request.contextPath}/src/assets/js/bootstrap-datetimepicker.js"></script>
	<!-- Necessari per les cookies -->
	<script src="${pageContext.request.contextPath}/src/assets/js/modernizr.js"></script>
	<!-- Del Goib -->
	<script src="${pageContext.request.contextPath}/src/assets/js/globales.js" type="text/javascript"></script>
	<!-- Bootstrap compatibilitat amb IE -->
	<script src="${pageContext.request.contextPath}/src/assets/js/respond.js" type="text/javascript"></script>
	
	<script src="${pageContext.request.contextPath}/src/assets/js/jquery.dataTables.min.js" type="text/javascript"></script>
	
	<!-- Scripts per Carpeta-->
	<!--script type="text/javascript" src="src/assets/js/carpeta.js"></script-->
	<!-- Scripts per Components React -->
	<script src="${pageContext.request.contextPath}/src/utils/carpeta_react.js" type="text/javascript"></script>
	
	<!-- ESTILS -->
	<link href="${pageContext.request.contextPath}/src/assets/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/src/assets/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/src/assets/css/goib.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/src/assets/css/carpeta.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/src/assets/css/open-iconic-bootstrap.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/src/assets/css/jquery.dataTables.min.css" rel="stylesheet">
	

  </head>

  <body>
  
	<!-- Menú vertical -->
	<div id = "menuLateral"></div>
	
	<!-- Contingut dret --> 
	<div className="contenedor" id="contenedor">

		<!-- Capçalera --> 
		<div id = "barraMenu"></div>

		<!-- Menú desplegable -->
		<div id = "menuDesllisant"></div>
			
		<!-- Zona Contingut -->
		<div class="imc-continguts" id="continguts">

			<!-- Molla de pa -->
			<div id = "mollaPa"></div>

			<!-- Contingut pàgina -->
			<div id = "contingut"></div>

		</div>
		<!-- Fi Zona Contingut -->

		<!-- Peu -->
		<div id = "peu"></div>
	
	</div>

	<!-- js react -->
	<script src = "${pageContext.request.contextPath}/dist/reactjs_main.js"></script>
	<!-- menú lateral -->
	<script src="${pageContext.request.contextPath}/src/assets/js/menu-lateral.js" type="text/javascript"></script>
	<!-- acceptar cookies -->
	<script src="${pageContext.request.contextPath}/src/assets/js/aceptar_cookies.js" type="text/javascript"></script>

  </body>
</html>