import React, { Component, Suspense } from 'react';
import { withTranslation } from 'react-i18next';


class LegacyComponentClass extends Component{

  render(){

    const { t } = this.props;

    return (
        <div className="container-contenido">

          	<div className="infoNoMenu">
      					<p className="titol h2">{ t('notificacions.titol') }</p>

      					<div className="col-md-12 border-0 pl-0 pr-0">

                    <p className="lh15">{ t('notificacions.descripcio') }</p>

                    <div className="card-body imc--llista--capses">

                        <div className="row mb-5">

                          <div className="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                              <button className="card col-md-10 align-items-lg-center imc--capsa" onclick="goTo()">
                                  <span className="oi oi-envelope-closed h3 mt-2 mb-2" title={ t('notificacions.notificacions') } alt={ t('notificacions.notificacions') } aria-hidden="true">
                                      <span className="card-title titol pl-1">{ t('notificacions.notificacions') }</span>
                                  </span>
                                  <span className="card-text mb-3">{ t('notificacions.notdescripcio') }</span>
                              </button>
                          </div>

                            <div className="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                <button className="card col-md-10 align-items-lg-center imc--capsa" onclick="goTo()">
                                    <span className="oi oi-box h3 mt-2 mb-2" title={ t('notificacions.altres') } alt={ t('notificacions.altres') } aria-hidden="true">
                                        <span className="card-title titol pl-1">{ t('notificacions.altres') }</span>
                                    </span>
                                    <span className="card-text mb-3">{ t('notificacions.altdescripcio') }</span>
                                </button>
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

export default function Notificacions() {
  return (
    <Suspense fallback="loading">
      <Contingut />
    </Suspense>
  );
}
