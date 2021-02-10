import React, { Component, Suspense } from 'react';
import { withTranslation } from 'react-i18next';
import i18n from 'i18next';
import axios from 'axios'
import LlistatDePlugins from './LlistatDePlugins';

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
	}

	componentWillMount() {
		var url = window.location.href + `pluginfront/veureplugins`;
		axios.get(url).then(res => {
			this.setState({ plugins: res.data })
		});
		var url2 = window.location.href + `webui/menuslidelinks`;
		axios.get(url2).then(res => {
			this.setState({ menuEnllasos: res.data })
		});

		var url3 = window.location.href + `webui/idiomesFront`;
		axios.get(url3).then(res => {
			this.setState({ idiomes: res.data })
		})

		var url4 = window.location.href + `webui/menupseudoplugin`;
		axios.get(url4).then(res => {
			this.setState({ menupseudoplugin: res.data })
		});

		// 0 == Nivell Arell        
        var url5 = window.location.href + `webui/seccions/0`;
		axios.get(url5).then(res => {
			this.setState({ seccions: res.data })
        });

		
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
		});
		var url3 = window.location.href + `webui/idiomesFront`;
		axios.get(url3).then(res => {
			this.setState({ idiomes: res.data })
		})

		var url4 = window.location.href + `webui/menupseudoplugin`;
		axios.get(url4).then(res => {
			this.setState({ menupseudoplugin: res.data })
		});

		// 0 == Nivell Arell        
		var url5 = window.location.href + `webui/seccions/0`;
		axios.get(url5).then(res => {
			this.setState({ seccions: res.data })
		});
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

	mostrarNovaSeccio(seccioID) {

        console.log("DESLLISSANT Entra a mostrarNovaSeccio");
        const { t } = this.props;
        ReactDOM.render(<LlistatDePlugins seccioID={seccioID}  t={t} />, document.getElementById('contingut'));
        console.log("DESLLISSTANT Surt mostrarNovaSeccio");

    }

	render() {

		const {t} = this.props;
		var autenticat = this.props.autenticat;
		var urlBase = window.location.href;
		var defaultEntityCode = sessionStorage.getItem("defaultEntityCode");
		var canviarDeFront = sessionStorage.getItem("canviarDeFront");
		var numEntitats = sessionStorage.getItem("numEntitats");

		var seccionsS;
		seccionsS = this.state.seccions.map(s => (
			<li>
				<a href="javascript:" onClick={() => this.mostrarNovaSeccio(s.seccioID) } title={s.nom}>
					<img src={s.iconaID} title="" alt={s.descripcio} className="imc-icona iconaEnllas" />
					<span>{s.nom}</span>
				</a>
			</li>

        ));


		let enllasosMenu;
		if(!this.state.menuEnllasos.length){
			enllasosMenu = "";
		} else{
			enllasosMenu = this.state.menuEnllasos.map((s, i) => (
				<li>
					<a href={s.url} title={s.nom}>
						<img src={s.urllogo} title="" alt="" className="imc-icona iconaEnllas" />
						<span>{s.label}</span>
					</a>
				</li>
			))
		}


		let enllasosPseusoPluginMenu;

		if(!this.state.menupseudoplugin.length){
			enllasosPseusoPluginMenu = "";
		} else {
			enllasosPseusoPluginMenu = this.state.menupseudoplugin.map((s, i) => (
				<li>
					<a href={s.url} target="_blank" title={s.nom}>
						<img src={s.urllogo} title="" alt="" className="imc-icona iconaEnllas" />
						<span>{s.label}</span>
					</a>
				</li>
			))
		}



		const idiomes = this.state.idiomes;

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
			boto_ca = idiomes.filter(s => s === 'ca').map(s => (
				<strong className="lletraIdioma">{t('menuIdioma_ca')}</strong>
			));
			boto_es = idiomes.filter(s => s === 'es').map(s => (
				<button onClick={() => i18n.changeLanguage('es')}
						className="boton-menu lletraIdioma">{t('menuIdioma_es')}</button>
			));
			boto_en = idiomes.filter(s => s === 'en').map(s => (
				<button onClick={() => i18n.changeLanguage('en')}
						className="boton-menu lletraIdioma">{t('menuIdioma_en')}</button>
			));
		}

		if (i18n.language === 'es') {
			boto_ca = idiomes.filter(s => s === 'ca').map(s => (
				<button onClick={() => i18n.changeLanguage('ca')}
					className="boton-menu lletraIdioma">{t('menuIdioma_ca')}</button>
			));
			boto_es = idiomes.filter(s => s === 'es').map(s => (
				<strong className="lletraIdioma">{t('menuIdioma_es')}</strong>
			));
			boto_en = idiomes.filter(s => s === 'en').map(s => (
				<button onClick={() => i18n.changeLanguage('en')}
						className="boton-menu lletraIdioma">{t('menuIdioma_en')}</button>
			));
		}

		if (i18n.language === 'en') {
			boto_ca = idiomes.filter(s => s === 'ca').map(s => (
				<button onClick={() => i18n.changeLanguage('ca')}
						className="boton-menu lletraIdioma">{t('menuIdioma_ca')}</button>
			));
			boto_es = idiomes.filter(s => s === 'es').map(s => (
				<button onClick={() => i18n.changeLanguage('es')}
						className="boton-menu lletraIdioma">{t('menuIdioma_es')}</button>
			));
			boto_en = idiomes.filter(s => s === 'en').map(s => (
				<strong className="lletraIdioma">{t('menuIdioma_en')}</strong>
			));
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
					className="imc-icona" title="" alt="" /><span>{s.nom}</span></a></li>
			));
			plugHtmlInfo = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 1).map(s => (
				<li><button title={s.nom} className="botoMenu alert1menu" onClick={(event) => this.infoHtml(s.missatge,s.pluginID)}><img
					src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
					className="imc-icona" title="" alt="" /><span>{s.nom}</span></button></li>
			));
			plugHtmlWarning = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 2).map(s => (
				<li><button title={s.nom} className="botoMenu alert2menu" onClick={(event) => this.infoHtml(s.missatge,s.pluginID)}><img
					src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
					className="imc-icona" title="" alt="" /><span>{s.nom}</span></button></li>
			));
			plugHtmlError = plugins.filter(s => s.reactComponent === 'false').filter(s => s.gravetat === 3).map(s => (
				<li><button title={s.nom} className="botoMenu alert3menu" onClick={(event) => this.error(s.missatge)}><img
					src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
					className="imc-icona" title="" alt="" /><span>{s.nom}</span></button></li>
			));

			plugReact = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 0).map(s => (
				<li><a href={"javascript:newPluginReact('contingut', '1', '" + s.pluginID + "');"}
					title={s.nom}><img
					src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
					className="imc-icona" title="" alt="" /><span>{s.nom}</span></a></li>
			));
			plugReactInfo = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 1).map(s => (
				<li><button title={s.nom} className="botoMenu alert1menu" onClick={(event) => this.infoReact(s.missatge,s.pluginID)}><img
					src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
					className="imc-icona" title="" alt="" /><span>{s.nom}</span></button></li>
			));
			plugReactWarning = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 2).map(s => (
				<li><button title={s.nom} className="botoMenu alert2menu" onClick={(event) => this.infoReact(s.missatge,s.pluginID)}><img
					src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
					className="imc-icona" title="" alt="" /><span>{s.nom}</span></button></li>
			));
			plugReactError = plugins.filter(s => s.reactComponent === 'true').filter(s => s.gravetat === 3).map(s => (
				<li><button title={s.nom} className="botoMenu alert3menu" onClick={(event) => this.error(s.missatge)}><img
					src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
					className="imc-icona" title="" alt="" /><span>{s.nom}</span></button></li>
			));

		}

		if (autenticat === '0') {
			accessibilitat = <li><a href="javascript:newAccessibilitat('contingut', '0');"
									className="imc-marc-ico imc--accessibilitat" id="imc-marc-accessibilitat"
									title={t('menuAccessibilitat')}><span>{t('menuAccessibilitat')}</span></a></li>;
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

		return (
			<div className="imc-marc" id="imc-marc" tabIndex="-1" aria-hidden="true">
				<div className="imc--fons"></div>

				<div className="imc-marc-menu" id="imc-marc-menu" aria-hidden="true">
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
			</div>
		);
	}
}

export default withTranslation()(MenuDesllisant);
