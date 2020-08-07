package es.caib.carpeta.ejb.interceptor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Interceptor per loguejar les cridades als mètodes de la classe interceptada. El feim serializable perquè
 * es pugui aplicar a classes que perquè estiguin a un determinat scope hagin de ser serializables.
 * <p>
 * Cal tenir en compte que els interceptors s'han de declarar dins el fitxer META-INF/beans.xml del mòdul.
 *
 * @author areus
 */
@Logged
@Interceptor
public class LoggerInterceptor implements Serializable {

    private static final long serialVersionUID = 7109011370027722074L;

    private static final Logger LOG = LoggerFactory.getLogger(LoggerInterceptor.class);

    /**
     * Intercepta un mètode de negoci i fa un log a l'inici i al final.
     *
     * @param context Contexte d'invocació.
     * @return El resultat del mètode interceptar.
     * @throws Exception Llança la mateixa excepció que el mètode invocat.
     */
    @AroundInvoke
    public Object logCall(InvocationContext context) throws Exception {
        final String simpleName = context.getTarget().getClass().getSimpleName();
        final String methodName = simpleName + "." + context.getMethod().getName();
        final String callMessage = methodName + Arrays.toString(context.getParameters());
        LOG.debug(callMessage);

        Object result = context.proceed();

        final String returnMessage = methodName + " return(" + result + ")";
        LOG.debug(returnMessage);
        return result;
    }
}
