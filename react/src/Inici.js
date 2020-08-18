import React from 'react';
import { withTranslation } from 'react-i18next';


function Inici ({ t }) {

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

export default withTranslation()(Inici);
