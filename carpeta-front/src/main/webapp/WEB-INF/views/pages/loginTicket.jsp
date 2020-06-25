<%@ include file="/WEB-INF/views/includes.jsp" %>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="Expires" content="0">
    <meta http-equiv="Last-Modified" content="0">
    <meta http-equiv="Cache-Control" content="no-cache, mustrevalidate">
    <meta http-equiv="Pragma" content="no-cache">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
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