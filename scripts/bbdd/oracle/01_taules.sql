 
 
 
CREATE SEQUENCE car_carpeta_seq START WITH 1000 INCREMENT BY 1;
 
 
--
-- TOC entry 181 (class 1259 OID 112626)
-- Name: car_acces; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_acces (
    accesid NUMBER(19) DEFAULT car_carpeta_seq.nextval NOT NULL,
    nom VARCHAR2(255),
    llinatges VARCHAR2(255),
    nif VARCHAR2(50),
    ip VARCHAR2(100),
    proveidoridentitat VARCHAR2(255),
    nivellseguretat VARCHAR2(255),
    resultatautenticacio NUMBER(10),
    datadarreracces DATE,
    idioma VARCHAR2(50),
    entitatid NUMBER(19) NOT NULL
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
    auditoriaid NUMBER(19) DEFAULT car_carpeta_seq.nextval NOT NULL,
    accio NUMBER(10) NOT NULL,
    element VARCHAR2(255),
    dataaudit DATE NOT NULL,
    entitatid NUMBER(19),
    usuariid NUMBER(19)
);




--
-- TOC entry 179 (class 1259 OID 112591)
-- Name: car_avis; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_avis (
    avisid NUMBER(19) DEFAULT car_carpeta_seq.nextval NOT NULL,
    descripcioid NUMBER(19) NOT NULL,
    entitatid NUMBER(19) NOT NULL,
    datainici DATE,
    datafi DATE,
    tipus NUMBER(10) NOT NULL
);




--
-- TOC entry 175 (class 1259 OID 112505)
-- Name: car_enllaz; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_enllaz (
    enllazid NUMBER(19) DEFAULT car_carpeta_seq.nextval NOT NULL,
    tipus NUMBER(10) NOT NULL,
    urlid NUMBER(19) NOT NULL,
    nomid NUMBER(19) NOT NULL,
    entitatid NUMBER(19) NOT NULL
);




--
-- TOC entry 176 (class 1259 OID 112508)
-- Name: car_entitat; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_entitat (
    entitatid NUMBER(19) DEFAULT car_carpeta_seq.nextval NOT NULL,
    nomid NUMBER(19) NOT NULL,
    codidir3 VARCHAR2(255) NOT NULL,
    activa NUMBER(1) NOT NULL,
    logomenuid NUMBER(19),
    colormenu VARCHAR2(100) NOT NULL,
    CLOBepeu VARCHAR2(4000),
    logopeuid NUMBER(19) NOT NULL,
    versio VARCHAR2(50) NOT NULL,
    commit VARCHAR2(255),
    fitxercss NUMBER(19),
    context VARCHAR2(255),
    codi VARCHAR2(30) NOT NULL
);




--
-- TOC entry 182 (class 1259 OID 112640)
-- Name: car_estadistica; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_estadistica (
    estadisticaid NUMBER(19) DEFAULT car_carpeta_seq.nextval NOT NULL,
    entitatid NUMBER(19) NOT NULL,
    accesid NUMBER(19),
    accio NUMBER(10) NOT NULL,
    element VARCHAR2(255),
    dataestadistica DATE NOT NULL
);




--
-- TOC entry 171 (class 1259 OID 112421)
-- Name: car_fitxer; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_fitxer (
    fitxerid NUMBER(19) DEFAULT car_carpeta_seq.nextval NOT NULL,
    descripcio VARCHAR2(1000) DEFAULT NULL::VARCHAR2,
    mime VARCHAR2(255) NOT NULL,
    nom VARCHAR2(255) NOT NULL,
    tamany NUMBER(19) NOT NULL
);




--
-- TOC entry 172 (class 1259 OID 112429)
-- Name: car_idioma; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_idioma (
    idiomaid VARCHAR2(5) NOT NULL,
    nom VARCHAR2(50) NOT NULL,
    suportat NUMBER(1) DEFAULT true NOT NULL,
    ordre NUMBER(10) DEFAULT 0 NOT NULL
);




--
-- TOC entry 180 (class 1259 OID 112607)
-- Name: car_log; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_log (
    logid NUMBER(19) DEFAULT car_carpeta_seq.nextval NOT NULL,
    descripcio VARCHAR2(2000) NOT NULL,
    entitatid NUMBER(19),
    pluginid NUMBER(19),
    tipus NUMBER(10) NOT NULL,
    estat NUMBER(10) NOT NULL,
    temps NUMBER(19),
    datainici DATE NOT NULL,
    peticio VARCHAR2(255),
    error VARCHAR2(2000),
    excepcio CLOB
);




--
-- TOC entry 178 (class 1259 OID 112580)
-- Name: car_plugin; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_plugin (
    pluginid NUMBER(19) DEFAULT car_carpeta_seq.nextval NOT NULL,
    nomid NUMBER(19) NOT NULL,
    classe VARCHAR2(255) NOT NULL,
    tipus NUMBER(10) NOT NULL,
    propietats CLOB,
    actiu NUMBER(1) DEFAULT true NOT NULL
);




--
-- TOC entry 177 (class 1259 OID 112559)
-- Name: car_propietatglobal; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_propietatglobal (
    propietatglobalid NUMBER(19) DEFAULT car_carpeta_seq.nextval NOT NULL,
    codi VARCHAR2(250) NOT NULL,
    value VARCHAR2(4000),
    descripcio VARCHAR2(1000),
    entitatid NUMBER(19)
);




--
-- TOC entry 173 (class 1259 OID 112434)
-- Name: car_traduccio; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_traduccio (
    traduccioid NUMBER(19) DEFAULT car_carpeta_seq.nextval NOT NULL
);




--
-- TOC entry 174 (class 1259 OID 112438)
-- Name: car_traducciomap; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_traducciomap (
    traducciomapid NUMBER(19) NOT NULL,
    idiomaid VARCHAR2(10) NOT NULL,
    valor VARCHAR2(4000)
);




--
-- TOC entry 183 (class 1259 OID 112656)
-- Name: car_usuari; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_usuari (
    usuariid NUMBER(19) DEFAULT car_carpeta_seq.nextval NOT NULL,
    username VARCHAR2(255) NOT NULL,
    nom VARCHAR2(255) NOT NULL,
    llinatge1 VARCHAR2(255) NOT NULL,
    llinatge2 VARCHAR2(255),
    email VARCHAR2(255),
    nif VARCHAR2(255),
    idioma VARCHAR2(50) DEFAULT 'ca'::VARCHAR2 NOT NULL,
    darreraentitat NUMBER(19)
);




--
-- TOC entry 184 (class 1259 OID 112665)
-- Name: car_usuarientitat; Type: TABLE; Schema: public; Owner: carpeta; Tablespace: 
--

CREATE TABLE car_usuarientitat (
    usuarientitatid NUMBER(19) DEFAULT car_carpeta_seq.nextval NOT NULL,
    usuariid NUMBER(19) NOT NULL,
    entitatid NUMBER(19) NOT NULL,
    actiu NUMBER(1) NOT NULL
);



