<!DOCTYPE html>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ca" style="" class=" js flexbox flexboxlegacy hashchange backgroundsize boxshadow textshadow opacity cssanimations cssgradients csstransforms csstransitions fontface generatedcontent localstorage svg" lang="ca">
<head>
	<title>${title_page}</title>
	<link rel="shortcut icon" type="image/x-ico" href="http://www.caib.es/sites/favicon.png">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

	<!-- Adapta la escala del responsive en los dispositivos moviles -->
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">

	<!-- Scripts -->
	<!-- google analytics -->
	<script type="text/javascript" async="" src="js/ga.js"></script>
	<script src="js/jquery-3.5.0.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<!-- Datetimpicker -->
	<script src="js/moment-with-locales.min.js"></script>
	<script src="js/bootstrap-datetimepicker.js"></script>
	<script src="js/carpeta.js"></script>
	<!-- Necessari per les cookies -->
	<script src="js/modernizr.js"></script>
	<!-- Del Goib -->
	<script src="js/globales.js" type="text/javascript"></script>
	<!-- Bootstrap compatibilitat amb IE -->
	<script src="js/respond.js" type="text/javascript"></script>
	
	<!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
	<link href="css/goib.css" rel="stylesheet">
	<link href="css/carpeta.css" rel="stylesheet">
	<link href="css/open-iconic-bootstrap.css" rel="stylesheet">
	
</head>

