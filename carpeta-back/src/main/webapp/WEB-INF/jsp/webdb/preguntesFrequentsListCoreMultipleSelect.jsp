      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${preguntesFrequents.preguntesFrequentsID}"/>
       &nbsp;
      </td>
      </c:if>

