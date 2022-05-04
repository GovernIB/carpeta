
-- 04/05/2022 Nous camps fitxers per FAQ (Poder afegir captures de pantalla a les FAQ al backoffice #630)

ALTER TABLE car_preguntesfrequents ADD fitxer1id NUMBER(19,0);
ALTER TABLE car_preguntesfrequents ADD CONSTRAINT car_faq_fitxer_fitxer1_fk FOREIGN KEY (fitxer1id) REFERENCES car_fitxer (fitxerid);
CREATE INDEX car_faq_fitxer1id_fk_i on car_preguntesfrequents (fitxer1id);

ALTER TABLE car_preguntesfrequents ADD fitxer2id NUMBER(19,0);
ALTER TABLE car_preguntesfrequents ADD CONSTRAINT car_faq_fitxer_fitxer2_fk FOREIGN KEY (fitxer2id) REFERENCES car_fitxer (fitxerid);
CREATE INDEX car_faq_fitxer2id_fk_i on car_preguntesfrequents (fitxer2id);

ALTER TABLE car_preguntesfrequents ADD fitxer3id NUMBER(19,0);
ALTER TABLE car_preguntesfrequents ADD CONSTRAINT car_faq_fitxer_fitxer3_fk FOREIGN KEY (fitxer3id) REFERENCES car_fitxer (fitxerid);
CREATE INDEX car_faq_fitxer3id_fk_i on car_preguntesfrequents (fitxer3id);