<%@include file="/WEB-INF/views/includes.jsp"%>

<c:if test="${empty registros}">
    <nav>
        <p><fmt:message key="registro.vacio"/></p>
    </nav>
</c:if>

<c:if test="${not empty registros}">

    <!-- Lista registros -->
    <div class="card mb-12 border-0">
        <h5 class="card-title"><fmt:message key="registro.listado"/></h5>

        <div class="card-body">
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

   <%-- <c:if test="${paginacion.totalPages > 1}">
        <c:url value="/registro/list/1" var="firstUrl" />
        <c:url value="/registro/list/${paginacion.totalPages}" var="lastUrl"/>
        <c:url value="/registro/list/${paginacion.currentIndex - 1}" var="prevUrl"/>
        <c:url value="/registro/list/${paginacion.currentIndex + 1}" var="nextUrl" />

        <div class="row mt-20">
&lt;%&ndash;            <div class="col-sm-12 col-md-5">&ndash;%&gt;
&lt;%&ndash;                <div class="dataTables_info izq" id="dataTable_info" role="status" aria-live="polite">&ndash;%&gt;
&lt;%&ndash;                    <fmt:message key="carpeta.pagina"/> ${paginacion.currentIndex} de ${paginacion.totalPages}&ndash;%&gt;
&lt;%&ndash;                </div>&ndash;%&gt;
&lt;%&ndash;            </div>&ndash;%&gt;

            <div class="col-sm-12 col-md-12">
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
    </c:if>--%>

</c:if>
