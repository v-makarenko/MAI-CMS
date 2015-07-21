package ru.vmakarenko.dao;

import ru.vmakarenko.dao.generic.GenericDao;
import ru.vmakarenko.entities.Message;
import ru.vmakarenko.entities.User;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class MessageDao extends GenericDao<Message> {
    public List<Message> getAllIncoming(String email){
        return em.createQuery("select m from Message m where m.to.email=:email", Message.class)
                .setParameter("email", email).getResultList();
    }

}
