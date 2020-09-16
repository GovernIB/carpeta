import React, { Component, Suspense } from 'react';
import { withTranslation } from 'react-i18next';
import i18n from 'i18next';


function MapaWeb ({ t }) {
	
	var autenticat = sessionStorage.getItem('autenticat');
	const plugins = JSON.parse(sessionStorage.getItem('plugins'));
	var plug;

	var informacio;
	var tramits;
	var registres;
	var notificacions;
	var altres;
	var dades;
	var logoClau;
	
	if (autenticat === '1'){
		logoClau = '';
		informacio = <a href="javascript:newInici('contingut', '1');">{ t('mapaWebInformacio') }</a>;
		
		if (i18n.language === 'ca') {
			plug = plugins.map(s => (<p className="lh15 upper"><a href={"javascript:newPlugin('contingut', '1', '" + s.pluginID + "');"}>{s.nomCa}</a></p>));
		}
		if (i18n.language === 'es') {
		  plug = plugins.map(s => (<p className="lh15 upper"><a href={"javascript:newPlugin('contingut', '1', '" + s.pluginID + "');"}>{s.nomEs}</a></p>));
		}
		dades = <a href="javascript:newDadesPersonals('contingut', '1');">{ t('mapaWebDades') }</a>;
	} 
	if(autenticat === '0'){
		logoClau = <span class="oi oi-lock-locked colorClave" title={ t('mapaWebClave') }></span>;
		informacio = <a href="javascript:newInici('contingut', '0');">{ t('mapaWebInformacio') }</a>;
		
		if (i18n.language === 'ca') {
			plug = plugins.map(s => (<p className="lh15 upper"><a href="/carpetafront/login">{s.nomCa}</a>    {logoClau}</p>));
		}
		if (i18n.language === 'es') {
		  plug = plugins.map(s => (<p className="lh15 upper"><a href="/carpetafront/login">{s.nomEs}</a>    {logoClau}</p>));
		}
		dades = <a href="/carpetafront/login">{ t('mapaWebDades') }</a>;
	}

    return (
        <div className="container-contenido">

          	<div className="infoNoMenu">
      			<p className="titol h2">{ t('mapaWebTitol') }</p>

      				<div className="col-md-12 border-0 pl-0 pr-0">

                    <p className="lh15">{ t('mapaWebDescripcio') }</p>
					
					<div className="card">
						<ul className="list-group list-group-flush">
						  <li className="list-group-item">
							<p className="lh15 upper">{informacio}</p>
						  </li>
						  <li className="list-group-item">
							<p className="titol h5 upper">{ t('mapaWebPlugins') }</p>
							{plug}
						  </li>
						  <li className="list-group-item">
							<p className="lh15 upper">{dades}    {logoClau}</p>
						  </li>
						</ul>
					  </div>

      			</div>

              </div>

		</div>
    );
}

export default withTranslation()(MapaWeb);