import React, { Component, Suspense } from 'react';
import { withTranslation } from 'react-i18next';


class LegacyComponentClass extends Component{

  render(){

    const { t } = this.props;

    return (
      <div className="container-contenido">
        <div className="infoNoMenu">
          <p className="titol h2">{ t('tramitspendents.titol') }</p>

          <div className="col-md-12 border-0 float-left p-0">
              <p className="lh15">{ t('tramitspendents.descripcio') } </p>

              <div className="card-body tablaRegistros">

                <form method="post" modelAttribute="fechaBusqueda">
                    <div className="row">
                        <div className="col-md-5">
                            <div className="form-group">
                                <label path="fechaInicio" placeholder="dd/mm/yyyy">{ t('tramitspendents.dataInici') }</label>
                                <div className="input-group">
                                    <input path="fechaInicio" maxlength="10" className="form-control form-control-sm" required="required"/><span class="input-group-text"><span className="oi oi-calendar"></span></span>
                                </div>
                            </div>
                        </div>
                        <div className="col-md-5">
                            <div className="form-group">
                                <label path="fechaFin" placeholder="dd/mm/yyyy">{ t('tramitspendents.dataFi') }</label>
                                <div className="input-group">
                                    <input path="fechaFin" maxlength="10" className="form-control form-control-sm" required="required"/><span className="input-group-text"><span className="oi oi-calendar"></span></span >
                                </div>
                            </div>
                        </div>
                    </div>
                    <button type="submit" className="btn btn-primary carpeta-btn">{ t('tramitspendents.cercar') }</button>
                </form>


                <div className="mt-5">

                        <p className="card-text mb-5">{ t('tramitspendents.continuar') }</p>

                        <table id="dataTable_paginate" className="table table-striped table-bordered table-hover">
                            <thead className="table-success verd-carpeta">
                            <tr>
                                <th>{ t('tramitspendents.tramit') } </th>
                                <th>{ t('tramitspendents.dataInici') } </th>
                                <th>{ t('tramitspendents.darrer') } </th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr className="clickable-row" data-target="_blank" data-href="">
                                    <td>descripcio</td>
                                    <td>12/02/20</td>
                                    <td>15/02/20</td>
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

export default function TramitsPendents() {
  return (
    <Suspense fallback="loading">
      <Contingut />
    </Suspense>
  );
}
