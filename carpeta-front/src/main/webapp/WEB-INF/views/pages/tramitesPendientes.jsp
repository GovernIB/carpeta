<%@include file="/WEB-INF/views/includes.jsp"%>

<c:if test="${empty tramites}">
    <nav>
        <p><fmt:message key="tramite.vacio"/></p>
    </nav>
</c:if>

<c:if test="${not empty tramites}">

     <!-- Lista trámites -->
    <div class="card mb-12 border-0">
        <h5 class="card-title border-bottom verde"><fmt:message key="tramite.listado"/></h5>
        <div class="card-body">

            <table id="dataTable_paginate" class="table table-striped table-bordered table-hover" style="width:100%">
                <thead class="table-success">
                    <tr>
                        <th><fmt:message key="tramite.tramite"/></th>
                        <th><fmt:message key="tramite.fecha.inicio"/></th>
                        <th><fmt:message key="tramite.acceso"/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${tramites}" var="tramite" varStatus="index">
                        <tr class="clickable-row" data-target="_blank" data-href="<c:url value="/tramite/sistra${tramite.sistra}/${tramite.idSesionTramitacion}"/>">
                            <td>${tramite.descripcionTramite}</td>
                            <td><fmt:formatDate value="${tramite.fechaInicio}" pattern="dd/MM/yyyy HH:mm"/></td>
                            <td><fmt:formatDate value="${tramite.fechaUltimoAcceso}" pattern="dd/MM/yyyy HH:mm"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </div>
    </div>

    <script type="text/javascript">
        $(document).ready(function() {
            $('#dataTable_paginate').DataTable({
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
