package ru.vmakarenko.services;

import ru.vmakarenko.dao.EmailTemplateDao;
import ru.vmakarenko.dto.common.EmailTemplateDto;
import ru.vmakarenko.entities.messages.EmailTemplate;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
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
