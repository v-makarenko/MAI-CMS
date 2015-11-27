package ru.vmakarenko.dao;

import ru.vmakarenko.dao.filters.UserFilter;
import ru.vmakarenko.dao.generic.GenericDao;
import ru.vmakarenko.entities.users.User;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class UserDao extends GenericDao<User> {
    public User getByEmailAndPassword(String email, String password) {
        List<User> userList = em.createQuery("select u from User u where u.email = :email and u.passwordHash = :password", User.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getResultList();
        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    public User getByEmail(String email) {
        List<User> userList = em.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList();
        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    public List<User> getFiltered(UserFilter filter) {
        String sql = "";
        if (filter.getEventId() != null) {
            sql = " select u from Event e join e.userList u where e.id = :eventId";
        } else {
            sql = "select u from User u where  1=1 ";
        }
        TypedQuery<User> query = em.createQuery(sql, User.class);
        if (filter.getEventId() != null) {
            query.setParameter("eventId", filter.getEventId());
        }
        return query.getResultList();
    }
}
