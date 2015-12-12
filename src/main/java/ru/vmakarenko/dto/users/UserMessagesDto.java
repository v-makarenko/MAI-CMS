package ru.vmakarenko.dto.users;

import ru.vmakarenko.dto.common.CommonDto;

import java.util.UUID;

/**
 * Created by VMakarenko on 4/25/2015.
 */
public class UserMessagesDto extends UserDto {
    private Long unreadCount;

    public Long getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(Long unreadCount) {
        this.unreadCount = unreadCount;
    }
}
