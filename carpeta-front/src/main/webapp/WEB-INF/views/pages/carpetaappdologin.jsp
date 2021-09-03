<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script>
/*
function redireccionar2() {
	//alert("Intentant Obrir Browser Extern ...");
    document.forms['redirectForm'].submit();
    
}
*/

function redireccionar() {
	window.open("<%=request.getContextPath()%>/public/preLoginApp/${codiLogin}?urlbase=${urlbaseEncoded}", "_blank");
}

</script>
</head>
<body onload>  <%--="redireccionar2()">  --%>
<form id="redirectForm" name="redirectForm" method="get" action="<%=request.getContextPath()%>/public/preLoginApp/${codiLogin}" target="_blank">
<input type="hidden" name="urlbase"  value="${urlbase}" />
</form>
<br/>
<center>

<h1><a href="javascript:redireccionar()"> PER FAVOR, SI VEU AQUEST MISSATGE<br/>
           FACI CLIC AQUÍ PER INICIAR<br/> EL PROCÉS DE LOGIN. GRACIES. </a> </h1>

<img src="<%=request.getContextPath()%>/src/assets/images/ajax-loader.gif" />
</center>
</body>
</html>