package ru.vmakarenko.dao;

import ru.vmakarenko.dao.generic.GenericDao;
import ru.vmakarenko.entities.events.thesis.Thesis;
import ru.vmakarenko.entities.users.WorkingPlace;

import javax.ejb.Stateless;
import java.util.List;
import java.util.UUID;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class ThesisDao extends GenericDao<Thesis> {
    public List<Thesis> getByEventAndUser(UUID eventId, UUID userId){
        return em.createQuery("select t from Thesis t join t.event e join t.user u where e.id = :eventId and u.id = :userId")
                .setParameter("eventId", eventId)
                .setParameter("userId", userId)
                .getResultList();
    }

}
