--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2020-12-22 14:12:29

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 200 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2163 (class 0 OID 0)
-- Dependencies: 200
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- TOC entry 185 (class 1259 OID 114912)
-- Name: car_acces_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_acces_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_acces_seq OWNER TO carpeta;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 180 (class 1259 OID 112626)
-- Name: car_acces; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_acces (
    accesid bigint DEFAULT nextval('car_acces_seq'::regclass) NOT NULL,
    nom character varying(255),
    llinatges character varying(255),
    nif character varying(50),
    ip character varying(100),
    proveidoridentitat character varying(255),
    nivellseguretat character varying(255),
    resultatautenticacio integer,
    datadarreracces timestamp without time zone,
    idioma character varying(50),
    entitatid bigint NOT NULL,
    tipus integer NOT NULL,
    pluginid integer
);


ALTER TABLE public.car_acces OWNER TO carpeta;

--
-- TOC entry 2164 (class 0 OID 0)
-- Dependencies: 180
-- Name: COLUMN car_acces.idioma; Type: COMMENT; Schema: public; Owner: carpeta
--

COMMENT ON COLUMN car_acces.idioma IS 'Hauria d''estar enllaçat amb la taula idioma';


--
-- TOC entry 186 (class 1259 OID 114914)
-- Name: car_auditoria_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_auditoria_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_auditoria_seq OWNER TO carpeta;

--
-- TOC entry 184 (class 1259 OID 112683)
-- Name: car_auditoria; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_auditoria (
    auditoriaid bigint DEFAULT nextval('car_auditoria_seq'::regclass) NOT NULL,
    tipus integer NOT NULL,
    dataaudit timestamp without time zone NOT NULL,
    entitatid bigint,
    usuariclave character varying(256),
    pluginid integer,
    username character varying(255)
);


ALTER TABLE public.car_auditoria OWNER TO carpeta;

--
-- TOC entry 187 (class 1259 OID 114916)
-- Name: car_avis_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_avis_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_avis_seq OWNER TO carpeta;

--
-- TOC entry 178 (class 1259 OID 112591)
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


ALTER TABLE public.car_avis OWNER TO carpeta;

--
-- TOC entry 188 (class 1259 OID 114918)
-- Name: car_enllaz_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_enllaz_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_enllaz_seq OWNER TO carpeta;

--
-- TOC entry 174 (class 1259 OID 112505)
-- Name: car_enllaz; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_enllaz (
    enllazid bigint DEFAULT nextval('car_enllaz_seq'::regclass) NOT NULL,
    tipus integer NOT NULL,
    urlid bigint NOT NULL,
    nomid bigint NOT NULL,
    entitatid bigint NOT NULL,
    logoid bigint NOT NULL
);


ALTER TABLE public.car_enllaz OWNER TO carpeta;

--
-- TOC entry 189 (class 1259 OID 114920)
-- Name: car_entitat_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_entitat_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_entitat_seq OWNER TO carpeta;

--
-- TOC entry 175 (class 1259 OID 112508)
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
    logintextid bigint
);


ALTER TABLE public.car_entitat OWNER TO carpeta;

--
-- TOC entry 2165 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN car_entitat.suportfaq; Type: COMMENT; Schema: public; Owner: carpeta
--

COMMENT ON COLUMN car_entitat.suportfaq IS 'Preguntes Freqüents';


--
-- TOC entry 2166 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN car_entitat.suportqssi; Type: COMMENT; Schema: public; Owner: carpeta
--

COMMENT ON COLUMN car_entitat.suportqssi IS ' Queixes i suggeriments ';


--
-- TOC entry 2167 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN car_entitat.suportautenticacio; Type: COMMENT; Schema: public; Owner: carpeta
--

COMMENT ON COLUMN car_entitat.suportautenticacio IS 'Suport autenticació Front';


--
-- TOC entry 190 (class 1259 OID 114922)
-- Name: car_estadistica_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_estadistica_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_estadistica_seq OWNER TO carpeta;

--
-- TOC entry 181 (class 1259 OID 112640)
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


