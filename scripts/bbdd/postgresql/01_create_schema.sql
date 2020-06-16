--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.16
-- Dumped by pg_dump version 9.6.16

-- Started on 2020-06-15 12:45:50


--
-- TOC entry 197 (class 1259 OID 38361)
-- Name: car_acceso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.car_acceso (
    id bigint NOT NULL,
    apellidos character varying(255) NOT NULL,
    direccion_ip character varying(255) NOT NULL,
    fecha_ultimo_acceso timestamp without time zone NOT NULL,
    idioma character varying(255) NOT NULL,
    nif character varying(9) NOT NULL,
    nivel_seguridad character varying(255),
    nombre character varying(255) NOT NULL,
    proveedor_entidad character varying(255),
    result_autenticacion character varying(255),
    entidad bigint NOT NULL
);


-- ALTER TABLE public.car_acceso OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 38337)
-- Name: car_acceso_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.car_acceso_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


-- ALTER TABLE public.car_acceso_seq OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 38369)
-- Name: car_archivo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.car_archivo (
    id bigint NOT NULL,
    mime character varying(255) NOT NULL,
    nombre character varying(255) NOT NULL,
    tamano bigint NOT NULL
);


-- ALTER TABLE public.car_archivo OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 38339)
-- Name: car_archivo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.car_archivo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


-- ALTER TABLE public.car_archivo_seq OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 38377)
-- Name: car_auditoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.car_auditoria (
    id bigint NOT NULL,
    accion character varying(255) NOT NULL,
    elemento character varying(255) NOT NULL,
    fecha timestamp without time zone NOT NULL,
    entidad bigint NOT NULL,
    usuario bigint NOT NULL
);


-- ALTER TABLE public.car_auditoria OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 38341)
-- Name: car_auditoria_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.car_auditoria_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


-- ALTER TABLE public.car_auditoria_seq OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 38385)
-- Name: car_aviso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.car_aviso (
    id bigint NOT NULL,
    fecha_fin timestamp without time zone NOT NULL,
    fecha_inicio timestamp without time zone NOT NULL,
    tipo character varying(255) NOT NULL,
    entidad bigint NOT NULL
);


-- ALTER TABLE public.car_aviso OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 38343)
-- Name: car_aviso_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.car_aviso_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


-- ALTER TABLE public.car_aviso_seq OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 38390)
-- Name: car_enlace; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.car_enlace (
    id bigint NOT NULL,
    tipo character varying(255) NOT NULL,
    entidad bigint NOT NULL
);


-- ALTER TABLE public.car_enlace OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 38345)
-- Name: car_enlace_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.car_enlace_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


-- ALTER TABLE public.car_enlace_seq OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 38395)
-- Name: car_entidad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.car_entidad (
    id bigint NOT NULL,
    activo boolean NOT NULL,
    codigodir3 character varying(9) NOT NULL,
    colormenu character varying(255),
    comit character varying(255),
    contexto character varying(255) NOT NULL,
    textopie character varying(4000),
    version character varying(255),
    ficherocss bigint,
    logomenu bigint,
    logopie bigint
);


-- ALTER TABLE public.car_entidad OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 38347)
-- Name: car_entidad_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.car_entidad_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


-- ALTER TABLE public.car_entidad_seq OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 38403)
-- Name: car_estadistica; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.car_estadistica (
    id bigint NOT NULL,
    accion character varying(255) NOT NULL,
    elemento character varying(255) NOT NULL,
    fecha timestamp without time zone NOT NULL,
    acceso bigint NOT NULL,
    entidad bigint NOT NULL
);


-- ALTER TABLE public.car_estadistica OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 38349)
-- Name: car_estadistica_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.car_estadistica_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


-- ALTER TABLE public.car_estadistica_seq OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 38411)
-- Name: car_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.car_log (
    id bigint NOT NULL,
    descripcion character varying(255),
    error character varying(255),
    estado character varying(255) NOT NULL,
    excepcion character varying(255),
    fecha timestamp without time zone NOT NULL,
    peticion character varying(255),
    tiempo bigint NOT NULL,
    tipo character varying(255) NOT NULL,
    entidad bigint NOT NULL,
    plugin bigint NOT NULL
);


