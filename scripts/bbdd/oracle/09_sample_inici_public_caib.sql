﻿
-- 24/02/2021 Informació de Login que s'obtengui de l'ENTITAT #292

INSERT INTO car_traduccio(traduccioid) VALUES (300);

INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (300, 'ca', 
'<div class="row mr-0 ml-0"><div class="infoNoMenu">
<h2><p class="titol h2">Us donam la benvinguda a la Carpeta Ciutadana - Govern de les Illes Balears</p></h2>
<div class="col-md-5 border-0 float-left p-0"><p class="titol h5 margen-top-clave">
<span class="oi oi-person"></span>Què podeu fer a la Carpeta Ciutadana?</p><ul class="lh15 pl-5 pt-3"><li><span class="oi oi-arrow-right"></span>
Consultar l''estat dels vostres tràmits telemàtics.</li><li><span class="oi oi-arrow-right"></span>Consultar els vostres registres d''entrada i descarregar-vos els justificants.</li>
<li><span class="oi oi-arrow-right"></span>Rebre notificacions i comunicacions.</li>
<li><span class="oi oi-arrow-right"></span>Consultar les vostres dades personals.</li></ul>
<p class="titol h5 margen-top-clave"><span class="oi oi-account-login"></span>Com podeu accedir a la Carpeta Ciutadana?</p>
<p class="lh15">L''accés a la teva Carpeta Ciutadana requereix que t''identifiquis mitjançant el sistema d''autenticació de Cl@ve a través de:</p>
<div class="pt-3 imc--logoclave"></div><ul class="lh15 pl-5 pt-3 opcionesAcceso"><li><span class="oi oi-arrow-right"></span>
Certificat digital o DNI electrònic</li><li><span class="oi oi-arrow-right"></span>Clau PIN</li><li><span class="oi oi-arrow-right"></span>
Clau permanent</li></ul><br class="clearBoth"></div><div class="col-md-5 border-0 columna2Inici">
<p class="margen-top-clave pb-3"><a class="btn btn-primary carpeta-btn botoAccedirCarpeta" 
href="javascript: var loc=new URL(window.location.href); 
window.location.href=(''prelogin?urlbase='' + encodeURIComponent(loc.protocol + ''\'' + loc.host ) )" role="button">
<span class="oi oi-account-login" title="" aria-hidden="true"></span> Accedeix a la Carpeta</a></p><p class="titol h5">Teniu una clau de tramitació?</p>
<p class="lh15 pb-3"><a href="#" id="tramitacioModalBtn" data-toggle="modal" data-target="#tramitacioModal">
<span class="oi oi-external-link"></span>Accediu aquí per reprendre tramitació anònima</a></p>
<div class="modal fade" id="tramitacioModal" tabindex="-1" aria-labelledby="tramitacioLabel" aria-hidden="true">
<div class="modal-dialog"><div class="modal-content"><div class="modal-header">
<h5 class="modal-title" id="tramitacioModalLabel">Iniciar tramitació anònima</h5>
<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
</div>
<div class="modal-body"><div class="alert alert-danger" role="alert">El codi és erroni o s''ha produït un error.</div>
<p>Introdueixi la clau del tràmit i cliqueu el botó Iniciar per conèixer el seu estat:</p><input type="text" id="clauAnonima"></div>
<div class="modal-footer"><button type="button" class="btn btn-secondary" data-dismiss="modal">Tancar</button>
<button type="button" class="btn btn-primary" onclick="$.ajax({url: window.location.href.split(''/'').slice(0, 3).join(''/'')+''/carpetafront/tramitacio?id='' + jQuery(''#clauAnonima'').val()}).done(function(data){if (data.url !==undefined){window.open(data.url, ''_blank''); $(''#tramitacioModal'').modal(''hide'');}else if (data.error==''true''){$(''#tramitacioModal .alert-danger'').css(''display'',''block'');}})">Iniciar</button>
</div></div></div></div><p class="titol h5">No podeu accedir a la teva Carpeta Ciutadana?</p>
<p class="lh15">Si necessitau ajuda addicional per a utilitzar el sistema Cl@ve, pots obtenir-la a través dels mitjans següents:</p>
<ul class="lh15 pl-5 pt-3"><li><span class="oi oi-arrow-right"></span> A través del portal d''informació de 
<a href="http://clave.gob.es/clave_Home/clave.html" title="Accedir Cl@ve" target="_blank" rel="noopener noreferrer">Cl@ve</a></li><li>
<span class="oi oi-arrow-right"></span> Per telèfon, cridant al telèfon 060.</li><li><span class="oi oi-arrow-right"></span> 
Deixant un missatge a la nostra <a href="https://ssweb.seap.minhap.es/ayuda/consulta/Claveciudadanos" title="Accedir Bústia Cl@ve" target="_blank" rel="noopener noreferrer">Bústia d''atenció</a>
</li></ul></div></div></div></div></div>');

INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (300, 'es', '<div class="row mr-0 ml-0"><div class="infoNoMenu">
<h2><p class="titol h2">Os damos la bienvenida a la Carpeta Ciutadana - Gobierno de las Islas Baleares</p></h2><div class="col-md-5 border-0 float-left p-0">
<p class="titol h5 margen-top-clave"><span class="oi oi-person"></span>¿Qué puede hacer en la Carpeta Ciudadana?</p>
<ul class="lh15 pl-5 pt-3"><li><span class="oi oi-arrow-right"></span>Consultar el estado de vuestros trámites telemáticos.</li>
<li><span class="oi oi-arrow-right"></span>Consultar vuestros registros de entrada y descargaros los justificantes.</li>
<li><span class="oi oi-arrow-right"></span>Recibir notificaciones y comunicaciones.</li><li><span class="oi oi-arrow-right"></span>Consultar vuestros datos personales.</li>
</ul><p class="titol h5 margen-top-clave"><span class="oi oi-account-login"></span>¿Cómo podéis acceder a la Carpeta Ciudadana?</p>
<p class="lh15">El acceso a tu Carpeta Ciudadana requiere que te identifiques mediante el sistema de autenticación de Cl@ve a través de:</p>
<div class="pt-3 imc--logoclave"></div><ul class="lh15 pl-5 pt-3 opcionesAcceso"><li><span class="oi oi-arrow-right"></span>
Certificado digital o DNI electrónico</li><li><span class="oi oi-arrow-right"></span>Clave PIN</li><li><span class="oi oi-arrow-right"></span>
Clave permanente</li></ul><br class="clearBoth"></div><div class="col-md-5 border-0 columna2Inici"><p class="margen-top-clave pb-3">
<a class="btn btn-primary carpeta-btn botoAccedirCarpeta" 
href="javascript: var loc=new URL(window.location.href); 
window.location.href=(''prelogin?urlbase='' + encodeURIComponent(loc.protocol + ''\'' + loc.host ) )" role="button">
<span class="oi oi-account-login" title="" aria-hidden="true"></span> Accede a la Carpeta</a></p>
<p class="titol h5">¿Tienes una clave de tramitación?</p><p class="lh15 pb-3"><a href="#" id="tramitacioModalBtn" data-toggle="modal" data-target="#tramitacioModal">
<span class="oi oi-external-link"></span>Accede aquí para retomar un trámite anónimo</a></p><div class="modal fade" id="tramitacioModal" tabindex="-1" aria-labelledby="tramitacioLabel" aria-hidden="true">
<div class="modal-dialog"><div class="modal-content"><div class="modal-header"><h5 class="modal-title">Iniciar tramitación anónimo</h5>
<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
<div class="modal-body"><div class="alert alert-danger" role="alert">El código es erróneo o se ha producido un error.</div>
<p>Introduzca el código del trámite y clique el botón Iniciar para conocer su estado:</p><input type="text" id="clauAnonima"></div>
<div class="modal-footer"><button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
<button type="button" class="btn btn-primary" onclick="$.ajax({url: window.location.href.split(''/'').slice(0, 3).join(''/'')+''/carpetafront/tramitacio?id='' + jQuery(''#clauAnonima'').val()}).done(function(data){if (data.url !==undefined){window.open(data.url, ''_blank''); $(''#tramitacioModal'').modal(''hide'');}else if (data.error==''true''){$(''#tramitacioModal .alert-danger'').css(''display'',''block'');}})">Iniciar</button>
</div></div></div></div><p class="titol h5">¿No puedes acceder a tu Carpeta Ciudadana?</p><p class="lh15">Si necesitáis ayuda adicional para utilizar el sistema Cl@ve, puedes obtenerla a través de los medios siguientes:</p>
<ul class="lh15 pl-5 pt-3"><li><span class="oi oi-arrow-right"></span> A través del portal de información de 
<a href="http://clave.gob.es/clave_Home/clave.html" title="Acceder Cl@ve" target="_blank" rel="noopener noreferrer">Cl@ve</a></li>
<li><span class="oi oi-arrow-right"></span> Por teléfono, llamando al teléfono 060.</li><li><span class="oi oi-arrow-right"></span> Dejando un mensaje en nuestro 
<a href="https://ssweb.seap.minhap.es/ayuda/consulta/Claveciudadanos" title="Acceder Buzón Cl@ve" target="_blank" rel="noopener noreferrer">Buzón de atención.</a></li></ul></div></div></div></div></div>');

UPDATE car_entitat SET  logintextid=300  WHERE codi = 'caib';

