import React, { Component, Suspense } from 'react';
import { withTranslation } from 'react-i18next';


function MapaWeb ({ t }) {

    return (
        <div className="container-contenido">

          	<div className="infoNoMenu">
      			<p className="titol h2">{ t('mapaWebTitol') }</p>

      				<div className="col-md-12 border-0 pl-0 pr-0">

                    <p className="lh15">{ t('mapaWebDescripcio') }</p>
					
					<div className="card">
						<ul className="list-group list-group-flush">
						  <li className="list-group-item">
							<p className="lh15 upper"><a href="javascript:newInici('contingut', '');">{ t('mapaWebInformacio') }</a></p>
						  </li>
						  <li className="list-group-item">
							<p className="titol h5 upper">{ t('mapaWebTramits') }</p>
							<p className="lh15 upper"><a href="javascript:newTramitsPendents('contingut', '');">{ t('mapaWebTramits') }</a>    <span className="oi oi-lock-locked colorClave" title={ t('mapaWebClave') }></span></p>
						  </li>
						  <li className="list-group-item">
							<p className="titol h5 upper">{ t('mapaWebRegistres') }</p>
							<p className="lh15"><a href="javascript:newRegistres('contingut', '');">{ t('mapaWebRegistres') }</a>    <span className="oi oi-lock-locked colorClave" title={ t('mapaWebClave') }></span></p>
						  </li>
						  <li className="list-group-item">
							<p className="titol h5 upper">{ t('notificacionsTitol') }</p>
							<p className="lh15 upper"><a href="javascript:newNotificacions('contingut', '');">{ t('notificacionsNotificacions') }</a>    <span className="oi oi-lock-locked colorClave" title={ t('mapaWebClave') }></span></p>
							<p className="lh15 upper"><a href="javascript:newNotificacions('contingut', '');">{ t('notificacionsAltres') }</a>    <span className="oi oi-lock-locked colorClave" title={ t('mapaWebClave') }></span></p>
						  </li>
						  <li className="list-group-item">
							<p className="lh15 upper"><a href="javascript:newDadesPersonals('contingut', '');">{ t('mapaWebDades') }</a>    <span class="oi oi-lock-locked colorClave" title={ t('mapaWebClave') }></span></p>
						  </li>
						</ul>
					  </div>

      			</div>

              </div>

		</div>
    );
}

export default withTranslation()(MapaWeb);