<%@include file="/WEB-INF/views/includes.jsp"%>

<%--Miga de pan--%>
<nav aria-label="breadcrumb">
    <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="<c:url value="/"/>"><fmt:message key="menu.inicio"/></a></li>
        <li class="breadcrumb-item"><a href="<c:url value="/registros"/>"><fmt:message key="menu.registros"/></a></li>
        <li class="breadcrumb-item active" aria-current="page"><fmt:message key="registro.registro"/> ${asiento.numeroRegistroFormateado}</li>
    </ol>
</nav>

<div class="container contenido">

    <div class="row">

        <!-- Registro Información -->
        <div class="col-md-6 mb-4">
            <div class="card border-left-primary shadow py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-3"><fmt:message key="registro.detalle.nom"/></div>
                            <div class="h5 mb-0 text-gray-800">
                                <ul class="dadesRegistre">
                                    <li><strong><fmt:message key="registro.detalle.fecha"/> </strong><fmt:message key="registro.detalle.fecha.valor"/></li>
                                    <li><strong><fmt:message key="registro.detalle.oficina"/> </strong><fmt:message key="registro.detalle.oficina.valor"/></li>
                                    <li><strong><fmt:message key="registro.detalle.organismo"/> </strong><fmt:message key="registro.detalle.organismo.valor"/></li>
                                    <li><strong><fmt:message key="registro.detalle.extracto"/> </strong><fmt:message key="registro.detalle.extracto.valor"/></li>
                                    <li><strong><fmt:message key="registro.detalle.idioma"/> </strong><fmt:message key="idioma.catala"/></li>
                                    <li><strong><fmt:message key="registro.detalle.tipo"/> </strong><fmt:message key="registro.detalle.tipo.valor"/></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Registro Anexos -->
        <div class="col-md-6 mb-4">
            <div class="card border-left-success shadow py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-success text-uppercase mb-3"><fmt:message key="registro.detalle.anexos"/></div>
                            <div class="h5 mb-0 text-gray-800">
                                <ul class="dadesRegistre">
                                    <li><fmt:message key="registro.detalle.anexos.1"/></li>
                                    <li><fmt:message key="registro.detalle.anexos.2"/></li>
                                    <li><fmt:message key="registro.detalle.anexos.3"/></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Registro Interesados -->
        <div class="col-md-6 mb-4">
            <div class="card border-left-warning shadow py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-warning text-uppercase mb-3"><fmt:message key="registro.detalle.interesados"/></div>
                            <div class="row no-gutters align-items-center">
                                <div class="h5 mb-0 mr-3 text-gray-800">
                                    <ul class="dadesRegistre">
                                        <li><fmt:message key="registro.detalle.interesados.1"/> (<strong><fmt:message key="registro.detalle.representante"/></strong>: <fmt:message key="registro.detalle.representante.1"/>)</li>
                                        <li><fmt:message key="registro.detalle.interesados.2"/></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Registro Justificante -->
        <div class="col-md-6 mb-4">
            <div class="card border-left-danger shadow py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-danger text-uppercase mb-3"><fmt:message key="registro.detalle.justificante"/></div>
                            <div class="h5 mb-0 text-gray-800">
                                <a href="#" class="d-sm-inline-block btn btn-sm btn-danger shadow-sm"><span class="oi oi-data-transfer-download" title="" alt="" aria-hidden="true"></span> <fmt:message key="registro.detalle.descargar"/></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

</div>