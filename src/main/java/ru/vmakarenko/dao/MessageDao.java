package ru.vmakarenko.dao;

import ru.vmakarenko.dao.generic.GenericDao;
import ru.vmakarenko.entities.messages.InnerMessage;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class MessageDao extends GenericDao<InnerMessage> {
    public List<InnerMessage> getAllIncoming(String email){
        return em.createQuery("select m from InnerMessage m where m.to.email=:email", InnerMessage.class)
                .setParameter("email", email).getResultList();
    }

}
