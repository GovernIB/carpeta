package es.caib.carpeta.logic;

import es.caib.carpeta.ejb.EstadisticaEJB;
import es.caib.carpeta.jpa.EstadisticaJPA;
import es.caib.carpeta.model.entity.Estadistica;
import es.caib.carpeta.model.fields.EstadisticaFields;

import java.sql.Timestamp;
import java.util.*;

import org.fundaciobit.genapp.common.i18n.I18NException;
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
    public List<EstadisticaJPA> findByTipus(@NotNull Integer tipus)throws I18NException {

        List<Estadistica> estadisticas = select(EstadisticaFields.TIPUS.equal(tipus)); ;

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
    public void crearActualizarEstadistica(Long entitatID, @NotNull int tipus, Long pluginID) throws I18NException {

        EstadisticaJPA estadisticaJPA = new EstadisticaJPA();

        List<EstadisticaJPA> estadisticas = findByTipus(tipus);

        if(estadisticas != null && !estadisticas.isEmpty()){ // Si ja està creada
            estadisticaJPA = estadisticas.get(0);

            incrementarComptador(estadisticaJPA);
        }else{
            estadisticaJPA.setDataEstadistica(new Timestamp(dataSenseHora().getTime()));
            estadisticaJPA.setComptador(1);
            estadisticaJPA.setTipus(tipus);
            estadisticaJPA.setEntitatID(entitatID);
            estadisticaJPA.setPluginID(pluginID);

            create(estadisticaJPA);
        }

    }


    private void incrementarComptador(@NotNull EstadisticaJPA estadisticaJPA) throws I18NException{

        estadisticaJPA.setComptador(estadisticaJPA.getComptador() + 1);

    }



    private Date dataSenseHora(){

        Date date = new Date();                      // timestamp now
        Calendar cal = Calendar.getInstance();       // get calendar instance
        cal.setTime(date);                           // set cal to date
        cal.set(Calendar.HOUR_OF_DAY, 0);            // set hour to midnight
        cal.set(Calendar.MINUTE, 0);                 // set minute in hour
        cal.set(Calendar.SECOND, 0);                 // set second in minute
        cal.set(Calendar.MILLISECOND, 0);            // set millis in second
        return  cal.getTime();

    }
}
