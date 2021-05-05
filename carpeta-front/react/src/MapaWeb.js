import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import axios from "axios";
import ExpirarSessio from "./ExpirarSessio";
import {Link} from "react-router-dom";
import i18n from 'i18next';
import * as Constants from './Constants';

/**
 * @author Adaptació a fillInfo
 */
class MapaWeb extends Component {

	constructor(){
		super();
		this.state = {
			plugins: [],
			error: null
		}
		this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);
    }

    canviatIdioma(lng) {
        console.log(" CANVIAT IDIOMA EN MapaWeb A ]" + lng+ "[");
        this.componentDidMount();
    }

	componentDidMount() {

		var autenticat = sessionStorage.getItem('autenticat');

		if (autenticat === '1') {

			var baseURL = sessionStorage.getItem('contextPath');

			// 0 == Nivell Arell
			var url = baseURL + `/webui/fullinfo/0`;
			axios.get(url)
				.then(res => {
					var fulldata = res.data;
					this.setState({ plugins: fulldata.veureplugins,
									menuEnllasos: fulldata.menuslidelinks,
									menupseudoplugin: fulldata.menupseudoplugin,
									seccions: fulldata.seccions});
				})
				.catch(error => {
					console.log(JSON.stringify(error));
					if (error.response) {
						console.log("error.response.data: " + error.response.data);
						console.log("error.response.status: " + error.response.status);
						console.log("error.response.headers: " + error.response.headers);
					}
					this.setState({
						error: JSON.stringify(error)
					});
				});
		}
	}


	render() {

		const {t} = this.props;

		var logoClau;

		let enllazos = [];

		if (this.state.error) {
			enllazos = <div className="alert alert-danger" role="alert">{this.state.error}</div>;
		} else {


			var accessibilitat = <p key={0} className="lh15 upper">
				<Link to={{pathname: `/accessibilitat`, nomPagina: 'menuAccessibilitat'}}
					  className="imc-marc-ico imc--accessibilitat">
					<span>{t('menuAccessibilitat')}</span>
				</Link>
			</p>;

			enllazos.push(accessibilitat);


			var autenticat = sessionStorage.getItem('autenticat');
			if (autenticat === '1') {
				logoClau = '';
				var urlBase = sessionStorage.getItem('contextPath');

				const plugins = this.state.plugins;

				if (this.state.plugins) {
					plugins.filter(s => s.reactComponent === false).map((s, i) => {
						enllazos.push(
							<p key={'ph' + i} className="lh15 upper">
								<img src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
									 className="imc-icona" title="" alt=""/>
								<Link to={Constants.PLUGINHTML_PATH + s.context}>{s.nom}</Link>
							</p>)
					});

					plugins.filter(s => s.reactComponent === true).map((s, i) => {
						enllazos.push(
							<p key={'pr' + i} className="lh15 upper">
								<img src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
									 className="imc-icona" title="" alt=""/>
								<Link to={Constants.PLUGINREACT_PATH + s.context}>{s.nom}</Link>
							</p>);
					});
				}


				if (this.state.menupseudoplugin) {
					this.state.menupseudoplugin.map((s, i) => {
						enllazos.push(
							<p key={'ps' + i} className="lh15 upper">
								<a href={s.url} target="_blank" title={s.nom}>
									<img src={s.urllogo} title="" alt="" className="imc-icona iconaEnllas"/>
									<span>{s.label}</span>
								</a>
							</p>
						)
					})
				}


				if (this.state.seccions) {
					this.state.seccions.map((s, i) => {
						enllazos.push(
							<p key={'s' + i} className="lh15 upper">
								<Link to={Constants.SECCIO_PATH + s.context}>
									<img src={s.iconaID} title="" alt={s.descripcio} className="imc-icona iconaEnllas"/>
									<span>{s.nom}</span>
								</Link>
							</p>
						)
					});
				}


			}
			if (autenticat === '0') {
				logoClau = <span className="oi oi-lock-locked colorClave" title={t('mapaWebClave')}/>;
			}
		}

		clearTimeout(sessionStorage.getItem('idTimeOut'));

		return (
			<div className="container-contenido">
	
				{autenticat === '1' && <ExpirarSessio/>	}

				<div className="infoNoMenu">
					<h2><p className="titol h2">{t('mapaWebTitol')}</p></h2>

					<div className="col-md-12 border-0 pl-0 pr-0">

						<p className="lh15">{t('mapaWebDescripcio')}</p>

						<div className="card">
							<ul className="list-group list-group-flush">
								<li className="list-group-item">
									<p className="lh15 upper"><Link to="/">{t('mapaWebInformacio')}</Link></p>
								</li>
								<li className="list-group-item">
									{enllazos}
								</li>
							</ul>
						</div>

					</div>

				</div>

			</div>
		);
	}
}

export default withTranslation()(MapaWeb);