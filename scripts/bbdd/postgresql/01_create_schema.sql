--
-- PostgreSQL database dump
--

-- TO REPLACE RegEx => (1) TOC entry \d\d\d\d (2) TOC entry \d\d\d

-- Dumped from database version 9.3.25
-- Dumped by pg_dump version 9.3.25
-- Started on 2022-09-22 14:58:20

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;




--
-- TOC entry 171 (class 1259 OID 18233)
-- Name: car_acces_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_acces_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE car_acces_seq OWNER TO carpeta;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 172 (class 1259 OID 18235)
-- Name: car_acces; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_acces (
    accesid bigint DEFAULT nextval('car_acces_seq'::regclass) NOT NULL,
    nom character varying(255),
    llinatges character varying(255),
    nif character varying(50),
    ip character varying(100),
    proveidoridentitat character varying(255),
    metodeautenticacio character varying(255),
    dataacces timestamp without time zone,
    idioma character varying(50) NOT NULL,
    entitatid bigint NOT NULL,
    tipus integer NOT NULL,
    pluginid integer,
    resultat boolean DEFAULT true NOT NULL,
    qaa integer,
    idsessio character varying(255)
);


ALTER TABLE car_acces OWNER TO carpeta;

--
--  (class 0 OID 0)
-- Dependencies: 172
-- Name: COLUMN car_acces.idioma; Type: COMMENT; Schema: public; Owner: carpeta
--

COMMENT ON COLUMN car_acces.idioma IS 'Hauria d''estar enllaçat amb la taula idioma';


--
-- TOC entry 173 (class 1259 OID 18243)
-- Name: car_auditoria_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_auditoria_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE car_auditoria_seq OWNER TO carpeta;

--
-- TOC entry 174 (class 1259 OID 18245)
-- Name: car_auditoria; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_auditoria (
    auditoriaid bigint DEFAULT nextval('car_auditoria_seq'::regclass) NOT NULL,
    tipus integer NOT NULL,
    dataaudit timestamp without time zone NOT NULL,
    entitatid bigint,
    username character varying(255),
    objecte character varying(255)
);


ALTER TABLE car_auditoria OWNER TO carpeta;

--
-- TOC entry 175 (class 1259 OID 18252)
-- Name: car_avis_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_avis_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE car_avis_seq OWNER TO carpeta;

--
-- TOC entry 176 (class 1259 OID 18254)
-- Name: car_avis; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_avis (
    avisid bigint DEFAULT nextval('car_avis_seq'::regclass) NOT NULL,
    descripcioid bigint NOT NULL,
    entitatid bigint,
    datainici timestamp without time zone,
    datafi timestamp without time zone,
    tipus integer NOT NULL,
    pluginfrontid bigint,
    gravetat integer DEFAULT 1 NOT NULL
);


ALTER TABLE car_avis OWNER TO carpeta;

--
-- TOC entry 205 (class 1259 OID 56488)
-- Name: car_ciutada_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_ciutada_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE car_ciutada_seq OWNER TO carpeta;

--
-- TOC entry 206 (class 1259 OID 56490)
-- Name: car_ciutada; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_ciutada (
    ciutadaid bigint DEFAULT nextval('car_ciutada_seq'::regclass) NOT NULL,
    nif character varying(100) NOT NULL,
    nom character varying(255),
    llinatge1 character varying(255),
    llinatge2 character varying(255),
    empresa boolean NOT NULL,
    representantnif character varying(100),
    representantnom character varying(255),
    representantllinatge1 character varying(255),
    representantllinatge2 character varying(255),
    datacreacio timestamp without time zone NOT NULL,
    mobileid character varying(255)
);


ALTER TABLE car_ciutada OWNER TO carpeta;

--
-- TOC entry 177 (class 1259 OID 18259)
-- Name: car_enllaz_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_enllaz_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE car_enllaz_seq OWNER TO carpeta;

--
-- TOC entry 178 (class 1259 OID 18261)
-- Name: car_enllaz; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_enllaz (
    enllazid bigint DEFAULT nextval('car_enllaz_seq'::regclass) NOT NULL,
    tipus integer NOT NULL,
    urlid bigint NOT NULL,
    nomid bigint NOT NULL,
    entitatid bigint NOT NULL,
    logoid bigint NOT NULL,
    seccioid bigint,
    descripcioid bigint,
    actiu boolean DEFAULT true NOT NULL,
    ordre integer DEFAULT 1 NOT NULL
);


ALTER TABLE car_enllaz OWNER TO carpeta;

--
-- TOC entry 179 (class 1259 OID 18265)
-- Name: car_entitat_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_entitat_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE car_entitat_seq OWNER TO carpeta;

--
-- TOC entry 180 (class 1259 OID 18267)
-- Name: car_entitat; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_entitat (
    entitatid bigint DEFAULT nextval('car_entitat_seq'::regclass) NOT NULL,
    nomid bigint NOT NULL,
    codidir3 character varying(255) NOT NULL,
    activa boolean NOT NULL,
    colormenu character varying(100) NOT NULL,
    versio character varying(50) NOT NULL,
    commit character varying(255),
    fitxercss bigint,
    context character varying(255),
    codi character varying(30) NOT NULL,
    logocapbackid bigint NOT NULL,
    logopeubackid bigint NOT NULL,
    logolateralfrontid bigint NOT NULL,
    iconid bigint NOT NULL,
    suportweb character varying(255),
    suportemail character varying(255),
    suporttelefon character varying(255),
    entitatdescfront character varying(4000) NOT NULL,
    webentitat character varying(255) NOT NULL,
    pluginloginid bigint,
    suportfaq character varying(255),
    suportqssi character varying(255),
    suportautenticacio character varying(255),
    logintextid bigint,
    descripcioid bigint,
    avislegalca text,
    accessibilitatca text,
    avislegales text,
    accessibilitates text
);


