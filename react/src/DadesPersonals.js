import React, { Component, Suspense } from 'react';
import { withTranslation } from 'react-i18next';


function DadesPersonals ({ t }) {

    return (
      <div className="container-contenido">
        <div className="infoNoMenu">
          <p className="titol h2">{ t('dadespersonalsTitol') }</p>

          <div className="col-md-12 border-0 float-left p-0">
              <p className="lh15">{ t('dadespersonalsDescripcio') } </p>

              <div className="card">
                <ul className="list-group list-group-flush">
                  <li className="list-group-item"><p className="titol h5">{ t('dadespersonalsNom') }</p><p className="lh15">xxx</p></li>
                  <li className="list-group-item"><p className="titol h5">{ t('dadespersonalsLlinatge1') }</p><p className="lh15">xxx</p></li>
                  <li className="list-group-item"><p className="titol h5">{ t('dadespersonalsLlinatge2') }</p><p className="lh15">xxx</p></li>
                  <li className="list-group-item"><p className="titol h5">{ t('dadespersonalsDni') }</p><p className="lh15">xxx</p></li>
                  <li className="list-group-item"><p className="titol h5">{ t('dadespersonalsMetode') }</p><p className="lh15">xxx</p></li>
                </ul>
              </div>

          </div>

        </div>
      </div>
    );
}

export default withTranslation()(DadesPersonals);
