--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2020-08-12 12:05:51


--
-- TOC entry 170 (class 1259 OID 112419)
-- Name: car_carpeta_seq; Type: SEQUENCE; Schema: public; Owner: carpeta
--

CREATE SEQUENCE car_carpeta_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 181 (class 1259 OID 112626)
-- Name: car_acces; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_acces (
    accesid bigint DEFAULT nextval('car_carpeta_seq'::regclass) NOT NULL,
    nom character varying(255),
    llinatges character varying(255),
    nif character varying(50),
    ip character varying(100),
    proveidoridentitat character varying(255),
    nivellseguretat character varying(255),
    resultatautenticacio integer,
    datadarreracces timestamp without time zone,
    idioma character varying(50),
    entitatid bigint NOT NULL
);




--
-- TOC entry 2111 (class 0 OID 0)
-- Dependencies: 181
-- Name: COLUMN car_acces.idioma; Type: COMMENT; Schema: public; Owner: carpeta
--

COMMENT ON COLUMN car_acces.idioma IS 'Hauria d''estar enllaçat amb la taula idioma';


--
-- TOC entry 185 (class 1259 OID 112683)
-- Name: car_auditoria; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_auditoria (
    auditoriaid bigint DEFAULT nextval('car_carpeta_seq'::regclass) NOT NULL,
    accio integer NOT NULL,
    element character varying(255),
    dataaudit timestamp without time zone NOT NULL,
    entitatid bigint,
    usuariid bigint
);




--
-- TOC entry 179 (class 1259 OID 112591)
-- Name: car_avis; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_avis (
    avisid bigint DEFAULT nextval('car_carpeta_seq'::regclass) NOT NULL,
    descripcioid bigint NOT NULL,
    entitatid bigint NOT NULL,
    datainici timestamp without time zone,
    datafi timestamp without time zone,
    tipus integer NOT NULL
);




--
-- TOC entry 175 (class 1259 OID 112505)
-- Name: car_enllaz; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_enllaz (
    enllazid bigint DEFAULT nextval('car_carpeta_seq'::regclass) NOT NULL,
    tipus integer NOT NULL,
    urlid bigint NOT NULL,
    nomid bigint NOT NULL,
    entitatid bigint NOT NULL
);




--
-- TOC entry 176 (class 1259 OID 112508)
-- Name: car_entitat; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_entitat (
    entitatid bigint DEFAULT nextval('car_carpeta_seq'::regclass) NOT NULL,
    nomid bigint NOT NULL,
    codidir3 character varying(255) NOT NULL,
    activa boolean NOT NULL,
    logomenuid bigint,
    colormenu character varying(100) NOT NULL,
    textepeu character varying(4000),
    logopeuid bigint NOT NULL,
    versio character varying(50) NOT NULL,
    commit character varying(255),
    fitxercss bigint,
    context character varying(255),
    codi character varying(30) NOT NULL
);




--
-- TOC entry 182 (class 1259 OID 112640)
-- Name: car_estadistica; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_estadistica (
    estadisticaid bigint DEFAULT nextval('car_carpeta_seq'::regclass) NOT NULL,
    entitatid bigint NOT NULL,
    accesid bigint,
    accio integer NOT NULL,
    element character varying(255),
    dataestadistica timestamp without time zone NOT NULL
);




--
-- TOC entry 171 (class 1259 OID 112421)
-- Name: car_fitxer; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_fitxer (
    fitxerid bigint DEFAULT nextval('car_carpeta_seq'::regclass) NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    mime character varying(255) NOT NULL,
    nom character varying(255) NOT NULL,
    tamany bigint NOT NULL
);




--
-- TOC entry 172 (class 1259 OID 112429)
-- Name: car_idioma; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_idioma (
    idiomaid character varying(5) NOT NULL,
    nom character varying(50) NOT NULL,
    suportat boolean DEFAULT true NOT NULL,
    ordre integer DEFAULT 0 NOT NULL
);




--
-- TOC entry 180 (class 1259 OID 112607)
-- Name: car_log; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_log (
    logid bigint DEFAULT nextval('car_carpeta_seq'::regclass) NOT NULL,
    descripcio character varying(2000) NOT NULL,
    entitatid bigint,
    pluginid bigint,
    tipus integer NOT NULL,
    estat integer NOT NULL,
    temps bigint,
    datainici timestamp without time zone NOT NULL,
    peticio character varying(255),
    error character varying(2000),
    excepcio text
);




