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
            filter_status: null,
            pagination_active: 1,
            pagination_total_items: 10,
            numeroRegistro: null,
            error: false
        };

        this.handleNumberFilterParam = this.handleNumberFilterParam.bind(this);
        this.handleStartDateFilterParam = this.handleStartDateFilterParam.bind(this);
        this.handleEndDateFilterParam = this.handleEndDateFilterParam.bind(this);
        this.handleStateFilterParam = this.handleStateFilterParam.bind(this);
        this.handleSubmitSearcher = this.handleSubmitSearcher.bind(this);

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


        let validatFormulari = this.validaFormulari();

        if (validatFormulari){

            e.preventDefault();

            this.setState({
                ...this.state, 
                isLoaded: false,
                error: false,
                numeroRegistro: null,
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

        const {t} = this.props;

        let taulaRegistres;
        var tamanyTaula = { width: '99%'};

        if(this.state.data != null && this.state.pagination_total_items !== 0){

            let paginationNumbers = []; 
            for (let number = 1; number <= Math.ceil(this.state.pagination_total_items/10); number++) {
                paginationNumbers.push(<Pagination.Item key={number} active={number === this.state.pagination_active} activeLabel="" onClick={(event) => this.handlePagination(event)} >{number}</Pagination.Item>,);
            }

           taulaRegistres = <>
                <Table responsive striped bordered hover style={tamanyTaula}>
                    <thead className="table-sucess">
                        <tr>
                            <th>{t('registro_numero')}</th>
                            <th>{t('registro_fecha')}</th>
                            <th>{t('registro_extracto')}</th>
                            <th>{t('registro_estado')}</th>
                            <th>{t('registro_destinatario')}</th>
                        </tr>
                    </thead>
                    <tbody>
                    {this.state.data.map(({ numeroRegistro, fechaRegistro, extracto, tipoRegistro, denominacionDestino }) => {
                        return <tr key={numeroRegistro} onClick={(e) => this.handleItemClick(numeroRegistro) }>
                                <td>{numeroRegistro}</td>
                                <td>{fechaRegistro}</td>
                                <td>{extracto}</td>
                                <td>{tipoRegistro}</td>
                                <td>{denominacionDestino}</td>
                            </tr>
                    })}
                    </tbody>
                </Table>
                <Pagination>
                    {paginationNumbers}
                </Pagination>
            </>

        } else{
            if (this.state.totalRegistres === 0 && this.state.data !== null) {
                taulaRegistres = <div className="pt-3 alert alert-secondary" style={{ float: 'left', width: '95%'}} role="alert">{t('registro_vacio')}</div>
            }
        }

        let detallRegistreContainer;
        if ( this.state.numeroRegistro != null ){
            detallRegistreContainer = <DetallRegistre pathtoservei={props.detallpathtoservei} numero={this.state.numeroRegistro} />;
            ReactDOM.render(<DetallRegistre pathtoservei={props.detallpathtoservei} numero={this.state.numeroRegistro} />, document.getElementById('detallregistrecontainer'));
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

            const estilsForm = (this.state.data == null) ? {marginBottom: '20px', minHeight: '450px'} : {marginBottom: '20px'};

            content = 
            <>  
                <Form id="fechaBusqueda" style={estilsForm}>
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
                                        className="form-control form-control-sm"  
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
                                        startDate={this.state.filter_startDate}
                                        endDate={this.state.filter_endDate} 
                                        name="fechaInicio"
                                        id="fechaInicio"
                                        dateFormat="dd/MM/yyyy"
                                        className="form-control form-control-sm estilCalendar focusIn"
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
                                        startDate={this.state.filter_startDate}
                                        endDate={this.state.filter_endDate} 
                                        minDate={this.state.filter_startDate}
                                        name="fechaFin"
                                        id="fechaFin"
                                        dateFormat="dd/MM/yyyy"
                                        className="form-control form-control-sm estilCalendar focusIn"
                                    />
                                </Form.Group>
                            </Col>
                            <Col className="col-xs-12 mb-3">
                                <Form.Group>
                                    <Form.Label>{t('registro_estado')}</Form.Label>
                                    <Form.Select id="estado"
                                    name="estado" className="form-control form-control-sm"
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

            if(this.state.data != null){

                let paginationNumbers = []; 
                for (let number = 1; number <= Math.ceil(this.state.pagination_total_items/10); number++) {
                    paginationNumbers.push(<Pagination.Item key={number} active={number === this.state.pagination_active} activeLabel="" onClick={(event) => this.handlePagination(event)} >{number}</Pagination.Item>,);
                }

               taulaRegistres = <>
                    <Table responsive striped bordered hover style={tamanyTaula}>
                        <thead className="table-success">
                            <tr>
                                <th>{t('registro_numero')}</th>
                                <th>{t('registro_fecha')}</th>
                                <th>{t('registro_fecha')}</th>
                                <th>{t('registro_estado')}</th>
                                <th>{t('registro_destinatario')}</th>
                            </tr>
                        </thead>
                        <tbody>
                        {this.state.data.map(({ numeroRegistro, fechaRegistro, extracto, tipoRegistro, denominacionDestino }) => {
                            return <tr key={numeroRegistro} onClick={(e) => this.handleItemClick(numeroRegistro) }>
                                    <td>{numeroRegistro}</td>
                                    <td>{$.dateFormat(fechaRegistro)}</td>
                                    <td>{extracto}</td>
                                    <td>{t('registro_estado_'+tipoRegistro)}</td>
                                    <td>{denominacionDestino}</td>
                                </tr>
                        })}
                        </tbody>
                    </Table>
                    <Pagination>
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
                    <h2 className="titol h2">{this.props.titles[i18n.language]}</h2>
                    <div className="col-md-12 border-0 float-left p-0">
                        <p className="lh15">{this.props.subtitles[i18n.language]} </p>
                        <div className="infoNoMenu">
                            <div className="col-md-12 border-0 float-left p-0">
                                {this.state.error && <div className="alert alert-danger hide" role="alert">{this.state.error}</div>}                            
                                {this.state.numeroRegistro == null && content}
                                {this.state.numeroRegistro == null && taulaRegistres}
                                
                            </div>
                        </div>
                    </div>
                    {this.state.numeroRegistro != null && detallRegistreContainer}
                </div>
            </div>);
            
    }
}

export default withTranslation()(Regweb);