ALTER TABLE car_entitat OWNER TO carpeta;

--
--  (class 0 OID 0)
-- Dependencies: 180
-- Name: COLUMN car_entitat.suportfaq; Type: COMMENT; Schema: public; Owner: carpeta
--

COMMENT ON COLUMN car_entitat.suportfaq IS 'Preguntes Freqüents';


--
--  (class 0 OID 0)
-- Dependencies: 180
-- Name: COLUMN car_entitat.suportqssi; Type: COMMENT; Schema: public; Owner: carpeta
--

COMMENT ON COLUMN car_entitat.suportqssi IS ' Queixes i suggeriments ';


--
--  (class 0 OID 0)
-- Dependencies: 180
-- Name: COLUMN car_entitat.suportautenticacio; Type: COMMENT; Schema: public; Owner: carpeta
--

COMMENT ON COLUMN car_entitat.suportautenticacio IS 'Suport autenticació Front';


--
--  (class 1259 OID 18274)
-- Name: car_estadistica_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_estadistica_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE car_estadistica_seq OWNER TO carpeta;

--
--  (class 1259 OID 18276)
-- Name: car_estadistica; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_estadistica (
    estadisticaid bigint DEFAULT nextval('car_estadistica_seq'::regclass) NOT NULL,
    entitatid bigint,
    tipus integer NOT NULL,
    dataestadistica timestamp without time zone NOT NULL,
    comptador integer DEFAULT 0 NOT NULL,
    pluginid bigint
);


ALTER TABLE car_estadistica OWNER TO carpeta;

--
--  (class 1259 OID 18281)
-- Name: car_fitxer_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_fitxer_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE car_fitxer_seq OWNER TO carpeta;

--
--  (class 1259 OID 18283)
-- Name: car_fitxer; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_fitxer (
    fitxerid bigint DEFAULT nextval('car_fitxer_seq'::regclass) NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    mime character varying(255) NOT NULL,
    nom character varying(255) NOT NULL,
    tamany bigint NOT NULL
);


ALTER TABLE car_fitxer OWNER TO carpeta;

--
--  (class 1259 OID 18291)
-- Name: car_idioma; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_idioma (
    idiomaid character varying(5) NOT NULL,
    nom character varying(50) NOT NULL,
    suportat boolean DEFAULT true NOT NULL,
    ordre integer DEFAULT 0 NOT NULL
);


ALTER TABLE car_idioma OWNER TO carpeta;

--
--  (class 1259 OID 18296)
-- Name: car_log_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_log_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE car_log_seq OWNER TO carpeta;

--
--  (class 1259 OID 18298)
-- Name: car_log; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_log (
    logid bigint DEFAULT nextval('car_log_seq'::regclass) NOT NULL,
    descripcio character varying(2000) NOT NULL,
    pluginid bigint,
    tipus integer NOT NULL,
    estat integer NOT NULL,
    temps bigint,
    datainici timestamp without time zone NOT NULL,
    peticio character varying(255),
    error text,
    excepcio text,
    entitatcodi character varying(9),
    idsessio text
);


ALTER TABLE car_log OWNER TO carpeta;

--
--  (class 1259 OID 64734)
-- Name: car_notificacioapp_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_notificacioapp_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE car_notificacioapp_seq OWNER TO carpeta;

--
--  (class 1259 OID 64736)
-- Name: car_notificacioapp; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_notificacioapp (
    notificacioappid bigint DEFAULT nextval('car_notificacioapp_seq'::regclass) NOT NULL,
    codi character varying(50) NOT NULL,
    titolid bigint NOT NULL,
    missatgeid bigint NOT NULL,
    frontpluginid bigint,
    activa boolean NOT NULL,
    ajuda text,
    entitatid bigint NOT NULL
);


ALTER TABLE car_notificacioapp OWNER TO carpeta;

--
--  (class 1259 OID 18305)
-- Name: car_plugin_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_plugin_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE car_plugin_seq OWNER TO carpeta;

--
--  (class 1259 OID 18307)
-- Name: car_plugin; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_plugin (
    pluginid bigint DEFAULT nextval('car_plugin_seq'::regclass) NOT NULL,
    nomid bigint NOT NULL,
    classe character varying(255) NOT NULL,
    tipus integer NOT NULL,
    propietats text,
    actiu boolean DEFAULT true NOT NULL,
    descripcioid bigint,
    logoid bigint,
    context character varying(50) NOT NULL,
    titolllargid bigint,
    subtitolllargid bigint
);


ALTER TABLE car_plugin OWNER TO carpeta;

--
--  (class 1259 OID 18315)
-- Name: car_pluginentitat_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_pluginentitat_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE car_pluginentitat_seq OWNER TO carpeta;

--
--  (class 1259 OID 18317)
-- Name: car_pluginentitat; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_pluginentitat (
    pluginentitatid bigint DEFAULT nextval('car_pluginentitat_seq'::regclass) NOT NULL,
    pluginid bigint NOT NULL,
    entitatid bigint NOT NULL,
    actiu boolean NOT NULL,
    seccioid bigint,
    ordre integer DEFAULT 1 NOT NULL
);