<body>

	<!-- Menú vertical -->
	<div class="imc-logo">
		<a href="http://www.caib.es/govern/index.do?lang=ca" class="imc--goib" title="Govern Illes Balears">
			<span>Govern Illes Balears</span>
		</a>
		<ul>
			<li>
				<a href="http://www.caib.es/pidip2front/jsp/ca/noticies" class="imc-logo-ico imc--informat" title="Notícies">
					<span>Notícies</span>
				</a>
			</li>
			<li>
				<a href="http://www.caib.es/govern/administracio.do?lang=ca" class="imc-logo-ico imc--administracio" title="Administració">
					<span>Administració</span>
				</a>
			</li>
			<li>
				<a href="http://www.illesbalears.travel/?lang=ca" class="imc-logo-ico imc--illes" title="Illes">
					<span>Illes</span>
				</a>
			</li>
		</ul>
	</div>

	<!-- Contingut dret --> 
	<div class="contenedor" id="contenedor">
	
		<!-- Capçalera --> 
		<header class="imc-titol">
		
			<nav class="imc--contingut">
				<!--<button class="navbar-toggler botonMovil" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="">
					<span class="navbar-toggler-icon"></span>
				</button>-->

				<!-- Cabecera -->
				<a href="javascript:history.back()" class="imc-torna" title="Torna"><span>Torna</span></a>
				<h1>
					<span>CARPETA CIUTADANA</span>
					<sec:authorize access="isAuthenticated()">
						<sec:authentication var="user" property="principal.usuarioClave.nombreCompleto" />
						<span class="subtituloMay">
							<c:if test="${user != null}">
								<sec:authentication property="principal.usuarioClave.nombreCompleto" />
							</c:if>
						</span>
					</sec:authorize>
				</h1>

				<!-- Menú -->
				<ul>
					<li>
						<a href="http://www.caib.es/govern/organigrama/directori.do?lang=ca" class="imc-bt-directori" title="Directori">
							<span>Directori</span>
						</a>
					</li>
					<li>
						<button type="button" class="imc-bt-menu" id="imc-bt-menu" title="Menú">
							<span>Menú</span>
						</button>
					</li>
				</ul>
			</nav>
			
		</header>
		<!-- Fi Capçalera --> 
			
					
		<!-- Menú desplegable -->
		<div class="imc-marc" id="imc-marc" tabindex="-1" aria-hidden="true">	 
			<div class="imc--fons"></div>
			<!-- menu lateral -->
			<div class="imc-marc-menu" id="imc-marc-menu" aria-hidden="true">
				<div class="imc-cercador" id="imc-cercador">
					<!--<form id="cercador" action="/govern/cercador.do?lang=ca" method="get">
						<p>
							<input type="hidden" name="coduo" id="coduo" value="" />
							<input type="hidden" name="lang" id="lang" value="ca" />
						 <input id="cercadorText" name="cercadorText" type="text" placeholder="Cerca amb les paraules" title="Cerca amb les paraules" />	
							<button type="submit">Cercar</button> 
						</p>
					</form>-->
				</div>
				<ul>
					<li>
						<a href="http://www.caib.es/govern/cercadorAv.do?lang=ca" class="imc-marc-ico imc--avanzada" title="Recerca avançada">
							<span>Recerca avançada</span>
						</a>
					</li>
					<li class="imc-marc-ico imc--idioma">
						<strong>Català</strong>
						\
						<a href="../../../index.html?lang=es">Castellano</a>
					</li>
					<li>
						<a href="../../../accessibilitat.html" class="imc-marc-ico imc--accessibilitat" id="imc-marc-accessibilitat" title="Accessibilitat">
							<span>Accessibilitat</span>
						</a>
					</li>

					<sec:authorize access="isAuthenticated()">
						<li>
							<c:url var="logoutUrl" value="/logout" />
							<form action="${logoutUrl}" id="logout" method="post">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							</form>
							<a href="javascript:sortirCarpeta()" class="imc-marc-ico imc--sortir" title="Sortir">
								<span>Sortir</span>
							</a>
						</li>
					</sec:authorize>

				</ul>
			</div>
		</div>				
		<!-- Fi Menú desplegable -->
		
		
		<!-- Zona Contingut -->
		<div class="imc-continguts" id="continguts">
			
			<!-- Molla de pa -->
			<ul class="mollaPa" id="imc-molla-pa">
				<li>
					<a href="/carpetafront/inicio">Inici</a>
				</li>
			</ul>
			<!-- Fi Molla de pa -->
			
			<!-- Contingut pàgina -->

			<sec:authorize access="!isAuthenticated()">
				<div class="container-contenido">
					<div class="infoNoMenu">
						<p class="titol h2">Benvingut a la Carpeta Ciutadana del Govern de les Illes Balears</p>

						<div class="col-md-5 border-0 float-left p-0">
							<p class="lh15">
								Què podeu fer a la Carpeta Ciutadana?
								<ul class="lh15 pl-5 pt-3">
									<li>Consultar l'estat dels vostres tràmits telemàtics.</li>
									<li>Consultar els vostres registres d'entrada i descarregar-vos els justificants.</li>
									<li>Rebre notificacions i comunicacions.</li>
									<li>Consultar les vostres dades personals.</li>
								</ul>
							</p>

							<p class="lh15 pt-3"><img src="../../../files/solicitar_clave_acceso_dgt.jpg" class="w-25" alt="Logo Clave" title="Logo Clave"/></p>

							<p class="lh15">
								L'accés a la teva Carpeta Ciutadana requereix que t'identifiquis mitjançant el sistema d'autenticació de Cl@ve a través de:
								<ul class="lh15 pl-5 pt-3">
									<li>Certificat digital o DNI electrònic</li>
									<li>Clau PIN</li>
									<li>Clau permanent</li>
								</ul>
							</p>
						</div>

						<div class="col-md-5 border-0 float-right">

							<p class="margen-top-clave pb-3">
								<a class="btn btn-primary carpeta-btn" href="login" role="button"><span class="oi oi-share" title="" aria-hidden="true"></span> Accedir a la Carpeta</a>
							</p>

							<p class="titol h5">No pots accedir a la teva Carpeta Ciutadana?</p>
							<p class="lh15">
								Si necessites ajuda addicional per a utilitzar el sistema Cl@ve, pots obtenir-la a través dels mitjans següents:
								<ul class="lh15 pl-5 pt-3">
									<li>A través del portal d'informació de <a href="http://clave.gob.es/clave_Home/clave.html" title="Accedir Clave" alt="Accedir Clave" target="_blank">Cl@ve</a></li>
									<li>Per telèfon, cridant al telèfon 060.</li>
									<li>Deixant un missatge a la nostra <a href="https://ssweb.seap.minhap.es/ayuda/consulta/Claveciudadanos"  title="Accedir Bústia Clave" title="Accedir Bústia Clave" target="_blank">Bústia d'atenció</a></li>
								</ul>
							</p>

						</div>

					</div>
				</div>
			</sec:authorize>

			<sec:authorize access="isAuthenticated()">
				<div class="container-contenido">
					<div class="infoNoMenu">
						<p class="titol h2">Benvingut a la teva Carpeta Ciutadana personal</p>

						<div class="col-md-12 border-0 pl-0 pr-0">

                            <p class="lh15">A Carpeta Ciutadana podràs trobar ...</p>

                            <div class="card-body imc--llista--capses">

                                <div class="row mb-5">

                                    <!-- Trámites no acabados -->
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                        <button class="card col-md-10 align-items-lg-center imc--capsa" onclick="goTo('<c:url value="/tramite/list"/>')">
                                            <span class="oi oi-document h3 mt-2 mb-2" title="Llista tràmits" alt="Llista tràmits" aria-hidden="true">
                                                <span class="card-title titol">Llista de tràmits</span>
                                            </span>
                                            <span class="card-text mb-3">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed eiusmod tempor  ...</span>
                                        </button>
                                    </div>

                                    <!-- Registros -->
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                        <button class="card col-md-10 align-items-lg-center imc--capsa" onclick="goTo('<c:url value="/registro/list"/>')">
                                            <span class="oi oi-book h3 mt-2 mb-2" title="Llista registres" alt="Llista registres" aria-hidden="true">
                                                <span class="card-title titol">Llista de registres</span>
                                            </span>
                                            <span class="card-text mb-3">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed eiusmod tempor  ...</span>
                                        </button>
                                    </div>

                                    <!-- Notificaciones -->
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                        <button class="card col-md-10 align-items-lg-center imc--capsa" onclick="goTo('<c:url value="/notificacion/list"/>')">
                                            <span class="oi oi-envelope-closed h3 mt-2 mb-2" title="Notificacions" alt="Notificacions" aria-hidden="true">
                                                <span class="card-title titol">Notificacions</span>
                                            </span>
                                            <span class="card-text mb-3">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed eiusmod tempor  ...</span>
                                        </button>
                                    </div>

                                </div>

                                <div class="row">
                                    <!-- Mis Datos -->
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                        <button class="card col-md-10 align-items-lg-center imc--capsa" onclick="goTo('<c:url value="/datosPersonales"/>')">
                                            <span class="oi oi-folder h3 mt-2 mb-2" title="Dades personals" alt="Dades personals" aria-hidden="true">
                                                <span class="card-title titol">Dades personals</span>
                                            </span>
                                            <span class="card-text mb-3">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed eiusmod tempor  ...</span>
                                        </button>
                                    </div>

                                </div>

                            </div>

						</div>

					</div>
				</div>
			</sec:authorize>

			<!-- Fi Contingut pàgina -->

		</div>
		<!-- Fi Zona Contingut -->
		
		<!-- Peu -->
		<footer class="imc-peu">
			<div class="imc--contingut">
		
				<!-- Peu Esquerra -->
				<div class="imc-peu-govern">
					<p><strong>© Govern Illes Balears</strong></p>	
					
					<p>
						<a href="http://www.caib.es/govern/organigrama/area.do?coduo=2390499&amp;lang=ca">Direcció General de Comunicació</a><a href="http://www.caib.es/govern/organigrama/planol.do?coduo=2390499&amp;lang=ca">: Passeig de Sagrera, 2 - 07012 Palma</a></p><p></p>Telèfon 971177166 - Fax 971176348
					<p></p>
												
				</div>
				<!-- Fi Peu Esquerra -->

				<!-- Peu Central -->
				<div class="imc-peu-opcions">
					<ul>
						<li><a href="http://www.caib.es/sites/M180123085529629/ca/mapa/">Mapa web</a></li>
						<li><a href="http://www.caib.es/govern/external/infoLegal.do?lang=ca">Avís legal</a></li>
						<li>
							<a href="http://www.caib.es/govern/rss.do?lang=ca" class="imc-en-rss">
								<span>RSS</span>
								<svg viewBox="0 0 430.117 430.118" xml:space="preserve">
									<g>
										<path d="M97.493,332.473c10.419,10.408,16.755,24.525,16.794,40.244c-0.04,15.687-6.375,29.809-16.755,40.17l-0.04,0.019            c-10.398,10.352-24.603,16.681-40.398,16.681c-15.775,0-29.944-6.348-40.34-16.699C6.384,402.526,0,388.422,0,372.717            c0-15.719,6.384-29.869,16.754-40.253v0.009c10.401-10.36,24.57-16.735,40.34-16.735C72.89,315.738,87.081,322.131,97.493,332.473z             M97.493,332.464v0.009c0.019,0,0.019,0,0.019,0L97.493,332.464z M16.754,412.906c0,0,0,0,0-0.019c-0.019,0-0.019,0-0.019,0            L16.754,412.906z M0.046,146.259v82.129c53.618,0.033,104.328,21.096,142.278,59.104c37.943,37.888,58.917,88.675,59.003,142.477            h0.028v0.149h82.467c-0.065-78.233-31.866-149.099-83.279-200.549C149.122,178.126,78.285,146.308,0.046,146.259z M0.196,0v82.089            c191.661,0.14,347.464,156.184,347.594,348.028h82.327c-0.056-118.571-48.248-225.994-126.132-303.932            C226.073,48.274,118.721,0.051,0.196,0z"></path>
									</g>
								</svg>
							</a>
						</li>
					</ul>
				</div>
				<!-- Fi Peu Central -->

				<!-- Peu Dreta -->
				<div class="imc-peu-xarxes">
					<p>Segueix-nos:</p>
					<ul>
						<li>
							<a href="http://www.youtube.com/CanalIllesBalears" class="imc-bt-xarxa imc-logo-youtube" title="YouTube">
								<svg viewBox="0 0 90 90" xml:space="preserve">
									<g>
										<path d="M70.939,65.832H66l0.023-2.869c0-1.275,1.047-2.318,2.326-2.318h0.315c1.282,0,2.332,1.043,2.332,2.318            L70.939,65.832z M52.413,59.684c-1.253,0-2.278,0.842-2.278,1.873V75.51c0,1.029,1.025,1.869,2.278,1.869            c1.258,0,2.284-0.84,2.284-1.869V61.557C54.697,60.525,53.671,59.684,52.413,59.684z M82.5,51.879v26.544            C82.5,84.79,76.979,90,70.23,90H19.771C13.02,90,7.5,84.79,7.5,78.423V51.879c0-6.367,5.52-11.578,12.271-11.578H70.23            C76.979,40.301,82.5,45.512,82.5,51.879z M23.137,81.305l-0.004-27.961l6.255,0.002v-4.143l-16.674-0.025v4.073l5.205,0.015v28.039            H23.137z M41.887,57.509h-5.215v14.931c0,2.16,0.131,3.24-0.008,3.621c-0.424,1.158-2.33,2.388-3.073,0.125            c-0.126-0.396-0.015-1.591-0.017-3.643l-0.021-15.034h-5.186l0.016,14.798c0.004,2.268-0.051,3.959,0.018,4.729            c0.127,1.357,0.082,2.939,1.341,3.843c2.346,1.69,6.843-0.252,7.968-2.668l-0.01,3.083l4.188,0.005L41.887,57.509L41.887,57.509z             M58.57,74.607L58.559,62.18c-0.004-4.736-3.547-7.572-8.356-3.74l0.021-9.239l-5.209,0.008l-0.025,31.89l4.284-0.062l0.39-1.986            C55.137,84.072,58.578,80.631,58.57,74.607z M74.891,72.96l-3.91,0.021c-0.002,0.155-0.008,0.334-0.01,0.529v2.182            c0,1.168-0.965,2.119-2.137,2.119h-0.766c-1.174,0-2.139-0.951-2.139-2.119V75.45v-2.4v-3.097h8.954v-3.37            c0-2.463-0.063-4.925-0.267-6.333c-0.641-4.454-6.893-5.161-10.051-2.881c-0.991,0.712-1.748,1.665-2.188,2.945            c-0.444,1.281-0.665,3.031-0.665,5.254v7.41C61.714,85.296,76.676,83.555,74.891,72.96z M54.833,32.732            c0.269,0.654,0.687,1.184,1.254,1.584c0.56,0.394,1.276,0.592,2.134,0.592c0.752,0,1.418-0.203,1.998-0.622            c0.578-0.417,1.065-1.04,1.463-1.871l-0.099,2.046h5.813V9.74H62.82v19.24c0,1.042-0.858,1.895-1.907,1.895            c-1.043,0-1.904-0.853-1.904-1.895V9.74h-4.776v16.674c0,2.124,0.039,3.54,0.102,4.258C54.4,31.385,54.564,32.069,54.833,32.732z             M37.217,18.77c0-2.373,0.198-4.226,0.591-5.562c0.396-1.331,1.107-2.401,2.137-3.208c1.027-0.811,2.342-1.217,3.941-1.217            c1.345,0,2.497,0.264,3.459,0.781c0.967,0.52,1.713,1.195,2.23,2.028c0.527,0.836,0.885,1.695,1.076,2.574            c0.195,0.891,0.291,2.235,0.291,4.048v6.252c0,2.293-0.092,3.98-0.271,5.051c-0.177,1.074-0.557,2.07-1.146,3.004            c-0.58,0.924-1.329,1.615-2.237,2.056c-0.918,0.445-1.968,0.663-3.154,0.663c-1.325,0-2.441-0.183-3.361-0.565            c-0.923-0.38-1.636-0.953-2.144-1.714c-0.513-0.762-0.874-1.69-1.092-2.772c-0.219-1.081-0.323-2.707-0.323-4.874L37.217,18.77            L37.217,18.77z M41.77,28.59c0,1.4,1.042,2.543,2.311,2.543c1.27,0,2.308-1.143,2.308-2.543V15.43c0-1.398-1.038-2.541-2.308-2.541            c-1.269,0-2.311,1.143-2.311,2.541V28.59z M25.682,35.235h5.484l0.006-18.96l6.48-16.242h-5.998l-3.445,12.064L24.715,0h-5.936            l6.894,16.284L25.682,35.235z"></path>
									</g>
								</svg>
								<span>YouTube</span>
							</a>
						</li>
						<li>
							<a href="https://www.instagram.com/goib" class="imc-bt-xarxa imc-logo-instagram" title="Instagram">
								<svg viewBox="0 0 169.063 169.063" xml:space="preserve">
									<g>
										<path d="M122.406,0H46.654C20.929,0,0,20.93,0,46.655v75.752c0,25.726,20.929,46.655,46.654,46.655h75.752            c25.727,0,46.656-20.93,46.656-46.655V46.655C169.063,20.93,148.133,0,122.406,0z M154.063,122.407            c0,17.455-14.201,31.655-31.656,31.655H46.654C29.2,154.063,15,139.862,15,122.407V46.655C15,29.201,29.2,15,46.654,15h75.752            c17.455,0,31.656,14.201,31.656,31.655V122.407z"></path>
										<path d="M84.531,40.97c-24.021,0-43.563,19.542-43.563,43.563c0,24.02,19.542,43.561,43.563,43.561s43.563-19.541,43.563-43.561            C128.094,60.512,108.552,40.97,84.531,40.97z M84.531,113.093c-15.749,0-28.563-12.812-28.563-28.561            c0-15.75,12.813-28.563,28.563-28.563s28.563,12.813,28.563,28.563C113.094,100.281,100.28,113.093,84.531,113.093z"></path>
										<path d="M129.921,28.251c-2.89,0-5.729,1.17-7.77,3.22c-2.051,2.04-3.23,4.88-3.23,7.78c0,2.891,1.18,5.73,3.23,7.78            c2.04,2.04,4.88,3.22,7.77,3.22c2.9,0,5.73-1.18,7.78-3.22c2.05-2.05,3.22-4.89,3.22-7.78c0-2.9-1.17-5.74-3.22-7.78            C135.661,29.421,132.821,28.251,129.921,28.251z"></path>
									</g>
								</svg>
								<span>Instagram</span>
							</a>
						</li>
						<li>
							<a href="http://www.twitter.com/goib" class="imc-bt-xarxa imc-logo-twitter" title="Twitter">
								<svg viewBox="0 0 430.117 430.117" xml:space="preserve">
									<g>
										<path d="M381.384,198.639c24.157-1.993,40.543-12.975,46.849-27.876            c-8.714,5.353-35.764,11.189-50.703,5.631c-0.732-3.51-1.55-6.844-2.353-9.854c-11.383-41.798-50.357-75.472-91.194-71.404            c3.304-1.334,6.655-2.576,9.996-3.691c4.495-1.61,30.868-5.901,26.715-15.21c-3.5-8.188-35.722,6.188-41.789,8.067            c8.009-3.012,21.254-8.193,22.673-17.396c-12.27,1.683-24.315,7.484-33.622,15.919c3.36-3.617,5.909-8.025,6.45-12.769            C241.68,90.963,222.563,133.113,207.092,174c-12.148-11.773-22.915-21.044-32.574-26.192            c-27.097-14.531-59.496-29.692-110.355-48.572c-1.561,16.827,8.322,39.201,36.8,54.08c-6.17-0.826-17.453,1.017-26.477,3.178            c3.675,19.277,15.677,35.159,48.169,42.839c-14.849,0.98-22.523,4.359-29.478,11.642c6.763,13.407,23.266,29.186,52.953,25.947            c-33.006,14.226-13.458,40.571,13.399,36.642C113.713,320.887,41.479,317.409,0,277.828            c108.299,147.572,343.716,87.274,378.799-54.866c26.285,0.224,41.737-9.105,51.318-19.39            C414.973,206.142,393.023,203.486,381.384,198.639z"></path>
									</g>
								</svg>
								<span>Twitter</span>
							</a>
						</li>
						<li>
							<a href="https://www.facebook.com/GovernIllesBalears/" class="imc-bt-xarxa imc-logo-facebook" title="Facebook">
								<svg viewBox="0 0 430.113 430.114" xml:space="preserve">
									<g>
										<path d="M158.081,83.3c0,10.839,0,59.218,0,59.218h-43.385v72.412h43.385v215.183h89.122V214.936h59.805            c0,0,5.601-34.721,8.316-72.685c-7.784,0-67.784,0-67.784,0s0-42.127,0-49.511c0-7.4,9.717-17.354,19.321-17.354            c9.586,0,29.818,0,48.557,0c0-9.859,0-43.924,0-75.385c-25.016,0-53.476,0-66.021,0C155.878-0.004,158.081,72.48,158.081,83.3z"></path>
									</g>
								</svg>
								<span>Facebook</span>
							</a>
						</li>
					</ul>				
				</div>								
			</div>
			<!-- Fi Peu Dreta -->
			
		</footer>			
		<!-- Fi Peu -->
		
	</div>
	<!-- Fi Contingut dret --> 
	
	<!-- menú lateral -->
	<script src="js/menu-lateral.js" type="text/javascript"></script>
	<!-- acceptar cookies -->
	<script src="js/aceptar_cookies.js" type="text/javascript"></script>

</body>
</html>