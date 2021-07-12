
-- Integració del Servei de verificació i consulta de dades de família nombrosa #535 


INSERT INTO public.car_traduccio (traduccioid) VALUES (100);
INSERT INTO public.car_traduccio (traduccioid) VALUES (101);

INSERT INTO public.car_traducciomap (traducciomapid, idiomaid, valor) VALUES (100, 'ca', 'Servei de verificació i consulta de dades de família nombrosa');
INSERT INTO public.car_traducciomap (traducciomapid, idiomaid, valor) VALUES (100, 'es', 'Servicio de verificación y consulta de datos de família numerosa');
INSERT INTO public.car_traducciomap (traducciomapid, idiomaid, valor) VALUES (101, 'ca', 'Aquest servei permet consultar dades de família nombrosa');
INSERT INTO public.car_traducciomap (traducciomapid, idiomaid, valor) VALUES (101, 'es', 'Este servicio permite consultar los datos de familia numerosa.');


INSERT INTO car_plugin (pluginid, nomid, classe, tipus, propietats, actiu, descripcioid, logoid, context) VALUES (92, 100, 'org.fundaciobit.pluginsib.carpetafront.pinbalfamilia.PinbalFamiliaCarpetaFrontPlugin', 1, '# Class => org.fundaciobit.pluginsib.carpetafront.pinbalfamilia.PinbalFamiliaCarpetaFrontPlugin

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

es.caib.carpeta.pluginsib.carpetafront.pinbalfamilia.development=true', true, 101, NULL, 'familianombrosa');



