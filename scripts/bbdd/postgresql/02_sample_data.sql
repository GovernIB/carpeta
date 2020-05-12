
-- Crear unitats orgàniques d'exemple
INSERT INTO car_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (1, 'A00000001', '2019-12-14', 0, 'Unitat 1');
INSERT INTO car_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (2, 'A00000002', '2019-12-31', 0, 'Unitat 2');
INSERT INTO car_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (3, 'A00000003', '2020-01-21', 0, 'Unitat 3');
INSERT INTO car_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (4, 'A00000004', '2019-12-26', 0, 'Unitat 4');
INSERT INTO car_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (5, 'A00000005', '2020-01-14', 0, 'Unitat 5');
INSERT INTO car_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (6, 'A00000006', '2020-01-24', 0, 'Unitat 6');
INSERT INTO car_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (7, 'A00000007', '2020-01-14', 0, 'Unitat 7');
INSERT INTO car_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (8, 'A00000008', '2019-12-13', 0, 'Unitat 8');
INSERT INTO car_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (9, 'A00000009', '2019-12-01', 0, 'Unitat 9');
INSERT INTO car_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (10, 'A00000010', '2019-12-31', 0, 'Unitat 10');
INSERT INTO car_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (11, 'A00000011', '2019-12-10', 0, 'Unitat 11');
INSERT INTO car_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (12, 'A00000012', '2020-01-15', 0, 'Unitat 12');

-- Crear procediments d'exemple
INSERT INTO car_procediment (id, codisia, nom, unitatorganicaid) VALUES (1, '000001', 'Procediment 1', 1);
INSERT INTO car_procediment (id, codisia, nom, unitatorganicaid) VALUES (2, '000002', 'Procediment 2', 1);
INSERT INTO car_procediment (id, codisia, nom, unitatorganicaid) VALUES (3, '000003', 'Procediment 3', 2);
INSERT INTO car_procediment (id, codisia, nom, unitatorganicaid) VALUES (4, '000004', 'Procediment 4', 3);

-- Actualitzar seqüència de les unitats orgàniques
SELECT pg_catalog.setval('car_unitatorganica_seq', 12, true);
-- Actualitzar seqüència dels procediments
SELECT pg_catalog.setval('car_procediment_seq', 4, true);

