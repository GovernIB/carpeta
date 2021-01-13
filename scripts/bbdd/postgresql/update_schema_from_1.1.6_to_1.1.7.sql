-- 13/01/2021 Es crearan auditories només de les accions del backoffice #278

ALTER TABLE car_auditoria DROP COLUMN usuariclave;
ALTER TABLE car_auditoria DROP COLUMN pluginid;