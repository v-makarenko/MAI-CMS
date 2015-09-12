package ru.vmakarenko.services;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@SessionScoped
public class CurrentService implements Serializable{
    private String email;
    private String token;

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
}
