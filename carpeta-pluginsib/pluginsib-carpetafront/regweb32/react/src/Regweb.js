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
        startDateObj.setMonth(endDateObj.getMonth()-1);

        this.state = {
            isLoaded: false,
            data: null,
            filter_number: null,
            filter_startDate: startDateObj,
            filter_endDate: endDateObj,
            filter_status: 0,
            filter_regPorPagina: 5,
            pagination_active: 1,
            pagination_total_items: 5,
            numeroRegistro: null,
            error: null,
            criteriosTexto: '',
            cercaRegistres: 5,
            formVisible: false
        };

        this.handleNumberFilterParam = this.handleNumberFilterParam.bind(this);
        this.handleStartDateFilterParam = this.handleStartDateFilterParam.bind(this);
        this.handleEndDateFilterParam = this.handleEndDateFilterParam.bind(this);
        this.handleStateFilterParam = this.handleStateFilterParam.bind(this);
        this.handleRegPorPaginaFilterParam = this.handleRegPorPaginaFilterParam.bind(this);
        this.handleSubmitSearcher = this.handleSubmitSearcher.bind(this);
        this.mostrarForm = this.mostrarForm.bind(this);

        const getLocale = Locale => require(`date-fns/locale/${sessionStorage.getItem("langActual")}/index.js`);
        this.locale = getLocale(this.props.language);

        this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);

    }

    mostrarForm(){
        if(document.getElementById("fechaBusqueda").classList.contains("ocultarMobil")) {
            document.getElementById("fechaBusqueda").classList.remove("ocultarMobil");
        }
        document.getElementById("idCriteris").style.display = "none";

        this.setState({
            ...this.state,
            formVisible: true
        });
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

    handleRegPorPaginaFilterParam(e){
        this.setState({
            ...this.state,
            filter_regPorPagina: e.target.value,
            isLoaded: false,
            error: null,
            cercaRegistres: e.target.value
        });

        const params = {
            numero: this.state.filter_number,
            fechaInicio: this.state.filter_startDate,
            fechaFin: this.state.filter_endDate,
            estado: this.state.filter_status,
            registrosPorPagina: e.target.value,
            pageNumber: 0
        };

        const url3 = this.props.pathtoservei;

        axios.get(url3, {params: params}).then( (response) => {

            if (response.data.registres != null){
                this.setState({
                    ...this.state,
                    data: JSON.parse(response.data.registres),
                    pagination_active: 1,
                    pagination_total_items: response.data.totalRegistres,
                    isLoaded: true,
                    error: null
                });
            }

        }).catch(error => {
            console.log(JSON.stringify(error));
            if (error.response) {
                console.log("error.response.data: " + error.response.data);
                console.log("error.response.status: " + error.response.status);
                console.log("error.response.headers: " + error.response.headers);
            }
            if(JSON.stringify(error).toString().includes("Request failed with status code 500")){
                var errorPantalla = error.response.data.replace("<html><head><title>Error</title></head><body>", '');
                errorPantalla = errorPantalla.replace("</body></html>", '');
                this.setState({
                    error: errorPantalla,
                    isLoaded: true
                });
            } else{
                this.setState({
                    error: JSON.stringify(error),
                    isLoaded: true
                });
            }

        });

    };

    handleNumberFilterParam(e) {       
        this.setState({
            ...this.state, 
            filter_number: e.target.value,
        });
    }


    handlePagination(event, accio, isNumber, pag){
        const newPageNumber =  event.target.text;

        let pagActiva;

        if(!isNumber) {
            if (accio === 0) {
                pagActiva = newPageNumber;
            }
            if (accio === 1) {
                pagActiva = this.state.pagination_active - 1;
            }
            if (accio === 2) {
                pagActiva = this.state.pagination_active + 1;
            }
        }else{
            pagActiva = pag;
        }

        this.setState({
            ...this.state,
            pagination_active: pagActiva,
            error: null,
            isLoaded: false
        });

        this.setState({
            ...this.state,
            pagination_active: pagActiva,
            error: null,
            isLoaded: false
        }); 

        const params = {
            numero: this.state.filter_number,
            fechaInicio: this.state.filter_startDate,
            fechaFin: this.state.filter_endDate,
            estado: this.state.filter_status,
            registrosPorPagina: this.state.filter_regPorPagina,
            pageNumber: pagActiva-1
        };

        const url3 = this.props.pathtoservei;

        axios.get(url3, {params: params}).then( (response) => {

            if (response.data.registres != null){
                this.setState({
                    ...this.state,
                    data: JSON.parse(response.data.registres),
                    pagination_active: pagActiva,
                    pagination_total_items: response.data.totalRegistres,
                    isLoaded: true,
                    error: null
                });
            }
            
        }).catch(error => {
            console.log(JSON.stringify(error));
            if (error.response) {
                console.log("error.response.data: " + error.response.data);
                console.log("error.response.status: " + error.response.status);
                console.log("error.response.headers: " + error.response.headers);
            }
            if(JSON.stringify(error).toString().includes("Request failed with status code 500")){
                var errorPantalla = error.response.data.replace("<html><head><title>Error</title></head><body>", '');
                errorPantalla = errorPantalla.replace("</body></html>", '');
                this.setState({
                    error: errorPantalla,
                    isLoaded: true
                });
            } else{
                this.setState({
                    error: JSON.stringify(error),
                    isLoaded: true
                });
            }

        });
    }


    handleSubmitSearcher(e) {

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
                error: null,
                numeroRegistro: null,
                cercaRegistres: this.state.filter_regPorPagina,
                data: null
            });  

            const url2 = this.props.pathtoservei;

            const params = {
                numero: this.state.filter_number,
                fechaInicio: this.state.filter_startDate,
                fechaFin: this.state.filter_endDate,
                estado: this.state.filter_status,
                registrosPorPagina: this.state.filter_regPorPagina,
                pageNumber: 0
            };

            axios.get(url2, {params: params}).then( (response) => {

                if (response.data.registres != null){
                    this.setState({
                        ...this.state,
                        data: JSON.parse(response.data.registres),
                        pagination_active: 1,
                        pagination_total_items: response.data.totalRegistres,
                        isLoaded: true,
                        error: null
                    });
                }

            }).catch(error => {
                console.log(JSON.stringify(error));
                if (error.response) {
                    console.log("error.response.data: " + error.response.data);
                    console.log("error.response.status: " + error.response.status);
                    console.log("error.response.headers: " + error.response.headers);
                }
                if(JSON.stringify(error).toString().includes("Request failed with status code 500")){
                    var errorPantalla = error.response.data.replace("<html><head><title>Error</title></head><body>", '');
                    errorPantalla = errorPantalla.replace("</body></html>", '');
                    this.setState({
                        error: errorPantalla,
                        isLoaded: true
                    });
                } else{
                    this.setState({
                        error: JSON.stringify(error),
                        isLoaded: true
                    });
                }

            });

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

        const {t} = this.props;

        let validatFormulari = this.validaFormulari();

        if (validatFormulari){

            let criterisCercaText =
                t('carpeta_criterio_1') + $.dateFormatCerca(this.state.filter_startDate) +
                t('carpeta_criterio_2') + $.dateFormatCerca(this.state.filter_endDate) +
                t('carpeta_criterio_3') + $.nomEstat(this.state.filter_status) +
                t('carpeta_criterio_4') +
                ((this.state.filter_number != null) ? t('carpeta_criterio_5') + this.state.filter_number : '');

            this.setState({
                ...this.state,
                isLoaded: false,
                error: null,
                numeroRegistro: null,
                criteriosTexto: criterisCercaText
            });

            const url = this.props.pathtoservei;

            const params = {
                numero: this.state.filter_number,
                fechaInicio: this.state.filter_startDate,
                fechaFin: this.state.filter_endDate,
                estado: this.state.filter_status,
                registrosPorPagina: this.state.filter_regPorPagina,
                pageNumber: this.state.pagination_active-1
            };

            axios.get(url, {params: params}).then( (response) => {

                if (response.data.registres != null){
                    this.setState({
                        ...this.state,
                        data: JSON.parse(response.data.registres),
                        pagination_active: 1,
                        pagination_total_items: response.data.totalRegistres,
                        isLoaded: true,
                        error: null
                    });
                }

            }).catch(error => {
                console.log(JSON.stringify(error));
                if (error.response) {
                    console.log("error.response.data: " + error.response.data);
                    console.log("error.response.status: " + error.response.status);
                    console.log("error.response.headers: " + error.response.headers);
                }
                if(JSON.stringify(error).toString().includes("Request failed with status code 500")){
                    var errorPantalla = error.response.data.replace("<html><head><title>Error</title></head><body>", '');
                    errorPantalla = errorPantalla.replace("</body></html>", '');
                    this.setState({
                        error: errorPantalla,
                        isLoaded: true
                    });
                } else{
                    this.setState({
                        error: JSON.stringify(error),
                        isLoaded: true
                    });
                }

            });

        }
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

        if(this.state.data && typeof (this.state.total_items) !== undefined && typeof (this.state.data) !== undefined && this.state.total_items !== 0) {

            let paginationNumbers = [];

            let showMax = 5;
            let endPage;
            let startPage;
            let pageNumbers = Math.ceil(this.state.pagination_total_items/this.state.cercaRegistres);
            if (pageNumbers <= showMax) {
                startPage = 1;
                endPage = pageNumbers;
            }else {
                if(this.state.pagination_active < 4){
                    startPage = 1;
                } else{
                    startPage = this.state.pagination_active - 2;
                }
                if (this.state.pagination_active + 2 < pageNumbers) {
                    endPage = this.state.pagination_active + 2;
                }else {
                    endPage = pageNumbers;
                }
            }

            for (let number = startPage; number <= endPage; number++) {
                if (number === startPage && startPage > 1) {
                    paginationNumbers.push(<Pagination.Ellipsis key={number} className="muted" />);
                }
                paginationNumbers.push(<Pagination.Item key={number}
                                                        active={number.toString() === this.state.pagination_active.toString()}
                                                        activeLabel=""
                                                        onClick={(event) => this.handlePagination(event, 0, false, 0)}>{number}</Pagination.Item>,);
                if (number === endPage && endPage < pageNumbers) {
                    paginationNumbers.push(<Pagination.Ellipsis key={number} className="muted" />);
                }
            }

           taulaRegistres = <>
                <Table responsive striped bordered hover style={tamanyTaula}>
                    <thead className="table-sucess">
                        <tr>
                            <th>{t('registro_numero')}</th>
                            <th style={{whiteSpace: 'nowrap'}}>{t('registro_fecha')}</th>
                            <th>{t('registro_extracto')}</th>
                            <th>{t('registro_estado')}</th>
                            <th>{t('registro_destinatario')}</th>
                        </tr>
                    </thead>
                    <tbody>
                    {this.state.data.map(({ numeroRegistro, fechaRegistro, extracto, tipoRegistro, denominacionDestino }) => {
                        return <tr key={numeroRegistro} onClick={(e) => this.handleItemClick(numeroRegistro) }>
                                <td>{numeroRegistro}</td>
                                <td style={{whiteSpace: 'nowrap'}}>{fechaRegistro}</td>
                                <td>{extracto}</td>
                                <td>{tipoRegistro}</td>
                                <td>{denominacionDestino}</td>
                            </tr>
                    })}
                    </tbody>
                </Table>

               <div style={{float:'left', marginTop: '9px', width: '60%'}}>
                   {t('carpeta_paginacion_1') +
                   ((parseInt(this.state.pagination_active,10)-1)*parseInt(this.state.cercaRegistres,10)+1) +
                   t('carpeta_paginacion_2') +
                   ( ((parseInt(this.state.pagination_active,10)*parseInt(this.state.cercaRegistres,10)) <= parseInt(this.state.pagination_total_items,10))
                           ? parseInt(this.state.pagination_active,10)*parseInt(this.state.cercaRegistres,10)
                           : this.state.pagination_total_items
                   ) +
                   t('carpeta_paginacion_3') +
                   this.state.pagination_total_items +
                   t('carpeta_paginacion_4')}
               </div>
                <Pagination style={{float:'right'}}>
                    {pageNumbers > showMax && <Pagination.First onClick={(event) => this.handlePagination(event, 0, true, 1)}
                                                                disabled={this.state.pagination_active === 1} />}
                    {pageNumbers > showMax && <Pagination.Prev onClick={(event) => this.handlePagination(event, 1, false, 0)}
                                                               disabled={this.state.pagination_active === 1}/>}
                    {paginationNumbers}
                    {pageNumbers > showMax && <Pagination.Next onClick={(event) => this.handlePagination(event, 2, false, 0)}
                                                               disabled={this.state.pagination_active === pageNumbers}/>}
                    {pageNumbers > showMax && <Pagination.Last onClick={(event) => this.handlePagination(event, 0, true, pageNumbers)}
                                                               disabled={this.state.pagination_active === pageNumbers}/>}
                </Pagination>
            </>

        } else if(this.state.total_items === 0 && this.state.data !== null) {
            taulaRegistres = <div className="pt-3 alert alert-secondary margeBuid" style={{ float: 'left', width: '95%'}} role="alert">{t('registro_vacio')}</div>

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
        let formulari;
        
        let taulaRegistres; 
        let detallRegistreContainer;
        var tamanyTaula = { width: '99%'};
        let selectRegistres;
        let registresBuid;
        let criteris;

        let cardRegistres = [];

        formulari = <>
            <Form id="fechaBusqueda" style={{marginBottom: '20px'}} className="ocultarMobil">
                <Container style={{ width: '95%', paddingLeft: '0', margin: '0' }} className="ampleTotalApp">
                    <Row>
                        <Col className="col-xs-12 mb-3 campFormApp">
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
                        <Col className="col-xs-12 mb-3 campFormApp">
                            <Form.Group>
                                <Form.Label>{t('registro_fecha_inicio')}</Form.Label>
                                <DatePicker
                                    portalId="root-portal"
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
                                    popperPlacement="bottom"
                                    popperModifiers={{
                                        flip: {
                                            behavior: ["bottom"] // don't allow it to flip to be above
                                        },
                                        preventOverflow: {
                                            enabled: false // tell it not to try to stay within the view (this prevents the popper from covering the element you clicked)
                                        },
                                        hide: {
                                            enabled: false // turn off since needs preventOverflow to be enabled
                                        }
                                    }}
                                />
                            </Form.Group>
                        </Col>
                        <Col className="col-xs-12 mb-3 campFormApp">
                            <Form.Group>
                                <Form.Label>{t('registro_fecha_fin')}</Form.Label>
                                <DatePicker
                                    portalId="root-portal"
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
                                    popperPlacement="bottom"
                                    popperModifiers={{
                                        flip: {
                                            behavior: ["bottom"] // don't allow it to flip to be above
                                        },
                                        preventOverflow: {
                                            enabled: false // tell it not to try to stay within the view (this prevents the popper from covering the element you clicked)
                                        },
                                        hide: {
                                            enabled: false // turn off since needs preventOverflow to be enabled
                                        }
                                    }}
                                />
                            </Form.Group>
                        </Col>
                        <Col className="col-xs-12 mb-3 campFormApp">
                            <Form.Group>
                                <Form.Label>{t('registro_estado')}</Form.Label>
                                <Form.Select id="estado"
                                             name="estado" className="form-control form-control-sm focusIn"
                                             value={this.state.filter_status}
                                             tabindex="504"
                                             aria-labelledby="estado"
                                             onChange={(e) => {this.handleStateFilterParam(e); }}>
                                    <option value="0" className="form-control form-control-sm selectMobil" selected="selected">{t('registro_estado_todos')}</option>
                                    <option value="1" className="form-control form-control-sm selectMobil">{t('registro_estado_1')}</option>
                                    <option value="2" className="form-control form-control-sm selectMobil">{t('registro_estado_4')}</option>
                                    <option value="3" className="form-control form-control-sm selectMobil">{t('registro_estado_10')}</option>
                                    <option value="4" className="form-control form-control-sm selectMobil">{t('registro_estado_11')}</option>
                                </Form.Select>
                            </Form.Group>
                        </Col>
                    </Row>
                    <Row style={{ width: 'fit-content', display: 'none'}}>
                        <Col className="col-xs-12 mb-3 campFormApp">
                            <Form.Group>
                                <Form.Label>{t('registro_regPorPagina')}</Form.Label>
                                <Form.Select id="regPorPagina"
                                             name="regPorPagina" className="form-control form-control-sm focusIn"
                                             value={this.state.filter_regPorPagina}
                                             tabindex="505"
                                             aria-labelledby="regPorPagina"
                                             style={{color: '#666', borderRadius: '0.2rem'}}
                                             onChange={(e) => {this.handleRegPorPaginaFilterParam(e); }}>
                                    <option value="5" className="form-control form-control-sm selectMobil">5</option>
                                    <option value="10" className="form-control form-control-sm selectMobil">10</option>
                                    <option value="25" className="form-control form-control-sm selectMobil">25</option>
                                </Form.Select>
                            </Form.Group>
                        </Col>
                    </Row>
                    <Row>
                        <div id="errorContainer" className="row pb-2 ml-3 mr-0 ocult">
                            <div className="alert alert-danger" role="alert" id="errorMsg"/>
                        </div>
                    </Row>
                    <Row className="col-md-3 pl-3 row botoFormApp" style={{zIndex: '4'}}>
                        <Button type="submit" className="btn btn-primary carpeta-btn mt-2"  onClick={e => {this.handleSubmitSearcher(e)}} tabindex="505">{t('carpeta_buscar')}</Button>
                    </Row>
                </Container>
            </Form>
            </>

        if (!isLoaded) {
            taulaRegistres = '';

            content = <div  id="carregant" className="loader-container centrat ">
                <div className="loader"/>
            </div>;

            selectRegistres = '';
            registresBuid = '';
            criteris = '';

        } else {

            criteris = <div id="idCriteris">
                <div style={{float: 'left', marginTop: '9px;', paddingBottom: '0.7em'}} className="col-md-10 pr-3 visioMobil">
                    <span className="oi oi-calendar iconaFormApp" title={t('registroDates')} style={{verticalAlign: 'sub'}}/>
                    <span className="pl-3">{$.dateFormatCerca(this.state.filter_startDate) +
                    t('carpeta_criterio_6') +
                    $.dateFormatCerca(this.state.filter_endDate)}</span>
                </div>
                <div style={{float: 'rigth', marginTop: '9px;', paddingBottom: '0.7em', textAlign: 'end'}} onClick={() => {this.mostrarForm();}} className="col-md-1 visioMobil">
                    <span className="oi oi-magnifying-glass iconaFormApp" title={t('registroCerca')}/>
                </div>
            </div>

            const data = this.state.data;
            registresBuid = <div className="pt-3 alert alert-secondary" style={{ float: 'left', width: '95%'}} role="alert">{t('registro_vacio')}</div>;

                if (this.state.data != null && parseInt(this.state.pagination_total_items, 10) > 0 && this.state.data && typeof (this.state.total_items) !== undefined && typeof (this.state.data) !== undefined && this.state.total_items !== 0) {

                    let paginationNumbers = [];

                    let showMax = 5;
                    let endPage;
                    let startPage;
                    let pageNumbers = Math.ceil(this.state.pagination_total_items/this.state.cercaRegistres);
                    if (pageNumbers <= showMax) {
                        startPage = 1;
                        endPage = pageNumbers;
                    }else {
                        if(this.state.pagination_active < 4){
                            startPage = 1;
                        } else{
                            startPage = this.state.pagination_active - 2;
                        }
                        if (this.state.pagination_active + 2 < pageNumbers) {
                            endPage = this.state.pagination_active + 2;
                        }else {
                            endPage = pageNumbers;
                        }
                    }

                    for (let number = startPage; number <= endPage; number++) {
                        if (number === startPage && startPage > 1) {
                            paginationNumbers.push(<Pagination.Ellipsis key={number} className="muted" />);
                        }
                        paginationNumbers.push(<Pagination.Item key={number}
                                                                active={number.toString() === this.state.pagination_active.toString()}
                                                                activeLabel=""
                                                                onClick={(event) => this.handlePagination(event, 0, false, 0)}>{number}</Pagination.Item>,);
                        if (number === endPage && endPage < pageNumbers) {
                            paginationNumbers.push(<Pagination.Ellipsis key={number} className="muted" />);
                        }
                    }

                        selectRegistres = <div className="col-md-12 border-0 p-0">
                            <div className="col-sd-1 pb-2 margRegSelect">
                                <p className="lh15 mb-1 mRegSelApp">
                                    {t('registroMostra')}
                                    <Form.Select id="rPP"
                                                 name="rPP" className="focusIn"
                                                 value={this.state.filter_regPorPagina}
                                                 tabindex="510"
                                                 aria-labelledby="rPP"
                                                 style={{color: '#666', borderRadius: '0.2rem'}}
                                                 onChange={(e) => {this.handleRegPorPaginaFilterParam(e); }}>
                                        <option value="5" className="form-control form-control-sm selectMobil">5</option>
                                        <option value="10" className="form-control form-control-sm selectMobil">10</option>
                                        <option value="25" className="form-control form-control-sm selectMobil">25</option>
                                    </Form.Select>
                                    {t('registroRegistres')}
                                </p>
                            </div>
                        </div>;

                        taulaRegistres = <>
                            <Table responsive striped bordered hover style={tamanyTaula} className="ocultarMobil">
                                <thead className="table-success">
                                <tr>
                                    <th>{t('registro_numero')}</th>
                                    <th style={{whiteSpace: 'nowrap'}}>{t('registro_fecha')}</th>
                                    <th>{t('registro_extracto')}</th>
                                    <th>{t('registro_estado')}</th>
                                    <th>{t('registro_destinatario')}</th>
                                </tr>
                                </thead>
                                <tbody>
                                {this.state.data.map(({   numeroRegistro,
                                                          fechaRegistro,
                                                          extracto,
                                                          tipoRegistro,
                                                          denominacionDestino,
                                                          estado
                                                      },i) => {
                                    return <tr key={numeroRegistro}
                                               onClick={(e) => this.handleItemClick(numeroRegistro)}>
                                        <td>{numeroRegistro}</td>
                                        <td style={{whiteSpace: 'nowrap'}}>{$.dateFormat(fechaRegistro)}</td>
                                        <td>{extracto}</td>
                                        <td>{t('registro_estado_' + estado)}</td>
                                        <td>{denominacionDestino}</td>
                                    </tr>
                                })}
                                </tbody>
                            </Table>
                            <div style={{float: 'left', marginTop: '9px', width: '60%'}} className="ocultarMobil">
                                {t('carpeta_paginacion_1') +
                                ((parseInt(this.state.pagination_active, 10) - 1) * parseInt(this.state.cercaRegistres, 10) + 1) +
                                t('carpeta_paginacion_2') +
                                (((parseInt(this.state.pagination_active, 10) * parseInt(this.state.cercaRegistres, 10)) <= parseInt(this.state.pagination_total_items, 10))
                                        ? parseInt(this.state.pagination_active, 10) * parseInt(this.state.cercaRegistres, 10)
                                        : this.state.pagination_total_items
                                ) +
                                t('carpeta_paginacion_3') +
                                this.state.pagination_total_items +
                                t('carpeta_paginacion_4')}
                            </div>
                            <Pagination style={{float: 'right', paddingRight: '0.7em'}} className="ocultarMobil">
                                {pageNumbers > showMax && <Pagination.First onClick={(event) => this.handlePagination(event, 0, true, 1)}
                                                                            disabled={this.state.pagination_active === 1} />}
                                {pageNumbers > showMax && <Pagination.Prev onClick={(event) => this.handlePagination(event, 1, false, 0)}
                                                                           disabled={this.state.pagination_active === 1}/>}
                                {paginationNumbers}
                                {pageNumbers > showMax && <Pagination.Next onClick={(event) => this.handlePagination(event, 2, false, 0)}
                                                                           disabled={this.state.pagination_active === pageNumbers}/>}
                                {pageNumbers > showMax && <Pagination.Last onClick={(event) => this.handlePagination(event, 0, true, pageNumbers)}
                                                                           disabled={this.state.pagination_active === pageNumbers}/>}
                            </Pagination>

                        </>

                    this.state.data.map(({   numeroRegistro,
                                             fechaRegistro,
                                             extracto,
                                             tipoRegistro,
                                             denominacionDestino,
                                             estado
                                         },i) => {

                            cardRegistres.push(
                                <div className="col-lg-4 col-md-4 col-sm-4 pl-2 pt-5 pb-5 visioMobil cardAppVerd visioMobil"
                                     key={i} tabIndex={502+i} onClick={(e) =>
                                    this.handleItemClick(numeroRegistro)}>
                                    <div className="col-sm-1 float-left">
                                        <span className="oi oi-book iconaFormApp" title={t('registroDates')} style={{verticalAlign: 'sub'}}/>
                                    </div>
                                    <div className="col-sm-10 float-right">
                                        <p className="card-text pl-1 mt-0 font-weight-bold" style={{color: 'rgb(102, 102, 102)'}}>{numeroRegistro}</p>
                                        <p className="card-text pl-1" style={{color: 'rgb(102, 102, 102)'}}>{$.dateFormat(fechaRegistro)}</p>
                                        <p className="card-text pl-1" style={{color: 'rgb(102, 102, 102)'}}>{extracto}</p>
                                        <p className="card-text pl-1" style={{color: 'rgb(102, 102, 102)'}}>{denominacionDestino}</p>
                                        <h3 className="titolPlugin titol h3 visioMobil titolPluginApp">{t('registro_estado_' + estado)}</h3>
                                    </div>
                                </div>
                            )

                    })

                    cardRegistres.push(<div className="visioMobil">
                            <div style={{float: 'left', marginTop: '9px;', width: '100%'}} className="visioMobil pb-4">
                                {t('carpeta_paginacion_1_App') +
                                ((parseInt(this.state.pagination_active, 10) - 1) * parseInt(this.state.cercaRegistres, 10) + 1) +
                                t('carpeta_paginacion_2') +
                                (((parseInt(this.state.pagination_active, 10) * parseInt(this.state.cercaRegistres, 10)) <= parseInt(this.state.pagination_total_items, 10))
                                        ? parseInt(this.state.pagination_active, 10) * parseInt(this.state.cercaRegistres, 10)
                                        : this.state.pagination_total_items
                                ) +
                                t('carpeta_paginacion_3_App') +
                                this.state.pagination_total_items +
                                t('carpeta_paginacion_4')}
                            </div>
                            <Pagination style={{float: 'left', paddingRight: '0.7em', width: '100%'}} size="lg">
                                {pageNumbers > showMax && <Pagination.First onClick={(event) => this.handlePagination(event, 0, true, 1)}
                                                                            disabled={this.state.pagination_active === 1} />}
                                {pageNumbers > showMax && <Pagination.Prev onClick={(event) => this.handlePagination(event, 1, false, 0)}
                                                                           disabled={this.state.pagination_active === 1}/>}
                                {paginationNumbers}
                                {pageNumbers > showMax && <Pagination.Next onClick={(event) => this.handlePagination(event, 2, false, 0)}
                                                                           disabled={this.state.pagination_active === pageNumbers}/>}
                                {pageNumbers > showMax && <Pagination.Last onClick={(event) => this.handlePagination(event, 0, true, pageNumbers)}
                                                                           disabled={this.state.pagination_active === pageNumbers}/>}
                            </Pagination>
                        </div>
                    )

                } else if(this.state.total_items === 0 && this.state.data !== null) {
                    taulaRegistres = <div className="pt-3 alert alert-secondary margeBuid" style={{ float: 'left', width: '95%'}} role="alert">{t('registro_vacio')}</div>
                }

        }
        
        return (<>
                <div className="titolPaginaApp visioMobil">
                    {this.props.titles[i18n.language]}
                </div>
                <div className="infoNoMenu">
                    {this.state.numeroRegistro == null && <>
                        <h2 className="titol h2 ocultarMobil">{this.props.titles[i18n.language]}</h2>
                        <div className="col-md-12 border-0 float-left p-0">
                            <p className="lh15 ocultarMobil">{this.props.subtitles[i18n.language]} </p>
                            <div className="infoNoMenu">
                                <div className="col-md-12 border-0 float-left p-0">
                                    {!this.state.formVisible && criteris}
                                    {formulari}
                                    {this.state.error && <div className="alert alert-danger hide" role="alert">{this.state.error}</div>}                            
                                    {this.state.numeroRegistro == null && content}
                                </div>
                            </div>
                        </div>
                        <div className="float-left" style={{width: '97%', position: 'relative'}}>
                            {selectRegistres}
                            {this.state.numeroRegistro == null && taulaRegistres}
                            {this.state.pagination_total_items.toString() === '0' && registresBuid}
                            {cardRegistres}
                        </div>
                        <div className="col-md-12 border-0 float-left p-0" id="botoTornarRegistro"
                             style={{marginTop: '20px'}}>
                            <button type="button" data-toggle="modal" onClick={() => {
                                window.location.href = sessionStorage.getItem("pagTornar");
                                sessionStorage.setItem("pagTornar", sessionStorage.getItem("contextPath"))
                            }} className="botoSuport" tabIndex="520"
                                    aria-labelledby="botoTornarRegistro">{t('reprendreTornar')}</button>
                        </div>
                    </>}
                    {this.state.numeroRegistro != null && detallRegistreContainer}
                </div>
            </>);
            
    }
}

export default withTranslation()(Regweb);