
-- 07/04/2021  Procés automàtic per esborrar els logs de forma periòdica #344 

INSERT INTO car_propietatglobal (propietatglobalid, codi, value, descripcio, entitatid) VALUES (101, 'es.caib.carpeta.esborrarlogs.hora', '4', '<p>Valor enter, entre les 0 i 23 que representa l''hora en format 24H que volem que s''executi la tasca d''esborrar logs. <br /><br />IMPORTANT: Si es fa un canvi en aquest valor, s''ha de reiniciar el Jboss per a que tingui efecte.</p>', NULL);

INSERT INTO car_propietatglobal (propietatglobalid, codi, value, descripcio, entitatid) VALUES (102, 'es.caib.carpeta.esborrarlogs.dies', '90', '<p>Valor enter que representa el n&uacute;mero de dies que volem mantenir els logs. <br /><br />IMPORTANT: Si es fa un canvi en aquest valor, s''ha de reiniciar el Jboss per a que tingui efecte.</p>', NULL);


-- Redirigir des del plugin de SISTRA al detall de Registre #401
 
UPDATE car_plugin SET propietats = CONCAT (propietats, '

# Regweb3.2
es.caib.carpeta.pluginsib.carpetafront.regweb32.url=[=SP["es.caib.carpeta.pluginsib.carpetafront.regweb32.url"]]
es.caib.carpeta.pluginsib.carpetafront.regweb32.user=[=SP["es.caib.carpeta.pluginsib.carpetafront.regweb32.user"]]
es.caib.carpeta.pluginsib.carpetafront.regweb32.pass=[=SP["es.caib.carpeta.pluginsib.carpetafront.regweb32.pass"]]
es.caib.carpeta.pluginsib.carpetafront.sistra.entidad=A04003003') 
WHERE classe = 'org.fundaciobit.pluginsib.carpetafront.sistra.SistraCarpetaFrontPlugin';