--
-- TOC entry 178 (class 1259 OID 112580)
-- Name: car_plugin; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_plugin (
    pluginid bigint DEFAULT nextval('car_carpeta_seq'::regclass) NOT NULL,
    nomid bigint NOT NULL,
    classe character varying(255) NOT NULL,
    tipus integer NOT NULL,
    propietats text,
    actiu boolean DEFAULT true NOT NULL
);




--
-- TOC entry 177 (class 1259 OID 112559)
-- Name: car_propietatglobal; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_propietatglobal (
    propietatglobalid bigint DEFAULT nextval('car_carpeta_seq'::regclass) NOT NULL,
    codi character varying(250) NOT NULL,
    value character varying(4000),
    descripcio character varying(1000),
    entitatid bigint
);




--
-- TOC entry 173 (class 1259 OID 112434)
-- Name: car_traduccio; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_traduccio (
    traduccioid bigint DEFAULT nextval('car_carpeta_seq'::regclass) NOT NULL
);




--
-- TOC entry 174 (class 1259 OID 112438)
-- Name: car_traducciomap; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_traducciomap (
    traducciomapid bigint NOT NULL,
    idiomaid character varying(10) NOT NULL,
    valor character varying(4000)
);




--
-- TOC entry 183 (class 1259 OID 112656)
-- Name: car_usuari; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_usuari (
    usuariid bigint DEFAULT nextval('car_carpeta_seq'::regclass) NOT NULL,
    username character varying(255) NOT NULL,
    nom character varying(255) NOT NULL,
    llinatge1 character varying(255) NOT NULL,
    llinatge2 character varying(255),
    email character varying(255),
    nif character varying(255),
    idioma character varying(50) DEFAULT 'ca'::character varying NOT NULL,
    darreraentitat bigint
);




--
-- TOC entry 184 (class 1259 OID 112665)
-- Name: car_usuarientitat; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_usuarientitat (
    usuarientitatid bigint DEFAULT nextval('car_carpeta_seq'::regclass) NOT NULL,
    usuariid bigint NOT NULL,
    entitatid bigint NOT NULL,
    actiu boolean NOT NULL
);




--
-- TOC entry 1951 (class 2606 OID 112634)
-- Name: car_acces_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_acces
    ADD CONSTRAINT car_acces_pk PRIMARY KEY (accesid);


--
-- TOC entry 1971 (class 2606 OID 112688)
-- Name: car_auditoria_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_auditoria
    ADD CONSTRAINT car_auditoria_pk PRIMARY KEY (auditoriaid);


--
-- TOC entry 1942 (class 2606 OID 112596)
-- Name: car_avis_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_avis
    ADD CONSTRAINT car_avis_pk PRIMARY KEY (avisid);


--
-- TOC entry 1921 (class 2606 OID 112515)
-- Name: car_enllaz_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_enllaz
    ADD CONSTRAINT car_enllaz_pk PRIMARY KEY (enllazid);


--
-- TOC entry 1929 (class 2606 OID 112517)
-- Name: car_entitat_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_pk PRIMARY KEY (entitatid);


--
-- TOC entry 1956 (class 2606 OID 112645)
-- Name: car_estadistica_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_estadistica
    ADD CONSTRAINT car_estadistica_pk PRIMARY KEY (estadisticaid);


--
-- TOC entry 1906 (class 2606 OID 112445)
-- Name: car_fitxer_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_fitxer
    ADD CONSTRAINT car_fitxer_pk PRIMARY KEY (fitxerid);


--
-- TOC entry 1909 (class 2606 OID 112447)
-- Name: car_idioma_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_idioma
    ADD CONSTRAINT car_idioma_pk PRIMARY KEY (idiomaid);


--
-- TOC entry 1946 (class 2606 OID 112614)
-- Name: car_log_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_log
    ADD CONSTRAINT car_log_pk PRIMARY KEY (logid);


--
-- TOC entry 1937 (class 2606 OID 112585)
-- Name: car_plugin_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_plugin
    ADD CONSTRAINT car_plugin_pk PRIMARY KEY (pluginid);


--
-- TOC entry 1933 (class 2606 OID 112567)
-- Name: car_propietatglobal_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_propietatglobal
    ADD CONSTRAINT car_propietatglobal_pk PRIMARY KEY (propietatglobalid);


--
-- TOC entry 1912 (class 2606 OID 112449)
-- Name: car_traduccio_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_traduccio
    ADD CONSTRAINT car_traduccio_pk PRIMARY KEY (traduccioid);


--
-- TOC entry 1917 (class 2606 OID 112451)
-- Name: car_traducmap_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_traducciomap
    ADD CONSTRAINT car_traducmap_pk PRIMARY KEY (traducciomapid, idiomaid);


