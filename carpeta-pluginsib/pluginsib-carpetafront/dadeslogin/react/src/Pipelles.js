import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';
import DadesPersonals from './DadesPersonals';
import i18n from './i18n';

/**
 *  @author anadal
 */
class Pipelles extends Component {


    constructor(props) {
        super(props);
    }


    render() {
        // console.log('  RENDER PIPELLES !!!!!');
        const { t } = this.props;
        const dades2 = this.props.dades;
        
        return (
            <div className="infoNoMenu">
                <h2><p className="titol h2">{this.props.dades.titles[i18n.language]}</p></h2>
                <div className="col-md-12 border-0 float-left p-0">
                    <p className="lh15">{this.props.dades.subtitles[i18n.language]} </p>
                    
                    <div class="tab-pane fade show active" id="dadespersonals" role="tabpanel" aria-labelledby="home-tab">
                             <DadesPersonals dades={dades2} />
                    </div>
                        
                    
                </div>
            </div>
        );
    }
}

export default withTranslation()(Pipelles);