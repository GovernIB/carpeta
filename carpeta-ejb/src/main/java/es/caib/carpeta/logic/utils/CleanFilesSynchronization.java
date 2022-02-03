package es.caib.carpeta.logic.utils;

import java.util.Arrays;
import java.util.Set;

import javax.transaction.Status;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;

/**
 * 
 * @author anadal
 *
 */
public class CleanFilesSynchronization implements javax.transaction.Synchronization {

    public final Logger log = Logger.getLogger(this.getClass());

    protected final Set<Long> filesToDelete;

    public CleanFilesSynchronization(Set<Long> filesToDelete) {
        super();
        this.filesToDelete = filesToDelete;
    }

    @Override
    public void beforeCompletion() {
    }

    @Override
    public void afterCompletion(int status) {

        // log.error("Status.STATUS_COMMITTED: " + Status.STATUS_COMMITTED);
        // log.error("Passa per CleanFilesSynchronization::afterCompletion(status ==> "
        // + status + ")");

        if (status == Status.STATUS_COMMITTED) {
            if (!FileSystemManager.eliminarArxius(filesToDelete)) {
                log.error("No s'ha pogut esborrar alguns dels següents fitxers: "
                        + Arrays.toString(filesToDelete.toArray()));
            }
        }
    }
}
