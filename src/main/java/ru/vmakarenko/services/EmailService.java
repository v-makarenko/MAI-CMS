package ru.vmakarenko.services;

import ru.vmakarenko.dao.EmailDao;
import ru.vmakarenko.dao.SettingsDao;
import ru.vmakarenko.dto.common.EmailMessageDto;
import ru.vmakarenko.entities.messages.EmailMessage;
import ru.vmakarenko.entities.users.User;

import javax.annotation.PostConstruct;
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
    @Inject
    private PasswordService passwordService;
    // TODO get setting saved
    @Inject
    private SettingsDao settingsDao;

    private Session mailSession;

    @PostConstruct
    public void postConstruct(){
        // TODO make centralized key store
        email = settingsDao.get("mail.my.email");
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

        properties.put("mail.smtp.connectiontimeout", "10000");
        properties.put("mail.smtp.timeout", "10000");
        mailSession = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(settingsDao.get("mail.my.username"),
                        settingsDao.get("mail.my.password"));
            }
        });
        mailSession.setDebug(false);
    }

    private String email, username, pass;

    public void sendMailToAll(EmailMessageDto message){
        addToSendQueue(message);
    }

    private void addToSendQueue(EmailMessageDto message){
        emailDao.insert(mapperService.map(message, EmailMessage.class));
    }

    public void sendPassword(User user, String pass){
        EmailMessageDto dto = new EmailMessageDto();
        dto.setTopic("Новый пароль");
        dto.setText("Здравствуйте, :surname :name!\n\nВаш новый пароль: :password"
                .replace(":name", user.getName())
                .replace(":surname", user.getSurname())
                .replace(":password", pass));
        dto.setTopic("Новый пароль");
        dto.getToList().add(user.getEmail());
        sendMailToAll(dto);
    }

    @Schedule(hour = "*", minute = "*", second = "*/30")
    public void sendAll(){
        List<EmailMessage> emailList = emailDao.findNotSent();
        for(EmailMessage item : emailList) {
            for (User user : item.getToList()) {
                try {
                    MimeMessage msg = new MimeMessage(mailSession);
                    msg.setSubject(item.getSubject(), "utf-8");
                    msg.setSentDate(new Date());
                    msg.setFrom(new InternetAddress(email));
                    msg.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(user.getEmail(), false));
                    msg.setText(item.getText(), "utf-8");
                    Transport.send(msg);
                    item.setSentStatus(true);
                    emailDao.update(item);
                } catch (Exception e) {
                    // TODO норм логгер
                    e.printStackTrace();
                }
            }
        }
    }

}
