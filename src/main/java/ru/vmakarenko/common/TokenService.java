package ru.vmakarenko.common;

import ru.vmakarenko.dto.users.UserDto;

import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by VMakarenko on 4/22/2015.
 */
@Singleton
@ApplicationScoped
public class TokenService {
    private Map<String, UserEntry> tokenMap = new HashMap<>();

    public void add(String token, UserDto userDto) {
        UserEntry entry = new UserEntry();
        entry.setUser(userDto);
        tokenMap.put(token, entry);
    }

    public void remove(String token) {
        tokenMap.remove(token);
    }

    public boolean check(String token, String email) {
        UserEntry entry = tokenMap.get(token);
        return entry != null && entry.getUser().getEmail().equals(email);
    }

    public UserDto get(String token) {
        return tokenMap.get(token).getUser();
    }

    class UserEntry {
        private UserDto user;

        public UserDto getUser() {
            return user;
        }

        public void setUser(UserDto user) {
            this.user = user;
        }
    }
}

