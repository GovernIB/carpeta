  <c:if test="${empty pluginEntitatItems}">
     <%@include file="pluginEntitatListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty pluginEntitatItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="pluginEntitatListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="pluginEntitatListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="pluginEntitatListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="pluginEntitat" items="${pluginEntitatItems}">

        <tr id="pluginEntitat_rowid_${pluginEntitat.pluginEntitatID}">
          <%@include file="pluginEntitatListCoreMultipleSelect.jsp" %>

          <%@include file="pluginEntitatListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="pluginEntitatListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
