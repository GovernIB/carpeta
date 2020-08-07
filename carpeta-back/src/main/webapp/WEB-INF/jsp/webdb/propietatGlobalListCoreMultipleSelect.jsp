      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${propietatGlobal.propietaGlobalID}"/>
       &nbsp;
      </td>
      </c:if>

