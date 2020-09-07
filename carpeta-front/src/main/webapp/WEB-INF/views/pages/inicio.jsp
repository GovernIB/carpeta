<%@include file="/WEB-INF/views/includes.jsp"%>

<sec:authorize access="!isAuthenticated()">
    <div class="card col-12 border-0 p-2">
        <span class="h5 card-title border-bottom verde paddingBottomEstandard"><fmt:message key="inicio.bienvenida"/></span>
    </div>

    <div class="card col-md-6 border-0 float-left pt-1">
        <div class="margen-clave" style="padding-top: 1rem !important;">
            <p class="lh15 font-weight-bolder">
                <span class="oi oi-person verde p-1" title="" aria-hidden="true"></span><fmt:message key="inicio.contenido.1"/>
            </p>
            <ul class="lh15" style="list-style-type: none">
                <li><span class="oi oi-arrow-right verde p-1" title="" aria-hidden="true"></span><fmt:message key="inicio.contenido.2"/></li>
                <li><span class="oi oi-arrow-right verde p-1" title="" aria-hidden="true"></span><fmt:message key="inicio.contenido.31"/></li>
                <li><span class="oi oi-arrow-right verde p-1" title="" aria-hidden="true"></span><fmt:message key="inicio.contenido.4"/></li>
                <li><span class="oi oi-arrow-right verde p-1" title="" aria-hidden="true"></span><fmt:message key="inicio.contenido.5"/></li>
            </ul>
        </div>

        <p class="pt-2 lh15 font-weight-bolder">
            <span class="oi oi-account-login verde p-1" title="" aria-hidden="true"></span><fmt:message key="inicio.clave.0"/>
        </p>
        <div class="row">
            <ul style="list-style-type: none">
                <li>
                    <p><fmt:message key="inicio.clave.01"/></p>
                </li>
            </ul>
            <div class="col-xs-12 col-sm-4 text-center">
<%--                <ul style="list-style-type: none">--%>
<%--                    <li>--%>
                        <a href="https://clave.gob.es/clave_Home/clave.html">
                            <img class="claveWitdh" src="${pageContext.request.contextPath}/static/img/solicitar_clave_acceso_dgt.jpg" alt="<fmt:message key="inicio.clave.logo.alternativo"/>" title="<fmt:message key="inicio.clave.logo"/>"/>
                        </a>
<%--                    </li>--%>
<%--                </ul>--%>
            </div>
            <div class="col-xs-12 col-sm-8">
                <ul class="lh15" style="list-style-type: none">
                    <li><span class="oi oi-arrow-right verde p-1" title="" aria-hidden="true"></span><fmt:message key="inicio.clave.2"/></li>
                    <li><span class="oi oi-arrow-right verde p-1" title="" aria-hidden="true"></span><fmt:message key="inicio.clave.3"/></li>
                    <li><span class="oi oi-arrow-right verde p-1" title="" aria-hidden="true"></span><fmt:message key="inicio.clave.4"/></li>
                </ul>
            </div>
        </div>
    </div>

    <div class="card col-md-6 border-0 margen-left-clave">

        <p class="margen-clave">
            <a style="font-size: 20px; padding: 12px 30px" class="btn btn-primary carpeta-btn" href="<c:url value="/login"/>" role="button"><span class="oi oi-account-login p-1" title="" aria-hidden="true"></span> <fmt:message key="inicio.login"/></a>
        </p>

        <div class="pt3 pb-3">
            <p class="font-weight-bold"><strong><fmt:message key="inicio.clau.tramitacio"/></strong></p>
            <p class="lh15">
                <span class="oi oi-external-link verde pt-1 pr-1 pb-1" title="" aria-hidden="true"></span>
                <a class="_btn _btn-primary" href="${entornoUrl}/sistrafront/zonaperfront/protected/init.do?lang=${idioma}&autenticacion=A" role="button"><fmt:message key="inicio.clau.tramitacio.acces"/></a>
            </p>
        </div>

        <p class="font-weight-bold"><strong><fmt:message key="inicio.problema.1"/></strong></p>
        <p class="lh15">
                <fmt:message key="inicio.problema.2"/>
        <ul class="lh15" style="list-style-type: none">
            <li><span class="oi oi-arrow-right verde p-1" title="" aria-hidden="true"></span><fmt:message key="inicio.problema.3"/> <a href="http://clave.gob.es/clave_Home/clave.html" class="btn btn-soporte pb-1" title="<fmt:message key="inicio.clave"/>" alt="<fmt:message key="inicio.clave.alternativo"/>" target="_blank">Cl@ve</a></li>
            <li><span class="oi oi-arrow-right verde p-1" title="" aria-hidden="true"></span><fmt:message key="inicio.problema.4"/></li>
            <li><span class="oi oi-arrow-right verde p-1" title="" aria-hidden="true"></span><fmt:message key="inicio.problema.5"/> <a href="https://ssweb.seap.minhap.es/ayuda/consulta/Claveciudadanos" class="btn btn-soporte pb-1" title="<fmt:message key="inicio.buzon"/>" title="<fmt:message key="inicio.buzon.alternativo"/>" target="_blank"><fmt:message key="inicio.problema.6"/></a></li>
        </ul>
        </p>

    </div>

    <script type="text/javascript">
        $("div.bg-white").css("overflow","hidden");
    </script>

</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <div class="card mb-12 border-0 p-2">
        <span class="h5 card-title border-bottom verde paddingBottomEstandard"><fmt:message key="inicio.bienvenida"/></span>

        <p class="lh15"><fmt:message key="inicio.contenido.logeado"/></p>

        <div class="card-body">

            <div class="row">

                <!-- Trámites no acabados -->
                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 boxGestio">
                    <button class="box-part text-center capsaMenu" onclick="goTo('<c:url value="/tramite/list"/>')">
                        <span class="oi oi-document imagenMenu row" title="<fmt:message key="menu.tramite/list"/>" alt="<fmt:message key="menu.tramite/list"/>" aria-hidden="true"></span>
                        <span class="tituloMenu "><fmt:message key="menu.tramite/list"/></span>
                        <span class="text row"><spring:message code="tramite.descripcion"/></span>
                    </button>
                </div>

                <!-- Registros -->
                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 boxGestio">
                    <button class="box-part text-center capsaMenu" onclick="goTo('<c:url value="/registro/list"/>')">
                        <span class="oi oi-book imagenMenu row" title="<fmt:message key="menu.registro/list"/>" alt="<fmt:message key="menu.registro/list"/>" aria-hidden="true"></span>
                        <span class="tituloMenu"><fmt:message key="menu.registro/list"/></span>
                        <span class="text row">
                            <span><spring:message code="registro.descripcion.corta"/></span>
                        </span>
                    </button>
                </div>

                <!-- Notificaciones -->
                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 boxGestio">
                    <button class="box-part text-center capsaMenu" onclick="goTo('<c:url value="/notificacion/list"/>')">
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
                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 boxGestio">
                    <button class="box-part text-center capsaMenu" onclick="goTo('<c:url value="/datosPersonales"/>')">
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