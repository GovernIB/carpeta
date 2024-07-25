  <c:if test="${empty auditoriaItems}">
     <%@include file="auditoriaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty auditoriaItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp table-genapp-list" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="auditoriaListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="auditoriaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="auditoriaListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="auditoria" items="${auditoriaItems}">

        <tr id="auditoria_rowid_${auditoria.auditoriaID}">
          <%@include file="auditoriaListCoreMultipleSelect.jsp" %>

          <%@include file="auditoriaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="auditoriaListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
