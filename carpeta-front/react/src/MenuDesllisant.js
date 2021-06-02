import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import i18n from 'i18next';
import axios from 'axios';
import {Link} from "react-router-dom";
import {withRouter} from "react-router";
import {menuDesllisantJS} from "./assets/js/menu-lateral";
import * as Constants from './Constants';

/**
 *
 * @author anadal Migració a ROUTER
 */
class MenuDesllisant extends Component {

	constructor() {
		super();
		this.state = {
			idiomes: [],
			items: [], // plugins, menuEnllasos, menupseudoplugin i seccions
			error: null,
			colorMenu: null
		}
		this.canviarIdioma = this.canviarIdioma.bind(this);

		this.canviatIdioma = this.canviatIdioma.bind(this);
		i18n.on('languageChanged', this.canviatIdioma);
	}

	canviatIdioma(lng) {
		// console.log(" CANVIAT IDIOMA EN MENU DESLLISTANT A ]" + lng + "[")

		this.componentDidMount();
	}


	componentDidMount() {

		// console.log("MENU DESLISSTANT componentDidMount()");

		var baseURL = sessionStorage.getItem('contextPath');

		var autenticat = sessionStorage.getItem('autenticat');
		var url;
		if (autenticat === '1') {
			// 0 == Nivell Arell
			url = baseURL + `/webui/fullinfosortedauth/0`;
		} else {
			// 0 == Nivell Arell
			url = baseURL + `/webui/fullinfosorted/0`;
		}

		axios.get(url)
			.then(res => {
				var fulldata = res.data;
				this.setState({
					idiomes: fulldata.idiomesFront,
					items: fulldata.items
				});
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

		var url2 = baseURL + `/webui/infoEntitat`;

		axios.get(url2)
			.then(res => {
				this.setState({ colorMenu : res.data.color });
			})
			.catch(error => {
				console.log(JSON.stringify(error));
				if (error.response) {
					console.log("error.response.data: " + error.response.data);
					console.log("error.response.status: " + error.response.status);
					console.log("error.response.headers: " + error.response.headers);
				}
				this.setState({
					loading:false,
					entitats: "",
					error: JSON.stringify(error)
				});
			});

		menuDesllisantJS();

		$( ".imc--accessibilitat" )
			.focusout(function() {
				$( ".imc-marc-menu").css("transform", "");
			});

	}


	mostrarPlugin(gravetat, missatge, pluginContext, tipus) {
		switch (gravetat) {
			case 0:
				// No fer res
				break;
			case 1:
			case 2:
				// Mostrar Alert
				alert(missatge);
				break;
			case 3:
				// Mostrar Alert
				alert(missatge);
				return;
		}



		var url;
		switch(tipus) {

		
			case -1: // Plugin html public
				url = Constants.PLUGINHTML_PUBLIC_PATH + pluginContext;
			break;
			case -2: // Plugin react public	
			  url = Constants.PLUGINREACT_PUBLIC_PATH + pluginContext;				
			break;
			case 0: // Plugin react
				url = Constants.PLUGINREACT_PATH + pluginContext;
			break;
			case 1: // Plugin html
				url = Constants.PLUGINHTML_PATH + pluginContext;
			break;
			default:
				alert("El sistema no reconeix un plugin amb tipus " + tipus);
				return;

		}


		this.props.history.push(url);
		
	}




	canviarIdioma(lng) {

		// Canviam servidor
		var baseURL = sessionStorage.getItem('contextPath');

		var url = baseURL + `/webui/canviarIdioma/${lng}`;
		axios.get(url)
			.then(res => {
				const lang = res.data;

				// Canviam en local
				sessionStorage.setItem("langActual", lng);
				i18n.changeLanguage(lng);

			}).catch(error => {
				alert("No s'ha pogut actualitzar l´idioma. Error: " + error);
			});

			// Canviam lang al html
			$(document).ready(function() {
				$("html").attr("lang", lng).attr("xml:lang", lng);
			});


		var f = document.getElementById("imc--fons");
		f.click();
	}

	render() {

		// console.log("MENU DESLLISSANT RENDER ");

		const { t } = this.props;
		var autenticat = sessionStorage.getItem('autenticat');
		var urlBase = sessionStorage.getItem('contextPath');

		const idiomes = this.state.idiomes;

		// console.log(" MENU DESLLISSANT IDIOMES: " + idiomes);

		var idioma_seleccionat;
		var idiomes_seleccionables;
		var langActual = sessionStorage.getItem("langActual");
		if (langActual === null) {
			// console.log("Canvi idioma null a ca");
			langActual = 'ca';
			sessionStorage.setItem("langActual", "ca");
		}
		console.log("Idioma actual: " + langActual);

		idioma_seleccionat = idiomes.filter(s => s === langActual).map((s, i) => (
			<strong className="lletraIdioma" key={i} lang={s}>{t('menuIdioma_' + s)}</strong>
		));
		idiomes_seleccionables = idiomes.filter(s => s !== langActual).map((s, i) => (
			<span key={i}><button onClick={() => this.canviarIdioma(s)}
				className="boton-menu lletraIdioma" tabIndex={201+i} aria-labelledby={t('menuIdioma_' + s)} aria-describedby={t('accedirIdioma') + t('menuIdioma_' + s)} lang={s}>{t('menuIdioma_' + s)}</button> \ </span>
		));

		let allItems = [];

		if (this.state.error) {
			allItems = <div className="alert alert-danger" role="alert">{this.state.error}</div>;
		} else {

			this.state.items.forEach((s, i) => {
				switch (s.tipus) {


					case -1: // Plugin html public
					case -2: // Plugin react public					
					case 0: // Plugin react
					case 1: // Plugin html
						allItems.push(
							<li key={i}>
								<button title={s.missatge} className={"botoMenu alert" + s.gravetat + "menu"}
										onClick={(event) => this.mostrarPlugin(s.gravetat, s.missatge, s.context, s.tipus)} tabIndex={205+i} aria-labelledby={s.nom} aria-describedby={t('accedirPlugin') + s.nom}>
									<img src={urlBase + s.urllogo} className="imc-icona" title="" alt={s.nom} />
									<span>{s.nom} </span>
								</button>
							</li>)
						break;

					case 2: // Enllaz
						allItems.push(<li key={i}>
							<a href={s.url} title={s.nom} target="_blank" tabIndex={205+i} aria-labelledby={s.nom} aria-describedby={t('accedirEnllas') + s.nom}>
								<img src={s.urllogo} title="" alt={s.nom} className="imc-icona iconaEnllas"/>
								<span>{s.nom}</span>
							</a>
						</li>);
						break;

					case 3: // Seccio
						allItems.push(<li key={i}>
							<Link to={Constants.SECCIO_PATH + s.context} tabIndex={205+i} aria-labelledby={s.nom} aria-describedby={t('accedirSeccio') + s.nom}>
								<img src={s.urllogo} title="" alt={s.descripcio} className="imc-icona iconaEnllas"/>
								<span>{s.nom} </span>
							</Link>
						</li>)
						break;

					case 4: // PseudoPlugin
						allItems.push(<li key={i}>
							<a href={s.url} target="_blank" title={s.nom} tabIndex={205+i} aria-labelledby={s.nom} aria-describedby={t('accedirPlugin') + s.nom}>
								<img src={s.urllogo} title="" alt={s.nom} className="imc-icona iconaEnllas"/>
								<span>{s.nom} </span>
							</a>
						</li>);
						break;

				}
			})


			var numEntitats = sessionStorage.getItem("numEntitats");
			var canviarDeFront = sessionStorage.getItem("canviarDeFront");
			if (canviarDeFront === 'true' && numEntitats > 1) {
				allItems.push(<li key="canvent">
					<Link to={{pathname: `/canviarEntitat`, nomPagina: 'menuCanviarEntitat'}}
						  className="imc-marc-ico imc--canviarEntitat" tabIndex="250" aria-labelledby={t('menuCanviarEntitat')} aria-describedby={t('accedirSeccio') + t('menuCanviarEntitat')}>
						<span>{t('menuCanviarEntitat')}</span>
					</Link>
				</li>);
			}

			if (autenticat === '1') {
				allItems.push(<li key="exit"><a href="sortir" className="imc-marc-ico imc--sortir" id="imc-marc-sortir"
												title={t('menuSortir')} tabIndex="260" aria-labelledby={t('menuSortir')} aria-describedby={t('accedirEnllas') + t('menuSortir')}><span>{t('menuSortir')}</span></a></li>);
			}

			allItems.push(<li key="acc">
				<Link to={{pathname: `/accessibilitat`, nomPagina: 'menuAccessibilitat'}}
					  className="imc-marc-ico imc--accessibilitat" tabIndex="270" aria-labelledby={t('menuAccessibilitat')} aria-describedby={t('accedirSeccio') + t('menuAccessibilitat')}>
					<span>{t('menuAccessibilitat')}</span>
				</Link>
			</li>);

		}

		const styleColorMenu = (this.state.colorMenu === null)? { backgroundColor : '#32814B'} : { backgroundColor : "#"+this.state.colorMenu};

		return (
			<div>
				<div className="imc-cercador" id="imc-cercador" style={styleColorMenu}>
				</div>
				<ul>
					<li className="imc-marc-ico imc--idioma">
						{idiomes_seleccionables} {idioma_seleccionat}
					</li>
					{allItems}
				</ul>
			</div>
		);
	}
}

export default withTranslation()(withRouter(MenuDesllisant));

