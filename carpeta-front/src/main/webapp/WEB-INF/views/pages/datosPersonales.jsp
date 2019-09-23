<%@include file="/WEB-INF/views/includes.jsp"%>


<nav>

    <div class="row">

        <!-- Datos Personales -->
        <div class="col-md-6 mb-4">
            <div class="card border-left-primary shadow py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="font-weight-bold text-primary text-uppercase mb-3 text-center"><fmt:message key="menu.datos.personales"/></div>
                            <div class="h5 mb-0 text-gray-800">
                                <ul class="dadesRegistre">
                                    <li><strong><fmt:message key="usuario.nombre"/>: </strong><sec:authentication property="principal.usuarioClave.nombre" /></li>
                                    <li><strong><fmt:message key="usuario.apellido1"/>: </strong><sec:authentication property="principal.usuarioClave.apellido1" /></li>
                                    <li><strong><fmt:message key="usuario.apellido2"/>: </strong><sec:authentication property="principal.usuarioClave.apellido2" /></li>
                                    <li><strong><fmt:message key="usuario.dni"/>: </strong><sec:authentication property="principal.usuarioClave.nif" /></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

</nav>