--
-- TOC entry 1960 (class 2606 OID 112664)
-- Name: car_usuari_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_usuari
    ADD CONSTRAINT car_usuari_pk PRIMARY KEY (usuariid);


--
-- TOC entry 1963 (class 2606 OID 112670)
-- Name: car_usuarientitat_pk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_usuarientitat
    ADD CONSTRAINT car_usuarientitat_pk PRIMARY KEY (usuarientitatid);


--
-- TOC entry 1967 (class 2606 OID 112682)
-- Name: car_usuent_usu_ent_uk; Type: CONSTRAINT; Schema: public; Owner: carpeta; Tablespace: 
--

ALTER TABLE ONLY car_usuarientitat
    ADD CONSTRAINT car_usuent_usu_ent_uk UNIQUE (usuariid, entitatid);


--
-- TOC entry 1949 (class 1259 OID 112706)
-- Name: car_acces_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_acces_entitatid_fk_i ON car_acces USING btree (entitatid);


--
-- TOC entry 1952 (class 1259 OID 112705)
-- Name: car_acces_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_acces_pk_i ON car_acces USING btree (accesid);


--
-- TOC entry 1969 (class 1259 OID 112708)
-- Name: car_auditoria_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_auditoria_entitatid_fk_i ON car_auditoria USING btree (entitatid);


--
-- TOC entry 1972 (class 1259 OID 112707)
-- Name: car_auditoria_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_auditoria_pk_i ON car_auditoria USING btree (auditoriaid);


--
-- TOC entry 1973 (class 1259 OID 112709)
-- Name: car_auditoria_usuariid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_auditoria_usuariid_fk_i ON car_auditoria USING btree (usuariid);


--
-- TOC entry 1939 (class 1259 OID 112711)
-- Name: car_avis_descripcioid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_avis_descripcioid_fk_i ON car_avis USING btree (descripcioid);


--
-- TOC entry 1940 (class 1259 OID 112712)
-- Name: car_avis_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_avis_entitatid_fk_i ON car_avis USING btree (entitatid);


--
-- TOC entry 1943 (class 1259 OID 112710)
-- Name: car_avis_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_avis_pk_i ON car_avis USING btree (avisid);


--
-- TOC entry 1918 (class 1259 OID 112704)
-- Name: car_enllaz_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_enllaz_entitatid_fk_i ON car_enllaz USING btree (entitatid);


--
-- TOC entry 1919 (class 1259 OID 112549)
-- Name: car_enllaz_nomid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_enllaz_nomid_fk_i ON car_enllaz USING btree (nomid);


--
-- TOC entry 1922 (class 1259 OID 112548)
-- Name: car_enllaz_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_enllaz_pk_i ON car_enllaz USING btree (enllazid);


--
-- TOC entry 1923 (class 1259 OID 112550)
-- Name: car_enllaz_urlid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_enllaz_urlid_fk_i ON car_enllaz USING btree (urlid);


--
-- TOC entry 1924 (class 1259 OID 112555)
-- Name: car_entitat_fitxercss_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_fitxercss_fk_i ON car_entitat USING btree (fitxercss);


--
-- TOC entry 1925 (class 1259 OID 112553)
-- Name: car_entitat_logomenuid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_logomenuid_fk_i ON car_entitat USING btree (logomenuid);


--
-- TOC entry 1926 (class 1259 OID 112554)
-- Name: car_entitat_logopeuid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_logopeuid_fk_i ON car_entitat USING btree (logopeuid);


--
-- TOC entry 1927 (class 1259 OID 112552)
-- Name: car_entitat_nom_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_nom_fk_i ON car_entitat USING btree (nomid);


--
-- TOC entry 1930 (class 1259 OID 112551)
-- Name: car_entitat_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_entitat_pk_i ON car_entitat USING btree (entitatid);


--
-- TOC entry 1953 (class 1259 OID 112715)
-- Name: car_estadistica_accesid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_estadistica_accesid_fk_i ON car_estadistica USING btree (accesid);


--
-- TOC entry 1954 (class 1259 OID 112714)
-- Name: car_estadistica_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_estadistica_entitatid_fk_i ON car_estadistica USING btree (entitatid);


--
-- TOC entry 1957 (class 1259 OID 112713)
-- Name: car_estadistica_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_estadistica_pk_i ON car_estadistica USING btree (estadisticaid);


--
-- TOC entry 1907 (class 1259 OID 112452)
-- Name: car_fitxer_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_fitxer_pk_i ON car_fitxer USING btree (fitxerid);


