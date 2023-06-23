package es.caib.carpeta.back.controller.adminentitat;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.carpeta.back.controller.webdb.PreguntesFrequentsController;
import es.caib.carpeta.back.form.webdb.PreguntesFrequentsFilterForm;
import es.caib.carpeta.back.form.webdb.PreguntesFrequentsForm;
import es.caib.carpeta.back.security.LoginInfo;
import es.caib.carpeta.model.fields.PreguntesFrequentsFields;
import es.caib.carpeta.persistence.PreguntesFrequentsJPA;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/adminentitat/faq")
@SessionAttributes(types = { PreguntesFrequentsForm.class, PreguntesFrequentsFilterForm.class })
public class PreguntesFrequentsAdminEntitatController extends PreguntesFrequentsController {

    @Override
    public String getTileForm() {
        return "preguntesFrequentsFormAdminEntitat";
    }

    @Override
    public String getTileList() {
        return "preguntesFrequentsListAdminEntitat";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "PreguntesFrequentsAdminEntitat_FilterForm";
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        return PreguntesFrequentsFields.ENTITATID.equal(LoginInfo.getInstance().getEntitatID());
    }

    @Override
    public PreguntesFrequentsForm getPreguntesFrequentsForm(PreguntesFrequentsJPA _jpa,
            boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
        PreguntesFrequentsForm preguntesFrequentsForm = super.getPreguntesFrequentsForm(_jpa,
                __isView, request, mav);

        if (preguntesFrequentsForm.isNou()) {
          preguntesFrequentsForm.getPreguntesFrequents()
                .setEntitatID(LoginInfo.getInstance().getEntitatID());
        }
        preguntesFrequentsForm.addHiddenField(ENTITATID);

        HtmlUtils.saveMessageInfo(request, "<h3>COM INCRUSTAR UN VIDEO</h3><br/>\r\n"
                + "<b>La millor opció és copiar (Ctrl + C) i aferrar (Ctrl + V) el video i el propi editor ja processarà el video.</b><br/>\r\n"
                + "<br/>\r\n"
                + "Una altra opció és fer arrastrar i amollar (Drag&Drop), però el video es procesa com una imatge i s'han de seguir les següent passes per arreglar-ho:<br/>\r\n"
                + "(1) Després de fer Drop del video al RichText titulat \"Resposta Català\" o \"Resposta Castellà\" apareixerà un petiti quadrat blanc.<br/>\r\n"
                + "(2) Pitjar el Boto \"HTML\" (segona fila de botons a la dreta)<br/>\r\n"
                + "(3) Apareixerà una finestra emergent i en el codi haureu de cercar una estructura d'aquest tipus<br/>\r\n"
                + "&lt;img src=\"data:video/mp4;base64,AAAAIGZ0eXBpc29tAAACA ...\" alt=\"\" /&gt;<br/>\r\n"
                + "(4) Substituirem [&lt;img src=\"] per [&lt;video width=\"320\" height=\"240\" controls=\"controls\"&gt;&lt;source type=\"video/mp4\" src=\"] <br/>\r\n"
                + "(5) Tancarem la finestra emergent i el quadrat blanc s'haurà convertit en un quadrat  més gran de color groguent.");

        return preguntesFrequentsForm;
    }

    @Override
    public PreguntesFrequentsFilterForm getPreguntesFrequentsFilterForm(Integer pagina,
            ModelAndView mav, HttpServletRequest request) throws I18NException {
        PreguntesFrequentsFilterForm preguntesFrequentsFilterForm;
        preguntesFrequentsFilterForm = super.getPreguntesFrequentsFilterForm(pagina, mav, request);

        if (preguntesFrequentsFilterForm.isNou()) {

            preguntesFrequentsFilterForm.addHiddenField(ENTITATID);

            preguntesFrequentsFilterForm.addHiddenField(RESPOSTACA);
            preguntesFrequentsFilterForm.addHiddenField(RESPOSTAES);

            preguntesFrequentsFilterForm.setDefaultOrderBy(new OrderBy[] { new OrderBy(ORDRE) });
            
            preguntesFrequentsFilterForm.setFilterByFields(new ArrayList<Field<?>>());
        }
        

        return preguntesFrequentsFilterForm;
    }

}
