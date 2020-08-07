<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${enllazFilterForm.contexte}"/>
  <c:set var="formName" value="enllaz" />
  <c:set var="__theFilterForm" value="${enllazFilterForm}" />
  <c:if test="${empty enllazFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="enllaz.enllaz"/>
  </c:if>
  <c:if test="${not empty enllazFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${enllazFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty enllazFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="enllaz.enllaz"/>
  </c:if>
  <c:if test="${not empty enllazFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${enllazFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.enllaz.submit();  
  }
</script>
