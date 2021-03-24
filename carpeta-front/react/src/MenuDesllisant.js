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

	constructor(){
		super();
		this.state = {
			plugins: [],
			menuEnllasos: [],
			idiomes: [],
			menupseudoplugin: [],
			seccions : []
		}
		this.canviarIdioma = this.canviarIdioma.bind(this);

		this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);
    }

    canviatIdioma(lng) {
        console.log(" CANVIAT IDIOMA EN MENU DESLLISTANT A ]" + lng+ "[")
		
        this.componentDidMount();
    }


	componentDidMount() {

		console.log("MENU DESLISSTANT componentDidMount()");

		var baseURL = sessionStorage.getItem('contextPath');

		var autenticat = sessionStorage.getItem('autenticat');;
        if (autenticat === '1') {


			// 0 == Nivell Arell
			var url = baseURL + `/webui/fullinfo/0`;
			axios.get(url).then(res => {
				var fulldata = res.data;
				this.setState({ plugins: fulldata.veureplugins, 
								menuEnllasos: fulldata.menuslidelinks,
								idiomes: fulldata.idiomesFront,
								menupseudoplugin: fulldata.menupseudoplugin,
								seccions: fulldata.seccions});
			});
		} else {

			var url3 =  baseURL + `/webui/idiomesFront`;
			axios.get(url3).then(res => {
				this.setState({ idiomes: res.data });
				console.log("FINAL DE LECTURA DE IDIOMES !!!!! " + this.state.idiomes);
			})

		}

		menuDesllisantJS();

	}

	infoHtml(missatge, pluginContext) {
		alert(missatge);

        this.props.history.push(Constants.PLUGINHTML_PATH + pluginContext);
        
	}

	infoReact(missatge, pluginContext) {
		alert(missatge);
		this.props.history.push(Constants.PLUGINREACT_PATH + pluginContext);
	}

	error(missatge) {
		alert(missatge);
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

		

		const {t} = this.props;
		var autenticat = sessionStorage.getItem('autenticat');
		var urlBase = sessionStorage.getItem('contextPath');
		var canviarDeFront = sessionStorage.getItem("canviarDeFront");
		var numEntitats = sessionStorage.getItem("numEntitats");

		let enllasosMenu;
		if(!this.state.menuEnllasos.length || autenticat === '0') {
			enllasosMenu = "";
		} else {
			enllasosMenu = this.state.menuEnllasos.map((s, i) => (
				<li key={i}>
					<a href={s.url} title={s.nom} target="_blank">
						<img src={s.urllogo} title="" alt="" className="imc-icona iconaEnllas" />
						<span>{s.label}</span>
					</a>
				</li>
			))
		}



		const idiomes = this.state.idiomes;

		console.log(" MENU DESLLISSANT IDIOMES: " + idiomes);

		var idioma_seleccionat;
		var idiomes_seleccionables;
		var langActual = sessionStorage.getItem("langActual");
		console.log("idioma actual: " + langActual);

		idioma_seleccionat = idiomes.filter(s => s === langActual).map((s, i) => (
				<strong className="lletraIdioma" key={i}>{t('menuIdioma_'+ s)}</strong>
			));
		idiomes_seleccionables = idiomes.filter(s => s !== langActual).map((s, i) => (
				<span key={i}><button onClick={() => this.canviarIdioma(s)}
						className="boton-menu lletraIdioma">{t('menuIdioma_' + s)}</button> \ </span>
			));


		const plugins = this.state.plugins;

		var accessibilitat;
		accessibilitat = <li>
		<Link to={{pathname: `/accessibilitat`, nomPagina: 'menuAccessibilitat' }} className="imc-marc-ico imc--accessibilitat">
				<span>{t('menuAccessibilitat')}</span>
		</Link>
		</li>;

		var canviarEntitat = "";
		if(canviarDeFront === 'true' && numEntitats > 1) {
			canviarEntitat = <li>
				<Link to={{pathname: `/canviarEntitat`, nomPagina: 'menuCanviarEntitat'}}
					  className="imc-marc-ico imc--canviarEntitat">
					<span>{t('menuCanviarEntitat')}</span>
				</Link>
			</li>;
		}


		var plugHtml;
		var plugHtmlInfo;
		var plugHtmlWarning;
		var plugHtmlError;
		var plugReact;
		var plugReactInfo;
		var plugReactWarning;
		var plugReactError;
		var sortir;
		var seccionsS;
		var enllasosPseusoPluginMenu;

		if (autenticat === '1') {


			if(!this.state.menupseudoplugin.length ){
				enllasosPseusoPluginMenu = "";
			} else {
				enllasosPseusoPluginMenu = this.state.menupseudoplugin.map((s, i) => (
					<li key={i}>
						<a href={s.url} target="_blank" title={s.nom}>
							<img src={s.urllogo} title="" alt="" className="imc-icona iconaEnllas" />
							<span>{s.label}</span>
						</a>
					</li>
				))
			}

			seccionsS = this.state.seccions.map((s, i) => (
				<li key={i}>

					<Link to={Constants.SECCIO_PATH + s.context} >
						<img src={s.iconaID} title="" alt={s.descripcio} className="imc-icona iconaEnllas" />
						<span>{s.nom}</span>
					</Link>
					{/*</a>*/}
				</li>
			));


			sortir = <li><a href="sortir" className="imc-marc-ico imc--sortir" id="imc-marc-sortir"
							title={t('menuSortir')}><span>{t('menuSortir')}</span></a></li>;


			plugHtml = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 0).map((s,i) => (
				<li key={i}>				
					<Link to={Constants.PLUGINHTML_PATH + s.context} >
					<img src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
					className="imc-icona" title="" alt="" /><span>{s.nom}</span>
					</Link>
				</li>
			));
			plugHtmlInfo = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 1).map((s,i) => (
				<li key={i}><button title={s.nom} className="botoMenu alert1menu" onClick={(event) => this.infoHtml(s.missatge,s.context)}><img
					src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
					className="imc-icona" title="" alt="" /><span>{s.nom}</span></button></li>
			));
			plugHtmlWarning = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 2).map((s,i) => (
				<li key={i}><button title={s.nom} className="botoMenu alert2menu" onClick={(event) => this.infoHtml(s.missatge,s.context)}><img
					src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
					className="imc-icona" title="" alt="" /><span>{s.nom}</span></button></li>
			));
			plugHtmlError = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 3).map(s => (
				<li key={i}><button title={s.nom} className="botoMenu alert3menu" onClick={(event) => this.error(s.missatge)}><img
					src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
					className="imc-icona" title="" alt="" /><span>{s.nom}</span></button></li>
			));

			plugReact = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 0).map((s,i) => (
				<li key={i}>				    
					<Link to={Constants.PLUGINREACT_PATH + s.context} >
					<img src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
					className="imc-icona" title="" alt="" /><span>{s.nom}</span>
					</Link>
					
				</li>
			));
			plugReactInfo = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 1).map((s,i) => (
				<li key={i}><button title={s.nom} className="botoMenu alert1menu" onClick={(event) => this.infoReact(s.missatge,s.context)}><img
					src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
					className="imc-icona" title="" alt="" /><span>{s.nom}</span></button></li>
			));
			plugReactWarning = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 2).map((s,i) => (
				<li key={i}><button title={s.nom} className="botoMenu alert2menu" onClick={(event) => this.infoReact(s.missatge,s.context)}><img
					src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
					className="imc-icona" title="" alt="" /><span>{s.nom}</span></button></li>
			));
			plugReactError = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 3).map((s,i) => (
				<li key={i}><button title={s.nom} className="botoMenu alert3menu" onClick={(event) => this.error(s.missatge)}><img
					src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
					className="imc-icona" title="" alt="" /><span>{s.nom}</span></button></li>
			));

		}

		if (autenticat === '0') {

			seccionsS = '';
			plugHtml = '';
			plugReact = '';
			plugHtmlInfo = '';
			plugHtmlWarning = '';
			plugHtmlError = '';
			plugReactInfo = '';
			plugReactWarning = '';
			plugReactError = '';
			sortir = '';
			enllasosPseusoPluginMenu = '';
		}

		
		return (
			<div>
				<div className="imc-cercador" id="imc-cercador">
				</div>
				<ul>
					{enllasosMenu}
					<li className="imc-marc-ico imc--idioma">
						{idiomes_seleccionables} {idioma_seleccionat}
					</li>
					{accessibilitat}
					{seccionsS}
					{plugHtml}
					{plugHtmlInfo}
					{plugHtmlWarning}
					{plugHtmlError}
					{plugReact}
					{plugReactInfo}
					{plugReactWarning}
					{plugReactError}
					{enllasosPseusoPluginMenu}
					{sortir}
					{canviarEntitat}
				</ul>

			</div>
		);
	}
}

export default withTranslation()(withRouter(MenuDesllisant));

