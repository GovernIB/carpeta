import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import i18n from './i18n';
import axios from "axios";
import Table from 'react-bootstrap/Table';
import Pagination  from 'react-bootstrap/Pagination';

/**
 *  @author jpernia
 */

class NotificacionsSistra extends Component {

    constructor(props) {
        super(props);

        this.state = {
            isLoaded: false,
            dataComunicacions: null,
            pagination_active: 1,
            pagination_total_items: 10,
            total_items: 0,
            cercaRegistres: 5,
            error: false
        };

        const getLocale = Locale => require(`date-fns/locale/${sessionStorage.getItem("langActual")}/index.js`);
        this.locale = getLocale(this.props.language);

        this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);

    }

    canviatIdioma(lng) {
        this.componentDidMount();
    }

    componentDidMount() {

        const url = this.props.pathtoservei;

        const params = {
            pageNumber: 0
        };

        axios.get(url, {params: params}).then( (response) => {
            if (response.data != null){
                this.setState({
                    ...this.state,
                    dataComunicacions: response.data.comunicacions,
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

    handlePagination(event, accio, isNumber, pag){

        let newPageNumber =  event.target.text;
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
            error: false
        });

        const params = {
            pageNumber: pagActiva-1
        };
        const url2 = this.props.pathtoservei;

        axios.get(url2, {params: params}).then( (response) => {
            if (response.data != null){
                this.setState({
                    ...this.state,
                    dataComunicacions: response.data.comunicacions,
                    total_items: response.data.totalRegistres,
                    pagination_total_items: response.data.registresPagina,
                    pagination_active: pagActiva,
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
            var day = dateObject.orig_day;
            var month = dateObject.orig_month;
            var year = dateObject.orig_year;
            var hour = dateObject.orig_hour;
            var minute = dateObject.orig_minute;
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
            var day = dateObject.orig_day;
            var month = dateObject.orig_month;
            var year = dateObject.orig_year;
            var hour = dateObject.orig_hour;
            var minute = dateObject.orig_minute;
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

        var tamanyData = { width: '120px !important'};
        var cursorPointer = { cursor: 'pointer'};
        var tamanyTaula = { width: '99%'};

        let content;
        let taulaNotificacionsSistra;
        let cardNotificacions = [];


        if (!isLoaded) {
            content = <div  id="carregant" className="loader-container centrat ">
                <div className="loader"/>
            </div>;
        } else {
            content = "";

            if(this.state.dataComunicacions !== null && typeof(this.state.total_items) !== undefined  && typeof(this.state.dataComunicacions) !== undefined && this.state.total_items !== 0) {
                let paginationNumbers = [];

                let showMax = 5;
                let endPage;
                let startPage;
                let pageNumbers = Math.ceil(this.state.total_items/this.state.cercaRegistres);
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

                if(this.state.dataComunicacions) {
                    taulaNotificacionsSistra =
                        <>
                            <Table responsive striped bordered hover style={tamanyTaula} className="ocultarMobil">
                                <thead className="table-success">
                                <tr>
                                    <th>{t('notificacionsSistraComunicacionDescripcion')}</th>
                                    <th style={tamanyData}>{t('notificacionsSistraComunicacionFecha')}</th>
                                </tr>
                                </thead>
                                <tbody>
                                {this.state.dataComunicacions.map(({descripcion, fecha, url}) => {
                                    return <tr className="clickableRow" data-target="_blank" data-url={url}
                                               style={cursorPointer} tabIndex="511">
                                        <td>{descripcion}</td>
                                        <td data-order={$.dateOrder(fecha)}>{$.dateFormat(fecha)}</td>
                                    </tr>
                                })}
                                </tbody>
                            </Table>
                            <div style={{float: 'left', marginTop: '9px;', width: '60%'}} className="ocultarMobil">
                                {t('carpeta_paginacion_1') +
                                ((parseInt(this.state.pagination_active, 10) - 1) * 10 + 1) +
                                t('carpeta_paginacion_2') +
                                (((parseInt(this.state.pagination_active, 10) * 10) <= parseInt(this.state.total_items, 10))
                                        ? parseInt(this.state.pagination_active, 10) * 10
                                        : this.state.total_items
                                ) +
                                t('carpeta_paginacion_3') +
                                this.state.total_items +
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


                    this.state.dataComunicacions.map(({descripcion, fecha, url},i) => {

                        cardNotificacions.push(
                            <div className="col-lg-4 col-md-4 col-sm-4 pl-2 pt-5 pb-5 visioMobil cardAppVerd visioMobil" key={i} tabIndex={502+i}
                                 onClick={(e) => window.open(url, '_blank')}>
                                <div className="col-sm-1 float-left">
                                    <span className="oi oi-envelope-closed iconaFormApp" title={t('notificacionsSistraTitle')} style={{verticalAlign: 'sub'}}/>
                                </div>
                                <div className="col-sm-10 float-right">
                                    <p className="card-text pl-1 mt-0 font-weight-bold" style={{color: 'rgb(102, 102, 102)'}}>{descripcion}</p>
                                    <p className="card-text pl-1" style={{color: 'rgb(102, 102, 102)'}}>{$.dateFormat(fecha)}</p>
                                </div>
                            </div>
                        )
                    })

                    cardNotificacions.push(<div className="visioMobil">
                            <div style={{float: 'left', marginTop: '9px;', width: '100%'}} className="visioMobil pb-4">
                                {t('carpeta_paginacion_1_App') +
                                ((parseInt(this.state.pagination_active, 10) - 1) * 10 + 1) +
                                t('carpeta_paginacion_2') +
                                (((parseInt(this.state.pagination_active, 10) * 10) <= parseInt(this.state.total_items, 10))
                                        ? parseInt(this.state.pagination_active, 10) * 10
                                        : this.state.total_items
                                ) +
                                t('carpeta_paginacion_3_App') +
                                this.state.total_items +
                                t('carpeta_paginacion_4')}
                            </div>
                            <Pagination style={{float: 'left', paddingRight: '0.7em', width: '100%'}} size="lg">
                                {pageNumbers > showMax && <Pagination.First onClick={(event) => this.handlePagination(event, 0, true, 1)}
                                                                            disabled={this.state.pagination_active === 1}/>}
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

                }else{
                    taulaNotificacionsSistra = <div className="pt-3 alert alert-secondary" style={{float: 'left', width: '95%'}}
                                                    role="alert">{t('notificacionsSistraBuid')}</div>;
                }

            } else{
                if(this.state.total_items === 0 && this.state.dataComunicacions !== null) {
                    taulaNotificacionsSistra =
                        <div className="pt-3 alert alert-secondary" style={{float: 'left', width: '95%'}}
                             role="alert">{t('notificacionsSistraBuid')}</div>
                }
            }

        }

        
        return (<>
                <div className="titolPaginaApp visioMobil">
                    {this.props.titles[i18n.language]}
                </div>
                <div className="infoNoMenu">
                    <h2 className="titol h2 ocultarMobil">{this.props.titles[i18n.language]}</h2>
                    <div className="col-md-12 border-0 float-left p-0">
                        <p className="lh15 ocultarMobil">{this.props.subtitles[i18n.language]} </p>
                        <div className="infoNoMenu">
                            <div className="col-md-12 border-0 float-left p-0">
                                {this.state.error && <div className="alert alert-danger hide" role="alert">{this.state.error}</div>}
                                {content}
                                <div className="card-body imc--llista--capses pl-0">
                                    {this.state.isLoaded && taulaNotificacionsSistra }
                                    {this.state.isLoaded && cardNotificacions }
                                </div>
                            </div>
                        </div>
                    </div>

                    <div className="col-md-12 border-0 float-left p-0" id="botoTornarNotificacionsSistra" style={{ marginTop: '20px' }}>
                        <button type="button" data-toggle="modal" onClick={() => {
                            window.location.href = sessionStorage.getItem("pagTornar"); sessionStorage.setItem("pagTornar", sessionStorage.getItem("contextPath"))
                        }} className="botoSuport botoTornauApp" tabIndex="520" aria-labelledby="notificacionsSistraTornar">{t('notificacionsSistraTornar')}</button>
                    </div>
                </div>
            </>
            );
            
    }
}

export default withTranslation()(NotificacionsSistra);