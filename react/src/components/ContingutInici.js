import React, { Component, Suspense } from 'react';
import { withTranslation } from 'react-i18next';
import claveImg from '../assets/images/solicitar_clave_acceso_dgt.jpg';


class LegacyComponentClass extends Component{


  render(){

    const { t } = this.props;


    return (
        <div className="container-contenido">

<div className="row mr-0 ml-0">
          <div className="infoNoMenu">
            <p className="titol h2">{ t('paginaInici.titol') }</p>

            <div className="col-md-5 border-0 float-left p-0">
              <p className="lh15">{ t('paginaInici.quefer') }</p>
              <ul className="lh15 pl-5 pt-3">
                <li>{ t('paginaInici.quefer1') }</li>
                <li>{ t('paginaInici.quefer2') }</li>
                <li>{ t('paginaInici.quefer3') }</li>
                <li>{ t('paginaInici.quefer4') }</li>
              </ul>

              <p className="lh15 pt-3"><img src={claveImg} className="w-25" alt={ t('paginaInici.logoClave') } title={ t('paginaInici.logoClave') }/></p>

              <p className="lh15">{ t('paginaInici.clave') }</p>
              <ul className="lh15 pl-5 pt-3">
                <li>{ t('paginaInici.clave1') }</li>
                <li>{ t('paginaInici.clave2') }</li>
                <li>{ t('paginaInici.clave3') }</li>
              </ul>
            </div>

            <div className="col-md-5 border-0 float-right">

              <p className="margen-top-clave pb-3">
                <a className="btn btn-primary carpeta-btn" href="login" role="button"><span className="oi oi-share" title="" aria-hidden="true"></span> { t('paginaInici.botoAccedir') }</a>
              </p>

              <p className="titol h5">{ t('paginaInici.problemes') }</p>
              <p className="lh15">{ t('paginaInici.ajuda') }</p>
              <ul className="lh15 pl-5 pt-3">
                <li>{ t('paginaInici.ajuda1') } <a href="http://clave.gob.es/clave_Home/clave.html" title={ t('paginaInici.ajudaClaveText') } alt={ t('paginaInici.ajudaClaveText') } target="_blank" rel="noopener noreferrer">{ t('paginaInici.ajudaClave') }</a></li>
                <li>{ t('paginaInici.ajuda2') }</li>
                <li>{ t('paginaInici.ajuda3') } <a href="https://ssweb.seap.minhap.es/ayuda/consulta/Claveciudadanos" title={ t('paginaInici.ajudaBustiaText') } alt={ t('paginaInici.ajudaBustiaText') } target="_blank" rel="noopener noreferrer">{ t('paginaInici.ajudaBustia') }</a></li>
              </ul>

            </div>

          </div>
</div>



<div className="row mr-0 ml-0 mt-5">
				<div className="infoNoMenu">
					<p className="titol h2">{ t('paginaInici.titolPrivat') }</p>

					<div className="col-md-12 border-0 pl-0 pr-0">

              <p className="lh15">{ t('paginaInici.introduccioPrivat') }</p>

              <div className="card-body imc--llista--capses">

                  <div className="row mb-5">

                      <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                          <button className="card col-md-10 align-items-lg-center imc--capsa" onClick={event =>  window.location.href='/tramitsPendents'}>
                              <span className="oi oi-document h3 mt-2 mb-2" title={ t('paginaInici.descripcioTramits') } alt={ t('paginaInici.descripcioTramits') } aria-hidden="true">
                                  <span className="card-title titol pl-1">{ t('paginaInici.llistaTramits') }</span>
                              </span>
                              <span className="card-text mb-3">{ t('paginaInici.descripcioTramits') }</span>
                          </button>
                      </div>

                      <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                          <button className="card col-md-10 align-items-lg-center imc--capsa" onClick={event =>  window.location.href='/registres'}>
                              <span className="oi oi-book h3 mt-2 mb-2" title={ t('paginaInici.llistaRegistres') } alt={ t('paginaInici.llistaRegistres') } aria-hidden="true">
                                  <span className="card-title titol pl-1">{ t('paginaInici.llistaRegistres') }</span>
                              </span>
                              <span className="card-text mb-3">{ t('paginaInici.descripcioRegistres') }</span>
                          </button>
                      </div>


                      <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                          <button className="card col-md-10 align-items-lg-center imc--capsa" onClick={event =>  window.location.href='/notificacions'}>
                              <span className="oi oi-envelope-closed h3 mt-2 mb-2" title={ t('paginaInici.notificacions') } alt={ t('paginaInici.notificacions') } aria-hidden="true">
                                  <span className="card-title titol pl-1">{ t('paginaInici.notificacions') }</span>
                              </span>
                              <span className="card-text mb-3">{ t('paginaInici.descripcioNotificacions') }</span>
                          </button>
                      </div>

                  </div>

                  <div className="row">

                      <div className="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                          <button className="card col-md-10 align-items-lg-center imc--capsa" onClick={event =>  window.location.href='/dadesPersonals'}>
                              <span className="oi oi-folder h3 mt-2 mb-2" title={ t('paginaInici.dadesPersonals') } alt={ t('paginaInici.dadesPersonals') } aria-hidden="true">
                                  <span className="card-title titol pl-1">{ t('paginaInici.dadesPersonals') }</span>
                              </span>
                              <span className="card-text mb-3">{ t('paginaInici.descripcioDades') }</span>
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
const Contingut = withTranslation()(LegacyComponentClass)

export default function ContingutInici() {
  return (
    <Suspense fallback="loading">
      <Contingut />
    </Suspense>
  );
}
