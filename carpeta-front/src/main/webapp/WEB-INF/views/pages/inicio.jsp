<%@include file="/WEB-INF/views/includes.jsp"%>

<sec:authorize access="!isAuthenticated()">
    <div class="card col-12 border-0">
        <span class="h5 card-title border-bottom verde"><fmt:message key="inicio.bienvenida"/></span>
    </div>

    <div class="card col-md-6 border-0 float-left">
        <p class="lh15">
            <fmt:message key="inicio.contenido.1"/>
            <ul class="lh15">
                <li><fmt:message key="inicio.contenido.2"/></li>
                <li><fmt:message key="inicio.contenido.3"/></li>
                <li><fmt:message key="inicio.contenido.4"/></li>
                <li><fmt:message key="inicio.contenido.5"/></li>
            </ul>
        </p>

        <p><img src="${pageContext.request.contextPath}/static/img/solicitar_clave_acceso_dgt.jpg" class="w-25" alt="<fmt:message key="inicio.clave.logo.alternativo"/>" title="<fmt:message key="inicio.clave.logo"/>"/></p>

        <p class="lh15">
            <fmt:message key="inicio.clave.1"/>
            <ul class="lh15">
                <li><fmt:message key="inicio.clave.2"/></li>
                <li><fmt:message key="inicio.clave.3"/></li>
                <li><fmt:message key="inicio.clave.4"/></li>
            </ul>
        </p>
    </div>

    <div class="card col-md-6 border-0 margen-left-clave">

        <p class="margen-top-clave">
            <a class="btn btn-primary carpeta-btn" href="<c:url value="/login"/>" role="button"><span class="oi oi-share" title="" aria-hidden="true"></span> <fmt:message key="inicio.login"/></a>
        </p>

        <p class="font-weight-bold"><strong><fmt:message key="inicio.problema.1"/></strong></p>
        <p class="lh15">
            <fmt:message key="inicio.problema.2"/>
            <ul class="lh15">
                <li><fmt:message key="inicio.problema.3"/> <a href="http://clave.gob.es/clave_Home/clave.html" class="btn btn-soporte pb-1" title="<fmt:message key="inicio.clave"/>" alt="<fmt:message key="inicio.clave.alternativo"/>" target="_blank">Cl@ve</a></li>
                <li><fmt:message key="inicio.problema.4"/></li>
                <li><fmt:message key="inicio.problema.5"/> <a href="https://ssweb.seap.minhap.es/ayuda/consulta/Claveciudadanos" class="btn btn-soporte pb-1" title="<fmt:message key="inicio.buzon"/>" title="<fmt:message key="inicio.buzon.alternativo"/>" target="_blank"><fmt:message key="inicio.problema.6"/></a></li>
            </ul>
        </p>

    </div>

    <script type="text/javascript">
        $("div.bg-white").css("overflow","hidden");
    </script>

</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <div class="card mb-12 border-0">
        <span class="h5 card-title border-bottom verde"><fmt:message key="inicio.bienvenida"/></span>

        <p class="lh15"><fmt:message key="inicio.contenido.logeado"/></p>

        <div class="card-body">

            <div class="row">

                <!-- Trámites no acabados -->
                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                    <button class="box-part text-center" onclick="goTo('<c:url value="/tramite/list"/>')">
                        <span class="oi oi-document imagenMenu row" title="<fmt:message key="menu.tramite/list"/>" alt="<fmt:message key="menu.tramite/list"/>" aria-hidden="true"></span>
                        <span class="tituloMenu "><fmt:message key="menu.tramite/list"/></span>
                        <span class="text row"><spring:message code="tramite.descripcion"/></span>
                    </button>
                </div>

                <!-- Registros -->
                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                    <button class="box-part text-center" onclick="goTo('<c:url value="/registro/list"/>')">
                        <span class="oi oi-book imagenMenu row" title="<fmt:message key="menu.registro/list"/>" alt="<fmt:message key="menu.registro/list"/>" aria-hidden="true"></span>
                        <span class="tituloMenu"><fmt:message key="menu.registro/list"/></span>
                        <span class="text row">
                            <span><spring:message code="registro.descripcion.corta"/></span>
                        </span>
                    </button>
                </div>

                <!-- Notificaciones -->
                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                    <button class="box-part text-center" onclick="goTo('<c:url value="/notificacion/list"/>')">
                        <span class="oi oi-envelope-closed imagenMenu row" title="<fmt:message key="menu.notificaciones"/>" alt="<fmt:message key="menu.notificaciones"/>" aria-hidden="true"></span>
                        <span class="tituloMenu"><fmt:message key="menu.notificaciones"/></span>
                        <span class="text row">
                            <span><spring:message code="notificaciones.descripcion"/></span>
                        </span>
                    </button>
                </div>

            </div>

            <div class="row">
                <!-- Mis Datos -->
                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                    <button class="box-part text-center" onclick="goTo('<c:url value="/datosPersonales"/>')">
                        <span class="oi oi-folder imagenMenu row" title="<fmt:message key="menu.datosPersonales"/>" alt="<fmt:message key="menu.datosPersonales"/>" aria-hidden="true"></span>
                        <span class="tituloMenu"><fmt:message key="menu.datosPersonales"/></span>
                        <span class="text row">
                            <span><fmt:message key="datos.descripcion"/></span>
                        </span>
                    </button>
                </div>
            </div>


        </div>

    </div>
</sec:authorize>