
BEGIN;

DELETE FROM car_preguntesfrequents;

ALTER TABLE car_preguntesfrequents DROP COLUMN respostaid;
ALTER TABLE car_preguntesfrequents DROP COLUMN fitxer1id;
ALTER TABLE car_preguntesfrequents DROP COLUMN fitxer2id;
ALTER TABLE car_preguntesfrequents DROP COLUMN fitxer3id;
ALTER TABLE car_preguntesfrequents ADD COLUMN respostaca text NOT NULL;
ALTER TABLE car_preguntesfrequents ADD COLUMN respostaes text NOT NULL;

COMMIT;
