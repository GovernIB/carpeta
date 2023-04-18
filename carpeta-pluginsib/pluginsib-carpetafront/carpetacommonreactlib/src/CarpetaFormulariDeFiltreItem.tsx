/**
 * @author anadal
 * @create date 2023-03-20 13:04:34
 * @modify date 2023-03-20 13:04:34
 * @desc [description]
 */

import React from "react";

interface CarpetaFormulariDeFiltreItemProps {
  children: JSX.Element;
  label?: string;
}

class CarpetaFormulariDeFiltreItem extends React.Component<CarpetaFormulariDeFiltreItemProps> {
  constructor(props: CarpetaFormulariDeFiltreItemProps) {
    super(props);
  }

  render() {
    return (
      <div className="col-xs-12 campFormApp col">
        <div>
          { this.props.label && <label className="form-label">{this.props.label}</label> }
          {this.props.children}
        </div>
      </div>
    );
  }
}

export default CarpetaFormulariDeFiltreItem;
