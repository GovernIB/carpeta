
-- Afegir més dades a la taula d'Entitat #147


INSERT INTO car_traduccio VALUES (111);


INSERT INTO car_traducciomap VALUES (111, 'en', 'Government of the Balearic Islands');
INSERT INTO car_traducciomap VALUES (111, 'ca', 'Govern de les Illes Balears');
INSERT INTO car_traducciomap VALUES (111, 'es', 'Gobierno de las Islas Baleares');



INSERT INTO car_fitxer(fitxerid, descripcio, mime, nom, tamany) VALUES (104, NULL, 'image/png', 'goib_back_cap.png', 2973);
INSERT INTO car_fitxer(fitxerid, descripcio, mime, nom, tamany) VALUES (105, NULL, 'image/png', 'goib_back_peu.png', 2521);
INSERT INTO car_fitxer(fitxerid, descripcio, mime, nom, tamany) VALUES (106, NULL, 'image/png', 'goib_front_lateral.png', 19397);
INSERT INTO car_fitxer(fitxerid, descripcio, mime, nom, tamany) VALUES (107, NULL, 'image/png', 'favicon.png', 638);



INSERT INTO car_entitat(entitatid, nomid, codidir3, activa, colormenu, versio, commit, 
            fitxercss, context, codi, logocapbackid, logopeubackid, logolateralfrontid, 
            iconid, suportweb, suportemail, suporttelefon, entitatdescfront, 
            webentitat, pluginloginid) VALUES (100, 111, 'A04019898', true, '32814B', '1.1.1', NULL, NULL, NULL, 'caib', 104, 105, 106, 107, 'http://www.caib.es/sites/suportinformatic/es/inicio-5586/?campa=yes', 'suport@caib.es', '971177070', '<div class="imc-peu-govern"><p><strong>© Govern Illes Balears</strong></p><p><a href="http://www.caib.es/govern/organigrama/area.do?coduo=2390499&amp;lang=ca">Direcció General de Comunicació</a><a href="http://www.caib.es/govern/organigrama/planol.do?coduo=2390499&amp;lang=ca">: Passeig de Sagrera, 2 - 07012 Palma</a></p><p></p>Telèfon 971177166 - Fax 971176348<p></p></div>', 'https://www.caib.es', NULL);









-- 06-10-2020 Afegir gestio d'Enllaços a Back #138

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

