  <c:if test="${empty usuariItems}">
     <%@include file="usuariListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty usuariItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="usuariListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="usuariListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="usuariListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="usuari" items="${usuariItems}">

        <tr id="usuari_rowid_${usuari.usuariID}">
          <%@include file="usuariListCoreMultipleSelect.jsp" %>

          <%@include file="usuariListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="usuariListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
