  <c:if test="${empty preguntesFrequentsItems}">
     <%@include file="preguntesFrequentsListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty preguntesFrequentsItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="preguntesFrequentsListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="preguntesFrequentsListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="preguntesFrequentsListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="preguntesFrequents" items="${preguntesFrequentsItems}">

        <tr id="preguntesFrequents_rowid_${preguntesFrequents.preguntesFrequentsID}">
          <%@include file="preguntesFrequentsListCoreMultipleSelect.jsp" %>

          <%@include file="preguntesFrequentsListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="preguntesFrequentsListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
