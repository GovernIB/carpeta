<%@include file="/WEB-INF/views/includes.jsp"%>

<%--Miga de pan--%>
<%--<nav aria-label="breadcrumb">--%>
<%--    <ol class="breadcrumb">--%>
<%--        <li class="breadcrumb-item active"><a href="<c:url value="/"/>"><fmt:message key="menu.inicio"/></a></li>--%>
<%--    </ol>--%>
<%--</nav>--%>

<nav>

    <div class="container">
        <p><fmt:message key="inicio.contenido"/></p>
        <div class="row">
            <!-- Trámites no acabados -->
            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                <button class="box-part text-center" onclick="goTo('<c:url value="/tramites"/>')">
                    <span class="oi oi-document imagenMenu" title="" alt="" aria-hidden="true"></span>
                    <div class="tituloMenu"><fmt:message key="menu.tramites"/></div>
                    <div class="text">
                        <span><fmt:message key="menu.lorem"/></span>
                    </div>
                </button>
            </div>

            <!-- Registros -->
            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                <button class="box-part text-center" onclick="goTo('<c:url value="/registros"/>')">
                    <span class="oi oi-book imagenMenu" title="" alt="" aria-hidden="true"></span>
                    <div class="tituloMenu"><fmt:message key="menu.registros"/></div>
                    <div class="text">
                        <span><fmt:message key="menu.lorem"/></span>
                    </div>
                </button>
            </div>

            <!-- Mis Datos -->
            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                <button class="box-part text-center" onclick="goTo('<c:url value="/datosPersonales"/>')">
                    <span class="oi oi-folder imagenMenu" title="" alt="" aria-hidden="true"></span>
                    <div class="tituloMenu"><fmt:message key="menu.datos.personales"/></div>
                    <div class="text">
                        <span><fmt:message key="menu.lorem"/></span>
                    </div>
                </button>
            </div>

            <!-- Accesibilidad -->
<%--            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">--%>
<%--                <button class="box-part text-center" onclick="goTo('<c:url value="/accesibilidad"/>')">--%>
<%--                    <img src="${pageContext.request.contextPath}/static/img/ico_accessibilitat.svg" class="iconoMenu" alt=""/>--%>
<%--                    <div class="tituloMenu"><fmt:message key="menu.accesibilidad"/></div>--%>
<%--                    <div class="text">--%>
<%--                        <span><fmt:message key="menu.lorem"/></span>--%>
<%--                    </div>--%>
<%--                </button>--%>
<%--            </div>--%>

        </div>
    </div>

</nav>
