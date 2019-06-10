<%@include file="/WEB-INF/views/includes.jsp"%>
<div class="contacto">
    <div data-conf="suport">
        <strong><fmt:message key="soporte.ayuda"/> </strong><fmt:message key="soporte.contacto"/><button type="button" class="botonAyuda" onclick="abrirAyuda()"><span><fmt:message key="soporte.equipo"/></span></button>
    </div>

    <div id="soporte" class="soporte" aria-hidden="true" role="dialog">

        <div id="cajaSoporte" class="contenidoSoporte">

            <div class="row col-12 ayudaSoporte">
                <img src="${pageContext.request.contextPath}/static/img/ico_info.svg" width="40" class="iconoBoton" alt=""/>
                <span><fmt:message key="soporte.mensaje.ayuda"/>&nbsp;</span>
            </div>

            <div class="row col-12 datosSoporte">
                <div class="row col-12"><fmt:message key="soporte.mensaje.necesita"/></div>

                <ul class="dades">
                    <li>
                        <fmt:message key="soporte.mensaje.envia"/> <a href="mailto:otae@fundaciobit.org" target="_blank"><fmt:message key="soporte.mensaje.correo"/></a>
                    </li>
                    <li>
                        <fmt:message key="soporte.mensaje.llamar"/> <a href="tel:971784940"><fmt:message key="soporte.mensaje.telefono"/></a>.
                    </li>
                </ul>
            </div>
            <div class="row col-12 botonera">
                <ul>
                    <li>
                        <button type="button" class="botonCerrar" data-tipus="tanca" onclick='cerrarAyuda()'><span><fmt:message key="soporte.mensaje.cerrar"/></span></button>
                    </li>
                </ul>
            </div>

        </div>

    </div>
</div>