ALTER TABLE car_pluginentitat OWNER TO carpeta;

--
--  (class 1259 OID 56010)
-- Name: car_preguntesfrequents_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_preguntesfrequents_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE car_preguntesfrequents_seq OWNER TO carpeta;

--
--  (class 1259 OID 56012)
-- Name: car_preguntesfrequents; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_preguntesfrequents (
    preguntesfrequentsid bigint DEFAULT nextval('car_preguntesfrequents_seq'::regclass) NOT NULL,
    enunciatid bigint NOT NULL,
    respostaid bigint NOT NULL,
    ordre integer NOT NULL,
    entitatid bigint NOT NULL,
    fitxer1id bigint,
    fitxer2id bigint,
    fitxer3id bigint
);


ALTER TABLE car_preguntesfrequents OWNER TO carpeta;

--
--  (class 1259 OID 18321)
-- Name: car_propietatglobal_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_propietatglobal_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE car_propietatglobal_seq OWNER TO carpeta;

--
--  (class 1259 OID 18323)
-- Name: car_propietatglobal; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_propietatglobal (
    propietatglobalid bigint DEFAULT nextval('car_propietatglobal_seq'::regclass) NOT NULL,
    codi character varying(250) NOT NULL,
    value character varying(4000),
    descripcio character varying(1000),
    entitatid bigint
);


ALTER TABLE car_propietatglobal OWNER TO carpeta;

--
--  (class 1259 OID 18330)
-- Name: car_seccio_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_seccio_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE car_seccio_seq OWNER TO carpeta;

--
--  (class 1259 OID 18332)
-- Name: car_seccio; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_seccio (
    seccioid bigint DEFAULT nextval('car_seccio_seq'::regclass) NOT NULL,
    nomid bigint NOT NULL,
    descripcioid bigint NOT NULL,
    activa boolean DEFAULT true NOT NULL,
    iconaid bigint NOT NULL,
    secciopareid bigint,
    entitatid bigint NOT NULL,
    context character varying(50) DEFAULT nextval('car_seccio_seq'::regclass) NOT NULL,
    ordre integer DEFAULT 1 NOT NULL
);


ALTER TABLE car_seccio OWNER TO carpeta;

--
--  (class 1259 OID 18337)
-- Name: car_traduccio_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_traduccio_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE car_traduccio_seq OWNER TO carpeta;

--
--  (class 1259 OID 18339)
-- Name: car_traduccio; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_traduccio (
    traduccioid bigint DEFAULT nextval('car_traduccio_seq'::regclass) NOT NULL
);


ALTER TABLE car_traduccio OWNER TO carpeta;

--
--  (class 1259 OID 18343)
-- Name: car_traducciomap; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_traducciomap (
    traducciomapid bigint NOT NULL,
    idiomaid character varying(10) NOT NULL,
    valor character varying(4000)
);


ALTER TABLE car_traducciomap OWNER TO carpeta;

--
--  (class 1259 OID 18349)
-- Name: car_usuari_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_usuari_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE car_usuari_seq OWNER TO carpeta;

--
--  (class 1259 OID 18351)
-- Name: car_usuari; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_usuari (
    usuariid bigint DEFAULT nextval('car_usuari_seq'::regclass) NOT NULL,
    username character varying(255) NOT NULL,
    nom character varying(255) NOT NULL,
    llinatge1 character varying(255) NOT NULL,
    llinatge2 character varying(255),
    email character varying(255),
    nif character varying(255),
    idiomaid character varying(5) NOT NULL,
    darreraentitat bigint
);


ALTER TABLE car_usuari OWNER TO carpeta;

--
--  (class 1259 OID 18358)
-- Name: car_usuarientitat_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_usuarientitat_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE car_usuarientitat_seq OWNER TO carpeta;

--
--  (class 1259 OID 18360)
-- Name: car_usuarientitat; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_usuarientitat (
    usuarientitatid bigint DEFAULT nextval('car_usuarientitat_seq'::regclass) NOT NULL,
    usuariid bigint NOT NULL,
    entitatid bigint NOT NULL,
    actiu boolean NOT NULL
);


ALTER TABLE car_usuarientitat OWNER TO carpeta;

--
--  (class 2606 OID 18397)
-- Name: car_acces_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_acces
    ADD CONSTRAINT car_acces_pk PRIMARY KEY (accesid);


--
--  (class 2606 OID 18399)
-- Name: car_auditoria_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_auditoria
    ADD CONSTRAINT car_auditoria_pk PRIMARY KEY (auditoriaid);


--
--  (class 2606 OID 18401)
-- Name: car_avis_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_avis
    ADD CONSTRAINT car_avis_pk PRIMARY KEY (avisid);


--
--  (class 2606 OID 56500)
-- Name: car_ciutada_nif_rnif_uk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_ciutada
    ADD CONSTRAINT car_ciutada_nif_rnif_uk UNIQUE (nif, representantnif);


--
--  (class 2606 OID 56498)
-- Name: car_ciutada_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_ciutada
    ADD CONSTRAINT car_ciutada_pk PRIMARY KEY (ciutadaid);


--
--  (class 2606 OID 18403)
-- Name: car_enllaz_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_enllaz
    ADD CONSTRAINT car_enllaz_pk PRIMARY KEY (enllazid);


--
--  (class 2606 OID 18405)
-- Name: car_entitat_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_pk PRIMARY KEY (entitatid);


