package ru.vmakarenko.dto.users;

import ru.vmakarenko.dto.common.CommonDto;

/**
 * Created by VMakarenko on 4/25/2015.
 */
public class UserAuthDto extends CommonDto {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
