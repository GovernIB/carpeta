<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${logCarpetaFilterForm.contexte}"/>
  <c:set var="formName" value="logCarpeta" />
  <c:set var="__theFilterForm" value="${logCarpetaFilterForm}" />
  <c:if test="${empty logCarpetaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="logCarpeta.logCarpeta"/>
  </c:if>
  <c:if test="${not empty logCarpetaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${logCarpetaFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty logCarpetaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="logCarpeta.logCarpeta"/>
  </c:if>
  <c:if test="${not empty logCarpetaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${logCarpetaFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.logCarpeta.submit();  
  }
</script>
