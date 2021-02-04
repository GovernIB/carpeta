package es.caib.carpeta.back.controller;

import static es.caib.carpeta.commons.utils.Constants.ESTAT_LOG_ERROR;
import static es.caib.carpeta.commons.utils.Constants.TIPUS_LOG_AUTENTICACIO_BACK;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.IGenAppEntity;
import org.fundaciobit.genapp.common.web.controller.CommonBaseController;


import es.caib.carpeta.back.security.LoginInfo;
import es.caib.carpeta.back.utils.Utils;
import es.caib.carpeta.commons.utils.Constants;
import es.caib.carpeta.logic.LogCarpetaLogicaService;

/**
 * POT SOBRESCRIURE AQUESTA CLASSE
 * 
 * @author anadal
 *
 */
public abstract class CarpetaBaseController<I extends IGenAppEntity, PK extends Object>
        extends CommonBaseController<I, PK> implements Constants {

    @EJB(mappedName = LogCarpetaLogicaService.JNDI_NAME)
    protected LogCarpetaLogicaService logCarpetaLogicaEjb;

    @Override
    public String createMessageError(HttpServletRequest request, String code, Object pk) {
        String msg = super.createMessageError(request, code, pk);

        Utils.createLog(logCarpetaLogicaEjb, msg, request, code, pk, null);

        return msg;
    }

    @Override
    public String createMessageError(HttpServletRequest request, String code, Object pk, Throwable e) {
        String msg = super.createMessageError(request, code, pk, e);

        Utils.createLog(logCarpetaLogicaEjb, msg, request, code, pk, e);

        return msg;
    }

    
}
