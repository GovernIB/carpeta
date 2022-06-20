import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import axios from "axios";
import i18n from './i18n';
import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';

/**
 *  @author jagarcia
 */

class DetallRegistre extends Component {

    constructor(props) {
        super(props);

        this.state = {
            isLoaded: false,
            data: null,
            error: ''
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
                error: ''
            });
            
            if (response.data.registre != null){
                this.setState({
                    ...this.state,
                    data: response.data,
                    isLoaded: true
                });
                // JSON.parse(response.data.registre.replace(/'/g, '"')),
            }else if (response.data.error != null){
                this.setState({
                    ...this.state,
                    isLoaded: true,
                    error: response.data.error
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

    descarregarAnnex(annexId) {
        fetch(this.state.data.urlAnnexe + annexId, { 
            method: 'GET',
            headers: { 
                'Content-Type': 'application/pdf',
            },
        })
        .then( (response) => response.blob())
        .then( (blob) => {
            const url = window.URL.createObjectURL(new Blob([blob]));
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute( 'download', `Annex-${annexId}.pdf`,);

            document.body.appendChild(link);
            link.click();
            link.parentNode.removeChild(link);
        });
    }

    async generarJustificant(url){

        document.getElementById('justificantLoader').style.display = 'block';

        await fetch(url, { 
            method: 'GET',
            headers: { 
                'Content-Type': 'application/json',
            },
        })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            document.getElementById('justificantLoader').style.display = 'none';
            if (data.error != null)
                this.setState({...this.state, error: data.error});
            else{
                this.downloadDoc(data.justificantData, data.justificantFilename);
            }
        });   
    }

    downloadDoc(datafile, dataName) {

        const {t} = this.props;

        const linkSource = `data:application/pdf;base64,${datafile}`;
    	const downloadLink = document.createElement("a");
    	const fileName = (typeof(dataName) !== 'undefined' && dataName != '') ? `${dataName}.pdf` : `justificant.pdf`;
        
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
    	
        downloadLink.appendChild(document.createTextNode(t('downloadjustificantText')));
        document.getElementById("justificantEnllaz").append(downloadLink);
        downloadLink.click();
    }

    mostraTooltip(){
        const estiloElemento = document.getElementById('descripcionTooltip').style.display;
        document.getElementById('descripcionTooltip').style.display = (estiloElemento == 'none') ? 'block' : 'none';
    }

    render() {

        $.dateFormat = function(dateObject) {
            var d = new Date(dateObject);
            var day = d.getDate();
            var month = d.getMonth() + 1;
            var year = d.getFullYear();
            var hour = d.getHours();
            var minute = d.getMinutes();
            if (day < 10) {
                day = "0" + day;
            }
            if (month < 10) {
                month = "0" + month;
            }
            if (hour < 10) {
                hour = "0" + hour;
            }
            if (minute < 10) {
                minute = "0" + minute;
            }
            return day + "/" + month + "/" + year + " " + hour + ":" + minute;
        };

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
                
                let registre = JSON.parse(this.state.data.registre.replace(/'/g, '"'));

                console.log("RESPONSE", this.state.data);

                let solicitaExponeContainer = (typeof (registre.expone) != 'undefined' || typeof(registre.solicita) != 'undefined') ?
                <div class="card border-left-carpeta shadow py-2 mb-3 alert">
                    <div class="card-body">
                        <div class="col mr-2 font15">
                                {typeof(registre.expone) != 'undefined' &&  <div><h3 class="font-weight-bold verde text-uppercase mb-3 text-center h3">EXPOSA</h3><p>{registre.expone}</p></div>}
                                {typeof(registre.solicita) != 'undefined' && <div><h3 class="font-weight-bold verde text-uppercase mb-3 text-center h3">SOLICITA</h3><p>{registre.solicita}</p></div>}
                        </div>
                    </div>
                </div> : "";

                const iconoDescargar = <span className="oi oi-data-transfer-download mr-2" title={t('carpeta_descargar') + " " + t('registro_justificante')} aria-hidden="true"></span>;

                content = 
                <>  
                    <h2 className="titol h2 ocultarMobil">{t('registro_titulo_detalle')} {registre.numeroRegistro}</h2>
                    <div className="col-md-12 border-0 float-left p-0">
                    <div className="card-body pl-0 pr-0" style={{flexFlow: "row wrap", display: "flex"}}>
                        <div className="pri-col-deta-reg col-md-6">
                            <div className="card border-left-carpeta shadow py-2 mb-3 alert cardAppVerd">
                                <div className="card-body">
                                    <div className="row no-gutters align-items-center">
                                        <div className="col mr-2 font15">
                                            <h3 className="font-weight-bold verde text-uppercase mb-3 text-center h3">{t('registro_entrada')}</h3>
                                            <dl className="row colorGrisApp">
                                                <dt className="col-sm-3 pb-2">{t('registro_fecha')}</dt>
                                                <dd className="col-sm-7">{$.dateFormat(registre.fechaRegistro)}</dd>

                                                <dt className="col-sm-3 pb-2">{t('registro_numero')}</dt>
                                                <dd className="col-sm-7">{registre.numeroRegistro}</dd>

                                                { registre.denominacionOficinaOrigen && <>
                                                <dt className="col-sm-3 pb-2">{t('registro_oficina')}</dt>
                                                <dd className="col-sm-7">{registre.denominacionOficinaOrigen}</dd>
                                                </>}

                                                { registre.denominacionDestino && <>
                                                <dt className="col-sm-3 pb-2">{t('registro_destinatario')}</dt>
                                                <dd className="col-sm-7">{registre.denominacionDestino}</dd>
                                                </>}

                                                { registre.tipoDocumetacionFisica && <>
                                                <dt className="col-sm-3 pb-2">{t('registro_tipo_doc')}</dt>
                                                <dd className="col-sm-7">{registre.tipoDocumetacionFisica}</dd>
                                                </>}

                                                { registre.extracto && <>
                                                <dt className="col-sm-3 pb-2">{t('registro_extracto')}</dt>
                                                <dd className="col-sm-7">{registre.extracto}</dd>
                                                </> }

                                                { registre.idioma && <>
                                                <dt className="col-sm-3 pb-2">{t('carpeta_idioma')}</dt>
                                                <dd className="col-sm-7">{t('registro_idioma_' + registre.idioma)}</dd>
                                                </> }

                                                { registre.estado && <>
                                                <dt className="col-sm-3 pb-2">{t('registro_estado')}</dt>
                                                <dd className="col-sm-7">{t('registro_estado_' + registre.estado)} <span class="oi oi-info pl-2" onClick={() => this.mostraTooltip()}></span> <span id="descripcionTooltip" style={{display:'none'}}>{t('registro_estado_explicacion_'+registre.estado)}</span></dd>
                                                </> }

                                                <dt className="col-sm-3 pb-2">{t('registro_presencial')}</dt>
                                                <dd className="col-sm-7">{ registre.presencial === true ? 'Sí' :  'No'}</dd>

                                                { registre.codigoSia  && <>
                                                <dt className="col-sm-3 pb-2">{t('registro_codigoSia')}</dt>
                                                <dd className="col-sm-7">{registre.codigoSia}</dd>
                                                </>}

                                            </dl>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div className="card border-left-carpeta shadow py-2 mb-3 alert cardAppVerd">
                                <div className="card-body">
                                    <div className="row no-gutters">
                                        <div className="col mr-2 font15 text-center">
                                            <h3 className="font-weight-bold verde text-uppercase mb-3 text-center h3">{t('registro_justificante')}</h3>
                                            
                                            <div  id="justificantLoader" className="loader-container centrat" style={{display:'none', textAlign:'center', marginBottom:'20px'}}><div className="loader" style={{width: '40px', height: '40px', margin:'0 auto'}}/></div>

                                            { /* typeof(this.state.data.justificante) != 'undefined' && !this.state.data.justificante.confidencial && <Button onClick={() => this.handlerJustificant(registre)}>{iconoDescargar} {t('carpeta_descargar')}</Button> */}
                                            
                                            {this.state.data.justificanteUrl !== '' && <Button className="btn btn-danger" onClick={() => { window.open(this.state.data.justificanteUrl); }}>{iconoDescargar} {t('carpeta_descargar')}</Button>}

                                            {this.state.data.justificanteUrl === '' && this.state.data.justificantSenseGenerar !== '' && this.state.error === '' && <Button className="btn btn-danger" onClick={() => { this.generarJustificant(this.state.data.urlGeneracioJustificant); }}>{iconoDescargar} {t('carpeta_descargar')}</Button>}

                                            {this.state.data.justificanteId !== '' && this.state.data.justificantData !== '' && this.state.error === '' && <Button className="btn btn-danger" onClick={() => this.downloadDoc(this.state.data.justificantData, this.state.data.justificantFileName)}>{iconoDescargar} {t('carpeta_descargar')}</Button>}

                                            {this.state.data.justificanteUrl === '' && this.state.data.justificantSenseGenerar === '' && this.state.data.justificanteId === '' && this.state.data.error !== '' && <div className="text-center pt-3 text-danger"><span className="oi oi-warning pr-1"> </span>{this.state.data.error}</div>}

                                            <p id="justificantEnllaz"/>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div className="seg-col-deta-reg col-md-5 pr-0">
                            <div className="card border-left-carpeta shadow py-2 mb-3 alert cardAppVerd">
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
                                                        { registre.interesados.map( (item, index) => <tr>
                                                            <td>{index+1}</td>
                                                            {typeof item.interesado.razonSocial === 'undefined' && <td>{item.interesado.nombre} {item.interesado.apellido1} {item.interesado.apellido2}</td>}
                                                            {typeof item.interesado.razonSocial !== 'undefined' && <td>{item.interesado.razonSocial}</td>}
                                                            <td>{item.interesado.documento}</td>
                                                            <td>{t('registro_tipo_interesado_'+item.interesado.tipoInteresado)}</td>
                                                        </tr> ) }
                                                    </tbody>
                                                </Table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                </div>

                                <div className="card border-left-carpeta shadow py-2 mb-3 alert cardAppVerd">
                                    <div className="card-body">
                                        <div className="row no-gutters align-items-center">
                                            <div className="col mr-2 font15">
                                                <h3 className="font-weight-bold verde text-uppercase mb-3 text-center h3">{t('registro_anexos')}</h3>
                                                { registre.anexos && registre.anexos.length > 0 &&
                                                <Table hover className="table-sm colorGrisApp">
                                                    <thead>
                                                        <tr>
                                                            <th scope="col">#</th>
                                                            <th scope="col">{t('registro_anexo_name')}</th>
                                                            <th scope="col">{t('registro_anexo_validezdocumento')}</th>
                                                            <th scope="col">{t('registro_anexo_file')}</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        { registre.anexos.map( (item,index) => <tr>
                                                            <td>{index+1}</td>
                                                            <td>{item.name}</td>
                                                            <td>{t('registro_anexo_validezdocumento_'+item.validezDocumento)}</td>
                                                            <td>{ (!item.confidencial) ? 
                                                                    ((item.mime != '') ? 
                                                                        <Button className="d-sm-inline-block btn btn-sm btn-danger shadow-sm" onClick={() => this.descarregarAnnex(item.fileID)}><span className="oi oi-data-transfer-download" title={t('registro_anexo_descargar') + " " + item.name} alt={t('registro_anexo_descargar') + " " + item.name} aria-hidden="true"></span></Button> 
                                                                    : t('registro_anexo_nodisponible')) 
                                                                   : t('registro_anexo_confidencial')
                                                                }</td>
                                                        </tr> ) }
                                                    </tbody>
                                                </Table> }
                                                { registre.anexos && registre.anexos.length < 1 && <p className="text-center colorGrisApp">{t('registro_anexos_vacio')}</p>}
                                                { !registre.anexos && <p className="text-center colorGrisApp">{t('registro_anexos_vacio')}</p>}
                                                <p className="text-md-left pt-2 colorGrisApp lletraPetitaApp" style={{fontSize: 'small'}}>{t('registro_anexos_nodisponibles')}</p>
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
                    }} className="botoSuport botoTornauApp" tabIndex="520" aria-labelledby="botoTornarDiscapacidad">{t('reprendreTornar')}</button>
                </div>

                </>

            } else {
                content = <></>
            }

            errorContainer = <div className="alert alert-danger hide" role="alert">{this.state.error}</div>;
        }
        
        return <>
            <div className="infoNoMenu" style={{marginTop: '10px'}}>
                {this.state.error && <div className="alert alert-danger" role="alert">{this.state.error}</div>}
                {content}
            </div>
        </>;    
    }
}

export default withTranslation()(DetallRegistre);