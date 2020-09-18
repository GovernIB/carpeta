import React from 'react';
import { withTranslation } from 'react-i18next';
import i18n from 'i18next';


function Inici ({ t , autenticat}) {
	
	if(autenticat === '0'){

		return (
			<div className="container-contenido">

				<div className="row mr-0 ml-0">
						  <div className="infoNoMenu">
							<p className="titol h2">{ t('paginaIniciTitol') }</p>

							<div className="col-md-5 border-0 float-left p-0">
							  <p className="lh15">{ t('paginaIniciQuefer') }</p>
							  <ul className="lh15 pl-5 pt-3">
								<li>{ t('paginaIniciQuefer1') }</li>
								<li>{ t('paginaIniciQuefer2') }</li>
								<li>{ t('paginaIniciQuefer3') }</li>
								<li>{ t('paginaIniciQuefer4') }</li>
							  </ul>

							  <div className="pt-3 imc--logoclave"></div>

							  <p className="lh15">{ t('paginaIniciClave') }</p>
							  <ul className="lh15 pl-5 pt-3">
								<li>{ t('paginaIniciClave1') }</li>
								<li>{ t('paginaIniciClave2') }</li>
								<li>{ t('paginaIniciClave3') }</li>
							  </ul>
							</div>

							<div className="col-md-5 border-0 float-right">

							  <p className="margen-top-clave pb-3">
								<a className="btn btn-primary carpeta-btn" href="login" role="button"><span className="oi oi-share" title="" aria-hidden="true"></span> { t('paginaIniciBotoAccedir') }</a>
							  </p>

							  <p className="titol h5">{ t('paginaIniciProblemes') }</p>
							  <p className="lh15">{ t('paginaIniciAjuda') }</p>
							  <ul className="lh15 pl-5 pt-3">
								<li>{ t('paginaIniciAjuda1') } <a href="http://clave.gob.es/clave_Home/clave.html" title={ t('paginaIniciAjudaClaveText') } alt={ t('paginaIniciAjudaClaveText') } target="_blank" rel="noopener noreferrer">{ t('paginaIniciAjudaClave') }</a></li>
								<li>{ t('paginaIniciAjuda2') }</li>
								<li>{ t('paginaIniciAjuda3') } <a href="https://ssweb.seap.minhap.es/ayuda/consulta/Claveciudadanos" title={ t('paginaIniciAjudaBustiaText') } alt={ t('paginaIniciAjudaBustiaText') } target="_blank" rel="noopener noreferrer">{ t('paginaIniciAjudaBustia') }</a></li>
							  </ul>

							</div>

						  </div>
				</div>

			</div>
		);
	}
	
	if(autenticat === '1'){
		
		const plugins = JSON.parse(sessionStorage.getItem('plugins'));
		

		var plug;
		
		if (i18n.language === 'ca') {
			plug = plugins.map(s => (
			  <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5">
				<button className="card col-md-10 align-items-lg-center" onClick={event =>  window.location.href="javascript:newPlugin('contingut', '1', '" + s.pluginID + "');"}>
					<span className="oi oi-document h3 mt-2 mb-2" title={s.nomCa} alt={s.nomCa} aria-hidden="true">
						<span className="card-title titol pl-1">{s.nomCa}</span>
					</span>
					<span className="card-text mb-3">{s.descripcioCa}</span>
				</button>
			  </div>
			));
		}

		if (i18n.language === 'es') {
		  plug = plugins.map(s => (
			  <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb-5">
				<button className="card col-md-10 align-items-lg-center" onClick={event =>  window.location.href="javascript:newPlugin('contingut', '1', '" + s.pluginID + "');"}>
					<span className="oi oi-document h3 mt-2 mb-2" title={s.nomEs} alt={s.nomEs} aria-hidden="true">
						<span className="card-title titol pl-1">{s.nomEs}</span>
					</span>
					<span className="card-text mb-3">{s.descripcioEs}</span>
				</button>
			  </div>
			));
		}
		

		return (
			<div className="container-contenido">

				<div className="row mr-0 ml-0">
					<div className="infoNoMenu">
						<p className="titol h2">{ t('paginaIniciTitolPrivat') }</p>

							<div className="col-md-12 border-0 pl-0 pr-0">

							  <p className="lh15">{ t('paginaIniciIntroduccioPrivat') }</p>

							  <div className="card-body imc--llista--capses">

								  <div className="row mb-0">

									{plug}
								  
								  </div>

							  </div>

						</div>

					</div>
				</div>

			</div>
		);
	}

}

export default withTranslation()(Inici);
