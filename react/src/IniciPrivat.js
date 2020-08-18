import React from 'react';
import { withTranslation } from 'react-i18next';


function IniciPrivat ({ t }) {

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

export default withTranslation()(IniciPrivat);
