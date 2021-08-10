BEGIN;

--  Integració del Servei d'estar al corrent de pagament amb la CAIB per a contractacions. #555 


INSERT INTO public.car_traduccio (traduccioid) VALUES (102);
INSERT INTO public.car_traduccio (traduccioid) VALUES (103);

INSERT INTO public.car_traducciomap (traducciomapid, idiomaid, valor) VALUES (102, 'ca', 'Estar al corrent de pagament per a contractacions');
INSERT INTO public.car_traducciomap (traducciomapid, idiomaid, valor) VALUES (102, 'es', 'Estar al corriente de pago para contrataciones');
INSERT INTO public.car_traducciomap (traducciomapid, idiomaid, valor) VALUES (103, 'ca', 'Aquest servei permet obtenir les dades de la situació de l''estat de deute del ciutadà o empresa objecte de la consulta amb la CAIB.');
INSERT INTO public.car_traducciomap (traducciomapid, idiomaid, valor) VALUES (103, 'es', 'Este servicio permite obtener los datos de la situación del estado de deuda del ciudadano o empresa objeto de la consulta con la CAIB.');


INSERT INTO car_plugin (pluginid, nomid, classe, tipus, propietats, actiu, descripcioid, logoid, context) VALUES (93, 102, 'org.fundaciobit.pluginsib.carpetafront.pinbalcontrataciones.PinbalContratacionesCarpetaFrontPlugin', 1, '# Class => org.fundaciobit.pluginsib.carpetafront.pinbalcontrataciones.PinbalContratacionesCarpetaFrontPlugin

es.caib.carpeta.pluginsib.carpetafront.pinbalcontrataciones.baseurl=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.baseurl"]]
es.caib.carpeta.pluginsib.carpetafront.pinbalcontrataciones.username=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.username"]]
es.caib.carpeta.pluginsib.carpetafront.pinbalcontrataciones.password=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.password"]]

es.caib.carpeta.pluginsib.carpetafront.pinbalcontrataciones.codicertificat=SVDCCAACPCWS01

es.caib.carpeta.pluginsib.carpetafront.pinbalcontrataciones.codiprocediment=CODSVDR_GBA_20121107
es.caib.carpeta.pluginsib.carpetafront.pinbalcontrataciones.finalitat=Consulta familia nombrosa
es.caib.carpeta.pluginsib.carpetafront.pinbalcontrataciones.identificadorsolicitant=S0711001H
es.caib.carpeta.pluginsib.carpetafront.pinbalcontrataciones.unitattramitadora=Servei d''escolarització

es.caib.carpeta.pluginsib.carpetafront.pinbalcontrataciones.codigocomunidadautonoma=04
es.caib.carpeta.pluginsib.carpetafront.pinbalcontrataciones.codigoprovincia=07

# Necessari en DEV i PRE. NO es fan cridades reals sinó d,un joc de proves
# Exemples:   - 24255536N, FUSTER
es.caib.carpeta.pluginsib.carpetafront.pinbalcontrataciones.testnif=24255536N
es.caib.carpeta.pluginsib.carpetafront.pinbalcontrataciones.testsurname=FUSTER

es.caib.carpeta.pluginsib.carpetafront.pinbalcontrataciones.development=true', true, 103, NULL, 'pinbalcontractacions');

COMMIT;