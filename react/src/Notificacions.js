import React, { Component, Suspense } from 'react';
import { withTranslation } from 'react-i18next';


function Notificacions ({ t }) {

    return (
        <div className="container-contenido">

          	<div className="infoNoMenu">
      					<p className="titol h2">{ t('notificacionsTitol') }</p>

      					<div className="col-md-12 border-0 pl-0 pr-0">

                    <p className="lh15">{ t('notificacionsDescripcio') }</p>

                    <div className="card-body imc--llista--capses">

                        <div className="row mb-5">

                          <div className="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                              <button className="card col-md-10 align-items-lg-center" onclick="goTo()">
                                  <span className="oi oi-envelope-closed h3 mt-2 mb-2" title={ t('notificacionsNotificacions') } alt={ t('notificacionsNotificacions') } aria-hidden="true">
                                      <span className="card-title titol pl-1">{ t('notificacionsNotificacions') }</span>
                                  </span>
                                  <span className="card-text mb-3">{ t('notificacionsNotdescripcio') }</span>
                              </button>
                          </div>

                            <div className="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                <button className="card col-md-10 align-items-lg-center" onclick="goTo()">
                                    <span className="oi oi-box h3 mt-2 mb-2" title={ t('notificacionsAltres') } alt={ t('notificacionsAltres') } aria-hidden="true">
                                        <span className="card-title titol pl-1">{ t('notificacionsAltres') }</span>
                                    </span>
                                    <span className="card-text mb-3">{ t('notificacionsAltdescripcio') }</span>
                                </button>
                            </div>

                        </div>


                    </div>

      					</div>

              </div>

				</div>
    );
}

export default withTranslation()(Notificacions);