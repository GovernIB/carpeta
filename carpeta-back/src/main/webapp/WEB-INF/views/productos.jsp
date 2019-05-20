<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/includes.jsp" %>
<html>
<head>
    <title><fmt:message key="carpeta.titulo"/></title>
</head>
<body>
    <h1>Bienvenidos a la Carpeta ciudadana </h1>
    <p><c:out value="${now}"/></p>

    <h3>Productos</h3>
    <c:forEach items="${productos}" var="producto">
        <c:out value="${producto.descripcion}"/> <i>$<c:out value="${producto.precio}"/></i><br><br>
    </c:forEach>
</body>
</html>
