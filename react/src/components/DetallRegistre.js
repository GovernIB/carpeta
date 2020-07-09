import React, { Component, Suspense } from 'react';
import { withTranslation } from 'react-i18next';


function obtenerValorParametro(sParametroNombre) {
var sPaginaURL = window.location.search.substring(1);
 var sURLVariables = sPaginaURL.split('&');
  for (var i = 0; i < sURLVariables.length; i++) {
    var sParametro = sURLVariables[i].split('=');
    if (sParametro[0] === sParametroNombre) {
      return sParametro[1];
    }
  }
 return null;
}



class LegacyComponentClass extends Component{

  render(){

    let registre = obtenerValorParametro('id');

    const { t } = this.props;

    return (
      <div className="container-contenido">
        <div className="infoNoMenu">
          <p className="titol h2">{ t('detallregistre.titol') } {registre}</p>

          <div className="col-md-12 border-0 float-left p-0">

          <div className="card-body">

                  <div className="row">

                      <div className="pri-col-deta-reg col-md-5">

                          <div className="card border-left-carpeta shadow py-2 mb-3">

                              <div className="card-body">
                                  <div className="row no-gutters align-items-center">
                                      <div className="col mr-2">
                                          <div className="font-weight-bold verde text-uppercase mb-3 text-center h3">{ t('detallregistre.entrada') }</div>

                                          <dl className="row">
                                              <dt className="col-sm-3 pb-2">{ t('detallregistre.data') }</dt>
                                              <dd className="col-sm-7">12/12/19</dd>

                                              <dt className="col-sm-3 pb-2">{ t('detallregistre.numero') }</dt>
                                              <dd className="col-sm-7">L19/2345</dd>

                                              <dt className="col-sm-3 pb-2">{ t('detallregistre.oficina') }</dt>
                                              <dd className="col-sm-7">Oficina prova</dd>

                                              <dt className="col-sm-3 pb-2">{ t('detallregistre.organisme') }</dt>
                                              <dd className="col-sm-7">Organisme prova</dd>

                                              <dt className="col-sm-3 pb-2">{ t('detallregistre.tipus') }</dt>
                                              <dd className="col-sm-7">Documentació física</dd>

                                              <dt className="col-sm-3 pb-2">{ t('detallregistre.extracte') }</dt>
                                              <dd className="col-sm-7">Asiento registral ...</dd>

                                              <dt className="col-sm-3 pb-2">{ t('detallregistre.idioma') }</dt>
                                              <dd className="col-sm-7">Català</dd>

                                              <dt className="col-sm-3 pb-2">{ t('detallregistre.presencial') }</dt>
                                              <dd className="col-sm-7">Si</dd>

                                              <dt className="col-sm-3 pb-2">{ t('detallregistre.sia') }</dt>
                                              <dd className="col-sm-7">12312313</dd>

                                              <dt className="col-sm-3 pb-2">{ t('detallregistre.codi') }</dt>
                                              <dd className="col-sm-7">2323</dd>

                                              <dt className="col-sm-3 pb-2">{ t('detallregistre.refexterna') }</dt>
                                              <dd className="col-sm-7">7979</dd>

                                              <dt className="col-sm-3 pb-2">{ t('detallregistre.expedient') }</dt>
                                              <dd className="col-sm-7">87686</dd>

                                              <dt className="col-sm-3 pb-2">{ t('detallregistre.transport') }</dt>
                                              <dd className="col-sm-7">En mà</dd>

                                              <dt className="col-sm-3 pb-2">{ t('detallregistre.numtransport') }</dt>
                                              <dd className="col-sm-7">4545</dd>

                                              <dt className="col-sm-3 pb-2">{ t('detallregistre.observacions') }</dt>
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
                                          <div className="font-weight-bold verde text-uppercase mb-3 text-center h3">{ t('detallregistre.justificant') }</div>

                                              <div className="text-center">
                                                  <button type="button" className="d-sm-inline-block btn btn-sm btn-danger shadow-sm" onClick={event =>  window.location.href='/descarregar'}><span className="oi oi-data-transfer-download" title="" alt="" aria-hidden="true"></span> { t('detallregistre.descarregar') }</button>
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
                                          <div className="font-weight-bold verde text-uppercase mb-3 text-center h3">{ t('detallregistre.interessats') }</div>
                                          <div className="row no-gutters align-items-center">

                                              <table className="table table-hover">
                                                  <thead>
                                                      <tr>
                                                          <th scope="col">#</th>
                                                          <th scope="col">{ t('detallregistre.nom') }</th>
                                                          <th scope="col">{ t('detallregistre.document') }</th>
                                                          <th scope="col">{ t('detallregistre.tipus') }</th>
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
                                          <div className="font-weight-bold verde text-uppercase mb-3 text-center h3">{ t('detallregistre.annexos') }</div>

                                                  <table className="table table-hover">
                                                      <thead>
                                                          <tr>
                                                              <th scope="col">#</th>
                                                              <th scope="col">{ t('detallregistre.titolannexe') }</th>
                                                              <th scope="col">{ t('detallregistre.mime') }</th>
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

                                                  <div className="font-weight-bold verde text-uppercase mb-3 text-center">{ t('detallregistre.exposa') }</div>
                                                  <div className="row no-gutters text-justify mb-4">Estant en una wer wer wer were r</div>

                                                  <div className="font-weight-bold verde text-uppercase mb-3 text-center">{ t('detallregistre.solicita') }</div>
                                                  <div className="row no-gutters text-justify">rwer wer wer we rwer we rwere  r</div>

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
const Contingut = withTranslation()(LegacyComponentClass)

export default function DetallRegistre() {
  return (
    <Suspense fallback="loading">
      <Contingut />
    </Suspense>
  );
}