--
--  (class 2606 OID 18407)
-- Name: car_estadistica_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_estadistica
    ADD CONSTRAINT car_estadistica_pk PRIMARY KEY (estadisticaid);


--
--  (class 2606 OID 18409)
-- Name: car_fitxer_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_fitxer
    ADD CONSTRAINT car_fitxer_pk PRIMARY KEY (fitxerid);


--
--  (class 2606 OID 18411)
-- Name: car_idioma_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_idioma
    ADD CONSTRAINT car_idioma_pk PRIMARY KEY (idiomaid);


--
--  (class 2606 OID 18413)
-- Name: car_log_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_log
    ADD CONSTRAINT car_log_pk PRIMARY KEY (logid);


--
--  (class 2606 OID 64743)
-- Name: car_notifica_codi_uk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_notificacioapp
    ADD CONSTRAINT car_notifica_codi_uk UNIQUE (codi);


--
--  (class 2606 OID 64741)
-- Name: car_notificacioapp_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_notificacioapp
    ADD CONSTRAINT car_notificacioapp_pk PRIMARY KEY (notificacioappid);


--
--  (class 2606 OID 18415)
-- Name: car_plugent_plug_ent_uk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_pluginentitat
    ADD CONSTRAINT car_plugent_plug_ent_uk UNIQUE (pluginid, entitatid);


--
--  (class 2606 OID 18417)
-- Name: car_plugin_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_plugin
    ADD CONSTRAINT car_plugin_pk PRIMARY KEY (pluginid);


--
--  (class 2606 OID 18419)
-- Name: car_pluginentitat_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_pluginentitat
    ADD CONSTRAINT car_pluginentitat_pk PRIMARY KEY (pluginentitatid);


--
--  (class 2606 OID 56017)
-- Name: car_preguntesfrequents_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_preguntesfrequents
    ADD CONSTRAINT car_preguntesfrequents_pk PRIMARY KEY (preguntesfrequentsid);


--
--  (class 2606 OID 18421)
-- Name: car_propietatglobal_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_propietatglobal
    ADD CONSTRAINT car_propietatglobal_pk PRIMARY KEY (propietatglobalid);


--
--  (class 2606 OID 30618)
-- Name: car_seccio_context_uk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_seccio
    ADD CONSTRAINT car_seccio_context_uk UNIQUE (context);


--
--  (class 2606 OID 18423)
-- Name: car_seccio_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_seccio
    ADD CONSTRAINT car_seccio_pk PRIMARY KEY (seccioid);


--
--  (class 2606 OID 18425)
-- Name: car_traduccio_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_traduccio
    ADD CONSTRAINT car_traduccio_pk PRIMARY KEY (traduccioid);


--
--  (class 2606 OID 18427)
-- Name: car_traducmap_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_traducciomap
    ADD CONSTRAINT car_traducmap_pk PRIMARY KEY (traducciomapid, idiomaid);


--
--  (class 2606 OID 18429)
-- Name: car_usuari_nif_uk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_usuari
    ADD CONSTRAINT car_usuari_nif_uk UNIQUE (nif);


--
--  (class 2606 OID 18431)
-- Name: car_usuari_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_usuari
    ADD CONSTRAINT car_usuari_pk PRIMARY KEY (usuariid);


--
--  (class 2606 OID 18433)
-- Name: car_usuari_username_uk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_usuari
    ADD CONSTRAINT car_usuari_username_uk UNIQUE (username);


--
--  (class 2606 OID 18435)
-- Name: car_usuarientitat_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_usuarientitat
    ADD CONSTRAINT car_usuarientitat_pk PRIMARY KEY (usuarientitatid);


--
--  (class 2606 OID 18437)
-- Name: car_usuent_usu_ent_uk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_usuarientitat
    ADD CONSTRAINT car_usuent_usu_ent_uk UNIQUE (usuariid, entitatid);


--
--  (class 1259 OID 18438)
-- Name: car_acces_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_acces_entitatid_fk_i ON car_acces USING btree (entitatid);


--
--  (class 1259 OID 18439)
-- Name: car_acces_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_acces_pk_i ON car_acces USING btree (accesid);


--
--  (class 1259 OID 18440)
-- Name: car_auditoria_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_auditoria_entitatid_fk_i ON car_auditoria USING btree (entitatid);


--
--  (class 1259 OID 18441)
-- Name: car_auditoria_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_auditoria_pk_i ON car_auditoria USING btree (auditoriaid);


--
--  (class 1259 OID 18442)
-- Name: car_avis_descripcioid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_avis_descripcioid_fk_i ON car_avis USING btree (descripcioid);


--
--  (class 1259 OID 18443)
-- Name: car_avis_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_avis_entitatid_fk_i ON car_avis USING btree (entitatid);


--
--  (class 1259 OID 18444)
-- Name: car_avis_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_avis_pk_i ON car_avis USING btree (avisid);


--
--  (class 1259 OID 18445)
-- Name: car_avis_pluginfrontid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_avis_pluginfrontid_fk_i ON car_avis USING btree (pluginfrontid);


--
--  (class 1259 OID 56501)
-- Name: car_ciutada_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_ciutada_pk_i ON car_ciutada USING btree (ciutadaid);


--
--  (class 1259 OID 18446)
-- Name: car_enllaz_descripcioid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_enllaz_descripcioid_fk_i ON car_enllaz USING btree (descripcioid);


