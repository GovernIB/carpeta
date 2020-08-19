--
-- PostgreSQL database dump
--



INSERT INTO car_idioma VALUES ('ca', 'Català', true, 0);
INSERT INTO car_idioma VALUES ('es', 'Castellano', true, 1);
INSERT INTO car_idioma VALUES ('en', 'English', true, 2);


--
-- TOC entry 2007 (class 0 OID 112434)
-- Dependencies: 173
-- Data for Name: car_traduccio; Type: TABLE DATA; Schema: public; Owner: carpeta
--


INSERT INTO car_traduccio VALUES (26);
INSERT INTO car_traduccio VALUES (36);



--
-- TOC entry 2008 (class 0 OID 112438)
-- Dependencies: 174
-- Data for Name: car_traducciomap; Type: TABLE DATA; Schema: public; Owner: carpeta
--


INSERT INTO car_traducciomap VALUES (26, 'en', 'Sistra v1 & v2');
INSERT INTO car_traducciomap VALUES (26, 'es', 'Sistra v1 & v2');
INSERT INTO car_traducciomap VALUES (26, 'ca', 'Sistra v1 & v2');

INSERT INTO car_traducciomap VALUES (36, 'en', 'Description Sistra v1 & v2');
INSERT INTO car_traducciomap VALUES (36, 'es', 'Descripción Sistra v1 & v2');
INSERT INTO car_traducciomap VALUES (36, 'ca', 'Descripció Sistra v1 & v2');


--
-- TOC entry 2010 (class 0 OID 112580)
-- Dependencies: 178
-- Data for Name: car_plugin; Type: TABLE DATA; Schema: public; Owner: carpeta
--
INSERT INTO car_plugin(
            nomid, descripcioid, classe, tipus, propietats, actiu)
    VALUES (26, 36, 'org.fundaciobit.pluginsib.carpetafront.sistra.SistraCarpetaFrontPlugin', 1, '# Common
es.caib.carpeta.pluginsib.carpetafront.sistra.development=true

# Sistra 1
es.caib.carpeta.pluginsib.carpetafront.sistra1.url=https://dev.caib.es/sistraback/zonaperws/services/v2/BackofficeFacade
es.caib.carpeta.pluginsib.carpetafront.sistra1.user=$carpeta_sistra
es.caib.carpeta.pluginsib.carpetafront.sistra1.pass=setpassword
es.caib.carpeta.pluginsib.carpetafront.sistra1.level=2
es.caib.carpeta.pluginsib.carpetafront.sistra1.web=https://www.caib.es/sistrafront/zonaperfront/protected/init.do', true);


--
-- TOC entry 2009 (class 0 OID 112559)
-- Dependencies: 177
-- Data for Name: car_propietatglobal; Type: TABLE DATA; Schema: public; Owner: carpeta
--

INSERT INTO car_propietatglobal VALUES (24, 'es.caib.carpeta.defaultentitycode', 'caib', '<p>Codi de l''entitat per defecte a la que es donar&agrave; d''alta autom&agrave;ticament a un usuari just despr&eacute;s d''haver-se creat a Carpeta</p>', NULL);


-- Completed on 2020-08-12 12:07:20

--
-- PostgreSQL database dump complete
--

