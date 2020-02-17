<%@include file="/WEB-INF/views/includes.jsp"%>

<div class="card mb-12 border-0">
    <span class="h5 card-title border-bottom verde"><fmt:message key="menu.mapaWeb"/></span>
    <nav>
        <p><fmt:message key="mapaWeb.descripcion"/></p>
    </nav>

    <span class="h5 card-title border-bottom verde"><fmt:message key="mapaWeb.secciones"/></span>
    <nav>

        <div class="table-responsive">
            <table class="table table-hover" style="width:100%">
                <tbody>
                    <tr class="text-uppercase backWhite">
                        <td class="border-0"><a href="<c:url value="/inicio"/>"><fmt:message key="mapaWeb.informacion"/></td>
                    </tr>
                    <tr class="text-uppercase backWhite">
                        <td class="border-0"><fmt:message key="menu.tramites.no.acabados"/></td>
                    </tr>
                    <tr class="text-uppercase backWhite">
                        <td class="border-0 pl-5"><a href="<c:url value="/tramite/list"/>"><fmt:message key="mapaWeb.tramites"/></a>  <sec:authorize access="!isAuthenticated()"><img src="${pageContext.request.contextPath}/static/img/clave2.png" class="imatgeGovern" alt="<fmt:message key="accesibilidad.acceso.clave"/>" title="<fmt:message key="accesibilidad.acceso.clave"/>"/></sec:authorize></td>
                    </tr>
                    <tr class="text-uppercase backWhite">
                        <td class="border-0"><fmt:message key="menu.registro/list"/></td>
                    </tr>
                    <tr class="text-uppercase backWhite">
                        <td class="border-0 pl-5"><a href="<c:url value="/registro/list"/>"><fmt:message key="mapaWeb.registros"/></a>  <sec:authorize access="!isAuthenticated()"><img src="${pageContext.request.contextPath}/static/img/clave2.png" class="imatgeGovern" alt="<fmt:message key="accesibilidad.acceso.clave"/>" title="<fmt:message key="accesibilidad.acceso.clave"/>"/></sec:authorize></td>
                    </tr>
                    <tr class="text-uppercase backWhite">
                        <td class="border-0"><fmt:message key="menu.notificaciones"/></td>
                    </tr>
                    <tr class="text-uppercase backWhite">
                        <td class="border-0 pl-5"><a href="" onclick="goToWindow('${notificacionesUrl}')"><fmt:message key="menu.notificacion"/></a></td>
                    </tr>
                    <tr class="text-uppercase backWhite">
                        <td class="border-0 pl-5"><a href="" onclick="goToWindow('${zonaperUrl}')"><fmt:message key="menu.notificaciones.otras"/></a>  <sec:authorize access="!isAuthenticated()"><img src="${pageContext.request.contextPath}/static/img/clave2.png" class="imatgeGovern" alt="<fmt:message key="accesibilidad.acceso.clave"/>" title="<fmt:message key="accesibilidad.acceso.clave"/>"/></sec:authorize></td>
                    </tr>
                    <tr class="text-uppercase backWhite">
                        <td class="border-0"><a href="<c:url value="/datosPersonales"/>"><fmt:message key="menu.datosPersonales"/></a>  <sec:authorize access="!isAuthenticated()"><img src="${pageContext.request.contextPath}/static/img/clave2.png" class="imatgeGovern" alt="<fmt:message key="accesibilidad.acceso.clave"/>" title="<fmt:message key="accesibilidad.acceso.clave"/>"/></sec:authorize></td>
                    </tr>
                </tbody>
            </table>
        </div>

    </nav>
</div>