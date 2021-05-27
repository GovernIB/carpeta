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
			items: [],
			error: null
		}
		this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);
    }

    canviatIdioma(lng) {
        // console.log(" CANVIAT IDIOMA EN MapaWeb A ]" + lng+ "[");
        this.componentDidMount();
    }

	componentDidMount() {

		// 0 == Nivell Arell
		var baseURL = sessionStorage.getItem('contextPath');
		var autenticat = sessionStorage.getItem('autenticat');
		var url = (autenticat === '1') ? baseURL + `/webui/fullinfosortedauth/0` : baseURL + `/webui/fullinfosorted/0`;
		axios.get(url)
			.then(res => {
				var fulldata = res.data;
				this.setState({ items: fulldata.items });
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

		$('[tabIndex=1]').focus();

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
					  className="imc-marc-ico imc--accessibilitat" tabIndex="3">
					<span>{t('menuAccessibilitat')}</span>
				</Link>
			</p>;

			enllazos.push(accessibilitat);


			var autenticat = sessionStorage.getItem('autenticat');
			if (autenticat === '1') {
				logoClau = '';
				var urlBase = sessionStorage.getItem('contextPath');

				if (this.state.error) {
					enllazos.push( <div className="alert alert-danger" role="alert">{this.state.error}</div>);
				} else {

					this.state.items.forEach((s, i) => {
						switch (s.tipus) {

							case 0: // Plugin react
								enllazos.push(
									<p key={'pr' + i} className="lh15 upper">
										<Link to={Constants.PLUGINREACT_PATH + s.context} tabIndex={4+i}>
											<img src={urlBase + s.urllogo} alt={s.nom} title="" className="imc-icona"/>
											{s.nom}
										</Link>
									</p>);
								break;

							case 1: // Plugin html
								enllazos.push(
									<p key={'ph' + i} className="lh15 upper">
										{/*<img src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}*/}
										{/*	 className="imc-icona" title="" alt=""/>*/}
										<Link to={Constants.PLUGINHTML_PATH + s.context} tabIndex={4+i}>
											<img src={urlBase + s.urllogo} alt={s.nom} title="" className="imc-icona"/>
											{s.nom}
										</Link>
									</p>);
								break;

							case 3: // Seccio
								enllazos.push(
									<p key={'s' + i} className="lh15 upper">
										<Link to={Constants.SECCIO_PATH + s.context} tabIndex={4+i}>
											<img src={s.urllogo} title="" alt={s.nom} className="imc-icona"/>
											<span className="menuRapidView">{s.nom}</span>
										</Link>
									</p>
								);
								break;

							case 4: // PseudoPlugin
								enllazos.push(
									<p key={'ps' + i} className="lh15 upper">
										<a href={s.url} target="_blank" title={s.nom} tabIndex={4+i}>
											<img src={s.urllogo} alt={s.nom} title="" className="imc-icona"/>
											<span className="menuRapidView">{s.nom}</span>
										</a>
									</p>
								);
								break;
						}
					});

				}

			}
			if (autenticat === '0') {
				logoClau = <span className="oi oi-lock-locked colorClave" title={t('mapaWebClave')}/>;
			}
		}

		clearTimeout(sessionStorage.getItem('idTimeOut'));

		return (
			<div className="container-contenido" tabIndex="1">
	
				{autenticat === '1' && <ExpirarSessio/>	}

				<div className="infoNoMenu">
					<h2><p className="titol h2">{t('mapaWebTitol')}</p></h2>

					<div className="col-md-12 border-0 pl-0 pr-0">

						<p className="lh15">{t('mapaWebDescripcio')}</p>

						<div className="card">
							<ul className="list-group list-group-flush">
								<li className="list-group-item">
									<p className="lh15 upper"><Link to="/" tabIndex="2">{t('mapaWebInformacio')}</Link></p>
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