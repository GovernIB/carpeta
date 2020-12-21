import React, { Component, Suspense } from 'react';
import { withTranslation } from 'react-i18next';
import i18n from 'i18next';
import axios from 'axios'

class MenuDesllisant extends Component {

	constructor(){
		super();
		this.state = {
			plugins: [],
			menuEnllasos: []
		}
	}

	componentWillMount() {
		var url = window.location.href + `pluginfront/veureplugins`;
		axios.get(url).then(res => {
			this.setState({ plugins: res.data })
		});
		var url2 = window.location.href + `webui/menuslidelinks`;
		axios.get(url2).then(res => {
			this.setState({ menuEnllasos: res.data })
		})
	}

	infoHtml(missatge, pluginID) {
		alert(missatge);
		var contingut = newPluginHtml('contingut', '1', pluginID);
		window.location.href(contingut);
	}

	infoReact(missatge, pluginID) {
		alert(missatge);
		var contingut = newPluginReact('contingut', '1', pluginID);
		window.location.href(contingut);
	}

	error(missatge) {
		alert(missatge);
	}

	componentWillReceiveProps(lng) {
		var url = window.location.href + `pluginfront/veureplugins`;
		axios.get(url).then(res => {
			this.setState({ plugins: res.data })
		});
		var url2 = window.location.href + `webui/menuslidelinks`;
		axios.get(url2).then(res => {
			this.setState({ menuEnllasos: res.data })
		})
	}

	componentDidMount() {
		i18n.on('languageChanged', function(lng) {
			sessionStorage.setItem("langActual", lng);
			var url = window.location.href + `webui/canviarIdioma/${lng}`;
			axios.get(url).then(res => {
				const lang = res.data;
			})
		});
	}

	render() {

		const {t} = this.props;
		var autenticat = this.props.autenticat;
		var urlBase = window.location.href;
		var defaultEntityCode = sessionStorage.getItem("defaultEntityCode");
		var canviarDeFront = sessionStorage.getItem("canviarDeFront");
		var numEntitats = sessionStorage.getItem("numEntitats");

		let enllasosMenu;

		if(!this.state.menuEnllasos.length){
			enllasosMenu = "";
		} else{
			enllasosMenu = this.state.menuEnllasos.map((s, i) => (
				<li>
					<a href={s.url} title={s.nom}>
						<img src={s.urllogo} title={s.label} alt={s.label} className="imc-icona iconaEnllas"></img>
						<span>{s.label}</span>
					</a>
				</li>
			))
		}


		var boto_ca;
		if (i18n.language === 'ca') {
			boto_ca = <strong>{t('menuIdioma_ca')}</strong>;
		} else {
			boto_ca =
				<button onClick={() => i18n.changeLanguage('ca')} className="boton-menu">{t('menuIdioma_ca')}</button>;
		}

		var boto_es;
		if (i18n.language === 'es') {
			boto_es = <strong>{t('menuIdioma_es')}</strong>;
		} else {
			boto_es =
				<button onClick={() => i18n.changeLanguage('es')} className="boton-menu">{t('menuIdioma_es')}</button>;
		}


		const plugins = this.state.plugins;

		var accessibilitat;
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
		if (autenticat === '1') {
			accessibilitat = <li><a href="javascript:newAccessibilitat('contingut', '1');"
									className="imc-marc-ico imc--accessibilitat" id="imc-marc-accessibilitat"
									title={t('menuAccessibilitat')}><span>{t('menuAccessibilitat')}</span></a></li>;
			sortir = <li><a href="sortir" className="imc-marc-ico imc--sortir" id="imc-marc-sortir"
							title={t('menuSortir')}><span>{t('menuSortir')}</span></a></li>;

			plugHtml = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 0).map(s => (
				<li><a href={"javascript:newPluginHtml('contingut', '1', '" + s.pluginID + "');"}
					   title={s.nom}><img
					src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
					className="imc-icona"></img><span>{s.nom}</span></a></li>
			));
			plugHtmlInfo = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 1).map(s => (
				<li><button title={s.nom} className="botoMenu alert1menu" onClick={(event) => this.infoHtml(s.missatge,s.pluginID)}><img
					src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
					className="imc-icona"></img><span>{s.nom}</span></button></li>
			));
			plugHtmlWarning = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 2).map(s => (
				<li><button title={s.nom} className="botoMenu alert2menu" onClick={(event) => this.infoHtml(s.missatge,s.pluginID)}><img
					src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
					className="imc-icona"></img><span>{s.nom}</span></button></li>
			));
			plugHtmlError = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 3).map(s => (
				<li><button title={s.nom} className="botoMenu alert3menu" onClick={(event) => this.error(s.missatge)}><img
					src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
					className="imc-icona"></img><span>{s.nom}</span></button></li>
			));

			plugReact = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 0).map(s => (
				<li><a href={"javascript:newPluginReact('contingut', '1', '" + s.pluginID + "');"}
					   title={s.nom}><img
					src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
					className="imc-icona"></img><span>{s.nom}</span></a></li>
			));
			plugReactInfo = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 1).map(s => (
				<li><button title={s.nom} className="botoMenu alert1menu" onClick={(event) => this.infoReact(s.missatge,s.pluginID)}><img
					src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
					className="imc-icona"></img><span>{s.nom}</span></button></li>
			));
			plugReactWarning = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 2).map(s => (
				<li><button title={s.nom} className="botoMenu alert2menu" onClick={(event) => this.infoReact(s.missatge,s.pluginID)}><img
					src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
					className="imc-icona"></img><span>{s.nom}</span></button></li>
			));
			plugReactError = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 3).map(s => (
				<li><button title={s.nom} className="botoMenu alert3menu" onClick={(event) => this.error(s.missatge)}><img
					src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
					className="imc-icona"></img><span>{s.nom}</span></button></li>
			));

		}
		if (autenticat === '0') {
			accessibilitat = <li><a href="javascript:newAccessibilitat('contingut', '0');"
									className="imc-marc-ico imc--accessibilitat" id="imc-marc-accessibilitat"
									title={t('menuAccessibilitat')}><span>{t('menuAccessibilitat')}</span></a></li>;
			plugHtml = '';
			plugReact = '';
			plugHtmlInfo = '';
			plugHtmlWarning = '';
			plugHtmlError = '';
			plugReactInfo = '';
			plugReactWarning = '';
			plugReactError = '';
			sortir = '';
		}

		if(canviarDeFront === 'true' && numEntitats > 1){
			canviarEntitat = <li><a href="/carpetafront/entitat" className="imc-marc-ico imc--registres" title={t('menuEntitat')}><span>{t('menuEntitat')}</span></a></li>;
		} else{
			canviarEntitat = "";
		}

		return (
			<div className="imc-marc" id="imc-marc" tabIndex="-1" aria-hidden="true">
				<div className="imc--fons"></div>

				<div className="imc-marc-menu" id="imc-marc-menu" aria-hidden="true">
					<div className="imc-cercador" id="imc-cercador">
					</div>
					<ul>
						{enllasosMenu}
						<li className="imc-marc-ico imc--idioma">
							{boto_ca} \ {boto_es}
						</li>
						{accessibilitat}
						{plugHtml}
						{plugHtmlInfo}
						{plugHtmlWarning}
						{plugHtmlError}
						{plugReact}
						{plugReactInfo}
						{plugReactWarning}
						{plugReactError}
						{sortir}
						{canviarEntitat}
					</ul>
				</div>
			</div>
		);
	}
}

export default withTranslation()(MenuDesllisant);
