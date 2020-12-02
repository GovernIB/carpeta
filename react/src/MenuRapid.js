import React, {Component} from 'react';
import { withTranslation } from 'react-i18next';
import axios from "axios";
import i18n from "i18next";


class MenuRapid extends Component {

    constructor(){
        super();
        this.state = {
            plugins: []
        }
    }

    componentWillMount() {
        var url = window.location.href + `pluginfront/veureplugins`;
        axios.get(url).then(res => {
            const plugins = res.data;
            this.setState({ plugins });
        })
    }

    componentWillReceiveProps(lng) {
        var url = window.location.href + `pluginfront/veureplugins`;
        axios.get(url).then(res => {
            const plugins = res.data;
            this.setState({ plugins });
        })
    }

    render() {

        const {t} = this.props;
        var autenticat = this.props.autenticat;
        const plugins = this.state.plugins;
        var urlBase = window.location.href;

        var gestionsHtml;
        var gestionsReact;
        var accessibilitat;
        var dades;
        var gestions;

        if(autenticat === '0'){
            // accessibilitat = <li className="nav-item pr-5">
            //     <a href="javascript:newAccessibilitat('contingut', '0');" className="imc-marc-ico imc--accessibilitat navCarpeta" id="imc-marc-accessibilitat" title={t('menuAccessibilitat')}><span>{t('menuAccessibilitat')}</span></a>
            // </li>;
            accessibilitat = "";

            dades = "";
            gestionsHtml = "";
            gestionsReact = "";

        } else if(autenticat === '1'){
            accessibilitat = <li className="nav-item pr-5">
                    <a href="javascript:newAccessibilitat('contingut', '1');" className="imc-marc-ico imc--accessibilitat navCarpeta" id="imc-marc-accessibilitat" title={t('menuAccessibilitat')}><span>{t('menuAccessibilitat')}</span></a>
                </li>;

            dades = <li className="nav-item">
                    <a href="javascript:newDadesPersonals('contingut', '1');" className="imc-marc-ico navCarpeta imc--dades"><span>{t('menuDades')}</span></a>
                </li>;

            gestionsHtml = plugins.filter(s => s.reactComponent === 'false').map(s => (
                <a className="dropdown-item menuRapid submenuRapid--item" href={"javascript:newPluginHtml('contingut', '1', '" + s.pluginID + "');"}
                       title={s.nom}><img src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
                    className="imc-icona"></img><span>{s.nom}</span></a>
            ));

            gestionsReact = plugins.filter(s => s.reactComponent === 'true').map(s => (
                <a className="dropdown-item menuRapid submenuRapid--item" href={"javascript:newPluginReact('contingut', '1', '" + s.pluginID + "');"}
                   title={s.nom}><img src={urlBase + "pluginfront/pluginicon/" + s.pluginID + "/" + i18n.language + ""}
                                      className="imc-icona"></img><span>{s.nom}</span></a>
            ));

            gestions = <li className="nav-item pr-5">
                <button className="imc-marc-ico dropdown-toggle menuRapid imc--gestions" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span>{t('menuGestions')}</span></button>
                <div className="dropdown-menu submenuRapid" aria-labelledby="dropdownMenuButton">
                    {gestionsHtml}
                    {gestionsReact}
                </div>
            </li>;

        }

        return (
            <div>
                <nav className="navbar navbar-expand-sm bg-light pl-0">
                    <ul className="navbar-nav">
                        {/*<li className="nav-item pr-5">*/}
                        {/*    <button className="imc-marc-ico dropdown-toggle menuRapid imc--gestions" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span>{t('menuGestions')}</span></button>*/}
                        {/*    <div className="dropdown-menu submenuRapid" aria-labelledby="dropdownMenuButton">*/}
                        {/*        {gestionsHtml}*/}
                        {/*        {gestionsReact}*/}
                        {/*    </div>*/}
                        {/*</li>*/}
                        {gestions}
                        {accessibilitat}
                        {dades}
                    </ul>
                </nav>
            </div>
        );
    }
}

export default withTranslation()(MenuRapid);