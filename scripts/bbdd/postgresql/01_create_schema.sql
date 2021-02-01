--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.16
-- Dumped by pg_dump version 9.6.16

-- Started on 2021-02-01 12:07:55

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 186 (class 1259 OID 55054)
-- Name: car_acces_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE public.car_acces_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_acces_seq OWNER TO carpeta;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 187 (class 1259 OID 55056)
-- Name: car_acces; Type: TABLE; Schema: public; Owner: carpeta
--

CREATE TABLE public.car_acces (
    accesid bigint DEFAULT nextval('public.car_acces_seq'::regclass) NOT NULL,
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
    qaa integer
);


ALTER TABLE public.car_acces OWNER TO carpeta;

--
-- TOC entry 2354 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN car_acces.idioma; Type: COMMENT; Schema: public; Owner: carpeta
--

COMMENT ON COLUMN public.car_acces.idioma IS 'Hauria d''estar enllaçat amb la taula idioma';


--
-- TOC entry 188 (class 1259 OID 55063)
-- Name: car_auditoria_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE public.car_auditoria_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_auditoria_seq OWNER TO carpeta;

--
-- TOC entry 189 (class 1259 OID 55065)
-- Name: car_auditoria; Type: TABLE; Schema: public; Owner: carpeta
--

CREATE TABLE public.car_auditoria (
    auditoriaid bigint DEFAULT nextval('public.car_auditoria_seq'::regclass) NOT NULL,
    tipus integer NOT NULL,
    dataaudit timestamp without time zone NOT NULL,
    entitatid bigint,
    username character varying(255),
    objecte character varying(255)
);


ALTER TABLE public.car_auditoria OWNER TO carpeta;

--
-- TOC entry 190 (class 1259 OID 55069)
-- Name: car_avis_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE public.car_avis_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_avis_seq OWNER TO carpeta;

--
-- TOC entry 191 (class 1259 OID 55071)
-- Name: car_avis; Type: TABLE; Schema: public; Owner: carpeta
--

