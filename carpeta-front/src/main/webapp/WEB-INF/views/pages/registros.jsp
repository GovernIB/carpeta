<%@include file="/WEB-INF/views/includes.jsp"%>
<div class="container contenido">

    <c:if test="${empty paginacion.listado}">
        <div class="row">
            <p><fmt:message key="registro.vacio"/></p>
        </div>
    </c:if>

    <c:if test="${not empty paginacion.listado}">
        <div class="row">
            <p>
                <fmt:message key="registro.resultados">
                    <fmt:param value="${paginacion.totalResults}"/>
                </fmt:message>
            </p>
        </div>

        <!-- Lista registros -->
        <div class="row">

            <div class="list-group col-12">

                <c:forEach items="${paginacion.listado}" var="asiento" varStatus="index">

                    <%--Registro Entrada--%>
                    <c:if test="${asiento.tipoRegistro == 1}">

                        <a href="<c:url value="/registreDetall"/>" class="list-group-item list-group-item-action flex-column align-items-start cajaRegistro">
                            <div class="d-flex w-100">
                                <span class="numLista">${index.count}</span>
                                <h5 class="mb-1 verde col-10 listVerde"><fmt:message key="registro.tipo.${asiento.tipoRegistro}"/>: ${asiento.numeroRegistroFormateado}</h5>
                                <small><fmt:formatDate value="${asiento.fechaRegistro}" pattern="dd/MM/yyyy HH:mm"/></small>
                            </div>
                            <p class="mb-1 col-12 infoList">
                                <span><fmt:message key="registro.extracto"/>: <strong>${asiento.resumen}</strong></span>
                            </p>
                            <p class="mb-1 col-12 infoList">
                                <span><fmt:message key="registro.oficina"/>: <strong>${asiento.entidadRegistralInicioDenominacion}</strong></span>
                            </p>
                            <p class="mb-1 col-12 infoList">
                                <span><fmt:message key="registro.organismo"/>: <strong>${asiento.unidadTramitacionDestinoDenominacion}</strong></span>
                            </p>
                        </a>

                    </c:if>

                </c:forEach>

            </div>

        </div>

        <c:if test="${paginacion.totalPages > 1}">
            <c:url value="/registros/1" var="firstUrl" />
            <c:url value="/registros/${paginacion.totalPages}" var="lastUrl"/>
            <c:url value="/registros/${paginacion.currentIndex - 1}" var="prevUrl"/>
            <c:url value="/registros/${paginacion.currentIndex + 1}" var="nextUrl" />

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
                                <c:url var="pageUrl" value="/registros/${i}" />
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

</div>