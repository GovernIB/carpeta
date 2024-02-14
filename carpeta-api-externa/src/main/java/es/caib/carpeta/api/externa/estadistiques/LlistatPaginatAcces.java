package es.caib.carpeta.api.externa.estadistiques;

import java.util.Date;
import java.util.List;

import org.fundaciobit.pluginsib.utils.rest.AbstractPagination;
import org.fundaciobit.pluginsib.utils.rest.RestUtils;
import org.fundaciobit.pluginsib.utils.rest.ReuseDataPagination;

import io.swagger.v3.oas.annotations.media.Schema;

/*
 * Classe necessària per generar correctament la documentació OpenAPI, atès que els resultats
 * amb genèrics no funcionen del tot bé.
 * 
 * És equivalent a un Pagina<AccesDTO> i només s'empra per a la documentació del API REST
 * 
 * @author jagarcia  
 * @author anadal (refactoring a RestUtils)
 */
@Schema(name = "LlistatPaginatAcces")
public class LlistatPaginatAcces extends ReuseDataPagination<AccesDTO> {

    public LlistatPaginatAcces() {
        super();
    }

    public LlistatPaginatAcces(List<AccesDTO> data, AbstractPagination<?> p, String nextUrl, String name) {
        super(data, p.getPage(), p.getPagesize(), p.getTotalpages(), p.getTotalcount(), nextUrl,
                RestUtils.convertDateToDateTimeISO8601(new Date()), name);
    }

    public LlistatPaginatAcces(List<AccesDTO> data, int page, int pagesize, int totalpages, int totalcount, String nextUrl,
            String dateDownload, String name) {
        super(data, page, pagesize, totalpages, totalcount, nextUrl, dateDownload, name);
    }

}
