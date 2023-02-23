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
import {RenderTableProps} from "./RenderTableProps";

interface RenderPaginationTableProps extends RenderTableProps {
  paginationInfo: PaginationCarpetaProps;
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
        <PaginationCarpeta {...this.props} />
      </>
    );
  }
}

export default RenderPaginationTable;
