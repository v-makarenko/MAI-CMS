package ru.vmakarenko.services;

import ru.vmakarenko.dao.MessageDao;
import ru.vmakarenko.dao.UserDao;
import ru.vmakarenko.dto.common.MessageDto;
import ru.vmakarenko.dto.users.UserMessagesDto;
import ru.vmakarenko.entities.messages.InnerMessage;
import ru.vmakarenko.entities.users.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class MessageService {
    @Inject
    private CurrentService currentService;
    @Inject
    private MessageDao messageDao;
    @Inject
    private MapperService mapperService;
    @Inject
    private UserDao userDao;

    public List<MessageDto> getAllMessages(UUID fromId, boolean forAdmin){
        User currentToUser = forAdmin ? userDao.find(UUID.fromString("00000000-0000-0000-0000-000000000002")) : userDao.getByEmail(currentService.getEmail());
        return mapperService.map(messageDao
                .getAllIncoming(currentToUser.getEmail(), fromId), MessageDto.class);
    }

    public void sendMsg(MessageDto messageDto) {
        InnerMessage msg = mapperService.map(messageDto, InnerMessage.class);
        msg.setFrom(userDao.find(messageDto.getFromId()));
        msg.setTo(userDao.find(messageDto.getToId()));
        messageDao.insert(msg);
    }

    public List<UserMessagesDto> getUserList() {
        return userDao.getUsersWithUnread(UUID.randomUUID());
    }
}
