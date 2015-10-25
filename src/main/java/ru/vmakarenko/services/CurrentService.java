package ru.vmakarenko.services;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@SessionScoped
public class CurrentService implements Serializable{
    private String email;
    private String token;
    private UUID id;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
