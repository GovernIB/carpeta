import React from 'react';
import { withTranslation } from 'react-i18next';


function Registres ({ t }) {

	const registre = '8892020';
	
	$(document).ready(function () {
	  $('#dataTable_paginate').DataTable();
	  $('.dataTables_length').addClass('bs-select');
	});

    return (
      <div className="container-contenido">
        <div className="infoNoMenu">
          <p className="titol h2">{ t('registresTitol') }</p>

          <div className="col-md-12 border-0 float-left p-0">
              <p className="lh15">{ t('registresDescripcio') } </p>

              <div className="card-body tablaRegistros">

                <div className="mt-5 table-responsive">

                        <table id="dataTable_paginate" className="table table-striped table-bordered table-hover">
                            <thead className="table-success verd-carpeta">
                            <tr>
                                <th scope="col">{ t('registresNumero') }</th>
                                <th scope="col">{ t('registresData') }</th>
                                <th scope="col">{ t('registresExtracte') }</th>
                                <th scope="col" className="quitarMovil">{ t('registresOrganisme') }</th>
                            </tr>
                            </thead>
                            <tbody>
                                <tr className="clickable-row" data-target="" data-href="javascript:newDetallRegistre('contingut', '8892020');">
                                    <td>
                                        <label class="ponerMovil" data-toggle="tooltip" data-placement="top" title="L17/889-2020">
                                                <a href="javascript:newDetallRegistre('contingut', '8892020');">L17/889-2020</a>
                                        </label>
                                        <p class="quitarMovil mb-0 mt-0"><a href="javascript:newDetallRegistre('contingut', '8892020');" rel="noopener noreferrer">L17/889-2020</a></p>
                                    </td>
                                    <td>12/02/20</td>
                                    <td>Registre fet per...</td>
                                    <td>Conselleria de Salut</td>
                                </tr>
								<tr className="clickable-row" data-target="_blank" data-href="goTo('detallRegistre')">
                                    <td>
                                        <label class="ponerMovil" data-toggle="tooltip" data-placement="top" title="L17/901-2020">
                                                <a href="javascript:newDetallRegistre('contingut', '8892020');">L17/901-2020</a>
                                        </label>
                                        <p class="quitarMovil mb-0 mt-0"><a href="javascript:newDetallRegistre('contingut', '889242');" rel="noopener noreferrer">L17/901-2020</a></p>
                                    </td>
                                    <td>12/02/20</td>
                                    <td>Registre fet per...</td>
                                    <td>Conselleria de Joventut</td>
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

export default withTranslation()(Registres);
