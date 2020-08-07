<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${accesFilterForm.contexte}"/>
  <c:set var="formName" value="acces" />
  <c:set var="__theFilterForm" value="${accesFilterForm}" />
  <c:if test="${empty accesFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="acces.acces"/>
  </c:if>
  <c:if test="${not empty accesFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${accesFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty accesFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="acces.acces"/>
  </c:if>
  <c:if test="${not empty accesFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${accesFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.acces.submit();  
  }
</script>
