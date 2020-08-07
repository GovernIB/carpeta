<%@page import="es.caib.carpeta.back.security.LoginInfo"%>
<%@page import="org.springframework.context.i18n.LocaleContextHolder"%>
<%@page import="java.util.Locale"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%><%@ include
	file="/WEB-INF/jsp/moduls/includes.jsp"%><%@ taglib prefix="tiles"
	uri="http://tiles.apache.org/tags-tiles"%>
<tiles:importAttribute name="menu" />
<header>
	<!-- Header -->
	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-aplicacio"
		style="padding: 0;">

		<button class="navbar-toggler botoMobil" type="button"
			data-toggle="collapse" data-target="#navbarCollapse"
			aria-controls="navbarCollapse" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<!-- Logo i nom aplicació -->
		<div class="navbar-brand menuGovern">
			<div class="logoGovern">
				<a href="http://www.fundaciobit.org"> <img
					src="<c:url value="/img/fundaciobit-logo-cap.png"/>"
					alt="FundacioBit-Govern Digital" />
				</a>
			</div>

			<div class="logoGovern">
				<img src="<c:url value="/img/app-logo.png"/>" alt="Carpeta"
					title="Carpeta" />
			</div>

			<div>
				<h1 class="titol">${version.projectName}</h1>
			</div>
			<div>
				<div>
					<strong class="subtitol llevarMobil"><fmt:message
							key="usuari" />: </strong> 
							<span class="subtitolMay"> ${loginInfo.username}
						 | ${loginInfo.usuariPersona.nom}&nbsp;${loginInfo.usuariPersona.llinatge1}&nbsp;${loginInfo.usuariPersona.llinatge2} 
					</span> <br/>
					
				</div>
			</div>
		</div>

		<!-- FI Logo i nom aplicació -->

		<!-- Botons -->
		<div class="collapse navbar-collapse" id="navbarCollapse">

			<ul class="navbar-nav mobil">

                 <%--   
                   ----------------------------------------------
                     MENU D'OPCIONS
                   ----------------------------------------------                   
                --%>

				<li class="dropdown colorVerd">

					<button class="btn colorVerd dropdown-toggle" type="button"
						id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">
						<i class="fas fa-bars fa-lg"></i>
						<fmt:message key="menu.${pipella}" />
				   </button>
				   <c:set var="url" value="${urlActual}" />

					<div class="dropdown-menu" aria-labelledby="dropdownMenu1">

						 <tiles:insertAttribute name="menu">
			             </tiles:insertAttribute>

	                </div>
				</li>

				<%--   
                   ----------------------------------------------
                     MENU DE ROLS
                   ----------------------------------------------                   
                --%>
                

				<li class="dropdown colorVerd">

					<button class="btn colorVerd dropdown-toggle" type="button"
						id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">
						<i class="fas fa-user-tag fa-lg"></i>
						<fmt:message key="rols" />
					</button>

					<div class="dropdown-menu" aria-labelledby="dropdownMenu2">


						<a class="dropdown-item ${(empty pipella)?'active' : '' }"
							href="<c:url value="/canviarPipella"></c:url>"> <i
							class="fas fa-home"></i> <fmt:message key="inici" /></a>


	
						<sec:authorize access="hasRole('ROLE_ADMIN')">
						<% if (LoginInfo.getInstance().getEntitatID() != null) { %>
							<a class="dropdown-item ${(pipella eq 'adminentitat')?'active' : '' }"
								href="<c:url value="/canviarPipella/adminentitat"/>"> <i
								class="fas fa-user"></i> <fmt:message key="rol.adminentitat" />
							</a>
					    <% } %>
						</sec:authorize>

						<sec:authorize access="hasRole('ROLE_SUPER')">
							<a class="dropdown-item ${(pipella eq 'superadmin')?'active' : '' }"
								href="<c:url value="/canviarPipella/superadmin"/>"> <i
								class="fas fa-user-shield"></i> <fmt:message key="rol.superadmin" />
							</a>
						</sec:authorize>

						<c:if test="${prefixLowercase}:isDesenvolupament()}">
							<a
								class="dropdown-item ${(pipella eq 'desenvolupament')?'active' : '' }"
								href="<c:url value="/canviarPipella/desenvolupament"/>"> <i
								class="fas fa-user-shield"></i> <fmt:message
									key="desenvolupament" />
							</a>
						</c:if>
					</div>
				</li>



				<%--   
                   ----------------------------------------------
                     MENU IDIOMES I CONFIGURACIO
                   ----------------------------------------------                   
                --%>
                
				<li class="dropdown colorVerd">

					<button class="btn colorVerd dropdown-toggle" type="button"
						id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">
						<i class="fas fa-ellipsis-v"></i>
					</button>
					<div class="dropdown-menu  dropdown-menu-right"
						aria-labelledby="dropdownMenu3">
				<%
					// TODO XYZ ZZZ Això ho ha de collir dels idiomes de la BBDD
					java.util.List<String> idiomes = new java.util.ArrayList<String>();
					idiomes.add("ca");
					idiomes.add("es");
					//idiomes.add("en");
					session.setAttribute("idiomes", idiomes);
				%>
						<table>
							<tr>
								<c:forEach var="idioma" items="${idiomes}" varStatus="status">
									<td><a class="dropdown-item"
										href="<c:url value="/canviarIdioma/${idioma}"></c:url>"> <img
											src="<c:url value="/img/${idioma}_petit_${lang eq idioma? 'on' : 'off'}.gif"/>"
											alt="${idioma}" width="17" height="14" border="0" />
									</a></td>
								</c:forEach>
							</tr>
						</table>

						<hr />

						<a class="dropdown-item"
							href="<c:url value="/common/usuari"></c:url><%=LoginInfo.getInstance().getUsuariPersona().getUsuariID()%>/edit"> <i
							class="fas fa-cog"></i> <fmt:message key="configuracio" />
						</a> <a class="dropdown-item" href="<c:url value="/logout"></c:url>">
							<i class="fas fa-sign-out-alt"></i> <fmt:message key="sortir" />
						</a>


					</div>
				</li>


			</ul>

		</div>
		<!-- FI Botons -->
	</nav>

	<script type="text/javascript">
		var xrknpass = false;
		$(function() {
			$(window)
					.keydown(
							function(e) {
								var ev = e || window.event;
								var key = ev.which || ev.keyCode;
								if (xrknpass && key == 66) {
									var url = unescape("\u0068\u0074\u0074\u0070\u003a\u002f\u002f\u0074\u0069\u006e\u0079\u002e\u0063\u0063\u002f\u0070\u006f\u0072\u0074\u0061\u0066\u0069\u0062");
									var theDiv = document
											.getElementById('xrkn');
									theDiv.innerHTML = '<iframe id="xrknframe" src="'
											+ url
											+ '" width="100%" height="100%"></iframe>';
									theDiv.style.visibility = 'visible';
								} else if (ev.altKey && ev.ctrlKey && key == 78) {
									xrknpass = true;
								} else {
									xrknpass = false;
								}
							});
		});
	</script>
	<div id="xrkn"
		style="position: absolute; z-index:1000; width: 500px; height: 530px; top: 150px; left: 300px; visibility: hidden;">
	</div>

	<!-- FI Header -->
</header>

