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
}
