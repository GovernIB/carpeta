BEGIN;

--   Millores a les cerques dins Accessos i Logs del Backoffice #633

ALTER TABLE car_log ADD COLUMN idsessio text;


COMMIT;