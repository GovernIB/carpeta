/**
 * @author fbosch, anadal
 * @create date 2022-11-23 10:30:40
 * @modify date 2022-11-23 10:30:40
 * @desc [description]
 */

import React from "react";
import * as reactdetect from "react-device-detect";
import RenderTableMobile from "./RenderTableMobile";
import RenderTableDesktop from "./RenderTableDesktop";
import RenderTableProps from "./RenderTableProps";

class RenderTable extends React.Component<RenderTableProps> {
  constructor(props: RenderTableProps) {
    super(props);
    console.log("  CONSTRUCTOR RenderTable !!!!!");
  }

  render() {
    //console.log("Render OK: Imprimint Data RENDER TABLE...!");

    if (!reactdetect.isMobileOnly) {
      {
        /*  ============== VERSIO DESKTOP ================= */
      }
      return <RenderTableDesktop {...this.props} />;
      {
        /*  ============== FINAL VERSIO DESKTOP ================= */
      }
    } else {
      {
        /*  ============== VERSIO MÒBIL =================        */
      }
      return <RenderTableMobile {...this.props} />;
      {
        /*  ============== FINAL VERSIO MÒBIL ================= */
      }
    }
  }
}

export default RenderTable;