ALTER TABLE public.car_estadistica OWNER TO carpeta;

--
-- TOC entry 191 (class 1259 OID 114924)
-- Name: car_fitxer_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_fitxer_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_fitxer_seq OWNER TO carpeta;

--
-- TOC entry 170 (class 1259 OID 112421)
-- Name: car_fitxer; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_fitxer (
    fitxerid bigint DEFAULT nextval('car_fitxer_seq'::regclass) NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    mime character varying(255) NOT NULL,
    nom character varying(255) NOT NULL,
    tamany bigint NOT NULL
);


ALTER TABLE public.car_fitxer OWNER TO carpeta;

--
-- TOC entry 171 (class 1259 OID 112429)
-- Name: car_idioma; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_idioma (
    idiomaid character varying(5) NOT NULL,
    nom character varying(50) NOT NULL,
    suportat boolean DEFAULT true NOT NULL,
    ordre integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.car_idioma OWNER TO carpeta;

--
-- TOC entry 192 (class 1259 OID 114926)
-- Name: car_log_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_log_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_log_seq OWNER TO carpeta;

--
-- TOC entry 179 (class 1259 OID 112607)
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
    error character varying(2000),
    excepcio text,
    entitatcodi character varying(9)
);


ALTER TABLE public.car_log OWNER TO carpeta;

--
-- TOC entry 193 (class 1259 OID 114928)
-- Name: car_plugin_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_plugin_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_plugin_seq OWNER TO carpeta;

--
-- TOC entry 177 (class 1259 OID 112580)
-- Name: car_plugin; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_plugin (
    pluginid bigint DEFAULT nextval('car_plugin_seq'::regclass) NOT NULL,
    nomid bigint NOT NULL,
    classe character varying(255) NOT NULL,
    tipus integer NOT NULL,
    propietats text,
    actiu boolean DEFAULT true NOT NULL,
    descripcioid bigint
);


ALTER TABLE public.car_plugin OWNER TO carpeta;

--
-- TOC entry 198 (class 1259 OID 114987)
-- Name: car_pluginentitat_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_pluginentitat_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_pluginentitat_seq OWNER TO carpeta;

--
-- TOC entry 199 (class 1259 OID 115002)
-- Name: car_pluginentitat; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_pluginentitat (
    pluginentitatid bigint DEFAULT nextval('car_pluginentitat_seq'::regclass) NOT NULL,
    pluginid bigint NOT NULL,
    entitatid bigint NOT NULL,
    actiu boolean NOT NULL
);


ALTER TABLE public.car_pluginentitat OWNER TO carpeta;

--
-- TOC entry 194 (class 1259 OID 114930)
-- Name: car_propietatglobal_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_propietatglobal_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_propietatglobal_seq OWNER TO carpeta;

--
-- TOC entry 176 (class 1259 OID 112559)
-- Name: car_propietatglobal; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_propietatglobal (
    propietatglobalid bigint DEFAULT nextval('car_propietatglobal_seq'::regclass) NOT NULL,
    codi character varying(250) NOT NULL,
    value character varying(4000),
    descripcio character varying(1000),
    entitatid bigint
);


ALTER TABLE public.car_propietatglobal OWNER TO carpeta;

--
-- TOC entry 195 (class 1259 OID 114932)
-- Name: car_traduccio_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_traduccio_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_traduccio_seq OWNER TO carpeta;

--
-- TOC entry 172 (class 1259 OID 112434)
-- Name: car_traduccio; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_traduccio (
    traduccioid bigint DEFAULT nextval('car_traduccio_seq'::regclass) NOT NULL
);


ALTER TABLE public.car_traduccio OWNER TO carpeta;

--
-- TOC entry 173 (class 1259 OID 112438)
-- Name: car_traducciomap; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_traducciomap (
    traducciomapid bigint NOT NULL,
    idiomaid character varying(10) NOT NULL,
    valor character varying(4000)
);


ALTER TABLE public.car_traducciomap OWNER TO carpeta;

