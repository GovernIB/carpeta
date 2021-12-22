  <c:if test="${empty enllazItems}">
     <%@include file="enllazListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty enllazItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="enllazListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="enllazListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="enllazListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="enllaz" items="${enllazItems}">

        <tr id="enllaz_rowid_${enllaz.enllazID}">
          <%@include file="enllazListCoreMultipleSelect.jsp" %>

          <%@include file="enllazListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="enllazListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
