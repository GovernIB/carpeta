import { RowType } from "./RowType";
export interface RenderTableData {
    tableData: any[] | null;
    error: string | null;
}
export interface RenderTableReturn {
    returnDataFunction(data: RenderTableData): void;
}
export interface RenderCommonTableProps {
    columnNames: any[];
    columnTitles: any[];
    columnNamesAdditionals?: any[];
    columnTitlesAdditionals?: any[];
    onClickRow?: Function;
    mobileIcon?: string;
    rowType?: RowType;
    i18n: any;
}
export interface RenderTableProps extends RenderCommonTableProps {
    loadData?(returnData: RenderTableReturn): void;
}
export interface RenderInternalTableProps extends RenderTableProps {
    tableData: any[];
}