-- ALTER TABLE public.car_log OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 38351)
-- Name: car_log_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.car_log_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


-- ALTER TABLE public.car_log_seq OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 38419)
-- Name: car_plugin; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.car_plugin (
    id bigint NOT NULL,
    clase character varying(255) NOT NULL,
    estado character varying(255) NOT NULL,
    prefijo_entidad character varying(255) NOT NULL,
    prefijo_props character varying(255) NOT NULL,
    tipo character varying(255) NOT NULL,
    entidad bigint
);


-- ALTER TABLE public.car_plugin OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 38353)
-- Name: car_plugin_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.car_plugin_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


-- ALTER TABLE public.car_plugin_seq OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 38355)
-- Name: car_propglobal_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.car_propglobal_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


-- ALTER TABLE public.car_propglobal_seq OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 38427)
-- Name: car_propiedad_global; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.car_propiedad_global (
    id bigint NOT NULL,
    codigo character varying(255) NOT NULL,
    descripcion character varying(255),
    valor character varying(255),
    entidad bigint NOT NULL
);


-- ALTER TABLE public.car_propiedad_global OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 38435)
-- Name: car_propiedad_plugin; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.car_propiedad_plugin (
    id bigint NOT NULL,
    codigo character varying(255) NOT NULL,
    descripcion character varying(255),
    valor character varying(255),
    plugin bigint NOT NULL
);


-- ALTER TABLE public.car_propiedad_plugin OWNER TO postgres;

--
-- TOC entry 195 (class 1259 OID 38357)
-- Name: car_propplugin_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.car_propplugin_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


-- ALTER TABLE public.car_propplugin_seq OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 38443)
-- Name: car_tra_aviso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.car_tra_aviso (
    idaviso bigint NOT NULL,
    texto_aviso character varying(255) NOT NULL,
    lang character varying(255) NOT NULL
);


-- ALTER TABLE public.car_tra_aviso OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 38451)
-- Name: car_tra_enlace; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.car_tra_enlace (
    idenlace bigint NOT NULL,
    url character varying(255) NOT NULL,
    lang character varying(255) NOT NULL
);


-- ALTER TABLE public.car_tra_enlace OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 38459)
-- Name: car_tra_entidad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.car_tra_entidad (
    identidad bigint NOT NULL,
    descripcion character varying(255),
    nombre character varying(255),
    lang character varying(255) NOT NULL
);


-- ALTER TABLE public.car_tra_entidad OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 38467)
-- Name: car_tra_plugin; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.car_tra_plugin (
    idplugin bigint NOT NULL,
    descripcion character varying(255),
    nombre character varying(255),
    lang character varying(255) NOT NULL
);


-- ALTER TABLE public.car_tra_plugin OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 38475)
-- Name: car_usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.car_usuario (
    id bigint NOT NULL,
    apellido1 character varying(255),
    apellido2 character varying(255),
    email character varying(255) NOT NULL,
    nombre character varying(255) NOT NULL,
    username character varying(255) NOT NULL,
    ultima_entidad bigint NOT NULL
);


-- ALTER TABLE public.car_usuario OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 38359)
-- Name: car_usuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.car_usuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


-- ALTER TABLE public.car_usuario_seq OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 38483)
-- Name: car_usuarioentidad; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.car_usuarioentidad (
    id bigint NOT NULL,
    activo boolean NOT NULL,
    entidad bigint NOT NULL,
    usuario bigint NOT NULL
);


-- ALTER TABLE public.car_usuarioentidad OWNER TO postgres;

--
-- TOC entry 2104 (class 2606 OID 38368)
-- Name: car_acceso car_acceso_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_acceso
    ADD CONSTRAINT car_acceso_pkey PRIMARY KEY (id);


--
-- TOC entry 2107 (class 2606 OID 38376)
-- Name: car_archivo car_archivo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_archivo
    ADD CONSTRAINT car_archivo_pkey PRIMARY KEY (id);


--
-- TOC entry 2112 (class 2606 OID 38384)
-- Name: car_auditoria car_auditoria_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_auditoria
    ADD CONSTRAINT car_auditoria_pkey PRIMARY KEY (id);


