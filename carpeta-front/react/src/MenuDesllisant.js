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
				if(JSON.stringify(error).toString().includes("Request failed with status code 500")){
					this.setState({
						error: "error500plugin"
					});
				} else{
					this.setState({
						error: JSON.stringify(error)
					});
				}
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

		$( "#tancaMenu" )
			.focusout(function() {
				$( ".imc-marc-menu").css("transform", "");
				$( ".imc-marc-menu").css("display", "none");
				$("html").removeClass("imc-menu-visible");
			})
			.click(function() {
				$( ".imc-marc-menu").css("transform", "none");
				$("html").removeClass("imc-menu-visible");
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
			<strong className="lletraIdioma pl-0 pr-0" key={i} lang={s}>{t('menuIdioma_' + s)}</strong>
		));
		idiomes_seleccionables = idiomes.filter(s => s !== langActual).map((s, i) => (
			<span key={i} id={"idiomaMenu"+i}><button onClick={() => this.canviarIdioma(s)}
				className="boton-menu lletraIdioma pl-0 pr-0" tabIndex={201+i} aria-labelledby={"idiomaMenu"+i} lang={s}>{t('menuIdioma_' + s)}</button>&nbsp;&nbsp;\&nbsp;&nbsp;</span>
		));

		let allItems = [];

		if (this.state.error) {
			allItems = <div className="alert alert-danger" role="alert">{t(this.state.error)}</div>;
		} else {

			allItems.push(<li key="acc">
				<div>
					<div className="float-left styleLiniaMenu">
						<span className="oi oi-eye iconaMenu" title={t('menuAccessibilitat')}/>
					</div>
					<Link to={{pathname: `/accessibilitat`, nomPagina: 'menuAccessibilitat'}}
						  className="imc-marc-ico imc--accessibilitat margesMenu" tabIndex="207" aria-labelledby="accessibilitatMenu">
						<span id="accessibilitatMenu">{t('menuAccessibilitat')}</span>
					</Link>
				</div>
			</li>);

			this.state.items.forEach((s, i) => {
				switch (s.tipus) {


					case -1: // Plugin html public
					case -2: // Plugin react public
					case 0: // Plugin react
					case 1: // Plugin html
						allItems.push(
							<li key={i}>
								<div>
									<div className="float-left styleLiniaMenu" style={{transform: "translate(0, 25%)"}}>
										<img src={urlBase + s.urllogo} className="iconaMenu" title="" alt={s.nom} />
									</div>
									<button title={s.missatge} className={"botoMenu margesMenu alert" + s.gravetat + "menu"}
											onClick={(event) => this.mostrarPlugin(s.gravetat, s.missatge, s.context, s.tipus)} tabIndex={208+i} aria-labelledby={"botoMenu"+i}>
										{/*<img src={urlBase + s.urllogo} className="imc-icona" title="" alt={s.nom} />*/}
										<span id={"botoMenu"+i} className="ml-2 linkVermell displayMenu">{s.nom} </span>
									</button>
								</div>
							</li>)
						break;

					case 2: // Enllaz
						allItems.push(<li key={i}>
							<div>
								<div className="float-left styleLiniaMenu">
									<img src={s.urllogo} className="iconaEnllas" title="" alt={s.nom} />
								</div>
								<a href={s.url} title={s.nom} target="_blank" tabIndex={208+i} aria-labelledby={"botoMenu"+i} className="margesMenu">
									{/*<img src={s.urllogo} title="" alt={s.nom} className="imc-icona iconaEnllas"/>*/}
									<span id={"botoMenu"+i} className="ml-2 displayMenu">{s.nom}</span>
								</a>
							</div>
						</li>);
						break;

					case 3: // Seccio
						allItems.push(<li key={i}>
							<div>
								<div className="float-left styleLiniaMenu">
									<img src={s.urllogo} className="iconaEnllas" title="" alt={s.descripcio} />
								</div>
								<Link to={Constants.SECCIO_PATH + s.context} tabIndex={208+i} aria-labelledby={"botoMenu"+i} className="margesMenu">
									{/*<img src={s.urllogo} title="" alt={s.descripcio} className="imc-icona iconaEnllas"/>*/}
									<span id={"botoMenu"+i} className="ml-2 displayMenu">{s.nom}</span>
								</Link>
							</div>
						</li>)
						break;

					case 4: // PseudoPlugin
						allItems.push(<li key={i}>
							<div>
								<div className="float-left styleLiniaMenu">
									<img src={s.urllogo} className="iconaEnllas" title="" alt={s.nom} />
								</div>
								<a href={s.url} target="_blank" title={s.nom} tabIndex={208+i} aria-labelledby={"botoMenu"+i} className="margesMenu">
									{/*<img src={s.urllogo} title="" alt={s.nom} className="imc-icona iconaEnllas"/>*/}
									<span id={"botoMenu"+i} className="ml-2 displayMenu">{s.nom} </span>
								</a>
							</div>
						</li>);
						break;

				}
			})


			var numEntitats = sessionStorage.getItem("numEntitats");
			var canviarDeFront = sessionStorage.getItem("canviarDeFront");
			if (canviarDeFront === 'true' && numEntitats > 1) {
				allItems.push(<li key="canvent">
					<div>
						<div className="float-left styleLiniaMenu">
							<span className="oi oi-loop-circular iconaMenu" title={t('menuCanviarEntitat')}/>
						</div>
						<Link to={{pathname: `/canviarEntitat`, nomPagina: 'menuCanviarEntitat'}}
							  className="imc-marc-ico imc--canviarEntitat margesMenu" tabIndex="250" aria-labelledby="canviarEntitatMenu">
							<span id="canviarEntitatMenu">{t('menuCanviarEntitat')}</span>
						</Link>
					</div>
				</li>);
			}

			if (autenticat === '1') {
				allItems.push(<li key="exit">
					<div>
						<div className="float-left styleLiniaMenu">
							<span className="oi oi-power-standby iconaMenu" title={t('menuSortir')}/>
						</div>
						<a href="sortir" className="imc-marc-ico imc--sortir margesMenu" id="imc-marc-sortir"
												title={t('menuSortir')} tabIndex="260" aria-labelledby="sortirMenu"><span id="sortirMenu">{t('menuSortir')}</span></a></div></li>);

			}

			// allItems.push(<li key="config">
			// 	<div>
			// 		<div className="float-left styleLiniaMenu">
			// 			<span className="oi oi-power-standby iconaMenu" title="ConfigAPP"/>
			// 		</div>
			// 		<a href="configureApp" className="imc-marc-ico imc--sortir margesMenu" id="imc-marc-sortir"
			// 		   title="configAPP" tabIndex="261" aria-labelledby="configAPP"><span id="configAPP">ConfigAPP</span></a></div></li>);

		}

		const styleColorMenu = (this.state.colorMenu === null)? { backgroundColor : '#32814B'} : { backgroundColor : "#"+this.state.colorMenu};

		return (
			<div>
				<div className="imc-cercador pr-1" id="imc-cercador" style={styleColorMenu}>
					<button type="button" className="float-right fonsTransparent" id="tancaMenu" title={t('menuHamburguesaTanca')} tabIndex="290"
							aria-label={t('menuHamburguesaTanca')} aria-describedby="tancarMenuHamburguesa">
						<span className="oi oi-x"/>
						<span className="noVisible" id="tancarMenuHamburguesa">{t('accedirBoto') + t('menuHamburguesaTanca')}</span>
					</button>
				</div>
				<ul>
					<li className="imc-marc-ico">
						<div>
							<div className="float-left styleLiniaMenu">
								<span className="oi oi-globe iconaMenu" title={t('menuIdioma')}/>
							</div>
							{idiomes_seleccionables} {idioma_seleccionat}
						</div>
					</li>
					{allItems}
				</ul>
			</div>
		);
	}
}

export default withTranslation()(withRouter(MenuDesllisant));

