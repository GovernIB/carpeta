
--   Crear una taula per guardar les preguntes freqüents #622 


create sequence car_preguntesfrequents_seq start with 1 increment by  1;


CREATE TABLE car_preguntesfrequents
(
   preguntesfrequentsid number(19,0) NOT NULL DEFAULT car_preguntesfrequents_seq.nextval, 
   enunciatid number(19,0) NOT NULL, 
   respostaid number(19,0) NOT NULL, 
   ordre number(10,0) NOT NULL, 
   entitatid number(19,0) NOT NULL
);


alter table car_preguntesfrequents add constraint car_preguntesfrequents_pk primary key (preguntesfrequentsid);

alter table car_preguntesfrequents add constraint car_faq_traduccio_enun_fk FOREIGN KEY (enunciatid) REFERENCES car_traduccio (traduccioid);

alter table car_preguntesfrequents add constraint car_faq_traduccio_resp_fk FOREIGN KEY (respostaid) REFERENCES car_traduccio (traduccioid);

alter table car_preguntesfrequents add constraint car_faq_entitat_ent_fk FOREIGN KEY (entitatid) REFERENCES car_entitat (entitatid);



create index car_preguntesfrequents_pk_i on car_preguntesfrequents (preguntesfrequentsid);

create index car_faq_enunciatid_fk_i on car_preguntesfrequents (enunciatid);

create index car_faq_respostaid_fk_i on car_preguntesfrequents (respostaid);

create index car_faq_entitatid_fk_i on car_preguntesfrequents (entitatid);



ALTER TABLE car_acces ADD idsessio varchar2(255 char);
