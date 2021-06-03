--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.25
-- Dumped by pg_dump version 9.3.25
-- Started on 2021-06-03 12:25:37

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 1 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2205 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 171 (class 1259 OID 18233)
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
-- TOC entry 172 (class 1259 OID 18235)
-- Name: car_acces; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
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
-- TOC entry 2206 (class 0 OID 0)
-- Dependencies: 172
-- Name: COLUMN car_acces.idioma; Type: COMMENT; Schema: public; Owner: carpeta
--

COMMENT ON COLUMN public.car_acces.idioma IS 'Hauria d''estar enllaçat amb la taula idioma';


--
-- TOC entry 173 (class 1259 OID 18243)
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
-- TOC entry 174 (class 1259 OID 18245)
-- Name: car_auditoria; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
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
-- TOC entry 175 (class 1259 OID 18252)
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
-- TOC entry 176 (class 1259 OID 18254)
-- Name: car_avis; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE public.car_avis (
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
-- TOC entry 177 (class 1259 OID 18259)
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
-- TOC entry 178 (class 1259 OID 18261)
-- Name: car_enllaz; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE public.car_enllaz (
    enllazid bigint DEFAULT nextval('public.car_enllaz_seq'::regclass) NOT NULL,
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


ALTER TABLE public.car_enllaz OWNER TO carpeta;

--
-- TOC entry 179 (class 1259 OID 18265)
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
-- TOC entry 180 (class 1259 OID 18267)
-- Name: car_entitat; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE public.car_entitat (
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
    logintextid bigint,
    descripcioid bigint
);


ALTER TABLE public.car_entitat OWNER TO carpeta;

--
-- TOC entry 2207 (class 0 OID 0)
-- Dependencies: 180
-- Name: COLUMN car_entitat.suportfaq; Type: COMMENT; Schema: public; Owner: carpeta
--

COMMENT ON COLUMN public.car_entitat.suportfaq IS 'Preguntes Freqüents';


--
-- TOC entry 2208 (class 0 OID 0)
-- Dependencies: 180
-- Name: COLUMN car_entitat.suportqssi; Type: COMMENT; Schema: public; Owner: carpeta
--

COMMENT ON COLUMN public.car_entitat.suportqssi IS ' Queixes i suggeriments ';


--
-- TOC entry 2209 (class 0 OID 0)
-- Dependencies: 180
-- Name: COLUMN car_entitat.suportautenticacio; Type: COMMENT; Schema: public; Owner: carpeta
--

COMMENT ON COLUMN public.car_entitat.suportautenticacio IS 'Suport autenticació Front';


--
-- TOC entry 181 (class 1259 OID 18274)
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
-- TOC entry 182 (class 1259 OID 18276)
-- Name: car_estadistica; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE public.car_estadistica (
    estadisticaid bigint DEFAULT nextval('public.car_estadistica_seq'::regclass) NOT NULL,
    entitatid bigint,
    tipus integer NOT NULL,
    dataestadistica timestamp without time zone NOT NULL,
    comptador integer DEFAULT 0 NOT NULL,
    pluginid bigint
);


ALTER TABLE public.car_estadistica OWNER TO carpeta;

--
-- TOC entry 183 (class 1259 OID 18281)
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
-- TOC entry 184 (class 1259 OID 18283)
-- Name: car_fitxer; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE public.car_fitxer (
    fitxerid bigint DEFAULT nextval('public.car_fitxer_seq'::regclass) NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    mime character varying(255) NOT NULL,
    nom character varying(255) NOT NULL,
    tamany bigint NOT NULL
);


ALTER TABLE public.car_fitxer OWNER TO carpeta;

--
-- TOC entry 185 (class 1259 OID 18291)
-- Name: car_idioma; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE public.car_idioma (
    idiomaid character varying(5) NOT NULL,
    nom character varying(50) NOT NULL,
    suportat boolean DEFAULT true NOT NULL,
    ordre integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.car_idioma OWNER TO carpeta;

--
-- TOC entry 186 (class 1259 OID 18296)
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
-- TOC entry 187 (class 1259 OID 18298)
-- Name: car_log; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE public.car_log (
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
-- TOC entry 188 (class 1259 OID 18305)
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
-- TOC entry 189 (class 1259 OID 18307)
-- Name: car_plugin; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE public.car_plugin (
    pluginid bigint DEFAULT nextval('public.car_plugin_seq'::regclass) NOT NULL,
    nomid bigint NOT NULL,
    classe character varying(255) NOT NULL,
    tipus integer NOT NULL,
    propietats text,
    actiu boolean DEFAULT true NOT NULL,
    descripcioid bigint,
    logoid bigint,
    context character varying(50),
    titolllargid bigint,
    subtitolllargid bigint
);


ALTER TABLE public.car_plugin OWNER TO carpeta;

--
-- TOC entry 190 (class 1259 OID 18315)
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
-- TOC entry 191 (class 1259 OID 18317)
-- Name: car_pluginentitat; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE public.car_pluginentitat (
    pluginentitatid bigint DEFAULT nextval('public.car_pluginentitat_seq'::regclass) NOT NULL,
    pluginid bigint NOT NULL,
    entitatid bigint NOT NULL,
    actiu boolean NOT NULL,
    seccioid bigint,
    ordre integer DEFAULT 1 NOT NULL
);


ALTER TABLE public.car_pluginentitat OWNER TO carpeta;

--
-- TOC entry 192 (class 1259 OID 18321)
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
-- TOC entry 193 (class 1259 OID 18323)
-- Name: car_propietatglobal; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE public.car_propietatglobal (
    propietatglobalid bigint DEFAULT nextval('public.car_propietatglobal_seq'::regclass) NOT NULL,
    codi character varying(250) NOT NULL,
    value character varying(4000),
    descripcio character varying(1000),
    entitatid bigint
);


ALTER TABLE public.car_propietatglobal OWNER TO carpeta;

--
-- TOC entry 194 (class 1259 OID 18330)
-- Name: car_seccio_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE public.car_seccio_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_seccio_seq OWNER TO carpeta;

--
-- TOC entry 195 (class 1259 OID 18332)
-- Name: car_seccio; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE public.car_seccio (
    seccioid bigint DEFAULT nextval('public.car_seccio_seq'::regclass) NOT NULL,
    nomid bigint NOT NULL,
    descripcioid bigint NOT NULL,
    activa boolean DEFAULT true NOT NULL,
    iconaid bigint NOT NULL,
    secciopareid bigint,
    entitatid bigint NOT NULL,
    context character varying(50) DEFAULT nextval('public.car_seccio_seq'::regclass) NOT NULL,
    ordre integer DEFAULT 1 NOT NULL
);


ALTER TABLE public.car_seccio OWNER TO carpeta;

--
-- TOC entry 196 (class 1259 OID 18337)
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
-- TOC entry 197 (class 1259 OID 18339)
-- Name: car_traduccio; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE public.car_traduccio (
    traduccioid bigint DEFAULT nextval('public.car_traduccio_seq'::regclass) NOT NULL
);


ALTER TABLE public.car_traduccio OWNER TO carpeta;

--
-- TOC entry 198 (class 1259 OID 18343)
-- Name: car_traducciomap; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE public.car_traducciomap (
    traducciomapid bigint NOT NULL,
    idiomaid character varying(10) NOT NULL,
    valor character varying(4000)
);


ALTER TABLE public.car_traducciomap OWNER TO carpeta;

--
-- TOC entry 199 (class 1259 OID 18349)
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
-- TOC entry 200 (class 1259 OID 18351)
-- Name: car_usuari; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE public.car_usuari (
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
-- TOC entry 201 (class 1259 OID 18358)
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
-- TOC entry 202 (class 1259 OID 18360)
-- Name: car_usuarientitat; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE public.car_usuarientitat (
    usuarientitatid bigint DEFAULT nextval('public.car_usuarientitat_seq'::regclass) NOT NULL,
    usuariid bigint NOT NULL,
    entitatid bigint NOT NULL,
    actiu boolean NOT NULL
);


ALTER TABLE public.car_usuarientitat OWNER TO carpeta;

--
-- TOC entry 1954 (class 2606 OID 18397)
-- Name: car_acces_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY public.car_acces
    ADD CONSTRAINT car_acces_pk PRIMARY KEY (accesid);


--
-- TOC entry 1958 (class 2606 OID 18399)
-- Name: car_auditoria_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY public.car_auditoria
    ADD CONSTRAINT car_auditoria_pk PRIMARY KEY (auditoriaid);


--
-- TOC entry 1963 (class 2606 OID 18401)
-- Name: car_avis_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY public.car_avis
    ADD CONSTRAINT car_avis_pk PRIMARY KEY (avisid);


--
-- TOC entry 1971 (class 2606 OID 18403)
-- Name: car_enllaz_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY public.car_enllaz
    ADD CONSTRAINT car_enllaz_pk PRIMARY KEY (enllazid);


--
-- TOC entry 1984 (class 2606 OID 18405)
-- Name: car_entitat_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY public.car_entitat
    ADD CONSTRAINT car_entitat_pk PRIMARY KEY (entitatid);


--
-- TOC entry 1989 (class 2606 OID 18407)
-- Name: car_estadistica_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY public.car_estadistica
    ADD CONSTRAINT car_estadistica_pk PRIMARY KEY (estadisticaid);


--
-- TOC entry 1992 (class 2606 OID 18409)
-- Name: car_fitxer_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY public.car_fitxer
    ADD CONSTRAINT car_fitxer_pk PRIMARY KEY (fitxerid);


--
-- TOC entry 1995 (class 2606 OID 18411)
-- Name: car_idioma_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY public.car_idioma
    ADD CONSTRAINT car_idioma_pk PRIMARY KEY (idiomaid);


--
-- TOC entry 1998 (class 2606 OID 18413)
-- Name: car_log_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY public.car_log
    ADD CONSTRAINT car_log_pk PRIMARY KEY (logid);


--
-- TOC entry 2011 (class 2606 OID 18415)
-- Name: car_plugent_plug_ent_uk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY public.car_pluginentitat
    ADD CONSTRAINT car_plugent_plug_ent_uk UNIQUE (pluginid, entitatid);


--
-- TOC entry 2005 (class 2606 OID 18417)
-- Name: car_plugin_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY public.car_plugin
    ADD CONSTRAINT car_plugin_pk PRIMARY KEY (pluginid);


--
-- TOC entry 2015 (class 2606 OID 18419)
-- Name: car_pluginentitat_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY public.car_pluginentitat
    ADD CONSTRAINT car_pluginentitat_pk PRIMARY KEY (pluginentitatid);


--
-- TOC entry 2019 (class 2606 OID 18421)
-- Name: car_propietatglobal_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY public.car_propietatglobal
    ADD CONSTRAINT car_propietatglobal_pk PRIMARY KEY (propietatglobalid);


--
-- TOC entry 2022 (class 2606 OID 30618)
-- Name: car_seccio_context_uk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY public.car_seccio
    ADD CONSTRAINT car_seccio_context_uk UNIQUE (context);


--
-- TOC entry 2028 (class 2606 OID 18423)
-- Name: car_seccio_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY public.car_seccio
    ADD CONSTRAINT car_seccio_pk PRIMARY KEY (seccioid);


--
-- TOC entry 2031 (class 2606 OID 18425)
-- Name: car_traduccio_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY public.car_traduccio
    ADD CONSTRAINT car_traduccio_pk PRIMARY KEY (traduccioid);


--
-- TOC entry 2036 (class 2606 OID 18427)
-- Name: car_traducmap_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY public.car_traducciomap
    ADD CONSTRAINT car_traducmap_pk PRIMARY KEY (traducciomapid, idiomaid);


--
-- TOC entry 2040 (class 2606 OID 18429)
-- Name: car_usuari_nif_uk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY public.car_usuari
    ADD CONSTRAINT car_usuari_nif_uk UNIQUE (nif);


--
-- TOC entry 2042 (class 2606 OID 18431)
-- Name: car_usuari_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY public.car_usuari
    ADD CONSTRAINT car_usuari_pk PRIMARY KEY (usuariid);


--
-- TOC entry 2045 (class 2606 OID 18433)
-- Name: car_usuari_username_uk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY public.car_usuari
    ADD CONSTRAINT car_usuari_username_uk UNIQUE (username);


--
-- TOC entry 2047 (class 2606 OID 18435)
-- Name: car_usuarientitat_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY public.car_usuarientitat
    ADD CONSTRAINT car_usuarientitat_pk PRIMARY KEY (usuarientitatid);


--
-- TOC entry 2051 (class 2606 OID 18437)
-- Name: car_usuent_usu_ent_uk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY public.car_usuarientitat
    ADD CONSTRAINT car_usuent_usu_ent_uk UNIQUE (usuariid, entitatid);


--
-- TOC entry 1952 (class 1259 OID 18438)
-- Name: car_acces_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_acces_entitatid_fk_i ON public.car_acces USING btree (entitatid);


--
-- TOC entry 1955 (class 1259 OID 18439)
-- Name: car_acces_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_acces_pk_i ON public.car_acces USING btree (accesid);


--
-- TOC entry 1956 (class 1259 OID 18440)
-- Name: car_auditoria_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_auditoria_entitatid_fk_i ON public.car_auditoria USING btree (entitatid);


--
-- TOC entry 1959 (class 1259 OID 18441)
-- Name: car_auditoria_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_auditoria_pk_i ON public.car_auditoria USING btree (auditoriaid);


--
-- TOC entry 1960 (class 1259 OID 18442)
-- Name: car_avis_descripcioid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_avis_descripcioid_fk_i ON public.car_avis USING btree (descripcioid);


--
-- TOC entry 1961 (class 1259 OID 18443)
-- Name: car_avis_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_avis_entitatid_fk_i ON public.car_avis USING btree (entitatid);


--
-- TOC entry 1964 (class 1259 OID 18444)
-- Name: car_avis_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_avis_pk_i ON public.car_avis USING btree (avisid);


--
-- TOC entry 1965 (class 1259 OID 18445)
-- Name: car_avis_pluginfrontid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_avis_pluginfrontid_fk_i ON public.car_avis USING btree (pluginfrontid);


--
-- TOC entry 1966 (class 1259 OID 18446)
-- Name: car_enllaz_descripcioid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_enllaz_descripcioid_fk_i ON public.car_enllaz USING btree (descripcioid);


--
-- TOC entry 1967 (class 1259 OID 18447)
-- Name: car_enllaz_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_enllaz_entitatid_fk_i ON public.car_enllaz USING btree (entitatid);


--
-- TOC entry 1968 (class 1259 OID 18448)
-- Name: car_enllaz_logoid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_enllaz_logoid_fk_i ON public.car_enllaz USING btree (logoid);


--
-- TOC entry 1969 (class 1259 OID 18449)
-- Name: car_enllaz_nomid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_enllaz_nomid_fk_i ON public.car_enllaz USING btree (nomid);


--
-- TOC entry 1972 (class 1259 OID 18450)
-- Name: car_enllaz_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_enllaz_pk_i ON public.car_enllaz USING btree (enllazid);


--
-- TOC entry 1973 (class 1259 OID 18451)
-- Name: car_enllaz_seccioid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_enllaz_seccioid_fk_i ON public.car_enllaz USING btree (seccioid);


--
-- TOC entry 1974 (class 1259 OID 18452)
-- Name: car_enllaz_urlid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_enllaz_urlid_fk_i ON public.car_enllaz USING btree (urlid);


--
-- TOC entry 1975 (class 1259 OID 47052)
-- Name: car_entitat_descripcioid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_descripcioid_fk_i ON public.car_entitat USING btree (descripcioid);


--
-- TOC entry 1976 (class 1259 OID 18453)
-- Name: car_entitat_fitxercss_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_fitxercss_fk_i ON public.car_entitat USING btree (fitxercss);


--
-- TOC entry 1977 (class 1259 OID 18454)
-- Name: car_entitat_iconid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_iconid_fk_i ON public.car_entitat USING btree (iconid);


--
-- TOC entry 1978 (class 1259 OID 18455)
-- Name: car_entitat_logintextid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_logintextid_fk_i ON public.car_entitat USING btree (logintextid);


--
-- TOC entry 1979 (class 1259 OID 18456)
-- Name: car_entitat_logocapback_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_logocapback_fk_i ON public.car_entitat USING btree (logocapbackid);


--
-- TOC entry 1980 (class 1259 OID 18457)
-- Name: car_entitat_logolatfront_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_logolatfront_fk_i ON public.car_entitat USING btree (logolateralfrontid);


--
-- TOC entry 1981 (class 1259 OID 18458)
-- Name: car_entitat_logopeuback_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_logopeuback_fk_i ON public.car_entitat USING btree (logopeubackid);


--
-- TOC entry 1982 (class 1259 OID 18459)
-- Name: car_entitat_nom_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_nom_fk_i ON public.car_entitat USING btree (nomid);


--
-- TOC entry 1985 (class 1259 OID 18460)
-- Name: car_entitat_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_pk_i ON public.car_entitat USING btree (entitatid);


--
-- TOC entry 1986 (class 1259 OID 18461)
-- Name: car_entitat_pluginloginid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_pluginloginid_fk_i ON public.car_entitat USING btree (pluginloginid);


--
-- TOC entry 1987 (class 1259 OID 18462)
-- Name: car_estadistica_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_estadistica_entitatid_fk_i ON public.car_estadistica USING btree (entitatid);


--
-- TOC entry 1990 (class 1259 OID 18463)
-- Name: car_estadistica_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_estadistica_pk_i ON public.car_estadistica USING btree (estadisticaid);


--
-- TOC entry 1993 (class 1259 OID 18464)
-- Name: car_fitxer_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_fitxer_pk_i ON public.car_fitxer USING btree (fitxerid);


--
-- TOC entry 1996 (class 1259 OID 18465)
-- Name: car_idioma_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_idioma_pk_i ON public.car_idioma USING btree (idiomaid);


--
-- TOC entry 1999 (class 1259 OID 18466)
-- Name: car_log_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_log_pk_i ON public.car_log USING btree (logid);


--
-- TOC entry 2000 (class 1259 OID 18467)
-- Name: car_log_pluginid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_log_pluginid_fk_i ON public.car_log USING btree (pluginid);


--
-- TOC entry 2009 (class 1259 OID 18468)
-- Name: car_plugent_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_plugent_entitatid_fk_i ON public.car_pluginentitat USING btree (entitatid);


--
-- TOC entry 2012 (class 1259 OID 18469)
-- Name: car_plugent_pluginid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_plugent_pluginid_fk_i ON public.car_pluginentitat USING btree (pluginid);


--
-- TOC entry 2013 (class 1259 OID 18470)
-- Name: car_plugent_seccioid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_plugent_seccioid_fk_i ON public.car_pluginentitat USING btree (seccioid);


--
-- TOC entry 2001 (class 1259 OID 18471)
-- Name: car_plugin_descripcioid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_plugin_descripcioid_fk_i ON public.car_plugin USING btree (descripcioid);


--
-- TOC entry 2002 (class 1259 OID 18472)
-- Name: car_plugin_logoid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_plugin_logoid_fk_i ON public.car_plugin USING btree (logoid);


--
-- TOC entry 2003 (class 1259 OID 18473)
-- Name: car_plugin_nomid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_plugin_nomid_fk_i ON public.car_plugin USING btree (nomid);


--
-- TOC entry 2006 (class 1259 OID 18474)
-- Name: car_plugin_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_plugin_pk_i ON public.car_plugin USING btree (pluginid);


--
-- TOC entry 2007 (class 1259 OID 47135)
-- Name: car_plugin_subtitllargid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_plugin_subtitllargid_fk_i ON public.car_plugin USING btree (subtitolllargid);


--
-- TOC entry 2008 (class 1259 OID 47134)
-- Name: car_plugin_titolllargid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_plugin_titolllargid_fk_i ON public.car_plugin USING btree (titolllargid);


--
-- TOC entry 2016 (class 1259 OID 18475)
-- Name: car_pluginentitat_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_pluginentitat_pk_i ON public.car_pluginentitat USING btree (pluginentitatid);


--
-- TOC entry 2017 (class 1259 OID 18476)
-- Name: car_propglob_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_propglob_entitatid_fk_i ON public.car_propietatglobal USING btree (entitatid);


--
-- TOC entry 2020 (class 1259 OID 18477)
-- Name: car_propietatglobal_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_propietatglobal_pk_i ON public.car_propietatglobal USING btree (propietatglobalid);


--
-- TOC entry 2023 (class 1259 OID 18478)
-- Name: car_seccio_descripcioid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_seccio_descripcioid_fk_i ON public.car_seccio USING btree (descripcioid);


--
-- TOC entry 2024 (class 1259 OID 18479)
-- Name: car_seccio_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_seccio_entitatid_fk_i ON public.car_seccio USING btree (entitatid);


--
-- TOC entry 2025 (class 1259 OID 18480)
-- Name: car_seccio_iconaid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_seccio_iconaid_fk_i ON public.car_seccio USING btree (iconaid);


--
-- TOC entry 2026 (class 1259 OID 18481)
-- Name: car_seccio_nomid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_seccio_nomid_fk_i ON public.car_seccio USING btree (nomid);


--
-- TOC entry 2029 (class 1259 OID 18482)
-- Name: car_seccio_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_seccio_pk_i ON public.car_seccio USING btree (seccioid);


--
-- TOC entry 2032 (class 1259 OID 18483)
-- Name: car_traduccio_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_traduccio_pk_i ON public.car_traduccio USING btree (traduccioid);


--
-- TOC entry 2033 (class 1259 OID 18484)
-- Name: car_traducciomap_idiomaid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_traducciomap_idiomaid_fk_i ON public.car_traducciomap USING btree (idiomaid);


--
-- TOC entry 2034 (class 1259 OID 18485)
-- Name: car_traducciomap_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_traducciomap_pk_i ON public.car_traducciomap USING btree (traducciomapid);


--
-- TOC entry 2037 (class 1259 OID 18486)
-- Name: car_usuari_darreraentitat_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_usuari_darreraentitat_fk_i ON public.car_usuari USING btree (darreraentitat);


--
-- TOC entry 2038 (class 1259 OID 18487)
-- Name: car_usuari_idiomaid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_usuari_idiomaid_fk_i ON public.car_usuari USING btree (idiomaid);


--
-- TOC entry 2043 (class 1259 OID 18488)
-- Name: car_usuari_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_usuari_pk_i ON public.car_usuari USING btree (usuariid);


--
-- TOC entry 2048 (class 1259 OID 18489)
-- Name: car_usuarientitat_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_usuarientitat_pk_i ON public.car_usuarientitat USING btree (usuarientitatid);


--
-- TOC entry 2049 (class 1259 OID 18490)
-- Name: car_usuent_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_usuent_entitatid_fk_i ON public.car_usuarientitat USING btree (entitatid);


--
-- TOC entry 2052 (class 1259 OID 18491)
-- Name: car_usuent_usuariid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_usuent_usuariid_fk_i ON public.car_usuarientitat USING btree (usuariid);


--
-- TOC entry 2053 (class 2606 OID 18492)
-- Name: car_acces_entitat_entitatid_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_acces
    ADD CONSTRAINT car_acces_entitat_entitatid_fk FOREIGN KEY (entitatid) REFERENCES public.car_entitat(entitatid);


--
-- TOC entry 2054 (class 2606 OID 18497)
-- Name: car_avis_entitat_ent_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_avis
    ADD CONSTRAINT car_avis_entitat_ent_fk FOREIGN KEY (entitatid) REFERENCES public.car_entitat(entitatid);


--
-- TOC entry 2055 (class 2606 OID 18502)
-- Name: car_avis_plugin_pfront_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_avis
    ADD CONSTRAINT car_avis_plugin_pfront_fk FOREIGN KEY (pluginfrontid) REFERENCES public.car_plugin(pluginid);


--
-- TOC entry 2056 (class 2606 OID 18507)
-- Name: car_avis_traduccio_desc_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_avis
    ADD CONSTRAINT car_avis_traduccio_desc_fk FOREIGN KEY (descripcioid) REFERENCES public.car_traduccio(traduccioid);


--
-- TOC entry 2057 (class 2606 OID 18512)
-- Name: car_enllaz_entitat_ent_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_enllaz
    ADD CONSTRAINT car_enllaz_entitat_ent_fk FOREIGN KEY (entitatid) REFERENCES public.car_entitat(entitatid);


--
-- TOC entry 2058 (class 2606 OID 18517)
-- Name: car_enllaz_fitxer_logo_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_enllaz
    ADD CONSTRAINT car_enllaz_fitxer_logo_fk FOREIGN KEY (logoid) REFERENCES public.car_fitxer(fitxerid);


--
-- TOC entry 2059 (class 2606 OID 18522)
-- Name: car_enllaz_seccio_sec_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_enllaz
    ADD CONSTRAINT car_enllaz_seccio_sec_fk FOREIGN KEY (seccioid) REFERENCES public.car_seccio(seccioid);


--
-- TOC entry 2060 (class 2606 OID 18527)
-- Name: car_enllaz_traduccio_desid_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_enllaz
    ADD CONSTRAINT car_enllaz_traduccio_desid_fk FOREIGN KEY (descripcioid) REFERENCES public.car_traduccio(traduccioid);


--
-- TOC entry 2061 (class 2606 OID 18532)
-- Name: car_enllaz_traduccio_nomid_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_enllaz
    ADD CONSTRAINT car_enllaz_traduccio_nomid_fk FOREIGN KEY (nomid) REFERENCES public.car_traduccio(traduccioid);


--
-- TOC entry 2062 (class 2606 OID 18537)
-- Name: car_enllaz_traduccio_urlid_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_enllaz
    ADD CONSTRAINT car_enllaz_traduccio_urlid_fk FOREIGN KEY (urlid) REFERENCES public.car_traduccio(traduccioid);


--
-- TOC entry 2063 (class 2606 OID 18542)
-- Name: car_entitat_fitxer_css_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_entitat
    ADD CONSTRAINT car_entitat_fitxer_css_fk FOREIGN KEY (fitxercss) REFERENCES public.car_fitxer(fitxerid);


--
-- TOC entry 2064 (class 2606 OID 18547)
-- Name: car_entitat_fitxer_icon_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_entitat
    ADD CONSTRAINT car_entitat_fitxer_icon_fk FOREIGN KEY (iconid) REFERENCES public.car_fitxer(fitxerid);


--
-- TOC entry 2065 (class 2606 OID 18552)
-- Name: car_entitat_fitxer_lcb_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_entitat
    ADD CONSTRAINT car_entitat_fitxer_lcb_fk FOREIGN KEY (logocapbackid) REFERENCES public.car_fitxer(fitxerid);


--
-- TOC entry 2066 (class 2606 OID 18557)
-- Name: car_entitat_fitxer_llf_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_entitat
    ADD CONSTRAINT car_entitat_fitxer_llf_fk FOREIGN KEY (logolateralfrontid) REFERENCES public.car_fitxer(fitxerid);


--
-- TOC entry 2067 (class 2606 OID 18562)
-- Name: car_entitat_fitxer_lpb_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_entitat
    ADD CONSTRAINT car_entitat_fitxer_lpb_fk FOREIGN KEY (logopeubackid) REFERENCES public.car_fitxer(fitxerid);


--
-- TOC entry 2068 (class 2606 OID 18567)
-- Name: car_entitat_plugin_login_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_entitat
    ADD CONSTRAINT car_entitat_plugin_login_fk FOREIGN KEY (pluginloginid) REFERENCES public.car_plugin(pluginid);


--
-- TOC entry 2071 (class 2606 OID 47047)
-- Name: car_entitat_traduccio_des_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_entitat
    ADD CONSTRAINT car_entitat_traduccio_des_fk FOREIGN KEY (descripcioid) REFERENCES public.car_traduccio(traduccioid);


--
-- TOC entry 2069 (class 2606 OID 18572)
-- Name: car_entitat_traduccio_log_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_entitat
    ADD CONSTRAINT car_entitat_traduccio_log_fk FOREIGN KEY (logintextid) REFERENCES public.car_traduccio(traduccioid);


--
-- TOC entry 2070 (class 2606 OID 18577)
-- Name: car_entitat_traduccio_nom_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_entitat
    ADD CONSTRAINT car_entitat_traduccio_nom_fk FOREIGN KEY (nomid) REFERENCES public.car_traduccio(traduccioid);


--
-- TOC entry 2077 (class 2606 OID 18582)
-- Name: car_plugent_entitat_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_pluginentitat
    ADD CONSTRAINT car_plugent_entitat_fk FOREIGN KEY (entitatid) REFERENCES public.car_entitat(entitatid);


--
-- TOC entry 2078 (class 2606 OID 18587)
-- Name: car_plugent_plugin_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_pluginentitat
    ADD CONSTRAINT car_plugent_plugin_fk FOREIGN KEY (pluginid) REFERENCES public.car_plugin(pluginid);


--
-- TOC entry 2079 (class 2606 OID 18592)
-- Name: car_plugent_seccio_sec_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_pluginentitat
    ADD CONSTRAINT car_plugent_seccio_sec_fk FOREIGN KEY (seccioid) REFERENCES public.car_seccio(seccioid);


--
-- TOC entry 2072 (class 2606 OID 18597)
-- Name: car_plugin_fitxer_logo_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_plugin
    ADD CONSTRAINT car_plugin_fitxer_logo_fk FOREIGN KEY (logoid) REFERENCES public.car_fitxer(fitxerid);


--
-- TOC entry 2073 (class 2606 OID 18602)
-- Name: car_plugin_traduccio_desc_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_plugin
    ADD CONSTRAINT car_plugin_traduccio_desc_fk FOREIGN KEY (descripcioid) REFERENCES public.car_traduccio(traduccioid);


--
-- TOC entry 2074 (class 2606 OID 18607)
-- Name: car_plugin_traduccio_nom_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_plugin
    ADD CONSTRAINT car_plugin_traduccio_nom_fk FOREIGN KEY (nomid) REFERENCES public.car_traduccio(traduccioid);


--
-- TOC entry 2076 (class 2606 OID 47129)
-- Name: car_plugin_traduccio_stlar_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_plugin
    ADD CONSTRAINT car_plugin_traduccio_stlar_fk FOREIGN KEY (subtitolllargid) REFERENCES public.car_traduccio(traduccioid);


--
-- TOC entry 2075 (class 2606 OID 47119)
-- Name: car_plugin_traduccio_tllarg_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_plugin
    ADD CONSTRAINT car_plugin_traduccio_tllarg_fk FOREIGN KEY (titolllargid) REFERENCES public.car_traduccio(traduccioid);


--
-- TOC entry 2080 (class 2606 OID 18612)
-- Name: car_propglob_entitat_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_propietatglobal
    ADD CONSTRAINT car_propglob_entitat_fk FOREIGN KEY (entitatid) REFERENCES public.car_entitat(entitatid);


--
-- TOC entry 2081 (class 2606 OID 18617)
-- Name: car_seccio_entitat_ent_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_seccio
    ADD CONSTRAINT car_seccio_entitat_ent_fk FOREIGN KEY (entitatid) REFERENCES public.car_entitat(entitatid);


--
-- TOC entry 2082 (class 2606 OID 18622)
-- Name: car_seccio_fitxer_icon_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_seccio
    ADD CONSTRAINT car_seccio_fitxer_icon_fk FOREIGN KEY (iconaid) REFERENCES public.car_fitxer(fitxerid);


--
-- TOC entry 2083 (class 2606 OID 18627)
-- Name: car_seccio_traduccio_des_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_seccio
    ADD CONSTRAINT car_seccio_traduccio_des_fk FOREIGN KEY (descripcioid) REFERENCES public.car_traduccio(traduccioid);


--
-- TOC entry 2084 (class 2606 OID 18632)
-- Name: car_seccio_traduccio_nom_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_seccio
    ADD CONSTRAINT car_seccio_traduccio_nom_fk FOREIGN KEY (nomid) REFERENCES public.car_traduccio(traduccioid);


--
-- TOC entry 2085 (class 2606 OID 18637)
-- Name: car_traducmap_traduccio_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_traducciomap
    ADD CONSTRAINT car_traducmap_traduccio_fk FOREIGN KEY (traducciomapid) REFERENCES public.car_traduccio(traduccioid);


--
-- TOC entry 2086 (class 2606 OID 18642)
-- Name: car_usuari_entitat_last_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_usuari
    ADD CONSTRAINT car_usuari_entitat_last_fk FOREIGN KEY (darreraentitat) REFERENCES public.car_entitat(entitatid);


--
-- TOC entry 2087 (class 2606 OID 18647)
-- Name: car_usuari_idioma_idi_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_usuari
    ADD CONSTRAINT car_usuari_idioma_idi_fk FOREIGN KEY (idiomaid) REFERENCES public.car_idioma(idiomaid);


--
-- TOC entry 2088 (class 2606 OID 18652)
-- Name: car_usuent_entitat_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_usuarientitat
    ADD CONSTRAINT car_usuent_entitat_fk FOREIGN KEY (entitatid) REFERENCES public.car_entitat(entitatid);


--
-- TOC entry 2089 (class 2606 OID 18657)
-- Name: car_usuent_usuari_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY public.car_usuarientitat
    ADD CONSTRAINT car_usuent_usuari_fk FOREIGN KEY (usuariid) REFERENCES public.car_usuari(usuariid);


--
-- TOC entry 2204 (class 0 OID 0)
-- Dependencies: 7
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2021-06-03 12:25:38

--
-- PostgreSQL database dump complete
--

