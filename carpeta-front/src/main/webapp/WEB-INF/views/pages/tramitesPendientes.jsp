<%@include file="/WEB-INF/views/includes.jsp"%>


<div class="card mb-12 border-0">
    <div class="card-title border-bottom verde h5"><fmt:message key="tramite.listado"/></div>
    <div class="card-body tablaRegistros">
        <p class="card-text mb-5"><spring:message code="tramite.descripcion"/></p>

        <form:form method="post" modelAttribute="fechaBusqueda">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <form:label path="fechaInicio" placeholder="dd/mm/yyyy"><spring:message code="carpeta.fecha.inicio"/></form:label>
                        <div class="input-group">
                            <form:input path="fechaInicio" maxlength="10" cssClass="form-control form-control-sm"/><span class="input-group-text"><span class="oi oi-calendar"></span></span>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <form:label path="fechaFin" placeholder="dd/mm/yyyy"><spring:message code="carpeta.fecha.fin"/></form:label>
                        <div class="input-group">
                            <form:input path="fechaFin" maxlength="10" cssClass="form-control form-control-sm"/><span class="input-group-text"><span class="oi oi-calendar"></span></span >
                        </div>
                    </div>
                </div>
            </div>
            <button type="submit" class="btn btn-carpeta"><spring:message code="carpeta.buscar"/></button>
        </form:form>

        <c:if test="${tramites != null && empty tramites}">
            <div class="alert alert-secondary mt-5" role="alert">
                <fmt:message key="tramite.vacio"/>
            </div>
        </c:if>

        <div class="mt-5">

            <c:if test="${not empty tramites}">

                <p class="card-text mb-5"><spring:message code="tramite.continuar"/></p>

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
        </div>
    </div>

</div>


