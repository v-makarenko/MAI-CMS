package ru.vmakarenko.services;

import ru.vmakarenko.dao.EmailDao;
import ru.vmakarenko.dao.EmailTemplateDao;
import ru.vmakarenko.dao.SettingsDao;
import ru.vmakarenko.dto.common.EmailMessageDto;
import ru.vmakarenko.dto.common.MessageDto;
import ru.vmakarenko.entities.EmailMessage;
import ru.vmakarenko.entities.EmailTemplate;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class EmailDistributionService {
    @Inject
    private EmailService emailService;
    @Inject
    private EmailTemplateDao emailTemplateDao;

    public void distribute(){
        EmailMessageDto messageDto = new EmailMessageDto();
        messageDto.setTo("vladimir@makarenko.io");
        messageDto.setText("Sample text \n\nSample text");
        emailService.sendMailToAll(messageDto);
    }

    public void distributeWithTemplate(UUID id) {
        EmailTemplate template = emailTemplateDao.find(id);
        EmailMessageDto messageDto = new EmailMessageDto();
        messageDto.setTopic(messageDto.getTopic());
        messageDto.setTo("vladimir@makarenko.io");
        messageDto.setText(template.getText().replaceAll(":email", "vladimir@makarenko.io"));
        emailService.sendMailToAll(messageDto);
    }
}
