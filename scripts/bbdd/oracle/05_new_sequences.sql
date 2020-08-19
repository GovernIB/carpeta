create sequence car_acces_seq start 1000 increment 1;
create sequence car_auditoria_seq start 1000 increment 1;
create sequence car_avis_seq start 1000 increment 1;
create sequence car_enllaz_seq start 1000 increment 1;
create sequence car_entitat_seq start 1000 increment 1;
create sequence car_estadistica_seq start 1000 increment 1;
create sequence car_fitxer_seq start 1000 increment 1;
create sequence car_log_seq start 1000 increment 1;
create sequence car_plugin_seq start 1000 increment 1;
create sequence car_propietatglobal_seq start 1000 increment 1;
create sequence car_traduccio_seq start 1000 increment 1;
create sequence car_usuari_seq start 1000 increment 1;
create sequence car_usuarientitat_seq start 1000 increment 1;

grant select, alter on car_acces_seq to www_carpeta;
grant select, alter on car_auditoria_seq to www_carpeta;
grant select, alter on car_avis_seq to www_carpeta;
grant select, alter on car_enllaz_seq to www_carpeta;
grant select, alter on car_entitat_seq to www_carpeta;
grant select, alter on car_estadistica_seq to www_carpeta;
grant select, alter on car_fitxer_seq to www_carpeta;
grant select, alter on car_log_seq to www_carpeta;
grant select, alter on car_plugin_seq to www_carpeta;
grant select, alter on car_propietatglobal_seq to www_carpeta;
grant select, alter on car_traduccio_seq to www_carpeta;
grant select, alter on car_usuari_seq to www_carpeta;
grant select, alter on car_usuarientitat_seq to www_carpeta;


ALTER TABLE car_acces ALTER COLUMN accesid SET DEFAULT car_acces_seq.nextval;
ALTER TABLE car_auditoria ALTER COLUMN auditoriaid SET DEFAULT car_auditoria_seq.nextval;
ALTER TABLE car_avis ALTER COLUMN avisid SET DEFAULT car_avis_seq.nextval;
ALTER TABLE car_enllaz ALTER COLUMN enllazid SET DEFAULT car_enllaz_seq.nextval;
ALTER TABLE car_entitat ALTER COLUMN entitatid SET DEFAULT car_entitat_seq.nextval;
ALTER TABLE car_estadistica ALTER COLUMN estadisticaid SET DEFAULT car_estadistica_seq.nextval;
ALTER TABLE car_fitxer ALTER COLUMN fitxerid SET DEFAULT car_fitxer_seq.nextval;
ALTER TABLE car_log ALTER COLUMN logid SET DEFAULT car_log_seq.nextval;
ALTER TABLE car_plugin ALTER COLUMN pluginid SET DEFAULT car_plugin_seq.nextval;
ALTER TABLE car_propietatglobal ALTER COLUMN propietatglobalid SET DEFAULT car_propietatglobal_seq.nextval;
ALTER TABLE car_traduccio ALTER COLUMN traduccioid SET DEFAULT car_traduccio_seq.nextval;
ALTER TABLE car_usuari ALTER COLUMN usuariid SET DEFAULT car_usuari_seq.nextval;
ALTER TABLE car_usuarientitat ALTER COLUMN usuarientitatid SET DEFAULT car_usuarientitat_seq.nextval;


DROP SEQUENCE car_carpeta_seq;
