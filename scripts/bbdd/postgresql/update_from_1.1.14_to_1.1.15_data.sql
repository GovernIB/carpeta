BEGIN;

-- Integració del Servei de consulta de dades de convivència #559 


INSERT INTO public.car_traduccio (traduccioid) VALUES (106);
INSERT INTO public.car_traduccio (traduccioid) VALUES (107);

INSERT INTO public.car_traducciomap (traducciomapid, idiomaid, valor) VALUES (106, 'ca', 'Consulta de dades de convivencia');
INSERT INTO public.car_traducciomap (traducciomapid, idiomaid, valor) VALUES (106, 'es', 'Consulta de datos de convivencia');
INSERT INTO public.car_traducciomap (traducciomapid, idiomaid, valor) VALUES (107, 'ca', 'Aquest servei permet consultar dades de convivència sobre els padrons municipals');
INSERT INTO public.car_traducciomap (traducciomapid, idiomaid, valor) VALUES (107, 'es', 'Este servicio permite consultar datos de convivencia sobre los padrones municipales');

INSERT INTO car_plugin (pluginid, nomid, classe, tipus, propietats, actiu, descripcioid, logoid, context) VALUES (95, 106, 'org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.PinbalConvivenciaCarpetaFrontPlugin', 1, '# Class => org.fundaciobit.pluginsib.carpetafront.pinbalconvivencia.PinbalConvivenciaCarpetaFrontPlugin

es.caib.carpeta.pluginsib.carpetafront.pinbalconvivencia.baseurl=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.baseurl"]]
es.caib.carpeta.pluginsib.carpetafront.pinbalconvivencia.username=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.username"]]
es.caib.carpeta.pluginsib.carpetafront.pinbalconvivencia.password=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.password"]]

es.caib.carpeta.pluginsib.carpetafront.pinbalconvivencia.codicertificat=SCDCPAJU

es.caib.carpeta.pluginsib.carpetafront.pinbalconvivencia.codiprocediment=CODSVDR_GBA_20121107
es.caib.carpeta.pluginsib.carpetafront.pinbalconvivencia.finalitat=Consulta dades convivencia
es.caib.carpeta.pluginsib.carpetafront.pinbalconvivencia.identificadorsolicitant=S0711001H
es.caib.carpeta.pluginsib.carpetafront.pinbalconvivencia.unitattramitadora=Servei d\'escolarització

# Necessari en DEV i PRE. NO es fan cridades reals sinó d,un joc de proves
# Exemples:   - 43103945J, AMADOR
#                   - 53745887Q, 
es.caib.carpeta.pluginsib.carpetafront.pinbalconvivencia.testnif=12345678Z
es.caib.carpeta.pluginsib.carpetafront.pinbalconvivencia.testsurname=AMADOR
es.caib.carpeta.pluginsib.carpetafront.pinbalconvivencia.codigoprovincia=07

es.caib.carpeta.pluginsib.carpetafront.pinbalconvivencia.development=true', true, 107, NULL, 'dadesconvivencia');


COMMIT;