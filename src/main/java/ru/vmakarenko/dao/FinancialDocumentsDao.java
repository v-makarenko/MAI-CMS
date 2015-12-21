package ru.vmakarenko.dao;

import ru.vmakarenko.dao.generic.GenericDao;
import ru.vmakarenko.entities.events.Section;
import ru.vmakarenko.entities.events.financial.FinancialDocument;

import javax.ejb.Stateless;
import java.util.List;
import java.util.UUID;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class FinancialDocumentsDao extends GenericDao<FinancialDocument> {
    public List<FinancialDocument> getForEvent(UUID eventId, UUID userId) {
        return em.createQuery("select fd from FinancialDocument fd where fd.type.event.id = :eventId and fd.user.id = :userId and fd.type.active = true", FinancialDocument.class)
                .setParameter("eventId", eventId)
                .setParameter("userId", userId)
                .getResultList();
    }
}
