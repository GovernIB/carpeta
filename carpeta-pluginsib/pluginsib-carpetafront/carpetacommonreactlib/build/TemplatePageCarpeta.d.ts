/**
 * @author anadal
 * @create date 2022-12-28 13:46:48
 * @modify date 2022-12-28 13:46:48
 * @desc [description]
 */
import React from "react";
interface TemplatePageCarpetaProps {
    titles: any;
    subtitles: any;
    children: JSX.Element;
    i18n: any;
}
declare class TemplatePageCarpeta extends React.Component<TemplatePageCarpetaProps> {
    constructor(props: TemplatePageCarpetaProps);
    render(): JSX.Element;
}
export default TemplatePageCarpeta;
