


-- YouTube

INSERT INTO car_traduccio VALUES (203);
INSERT INTO car_traduccio VALUES (204);

INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (203, 'en', 'YouTube');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (203, 'ca', 'YouTube');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (203, 'es', 'YouTube');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (204, 'en', 'http://www.youtube.com/CanalIllesBalears');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (204, 'ca', 'http://www.youtube.com/CanalIllesBalears');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (204, 'es', 'http://www.youtube.com/CanalIllesBalears');

INSERT INTO car_fitxer(fitxerid, descripcio, mime, nom, tamany) VALUES (200, NULL, 'image/png', 'youtube.png', 1024);

INSERT INTO car_enllaz(enllazid, tipus, urlid, nomid, entitatid, logoid) VALUES (202, 1, 204, 203, (SELECT entitatid FROM car_entitat where codi = 'caib'), 200);

-- Twitter

INSERT INTO car_traduccio VALUES (205);
INSERT INTO car_traduccio VALUES (206);

INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (205, 'en', 'Twitter');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (205, 'ca', 'Twitter');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (205, 'es', 'Twitter');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (206, 'en', 'http://www.twitter.com/goib');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (206, 'ca', 'http://www.twitter.com/goib');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (206, 'es', 'http://www.twitter.com/goib');

INSERT INTO car_fitxer(fitxerid, descripcio, mime, nom, tamany) VALUES (201, NULL, 'image/png', 'twitter.png', 928);

INSERT INTO car_enllaz(enllazid, tipus, urlid, nomid, entitatid, logoid) VALUES (203, 1, 206, 205, (SELECT entitatid FROM car_entitat where codi = 'caib'), 201);

-- Instagram

INSERT INTO car_traduccio VALUES (207);
INSERT INTO car_traduccio VALUES (208);

INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (207, 'en', 'Instagram');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (207, 'ca', 'Instagram');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (207, 'es', 'Instagram');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (208, 'en', 'https://www.instagram.com/goib');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (208, 'ca', 'https://www.instagram.com/goib');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (208, 'es', 'https://www.instagram.com/goib');

INSERT INTO car_fitxer(fitxerid, descripcio, mime, nom, tamany) VALUES (202, NULL, 'image/png', 'instagram.png', 950);

INSERT INTO car_enllaz(enllazid, tipus, urlid, nomid, entitatid, logoid) VALUES (204, 1, 208, 207, (SELECT entitatid FROM car_entitat where codi = 'caib'), 202);


-- Facebook

INSERT INTO car_traduccio VALUES (209);
INSERT INTO car_traduccio VALUES (210);

INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (209, 'en', 'Facebook');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (209, 'ca', 'Facebook');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (209, 'es', 'Facebook');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (210, 'en', 'https://www.facebook.com/GovernIllesBalears/');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (210, 'ca', 'https://www.facebook.com/GovernIllesBalears/');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (210, 'es', 'https://www.facebook.com/GovernIllesBalears/');

INSERT INTO car_fitxer(fitxerid, descripcio, mime, nom, tamany) VALUES (203, NULL, 'image/png', 'facebook.png', 824);

INSERT INTO car_enllaz(enllazid, tipus, urlid, nomid, entitatid, logoid) VALUES (205, 1, 210, 209, (SELECT entitatid FROM car_entitat where codi = 'caib'), 203);

