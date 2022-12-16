
-- Idiomes

INSERT INTO car_idioma(idiomaid, nom, suportat, ordre) VALUES ('ca', 'Català', true, 0);
INSERT INTO car_idioma(idiomaid, nom, suportat, ordre) VALUES ('es', 'Castellano', true, 1);
INSERT INTO car_idioma(idiomaid, nom, suportat, ordre) VALUES ('en', 'English', true, 2);

-- Propietat Global

INSERT INTO car_propietatglobal VALUES (24, 'es.caib.carpeta.defaultentitycode', '', '<p>Codi de l''entitat per defecte a la que es donar&agrave; d''alta autom&agrave;ticament a un usuari just despr&eacute;s d''haver-se creat a Carpeta</p>', NULL);



-- Plugin de SISTRA

INSERT INTO car_traduccio VALUES (26);
INSERT INTO car_traduccio VALUES (36);


INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (26, 'en', 'Sistra v1 & v2');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (26, 'es', 'Sistra v1 & v2');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (26, 'ca', 'Sistra v1 & v2');

INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (36, 'en', 'Description Sistra v1 & v2');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (36, 'es', 'Descripción Sistra v1 & v2');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (36, 'ca', 'Descripció Sistra v1 & v2');


INSERT INTO car_plugin(
            nomid, descripcioid, classe, tipus, propietats, actiu, context)
    VALUES (26, 36, 'org.fundaciobit.pluginsib.carpetafront.sistra.SistraCarpetaFrontPlugin', 1, '# Common
es.caib.carpeta.pluginsib.carpetafront.sistra.development=true

# Sistra 1
es.caib.carpeta.pluginsib.carpetafront.sistra1.url=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra1.url"]]
es.caib.carpeta.pluginsib.carpetafront.sistra1.user=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra1.user"]]
es.caib.carpeta.pluginsib.carpetafront.sistra1.pass=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra1.pass"]]
es.caib.carpeta.pluginsib.carpetafront.sistra1.level=2
es.caib.carpeta.pluginsib.carpetafront.sistra1.web=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra1.web"]]

# Sistra 2
es.caib.carpeta.pluginsib.carpetafront.sistra2.url=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra2.url"]]
es.caib.carpeta.pluginsib.carpetafront.sistra2.user=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra2.user"]]
es.caib.carpeta.pluginsib.carpetafront.sistra2.pass=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra2.pass"]]', true, 'sistra');

--   Plugin de RegWeb3

INSERT INTO car_traduccio VALUES (46);
INSERT INTO car_traduccio VALUES (56);

INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (46, 'en', 'Registre');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (46, 'es', 'Registro');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (46, 'ca', 'Registre');

INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (56, 'en', 'REGWEB3 Registre electrònic');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (56, 'es', 'REGWEB3 Registro electrónico');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (56, 'ca', 'REGWEB3 Registre electrònic');


INSERT INTO car_plugin(
            nomid, descripcioid, classe, tipus, propietats, actiu, context)
    VALUES (46, 56, 'org.fundaciobit.pluginsib.carpetafront.regweb32.Regweb32CarpetaFrontPlugin', 1, 
    '# Common
es.caib.carpeta.pluginsib.carpetafront.regweb32.development=true
es.caib.carpeta.pluginsib.carpetafront.regweb32.isreact=true
es.caib.carpeta.pluginsib.carpetafront.regweb32.concsv.url=https://dev.caib.es/concsv/view.xhtml?hash=
# Regweb3.2
es.caib.carpeta.pluginsib.carpetafront.regweb32.url=[=SP["es.caib.carpeta.pluginsib.carpetafront.regweb32.url"]]
es.caib.carpeta.pluginsib.carpetafront.regweb32.user=[=SP["es.caib.carpeta.pluginsib.carpetafront.regweb32.user"]]
es.caib.carpeta.pluginsib.carpetafront.regweb32.pass=[=SP["es.caib.carpeta.pluginsib.carpetafront.regweb32.pass"]]
es.caib.carpeta.pluginsib.carpetafront.regweb32.entidad=A04003003', true, 'regweb3');

-- Plugin de NOTIB

INSERT INTO car_traduccio VALUES (66);
INSERT INTO car_traduccio VALUES (76);

INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (66, 'en', 'Notifications');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (66, 'ca', 'Notificacions');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (66, 'es', 'Notificaciones');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (76, 'en', 'Notifications');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (76, 'ca', 'Notificacions');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (76, 'es', 'Notificaciones');



INSERT INTO car_plugin(nomid, classe, tipus, propietats, actiu, descripcioid, context) VALUES (66, 'org.fundaciobit.pluginsib.carpetafront.notib.NotibCarpetaFrontPlugin', 1, '# Class => org.fundaciobit.pluginsib.carpetafront.notib.NotibCarpetaFrontPlugin

# Common
es.caib.carpeta.pluginsib.carpetafront.notib.development=true
es.caib.carpeta.pluginsib.carpetafront.notib.usenotibapi=true

#
# Sistra 1
es.caib.carpeta.pluginsib.carpetafront.notib.sistra1.url=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra1.url"]]
es.caib.carpeta.pluginsib.carpetafront.notib.sistra1.user=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra1.user"]]
es.caib.carpeta.pluginsib.carpetafront.notib.sistra1.pass=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra1.pass"]]

# NOTIFICACIONES
es.caib.carpeta.pluginsib.carpetafront.notib.notificaciones.url=https://sede.administracion.gob.es/carpeta/notificaciones/notifica/consultaNotificaciones.htm

# Notificacions CAIB: NOTIB
es.caib.carpeta.pluginsib.carpetafront.notib.url=[=SP["es.caib.carpeta.pluginsib.carpetafront.notib.url"]]
es.caib.carpeta.pluginsib.carpetafront.notib.user=[=SP["es.caib.carpeta.pluginsib.carpetafront.notib.user"]]
es.caib.carpeta.pluginsib.carpetafront.notib.pass=[=SP["es.caib.carpeta.pluginsib.carpetafront.notib.pass"]]


# Detall Notificacions CAIB: NOTIB
es.caib.carpeta.pluginsib.carpetafront.notib.notificaciones.detalle.pendientes.url=https://dehu.redsara.es/notificaciones-pendientes
es.caib.carpeta.pluginsib.carpetafront.notib.notificaciones.detalle.realizadas.url=https://dehu.redsara.es/notificaciones-realizadas
es.caib.carpeta.pluginsib.carpetafront.notib.comunicaciones.detalle.url=https://dehu.redsara.es/comunicaciones', true, 76, 'notib');


-- Propietat Global

-- INSERT INTO car_propietatglobal VALUES (24, 'es.caib.carpeta.defaultentitycode', '', '<p>Codi de l''entitat per defecte a la que es donar&agrave; d''alta autom&agrave;ticament a un usuari just despr&eacute;s d''haver-se creat a Carpeta</p>', NULL);


-- Plugin de "Els Meus Expedients"

INSERT INTO car_traduccio VALUES (94);
INSERT INTO car_traduccio VALUES (95);


INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (94, 'ca', 'Els meus expedients');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (94, 'es', 'Mis Expedientes');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (95, 'ca', 'Els meus expedients');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (95, 'es', 'Mis Expedientes');



INSERT INTO car_plugin(pluginid, nomid, classe, tipus, propietats, actiu, descripcioid, context) VALUES (90, 94, 'org.fundaciobit.pluginsib.carpetafront.expedients.ExpedientsCarpetaFrontPlugin', 1, '# Class => org.fundaciobit.pluginsib.carpetafront.expedients.ExpedientsCarpetaFrontPlugin
# Package BASE => es.caib.carpeta.pluginsib.carpetafront.expedients.

# Classe i Propietats mínimes del Plugin d''Arxiu per fer Consultes
es.caib.carpeta.pluginsib.carpetafront.expedients.arxiuclass=es.caib.plugins.arxiu.caib.ArxiuPluginCaib
es.caib.carpeta.pluginsib.carpetafront.expedients.plugin.arxiu.caib.base.url=https://esbse.caib.es:4430/esb
es.caib.carpeta.pluginsib.carpetafront.expedients.plugin.arxiu.caib.aplicacio.codi=CODE_APP
es.caib.carpeta.pluginsib.carpetafront.expedients.plugin.arxiu.caib.usuari=username_arxiu
es.caib.carpeta.pluginsib.carpetafront.expedients.plugin.arxiu.caib.contrasenya=password_arxiu


# Connexió amb ROLSAC

es.caib.carpeta.pluginsib.carpetafront.expedients.rolsac.url=https://proves.caib.es/rolsac/api/rest/v1/
es.caib.carpeta.pluginsib.carpetafront.expedients.rolsac.username=username_rolsac
es.caib.carpeta.pluginsib.carpetafront.expedients.rolsac.password=password_rolsac

es.caib.carpeta.pluginsib.carpetafront.expedients.rolsac.connecttimeout=20000
es.caib.carpeta.pluginsib.carpetafront.expedients.rolsac.readtimeout=20000

#Filtres plugin Expedient
#es.caib.carpeta.pluginsib.carpetafront.expedients.filtre.1.metadada=eni:metadada_sample
#es.caib.carpeta.pluginsib.carpetafront.expedients.filtre.1.operacio=CONTE
#es.caib.carpeta.pluginsib.carpetafront.expedients.filtre.1.valor1=valor1_sample
#es.caib.carpeta.pluginsib.carpetafront.expedients.filtre.1.valor2=valor2_sample

#es.caib.carpeta.pluginsib.carpetafront.expedients.filtre.2.metadada=...', true, 95, 'elsmeusexpedients');





-- Plugin de "Apodera"

INSERT INTO car_traduccio VALUES (104);
INSERT INTO car_traduccio VALUES (105);


INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (104, 'ca', 'Apodera');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (104, 'es', 'Apodera');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (105, 'ca', 'Consulta d''apoderaments en Rea.');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (105, 'es', 'Consulta de apoderamientos en Rea.');



INSERT INTO car_plugin(pluginid, nomid, classe, tipus, propietats, actiu, descripcioid, context) VALUES (103, 104, 'org.fundaciobit.pluginsib.carpetafront.apodera.ApoderaCarpetaFrontPlugin', 1, '# Common
es.caib.carpeta.pluginsib.carpetafront.apodera.development=true

# Apoderaments APODERA

#LLEVAR !!!
#es.caib.carpeta.pluginsib.carpetafront.apodera.url=[=SP["es.caib.carpeta.pluginsib.carpetafront.apodera.endpoint"]]
es.caib.carpeta.pluginsib.carpetafront.apodera.url=https://192.168.39.9/reaCXFServer/services/reaCXFWSv2

es.caib.carpeta.pluginsib.carpetafront.apodera.endpoint=[=SP["es.caib.carpeta.pluginsib.carpetafront.apodera.endpoint"]]
es.caib.carpeta.pluginsib.carpetafront.apodera.codiApp=CARPETA
es.caib.carpeta.pluginsib.carpetafront.apodera.organisme.dir3=A04003003
es.caib.carpeta.pluginsib.carpetafront.apodera.organisme.denominacio=Gobierno de las Islas Baleares
es.caib.carpeta.pluginsib.carpetafront.apodera.authorization.ks.path=[=SP["es.caib.carpeta.pluginsib.carpetafront.apodera.authorization.ks.path"]]
es.caib.carpeta.pluginsib.carpetafront.apodera.authorization.ks.type=[=SP["es.caib.carpeta.pluginsib.carpetafront.apodera.authorization.ks.type"]]
es.caib.carpeta.pluginsib.carpetafront.apodera.authorization.ks.password=[=SP["es.caib.carpeta.pluginsib.carpetafront.apodera.authorization.ks.password"]]
es.caib.carpeta.pluginsib.carpetafront.apodera.authorization.ks.cert.alias=[=SP["es.caib.carpeta.pluginsib.carpetafront.apodera.authorization.ks.cert.alias"]]
es.caib.carpeta.pluginsib.carpetafront.apodera.authorization.ks.cert.password=[=SP["es.caib.carpeta.pluginsib.carpetafront.apodera.authorization.ks.cert.password"]]

es.caib.carpeta.pluginsib.carpetafront.apodera.connectiontimeout=120000
es.caib.carpeta.pluginsib.carpetafront.apodera.readtimeout=120000', true, 105, 'apodera');


-- Plugin de "Altres Comunicacions"

INSERT INTO car_traduccio VALUES (115);
INSERT INTO car_traduccio VALUES (116);


INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (115, 'ca', 'Altres comunicacions');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (115, 'es', 'Otras comunicaciones');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (116, 'ca', 'Des d''aquí podeu accedir a les comunicacions corresponents als tràmits enviats mitjançant l''assistent de tramitació del Govern de les Illes Balears.');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (116, 'es', 'Desde aquí puede acceder a las comunicaciones correspondientes a los trámites eviados mediante el asistente de tramitación del Govern de les Illes Balears.');



INSERT INTO car_plugin(pluginid, nomid, classe, tipus, propietats, actiu, descripcioid, context) VALUES (114, 115, 'org.fundaciobit.pluginsib.carpetafront.notificacionssistra.NotificacionsSistraCarpetaFrontPlugin', 1, '# Class => org.fundaciobit.pluginsib.carpetafront.notificacionssistra.NotificacionsSistraCarpetaFrontPlugin

# Common
es.caib.carpeta.pluginsib.carpetafront.notificacionssistra.development=true

#
# Notificacions CAIB: Sistra 1
es.caib.carpeta.pluginsib.carpetafront.notificacionssistra.sistra1.url=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra1.url"]]
es.caib.carpeta.pluginsib.carpetafront.notificacionssistra.sistra1.user=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra1.user"]]
es.caib.carpeta.pluginsib.carpetafront.notificacionssistra.sistra1.pass=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra1.pass"]]', true, 116, 'altrescomunicacions');

-- Plugin de "Dades de Discapacitat"

INSERT INTO car_traduccio VALUES (125);
INSERT INTO car_traduccio VALUES (126);


INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (125, 'ca', 'Consulta de dades de discapacitat');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (125, 'es', 'Consulta de datos de discapacidad');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (126, 'ca', 'Aquest servei permet consultar dades de discapacitat');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (126, 'es', 'Este servicio permite obtener los datos de discapacidad');



INSERT INTO car_plugin(pluginid, nomid, classe, tipus, propietats, actiu, descripcioid, context) VALUES (124, 125, 'org.fundaciobit.pluginsib.carpetafront.pinbaldiscapacidad.PinbalDiscapacidadCarpetaFrontPlugin', 1, '# Class => org.fundaciobit.pluginsib.carpetafront.pinbaldiscapacidad.PinbalDiscapacidadCarpetaFrontPlugin

es.caib.carpeta.pluginsib.carpetafront.pinbaldiscapacidad.baseurl=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.baseurl"]]
es.caib.carpeta.pluginsib.carpetafront.pinbaldiscapacidad.username=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.username"]]
es.caib.carpeta.pluginsib.carpetafront.pinbaldiscapacidad.password=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.password"]]

es.caib.carpeta.pluginsib.carpetafront.pinbaldiscapacidad.codicertificat=SVDSCDDWS01

es.caib.carpeta.pluginsib.carpetafront.pinbaldiscapacidad.codiprocediment=CODSVDR_GBA_20121107
es.caib.carpeta.pluginsib.carpetafront.pinbaldiscapacidad.finalitat=Consulta dades discapacitat
es.caib.carpeta.pluginsib.carpetafront.pinbaldiscapacidad.identificadorsolicitant=S0711001H
es.caib.carpeta.pluginsib.carpetafront.pinbaldiscapacidad.unitattramitadora=Servei d''escolarització

es.caib.carpeta.pluginsib.carpetafront.pinbaldiscapacidad.codigocomunidadautonoma=04
es.caib.carpeta.pluginsib.carpetafront.pinbaldiscapacidad.codigoprovincia=07

# Necessari en DEV i PRE. NO es fan cridades reals sinó d,un joc de proves
# Exemples:   - 24255536N, FUSTER
es.caib.carpeta.pluginsib.carpetafront.pinbaldiscapacidad.testnif=24255536N
es.caib.carpeta.pluginsib.carpetafront.pinbaldiscapacidad.testsurname=FUSTER

es.caib.carpeta.pluginsib.carpetafront.pinbaldiscapacidad.development=true', true, 126, 'dadesdiscapacitat');


-- Plugin de "Dades de Login"

INSERT INTO car_traduccio VALUES (135);
INSERT INTO car_traduccio VALUES (136);


INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (135, 'ca', 'Dades Login');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (135, 'es', 'Datos Login');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (136, 'ca', 'Informació de les dades de Login');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (136, 'es', 'Información de los datos de Login');



INSERT INTO car_plugin(pluginid, nomid, classe, tipus, propietats, actiu, descripcioid, context) VALUES (134, 135, 'org.fundaciobit.pluginsib.carpetafront.dadeslogin.DadesLoginCarpetaFrontPlugin', 1, '# Class => org.fundaciobit.pluginsib.carpetafront.dadeslogin.DadesLoginCarpetaFrontPlugin', true, 136, 'dadeslogin');


-- Plugin de "Dades de Policia"

INSERT INTO car_traduccio VALUES (145);
INSERT INTO car_traduccio VALUES (146);


INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (145, 'ca', 'Dades Login');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (145, 'es', 'Datos Login');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (146, 'ca', 'Informació de les dades de Login');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (146, 'es', 'Información de los datos de Login');



INSERT INTO car_plugin(pluginid, nomid, classe, tipus, propietats, actiu, descripcioid, context) VALUES (144, 145, 'org.fundaciobit.pluginsib.carpetafront.pinbalpolicia.PinbalPoliciaCarpetaFrontPlugin', 1, 
'# Class => org.fundaciobit.pluginsib.carpetafront.pinbalpolicia.PinbalPoliciaCarpetaFrontPlugin

es.caib.carpeta.pluginsib.carpetafront.pinbalpolicia.baseurl=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.baseurl"]]
es.caib.carpeta.pluginsib.carpetafront.pinbalpolicia.username=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.username"]]
es.caib.carpeta.pluginsib.carpetafront.pinbalpolicia.password=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.password"]]

es.caib.carpeta.pluginsib.carpetafront.pinbalpolicia.codicertificat=SVDDGPCIWS02

es.caib.carpeta.pluginsib.carpetafront.pinbalpolicia.codiprocediment=CODSVDR_GBA_20121107	
es.caib.carpeta.pluginsib.carpetafront.pinbalpolicia.finalitat=Baremacions per el proces d''escolaritzacio
es.caib.carpeta.pluginsib.carpetafront.pinbalpolicia.identificadorsolicitant=S0711001H
es.caib.carpeta.pluginsib.carpetafront.pinbalpolicia.unitattramitadora=Servei d''escolarització

# Necessari en DEV i PRE. NO es fan cridades reals sinó d,un joc de proves
# Exemples:   - 30000056Y, FUSTER
#             - 41107605G, JAUME
es.caib.carpeta.pluginsib.carpetafront.pinbalpolicia.testnif=30000056Y
es.caib.carpeta.pluginsib.carpetafront.pinbalpolicia.testsurname=FUSTER

es.caib.carpeta.pluginsib.carpetafront.pinbalpolicia.development=true'
, true, 146, 'dadespolicia');

-- Plugin de "Subvencions i ajudes"

INSERT INTO car_traduccio VALUES (155);
INSERT INTO car_traduccio VALUES (156);


INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (155, 'ca', 'Estar al corrent de pagament amb la CAIB per a subvencions i ajudes');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (155, 'es', 'Estar al corriente del pago con la CAIB para subvenciones y ayudas');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (156, 'ca', 'Aquest servei permet obtenir les dades de la situació de l''estat de deute del ciutadà o empresa objecte de la consulta.');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (156, 'es', 'Este servicio permite obtener los datos de la situación del estado de deuda del ciudadano o empresa.');

INSERT INTO car_plugin(pluginid, nomid, classe, tipus, propietats, actiu, descripcioid, context) VALUES (154, 155, 'org.fundaciobit.pluginsib.carpetafront.pinbalayudas.PinbalAyudasCarpetaFrontPlugin', 1, 
'# Class => org.fundaciobit.pluginsib.carpetafront.pinbalayudas.PinbalAyudasCarpetaFrontPlugin

es.caib.carpeta.pluginsib.carpetafront.pinbalayudas.baseurl=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.baseurl"]]
es.caib.carpeta.pluginsib.carpetafront.pinbalayudas.username=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.username"]]
es.caib.carpeta.pluginsib.carpetafront.pinbalayudas.password=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.password"]]

es.caib.carpeta.pluginsib.carpetafront.pinbalayudas.codicertificat=SVDCCAACPASWS01

es.caib.carpeta.pluginsib.carpetafront.pinbalayudas.codiprocediment=CODSVDR_GBA_20121107
es.caib.carpeta.pluginsib.carpetafront.pinbalayudas.finalitat=Consulta estar al corrent de pagament a CAIB
es.caib.carpeta.pluginsib.carpetafront.pinbalayudas.identificadorsolicitant=S0711001H
es.caib.carpeta.pluginsib.carpetafront.pinbalayudas.unitattramitadora=Servei d''escolarització

es.caib.carpeta.pluginsib.carpetafront.pinbalayudas.codigoprovincia=07
es.caib.carpeta.pluginsib.carpetafront.pinbalayudas.codigocomunidadautonoma=04

# Necessari en DEV i PRE. NO es fan cridades reals sinó d,un joc de proves
# Exemples:   - 30000056Y, FUSTER
#             - 41107605G, JAUME
es.caib.carpeta.pluginsib.carpetafront.pinbalayudas.testnif=30000056Y
es.caib.carpeta.pluginsib.carpetafront.pinbalayudas.testsurname=FUSTER

es.caib.carpeta.pluginsib.carpetafront.pinbalayudas.development=true'
, true, 156, 'subvencionsiajudes');


-- Plugin de "Pagaments de contractacions"

INSERT INTO car_traduccio VALUES (165);
INSERT INTO car_traduccio VALUES (166);


INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (165, 'ca', 'Estar al corrent de pagament per a contractacions');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (165, 'es', 'Estar al corriente de pago para contrataciones');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (166, 'ca', 'Aquest servei permet obtenir les dades de la situació de l''estat de deute del ciutadà o empresa objecte de la consulta amb la CAIB.');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (166, 'es', 'Este servicio permite obtener los datos de la situación del estado de deuda del ciudadano o empresa objeto de la consulta con la CAIB.');

INSERT INTO car_plugin(pluginid, nomid, classe, tipus, propietats, actiu, descripcioid, context) VALUES (164, 165, 'org.fundaciobit.pluginsib.carpetafront.pinbalcontrataciones.PinbalContratacionesCarpetaFrontPlugin', 1, 
'# Class => org.fundaciobit.pluginsib.carpetafront.pinbalcontrataciones.PinbalContratacionesCarpetaFrontPlugin

es.caib.carpeta.pluginsib.carpetafront.pinbalcontrataciones.baseurl=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.baseurl"]]
es.caib.carpeta.pluginsib.carpetafront.pinbalcontrataciones.username=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.username"]]
es.caib.carpeta.pluginsib.carpetafront.pinbalcontrataciones.password=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.password"]]

es.caib.carpeta.pluginsib.carpetafront.pinbalcontrataciones.codicertificat=SVDCCAACPCWS01

es.caib.carpeta.pluginsib.carpetafront.pinbalcontrataciones.codiprocediment=CODSVDR_GBA_20121107
es.caib.carpeta.pluginsib.carpetafront.pinbalcontrataciones.finalitat=Consulta familia nombrosa
es.caib.carpeta.pluginsib.carpetafront.pinbalcontrataciones.identificadorsolicitant=S0711001H
es.caib.carpeta.pluginsib.carpetafront.pinbalcontrataciones.unitattramitadora=Servei d''escolarització

es.caib.carpeta.pluginsib.carpetafront.pinbalcontrataciones.codigocomunidadautonoma=04
es.caib.carpeta.pluginsib.carpetafront.pinbalcontrataciones.codigoprovincia=07

# Necessari en DEV i PRE. NO es fan cridades reals sinó d,un joc de proves
# Exemples:   - 24255536N, FUSTER
es.caib.carpeta.pluginsib.carpetafront.pinbalcontrataciones.testnif=24255536N
es.caib.carpeta.pluginsib.carpetafront.pinbalcontrataciones.testsurname=FUSTER

es.caib.carpeta.pluginsib.carpetafront.pinbalcontrataciones.development=true'
, true, 166, 'pagamentcontractacions');


-- Plugin de "Matricula"

INSERT INTO car_traduccio VALUES (175);
INSERT INTO car_traduccio VALUES (176);


INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (175, 'ca', 'Matrícula Centre');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (175, 'es', 'Matrícula Centro');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (176, 'ca', 'Certificat Matrícula Centre1');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (176, 'es', 'Certificado Matrícula Centro1');

INSERT INTO car_plugin(pluginid, nomid, classe, tipus, propietats, actiu, descripcioid, context) VALUES (174, 175, 'org.fundaciobit.pluginsib.carpetafront.pinbalmatricula.PinbalMatriculaCarpetaFrontPlugin', 1, 
'es.caib.carpeta.pluginsib.carpetafront.pinbalmatricula.baseurl=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.baseurl"]]
es.caib.carpeta.pluginsib.carpetafront.pinbalmatricula.username=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.username"]]
es.caib.carpeta.pluginsib.carpetafront.pinbalmatricula.password=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.password"]]

es.caib.carpeta.pluginsib.carpetafront.pinbalmatricula.codicertificat=SCMCEDU

es.caib.carpeta.pluginsib.carpetafront.pinbalmatricula.codiprocediment=CODSVDR_GBA_20121107
es.caib.carpeta.pluginsib.carpetafront.pinbalmatricula.finalitat=Consulta estar matriculat centre educatiu
es.caib.carpeta.pluginsib.carpetafront.pinbalmatricula.identificadorsolicitant=S0711001H
es.caib.carpeta.pluginsib.carpetafront.pinbalmatricula.unitattramitadora=Servei d''escolarització

# Necessari en DEV i PRE. NO es fan cridades reals sinó d,un joc de proves
# es.caib.carpeta.pluginsib.carpetafront.pinbalmatricula.testnif=14661076W
# es.caib.carpeta.pluginsib.carpetafront.pinbalmatricula.testsurname=USUARI_PROVA

# Dades per fer consulta Documentación Tutor i Fecha Nacimiento Titular
es.caib.carpeta.pluginsib.carpetafront.pinbalmatricula.fechaNacimientoTitular=
es.caib.carpeta.pluginsib.carpetafront.pinbalmatricula.tipoDocumentacion=NIF
es.caib.carpeta.pluginsib.carpetafront.pinbalmatricula.documentacion=14661076W

# Common
es.caib.carpeta.pluginsib.carpetafront.pinbalmatricula.development=true'
, true, 176, 'matricula');


-- Plugin de "Reprendre tramit sistra"

INSERT INTO car_traduccio VALUES (185);
INSERT INTO car_traduccio VALUES (186);


INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (185, 'ca', 'Reprendre Tramitació anònima');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (185, 'es', 'Retomar Tramitación anónima');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (186, 'ca', 'Reprendre Tràmit Sistra');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (186, 'es', 'Retomar Trámite Sistra');

INSERT INTO car_plugin(pluginid, nomid, classe, tipus, propietats, actiu, descripcioid, context) VALUES (184, 185, 'org.fundaciobit.pluginsib.carpetafront.reprendretramitsistra.ReprendreTramitSistraCarpetaFrontPlugin', 1, 
'# Common
es.caib.carpeta.pluginsib.carpetafront.reprendretramitsistra.development=true


# Sistra 1
es.caib.carpeta.pluginsib.carpetafront.reprendretramitsistra.url=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra1.url"]]
es.caib.carpeta.pluginsib.carpetafront.reprendretramitsistra.user=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra1.user"]]
es.caib.carpeta.pluginsib.carpetafront.reprendretramitsistra.pass=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra1.pass"]]'
, true, 186, 'reprenretramitsistra');


-- Plugin de "Familia Nombrosa"

INSERT INTO car_traduccio VALUES (195);
INSERT INTO car_traduccio VALUES (196);


INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (195, 'ca', 'Servei de verificació i consulta de dades de família nombrosa');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (195, 'es', 'Servicio de verificación y consulta de datos de família numerosa');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (196, 'ca', 'Aquest servei permet consultar dades de família nombrosa');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (196, 'es', 'Este servicio permite consultar los datos de familia numerosa.');

INSERT INTO car_plugin(pluginid, nomid, classe, tipus, propietats, actiu, descripcioid, context) VALUES (194, 195, 'org.fundaciobit.pluginsib.carpetafront.pinbalfamilia.PinbalFamiliaCarpetaFrontPlugin', 1, 
'# Class => org.fundaciobit.pluginsib.carpetafront.pinbalfamilia.PinbalFamiliaCarpetaFrontPlugin

es.caib.carpeta.pluginsib.carpetafront.pinbalfamilia.baseurl=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.baseurl"]]
es.caib.carpeta.pluginsib.carpetafront.pinbalfamilia.username=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.username"]]
es.caib.carpeta.pluginsib.carpetafront.pinbalfamilia.password=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.password"]]

es.caib.carpeta.pluginsib.carpetafront.pinbalfamilia.codicertificat=SVDSCTFNWS01

es.caib.carpeta.pluginsib.carpetafront.pinbalfamilia.codiprocediment=CODSVDR_GBA_20121107
es.caib.carpeta.pluginsib.carpetafront.pinbalfamilia.finalitat=Consulta familia nombrosa
es.caib.carpeta.pluginsib.carpetafront.pinbalfamilia.identificadorsolicitant=S0711001H
es.caib.carpeta.pluginsib.carpetafront.pinbalfamilia.unitattramitadora=Servei d''escolarització

es.caib.carpeta.pluginsib.carpetafront.pinbalfamilia.codigocomunidadautonoma=04

# Necessari en DEV i PRE. NO es fan cridades reals sinó d,un joc de proves
# Exemples:   - 24255536N, FUSTER
es.caib.carpeta.pluginsib.carpetafront.pinbalfamilia.testnif=24255536N
es.caib.carpeta.pluginsib.carpetafront.pinbalfamilia.testsurname=FUSTER

es.caib.carpeta.pluginsib.carpetafront.pinbalfamilia.development=true'
, true, 196, 'familianombrosa');


-- Plugin de "Tramits"

INSERT INTO car_traduccio VALUES (255);
INSERT INTO car_traduccio VALUES (256);


INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (255, 'ca', 'TRÀMITS TELEMÀTICS');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (255, 'es', 'TRÁMITES TELEMÁTICOS');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (256, 'ca', 'Descripció Sistra v1 & v2');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (256, 'es', 'Descripción Sistra v1 & v2');

INSERT INTO car_plugin(pluginid, nomid, classe, tipus, propietats, actiu, descripcioid, context) VALUES (254, 255, 'org.fundaciobit.pluginsib.carpetafront.sistra.SistraCarpetaFrontPlugin', 1, 
'# Common
es.caib.carpeta.pluginsib.carpetafront.sistra.development=true
es.caib.carpeta.pluginsib.carpetafront.sistra.isreact=true

# Sistra 1
es.caib.carpeta.pluginsib.carpetafront.sistra1.url=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra1.url"]]
es.caib.carpeta.pluginsib.carpetafront.sistra1.user=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra1.user"]]
es.caib.carpeta.pluginsib.carpetafront.sistra1.pass=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra1.pass"]]
es.caib.carpeta.pluginsib.carpetafront.sistra1.level=2
es.caib.carpeta.pluginsib.carpetafront.sistra1.web=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra1.web"]]

# Sistra 2
es.caib.carpeta.pluginsib.carpetafront.sistra2.url=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra2.url"]]
es.caib.carpeta.pluginsib.carpetafront.sistra2.user=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra2.user"]]
es.caib.carpeta.pluginsib.carpetafront.sistra2.pass=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra2.pass"]]

# Regweb3.2
es.caib.carpeta.pluginsib.carpetafront.regweb32.url=[=SP["es.caib.carpeta.pluginsib.carpetafront.regweb32.url"]]
es.caib.carpeta.pluginsib.carpetafront.regweb32.user=[=SP["es.caib.carpeta.pluginsib.carpetafront.regweb32.user"]]
es.caib.carpeta.pluginsib.carpetafront.regweb32.pass=[=SP["es.caib.carpeta.pluginsib.carpetafront.regweb32.pass"]]
es.caib.carpeta.pluginsib.carpetafront.sistra.entidad=A04003003'
, true, 256, 'tramits');