--
--  (class 1259 OID 18447)
-- Name: car_enllaz_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_enllaz_entitatid_fk_i ON car_enllaz USING btree (entitatid);


--
--  (class 1259 OID 18448)
-- Name: car_enllaz_logoid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_enllaz_logoid_fk_i ON car_enllaz USING btree (logoid);


--
--  (class 1259 OID 18449)
-- Name: car_enllaz_nomid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_enllaz_nomid_fk_i ON car_enllaz USING btree (nomid);


--
--  (class 1259 OID 18450)
-- Name: car_enllaz_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_enllaz_pk_i ON car_enllaz USING btree (enllazid);


--
--  (class 1259 OID 18451)
-- Name: car_enllaz_seccioid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_enllaz_seccioid_fk_i ON car_enllaz USING btree (seccioid);


--
--  (class 1259 OID 18452)
-- Name: car_enllaz_urlid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_enllaz_urlid_fk_i ON car_enllaz USING btree (urlid);


--
--  (class 1259 OID 47052)
-- Name: car_entitat_descripcioid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_descripcioid_fk_i ON car_entitat USING btree (descripcioid);


--
--  (class 1259 OID 18453)
-- Name: car_entitat_fitxercss_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_fitxercss_fk_i ON car_entitat USING btree (fitxercss);


--
--  (class 1259 OID 18454)
-- Name: car_entitat_iconid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_iconid_fk_i ON car_entitat USING btree (iconid);


--
--  (class 1259 OID 18455)
-- Name: car_entitat_logintextid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_logintextid_fk_i ON car_entitat USING btree (logintextid);


--
--  (class 1259 OID 18456)
-- Name: car_entitat_logocapback_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_logocapback_fk_i ON car_entitat USING btree (logocapbackid);


--
--  (class 1259 OID 18457)
-- Name: car_entitat_logolatfront_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_logolatfront_fk_i ON car_entitat USING btree (logolateralfrontid);


--
--  (class 1259 OID 18458)
-- Name: car_entitat_logopeuback_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_logopeuback_fk_i ON car_entitat USING btree (logopeubackid);


--
--  (class 1259 OID 18459)
-- Name: car_entitat_nom_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_nom_fk_i ON car_entitat USING btree (nomid);


--
--  (class 1259 OID 18460)
-- Name: car_entitat_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_pk_i ON car_entitat USING btree (entitatid);


--
--  (class 1259 OID 18461)
-- Name: car_entitat_pluginloginid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_pluginloginid_fk_i ON car_entitat USING btree (pluginloginid);


--
--  (class 1259 OID 18462)
-- Name: car_estadistica_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_estadistica_entitatid_fk_i ON car_estadistica USING btree (entitatid);


--
--  (class 1259 OID 18463)
-- Name: car_estadistica_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_estadistica_pk_i ON car_estadistica USING btree (estadisticaid);


--
--  (class 1259 OID 56036)
-- Name: car_faq_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_faq_entitatid_fk_i ON car_preguntesfrequents USING btree (entitatid);


--
--  (class 1259 OID 56034)
-- Name: car_faq_enunciatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_faq_enunciatid_fk_i ON car_preguntesfrequents USING btree (enunciatid);


--
--  (class 1259 OID 64721)
-- Name: car_faq_fitxer1id_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_faq_fitxer1id_fk_i ON car_preguntesfrequents USING btree (fitxer1id);


--
--  (class 1259 OID 64727)
-- Name: car_faq_fitxer2id_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_faq_fitxer2id_fk_i ON car_preguntesfrequents USING btree (fitxer2id);


--
--  (class 1259 OID 64733)
-- Name: car_faq_fitxer3id_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_faq_fitxer3id_fk_i ON car_preguntesfrequents USING btree (fitxer3id);


--
--  (class 1259 OID 56035)
-- Name: car_faq_respostaid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_faq_respostaid_fk_i ON car_preguntesfrequents USING btree (respostaid);


--
--  (class 1259 OID 18464)
-- Name: car_fitxer_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_fitxer_pk_i ON car_fitxer USING btree (fitxerid);


--
--  (class 1259 OID 18465)
-- Name: car_idioma_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_idioma_pk_i ON car_idioma USING btree (idiomaid);


--
--  (class 1259 OID 18466)
-- Name: car_log_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_log_pk_i ON car_log USING btree (logid);


--
--  (class 1259 OID 18467)
-- Name: car_log_pluginid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_log_pluginid_fk_i ON car_log USING btree (pluginid);


--
--  (class 1259 OID 64774)
-- Name: car_notifica_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_notifica_entitatid_fk_i ON car_notificacioapp USING btree (entitatid);


--
--  (class 1259 OID 64761)
-- Name: car_notifica_missatgeid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_notifica_missatgeid_fk_i ON car_notificacioapp USING btree (missatgeid);


--
--  (class 1259 OID 64762)
-- Name: car_notifica_pluginid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_notifica_pluginid_fk_i ON car_notificacioapp USING btree (frontpluginid);


--
--  (class 1259 OID 64760)
-- Name: car_notifica_titolid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_notifica_titolid_fk_i ON car_notificacioapp USING btree (titolid);


--
--  (class 1259 OID 64759)
-- Name: car_notificacioapp_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_notificacioapp_pk_i ON car_notificacioapp USING btree (notificacioappid);


--
--  (class 1259 OID 18468)
-- Name: car_plugent_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_plugent_entitatid_fk_i ON car_pluginentitat USING btree (entitatid);