--
-- TOC entry 196 (class 1259 OID 114934)
-- Name: car_usuari_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_usuari_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_usuari_seq OWNER TO carpeta;

--
-- TOC entry 182 (class 1259 OID 112656)
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


ALTER TABLE public.car_usuari OWNER TO carpeta;

--
-- TOC entry 197 (class 1259 OID 114936)
-- Name: car_usuarientitat_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_usuarientitat_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_usuarientitat_seq OWNER TO carpeta;

--
-- TOC entry 183 (class 1259 OID 112665)
-- Name: car_usuarientitat; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_usuarientitat (
    usuarientitatid bigint DEFAULT nextval('car_usuarientitat_seq'::regclass) NOT NULL,
    usuariid bigint NOT NULL,
    entitatid bigint NOT NULL,
    actiu boolean NOT NULL
);


ALTER TABLE public.car_usuarientitat OWNER TO carpeta;

--
-- TOC entry 1990 (class 2606 OID 112634)
-- Name: car_acces_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_acces
    ADD CONSTRAINT car_acces_pk PRIMARY KEY (accesid);


--
-- TOC entry 2014 (class 2606 OID 112688)
-- Name: car_auditoria_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_auditoria
    ADD CONSTRAINT car_auditoria_pk PRIMARY KEY (auditoriaid);


--
-- TOC entry 1981 (class 2606 OID 112596)
-- Name: car_avis_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_avis
    ADD CONSTRAINT car_avis_pk PRIMARY KEY (avisid);


--
-- TOC entry 1955 (class 2606 OID 112515)
-- Name: car_enllaz_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_enllaz
    ADD CONSTRAINT car_enllaz_pk PRIMARY KEY (enllazid);


--
-- TOC entry 1966 (class 2606 OID 112517)
-- Name: car_entitat_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_pk PRIMARY KEY (entitatid);


--
-- TOC entry 1994 (class 2606 OID 112645)
-- Name: car_estadistica_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_estadistica
    ADD CONSTRAINT car_estadistica_pk PRIMARY KEY (estadisticaid);


--
-- TOC entry 1939 (class 2606 OID 112445)
-- Name: car_fitxer_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_fitxer
    ADD CONSTRAINT car_fitxer_pk PRIMARY KEY (fitxerid);


--
-- TOC entry 1942 (class 2606 OID 112447)
-- Name: car_idioma_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_idioma
    ADD CONSTRAINT car_idioma_pk PRIMARY KEY (idiomaid);


--
-- TOC entry 1985 (class 2606 OID 112614)
-- Name: car_log_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_log
    ADD CONSTRAINT car_log_pk PRIMARY KEY (logid);


--
-- TOC entry 2018 (class 2606 OID 115009)
-- Name: car_plugent_plug_ent_uk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_pluginentitat
    ADD CONSTRAINT car_plugent_plug_ent_uk UNIQUE (pluginid, entitatid);


--
-- TOC entry 1976 (class 2606 OID 112585)
-- Name: car_plugin_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_plugin
    ADD CONSTRAINT car_plugin_pk PRIMARY KEY (pluginid);


--
-- TOC entry 2021 (class 2606 OID 115007)
-- Name: car_pluginentitat_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_pluginentitat
    ADD CONSTRAINT car_pluginentitat_pk PRIMARY KEY (pluginentitatid);


--
-- TOC entry 1971 (class 2606 OID 112567)
-- Name: car_propietatglobal_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_propietatglobal
    ADD CONSTRAINT car_propietatglobal_pk PRIMARY KEY (propietatglobalid);


--
-- TOC entry 1945 (class 2606 OID 112449)
-- Name: car_traduccio_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_traduccio
    ADD CONSTRAINT car_traduccio_pk PRIMARY KEY (traduccioid);


--
-- TOC entry 1950 (class 2606 OID 112451)
-- Name: car_traducmap_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_traducciomap
    ADD CONSTRAINT car_traducmap_pk PRIMARY KEY (traducciomapid, idiomaid);


