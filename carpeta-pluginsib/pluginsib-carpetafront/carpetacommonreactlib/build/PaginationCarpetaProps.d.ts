/**
 * @author anadal
 * @create date 2023-01-03 08:39:29
 * @modify date 2023-01-03 08:39:29
 * @desc [description]
 */
export default interface PaginationCarpetaProps {
    paginaActual: number;
    elementsPerPagina: number;
    totalPagines: number;
    registresRetornats: number;
    totalRegistres: number;
    onClickPagination: Function;
    onClickSelectElementsByPage?: Function;
    selectElementsByPage?: number[];
}
