/**
 * @author anadal
 * @create date 2022-12-28 13:46:48
 * @modify date 2022-12-28 13:46:48
 * @desc [description]
 */

import React from "react";
import { RenderPaginationTableData, RenderPaginationTableProps, ReturnPaginationData } from "./PaginationCarpetaProps";
import RenderTable from "./RenderTable";
import PaginationCarpeta from "./PaginationCarpeta";
// XYZ ZZZ import { RenderTableData } from "./RenderTableProps";

class RenderPaginationTable extends React.Component<RenderPaginationTableProps> {
  private page: number;
  private elementsByPage: number;

  static DEFAULT_SELECT_ELEMENTS_BY_PAGE: number[] = [5, 10, 25];

  private childRenderTable: React.RefObject<RenderTable> = React.createRef();

  private childRenderPagination: React.RefObject<PaginationCarpeta> = React.createRef();

  constructor(props: RenderPaginationTableProps) {
    super(props);
    console.log("RenderPaginationTable() Entra CONSTRUCTOR.");

    //let elementsByPage;
    if (this.props.selectElementsByPage == undefined) {
      this.elementsByPage = 5;
    } else {
      this.elementsByPage = this.props.selectElementsByPage[0];
    }

    console.log("RenderPaginationTable() => elementsByPage= " + this.elementsByPage);

    this.page = 0;

    this.onClickSelectElementsByPage = this.onClickSelectElementsByPage.bind(this);
    this.onClickPagination = this.onClickPagination.bind(this);
    this.loadDataAsync = this.loadDataAsync.bind(this);
    this.returnDataFunction = this.returnDataFunction.bind(this);
  }

  componentDidMount(): void {
    this.loadDataAsync(this.page, this.elementsByPage);
  }

  componentDidUpdate(): void {

    console.log(
      "RenderPaginationTable::componentDidUpdate()  => this.props.selectElementsByPage: " +
        this.props.selectElementsByPage
    );

    if (this.props.selectElementsByPage == undefined) {
      this.elementsByPage = RenderPaginationTable.DEFAULT_SELECT_ELEMENTS_BY_PAGE[0];
    } else {
      this.elementsByPage = this.props.selectElementsByPage[0];
    }

    this.page = 0;
    this.loadDataAsync(this.page, this.elementsByPage);
  }

  returnDataFunction(data: RenderPaginationTableData): void {
    console.log("RenderPaginationTable::returnDataFunction() => Rebudes noves dades ...");
    this.childRenderTable.current?.updateTableData(data);
    this.childRenderPagination.current?.updatePaginationInfo(data.paginationInfo);
  }

  loadDataAsync(page: number, elementsByPage: number) {
    console.log("RenderPaginationTable::loadDataAsync() Entra ...");

    // Avisam que estam carregant les dades de la Taula
    this.childRenderTable.current?.updateTableData(null);
    // Avisam al la paginació que estam carregant dades
    this.childRenderPagination.current?.updatePaginationInfo(null);

    let returnData: ReturnPaginationData = {
      page: page,
      elementsByPage: elementsByPage,
      returnDataFunction: this.returnDataFunction,
    };

    this.props.loadPaginatedData(returnData);

    console.log("RenderPaginationTable::loadDataAsync() Surt ...");
  }

  // Aquest mètode mai s'ha de cridar ja que les dades les enviam a RenderTable
  /* XYZ 
  loadData(): RenderTableData {
    let msg: string = "Mai s'ha de cridar a la funció loadData()";
    console.error(msg);

    let rtd: RenderTableData = {
      tableData: null,
      error: msg,
    };

    return rtd;
  }
  */

  onClickPagination(page: number): void {
    console.log("RenderPaginationTable::onClickPagination(" + page + ") Entra ..." + new Date());
    this.loadDataAsync(page, this.elementsByPage);
    console.log("RenderPaginationTable::onClickPagination(" + page + ") Surt ..." + new Date());
  }

  onClickSelectElementsByPage(elementsByPage: number): void {
    this.loadDataAsync(0, elementsByPage);
  }

  render() {
    console.log("RenderPaginationTable::render() Entra ... ");
    console.log("RenderPaginationTable::render() this.props.selectElementsByPage: " + this.props.selectElementsByPage);
    return (
      <>
        <div className="infoNoMenu">
          <RenderTable ref={this.childRenderTable} {...this.props}  />
        </div>
        <PaginationCarpeta
          ref={this.childRenderPagination}
          {...this.props}
          onClickPagination={this.onClickPagination}
          onClickSelectElementsByPage={this.onClickSelectElementsByPage}
          selectElementsByPage={this.props.selectElementsByPage}
        />
      </>
    );
  }
}

export default RenderPaginationTable;
