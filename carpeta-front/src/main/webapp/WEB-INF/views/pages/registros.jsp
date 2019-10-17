<%@include file="/WEB-INF/views/includes.jsp"%>

<c:if test="${empty registros}">
    <nav>
        <p><fmt:message key="registro.vacio"/></p>
    </nav>
</c:if>

<c:if test="${not empty registros}">

    <!-- Lista registros -->
    <div class="card mb-12 border-0">
        <h5 class="card-title border-bottom verde"><fmt:message key="registro.listado"/></h5>

        <div class="card-body">

            <p class="card-text mb-5"><spring:message code="registro.descripcion"/></p>

            <div class="table-responsive">
                <table id="dataTable_paginate" class="table table-striped table-bordered table-hover" style="width:100%">
                    <thead class="table-success">
                        <tr>
                            <th scope="col"><fmt:message key="registro.numero"/></th>
                            <th scope="col"><fmt:message key="registro.fecha"/></th>
                            <th scope="col"><fmt:message key="registro.oficina"/></th>
                            <th scope="col"><fmt:message key="registro.organismo"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${registros}" var="asiento" varStatus="index">

                            <tr class="clickable-row" data-target="_blank" data-href="<c:url value="/registro/detalle/${asiento.numeroRegistroFormateado}"/>">
                                <td>${asiento.numeroRegistroFormateado}</td>
                                <td><fmt:formatDate value="${asiento.fechaRegistro}" pattern="dd/MM/yyyy HH:mm"/></td>
                                <td>${asiento.entidadRegistralInicioDenominacion}</td>
                                <td>${asiento.unidadTramitacionDestinoDenominacion}</td>
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
            $('#dataTable_paginate').DataTable({
                "order": [[ 1, "desc" ]],
                "language": {
                    <c:if test="${pageContext.response.locale.language == 'ca'}">
                    "url": "<c:url value="/static/i18n/Catalan.json"/>"
                    </c:if>
                    <c:if test="${pageContext.response.locale.language == 'es'}">
                    "url": "<c:url value="/static/i18n/Spanish.json"/>"
                    </c:if>
                }
            });
        } );
    </script>

</c:if>