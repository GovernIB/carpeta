import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import axios from "axios";
import i18n from './i18n';
import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';
import { Tooltip } from 'bootstrap';

/**
 *  @author jagarcia
 */

class DetallRegistre extends Component {

    constructor(props) {
        super(props);

        this.state = {
            isLoaded: false,
            data: null,
            error: false
        };

    }

    componentDidMount() {
        this.setState({
            ...this.state, 
            isLoaded: false
        });  
        
        const url2 = this.props.pathtoservei;

        const params = { numero : this.props.numero }

        axios.get(url2, {params: params}).then( (response) => {

            this.setState({
                ...this.state,
                isLoaded: true,
                error: false
            });
            
            if (response.data != null){

                console.log(response.data);

                this.setState({
                    ...this.state,
                    data: JSON.parse(response.data),
                    isLoaded: true
                });
            }
            

        }).catch( error => {
            console.log('Error axios', error);
            this.setState({
                ...this.state,
                error: error
            });
        } );
    }

    handlerJustificant(justificante){
        console.log('descargar justificante');
        console.log(justificante);
    }

    downloadPDF(datafile, dataName) {

        console.log("DESCARGAR DOWNLOAD PDF");

        const linkSource = `data:application/pdf;base64, ${datafile}`;
    	const downloadLink = document.createElement("a");
    	const fileName = `${dataName}`;
    	downloadLink.href = linkSource;
    	downloadLink.download = fileName;
    	
    	if (typeof window.navigator.msSaveBlob === 'function') {
            window.navigator.msSaveBlob(
              response.data,
              fileName
            );
        } else {
        	downloadLink.setAttribute('download', fileName);
        }
    	
    	downloadLink.click();
    }