--
--  (class 1259 OID 18469)
-- Name: car_plugent_pluginid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_plugent_pluginid_fk_i ON car_pluginentitat USING btree (pluginid);


--
--  (class 1259 OID 18470)
-- Name: car_plugent_seccioid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_plugent_seccioid_fk_i ON car_pluginentitat USING btree (seccioid);


--
--  (class 1259 OID 18471)
-- Name: car_plugin_descripcioid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_plugin_descripcioid_fk_i ON car_plugin USING btree (descripcioid);


--
--  (class 1259 OID 18472)
-- Name: car_plugin_logoid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_plugin_logoid_fk_i ON car_plugin USING btree (logoid);


--
--  (class 1259 OID 18473)
-- Name: car_plugin_nomid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_plugin_nomid_fk_i ON car_plugin USING btree (nomid);


--
--  (class 1259 OID 18474)
-- Name: car_plugin_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_plugin_pk_i ON car_plugin USING btree (pluginid);


--
--  (class 1259 OID 47135)
-- Name: car_plugin_subtitllargid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_plugin_subtitllargid_fk_i ON car_plugin USING btree (subtitolllargid);


--
--  (class 1259 OID 47134)
-- Name: car_plugin_titolllargid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_plugin_titolllargid_fk_i ON car_plugin USING btree (titolllargid);


--
--  (class 1259 OID 18475)
-- Name: car_pluginentitat_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_pluginentitat_pk_i ON car_pluginentitat USING btree (pluginentitatid);


--
--  (class 1259 OID 56033)
-- Name: car_preguntesfrequents_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_preguntesfrequents_pk_i ON car_preguntesfrequents USING btree (preguntesfrequentsid);


--
--  (class 1259 OID 18476)
-- Name: car_propglob_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_propglob_entitatid_fk_i ON car_propietatglobal USING btree (entitatid);


--
--  (class 1259 OID 18477)
-- Name: car_propietatglobal_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_propietatglobal_pk_i ON car_propietatglobal USING btree (propietatglobalid);


--
--  (class 1259 OID 18478)
-- Name: car_seccio_descripcioid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_seccio_descripcioid_fk_i ON car_seccio USING btree (descripcioid);


--
--  (class 1259 OID 18479)
-- Name: car_seccio_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_seccio_entitatid_fk_i ON car_seccio USING btree (entitatid);


--
--  (class 1259 OID 18480)
-- Name: car_seccio_iconaid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_seccio_iconaid_fk_i ON car_seccio USING btree (iconaid);


--
--  (class 1259 OID 18481)
-- Name: car_seccio_nomid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_seccio_nomid_fk_i ON car_seccio USING btree (nomid);


--
--  (class 1259 OID 18482)
-- Name: car_seccio_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_seccio_pk_i ON car_seccio USING btree (seccioid);


--
--  (class 1259 OID 18483)
-- Name: car_traduccio_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_traduccio_pk_i ON car_traduccio USING btree (traduccioid);


--
--  (class 1259 OID 18484)
-- Name: car_traducciomap_idiomaid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_traducciomap_idiomaid_fk_i ON car_traducciomap USING btree (idiomaid);


--
--  (class 1259 OID 18485)
-- Name: car_traducciomap_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_traducciomap_pk_i ON car_traducciomap USING btree (traducciomapid);


--
--  (class 1259 OID 18486)
-- Name: car_usuari_darreraentitat_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_usuari_darreraentitat_fk_i ON car_usuari USING btree (darreraentitat);


--
--  (class 1259 OID 18487)
-- Name: car_usuari_idiomaid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_usuari_idiomaid_fk_i ON car_usuari USING btree (idiomaid);


--
--  (class 1259 OID 18488)
-- Name: car_usuari_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_usuari_pk_i ON car_usuari USING btree (usuariid);


--
--  (class 1259 OID 18489)
-- Name: car_usuarientitat_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_usuarientitat_pk_i ON car_usuarientitat USING btree (usuarientitatid);


--
--  (class 1259 OID 18490)
-- Name: car_usuent_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_usuent_entitatid_fk_i ON car_usuarientitat USING btree (entitatid);


--
--  (class 1259 OID 18491)
-- Name: car_usuent_usuariid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_usuent_usuariid_fk_i ON car_usuarientitat USING btree (usuariid);


--
--  (class 2606 OID 18492)
-- Name: car_acces_entitat_entitatid_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_acces
    ADD CONSTRAINT car_acces_entitat_entitatid_fk FOREIGN KEY (entitatid) REFERENCES car_entitat(entitatid);


--
--  (class 2606 OID 18497)
-- Name: car_avis_entitat_ent_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_avis
    ADD CONSTRAINT car_avis_entitat_ent_fk FOREIGN KEY (entitatid) REFERENCES car_entitat(entitatid);


--
--  (class 2606 OID 18502)
-- Name: car_avis_plugin_pfront_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_avis
    ADD CONSTRAINT car_avis_plugin_pfront_fk FOREIGN KEY (pluginfrontid) REFERENCES car_plugin(pluginid);


--
--  (class 2606 OID 18507)
-- Name: car_avis_traduccio_desc_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_avis
    ADD CONSTRAINT car_avis_traduccio_desc_fk FOREIGN KEY (descripcioid) REFERENCES car_traduccio(traduccioid);


--
--  (class 2606 OID 18512)
-- Name: car_enllaz_entitat_ent_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_enllaz
    ADD CONSTRAINT car_enllaz_entitat_ent_fk FOREIGN KEY (entitatid) REFERENCES car_entitat(entitatid);


