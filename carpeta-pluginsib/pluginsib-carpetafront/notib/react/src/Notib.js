import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import i18n from './i18n';
import axios from "axios";
import Table from 'react-bootstrap/Table';
import Pagination  from 'react-bootstrap/Pagination';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

/**
 *  @author jpernia
 */

class Notib extends Component {

    constructor(props) {
        super(props);

        this.state = {
            isLoaded: false,
            dataComunicacions: null,
            urldetallbase: null,
            filter_regPorPagina: 10,
            pagination_active: 1,
            pagination_total_items: 10,
            total_items: 0,
            error: false,
            cercaRegistres: 10
        };

        this.handleRegPorPaginaFilterParam = this.handleRegPorPaginaFilterParam.bind(this);

        const getLocale = Locale => require(`date-fns/locale/${sessionStorage.getItem("langActual")}/index.js`);
        this.locale = getLocale(this.props.language);

        this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);

    }

    canviatIdioma(lng) {
        this.componentDidMount();
    }

    handleRegPorPaginaFilterParam(e){
        this.setState({
            ...this.state,
            filter_regPorPagina: e.target.value,
        });
    };

    componentDidMount() {

        const url = this.props.pathtoservei;

        const params = {
            pageNumber: 0,
            registrosPorPagina: this.state.filter_regPorPagina
        };

        axios.get(url, {params: params}).then( (response) => {
            if (response.data != null){
                this.setState({
                    ...this.state,
                    dataComunicacions: response.data.comunicacions,
                    urldetallbase: response.data.urldetallbase,
                    total_items: response.data.totalRegistres,
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

    }

    handlePagination(event){

        let newPageNumber =  event.target.text;

        this.setState({
            ...this.state,
            pagination_active: newPageNumber,
            error: false
        });

        const params = {
            pageNumber: newPageNumber-1,
            registrosPorPagina: this.state.filter_regPorPagina
        };
        const url2 = this.props.pathtoservei;

        axios.get(url2, {params: params}).then( (response) => {
            if (response.data != null){
                this.setState({
                    ...this.state,
                    dataComunicacions: response.data.comunicacions,
                    urldetallbase: response.data.urldetallbase,
                    total_items: response.data.totalRegistres,
                    pagination_total_items: response.data.registresPagina,
                    pagination_active: newPageNumber,
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

    }

    handleSubmitSearcher(e) {

        const {t} = this.props;

        e.preventDefault();

        this.setState({
            ...this.state,
            isLoaded: false,
            error: false,
            cercaRegistres: this.state.filter_regPorPagina
        });

            const url2 = this.props.pathtoservei;

            const params = {
                registrosPorPagina: this.state.filter_regPorPagina,
                pageNumber: this.state.pagination_active-1
            };

            axios.get(url2, {params: params}).then( (response) => {

                if (response.data != null){
                    this.setState({
                        ...this.state,
                        dataComunicacions: response.data.comunicacions,
                        urldetallbase: response.data.urldetallbase,
                        total_items: response.data.totalRegistres,
                        pagination_active: 1,
                        pagination_total_items: response.data.registresPagina,
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

    }

    componentDidUpdate() {
        $('.clickableRow')
            .click(function(event){
                console.log("CLICK");
                event.preventDefault();
                carregarUrl($(this));
            })
            .keypress(function(event){
                console.log("KEYPRESS");
                event.preventDefault();
                carregarUrl($(this));
            });

        $("tr").each(function(i) {
            $(this).removeAttr("role");
        });

        function carregarUrl(obj) {
            window.open(obj.data('url'), '_blank');
        }
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
            return day + "/" + month + "/" + year + " " + hour + ":" + minute;
        };

        const isLoaded = this.state.isLoaded;

        const { t } = this.props;

        var tamanyData = { width: '120px !important'};
        var cursorPointer = { cursor: 'pointer'};
        var tamanyTaula = { width: '99%'};

        let content;
        let taulaNotib;

        let formulari = <>
            <Form id="fechaBusqueda" style={{marginBottom: '20px'}}>
                <Container style={{ width: '95%', paddingLeft: '0', margin: '0' }}>
                    <Row style={{ width: 'fit-content'}}>
                        <Col className="col-xs-12 mb-3">
                            <Form.Group>
                                <Form.Label>{t('registro_regPorPagina')}</Form.Label>
                                <Form.Select id="regPorPagina"
                                             name="regPorPagina" className="form-control form-control-sm focusIn"
                                             value={this.state.filter_regPorPagina}
                                             tabindex="505"
                                             aria-labelledby="regPorPagina"
                                             onChange={(e) => {this.handleRegPorPaginaFilterParam(e); }}>
                                    <option value="5" selected={this.state.filter_regPorPagina.toString() === '5'}>5</option>
                                    <option value="10" selected={this.state.filter_regPorPagina.toString() === '10'}>10</option>
                                    <option value="25" selected={this.state.filter_regPorPagina.toString() === '25'}>25</option>
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
                        <Button type="submit" className="btn btn-primary carpeta-btn ml-3 mt-2"  onClick={e => {this.handleSubmitSearcher(e)}} tabindex="505">{t('carpeta_buscar')}</Button>
                    </Row>
                </Container>
            </Form>
        </>

        if (!isLoaded) {
            content = <div  id="carregant" className="loader-container centrat ">
                <div className="loader"/>
            </div>;
        } else {
            content = "";

            const Header = [t('notibComunicacionFecha'), t('notibComunicacionConcepte'), t('notibComunicacionEstat')];

            if(this.state.dataComunicacions !== null && typeof(this.state.total_items) !== undefined  && typeof(this.state.dataComunicacions) !== undefined && this.state.total_items !== 0) {
                let paginationNumbers = [];
                for (let number = 1; number <= Math.ceil(this.state.total_items/this.state.cercaRegistres); number++) {
                    paginationNumbers.push(<Pagination.Item key={number}
                                                            active={number.toString() === this.state.pagination_active.toString()}
                                                            activeLabel=""
                                                            onClick={(event) => this.handlePagination(event)} >{number}</Pagination.Item>,);
                }

                taulaNotib = <>
                    <Table responsive striped bordered hover style={tamanyTaula}>
                        <thead className="table-success">
                        <tr>
                            <th style={tamanyData}>{t('notibComunicacionFecha')}</th>
                            <th>{t('notibComunicacionOrgano')}</th>
                            <th>{t('notibComunicacionConcepte')}</th>
                            <th style={tamanyData}>{t('notibComunicacionDataEstat')}</th>
                            <th>{t('notibComunicacionEstat')}</th>
                        </tr>
                        </thead>
                        <tbody>
                        {this.state.dataComunicacions.map(({emisor, organGestor, procediment, concepte, descripcio, dataEnviament, estat, dataEstat}) => {
                            return <tr className="clickableRow" data-target="_blank" data-url={this.state.urldetallbase} style={cursorPointer} tabIndex="511">
                                <td data-order={$.dateOrder(dataEnviament)}>{$.dateFormat(dataEnviament)}</td>
                                <td>{organGestor}</td>
                                <td>{concepte}</td>
                                <td>{$.dateFormat(dataEstat)}</td>
                                <td>{estat}</td>
                            </tr>
                        })}
                        </tbody>
                    </Table>
                    <div style={{float:'left', marginTop: '9px;',width: '60%'}}>
                        {t('carpeta_paginacion_1') +
                        ((parseInt(this.state.pagination_active,10)-1)*parseInt(this.state.cercaRegistres, 10)+1) +
                        t('carpeta_paginacion_2') +
                        ( ((parseInt(this.state.pagination_active,10)*parseInt(this.state.cercaRegistres, 10)) <= parseInt(this.state.total_items,10))
                                ? parseInt(this.state.pagination_active,10)*parseInt(this.state.cercaRegistres, 10)
                                : this.state.total_items
                        ) +
                        t('carpeta_paginacion_3') +
                        this.state.total_items +
                        t('carpeta_paginacion_4')}
                    </div>
                    <Pagination style={{float:'right',paddingRight: '0.7em'}}>
                        {paginationNumbers}
                    </Pagination>
                </>

            } else{
                if(this.state.total_items === 0 && this.state.dataComunicacions !== null) {
                    taulaNotib = <div className="pt-3 alert alert-secondary" style={{float: 'left', width: '95%'}}
                                      role="alert">{t('notibBuid')}</div>
                }
            }

        }

        
        return (
            <div className="infoNoMenu">
                <h2 className="titol h2">{this.props.titles[i18n.language]}</h2>
                <div className="col-md-12 border-0 float-left p-0">
                    <p className="lh15">{this.props.subtitles[i18n.language]} </p>
                    <div className="infoNoMenu">
                        <div className="col-md-12 border-0 float-left p-0">
                            {formulari}
                            {this.state.error && <div className="alert alert-danger hide" role="alert">{this.state.error}</div>}
                            {content}
                            <div className="card-body imc--llista--capses pl-0">
                                {this.state.isLoaded && taulaNotib }
                            </div>
                        </div>
                    </div>
                </div>

                <div className="col-md-12 border-0 float-left p-0" id="botoTornarNotib" style={{ marginTop: '20px' }}>
                    <button type="button" data-toggle="modal" onClick={() => {
                        window.location.href = sessionStorage.getItem("pagTornar"); sessionStorage.setItem("pagTornar", sessionStorage.getItem("contextPath"))
                    }} className="botoSuport" tabIndex="520" aria-labelledby="botoTornarNotib">{t('notibTornar')}</button>
                </div>
            </div>
            );
            
    }
}

export default withTranslation()(Notib);