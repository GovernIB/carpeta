<!-- Menú lateral y también para móviles -->
<%@include file="/WEB-INF/views/includes.jsp"%>
<div class="collapse navbar-collapse" id="navbarsExampleDefault">

    <ul class="navbar-nav movil">
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle movil" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="${pageContext.request.contextPath}/static/img/folder-open-regular.svg" width="15" class="icono" alt="<fmt:message key="menu.gestiones"/>"/> <fmt:message key="menu.gestiones"/></a>
            <div class="dropdown-menu marg0" aria-labelledby="dropdown01">
                <a class="dropdown-item movil" href="<c:url value="/tramites"/>"><img src="${pageContext.request.contextPath}/static/img/file-alt-regular.svg" width="15" class="icono" alt="<fmt:message key="menu.tramites.no.acabados"/>"/> <fmt:message key="menu.tramites.no.acabados"/></a>
                <a class="dropdown-item movil" href="<c:url value="/registros"/>"><img src="${pageContext.request.contextPath}/static/img/file-regular.svg" width="15" class="icono" alt="<fmt:message key="menu.registros"/>"/> <fmt:message key="menu.registros"/></a>
            </div>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle movil" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="${pageContext.request.contextPath}/static/img/idioma.svg" width="15" class="icono" alt="<fmt:message key="menu.idioma"/>"/> <fmt:message key="menu.idioma"/></a>
            <div class="dropdown-menu marg0" aria-labelledby="dropdown01">
                <a class="dropdown-item movil disabled" href="<c:url value="/?idioma=ca"/>"><fmt:message key="idioma.catala"/></a>
                <a class="dropdown-item movil" href="<c:url value="/?idioma=es"/>"><fmt:message key="idioma.castellano"/></a>
            </div>
        </li>
        <li class="nav-item">
            <a class="nav-link movil" href="#"><img src="${pageContext.request.contextPath}/static/img/power-off-solid.svg" width="15" class="icono" alt=""/> <fmt:message key="menu.salir"/></a>
        </li>
    </ul>

</div>