--
--  (class 2606 OID 18517)
-- Name: car_enllaz_fitxer_logo_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_enllaz
    ADD CONSTRAINT car_enllaz_fitxer_logo_fk FOREIGN KEY (logoid) REFERENCES car_fitxer(fitxerid);


--
--  (class 2606 OID 18522)
-- Name: car_enllaz_seccio_sec_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_enllaz
    ADD CONSTRAINT car_enllaz_seccio_sec_fk FOREIGN KEY (seccioid) REFERENCES car_seccio(seccioid);


--
--  (class 2606 OID 18527)
-- Name: car_enllaz_traduccio_desid_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_enllaz
    ADD CONSTRAINT car_enllaz_traduccio_desid_fk FOREIGN KEY (descripcioid) REFERENCES car_traduccio(traduccioid);


--
--  (class 2606 OID 18532)
-- Name: car_enllaz_traduccio_nomid_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_enllaz
    ADD CONSTRAINT car_enllaz_traduccio_nomid_fk FOREIGN KEY (nomid) REFERENCES car_traduccio(traduccioid);


--
--  (class 2606 OID 18537)
-- Name: car_enllaz_traduccio_urlid_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_enllaz
    ADD CONSTRAINT car_enllaz_traduccio_urlid_fk FOREIGN KEY (urlid) REFERENCES car_traduccio(traduccioid);


--
--  (class 2606 OID 18542)
-- Name: car_entitat_fitxer_css_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_fitxer_css_fk FOREIGN KEY (fitxercss) REFERENCES car_fitxer(fitxerid);


--
--  (class 2606 OID 18547)
-- Name: car_entitat_fitxer_icon_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_fitxer_icon_fk FOREIGN KEY (iconid) REFERENCES car_fitxer(fitxerid);


--
--  (class 2606 OID 18552)
-- Name: car_entitat_fitxer_lcb_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_fitxer_lcb_fk FOREIGN KEY (logocapbackid) REFERENCES car_fitxer(fitxerid);


--
--  (class 2606 OID 18557)
-- Name: car_entitat_fitxer_llf_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_fitxer_llf_fk FOREIGN KEY (logolateralfrontid) REFERENCES car_fitxer(fitxerid);


--
--  (class 2606 OID 18562)
-- Name: car_entitat_fitxer_lpb_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_fitxer_lpb_fk FOREIGN KEY (logopeubackid) REFERENCES car_fitxer(fitxerid);


--
--  (class 2606 OID 18567)
-- Name: car_entitat_plugin_login_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_plugin_login_fk FOREIGN KEY (pluginloginid) REFERENCES car_plugin(pluginid);


--
--  (class 2606 OID 47047)
-- Name: car_entitat_traduccio_des_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_traduccio_des_fk FOREIGN KEY (descripcioid) REFERENCES car_traduccio(traduccioid);


--
--  (class 2606 OID 18572)
-- Name: car_entitat_traduccio_log_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_traduccio_log_fk FOREIGN KEY (logintextid) REFERENCES car_traduccio(traduccioid);


--
--  (class 2606 OID 18577)
-- Name: car_entitat_traduccio_nom_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_traduccio_nom_fk FOREIGN KEY (nomid) REFERENCES car_traduccio(traduccioid);


--
--  (class 2606 OID 56028)
-- Name: car_faq_entitat_ent_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_preguntesfrequents
    ADD CONSTRAINT car_faq_entitat_ent_fk FOREIGN KEY (entitatid) REFERENCES car_entitat(entitatid);


--
--  (class 2606 OID 64716)
-- Name: car_faq_fitxer_fitxer1_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_preguntesfrequents
    ADD CONSTRAINT car_faq_fitxer_fitxer1_fk FOREIGN KEY (fitxer1id) REFERENCES car_fitxer(fitxerid);


--
--  (class 2606 OID 64722)
-- Name: car_faq_fitxer_fitxer2_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_preguntesfrequents
    ADD CONSTRAINT car_faq_fitxer_fitxer2_fk FOREIGN KEY (fitxer2id) REFERENCES car_fitxer(fitxerid);


--
--  (class 2606 OID 64728)
-- Name: car_faq_fitxer_fitxer3_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_preguntesfrequents
    ADD CONSTRAINT car_faq_fitxer_fitxer3_fk FOREIGN KEY (fitxer3id) REFERENCES car_fitxer(fitxerid);


--
--  (class 2606 OID 56018)
-- Name: car_faq_traduccio_enun_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_preguntesfrequents
    ADD CONSTRAINT car_faq_traduccio_enun_fk FOREIGN KEY (enunciatid) REFERENCES car_traduccio(traduccioid);


--
--  (class 2606 OID 56023)
-- Name: car_faq_traduccio_resp_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_preguntesfrequents
    ADD CONSTRAINT car_faq_traduccio_resp_fk FOREIGN KEY (respostaid) REFERENCES car_traduccio(traduccioid);


--
--  (class 2606 OID 64769)
-- Name: car_notifica_entitat_ent_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_notificacioapp
    ADD CONSTRAINT car_notifica_entitat_ent_fk FOREIGN KEY (entitatid) REFERENCES car_entitat(entitatid);


--
--  (class 2606 OID 64754)
-- Name: car_notifica_plugin_plug_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_notificacioapp
    ADD CONSTRAINT car_notifica_plugin_plug_fk FOREIGN KEY (frontpluginid) REFERENCES car_plugin(pluginid);