--
-- TOC entry 1999 (class 2606 OID 115033)
-- Name: car_usuari_nif_uk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_usuari
    ADD CONSTRAINT car_usuari_nif_uk UNIQUE (nif);


--
-- TOC entry 2001 (class 2606 OID 112664)
-- Name: car_usuari_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_usuari
    ADD CONSTRAINT car_usuari_pk PRIMARY KEY (usuariid);


--
-- TOC entry 2004 (class 2606 OID 115031)
-- Name: car_usuari_username_uk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_usuari
    ADD CONSTRAINT car_usuari_username_uk UNIQUE (username);


--
-- TOC entry 2006 (class 2606 OID 112670)
-- Name: car_usuarientitat_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_usuarientitat
    ADD CONSTRAINT car_usuarientitat_pk PRIMARY KEY (usuarientitatid);


--
-- TOC entry 2010 (class 2606 OID 112682)
-- Name: car_usuent_usu_ent_uk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_usuarientitat
    ADD CONSTRAINT car_usuent_usu_ent_uk UNIQUE (usuariid, entitatid);


--
-- TOC entry 1988 (class 1259 OID 112706)
-- Name: car_acces_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_acces_entitatid_fk_i ON car_acces USING btree (entitatid);


--
-- TOC entry 1991 (class 1259 OID 112705)
-- Name: car_acces_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_acces_pk_i ON car_acces USING btree (accesid);


--
-- TOC entry 2012 (class 1259 OID 112708)
-- Name: car_auditoria_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_auditoria_entitatid_fk_i ON car_auditoria USING btree (entitatid);


--
-- TOC entry 2015 (class 1259 OID 112707)
-- Name: car_auditoria_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_auditoria_pk_i ON car_auditoria USING btree (auditoriaid);


--
-- TOC entry 1978 (class 1259 OID 112711)
-- Name: car_avis_descripcioid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_avis_descripcioid_fk_i ON car_avis USING btree (descripcioid);


--
-- TOC entry 1979 (class 1259 OID 112712)
-- Name: car_avis_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_avis_entitatid_fk_i ON car_avis USING btree (entitatid);


--
-- TOC entry 1982 (class 1259 OID 112710)
-- Name: car_avis_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_avis_pk_i ON car_avis USING btree (avisid);


--
-- TOC entry 1983 (class 1259 OID 123215)
-- Name: car_avis_pluginfrontid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_avis_pluginfrontid_fk_i ON car_avis USING btree (pluginfrontid);


--
-- TOC entry 1951 (class 1259 OID 112704)
-- Name: car_enllaz_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_enllaz_entitatid_fk_i ON car_enllaz USING btree (entitatid);


--
-- TOC entry 1952 (class 1259 OID 123216)
-- Name: car_enllaz_logoid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_enllaz_logoid_fk_i ON car_enllaz USING btree (logoid);


--
-- TOC entry 1953 (class 1259 OID 112549)
-- Name: car_enllaz_nomid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_enllaz_nomid_fk_i ON car_enllaz USING btree (nomid);


--
-- TOC entry 1956 (class 1259 OID 112548)
-- Name: car_enllaz_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_enllaz_pk_i ON car_enllaz USING btree (enllazid);


--
-- TOC entry 1957 (class 1259 OID 112550)
-- Name: car_enllaz_urlid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_enllaz_urlid_fk_i ON car_enllaz USING btree (urlid);


--
-- TOC entry 1958 (class 1259 OID 112555)
-- Name: car_entitat_fitxercss_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_fitxercss_fk_i ON car_entitat USING btree (fitxercss);


--
-- TOC entry 1959 (class 1259 OID 123254)
-- Name: car_entitat_iconid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_iconid_fk_i ON car_entitat USING btree (iconid);


--
-- TOC entry 1960 (class 1259 OID 131479)
-- Name: car_entitat_logintextid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_logintextid_fk_i ON car_entitat USING btree (logintextid);


--
-- TOC entry 1961 (class 1259 OID 123251)
-- Name: car_entitat_logocapback_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_logocapback_fk_i ON car_entitat USING btree (logocapbackid);


