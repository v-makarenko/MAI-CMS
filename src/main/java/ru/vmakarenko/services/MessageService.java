package ru.vmakarenko.services;

import ru.vmakarenko.dao.MessageDao;
import ru.vmakarenko.dao.UserDao;
import ru.vmakarenko.dto.common.MessageDto;
import ru.vmakarenko.entities.messages.InnerMessage;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

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
        InnerMessage msg = mapperService.map(messageDto, InnerMessage.class);
        msg.setFrom(userDao.getByEmail(currentService.getEmail()));
        msg.setTo(userDao.find(messageDto.getTo()));
        messageDao.insert(msg);
    }
}
