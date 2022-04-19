import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import axios from "axios";
import i18n from 'i18next';

/**
 *  @author jpernia
 */

class Apodera extends Component {

    constructor(props) {
        super(props);

        this.state = {
            isLoaded: false,
            data: null
        };
    }

    componentDidMount() {

        const url = this.props.pathtoservei;
        console.log("url: " + url);
        axios.get(url).then(res => {

            console.log(" AXIOS OK OK OK OK OK", res.data);

            this.setState({
                ...this.state,
                isLoaded: true,
                data: res.data
            });
        }).catch(function (error) {

            console.log(JSON.stringify(error));
            const restdata = { "error": JSON.stringify(error) };
            if (error.response) {
                console.log("error.response.data: " + error.response.data);
                console.log("error.response.status: " + error.response.status);
                console.log("error.response.headers: " + error.response.headers);
            }
            this.setState({
                ...this.state,
                isLoaded: true,
                data: restdata
            });
        });

    }

    render() {
        const isLoaded = this.state.isLoaded;

        const { t } = this.props;

        let content;

        if (!isLoaded) {

            content = <div  id="carregant" className="loader-container centrat ">
                <div className="loader"/>
            </div>;

        } else {

            const data = this.state.data;
            console.log("DATA: " + data);
            if (data.error) {
                content = <div className="alert alert-danger" role="alert">{data.error}</div>;
            } else {
                content = <div>
                    <div className="contenedorInfoPersonal mt-2">
                        <dl className="row">
                            {data && <div>
                                <dt className="col-sm-3">{t('apoderaDni')}</dt>
                                <dd className="col-sm-7">{data}</dd>
                            </div>}

                        </dl>
                    </div>
                </div>;
            }
        }

        return (
            <div className="infoNoMenu">
                <h2 className="titol h2">{this.props.titles[i18n.language]}</h2>
                <div className="col-md-12 border-0 float-left p-0">
                    <p className="lh15">{this.props.subtitles[i18n.language]} </p>
                    <div className="infoNoMenu">
                        <div className="col-md-12 border-0 float-left p-0">
                            {content}
                        </div>
                    </div>
                </div>
                <div className="col-md-12 border-0 float-left p-0" id="botoTornarApodera" style={{ marginTop: '20px' }}>
                    <button type="button" data-toggle="modal" onClick={() => {
                        window.location.href = sessionStorage.getItem("pagTornar"); sessionStorage.setItem("pagTornar", sessionStorage.getItem("contextPath"))
                    }} className="botoSuport" tabIndex="520" aria-labelledby="botoTornarApodera">{t('apoderaTornar')}</button>
                </div>
            </div>
        );

    }
}

export default withTranslation()(Apodera);