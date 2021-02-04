import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';

class TramitsPendents extends Component {

    render() {

        const {t} = this.props;


        return (
            <div className="container-contenido">
                <div className="infoNoMenu">
                    <p className="titol h2">{t('tramitspendentsTitol')}</p>

                    <div className="col-md-12 border-0 float-left p-0">
                        <p className="lh15">{t('tramitspendentsDescripcio')} </p>

                        <div className="card-body tablaRegistros">

                            <form method="post" modelAttribute="fechaBusqueda">
                                <div className="row">
                                    <div className="col-md-5">
                                        <div className="form-group">
                                            <label path="fechaInicio"
                                                   placeholder="dd/mm/yyyy">{t('tramitspendentsDataInici')}</label>
                                            <div className="input-group">
                                                <input path="fechaInicio" maxlength="10"
                                                       className="form-control form-control-sm"
                                                       required="required"/><span class="input-group-text"><span
                                                className="oi oi-calendar"></span></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="col-md-5">
                                        <div className="form-group">
                                            <label path="fechaFin"
                                                   placeholder="dd/mm/yyyy">{t('tramitspendentsDataFi')}</label>
                                            <div className="input-group">
                                                <input path="fechaFin" maxlength="10"
                                                       className="form-control form-control-sm"
                                                       required="required"/><span className="input-group-text"><span
                                                className="oi oi-calendar"></span></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <button type="submit"
                                        className="btn btn-primary carpeta-btn">{t('tramitspendentsCercar')}</button>
                            </form>


                            <div className="mt-5">

                                <p className="card-text mb-5">{t('tramitspendentsContinuar')}</p>

                                <table id="dataTable_paginate"
                                       className="table table-striped table-bordered table-hover">
                                    <thead className="table-success verd-carpeta">
                                    <tr>
                                        <th>{t('tramitspendentsTramit')} </th>
                                        <th>{t('tramitspendentsDataInici')} </th>
                                        <th>{t('tramitspendentsDarrer')} </th>
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

export default withTranslation()(TramitsPendents);
