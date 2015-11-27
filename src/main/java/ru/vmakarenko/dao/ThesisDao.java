package ru.vmakarenko.dao;

import ru.vmakarenko.dao.generic.GenericDao;
import ru.vmakarenko.entities.events.thesis.Thesis;
import ru.vmakarenko.entities.users.WorkingPlace;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class ThesisDao extends GenericDao<Thesis> {
    public List<Thesis> getByEventAndUser(UUID eventId, UUID userId) {
        String sql = "select t from Thesis t join t.event e join t.user u where 1=1 ";
        if(eventId != null){
            sql+= " and e.id = :eventId";
        }
        if(userId != null){
            sql+= " and u.id = :userId";
        }
        TypedQuery<Thesis> typedQuery  = em.createQuery(sql, Thesis.class);
        if(eventId != null){
            typedQuery.setParameter("eventId", eventId);
        }
        if(userId != null){
            typedQuery.setParameter("userId", userId);
        }
        return typedQuery.getResultList();
    }

}
