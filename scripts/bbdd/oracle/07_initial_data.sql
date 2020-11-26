 
-- Idiomes

INSERT INTO car_idioma(idiomaid, nom, suportat, ordre) VALUES ('ca', 'Català', 1, 0);
INSERT INTO car_idioma(idiomaid, nom, suportat, ordre) VALUES ('es', 'Castellano', 1, 1);
INSERT INTO car_idioma(idiomaid, nom, suportat, ordre) VALUES ('en', 'English', 1, 2);


-- Plugin de SISTRA

INSERT INTO car_traduccio VALUES (26);
INSERT INTO car_traduccio VALUES (36);


INSERT INTO car_traducciomap(traducciomapid, valor, idiomaid) VALUES (26, 'en', 'Sistra v1 and v2');
INSERT INTO car_traducciomap(traducciomapid, valor, idiomaid) VALUES (26, 'es', 'Sistra v1 y v2');
INSERT INTO car_traducciomap(traducciomapid, valor, idiomaid) VALUES (26, 'ca', 'Sistra v1 i v2');

INSERT INTO car_traducciomap(traducciomapid, valor, idiomaid) VALUES (36, 'en', 'Description Sistra v1 and v2');
INSERT INTO car_traducciomap(traducciomapid, valor, idiomaid) VALUES (36, 'es', 'Descripción Sistra v1 y v2');
INSERT INTO car_traducciomap(traducciomapid, valor, idiomaid) VALUES (36, 'ca', 'Descripció Sistra v1 i v2');


INSERT INTO car_plugin(
            nomid, descripcioid, classe, tipus, propietats, actiu)
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
            nomid, descripcioid, classe, tipus, propietats, actiu)
    VALUES (46, 56, 'org.fundaciobit.pluginsib.carpetafront.regweb3.Regweb3CarpetaFrontPlugin', 1, '# Common
es.caib.carpeta.pluginsib.carpetafront.regweb3.development=true

# Regweb3   
es.caib.carpeta.pluginsib.carpetafront.regweb3.url=[=SP[""]]
es.caib.carpeta.pluginsib.carpetafront.regweb3.user=[=SP[""]]
es.caib.carpeta.pluginsib.carpetafront.regweb3.pass=[=SP[""]]
es.caib.carpeta.pluginsib.carpetafront.regweb3.entidad=[=SP[""]]', 1);



-- Propietat Global

INSERT INTO car_propietatglobal(
            propietatglobalid, codi, value, descripcio, entitatid) VALUES (24, 'es.caib.carpeta.defaultentitycode', '', '<p>Codi de l''entitat per defecte a la que es donarà d''alta automàticament a un usuari just després d''haver-se creat a Carpeta</p>', NULL);

