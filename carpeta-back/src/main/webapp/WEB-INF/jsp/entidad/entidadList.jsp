<%@ page language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"
%>
<div class="clear"></div>
<div class="spacer"></div>

<center><h1><spring:message code="entidad.list"/></h1></center>


<a class="btn btn-success btn-xs pull-right" href="<c:url value="/entidad/new"/>" role="button"><span class="fa fa-plus"></span> <spring:message code="entidad.nuevo"/></a>



<c:if test="${not empty listado}">


    <div class="table-responsive">
    <table class="table table-bordered table-hover table-striped tablesorter">
    <colgroup>
    <col>
    <col>
    <col>
    <col>
    <col width="200">
    </colgroup>
    <thead>
    <tr>
    <th><spring:message code="carpeta.nombre"/></th>
    <th><spring:message code="entidad.codigoDir3"/></th>
    <th><spring:message code="entidad.activa"/></th>
    <th class="center"><spring:message code="carpeta.acciones"/></th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="entidad" items="${listado}">
        <tr>
            <td>${entidad.traduccion.nombre}</td>
            <td>${entidad.codigoDir3}</td>
            <td>
                <c:if test="${entidad.activa}"><span class="label label-success">Si</span></c:if>
                <c:if test="${not entidad.activa}"><span class="label label-danger">No</span></c:if>
            </td>
            <td class="center">
                <c:if test="${entidad.activa}">
                    <a class="btn btn-warning btn-sm" href="<c:url value="/entidad/${entidad.id}/edit"/>" title="<spring:message code="carpeta.editar"/>"><span class="fa fa-pencil"></span></a>
                    <a class="btn btn-danger btn-sm" onclick='confirm("<c:url value="/entidad/${entidad.id}/anular"/>","<spring:message code="entidad.confirmar.anular" htmlEscape="true"/>")' href="javascript:void(0);" title="<spring:message code="entidad.anular"/>"><span class="fa fa-thumbs-o-down"></span></a>
                    <a class="btn btn-danger btn-sm" onclick='confirm("<c:url value="/entidad/${entidad.id}/eliminar"/>","<spring:message code="entidad.confirmar.eliminar" htmlEscape="true"/>")' href="javascript:void(0);" title="<spring:message code="entidad.eliminar"/>"><span class="fa fa fa-eraser"></span></a>
                </c:if>
                <c:if test="${not entidad.activa}">
                    <a class="btn btn-primary btn-sm" onclick='confirm("<c:url value="/entidad/${entidad.id}/activar"/>","<spring:message code="entidad.confirmar.activar" htmlEscape="true"/>")' href="javascript:void(0);" title="<spring:message code="entidad.activar"/>"><span class="fa fa-thumbs-o-up"></span></a>
                </c:if>

            </td>
        </tr>
    </c:forEach>


    </tbody>
    </table>
    </div>

</c:if>


<br/>
<br/>
<br/>
<br/>
<br/>
<br/>


<center></center>