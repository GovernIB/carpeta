BEGIN;

-- 16/08/2022 Actualitzar Carpeta amb els darrers canvis de GenApp #689

 SELECT setval('car_notificacioapp_seq', 1000, true);
 SELECT setval('car_preguntesfrequents_seq', 1000, true);


COMMIT;