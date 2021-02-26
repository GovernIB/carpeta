--
-- PostgreSQL database dump
--

-- Dumped from database version 8.4.14
-- Dumped by pg_dump version 9.3.1
-- Started on 2014-08-25 12:39:21

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = carpeta, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 150 (class 1259 OID 69544)
-- Name: car_fitxer; Type: TABLE; Schema: carpeta; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_fitxer (
    fitxerid bigint DEFAULT nextval('car_carpeta_seq'::regclass) NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    mime character varying(45) NOT NULL,
    nom character varying(255) NOT NULL,
    tamany bigint NOT NULL
);


ALTER TABLE carpeta.car_fitxer OWNER TO carpeta;

--
-- TOC entry 169 (class 1259 OID 92635)
-- Name: car_idioma; Type: TABLE; Schema: carpeta; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_idioma (
    idiomaid character varying(5) NOT NULL,
    nom character varying(50) NOT NULL,
    suportat boolean DEFAULT true NOT NULL,
    ordre integer DEFAULT 0 NOT NULL
);


ALTER TABLE carpeta.car_idioma OWNER TO carpeta;

--
-- TOC entry 184 (class 1259 OID 210385)
-- Name: car_traduccio; Type: TABLE; Schema: carpeta; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_traduccio (
    traduccioid bigint DEFAULT nextval('car_carpeta_seq'::regclass) NOT NULL
);


ALTER TABLE carpeta.car_traduccio OWNER TO carpeta;

--
-- TOC entry 183 (class 1259 OID 210326)
-- Name: car_traducciomap; Type: TABLE; Schema: carpeta; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_traducciomap (
    traducciomapid bigint NOT NULL,
    idiomaid character varying(5) NOT NULL,
    valor character varying(4000)
);


ALTER TABLE carpeta.car_traducciomap OWNER TO carpeta;

--
-- TOC entry 1836 (class 2606 OID 70326)
-- Name: car_fitxer_pk; Type: CONSTRAINT; Schema: carpeta; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_fitxer
    ADD CONSTRAINT car_fitxer_pk PRIMARY KEY (fitxerid);


--
-- TOC entry 1839 (class 2606 OID 96099)
-- Name: car_idioma_pk; Type: CONSTRAINT; Schema: carpeta; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_idioma
    ADD CONSTRAINT car_idioma_pk PRIMARY KEY (idiomaid);


--
-- TOC entry 1846 (class 2606 OID 210396)
-- Name: car_traduccio_pk; Type: CONSTRAINT; Schema: carpeta; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_traduccio
    ADD CONSTRAINT car_traduccio_pk PRIMARY KEY (traduccioid);


--
-- TOC entry 1842 (class 2606 OID 210501)
-- Name: car_traducciomap_pk; Type: CONSTRAINT; Schema: carpeta; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_traducciomap
    ADD CONSTRAINT car_traducciomap_pk PRIMARY KEY (traducciomapid, idiomaid);


--
-- TOC entry 1837 (class 1259 OID 202159)
-- Name: car_fitxer_pk_i; Type: INDEX; Schema: carpeta; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_fitxer_pk_i ON car_fitxer USING btree (fitxerid);


--
-- TOC entry 1840 (class 1259 OID 202163)
-- Name: car_idioma_pk_i; Type: INDEX; Schema: carpeta; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_idioma_pk_i ON car_idioma USING btree (idiomaid);


--
-- TOC entry 1847 (class 1259 OID 210461)
-- Name: car_traduccio_pk_i; Type: INDEX; Schema: carpeta; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_traduccio_pk_i ON car_traduccio USING btree (traduccioid);


--
-- TOC entry 1843 (class 1259 OID 210529)
-- Name: car_traducmap_idiomaid_pk_i; Type: INDEX; Schema: carpeta; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_traducmap_idiomaid_pk_i ON car_traducciomap USING btree (traducciomapid);


--
-- TOC entry 1844 (class 1259 OID 210528)
-- Name: car_traducmap_tradmapid_pk_i; Type: INDEX; Schema: carpeta; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_traducmap_tradmapid_pk_i ON car_traducciomap USING btree (traducciomapid);


--
-- TOC entry 1848 (class 2606 OID 210469)
-- Name: car_traducmap_traduccio_fk; Type: FK CONSTRAINT; Schema: carpeta; Owner: carpeta
--

ALTER TABLE ONLY car_traducciomap
    ADD CONSTRAINT car_traducmap_traduccio_fk FOREIGN KEY (traducciomapid) REFERENCES car_traduccio(traduccioid);


-- Completed on 2014-08-25 12:39:21

--
-- PostgreSQL database dump complete
--

