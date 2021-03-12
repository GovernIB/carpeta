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



-- Plugin React de Policia: completar propietats #427



INSERT INTO car_plugin (pluginid, nomid, classe, tipus, propietats, actiu, descripcioid, logoid, context) VALUES (90, 91, 'org.fundaciobit.pluginsib.carpetafront.pinbalpolicia.PinbalPoliciaCarpetaFrontPlugin', 1, '# Class => org.fundaciobit.pluginsib.carpetafront.pinbalpolicia.PinbalPoliciaCarpetaFrontPlugin

es.caib.carpeta.pluginsib.carpetafront.pinbalpolicia.pinbal.baseurl=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.baseurl"]]
es.caib.carpeta.pluginsib.carpetafront.pinbalpolicia.pinbal.username=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.username"]]
es.caib.carpeta.pluginsib.carpetafront.pinbalpolicia.pinbal.password=[=SP["es.caib.carpeta.pluginsib.carpetafront.dadespersonals.pinbal.password"]]

es.caib.carpeta.pluginsib.carpetafront.pinbalpolicia.pinbal.codicertificat=SVDDGPCIWS02

es.caib.carpeta.pluginsib.carpetafront.pinbalpolicia.pinbal.nifemisor=S2816015H
es.caib.carpeta.pluginsib.carpetafront.pinbalpolicia.pinbal.nomemisor=DGP

es.caib.carpeta.pluginsib.carpetafront.pinbalpolicia.pinbal.niffuncionari=43125996F
es.caib.carpeta.pluginsib.carpetafront.pinbalpolicia.pinbal.nomfuncionari=Ana Bustos Nadal

es.caib.carpeta.pluginsib.carpetafront.pinbalpolicia.pinbal.codiprocediment=CODSVDR_GBA_20121107
es.caib.carpeta.pluginsib.carpetafront.pinbalpolicia.pinbal.nomprocediment=PRUEBAS DE INTEGRACION PARA GOBIERNO DE BALEARES
		
es.caib.carpeta.pluginsib.carpetafront.pinbalpolicia.pinbal.finalitat=Baremacions per el proces d''escolaritzacio

es.caib.carpeta.pluginsib.carpetafront.pinbalpolicia.pinbal.identificadorsolicitant=S0711001H
es.caib.carpeta.pluginsib.carpetafront.pinbalpolicia.pinbal.nomsolicitant=Conselleria d''Educació
es.caib.carpeta.pluginsib.carpetafront.pinbalpolicia.pinbal.unitattramitadora=Servei d''escolarització

# Necessari en DEV i PRE. NO es fan cridades reals sinó d,un joc de proves
# Exemples:   - 30000056Y, FUSTER
#             - 41107605G, JAUME
es.caib.carpeta.pluginsib.carpetafront.pinbalpolicia.pinbal.testnif=30000056Y
es.caib.carpeta.pluginsib.carpetafront.pinbalpolicia.pinbal.testsurname=FUSTER', true, 92, NULL, 'dadespersonalspolicia');


INSERT INTO public.car_traduccio (traduccioid) VALUES (91);
INSERT INTO public.car_traduccio (traduccioid) VALUES (92);


INSERT INTO public.car_traducciomap (traducciomapid, idiomaid, valor) VALUES (91, 'ca', 'Dades Personals Policia');
INSERT INTO public.car_traducciomap (traducciomapid, idiomaid, valor) VALUES (91, 'es', 'Datos Personales Policia');
INSERT INTO public.car_traducciomap (traducciomapid, idiomaid, valor) VALUES (92, 'ca', 'Dades Personals de la  Policia que apareixen al DNI');
INSERT INTO public.car_traducciomap (traducciomapid, idiomaid, valor) VALUES (92, 'es', 'Datos Personales de la Policia que aparecen en el DNI');


INSERT INTO car_plugin (pluginid, nomid, classe, tipus, propietats, actiu, descripcioid, logoid, context) VALUES (95, 96, 'org.fundaciobit.pluginsib.carpetafront.dadeslogin.DadesLoginCarpetaFrontPlugin', 1, '# Class => org.fundaciobit.pluginsib.carpetafront.dadeslogin.DadesLoginCarpetaFrontPlugin', true, 97, NULL, 'dadeslogin');



INSERT INTO public.car_traduccio (traduccioid) VALUES (96);
INSERT INTO public.car_traduccio (traduccioid) VALUES (97);




INSERT INTO public.car_traducciomap (traducciomapid, idiomaid, valor) VALUES (96, 'ca', 'Dades Login');
INSERT INTO public.car_traducciomap (traducciomapid, idiomaid, valor) VALUES (96, 'es', 'Datos Login');
INSERT INTO public.car_traducciomap (traducciomapid, idiomaid, valor) VALUES (97, 'ca', 'Informació de les dades de Login');
INSERT INTO public.car_traducciomap (traducciomapid, idiomaid, valor) VALUES (97, 'es', 'Información de los datos de Login');


