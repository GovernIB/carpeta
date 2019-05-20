<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/includes.jsp" %>
<html>
<head>
    <title><fmt:message key="carpeta.titulo"/></title>
    <script type="text/javascript">
        function loginTicket() {
            document.getElementById("formLogin").submit();
        }
    </script>
</head>
<body onload="loginTicket()">
Autenticando...

<form name="formLogin" id="formLogin" method="post" action="login">
    <input type="hidden" name="username" id="username" value="<c:out value="${ticketName}"/>"/>
    <input type="hidden" name="password" id="password" value="<c:out value="${ticketValue}"/>"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

</body>
</html>