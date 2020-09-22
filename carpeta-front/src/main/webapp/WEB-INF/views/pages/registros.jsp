<%@include file="/WEB-INF/views/includes.jsp"%>

<div class="card mb-12 border-0 p-2">

    <div class="card-title border-bottom verde h5 paddingBottomEstandard"><fmt:message key="registro.listado"/></div>

    <c:if test="${empty registros}">
        <div class="alert alert-secondary mt-5" role="alert">
            <fmt:message key="registro.vacio"/>
        </div>
    </c:if>

    <c:if test="${not empty registros}">

        <p class="card-text lh15"><spring:message code="registro.descripcion.1"/></p>

        <div class="card-body tablaRegistros">

            <div class="table-responsive">
                <table id="dataTable_paginate_registro" class="table table-striped table-bordered table-hover" style="width:100%">
                    <thead class="table-success">
                    <tr>
                        <th scope="col"><fmt:message key="registro.numero"/></th>
                        <th scope="col"><fmt:message key="registro.fecha"/></th>
                        <th scope="col"><fmt:message key="registro.detalle.extracto"/></th>
                        <th scope="col" class="quitarMovil"><fmt:message key="registro.organismo"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${registros}" var="asiento" varStatus="index">

                        <tr class="clickable-row" data-target="" data-href="<c:url value="/registro/detalle/${asiento.numeroRegistroFormateado}"/>">
                            <td>
                                <label class="ponerMovil" data-toggle="tooltip" data-placement="top" title="${asiento.numeroRegistroFormateado} - ${asiento.unidadTramitacionDestinoDenominacion}">
                                        ${asiento.numeroRegistroFormateado}
                                </label>
                                <p class="quitarMovil">${asiento.numeroRegistroFormateado}</p>
                            </td>
                            <td><fmt:formatDate value="${asiento.fechaRegistro}" pattern="dd/MM/yyyy HH:mm"/></td>
                            <td>
                                <c:if test="${fn:length(asiento.resumen) > 30}">
                                    <label data-toggle="tooltip" data-placement="top" title="${asiento.resumen}">
                                        ${fn:substring(asiento.resumen, 0, 30)}...
                                    </label>
                                </c:if>
                                <c:if test="${fn:length(asiento.resumen) <= 30}">
                                    ${asiento.resumen}
                                </c:if>
                            </td>
                            <td class="quitarMovil">${asiento.unidadTramitacionDestinoDenominacion}</td>
                        </tr>

                    </c:forEach>

                    </tbody>
                </table>
            </div>

        </div>

        <script type="text/javascript">
            $(document).ready(function() {
                $.fn.dataTable.moment('D/MM/YYY HH:mm');
                $('#dataTable_paginate_registro').DataTable({
                    "order": [[ 1, "desc" ]],
                    "language": {
                        <c:if test="${pageContext.response.locale.language == 'ca'}">
                        "url": "<c:url value="/static/i18n/Catalan.json"/>"
                        </c:if>
                        <c:if test="${pageContext.response.locale.language == 'es'}">
                        "url": "<c:url value="/static/i18n/Spanish.json"/>"
                        </c:if>
                    },
                    "lengthMenu": [ 5, 10, 25, 50 ],
                    "pageLength": 5
                });
            } );

            $(function () {
                $('[data-toggle="tooltip"]').tooltip()
            })
        </script>

    </c:if>

    <c:if test="${not empty tramites}">
        <div class="mt-5 mb-5">
<%--            <p class="card-text lh15"><spring:message code="registro.descripcion.1"/></p>--%>
            <p class="card-text lh15"><fmt:message key="tramite.descripcion.registro"></fmt:message> </p>

            <div class="card-body tablaTramites">

                <div class="table-responsive">
                    <table id="dataTable_paginate_tramite" class="table table-striped table-bordered table-hover" style="width:100%">
                        <thead class="table-success">
                        <tr>
                            <th scope="col"><fmt:message key="tramite.tramite"/></th>
                            <th scope="col"><fmt:message key="tramite.fecha.inicio"/></th>
    <%--                        <th scope="col"><fmt:message key="registro.detalle.extracto"/></th>--%>
    <%--                        <th scope="col" class="quitarMovil"><fmt:message key="registro.organismo"/></th>--%>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${tramites}" var="tramite" varStatus="index">

                            <tr class="clickable-row" data-target="" data-href="<c:url value="${tramite.url}"/>">
                                <td>${tramite.descripcionTramite}</td>
                                <td><fmt:formatDate value="${tramite.fechaInicio}" pattern="dd/MM/yyyy HH:mm"/></td>
                            </tr>

                        </c:forEach>

                        </tbody>
                    </table>
                </div>

            </div>
        </div>

        <script type="text/javascript">
            $(document).ready(function() {
                $.fn.dataTable.moment('D/MM/YYY HH:mm');
                $('#dataTable_paginate_tramite').DataTable({
                    "order": [[ 1, "desc" ]],
                    "language": {
                        <c:if test="${pageContext.response.locale.language == 'ca'}">
                        "url": "<c:url value="/static/i18n/CatalanTramites.json"/>"
                        </c:if>
                        <c:if test="${pageContext.response.locale.language == 'es'}">
                        "url": "<c:url value="/static/i18n/SpanishTramites.json"/>"
                        </c:if>
                    },
                    "lengthMenu": [ 5, 10, 25, 50 ],
                    "pageLength": 5
                });
            } );

            $(function () {
                $('[data-toggle="tooltip"]').tooltip()
            })
        </script>
<%--        <script>--%>
<%--            $('#dataTable_paginate_tramite .clickable-row').each(function(event) {--%>
<%--                event.preventDefault();--%>
<%--                var $th = $(this);--%>
<%--                $th.on('click', function() {--%>
<%--                    window.open($th.attr('data-href'), $th.attr('data-target'));--%>
<%--                });--%>
<%--            });--%>
<%--        </script>--%>

    </c:if>

</div>
