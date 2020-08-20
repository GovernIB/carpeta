import React from 'react';
import { withTranslation } from 'react-i18next';


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

		return (
			<div className="container-contenido">

				<div className="row mr-0 ml-0">
					<div className="infoNoMenu">
						<p className="titol h2">{ t('paginaIniciTitolPrivat') }</p>

							<div className="col-md-12 border-0 pl-0 pr-0">

							  <p className="lh15">{ t('paginaIniciIntroduccioPrivat') }</p>

							  <div className="card-body imc--llista--capses">

								  <div className="row mb-5">

									  <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12">
										  <button className="card col-md-10 align-items-lg-center" onClick={event =>  window.location.href="javascript:newTramitsPendents('contingut', '');"}>
											  <span className="oi oi-document h3 mt-2 mb-2" title={ t('paginaIniciDescripcioTramits') } alt={ t('paginaIniciDescripcioTramits') } aria-hidden="true">
												  <span className="card-title titol pl-1">{ t('paginaIniciLlistaTramits') }</span>
											  </span>
											  <span className="card-text mb-3">{ t('paginaIniciDescripcioTramits') }</span>
										  </button>
									  </div>

									  <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12">
										  <button className="card col-md-10 align-items-lg-center" onClick={event =>  window.location.href="javascript:newRegistres('contingut', '');"}>
											  <span className="oi oi-book h3 mt-2 mb-2" title={ t('paginaIniciLlistaRegistres') } alt={ t('paginaIniciLlistaRegistres') } aria-hidden="true">
												  <span className="card-title titol pl-1">{ t('paginaIniciLlistaRegistres') }</span>
											  </span>
											  <span className="card-text mb-3">{ t('paginaIniciDescripcioRegistres') }</span>
										  </button>
									  </div>


									  <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12">
										  <button className="card col-md-10 align-items-lg-center" onClick={event =>  window.location.href="javascript:newNotificacions('contingut', '');"}>
											  <span className="oi oi-envelope-closed h3 mt-2 mb-2" title={ t('paginaIniciNotificacions') } alt={ t('paginaIniciNotificacions') } aria-hidden="true">
												  <span className="card-title titol pl-1">{ t('paginaIniciNotificacions') }</span>
											  </span>
											  <span className="card-text mb-3">{ t('paginaIniciDescripcioNotificacions') }</span>
										  </button>
									  </div>

								  </div>

								  <div className="row">

									  <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12">
										  <button className="card col-md-10 align-items-lg-center" onClick={event =>  window.location.href="javascript:newDadesPersonals('contingut', '');"}>
											  <span className="oi oi-folder h3 mt-2 mb-2" title={ t('paginaIniciDadesPersonals') } alt={ t('paginaIniciDadesPersonals') } aria-hidden="true">
												  <span className="card-title titol pl-1">{ t('paginaIniciDadesPersonals') }</span>
											  </span>
											  <span className="card-text mb-3">{ t('paginaIniciDescripcioDades') }</span>
										  </button>
									  </div>

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
