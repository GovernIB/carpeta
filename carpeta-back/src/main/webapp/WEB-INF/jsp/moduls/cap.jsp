<%@page import="org.springframework.context.i18n.LocaleContextHolder"%>
<%@page import="java.util.Locale"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%><%@ include
	file="/WEB-INF/jsp/moduls/includes.jsp"%><%@ taglib prefix="tiles"
	uri="http://tiles.apache.org/tags-tiles"%>

<header>
	<!-- Header -->
	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-aplicacio" style="padding:0;">

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
							key="usuari" />: </strong> <span class="subtitolMay"> <%=request.getUserPrincipal().getName()%>
						| <%= request.getRemoteUser() %>
					</span>
				</div>
			</div>
		</div>

		<!-- FI Logo i nom aplicació -->

		<!-- Botons -->
		<div class="collapse navbar-collapse" id="navbarCollapse">

			<ul class="navbar-nav mobil">

				<%--  XYZ ZZZ  AQUI VAN ELS MENUS   --%>
				<%--
                            <li class="nav-item colorVerd">
                                <a class="nav-link mobil" href="/listUnitatOrganica"
                                            title="{labels.listUnitatOrganica_link}">                                                                    
                                    <span class="oi oi-briefcase" title="{labels.listUnitatOrganica_link}"
                                          aria-hidden="true"></span>
                                    <p class="mb-0 float-right botoCurt">{labels.listUnitatOrganica_link}</p>
                                </a>
                            </li>
                             --%>

				<%
                                // TODO XYZ ZZZ Això ho ha de collir dels idiomes de la BBDD
                                java.util.List<String> idiomes = new java.util.ArrayList<String>();
                                idiomes.add("ca");
                                idiomes.add("es");
                                //idiomes.add("en");
                                session.setAttribute("idiomes", idiomes);

                            %>
				<li class="dropdown colorVerd">

					<button class="btn colorVerd dropdown-toggle" type="button"
						id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">
						<i class="fas fa-user-tag fa-lg"></i>
						<fmt:message key="rols" />
					</button>
				
					<div class="dropdown-menu" aria-labelledby="dropdownMenu2">

					   <%-- 
					
						<c:forEach var="idioma" items="${idiomes}"
							varStatus="status">
							<a class="dropdown-item"
								href="<c:url value="/canviarIdioma/${idioma}"></c:url>">
								<img
								src="<c:url value="/img/${idioma}_petit_${lang eq idioma? 'on' : 'off'}.gif"/>"
								alt="${idioma}" width="17" height="14" border="0" />
							</a>
						</c:forEach>
						--%>
	    
	                      <a class="dropdown-item ${(empty pipella)?'active' : '' }"	                      
								href="<c:url value="/canviarPipella"></c:url>">
								<i class="fas fa-home"></i>
								<fmt:message key="inici" /></a>
							</a>
	    
	    
	
	<%--  DRAW MENU OPTIONS  XYZ ZZZ
	    <c:forEach var="rolG" items="${loginInfo.roles}">
	    <c:set var="rol" value="${rolG.authority}"/>
	    <c:if test="${not(rol eq 'ROLE_USER')}">
	    <li ${(pipella eq rol)?'class="active"' : '' }>
	       <a href="<c:url value="/canviarPipella/${rol}"/>"><fmt:message key="${rol}" />
	       <c:if test="${not(empty avisos[rol])}">
	         &nbsp; <span class="badge badge-warning">${avisos[rol]}</span>
	       </c:if>
	       </a>
	    </li>
	    </c:if>  
	    </c:forEach>
	    --%>
	    
	    <sec:authorize access="hasRole('ROLE_USER')">
			<a class="dropdown-item ${(pipella eq 'user')?'active' : '' }" href="<c:url value="/canviarPipella/user"/>">
			<i class="fas fa-user"></i>
				ROLE_USER</a>
			</a>
	    </sec:authorize>
	    

	    <sec:authorize access="hasRole('ROLE_ADMIN')">
	       	<a class="dropdown-item ${(pipella eq 'admin')?'active' : '' }" href="<c:url value="/canviarPipella/admin"/>">
			<i class="fas fa-user-shield"></i>
				ROLE_ADMIN
			</a>
	    </sec:authorize>
	

	
	    <c:if test="${prefixLowercase}:isDesenvolupament()}">
	       <a class="dropdown-item ${(pipella eq 'desenvolupament')?'active' : '' }" href="<c:url value="/canviarPipella/desenvolupament"/>">
			<i class="fas fa-user-shield"></i>
				 <fmt:message key="desenvolupament" />
           </a>
	    </c:if>
						
					
						
					</div>
				
				</li>



				<%--   OPCIONS  --%>
				<li class="dropdown colorVerd">

					<button class="btn colorVerd dropdown-toggle" type="button"
						id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">
						<i class="fas fa-ellipsis-v"></i>
					</button>
					<div class="dropdown-menu  dropdown-menu-right"
						aria-labelledby="dropdownMenu3">
						
						<table><tr>
						<c:forEach var="idioma" items="${idiomes}"
							varStatus="status">
							<td><a class="dropdown-item"
								href="<c:url value="/canviarIdioma/${idioma}"></c:url>">
								<img
								src="<c:url value="/img/${idioma}_petit_${lang eq idioma? 'on' : 'off'}.gif"/>"
								alt="${idioma}" width="17" height="14" border="0" />
							</a>
					       </td>
						</c:forEach>
						</tr></table>
						
						<hr/>
						

						<a class="dropdown-item"
							href="<c:url value="/configuracio"></c:url>"> <i
							class="fas fa-cog"></i> <fmt:message key="configuracio" />
						</a>
						
						<a class="dropdown-item" href="<c:url value="/logout"></c:url>">
							<i class="fas fa-sign-out-alt"></i> <fmt:message key="sortir" />
						</a>


					</div>
				</li>




			</ul>

		</div>
		<!-- FI Botons -->
	</nav>

	<!-- FI Header -->
</header>

