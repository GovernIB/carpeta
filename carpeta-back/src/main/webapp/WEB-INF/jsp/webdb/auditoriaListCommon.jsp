<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${auditoriaFilterForm.contexte}"/>
  <c:set var="formName" value="auditoria" />
  <c:set var="__theFilterForm" value="${auditoriaFilterForm}" />
  <c:if test="${empty auditoriaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="auditoria.auditoria"/>
  </c:if>
  <c:if test="${not empty auditoriaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${auditoriaFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty auditoriaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="auditoria.auditoria"/>
  </c:if>
  <c:if test="${not empty auditoriaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${auditoriaFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.auditoria.submit();  
  }
</script>
