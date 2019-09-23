<%@include file="/WEB-INF/views/includes.jsp"%>

<%--Miga de pan--%>
<%--<nav aria-label="breadcrumb">--%>
<%--    <ol class="breadcrumb">--%>
<%--        <li class="breadcrumb-item"><a href="<c:url value="/"/>"><fmt:message key="menu.inicio"/></a></li>--%>
<%--        <li class="breadcrumb-item"><a href="<c:url value="/registros"/>"><fmt:message key="menu.registros"/></a></li>--%>
<%--        <li class="breadcrumb-item active" aria-current="page"><fmt:message key="registro.registro"/> ${asiento.numeroRegistroFormateado}</li>--%>
<%--    </ol>--%>
<%--</nav>--%>

<c:if test="${empty asiento}">
    <div class="card mb-12 border-0">
        <h5 class="card-title">No ha sido posible obtener el Registro</h5>

    </div>
</c:if>

<c:if test="${not empty asiento}">

    <div class="card mb-12 border-0">
        <h5 class="card-title border-bottom verde"><fmt:message key="registro.detalle"/> ${asiento.numeroRegistroFormateado}</h5>

        <div class="card-body">

            <div class="row">

                <div class="col-5">
                    <!-- Información Registro -->
                    <div class="card border-left-primary shadow py-2 mb-3">
                        <%--<h5 class="card-title"><fmt:message key="registro.registro"/> ${asiento.numeroRegistroFormateado}</h5>--%>
                        <div class="card-body">
                            <div class="row no-gutters align-items-center">
                                <div class="col mr-2">
                                    <div class="font-weight-bold text-primary text-uppercase mb-3 text-center"><fmt:message key="registro.tipo.${asiento.tipoRegistro}"/></div>

                                        <ul class="dadesRegistre">
                                            <li><strong><fmt:message key="registro.detalle.fecha"/>: </strong><fmt:formatDate value="${asiento.fechaRegistro}" pattern="dd/MM/yyyy HH:mm"/></li>
                                            <li><strong><fmt:message key="registro.numero"/>: </strong>${asiento.numeroRegistroFormateado}</li>
                                            <li><strong><fmt:message key="registro.detalle.oficina"/>: </strong>${asiento.entidadRegistralInicioDenominacion}</li>
                                            <li><strong><fmt:message key="registro.detalle.organismo"/>: </strong>${asiento.unidadTramitacionDestinoDenominacion}</li>
                                            <li><strong><fmt:message key="registro.detalle.tipoDocumentacion"/>: </strong><fmt:message key="registro.detalle.tipoDocumentacion.${asiento.tipoDocumentacionFisicaCodigo}"/></li>
                                            <li><strong><fmt:message key="registro.detalle.extracto"/>: </strong>${asiento.resumen}</li>
                                            <li><strong><fmt:message key="registro.detalle.idioma"/>: </strong><fmt:message key="idioma.${asiento.idioma}"/></li>
                                            <li><strong><fmt:message key="registro.detalle.presencial"/>: </strong>
                                                <c:if test="${asiento.isPresencial()}">Si</c:if>
                                                <c:if test="${not asiento.isPresencial()}">No</c:if>
                                            </li>
                                            <c:if test="${not empty asiento.codigoSia}"> <li><strong><fmt:message key="registro.detalle.codigoSia"/>: </strong>${asiento.codigoSia}</li></c:if>
                                            <c:if test="${not empty asiento.codigoAsuntoDenominacion}"> <li><strong><fmt:message key="registro.detalle.codigoAsunto"/>: </strong>${asiento.codigoAsuntoDenominacion}</li></c:if>
                                            <c:if test="${not empty asiento.referenciaExterna}"> <li><strong><fmt:message key="registro.detalle.refExterna"/>: </strong>${asiento.referenciaExterna}</li></c:if>
                                            <c:if test="${not empty asiento.numeroExpediente}"> <li><strong><fmt:message key="registro.detalle.expediente"/>: </strong>${asiento.numeroExpediente}</li></c:if>
                                            <c:if test="${not empty asiento.tipoTransporte}"> <li><strong><fmt:message key="registro.detalle.tipoTransporte"/>: </strong><fmt:message key="registro.detalle.transporte.${asiento.tipoTransporte}"/></li></c:if>
                                            <c:if test="${not empty asiento.numeroTransporte}"> <li><strong><fmt:message key="registro.detalle.numeroTransporte"/>: </strong>${asiento.numeroTransporte}</li></c:if>
                                            <c:if test="${not empty asiento.observaciones}"> <li><strong><fmt:message key="registro.detalle.observaciones"/>: </strong>${asiento.observaciones}</li></c:if>
                                        </ul>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-7">

                    <!-- Interesados -->
                    <div class="card border-left-warning shadow py-2 mb-3">
                        <div class="card-body">
                            <div class="row no-gutters align-items-center">
                                <div class="col mr-2">
                                    <div class="font-weight-bold text-warning text-uppercase mb-3 text-center"><fmt:message key="registro.detalle.interesados"/></div>
                                    <div class="row no-gutters align-items-center">

                                        <table class="table table-hover">
                                            <thead>
                                                <tr>
                                                    <th scope="col">#</th>
                                                    <th scope="col"><fmt:message key="interesado.nombre"/></th>
                                                    <th scope="col"><fmt:message key="interesado.documento"/></th>
                                                    <th scope="col"><fmt:message key="interesado.tipo"/></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${asiento.interesados}" var="interesado" varStatus="status">

                                                    <tr>
                                                        <td>${status.count}</td>
                                                        <td>${interesado.interesado.nombre} ${interesado.interesado.apellido1} ${interesado.interesado.apellido2}</td>
                                                        <td>${interesado.interesado.documento}</td>
                                                        <td><fmt:message key="interesado.tipo.${interesado.interesado.tipoInteresado}"/></td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Anexos -->
                    <div class="card border-left-success shadow py-2 mb-3">
                        <div class="card-body">
                            <div class="row no-gutters align-items-center">
                                <div class="col mr-2">
                                    <div class="font-weight-bold text-success text-uppercase mb-3 text-center"><fmt:message key="registro.detalle.anexos"/></div>

                                    <!-- No hay anexos -->
                                    <c:if test="${empty asiento.anexos}">
                                        <div class="row text-center">
                                            <p><fmt:message key="anexo.vacio"/></p>
                                        </div>
                                    </c:if>

                                    <c:if test="${not empty asiento.anexos}">

                                        <!-- Si hay anexos -->
                                        <c:if test="${justificante == null && fn:length(asiento.anexos) >= 1}">

                                            <table class="table table-hover">
                                                <thead>
                                                    <tr>
                                                        <th scope="col">#</th>
                                                        <th scope="col"><fmt:message key="anexo.titulo"/></th>
                                                        <th scope="col"><fmt:message key="anexo.mime"/></th>
                                                        <th scope="col"><fmt:message key="anexo.csv"/></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${asiento.anexos}" var="anexo" varStatus="status">
                                                        <c:if test="${anexo.tipoDocumental != 'TD99'}">
                                                            <tr>
                                                                <td>${status.count}</td>
                                                                <td>${anexo.titulo}</td>
                                                                <td>${anexo.tipoMIMEFicheroAnexado}</td>
                                                                <td>${anexo.csv}</td>
                                                            </tr>
                                                        </c:if>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </c:if>

                                        <!-- Solo existe el Justificante -->
                                        <c:if test="${justificante != null && fn:length(asiento.anexos) == 1}">
                                            <p class="text-center"><fmt:message key="anexo.vacio"/></p>
                                        </c:if>

                                    </c:if>

                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Justificante -->
                    <div class="card border-left-danger shadow py-2 mb-3">
                        <div class="card-body">
                            <div class="row no-gutters">
                                <div class="col mr-2">
                                    <div class="font-weight-bold text-danger text-uppercase mb-3 text-center"><fmt:message key="registro.detalle.justificante"/></div>

                                        <!-- Tiene justificante creado -->
                                        <c:if test="${justificante != null}">
                                            <%--                                <a href="#" class="d-sm-inline-block btn btn-sm btn-danger shadow-sm"><span class="oi oi-data-transfer-download" title="" alt="" aria-hidden="true"></span> <fmt:message key="registro.detalle.descargar"/></a>--%>
                                            <button type="button" class="d-sm-inline-block btn btn-sm btn-danger shadow-sm" onclick="goTo('${justificante.url}')"><span class="oi oi-data-transfer-download" title="" alt="" aria-hidden="true"></span> <fmt:message key="registro.detalle.descargar"/></button>
                                        </c:if>
                                        <!-- No tiene justificante creado -->
                                        <c:if test="${justificante == null}">
                                            <p class="text-center"><fmt:message key="registro.justificante.vacio"/></p>
                                        </c:if>

                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Expone - Solicita -->
                    <c:if test="${not empty asiento.expone || not empty asiento.solicita}">
                        <div class="card border-left-info shadow py-2 mb-3">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">

                                        <c:if test="${not empty asiento.expone}">
                                            <div class="font-weight-bold text-info text-uppercase mb-3 text-center"><fmt:message key="registro.detalle.expone"/></div>
                                            <div class="row no-gutters align-items-center mb-4">${asiento.expone}</div>
                                        </c:if>

                                        <c:if test="${not empty asiento.solicita}">
                                            <div class="font-weight-bold text-info text-uppercase mb-3 text-center"><fmt:message key="registro.detalle.solicita"/></div>
                                            <div class="row no-gutters align-items-center">${asiento.solicita}</div>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>

                </div>

            </div>
        </div>
    </div>

</c:if>


