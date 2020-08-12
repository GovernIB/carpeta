




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

