/// <reference types="react" />
import { RowType } from "./RowType";
declare class RowTypeUtils {
    static getIcon(rowType: RowType, color?: boolean): JSX.Element;
    static getLabel(rowType: RowType, i18n: any): string;
}
export { RowType, RowTypeUtils };
