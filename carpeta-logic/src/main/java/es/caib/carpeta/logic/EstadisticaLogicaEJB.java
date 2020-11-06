package es.caib.carpeta.logic;

import es.caib.carpeta.ejb.EstadisticaEJB;
import es.caib.carpeta.jpa.EstadisticaJPA;
import es.caib.carpeta.logic.utils.LogicUtils;
import es.caib.carpeta.model.entity.Estadistica;
import es.caib.carpeta.model.fields.EstadisticaFields;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import java.sql.Timestamp;
import java.util.*;
import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;

/**
 * Created by Fundació BIT.
 *
 * @author mgonzalez
 * Date: 29/10/2020
 */
@Stateless
public class EstadisticaLogicaEJB extends EstadisticaEJB implements EstadisticaLogicaLocal{


    @Override
    public List<EstadisticaJPA> findEstadistica(Integer tipus, Long entitatID, Date data, Long pluginID)throws I18NException {

        Where w = null;
        if(tipus != null){
            w = Where.AND(w,EstadisticaFields.TIPUS.equal(tipus));
        }
        if(entitatID!= null){
            w = Where.AND(w,EstadisticaFields.ENTITATID.equal(entitatID));
        }
        if(data != null){
            w = Where.AND(w,EstadisticaFields.DATAESTADISTICA.equal(new Timestamp(LogicUtils.llevarHoraData(data).getTime())));
        }
        if(pluginID != null){
            w = Where.AND(w,EstadisticaFields.PLUGINID.equal(pluginID));
        }


        List<Estadistica> estadisticas = select(w); ;

        if (estadisticas == null || estadisticas.size() == 0) {
            return null;
        } else {

            List<EstadisticaJPA> list2 = new ArrayList<EstadisticaJPA>(estadisticas.size());
            for (Estadistica estadistica : estadisticas) {
                list2.add((EstadisticaJPA) estadistica);
            }

            return list2;

        }
    }

    @Override
    public void crearEstadistica(Long entitatID, @NotNull int tipus, Long pluginID) throws I18NException {

        EstadisticaJPA estadisticaJPA = new EstadisticaJPA();


        estadisticaJPA.setDataEstadistica(new Timestamp(LogicUtils.novaDataSenseHora().getTime()));
        estadisticaJPA.setComptador(1);
        estadisticaJPA.setTipus(tipus);
        estadisticaJPA.setEntitatID(entitatID);
        estadisticaJPA.setPluginID(pluginID);

        create(estadisticaJPA);


    }




    @Override
    public void incrementarComptador(@NotNull EstadisticaJPA estadisticaJPA) throws I18NException{

        estadisticaJPA.setComptador(estadisticaJPA.getComptador() + 1);

        update(estadisticaJPA);

    }


}