--
-- TOC entry 1962 (class 1259 OID 123253)
-- Name: car_entitat_logolatfront_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_logolatfront_fk_i ON car_entitat USING btree (logolateralfrontid);


--
-- TOC entry 1963 (class 1259 OID 123252)
-- Name: car_entitat_logopeuback_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_logopeuback_fk_i ON car_entitat USING btree (logopeubackid);


--
-- TOC entry 1964 (class 1259 OID 112552)
-- Name: car_entitat_nom_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_nom_fk_i ON car_entitat USING btree (nomid);


--
-- TOC entry 1967 (class 1259 OID 112551)
-- Name: car_entitat_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_pk_i ON car_entitat USING btree (entitatid);


--
-- TOC entry 1968 (class 1259 OID 123255)
-- Name: car_entitat_pluginloginid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_pluginloginid_fk_i ON car_entitat USING btree (pluginloginid);


--
-- TOC entry 1992 (class 1259 OID 112714)
-- Name: car_estadistica_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_estadistica_entitatid_fk_i ON car_estadistica USING btree (entitatid);


--
-- TOC entry 1995 (class 1259 OID 112713)
-- Name: car_estadistica_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_estadistica_pk_i ON car_estadistica USING btree (estadisticaid);


--
-- TOC entry 1940 (class 1259 OID 112452)
-- Name: car_fitxer_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_fitxer_pk_i ON car_fitxer USING btree (fitxerid);


--
-- TOC entry 1943 (class 1259 OID 112453)
-- Name: car_idioma_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_idioma_pk_i ON car_idioma USING btree (idiomaid);


--
-- TOC entry 1986 (class 1259 OID 112716)
-- Name: car_log_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_log_pk_i ON car_log USING btree (logid);


--
-- TOC entry 1987 (class 1259 OID 112718)
-- Name: car_log_pluginid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_log_pluginid_fk_i ON car_log USING btree (pluginid);


--
-- TOC entry 2016 (class 1259 OID 115023)
-- Name: car_plugent_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_plugent_entitatid_fk_i ON car_pluginentitat USING btree (entitatid);


--
-- TOC entry 2019 (class 1259 OID 115022)
-- Name: car_plugent_pluginid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_plugent_pluginid_fk_i ON car_pluginentitat USING btree (pluginid);


--
-- TOC entry 1973 (class 1259 OID 115029)
-- Name: car_plugin_descripcioid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_plugin_descripcioid_fk_i ON car_plugin USING btree (descripcioid);


--
-- TOC entry 1974 (class 1259 OID 112720)
-- Name: car_plugin_nomid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_plugin_nomid_fk_i ON car_plugin USING btree (nomid);


--
-- TOC entry 1977 (class 1259 OID 112719)
-- Name: car_plugin_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_plugin_pk_i ON car_plugin USING btree (pluginid);


--
-- TOC entry 2022 (class 1259 OID 115021)
-- Name: car_pluginentitat_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_pluginentitat_pk_i ON car_pluginentitat USING btree (pluginentitatid);


--
-- TOC entry 1969 (class 1259 OID 112722)
-- Name: car_propglob_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_propglob_entitatid_fk_i ON car_propietatglobal USING btree (entitatid);


--
-- TOC entry 1972 (class 1259 OID 112721)
-- Name: car_propietatglobal_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_propietatglobal_pk_i ON car_propietatglobal USING btree (propietatglobalid);


--
-- TOC entry 1946 (class 1259 OID 112454)
-- Name: car_traduccio_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_traduccio_pk_i ON car_traduccio USING btree (traduccioid);


--
-- TOC entry 1947 (class 1259 OID 112455)
-- Name: car_traducciomap_idiomaid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_traducciomap_idiomaid_fk_i ON car_traducciomap USING btree (idiomaid);


--
-- TOC entry 1948 (class 1259 OID 112456)
-- Name: car_traducciomap_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_traducciomap_pk_i ON car_traducciomap USING btree (traducciomapid);