--
-- TOC entry 2116 (class 2606 OID 38389)
-- Name: car_aviso car_aviso_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_aviso
    ADD CONSTRAINT car_aviso_pkey PRIMARY KEY (id);


--
-- TOC entry 2120 (class 2606 OID 38394)
-- Name: car_enlace car_enlace_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_enlace
    ADD CONSTRAINT car_enlace_pkey PRIMARY KEY (id);


--
-- TOC entry 2127 (class 2606 OID 38402)
-- Name: car_entidad car_entidad_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_entidad
    ADD CONSTRAINT car_entidad_pkey PRIMARY KEY (id);


--
-- TOC entry 2134 (class 2606 OID 38410)
-- Name: car_estadistica car_estadistica_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_estadistica
    ADD CONSTRAINT car_estadistica_pkey PRIMARY KEY (id);


--
-- TOC entry 2138 (class 2606 OID 38418)
-- Name: car_log car_log_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_log
    ADD CONSTRAINT car_log_pkey PRIMARY KEY (id);


--
-- TOC entry 2143 (class 2606 OID 38426)
-- Name: car_plugin car_plugin_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_plugin
    ADD CONSTRAINT car_plugin_pkey PRIMARY KEY (id);


--
-- TOC entry 2148 (class 2606 OID 38434)
-- Name: car_propiedad_global car_propiedad_global_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_propiedad_global
    ADD CONSTRAINT car_propiedad_global_pkey PRIMARY KEY (id);


--
-- TOC entry 2152 (class 2606 OID 38442)
-- Name: car_propiedad_plugin car_propiedad_plugin_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_propiedad_plugin
    ADD CONSTRAINT car_propiedad_plugin_pkey PRIMARY KEY (id);


--
-- TOC entry 2159 (class 2606 OID 38450)
-- Name: car_tra_aviso car_tra_aviso_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_tra_aviso
    ADD CONSTRAINT car_tra_aviso_pkey PRIMARY KEY (idaviso, lang);


--
-- TOC entry 2161 (class 2606 OID 38458)
-- Name: car_tra_enlace car_tra_enlace_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_tra_enlace
    ADD CONSTRAINT car_tra_enlace_pkey PRIMARY KEY (idenlace, lang);


--
-- TOC entry 2163 (class 2606 OID 38466)
-- Name: car_tra_entidad car_tra_entidad_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_tra_entidad
    ADD CONSTRAINT car_tra_entidad_pkey PRIMARY KEY (identidad, lang);


--
-- TOC entry 2165 (class 2606 OID 38474)
-- Name: car_tra_plugin car_tra_plugin_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_tra_plugin
    ADD CONSTRAINT car_tra_plugin_pkey PRIMARY KEY (idplugin, lang);


--
-- TOC entry 2170 (class 2606 OID 38482)
-- Name: car_usuario car_usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_usuario
    ADD CONSTRAINT car_usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 2175 (class 2606 OID 38487)
-- Name: car_usuarioentidad car_usuarioentidad_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_usuarioentidad
    ADD CONSTRAINT car_usuarioentidad_pkey PRIMARY KEY (id);


--
-- TOC entry 2129 (class 2606 OID 38504)
-- Name: car_entidad uk_4ebo6icnww42h20lf6y38vkte; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_entidad
    ADD CONSTRAINT uk_4ebo6icnww42h20lf6y38vkte UNIQUE (codigodir3);


--
-- TOC entry 2157 (class 2606 OID 38522)
-- Name: car_propiedad_plugin uk_7k170hvydh8vk73rnbcb7mn27; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_propiedad_plugin
    ADD CONSTRAINT uk_7k170hvydh8vk73rnbcb7mn27 UNIQUE (codigo);


--
-- TOC entry 2172 (class 2606 OID 38527)
-- Name: car_usuario uk_90rvgbpbebcc9o6r123onct5q; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_usuario
    ADD CONSTRAINT uk_90rvgbpbebcc9o6r123onct5q UNIQUE (username);


--
-- TOC entry 2150 (class 2606 OID 38517)
-- Name: car_propiedad_global uk_d42vgayqtp4l97ltacw9f2bur; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_propiedad_global
    ADD CONSTRAINT uk_d42vgayqtp4l97ltacw9f2bur UNIQUE (codigo);


