<%@page import="es.caib.carpeta.commons.utils.Version"%>
<%@page import="es.caib.carpeta.commons.utils.Configuracio"
%><%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<%
  Version v = new Version();
  v.init();
  session.setAttribute("versio", v);
%>

<footer id="footer">
	<div class="row mr-auto ml-3 mr-3 peuResponsive">
		<!-- Esquerra -->
		<div class="col-4 pt-2 elementPeuResponsive">
			<strong class="font-weight-bold h6">
				${version.projectName}
				v${version.version}<%=Configuracio.isCAIB() ? "-caib" : ""%>
		    
			</strong> <br/>
			<small>
			Build: ${versio.buildTime} <br /> JDK:
			${version.jdkVersion} <br />

			<fmt:message key="revisio" />: 
			<c:if test="${empty versio.scmRevision}">
				<fmt:message key="scmversion.msg" />
			</c:if>
			<c:if test="${not empty versio.scmRevision}">${versio.scmRevision}</c:if>
			<br />
			<span style="padding-top: 2px">
			 <i><fmt:message key="desenvolupatper" /></i></span>
			 </small>
		</div>

		<!-- Centre esquerra -->
		<div
			class="col-4 text-center pt-2 text-decoration-none bg-transparent text-uppercase p-2 opcionsPeu elementPeuResponsive">

			<a styleClass="text-dark linkPeu" href="/mapaweb"> <fmt:message
					key="labels.mapaweb" />
			</a><br /> <a styleClass="text-dark linkPeu" href="/accessibilitat">
				<fmt:message key="labels.accessibilitat" />
			</a><br /> <a styleClass="text-dark linkPeu" href="/protecciodades">
				<fmt:message key="labels.protecciodades" />
			</a><br /> <a styleClass="text-dark linkPeu" href="/avislegal"> <fmt:message
					key="labels.avislegal" />
			</a>

		</div>

		<!-- Dreta -->
		<div class="col-4 text-center" >
		  <center>
			<a href="http://otaeweb.ibit.org/" style="padding-top: 10px" target="_blank"> <img
				src="<c:url value="/img/fundaciobit-logo-peu.png"/>"
				alt="Fundacio Bit" />
			</a> <br />

			<!-- Button to trigger modal -->
			<small><a href="#modalAjuda" role="button"
				data-toggle="modal"><fmt:message key="ajuda.necessitau" /></a></small>
		  </center>
		</div>

	</div>

	<!-- Modal -->
	<div id="modalAjuda" class="modal hide fade" tabindex="-1"
		role="dialog" aria-labelledby="<fmt:message key="ajuda.titol" />"
		aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="myModalLabel">
				<fmt:message key="ajuda.titol" />
			</h3>
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
	</div>
</footer>