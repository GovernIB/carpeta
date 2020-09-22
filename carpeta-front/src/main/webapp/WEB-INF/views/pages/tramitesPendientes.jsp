<%@include file="/WEB-INF/views/includes.jsp"%>


<div class="card mb-12 border-0 p-2">
    <div class="card-title border-bottom verde h5 paddingBottomEstandard"><fmt:message key="tramite.listado"/></div>

    <p class="card-text mb-5 lh15"><spring:message code="tramite.descripcion"/></p>

    <div class="card-body tablaRegistros">

        <form:form method="post" modelAttribute="fechaBusqueda">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <form:label path="fechaInicio" placeholder="dd/mm/yyyy"><spring:message code="carpeta.fecha.inicio"/></form:label>
                        <div class="input-group">
                            <form:input path="fechaInicio" maxlength="10" cssClass="form-control form-control-sm" required="required"/><span class="input-group-text"><span class="oi oi-calendar"></span></span>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <form:label path="fechaFin" placeholder="dd/mm/yyyy"><spring:message code="carpeta.fecha.fin"/></form:label>
                        <div class="input-group">
                            <form:input path="fechaFin" maxlength="10" cssClass="form-control form-control-sm" required="required"/><span class="input-group-text"><span class="oi oi-calendar"></span></span >
                        </div>
                    </div>
                </div>
            </div>
            <button type="submit" class="btn btn-primary carpeta-btn"><spring:message code="carpeta.buscar"/></button>
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

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${tramites}" var="tramite" varStatus="index">
                        <c:choose>
                            <c:when test="${tramite.sistra == 2}">
                                <tr class="clickable-row" data-target="_blank" data-href="<c:url value="/tramite/sistra${tramite.sistra}/${tramite.idSesionTramitacion}"/>">
                                    <td>${tramite.descripcionTramite}</td>
                                    <td><fmt:formatDate value="${tramite.fechaInicio}" pattern="dd/MM/yyyy HH:mm"/></td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <tr <c:if test="${tramite.sistra == 0}">class="" data-toggle="modal" data-target="#aviso" style="cursor: pointer" </c:if>
                                    <c:if test="${tramite.sistra == 1}">class="clickable-row" data-target="_blank" data-href="<c:url value="${tramite.url}"/>"</c:if>
<%--                                    class="clickable-row" data-target="_blank" data-href="<c:url value="${tramite.url}"/>"--%>
                                >
                                    <td>${tramite.descripcionTramite}</td>
                                    <td><fmt:formatDate value="${tramite.fechaInicio}" pattern="dd/MM/yyyy HH:mm"/></td>
                                    <td class="url_elemento" style="display: none">${tramite.url}</td>
                                </tr>
                            </c:otherwise>

                        </c:choose>

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
                                "url": "<c:url value="/static/i18n/CatalanTramites.json"/>"
                                </c:if>
                                <c:if test="${pageContext.response.locale.language == 'es'}">
                                "url": "<c:url value="/static/i18n/SpanishTramites.json"/>"
                                </c:if>
                            }
                        });
                    } );
                </script>
            </c:if>
        </div>
    </div>
    <!-- The Modal -->
    <div class="modal fade" id="aviso">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title"><fmt:message key="modal.tramitacio.titulo"></fmt:message></h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <fmt:message key="modal.tramitacio.cuerpo"></fmt:message>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <a target="_blank" class="detalle_elemento btn btn-success" href=""><fmt:message key="modal.tramitacio.detalle"></fmt:message></a>
                    <button type="button" class="btn btn-danger" data-dismiss="modal"><fmt:message key="modal.tramitacio.cerrar"></fmt:message></button>
                </div>

            </div>
        </div>
    </div>
    <script>
        $('tr').click(function() {
           var url = $(this).find('.url_elemento').html();
           url = url.replace(/&amp;/g, "&");
           $('.detalle_elemento').attr('href', url);
        });
    </script>
<%--    <script>--%>
<%--        $('#dataTable_paginate .clickable-row').each(function(event) {--%>
<%--            event.preventDefault();--%>
<%--            var $th = $(this);--%>
<%--            $th.on('click', function() {--%>
<%--                window.open($th.attr('data-href'), $th.attr('data-target'));--%>
<%--            });--%>
<%--        });--%>
<%--    </script>--%>
</div>


