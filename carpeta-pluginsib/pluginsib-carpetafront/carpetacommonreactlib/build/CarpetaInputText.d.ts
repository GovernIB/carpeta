/**
 * @author anadal
 * @create date 2023-03-20 13:04:34
 * @modify date 2023-03-20 13:04:34
 * @desc [description]
 */
import React from "react";
interface CarpetaInputTextProps {
    id: string;
    placeHolder: string;
    defaultValue?: string;
    onChangedText?(newText: string): void;
}
declare class CarpetaInputText extends React.Component<CarpetaInputTextProps> {
    constructor(props: CarpetaInputTextProps);
    private onKeyDownInput;
    private onKeyUpInput;
    render(): JSX.Element;
}
export default CarpetaInputText;
