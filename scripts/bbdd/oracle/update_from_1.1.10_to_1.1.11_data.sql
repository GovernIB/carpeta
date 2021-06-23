
--  Adaptar la cridada al Client REST del plugin de pinbalpolicia #523 

UPDATE car_plugin SET propietats = '# Class => org.fundaciobit.pluginsib.carpetafront.pinbalpolicia.PinbalPoliciaCarpetaFrontPlugin

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

es.caib.carpeta.pluginsib.carpetafront.pinbalpolicia.development=true' WHERE classe = 'org.fundaciobit.pluginsib.carpetafront.pinbalpolicia.PinbalPoliciaCarpetaFrontPlugin';


-- Plugin React de Pinbal: estar al corrent de pagament amb la CAIB per a subvencions i ajudes #525


INSERT INTO public.car_traduccio (traduccioid) VALUES (98);
INSERT INTO public.car_traduccio (traduccioid) VALUES (99);

INSERT INTO public.car_traducciomap (traducciomapid, idiomaid, valor) VALUES (98, 'ca', 'Estar al corrent de pagament amb la CAIB per a subvencions i ajudes');
INSERT INTO public.car_traducciomap (traducciomapid, idiomaid, valor) VALUES (98, 'es', 'Estar al corriente del pago con la CAIB para subvenciones y ayudas');
INSERT INTO public.car_traducciomap (traducciomapid, idiomaid, valor) VALUES (99, 'ca', 'Aquest servei permet obtenir les dades de la situació de l''estat de deute del ciutadà o empresa objecte de la consulta.');
INSERT INTO public.car_traducciomap (traducciomapid, idiomaid, valor) VALUES (99, 'es', 'Este servicio permite obtener los datos de la situación del estado de deuda del ciudadano o empresa.');


INSERT INTO car_plugin (pluginid, nomid, classe, tipus, propietats, actiu, descripcioid, logoid, context) VALUES (91, 98, 'org.fundaciobit.pluginsib.carpetafront.pinbalayudasubvenciones.PinbalAyudaSubvencionesCarpetaFrontPlugin', 1, '# Class => org.fundaciobit.pluginsib.carpetafront.pinbalayudasubvenciones.PinbalAyudaSubvencionesCarpetaFrontPlugin

es.caib.carpeta.pluginsib.carpetafront.pinbalsubvenciones.baseurl=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.baseurl"]]
es.caib.carpeta.pluginsib.carpetafront.pinbalsubvenciones.username=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.username"]]
es.caib.carpeta.pluginsib.carpetafront.pinbalsubvenciones.password=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.password"]]

es.caib.carpeta.pluginsib.carpetafront.pinbalsubvenciones.codicertificat=SVDCCAACPASWS01

es.caib.carpeta.pluginsib.carpetafront.pinbalsubvenciones.codiprocediment=CODSVDR_GBA_20121107
es.caib.carpeta.pluginsib.carpetafront.pinbalsubvenciones.finalitat=Consulta estar al corrent de pagament a CAIB
es.caib.carpeta.pluginsib.carpetafront.pinbalsubvenciones.identificadorsolicitant=S0711001H
es.caib.carpeta.pluginsib.carpetafront.pinbalsubvenciones.unitattramitadora=Servei d''escolarització

es.caib.carpeta.pluginsib.carpetafront.pinbalsubvenciones.codigoprovincia=07
es.caib.carpeta.pluginsib.carpetafront.pinbalsubvenciones.codigocomunidadautonoma=04

# Necessari en DEV i PRE. NO es fan cridades reals sinó d,un joc de proves
# Exemples:   - 30000056Y, FUSTER
#             - 41107605G, JAUME
es.caib.carpeta.pluginsib.carpetafront.pinbalsubvenciones.testnif=30000056Y
es.caib.carpeta.pluginsib.carpetafront.pinbalsubvenciones.testsurname=FUSTER

es.caib.carpeta.pluginsib.carpetafront.pinbalsubvenciones.development=true', true, 99, NULL, 'ajudasubvencions');



