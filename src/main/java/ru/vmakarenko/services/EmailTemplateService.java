package ru.vmakarenko.services;

import org.apache.tomcat.util.http.mapper.Mapper;
import ru.vmakarenko.dao.EmailDao;
import ru.vmakarenko.dao.EmailTemplateDao;
import ru.vmakarenko.dao.SettingsDao;
import ru.vmakarenko.dto.common.EmailMessageDto;
import ru.vmakarenko.dto.common.EmailTemplateDto;
import ru.vmakarenko.entities.EmailMessage;
import ru.vmakarenko.entities.EmailTemplate;

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
import java.util.UUID;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class EmailTemplateService {
    @Inject
    private EmailTemplateDao emailDao;
    @Inject
    private MapperService mapperService;

    public void delete(UUID id){
        emailDao.trueDelete(id);
    }

    public void update(EmailTemplateDto template){
        emailDao.update(mapperService.map(template, EmailTemplate.class));
    }

    public void save(EmailTemplateDto template){
        emailDao.insert(mapperService.map(template, EmailTemplate.class));
    }

    public List<EmailTemplate> getAll(){
        return emailDao.findAll();
    }


}
