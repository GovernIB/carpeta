import React from "react";
/**
 *  @author anadal
 */
interface CarpetaDatePickerProps {
    basename: string;
    defaultValue: Date;
    onChangeDate?(newDate: Date, oldDate: Date): boolean;
    i18n: any;
}
declare class CarpetaDatePicker extends React.Component<CarpetaDatePickerProps> {
    private datePickerName;
    constructor(props: CarpetaDatePickerProps);
    private canviatIdioma;
    componentDidMount(): void;
    private onChangeDateCarpetaDatePicker;
    render(): JSX.Element;
}
export default CarpetaDatePicker;
