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


                <table id="dataTable_paginate" class="table table-striped table-bordered table-hover" style="width:100%">
                    <thead class="table-success">
                    <tr>
                        <th><fmt:message key="comunicacion.descripcion.comunicacion"/></th>
                        <th><fmt:message key="comunicacion.fecha"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${comunicacions}" var="com" varStatus="index">
                        <tr style="cursor: pointer" class="" data-target="_blank" data-href="<c:url value="${com.url}"/>">
                            <td>${com.descripcion}</td>
                            <td><fmt:formatDate value="${com.fecha.toGregorianCalendar().time}" pattern="dd/MM/yyyy HH:mm"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>

    </div>

</div>
<script>
    $('tr').click(function() {
        var url = $(this).attr('data-href');
        // var url = $(this).find('.url_sistra').html();
        url = url.replace(/&amp;/g, "&");
        window.open(url, '_blank');
    });
</script>
