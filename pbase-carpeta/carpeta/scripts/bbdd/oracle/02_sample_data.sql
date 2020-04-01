
-- Crear unitats orgàniques d'exemple
INSERT INTO car_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (1, 'A00000001', TO_DATE('2019-12-14', 'YYYY-MM-DD'), 0, 'Unitat 1');
INSERT INTO car_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (2, 'A00000002', TO_DATE('2019-12-31', 'YYYY-MM-DD'), 0, 'Unitat 2');
INSERT INTO car_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (3, 'A00000003', TO_DATE('2020-01-21', 'YYYY-MM-DD'), 0, 'Unitat 3');
INSERT INTO car_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (4, 'A00000004', TO_DATE('2019-12-26', 'YYYY-MM-DD'), 0, 'Unitat 4');
INSERT INTO car_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (5, 'A00000005', TO_DATE('2020-01-14', 'YYYY-MM-DD'), 0, 'Unitat 5');
INSERT INTO car_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (6, 'A00000006', TO_DATE('2020-01-24', 'YYYY-MM-DD'), 0, 'Unitat 6');
INSERT INTO car_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (7, 'A00000007', TO_DATE('2020-01-14', 'YYYY-MM-DD'), 0, 'Unitat 7');
INSERT INTO car_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (8, 'A00000008', TO_DATE('2019-12-13', 'YYYY-MM-DD'), 0, 'Unitat 8');
INSERT INTO car_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (9, 'A00000009', TO_DATE('2019-12-01', 'YYYY-MM-DD'), 0, 'Unitat 9');
INSERT INTO car_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (10, 'A00000010', TO_DATE('2019-12-31', 'YYYY-MM-DD'), 0, 'Unitat 10');
INSERT INTO car_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (11, 'A00000011', TO_DATE('2019-12-10', 'YYYY-MM-DD'), 0, 'Unitat 11');
INSERT INTO car_unitatorganica (id, codidir3, datacreacio, estat, nom) VALUES (12, 'A00000012', TO_DATE('2020-01-15', 'YYYY-MM-DD'), 0, 'Unitat 12');

-- Crear procediments d'exemple
INSERT INTO car_procediment (id, codisia, nom, unitatorganicaid) VALUES (1, '000001', 'Procediment 1', 1);
INSERT INTO car_procediment (id, codisia, nom, unitatorganicaid) VALUES (2, '000002', 'Procediment 2', 1);
INSERT INTO car_procediment (id, codisia, nom, unitatorganicaid) VALUES (3, '000003', 'Procediment 3', 2);
INSERT INTO car_procediment (id, codisia, nom, unitatorganicaid) VALUES (4, '000004', 'Procediment 4', 3);

-- Actualitzar seqüència de les unitats orgàniques
ALTER SEQUENCE car_procediment_seq INCREMENT BY 4;
SELECT car_procediment_seq.NEXTVAL FROM dual;
ALTER SEQUENCE car_procediment_seq INCREMENT BY 1;

-- Actualitzar seqüència dels procediments
ALTER SEQUENCE car_unitatorganica_seq INCREMENT BY 12;
SELECT car_unitatorganica_seq.NEXTVAL FROM dual;
ALTER SEQUENCE car_unitatorganica_seq INCREMENT BY 1;

