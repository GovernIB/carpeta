<%@include file="/WEB-INF/views/includes.jsp"%>

<div class="collapse navbar-collapse" id="navbarCollapse">

    <ul class="navbar-nav movil">
        <sec:authorize access="isAuthenticated()">
            <li class="nav-item dropdown colorVerde">
                <a class="nav-link dropdown movil" href="#" title="<fmt:message key="menu.gestiones.img"/>" id="menu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="oi oi-caret-bottom float-right flechaSubmenu" title="" aria-hidden="true"></span><p class="mb-0 float-right botonCorto"><fmt:message key="menu.gestiones"/></p><span class="oi oi-briefcase" title="<fmt:message key="menu.gestiones.img"/>" aria-hidden="true"></span></a>
                <div class="dropdown-menu dropdown-menu-right marg0" aria-labelledby="menu">
                    <a class="dropdown-item movil" href="<c:url value="/tramite/list"/>"><span class="oi oi-document" title="" aria-hidden="true"></span> <fmt:message key="menu.tramite/list"/></a>
                    <a class="dropdown-item movil" href="<c:url value="/registro/list"/>"><span class="oi oi-book" title="" aria-hidden="true"></span> <fmt:message key="menu.registro/list"/></a>
                    <a class="dropdown-item movil" href="<c:url value="/notificacion/list"/>"><span class="oi oi-envelope-closed" title="" aria-hidden="true"></span> <fmt:message key="menu.notificaciones"/></a>
                </div>
            </li>
        </sec:authorize>

        <li class="nav-item colorVerde">
            <a class="nav-link movil accBoton" title="<fmt:message key="menu.accesibilidad.img"/>" href="<c:url value="/accesibilidad"/>"><img src="${pageContext.request.contextPath}/static/img/ico_accessibilitat.svg" class="iconoCabecera accIcon" title="<fmt:message key="menu.accesibilidad.img"/>" alt="<fmt:message key="menu.accesibilidad.img"/>"/><p class="mb-0 float-right botonCorto"><fmt:message key="menu.accesibilidad"/></p></a>
        </li>

        <li class="nav-item dropdown colorVerde">
            <a class="nav-link dropdown movil" title="<fmt:message key="menu.idioma.img"/>" href="#" id="idiomas" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="oi oi-caret-bottom float-right flechaSubmenu" title="" aria-hidden="true"></span><p class="mb-0 float-right botonCorto"><fmt:message key="menu.idioma"/></p><span class="oi oi-globe" title="<fmt:message key="menu.idioma.img"/>" aria-hidden="true"></span></a>
            <div class="dropdown-menu dropdown-menu-right marg0 minW0" aria-labelledby="idiomas">
                <c:if test="${pageContext.response.locale.language == 'ca'}">
                    <a class="dropdown-item movil" href="<c:url value="${requestScope.requestURI}?lang=es"/>" title="<fmt:message key="idioma.castellano.alternativo"/>"><fmt:message key="idioma.castellano"/></a>
                    <a class="dropdown-item movil desactivado"><fmt:message key="idioma.catala"/></a>
                </c:if>
                <c:if test="${pageContext.response.locale.language == 'es'}">
                    <a class="dropdown-item movil" href="<c:url value="${requestScope.requestURI}?lang=ca"/>" title="<fmt:message key="idioma.catalan.alternativo"/>"><fmt:message key="idioma.catala"/></a>
                    <a class="dropdown-item movil desactivado"><fmt:message key="idioma.castellano"/></a>
                </c:if>

            </div>
        </li>
        <sec:authorize access="isAuthenticated()">
            <li class="nav-item colorVerde">
                <c:url var="logoutUrl" value="/logout" />
                <form action="${logoutUrl}" id="logout" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
                <a class="nav-link movil" title="<fmt:message key="menu.salir.img"/>" href="javascript:sortirCarpeta()"><span class="oi oi-power-standby" title="<fmt:message key="menu.salir.img"/>" aria-hidden="true"></span> <p class="mb-0 float-right botonCorto"><fmt:message key="menu.salir"/></p></a>
            </li>
        </sec:authorize>
    </ul>

</div>