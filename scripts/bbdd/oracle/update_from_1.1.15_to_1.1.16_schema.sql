

-- Poder enviar notificacions al mòbil des de Carpeta #646 
 
CREATE SEQUENCE car_ciutada_seq START WITH 1000 INCREMENT BY 1;
  
  
CREATE TABLE car_ciutada
(
   ciutadaid number(19,0)  NOT NULL DEFAULT car_ciutada_seq.nextval, 
   nif varchar2(100 char) NOT NULL, 
   nom varchar2(255 char), 
   llinatge1 varchar2(255 char), 
   llinatge2 varchar2(255 char), 
   empresa number(1,0) NOT NULL,
   representantnif varchar2(100 char), 
   representantnom varchar2(255 char), 
   representantllinatge1 varchar2(255 char), 
   representantllinatge2 varchar2(255 char),
   datacreacio timestamp NOT NULL,
   mobileid varchar2(255 char)
);

ALTER TABLE car_ciutada
  ADD CONSTRAINT car_ciutada_pk PRIMARY KEY (ciutadaid);
  
ALTER TABLE car_ciutada
  ADD CONSTRAINT car_ciutada_nif_rnif_uk UNIQUE (nif, representantnif);

create index car_ciutada_pk_i on car_ciutada (ciutadaid);

