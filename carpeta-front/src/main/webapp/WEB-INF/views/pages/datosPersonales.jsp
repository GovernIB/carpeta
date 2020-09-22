<%@include file="/WEB-INF/views/includes.jsp"%>


<div class="card mb-12 border-0 p-2">

    <span class="h5 card-title border-bottom verde paddingBottomEstandard"><fmt:message key="datos.datos"/> <sec:authentication property="principal.usuarioClave.nombreCompleto" /></span>

    <p class="lh15"><fmt:message key="datos.descripcion"/></p>

    <div class="card-body">

        <!-- Datos Personales -->
        <div class="col-md-12 mb-4">
            <div class="card border-left-success shadow py-2">
                <div class="card-body" style="padding: 1.25rem">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <dl class="row">
                                <dt class="col-sm-4"><fmt:message key="usuario.nombre"/></dt>
                                <dd class="col-sm-8"><sec:authentication property="principal.usuarioClave.nombre" /></dd>

                                <dt class="col-sm-4"><fmt:message key="usuario.apellido1"/></dt>
                                <dd class="col-sm-8"><sec:authentication property="principal.usuarioClave.apellido1" /></dd>

                                <dt class="col-sm-4"><fmt:message key="usuario.apellido2"/></dt>
                                <dd class="col-sm-8"><sec:authentication property="principal.usuarioClave.apellido2" /></dd>

                                <dt class="col-sm-4"><fmt:message key="usuario.dni"/></dt>
                                <dd class="col-sm-8"><sec:authentication property="principal.usuarioClave.nif" /></dd>

                                <dt class="col-sm-4"><fmt:message key="usuario.metodo"/></dt>
                                <dd class="col-sm-8"><sec:authentication property="principal.usuarioClave.metodoAutentificacion" /></dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>