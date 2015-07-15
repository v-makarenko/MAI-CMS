package ru.vmakarenko.dto.users;

/**
 * Created by VMakarenko on 4/27/2015.
 */
public class AccessAuthDto {
    public static final String PARAM_AUTH_EMAIL = "PARAM_AUTH_EMAIL";
    public static final String PARAM_AUTH_TOKEN = "PARAM_AUTH_TOKEN";
    private String email;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
