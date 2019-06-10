<%@include file="/WEB-INF/views/includes.jsp"%>
<div class="container contenido">

    <div class="row">

        <p><fmt:message key="inicio.contenido"/></p>
    </div>
    <div class="row">
        <p>
            <sec:authorize access="!isAuthenticated()">
                <p><a href="<c:url value="/login"/>"><fmt:message key="inicio.login"/></a></p>
            </sec:authorize>
        </p>
    </div>

</div>