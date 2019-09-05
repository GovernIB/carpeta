<%@include file="/WEB-INF/views/includes.jsp"%>

<%--Miga de pan--%>
<%--<nav aria-label="breadcrumb">--%>
<%--    <ol class="breadcrumb">--%>
<%--        <li class="breadcrumb-item"><a href="<c:url value="/"/>"><fmt:message key="menu.inicio"/></a></li>--%>
<%--        <li class="breadcrumb-item"><a href="<c:url value="/registros"/>"><fmt:message key="menu.registros"/></a></li>--%>
<%--        <li class="breadcrumb-item active" aria-current="page"><fmt:message key="registro.registro"/> ${asiento.numeroRegistroFormateado}</li>--%>
<%--    </ol>--%>
<%--</nav>--%>


    <div class="row">

        <!-- Registro Información -->
        <div class="col-md-6 mb-4">
            <div class="card border-left-primary shadow py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-3 tituloPanel"><fmt:message key="registro.tipo.${asiento.tipoRegistro}"/> ${asiento.numeroRegistroFormateado}</div>
                            <div class="h5 mb-0 text-gray-800">
                                <ul class="dadesRegistre">
                                    <li><strong><fmt:message key="registro.detalle.fecha"/>: </strong><fmt:formatDate value="${asiento.fechaRegistro}" pattern="dd/MM/yyyy HH:mm"/></li>
                                    <li><strong><fmt:message key="registro.detalle.oficina"/>: </strong>${asiento.entidadRegistralInicioDenominacion}</li>
                                    <li><strong><fmt:message key="registro.detalle.organismo"/>: </strong>${asiento.unidadTramitacionDestinoDenominacion}</li>
                                    <li><strong><fmt:message key="registro.detalle.extracto"/>: </strong>${asiento.resumen}</li>
                                    <li><strong><fmt:message key="registro.detalle.idioma"/>: </strong><fmt:message key="idioma.${asiento.idioma}"/></li>
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
                            <div class="text-xs font-weight-bold text-warning text-uppercase mb-3 tituloPanel"><fmt:message key="registro.detalle.interesados"/></div>
                            <div class="row no-gutters align-items-center">
                                <div class="h5 mb-0 mr-3 text-gray-800">
                                    <ul class="dadesRegistre">
                                        <c:forEach items="${asiento.interesados}" var="interesado" varStatus="index">
                                            <li>${interesado.interesado.nombre} ${interesado.interesado.apellido1} ${interesado.interesado.apellido2} (<fmt:message key="interesado.tipo.${interesado.interesado.tipoInteresado}"/>)</li>
                                            <c:if test="${not empty interesado.representante.nombre}">
                                                <li class="sinEstiloList"><span class="textoDestacado"><fmt:message key="registro.detalle.representante"/>:</span> ${interesado.representante.nombre} ${interesado.representante.apellido1} ${interesado.representante.apellido2} (<fmt:message key="interesado.tipo.${interesado.representante.tipoInteresado}"/>)</li>
                                            </c:if>
                                        </c:forEach>
                                    </ul>
                                </div>
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
                            <div class="text-xs font-weight-bold text-success text-uppercase mb-3 tituloPanel"><fmt:message key="registro.detalle.anexos"/></div>
                            <div class="h5 mb-0 text-gray-800">
                                <ul class="dadesRegistre">

                                    <!-- No hay anexos -->
                                    <c:if test="${empty asiento.anexos}">
                                        <div class="row">
                                            <p><fmt:message key="anexo.vacio"/></p>
                                        </div>
                                    </c:if>

                                    <c:if test="${not empty asiento.anexos}">

                                        <!-- Si hay anexos -->
                                        <c:if test="${justificante == null && fn:length(asiento.anexos) >= 1}">
                                            <c:forEach items="${asiento.anexos}" var="anexo" varStatus="index">
                                                <c:if test="${anexo.tipoDocumental != 'TD99'}">
                                                    <li>${anexo.titulo} (<fmt:message key="anexo.origen.${anexo.origenCiudadanoAdmin}"/>)</li>
                                                    <li class="sinEstiloList">${anexo.csv}</li>
                                                    <li class="sinEstiloList">${anexo.observaciones}</li>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>

                                        <!-- Solo existe el Justificante -->
                                        <c:if test="${justificante != null && fn:length(asiento.anexos) == 1}">
                                            <div class="row">
                                                <p><fmt:message key="anexo.vacio"/></p>
                                            </div>
                                        </c:if>

                                    </c:if>
                                </ul>
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
                    <div class="row no-gutters">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-danger text-uppercase mb-3 tituloPanel"><fmt:message key="registro.detalle.justificante"/></div>
                            <div class="h5 mb-0 text-gray-800 centrado">
                                <!-- Tiene justificante creado -->
                                <c:if test="${justificante != null}">
<%--                                <a href="#" class="d-sm-inline-block btn btn-sm btn-danger shadow-sm"><span class="oi oi-data-transfer-download" title="" alt="" aria-hidden="true"></span> <fmt:message key="registro.detalle.descargar"/></a>--%>
                                    <button type="button" class="d-sm-inline-block btn btn-sm btn-danger shadow-sm" onclick="goTo('${justificante.url}')"><span class="oi oi-data-transfer-download" title="" alt="" aria-hidden="true"></span> <fmt:message key="registro.detalle.descargar"/></button>
                                </c:if>
                                <!-- No tiene justificante creado -->
                                <c:if test="${justificante == null}">
                                    <fmt:message key="registro.justificante.vacio"/>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

