<%@include file="/WEB-INF/views/includes.jsp"%>
<div data-conf="suport">
        <p><strong><fmt:message key="soporte.ayuda"/>&nbsp;</strong><fmt:message key="soporte.contacto"/><button type="button" id="imc-bt-equip-suport" class="imc-bt--com-text" onclick="obrirAjuda()"><span>&nbsp;<fmt:message key="soporte.equipo"/></span></button></p>
    </div>

    <div id="imc-suport" class="imc-suport" aria-hidden="true" role="dialog">
        <div class="imc--c">

            <!-- ajuda -->

            <div id="imc-suport-capsa" class="imc-s--contingut imc-s--ajuda">

                <h2><span><fmt:message key="soporte.mensaje.ayuda"/></span></h2>

                <p><fmt:message key="soporte.mensaje.necesita"/></p>

                <ul class="imc--dades">

                    <li data-suport="url">
                        <p><fmt:message key="soporte.mensaje.envia"/> <a href="mailto:otae@fundaciobit.org" target="_blank"><fmt:message key="soporte.mensaje.correo"/></a></p>
                    </li>


                    <li data-suport="telefon">
                        <p><fmt:message key="soporte.mensaje.llamar"/> <a href="tel:971784940"><fmt:message key="soporte.mensaje.telefono"/></a>.</p>
                    </li>

                </ul>

                <ul class="imc--botonera">
                    <li>
                        <button type="button" class="imc-bt imc--tanca" data-tipus="tanca" onclick='tancarAjuda()'><span><fmt:message key="soporte.mensaje.cerrar"/></span></button>
                    </li>
                </ul>

            </div>

        </div>
    </div>