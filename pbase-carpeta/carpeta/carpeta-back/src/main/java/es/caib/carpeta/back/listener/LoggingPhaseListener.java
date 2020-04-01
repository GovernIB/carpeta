package es.caib.carpeta.back.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * Classe d'utilitat per debug, permet veure quan comença i acaba cada fase de JSF. Cal que es registri o bé
 * amb un tag &lt;f:phaseListener&gt; a les pàgines JSF o bé fins el fitxer faces-config.xml.
 *
 * @author areus
 */
public class LoggingPhaseListener implements PhaseListener {

    private static final long serialVersionUID = 8891688142408982273L;

    private static final Logger LOG = LoggerFactory.getLogger(LoggingPhaseListener.class);

    /**
     * S'executa abans de començar una fase.
     *
     * @param phaseEvent Esdeveniment de fase.
     */
    @Override
    public void afterPhase(PhaseEvent phaseEvent) {
        LOG.debug("Acabant: " + phaseEvent.getPhaseId().getName());
    }

    /**
     * S'executa després d'acabar una fase.
     *
     * @param phaseEvent Esdeveniment de fase.
     */
    @Override
    public void beforePhase(PhaseEvent phaseEvent) {
        LOG.debug("Iniciant: " + phaseEvent.getPhaseId().getName());
    }

    /**
     * Indica les fases que es volen rebre, en aquest cas, totes.
     *
     * @return el valor especial ANY_PHASE per indicar que vol rebre els esdeveniments de totes les fases.
     */
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}
