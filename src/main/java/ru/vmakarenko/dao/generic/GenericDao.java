package ru.vmakarenko.dao.generic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vmakarenko.dao.filters.CommonFilter;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.UUID;

/**
 * Created by User on 25.02.2015.
 */

/**
 * Generic dao.
 * Чтобы создать новый DAO следует отнаследоваться от данного класса
 *
 * @param <T> класс, для которого создается DAO
 */

@Stateless
public class GenericDao<T> {
    @PersistenceContext
    protected EntityManager em;
    Class<T> entityClass;
    Logger LOG = LoggerFactory.getLogger(GenericDao.class);

    public Class getEntityClass() {
        if (entityClass == null) {
            //only works if one extends DomainEntity, we will take care of it with CDI
            entityClass = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return entityClass;
    }

    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Получить объект из БД
     *
     * @param id id объекта
     * @return запрашиваемый объект или null, если такого нет
     */
     public T find(UUID id) {
        Object o = this.em.getReference(getEntityClass(), id);
        return o != null ? (T) o : null;
    }

//
//    /**
//     * Выставить статус как inactive
//     *
//     * @param id id объекта
//     * @return true если объект успешно удален, false в ином случае
//     */
//    public boolean delete(UUID id) {
//        Object ref = null;
//        try {
//            ref = this.em.getReference(getEntityClass(), id);
//        } catch (Exception e) {
//            LOG.error("Сущность с id = :id не найдена для удаления".replace(":id", id.toString()));
//        }
//        if (ref != null) {
//            if (ref instanceof DomainEntity) {
//                ((DomainEntity) ref).setStatus(EntityStatus.DELETED.toString());
//            }
//            return true;
//        } else {
//            return false;
//        }
//    }

    /**
     * Реально удалить объект из бд
     *
     * @param id id объекта
     */
    public boolean trueDelete(UUID id) {
        Object ref = this.em.getReference(getEntityClass(), id);
        if (ref != null) {
            em.remove(ref);
            return true;
        } else {
            return false;
        }

    }

    /**
     * Обновить объект в БД. Возможно, требует кастомной обработки
     *
     * @param t объект для обновления
     * @return обновленная версия объекта
     */
    public T update(T t) {
        return (T) em.merge(t);
    }


    /**
     * Создать новую сущность в БД
     *
     * @param t объект для вставки
     */
    public T insert(T t) {
        return em.merge(t);
    }

    /**
     * получить все неудаленные объекты заданного класса T
     *
     * @return список найденных объектов
     */
    public List<T> findActive() {
        return em.createQuery("Select entity FROM " + getEntityClass().getSimpleName() + " entity where entity.status like 'ACTIVE'").getResultList();
    }

    /**
     * получить все объекты заданного класса T
     *
     * @return список найденных объектов
     */
    public List<T> findAll() {
        return em.createQuery("Select entity FROM " + getEntityClass().getSimpleName() + " entity").getResultList();
    }

    /**
     * получить все неудаленные объекты заданного класса T с фильтрацией
     *
     * @param filter фильтр
     * @return список найденных объектов
     */
    public List<T> findActive(CommonFilter filter) {
        return findActivePaged(filter);
    }

    /**
     * Получить все неудаленные объекты заданного класса T с фильтрацией
     *
     * @param filter      фильтр
     *                    если значение limit == 0, то ограничение не устанавливается
     * @return
     */
    public List<T> findActivePaged(CommonFilter filter) {
        TypedQuery<T> typedQuery = em.createQuery(getActivePagedQuery(filter, "entity", getEntityClass().getSimpleName()), getEntityClass());

        Long page = filter.getPage();
        Long limit = filter.getLimit();

        if (page != null && limit != null) {
            typedQuery.setFirstResult((int) ((page - 1) * limit));
            typedQuery.setMaxResults(filter.getLimit().intValue());
        }

        filter.setParams(typedQuery);
        return typedQuery.getResultList();
    }

    public Long findActiveCount(CommonFilter filter) {

        TypedQuery<Long> typedQuery = em.createQuery(getActivePagedQuery(filter, "count(entity.name)", getEntityClass().getSimpleName()), Long.class);
        filter.setParams(typedQuery);

        return typedQuery.getSingleResult();
    }

    protected String getActivePagedQuery(CommonFilter filter, String target, String type) {
        return String.format("Select %s FROM %s entity"
                + filter.getJoinClause()
                + filter.getWhereClause() + " and entity.status like 'ACTIVE'" + filter.getOrderByClause(), target, type);
        // только для неудаленных и не скрытых записей, если надо будет что-то еще, лучше написать отдельный метод

    }

    /**
     * получить все объекты заданного класса T с фильтрацией
     *
     * @param filter фильтр
     * @return список найденных объектов
     */
    public List<T> findAll(CommonFilter filter) {
        TypedQuery<T> typedQuery = em.createQuery("Select entity FROM " + getEntityClass().getSimpleName() + " entity"
                + filter.getJoinClause()
                + filter.getWhereClause() + filter.getOrderByClause(), getEntityClass()); // только для неудаленных и не скрытых записей, если надо будет что-то еще, лучше написать отдельный метод
        filter.setParams(typedQuery);
        return typedQuery.getResultList();
    }
}
