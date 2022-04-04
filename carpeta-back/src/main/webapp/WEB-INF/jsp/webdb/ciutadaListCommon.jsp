<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${ciutadaFilterForm.contexte}"/>
  <c:set var="formName" value="ciutada" />
  <c:set var="__theFilterForm" value="${ciutadaFilterForm}" />
  <c:if test="${empty ciutadaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="ciutada.ciutada"/>
  </c:if>
  <c:if test="${not empty ciutadaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${ciutadaFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty ciutadaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="ciutada.ciutada"/>
  </c:if>
  <c:if test="${not empty ciutadaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${ciutadaFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.ciutada.submit();  
  }
</script>
