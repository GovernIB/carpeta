/**
 * @author anadal
 * @email anadal@fundaciobit.org
 * @create date 2023-02-23 12:21:38
 * @modify date 2023-02-23 12:21:38
 * @desc [description]
 */
import React, { Component } from "react";

import { withTranslation, WithTranslation } from "react-i18next";

interface MollaPaProps extends WithTranslation {}


class MollaPa extends Component<MollaPaProps> {
  constructor(props: MollaPaProps) {
    super(props);
  }

  render() {
    const t = this.props.i18n.t;

    return (
      <ul className="mollaPa" id="imc-molla-pa">
        <li>
          <a href="inici.html">{t("mollaInici")}</a>
        </li>
      </ul>
    );
  }
}

export default withTranslation()(MollaPa);
