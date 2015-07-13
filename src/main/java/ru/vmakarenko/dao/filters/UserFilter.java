package ru.vmakarenko.dao.filters;

import ru.vmakarenko.dao.anno.FilterParam;

/**
 * Created by VMakarenko on 4/26/2015.
 */
public class UserFilter extends BasicFilter {
    @FilterParam
    private String username;
    @FilterParam
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public UserFilter username(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserFilter password(String password) {
        this.password = password;
        return this;
    }
}
