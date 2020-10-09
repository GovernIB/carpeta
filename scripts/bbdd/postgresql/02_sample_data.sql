
INSERT INTO car_idioma VALUES ('ca', 'Català', true, 0);
INSERT INTO car_idioma VALUES ('es', 'Castellano', true, 1);
INSERT INTO car_idioma VALUES ('en', 'English', true, 2);


--

INSERT INTO car_traduccio VALUES (26);
INSERT INTO car_traduccio VALUES (36);


INSERT INTO car_traducciomap VALUES (26, 'en', 'Sistra v1 & v2');
INSERT INTO car_traducciomap VALUES (26, 'es', 'Sistra v1 & v2');
INSERT INTO car_traducciomap VALUES (26, 'ca', 'Sistra v1 & v2');

INSERT INTO car_traducciomap VALUES (36, 'en', 'Description Sistra v1 & v2');
INSERT INTO car_traducciomap VALUES (36, 'es', 'Descripción Sistra v1 & v2');
INSERT INTO car_traducciomap VALUES (36, 'ca', 'Descripció Sistra v1 & v2');


INSERT INTO car_plugin(
            nomid, descripcioid, classe, tipus, propietats, actiu)
    VALUES (26, 36, 'org.fundaciobit.pluginsib.carpetafront.sistra.SistraCarpetaFrontPlugin', 1, '# Common
es.caib.carpeta.pluginsib.carpetafront.sistra.development=true

# Sistra 1
es.caib.carpeta.pluginsib.carpetafront.sistra1.url=https://dev.caib.es/sistraback/zonaperws/services/v2/BackofficeFacade
es.caib.carpeta.pluginsib.carpetafront.sistra1.user=$carpeta_sistra
es.caib.carpeta.pluginsib.carpetafront.sistra1.pass=setpassword
es.caib.carpeta.pluginsib.carpetafront.sistra1.level=2
es.caib.carpeta.pluginsib.carpetafront.sistra1.web=https://www.caib.es/sistrafront/zonaperfront/protected/init.do

# Sistra 2
es.caib.carpeta.pluginsib.carpetafront.sistra2.url=https://dev.caib.es/sistramitws/rest/externa/v1
es.caib.carpeta.pluginsib.carpetafront.sistra2.user=$carpeta_sistra
es.caib.carpeta.pluginsib.carpetafront.sistra2.pass=setpassword', true);

--   

INSERT INTO car_traduccio VALUES (46);
INSERT INTO car_traduccio VALUES (56);

INSERT INTO car_traducciomap VALUES (46, 'en', 'Registre');
INSERT INTO car_traducciomap VALUES (46, 'es', 'Registro');
INSERT INTO car_traducciomap VALUES (46, 'ca', 'Registre');

INSERT INTO car_traducciomap VALUES (56, 'en', 'REGWEB3 Registre electrònic');
INSERT INTO car_traducciomap VALUES (56, 'es', 'REGWEB3 Registro electrónico');
INSERT INTO car_traducciomap VALUES (56, 'ca', 'REGWEB3 Registre electrònic');


INSERT INTO car_plugin(
            nomid, descripcioid, classe, tipus, propietats, actiu)
    VALUES (46, 56, 'org.fundaciobit.pluginsib.carpetafront.regweb3.Regweb3CarpetaFrontPlugin', 1, '# Common
es.caib.carpeta.pluginsib.carpetafront.regweb3.development=true

# Regweb3
es.caib.carpeta.pluginsib.carpetafront.regweb3.url=http://registre3.fundaciobit.org/regweb3/ws/v3/RegWebAsientoRegistral
es.caib.carpeta.pluginsib.carpetafront.regweb3.user=caibapp
es.caib.carpeta.pluginsib.carpetafront.regweb3.pass=setpassword
es.caib.carpeta.pluginsib.carpetafront.regweb3.entidad=A04003003
es.caib.carpeta.pluginsib.carpetafront.regweb3.web=http://localhost:9080/carpeta/registro/detalle/{0}', true);


--


INSERT INTO car_propietatglobal VALUES (24, 'es.caib.carpeta.defaultentitycode', 'caib', '<p>Codi de l''entitat per defecte a la que es donar&agrave; d''alta autom&agrave;ticament a un usuari just despr&eacute;s d''haver-se creat a Carpeta</p>', NULL);




