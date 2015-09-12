package ru.vmakarenko.dao;

import ru.vmakarenko.dao.generic.GenericDao;
import ru.vmakarenko.entities.messages.EmailTemplate;

import javax.ejb.Stateless;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class EmailTemplateDao extends GenericDao<EmailTemplate> {

}
