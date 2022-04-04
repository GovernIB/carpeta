BEGIN;

-- Poder enviar notificacions al mòbil des de Carpeta #646 
 
CREATE SEQUENCE car_ciutada_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1000 CACHE 1;
  
  
CREATE TABLE public.car_ciutada
(
   ciutadaid bigint NOT NULL DEFAULT nextval('car_ciutada_seq'), 
   nif character varying(100) NOT NULL, 
   nom character varying(255), 
   llinatge1 character varying(255), 
   llinatge2 character varying(255), 
   empresa boolean NOT NULL,
   representantnif character varying(100), 
   representantnom character varying(255), 
   representantllinatge1 character varying(255), 
   representantllinatge2 character varying(255),
   datacreacio timestamp without time zone NOT NULL,
   mobileid character varying(255)
);

ALTER TABLE car_ciutada
  ADD CONSTRAINT car_ciutada_pk PRIMARY KEY (ciutadaid);
ALTER TABLE car_ciutada
  ADD CONSTRAINT car_ciutada_nif_rnif_uk UNIQUE (nif, representantnif);
  

create index car_ciutada_pk_i on car_ciutada (ciutadaid);

COMMIT;