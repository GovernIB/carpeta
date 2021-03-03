import React, { Component, Suspense } from 'react';
import { withTranslation } from 'react-i18next';
import i18n from 'i18next';
import axios from 'axios'
import LlistatDePlugins from './LlistatDePlugins';
import { Link } from "react-router-dom";
import { withRouter } from "react-router";
import { menuDesllisantJS } from "./assets/js/menu-lateral";
/**
 *
 * @author anadal Migració a ROUTER
 */
class MenuDesllisant extends Component {

	constructor(){
		super();
		this.state = {
			loading: 0,
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
		this.setState({ loading: 0 });

		
	
		

        this.componentDidMount();
    }


	componentDidMount() {

		console.log("MENU DESLISSTANT componentDidMount()");

		var baseURL = sessionStorage.getItem('contextPath');
		var url = baseURL + `/pluginfront/veureplugins`;

		axios.get(url).then(res => {
			this.setState({ plugins: res.data, loading: this.state.loading + 1 });
		});
		var url2 =  baseURL + `/webui/menuslidelinks`;
		axios.get(url2).then(res => {
			this.setState({ menuEnllasos: res.data, loading: this.state.loading + 1 });
		});


		var url3 =  baseURL + `/webui/idiomesFront`;

		axios.get(url3).then(res => {
			this.setState({ idiomes: res.data, loading: this.state.loading + 1 });

			console.log("FINAL DE LESTURA DE IDIOMES !!!!! " + this.state.idiomes);
		})

		var url4 =  baseURL + `/webui/menupseudoplugin`;
		axios.get(url4).then(res => {
			this.setState({ menupseudoplugin: res.data, loading: this.state.loading + 1 });
		});

		// 0 == Nivell Arell

        var url5 =  baseURL + `/webui/seccions/0`;
		axios.get(url5).then(res => {
			this.setState({ seccions: res.data, loading: this.state.loading + 1 });

        });

		menuDesllisantJS();

	}

	infoHtml(missatge, pluginID) {
		alert(missatge);

		if (this.props.history) {
            console.log("MENU DESLLISSANT => EXISTEIX  HISTORY !!!!!");
            this.props.history.push("/pluginhtml/" + pluginID);
        } else {
            console.log("MENU DESLLISSANT => NO PUC LLEGIR HISTORY !!!!!");
        }
	}

	infoReact(missatge, pluginID) {
		alert(missatge);

		if (this.props.history) {
            console.log("MENU DESLLISSANT => EXISTEIX  HISTORY !!!!!");
            this.props.history.push("/pluginreact/" + pluginID);
        } else {
            console.log("MENU DESLLISSANT => NO PUC LLEGIR HISTORY !!!!!");
        }
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
			alert("XYZ ZZZ No s'ha pogut actualitzar l´idioma. Error: " + error);
		});


