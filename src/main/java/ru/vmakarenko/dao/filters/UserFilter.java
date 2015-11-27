package ru.vmakarenko.dao.filters;

import ru.vmakarenko.dao.anno.FilterParam;

import java.util.UUID;

/**
 * Created by VMakarenko on 4/26/2015.
 */
public class UserFilter extends BasicFilter {
    private String username;
    private String password;
    private String name;
    private String query;
    private UUID eventId;
    // TODO status


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }
}
