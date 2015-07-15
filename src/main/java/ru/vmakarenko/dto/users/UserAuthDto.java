package ru.vmakarenko.dto.users;

import ru.vmakarenko.dto.common.CommonDto;

/**
 * Created by VMakarenko on 4/25/2015.
 */
public class UserAuthDto extends CommonDto {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
