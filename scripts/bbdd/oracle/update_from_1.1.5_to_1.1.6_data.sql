
-- 14/12/2020 Implementar Plugin de Notificacions #270
INSERT INTO car_traduccio VALUES (66);
INSERT INTO car_traduccio VALUES (76);

INSERT INTO car_traducciomap VALUES (66, 'en', 'Notifications');
INSERT INTO car_traducciomap VALUES (66, 'ca', 'Notificacions');
INSERT INTO car_traducciomap VALUES (66, 'es', 'Notificaciones');
INSERT INTO car_traducciomap VALUES (76, 'en', 'Notifications');
INSERT INTO car_traducciomap VALUES (76, 'ca', 'Notificacions');
INSERT INTO car_traducciomap VALUES (76, 'es', 'Notificaciones');

INSERT INTO car_plugin(pluginid, nomid, classe, tipus, propietats, actiu, descripcioid) VALUES (3, 66, 'org.fundaciobit.pluginsib.carpetafront.notib.NotibCarpetaFrontPlugin', 1, '# Class => org.fundaciobit.pluginsib.carpetafront.notib.NotibCarpetaFrontPlugin

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

-- 21/12/2020 Refactoritzar estadístiques #286
TRUNCATE TABLE car_estadistica;

-- 22/12/2020  Implementar Plugin de Dades Personals #283
INSERT INTO car_traduccio VALUES (86);
INSERT INTO car_traduccio VALUES (87);

INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (86, 'en', 'Personal Information');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (86, 'ca', 'Dades Personals');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (86, 'es', 'Datos Personales');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (87, 'en', 'Personal Information');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (87, 'ca', 'Dades Personals');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (87, 'es', 'Datos Personales');


INSERT INTO car_plugin(pluginid, nomid, classe, tipus, propietats, actiu, descripcioid) VALUES (85, 86, 'org.fundaciobit.pluginsib.carpetafront.dadespersonalsreact.DadesPersonalsReactCarpetaFrontPlugin', 1, NULL, true, 87);