--
-- TOC entry 1910 (class 1259 OID 112453)
-- Name: car_idioma_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_idioma_pk_i ON car_idioma USING btree (idiomaid);


--
-- TOC entry 1944 (class 1259 OID 112717)
-- Name: car_log_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_log_entitatid_fk_i ON car_log USING btree (entitatid);


--
-- TOC entry 1947 (class 1259 OID 112716)
-- Name: car_log_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_log_pk_i ON car_log USING btree (logid);


--
-- TOC entry 1948 (class 1259 OID 112718)
-- Name: car_log_pluginid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_log_pluginid_fk_i ON car_log USING btree (pluginid);


--
-- TOC entry 1935 (class 1259 OID 112720)
-- Name: car_plugin_nomid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_plugin_nomid_fk_i ON car_plugin USING btree (nomid);


--
-- TOC entry 1938 (class 1259 OID 112719)
-- Name: car_plugin_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_plugin_pk_i ON car_plugin USING btree (pluginid);


--
-- TOC entry 1931 (class 1259 OID 112722)
-- Name: car_propglob_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_propglob_entitatid_fk_i ON car_propietatglobal USING btree (entitatid);


--
-- TOC entry 1934 (class 1259 OID 112721)
-- Name: car_propietatglobal_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_propietatglobal_pk_i ON car_propietatglobal USING btree (propietatglobalid);


--
-- TOC entry 1913 (class 1259 OID 112454)
-- Name: car_traduccio_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_traduccio_pk_i ON car_traduccio USING btree (traduccioid);


--
-- TOC entry 1914 (class 1259 OID 112455)
-- Name: car_traducciomap_idiomaid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_traducciomap_idiomaid_fk_i ON car_traducciomap USING btree (idiomaid);


--
-- TOC entry 1915 (class 1259 OID 112456)
-- Name: car_traducciomap_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_traducciomap_pk_i ON car_traducciomap USING btree (traducciomapid);


--
-- TOC entry 1958 (class 1259 OID 114909)
-- Name: car_usuari_darreraentitat_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_usuari_darreraentitat_fk_i ON car_usuari USING btree (darreraentitat);


--
-- TOC entry 1961 (class 1259 OID 112723)
-- Name: car_usuari_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_usuari_pk_i ON car_usuari USING btree (usuariid);


--
-- TOC entry 1964 (class 1259 OID 112724)
-- Name: car_usuarientitat_pk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_usuarientitat_pk_i ON car_usuarientitat USING btree (usuarientitatid);


--
-- TOC entry 1965 (class 1259 OID 112726)
-- Name: car_usuent_entitatid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_usuent_entitatid_fk_i ON car_usuarientitat USING btree (entitatid);


--
-- TOC entry 1968 (class 1259 OID 112725)
-- Name: car_usuent_usuariid_fk_i; Type: INDEX; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE INDEX car_usuent_usuariid_fk_i ON car_usuarientitat USING btree (usuariid);


--
-- TOC entry 1988 (class 2606 OID 112699)
-- Name: car_acces_entitat_entitatid_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_acces
    ADD CONSTRAINT car_acces_entitat_entitatid_fk FOREIGN KEY (entitatid) REFERENCES car_entitat(entitatid);


--
-- TOC entry 1995 (class 2606 OID 112694)
-- Name: car_audit_entitat_ent_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_auditoria
    ADD CONSTRAINT car_audit_entitat_ent_fk FOREIGN KEY (entitatid) REFERENCES car_entitat(entitatid);


--
-- TOC entry 1994 (class 2606 OID 112689)
-- Name: car_audit_usuari_usu_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_auditoria
    ADD CONSTRAINT car_audit_usuari_usu_fk FOREIGN KEY (usuariid) REFERENCES car_usuari(usuariid);


--
-- TOC entry 1985 (class 2606 OID 112602)
-- Name: car_avis_entitat_ent_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_avis
    ADD CONSTRAINT car_avis_entitat_ent_fk FOREIGN KEY (entitatid) REFERENCES car_entitat(entitatid);


--
-- TOC entry 1984 (class 2606 OID 112597)
-- Name: car_avis_traduccio_desc_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_avis
    ADD CONSTRAINT car_avis_traduccio_desc_fk FOREIGN KEY (descripcioid) REFERENCES car_traduccio(traduccioid);


--
-- TOC entry 1977 (class 2606 OID 112574)
-- Name: car_enllaz_entitat_ent_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_enllaz
    ADD CONSTRAINT car_enllaz_entitat_ent_fk FOREIGN KEY (entitatid) REFERENCES car_entitat(entitatid);


