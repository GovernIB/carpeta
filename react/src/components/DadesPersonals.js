import React, { Component, Suspense } from 'react';
import { withTranslation } from 'react-i18next';


class LegacyComponentClass extends Component{

  render(){

    const { t } = this.props;

    return (
      <div className="container-contenido">
        <div className="infoNoMenu">
          <p className="titol h2">{ t('dadespersonals.titol') }</p>

          <div className="col-md-12 border-0 float-left p-0">
              <p className="lh15">{ t('dadespersonals.descripcio') } </p>

              <div class="card">
                <ul class="list-group list-group-flush">
                  <li class="list-group-item"><p className="titol h5">{ t('dadespersonals.nom') }</p><p className="lh15">xxx</p></li>
                  <li class="list-group-item"><p className="titol h5">{ t('dadespersonals.llinatge1') }</p><p className="lh15">xxx</p></li>
                  <li class="list-group-item"><p className="titol h5">{ t('dadespersonals.llinatge2') }</p><p className="lh15">xxx</p></li>
                  <li class="list-group-item"><p className="titol h5">{ t('dadespersonals.dni') }</p><p className="lh15">xxx</p></li>
                  <li class="list-group-item"><p className="titol h5">{ t('dadespersonals.metode') }</p><p className="lh15">xxx</p></li>
                </ul>
              </div>

          </div>

        </div>
      </div>
    );
  }
}
const Contingut = withTranslation()(LegacyComponentClass)

export default function DadesPersonals() {
  return (
    <Suspense fallback="loading">
      <Contingut />
    </Suspense>
  );
}