--
-- TOC entry 2101 (class 1259 OID 38489)
-- Name: car_acceso_entidad_fk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_acceso_entidad_fk_i ON public.car_acceso USING btree (entidad);


--
-- TOC entry 2102 (class 1259 OID 38488)
-- Name: car_acceso_pk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_acceso_pk_i ON public.car_acceso USING btree (id);


--
-- TOC entry 2105 (class 1259 OID 38490)
-- Name: car_archivo_pk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_archivo_pk_i ON public.car_archivo USING btree (id);


--
-- TOC entry 2108 (class 1259 OID 38493)
-- Name: car_audit_entidad_fk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_audit_entidad_fk_i ON public.car_auditoria USING btree (entidad);


--
-- TOC entry 2109 (class 1259 OID 38492)
-- Name: car_audit_usuario_fk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_audit_usuario_fk_i ON public.car_auditoria USING btree (usuario);


--
-- TOC entry 2110 (class 1259 OID 38491)
-- Name: car_auditoria_pk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_auditoria_pk_i ON public.car_auditoria USING btree (id);


--
-- TOC entry 2113 (class 1259 OID 38495)
-- Name: car_aviso_entidad_fk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_aviso_entidad_fk_i ON public.car_aviso USING btree (entidad);


--
-- TOC entry 2114 (class 1259 OID 38494)
-- Name: car_aviso_pk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_aviso_pk_i ON public.car_aviso USING btree (id);


--
-- TOC entry 2117 (class 1259 OID 38497)
-- Name: car_enlace_entidad_fk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_enlace_entidad_fk_i ON public.car_enlace USING btree (entidad);


--
-- TOC entry 2118 (class 1259 OID 38496)
-- Name: car_enlace_pk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_enlace_pk_i ON public.car_enlace USING btree (id);


--
-- TOC entry 2121 (class 1259 OID 38499)
-- Name: car_entidad_codigodir3_uk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_entidad_codigodir3_uk_i ON public.car_entidad USING btree (codigodir3);


--
-- TOC entry 2122 (class 1259 OID 38502)
-- Name: car_entidad_ficherocss_fk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_entidad_ficherocss_fk_i ON public.car_entidad USING btree (ficherocss);


--
-- TOC entry 2123 (class 1259 OID 38500)
-- Name: car_entidad_logomenu_fk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_entidad_logomenu_fk_i ON public.car_entidad USING btree (logomenu);


--
-- TOC entry 2124 (class 1259 OID 38501)
-- Name: car_entidad_logopie_fk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_entidad_logopie_fk_i ON public.car_entidad USING btree (logopie);


--
-- TOC entry 2125 (class 1259 OID 38498)
-- Name: car_entidad_pk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_entidad_pk_i ON public.car_entidad USING btree (id);


--
-- TOC entry 2130 (class 1259 OID 38506)
-- Name: car_estad_acceso_fk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_estad_acceso_fk_i ON public.car_estadistica USING btree (acceso);


--
-- TOC entry 2131 (class 1259 OID 38507)
-- Name: car_estad_entidad_fk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_estad_entidad_fk_i ON public.car_estadistica USING btree (entidad);


--
-- TOC entry 2132 (class 1259 OID 38505)
-- Name: car_estadistica_pk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_estadistica_pk_i ON public.car_estadistica USING btree (id);


--
-- TOC entry 2135 (class 1259 OID 38510)
-- Name: car_log_entidad_fk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_log_entidad_fk_i ON public.car_log USING btree (entidad);


--
-- TOC entry 2136 (class 1259 OID 38508)
-- Name: car_log_pk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_log_pk_i ON public.car_log USING btree (id);


--
-- TOC entry 2139 (class 1259 OID 38509)
-- Name: car_log_plugin_fk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_log_plugin_fk_i ON public.car_log USING btree (plugin);


--
-- TOC entry 2140 (class 1259 OID 38512)
-- Name: car_plugin_entidad_fk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_plugin_entidad_fk_i ON public.car_plugin USING btree (entidad);


--
-- TOC entry 2141 (class 1259 OID 38511)
-- Name: car_plugin_pk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_plugin_pk_i ON public.car_plugin USING btree (id);


