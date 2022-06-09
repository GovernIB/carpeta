import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import axios from "axios";
import i18n from 'i18next';
import {Link} from "react-router-dom";

class BarraMenu extends Component {

	constructor(){
		super();
		this.state = {
			enllasosMenuBar: null,
			colorMenu: null,
			error: null
		}
		this.canviatIdioma = this.canviatIdioma.bind(this);
        i18n.on('languageChanged', this.canviatIdioma);
    }

    canviatIdioma(lng) {
        // console.log(" CANVIAT IDIOMA EN BarraMenu A ]" + lng+ "[");
        this.componentDidMount();
    }


	componentDidMount() {

		var auth = sessionStorage.getItem('autenticat');

		// if (auth ==='1') {
			var baseURL = sessionStorage.getItem('contextPath');
			var url = baseURL + `/webui/menubarlinks`;

			axios.get(url)
				.then(res => {
					this.setState({ enllasosMenuBar : res.data });
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
		// }

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

	}


	render() {

        const { t } = this.props;
		var auth = sessionStorage.getItem('autenticat');

		let content;

		if (this.state.error) {
			content = <div className="alert alert-danger" role="alert">{this.state.error}</div>;
		} else {

			// if(auth === '0' || !this.state.enllasosMenuBar ){
			if (!this.state.enllasosMenuBar) {
				content = "";
			} else {
				content = this.state.enllasosMenuBar.map((s, i) => (
					<li className="itemBar pl-5" key={i} id={"enllas"+i}>
						<a href={s.url} className="imc-bt-menubar pl-0" target="_blank" tabIndex={111+i} aria-labelledby={"enllas"+i}>
							<img src={s.urllogo} title="" alt={s.label} className="logoMenuBar"/>
							<span>{s.label}</span>
						</a>
					</li>
				))
			}
		}

		const styleColorMenu = (this.state.colorMenu === null)? { backgroundColor : '#32814B'} : { backgroundColor : "#"+this.state.colorMenu};
		let colorMenu = (this.state.colorMenu === null)? '#32814B' : "#"+this.state.colorMenu;
		sessionStorage.setItem('colorBarra', colorMenu);

		// return <div id = "barraMenu" className="ocultarMobil">
		return <div id = "barraMenu">
			<header className="imc-titol barraApp" style={styleColorMenu} id="headerBarra">

				<nav className="">
					<div className="row imc--contingut posHambApp">

					{/*<a href="{this.context.router.goBack()}" className="imc-torna"*/}
					{/*   title={t('menuTorna')}><span>{t('menuTorna')}</span></a>*/}

						<div className="col-11 pl-0">
							<h1 id="inici" className="ocultarMobil">
								<Link to={'/'} className="titolAplicacio" tabIndex="101" aria-labelledby="inici">{t('menuTitol')}</Link>
							</h1>

							<ul className="ocultarMobil">
								{content}
							</ul>
						</div>

						<div className="col-1 pr-0">
							<button type="button" className="imc-bt-menu float-right" id="imc-bt-menu" title={t('menuMenu')} tabIndex="190" aria-label={t('menuHamburguesa')} aria-describedby="menuHamburguesa">
								<span className="noVisible" id="menuHamburguesa">{t('accedirBoto') + t('menuHamburguesa')}</span>
							</button>
						</div>
					</div>
				</nav>

			</header>
		</div>
	}
}

export default withTranslation()(BarraMenu);
