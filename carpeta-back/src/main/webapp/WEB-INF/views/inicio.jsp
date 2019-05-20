<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/includes.jsp" %>
<html>
<head>
    <title><fmt:message key="carpeta.titulo"/></title>
</head>
<body>
    <h1><fmt:message key="carpeta.titulo"/></h1>
    <p><c:out value="${now}"/></p>

    <p>User ${usuario}</p>
    <p>Roles:
    <c:forEach var="rol" items="${roles}">
        ${rol.authority}
    </c:forEach>
    </p>
</body>
</html>