CREATE TABLE car_avis (
    avisid bigint DEFAULT nextval('public.car_avis_seq'::regclass) NOT NULL,
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
-- TOC entry 192 (class 1259 OID 55075)
-- Name: car_enllaz_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE public.car_enllaz_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_enllaz_seq OWNER TO carpeta;

--
-- TOC entry 193 (class 1259 OID 55077)
-- Name: car_enllaz; Type: TABLE; Schema: public; Owner: carpeta
--

CREATE TABLE car_enllaz (
    enllazid bigint DEFAULT nextval('public.car_enllaz_seq'::regclass) NOT NULL,
    tipus integer NOT NULL,
    urlid bigint NOT NULL,
    nomid bigint NOT NULL,
    entitatid bigint NOT NULL,
    logoid bigint NOT NULL
);


ALTER TABLE public.car_enllaz OWNER TO carpeta;

--
-- TOC entry 194 (class 1259 OID 55081)
-- Name: car_entitat_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE public.car_entitat_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_entitat_seq OWNER TO carpeta;

--
-- TOC entry 195 (class 1259 OID 55083)
-- Name: car_entitat; Type: TABLE; Schema: public; Owner: carpeta
--

CREATE TABLE car_entitat (
    entitatid bigint DEFAULT nextval('public.car_entitat_seq'::regclass) NOT NULL,
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
-- TOC entry 2355 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN car_entitat.suportfaq; Type: COMMENT; Schema: public; Owner: carpeta
--

COMMENT ON COLUMN public.car_entitat.suportfaq IS 'Preguntes Freqüents';


--
-- TOC entry 2356 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN car_entitat.suportqssi; Type: COMMENT; Schema: public; Owner: carpeta
--

COMMENT ON COLUMN public.car_entitat.suportqssi IS ' Queixes i suggeriments ';


--
-- TOC entry 2357 (class 0 OID 0)
-- Dependencies: 195
-- Name: COLUMN car_entitat.suportautenticacio; Type: COMMENT; Schema: public; Owner: carpeta
--

COMMENT ON COLUMN public.car_entitat.suportautenticacio IS 'Suport autenticació Front';


--
-- TOC entry 196 (class 1259 OID 55090)
-- Name: car_estadistica_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE public.car_estadistica_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_estadistica_seq OWNER TO carpeta;

--
-- TOC entry 197 (class 1259 OID 55092)
-- Name: car_estadistica; Type: TABLE; Schema: public; Owner: carpeta
--

CREATE TABLE car_estadistica (
    estadisticaid bigint DEFAULT nextval('public.car_estadistica_seq'::regclass) NOT NULL,
    entitatid bigint,
    tipus integer NOT NULL,
    dataestadistica timestamp without time zone NOT NULL,
    comptador integer DEFAULT 0 NOT NULL,
    pluginid bigint
);


ALTER TABLE public.car_estadistica OWNER TO carpeta;

--
-- TOC entry 198 (class 1259 OID 55096)
-- Name: car_fitxer_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE public.car_fitxer_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_fitxer_seq OWNER TO carpeta;

--
-- TOC entry 199 (class 1259 OID 55098)
-- Name: car_fitxer; Type: TABLE; Schema: public; Owner: carpeta
--

CREATE TABLE car_fitxer (
    fitxerid bigint DEFAULT nextval('public.car_fitxer_seq'::regclass) NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    mime character varying(255) NOT NULL,
    nom character varying(255) NOT NULL,
    tamany bigint NOT NULL
);


ALTER TABLE public.car_fitxer OWNER TO carpeta;

--
-- TOC entry 200 (class 1259 OID 55106)
-- Name: car_idioma; Type: TABLE; Schema: public; Owner: carpeta
--

CREATE TABLE car_idioma (
    idiomaid character varying(5) NOT NULL,
    nom character varying(50) NOT NULL,
    suportat boolean DEFAULT true NOT NULL,
    ordre integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.car_idioma OWNER TO carpeta;

--
-- TOC entry 201 (class 1259 OID 55111)
-- Name: car_log_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE public.car_log_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_log_seq OWNER TO carpeta;

--
-- TOC entry 202 (class 1259 OID 55113)
-- Name: car_log; Type: TABLE; Schema: public; Owner: carpeta
--

CREATE TABLE car_log (
    logid bigint DEFAULT nextval('public.car_log_seq'::regclass) NOT NULL,
    descripcio character varying(2000) NOT NULL,
    pluginid bigint,
    tipus integer NOT NULL,
    estat integer NOT NULL,
    temps bigint,
    datainici timestamp without time zone NOT NULL,
    peticio character varying(255),
    error text,
    excepcio text,
    entitatcodi character varying(9)
);


ALTER TABLE public.car_log OWNER TO carpeta;

--
-- TOC entry 203 (class 1259 OID 55120)
-- Name: car_plugin_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE public.car_plugin_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_plugin_seq OWNER TO carpeta;

--
-- TOC entry 204 (class 1259 OID 55122)
-- Name: car_plugin; Type: TABLE; Schema: public; Owner: carpeta
--

CREATE TABLE car_plugin (
    pluginid bigint DEFAULT nextval('public.car_plugin_seq'::regclass) NOT NULL,
    nomid bigint NOT NULL,
    classe character varying(255) NOT NULL,
    tipus integer NOT NULL,
    propietats text,
    actiu boolean DEFAULT true NOT NULL,
    descripcioid bigint,
    logoid bigint
);


ALTER TABLE public.car_plugin OWNER TO carpeta;

--
-- TOC entry 205 (class 1259 OID 55130)
-- Name: car_pluginentitat_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE public.car_pluginentitat_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_pluginentitat_seq OWNER TO carpeta;

--
-- TOC entry 206 (class 1259 OID 55132)
-- Name: car_pluginentitat; Type: TABLE; Schema: public; Owner: carpeta
--

CREATE TABLE car_pluginentitat (
    pluginentitatid bigint DEFAULT nextval('public.car_pluginentitat_seq'::regclass) NOT NULL,
    pluginid bigint NOT NULL,
    entitatid bigint NOT NULL,
    actiu boolean NOT NULL
);


ALTER TABLE public.car_pluginentitat OWNER TO carpeta;

--
-- TOC entry 207 (class 1259 OID 55136)
-- Name: car_propietatglobal_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE public.car_propietatglobal_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_propietatglobal_seq OWNER TO carpeta;

--
-- TOC entry 208 (class 1259 OID 55138)
-- Name: car_propietatglobal; Type: TABLE; Schema: public; Owner: carpeta
--

CREATE TABLE car_propietatglobal (
    propietatglobalid bigint DEFAULT nextval('public.car_propietatglobal_seq'::regclass) NOT NULL,
    codi character varying(250) NOT NULL,
    value character varying(4000),
    descripcio character varying(1000),
    entitatid bigint
);


ALTER TABLE public.car_propietatglobal OWNER TO carpeta;

--
-- TOC entry 209 (class 1259 OID 55145)
-- Name: car_traduccio_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE public.car_traduccio_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_traduccio_seq OWNER TO carpeta;

--
-- TOC entry 210 (class 1259 OID 55147)
-- Name: car_traduccio; Type: TABLE; Schema: public; Owner: carpeta
--

CREATE TABLE car_traduccio (
    traduccioid bigint DEFAULT nextval('public.car_traduccio_seq'::regclass) NOT NULL
);


ALTER TABLE public.car_traduccio OWNER TO carpeta;

--
-- TOC entry 211 (class 1259 OID 55151)
-- Name: car_traducciomap; Type: TABLE; Schema: public; Owner: carpeta
--

CREATE TABLE car_traducciomap (
    traducciomapid bigint NOT NULL,
    idiomaid character varying(10) NOT NULL,
    valor character varying(4000)
);


ALTER TABLE public.car_traducciomap OWNER TO carpeta;

--
-- TOC entry 212 (class 1259 OID 55157)
-- Name: car_usuari_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE public.car_usuari_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_usuari_seq OWNER TO carpeta;

--
-- TOC entry 213 (class 1259 OID 55159)
-- Name: car_usuari; Type: TABLE; Schema: public; Owner: carpeta
--

CREATE TABLE car_usuari (
    usuariid bigint DEFAULT nextval('public.car_usuari_seq'::regclass) NOT NULL,
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
-- TOC entry 214 (class 1259 OID 55166)
-- Name: car_usuarientitat_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE public.car_usuarientitat_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_usuarientitat_seq OWNER TO carpeta;

--
-- TOC entry 215 (class 1259 OID 55168)
-- Name: car_usuarientitat; Type: TABLE; Schema: public; Owner: carpeta
--

CREATE TABLE car_usuarientitat (
    usuarientitatid bigint DEFAULT nextval('public.car_usuarientitat_seq'::regclass) NOT NULL,
    usuariid bigint NOT NULL,
    entitatid bigint NOT NULL,
    actiu boolean NOT NULL
);


ALTER TABLE public.car_usuarientitat OWNER TO carpeta;

--
-- TOC entry 2120 (class 2606 OID 55173)
-- Name: car_acces car_acces_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_acces
    ADD CONSTRAINT car_acces_pk PRIMARY KEY (accesid);


--
-- TOC entry 2124 (class 2606 OID 55175)
-- Name: car_auditoria car_auditoria_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_auditoria
    ADD CONSTRAINT car_auditoria_pk PRIMARY KEY (auditoriaid);


--
-- TOC entry 2129 (class 2606 OID 55177)
-- Name: car_avis car_avis_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_avis
    ADD CONSTRAINT car_avis_pk PRIMARY KEY (avisid);


--
-- TOC entry 2136 (class 2606 OID 55179)
-- Name: car_enllaz car_enllaz_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_enllaz
    ADD CONSTRAINT car_enllaz_pk PRIMARY KEY (enllazid);


--
-- TOC entry 2147 (class 2606 OID 55181)
-- Name: car_entitat car_entitat_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_pk PRIMARY KEY (entitatid);


--
-- TOC entry 2152 (class 2606 OID 55183)
-- Name: car_estadistica car_estadistica_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_estadistica
    ADD CONSTRAINT car_estadistica_pk PRIMARY KEY (estadisticaid);


--
-- TOC entry 2155 (class 2606 OID 55185)
-- Name: car_fitxer car_fitxer_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_fitxer
    ADD CONSTRAINT car_fitxer_pk PRIMARY KEY (fitxerid);


--
-- TOC entry 2158 (class 2606 OID 55187)
-- Name: car_idioma car_idioma_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_idioma
    ADD CONSTRAINT car_idioma_pk PRIMARY KEY (idiomaid);


--
-- TOC entry 2161 (class 2606 OID 55189)
-- Name: car_log car_log_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_log
    ADD CONSTRAINT car_log_pk PRIMARY KEY (logid);


--
-- TOC entry 2172 (class 2606 OID 55191)
-- Name: car_pluginentitat car_plugent_plug_ent_uk; Type: CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_pluginentitat
    ADD CONSTRAINT car_plugent_plug_ent_uk UNIQUE (pluginid, entitatid);


--
-- TOC entry 2168 (class 2606 OID 55197)
-- Name: car_plugin car_plugin_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_plugin
    ADD CONSTRAINT car_plugin_pk PRIMARY KEY (pluginid);


--
-- TOC entry 2175 (class 2606 OID 55199)
-- Name: car_pluginentitat car_pluginentitat_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_pluginentitat
    ADD CONSTRAINT car_pluginentitat_pk PRIMARY KEY (pluginentitatid);


--
-- TOC entry 2179 (class 2606 OID 55201)
-- Name: car_propietatglobal car_propietatglobal_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_propietatglobal
    ADD CONSTRAINT car_propietatglobal_pk PRIMARY KEY (propietatglobalid);


--
-- TOC entry 2182 (class 2606 OID 55203)
-- Name: car_traduccio car_traduccio_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_traduccio
    ADD CONSTRAINT car_traduccio_pk PRIMARY KEY (traduccioid);


--
-- TOC entry 2187 (class 2606 OID 55205)
-- Name: car_traducciomap car_traducmap_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_traducciomap
    ADD CONSTRAINT car_traducmap_pk PRIMARY KEY (traducciomapid, idiomaid);


--
-- TOC entry 2191 (class 2606 OID 55195)
-- Name: car_usuari car_usuari_nif_uk; Type: CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_usuari
    ADD CONSTRAINT car_usuari_nif_uk UNIQUE (nif);


--
-- TOC entry 2193 (class 2606 OID 55207)
-- Name: car_usuari car_usuari_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_usuari
    ADD CONSTRAINT car_usuari_pk PRIMARY KEY (usuariid);


--
-- TOC entry 2196 (class 2606 OID 55193)
-- Name: car_usuari car_usuari_username_uk; Type: CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_usuari
    ADD CONSTRAINT car_usuari_username_uk UNIQUE (username);


--
-- TOC entry 2198 (class 2606 OID 55209)
-- Name: car_usuarientitat car_usuarientitat_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_usuarientitat
    ADD CONSTRAINT car_usuarientitat_pk PRIMARY KEY (usuarientitatid);


--
-- TOC entry 2202 (class 2606 OID 55211)
-- Name: car_usuarientitat car_usuent_usu_ent_uk; Type: CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_usuarientitat
    ADD CONSTRAINT car_usuent_usu_ent_uk UNIQUE (usuariid, entitatid);


--
-- TOC entry 2118 (class 1259 OID 55212)
-- Name: car_acces_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_acces_entitatid_fk_i ON public.car_acces USING btree (entitatid);


--
-- TOC entry 2121 (class 1259 OID 55213)
-- Name: car_acces_pk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_acces_pk_i ON public.car_acces USING btree (accesid);


--
-- TOC entry 2122 (class 1259 OID 55214)
-- Name: car_auditoria_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_auditoria_entitatid_fk_i ON car_auditoria USING btree (entitatid);


--
-- TOC entry 2125 (class 1259 OID 55215)
-- Name: car_auditoria_pk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_auditoria_pk_i ON car_auditoria USING btree (auditoriaid);


--
-- TOC entry 2126 (class 1259 OID 55217)
-- Name: car_avis_descripcioid_fk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_avis_descripcioid_fk_i ON car_avis USING btree (descripcioid);


--
-- TOC entry 2127 (class 1259 OID 55218)
-- Name: car_avis_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_avis_entitatid_fk_i ON car_avis USING btree (entitatid);


--
-- TOC entry 2130 (class 1259 OID 55219)
-- Name: car_avis_pk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_avis_pk_i ON car_avis USING btree (avisid);


--
-- TOC entry 2131 (class 1259 OID 55576)
-- Name: car_avis_pluginfrontid_fk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_avis_pluginfrontid_fk_i ON car_avis USING btree (pluginfrontid);


--
-- TOC entry 2132 (class 1259 OID 55220)
-- Name: car_enllaz_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_enllaz_entitatid_fk_i ON car_enllaz USING btree (entitatid);


--
-- TOC entry 2133 (class 1259 OID 55577)
-- Name: car_enllaz_logoid_fk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_enllaz_logoid_fk_i ON car_enllaz USING btree (logoid);


--
-- TOC entry 2134 (class 1259 OID 55221)
-- Name: car_enllaz_nomid_fk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_enllaz_nomid_fk_i ON car_enllaz USING btree (nomid);


--
-- TOC entry 2137 (class 1259 OID 55222)
-- Name: car_enllaz_pk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_enllaz_pk_i ON car_enllaz USING btree (enllazid);


--
-- TOC entry 2138 (class 1259 OID 55223)
-- Name: car_enllaz_urlid_fk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_enllaz_urlid_fk_i ON car_enllaz USING btree (urlid);


--
-- TOC entry 2139 (class 1259 OID 55224)
-- Name: car_entitat_fitxercss_fk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_entitat_fitxercss_fk_i ON car_entitat USING btree (fitxercss);


--
-- TOC entry 2140 (class 1259 OID 55490)
-- Name: car_entitat_iconid_fk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_entitat_iconid_fk_i ON car_entitat USING btree (iconid);


--
-- TOC entry 2141 (class 1259 OID 63722)
-- Name: car_entitat_logintextid_fk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_entitat_logintextid_fk_i ON car_entitat USING btree (logintextid);


--
-- TOC entry 2142 (class 1259 OID 55487)
-- Name: car_entitat_logocapback_fk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_entitat_logocapback_fk_i ON car_entitat USING btree (logocapbackid);


--
-- TOC entry 2143 (class 1259 OID 55489)
-- Name: car_entitat_logolatfront_fk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_entitat_logolatfront_fk_i ON car_entitat USING btree (logolateralfrontid);


--
-- TOC entry 2144 (class 1259 OID 55488)
-- Name: car_entitat_logopeuback_fk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_entitat_logopeuback_fk_i ON car_entitat USING btree (logopeubackid);


--
-- TOC entry 2145 (class 1259 OID 55227)
-- Name: car_entitat_nom_fk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_entitat_nom_fk_i ON car_entitat USING btree (nomid);


--
-- TOC entry 2148 (class 1259 OID 55228)
-- Name: car_entitat_pk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_entitat_pk_i ON car_entitat USING btree (entitatid);


--
-- TOC entry 2149 (class 1259 OID 55491)
-- Name: car_entitat_pluginloginid_fk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_entitat_pluginloginid_fk_i ON car_entitat USING btree (pluginloginid);


--
-- TOC entry 2150 (class 1259 OID 55230)
-- Name: car_estadistica_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_estadistica_entitatid_fk_i ON car_estadistica USING btree (entitatid);


--
-- TOC entry 2153 (class 1259 OID 55231)
-- Name: car_estadistica_pk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_estadistica_pk_i ON car_estadistica USING btree (estadisticaid);


--
-- TOC entry 2156 (class 1259 OID 55232)
-- Name: car_fitxer_pk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_fitxer_pk_i ON car_fitxer USING btree (fitxerid);


--
-- TOC entry 2159 (class 1259 OID 55233)
-- Name: car_idioma_pk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_idioma_pk_i ON car_idioma USING btree (idiomaid);


--
-- TOC entry 2162 (class 1259 OID 55235)
-- Name: car_log_pk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_log_pk_i ON car_log USING btree (logid);


--
-- TOC entry 2163 (class 1259 OID 55236)
-- Name: car_log_pluginid_fk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_log_pluginid_fk_i ON car_log USING btree (pluginid);


--
-- TOC entry 2170 (class 1259 OID 55237)
-- Name: car_plugent_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_plugent_entitatid_fk_i ON car_pluginentitat USING btree (entitatid);


--
-- TOC entry 2173 (class 1259 OID 55238)
-- Name: car_plugent_pluginid_fk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_plugent_pluginid_fk_i ON car_pluginentitat USING btree (pluginid);


--
-- TOC entry 2164 (class 1259 OID 55239)
-- Name: car_plugin_descripcioid_fk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_plugin_descripcioid_fk_i ON car_plugin USING btree (descripcioid);


--
-- TOC entry 2165 (class 1259 OID 63764)
-- Name: car_plugin_logoid_fk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_plugin_logoid_fk_i ON car_plugin USING btree (logoid);


--
-- TOC entry 2166 (class 1259 OID 55240)
-- Name: car_plugin_nomid_fk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_plugin_nomid_fk_i ON car_plugin USING btree (nomid);


--
-- TOC entry 2169 (class 1259 OID 55241)
-- Name: car_plugin_pk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_plugin_pk_i ON car_plugin USING btree (pluginid);


--
-- TOC entry 2176 (class 1259 OID 55242)
-- Name: car_pluginentitat_pk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_pluginentitat_pk_i ON car_pluginentitat USING btree (pluginentitatid);


--
-- TOC entry 2177 (class 1259 OID 55243)
-- Name: car_propglob_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_propglob_entitatid_fk_i ON car_propietatglobal USING btree (entitatid);


--
-- TOC entry 2180 (class 1259 OID 55244)
-- Name: car_propietatglobal_pk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_propietatglobal_pk_i ON car_propietatglobal USING btree (propietatglobalid);


--
-- TOC entry 2183 (class 1259 OID 55245)
-- Name: car_traduccio_pk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_traduccio_pk_i ON car_traduccio USING btree (traduccioid);


--
-- TOC entry 2184 (class 1259 OID 55246)
-- Name: car_traducciomap_idiomaid_fk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_traducciomap_idiomaid_fk_i ON car_traducciomap USING btree (idiomaid);


--
-- TOC entry 2185 (class 1259 OID 55247)
-- Name: car_traducciomap_pk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_traducciomap_pk_i ON car_traducciomap USING btree (traducciomapid);


--
-- TOC entry 2188 (class 1259 OID 55248)
-- Name: car_usuari_darreraentitat_fk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_usuari_darreraentitat_fk_i ON car_usuari USING btree (darreraentitat);


--
-- TOC entry 2189 (class 1259 OID 55249)
-- Name: car_usuari_idiomaid_fk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_usuari_idiomaid_fk_i ON car_usuari USING btree (idiomaid);


--
-- TOC entry 2194 (class 1259 OID 55250)
-- Name: car_usuari_pk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_usuari_pk_i ON car_usuari USING btree (usuariid);


--
-- TOC entry 2199 (class 1259 OID 55251)
-- Name: car_usuarientitat_pk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_usuarientitat_pk_i ON car_usuarientitat USING btree (usuarientitatid);


--
-- TOC entry 2200 (class 1259 OID 55252)
-- Name: car_usuent_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_usuent_entitatid_fk_i ON car_usuarientitat USING btree (entitatid);


--
-- TOC entry 2203 (class 1259 OID 55253)
-- Name: car_usuent_usuariid_fk_i; Type: INDEX; Schema: public; Owner: carpeta
--

CREATE INDEX car_usuent_usuariid_fk_i ON car_usuarientitat USING btree (usuariid);


--
-- TOC entry 2204 (class 2606 OID 55254)
-- Name: car_acces car_acces_entitat_entitatid_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_acces
    ADD CONSTRAINT car_acces_entitat_entitatid_fk FOREIGN KEY (entitatid) REFERENCES public.car_entitat(entitatid);


--
-- TOC entry 2205 (class 2606 OID 55269)
-- Name: car_avis car_avis_entitat_ent_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_avis
    ADD CONSTRAINT car_avis_entitat_ent_fk FOREIGN KEY (entitatid) REFERENCES public.car_entitat(entitatid);


--
-- TOC entry 2207 (class 2606 OID 55444)
-- Name: car_avis car_avis_plugin_pfront_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_avis
    ADD CONSTRAINT car_avis_plugin_pfront_fk FOREIGN KEY (pluginfrontid) REFERENCES public.car_plugin(pluginid);


--
-- TOC entry 2206 (class 2606 OID 55274)
-- Name: car_avis car_avis_traduccio_desc_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_avis
    ADD CONSTRAINT car_avis_traduccio_desc_fk FOREIGN KEY (descripcioid) REFERENCES public.car_traduccio(traduccioid);


--
-- TOC entry 2208 (class 2606 OID 55279)
-- Name: car_enllaz car_enllaz_entitat_ent_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_enllaz
    ADD CONSTRAINT car_enllaz_entitat_ent_fk FOREIGN KEY (entitatid) REFERENCES public.car_entitat(entitatid);


--
-- TOC entry 2211 (class 2606 OID 55449)
-- Name: car_enllaz car_enllaz_fitxer_logo_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_enllaz
    ADD CONSTRAINT car_enllaz_fitxer_logo_fk FOREIGN KEY (logoid) REFERENCES public.car_fitxer(fitxerid);


--
-- TOC entry 2209 (class 2606 OID 55284)
-- Name: car_enllaz car_enllaz_traduccio_nomid_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_enllaz
    ADD CONSTRAINT car_enllaz_traduccio_nomid_fk FOREIGN KEY (nomid) REFERENCES public.car_traduccio(traduccioid);


--
-- TOC entry 2210 (class 2606 OID 55289)
-- Name: car_enllaz car_enllaz_traduccio_urlid_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_enllaz
    ADD CONSTRAINT car_enllaz_traduccio_urlid_fk FOREIGN KEY (urlid) REFERENCES public.car_traduccio(traduccioid);


--
-- TOC entry 2212 (class 2606 OID 55294)
-- Name: car_entitat car_entitat_fitxer_css_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_fitxer_css_fk FOREIGN KEY (fitxercss) REFERENCES public.car_fitxer(fitxerid);


--
-- TOC entry 2217 (class 2606 OID 55477)
-- Name: car_entitat car_entitat_fitxer_icon_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_fitxer_icon_fk FOREIGN KEY (iconid) REFERENCES public.car_fitxer(fitxerid);


--
-- TOC entry 2214 (class 2606 OID 55462)
-- Name: car_entitat car_entitat_fitxer_lcb_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_fitxer_lcb_fk FOREIGN KEY (logocapbackid) REFERENCES public.car_fitxer(fitxerid);


--
-- TOC entry 2216 (class 2606 OID 55472)
-- Name: car_entitat car_entitat_fitxer_llf_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_fitxer_llf_fk FOREIGN KEY (logolateralfrontid) REFERENCES public.car_fitxer(fitxerid);


--
-- TOC entry 2215 (class 2606 OID 55467)
-- Name: car_entitat car_entitat_fitxer_lpb_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_fitxer_lpb_fk FOREIGN KEY (logopeubackid) REFERENCES public.car_fitxer(fitxerid);


--
-- TOC entry 2218 (class 2606 OID 55482)
-- Name: car_entitat car_entitat_plugin_login_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_plugin_login_fk FOREIGN KEY (pluginloginid) REFERENCES public.car_plugin(pluginid);


--
-- TOC entry 2219 (class 2606 OID 63717)
-- Name: car_entitat car_entitat_traduccio_log_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_traduccio_log_fk FOREIGN KEY (logintextid) REFERENCES public.car_traduccio(traduccioid);


--
-- TOC entry 2213 (class 2606 OID 55309)
-- Name: car_entitat car_entitat_traduccio_nom_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_traduccio_nom_fk FOREIGN KEY (nomid) REFERENCES public.car_traduccio(traduccioid);


--
-- TOC entry 2223 (class 2606 OID 55334)
-- Name: car_pluginentitat car_plugent_entitat_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_pluginentitat
    ADD CONSTRAINT car_plugent_entitat_fk FOREIGN KEY (entitatid) REFERENCES public.car_entitat(entitatid);


--
-- TOC entry 2224 (class 2606 OID 55339)
-- Name: car_pluginentitat car_plugent_plugin_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_pluginentitat
    ADD CONSTRAINT car_plugent_plugin_fk FOREIGN KEY (pluginid) REFERENCES public.car_plugin(pluginid);


--
-- TOC entry 2222 (class 2606 OID 63759)
-- Name: car_plugin car_plugin_fitxer_logo_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_plugin
    ADD CONSTRAINT car_plugin_fitxer_logo_fk FOREIGN KEY (logoid) REFERENCES public.car_fitxer(fitxerid);


--
-- TOC entry 2220 (class 2606 OID 55344)
-- Name: car_plugin car_plugin_traduccio_desc_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_plugin
    ADD CONSTRAINT car_plugin_traduccio_desc_fk FOREIGN KEY (descripcioid) REFERENCES public.car_traduccio(traduccioid);


--
-- TOC entry 2221 (class 2606 OID 55349)
-- Name: car_plugin car_plugin_traduccio_nom_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_plugin
    ADD CONSTRAINT car_plugin_traduccio_nom_fk FOREIGN KEY (nomid) REFERENCES public.car_traduccio(traduccioid);


--
-- TOC entry 2225 (class 2606 OID 55354)
-- Name: car_propietatglobal car_propglob_entitat_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_propietatglobal
    ADD CONSTRAINT car_propglob_entitat_fk FOREIGN KEY (entitatid) REFERENCES public.car_entitat(entitatid);


--
-- TOC entry 2226 (class 2606 OID 55359)
-- Name: car_traducciomap car_traducmap_traduccio_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_traducciomap
    ADD CONSTRAINT car_traducmap_traduccio_fk FOREIGN KEY (traducciomapid) REFERENCES public.car_traduccio(traduccioid);


--
-- TOC entry 2227 (class 2606 OID 55364)
-- Name: car_usuari car_usuari_entitat_last_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_usuari
    ADD CONSTRAINT car_usuari_entitat_last_fk FOREIGN KEY (darreraentitat) REFERENCES public.car_entitat(entitatid);


--
-- TOC entry 2228 (class 2606 OID 55369)
-- Name: car_usuari car_usuari_idioma_idi_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_usuari
    ADD CONSTRAINT car_usuari_idioma_idi_fk FOREIGN KEY (idiomaid) REFERENCES public.car_idioma(idiomaid);


--
-- TOC entry 2229 (class 2606 OID 55374)
-- Name: car_usuarientitat car_usuent_entitat_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_usuarientitat
    ADD CONSTRAINT car_usuent_entitat_fk FOREIGN KEY (entitatid) REFERENCES public.car_entitat(entitatid);


--
-- TOC entry 2230 (class 2606 OID 55379)
-- Name: car_usuarientitat car_usuent_usuari_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_usuarientitat
    ADD CONSTRAINT car_usuent_usuari_fk FOREIGN KEY (usuariid) REFERENCES public.car_usuari(usuariid);


-- Completed on 2021-02-01 12:07:56

--
-- PostgreSQL database dump complete
--