--
-- TOC entry 2144 (class 1259 OID 38515)
-- Name: car_propglob_entidad_fk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_propglob_entidad_fk_i ON public.car_propiedad_global USING btree (entidad);


--
-- TOC entry 2145 (class 1259 OID 38514)
-- Name: car_propglobal_codigo_uk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_propglobal_codigo_uk_i ON public.car_propiedad_global USING btree (codigo);


--
-- TOC entry 2146 (class 1259 OID 38513)
-- Name: car_propglobal_pk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_propglobal_pk_i ON public.car_propiedad_global USING btree (id);


--
-- TOC entry 2153 (class 1259 OID 38520)
-- Name: car_propplug_plugin_fk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_propplug_plugin_fk_i ON public.car_propiedad_plugin USING btree (plugin);


--
-- TOC entry 2154 (class 1259 OID 38519)
-- Name: car_propplugin_codigo_uk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_propplugin_codigo_uk_i ON public.car_propiedad_plugin USING btree (codigo);


--
-- TOC entry 2155 (class 1259 OID 38518)
-- Name: car_propplugin_pk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_propplugin_pk_i ON public.car_propiedad_plugin USING btree (id);


--
-- TOC entry 2166 (class 1259 OID 38524)
-- Name: car_username_uk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_username_uk_i ON public.car_usuario USING btree (username);


--
-- TOC entry 2167 (class 1259 OID 38525)
-- Name: car_usuario_entidad_fk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_usuario_entidad_fk_i ON public.car_usuario USING btree (ultima_entidad);


--
-- TOC entry 2168 (class 1259 OID 38523)
-- Name: car_usuario_pk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_usuario_pk_i ON public.car_usuario USING btree (id);


--
-- TOC entry 2173 (class 1259 OID 38528)
-- Name: car_usuarioentidad_pk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_usuarioentidad_pk_i ON public.car_usuarioentidad USING btree (id);


--
-- TOC entry 2176 (class 1259 OID 38530)
-- Name: car_usuent_entidad_fk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_usuent_entidad_fk_i ON public.car_usuarioentidad USING btree (entidad);


--
-- TOC entry 2177 (class 1259 OID 38529)
-- Name: car_usuent_usuario_fk_i; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX car_usuent_usuario_fk_i ON public.car_usuarioentidad USING btree (usuario);


--
-- TOC entry 2178 (class 2606 OID 38531)
-- Name: car_acceso car_acceso_entidad_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_acceso
    ADD CONSTRAINT car_acceso_entidad_fk FOREIGN KEY (entidad) REFERENCES public.car_entidad(id);


--
-- TOC entry 2179 (class 2606 OID 38536)
-- Name: car_auditoria car_audit_entidad_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_auditoria
    ADD CONSTRAINT car_audit_entidad_fk FOREIGN KEY (entidad) REFERENCES public.car_entidad(id);


--
-- TOC entry 2180 (class 2606 OID 38541)
-- Name: car_auditoria car_audit_usuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_auditoria
    ADD CONSTRAINT car_audit_usuario_fk FOREIGN KEY (usuario) REFERENCES public.car_usuario(id);


--
-- TOC entry 2181 (class 2606 OID 38546)
-- Name: car_aviso car_aviso_entidad_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_aviso
    ADD CONSTRAINT car_aviso_entidad_fk FOREIGN KEY (entidad) REFERENCES public.car_entidad(id);


--
-- TOC entry 2193 (class 2606 OID 38606)
-- Name: car_tra_aviso car_aviso_traaviso_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_tra_aviso
    ADD CONSTRAINT car_aviso_traaviso_fk FOREIGN KEY (idaviso) REFERENCES public.car_aviso(id);


--
-- TOC entry 2182 (class 2606 OID 38551)
-- Name: car_enlace car_enlace_entidad_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_enlace
    ADD CONSTRAINT car_enlace_entidad_fk FOREIGN KEY (entidad) REFERENCES public.car_entidad(id);


--
-- TOC entry 2194 (class 2606 OID 38611)
-- Name: car_tra_enlace car_enlace_traenlac_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_tra_enlace
    ADD CONSTRAINT car_enlace_traenlac_fk FOREIGN KEY (idenlace) REFERENCES public.car_enlace(id);


