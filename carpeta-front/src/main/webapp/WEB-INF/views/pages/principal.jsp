<%@include file="/WEB-INF/views/includes.jsp"%>


<div class="card mb-12 border-0">
    <h5 class="card-title border-bottom verde"><fmt:message key="inicio.contenido"/></h5>

    <div class="card-body">

        <div class="row">

            <!-- Trámites no acabados -->
            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                <button class="box-part text-center" onclick="goTo('<c:url value="/tramite/list"/>')">
                    <span class="oi oi-document imagenMenu" title="<fmt:message key="menu.tramites"/>" alt="<fmt:message key="menu.tramites"/>" aria-hidden="true"></span>
                    <div class="tituloMenu"><fmt:message key="menu.tramites"/></div>
                    <div class="text">
                        <span><fmt:message key="menu.lorem"/></span>
                    </div>
                </button>
            </div>

            <!-- Registros -->
            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                <button class="box-part text-center" onclick="goTo('<c:url value="/registro/list"/>')">
                    <span class="oi oi-book imagenMenu" title="<fmt:message key="menu.registros"/>" alt="<fmt:message key="menu.registros"/>" aria-hidden="true"></span>
                    <div class="tituloMenu"><fmt:message key="menu.registros"/></div>
                    <div class="text">
                        <span><fmt:message key="menu.lorem"/></span>
                    </div>
                </button>
            </div>

            <!-- Mis Datos -->
            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                <button class="box-part text-center" onclick="goTo('<c:url value="/datosPersonales"/>')">
                    <span class="oi oi-folder imagenMenu" title="<fmt:message key="menu.datos.personales"/>" alt="<fmt:message key="menu.datos.personales"/>" aria-hidden="true"></span>
                    <div class="tituloMenu"><fmt:message key="menu.datos.personales"/></div>
                    <div class="text">
                        <span><fmt:message key="menu.lorem"/></span>
                    </div>
                </button>
            </div>

        </div>

    </div>

</div>
