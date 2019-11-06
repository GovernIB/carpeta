<%@include file="/WEB-INF/views/includes.jsp"%>

<div class="card mb-12 border-0">
    <span class="h5 card-title border-bottom verde"><fmt:message key="menu.notificaciones"/></span>

    <p class="lh15"><fmt:message key="notificaciones.descripcion"/></p>

    <div class="card-body">

        <div class="row">
            <!-- Notificaciones -->
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                <button class="box-part text-center" onclick="goToWindow('${notificacionesUrl}')">
                    <span class="oi oi-envelope-closed imagenMenu" title="<fmt:message key="menu.notificaciones"/>" alt="<fmt:message key="menu.notificaciones"/>" aria-hidden="true"></span>
                    <div class="tituloMenu"><fmt:message key="menu.notificacion"/></div>
                    <div class="text">
                        <span><fmt:message key="notificaciones.notificaciones"/></span>
                    </div>
                </button>
            </div>

            <!-- Otras notificaciones -->
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                <button class="box-part text-center" onclick="goToWindow('${zonaperUrl}')">
                    <span class="oi oi-box imagenMenu" title="<fmt:message key="menu.notificaciones.otras"/>" alt="<fmt:message key="menu.notificaciones.otras"/>" aria-hidden="true"></span>
                    <div class="tituloMenu"><fmt:message key="menu.notificaciones.otras"/></div>
                    <div class="text">
                        <span><fmt:message key="notificaciones.otras"/></span>
                    </div>
                </button>
            </div>

        </div>
    </div>

</div>