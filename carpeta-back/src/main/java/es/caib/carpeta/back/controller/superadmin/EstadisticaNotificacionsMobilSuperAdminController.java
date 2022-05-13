package es.caib.carpeta.back.controller.superadmin;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.carpeta.back.form.webdb.EstadisticaFilterForm;
import es.caib.carpeta.back.form.webdb.EstadisticaForm;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.model.fields.EstadisticaFields;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/superadmin/estadisticanotificacionsmobil")
@SessionAttributes(types = { EstadisticaForm.class, EstadisticaFilterForm.class })
public class EstadisticaNotificacionsMobilSuperAdminController
        extends EstadisticaSuperAdminController {

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        return EstadisticaFields.TIPUS.equal(Constants.TIPUS_ESTADISTICA_ENVIADA_NOTIFICACIO_MOBIL);
    }

    @Override
    public EstadisticaFilterForm getEstadisticaFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        EstadisticaFilterForm estadisticaFilterForm = super.getEstadisticaFilterForm(pagina, mav,
                request);

        if (estadisticaFilterForm.isNou()) {
            // XYZ ZZZ TRA
            estadisticaFilterForm.addLabel(COMPTADOR, "=Temps d´enviament(ms)");
            
            estadisticaFilterForm.getGroupByFields().remove(TIPUS);
            OrderBy[] orderByDef = {
                    new OrderBy(EstadisticaFields.DATAESTADISTICA.javaName, OrderType.DESC) };
            estadisticaFilterForm.setDefaultOrderBy(orderByDef);
        }

        return estadisticaFilterForm;
    }

}
