

-- 14/05/2021 Ficar Funcionalitat de reprendre tramits inacabats dins plugins de tipus public #494 


INSERT INTO public.car_traduccio VALUES (142);
INSERT INTO public.car_traduccio VALUES (143);

INSERT INTO public.car_traducciomap(traducciomapid, idiomaid, valor) VALUES (142, 'ca', 'Reprendre Tràmit Sistra');
INSERT INTO public.car_traducciomap(traducciomapid, idiomaid, valor) VALUES (142, 'es', 'Retomar Trámite Sistra');
INSERT INTO public.car_traducciomap(traducciomapid, idiomaid, valor) VALUES (143, 'ca', 'Reprendre Tràmit Sistra');
INSERT INTO public.car_traducciomap(traducciomapid, idiomaid, valor) VALUES (143, 'es', 'Retomar Trámite Sistra');



INSERT INTO public.car_plugin(pluginid, nomid, classe, tipus, propietats, actiu, descripcioid, logoid, context) VALUES (11, 142, 'org.fundaciobit.pluginsib.carpetafront.reprendretramitsistra.ReprendreTramitSistraCarpetaFrontPlugin', 2, '# Common
es.caib.carpeta.pluginsib.carpetafront.reprendretramitsistra.development=true


# Sistra 1
es.caib.carpeta.pluginsib.carpetafront.reprendretramitsistra.url=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra1.url"]]
es.caib.carpeta.pluginsib.carpetafront.reprendretramitsistra.user=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra1.user"]]
es.caib.carpeta.pluginsib.carpetafront.reprendretramitsistra.pass=[=SP["es.caib.carpeta.pluginsib.carpetafront.sistra1.pass"]]', 1, 143, NULL, 'reprendretramit');


