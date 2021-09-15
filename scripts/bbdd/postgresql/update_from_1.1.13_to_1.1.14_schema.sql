BEGIN;

--   Camps de BBDD per 'declaració d'accessibilitat' i per 'avis legal' #581 

ALTER TABLE car_entitat ADD COLUMN avislegalca text;
ALTER TABLE car_entitat ADD COLUMN accessibilitatca text;
ALTER TABLE car_entitat ADD COLUMN avislegales text;
ALTER TABLE car_entitat ADD COLUMN accessibilitates text;


COMMIT;