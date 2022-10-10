<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script>
function redireccionar() {
	window.open("<%=request.getContextPath()%>/public/preLoginApp/${codiLogin}?urlbase=${urlbaseEncoded}", "_blank");
}
setTimeout(function(){ document.getElementById("missatge").style.display="inline"}, 1500);
</script>
</head>
<body onload>  <%--="redireccionar2()">  --%>
<form id="redirectForm" name="redirectForm" method="get" action="<%=request.getContextPath()%>/public/preLoginApp/${codiLogin}" target="_blank">
<input type="hidden" name="urlbase"  value="${urlbase}" />
</form>
<br/>
<center>
<%--
<h1 id="missatge" style="display:none;">
<a href="javascript:redireccionar()"> 
    PER FAVOR, SI VEU AQUEST MISSATGE<br/>
    FACI CLIC AQUÍ PER INICIAR<br/> EL PROCÉS DE LOGIN. GRACIES. 
</a>
</h1>
 --%>
<a href="javascript:redireccionar()"> 
  <img src="<%=request.getContextPath()%>/src/assets/images/ajax-loader.gif" />
</a>
</center>
</body>
</html>