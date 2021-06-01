<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" 
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" 
%><!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="${pageContext.response.locale.language}" style="" class=" js flexbox flexboxlegacy hashchange backgroundsize boxshadow textshadow opacity cssanimations cssgradients csstransforms csstransitions fontface generatedcontent localstorage svg" lang="${pageContext.response.locale.language}">
<head>	
	<link rel="shortcut icon" type="image/x-ico" href="http://www.caib.es/sites/favicon.png">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

	<!-- Adapta la escala del responsive en los dispositivos moviles -->
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<%--
	<!-- Scripts -->
	<!-- google analytics -->
	<script type="text/javascript" async="" src="/js/ga.js"></script>
	<script src="/js/jquery-3.5.0.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<!-- Datetimpicker -->
	<script src="/js/moment-with-locales.min.js"></script>
	<script src="/js/bootstrap-datetimepicker.js"></script>
	<script src="/js/carpeta.js"></script>
	<!-- Necessari per les cookies -->
	<script src="/js/modernizr.js"></script>
	<!-- Del Goib -->
	<script src="/js/globales.js" type="text/javascript"></script>
	<!-- Bootstrap compatibilitat amb IE -->
	<script src="/js/respond.js" type="text/javascript"></script>
	
	<!-- Bootstrap -->
	<link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
	<link href="/css/goib.css" rel="stylesheet">
	<link href="/css/carpeta.css" rel="stylesheet">
	<link href="/css/open-iconic-bootstrap.css" rel="stylesheet">
	 --%>
</head>

<body>

<h1>Veure Plugin</h1>

<form action="http://localhost:8080/carpetafront/pluginfront/showplugin" method="POST">
<table border=1>
     
     <tr>
        <td>AdministrationID</td>
        <td><input name="administrationID" type="text" value="43096845C"></td>
     </tr>
     
     <tr>
        <td>URLBase</td>
        <td><input id="urlBase" name="urlBase" readonly type="text" value=""></td>
     </tr>
   <tr>
      <td>PluginID: </td>
       <td>
       <select name="pluginID" id="pluginID">
        <c:forEach items="${plugins}" var="plugin">
          <option value="${plugin.pluginID}">${plugin.nomCa} (${plugin.pluginID})</option>
        </c:forEach>
        </select>
       </td>
   </tr>

</table>
<input type="submit" value="Veure Plugin"/>

<script type="text/javascript">
document.getElementById("urlBase").value=window.location.href;
</script>

</body>
</html>
