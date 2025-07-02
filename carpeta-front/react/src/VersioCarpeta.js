import React, {Component} from 'react';
import {withTranslation} from 'react-i18next';

class VersioCarpeta extends Component {

    constructor(){
        super();
        this.state = {
            versioCarpeta: "",
            error: null
        }
    }


    componentDidMount() {
        var baseURL = sessionStorage.getItem('contextPath');
        fetch(baseURL + '/webui/versiocarpeta')

            .then((response) => {
                return response.json()
            })
            .then((versioCarpeta) => {
                this.setState({ versioCarpeta: versioCarpeta })
            })
            .catch(error => {
                console.log(JSON.stringify(error));
                if (error.response) {
                    console.log("error.response.data: " + error.response.data);
                    console.log("error.response.status: " + error.response.status);
                    console.log("error.response.headers: " + error.response.headers);
                }
                this.setState({
                    versioCarpeta: "",
                    error: JSON.stringify(error)
                });
            });
    }

    render() {
        let content;
        
        console.log("Entra al render");
        
        if (this.state.error) {
            content = <div className="alert alert-danger" role="alert">{this.state.error}</div>;
        } else {
                content = 
                    <p style={{ color: "#A6A9AC" }}>
                        Carpeta:{this.state.versioCarpeta}
                    </p>
        }
        

        return (
            <div>
                <div>
                    {content}
                </div>
            </div>
        );
    }
}

export default withTranslation()(VersioCarpeta);
