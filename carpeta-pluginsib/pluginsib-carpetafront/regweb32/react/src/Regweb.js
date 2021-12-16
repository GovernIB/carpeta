import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import {withTranslation} from 'react-i18next';
import axios from "axios";
import i18n from 'i18next';
import DatePicker from 'react-datepicker';
import Table from 'react-bootstrap/Table';
import Pagination  from 'react-bootstrap/Pagination';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import DetallRegistre from './DetallRegistre';

/**
 *  @author jagarcia
 */

class Regweb extends Component {

    constructor(props) {
        super(props);

        let startDateObj = new Date();
        const endDateObj = new Date();
        startDateObj.setMonth(endDateObj.getMonth()-6);

        this.state = {
            isLoaded: false,
            data: null,
            filter_number: null,
            filter_startDate: startDateObj,
            filter_endDate: endDateObj,
            filter_status: 0,
            pagination_active: 1,
            pagination_total_items: 10,
            numeroRegistro: null,
            error: false, 
            criteriosTexto: ''
        };

        this.handleNumberFilterParam = this.handleNumberFilterParam.bind(this);
        this.handleStartDateFilterParam = this.handleStartDateFilterParam.bind(this);
        this.handleEndDateFilterParam = this.handleEndDateFilterParam.bind(this);
        this.handleStateFilterParam = this.handleStateFilterParam.bind(this);
        this.handleSubmitSearcher = this.handleSubmitSearcher.bind(this);

        const getLocale = Locale => require(`date-fns/locale/${sessionStorage.getItem("langActual")}/index.js`);
        this.locale = getLocale(this.props.language);

        this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);

    }

    canviatIdioma(lng) {
        this.componentDidMount();
    }

    handleStartDateFilterParam(e){
        this.setState({
            ...this.state, 
            filter_startDate: e,
        });
    };
    
    handleEndDateFilterParam(e){
        this.setState({
            ...this.state, 
            filter_endDate: e,
        });
    };

    handleStateFilterParam(e){
        this.setState({
            ...this.state, 
            filter_status: e.target.value
        });
    };

    handleNumberFilterParam(e) {       
        this.setState({
            ...this.state, 
            filter_number: e.target.value,
        });
    }


    handlePagination(event){
        const newPageNumber =  event.target.text;

        this.setState({
            ...this.state,
            pagination_active: newPageNumber,
            error: false,
        }); 

        const params = {
            numero: this.state.filter_number,
            fechaInicio: this.state.filter_startDate,
            fechaFin: this.state.filter_endDate,
            estado: this.state.filter_status,
            pageNumber: newPageNumber-1
        };

        const url2 = this.props.pathtoservei;

        axios.get(url2, {params: params}).then( (response) => {

            if (response.data.registres != null){
                this.setState({
                    ...this.state,
                    data: JSON.parse(response.data.registres),
                    pagination_active: newPageNumber,
                    pagination_total_items: response.data.totalRegistres,
                });
            }
            
        }).catch( error => {
            console.log('Error axios', error);
            this.setState({
                ...this.state,
                error: true
            });
        } );
    }


    async handleSubmitSearcher(e) {

        const {t} = this.props;

        let validatFormulari = this.validaFormulari();

        if (validatFormulari){

            e.preventDefault();

            let criterisCercaText = 
        t('carpeta_criterio_1') + $.dateFormatCerca(this.state.filter_startDate) +
        t('carpeta_criterio_2') + $.dateFormatCerca(this.state.filter_endDate) +
        t('carpeta_criterio_3') + $.nomEstat(this.state.filter_status) +
        t('carpeta_criterio_4') + 
        ((this.state.filter_number != null) ? t('carpeta_criterio_5') + this.state.filter_number : '');

            this.setState({
                ...this.state, 
                isLoaded: false,
                error: false,
                numeroRegistro: null,
                criteriosTexto: criterisCercaText
            });  

            const url2 = this.props.pathtoservei;

            const params = {
                numero: this.state.filter_number,
                fechaInicio: this.state.filter_startDate,
                fechaFin: this.state.filter_endDate,
                estado: this.state.filter_status,
                pageNumber: this.state.pagination_active-1
            };

            await axios.get(url2, {params: params}).then( (response) => {

                if (response.data.registres != null){
                    this.setState({
                        ...this.state,
                        data: JSON.parse(response.data.registres),
                        pagination_active: 1,
                        pagination_total_items: response.data.totalRegistres,
                        isLoaded: true
                    });
                }

            }).catch( error => {
                console.log('Error axios', error);
                this.setState({
                    ...this.state,
                    error: true
                });
            } );

        } else{
            e.preventDefault();
        }

    }

    componentDidMount() {

        const getLocale = locale => require(`date-fns/locale/${sessionStorage.getItem("langActual")}/index.js`);
        this.locale = getLocale(this.props.language);

        this.setState({
            ...this.state, 
            isLoaded: true
        });     
    }

    validaFormulari() {
        
        if(!this.validaFecha(this.state.filter_startDate) || !this.validaFecha(this.state.filter_endDate)){
            return false;
        }

        if(Date.parse(this.state.filter_startDate) > Date.parse(this.state.filter_endDate)){
            $('#errorMsg').html(t('sistraDataIniciError'));
            $('#errorContainer').removeClass('ocult');
            return false;
        }

        return true;

    }

    validaFecha(date) {
        if (isNaN(Date.parse(date))){
            $('#errorMsg').html("Error de data");
            $('#errorContainer').removeClass("ocult");
            return false;
        }else{
            $('#errorContainer').addClass("ocult");
            return true;
        }
    }

    componentDidUpdate() {

        $("#fechaInicio").attr("tabindex","502");
        $("#fechaFin").attr("tabindex","503");

        const {t} = this.props;

        let taulaRegistres;
        var tamanyTaula = { width: '99%'};

        if(this.state.data != null && parseInt(this.state.pagination_total_items) > 0){

            let paginationNumbers = []; 
            for (let number = 1; number <= Math.ceil(this.state.pagination_total_items/10); number++) {
                paginationNumbers.push(<Pagination.Item key={number} active={number.toString() == this.state.pagination_active.toString()} activeLabel="" onClick={(event) => this.handlePagination(event)} >{number}</Pagination.Item>,);
            }

           taulaRegistres = <>
                {/*<div style={{float:'left', marginTop: '9px;',paddingBottom: '0.7em'}}>{this.state.criteriosTexto}</div>*/}
                <Table responsive striped bordered hover style={tamanyTaula}>
                    <thead className="table-sucess">
                        <tr>
                            <th>{t('registro_numero')}</th>
                            <th style={{width: '8em'}}>{t('registro_fecha')}</th>
                            <th>{t('registro_extracto')}</th>
                            <th>{t('registro_estado')}</th>
                            <th>{t('registro_destinatario')}</th>
                        </tr>
                    </thead>
                    <tbody>
                    {this.state.data.map(({ numeroRegistro, fechaRegistro, extracto, tipoRegistro, denominacionDestino }) => {
                        return <tr key={numeroRegistro} onClick={(e) => this.handleItemClick(numeroRegistro) }>
                                <td>{numeroRegistro}</td>
                                <td style={{width: '8em'}}>{fechaRegistro}</td>
                                <td>{extracto}</td>
                                <td>{tipoRegistro}</td>
                                <td>{denominacionDestino}</td>
                            </tr>
                    })}
                    </tbody>
                </Table>
                <div style={{float:'left', marginTop: '9px;'}}>
                        {t('carpeta_paginacion_1') + 
                        ((parseInt(this.state.pagination_active,10)-1)*10+1) + 
                        t('carpeta_paginacion_2') + 
                        ( ((parseInt(this.state.pagination_active,10)*10) <= parseInt(this.state.pagination_total_items,10)) 
                            ? parseInt(this.state.pagination_active,10)*10 
                            : this.state.pagination_total_items
                        ) + 
                        t('carpeta_paginacion_3') + 
                        this.state.pagination_total_items + 
                        t('carpeta_paginacion_4')}
                    </div>
                <Pagination style={{float:'right'}}>
                    {paginationNumbers}
                </Pagination>
            </>

        }

        let detallRegistreContainer;
        if ( this.state.numeroRegistro != null ){
            detallRegistreContainer = <DetallRegistre pathtoservei={props.detallpathtoservei} numero={this.state.numeroRegistro} />;
            ReactDOM.render(detallRegistreContainer, document.getElementById('detallregistrecontainer'));
        }
    }

    handleItemClick(numeroRegistro) {
        this.setState({
            ...this.state,
            isLoaded: false,
            numeroRegistro: numeroRegistro
        });
    }

    render() {

        $.dateOrder = function(dateObject) {
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
            return year.toString() + month.toString() + day.toString() + hour.toString() + minute.toString();
        };


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
            return day + "-" + month + "-" + year + " " + hour + ":" + minute;
        };

        $.dateFormatCerca = function(dateObject) {
            var d = new Date(dateObject);
            var day = d.getDate();
            var month = d.getMonth() + 1;
            var year = d.getFullYear();
            if (day < 10) {
                day = "0" + day;
            }
            if (month < 10) {
                month = "0" + month;
            }
            return day + "-" + month + "-" + year;
        };

        $.nomEstat = function(estat) {                
            switch(estat.toString()) {
                case '0': return t('registro_estado_todos');
                case '1': return t('registro_estado_1');
                case '2': return t('registro_estado_4');
                case '3': return t('registro_estado_10');
                case '4': return t('registro_estado_11');
              }
        }

        const isLoaded = this.state.isLoaded;

        const { t } = this.props;

        let content;
        
        let taulaRegistres; 
        let detallRegistreContainer;
        var tamanyTaula = { width: '99%'};

        if (!isLoaded) {
            content = <div  id="carregant" className="loader-container centrat ">
                        <div className="loader"/>
                    </div>;
        } else {
            
            content = 
            <>  
                <Form id="fechaBusqueda" style={{marginBottom: '20px'}}>
                    <Container style={{ width: '95%', paddingLeft: '0', margin: '0' }}>
                        <Row>
                            <Col className="col-xs-12 mb-3">
                                <Form.Group>
                                    <Form.Label>{t('registro_numero')}</Form.Label>
                                    <Form.Control 
                                        type="text" id="numero" placeholder="Número" 
                                        maxlength="25" 
                                        tabIndex="501" 
                                        value={this.state.filter_number} 
                                        onChange={(e)=>{this.handleNumberFilterParam(e);}}
                                        className="form-control form-control-sm focusIn"
                                    />
                                </Form.Group>
                            </Col>
                            <Col className="col-xs-12 mb-3">
                                <Form.Group>
                                    <Form.Label>{t('registro_fecha_inicio')}</Form.Label>
                                    <DatePicker 
                                        selected={this.state.filter_startDate}
                                        onChange={ (startDate) => this.handleStartDateFilterParam(startDate) }
                                        selectsStart
                                        //startDate={this.state.filter_startDate}
                                        //endDate={this.state.filter_endDate} 
                                        name="fechaInicio"
                                        id="fechaInicio"
                                        dateFormat="dd/MM/yyyy"
                                        className="form-control form-control-sm estilCalendar focusIn"
                                        locale={this.locale}
                                        showYearDropdown={true}
                                        preventOpenOnFocus={true}
                                    />
                                </Form.Group>
                            </Col>
                            <Col className="col-xs-12 mb-3">
                                <Form.Group>
                                    <Form.Label>{t('registro_fecha_fin')}</Form.Label>
                                    <DatePicker 
                                        selected={this.state.filter_endDate}
                                        onChange={ (endDate) => this.handleEndDateFilterParam(endDate) } 
                                        selectsEnd
                                        //startDate={this.state.filter_startDate}
                                        //endDate={this.state.filter_endDate} 
                                        minDate={this.state.filter_startDate}
                                        name="fechaFin"
                                        id="fechaFin"
                                        dateFormat="dd/MM/yyyy"
                                        className="form-control form-control-sm estilCalendar focusIn"
                                        locale={this.locale}
                                        showYearDropdown={true}
                                        preventOpenOnFocus={true}
                                    />
                                </Form.Group>
                            </Col>
                            <Col className="col-xs-12 mb-3">
                                <Form.Group>
                                    <Form.Label>{t('registro_estado')}</Form.Label>
                                    <Form.Select id="estado"
                                    name="estado" className="form-control form-control-sm focusIn"
                                    value={this.state.filter_status}
                                    tabindex="504" 
                                    aria-labelledby="estado"
                                    onChange={(e) => {this.handleStateFilterParam(e); }}>
                                        <option value="0" selected="selected">{t('registro_estado_todos')}</option>
                                        <option value="1">{t('registro_estado_1')}</option>
                                        <option value="2">{t('registro_estado_4')}</option>
                                        <option value="3">{t('registro_estado_10')}</option>
                                        <option value="4">{t('registro_estado_11')}</option>
                                    </Form.Select>
                                </Form.Group>
                            </Col>
                        </Row>
                        <Row>
                            <div id="errorContainer" className="row pb-2 ml-3 mr-0 ocult">
                                <div className="alert alert-danger" role="alert" id="errorMsg"/>
                            </div>
                        </Row>
                        <Row>
                            <Button type="submit" className="btn btn-primary carpeta-btn ml-3 mt-2"  onClick={(e) => {this.handleSubmitSearcher(e)}} tabindex="505">{t('carpeta_buscar')}</Button>
                        </Row>
                    </Container>
                </Form>
            </>

            if(this.state.data != null && parseInt(this.state.pagination_total_items,10) > 0){

                let paginationNumbers = []; 
                for (let number = 1; number <= Math.ceil(this.state.pagination_total_items/10); number++) {
                    paginationNumbers.push(<Pagination.Item key={number} active={number.toString() === this.state.pagination_active.toString()} activeLabel="" onClick={(event) => this.handlePagination(event)} >{number}</Pagination.Item>,); 
                }

               taulaRegistres = <>
                    {/*<div style={{float:'left', marginTop: '9px;',paddingBottom: '0.7em'}}>{this.state.criteriosTexto}</div>*/}
                    <Table responsive striped bordered hover style={tamanyTaula}>
                        <thead className="table-success">
                            <tr>
                                <th>{t('registro_numero')}</th>
                                <th style={{width: '8em'}}>{t('registro_fecha')}</th>
                                <th>{t('registro_extracto')}</th>
                                <th>{t('registro_estado')}</th>
                                <th>{t('registro_destinatario')}</th>
                            </tr>
                        </thead>
                        <tbody>
                        {this.state.data.map(({ numeroRegistro, fechaRegistro, extracto, tipoRegistro, denominacionDestino, estado }) => {
                            return <tr key={numeroRegistro} onClick={(e) => this.handleItemClick(numeroRegistro) }>
                                    <td>{numeroRegistro}</td>
                                    <td style={{width: '8em'}}>{$.dateFormat(fechaRegistro)}</td>
                                    <td>{extracto}</td>
                                    <td>{t('registro_estado_'+estado)}</td>
                                    <td>{denominacionDestino}</td>
                                </tr>
                        })}
                        </tbody>
                    </Table>
                    <div style={{float:'left', marginTop: '9px', width: '60%'}}>
                        {t('carpeta_paginacion_1') + 
                        ((parseInt(this.state.pagination_active,10)-1)*10+1) + 
                        t('carpeta_paginacion_2') + 
                        ( ((parseInt(this.state.pagination_active,10)*10) <= parseInt(this.state.pagination_total_items,10)) 
                            ? parseInt(this.state.pagination_active,10)*10 
                            : this.state.pagination_total_items
                        ) + 
                        t('carpeta_paginacion_3') + 
                        this.state.pagination_total_items + 
                        t('carpeta_paginacion_4')}
                    </div>
                    <Pagination style={{float:'right', paddingRight: '0.7em'}}>
                        {paginationNumbers}
                    </Pagination>

                    <div className="col-md-12 border-0 float-left p-0" id="botoTornarDiscapacidad" style={{ marginTop: '20px' }}>
                        <button type="button" data-toggle="modal" onClick={() => {
                            window.location.href = sessionStorage.getItem("pagTornar"); sessionStorage.setItem("pagTornar", sessionStorage.getItem("contextPath"))
                        }} className="botoSuport" tabIndex="520" aria-labelledby="botoTornarDiscapacidad">{t('reprendreTornar')}</button>
                    </div>

                </>

            }
        }
        
        return (
            <div>
                <div className="infoNoMenu">
                    {this.state.numeroRegistro == null && <>
                        <h2 className="titol h2">{this.props.titles[i18n.language]}</h2>
                        <div className="col-md-12 border-0 float-left p-0">
                            <p className="lh15">{this.props.subtitles[i18n.language]} </p>
                            <div className="infoNoMenu">
                                <div className="col-md-12 border-0 float-left p-0">
                                    {this.state.error && <div className="alert alert-danger hide" role="alert">{this.state.error}</div>}                            
                                    {this.state.numeroRegistro == null && content}
                                    {this.state.numeroRegistro == null && taulaRegistres}
                                    {this.state.pagination_total_items.toString() === '0' && <><div style={{float:'left', marginTop: '9px;',paddingBottom: '0.7em'}}>{this.state.criteriosTexto}</div><div className="pt-3 alert alert-secondary" style={{ float: 'left', width: '95%'}} role="alert">{t('registro_vacio')}</div></>}
                                </div>
                            </div>
                        </div>
                    </>}
                    {this.state.numeroRegistro != null && detallRegistreContainer}
                </div>
            </div>);
            
    }
}

export default withTranslation()(Regweb);