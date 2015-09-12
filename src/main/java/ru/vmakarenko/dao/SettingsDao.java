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

    public String get(String key){
        List rl = em.createNativeQuery("SELECT s.value from APP_SETTINGS s where key = :key").setParameter("key", key).getResultList();
        if(rl.size()==0){
            throw new IllegalArgumentException("Error key send for the parameter: " + key);
        }else{
            return (String)rl.get(0);
        }
    }

    public void set(String key, String value){
        em.createNativeQuery("INSERT INTO APP_SETTINGS (id, key, value) values (:id, :key, :value)")
                .setParameter("id", UUID.randomUUID())
                .setParameter("key", key)
                .setParameter("value", value)
                .executeUpdate();
    }

}
