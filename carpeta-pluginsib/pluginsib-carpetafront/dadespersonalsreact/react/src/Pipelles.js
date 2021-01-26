import React, { Component } from 'react';
import { withTranslation } from 'react-i18next';
import DadesPersonals from './DadesPersonals';
import DadesPolicia from './DadesPolicia';
import i18n from './i18n';
/**
 *  @author anadal
 */
class Pipelles extends Component {


    constructor(props) {
        super(props);
        this.childRef = React.createRef();

        this.onClickOnPolicia = this.onClickOnPolicia.bind(this);
    }



    onClickOnPolicia() {


        this.clickChild()

        /*
        console.log("Mostrar BBBBBB");
        if (this.childRef) {
            console.log("childRef == " + this.childRef)
            if (this.childRef.onShowPipella) {
                this.childRef.onShowPipella();
            } else {
                console.log("this.childRef.onShowPipella NO ESTA DEFINIT !!!!!");
                if (this.childRef.current) {
                    this.childRef.current();
                } else {
                    console.log("this.childRef.current NO ESTA DEFINIT !!!!!");
                }

            }
            //this.childRef.current.onShowPipella();
        } else {
            console.log("this.childRef NO ESTA DEFINIT !!!!!");
        }
        */
    }


    render() {
        console.log('  RENDER PIPELLES !!!!!');
        const { t } = this.props;
        const dades2 = this.props.dades;
        const pathtodocumentidentitat = this.props.dades.pathtodocumentidentitat;
        return (
            <div className="infoNoMenu">
                <h2><p className="titol h2">{t('dadespersonalsTitol')}</p></h2>
                <div className="col-md-12 border-0 float-left p-0">
                    <p className="lh15">{t('dadespersonalsDescripcio')} </p>
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="dadespersonals-tab" href="#dadespersonals" data-toggle="tab"   role="tab" aria-controls="dadespersonals" aria-selected="true">{t('pipella_dadesbasiques')}</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="dadespolicia-tab" href="#dadespolicia" onClick={this.onClickOnPolicia} data-toggle="tab" role="tab" aria-controls="dadespolicia" aria-selected="false">{t('pipella_dadespolicia')}</a>
                        </li>
                    </ul>
                    <div class="tab-content" id="myTabContent">
                        <div class="tab-pane fade show active" id="dadespersonals" role="tabpanel" aria-labelledby="home-tab"> <DadesPersonals dades={dades2} /></div>
                        <div class="tab-pane fade"  role="tabpanel" id="dadespolicia" aria-labelledby="profile-tab">
                            <DadesPolicia setClick={click => this.clickChild = click} ref={cd => {console.log(" ........... FEIM ASSIGNACIO A REF"); this.childRef = cd;}} pathtodocumentidentitat={pathtodocumentidentitat} /> 
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default withTranslation()(Pipelles);