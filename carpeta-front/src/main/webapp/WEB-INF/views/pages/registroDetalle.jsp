<%@include file="/WEB-INF/views/includes.jsp"%>

<c:if test="${empty asiento}">
    <div class="card mb-12 border-0">
        <div class="card-title h5"><fmt:message key="registro.detalle.error"/> ${numeroRegistro}</div>
    </div>
</c:if>

<c:if test="${not empty asiento}">

    <div class="card mb-12 border-0">
        <div class="card-title border-bottom verde h5"><fmt:message key="registro.detalle"/> ${asiento.numeroRegistroFormateado}</div>

        <div class="card-body">

            <div class="row">

                <div class="pri-col-deta-reg">
                    <!-- Información Registro -->
                    <div class="card border-left-carpeta shadow py-2 mb-3">

                        <div class="card-body">
                            <div class="row no-gutters align-items-center">
                                <div class="col mr-2">
                                    <div class="font-weight-bold verde text-uppercase mb-3 text-center"><fmt:message key="registro.tipo.${asiento.tipoRegistro}"/></div>

                                    <dl class="row">
                                        <dt class="col-sm-3"><fmt:message key="registro.detalle.fecha"/></dt>
                                        <dd class="col-sm-9"><fmt:formatDate value="${asiento.fechaRegistro}" pattern="dd/MM/yyyy HH:mm"/></dd>

                                        <dt class="col-sm-3"><fmt:message key="registro.numero"/></dt>
                                        <dd class="col-sm-9">${asiento.numeroRegistroFormateado}</dd>

                                        <dt class="col-sm-3"><fmt:message key="registro.detalle.oficina"/></dt>
                                        <dd class="col-sm-9">${asiento.entidadRegistralInicioDenominacion}</dd>

                                        <dt class="col-sm-3"><fmt:message key="registro.detalle.organismo"/></dt>
                                        <dd class="col-sm-9">${asiento.unidadTramitacionDestinoDenominacion}</dd>

                                        <dt class="col-sm-3"><fmt:message key="registro.detalle.tipoDocumentacion"/></dt>
                                        <dd class="col-sm-9"><fmt:message key="registro.detalle.tipoDocumentacion.${asiento.tipoDocumentacionFisicaCodigo}"/></dd>

                                        <dt class="col-sm-3"><fmt:message key="registro.detalle.extracto"/></dt>
                                        <dd class="col-sm-9">${asiento.resumen}</dd>

                                        <dt class="col-sm-3"><fmt:message key="registro.detalle.idioma"/></dt>
                                        <dd class="col-sm-9"><fmt:message key="idioma.${asiento.idioma}"/></dd>

                                        <dt class="col-sm-3"><fmt:message key="registro.detalle.presencial"/></dt>
                                        <dd class="col-sm-9">
                                            <c:if test="${asiento.isPresencial()}">Si</c:if>
                                            <c:if test="${not asiento.isPresencial()}">No</c:if>
                                        </dd>

                                        <%--Opcionales--%>
                                        <c:if test="${not empty asiento.codigoSia}">
                                            <dt class="col-sm-3"><fmt:message key="registro.detalle.codigoSia"/></dt>
                                            <dd class="col-sm-9">${asiento.codigoSia}</dd>
                                        </c:if>

                                        <c:if test="${not empty asiento.codigoAsuntoDenominacion}">
                                            <dt class="col-sm-3"><fmt:message key="registro.detalle.codigoAsunto"/></dt>
                                            <dd class="col-sm-9">${asiento.codigoAsuntoDenominacion}</dd>
                                        </c:if>

                                        <c:if test="${not empty asiento.referenciaExterna}">
                                            <dt class="col-sm-3"><fmt:message key="registro.detalle.refExterna"/></dt>
                                            <dd class="col-sm-9">${asiento.referenciaExterna}</dd>
                                        </c:if>

                                        <c:if test="${not empty asiento.numeroExpediente}">
                                            <dt class="col-sm-3"><fmt:message key="registro.detalle.expediente"/></dt>
                                            <dd class="col-sm-9">${asiento.numeroExpediente}</dd>
                                        </c:if>

                                        <c:if test="${not empty asiento.tipoTransporte}">
                                            <dt class="col-sm-3"><fmt:message key="registro.detalle.tipoTransporte"/></dt>
                                            <dd class="col-sm-9"><fmt:message key="registro.detalle.transporte.${asiento.tipoTransporte}"/></dd>
                                        </c:if>

                                        <c:if test="${not empty asiento.numeroTransporte}">
                                            <dt class="col-sm-3"><fmt:message key="registro.detalle.numeroTransporte"/></dt>
                                            <dd class="col-sm-9">${asiento.numeroTransporte}</dd>
                                        </c:if>

                                        <c:if test="${not empty asiento.observaciones}">
                                            <dt class="col-sm-3"><fmt:message key="registro.detalle.observaciones"/></dt>
                                            <dd class="col-sm-9">${asiento.observaciones}</dd>
                                        </c:if>

                                    </dl>

                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Justificante -->
                    <div class="card border-left-carpeta shadow py-2 mb-3">
                        <div class="card-body">
                            <div class="row no-gutters">
                                <div class="col mr-2">
                                    <div class="font-weight-bold verde text-uppercase mb-3 text-center"><fmt:message key="registro.detalle.justificante"/></div>

                                    <!-- Tiene justificante creado -->
                                    <c:if test="${justificante != null}">
                                        <div class="text-center">
                                            <button type="button" class="d-sm-inline-block btn btn-sm btn-danger shadow-sm" onclick="goToWindow('${justificante}')"><span class="oi oi-data-transfer-download" title="" alt="" aria-hidden="true"></span> <fmt:message key="registro.detalle.descargar"/></button>
                                        </div>

                                    </c:if>
                                    <!-- No tiene justificante creado -->
                                    <c:if test="${justificante == null}">
                                        <p class="text-center"><fmt:message key="registro.justificante.vacio"/></p>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="seg-col-deta-reg">

                    <!-- Interesados -->
                    <div class="card border-left-carpeta shadow py-2 mb-3">
                        <div class="card-body">
                            <div class="row no-gutters align-items-center">
                                <div class="col mr-2">
                                    <div class="font-weight-bold verde text-uppercase mb-3 text-center"><fmt:message key="registro.detalle.interesados"/></div>
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
                                                        <td>
                                                            <c:if test="${interesado.interesado.tipoInteresado == 2}">
                                                                ${interesado.interesado.nombre} ${interesado.interesado.apellido1} ${interesado.interesado.apellido2}
                                                            </c:if>
                                                            <c:if test="${interesado.interesado.tipoInteresado != 2}">
                                                                ${interesado.interesado.razonSocial}
                                                            </c:if>
                                                        </td>
                                                        <td>${interesado.interesado.documento}</td>
                                                        <td><fmt:message key="interesado.tipo.${interesado.interesado.tipoInteresado}"/></td>
                                                    </tr>
                                                    <c:if test="${interesado.representante != null}">
                                                        <tr>
                                                            <td class="font-weight-bold">R</td>
                                                            <td>
                                                                <c:if test="${interesado.representante.tipoInteresado == 2}">
                                                                    ${interesado.representante.nombre} ${interesado.representante.apellido1} ${interesado.representante.apellido2}
                                                                </c:if>
                                                                <c:if test="${interesado.representante.tipoInteresado != 2}">
                                                                    ${interesado.representante.razonSocial}
                                                                </c:if>
                                                            </td>
                                                            <td>${interesado.representante.documento}</td>
                                                            <td><fmt:message key="interesado.tipo.${interesado.representante.tipoInteresado}"/></td>
                                                        </tr>
                                                    </c:if>
                                                </c:forEach>
                                            </tbody>
                                        </table>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Anexos -->
                    <div class="card border-left-carpeta shadow py-2 mb-3">
                        <div class="card-body">
                            <div class="row no-gutters align-items-center">
                                <div class="col mr-2">
                                    <div class="font-weight-bold verde text-uppercase mb-3 text-center"><fmt:message key="registro.detalle.anexos"/></div>

                                    <!-- No hay anexos -->
                                    <c:if test="${empty asiento.anexos}">
                                        <p class="text-center"><fmt:message key="anexo.vacio"/></p>
                                    </c:if>

                                    <c:if test="${not empty asiento.anexos}">

                                        <!-- Si hay anexos -->
                                        <c:if test="${(justificante != null && fn:length(asiento.anexos) > 1) || (justificante == null && fn:length(asiento.anexos) > 0)}">

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

                    <!-- Expone - Solicita -->
                    <c:if test="${not empty asiento.expone || not empty asiento.solicita}">
                        <div class="card border-left-carpeta shadow py-2 mb-3">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">

                                        <c:if test="${not empty asiento.expone}">
                                            <div class="font-weight-bold verde text-uppercase mb-3 text-center"><fmt:message key="registro.detalle.expone"/></div>
                                            <div class="row no-gutters text-justify mb-4">${asiento.expone}</div>
                                        </c:if>

                                        <c:if test="${not empty asiento.solicita}">
                                            <div class="font-weight-bold verde text-uppercase mb-3 text-center"><fmt:message key="registro.detalle.solicita"/></div>
                                            <div class="row no-gutters text-justify">${asiento.solicita}</div>
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