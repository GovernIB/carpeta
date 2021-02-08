<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${seccioFilterForm.contexte}"/>
  <c:set var="formName" value="seccio" />
  <c:set var="__theFilterForm" value="${seccioFilterForm}" />
  <c:if test="${empty seccioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="seccio.seccio"/>
  </c:if>
  <c:if test="${not empty seccioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${seccioFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty seccioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="seccio.seccio"/>
  </c:if>
  <c:if test="${not empty seccioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${seccioFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.seccio.submit();  
  }
</script>
