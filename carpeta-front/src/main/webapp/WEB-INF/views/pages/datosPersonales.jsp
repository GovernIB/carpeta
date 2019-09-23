<%@include file="/WEB-INF/views/includes.jsp"%>


<div class="card mb-12 border-0">
    <h5 class="card-title border-bottom verde"><fmt:message key="datos.datos"/> <sec:authentication property="principal.usuarioClave.nombreCompleto" /></h5>

    <div class="card-body">

        <!-- Datos Personales -->
        <div class="col-md-6 mb-4">
            <div class="card border-left-success shadow py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="font-weight-bold text-success text-uppercase mb-3 text-center"><fmt:message key="menu.datos.personales"/></div>
                            <dl class="row">
                                <dt class="col-sm-4"><fmt:message key="usuario.nombre"/></dt>
                                <dd class="col-sm-8"><sec:authentication property="principal.usuarioClave.nombre" /></dd>

                                <dt class="col-sm-4"><fmt:message key="usuario.apellido1"/></dt>
                                <dd class="col-sm-8"><sec:authentication property="principal.usuarioClave.apellido1" /></dd>

                                <dt class="col-sm-4"><fmt:message key="usuario.apellido2"/></dt>
                                <dd class="col-sm-8"><sec:authentication property="principal.usuarioClave.apellido2" /></dd>

                                <dt class="col-sm-4"><fmt:message key="usuario.dni"/></dt>
                                <dd class="col-sm-8"><sec:authentication property="principal.usuarioClave.nif" /></dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

