<%@include file="/WEB-INF/views/includes.jsp"%>
<footer id="footer" class="containerPie">
    <div class="row mr-auto">

        <div class="col-3 governPie">
            <strong><fmt:message key="pie.govern"/></strong>
        </div>

        <div class="col-6 opcionesPie">
            <ul>

                <li><a href="" data-toggle="modal" data-target="#modalContacto"><fmt:message key="soporte.ayuda"/></a></li>
                <li><a href="" target="_blank"><fmt:message key="lopd.lopd"/></a></li>
                <li><a href="avisLegal" target="_blank"><fmt:message key="pie.legal"/></a></li>
                <li><a href="<c:url value="/accesibilidad"/>"><span class="oi oi-people" title="" alt="" aria-hidden="true"></span> <fmt:message key="menu.accesibilidad"/></a></li>
            </ul>
        </div>

        <div class="col-3 redesPie">

            <ul>

                <li>
                    <a class="">
                        <span class="">Youtube</span>
                    </a>

                    <a class="">
                        <span class="">Instagram</span>
                    </a>
                </li>


                <li>
                    <a class="">
                        <span class="">Twitter</span>
                    </a>

                    <a class="">
                        <span class="">Facebook</span>
                    </a>
                </li>

            </ul>

        </div>

    </div>

    <!-- Modal -->
    <div class="modal fade" id="modalContacto" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle"><span class="oi oi-info" title="" alt="" aria-hidden="true"></span> <fmt:message key="soporte.mensaje.ayuda"/></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
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
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="soporte.mensaje.cerrar"/></button>
                </div>
            </div>
        </div>
    </div>
</footer>