package ru.vmakarenko.services;

import ru.vmakarenko.common.RestResponse;
import ru.vmakarenko.dao.MessageDao;
import ru.vmakarenko.dao.UserDao;
import ru.vmakarenko.dto.common.MessageDto;
import ru.vmakarenko.dto.users.AccessAuthDto;
import ru.vmakarenko.dto.users.UserDto;
import ru.vmakarenko.dto.users.UserSignUpDto;
import ru.vmakarenko.entities.Message;
import ru.vmakarenko.entities.User;
import ru.vmakarenko.util.Util;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<MessageDto> getAllIncoming(){
        return mapperService.map(messageDao
                .getAllIncoming(currentService.getEmail()), MessageDto.class);
    }

    public void sendMsg(MessageDto messageDto) {
        Message msg = mapperService.map(messageDto, Message.class);
        msg.setFrom(userDao.getByEmail(currentService.getEmail()));
        msg.setTo(userDao.find(messageDto.getTo()));
        messageDao.insert(msg);
    }
}
