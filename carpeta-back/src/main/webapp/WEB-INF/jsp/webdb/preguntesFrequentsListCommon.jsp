<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${preguntesFrequentsFilterForm.contexte}"/>
  <c:set var="formName" value="preguntesFrequents" />
  <c:set var="__theFilterForm" value="${preguntesFrequentsFilterForm}" />
  <c:if test="${empty preguntesFrequentsFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="preguntesFrequents.preguntesFrequents"/>
  </c:if>
  <c:if test="${not empty preguntesFrequentsFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${preguntesFrequentsFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty preguntesFrequentsFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="preguntesFrequents.preguntesFrequents"/>
  </c:if>
  <c:if test="${not empty preguntesFrequentsFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${preguntesFrequentsFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.preguntesFrequents.submit();  
  }
</script>
