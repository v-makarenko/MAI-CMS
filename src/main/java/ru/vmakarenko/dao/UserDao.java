package ru.vmakarenko.dao;

import ru.vmakarenko.dao.generic.GenericDao;
import ru.vmakarenko.entities.User;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class UserDao extends GenericDao<User> {
    public User getByEmailAndPassword(String email, String password){
        List<User> userList = em.createQuery("select u from User u where u.email = :email and u.password = :password", User.class).getResultList();
        if(userList.size() > 0 ){
            return userList.get(0);
        }else{
            return null;
        }
    }
}
