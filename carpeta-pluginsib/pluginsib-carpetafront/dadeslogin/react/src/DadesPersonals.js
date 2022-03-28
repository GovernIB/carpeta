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

            representant = 
                <div>
                    <p className="titol h4">{t('representant')}</p>
                    <div className="col-md-12 border-0 float-left p-0">
                        <dl className="row">
                            {userData.representative.name && <div className="mt-3">
                                <dt className="col-sm-3 mr-2">{t('dadespersonalsNom')}</dt>
                                <dd className="col-sm-7">{userData.representative.name}</dd>
                            </div>}

                            {userData.representative.surname1 && <div className="mt-3">
                                <dt className="col-sm-3 mr-2">{t('dadespersonalsLlinatge1')}</dt>
                                <dd className="col-sm-7">{userData.representative.surname1}</dd>
                            </div>}

                            {userData.representative.surname2 && <div className="mt-3">
                                <dt className="col-sm-3 mr-2">{t('dadespersonalsLlinatge2')}</dt>
                                <dd className="col-sm-7">{userData.representative.surname2}</dd>
                            </div>}

                            {userData.representative.administrationID && <div className="mt-3">
                                <dt className="col-sm-3 mr-2">{t('dadespersonalsDni')}</dt>
                                <dd className="col-sm-7">{userData.representative.administrationID}</dd>
                            </div>}
                        </dl>
                    </div>
                </div>;
            
            tipus=<p className="titol h4">{t('representat')}</p>;

        } else {
            representant = "";
        }


        var nom;
        var llinatge1;
        var llinatge2;
        var dni;
        var metodeAutenticacio;
        console.log("DadesPersonals:: business => " + userData.business)
        if (userData.business) {
            llinatge1 = "";
            llinatge2 = "";
            tipus= <p className="titol h4">{t('empresa')}</p>;
        } else {
            if(userData.name){
                console.log("11");
                nom = <div className="mt-3">
                    <dt className="col-sm-3">{t('dadespersonalsNom')}</dt>
                    <dd className="col-sm-7">{userData.name}</dd>
                </div>;
            } else{
                console.log("22");
                nom = "";
            }

            if(userData.surname1){
                console.log("1");
                llinatge1 = <div className="mt-3">
                    <dt className="col-sm-3">{t('dadespersonalsLlinatge1')}</dt>
                    <dd className="col-sm-7">{userData.surname1}</dd>
                </div>;
            } else{
                console.log("2");
                llinatge1 = "";
            }

            if(userData.surname2) {
                console.log("3");
                llinatge2 = <div className="mt-3">
                    <dt className="col-sm-3">{t('dadespersonalsLlinatge2')}</dt>
                    <dd className="col-sm-7">{userData.surname2}</dd>
                </div>;
            }else{
                console.log("4");
                llinatge2 = "";
            }

            if(userData.administrationID) {
                console.log("33");
                dni = <div className="mt-3">
                    <dt className="col-sm-3">{t('dadespersonalsDni')}</dt>
                    <dd className="col-sm-7">{userData.administrationID}</dd>
                </div>;
            }else{
                console.log("34");
                dni = "";
            }

            if(userData.authenticationMethod) {
                console.log("43");
                metodeAutenticacio = <div className="mt-3">
                    <dt className="col-sm-3">{t('dadespersonalsMetode')}</dt>
                    <dd className="col-sm-7">{userData.authenticationMethod}</dd>
                </div>;
            }else{
                console.log("44");
                metodeAutenticacio = "";
            }

        }


        return (
            <div className="infoNoMenu">
                {tipus}
                <div className="col-md-12 border-0 float-left p-0">
                    <dl className="row">

                        {nom}
                        {llinatge1}
                        {llinatge2}
                        {dni}
                        {metodeAutenticacio}
                    </dl>
                </div>
                {representant}

                <div className="col-md-12 border-0 float-left p-0" id="botoTornarDadesP" style={{ marginTop: '20px' }}>
                    <button type="button" data-toggle="modal" onClick={() => {
                        window.location.href = sessionStorage.getItem("pagTornar"); sessionStorage.setItem("pagTornar", sessionStorage.getItem("contextPath"))
                    }} className="botoSuport" tabIndex="520" aria-labelledby="botoTornarDadesP">{t('tornar')}</button>
                </div>
            </div>
        );

    }
}

export default withTranslation()(DadesPersonals);