--
-- TOC entry 1996 (class 1259 OID 114909)
-- Name: car_usuari_darreraentitat_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_usuari_darreraentitat_fk_i ON car_usuari USING btree (darreraentitat);


--
-- TOC entry 1997 (class 1259 OID 115020)
-- Name: car_usuari_idiomaid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_usuari_idiomaid_fk_i ON car_usuari USING btree (idiomaid);


--
-- TOC entry 2002 (class 1259 OID 112723)
-- Name: car_usuari_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_usuari_pk_i ON car_usuari USING btree (usuariid);


--
-- TOC entry 2007 (class 1259 OID 112724)
-- Name: car_usuarientitat_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_usuarientitat_pk_i ON car_usuarientitat USING btree (usuarientitatid);


--
-- TOC entry 2008 (class 1259 OID 112726)
-- Name: car_usuent_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_usuent_entitatid_fk_i ON car_usuarientitat USING btree (entitatid);


--
-- TOC entry 2011 (class 1259 OID 112725)
-- Name: car_usuent_usuariid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_usuent_usuariid_fk_i ON car_usuarientitat USING btree (usuariid);


--
-- TOC entry 2042 (class 2606 OID 112699)
-- Name: car_acces_entitat_entitatid_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_acces
    ADD CONSTRAINT car_acces_entitat_entitatid_fk FOREIGN KEY (entitatid) REFERENCES car_entitat(entitatid);


--
-- TOC entry 2040 (class 2606 OID 112602)
-- Name: car_avis_entitat_ent_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_avis
    ADD CONSTRAINT car_avis_entitat_ent_fk FOREIGN KEY (entitatid) REFERENCES car_entitat(entitatid);


--
-- TOC entry 2041 (class 2606 OID 123205)
-- Name: car_avis_plugin_pfront_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_avis
    ADD CONSTRAINT car_avis_plugin_pfront_fk FOREIGN KEY (pluginfrontid) REFERENCES car_plugin(pluginid);


--
-- TOC entry 2039 (class 2606 OID 112597)
-- Name: car_avis_traduccio_desc_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_avis
    ADD CONSTRAINT car_avis_traduccio_desc_fk FOREIGN KEY (descripcioid) REFERENCES car_traduccio(traduccioid);


--
-- TOC entry 2026 (class 2606 OID 112574)
-- Name: car_enllaz_entitat_ent_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_enllaz
    ADD CONSTRAINT car_enllaz_entitat_ent_fk FOREIGN KEY (entitatid) REFERENCES car_entitat(entitatid);


--
-- TOC entry 2027 (class 2606 OID 123210)
-- Name: car_enllaz_fitxer_logo_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_enllaz
    ADD CONSTRAINT car_enllaz_fitxer_logo_fk FOREIGN KEY (logoid) REFERENCES car_fitxer(fitxerid);


--
-- TOC entry 2024 (class 2606 OID 112518)
-- Name: car_enllaz_traduccio_nomid_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_enllaz
    ADD CONSTRAINT car_enllaz_traduccio_nomid_fk FOREIGN KEY (nomid) REFERENCES car_traduccio(traduccioid);


--
-- TOC entry 2025 (class 2606 OID 112523)
-- Name: car_enllaz_traduccio_urlid_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_enllaz
    ADD CONSTRAINT car_enllaz_traduccio_urlid_fk FOREIGN KEY (urlid) REFERENCES car_traduccio(traduccioid);


--
-- TOC entry 2028 (class 2606 OID 112528)
-- Name: car_entitat_fitxer_css_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_fitxer_css_fk FOREIGN KEY (fitxercss) REFERENCES car_fitxer(fitxerid);


--
-- TOC entry 2033 (class 2606 OID 123232)
-- Name: car_entitat_fitxer_icon_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_fitxer_icon_fk FOREIGN KEY (iconid) REFERENCES car_fitxer(fitxerid);


--
-- TOC entry 2030 (class 2606 OID 123217)
-- Name: car_entitat_fitxer_lcb_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_fitxer_lcb_fk FOREIGN KEY (logocapbackid) REFERENCES car_fitxer(fitxerid);


