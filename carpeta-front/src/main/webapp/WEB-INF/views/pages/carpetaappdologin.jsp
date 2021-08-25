<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script>
function redireccionar() {
    //document.forms['redirectForm'].submit();
    window.open("<%=request.getContextPath()%>/public/ea/${codientitat}?urlbase=${urlbase}", "_blank");
}
</script>
</head>
<body onload="redireccionar()">
<form id="redirectForm" method="get" action="<%=request.getContextPath()%>/public/ea/${codientitat}" target="_blank">
<input type="hidden" name="urlbase"  value="${urlbase}" />
</form>
<br/>
<center>

<h1><a href="javascript:redireccionar()"> CLICK </a> </h1>

<img src="<%=request.getContextPath()%>/src/assets/images/ajax-loader.gif" />
</center>
</body>
</html>