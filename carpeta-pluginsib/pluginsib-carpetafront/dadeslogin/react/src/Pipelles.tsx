import React, {Component} from 'react';
import { withTranslation, WithTranslation } from "react-i18next";
import DadesPersonals from './DadesPersonals';
import i18n from './i18n';

interface PipellesProps extends WithTranslation {
    userData: any;
    titles: any;
    subtitles: any;
}
/**
 *  @author anadal
 */
class Pipelles extends Component<PipellesProps> {


    constructor(props: PipellesProps) {
        super(props);
    }


    render() {
        // console.log('  RENDER PIPELLES !!!!!');
        const { t } = this.props;
        const dades2 = this.props.userData;
        
        return (<>
                    <div className="titolPaginaApp visioMobil">
                        {this.props.titles[i18n.language]}
                    </div>
                    <div className="infoNoMenu">
                        <h2 className="titol h2 ocultarMobil">{this.props.titles[i18n.language]}</h2>
                        <div className="col-md-12 border-0 float-left p-0">
                            <p className="lh15 ocultarMobil">{this.props.subtitles[i18n.language]} </p>

                            <div className="tab-pane fade show active" id="dadespersonals" role="tabpanel" aria-labelledby="home-tab">
                                    <DadesPersonals userData={dades2} />
                            </div>


                        </div>
                    </div>
            </>
        );
    }
}

export default withTranslation()(Pipelles);