<%@include file="/WEB-INF/views/includes.jsp"%>
<div class="container contenido">

    <c:if test="${empty tramites}">
        <div class="row">
            <p><fmt:message key="tramite.vacio"/></p>
        </div>
    </c:if>

    <c:if test="${not empty tramites}">
        <div class="row">
            <p>
                <fmt:message key="tramites.resultados">
                    <fmt:param value="${fn:length(tramites)}"/>
                </fmt:message>
            </p>
        </div>

        <!-- Lista trámites -->
        <div class="row">

            <div class="list-group col-12">

                <c:forEach items="${tramites}" var="tramite" varStatus="index">

                    <a href="<c:url value="/tramite/${tramite.idSesionTramitacion}"/>" class="list-group-item list-group-item-action flex-column align-items-start cajaTramite">
                        <div class="d-flex w-100">
                            <span class="numLista">${index.count}</span>
                            <h5 class="mb-1 verde col-10 listVerde">${tramite.descripcionTramite}</h5>
                            <small></small>
                        </div>
                        <p class="mb-1 col-12 infoList">
                            <span><fmt:message key="tramite.id"/>: <strong>${tramite.idTramite}</strong></span>
                        </p>
                        <p class="mb-1 col-12 infoList">
                            <span><fmt:message key="tramite.fecha"/>: <strong><fmt:formatDate value="${tramite.fechaInicio}" pattern="dd/MM/yyyy HH:mm"/></strong></span>
                        </p>
                        <p class="mb-1 col-12 infoList">
                            <span><fmt:message key="tramite.acceso"/>: <strong><fmt:formatDate value="${tramite.fechaUltimoAcceso}" pattern="dd/MM/yyyy HH:mm"/></strong></span>
                        </p>
                    </a>

                </c:forEach>

            </div>

        </div>

        <!-- Paginación -->
        <div class="row mt-20">
            <div class="col-sm-12 col-md-5">
                <div class="dataTables_info izq" id="dataTable_info" role="status" aria-live="polite">
                    <fmt:message key="carpeta.mostrar"/> 1 de 10 de ${fn:length(tramites)} <fmt:message key="tramite.tramites"/>
                </div>
            </div>
            <div class="col-sm-12 col-md-7">
                <div class="dataTables_paginate paging_simple_numbers der" id="dataTable_paginate">
                    <ul class="pagination">
                        <li class="paginate_button page-item previous disabled" id="dataTable_previous">
                            <a href="#" aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link"><fmt:message key="carpeta.previo"/></a>
                        </li>
                        <li class="paginate_button page-item active">
                            <a href="#" aria-controls="dataTable" data-dt-idx="1" tabindex="0" class="page-link">1</a>
                        </li>
                        <li class="paginate_button page-item ">
                            <a href="#" aria-controls="dataTable" data-dt-idx="2" tabindex="0" class="page-link">2</a>
                        </li>
                        <li class="paginate_button page-item ">
                            <a href="#" aria-controls="dataTable" data-dt-idx="3" tabindex="0" class="page-link">3</a>
                        </li>
                        <li class="paginate_button page-item ">
                            <a href="#" aria-controls="dataTable" data-dt-idx="4" tabindex="0" class="page-link">4</a>
                        </li>
                        <li class="paginate_button page-item ">
                            <a href="#" aria-controls="dataTable" data-dt-idx="5" tabindex="0" class="page-link">5</a>
                        </li>
                        <li class="paginate_button page-item ">
                            <a href="#" aria-controls="dataTable" data-dt-idx="6" tabindex="0" class="page-link">6</a>
                        </li>
                        <li class="paginate_button page-item next" id="dataTable_next">
                            <a href="#" aria-controls="dataTable" data-dt-idx="7" tabindex="0" class="page-link"><fmt:message key="carpeta.proximo"/></a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

    </c:if>

</div>