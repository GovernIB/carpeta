import React, { Component, Suspense } from 'react';
import { withTranslation } from 'react-i18next';
import i18n from 'i18next';
import axios from 'axios';
import { Link } from "react-router-dom";
import { withRouter } from "react-router";
import { menuDesllisantJS } from "./assets/js/menu-lateral";
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
			items: [] // plugins, menuEnllasos, menupseudoplugin i seccions
		}
		this.canviarIdioma = this.canviarIdioma.bind(this);

		this.canviatIdioma = this.canviatIdioma.bind(this);
		i18n.on('languageChanged', this.canviatIdioma);
	}

	canviatIdioma(lng) {
		console.log(" CANVIAT IDIOMA EN MENU DESLLISTANT A ]" + lng + "[")

		this.componentDidMount();
	}


	componentDidMount() {

		console.log("MENU DESLISSTANT componentDidMount()");

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

		axios.get(url).then(res => {
			var fulldata = res.data;
			this.setState({
				idiomes: fulldata.idiomesFront,
				items: fulldata.items
			});
		});

		menuDesllisantJS();

	}


	mostrarPlugin(gravetat, missatge, pluginContext, reactComponent) {
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

		if (reactComponent) {
			this.props.history.push(Constants.PLUGINREACT_PATH + pluginContext);
		} else {
			this.props.history.push(Constants.PLUGINHTML_PATH + pluginContext);
		}
	}




	canviarIdioma(lng) {

		// Canviam servidor
		var baseURL = sessionStorage.getItem('contextPath');

		var url = baseURL + `/webui/canviarIdioma/${lng}`;
		axios.get(url).then(res => {
			const lang = res.data;

			// Canviam en local
			sessionStorage.setItem("langActual", lng);
			i18n.changeLanguage(lng);

		}).catch(error => {
			alert("No s'ha pogut actualitzar l´idioma. Error: " + error);
		});


		var f = document.getElementById("imc--fons");
		f.click();
	}

	render() {

		console.log("MENU DESLLISSANT RENDER ");

		const { t } = this.props;
		var autenticat = sessionStorage.getItem('autenticat');
		var urlBase = sessionStorage.getItem('contextPath');

		const idiomes = this.state.idiomes;

		console.log(" MENU DESLLISSANT IDIOMES: " + idiomes);

		var idioma_seleccionat;
		var idiomes_seleccionables;
		var langActual = sessionStorage.getItem("langActual");
		if (langActual === null) {
			console.log("Canvi idioma null a ca");
			langActual = 'ca';
			sessionStorage.setItem("langActual", "ca");
		}
		console.log("idioma actual: " + langActual);

		idioma_seleccionat = idiomes.filter(s => s === langActual).map((s, i) => (
			<strong className="lletraIdioma" key={i}>{t('menuIdioma_' + s)}</strong>
		));
		idiomes_seleccionables = idiomes.filter(s => s !== langActual).map((s, i) => (
			<span key={i}><button onClick={() => this.canviarIdioma(s)}
				className="boton-menu lletraIdioma">{t('menuIdioma_' + s)}</button> \ </span>
		));

		let allItems = [];


		allItems.push(<li key="acc">
			<Link to={{ pathname: `/accessibilitat`, nomPagina: 'menuAccessibilitat' }} className="imc-marc-ico imc--accessibilitat">
				<span>{t('menuAccessibilitat')}</span>
			</Link>
		</li>);

		this.state.items.forEach((s, i) => {
			switch (s.tipus) {

				case 0: // Plugin react
				case 1: // Plugin html
					allItems.push(
						<li key={i}>
							<button title={s.missatge} className={"botoMenu alert" + s.gravetat + "menu"}
								onClick={(event) => this.mostrarPlugin(s.gravetat, s.missatge, s.context, s.tipus == 0 ? true : false)}>
								<img src={urlBase + s.urllogo} className="imc-icona" title="" alt="" />
								<span>{s.nom} </span>
							</button>
						</li>)
					break;

				case 2: // Enllaz
					allItems.push(<li key={i}>
						<a href={s.url} title={s.nom} target="_blank">
							<img src={s.urllogo} title="" alt="" className="imc-icona iconaEnllas" />
							<span>{s.nom}</span>
						</a>
					</li>);
					break;

				case 3: // Seccio
					allItems.push(<li key={i}>
						<Link to={Constants.SECCIO_PATH + s.context} >
							<img src={s.urllogo} title="" alt={s.descripcio} className="imc-icona iconaEnllas" />
							<span>{s.nom} </span>
						</Link>
					</li>)
					break;

				case 4: // PseudoPlugin
					allItems.push(<li key={i}>
						<a href={s.url} target="_blank" title={s.nom}>
							<img src={s.urllogo} title="" alt="" className="imc-icona iconaEnllas" />
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
				<Link to={{ pathname: `/canviarEntitat`, nomPagina: 'menuCanviarEntitat' }}
					className="imc-marc-ico imc--canviarEntitat">
					<span>{t('menuCanviarEntitat')}</span>
				</Link>
			</li>);
		}

		if (autenticat === '1') {
			allItems.push(<li key="exit"><a href="sortir" className="imc-marc-ico imc--sortir" id="imc-marc-sortir"
				title={t('menuSortir')}><span>{t('menuSortir')}</span></a></li>);
		}


		return (
			<div>
				<div className="imc-cercador" id="imc-cercador">
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

