import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';

class DetallRegistre extends Component {

    render() {

        const {t} = this.props;
        const {id} = this.props.id;

        return (
            <div className="container-contenido">
                <div className="infoNoMenu">
                    <p className="titol h2">{t('detallregistreTitol')} NUM REG {id}</p>

                    <div className="col-md-12 border-0 float-left p-0">

                        <div className="card-body">

                            <div className="row">

                                <div className="pri-col-deta-reg col-md-5">

                                    <div className="card border-left-carpeta shadow py-2 mb-3">

                                        <div className="card-body">
                                            <div className="row no-gutters align-items-center">
                                                <div className="col mr-2">
                                                    <div
                                                        className="font-weight-bold verde text-uppercase mb-3 text-center h3">{t('detallregistreEntrada')}</div>

                                                    <dl className="row">
                                                        <dt className="col-sm-3 pb-2">{t('detallregistreData')}</dt>
                                                        <dd className="col-sm-7">12/12/19</dd>

                                                        <dt className="col-sm-3 pb-2">{t('detallregistreNumero')}</dt>
                                                        <dd className="col-sm-7">L19/2345</dd>

                                                        <dt className="col-sm-3 pb-2">{t('detallregistreOficina')}</dt>
                                                        <dd className="col-sm-7">Oficina prova</dd>

                                                        <dt className="col-sm-3 pb-2">{t('detallregistreOrganisme')}</dt>
                                                        <dd className="col-sm-7">Organisme prova</dd>

                                                        <dt className="col-sm-3 pb-2">{t('detallregistreTipus')}</dt>
                                                        <dd className="col-sm-7">Documentació física</dd>

                                                        <dt className="col-sm-3 pb-2">{t('detallregistreExtracte')}</dt>
                                                        <dd className="col-sm-7">Asiento registral ...</dd>

                                                        <dt className="col-sm-3 pb-2">{t('detallregistreIdioma')}</dt>
                                                        <dd className="col-sm-7">Català</dd>

                                                        <dt className="col-sm-3 pb-2">{t('detallregistrePresencial')}</dt>
                                                        <dd className="col-sm-7">Si</dd>

                                                        <dt className="col-sm-3 pb-2">{t('detallregistreSia')}</dt>
                                                        <dd className="col-sm-7">12312313</dd>

                                                        <dt className="col-sm-3 pb-2">{t('detallregistreCodi')}</dt>
                                                        <dd className="col-sm-7">2323</dd>

                                                        <dt className="col-sm-3 pb-2">{t('detallregistreRefexterna')}</dt>
                                                        <dd className="col-sm-7">7979</dd>

                                                        <dt className="col-sm-3 pb-2">{t('detallregistreExpedient')}</dt>
                                                        <dd className="col-sm-7">87686</dd>

                                                        <dt className="col-sm-3 pb-2">{t('detallregistreTransport')}</dt>
                                                        <dd className="col-sm-7">En mà</dd>

                                                        <dt className="col-sm-3 pb-2">{t('detallregistreNumtransport')}</dt>
                                                        <dd className="col-sm-7">4545</dd>

                                                        <dt className="col-sm-3 pb-2">{t('detallregistreObservacions')}</dt>
                                                        <dd className="col-sm-7">Observacions del registre</dd>

                                                    </dl>

                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div className="card border-left-carpeta shadow py-2 mb-3">
                                        <div className="card-body">
                                            <div className="row no-gutters">
                                                <div className="col mr-2">
                                                    <div
                                                        className="font-weight-bold verde text-uppercase mb-3 text-center h3">{t('detallregistreJustificant')}</div>

                                                    <div className="text-center">
                                                        <button type="button"
                                                                className="d-sm-inline-block btn btn-sm btn-danger shadow-sm"
                                                                onClick={event => window.location.href = '/descarregar'}>
                                                            <span className="oi oi-data-transfer-download" title=""
                                                                  alt=""
                                                                  aria-hidden="true"></span> {t('detallregistreDescarregar')}
                                                        </button>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div className="seg-col-deta-reg col-md-6">

                                    <div className="card border-left-carpeta shadow py-2 mb-3">
                                        <div className="card-body">
                                            <div className="row no-gutters align-items-center">
                                                <div className="col mr-2">
                                                    <div
                                                        className="font-weight-bold verde text-uppercase mb-3 text-center h3">{t('detallregistreInteressats')}</div>
                                                    <div className="row no-gutters align-items-center">

                                                        <table className="table table-hover">
                                                            <thead>
                                                            <tr>
                                                                <th scope="col">#</th>
                                                                <th scope="col">{t('detallregistreNom')}</th>
                                                                <th scope="col">{t('detallregistreDocument')}</th>
                                                                <th scope="col">{t('detallregistreTipus')}</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <tr>
                                                                <td>1</td>
                                                                <td>
                                                                    Pere Grau Garcia
                                                                </td>
                                                                <td>44444444H</td>
                                                                <td>Persona física</td>
                                                            </tr>

                                                            <tr>
                                                                <td className="font-weight-bold">R</td>
                                                                <td>
                                                                    Antoni Frau Grau
                                                                </td>
                                                                <td>44333444Y</td>
                                                                <td>Representant</td>
                                                            </tr>

                                                            </tbody>
                                                        </table>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div className="card border-left-carpeta shadow py-2 mb-3">
                                        <div className="card-body">
                                            <div className="row no-gutters align-items-center">
                                                <div className="col mr-2">
                                                    <div
                                                        className="font-weight-bold verde text-uppercase mb-3 text-center h3">{t('detallregistreAnnexos')}</div>

                                                    <table className="table table-hover">
                                                        <thead>
                                                        <tr>
                                                            <th scope="col">#</th>
                                                            <th scope="col">{t('detallregistreTitolannexe')}</th>
                                                            <th scope="col">{t('detallregistreMime')}</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>

                                                        <tr>
                                                            <td>1</td>
                                                            <td>Informe de resultats</td>
                                                            <td>pdf/application</td>
                                                        </tr>

                                                        </tbody>
                                                    </table>

                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                    <div className="card border-left-carpeta shadow py-2 mb-3">
                                        <div className="card-body">
                                            <div className="row no-gutters align-items-center">
                                                <div className="col mr-2">

                                                    <div
                                                        className="font-weight-bold verde text-uppercase mb-3 text-center">{t('detallregistreExposa')}</div>
                                                    <div className="row no-gutters text-justify mb-4">Estant en una wer
                                                        wer wer were r
                                                    </div>

                                                    <div
                                                        className="font-weight-bold verde text-uppercase mb-3 text-center">{t('detallregistreSolicita')}</div>
                                                    <div className="row no-gutters text-justify">rwer wer wer we rwer we
                                                        rwere r
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
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

export default withTranslation()(DetallRegistre);
