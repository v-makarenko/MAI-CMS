package ru.vmakarenko.dao;

import ru.vmakarenko.dao.generic.GenericDao;
import ru.vmakarenko.entities.messages.EmailMessage;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class EmailDao extends GenericDao<EmailMessage> {

    public List<EmailMessage> findNotSent() {
        return em.createQuery("select M from EmailMessage M where M.sent = false", EmailMessage.class).getResultList();
    }
}
