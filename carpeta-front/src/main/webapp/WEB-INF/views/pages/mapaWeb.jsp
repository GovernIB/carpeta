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
                    <tr class="clickable-row" data-target="" data-href="<c:url value="/inicio"/>">
                        <td class="border-0"><fmt:message key="mapaWeb.informacion"/></td>
                    </tr>

                    <sec:authorize access="isAuthenticated()">
                        <tr class="text-uppercase">
                            <td class="border-0"><fmt:message key="menu.tramites.no.acabados"/></td>
                        </tr>
                        <tr class="clickable-row text-uppercase" data-target="" data-href="<c:url value="/tramite/list"/>">
                            <td class="border-0 pl-5"><fmt:message key="mapaWeb.tramites"/></td>
                        </tr>
                        <tr class="text-uppercase">
                            <td class="border-0"><fmt:message key="menu.registro/list"/></td>
                        </tr>
                        <tr class="clickable-row text-uppercase" data-target="" data-href="<c:url value="/registro/list"/>">
                            <td class="border-0 pl-5"><fmt:message key="mapaWeb.registros"/></td>
                        </tr>
                        <tr class="text-uppercase">
                            <td class="border-0"><fmt:message key="menu.notificaciones"/></td>
                        </tr>
                        <tr class="clickable-row text-uppercase" data-target="" onclick="goToWindow('${notificacionesUrl}')" data-href="<c:url value="/mapaWeb"/>">
                            <td class="border-0 pl-5"><fmt:message key="menu.notificacion"/></td>
                        </tr>
                        <tr class="clickable-row text-uppercase" data-target="" onclick="goToWindow('${zonaperUrl}')" data-href="<c:url value="/mapaWeb"/>">
                            <td class="border-0 pl-5"><fmt:message key="menu.notificaciones.otras"/></td>
                        </tr>
                        <tr class="clickable-row text-uppercase" data-target="" data-href="<c:url value="/datosPersonales"/>">
                            <td class="border-0"><fmt:message key="menu.datosPersonales"/></td>
                        </tr>
                    </sec:authorize>

                </tbody>
            </table>
        </div>

    </nav>
</div>