--11/02/2021 Nou plugin de Registre atacants als nous mètodes de RegWeb 3.2 #333
INSERT INTO car_traduccio VALUES (96);
INSERT INTO car_traduccio VALUES (97);

INSERT INTO car_traducciomap VALUES (96, 'en', 'Regweb 3.2');
INSERT INTO car_traducciomap VALUES (96, 'ca', 'Regweb 3.2');
INSERT INTO car_traducciomap VALUES (96, 'es', 'Regweb 3.2');
INSERT INTO car_traducciomap VALUES (97, 'en', 'Regweb 3.2');
INSERT INTO car_traducciomap VALUES (97, 'ca', 'Regweb 3.2');
INSERT INTO car_traducciomap VALUES (97, 'es', 'Regweb 3.2');



INSERT INTO car_plugin(nomid, classe, tipus, propietats, actiu, descripcioid) VALUES (96, 'org.fundaciobit.pluginsib.carpetafront.regweb32.Regweb32CarpetaFrontPlugin', 1, '# Common
es.caib.carpeta.pluginsib.carpetafront.regweb32.development=true
es.caib.carpeta.pluginsib.carpetafront.regweb32.concsv.url=https://dev.caib.es/concsv/view.xhtml?hash=
# Regweb3.2
es.caib.carpeta.pluginsib.carpetafront.regweb32.url=[=SP["es.caib.carpeta.pluginsib.carpetafront.regweb32.url"]]
es.caib.carpeta.pluginsib.carpetafront.regweb32.user=[=SP["es.caib.carpeta.pluginsib.carpetafront.regweb32.user"]]
es.caib.carpeta.pluginsib.carpetafront.regweb32.pass=[=SP["es.caib.carpeta.pluginsib.carpetafront.regweb32.pass"]]
es.caib.carpeta.pluginsib.carpetafront.regweb32.entidad=A04003003', 1, 97);
