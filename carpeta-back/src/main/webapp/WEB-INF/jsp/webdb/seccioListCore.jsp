  <c:if test="${empty seccioItems}">
     <%@include file="seccioListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty seccioItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-condensed table-bordered table-striped" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="seccioListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="seccioListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="seccioListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="seccio" items="${seccioItems}">

        <tr id="seccio_rowid_${seccio.seccioID}">
          <%@include file="seccioListCoreMultipleSelect.jsp" %>

          <%@include file="seccioListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="seccioListButtons.jsp" %>

            
        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