--
-- TOC entry 2183 (class 2606 OID 38556)
-- Name: car_entidad car_entidad_ficherocss_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_entidad
    ADD CONSTRAINT car_entidad_ficherocss_fk FOREIGN KEY (ficherocss) REFERENCES public.car_archivo(id);


--
-- TOC entry 2184 (class 2606 OID 38561)
-- Name: car_entidad car_entidad_logomenu_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_entidad
    ADD CONSTRAINT car_entidad_logomenu_fk FOREIGN KEY (logomenu) REFERENCES public.car_archivo(id);


--
-- TOC entry 2185 (class 2606 OID 38566)
-- Name: car_entidad car_entidad_logopie_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_entidad
    ADD CONSTRAINT car_entidad_logopie_fk FOREIGN KEY (logopie) REFERENCES public.car_archivo(id);


--
-- TOC entry 2195 (class 2606 OID 38616)
-- Name: car_tra_entidad car_entidad_traentid_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_tra_entidad
    ADD CONSTRAINT car_entidad_traentid_fk FOREIGN KEY (identidad) REFERENCES public.car_entidad(id);


--
-- TOC entry 2186 (class 2606 OID 38571)
-- Name: car_estadistica car_estad_acceso_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_estadistica
    ADD CONSTRAINT car_estad_acceso_fk FOREIGN KEY (acceso) REFERENCES public.car_acceso(id);


--
-- TOC entry 2187 (class 2606 OID 38576)
-- Name: car_estadistica car_estad_entidad_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_estadistica
    ADD CONSTRAINT car_estad_entidad_fk FOREIGN KEY (entidad) REFERENCES public.car_entidad(id);


--
-- TOC entry 2188 (class 2606 OID 38581)
-- Name: car_log car_log_entidad_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_log
    ADD CONSTRAINT car_log_entidad_fk FOREIGN KEY (entidad) REFERENCES public.car_entidad(id);


--
-- TOC entry 2189 (class 2606 OID 38586)
-- Name: car_log car_log_plugin_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_log
    ADD CONSTRAINT car_log_plugin_fk FOREIGN KEY (plugin) REFERENCES public.car_plugin(id);


--
-- TOC entry 2190 (class 2606 OID 38591)
-- Name: car_plugin car_plugin_entidad_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_plugin
    ADD CONSTRAINT car_plugin_entidad_fk FOREIGN KEY (entidad) REFERENCES public.car_entidad(id);


--
-- TOC entry 2196 (class 2606 OID 38621)
-- Name: car_tra_plugin car_plugin_traplug_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_tra_plugin
    ADD CONSTRAINT car_plugin_traplug_fk FOREIGN KEY (idplugin) REFERENCES public.car_plugin(id);


--
-- TOC entry 2191 (class 2606 OID 38596)
-- Name: car_propiedad_global car_propglob_entidad_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_propiedad_global
    ADD CONSTRAINT car_propglob_entidad_fk FOREIGN KEY (entidad) REFERENCES public.car_entidad(id);


--
-- TOC entry 2192 (class 2606 OID 38601)
-- Name: car_propiedad_plugin car_propplug_plugin_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_propiedad_plugin
    ADD CONSTRAINT car_propplug_plugin_fk FOREIGN KEY (plugin) REFERENCES public.car_plugin(id);


--
-- TOC entry 2197 (class 2606 OID 38626)
-- Name: car_usuario car_usuario_entidad_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_usuario
    ADD CONSTRAINT car_usuario_entidad_fk FOREIGN KEY (ultima_entidad) REFERENCES public.car_entidad(id);


--
-- TOC entry 2198 (class 2606 OID 38631)
-- Name: car_usuarioentidad car_usuent_entidad_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_usuarioentidad
    ADD CONSTRAINT car_usuent_entidad_fk FOREIGN KEY (entidad) REFERENCES public.car_entidad(id);


--
-- TOC entry 2199 (class 2606 OID 38636)
-- Name: car_usuarioentidad car_usuent_usuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car_usuarioentidad
    ADD CONSTRAINT car_usuent_usuario_fk FOREIGN KEY (usuario) REFERENCES public.car_usuario(id);


-- Completed on 2020-06-15 12:45:51

--
-- PostgreSQL database dump complete
--

