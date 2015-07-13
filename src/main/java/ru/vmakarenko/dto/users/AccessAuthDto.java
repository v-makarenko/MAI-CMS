package ru.vmakarenko.dto.users;

/**
 * Created by VMakarenko on 4/27/2015.
 */
public class AccessAuthDto {
    public static final String PARAM_AUTH_ID = "PARAM_AUTH_ID";
    public static final String PARAM_AUTH_TOKEN = "PARAM_AUTH_TOKEN";
    private String id;
    private String token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
