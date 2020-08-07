      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${auditoria.auditoriaID}"/>
       &nbsp;
      </td>
      </c:if>

