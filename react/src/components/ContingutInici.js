import React, { Component, Suspense } from 'react';
import { withTranslation } from 'react-i18next';
import claveImg from '../assets/images/solicitar_clave_acceso_dgt.jpg';


class LegacyComponentClass extends Component{

  render(){

    const { t } = this.props;

    return (
        <div className="container-contenido">
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
                <a className="btn btn-primary carpeta-btn" href="loginib" role="button"><span className="oi oi-share" title="" aria-hidden="true"></span> { t('paginaInici.botoAccedir') }</a>
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
