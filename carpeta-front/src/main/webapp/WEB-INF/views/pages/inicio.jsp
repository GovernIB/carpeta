<%@include file="/WEB-INF/views/includes.jsp"%>


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

<%--<script type="text/javascript">--%>
<%--    var valoresMiga = { 0: '1', 1: '<c:url value="/"/>', 2: '<fmt:message key="menu.inicio"/>' };--%>
<%--</script>--%>