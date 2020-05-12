package es.caib.carpeta.persistence.dao;

import es.caib.carpeta.commons.i18n.I18NException;
import es.caib.carpeta.commons.query.OrderBy;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Defineix les operacions dels Data Access Object per una entitat.
 *
 * @param <E>  Tipus de l'entitat.
 * @param <PK> Tipus de la clau primària de l'entitat.
 * @author anadal
 * @author areus
 */
public interface DAO<E extends Serializable, PK> {

    /**
     * Crea un entity.
     *
     * @param entity l'entity a crear.
     * @return El entity creat.
     */
    E create(@NotNull E entity) throws I18NException;

    /**
     * Actualitza un entity.
     *
     * @param entity l'entity a actualitzar
     * @return entity actualitzat.
     */
    E update(@NotNull E entity) throws I18NException;

    /**
     * Esborra un entity a partir de l'identificador indicat.
     *
     * @param id Identificador del entity
     */
    void delete(@NotNull PK id) throws I18NException;

    /**
     * Obté un entity amb l'identificador indicat.
     *
     * @param id identificador del entity.
     * @return El entity o <code>null</code> sino n'hi ha cap entitat amb l'id indicat.
     */
    E findById(@NotNull PK id);

    /**
     * Retorna el nombre total d'entitats.
     *
     * @return nombre total d'entitats.
     */
    long countAll();

    /**
     * Retorna el nombre total d'entitats que compleixen els filtres indicats.
     *
     * @param filters map on les claus són el nom d'atribut i el valor pel qual s'ha de filtrar.
     * @return nombre d'entitats que compleixen el filtre.
     */
    long countFiltered(Map<String, Object> filters);

    /**
     * Obté totes les entitats.
     *
     * @param orderBy ordenacions que s'aplicaran amb l'ordre indicat.
     * @return llista d'entitats ordenada amb els criteris indicats.
     * @throws I18NException si es produeix qualsevol error d'accés a les dades.
     */
    List<E> findAll(OrderBy... orderBy) throws I18NException;

    /**
     * Obté les entitats que compleixen el filtre indicat.
     *
     * @param filters map on les claus són el nom d'atribut i el valor pel qual s'ha de filtrar.
     * @param orderBy ordenacions que s'aplicaran amb l'ordre indicat.
     * @return llista d'entitats ordenada amb els criteris indicats.
     * @throws I18NException si es produeix qualsevol error d'accés a les dades.
     */
    List<E> findFiltered(Map<String, Object> filters, OrderBy... orderBy) throws I18NException;

    /**
     * Obté les entitats dins el rang indicat.
     *
     * @param first    primer resultat a retornar. El primer és 0.
     * @param pageSize nombre màxim de resultats a retornar.
     * @param orderBy  ordenacions que s'aplicaran amb l'ordre indicat.
     * @return llista d'entitats dins el rang indicat ordenada amb els criteris indicats.
     * @throws I18NException si es produeix qualsevol error d'accés a les dades.
     */
    List<E> findAll(@PositiveOrZero int first, @Positive int pageSize, OrderBy... orderBy)
            throws I18NException;


    /**
     * Obté les entitats dins el rang indicat que compleixen els filtres.
     *
     * @param filters  map on les claus són el nom d'atribut i el valor pel qual s'ha de filtrar.
     * @param first    primer resultat a retornar. El primer és 0.
     * @param pageSize nombre màxim de resultats a retornar.
     * @param orderBy  ordenacions que s'aplicaran amb l'ordre indicat.
     * @return llista d'entitats dins el rang indicat que compleixen els filtres ordenada amb els criteris indicats.
     * @throws I18NException si es produeix qualsevol error d'accés a les dades.
     */
    List<E> findFiltered(Map<String, Object> filters, @PositiveOrZero int first, @Positive int pageSize,
                         OrderBy... orderBy)
            throws I18NException;

    /**
     * Obté una entitat en forma de referència, per tant sense carregar les seves dades. Emprat
     * bàsicament per fixar claus foranes.
     *
     * @param id Identificador de l'entitat a carregar.
     * @return L'entitat en forma de referència.
     */
    E getReference(@NotNull PK id);
}