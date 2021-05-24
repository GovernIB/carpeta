import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import axios from "axios";
import i18n from 'i18next';

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
					<li className="itemBar pl-5" key={i}>
						<a href={s.url} className="imc-bt-menubar pl-0" target="_blank">
							<img src={s.urllogo} title="" alt={s.label} className="logoMenuBar"/>
							<span>{s.label}</span>
						</a>
					</li>
				))
			}
		}

		const styleColorMenu = (this.state.colorMenu === null)? { backgroundColor : '#32814B'} : { backgroundColor : "#"+this.state.colorMenu};

		return (
			<div id = "barraMenu">
				<header className="imc-titol" style={styleColorMenu}>

					<nav className="">
						<div className="row imc--contingut">

						{/*<a href="{this.context.router.goBack()}" className="imc-torna"*/}
						{/*   title={t('menuTorna')}><span>{t('menuTorna')}</span></a>*/}

							<div className="col-11 pl-0">
								<h1>
									<span>{t('menuTitol')}</span>
								</h1>

								<ul>
									{content}
								</ul>
							</div>

							<div className="col-1 pr-0">
								<button type="button" className="imc-bt-menu float-right" id="imc-bt-menu" title={t('menuMenu')}>
									{/*<span>{t('menuMenu')}</span>*/}
								</button>
							</div>
						</div>
					</nav>

				</header>
			</div>
		)
	}
}

export default withTranslation()(BarraMenu);
