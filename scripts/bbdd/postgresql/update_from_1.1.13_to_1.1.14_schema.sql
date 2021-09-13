BEGIN;

--   Camps de BBDD per 'declaració d'accessibilitat' i per 'avis legal' #581 

ALTER TABLE car_entitat ADD COLUMN avislegal text;
ALTER TABLE car_entitat ADD COLUMN accessibilitat text;

COMMIT;