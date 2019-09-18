<%@include file="/WEB-INF/views/includes.jsp"%>

<c:if test="${empty tramites}">
    <nav>
        <p><fmt:message key="tramite.vacio"/></p>
    </nav>
</c:if>

<c:if test="${not empty tramites}">

     <!-- Lista trámites -->
    <div class="card mb-12 sinBorde">
        <div class="card-header cabeceraTabla"><fmt:message key="tramite.listado"/></div>
        <div class="card-body">

            <table id="dataTable_paginate" class="table table-striped table-bordered table-hover" style="width:100%">
                <thead>
                    <tr>
                        <th><fmt:message key="tramite.tramite"/></th>
                        <th><fmt:message key="tramite.id"/></th>
                        <th><fmt:message key="tramite.fecha"/></th>
                        <th><fmt:message key="tramite.acceso"/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${tramites}" var="tramite" varStatus="index">
                        <tr class="clickable-row" data-href="<c:url value="/tramite/sistra${tramite.sistra}/${tramite.idSesionTramitacion}"/>">
                            <td>${tramite.descripcionTramite}</td>
                            <td>${tramite.idTramite}</td>
                            <td><fmt:formatDate value="${tramite.fechaInicio}" pattern="dd/MM/yyyy HH:mm"/></td>
                            <td><fmt:formatDate value="${tramite.fechaUltimoAcceso}" pattern="dd/MM/yyyy HH:mm"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
</c:if>