--
-- TOC entry 1975 (class 2606 OID 112518)
-- Name: car_enllaz_traduccio_nomid_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_enllaz
    ADD CONSTRAINT car_enllaz_traduccio_nomid_fk FOREIGN KEY (nomid) REFERENCES car_traduccio(traduccioid);


--
-- TOC entry 1976 (class 2606 OID 112523)
-- Name: car_enllaz_traduccio_urlid_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_enllaz
    ADD CONSTRAINT car_enllaz_traduccio_urlid_fk FOREIGN KEY (urlid) REFERENCES car_traduccio(traduccioid);


--
-- TOC entry 1978 (class 2606 OID 112528)
-- Name: car_entitat_fitxer_css_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_fitxer_css_fk FOREIGN KEY (fitxercss) REFERENCES car_fitxer(fitxerid);


--
-- TOC entry 1979 (class 2606 OID 112533)
-- Name: car_entitat_fitxer_logom_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_fitxer_logom_fk FOREIGN KEY (logomenuid) REFERENCES car_fitxer(fitxerid);


--
-- TOC entry 1980 (class 2606 OID 112538)
-- Name: car_entitat_fitxer_logop_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_fitxer_logop_fk FOREIGN KEY (logopeuid) REFERENCES car_fitxer(fitxerid);


--
-- TOC entry 1981 (class 2606 OID 112543)
-- Name: car_entitat_traduccio_nom_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_entitat
    ADD CONSTRAINT car_entitat_traduccio_nom_fk FOREIGN KEY (nomid) REFERENCES car_traduccio(traduccioid);


--
-- TOC entry 1990 (class 2606 OID 112651)
-- Name: car_estadis_acces_ac_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_estadistica
    ADD CONSTRAINT car_estadis_acces_ac_fk FOREIGN KEY (accesid) REFERENCES car_acces(accesid);


--
-- TOC entry 1989 (class 2606 OID 112646)
-- Name: car_estadis_entitat_ent_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_estadistica
    ADD CONSTRAINT car_estadis_entitat_ent_fk FOREIGN KEY (entitatid) REFERENCES car_entitat(entitatid);


--
-- TOC entry 1986 (class 2606 OID 112615)
-- Name: car_log_entitat_ent_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_log
    ADD CONSTRAINT car_log_entitat_ent_fk FOREIGN KEY (entitatid) REFERENCES car_entitat(entitatid);


--
-- TOC entry 1987 (class 2606 OID 112620)
-- Name: car_log_plugin_plu_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_log
    ADD CONSTRAINT car_log_plugin_plu_fk FOREIGN KEY (pluginid) REFERENCES car_plugin(pluginid);


--
-- TOC entry 1983 (class 2606 OID 112586)
-- Name: car_plugin_traduccio_nom_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_plugin
    ADD CONSTRAINT car_plugin_traduccio_nom_fk FOREIGN KEY (nomid) REFERENCES car_traduccio(traduccioid);


--
-- TOC entry 1982 (class 2606 OID 112568)
-- Name: car_propglob_entitat_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_propietatglobal
    ADD CONSTRAINT car_propglob_entitat_fk FOREIGN KEY (entitatid) REFERENCES car_entitat(entitatid);


--
-- TOC entry 1974 (class 2606 OID 112457)
-- Name: car_traducmap_traduccio_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_traducciomap
    ADD CONSTRAINT car_traducmap_traduccio_fk FOREIGN KEY (traducciomapid) REFERENCES car_traduccio(traduccioid);


--
-- TOC entry 1991 (class 2606 OID 114904)
-- Name: car_usuari_entitat_last_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_usuari
    ADD CONSTRAINT car_usuari_entitat_last_fk FOREIGN KEY (darreraentitat) REFERENCES car_entitat(entitatid);


--
-- TOC entry 1993 (class 2606 OID 112676)
-- Name: car_usuent_entitat_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_usuarientitat
    ADD CONSTRAINT car_usuent_entitat_fk FOREIGN KEY (entitatid) REFERENCES car_entitat(entitatid);


--
-- TOC entry 1992 (class 2606 OID 112671)
-- Name: car_usuent_usuari_fk; Type: FK CONSTRAINT; Schema: public; Owner: carpeta
--

ALTER TABLE ONLY car_usuarientitat
    ADD CONSTRAINT car_usuent_usuari_fk FOREIGN KEY (usuariid) REFERENCES car_usuari(usuariid);



-- Completed on 2020-08-12 12:05:54

--
-- PostgreSQL database dump complete
--

