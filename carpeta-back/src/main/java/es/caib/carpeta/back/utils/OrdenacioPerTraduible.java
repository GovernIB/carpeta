package es.caib.carpeta.back.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;

/**
 * 
 * @author anadal
 *
 * @param <T>
 * @param <PK>
 */
public abstract class OrdenacioPerTraduible<T, PK> {

    public abstract PK getPK(T o1);

    public void ordenar(List<T> items, Field<?> field, Map<PK, String> traduiblePerPK, final OrderBy[] orderBy) {

        if (orderBy != null && orderBy.length != 0) {

            if (orderBy[0].javaName.equals(field.fullName) || orderBy[0].javaName.equals(field.javaName)) {
                Collections.sort(items, new TraduibleComparator(traduiblePerPK, orderBy[0].orderType));
            }
        }

    }

    public class TraduibleComparator implements Comparator<T> {

        protected final Map<PK, String> traduiblePerPK;

        protected final int order;

        public TraduibleComparator(Map<PK, String> traduiblePerPK, OrderType orderType) {
            super();
            this.traduiblePerPK = traduiblePerPK;
            this.order = orderType == (OrderType.ASC) ? 1 : -1;
        }

        @Override
        public int compare(T o1, T o2) {

            String n1 = traduiblePerPK.get(getPK(o1));
            String n2 = traduiblePerPK.get(getPK(o2));

            if (n1 == null) {
                throw new RuntimeException(
                        "NO existeix la traducció per la PK " + getPK(o1) + " de la classe " + o1.getClass());
            }
            return order * n1.compareToIgnoreCase(n2);
        }

    }
}
