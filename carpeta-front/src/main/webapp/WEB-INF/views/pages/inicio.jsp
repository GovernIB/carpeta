<%@include file="/WEB-INF/views/includes.jsp"%>
<div class="imc--explicacio-detallada imc--e-d" id="imc--explicacio-detallada">
    <p><fmt:message key="inicio.contenido"/></p>
    <p></p>
    <sec:authorize access="!isAuthenticated()">
        <p><a href="<c:url value="/login"/>"><fmt:message key="inicio.login"/></a></p>
    </sec:authorize>
</div>