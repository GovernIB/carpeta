
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