--
-- TOC entry 2032 (class 2606 OID 123227)
-- Name: car_entitat_fitxer_llf_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_fitxer_llf_fk FOREIGN KEY (logolateralfrontid) REFERENCES car_fitxer(fitxerid);


--
-- TOC entry 2031 (class 2606 OID 123222)
-- Name: car_entitat_fitxer_lpb_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_fitxer_lpb_fk FOREIGN KEY (logopeubackid) REFERENCES car_fitxer(fitxerid);


--
-- TOC entry 2034 (class 2606 OID 123237)
-- Name: car_entitat_plugin_login_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_plugin_login_fk FOREIGN KEY (pluginloginid) REFERENCES car_plugin(pluginid);


--
-- TOC entry 2035 (class 2606 OID 131474)
-- Name: car_entitat_traduccio_log_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_traduccio_log_fk FOREIGN KEY (logintextid) REFERENCES car_traduccio(traduccioid);


--
-- TOC entry 2029 (class 2606 OID 112543)
-- Name: car_entitat_traduccio_nom_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_traduccio_nom_fk FOREIGN KEY (nomid) REFERENCES car_traduccio(traduccioid);


--
-- TOC entry 2047 (class 2606 OID 115010)
-- Name: car_plugent_entitat_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_pluginentitat
    ADD CONSTRAINT car_plugent_entitat_fk FOREIGN KEY (entitatid) REFERENCES car_entitat(entitatid);


--
-- TOC entry 2048 (class 2606 OID 115015)
-- Name: car_plugent_plugin_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_pluginentitat
    ADD CONSTRAINT car_plugent_plugin_fk FOREIGN KEY (pluginid) REFERENCES car_plugin(pluginid);


--
-- TOC entry 2038 (class 2606 OID 115024)
-- Name: car_plugin_traduccio_desc_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_plugin
    ADD CONSTRAINT car_plugin_traduccio_desc_fk FOREIGN KEY (descripcioid) REFERENCES car_traduccio(traduccioid);


--
-- TOC entry 2037 (class 2606 OID 112586)
-- Name: car_plugin_traduccio_nom_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_plugin
    ADD CONSTRAINT car_plugin_traduccio_nom_fk FOREIGN KEY (nomid) REFERENCES car_traduccio(traduccioid);


--
-- TOC entry 2036 (class 2606 OID 112568)
-- Name: car_propglob_entitat_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_propietatglobal
    ADD CONSTRAINT car_propglob_entitat_fk FOREIGN KEY (entitatid) REFERENCES car_entitat(entitatid);


--
-- TOC entry 2023 (class 2606 OID 112457)
-- Name: car_traducmap_traduccio_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_traducciomap
    ADD CONSTRAINT car_traducmap_traduccio_fk FOREIGN KEY (traducciomapid) REFERENCES car_traduccio(traduccioid);


--
-- TOC entry 2043 (class 2606 OID 114904)
-- Name: car_usuari_entitat_last_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_usuari
    ADD CONSTRAINT car_usuari_entitat_last_fk FOREIGN KEY (darreraentitat) REFERENCES car_entitat(entitatid);


--
-- TOC entry 2044 (class 2606 OID 114973)
-- Name: car_usuari_idioma_idi_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_usuari
    ADD CONSTRAINT car_usuari_idioma_idi_fk FOREIGN KEY (idiomaid) REFERENCES car_idioma(idiomaid);


--
-- TOC entry 2046 (class 2606 OID 112676)
-- Name: car_usuent_entitat_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_usuarientitat
    ADD CONSTRAINT car_usuent_entitat_fk FOREIGN KEY (entitatid) REFERENCES car_entitat(entitatid);


--
-- TOC entry 2045 (class 2606 OID 112671)
-- Name: car_usuent_usuari_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_usuarientitat
    ADD CONSTRAINT car_usuent_usuari_fk FOREIGN KEY (usuariid) REFERENCES car_usuari(usuariid);


--
-- TOC entry 2162 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2020-12-22 14:12:35

--
-- PostgreSQL database dump complete
--

