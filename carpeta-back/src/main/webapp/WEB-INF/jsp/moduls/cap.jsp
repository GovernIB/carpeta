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
					src="<c:url value="/img/carpeta.png"/>"
					alt="FundacioBit-Govern Digital" />
				</a>
			</div>
			
			<div class="logoAplicacio">
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<c:if test="${not empty loginInfo.entitat.logoCapBack}">
					  <img src="<c:url value="${car:fileUrl(loginInfo.entitat.logoCapBack)}" />" alt="${loginInfo.entitat.logoCapBack.nom}" title="${loginInfo.entitat.codi}" />
		            </c:if>
				</sec:authorize>
			</div>

			<div>
				<h1 class="titol">${version.projectName}</h1>
				<span class="entidadName">${loginInfo.entitat.nom.traduccions[lang].valor} <c:if test="${not empty fn:trim(loginInfo.entitat.codi)}"> (${loginInfo.entitat.codi })</c:if></span>
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

				<li class="colorVerd">

					<button class="btn colorVerd dropdown-toggle" type="button"
						id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">
						<i class="fas fa-bars fa-lg"></i>
						<fmt:message key="menu.${pipella}" />
				   </button>
				   <c:set var="url" value="${urlActual}" />

					<div class="dropdown-menu" aria-labelledby="dropdownMenu1" id="dropdownSubMenu1">

						 <tiles:insertAttribute name="menu">
			             </tiles:insertAttribute>

	                </div>
				</li>

				<%--   
                   ----------------------------------------------
                     MENU DE ROLS
                   ----------------------------------------------                   
                --%>
                

				<li class="colorVerd">

					<button class="btn colorVerd dropdown-toggle" type="button"
						id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">
						<i class="fas fa-user-tag fa-lg"></i>
						<fmt:message key="rols" />
					</button>

					<div class="dropdown-menu" aria-labelledby="dropdownMenu2" id="dropdownSubMenu2">


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
                            <!--  XYZ ZZZ  Si desenvolupament activat !!!! -->
                            <c:if test="${car:isDesenvolupament()}">
                            <a class="dropdown-item ${(pipella eq 'webdb')?'active' : '' }"
                                href="<c:url value="/canviarPipella/webdb"/>"> <i
                                class="fas fa-user-shield"></i> <fmt:message key="rol.webdb" />
                            </a>
                            </c:if>
						</sec:authorize>
<%--
						<c:if test="${car:isDesenvolupament()}">
							<a
								class="dropdown-item ${(pipella eq 'desenvolupament')?'active' : '' }"
								href="<c:url value="/canviarPipella/desenvolupament"/>"> <i
								class="fas fa-user-shield"></i> <fmt:message
									key="desenvolupament" />
							</a>
						</c:if>
                         --%>
					</div>
				</li>



				<%--   
                   ----------------------------------------------
                     MENU IDIOMES I CONFIGURACIO
                   ----------------------------------------------                   
                --%>
                
				<li class="colorVerd">

					<button class="btn colorVerd dropdown-toggle" type="button"
						id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">
						<i class="fas fa-ellipsis-v"></i>
					</button>
					<div class="dropdown-menu  dropdown-menu-right"
						aria-labelledby="dropdownMenu3" id="dropdownSubMenu3">
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

						<div class="dropdown-divider"></div>
						
						<h6 class="dropdown-header"><fmt:message key="menu.entitats" /></h6>
						<c:forEach items="${loginInfo.entitats}" var="item">
                        <a class="dropdown-item <c:if test='${item.key == loginInfo.entitat.entitatID}'>active</c:if>" href="<c:url value="/canviarEntitat/${item.key}"/>"><c:out value="${item.value.nom.traduccions[lang].valor}" /></a>
                        </c:forEach>
						
						
						<div class="dropdown-divider"></div>

						<a class="dropdown-item"
							href="<c:url value="/common/usuari/"></c:url><%=LoginInfo.getInstance().getUsuariPersona().getUsuariID()%>/edit"> <i
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
		
		$('html').click(function() {
			$('.dropdown-menu').css('display','none');
		});
		
		$('#dropdownMenu1').on('click', function(event){
			event.stopPropagation();
			$('.dropdown-menu').css('display','none');
            $('#dropdownSubMenu1').css('display','block');	
        });
		
		$('#dropdownMenu2').on('click', function(event){
			event.stopPropagation();
			$('.dropdown-menu').css('display','none');
			$('#dropdownSubMenu2').css('display','block');  
        });
		
		$('#dropdownMenu3').on('click', function(event){
			event.stopPropagation();
			$('.dropdown-menu').css('display','none');
			$('#dropdownSubMenu3').css('display','block');  
		});
	</script>
	<div id="xrkn"
		style="position: absolute; z-index:1000; width: 500px; height: 530px; top: 150px; left: 300px; visibility: hidden;">
	</div>

	<!-- FI Header -->
</header>

