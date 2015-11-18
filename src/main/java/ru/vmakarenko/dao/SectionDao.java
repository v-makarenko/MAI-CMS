package ru.vmakarenko.dao;

import ru.vmakarenko.dao.generic.GenericDao;
import ru.vmakarenko.entities.events.Section;
import ru.vmakarenko.entities.users.WorkingPlace;

import javax.ejb.Stateless;
import java.util.List;
import java.util.UUID;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class SectionDao extends GenericDao<Section> {
    public List<Section> findAllFiltered(UUID eventId){
        return em.createQuery("Select s from Section s join s.event e where e.id = :eventId").setParameter("eventId", eventId).getResultList();
    }
}
