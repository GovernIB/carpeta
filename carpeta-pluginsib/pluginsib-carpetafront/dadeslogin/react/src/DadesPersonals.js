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
        console.log("DadesPersonals:: business => " + userData.business);

        if (userData.business) {
            llinatge1 = "";
            llinatge2 = "";
            tipus= <p className="titol h4">{t('empresa')}</p>;
            if(userData.name){
                nom = <div className="mt-3">
                    <dt className="col-sm-3 colorGrisApp">{t('dadespersonalsNom')}</dt>
                    <dd className="col-sm-7 colorGrisApp">{userData.name}</dd>
                </div>;
            } else{
                nom = "";
            }
            if(userData.administrationID) {
                dni = <div className="mt-3">
                    <dt className="col-sm-3 colorGrisApp">{t('dadespersonalsDni')}</dt>
                    <dd className="col-sm-7 colorGrisApp">{userData.administrationID}</dd>
                </div>;
            }else{
                dni = "";
            }
        } else {
            if(userData.name){
                nom = <div className="mt-3">
                    <dt className="col-sm-3 colorGrisApp">{t('dadespersonalsNom')}</dt>
                    <dd className="col-sm-7 colorGrisApp">{userData.name}</dd>
                </div>;
            } else{
                nom = "";
            }

            if(userData.surname1){
                llinatge1 = <div className="mt-3">
                    <dt className="col-sm-3 colorGrisApp">{t('dadespersonalsLlinatge1')}</dt>
                    <dd className="col-sm-7 colorGrisApp">{userData.surname1}</dd>
                </div>;
            } else{
                llinatge1 = "";
            }

            if(userData.surname2) {
                llinatge2 = <div className="mt-3">
                    <dt className="col-sm-3 colorGrisApp">{t('dadespersonalsLlinatge2')}</dt>
                    <dd className="col-sm-7 colorGrisApp">{userData.surname2}</dd>
                </div>;
            }else{
                llinatge2 = "";
            }

            if(userData.administrationID) {
                dni = <div className="mt-3">
                    <dt className="col-sm-3 colorGrisApp">{t('dadespersonalsDni')}</dt>
                    <dd className="col-sm-7 colorGrisApp">{userData.administrationID}</dd>
                </div>;
            }else{
                dni = "";
            }

            if(userData.authenticationMethod) {
                metodeAutenticacio = <div className="mt-3">
                    <dt className="col-sm-3 colorGrisApp">{t('dadespersonalsMetode')}</dt>
                    <dd className="col-sm-7 colorGrisApp">{userData.authenticationMethod}</dd>
                </div>;
            }else{
                metodeAutenticacio = "";
            }

        }


        return (<>
                    <div className="infoNoMenu">
                        {tipus}
                        <div className="col-md-12 border-0 float-left p-0 pb-4 cardAppVerd padCardDadesApp">
                            <dl className="row">
                                {nom}
                                {llinatge1}
                                {llinatge2}
                                {dni}
                                {metodeAutenticacio}
                            </dl>
                        </div>
                        {representant}
                    </div>
                    <div className="col-md-12 border-0 float-left p-0" id="botoTornarDadesP" style={{ marginTop: '20px' }}>
                        <button type="button" data-toggle="modal" onClick={() => {
                            window.location.href = sessionStorage.getItem("pagTornar"); sessionStorage.setItem("pagTornar", sessionStorage.getItem("contextPath"))
                        }} className="botoSuport" tabIndex="520" aria-labelledby="botoTornarDadesP">{t('tornar')}</button>
                    </div>
                </>
        );

    }
}

export default withTranslation()(DadesPersonals);