package ru.vmakarenko.dao;

import ru.vmakarenko.dao.generic.GenericDao;
import ru.vmakarenko.entities.messages.InnerMessage;

import javax.ejb.Stateless;
import java.util.List;
import java.util.UUID;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class MessageDao extends GenericDao<InnerMessage> {
    public List<InnerMessage> getAllIncoming(String currentUserEmail, UUID fromUserId){
        return em.createQuery("select m from InnerMessage m where m.to.email=:email and (m.from.id=:fromUserId or m.to.id=:fromUserId)", InnerMessage.class)
                .setParameter("email", currentUserEmail)
                .setParameter("fromUserId", fromUserId)
                .getResultList();
    }

    public List<InnerMessage> getAdminMail( UUID fromUserId){
        return getAllIncoming("admin@admin.ru", fromUserId);
    }

}