    render() {

        const isLoaded = this.state.isLoaded;

        const { t } = this.props;

        let content;

        let errorContainer;

        if (!isLoaded) {
           
             content = <div  id="carregant" className="loader-container centrat ">
                        <div className="loader"/>
                    </div>;
        } else {

            if(this.state.data != null){

                
                let registre = JSON.parse(this.state.data.registre);

                const renderEstadoTooltip = () => <Tooltip id="registro_explicacion">{t('registro_explicacion_estado'+registre.estado)}</Tooltip>;

                let solicitaExponeContainer = (typeof (registre.expone) != 'undefined' || typeof(registre.solicita) != 'undefined') ?
                <div class="card border-left-carpeta shadow py-2 mb-3 alert">
                    <div class="card-body">
                        <div class="col mr-2 font15">
                                {typeof(registre.expone) != 'undefined' &&  <div><h3 class="font-weight-bold verde text-uppercase mb-3 text-center h3">EXPOSA</h3><p>{registre.expone}</p></div>}
                                {typeof(registre.solicita) != 'undefined' && <div><h3 class="font-weight-bold verde text-uppercase mb-3 text-center h3">SOLICITA</h3><p>{registre.solicita}</p></div>}
                        </div>
                    </div>
                </div> : "";

                content = 
                <>  
                    <h2 className="titol h2">{t('registro_titulo_detalle')} NUM REG {registre.numeroRegistro}</h2>
                    <div className="col-md-12 border-0 float-left p-0">
                    <div className="card-body pl-0 pr-0" style={{flexFlow: "row wrap", display: "flex"}}>
                        <div className="pri-col-deta-reg col-md-6">
                            <div className="card border-left-carpeta shadow py-2 mb-3 alert">
                                <div className="card-body">
                                    <div className="row no-gutters align-items-center">
                                        <div className="col mr-2 font15">
                                            <h3 className="font-weight-bold verde text-uppercase mb-3 text-center h3">{t('registro_entrada')}</h3>
                                            <dl className="row">
                                                <dt className="col-sm-3 pb-2">{t('registro_fecha')}</dt>
                                                <dd className="col-sm-7">{registre.fechaRegistro}</dd>

                                                <dt className="col-sm-3 pb-2">{t('registro_numero')}</dt>
                                                <dd className="col-sm-7">{registre.numeroRegistro}</dd>

                                                <dt className="col-sm-3 pb-2">{t('registro_oficina')}</dt>
                                                <dd className="col-sm-7">{registre.denominacionOficinaOrigen}</dd>
                                                
                                                <dt className="col-sm-3 pb-2">{t('registro_destinatario')}</dt>
                                                <dd className="col-sm-7">{registre.denominacionDestino}</dd>

                                                <dt className="col-sm-3 pb-2">{t('registro_tipo_doc')}</dt>
                                                <dd className="col-sm-7">{registre.tipoDocumetacionFisica}</dd>

                                                <dt className="col-sm-3 pb-2">{t('registro_extracto')}</dt>
                                                <dd className="col-sm-7">{registre.extracto}</dd>

                                                <dt className="col-sm-3 pb-2">{t('carpeta_idioma')}</dt>
                                                <dd className="col-sm-7">{t('registro_idioma_' + registre.idioma)}</dd>

                                                <dt className="col-sm-3 pb-2">{t('registro_estado')}</dt>
                                                <dd className="col-sm-7">{registre.estado}</dd>

                                                <dt className="col-sm-3 pb-2">{t('registro_descripcion_estado')}</dt>
                                                <dd className="col-sm-7">
                                                    {t('registro_estado_' + registre.estado)} 
                                                </dd>

                                                <dt className="col-sm-3 pb-2">{t('registro_presencial')}</dt>
                                                <dd className="col-sm-7">{ registre.presencial === 'true' ? 'Sí' :  'No'}</dd>

                                                <dt className="col-sm-3 pb-2">{t('registro_codigoSia')}</dt>
                                                <dd className="col-sm-7">{registre.codigoSia}</dd>

                                            </dl>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div className="card border-left-carpeta shadow py-2 mb-3 alert">
                                <div className="card-body">
                                    <div className="row no-gutters">
                                        <div className="col mr-2 font15">
                                            <h3 className="font-weight-bold verde text-uppercase mb-3 text-center h3">{t('registro_justificante')}</h3>
                                            {typeof(registre.justificante) != 'undefined' && registre.justificante.confidencial == 'false' && <Button onClick={() => this.handlerJustificant(registre)}>{t('carpeta_descargar')}</Button>}
                                            
                                            {this.state.data.justificanteUrl != '' && <Button onClick={() => { window.open(this.state.data.justificanteUrl); }}>{t('carpeta_descargar')}</Button>}

                                            {this.state.data.justificantSenseGenerar != '' && <Button onClick={() => { window.open(this.state.data.urlGeneracioJustificant); }}>{t('carpeta_descargar')}</Button>}

                                            {this.state.data.justificantId != '' && this.state.data.justificantData != '' && <Button onClick={downloadPDF(this.state.data.justificantData, this.state.data.justificantFileName)}>{t('carpeta_descargar')}</Button>}

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div className="seg-col-deta-reg col-md-5 pr-0">
                            <div className="card border-left-carpeta shadow py-2 mb-3 alert">
                                <div className="card-body">
                                    <div className="row no-gutters align-items-center">
                                        <div className="col mr-2 font15">
                                            <h3 className="font-weight-bold verde text-uppercase mb-3 text-center h3">{t('registro_interesados')}</h3>
                                            <div className="row no-gutters align-items-center">
                                                <Table hover className="table-sm">
                                                    <thead>
                                                        <tr>
                                                            <th scope="col">#</th>
                                                            <th scope="col">{t('registro_interesado_nombre')}</th>
                                                            <th scope="col">{t('registro_interesado_documento')}</th>
                                                            <th scope="col">{t('registro_interesado_tipo')}</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        { registre.interesados.map( (item) => <tr>
                                                            <td>#</td>
                                                            <td>{item.interesado.apellido1} {item.interesado.apellido2}</td>
                                                            <td>{item.interesado.documento}</td>
                                                            <td>{item.interesado.tipoInteresado}</td>
                                                        </tr> ) }
                                                    </tbody>
                                                </Table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                </div>

                                <div className="card border-left-carpeta shadow py-2 mb-3 alert">
                                    <div className="card-body">
                                        <div className="row no-gutters align-items-center">
                                            <div className="col mr-2 font15">
                                                <h3 className="font-weight-bold verde text-uppercase mb-3 text-center h3">{t('registro_anexos')}</h3>
                                                <Table hover className="table-sm">
                                                    <thead>
                                                        <tr>
                                                            <th scope="col">#</th>
                                                            <th scope="col">{t('registro_anexo_name')}</th>
                                                            <th scope="col">{t('registro_anexo_validezdocumento')}</th>
                                                            <th scope="col">{t('registro_anexo_file')}</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        { registre.anexos.map( (item) => <tr>
                                                            <td>#</td>
                                                            <td>{item.name}</td>
                                                            <td>{item.validezDocumento}</td>
                                                            <td>{item.condidencial === 'false' ? <Button className="d-sm-inline-block btn btn-sm btn-danger shadow-sm"></Button> : t('registro_anexo_nodisponible')}</td>
                                                        </tr> ) }
                                                    </tbody>
                                                </Table>
                                            </div>
                                        </div>
                                    </div>
                            </div>

                            {solicitaExponeContainer}

                        </div>
                    </div>
                </div>

                <div className="col-md-12 border-0 float-left p-0" id="botoTornarDiscapacidad" style={{ marginTop: '20px' }}>
                    <button type="button" data-toggle="modal" onClick={() => {
                        window.location.href = sessionStorage.getItem("pagTornar"); sessionStorage.setItem("pagTornar", sessionStorage.getItem("contextPath"))
                    }} className="botoSuport" tabIndex="520" aria-labelledby="botoTornarDiscapacidad">{t('reprendreTornar')}</button>
                </div>

                </>

            } else {
                content = <></>
            }

            errorContainer = <div className="alert alert-danger hide" role="alert">{this.state.error}</div>;
        }
        
        return <>
            <div className="infoNoMenu" style={{marginTop: '10px'}}>
                {this.state.error && errorContainer}
                {content}
            </div>
        </>;    
    }
}

export default withTranslation()(DetallRegistre);