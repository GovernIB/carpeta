/**
 * @author anadal
 * @create date 2022-12-28 13:46:48
 * @modify date 2022-12-28 13:46:48
 * @desc [description]
 */

import React from "react";
import PaginationCarpetaProps from "./PaginationCarpetaProps";
import RenderTable from "./RenderTable";
import PaginationCarpeta from "./PaginationCarpeta";
import RenderTableProps from "./RenderTableProps";

interface RenderPaginationTableProps extends RenderTableProps {
  paginationInfo: PaginationCarpetaProps;
  i18n: any;
}

class RenderPaginationTable extends React.Component<RenderPaginationTableProps> {
  constructor(props: RenderPaginationTableProps) {
    super(props);
    console.log("  CONSTRUCTOR RenderPaginationTable !!!!!");
  }

  render() {
    return (
      <>
        <div className="infoNoMenu">
          <RenderTable {...this.props} />
        </div>
        <PaginationCarpeta i18n={this.props.i18n} paginationInfo={this.props.paginationInfo} />
      </>
    );
  }
}

export default RenderPaginationTable;
