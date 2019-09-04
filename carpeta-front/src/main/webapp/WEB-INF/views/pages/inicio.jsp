<%@include file="/WEB-INF/views/includes.jsp"%>

<%--Miga de pan--%>
<%--<nav aria-label="breadcrumb">--%>
<%--    <ol class="breadcrumb">--%>
<%--        <li class="breadcrumb-item active"><a href="<c:url value="/"/>"><fmt:message key="menu.inicio"/></a></li>--%>
<%--    </ol>--%>
<%--</nav>--%>

<nav>
    <p><fmt:message key="inicio.contenido"/></p>
</nav>
<nav>
    <p>
        <sec:authorize access="!isAuthenticated()">
            <p><a href="<c:url value="/login"/>"><fmt:message key="inicio.login"/></a></p>
        </sec:authorize>
    </p>
</nav>
