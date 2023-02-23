/**
 * @author anadal
 * @create date 2023-01-03 08:39:29
 * @modify date 2023-01-03 08:39:29
 * @desc [description]
 */

import { RenderCommonTableProps, RenderTableData } from "./RenderTableProps";

export interface RenderPaginationTableData extends RenderTableData {
  paginationInfo: PaginationInfo | null;
}

export interface PaginationInfo {
  paginaActual: number;
  elementsPerPagina: number;
  totalPagines: number;
  elementsRetornats: number;
  totalElements: number;
}

export interface PaginationCarpetaProps {
  // undefined significa valor per defecte, o sigui RenderPaginationTable.DEFAULT_SELECT_ELEMENTS_BY_PAGE
  // mentre que null significa no mostrar 
  selectElementsByPage?: number[] | null;
  onClickPagination(page: number):void;
  onClickSelectElementsByPage(elementsByPage: number):void;
  i18n: any;
}

export interface ReturnPaginationData {
  page: number;
  elementsByPage: number;
  returnDataFunction(data:RenderPaginationTableData):void;
}

export interface RenderPaginationTableProps extends RenderCommonTableProps {
  loadPaginatedData(returnData: ReturnPaginationData): void;
  selectElementsByPage?: number[] | null;
}
