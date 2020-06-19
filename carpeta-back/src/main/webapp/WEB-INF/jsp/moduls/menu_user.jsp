<%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


      <a class="dropdown-item" href="<c:url value="/user/option1"/>">
        <span style="${(fn:contains(url, 'option1'))? "font-weight: bold;" : ""}">Menú USER Option 1</span>
      </a>
  

    <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
    
      <a class="dropdown-item"  href="<c:url value="/user/option2"/>">
        <span style="${(fn:contains(url, 'option2'))? "font-weight: bold;" : ""}">Menú USER Option 2</span>
      </a>
    

