  <c:if test="${empty logCarpetaItems}">
     <%@include file="logCarpetaListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty logCarpetaItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="logCarpetaListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="logCarpetaListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="logCarpetaListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="logCarpeta" items="${logCarpetaItems}">

        <tr id="logCarpeta_rowid_${logCarpeta.logID}">
          <%@include file="logCarpetaListCoreMultipleSelect.jsp" %>

          <%@include file="logCarpetaListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="logCarpetaListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