--
--  (class 2606 OID 64749)
-- Name: car_notifica_traduccio_msg_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_notificacioapp
    ADD CONSTRAINT car_notifica_traduccio_msg_fk FOREIGN KEY (missatgeid) REFERENCES car_traduccio(traduccioid);


--
--  (class 2606 OID 64744)
-- Name: car_notifica_traduccio_tit_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_notificacioapp
    ADD CONSTRAINT car_notifica_traduccio_tit_fk FOREIGN KEY (titolid) REFERENCES car_traduccio(traduccioid);


--
--  (class 2606 OID 18582)
-- Name: car_plugent_entitat_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_pluginentitat
    ADD CONSTRAINT car_plugent_entitat_fk FOREIGN KEY (entitatid) REFERENCES car_entitat(entitatid);


--
--  (class 2606 OID 18587)
-- Name: car_plugent_plugin_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_pluginentitat
    ADD CONSTRAINT car_plugent_plugin_fk FOREIGN KEY (pluginid) REFERENCES car_plugin(pluginid);


--
--  (class 2606 OID 18592)
-- Name: car_plugent_seccio_sec_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_pluginentitat
    ADD CONSTRAINT car_plugent_seccio_sec_fk FOREIGN KEY (seccioid) REFERENCES car_seccio(seccioid);


--
--  (class 2606 OID 18597)
-- Name: car_plugin_fitxer_logo_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_plugin
    ADD CONSTRAINT car_plugin_fitxer_logo_fk FOREIGN KEY (logoid) REFERENCES car_fitxer(fitxerid);


--
--  (class 2606 OID 18602)
-- Name: car_plugin_traduccio_desc_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_plugin
    ADD CONSTRAINT car_plugin_traduccio_desc_fk FOREIGN KEY (descripcioid) REFERENCES car_traduccio(traduccioid);


--
--  (class 2606 OID 18607)
-- Name: car_plugin_traduccio_nom_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_plugin
    ADD CONSTRAINT car_plugin_traduccio_nom_fk FOREIGN KEY (nomid) REFERENCES car_traduccio(traduccioid);


--
--  (class 2606 OID 47129)
-- Name: car_plugin_traduccio_stlar_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_plugin
    ADD CONSTRAINT car_plugin_traduccio_stlar_fk FOREIGN KEY (subtitolllargid) REFERENCES car_traduccio(traduccioid);


--
--  (class 2606 OID 47119)
-- Name: car_plugin_traduccio_tllarg_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_plugin
    ADD CONSTRAINT car_plugin_traduccio_tllarg_fk FOREIGN KEY (titolllargid) REFERENCES car_traduccio(traduccioid);


--
--  (class 2606 OID 18612)
-- Name: car_propglob_entitat_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_propietatglobal
    ADD CONSTRAINT car_propglob_entitat_fk FOREIGN KEY (entitatid) REFERENCES car_entitat(entitatid);


--
--  (class 2606 OID 18617)
-- Name: car_seccio_entitat_ent_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_seccio
    ADD CONSTRAINT car_seccio_entitat_ent_fk FOREIGN KEY (entitatid) REFERENCES car_entitat(entitatid);


--
--  (class 2606 OID 18622)
-- Name: car_seccio_fitxer_icon_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_seccio
    ADD CONSTRAINT car_seccio_fitxer_icon_fk FOREIGN KEY (iconaid) REFERENCES car_fitxer(fitxerid);


--
--  (class 2606 OID 18627)
-- Name: car_seccio_traduccio_des_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_seccio
    ADD CONSTRAINT car_seccio_traduccio_des_fk FOREIGN KEY (descripcioid) REFERENCES car_traduccio(traduccioid);


--
--  (class 2606 OID 18632)
-- Name: car_seccio_traduccio_nom_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_seccio
    ADD CONSTRAINT car_seccio_traduccio_nom_fk FOREIGN KEY (nomid) REFERENCES car_traduccio(traduccioid);


--
--  (class 2606 OID 18637)
-- Name: car_traducmap_traduccio_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_traducciomap
    ADD CONSTRAINT car_traducmap_traduccio_fk FOREIGN KEY (traducciomapid) REFERENCES car_traduccio(traduccioid);


--
--  (class 2606 OID 18642)
-- Name: car_usuari_entitat_last_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_usuari
    ADD CONSTRAINT car_usuari_entitat_last_fk FOREIGN KEY (darreraentitat) REFERENCES car_entitat(entitatid);


--
--  (class 2606 OID 18647)
-- Name: car_usuari_idioma_idi_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_usuari
    ADD CONSTRAINT car_usuari_idioma_idi_fk FOREIGN KEY (idiomaid) REFERENCES car_idioma(idiomaid);


--
--  (class 2606 OID 18652)
-- Name: car_usuent_entitat_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_usuarientitat
    ADD CONSTRAINT car_usuent_entitat_fk FOREIGN KEY (entitatid) REFERENCES car_entitat(entitatid);


--
--  (class 2606 OID 18657)
-- Name: car_usuent_usuari_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_usuarientitat
    ADD CONSTRAINT car_usuent_usuari_fk FOREIGN KEY (usuariid) REFERENCES car_usuari(usuariid);


--
--  (class 0 OID 0)
-- Dependencies: 7
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2022-09-22 14:58:20

--
-- PostgreSQL database dump complete
--

