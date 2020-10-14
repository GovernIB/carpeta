<%@page import="es.caib.carpeta.utils.Version"%>
<%@page import="es.caib.carpeta.utils.Configuracio"
%><%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<footer id="footer">
	<div class="row mr-auto ml-3 mr-3 peuResponsive">
		<!-- Esquerra -->
		<div class="col-4 pt-2 elementPeuResponsive">
			<strong class="font-weight-bold h6">
				${version.projectName}
				v${version.version}<%=Configuracio.isCAIB() ? "-caib" : ""%>
		    
			</strong> <br/>
			<small>
			Build: ${version.buildTime} <br /> JDK:
			${version.jdkVersion} <br />

			<fmt:message key="revisio" />: 
			<c:if test="${empty version.scmRevision}">
				<fmt:message key="scmversion.msg" />
			</c:if>
			<c:if test="${not empty version.scmRevision}">${version.scmRevision}</c:if>
			<br />
			<span style="padding-top: 2px">
			 <i><fmt:message key="desenvolupatper" /></i></span>
			 </small>
		</div>

		<!-- Centre esquerra -->
		<div
			class="col-4 text-center pt-2 text-decoration-none bg-transparent text-uppercase p-2 opcionsPeu elementPeuResponsive">

			<a styleClass="text-dark linkPeu" href="<c:url value="/mapaweb"/>"> <fmt:message
					key="labels.mapaweb" />
			</a><br /> <a styleClass="text-dark linkPeu" href="<c:url value="/accessibilitat"/>">
				<fmt:message key="labels.accessibilitat" />
			</a><br /> <a styleClass="text-dark linkPeu" href="<c:url value="/protecciodades"/>">
				<fmt:message key="labels.protecciodades" />
			</a><br /> <a styleClass="text-dark linkPeu" href="<c:url value="/avislegal"/>"> <fmt:message
					key="labels.avislegal" />
			</a>

		</div>

		<!-- Dreta -->
		<div class="col-4 text-center logoPeu" >
		  <br/>
		  <center>
		    <sec:authorize access="hasRole('ROLE_ADMIN')">
		    <c:if test="${not empty loginInfo.entitat.logoPeuBack}">
		      <%-- <a href="http://otaeweb.ibit.org/" style="padding-top: 10px" target="_blank"> --%> 
                  <img class="logoAplicacio" src="<c:url value="${car:fileUrl(loginInfo.entitat.logoPeuBack)}"/>" alt="${loginInfo.entitat.logoPeuBack.nom}"  title="${loginInfo.entitat.codi}" />
              <%-- </a> --%><br />
                </c:if>
			</sec:authorize>

			<!-- Button to trigger modal -->
			<small><a href="#modalAjuda" role="button"
				data-toggle="modal" data-target="#modalAjuda"><fmt:message key="ajuda.necessitau" /></a>
		    </small>

		  </center>
		</div>

	</div>


	<!-- Modal -->
	<div id="modalAjuda" class="modal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title"><fmt:message key="ajuda.titol" /></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
            <p>
				<fmt:message key="ajuda.missatge" />
			</p>
			<ul>
				<li><fmt:message key="ajuda.viatelefon" /> 123456789</li>
				<li><fmt:message key="ajuda.viaweb" /> http://www.help.hl/help</li>
				<li><fmt:message key="ajuda.viaemail" /><a
					href="mailto: help@help.hl"> help@help.hl</a></li>
			</ul>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="tancar" /></button>
      </div>
    </div>
  </div>
</div>

</footer>