		var f = document.getElementById("imc--fons");
		f.click();
	}

	render() {

		console.log("MENU DESLLISSANT loading = " + this.state.loading);

		if ( this.state.loading < 5 ) {
			return '';
		}


		const {t} = this.props;
		var autenticat = sessionStorage.getItem('autenticat');
		//var urlBase = window.location.href;
		var urlBase = sessionStorage.getItem('contextPath');
		var defaultEntityCode = sessionStorage.getItem("defaultEntityCode");
		var canviarDeFront = sessionStorage.getItem("canviarDeFront");
		var numEntitats = sessionStorage.getItem("numEntitats");

		let enllasosMenu;
		if(!this.state.menuEnllasos.length || autenticat === '0') {
			enllasosMenu = "";
		} else {
			enllasosMenu = this.state.menuEnllasos.map((s, i) => (
				<li key={i}>
					<a href={s.url} title={s.nom}>
						<img src={s.urllogo} title="" alt="" className="imc-icona iconaEnllas" />
						<span>{s.label}</span>
					</a>
				</li>
			))
		}



		const idiomes = this.state.idiomes;

		console.log("\n MENU DESLLISSANT IDIOMES: " + idiomes);

		var boto_ca;
		var boto_es;
		var boto_en;
		var separacio1 = "";
		var separacio2 = "";

		if(idiomes.length === 2){
			separacio1 = "\\";
		}
		if(idiomes.length === 3){
			separacio1 = "\\";
			separacio2 = "\\";
		}

		if (i18n.language === 'ca') {
			boto_ca = idiomes.filter(s => s === 'ca').map((s, i)  => (
				<strong className="lletraIdioma" key={i}>{t('menuIdioma_ca')}</strong>
			));
			boto_es = idiomes.filter(s => s === 'es').map((s, i)  => (
				<button onClick={() => this.canviarIdioma('es')}
						className="boton-menu lletraIdioma" key={i}>{t('menuIdioma_es')}</button>
			));
			boto_en = idiomes.filter(s => s === 'en').map((s, i)  => (
				<button onClick={() => this.canviarIdioma('en')}
						className="boton-menu lletraIdioma" key={i}>{t('menuIdioma_en')}</button>
			));
		}

		if (i18n.language === 'es') {
			boto_ca = idiomes.filter(s => s === 'ca').map((s, i)  => (
				<button onClick={() => this.canviarIdioma('ca')}
					className="boton-menu lletraIdioma" key={i}>{t('menuIdioma_ca')}</button>
			));
			boto_es = idiomes.filter(s => s === 'es').map((s, i)  => (
				<strong className="lletraIdioma" key={i}>{t('menuIdioma_es')}</strong>
			));
			boto_en = idiomes.filter(s => s === 'en').map((s, i)  => (
				<button onClick={() => this.canviarIdioma('en')}
						className="boton-menu lletraIdioma" key={i}>{t('menuIdioma_en')}</button>
			));
		}

		if (i18n.language === 'en') {
			boto_ca = idiomes.filter(s => s === 'ca').map((s, i)  => (
				<button onClick={() => this.canviarIdioma('ca')}
						className="boton-menu lletraIdioma" key={i}>{t('menuIdioma_ca')}</button>
			));
			boto_es = idiomes.filter(s => s === 'es').map((s, i)  => (
				<button onClick={() => this.canviarIdioma('es')}
						className="boton-menu lletraIdioma" key={i}>{t('menuIdioma_es')}</button>
			));
			boto_en = idiomes.filter(s => s === 'en').map((s, i)  => (
				<strong className="lletraIdioma" key={i}>{t('menuIdioma_en')}</strong>
			));
		}


		const plugins = this.state.plugins;

		var accessibilitat;
		accessibilitat = <li>
		<Link to={{pathname: `/accessibilitat`, nomPagina: 'menuAccessibilitat' }} className="imc-marc-ico imc--accessibilitat">
	{/*<a href="javascript:newwAccessibilitat('contingut', '1');"
							className="imc-marc-ico imc--accessibilitat" id="imc-marc-accessibilitat"
							title={t('menuAccessibilitat')}> */ }
				<span>{t('menuAccessibilitat')}</span>
		</Link>{/*</a>*/}
		</li>;


		var plugHtml;
		var plugHtmlInfo;
		var plugHtmlWarning;
		var plugHtmlError;
		var plugReact;
		var plugReactInfo;
		var plugReactWarning;
		var plugReactError;
		var sortir;
		var canviarEntitat;
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

					<Link to={"/seccio/" + s.seccioID} >
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
				{/*
				<a href={"javascript:newwPluginnHtml('contingut', '1', '" + s.pluginID + "');"} title={s.nom}> */}
					<Link to={"/pluginhtml/" + s.pluginID} >
					<img src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
					className="imc-icona" title="" alt="" /><span>{s.nom}</span>
					</Link>
					{/*</a> */}
				</li>
			));
			plugHtmlInfo = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 1).map((s,i) => (
				<li key={i}><button title={s.nom} className="botoMenu alert1menu" onClick={(event) => this.infoHtml(s.missatge,s.pluginID)}><img
					src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
					className="imc-icona" title="" alt="" /><span>{s.nom}</span></button></li>
			));
			plugHtmlWarning = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 2).map((s,i) => (
				<li key={i}><button title={s.nom} className="botoMenu alert2menu" onClick={(event) => this.infoHtml(s.missatge,s.pluginID)}><img
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
				    {/*<a href={"javascript:newwPluginnReact('contingut', '1', '" + s.pluginID + "');"} title={s.nom}>*/}
					<Link to={"/pluginreact/" + s.pluginID} >
					<img src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
					className="imc-icona" title="" alt="" /><span>{s.nom}</span>
					</Link>
					{/*</a>*/}
				</li>
			));
			plugReactInfo = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 1).map((s,i) => (
				<li key={i}><button title={s.nom} className="botoMenu alert1menu" onClick={(event) => this.infoReact(s.missatge,s.pluginID)}><img
					src={urlBase + "/pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
					className="imc-icona" title="" alt="" /><span>{s.nom}</span></button></li>
			));
			plugReactWarning = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 2).map((s,i) => (
				<li key={i}><button title={s.nom} className="botoMenu alert2menu" onClick={(event) => this.infoReact(s.missatge,s.pluginID)}><img
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

		if(canviarDeFront === 'true' && numEntitats > 1){
			if (i18n.language === 'ca') {
				canviarEntitat =
					<li><a href="/carpetafront/entitat?lang=ca" className="imc-marc-ico imc--registres"
						title={t('menuEntitat')}><span>{t('menuEntitat')}</span></a></li>;
			}
			if (i18n.language === 'es') {
				canviarEntitat =
					<li><a href="/carpetafront/entitat?lang=es" className="imc-marc-ico imc--registres"
						title={t('menuEntitat')}><span>{t('menuEntitat')}</span></a></li>;
			}
			if (i18n.language === 'en') {
				canviarEntitat =
					<li><a href="/carpetafront/entitat?lang=en" className="imc-marc-ico imc--registres"
						title={t('menuEntitat')}><span>{t('menuEntitat')}</span></a></li>;
			}
		} else{
			canviarEntitat = "";
		}

		console.log("MENUDESLLISSANT :: INICI RENDER ");



		return (
			<div>
				<div className="imc-cercador" id="imc-cercador">
				</div>
				<ul>
					{enllasosMenu}
					<li className="imc-marc-ico imc--idioma">
						{boto_ca} {separacio1} {boto_es} {separacio2} {boto_en}
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

