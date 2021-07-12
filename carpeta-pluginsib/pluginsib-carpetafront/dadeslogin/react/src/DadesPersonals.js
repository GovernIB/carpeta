import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';

/**
 *  @author anadal
 */
class DadesPersonals extends Component {


    constructor(props) {
        super(props);
        // console.log('  CONSTRUCTOR DADES PERSONALS !!!!!');
    }


    render() {

        console.log('  RENDER DADES PERSONALS!!!!!');

        const { t } = this.props;
        
        const dades = this.props.dades;

        const userData = dades.userData;

        var tipus = '';
        var representant;
        if (userData.representative) {

            representant =  <div>
                            <br/>
                            <p className="titol h4">{t('representant')}</p>
                            <div className="col-md-12 border-0 float-left p-0">
                            <div className="card">
                                <ul className="list-group list-group-flush">
                                    <li className="list-group-item">
                                        <label className="lh15" >{t('dadespersonalsNom')}: &nbsp;</label>
                                        <label className="titol h5">{userData.representative.name}</label>
                                </li>
                                    <li className="list-group-item"><label
                                        className="lh15">{t('dadespersonalsLlinatge1')}: &nbsp;</label><label
                                            className="titol h5">{userData.representative.surname1}</label></li>
                                    <li className="list-group-item"><label
                                        className="lh15">{t('dadespersonalsLlinatge2')}: &nbsp;</label><label
                                            className="titol h5">{userData.representative.surname2}</label></li>
                                    <li className="list-group-item"><label className="lh15">{t('dadespersonalsDni')}: &nbsp;</label><label
                                        className="titol h5">{userData.representative.administrationID}</label></li>
                                
                                </ul>
                            </div>
                            </div>
                            </div>;
            tipus=<p className="titol h4">{t('representat')}</p>;

        } else {
            representant = "";
        }


        var llinatge1;
        var llinatge2;
        console.log("DadesPersonals:: business => " + userData.business)
        if (userData.business) {
            llinatge1 = "";
            llinatge2 = "";
            tipus= <p className="titol h4">{t('empresa')}</p>;
        } else {
            llinatge1 = <li className="list-group-item"><label
                            className="lh15">{t('dadespersonalsLlinatge1')}: &nbsp;</label><label
                                className="titol h5">{userData.surname1}</label></li>;
            llinatge2 = <li className="list-group-item"><label
                            className="lh15">{t('dadespersonalsLlinatge2')}: &nbsp;</label><label
                                className="titol h5">{userData.surname2}</label></li>;
        }



        return (
            <div className="infoNoMenu">
                {tipus}
                <div className="col-md-12 border-0 float-left p-0">
                    <div className="card">
                        <ul className="list-group list-group-flush">
                            <li className="list-group-item">
                                <label className="lh15" >{t('dadespersonalsNom')}: &nbsp;</label>
                                <label className="titol h5">{userData.name}</label>
                            </li>
                            {llinatge1}
                            {llinatge2}
                            <li className="list-group-item"><label className="lh15">{t('dadespersonalsDni')}: &nbsp;</label><label
                                className="titol h5">{userData.administrationID}</label>
                            </li>
                            <li className="list-group-item"><label className="lh15">{t('dadespersonalsMetode')}: &nbsp;</label>
                                <label className="titol h5">{userData.authenticationMethod}</label>
                            </li>
                        </ul>
                    </div>
                </div>
                {representant}
                


            </div>

        );


    }
}

export default withTranslation()(DadesPersonals);