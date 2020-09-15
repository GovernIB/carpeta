import React, { Component, Suspense } from 'react';
import { withTranslation } from 'react-i18next';


function MapaWeb ({ t }) {
	
	var autenticat = sessionStorage.getItem('autenticat');

	var informacio;
	var tramits;
	var registres;
	var notificacions;
	var altres;
	var dades;
	var logoClau;
	
	if (autenticat === '1'){
		informacio = <a href="javascript:newInici('contingut', '1');">{ t('mapaWebInformacio') }</a>;
		tramits = <a href="javascript:newTramitsPendents('contingut', '1');">{ t('mapaWebTramits') }</a>;
		registres = <a href="javascript:newRegistres('contingut', '1');">{ t('mapaWebRegistres') }</a>;
		notificacions = <a href="javascript:newNotificacions('contingut', '1');">{ t('notificacionsNotificacions') }</a>;
		altres = <a href="javascript:newNotificacions('contingut', '1');">{ t('notificacionsAltres') }</a>;
		dades = <a href="javascript:newDadesPersonals('contingut', '1');">{ t('mapaWebDades') }</a>;
		logoClau = '';
	} 
	if(autenticat === '0'){
		informacio = <a href="javascript:newInici('contingut', '0');">{ t('mapaWebInformacio') }</a>;
		tramits = <a href="/carpetafront/login">{ t('mapaWebTramits') }</a>;
		registres = <a href="/carpetafront/login">{ t('mapaWebRegistres') }</a>;
		notificacions = <a href="/carpetafront/login">{ t('notificacionsNotificacions') }</a>;
		altres = <a href="/carpetafront/login">{ t('notificacionsAltres') }</a>;
		dades = <a href="/carpetafront/login">{ t('mapaWebDades') }</a>;
		logoClau = <span class="oi oi-lock-locked colorClave" title={ t('mapaWebClave') }></span>;
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
							<p className="titol h5 upper">{ t('mapaWebTramits') }</p>
							<p className="lh15 upper">{tramits}    {logoClau}</p>
						  </li>
						  <li className="list-group-item">
							<p className="titol h5 upper">{ t('mapaWebRegistres') }</p>
							<p className="lh15">{registres}    {logoClau}</p>
						  </li>
						  <li className="list-group-item">
							<p className="titol h5 upper">{ t('notificacionsTitol') }</p>
							<p className="lh15 upper">{notificacions}    {logoClau}</p>
							<p className="lh15 upper">{altres}    {logoClau}</p>
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