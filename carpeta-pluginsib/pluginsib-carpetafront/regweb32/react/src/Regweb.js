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
            filter_startDate: e.toISOString().slice(0, 10),
        });
    };
    
    handleEndDateFilterParam(e){
        this.setState({
            ...this.state, 
            filter_endDate: e.toISOString().slice(0, 10),
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
    }

    componentDidMount() {
        this.setState({
            ...this.state, 
            isLoaded: true
        });     
    }

    componentDidUpdate() {

        let taulaRegistres; 

        if(this.state.data != null){

            let paginationNumbers = []; 
            for (let number = 1; number <= Math.ceil(this.state.pagination_total_items/10); number++) {
                paginationNumbers.push(<Pagination.Item key={number} active={number == this.state.pagination_active} activeLabel="" onClick={(event) => this.handlePagination(event)} >{number}</Pagination.Item>,);
            }

           taulaRegistres = <>
                <Table responsive striped hover> 
                    <thead className="table-sucess">
                        <tr>
                            <th>Número</th>
                            <th>Data</th>
                            <th>Extracte</th>
                            <th>Estat</th>
                            <th>Organisme</th>
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

        const isLoaded = this.state.isLoaded;

        const { t } = this.props;

        let content;

        let taulaRegistres; 
        let detallRegistreContainer;

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
                                    <Form.Label>Número</Form.Label>
                                    <Form.Control 
                                        type="text" id="numero" placeholder="Número" 
                                        maxlength="25" 
                                        tabIndex="501" 
                                        value={this.state.filter_number} 
                                        onChange={(e)=>{this.handleNumberFilterParam(e);}}
                                        className="form-control"  
                                    />
                                </Form.Group>
                            </Col>
                            <Col className="col-xs-12 mb-3">
                                <Form.Group>
                                    <Form.Label>Data inici</Form.Label>
                                    <DatePicker 
                                        selected={this.state.filter_startDate}
                                        onChange={ (startDate) => this.handleStartDateFilterParam(startDate) }
                                        selectsStart
                                        startDate={this.state.filter_startDate}
                                        endDate={this.state.filter_endDate} 
                                        name="fechaInicio"
                                        dateFormat="dd/MM/yyyy"
                                        className="form-control"
                                    />
                                </Form.Group>
                            </Col>
                            <Col className="col-xs-12 mb-3">
                                <Form.Group>
                                    <Form.Label>Data fi</Form.Label>
                                    <DatePicker 
                                        selected={this.state.filter_endDate}
                                        onChange={ (endDate) => this.handleEndDateFilterParam(endDate) } 
                                        selectsEnd
                                        startDate={this.state.filter_startDate}
                                        endDate={this.state.filter_endDate} 
                                        minDate={this.state.filter_startDate}
                                        name="fechaFin"
                                        dateFormat="dd/MM/yyyy"
                                        className="form-control"
                                    />
                                </Form.Group>
                            </Col>
                            <Col className="col-xs-12 mb-3">
                                <Form.Group>
                                    <Form.Label>Estat</Form.Label>
                                    <Form.Select id="estado"
                                    name="estado" className="form-control"
                                    value={this.state.filter_status}
                                    tabindex="504" 
                                    aria-labelledby="estado"
                                    onChange={(e) => {this.handleStateFilterParam(e); }}>
                                        <option value="0" selected="selected">Tots</option>
                                        <option value="1">Acceptat</option>
                                        <option value="2">Remès a destí</option>
                                        <option value="3">Rebutjat a destí</option>
                                        <option value="4">Reenviat</option>
                                    </Form.Select>
                                </Form.Group>
                            </Col>
                        </Row>
                        <Row>
                            <Button type="submit" className="btn btn-primary carpeta-btn ml-3 mt-2"  onClick={(e) => {this.handleSubmitSearcher(e)}} tabindex="505">Cerca</Button>
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
                    <Table responsive striped hover> 
                        <thead className="table-success">
                            <tr>
                                <th>Número</th>
                                <th>Data</th>
                                <th>Extracte</th>
                                <th>Estat</th>
                                <th>Organisme</th>
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