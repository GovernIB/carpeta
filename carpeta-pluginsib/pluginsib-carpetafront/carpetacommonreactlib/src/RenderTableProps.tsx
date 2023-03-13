/* @author anadal
 * @create date 2023-01-03 08:39:29
 * @modify date 2023-01-03 08:39:29
 * @desc [description]
 */
import { RowType } from "./RowType";

export interface RenderTableData {
  tableData: any[] | null;
  error: string | null;
}

export interface RenderTableReturn {
  returnDataFunction(data:RenderTableData):void;
}

export interface RenderCommonTableProps {
  columnNames: any[];
  columnTitles: any[];
  columnNamesAdditionals?: any[];
  columnTitlesAdditionals?: any[];
  onClickRow?(pos:number,item:any):void;
  mobileIcon?: string;
  rowType?: RowType;
  i18n: any;
}

export interface RenderTableProps extends RenderCommonTableProps {
  loadData?(returnData:RenderTableReturn): void;
}

export interface RenderInternalTableProps extends RenderTableProps {
  tableData: any[];
}