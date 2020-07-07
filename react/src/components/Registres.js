import React, { Component, Suspense } from 'react';
import { withTranslation } from 'react-i18next';


class LegacyComponentClass extends Component{

  render(){

    const { t } = this.props;

    return (
      <div className="container-contenido">
        <div className="infoNoMenu">
          <p className="titol h2">{ t('registres.titol') }</p>

          <div className="col-md-12 border-0 float-left p-0">
              <p className="lh15">{ t('registres.descripcio') } </p>

              <div className="card-body tablaRegistros">

                <div className="mt-5">

                        <table id="dataTable_paginate" className="table table-striped table-bordered table-hover">
                            <thead className="table-success verd-carpeta">
                            <tr>
                                <th scope="col">{ t('registres.numero') }</th>
                                <th scope="col">{ t('registres.data') }</th>
                                <th scope="col">{ t('registres.extracte') }</th>
                                <th scope="col" className="quitarMovil">{ t('registres.organisme') }</th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr className="clickable-row" data-target="_blank" data-href="goTo('detallRegistre')">
                                    <td>
                                        <label class="ponerMovil" data-toggle="tooltip" data-placement="top" title="L17/889-2020">
                                                <a href="detallRegistre" target="_blank">L17/889-2020</a>
                                        </label>
                                        <p class="quitarMovil mb-0 mt-0"><a href="detallRegistre" target="_blank">L17/889-2020</a></p>
                                    </td>
                                    <td>12/02/20</td>
                                    <td>Registre fet per...</td>
                                    <td>Conselleria de Salut</td>
                                </tr>
                            </tbody>
                        </table>

                 </div>
            </div>

          </div>

        </div>
      </div>
    );
  }
}
const Contingut = withTranslation()(LegacyComponentClass)

export default function Registres() {
  return (
    <Suspense fallback="loading">
      <Contingut />
    </Suspense>
  );
}
