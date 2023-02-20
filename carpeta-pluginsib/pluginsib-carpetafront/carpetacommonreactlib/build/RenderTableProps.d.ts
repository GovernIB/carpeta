/**
 * @author anadal
 * @create date 2023-01-03 08:39:29
 * @modify date 2023-01-03 08:39:29
 * @desc [description]
 */
import { RowType } from "./RowType";
export interface RenderTableProps {
    tableData: any[];
    columnNames: any[];
    columnTitles: any[];
    columnNamesAdditionals?: any[];
    columnTitlesAdditionals?: any[];
    onClickRow?: Function;
    mobileIcon?: string;
    rowType?: RowType;
    i18n: any;
}
