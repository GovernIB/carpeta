import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import axios from "axios";
import ExpirarSessio from "./ExpirarSessio";
import {Link} from "react-router-dom";
import i18n from 'i18next';
import * as Constants from './Constants';
import DocumentTitle from 'react-document-title';

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
		var amplePantalla = screen.width;
		if(amplePantalla < 576) {
			document.getElementById("headerBarra").style.backgroundColor = sessionStorage.getItem("colorBarra");
		}

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


			var accessibilitat = <li className="list-group-item liApp"><h3 key={0} className="lh15 upper h3 mt-2">
				<Link to={{pathname: `/accessibilitat`, nomPagina: 'menuAccessibilitat'}}
					  className="imc-marc-ico imc--accessibilitat" tabIndex="503" aria-labelledby="accesAccessibilitat">
					<span className="oi oi-eye iconaMenu pl-2 pr-2 iconaApp" title={t('menuAccessibilitat')}/>
					<span id="accesAccessibilitat" className="mapaTextApp">{t('menuAccessibilitat')}</span>
				</Link>
			</h3></li>;

			enllazos.push(accessibilitat);

			var avislegal = <li className="list-group-item visioMobil liApp wAuto"><h3 key={0} className="lh15 upper h3 mt-2">
				<Link to={{pathname: `/avislegal`, nomPagina: 'avisLegalTitol'}}
					  className="imc-marc-ico imc--accessibilitat" tabIndex="503" aria-labelledby="accesAvisLegal">
					<span className="oi oi-thumb-up iconaMenu pl-2 pr-2 iconaApp" title={t('menuAccessibilitat')}/>
					<span id="accesAvisLegal" className="mapaTextApp">{t('avisLegalTitol')}</span>
				</Link>
			</h3></li>;

			enllazos.push(avislegal);


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
								enllazos.push(<li className="list-group-item liApp">
									<h3 key={'pr' + i} className="lh15 upper h3 mt-2" id={"mapaPlugin"+i}>
										<Link to={Constants.PLUGINREACT_PATH + s.context} tabIndex={504+i} aria-labelledby={"mapaPlugin"+i}>
											<img src={urlBase + s.urllogo} alt={s.nom} title="" className="imc-icona"/>
											<span className="menuRapidView mapaTextApp">{s.nom}</span>
										</Link>
									</h3>
								</li>);
								break;

							case 1: // Plugin html
								enllazos.push(<li className="list-group-item liApp">
									<h3 key={'ph' + i} className="lh15 upper h3 mt-2" id={"mapaPlugin"+i}>
										{/*<img src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}*/}
										{/*	 className="imc-icona" title="" alt=""/>*/}
										<Link to={Constants.PLUGINHTML_PATH + s.context} tabIndex={504+i} aria-labelledby={"mapaPlugin"+i}>
											<img src={urlBase + s.urllogo} alt={s.nom} title="" className="imc-icona"/>
											<span className="menuRapidView mapaTextApp">{s.nom}</span>
										</Link>
									</h3>
								</li>);
								break;

							case 3: // Seccio
								enllazos.push(<li className="list-group-item liApp">
									<h3 key={'s' + i} className="lh15 upper h3 mt-2" id={"mapaSeccio"+i}>
										<Link to={Constants.SECCIO_PATH + s.context} tabIndex={504+i} aria-labelledby={"mapaSeccio"+i}>
											<img src={s.urllogo} title="" alt={s.nom} className="imc-icona"/>
											<span className="menuRapidView mapaTextApp">{s.nom}</span>
										</Link>
									</h3>
									</li>
								);
								break;

							case 4: // PseudoPlugin
								enllazos.push(<li className="list-group-item liApp">
									<h3 key={'ps' + i} className="lh15 upper h3 mt-2" id={"mapaPseudo"+i}>
										<a href={s.url} target="_blank" title={s.nom} tabIndex={504+i} aria-labelledby={"mapaPseudo"+i}>
											<img src={s.urllogo} alt={s.nom} title="" className="imc-icona"/>
											<span className="menuRapidView mapaTextApp">{s.nom}</span>
										</a>
									</h3>
									</li>
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

		return ( <>
				<div className="titolPaginaApp">
					{t('mapaWebTitol')}
				</div>
				<div className="container-contenido" tabIndex="501">

					<DocumentTitle title={t('peuMapa') + " - " + t('menuTitol')} />

					{autenticat === '1' && <ExpirarSessio/>	}

					<div className="infoNoMenu">
						<h2 className="titol h2 ocultarMobil">{t('mapaWebTitol')}</h2>

						<div className="col-md-12 border-0 pl-0 pr-0">

							<p className="lh15 subtitol ocultarMobil">{t('mapaWebDescripcio')}</p>

							<div className="card cardApp">
								<ul className="list-group list-group-flush">
									<li className="list-group-item liApp" id="infoMapaWeb">
										<h3 className="lh15 upper h3 mt-2"><Link to="/" tabIndex="502" aria-labelledby="infoMapaWeb">
											<span className="oi oi-italic iconaMenu pl-2 pr-3 iconaApp" title={t('mapaWebInformacio')}/>
											<span id="accesInici" className="mapaTextApp">{t('mapaWebInformacio')}</span>
										</Link></h3>
									</li>
									{/*<li className="list-group-item">*/}
										{enllazos}
									{/*</li>*/}
								</ul>
							</div>

						</div>

						<div className="col-md-12 border-0 float-left p-0" id="botoTornarMapa" style={{ marginTop: '20px' }}>
							<button type="button" data-toggle="modal" onClick={() => {
								window.location.href = sessionStorage.getItem("pagTornar")
							}} className="botoSuport" tabIndex="520" aria-labelledby="botoTornarMapa">{t('tornar')}</button>
						</div>

					</div>

				</div>
			</>
		);
	}
}

export default withTranslation()(MapaWeb);