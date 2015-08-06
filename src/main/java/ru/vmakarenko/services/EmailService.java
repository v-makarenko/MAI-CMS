package ru.vmakarenko.services;

import ru.vmakarenko.dao.EmailDao;
import ru.vmakarenko.dao.SettingsDao;
import ru.vmakarenko.dto.common.EmailMessageDto;
import ru.vmakarenko.entities.EmailMessage;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class EmailService {
    @Inject
    private EmailDao emailDao;
    @Inject
    private MapperService mapperService;
    @Inject
    private UserService userService;
    // TODO get setting saved
    @Inject
    private SettingsDao settingsDao;

    private Session mailSession;

    @PostConstruct
    public void postConstruct(){
        // TODO make centralized key store
        email = settingsDao.get("mail.my.address");
        username = settingsDao.get("mail.my.username");
        pass = settingsDao.get("mail.my.password");
        Properties properties = System.getProperties();

        // TODO move to common config java class
        properties.put("mail.transport.protocol", "smtp");

        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port",  settingsDao.get("mail.my.port"));
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");

        properties.put("mail.smtp.host", settingsDao.get("mail.my.host"));
        properties.put("mail.smtp.port", settingsDao.get("mail.my.port"));
        properties.put("mail.smtp.user", settingsDao.get("mail.my.username"));
        properties.put("mail.smtp.password", settingsDao.get("mail.my.password"));
        mailSession = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(settingsDao.get("mail.my.username"),
                        settingsDao.get("mail.my.password"));
            }
        });
    }

    private String email, username, pass;

    public void sendMailToAll(EmailMessageDto message){
        // TODO send really to all, now to me
        message.setTo("vladimir@makarenko.io");
        addToSendQueue(message);
    }

    private void addToSendQueue(EmailMessageDto message){
        emailDao.insert(mapperService.map(message, EmailMessage.class));

    }

    @Schedule(hour = "*", minute = "*", second = "*/10")
    public void sendAll(){
        List<EmailMessage> emailList = emailDao.findNotSent();
        for(EmailMessage item : emailList) {
            try {
                Message msg = new MimeMessage(mailSession);
                msg.setSubject(item.getSubject());
                msg.setSentDate(new Date());
                msg.setFrom(new InternetAddress(email));
                    msg.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(item.getTo(), false));

                msg.setText(item.getText());
                Transport.send(msg);
                item.setSent(true);
                emailDao.update(item);
            } catch (Exception e) {
                // TODO норм логгер
                e.printStackTrace();
            }

        }
    }

}
