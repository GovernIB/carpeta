 
 
-- Idiomes

INSERT INTO car_idioma(idiomaid, nom, suportat, ordre) VALUES ('ca', 'Català', 1, 0);
INSERT INTO car_idioma(idiomaid, nom, suportat, ordre) VALUES ('es', 'Castellano', 1, 1);
INSERT INTO car_idioma(idiomaid, nom, suportat, ordre) VALUES ('en', 'English', 1, 2);


-- Plugin de SISTRA

INSERT INTO car_traduccio VALUES (26);
INSERT INTO car_traduccio VALUES (36);


INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (26, 'en', 'Sistra v1 and v2');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (26, 'es', 'Sistra v1 y v2');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (26, 'ca', 'Sistra v1 i v2');

INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (36, 'en', 'Description Sistra v1 and v2');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (36, 'es', 'Descripción Sistra v1 y v2');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (36, 'ca', 'Descripció Sistra v1 i v2');


INSERT INTO car_plugin(
           pluginid, nomid, descripcioid, classe, tipus, propietats, actiu)
    VALUES (1, 26, 36, 'org.fundaciobit.pluginsib.carpetafront.sistra.SistraCarpetaFrontPlugin', 1, '# Common
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
es.caib.carpeta.pluginsib.carpetafront.sistra2.pass=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra2.pass"]]', 1);

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
            pluginid, nomid, descripcioid, classe, tipus, propietats, actiu)
    VALUES (2, 46, 56, 'org.fundaciobit.pluginsib.carpetafront.regweb3.Regweb3CarpetaFrontPlugin', 1, '# Common
es.caib.carpeta.pluginsib.carpetafront.regweb3.development=true

# Regweb3   
es.caib.carpeta.pluginsib.carpetafront.regweb3.url=[=SP["es.caib.carpeta.pluginsib.carpetafront.regweb3.url"]]
es.caib.carpeta.pluginsib.carpetafront.regweb3.user=[=SP["es.caib.carpeta.pluginsib.carpetafront.regweb3.user"]]
es.caib.carpeta.pluginsib.carpetafront.regweb3.pass=[=SP["es.caib.carpeta.pluginsib.carpetafront.regweb3.pass"]]
es.caib.carpeta.pluginsib.carpetafront.regweb3.entidad=A04003003', 1);

-- Plugin de NOTIB

INSERT INTO car_traduccio VALUES (66);
INSERT INTO car_traduccio VALUES (76);

INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (66, 'en', 'Notifications');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (66, 'ca', 'Notificacions');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (66, 'es', 'Notificaciones');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (76, 'en', 'Notifications');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (76, 'ca', 'Notificacions');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (76, 'es', 'Notificaciones');



INSERT INTO car_plugin(pluginid, nomid, classe, tipus, propietats, actiu, descripcioid) VALUES (3, 66, 
'org.fundaciobit.pluginsib.carpetafront.notib.NotibCarpetaFrontPlugin', 1, 
'# Class => org.fundaciobit.pluginsib.carpetafront.notib.NotibCarpetaFrontPlugin

# Common
es.caib.carpeta.pluginsib.carpetafront.notib.development=true

#
# Sistra 1
es.caib.carpeta.pluginsib.carpetafront.notib.sistra1.url=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra1.url"]]
es.caib.carpeta.pluginsib.carpetafront.notib.sistra1.user=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra1.user"]]
es.caib.carpeta.pluginsib.carpetafront.notib.sistra1.pass=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra1.pass"]]

# NOTIFICACIONES
es.caib.carpeta.pluginsib.carpetafront.notib.notificaciones.url=https://sede.administracion.gob.es/carpeta/notificaciones/notifica/consultaNotificaciones.htm

# Detall Notificacions CAIB: NOTIB
es.caib.carpeta.pluginsib.carpetafront.notib.notificaciones.detalle.pendientes.url=https://dehu.redsara.es/notificaciones-pendientes
es.caib.carpeta.pluginsib.carpetafront.notib.notificaciones.detalle.realizadas.url=https://dehu.redsara.es/notificaciones-realizadas
es.caib.carpeta.pluginsib.carpetafront.notib.comunicaciones.detalle.url=https://dehu.redsara.es/comunicaciones', true, 76);


--  Plugin de Dades Personals 
INSERT INTO car_traduccio VALUES (86);
INSERT INTO car_traduccio VALUES (87);

INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (86, 'en', 'Personal Information');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (86, 'ca', 'Dades Personals');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (86, 'es', 'Datos Personales');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (87, 'en', 'Personal Information');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (87, 'ca', 'Dades Personals');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (87, 'es', 'Datos Personales');


INSERT INTO car_plugin(pluginid, nomid, classe, tipus, propietats, actiu, descripcioid) VALUES (4, 86, 
'org.fundaciobit.pluginsib.carpetafront.dadespersonalsreact.DadesPersonalsReactCarpetaFrontPlugin', 1, 
'# Class => org.fundaciobit.pluginsib.carpetafront.dadespersonalsreact.DadesPersonalsReactCarpetaFrontPlugin

es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.baseurl=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.baseurl"]]
es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.username=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.username"]]
es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.password=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.password"]]


# Necessari en DEV i PRE. NO es fan cridades reals sinó d,un joc de proves
# Exemples:   - 30000056Y, FUSTER
#             - 41107605G, JAUME
es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.testnif=30000056Y
es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.testsurname=FUSTER', 1, 87);



-- Propietat Global

INSERT INTO car_propietatglobal(
            propietatglobalid, codi, value, descripcio, entitatid) VALUES (24, 'es.caib.carpeta.defaultentitycode', '', 
			'<p>Codi de l''entitat per defecte a la que es donarà d''alta automàticament a un usuari just després d''haver-se creat a Carpeta</p>', NULL);



-- Plugin de "Els Meus Expedients"

INSERT INTO car_traduccio VALUES (94);
INSERT INTO car_traduccio VALUES (95);


INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (94, 'ca', 'Els meus expedients');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (94, 'es', 'Mis Expedientes');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (95, 'ca', 'Els meus expedients');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (95, 'es', 'Mis Expedientes');



INSERT INTO car_plugin(pluginid, nomid, classe, tipus, propietats, actiu, descripcioid) VALUES (90, 94, 'org.fundaciobit.pluginsib.carpetafront.expedients.ExpedientsCarpetaFrontPlugin', 1, '# Classe i Propietats mínimes del Plugin d''Arxiu per fer Consultes
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
es.caib.carpeta.pluginsib.carpetafront.expedients.rolsac.readtimeout=20000', 1, 95);