  <c:if test="${empty ciutadaItems}">
     <%@include file="ciutadaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty ciutadaItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="ciutadaListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="ciutadaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="ciutadaListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="ciutada" items="${ciutadaItems}">

        <tr id="ciutada_rowid_${ciutada.ciutadaID}">
          <%@include file="ciutadaListCoreMultipleSelect.jsp" %>

          <%@include file="ciutadaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="ciutadaListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
