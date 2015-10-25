package ru.vmakarenko.dto.users;

import java.util.UUID;

/**
 * Created by VMakarenko on 10/25/2015.
 */
public class ChangePasswordDto {
    private UUID userId;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
