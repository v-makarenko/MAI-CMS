package ru.vmakarenko.common;

import ru.vmakarenko.dto.users.UserDto;

import java.util.HashMap;

/**
 * Created by VMakarenko on 4/22/2015.
 */
public class TokenService {
    private HashMap<String, UserEntry> tokenMap;

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

