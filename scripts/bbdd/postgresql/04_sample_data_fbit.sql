
-- Afegir nova entitat Fundació Bit #265


INSERT INTO car_traduccio VALUES (112);


INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (112, 'en', 'Fundació BIT');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (112, 'ca', 'Fundació BIT');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor) VALUES (112, 'es', 'Fundación BIT');


INSERT INTO car_fitxer(fitxerid, descripcio, mime, nom, tamany) VALUES (108, NULL, 'image/png', 'fundaciobit-logo-cap.png', 7962);
INSERT INTO car_fitxer(fitxerid, descripcio, mime, nom, tamany) VALUES (109, NULL, 'image/png', 'fundaciobit-logo-peu.png', 1552);
INSERT INTO car_fitxer(fitxerid, descripcio, mime, nom, tamany) VALUES (110, NULL, 'image/png', 'fundaciobit-logo-lateral.png', 1746);
INSERT INTO car_fitxer(fitxerid, descripcio, mime, nom, tamany) VALUES (111, NULL, 'image/x-icon', 'fundaciobit.ico', 1150);

INSERT INTO car_entitat(entitatid, nomid, codidir3, activa, colormenu, versio, commit, 
            fitxercss, context, codi, logocapbackid, logopeubackid, logolateralfrontid, 
            iconid, suportweb, suportemail, suporttelefon, entitatdescfront, 
            webentitat, pluginloginid) VALUES (101, 112, 'G57775884', true, 'ECECEC', '1.1.1', NULL, NULL, NULL, 'fbit', 108, 109, 110, 111, 'https://www.fundaciobit.org/', 'governdigital@fundaciobit.org', '971176060', '<p><strong>© Fundació Bit</strong></p><p><a href="https://fundaciobit.org">Fundació Balear Innovació i Tecnologia</a>C\Laura Bassi, 1 - 07121 Palma</p><p></p>Telèfon 971176060<p></p>', 'https://www.fundaciobit.org', NULL);



-- Enllaços de TIPUS XARXA SOCIAL

-- YouTube

INSERT INTO car_traduccio VALUES (190);
INSERT INTO car_traduccio VALUES (191);

INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (190, 'en', 'YouTube');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (190, 'ca', 'YouTube');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (190, 'es', 'YouTube');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (191, 'en', 'https://www.youtube.com/user/IBITorg/videos?flow=grid&view=0&sort=dd');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (191, 'ca', 'https://www.youtube.com/user/IBITorg/videos?flow=grid&view=0&sort=dd');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (191, 'es', 'https://www.youtube.com/user/IBITorg/videos?flow=grid&view=0&sort=dd');

INSERT INTO car_fitxer(fitxerid, descripcio, mime, nom, tamany) VALUES (112, NULL, 'image/png', 'youtube.png', 1024);

INSERT INTO car_enllaz(enllazid, tipus, urlid, nomid, entitatid, logoid) VALUES (206, 1, 191, 190, (SELECT entitatid FROM car_entitat where codi = 'fbit'), 112);

-- Twitter

INSERT INTO car_traduccio VALUES (192);
INSERT INTO car_traduccio VALUES (193);

INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (192, 'en', 'Twitter');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (192, 'ca', 'Twitter');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (192, 'es', 'Twitter');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (193, 'en', 'http://www.twitter.com/fundaciobit');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (193, 'ca', 'http://www.twitter.com/fundaciobit');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (193, 'es', 'http://www.twitter.com/fundaciobit');

INSERT INTO car_fitxer(fitxerid, descripcio, mime, nom, tamany) VALUES (113, NULL, 'image/png', 'twitter.png', 928);

INSERT INTO car_enllaz(enllazid, tipus, urlid, nomid, entitatid, logoid) VALUES (207, 1, 193, 192, (SELECT entitatid FROM car_entitat where codi = 'fbit'), 113);


-- Enllaços de TIPUS LATERAL

-- Noticies

INSERT INTO car_traduccio VALUES (194);
INSERT INTO car_traduccio VALUES (195);

INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (194, 'en', 'News');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (194, 'ca', 'Notícies');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (194, 'es', 'Noticias');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (195, 'en', 'https://www.fundaciobit.org/category/actualitat/');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (195, 'ca', 'https://www.fundaciobit.org/category/actualitat/');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (195, 'es', 'https://www.fundaciobit.org/category/actualitat/');

INSERT INTO car_fitxer(fitxerid, descripcio, mime, nom, tamany) VALUES (114, NULL, 'image/png', 'noticies.png', 1322);

INSERT INTO car_enllaz(enllazid, tipus, urlid, nomid, entitatid, logoid) VALUES (208, 2, 195, 194, (SELECT entitatid FROM car_entitat where codi = 'fbit'), 114);




-- Enllaços de PEU CENTRAL


-- Legal

INSERT INTO car_traduccio VALUES (196);
INSERT INTO car_traduccio VALUES (197);

INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (196, 'en', 'Legal Warning');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (196, 'ca', 'Avís Legal');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (196, 'es', 'Aviso Legal');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (197, 'en', 'https://www.fundaciobit.org/avis-legal-3/');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (197, 'ca', 'https://www.fundaciobit.org/avis-legal-3/');
INSERT INTO car_traducciomap(traducciomapid, idiomaid, valor)  VALUES (197, 'es', 'https://www.fundaciobit.org/avis-legal-3/');

INSERT INTO car_fitxer(fitxerid, descripcio, mime, nom, tamany) VALUES (115, NULL, 'image/png', 'legal.png', 442);

INSERT INTO car_enllaz(enllazid, tipus, urlid, nomid, entitatid, logoid) VALUES (209, 3, 197, 196, (SELECT entitatid FROM car_entitat where codi = 'fbit'), 115);

