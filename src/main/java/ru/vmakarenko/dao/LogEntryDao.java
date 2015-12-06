package ru.vmakarenko.dao;

import ru.vmakarenko.dao.generic.GenericDao;
import ru.vmakarenko.entities.events.thesis.Thesis;
import ru.vmakarenko.entities.messages.EmailMessage;
import ru.vmakarenko.entities.users.LogEntry;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class LogEntryDao extends GenericDao<LogEntry> {
    public List<LogEntry> getAll(UUID userId, Date dateFrom, Date dateTo) {

        String sql = "select le from LogEntry le join le.user u where 1=1 ";
        if (userId != null) {
            sql += " and u.id = :userId";
        }
        if (dateFrom != null) {
            sql += " and le.time = :dateFrom";
        }
        if (dateTo != null) {
            sql += " and le.time = :dateTo";
        }
        sql += " order by le.time desc";
        TypedQuery<LogEntry> typedQuery = em.createQuery(sql, LogEntry.class);
        if (dateFrom != null) {
            typedQuery.setParameter("dateFrom", dateFrom);
        }
        if (dateTo != null) {
            typedQuery.setParameter("dateTo", dateTo);
        }
        if (userId != null) {
            typedQuery.setParameter("userId", userId);
        }

        return typedQuery.getResultList();
    }
}
