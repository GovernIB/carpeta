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
import { RenderTableProps, RenderTableData, RenderTableReturn } from "./RenderTableProps";

interface RenderTableState extends RenderTableData {
  isLoaded: boolean;
}



class RenderTable extends React.Component<RenderTableProps, RenderTableState> {
  constructor(props: RenderTableProps) {
    super(props);

    this.state = {
      isLoaded: false,
      tableData: null,
      error: null,
    };

    this.updateTableData = this.updateTableData.bind(this);

    //this.carregarDadesAsync = this.carregarDadesAsync.bind(this);

    console.log("  CONSTRUCTOR RenderTable !!!!!");
  }

  componentDidMount(): void {
    if (this.props.loadData  != undefined) {

      let returnData:RenderTableReturn = {
        returnDataFunction: this.updateTableData
      }
      this.props.loadData(returnData);
    }
  }

  updateTableData(dades: RenderTableData | null): void {
    if (dades == null) {
      console.log("RenderTable::updateTableData(CARREGANT...)");
      this.setState({ isLoaded: false, tableData: null, error: null });
    } else {
      console.log("RenderTable::updateTableData(CARREGAR DADES ...)");
      this.setState({ isLoaded: true, tableData: dades.tableData, error: dades.error });
    }
  }

  /*
  async carregarDadesAsync() {
    if (this.props.automaticLoadData == true) {
      let dades: RenderTableData = this.props.loadData();
      this.setState({ isLoaded: true, tableData: dades.tableData, error: dades.error });
    }
  }
  */

  render() {
    console.log("RenderTable::render() => Start");

    if (!this.state.isLoaded) {
      return (
        <div id="carregant" className="loader-container centrat ">
          <div className="loader" />
        </div>
      );
    }

    if (this.state.error != null) {
      return (
        <div className="alert alert-danger" role="alert">
          {this.state.error}
        </div>
      );
    }

    if (this.state.tableData == null || (this.state.tableData != null && this.state.tableData.length == 0)) {
      return (
        <div className="alert alert-warning"  role="alert">
          {this.props.i18n.t("taulaBuida")}
        </div>
      );
    }

    /*
    let currentProps: RenderInternalTableProps = {
      columnNames: this.props.columnNames,
      columnTitles: this.props.columnTitles,
      columnNamesAdditionals: this.props.columnNamesAdditionals,
      columnTitlesAdditionals: this.props.columnTitlesAdditionals,
      onClickRow: this.props.onClickRow,
      mobileIcon: this.props.mobileIcon,
      rowType: this.props.rowType,
      i18n: this.props.i18n,
      //loadData: this.props.loadData,
      automaticLoadData: false,
      tableData: this.state.tableData,
    };
    */

    // OK Tenim dades
    if (!reactdetect.isMobileOnly) {
      {
        /*  ============== VERSIO DESKTOP ================= */
      }
      return <RenderTableDesktop {...this.props} tableData={this.state.tableData}/>;
      {
        /*  ============== FINAL VERSIO DESKTOP ================= */
      }
    } else {
      {
        /*  ============== VERSIO MÒBIL =================        */
      }
      return <RenderTableMobile {...this.props} tableData={this.state.tableData} />;
      {
        /*  ============== FINAL VERSIO MÒBIL ================= */
      }
    }
  }
}

export default RenderTable;
