
INSERT INTO car_idioma(idiomaid, nom, suportat, ordre) VALUES ('ca', 'Catal�', 1, 0);
INSERT INTO car_idioma(idiomaid, nom, suportat, ordre) VALUES ('es', 'Castellano', 1, 1);
INSERT INTO car_idioma(idiomaid, nom, suportat, ordre) VALUES ('en', 'English', 1, 2);

INSERT INTO car_traduccio VALUES (26);
INSERT INTO car_traduccio VALUES (36);

INSERT INTO car_traducciomap VALUES (26, 'en', 'Sistra v1 - v2');
INSERT INTO car_traducciomap VALUES (26, 'es', 'Sistra v1 - v2');
INSERT INTO car_traducciomap VALUES (26, 'ca', 'Sistra v1 - v2');

INSERT INTO car_traducciomap VALUES (36, 'en', 'Description Sistra v1 - v2');
INSERT INTO car_traducciomap VALUES (36, 'es', 'Descripci�n Sistra v1 - v2');
INSERT INTO car_traducciomap VALUES (36, 'ca', 'Descripci� Sistra v1 - v2');


INSERT INTO car_plugin(
            pluginid, nomid, descripcioid, classe, tipus, propietats, actiu)
    VALUES (1, 26, 36, 'org.fundaciobit.pluginsib.carpetafront.sistra.SistraCarpetaFrontPlugin', 1, '# Common
es.caib.carpeta.pluginsib.carpetafront.sistra.development=true

# Sistra 1
es.caib.carpeta.pluginsib.carpetafront.sistra1.url=https://dev.caib.es/sistraback/zonaperws/services/v2/BackofficeFacade
es.caib.carpeta.pluginsib.carpetafront.sistra1.user=$carpeta_sistra
es.caib.carpeta.pluginsib.carpetafront.sistra1.pass=setpassword
es.caib.carpeta.pluginsib.carpetafront.sistra1.level=2
es.caib.carpeta.pluginsib.carpetafront.sistra1.web=https://www.caib.es/sistrafront/zonaperfront/protected/init.do', 1);


INSERT INTO car_propietatglobal(
            propietatglobalid, codi, value, descripcio, entitatid) VALUES (24, 'es.caib.carpeta.defaultentitycode', '', '<p>Codi de l''entitat per defecte a la que es donar� d''alta autom�ticament a un usuari just despr�s d''haver-se creat a Carpeta</p>', NULL);

