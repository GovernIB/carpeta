

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

