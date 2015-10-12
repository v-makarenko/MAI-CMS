package ru.vmakarenko.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class SettingsDao {
    @PersistenceContext
    protected EntityManager em;

    public String get(String name){
        List rl = em.createNativeQuery("SELECT s.value from APP_SETTINGS s where name = :name").setParameter("name", name).getResultList();
        if(rl.size()==0){
            throw new IllegalArgumentException("Error key send for the parameter: " + name);
        }else{
            return (String)rl.get(0);
        }
    }

    public void set(String name, String value){
        em.createNativeQuery("INSERT INTO APP_SETTINGS (id, name, value) values (:id, :name, :value)")
                .setParameter("id", UUID.randomUUID())
                .setParameter("name", name)
                .setParameter("value", value)
                .executeUpdate();
    }

}
