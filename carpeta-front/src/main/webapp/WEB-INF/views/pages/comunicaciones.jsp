<%@include file="/WEB-INF/views/includes.jsp"%>


<div class="card mb-12 border-0 p-2">
    <div class="card-title border-bottom verde h5 paddingBottomEstandard"><fmt:message key="comunicacion.listado"/></div>

    <p class="card-text mb-5 lh15"><spring:message code="comunicacion.descripcion"/></p>

    <div class="card-body tablaRegistros">
        <c:if test="${comunicacions != null && empty comunicacions}">
            <div class="alert alert-secondary mt-5" role="alert">
                <fmt:message key="comunicacion.vacio"/>
            </div>
        </c:if>

        <div class="mt-0">

            <c:if test="${not empty comunicacions}">

                <p class="card-text mb-5"><spring:message code="comunicacion.continuar"/></p>

                <table id="dataTable_paginate" class="table table-striped table-bordered table-hover" style="width:100%">
                    <thead class="table-success">
                    <tr>
                        <th><fmt:message key="comunicacion.tipo"/></th>
                        <th><fmt:message key="comunicacion.descripcion.comunicacion"/></th>
                        <th><fmt:message key="comunicacion.fecha"/></th>
                        <th><fmt:message key="comunicacion.pendiente"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${comunicacions}" var="com" varStatus="index">
                        <tr class="clickable-row" data-target="_blank" data-href="<c:url value="${com.url}"/>">
                            <td>${com.tipo}</td>
                            <td>${com.descripcion}</td>
                            <td><fmt:formatDate value="${com.fecha.toGregorianCalendar().time}" pattern="dd/MM/yyyy HH:mm"/></td>
                            <td>${com.pendiente}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <%--        <form:form method="post" modelAttribute="fechaBusqueda">--%>
            <%--            <div class="row">--%>
            <%--                <div class="col-md-6">--%>
            <%--                    <div class="form-group">--%>
            <%--                        <form:label path="fechaInicio" placeholder="dd/mm/yyyy"><spring:message code="carpeta.fecha.inicio"/></form:label>--%>
            <%--                        <div class="input-group">--%>
            <%--                            <form:input path="fechaInicio" maxlength="10" cssClass="form-control form-control-sm" required="required"/><span class="input-group-text"><span class="oi oi-calendar"></span></span>--%>
            <%--                        </div>--%>
            <%--                    </div>--%>
            <%--                </div>--%>
            <%--                <div class="col-md-6">--%>
            <%--                    <div class="form-group">--%>
            <%--                        <form:label path="fechaFin" placeholder="dd/mm/yyyy"><spring:message code="carpeta.fecha.fin"/></form:label>--%>
            <%--                        <div class="input-group">--%>
            <%--                            <form:input path="fechaFin" maxlength="10" cssClass="form-control form-control-sm" required="required"/><span class="input-group-text"><span class="oi oi-calendar"></span></span >--%>
            <%--                        </div>--%>
            <%--                    </div>--%>
            <%--                </div>--%>
            <%--            </div>--%>
            <%--            <button type="submit" class="btn btn-primary carpeta-btn"><spring:message code="carpeta.buscar"/></button>--%>
            <%--        </form:form>--%>

            <%--        <c:if test="${tramites != null && empty tramites}">--%>
            <%--            <div class="alert alert-secondary mt-5" role="alert">--%>
            <%--                <fmt:message key="tramite.vacio"/>--%>
            <%--            </div>--%>
            <%--        </c:if>--%>

            <%--        <div class="mt-5">--%>

            <%--            <c:if test="${not empty tramites}">--%>

            <%--                <p class="card-text mb-5"><spring:message code="tramite.continuar"/></p>--%>

            <%--                <table id="dataTable_paginate" class="table table-striped table-bordered table-hover" style="width:100%">--%>
            <%--                    <thead class="table-success">--%>
            <%--                    <tr>--%>
            <%--                        <th><fmt:message key="tramite.tramite"/></th>--%>
            <%--                        <th><fmt:message key="tramite.fecha.inicio"/></th>--%>
            <%--                        <th><fmt:message key="tramite.acceso"/></th>--%>
            <%--                    </tr>--%>
            <%--                    </thead>--%>
            <%--                    <tbody>--%>
            <%--                    <c:forEach items="${tramites}" var="tramite" varStatus="index">--%>
            <%--                        <tr class="clickable-row" data-target="_blank" data-href="<c:url value="/tramite/sistra${tramite.sistra}/${tramite.idSesionTramitacion}"/>">--%>
            <%--                            <td>${tramite.descripcionTramite}</td>--%>
            <%--                            <td><fmt:formatDate value="${tramite.fechaInicio}" pattern="dd/MM/yyyy HH:mm"/></td>--%>
            <%--                            <td><fmt:formatDate value="${tramite.fechaUltimoAcceso}" pattern="dd/MM/yyyy HH:mm"/></td>--%>
            <%--                        </tr>--%>
            <%--                    </c:forEach>--%>
            <%--                    </tbody>--%>
            <%--                </table>--%>

            <%--                <script type="text/javascript">--%>
            <%--                    $(document).ready(function() {--%>
            <%--                        $.fn.dataTable.moment('D/MM/YYY HH:mm');--%>
            <%--                        $('#dataTable_paginate').DataTable({--%>
            <%--                            "order": [[ 1, "desc" ]],--%>
            <%--                            "language": {--%>
            <%--                                <c:if test="${pageContext.response.locale.language == 'ca'}">--%>
            <%--                                "url": "<c:url value="/static/i18n/Catalan.json"/>"--%>
            <%--                                </c:if>--%>
            <%--                                <c:if test="${pageContext.response.locale.language == 'es'}">--%>
            <%--                                "url": "<c:url value="/static/i18n/Spanish.json"/>"--%>
            <%--                                </c:if>--%>
            <%--                            }--%>
            <%--                        });--%>
            <%--                    } );--%>
            <%--                </script>--%>
            <%--            </c:if>--%>
            <%--        </div>--%>
        </div>

    </div>

</div>
