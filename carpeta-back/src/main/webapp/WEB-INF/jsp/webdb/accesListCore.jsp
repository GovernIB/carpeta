  <c:if test="${empty accesItems}">
     <%@include file="accesListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty accesItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="accesListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="accesListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="accesListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="acces" items="${accesItems}">

        <tr id="acces_rowid_${acces.accesID}">
          <%@include file="accesListCoreMultipleSelect.jsp" %>

          <%@include file="accesListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="accesListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
