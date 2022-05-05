      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${notificacioApp.notificacioAppID}"/>
       &nbsp;
      </td>
      </c:if>

