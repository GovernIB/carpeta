  <c:if test="${empty notificacioAppItems}">
     <%@include file="notificacioAppListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty notificacioAppItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="notificacioAppListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="notificacioAppListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="notificacioAppListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="notificacioApp" items="${notificacioAppItems}">

        <tr id="notificacioApp_rowid_${notificacioApp.notificacioAppID}">
          <%@include file="notificacioAppListCoreMultipleSelect.jsp" %>

          <%@include file="notificacioAppListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="notificacioAppListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
