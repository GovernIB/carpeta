<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${pluginEntitatFilterForm.contexte}"/>
  <c:set var="formName" value="pluginEntitat" />
  <c:set var="__theFilterForm" value="${pluginEntitatFilterForm}" />
  <c:if test="${empty pluginEntitatFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="pluginEntitat.pluginEntitat"/>
  </c:if>
  <c:if test="${not empty pluginEntitatFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${pluginEntitatFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty pluginEntitatFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="pluginEntitat.pluginEntitat"/>
  </c:if>
  <c:if test="${not empty pluginEntitatFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${pluginEntitatFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.pluginEntitat.submit();  
  }
</script>
