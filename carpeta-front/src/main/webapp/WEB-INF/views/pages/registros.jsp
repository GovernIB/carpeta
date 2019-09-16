<%@include file="/WEB-INF/views/includes.jsp"%>

<c:if test="${empty paginacion.listado}">
    <nav>
        <p><fmt:message key="registro.vacio"/></p>
    </nav>
</c:if>

<c:if test="${not empty paginacion.listado}">

    <div class="alert alert-light" role="alert">
        <fmt:message key="registro.resultados"><strong><fmt:param value="${paginacion.totalResults}"/></strong></fmt:message>
        <p class="der"><fmt:message key="carpeta.pagina"/> ${paginacion.currentIndex} de ${paginacion.totalPages}</p>
    </div>

    <!-- Lista registros -->

    <div class="card bg-light mb-12">
        <div class="card-header"><fmt:message key="registro.listado"/></div>

        <div class="card-body">
            <div class="table-responsive">
                <table class="table">
                    <thead class="thead-light">
                        <tr>
                            <th scope="col"><fmt:message key="registro.fecha"/></th>
                            <th scope="col"><fmt:message key="registro.numero"/></th>
                            <th scope="col"><fmt:message key="registro.oficina"/></th>
                            <th scope="col"><fmt:message key="registro.organismo"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${paginacion.listado}" var="asiento" varStatus="index">

                            <tr class="clickable-row" data-href="<c:url value="/registro/detalle/${asiento.numeroRegistroFormateado}"/>">
                                <td><fmt:formatDate value="${asiento.fechaRegistro}" pattern="dd/MM/yyyy HH:mm"/></td>
                                <td>${asiento.numeroRegistroFormateado}</td>
                                <td>${asiento.entidadRegistralInicioDenominacion}</td>
                                <td>${asiento.unidadTramitacionDestinoDenominacion}</td>
                            </tr>

                        </c:forEach>

                    </tbody>
                </table>
            </div>

        </div>
    </div>

    <c:if test="${paginacion.totalPages > 1}">
        <c:url value="/registro/list/1" var="firstUrl" />
        <c:url value="/registro/list/${paginacion.totalPages}" var="lastUrl"/>
        <c:url value="/registro/list/${paginacion.currentIndex - 1}" var="prevUrl"/>
        <c:url value="/registro/list/${paginacion.currentIndex + 1}" var="nextUrl" />

        <div class="row mt-20">
            <div class="col-sm-12 col-md-5">
                <div class="dataTables_info izq" id="dataTable_info" role="status" aria-live="polite">
                    <fmt:message key="carpeta.pagina"/> ${paginacion.currentIndex} de ${paginacion.totalPages}
                </div>
            </div>

            <div class="col-sm-12 col-md-7">
                <div class="dataTables_paginate paging_simple_numbers der" id="dataTable_paginate">
                    <ul class="pagination">

                        <c:choose>
                            <c:when test="${paginacion.currentIndex == 1}">
                            </c:when>
                            <c:otherwise>
                                <li class="paginate_button page-item previous" id="dataTable_previous">
                                    <a href="${prevUrl}" aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link"><fmt:message key="carpeta.previo"/></a>
                                </li>
                            </c:otherwise>
                        </c:choose>

                        <c:forEach var="i" begin="${paginacion.beginIndex}" end="${paginacion.endIndex}">
                            <c:url var="pageUrl" value="/registro/list/${i}" />
                            <li class="paginate_button page-item <c:if test="${i == paginacion.currentIndex}">active</c:if>">
                                <a href="${pageUrl}" aria-controls="dataTable" data-dt-idx="1" tabindex="0" class="page-link">${i}</a>
                            </li>
                        </c:forEach>

                        <c:choose>
                            <c:when test="${paginacion.currentIndex == paginacion.totalPages}">
                            </c:when>
                            <c:otherwise>
                                <li class="paginate_button page-item next" id="dataTable_next">
                                    <a href="${nextUrl}" aria-controls="dataTable" data-dt-idx="7" tabindex="0" class="page-link"><fmt:message key="carpeta.proximo"/></a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </div>

        </div>
    </c:if>

</c:if>
