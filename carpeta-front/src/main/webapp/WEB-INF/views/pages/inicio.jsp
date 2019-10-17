<%@include file="/WEB-INF/views/includes.jsp"%>

<sec:authorize access="!isAuthenticated()">
    <nav>
        <p><fmt:message key="inicio.contenido"/></p>
    </nav>
    <nav>
        <p><a href="<c:url value="/login"/>"><fmt:message key="inicio.login"/></a></p>
    </nav>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <div class="card mb-12 border-0">
        <span class="h5 card-title border-bottom verde"><fmt:message key="inicio.contenido"/></span>

        <div class="card-body">

            <div class="row">

                <!-- Trámites no acabados -->
                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                    <button class="box-part text-center" onclick="goTo('<c:url value="/tramite/list"/>')">
                        <span class="oi oi-document imagenMenu" title="<fmt:message key="menu.tramite/list"/>" alt="<fmt:message key="menu.tramite/list"/>" aria-hidden="true"></span>
                        <div class="tituloMenu"><fmt:message key="menu.tramite/list"/></div>
                        <div class="text">
                            <span><spring:message code="tramite.descripcion"/></span>
                        </div>
                    </button>
                </div>

                <!-- Registros -->
                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                    <button class="box-part text-center" onclick="goTo('<c:url value="/registro/list"/>')">
                        <span class="oi oi-book imagenMenu" title="<fmt:message key="menu.registro/list"/>" alt="<fmt:message key="menu.registro/list"/>" aria-hidden="true"></span>
                        <div class="tituloMenu"><fmt:message key="menu.registro/list"/></div>
                        <div class="text">
                            <span><spring:message code="registro.descripcion.corta"/></span>
                        </div>
                    </button>
                </div>

                <!-- Notificaciones -->
                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                    <button class="box-part text-center" onclick="goTo('<c:url value="/notificacion/list"/>')">
                        <span class="oi oi-envelope-closed imagenMenu" title="<fmt:message key="menu.notificaciones"/>" alt="<fmt:message key="menu.notificaciones"/>" aria-hidden="true"></span>
                        <div class="tituloMenu"><fmt:message key="menu.notificaciones"/></div>
                        <div class="text">
                            <span><spring:message code="menu.lorem"/></span>
                        </div>
                    </button>
                </div>

            </div>

            <div class="row">
                <!-- Mis Datos -->
                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                    <button class="box-part text-center" onclick="goTo('<c:url value="/datosPersonales"/>')">
                        <span class="oi oi-folder imagenMenu" title="<fmt:message key="menu.datosPersonales"/>" alt="<fmt:message key="menu.datosPersonales"/>" aria-hidden="true"></span>
                        <div class="tituloMenu"><fmt:message key="menu.datosPersonales"/></div>
                        <div class="text">
                            <span><fmt:message key="menu.lorem"/></span>
                        </div>
                    </button>
                </div>
            </div>


        </div>

    </div>
</sec:authorize>